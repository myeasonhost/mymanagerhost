package com.eason.report.pull.core.api;

import com.eason.report.pull.core.annotation.MQConsumer;
import com.eason.report.pull.core.model.ResponseModel;
import com.eason.report.pull.core.mongo.mgo.DtGFMgo;
import com.eason.report.pull.core.mongo.po.DtGFMgoPo;
import com.eason.report.pull.core.mq.MQServiceConsumer;
import com.eason.report.pull.core.mysqlDao.DSAuditTotalDao;
import com.eason.report.pull.core.po.DtGuangfangLotteryPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@MQConsumer
@Slf4j
public class DSGFPushAPIImpl extends MQServiceConsumer implements PushAPI {

    @Autowired
    private DSAuditTotalDao dsDao;
    @Autowired
    private DtGFMgo dtGFMgo;
    @Resource
    private EntityManager entityManager;


    @Override
    @Transactional
    public ResponseModel getPushBet(Integer siteId, String prex, Long startId, Long endId) {
        List<DtGFMgoPo> list=dtGFMgo.findAllBySiteId(siteId,startId,endId);
        list.forEach(dtGFMgoPo -> {
            DtGuangfangLotteryPo dtGuangfangLotteryPo=entityManager.find(DtGuangfangLotteryPo.class,dtGFMgoPo.getId());
            if(dtGuangfangLotteryPo==null){
                dtGuangfangLotteryPo=new DtGuangfangLotteryPo();
                BeanUtils.copyProperties(dtGFMgoPo,dtGuangfangLotteryPo);
                entityManager.persist(dtGuangfangLotteryPo);
            }else{
                BeanUtils.copyProperties(dtGFMgoPo,dtGuangfangLotteryPo);
                entityManager.merge(dtGuangfangLotteryPo);
            }
        });
        log.info("DS-GF官方彩站点siteId={}，接收数据rows={}",siteId,list.size());
        String result = dsDao.createAuditAndReportForDSGF(siteId, startId, endId);
        log.info("DS-GF官方彩站点siteId={}，执行审计报表存储过程，CALL ds_gf_audit_report({},{},{},@result);SELECT @result;", siteId,
                siteId, startId,endId);
        log.info("审计报表Procedure返回结果result={}",result);
        return successModel;
    }

    @Override
    public ResponseModel getPushBet(Integer siteId, String prex, String startId, String endId) {
        return errorModel;
    }
}
