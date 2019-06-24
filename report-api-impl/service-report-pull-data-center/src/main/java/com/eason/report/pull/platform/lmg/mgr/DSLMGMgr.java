package com.eason.report.pull.platform.lmg.mgr;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eason.report.pull.core.mgr.IPullMgr;
import com.eason.report.pull.platform.lmg.dao.po.DsLmgGameConfigPo;
import com.eason.report.pull.platform.lmg.exception.LMGException;
import com.eason.report.pull.platform.lmg.mgo.LMGMgoPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Slf4j
public class DSLMGMgr implements IPullMgr<LMGMgoPo, DsLmgGameConfigPo> {
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public LMGMgoPo extAttr(LMGMgoPo po, DsLmgGameConfigPo configPo) {
    po.setAgentId(configPo.getAgentId());
    for(Map.Entry<Integer,String> site:configPo.getSiteMap().entrySet()){
      po.setSiteId(site.getKey());
      if(po.getUsername().startsWith(site.getValue())){
        po.setUsername(po.getUsername().substring(site.getValue().length()));
      }
    }
    //判断输赢
    if(po.getWinLoss().doubleValue() >0){
      po.setWinLossType(2);//赢
    }else if(po.getWinLoss().doubleValue() < 0){
      po.setWinLossType(1);//输
    }else if(po.getWinLoss().doubleValue() == 0){
      po.setWinLossType(3);//和
    }
    po.setCreateTime(new Date());
    return po;
  }

  @Transactional
  @Override
  public void saveAndUpdate(LMGMgoPo po, DsLmgGameConfigPo configPo){
    po=this.extAttr(po,configPo);
    Optional<LMGMgoPo> result =mongoTemplate.update(LMGMgoPo.class)
            .matching(query(where("sequenceNo").is(po.getSequenceNo())))
            .replaceWith(po)
            .withOptions(FindAndReplaceOptions.options().upsert())
            .findAndReplace();
    if(result.isPresent()){
      log.info("DS-LMG存在重复值LMGMgoPo={}",result.get().toString());
    }
  }

  @Override
  public Long getMaxId(DsLmgGameConfigPo configPo){
    TypedAggregation<LMGMgoPo> agg = newAggregation(LMGMgoPo.class,
            match(where("agentId").is(configPo.getAgentId())),
            group().max("$sequenceNo").as("sequenceNo")
    );
    AggregationResults<LMGMgoPo> results = mongoTemplate.aggregate(agg,LMGMgoPo.class);
    LMGMgoPo po = results.getUniqueMappedResult();
    if(po==null){
      return configPo.getInitStartId();
    }
    return po.getSequenceNo();
  }

  @Override
  public Long getNextId(DsLmgGameConfigPo configPo) {
    return getMaxId(configPo);
  }

  /**
   *  LMG股东
   */
  public JSONArray loadDataByStartId(Long startId, DsLmgGameConfigPo configPo) throws LMGException {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

      JSONObject request = new JSONObject();
      request.put("hashCode", configPo.getHashcode());
      request.put("command", "GET_RECORD_BY_SEQUENCENO");
      JSONObject params = new JSONObject();
      params.put("beginId", String.valueOf(startId));
      params.put("count", String.valueOf(configPo.getLength()));
      request.put("params", params);
      log.info("DS-LMG拉取请求={}",configPo.getPullUrl());
      log.info("请求参数={}",request.toJSONString());

      HttpEntity<String> entity = new HttpEntity<>(request.toJSONString(), headers);
      String body = restTemplate.postForObject(configPo.getPullUrl(), entity, String.class);
      JSONObject result=JSONObject.parseObject(body);
      if(result.getInteger("errorCode")!=0){ //请求成功
        throw new LMGException(String.format("DS-LMG拉取出错，错误信息=%s",result.toJSONString()));
      }
      log.info("DS-LMG拉取返回结果={}",result);
      return result.getJSONObject("params").getJSONArray("recordList");
    } catch (Exception e) {
      if(e instanceof HttpClientErrorException){
        log.error("DS-LMG拉取请求次数频繁，暂停1分钟,错误信息={}",e.getMessage());
        try{
          Thread.sleep(10*1000L);
        }catch (Exception ex){
          ex.printStackTrace();
        }
      }
      throw new LMGException(e);
    }
  }


}
