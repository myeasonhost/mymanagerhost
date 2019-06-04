package com.eason.report.pull.core.api;

import com.eason.report.pull.core.annotation.MQConsumer;
import com.eason.report.pull.core.mongo.mgo.DtGFMgo;
import com.eason.report.pull.core.mysqlDao.DSAuditTotalDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@MQConsumer
@Slf4j
public class DSGFPushAPIImpl extends PushAPIService {

    @Autowired
    private DSAuditTotalDao dsDao;
    @Autowired
    private DtGFMgo dtGFMgo;

    @Override
    public void getPushBet(Integer siteId, String prex, Long startId, Long endId) {
        Integer rows = dsDao.sitePullForDSGF(siteId, prex, startId, endId);
        log.info("DS-GF官方彩站点siteId={}，执行分表存储过程，CALL ds_gf_site_pull({},'{}',{},{},@num);SELECT @num;", siteId,
                siteId, prex,startId, endId);
        log.info("分表Procedure返回数据rows={}",rows);
        String result = dsDao.createAuditAndReportForDSGF(siteId, startId, endId);
        log.info("DS-GF官方彩站点siteId={}，执行审计报表存储过程，CALL ds_gf_audit_report({},{},{},@result);SELECT @result;", siteId,
                siteId, startId,endId);
        log.info("审计报表Procedure返回结果result={}",result);
    }

    @Override
    public void getPushBet(Integer siteId, String prex, String startId, String endId) {

    }
}
