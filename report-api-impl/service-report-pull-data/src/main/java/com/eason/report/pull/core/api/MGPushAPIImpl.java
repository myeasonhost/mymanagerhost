package com.eason.report.pull.core.api;

import com.eason.report.pull.core.annotation.MQConsumer;
import com.eason.report.pull.core.model.ResponseModel;
import com.eason.report.pull.core.mq.MQServiceConsumer;
import com.eason.report.pull.core.mysqlDao.MGAuditTotalDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@MQConsumer
@Slf4j
public class MGPushAPIImpl extends MQServiceConsumer implements PushAPI {

    @Autowired
    private MGAuditTotalDao dsDao;

    @Override
    public ResponseModel getPushBet(Integer siteId, String prex, Long startId, Long endId) {
        return errorModel;
    }

    @Override
    public ResponseModel getPushBet(Integer siteId, String prex, String startId, String endId) {
        Integer rows = dsDao.sitePull(siteId, prex, startId, endId);
        log.info("MG站点siteId={}，执行分表存储过程，CALL ds_mg_site_pull({},'{}','{}','{}',@num);SELECT @num;", siteId,
                siteId, prex,startId, endId);
        log.info("分表Procedure返回数据rows={}",rows);
        String result = dsDao.createAuditAndReport(siteId, startId, endId);
        log.info("MG站点siteId={}，执行审计报表存储过程，CALL ds_mg_audit_report({},'{}','{}',@result);SELECT @result;", siteId,
                siteId, startId,endId);
        log.info("审计报表Procedure返回结果result={}",result);
        return successModel;
    }
}
