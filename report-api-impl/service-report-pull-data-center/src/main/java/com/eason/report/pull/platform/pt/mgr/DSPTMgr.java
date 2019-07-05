package com.eason.report.pull.platform.pt.mgr;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eason.report.pull.core.mgr.IPullMgr;
import com.eason.report.pull.core.utils.DateUtil;
import com.eason.report.pull.platform.pt.dao.po.DsPtGameConfigPo;
import com.eason.report.pull.platform.pt.exception.PTException;
import com.eason.report.pull.platform.pt.mgo.DSPTMgoPo;
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
public class DSPTMgr implements IPullMgr<DSPTMgoPo, DsPtGameConfigPo> {
  @Autowired
  private RestTemplate restTemplateForPTClient;
  @Autowired
  private MongoTemplate mongoTemplate;
  @Autowired
  private StringRedisTemplate stringRedisTemplate10;

  @Override
  public DSPTMgoPo extAttr(DSPTMgoPo po, DsPtGameConfigPo configPo) {
    po.setAgentId(configPo.getAgentId());
    for(Map.Entry<Integer,String> site:configPo.getSiteMap().entrySet()){
      po.setSiteId(site.getKey());
      if(po.getPlayername().startsWith(site.getValue())){
        po.setUsername(po.getPlayername().substring(site.getValue().length()));
      }
    }
    //把输赢变为绝对输赢
    po.setWin(po.getWin().subtract(po.getBet()));
    //判断输赢
    if(po.getWin().doubleValue() >0){
      po.setWinLossType(2);//赢
    }else if(po.getWin().doubleValue() < 0){
      po.setWinLossType(1);//输
    }else if(po.getWin().doubleValue() == 0){
      po.setWinLossType(3);//和
    }
    po.setCreateTime(new Date());
    return po;
  }

  @Transactional
  @Override
  public void saveAndUpdate(DSPTMgoPo po, DsPtGameConfigPo configPo){
    po=this.extAttr(po,configPo);
    Optional<DSPTMgoPo> result =mongoTemplate.update(DSPTMgoPo.class)
            .matching(query(where("gamecode").is(po.getGamecode())))
            .replaceWith(po)
            .withOptions(FindAndReplaceOptions.options().upsert())
            .findAndReplace();
    if(result.isPresent()){
      log.info("DS-PT存在重复值DSPTMgoPo={}",result.get().toString());
    }
  }

  @Override
  public Timestamp getMaxId(DsPtGameConfigPo configPo){
    TypedAggregation<DSPTMgoPo> agg = newAggregation(DSPTMgoPo.class,
            match(where("agentId").is(configPo.getAgentId())),
            group().max("$gamedate").as("gamedate")
    );
    AggregationResults<DSPTMgoPo> results = mongoTemplate.aggregate(agg, DSPTMgoPo.class);
    DSPTMgoPo po = results.getUniqueMappedResult();

    Object obj=stringRedisTemplate10.boundHashOps("pt_pull_config").get("endTime_"+configPo.getAgentId());
    Date endDate=obj==null?null:DateUtil.covertTime((String)obj);
    if(endDate!=null){
      return new Timestamp(endDate.getTime());
    }
    if(po==null){
      return new Timestamp(configPo.getInitStartId().getTime());
    }
    return new Timestamp(po.getGamedate().getTime());
  }

