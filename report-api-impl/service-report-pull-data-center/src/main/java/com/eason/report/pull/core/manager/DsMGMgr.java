package com.eason.report.pull.core.manager;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eason.report.pull.core.config.MGAppInfoConfig;
import com.eason.report.pull.core.exception.MgException;
import com.eason.report.pull.core.exception.TimeOutException;
import com.eason.report.pull.core.mysqlDao.DsMGDao;
import com.eason.report.pull.core.mysqlDao.po.DsMgGamePo;
import com.eason.report.pull.core.utils.DateUtil;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.mongo.mgo.DsMGMgo;
import com.eason.report.pull.core.mongo.po.DsMGMgoPo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class DsMGMgr extends BaseAPI {
  public static final String DB_DATE_PATTERN="yyyy-MM-dd HH:mm:ss";

  @Autowired
  private StringRedisTemplate stringRedisTemplate10;
  @Autowired
  private DsMGDao dsMGDao;
  @Autowired
  private DsMGMgo dsMGMgo;
  @Resource
  private EntityManager entityManager;
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private MGAppInfoConfig appInfoConfig;

  public MGAppInfoConfig loadConfig() throws MgException {
    try {
//      Object pullUrl = stringRedisTemplate10.boundHashOps("core-gf").get("pullUrl");
//      Object user = stringRedisTemplate10.boundHashOps("core-gf").get("user");
//      Object level = stringRedisTemplate10.boundHashOps("core-gf").get("level");
//      Object length = stringRedisTemplate10.boundHashOps("core-gf").get("length");
//      Object siteId = stringRedisTemplate10.boundHashOps("core-gf").get("siteId");
//
//      if (pullUrl == null || StringUtils.isEmpty(pullUrl.toString())) {
//        throw new MgException("DS-GF读取缓存的配置，pullUrl为空，请正确配置");
//      }
//      if (user == null || StringUtils.isEmpty(user.toString())) {
//        throw new MgException("DS-GF读取缓存的配置，user为空，请正确配置");
//      }
//      if (level == null || StringUtils.isEmpty(level.toString())) {
//        throw new MgException("DS-GF读取缓存的配置，level为空，请正确配置");
//      }
//      if (length == null || StringUtils.isEmpty(length.toString())) {
//        throw new MgException("DS-GF读取缓存的配置，length为空，请正确配置");
//      }
//      if (siteId == null || StringUtils.isEmpty(siteId.toString())) {
//        throw new MgException("DS-GF读取缓存的配置，siteId为空，请正确配置");
//      }
      Long agentId=124743826L;    //888821
      String username="MSKJCNYA02";  //MSKJCNYA
      String password="AAbb1122+";  //4
      String pullUrl="https://ag.adminserv88.com/"; //http://180.150.154.116/gfrecordapi/getRecord
      String prex="DF91"; //1000
      Integer length=30;
      String  MG_WEBSITE_URL = pullUrl+"/lps/j_spring_security_check";
      String siteId="TYZ_1020,MHD_1040";

      Map<Integer,String> map=new HashMap<>();
      String[] ary=siteId.toString().split(","); //TYZ_1020,MHD_1040,MAA_1070,MAB_1080
      for (String s:ary){ //TYZ_1020
        String[] i=s.split("_");
        map.put(Integer.parseInt(i[1]),prex+""+i[0]);
      }
      appInfoConfig.setAgentId(agentId);
      appInfoConfig.setUsername(username);
      appInfoConfig.setPassword(password);
      appInfoConfig.setPullUrl(pullUrl);
      appInfoConfig.setPrex(prex);
      appInfoConfig.setLength(length);
      appInfoConfig.setMG_WEBSITE_URL(MG_WEBSITE_URL);
      appInfoConfig.setSiteId(map);
      log.info("MG读取缓存的配置：appInfoConfig="+appInfoConfig);
      return appInfoConfig;
    }catch (Exception e){
      throw new MgException(e.getMessage());
    }
  }

  /**
   *  Transaction History To EndTime>TransTime
   */
  public JSONArray loadDataToEndTime(String token,String pullTime,Integer length) throws MgException {
    try {
      Date startTime= DateUtil.getDateFormat(DB_DATE_PATTERN).parse(pullTime);
      Date endDate= DateUtils.addMinutes(startTime,length);
      String endTime=DateUtil.getDateFormat(DB_DATE_PATTERN).format(endDate);
      JSONArray jsonArray=this.viewHorTxCall(token,startTime,endDate);
      BoundHashOperations bho=stringRedisTemplate10.boundHashOps(MG_PULL_CONFIG);
      bho.put(START_TIME, pullTime);
      bho.put(END_TIME, endTime);
      bho.put(CREATE_TIME, DateUtil.getDateFormat(DB_DATE_PATTERN).format(new Date()));
      if (jsonArray.isEmpty() || jsonArray.size()==0){
        log.info("MG大富豪网站={} 拉取成功,但注单数量为0,时间段{}——{}",appInfoConfig.getAgentId(), startTime, endTime);
        if(endDate.compareTo(new Date())==-1){
          log.info("从startTime={}，上升={}，休息1分钟",endDate.getTime(),length);
          Thread.sleep(10*1000L);
          this.loadDataToEndTime(token,endTime,length);

        }
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
  public JSONArray viewHorTxCall(String token,Date startDate,Date endDate) throws MgException {
    try {
      HttpHeaders requestHeaders = new HttpHeaders();
      requestHeaders.add("user-agent", "PostmanRuntime/7.13.0");
      requestHeaders.add("X-Api-Call", "X-Api-Client");
      requestHeaders.add("X-Requested-With", "X-Api-Client");
      requestHeaders.add("X-Api-Auth", token);

      Map<String, String> request= new HashMap<>();
      request.put("agentId", appInfoConfig.getAgentId().toString());
      request.put("start", DateUtil.getDateFormat(DATE_PATTERN).format(startDate));
      request.put("end", DateUtil.getDateFormat(DATE_PATTERN).format(endDate));
      request.put("timezone", "Asia/Shanghai");

      String url=appInfoConfig.getPullUrl()+"/lps/secure/agenttx/{agentId}?start={start}&end={end}&timezone={timezone}";
      log.info("MG拉取请求={}",url);
      log.info("请求参数={}",request);
      ResponseEntity<JSONArray> exchange= restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(request,requestHeaders), JSONArray.class,request);
      if(exchange.getStatusCode().toString().equalsIgnoreCase("401 Unauthorized")){  //"message": "Unauthorized"
        token=this.loginWebSite().get("token").toString();
        viewHorTxCall(token,startDate,endDate);
      }
      JSONArray result=exchange.getBody();
      log.info("MG拉取返回结果={}",result.toJSONString());
      return result;
    } catch (Exception e) {
      if(e instanceof HttpClientErrorException){
        log.error("MG拉取请求次数频繁，暂停1分钟,错误信息={}",e.getMessage());
        try{
          Thread.sleep(10*1000L);
          viewHorTxCall(token,startDate,endDate);
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
  public JSONObject loginWebSite() throws MgException {
    try {
      HttpHeaders requestHeaders = new HttpHeaders();
      requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      requestHeaders.add("X-Requested-With", "X-Api-Client");
      requestHeaders.add("X-Api-Call", "X-Api-Client");
      requestHeaders.add("user-agent", "PostmanRuntime/7.13.0");

      MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
      params.add("j_username", appInfoConfig.getUsername());
      params.add("j_password", appInfoConfig.getPassword());

      HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params,requestHeaders);
      log.info("MG登录请求={}",appInfoConfig.getMG_WEBSITE_URL());
      log.info("请求参数={}",params);

      ResponseEntity<String> exchange= restTemplate.exchange(appInfoConfig.getMG_WEBSITE_URL(), HttpMethod.POST,entity,String.class);
      if (exchange.getStatusCodeValue()!=200){
        log.error("MG错误信息={}",exchange.toString());
         throw new MgException("代理：" + appInfoConfig.getAgentId() + "获取token异常="+exchange.getStatusCodeValue());
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

  @Transactional
  public void saveAndUpdate(DsMgGamePo vo){
    DsMgGamePo po= dsMGDao.findByColId(vo.getColId());
    vo=this.extAttr(vo);
    if (po!=null){
      Long id=po.getId();
      BeanUtils.copyProperties(vo,po);
      po.setId(id);
      entityManager.merge(po);
    }else{
      this.dsMGDao.save(vo);
    }

    DsMGMgoPo dtGFMgoPo=new DsMGMgoPo();
    BeanUtils.copyProperties(vo,dtGFMgoPo);
    dtGFMgoPo.setLiveId();
    this.dsMGMgo.save(dtGFMgoPo);

  }

  public DsMgGamePo extAttr(DsMgGamePo po) {
    for(Map.Entry<Integer,String> site:appInfoConfig.getSiteId().entrySet()){
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
    po.setKeyB(appInfoConfig.getUsername()+"-"+appInfoConfig.getPassword());
    po.setMemo(po.getMbrCode()+"-"+po.getGameId()+"-"+po.getMgsGameId());
    po.setCreateTime(new Timestamp(System.currentTimeMillis()));
    return po;
  }


}
