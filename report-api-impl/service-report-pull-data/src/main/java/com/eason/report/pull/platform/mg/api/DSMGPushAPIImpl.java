package com.eason.report.pull.platform.mg.api;

import com.eason.report.pull.core.annotation.AuditReport;
import com.eason.report.pull.core.annotation.MQConsumer;
import com.eason.report.pull.core.annotation.SourceQuery;
import com.eason.report.pull.core.api.service.SourceServiceImpl;
import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.core.mongo.po.MGMgoPo;
import com.eason.report.pull.core.dao.AuditTotalDao;
import com.eason.report.pull.platform.mg.po.DsMgGamePo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MQConsumer(name = "DSMGPullAPIImpl",code = "DS-MG")
@Slf4j
public class DSMGPushAPIImpl {

    @Autowired
    private SourceServiceImpl sourceService;

    @SourceQuery(targetId = "transTime",targetMgo = MGMgoPo.class)
    public void getPushBet(Integer siteId, List<MGMgoPo> list) throws ServiceException {
        try{
            log.info("Mdt-JD经典彩站点siteId={}，接收数据rows={}",siteId,list.size());
            sourceService.insertSource(list, DsMgGamePo.class);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @AuditReport(procedureName = "createAuditAndReportForMG",targetDao = AuditTotalDao.class)
    public void auditReport(Integer siteId,String result) {
        log.info("审计报表Procedure返回结果result={}",result);
        if(result.isEmpty()){
            log.error("Mdt-JD经典彩站点siteId={}，执行审计报表存储过程为空，请查看传参",siteId);
        }
    }
}