  @Override
  public Timestamp getNextId(DsPtGameConfigPo configPo) {
    return getMaxId(configPo);
  }
  /**
   *  PT 20个代理账号
   *  拉取（当前时间 1 分钟之前数据；建议拉取区间为 1-5 分钟，最大不能超过 30 分钟）
   *  在startDate拉取长度length=5分钟(心跳时间)内<当前时间，如果没有数据，根据AgentId缓存水位时间标记
   *  如果超过水位标记，删除掉水位标记即可
   *  PT涉及分页操作：有数据分页结构
   *  {
   *     "result": [
   *         {
   *             "PLAYERNAME": "TYZ_EASON",
   *             "WINDOWCODE": "0",
   *             "GAMEID": "10",
   *             "GAMECODE": "161547665631",
   *             "GAMETYPE": "Slot Machines",
   *             "GAMENAME": "Atlantis Queen (gtsatq)",
   *             "SESSIONID": "708015349689",
   *             "CURRENCYCODE": "CNY",
   *             "BET": "0.25",
   *             "WIN": "0.43",
   *             "PROGRESSIVEBET": "0",
   *             "PROGRESSIVEWIN": "0",
   *             "BALANCE": "19.28",
   *             "CURRENTBET": "0",
   *             "GAMEDATE": "2019-06-24 14:20:44",
   *             "RNUM": "1",
   *             "LIVENETWORK": null
   *         }
   *     ],
   *     "pagination": {
   *         "currentPage": 1,
   *         "totalPages": 1,
   *         "itemsPerPage": 10,
   *         "totalCount": 1
   *     }
   * }
   */
  public JSONArray loadDataToEndTime(Date pullDate, Integer length, DsPtGameConfigPo configPo) throws PTException {
    try {
      JSONArray jsonArray=new JSONArray();
      Date startDate= pullDate;
      Date endDate= DateUtils.addMinutes(startDate,length);
      Date date=new Date();
      JSONObject jsonObject=this.getRecordHandle(startDate,endDate,configPo,1); //起始值page=1
      jsonArray.addAll(jsonObject.getJSONArray("result"));
      JSONObject pagination=jsonObject.getJSONObject("pagination");
      Integer currentPage=pagination.getInteger("currentPage");
      Integer totalPages=pagination.getInteger("totalPages");
      for(int page=currentPage;page<totalPages;page++){
        JSONObject jsonObject2=getRecordHandle(startDate,endDate,configPo,page+1);
        if(jsonObject2.getJSONArray("result")!=null && !jsonObject2.getJSONArray("result").isEmpty()){
          jsonArray.addAll(jsonObject2.getJSONArray("result"));
        }
      }

      if (jsonArray.isEmpty() || jsonArray.size()==0){
        log.info("PT网站={} 拉取成功,但注单数量为0,时间段{}——{}",configPo.getAgentId(), DateUtil.covertStr(startDate), DateUtil.covertStr(endDate));
        if(endDate.compareTo(date)==-1){
          stringRedisTemplate10.boundHashOps("pt_pull_config").put("endTime_"+configPo.getAgentId(), DateUtil.covertStr(endDate));
        }
      }else{
        stringRedisTemplate10.boundHashOps("pt_pull_config").delete("endTime_"+configPo.getAgentId());
      }
      return jsonArray;
    } catch (Exception e) {
      throw new PTException(e);
    }
  }

  public JSONObject getRecordHandle(Date startDate, Date endDate, DsPtGameConfigPo configPo,Integer page) throws PTException {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
      headers.set("X_ENTITY_KEY",configPo.getEntityKey());

      Map<String, Object> request= new HashMap<>();
      request.put("startdate", DateUtil.covertStr(startDate,"yyyy-MM-dd%20HH:mm:ss"));
      request.put("enddate",DateUtil.covertStr(endDate,"yyyy-MM-dd%20HH:mm:ss"));
      request.put("page", page);
      request.put("perPage", 10); //默认拉取的条数

      String url=String.format(configPo.getPullUrl()+"/customreport/getdata/reportname/PlayerGames/showinfo/1/startdate" +
                      "/%s/enddate/%s/frozen/all/page/%s/perPage/%s",
              request.get("startdate"),
              request.get("enddate"),
              request.get("page"),
              request.get("perPage"));
      UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
      URI uri = builder.build(true).toUri();

      log.info("PT拉取请求={}",uri.toString());
      log.info("请求参数={}",request);
      JSONObject result=restTemplateForPTClient.exchange(uri, HttpMethod.POST,new HttpEntity<>(headers),JSONObject.class).getBody();
      log.info("PT拉取返回结果={}",result.toJSONString());
      if(result.getJSONArray("result")==null){
        throw new PTException(String.format("PT拉取出错，错误信息=%s",result.toJSONString()));
      }
      return result;
    } catch (Exception e) {
      if(e instanceof HttpClientErrorException){
        log.error("PT拉取请求次数频繁，暂停1分钟,错误信息={}",e.getMessage());
        try{
          Thread.sleep(10*1000L);
        }catch (Exception ex){
          ex.printStackTrace();
        }
      }
      throw new PTException(e);
    }
  }

}
