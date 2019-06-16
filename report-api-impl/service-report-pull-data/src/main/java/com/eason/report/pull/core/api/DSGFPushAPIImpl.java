package com.eason.report.pull.core.api;

import com.eason.report.pull.core.annotation.MQConsumer;
import com.eason.report.pull.core.api.service.DSGFServiceImpl;
import com.eason.report.pull.core.model.ResponseModel;
import com.eason.report.pull.core.mq.MQServiceConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@MQConsumer
@Slf4j
public class DSGFPushAPIImpl extends MQServiceConsumer implements PushAPI {
    @Autowired
    private DSGFServiceImpl dSGFServiceImpl;

    @Override
    public ResponseModel getPushBet(Integer siteId, String type, Long startId, Long endId) {
        try{
            if(dSGFServiceImpl.insertSource(siteId, type, startId, endId)){
                dSGFServiceImpl.insertReport(siteId, type, startId, endId);
                return successModel;
            }
            return errorModel;
        }catch (Exception e){
            int num=dSGFServiceImpl.deleteRollback(siteId, type, startId, endId);
            log.error("DS-GF官方彩站点siteId={}，数据回滚num={}，异常信息={}",siteId,num,e.getMessage());
            e.printStackTrace();
            return errorModel;
        }
    }

    @Override
    public ResponseModel getPushBet(Integer siteId, String type, String startId, String endId) {
        return errorModel;
    }
}
