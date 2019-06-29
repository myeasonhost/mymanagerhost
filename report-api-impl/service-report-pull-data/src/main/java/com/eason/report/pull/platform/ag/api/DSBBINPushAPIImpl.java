package com.eason.report.pull.platform.ag.api;

import com.eason.report.pull.core.annotation.AuditReport;
import com.eason.report.pull.core.annotation.MQConsumer;
import com.eason.report.pull.core.annotation.SourceQuery;
import com.eason.report.pull.core.api.service.SourceServiceImpl;
import com.eason.report.pull.core.dao.AuditTotalDao;
import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.platform.bbin.mgo.DSBBINMgoPo;
import com.eason.report.pull.platform.bbin.po.DsBbinGamePo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MQConsumer(name = "DSBBINPushAPIImpl",code = "DS-BBIN")
@Slf4j
public class DSBBINPushAPIImpl {

    @Autowired
    private SourceServiceImpl sourceService;

    @SourceQuery(targetId = "wagersDate",targetMgo = DSBBINMgoPo.class)
    public void getPushBet(Integer siteId, List<DSBBINMgoPo> list) throws ServiceException {
        try{
            log.info("DS-BBIN站点siteId={}，接收数据rows={}",siteId,list.size());
            sourceService.insertSource(list, DsBbinGamePo.class);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @AuditReport(procedureName = "createAuditAndReportForDSBBIN",targetDao = AuditTotalDao.class)
    public void auditReport(Integer siteId,String result) {
        log.info("审计报表Procedure返回结果result={}",result);
        if(result.isEmpty()){
            log.error("DS-BBIN站点siteId={}，执行审计报表存储过程为空，请查看传参",siteId);
        }
    }
}
