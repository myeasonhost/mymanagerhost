package com.eason.report.pull.platform.ag.mgr;


import com.eason.report.pull.core.mgr.IPullMgr;
import com.eason.report.pull.core.utils.DateUtil;
import com.eason.report.pull.core.utils.Md5Util;
import com.eason.report.pull.platform.ag.exception.AGException;
import com.eason.report.pull.platform.ag.mgo.DSAGHunterMgoPo;
import com.eason.report.pull.platform.ag.model.hunter.HunterListModel;
import com.eason.report.pull.platform.ag.model.hunter.HunterModel;
import com.eason.report.pull.platform.ag.model.hunter.PageData;
import com.eason.report.pull.platform.ag.po.DsAGGameConfigPo;
import com.eason.report.pull.platform.ag.utils.XMLUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Slf4j
public class DSAGHunterMgr implements IPullMgr<DSAGHunterMgoPo, DsAGGameConfigPo> {
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private MongoTemplate mongoTemplate;
  @Autowired
  private StringRedisTemplate stringRedisTemplate10;

  @Override
  public DSAGHunterMgoPo extAttr(DSAGHunterMgoPo po, DsAGGameConfigPo configPo) {
    po.setAgentId(configPo. getAgentId());
    for(Map.Entry<Integer,String> site:configPo.getSiteMap().entrySet()){
      if(po.getUsername().startsWith(site.getValue())){
        po.setSiteId(site.getKey());
        po.setPlayName(po.getUsername());
        po.setUsername(po.getPlayName().substring(site.getValue().length()));
      }
    }
    po.setWin(po.getEarn().subtract(po.getCost()));
    //判断输赢
    if(po.getWin().doubleValue() >0){
      po.setWinLossType(Byte.valueOf("2"));//赢
    }else if(po.getWin().doubleValue() < 0){
      po.setWinLossType(Byte.valueOf("1"));//输
    }else if(po.getWin().doubleValue() == 0){
      po.setWinLossType(Byte.valueOf("3"));//和
    }
    po.setCreateTime(new Date());
    return po;
  }

  @Transactional
  @Override
  public void saveAndUpdate(DSAGHunterMgoPo po, DsAGGameConfigPo configPo){
    po=this.extAttr(po,configPo);
    Optional<DSAGHunterMgoPo> result =mongoTemplate.update(DSAGHunterMgoPo.class)
            .matching(query(where("billNo").is(po.getBillNo())))
            .replaceWith(po)
            .withOptions(FindAndReplaceOptions.options().upsert())
            .findAndReplace();
  }

  @Override
  public Timestamp getMaxId(DsAGGameConfigPo configPo){
    TypedAggregation<DSAGHunterMgoPo> agg = newAggregation(DSAGHunterMgoPo.class,
            match(where("agentId").is(configPo.getAgentId())),
            group().max("$endTime").as("endTime")
    );
    AggregationResults<DSAGHunterMgoPo> results = mongoTemplate.aggregate(agg, DSAGHunterMgoPo.class);
    DSAGHunterMgoPo po = results.getUniqueMappedResult();

    Object obj=stringRedisTemplate10.boundHashOps("ag_hunter_pull_config").get("endTime_"+configPo.getAgentId());
    Date endDate=obj==null?null:DateUtil.covertTime((String)obj);
    if(endDate!=null){
      return new Timestamp(endDate.getTime());
    }
    if(po==null){
      return new Timestamp(configPo.getInitStartId().getTime());
    }
    return new Timestamp(po.getEndTime().getTime());
  }

  @Override
  public Timestamp getNextId(DsAGGameConfigPo configPo) {
    Date startId= DateUtils.addSeconds(getMaxId(configPo),1);
    return new Timestamp(startId.getTime());
  }

