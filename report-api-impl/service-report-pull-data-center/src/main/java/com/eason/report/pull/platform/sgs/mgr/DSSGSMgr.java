package com.eason.report.pull.platform.sgs.mgr;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eason.report.pull.core.mgr.IPullMgr;
import com.eason.report.pull.core.utils.DateUtil;
import com.eason.report.pull.platform.sgs.dao.po.DsSgsGameConfigPo;
import com.eason.report.pull.platform.sgs.exception.SGSException;
import com.eason.report.pull.platform.sgs.mgo.DSSGSMgoPo;
import com.eason.report.pull.platform.sgs.utils.SHA1Utils;
import com.eason.report.pull.platform.sgs.vo.SgsCsvVo;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
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
import java.io.StringReader;
import java.sql.Timestamp;
import java.util.*;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Slf4j
public class DSSGSMgr implements IPullMgr<DSSGSMgoPo, DsSgsGameConfigPo> {
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private MongoTemplate mongoTemplate;
  @Autowired
  private StringRedisTemplate stringRedisTemplate10;

  @Override
  public DSSGSMgoPo extAttr(DSSGSMgoPo po, DsSgsGameConfigPo configPo) {
    po.setAgentId(configPo.getAgentId());
    for(Map.Entry<Integer,String> site:configPo.getSiteMap().entrySet()){
      if(po.getUserid().startsWith(site.getValue())){
        po.setSiteId(site.getKey());
      }
    }
    //判断输赢
    if(po.getWinloss().doubleValue() >0){
      po.setWinLossType(Byte.valueOf("2"));//赢
    }else if(po.getWinloss().doubleValue() < 0){
      po.setWinLossType(Byte.valueOf("1"));//输
    }else if(po.getWinloss().doubleValue() == 0){
      po.setWinLossType(Byte.valueOf("3"));//和
    }
    po.setCreateTime(new Date());
    return po;
  }

  @Transactional
  @Override
  public void saveAndUpdate(DSSGSMgoPo po, DsSgsGameConfigPo configPo){
    po=this.extAttr(po,configPo);
    Optional<DSSGSMgoPo> result =mongoTemplate.update(DSSGSMgoPo.class)
            .matching(query(where("ugsBetId").is(po.getUgsBetId())))
            .replaceWith(po)
            .withOptions(FindAndReplaceOptions.options().upsert())
            .findAndReplace();
    if(result.isPresent()){
      log.info("DS-SGS存在重复值DSSGSMgoPo={}",result.get().toString());
    }
  }

  @Override
  public Timestamp getMaxId(DsSgsGameConfigPo configPo){
    TypedAggregation<DSSGSMgoPo> agg = newAggregation(DSSGSMgoPo.class,
            match(where("agentId").is(configPo.getAgentId())),
            group().max("$betClosedOn").as("betClosedOn")
    );
    AggregationResults<DSSGSMgoPo> results = mongoTemplate.aggregate(agg, DSSGSMgoPo.class);
    DSSGSMgoPo po = results.getUniqueMappedResult();
    if(po==null){
      return new Timestamp(configPo.getInitStartId().getTime());
    }
    Object obj=stringRedisTemplate10.boundHashOps("sgs_pull_config").get("endTime_"+configPo.getAgentId());
    Date endDate=obj==null?null:DateUtil.covertTime((String)obj);
    if(endDate!=null && endDate.compareTo(po.getBetClosedOn())==1){
      return new Timestamp(endDate.getTime());
    }else{
      stringRedisTemplate10.boundHashOps("sgs_pull_config").delete("endTime_"+configPo.getAgentId());
    }
    return new Timestamp(po.getBetClosedOn().getTime());
  }

  @Override
  public Timestamp getNextId(DsSgsGameConfigPo configPo) {
    return getMaxId(configPo);
  }
  /**
   *  SGS
   *  拉取（当前时间 1 分钟之前数据；建议拉取区间为 1-5 分钟，最大不能超过 30 分钟）
   *  在startDate拉取长度length=5分钟(心跳时间)内<当前时间，如果没有数据，根据AgentId缓存水位时间标记
   *  如果超过水位标记，删除掉水位标记即可
   */
  public JSONArray loadDataToEndTime(Date pullDate, Integer length, DsSgsGameConfigPo configPo) throws SGSException {
    try {
      Date startDate= pullDate;
      Date endDate= DateUtils.addMinutes(startDate,length);
      Date date=new Date();
      JSONArray jsonArray=this.getRecordHandle(startDate,endDate,configPo,1); //起始值page=1

      if (jsonArray.isEmpty() || jsonArray.size()==0){
        log.info("SGS网站={} 拉取成功,但注单数量为0,时间段{}——{}",configPo.getAgentId(), startDate, pullDate);
        if(endDate.compareTo(date)==-1){
          stringRedisTemplate10.boundHashOps("sgs_pull_config").put("endTime_"+configPo.getAgentId(), DateUtil.covertStr(endDate));
        }
      }else{
        stringRedisTemplate10.boundHashOps("sgs_pull_config").delete("endTime_"+configPo.getAgentId());
      }
      return jsonArray;
    } catch (Exception e) {
      throw new SGSException(e);
    }
  }

  public JSONArray getRecordHandle(Date startDate, Date endDate, DsSgsGameConfigPo configPo, Integer page) throws SGSException {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
      String sgsDate = DateUtil.getUTCTime(new Date());
      String signature= SHA1Utils.genHMAC2(configPo.getClientSecret(),configPo.getClientSecret()+sgsDate);
      headers.set("Authorization","SGS "+configPo.getAgentId()+":" +signature);
      headers.set("X-Sgs-Date", sgsDate);

      Map<String, Object> request= new HashMap<>();
      request.put("startdate", DateUtil.getUTCTime(startDate));
      request.put("enddate", DateUtil.getUTCTime(endDate));
      request.put("includetestplayers", false); //测试用户：true=包含，false=不包含
      request.put("issettled", true);//结算：true=已结算，false=已结算+未结算

      String url=configPo.getPullUrl()+"?startdate={startdate}&enddate={enddate}&includetestplayers={includetestplayers}&issettled={issettled}";
      log.info("SGS拉取请求={}",url);
      log.info("请求参数={}",request);
      String result= restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(headers), String.class,request).getBody();
      JSONObject jsonStr=null;
      try{
        jsonStr= JSONObject.parseObject(result);
      }catch(Exception e){
      }
      if(jsonStr!=null && jsonStr.get("err")!=null){
        throw new SGSException(String.format("SGS拉取出错，错误信息=%s",jsonStr.toJSONString()));
      }

      HeaderColumnNameMappingStrategy<SgsCsvVo> mapper = new HeaderColumnNameMappingStrategy<>();
      mapper.setType(SgsCsvVo.class);
      CsvToBean csvToBean = new CsvToBeanBuilder(new StringReader(result)).withMappingStrategy(mapper).build();
      List<SgsCsvVo> list=csvToBean.parse();
      JSONArray jsonArray=new JSONArray();
      jsonArray.addAll(list);
      log.info("SGS拉取返回结果={}",jsonArray.toJSONString());
      return jsonArray;
    } catch (Exception e) {
      if(e instanceof HttpClientErrorException){
        log.error("SGS拉取请求次数频繁，暂停1分钟,错误信息={}",e.getMessage());
        try{
          Thread.sleep(10*1000L);
        }catch (Exception ex){
          ex.printStackTrace();
        }
      }
      throw new SGSException(e);
    }
  }

}
