//package com.eason.report.pull.core.receiver;
//
//import com.eason.report.pull.core.mq.MQServiceConsumer;
//import com.eason.report.pull.core.mysqlDao.DSAuditTotalDao;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class DSServiceReceiver extends MQServiceConsumer {
//    @Autowired
//    private DSAuditTotalDao dsDao;
//
//
//    @Override
//    protected void receiverMsg(Integer siteId, String prex, Long startId, Long endId) {
//        Integer rows = dsDao.sitePullForDSGF(siteId, prex, startId, endId);
//        log.info("DS-GF官方彩站点siteId={}，执行分表存储过程，CALL ds_gf_site_pull({},'{}',{},{},@num);SELECT @num;返回数据rows={}", siteId,
//                siteId, prex, startId, endId, rows);
//        String result = dsDao.createAuditAndReportForDSGF(siteId, startId, endId);
//        log.info("DS-GF官方彩站点siteId={}，执行审计报表存储过程，CALL ds_gf_audit_report({},{},{},@result);SELECT @result;返回结果result={}", siteId,
//                siteId, startId, endId, result);
//    }
//
//    @Override
//    protected void receiverMsg(Integer siteId, String prex, String startId, String endId) {
//
//    }
//
//    //    @JmsListener(destination = DS_GF_TYPE)
////    public void receiverMsg01(MsgModel msg){
////        log.info("收到消息，开始分发="+msg);
////        DateModel dsLotteryModel=(DateModel)msg.getObject();
////        String prex=dsLotteryModel.getSiteId().get(siteId);
////        try {
////            if (siteId.equals(msg.getCode())){
////                Integer rows = dsDao.sitePullForDSGF(siteId, prex, startId, endId);
////                log.info("DS-GF官方彩站点siteId={}，执行分表存储过程，CALL ds_gf_site_pull({},'{}',{},{},@num);SELECT @num;返回数据rows={}", siteId,
////                        siteId, prex, startId, endId, rows);
////                String result = dsDao.createAuditAndReportForDSGF(siteId, startId, endId);
////                log.info("DS-GF官方彩站点siteId={}，执行审计报表存储过程，CALL ds_gf_audit_report({},{},{},@result);SELECT @result;返回结果result={}", siteId,
////                        siteId, startId, endId, result);
////            }
////        } catch (Exception e) {
////            log.info(msg.getCode()+"彩票拉取系统异常={}", e.getMessage());
////            e.printStackTrace();
////        }
////    }
//
////    @JmsListener(destination = DS_JD_TYPE)
////    public void receiverMsg02(MsgModel msg){
////        log.info("收到消息，开始分发="+msg);
////        DateModel dsLotteryModel=(DateModel)msg.getObject();
////        Map<String,String> map=dsLotteryModel.getSiteId();
////        CountDownLatch cdl = new CountDownLatch(map.size());
////        map.forEach((key,value)-> {
////            executorService.submit(new Runnable() {
////                @Override
////                public void run() {
////                    try {
////                        if (!executorService.isShutdown()) {
////                            if (DS_JD_TYPE.equals(msg.getCode())) {
////                                Integer siteId = Integer.parseInt(value);
////                                Integer rows = dsDao.sitePullForDSGF(siteId, key, startId, endId);
////                                log.info("DS-JD经典彩站点siteId={}，执行分表存储过程，CALL ds_jd_site_pull({},{},{},{},@num);SELECT @num;返回数据rows={}", siteId,
////                                        siteId, key, startId, endId, rows);
////                                String result = dsDao.createAuditAndReportForDSGF(siteId, startId, endId);
////                                log.info("DS-JD经典彩站点siteId={}，执行审计报表存储过程，CALL ds_jd_audit_report({},{},{},@result);SELECT @result;返回结果result={}", siteId,
////                                        siteId, startId, endId, result);
////                            }
////                        }
////                    } catch (Exception e) {
////                        log.info(msg.getCode()+"彩票拉取系统异常={}", e.getMessage());
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
////            log.info(msg.getCode()+"彩票拉取系统异常={}", e.getMessage());
////            e.printStackTrace();
////        }
////    }
//
////    @JmsListener(destination = MDT_JD_TYPE)
////    public void receiverMsg03(MsgModel msg){
////        log.info("收到消息，开始分发="+msg);
////        DateModel dsLotteryModel=(DateModel)msg.getObject();
////        Map<String,String> map=dsLotteryModel.getSiteId();
////        CountDownLatch cdl = new CountDownLatch(map.size());
////        map.forEach((key,value)-> {
////            executorService.submit(new Runnable() {
////                @Override
////                public void run() {
////                    try {
////                        if (!executorService.isShutdown()) {
////                            if (MDT_JD_TYPE.equals(msg.getCode())) {
////                                Integer siteId = Integer.parseInt(value);
////                                Integer rows = dsDao.sitePullForMdtJD(siteId, key, startId, endId);
////                                log.info("MDT-JD经典彩站点siteId={}，执行分表存储过程，CALL mdt_jd_site_pull({},{},{},{},@num);SELECT @num;返回数据rows={}", siteId,
////                                        siteId, key, startId, endId, rows);
////                                String result = dsDao.createAuditAndReportForMdtJD(siteId, startId, endId);
////                                log.info("MDT-JD经典彩站点siteId={}，执行审计报表存储过程，CALL mdt_jd_audit_report({},{},{},@result);SELECT @result;返回结果result={}", siteId,
////                                        siteId, startId, endId, result);
////                            }
////                        }
////                    } catch (Exception e) {
////                        log.info(msg.getCode()+"彩票拉取系统异常={}", e.getMessage());
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
////            log.info(msg.getCode()+"彩票拉取系统异常={}", e.getMessage());
////            e.printStackTrace();
////        }
////    }
//
//}
