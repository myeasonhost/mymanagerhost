package com.eason.report.pull.platform.lottery.api;

import com.eason.report.pull.core.annotation.AuditReport;
import com.eason.report.pull.core.annotation.MQConsumer;
import com.eason.report.pull.core.annotation.SourceQuery;
import com.eason.report.pull.core.api.service.SourceServiceImpl;
import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.core.mongo.po.DtGFMgoPo;
import com.eason.report.pull.core.dao.AuditTotalDao;
import com.eason.report.pull.platform.lottery.po.DtGuangfangLotteryPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MQConsumer(name = "DSGFPushAPIImpl",code = "DS-GF")
@Slf4j
public class DSGFPushAPIImpl{
    @Autowired
    private SourceServiceImpl sourceService;


    @SourceQuery(targetId = "id",targetMgo = DtGFMgoPo.class)
    public void getPushBet(Integer siteId,List<DtGFMgoPo> list) throws ServiceException{
        try{
            log.info("DS-GF官方彩站点siteId={}，接收数据rows={}",siteId,list.size());
            sourceService.insertSource(list,DtGuangfangLotteryPo.class);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @AuditReport(procedureName = "createAuditAndReportForDSGF",targetDao = AuditTotalDao.class)
    public void auditReport(Integer siteId,String result) {
        log.info("审计报表Procedure返回结果result={}",result);
        if(result.isEmpty()){
            log.error("DS-GF官方彩站点siteId={}，执行审计报表存储过程为空，请查看传参",siteId);
        }
    }

}
