package com.eason.report.pull.platform.bbin.mgr;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eason.report.pull.core.mgr.IPullMgr;
import com.eason.report.pull.core.utils.DateUtil;
import com.eason.report.pull.platform.bbin.dao.po.DsBbinGameConfigPo;
import com.eason.report.pull.platform.bbin.exception.BBINException;
import com.eason.report.pull.platform.bbin.mgo.DSBBINMgoPo;
import com.eason.report.pull.platform.bbin.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Slf4j
public class DSBBINMgr implements IPullMgr<DSBBINMgoPo, DsBbinGameConfigPo> {
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private MongoTemplate mongoTemplate;
  @Autowired
  private StringRedisTemplate stringRedisTemplate10;

  @Override
  public DSBBINMgoPo extAttr(DSBBINMgoPo po, DsBbinGameConfigPo configPo) {
    po.setAgentId(configPo.getAgentId());
    for(Map.Entry<Integer,String> site:configPo.getSiteMap().entrySet()){
      if(po.getUserName().startsWith(site.getValue())){
        po.setSiteId(site.getKey());
        po.setUserName(po.getUserName().substring(site.getValue().length()));
      }
    }
    //判断输赢
    if(po.getPayOff().doubleValue() >0){
      po.setWinLossType(Byte.valueOf("2"));//赢
    }else if(po.getPayOff().doubleValue() < 0){
      po.setWinLossType(Byte.valueOf("1"));//输
    }else if(po.getPayOff().doubleValue() == 0){
      po.setWinLossType(Byte.valueOf("3"));//和
    }
    po.setCreateTime(new Date());
    return po;
  }

  @Transactional
  @Override
  public void saveAndUpdate(DSBBINMgoPo po, DsBbinGameConfigPo configPo){
    po=this.extAttr(po,configPo);
    Optional<DSBBINMgoPo> result =mongoTemplate.update(DSBBINMgoPo.class)
            .matching(query(where("wagersId").is(po.getWagersId())))
            .replaceWith(po)
            .withOptions(FindAndReplaceOptions.options().upsert())
            .findAndReplace();
  }

  @Override
  public Timestamp getMaxId(DsBbinGameConfigPo configPo){
    String day=this.getBBINRounddate(configPo);
    return DateUtil.covertTime(day+" 23:59:59");
  }

