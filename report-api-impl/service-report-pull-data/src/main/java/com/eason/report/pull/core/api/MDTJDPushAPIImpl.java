package com.eason.report.pull.core.api;

import com.eason.report.pull.core.annotation.AuditReport;
import com.eason.report.pull.core.annotation.MQConsumer;
import com.eason.report.pull.core.annotation.SourceQuery;
import com.eason.report.pull.core.api.service.SourceServiceImpl;
import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.core.mongo.po.MdtJDMgoPo;
import com.eason.report.pull.core.mysqlDao.AuditTotalDao;
import com.eason.report.pull.core.po.MdtJingdianLotteryPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MQConsumer(code = "MDT-JD")
@Slf4j
public class MDTJDPushAPIImpl{
    @Autowired
    private SourceServiceImpl sourceService;

    @SourceQuery(targetId = "id",targetMgo = MdtJDMgoPo.class)
    public void getPushBet(Integer siteId, List<MdtJDMgoPo> list) throws ServiceException {
        try{
            log.info("Mdt-JD经典彩站点siteId={}，接收数据rows={}",siteId,list.size());
            sourceService.insertSource(list, MdtJingdianLotteryPo.class);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @AuditReport(procedureName = "createAuditAndReportForMdtJD",targetDao = AuditTotalDao.class)
    public void auditReport(Integer siteId,String result) {
        log.info("审计报表Procedure返回结果result={}",result);
        if(result.isEmpty()){
            log.error("Mdt-JD经典彩站点siteId={}，执行审计报表存储过程为空，请查看传参",siteId);
        }
    }
}
