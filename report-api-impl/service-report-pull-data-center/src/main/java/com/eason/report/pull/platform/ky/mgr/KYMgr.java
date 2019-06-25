package com.eason.report.pull.platform.ky.mgr;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eason.report.pull.core.mgr.IPullMgr;
import com.eason.report.pull.core.utils.DateUtil;
import com.eason.report.pull.core.utils.EncryptUtil;
import com.eason.report.pull.platform.ky.dao.po.KYGameConfigPo;
import com.eason.report.pull.platform.ky.exception.KyException;
import com.eason.report.pull.platform.ky.mgo.KyMgoPo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
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
public class KYMgr implements IPullMgr<KyMgoPo, KYGameConfigPo> {
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private StringRedisTemplate stringRedisTemplate10;
  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public KyMgoPo extAttr(KyMgoPo po, KYGameConfigPo configPo) {
    for(Map.Entry<Integer,String> site:configPo.getSiteMap().entrySet()){
      po.setSiteId(site.getKey());
      if(po.getAccount().startsWith(site.getValue())){
        po.setAccount(po.getAccount().substring(site.getValue().length()));
      }
    }
    //判断输赢
    if(po.getProfit().doubleValue() >0){
      po.setWinLossType(Byte.valueOf("2"));//赢
    }else if(po.getProfit().doubleValue() < 0){
      po.setWinLossType(Byte.valueOf("1"));//输
    }else if(po.getProfit().doubleValue() == 0){
      po.setWinLossType(Byte.valueOf("3"));//和
    }
    po.setCreateTime(new Timestamp(System.currentTimeMillis()));
    return po;
  }

  @Transactional
  @Override
  public void saveAndUpdate(KyMgoPo po, KYGameConfigPo configPo){
    po=this.extAttr(po,configPo);
    Optional<KyMgoPo> result =mongoTemplate.update(KyMgoPo.class)
            .matching(query(where("gameEndTime").is(po.getGameEndTime())))
            .replaceWith(po)
            .withOptions(FindAndReplaceOptions.options().upsert())
            .findAndReplace();
    if(result.isPresent()){
      log.info("KY存在重复值KyMgoPo={}",result.get().toString());
    }
  }

  @Override
  public Timestamp getMaxId(KYGameConfigPo configPo){
    TypedAggregation<KyMgoPo> agg = newAggregation(KyMgoPo.class,
            match(where("channelId").is(Integer.parseInt(configPo.getAgentId()))),
            group().max("$gameEndTime").as("gameEndTime")
    );
    AggregationResults<KyMgoPo> results = mongoTemplate.aggregate(agg,KyMgoPo.class);
    KyMgoPo po = results.getUniqueMappedResult();
    if(po==null){
      return new Timestamp(configPo.getInitStartId().getTime());
    }

    Object obj=stringRedisTemplate10.boundHashOps("ky_pull_config").get("endTime_"+configPo.getAgentId());
    Date endDate= DateUtil.covertTime((String)obj);
    if(obj!=null || endDate.compareTo(po.getGameEndTime())==1){
      return new Timestamp(endDate.getTime());
    }else{
      stringRedisTemplate10.boundHashOps("ky_pull_config").delete("endTime_"+configPo.getAgentId());
    }
    return new Timestamp(po.getGameEndTime().getTime());
  }

  @Override
  public Timestamp getNextId(KYGameConfigPo configPo) {
    Date startId= DateUtils.addSeconds(getMaxId(configPo),1);
    return new Timestamp(startId.getTime());
  }

  /**
   *  KY总代理
   *  拉取（当前时间 1 分钟之前数据；建议拉取区间为 1-5 分钟，最大不能超过 40 分钟）
   *  在startDate拉取长度length=5分钟内<当前时间，如果没有数据，根据AgentId缓存水位时间标记
   *  如果超过水位标记，删除掉水位标记即可
   */
  public JSONArray loadDataToEndTime(Date pullDate, Integer length, KYGameConfigPo configPo) throws KyException {
    try {
      Date startDate= pullDate;
      Date endDate= DateUtils.addMinutes(startDate,length);
      Date date=new Date();
      JSONArray jsonArray=this.getRecordHandle(startDate,endDate,configPo);

      if (jsonArray.isEmpty() || jsonArray.size()==0){
        log.info("KY网站={} 拉取成功,但注单数量为0,时间段{}——{}",configPo.getAgentId(), startDate, pullDate);
        if(endDate.compareTo(date)==-1){
          stringRedisTemplate10.boundHashOps("ky_pull_config").put("endTime_"+configPo.getAgentId(), DateUtil.covertStr(endDate));
        }
      }else{
        stringRedisTemplate10.boundHashOps("ky_pull_config").delete("endTime_"+configPo.getAgentId());
      }
      return jsonArray;
    } catch (Exception e) {
      throw new KyException(e);
    }
  }

