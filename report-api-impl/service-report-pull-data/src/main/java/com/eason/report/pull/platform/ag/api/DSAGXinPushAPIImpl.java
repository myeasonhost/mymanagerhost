package com.eason.report.pull.platform.ag.api;

import com.eason.report.pull.core.annotation.AuditReport;
import com.eason.report.pull.core.annotation.MQConsumer;
import com.eason.report.pull.core.annotation.SourceQuery;
import com.eason.report.pull.core.api.service.SourceServiceImpl;
import com.eason.report.pull.core.dao.AuditTotalDao;
import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.platform.ag.mgo.DSAGXinMgoPo;
import com.eason.report.pull.platform.ag.po.DsAgXinPo;
import com.eason.report.pull.platform.ag.po.DsAgYoplayPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MQConsumer(name = "DSAGXinPushAPIImpl",code = "DS-AG-XIN")
@Slf4j
public class DSAGXinPushAPIImpl {

    @Autowired
    private SourceServiceImpl sourceService;

    @SourceQuery(targetId = "reckonTime",targetMgo = DSAGXinMgoPo.class)
    public void getPushBet(Integer siteId, List<DSAGXinMgoPo> list) throws ServiceException {
        try{
            log.info("DS-AG站点siteId={}，接收数据rows={}",siteId,list.size());
            sourceService.insertSource(list, DsAgXinPo.class);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @AuditReport(procedureName = "createAuditAndReportForDSAGXin",targetDao = AuditTotalDao.class)
    public void auditReport(Integer siteId,String result) {
        log.info("审计报表Procedure返回结果result={}",result);
        if(result.isEmpty()){
            log.error("DS-AG站点siteId={}，执行审计报表存储过程为空，请查看传参",siteId);
        }
    }
}
