package com.eason.report.pull.core.manager;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eason.report.pull.core.api.IPullMgr;
import com.eason.report.pull.core.exception.MgException;
import com.eason.report.pull.core.exception.TimeOutException;
import com.eason.report.pull.core.mongo.po.MGMgoPo;
import com.eason.report.pull.core.mysqlDao.config.MgGameConfigPo;
import com.eason.report.pull.core.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Slf4j
public class MGMgr implements IPullMgr<MGMgoPo, MgGameConfigPo> {
  /**MG的配置**/
  public static final String DATE_PATTERN="yyyy:MM:dd:HH:mm:ss";

  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private StringRedisTemplate stringRedisTemplate10;
  @Autowired
  private MongoTemplate mongoTemplate;

  /**
   *  Transaction History To EndTime>TransTime
   *  在startDate拉取长度length=30分钟内<当前时间，如果没有数据，根据AgentId缓存水位时间标记
   *  如果超过水位标记，删除掉水位标记即可
   */
  public JSONArray loadDataToEndTime(String token, Date pullDate, Integer length, MgGameConfigPo configPo) throws MgException {
    try {
      Date startDate= pullDate;
      Date endDate= DateUtils.addMinutes(startDate,length);
      Date date=new Date();
      JSONArray jsonArray=this.viewHorTxCall(token,startDate,endDate,configPo);

      if (jsonArray.isEmpty() || jsonArray.size()==0){
        log.info("MG大富豪网站={} 拉取成功,但注单数量为0,时间段{}——{}",configPo.getAgentId(), startDate, pullDate);
        if(endDate.compareTo(date)==-1){
          stringRedisTemplate10.boundHashOps("mg_pull_config").put("endTime_"+configPo.getAgentId(), DateUtil.covertStr(endDate));
        }
      }else{
        stringRedisTemplate10.boundHashOps("mg_pull_config").delete("endTime_"+configPo.getAgentId());
      }
      return jsonArray;
    } catch (Exception e) {
      if(e instanceof HttpClientErrorException){
        throw new TimeOutException(e);
      }
      throw new MgException(e);
    }
  }

