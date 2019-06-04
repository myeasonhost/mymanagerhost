//package com.eason.report.pull.core.api;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.eason.report.pull.core.base.BaseAPI;
//import com.eason.report.pull.core.config.MdtJDAppInfoConfig;
//import com.eason.report.pull.core.exception.DsException;
//import com.eason.report.pull.core.manager.MdtJDMgr;
//import com.eason.report.pull.core.mysqlDao.MdtJDDao;
//import com.eason.report.pull.core.mysqlDao.po.MdtJingdianLotteryPo;
//import com.eason.report.pull.core.api.PullAPIService;
//import com.eason.report.pull.core.model.DateModel;
//import com.eason.report.pull.core.model.MsgModel;
//import com.eason.report.pull.core.mq.MQServiceProducer;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.ResourceAccessException;
//import org.springframework.web.client.RestTemplate;
//
//
///**
// * @author EASON LI
// */
//@Service
//@Slf4j
//public class MdtJDPullAPIImpl extends PullAPIService {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private MdtJDAppInfoConfig jdAppInfoConfig;
//
//    @Autowired
//    private MdtJDMgr dtJDMgr;
//
//    @Autowired
//    private MdtJDDao dtJDDao;
//
//    @Autowired
//    private MQServiceProducer mqServiceListener;
//
//    /**
//     * 经典彩拉取
//     */
//    public void getPullBet() throws DsException {
//        Long maxId=dtJDDao.getMaxId();
//        Integer length=jdAppInfoConfig.getLength();
//        if (maxId ==0 || length==0){
//            log.error("MDT-JD经典从startId="+maxId+"开始准备拉取,拉取配置可能有误，长度length="+length);
//            return;
//        }
//        getPullBet(maxId+1,length); //下一个开始
//    }
//
//    /**
//     * 经典彩拉取
//     */
//    public void getPullBet(Long maxId,Integer length) throws DsException {
//        try {
//            boolean flag=false;
//            log.info("MDT-JD经典彩开始准备拉取,拉取配置", jdAppInfoConfig);
//            Long startId = maxId;
//
//            HttpHeaders requestHeaders = new HttpHeaders();
//            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
//            requestHeaders.add("X-Requested-With", "X-Api-Client");
//            requestHeaders.add("X-Api-Call", "X-Api-Client");
//            requestHeaders.add("user-agent", "PostmanRuntime/7.13.0");
//
//            JSONObject request = new JSONObject();
//            request.put("len",length);
//            request.put("user", jdAppInfoConfig.getUser());
//            request.put("startId", startId);
//            request.put("level", jdAppInfoConfig.getLevel());
//
//            HttpEntity<String> entity = new HttpEntity<>(request.toString(),requestHeaders);
//
//            ResponseEntity<JSONObject> exchange=restTemplate.exchange(jdAppInfoConfig.getPullUrl(), HttpMethod.POST,entity,JSONObject.class);
//            if (exchange==null || exchange.getBody().isEmpty()){
//                log.error("MDT-JD经典彩: 重要报警，用户={},参数={},断网维护或者未加白名单", jdAppInfoConfig.getUser(),request.toString());
//                return;
//            }
//            JSONObject object=exchange.getBody();
//
//            if (object.getString("Message").equals("success") && object.getJSONArray("Result").size() != 0) {// 获取成功
//                JSONArray array = object.getJSONArray("Result");
//                int arraySize = array.size();
//                log.info("MDT-JD经典彩网站={},拉取到注单 ,数量={}", jdAppInfoConfig.getUser(), arraySize);
//                for (int i = 0; i < array.size(); i++) {
//                    MdtJingdianLotteryPo jingdianEntityPo = array.getObject(i, MdtJingdianLotteryPo.class);
//                    dtJDMgr.saveAndUpdate(jingdianEntityPo,jdAppInfoConfig);
//                    flag=true;
//                }
//                if (flag){
//                    Long endId=dtJDDao.getMaxId();
//                    DateModel dsLotteryModel=DateModel.builder()
//                            .startId(startId)
//                            .endId(endId)
//                            .siteId(jdAppInfoConfig.getSiteId()).build();
//                    jdAppInfoConfig.getSiteId().forEach((key,value)-> {
//                        mqServiceListener.sendReceiverMsg(MDT_JD_TYPE,
//                                MsgModel.builder()
//                                        .code(key)
//                                        .message("当前拉取size="+arraySize+"条已完成，开始分发")
//                                        .object(dsLotteryModel).build());
//                        log.info("MDT-JD-0000当前startId={}——endId={}拉取size="+arraySize+"条已完成，开始分发",dsLotteryModel.getStartId(),dsLotteryModel.getEndId());
//                    });
//                }
//            } else if (object.getString("Message").equals("success") && object.getJSONArray("Result").size() == 0) { // 没有拉取到注单
//                log.info("MDT-JD经典彩网站={} 拉取成功,但注单数量为0,休眠1分钟,拉取结果={}", jdAppInfoConfig.getUser(),object.toJSONString());
//                return;
//            } else if (!object.getString("Message").equals("success")) {
//                log.error("MDT-JD经典彩网站={} 拉取数据失败,请检查配置,休眠1分钟错误消息={}", jdAppInfoConfig.getUser(), object.toJSONString());
//                return;
//            }
//        }catch (Exception e){
//            if (e instanceof ResourceAccessException){
//                log.error("MDT-JD经典彩未添加白名单或者可能在维护，错误信息={}",e.getMessage());
//                return;
//            }
//            e.printStackTrace();
//            throw new DsException(e.getMessage());
//        }
//
//    }
//
//    @Override
//    protected void getPullBet(String maxId, Integer length) throws Exception {
//
//    }
//}