  public JSONArray getRecordHandle(Date startDate, Date endDate, KYGameConfigPo configPo) throws KyException {
    try {
      Long timestamp = System.currentTimeMillis();

      Map<String, Object> request= new HashMap<>();
      request.put("agent", configPo.getAgentId());
      request.put("timestamp",timestamp);
      request.put("param", getParam(startDate.getTime(),endDate.getTime(),configPo.getDesKey()));
      request.put("key", getKey(configPo.getAgentId(),timestamp,configPo.getMd5Key()));

      String url=String.format(configPo.getPullUrl()+"?agent=%s&timestamp=%s&param=%s&key=%s",
              request.get("agent"),
              request.get("timestamp"),
              request.get("param"),
              request.get("key"));
      UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
      URI uri = builder.build(true).toUri();

      log.info("KY拉取请求={}",uri.toString());
      log.info("请求参数={}",request);
      String body=restTemplate.getForObject(uri,String.class);
      JSONObject result=JSONObject.parseObject(body);
      log.info("KY拉取返回结果={}",result.toJSONString());
      if(result.getJSONObject("d")!=null && !"0".equals(result.getJSONObject("d").getString("code"))){ //请求成功
        throw new KyException(String.format("KY拉取出错，错误信息=%s",result.toJSONString()));
      }
      return convertList(result.getJSONObject("d").getJSONObject("list"));
    } catch (Exception e) {
      if(e instanceof HttpClientErrorException){
        log.error("KY拉取请求次数频繁，暂停1分钟,错误信息={}",e.getMessage());
        try{
          Thread.sleep(10*1000L);
        }catch (Exception ex){
          ex.printStackTrace();
        }
      }
      throw new KyException(e);
    }
  }

  private String getParam(long startTime, long endTime,String deskey) {
    StringBuffer sb = new StringBuffer();
    sb.append("s=6&startTime=").append(startTime).append("&endTime=").append(endTime);
    try {
      return EncryptUtil.AESEncrypt(sb.toString(), deskey);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private String getKey(String agent, long timestamp,String md5key) {
    StringBuffer sb = new StringBuffer();
    sb.append(agent).append(timestamp).append(md5key);
    return EncryptUtil.MD5(sb.toString());
  }

  private JSONArray convertList(JSONObject listObject) {
    JSONArray jsonArray=new JSONArray();
    JSONArray gameIdArray = listObject.getJSONArray("GameID");
    JSONArray accountArray = listObject.getJSONArray("Accounts");
    JSONArray serverArray = listObject.getJSONArray("ServerID");
    JSONArray kindIdArray = listObject.getJSONArray("KindID");
    JSONArray tabelIdArray = listObject.getJSONArray("TableID");
    JSONArray chariIdArray = listObject.getJSONArray("ChairID");
    JSONArray userCountArray = listObject.getJSONArray("UserCount");
    JSONArray allBetArray = listObject.getJSONArray("AllBet");
    JSONArray profitArray = listObject.getJSONArray("Profit");
    JSONArray revenueArray = listObject.getJSONArray("Revenue");
    JSONArray gameStartTimeArray = listObject.getJSONArray("GameStartTime");
    JSONArray gameEndTimeArray = listObject.getJSONArray("GameEndTime");
    JSONArray channelIDArray = listObject.getJSONArray("ChannelID");
    JSONArray cardValueArray = listObject.getJSONArray("CardValue");
    JSONArray cellScoreArray = listObject.getJSONArray("CellScore");
    for(int i=0;i<gameIdArray.size();i++){
      JSONObject jsonObject=new JSONObject();
      jsonObject.put("GameID",gameIdArray.get(i));
      jsonObject.put("Account",accountArray.get(i));  //去掉多余的s Accounts改成Account
      jsonObject.put("ServerID",serverArray.get(i));
      jsonObject.put("KindID",kindIdArray.get(i));
      jsonObject.put("TableID",tabelIdArray.get(i));
      jsonObject.put("ChairID",chariIdArray.get(i));
      jsonObject.put("UserCount",userCountArray.get(i));
      jsonObject.put("AllBet",allBetArray.get(i));
      jsonObject.put("Profit",profitArray.get(i));
      jsonObject.put("Revenue",revenueArray.get(i));
      jsonObject.put("GameStartTime",DateUtil.covertTime((String)gameStartTimeArray.get(i)));
      jsonObject.put("GameEndTime",DateUtil.covertTime((String)gameEndTimeArray.get(i)));
      jsonObject.put("ChannelID",channelIDArray.get(i));
      jsonObject.put("CardValue",cardValueArray.get(i));
      jsonObject.put("CellScore",cellScoreArray.get(i));
      jsonArray.add(jsonObject);
    }
    return jsonArray;
  }

}
