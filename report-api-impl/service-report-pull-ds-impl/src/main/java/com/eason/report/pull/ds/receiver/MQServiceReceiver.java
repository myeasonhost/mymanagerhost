package com.eason.report.pull.ds.receiver;

import com.eason.report.pull.ds.model.DsLotteryModel;
import com.eason.report.pull.ds.model.MsgModel;
import com.eason.report.pull.ds.mysqlDao.DtGFDao;
import com.eason.report.pull.ds.mysqlDao.DtJDDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class MQServiceReceiver {
    @Autowired
    private DtGFDao dtGFDao;
    @Autowired
    private DtJDDao dtJDDao;

    private ExecutorService executorService =  Executors.newFixedThreadPool(5);

    @JmsListener(destination = "ds_pull_error")
    public void errorMsg(MsgModel msg){
        log.info("收到ERROR消息，请注意="+msg);
        if("DS-GF-1111".equals(msg.getCode())){

        }else if("DS-JD-1111".equals(msg.getCode())){

        }
    }

    @JmsListener(destination = "ds_pull_receiver")
    public void receiverMsg(MsgModel msg){
        log.info("收到消息，开始分发="+msg);
        DsLotteryModel dsLotteryModel=(DsLotteryModel)msg.getObject();
        Map<String,String> map=dsLotteryModel.getSiteId();
        CountDownLatch cdl = new CountDownLatch(map.size());
        map.forEach((key,value)-> {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (!executorService.isShutdown()) {
                            if ("DS-GF-0000".equals(msg.getCode())) {
                                Integer siteId = Integer.parseInt(value);
                                Integer rows = dtGFDao.sitePull(siteId, key, dsLotteryModel.getStartId(), dsLotteryModel.getEndId());
                                log.info("DS-GF官方彩站点siteId={}，执行分表存储过程，CALL ds_gf_site_pull({},{},{},{},@num);SELECT @num;返回数据rows={}", siteId,
                                        siteId, key, dsLotteryModel.getStartId(), dsLotteryModel.getEndId(), rows);
                                String result = dtGFDao.createAuditAndReport(siteId, dsLotteryModel.getStartId(), dsLotteryModel.getEndId());
                                log.info("DS-GF官方彩站点siteId={}，执行审计报表存储过程，CALL ds_gf_audit_report({},{},{},@result);SELECT @result;返回结果result={}", siteId,
                                        siteId, dsLotteryModel.getStartId(), dsLotteryModel.getEndId(), result);
                            } else if ("DS-JD-0000".equals(msg.getCode())) {
                                Integer siteId = Integer.parseInt(value);
                                Integer rows = dtJDDao.sitePull(siteId, key, dsLotteryModel.getStartId(), dsLotteryModel.getEndId());
                                log.info("DS-JD经典彩站点siteId={}，执行分表存储过程，CALL ds_jd_site_pull({},{},{},{},@num);SELECT @num;返回数据rows={}", siteId,
                                        siteId, key, dsLotteryModel.getStartId(), dsLotteryModel.getEndId(), rows);
                                String result = dtJDDao.createAuditAndReport(siteId, dsLotteryModel.getStartId(), dsLotteryModel.getEndId());
                                log.info("DS-JD经典彩站点siteId={}，执行审计报表存储过程，CALL ds_jd_audit_report({},{},{},@result);SELECT @result;返回结果result={}", siteId,
                                        siteId, dsLotteryModel.getStartId(), dsLotteryModel.getEndId(), result);
                            } else {
                                log.error("消息信号ds_pull_receiver不正确，仅支持DS-GF-0000或者DS-JD-0000");
                            }
                        }
                    } catch (Exception e) {
                        log.info(msg.getCode()+"彩票拉取系统异常={}", e.getMessage());
                        e.printStackTrace();
                    } finally {
                        cdl.countDown();

                    }
                }
            });
        });
        try {
            cdl.await();
        } catch (InterruptedException e) {
            log.info(msg.getCode()+"彩票拉取系统异常={}", e.getMessage());
            e.printStackTrace();
        }
    }

}
