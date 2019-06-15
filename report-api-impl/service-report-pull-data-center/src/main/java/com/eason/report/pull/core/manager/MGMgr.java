package com.eason.report.pull.core.manager;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.config.MGConfigMgoCo;
import com.eason.report.pull.core.exception.MgException;
import com.eason.report.pull.core.exception.TimeOutException;
import com.eason.report.pull.core.mongo.mgo.DsMGMgo;
import com.eason.report.pull.core.mongo.mgo.MGConfigMgo;
import com.eason.report.pull.core.mongo.po.MGMgoPo;
import com.eason.report.pull.core.mysqlDao.config.MgGameConfigPo;
import com.eason.report.pull.core.mysqlDao.dao.DsMGDao;
import com.eason.report.pull.core.mysqlDao.dao.MGConfigDao;
import com.eason.report.pull.core.mysqlDao.po.DsGameTypePo;
import com.eason.report.pull.core.mysqlDao.po.DsMgGamePo;
import com.eason.report.pull.core.mysqlDao.vo.DsGameTypeVo;
import com.eason.report.pull.core.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.*;

@Service
@Slf4j
public class MGMgr extends BaseAPI {
  /**MG的配置**/
  public static final String DATE_PATTERN="yyyy:MM:dd:HH:mm:ss";

  @Autowired
  private DsMGDao dsMGDao;
  @Autowired
  private DsMGMgo dsMGMgo;
  @Resource
  private EntityManager entityManager;
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private MGConfigDao mgConfigDao;
  @Autowired
  private MGConfigMgo mgConfigMgo;

  public List<MGConfigMgoCo> loadConfig() throws MgException {
    List<MGConfigMgoCo> configMgoList=mgConfigMgo.findAll();
    if(configMgoList==null){
         List<MgGameConfigPo> configDaoList=mgConfigDao.findConfig();
         configDaoList=Collections.EMPTY_LIST;
         Collections.copy(configDaoList,configDaoList);
    }
    configMgoList.forEach(po -> {
      Map<Integer,String> map=new HashMap<>();
      String[] ary=po.getSiteId().split(","); //TYZ_1020,MHD_1040,MAA_1070,MAB_1080
      for (String s:ary){ //TYZ_1020
        String[] i=s.split("_");
        map.put(Integer.parseInt(i[1]),po.getPrex()+""+i[0]);
      }
      po.setSiteMap(map);
    });

    log.info("MG大富豪读取缓存的配置：configList="+configMgoList);
    return configMgoList;
  }

  /**
   *  Transaction History To EndTime>TransTime
   *  在length时间内，如果拉取数据为0，提高拉取速度到10秒一次，超过的length时间，或者当前时间，恢复调度时间
   */
  public JSONArray loadDataToEndTime(String token, Date pullDate, Integer length, MGConfigMgoCo configPo) throws MgException {
    try {
      Date startDate= pullDate;
      Date endDate= DateUtils.addMinutes(startDate,length);
      JSONArray jsonArray=this.viewHorTxCall(token,startDate,endDate,configPo);

      configPo.setStartTime(new Timestamp(startDate.getTime()));
      configPo.setEndTime(new Timestamp(endDate.getTime()));
      configPo.setCreateTime(new Timestamp(System.currentTimeMillis()));
      mgConfigMgo.save(configPo);

      if (jsonArray.isEmpty() || jsonArray.size()==0){
        log.info("MG大富豪网站={} 拉取成功,但注单数量为0,时间段{}——{}",configPo.getAgentId(), startDate, pullDate);
        if(endDate.compareTo(new Date())==-1 && configPo.getUpTime()<length*60*1000L){
          log.info("从startTime={}，上升={}，休息10秒钟",endDate.getTime(),length);
          Thread.sleep(10*1000L);
          configPo.setUpTime(configPo.getUpTime()+10*1000L);
          this.loadDataToEndTime(token,endDate,length,configPo);
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
  public JSONArray viewHorTxCall(String token, Date startDate, Date endDate, MGConfigMgoCo configPo) throws MgException {
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
  public JSONObject loginWebSite(MGConfigMgoCo configPo) throws MgException {
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

  @Transactional
  public void saveAndUpdate(DsMgGamePo vo, MGConfigMgoCo configPo,List<DsGameTypeVo> dsGameTypePoList){

    DsMgGamePo po= dsMGDao.findByColId(vo.getColId());

    MGMgoPo mgMgoPo=new MGMgoPo();
    BeanUtils.copyProperties(vo,mgMgoPo);
//    mgMgoPo.setLiveName(configPo.getLiveName());
//    dsGameTypePoList.forEach(dsGameTypePo -> {
//      if(mgMgoPo.getGameId().equals(dsGameTypePo.getOutGameCode())){
//        mgMgoPo.setLiveId(dsGameTypePo.getFkLiveId().intValue());
//        mgMgoPo.setParentType(dsGameTypePo.getParentId());
//        mgMgoPo.setParentName(dsGameTypePo.getParentName());
//        mgMgoPo.setType(mgMgoPo.getGameId());
//        mgMgoPo.setGameName(dsGameTypePo.getGameName());
//      }else{
//        DsGameTypePo dsGameTypePo1=new DsGameTypePo();
//        String newGameName=String.format(configPo.getInfo(),mgMgoPo.getGameId());
//        dsGameTypePo1.setGameName(newGameName);
//        dsGameTypePo1.setOutGameCode(mgMgoPo.getGameId().toString());
//        dsGameTypePo1.setParentId(Integer.parseInt(configPo.getGameKind().split(",")[0]));
//        dsGameTypePo1.setFkLiveId(configPo.getLiveId().byteValue());
//        entityManager.persist(dsGameTypePo1);
//      }
//    });
    this.dsMGMgo.save(mgMgoPo);


    vo=this.extAttr(vo,configPo);
    if (po!=null){
      Long id=po.getId();
      BeanUtils.copyProperties(vo,po);
      po.setId(id);
      entityManager.merge(po);
    }else{
      this.dsMGDao.save(vo);
    }

    MGMgoPo dtGFMgoPo=new MGMgoPo();
    BeanUtils.copyProperties(vo,dtGFMgoPo);
    this.dsMGMgo.save(dtGFMgoPo);

  }

  public DsMgGamePo extAttr(DsMgGamePo po, MGConfigMgoCo configPo) {
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


}
