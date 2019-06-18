package com.eason.report.pull.core.api;

import com.eason.report.pull.core.annotation.MQConsumer;
import com.eason.report.pull.core.api.service.DSJDServiceImpl;
import com.eason.report.pull.core.model.ResponseModel;
import com.eason.report.pull.core.mongo.po.DtJDMgoPo;
import com.eason.report.pull.core.mq.MQServiceConsumer;
import com.eason.report.pull.core.mysqlDao.DSAuditTotalDao;
import com.eason.report.pull.core.mysqlDao.DtJDDao;
import com.eason.report.pull.core.po.DtJingdianLotteryPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@MQConsumer
@Slf4j
public class DSJDPushAPIImpl extends MQServiceConsumer implements PushAPI {
    @Autowired
    private DSJDServiceImpl dSJDServiceImpl;

    @Override
    public ResponseModel getPushBet(Integer siteId, String type, Long startId, Long endId) {
        try{
            if(dSJDServiceImpl.insertSource(siteId, type, startId, endId)){
                dSJDServiceImpl.insertReport(siteId, type, startId, endId);
                return successModel;
            }
            return errorModel;
        }catch (Exception e){
            int num=dSJDServiceImpl.deleteRollback(siteId, type, startId, endId);
            log.error("DS-JD经典彩站点siteId={}，数据回滚num={}，异常信息={}",siteId,num,e.getMessage());
            e.printStackTrace();
            return errorModel;
        }
    }

    @Override
    public ResponseModel getPushBet(Integer siteId, String type, String startId, String endId) {
        return errorModel;
    }
}