  /**
   *  Transaction History
   */
  public JSONArray viewHorTxCall(String token, Date startDate, Date endDate, MgGameConfigPo configPo) throws MgException {
    try {
      HttpHeaders requestHeaders = new HttpHeaders();
      requestHeaders.add("user-agent", "PostmanRuntime/7.13.0");
      requestHeaders.add("X-Api-Call", "X-Api-Client");
      requestHeaders.add("X-Requested-With", "X-Api-Client");
      requestHeaders.add("X-Api-Auth", token);

      Map<String, String> request= new HashMap<>();
      request.put("agentId", configPo.getAgentId().toString());
      request.put("start", DateUtil.getDateFormat(DATE_PATTERN).format(startDate));
      request.put("end", DateUtil.getDateFormat(DATE_PATTERN).format(endDate));
      request.put("timezone", "Asia/Shanghai");

      String url=configPo.getPullUrl()+"/lps/secure/agenttx/{agentId}?start={start}&end={end}&timezone={timezone}";
      log.info("MG拉取请求={}",url);
      log.info("请求参数={}",request);
      ResponseEntity<JSONArray> exchange= restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(request,requestHeaders), JSONArray.class,request);
      if(exchange.getStatusCode().toString().equalsIgnoreCase("401 Unauthorized")){  //"message": "Unauthorized"
        token=this.loginWebSite(configPo).get("token").toString();
        viewHorTxCall(token,startDate,endDate,configPo);
      }
      JSONArray result=exchange.getBody();
      log.info("MG拉取返回结果={}",result.toJSONString());
      return result;
    } catch (Exception e) {
      if(e instanceof HttpClientErrorException){
        log.error("MG拉取请求次数频繁，暂停1分钟,错误信息={}",e.getMessage());
        try{
          Thread.sleep(10*1000L);
          viewHorTxCall(token,startDate,endDate,configPo);
        }catch (Exception ex){
          ex.printStackTrace();
        }
      }
      throw new MgException(e);
    }
  }

  /**
   *  login to API Website
   */
  public JSONObject loginWebSite(MgGameConfigPo configPo) throws MgException {
    try {
      HttpHeaders requestHeaders = new HttpHeaders();
      requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      requestHeaders.add("X-Requested-With", "X-Api-Client");
      requestHeaders.add("X-Api-Call", "X-Api-Client");
      requestHeaders.add("user-agent", "PostmanRuntime/7.13.0");

      MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
      params.add("j_username", configPo.getUsername());
      params.add("j_password", configPo.getPassword());

      HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params,requestHeaders);
      String loginUrl=configPo.getPullUrl()+"/lps/j_spring_security_check";
      log.info("MG登录请求={}",loginUrl);
      log.info("请求参数={}",params);

      ResponseEntity<String> exchange= restTemplate.exchange(loginUrl, HttpMethod.POST,entity,String.class);
      if (exchange.getStatusCodeValue()!=200){
        log.error("MG错误信息={}",exchange.toString());
         throw new MgException("代理：" + configPo.getAgentId() + "获取token异常="+exchange.getStatusCodeValue());
      }
      JSONObject result=JSONObject.parseObject(exchange.getBody());
      log.info("MG登录返回结果={}",result);
      return result;

    } catch (Exception e) {
      if(e instanceof HttpClientErrorException){
        throw new TimeOutException(e);
      }
      throw new MgException(e);
    }
  }

  @Override
  public MGMgoPo extAttr(MGMgoPo po, MgGameConfigPo configPo) {
    for(Map.Entry<Integer,String> site:configPo.getSiteMap().entrySet()){
      if(po.getMbrCode().startsWith(site.getValue())){
        po.setUserName(po.getMbrCode().substring(site.getValue().length()));
        po.setSiteId(site.getKey());
      }
    }
    po.setTransTime(new Timestamp(po.getTransTime().getTime()));
    if("bet".equalsIgnoreCase(po.getTransType())){
      po.setWinLossType(1); // 输 数据库定义
    }else if("win".equalsIgnoreCase(po.getTransType())){
      po.setWinLossType(2); // 赢 数据库定义
    }else{
      po.setWinLossType(0); // 无效字段，默认为null，老虎机没有和
    }
    po.setKeyB(configPo.getUsername()+"-"+configPo.getPassword());
    po.setMemo(po.getMbrCode()+"-"+po.getGameId()+"-"+po.getMgsGameId());
    po.setCreateTime(new Timestamp(System.currentTimeMillis()));
    return po;
  }

  @Transactional
  @Override
  public void saveAndUpdate(MGMgoPo po, MgGameConfigPo configPo){
    po=this.extAttr(po,configPo);
    Optional<MGMgoPo> result =mongoTemplate.update(MGMgoPo.class)
            .matching(query(where("colId").is(po.getColId())))
            .replaceWith(po)
            .withOptions(FindAndReplaceOptions.options().upsert())
            .findAndReplace();
    if(result.isPresent()){
      log.info("MG存在重复值MGMgoPo={}",result.get().toString());
    }
  }

  @Override
  public Timestamp getMaxId(MgGameConfigPo configPo){
    TypedAggregation<MGMgoPo> agg = newAggregation(MGMgoPo.class,
            match(where("agentId").is(configPo.getAgentId())),
            group().max("$transTime").as("transTime")
    );
    AggregationResults<MGMgoPo> results = mongoTemplate.aggregate(agg,MGMgoPo.class);
    MGMgoPo po = results.getUniqueMappedResult();
    Object obj=stringRedisTemplate10.boundHashOps("mg_pull_config").get("endTime_"+configPo.getAgentId());
    Date endDate;
    if(obj==null || po.getTransTime().compareTo(endDate=DateUtil.covertTime((String)obj))==1){
      return new Timestamp(po.getTransTime().getTime());
    }else{
      stringRedisTemplate10.boundHashOps("mg_pull_config").delete("endTime_"+configPo.getAgentId());
    }
    return new Timestamp(endDate.getTime());
  }

  @Override
  public Timestamp getNextId(MgGameConfigPo configPo) {
    Date startId= DateUtils.addSeconds(getMaxId(configPo),1);
    return new Timestamp(startId.getTime());
  }
}
