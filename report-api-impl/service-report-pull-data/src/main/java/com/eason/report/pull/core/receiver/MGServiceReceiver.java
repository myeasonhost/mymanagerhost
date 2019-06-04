//package com.eason.report.pull.core.receiver;
//
//import com.eason.report.pull.core.base.BaseAPI;
//import com.eason.report.pull.core.model.MsgModel;
//import com.eason.report.pull.core.mysqlDao.MGAuditTotalDao;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@Service
//@Slf4j
//public class MGServiceReceiver extends BaseAPI {
//    @Autowired
//    private MGAuditTotalDao mgDao;
//
//    private ExecutorService executorService =  Executors.newFixedThreadPool(5);
//
//    @JmsListener(destination = MG_TYPE)
//    public void receiverMsg(MsgModel msg){
//        log.info("收到消息，开始分发="+msg);
////        NumModel model=(NumModel)msg.getObject();
////        Map<Integer,String> map=model.getSiteId();
////        CountDownLatch cdl = new CountDownLatch(map.size());
////        map.forEach((key,value)-> {
////            executorService.submit(new Runnable() {
////                @Override
////                public void run() {
////                    try {
////                        if (!executorService.isShutdown()) {
////                            if (MG_TYPE.equals(msg.getCode())) {
////                                Integer siteId = Integer.parseInt(value);
////                                Integer rows = mgDao.sitePull(siteId, key, model.getStartId(), model.getEndId());
////                                log.info("MG站点siteId={}，执行分表存储过程，CALL ds_mg_site_pull({},'{}','{}','{}',@num);SELECT @num;", siteId,
////                                        siteId, key, model.getStartId(), model.getEndId());
////                                log.info("分表Procedure返回数据rows={}",rows);
////                                String result = mgDao.createAuditAndReport(siteId, model.getStartId(), model.getEndId());
////                                log.info("MG站点siteId={}，执行审计报表存储过程，CALL ds_mg_audit_report({},'{}','{}',@result);SELECT @result;", siteId,
////                                        siteId, model.getStartId(), model.getEndId());
////                                log.info("审计报表Procedure返回结果result={}",result);
////                            }
////                        }
////                    } catch (Exception e) {
////                        log.info(msg.getCode()+"拉取系统异常={}", e.getMessage());
////                        e.printStackTrace();
////                    } finally {
////                        cdl.countDown();
////
////                    }
////                }
////            });
////        });
////        try {
////            cdl.await();
////        } catch (InterruptedException e) {
////            log.info(msg.getCode()+"拉取系统异常={}", e.getMessage());
////            e.printStackTrace();
////        }
//    }
//
//}
