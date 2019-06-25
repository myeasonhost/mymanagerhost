package com.eason.report.pull.platform.pt.api;

import com.eason.report.pull.core.annotation.AuditReport;
import com.eason.report.pull.core.annotation.MQConsumer;
import com.eason.report.pull.core.annotation.SourceQuery;
import com.eason.report.pull.core.api.service.SourceServiceImpl;
import com.eason.report.pull.core.dao.AuditTotalDao;
import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.platform.ky.mgo.KyMgoPo;
import com.eason.report.pull.platform.ky.po.KyChessGamePo;
import com.eason.report.pull.platform.lmg.mgo.LMGMgoPo;
import com.eason.report.pull.platform.lmg.po.DsLmgGamePo;
import com.eason.report.pull.platform.pt.mgo.DSPTMgoPo;
import com.eason.report.pull.platform.pt.po.DsPtGamePo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MQConsumer(name = "DSPTPushAPIImpl",code = "DS-PT")
@Slf4j
public class DSPTPushAPIImpl {

    @Autowired
    private SourceServiceImpl sourceService;

    @SourceQuery(targetId = "gamedate",targetMgo = DSPTMgoPo.class)
    public void getPushBet(Integer siteId, List<DSPTMgoPo> list) throws ServiceException {
        try{
            log.info("DS-PT站点siteId={}，接收数据rows={}",siteId,list.size());
            sourceService.insertSource(list, DsPtGamePo.class);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @AuditReport(procedureName = "createAuditAndReportForDSPT",targetDao = AuditTotalDao.class)
    public void auditReport(Integer siteId,String result) {
        log.info("审计报表Procedure返回结果result={}",result);
        if(result.isEmpty()){
            log.error("DS-PT站点siteId={}，执行审计报表存储过程为空，请查看传参",siteId);
        }
    }
}
