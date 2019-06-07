//package com.eason.report.pull.core.api;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.eason.report.pull.core.base.BaseAPI;
//import com.eason.report.pull.core.config.JDAppInfoConfig;
//import com.eason.report.pull.core.exception.DsException;
//import com.eason.report.pull.core.manager.DtJDMgr;
//import com.eason.report.pull.core.mysqlDao.DtJDDao;
//import com.eason.report.pull.core.mysqlDao.po.DtJingdianLotteryPo;
//import com.eason.report.pull.core.service.PullAPIService;
//import com.eason.report.pull.core.model.DateModel;
//import com.eason.report.pull.core.model.MsgModel;
//import com.eason.report.pull.core.mq.MQServiceProducer;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
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
//public class JDPullAPIImpl extends PullAPIService {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private JDAppInfoConfig jdAppInfoConfig;
//
//    @Autowired
//    private DtJDMgr dtJDMgr;
//
//    @Autowired
//    private DtJDDao dtJDDao;
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
//            log.error("DS-JD经典从startId="+maxId+"开始准备拉取,拉取配置可能有误，长度length="+length);
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
//            log.info("DS-JD经典彩开始准备拉取,拉取配置", jdAppInfoConfig);
//            Long startId = maxId;
//
//            JSONObject request = new JSONObject();
//            request.put("num",length);
//            request.put("username", jdAppInfoConfig.getUser());
//            request.put("beginId", startId);
//            request.put("accType", jdAppInfoConfig.getLevel());
//            HttpEntity<String> entity = new HttpEntity<>(request.toString());
//            ResponseEntity<JSONObject> exchange=restTemplate.exchange(jdAppInfoConfig.getPullUrl(), HttpMethod.POST,entity,JSONObject.class);
//            if (exchange==null || exchange.getBody().isEmpty()){
//                log.error("DS-JD经典彩: 重要报警，用户={},参数={},断网维护或者未加白名单", jdAppInfoConfig.getUser(),request.toString());
//                return;
//            }
//            JSONObject object=exchange.getBody();
//
//            if (object.getString("status").equals("10000") && object.getJSONArray("message").size() != 0) {// 获取成功
//                JSONArray array = object.getJSONArray("message");
//                int arraySize = array.size();
//                log.info("DS-JD经典彩网站={},拉取到注单 ,数量={}", jdAppInfoConfig.getUser(), arraySize);
//                for (int i = 0; i < array.size(); i++) {
//                    DtJingdianLotteryPo jingdianEntityPo = array.getObject(i, DtJingdianLotteryPo.class);
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
//                        mqServiceListener.sendReceiverMsg(this.DS_JD_TYPE,
//                                MsgModel.builder()
//                                        .code(key)
//                                        .message("当前拉取size="+arraySize+"条已完成，开始分发")
//                                        .object(dsLotteryModel).build());
//                        log.info("DS-JD-0000当前startId={}——endId={}拉取size="+arraySize+"条已完成，开始分发",dsLotteryModel.getStartId(),dsLotteryModel.getEndId());
//                    });
//                }
//            } else if (object.getString("status").equals("10000") && object.getJSONArray("message").size() == 0) { // 没有拉取到注单
//                log.info("DS-JD经典彩网站={} 拉取成功,但注单数量为0,休眠1分钟,拉取结果={}", jdAppInfoConfig.getUser(),object.toJSONString());
//                return;
//            } else if (!object.getString("status").equals("10000")) {
//                log.error("DS-JD经典彩网站={} 拉取数据失败,请检查配置,休眠1分钟错误消息={}", jdAppInfoConfig.getUser(), object.toJSONString());
//                return;
//            }
//        }catch (Exception e){
//            if (e instanceof ResourceAccessException){
//                log.error("DS-JD经典彩未添加白名单或者可能在维护，错误信息={}",e.getMessage());
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