  @Override
  public Timestamp getNextId(DsBbinGameConfigPo configPo) {
    String day=this.getBBINRounddate(configPo);
    return DateUtil.covertTime(day+" 00:00:00");
  }
  /**
   *  BBIN
   *  涉及分页操作：有数据分页结构
   *  gamekind 必须参数 游戏种类 (1：BB 体育、3：BB 视讯、5：BB 电子、99：BB 小费
   *  subgamekind 不是必须参数 (gamekind=5 时，值:1、2、3、5，预设为 1)
   */
  public JSONArray loadDataToEndTime(DsBbinGameConfigPo configPo) throws BBINException {
    try {
      JSONArray jsonArray=new JSONArray();
      Date startDate= getNextId(configPo);
      Date endDate= getMaxId(configPo);

      ExecutorService executorService= Executors.newFixedThreadPool(8);
      CountDownLatch cdl = new CountDownLatch(8);
      for(int i=0;i<8;i++){
        Integer gamekind=null;
        Integer subgamekind=null;
        String uri=null;
        switch(i){
          case 0:uri="BetRecord";gamekind=1;break;
          case 1:uri="BetRecord";gamekind=3;break;
          case 2:uri="BetRecord";gamekind=5;subgamekind=1;break;
          case 3:uri="BetRecord";gamekind=5;subgamekind=2;break;
          case 4:uri="BetRecord";gamekind=5;subgamekind=3;break;
          case 5:uri="BetRecord";gamekind=5;subgamekind=5;break;
          case 6:uri="WagersRecordBy30";gamekind=30;break;
          case 7:uri="WagersRecordBy38";gamekind=38;break;
        }
        Future<JSONArray> future=executorService.submit(new Callable<JSONArray>() {
          private Integer gamekind;
          private Integer subgamekind;
          private String uri;
          public Callable accept(Integer gamekind,Integer subgamekind,String uri){
            this.gamekind=gamekind;
            this.subgamekind=subgamekind;
            this.uri=uri;
            return this;
          }
          @Override
          public JSONArray call() throws Exception {
            try {
              if(!executorService.isShutdown()) {
                if(gamekind!=30 && gamekind!=38){
                  JSONArray jsonArray=loadDataAllBBIN(startDate,endDate,configPo,gamekind,subgamekind,uri);
                  return jsonArray;
                }else{
                  JSONArray jsonArray=loadDataAllWagers(startDate,endDate,configPo,gamekind,uri);
                  return jsonArray;
                }


              }
            }catch (Exception e) {
              e.printStackTrace();
              log.error("站点{}拉取数据失败,错误消息={},请检查配置={}", configPo.getAgentId(),  e.getMessage(), configPo);
            } finally {
              cdl.countDown();
            }

            return null;
          }
        }.accept(gamekind,subgamekind,uri));
        try{
          JSONArray jsonArray0=future.get();
          if(jsonArray0!=null && !jsonArray0.isEmpty()){
            jsonArray.addAll(jsonArray0);
          }
        }catch (ExecutionException e) {
          e.printStackTrace();
        }catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      return jsonArray;
    } catch (Exception e) {
      throw new BBINException(e);
    }
  }

  public JSONArray loadDataAllBBIN(Date startDate, Date endDate, DsBbinGameConfigPo configPo,
                                   Integer gamekind,Integer subgamekind,String uri) throws BBINException {
    try {
      JSONArray jsonArray=new JSONArray();
      String day=getBBINRounddate(configPo);
      String key=String.format("%s_bbin_page_%s_%s%s",day,configPo.getAgentId(),gamekind,subgamekind==null?"":subgamekind);
      Object obj=stringRedisTemplate10.opsForValue().get(key);
      String pageInfo=(String)(obj==null?"{\"TotalPage\":0,\"TotalNumber\":\"0\",\"Page\":1,\"PageLimit\":"+configPo.getLength()+"}":obj);//起始值page=1
      JSONObject pagination=JSONObject.parseObject(pageInfo);
      Integer page=pagination.getInteger("Page");
      Integer totalPage=pagination.getInteger("TotalPage");
      if(page<totalPage){
        page++;
      }

      log.info("DS-BBIN从key="+key+"开始准备拉取page={}",page);
      JSONObject jsonObject=this.getRecordBBIN(startDate,endDate,configPo,gamekind,subgamekind,uri,page,day);
      if(jsonObject.getBoolean("result")){
        if(jsonObject.getJSONArray("data")!=null && !jsonObject.getJSONArray("data").isEmpty()){
          jsonArray.addAll(jsonObject.getJSONArray("data"));
        }
        JSONObject pagination0=jsonObject.getJSONObject("pagination");
        stringRedisTemplate10.opsForValue().set(key,pagination0.toJSONString(),2, TimeUnit.DAYS); //2天后失效
      }
      return jsonArray;
    } catch (Exception e) {
      throw new BBINException(e);
    }
  }

  public JSONArray loadDataAllWagers(Date startDate, Date endDate, DsBbinGameConfigPo configPo,Integer gamekind,String uri) throws BBINException {
    try {
      JSONArray jsonArray=new JSONArray();
      String day=getBBINRounddate(configPo);
      String key=String.format("%s_bbin_page_%s_%s",day,configPo.getAgentId(),gamekind);
      Object obj=stringRedisTemplate10.opsForValue().get(key);
      String pageInfo=(String)(obj==null?"{\"TotalPage\":0,\"TotalNumber\":\"0\",\"Page\":1,\"PageLimit\":"+configPo.getLength()+"}":obj);//起始值page=1
      JSONObject pagination=JSONObject.parseObject(pageInfo);
      Integer page=pagination.getInteger("Page");
      Integer totalPage=pagination.getInteger("TotalPage");
      if(page<totalPage){
        page++;
      }

      log.info("DS-BBIN从key="+key+"开始准备拉取page={}",page);
      JSONObject jsonObject=this.getRecordWagers(startDate,endDate,configPo,uri,page,day);
      if(jsonObject.getBoolean("result")){
        if(jsonObject.getJSONArray("data")!=null && !jsonObject.getJSONArray("data").isEmpty()){
          jsonArray.addAll(jsonObject.getJSONArray("data"));
        }
        JSONObject pagination0=jsonObject.getJSONObject("pagination");
        stringRedisTemplate10.opsForValue().set(key,pagination0.toJSONString(),2, TimeUnit.DAYS); //2天后失效
      }
      return jsonArray;
    } catch (Exception e) {
      throw new BBINException(e);
    }
  }

  public JSONObject getRecordBBIN(Date startDate, Date endDate, DsBbinGameConfigPo configPo,
                                   Integer gamekind,Integer subgamekind,String uri,Integer page,String day) throws BBINException {
    try {
      if(!DateUtils.isSameDay(startDate,endDate)){
        throw new BBINException(String.format("BBIN拉取出错,起始时间不一致,错误信息=%s-%s",startDate,endDate));
      }
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

      Map<String, String> request= new TreeMap<>();
      request.put("uppername", configPo.getAgentId());
      request.put("rounddate",day);
      request.put("starttime", DateUtil.covertStr(startDate,"HH:mm:ss"));
      request.put("endtime", DateUtil.covertStr(endDate,"HH:mm:ss"));
      request.put("gamekind", String.valueOf(gamekind));
      if(subgamekind!=null){
        request.put("subgamekind", String.valueOf(subgamekind));
      }
      request.put("page", String.valueOf(page));
      request.put("pagelimit", String.valueOf(configPo.getLength())); //默认拉取的条数
      request.put("key", getParamsKey(request,configPo.getParamsKey()));

      String url=configPo.getPullUrl()+"/"+uri;
      log.info("BBIN拉取请求={}",url);
      log.info("请求参数={}",request);
      MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
      map.setAll(request);

      JSONObject result=restTemplate.exchange(url, HttpMethod.POST,new HttpEntity<>(map,headers),JSONObject.class).getBody();
      log.info("BBIN拉取返回结果={}",result.toJSONString());
      if(result.getBoolean("result")==null){
        throw new BBINException(String.format("BBIN拉取出错，错误信息=%s",result.toJSONString()));
      }
      return result;
    } catch (Exception e) {
      if(e instanceof HttpClientErrorException){
        log.error("BBIN拉取请求次数频繁，暂停1分钟,错误信息={}",e.getMessage());
        try{
          Thread.sleep(10*1000L);
        }catch (Exception ex){
          ex.printStackTrace();
        }
      }
      throw new BBINException(e);
    }
  }

  /**
   *
   */
  public JSONObject getRecordWagers(Date startDate, Date endDate, DsBbinGameConfigPo configPo,String uri,Integer page,String day) throws BBINException {
    try {
      if(!DateUtils.isSameDay(startDate,endDate)){
        throw new BBINException(String.format("BBIN拉取出错,起始时间不一致,错误信息=%s-%s",startDate,endDate));
      }
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

      Map<String, String> request= new TreeMap<>();
      request.put("action", "BetTime");
      request.put("uppername", configPo.getAgentId());
      request.put("date",day);
      request.put("starttime", DateUtil.covertStr(startDate,"HH:mm:ss"));
      request.put("endtime", DateUtil.covertStr(endDate,"HH:mm:ss"));
      request.put("page", String.valueOf(page));
      request.put("pagelimit", String.valueOf(configPo.getLength())); //默认拉取的条数
      request.put("key", getParamsKey(request,configPo.getParamsKey()));

      String url=configPo.getPullUrl()+"/"+uri; //WagersRecordBy30 或者 WagersRecordBy38
      log.info("BBIN拉取请求={}",url);
      log.info("请求参数={}",request);
      MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
      map.setAll(request);

      JSONObject result=restTemplate.exchange(url, HttpMethod.POST,new HttpEntity<>(map,headers),JSONObject.class).getBody();
      log.info("BBIN拉取返回结果={}",result.toJSONString());
      if(result.getBoolean("result")==null){
        throw new BBINException(String.format("BBIN拉取出错，错误信息=%s",result.toJSONString()));
      }
      return result;
    } catch (Exception e) {
      if(e instanceof HttpClientErrorException){
        log.error("BBIN拉取请求次数频繁，暂停1分钟,错误信息={}",e.getMessage());
        try{
          Thread.sleep(10*1000L);
        }catch (Exception ex){
          ex.printStackTrace();
        }
      }
      throw new BBINException(e);
    }
  }

  /**
   * 例如参数为 username=dstest0001&password=12345678&transid=1001
   * 则 key 的加密方式为：
   * 1. 首先对参数名按照首字母英文顺序进行排序 password 、trasid 、username
   * 2. 对排序后的值进行加密 MD5（12345678+1001+dstest0001+userkey）--加号是连接符号不参与加密
   * @param request
   * @param key
   * @return
   */
  private String getParamsKey(Map<String, String> request,String key){
    StringBuffer sb = new StringBuffer();
    request.forEach((k,v)-> sb.append(k+"="+v+"&"));
    if(sb.length() > 0){
      sb.substring(0,sb.length()-1);
    }
    return  EncryptUtil.encrypt(sb.toString(), key);
  }

  /**
   * 获取当前时间，时间延迟5分钟;
   * 比如:2019-6-27 00:00:00 获取前5分钟，为2019-6-26 23:55:00; 截取时间为2019-6-26
   * 目的在，临界点2019-6-27 00:00:00，由于存在拉取延迟，有充裕时间给第三方汇总26日的数据，避免数据遗漏
   * @return
   */
  private String getBBINRounddate(DsBbinGameConfigPo configPo){
    Date date=configPo.getInitStartId();
    if(date==null){
      date=DateUtils.addHours(new Date(),-12); //当前时间为28号，BBIN为27号的美东时间，查询为美东时间的RoundDate
    }
    return DateUtil.getDayBefore(date,5);
  }

}
