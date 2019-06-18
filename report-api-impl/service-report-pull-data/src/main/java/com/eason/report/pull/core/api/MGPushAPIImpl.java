package com.eason.report.pull.core.api;

import com.eason.report.pull.core.annotation.MQConsumer;
import com.eason.report.pull.core.api.service.MGServiceImpl;
import com.eason.report.pull.core.model.ResponseModel;
import com.eason.report.pull.core.mq.MQServiceConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@MQConsumer
@Slf4j
public class MGPushAPIImpl extends MQServiceConsumer implements PushAPI {

    @Autowired
    private MGServiceImpl mgServiceImpl;

    @Override
    public ResponseModel getPushBet(Integer siteId, String type, String startId, String endId) {
        try{
            if(mgServiceImpl.insertSource(siteId, type, startId, endId)){
                mgServiceImpl.insertReport(siteId, type, startId, endId);
                return successModel;
            }
            return errorModel;
        }catch (Exception e){
            int num=mgServiceImpl.deleteRollback(siteId, type, startId, endId);
            log.error("MG大富豪站点siteId={}，数据回滚num={}，异常信息={}",siteId,num,e.getMessage());
            e.printStackTrace();
            return errorModel;
        }
    }

    @Override
    public ResponseModel getPushBet(Integer siteId, String type, Long startId, Long endId) {
        return errorModel;
    }
}