  public List<HunterModel> loadDataToEndTime(Date pullDate, Integer length, DsAGGameConfigPo configPo) throws AGException {
    try {
      List<HunterModel> list=new ArrayList<>();
      Date startDate= pullDate;
      Date endDate= DateUtils.addMinutes(startDate,length);
      Date date=new Date();
      HunterListModel hunterListModel=this.getRecordAG(startDate,endDate,configPo,1); //起始值page=1
      if(hunterListModel.getAgList()!=null && !hunterListModel.getAgList().isEmpty()){
        list.addAll(hunterListModel.getAgList());
      }
      PageData pagination=hunterListModel.getAddition();
      Integer currentPage=pagination.getNumPage();
      Integer totalPages=pagination.getItemTotal()/pagination.getPerpage()+1;
      if(currentPage!=null && totalPages!=null){
        for(int page=currentPage;page<totalPages;page++){
          HunterListModel hunterListModel2=getRecordAG(startDate,endDate,configPo,page+1);
          if(hunterListModel2.getAgList()!=null && !hunterListModel2.getAgList().isEmpty()) {
            list.addAll(hunterListModel.getAgList());
          }
        }
      }

      if (list.isEmpty() || list.size()==0){
        log.info("AG网站={} 拉取成功,但注单数量为0,时间段{}——{}",configPo.getAgentId(), DateUtil.covertStr(startDate), DateUtil.covertStr(endDate));
        if(endDate.compareTo(date)==-1){
          stringRedisTemplate10.boundHashOps("ag_hunter_pull_config").put("endTime_"+configPo.getAgentId(), DateUtil.covertStr(endDate));
        }
      }else{
        stringRedisTemplate10.boundHashOps("ag_hunter_pull_config").delete("endTime_"+configPo.getAgentId());
      }
      return list;
    } catch (Exception e) {
      throw new AGException(e);
    }
  }

  public HunterListModel getRecordAG(Date startDate, Date endDate, DsAGGameConfigPo configPo,Integer page) throws AGException {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_XML);

      Map<String, Object> request= new TreeMap<>();
      request.put("0params_key", configPo.getParamsKey());
      request.put("1begintime", startDate.getTime()/1000);
      request.put("2endtime", endDate.getTime()/1000);
      request.put("3numperpage", 100);//默认拉取的条数
      request.put("4order", "time");//默认拉取的条数
      request.put("5page", page);
      request.put("6productId", configPo.getAgentId());
      request.put("7sessionkey", getParamsKey(request));

      String url=configPo.getPullUrl()+"&pidtoken={0params_key}&productid={6productId}" +
              "&begintime={1begintime}&endtime={2endtime}&order={4order}&numperpage={6productId}&page={5page}&sessionkey={7sessionkey}";
      log.info("AG拉取请求={}",url);
      log.info("请求参数={}",request);

      String result=restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(headers),String.class,request).getBody();
      log.info("AG拉取返回结果={}",result);

      HunterListModel hunterListModel= XMLUtil.xmlStrToOject(HunterListModel.class,result);
      return hunterListModel;
    } catch (Exception e) {
      if(e instanceof HttpClientErrorException){
        log.error("AG拉取请求次数频繁，暂停1分钟,错误信息={}",e.getMessage());
        try{
          Thread.sleep(10*1000L);
        }catch (Exception ex){
          ex.printStackTrace();
        }
      }
      throw new AGException(e);
    }
  }

  /**
   * 注意顺序：cagent第一位，key(明码)最后一位
   * 则 key 的加密方式为：
   * 1. hash =MD5(pidtoken+begintime+endtime+numperpage+order+page+productId+key)
   */
  private String getParamsKey(Map<String, Object> request){
    StringBuffer sb = new StringBuffer();
    request.forEach((k,v)-> sb.append(v));
    return Md5Util.makeMd5Sum(sb.toString().getBytes());
  }

  /**
   * 获取当前时间，时间延迟0分钟; AG不需要延迟
   * 比如:2019-6-27 00:00:00 获取前5分钟，为2019-6-26 23:55:00; 截取时间为2019-6-26
   * 目的在，临界点2019-6-27 00:00:00，由于存在拉取延迟，有充裕时间给第三方汇总26日的数据，避免数据遗漏
   * @return
   */
  private String getAGRounddate(DsAGGameConfigPo configPo){
    Date date=configPo.getInitStartId();
    if(date==null){
      date=DateUtils.addHours(new Date(),-12); //当前时间为28号，AG为27号的美东时间，查询为美东时间的RoundDate
    }
    return DateUtil.getDayBefore(date,0);
  }

}
