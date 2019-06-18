package com.eason.report.pull.core.api.service;

import com.eason.report.pull.core.exception.DataException;
import com.eason.report.pull.core.mongo.po.MdtJDMgoPo;
import com.eason.report.pull.core.mysqlDao.DSAuditTotalDao;
import com.eason.report.pull.core.po.MdtJingdianLotteryPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Slf4j
public class MDTJDServiceImpl {
    @Autowired
    private DSAuditTotalDao dsDao;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Resource
    private EntityManager entityManager;

    @Transactional
    public boolean insertSource(Integer siteId, String type, Long startId, Long endId) throws DataException {
        try{
            List<MdtJDMgoPo> list=mongoTemplate.find(query(where("id")
                    .gte(startId)
                    .lte(endId)),MdtJDMgoPo.class);
            log.info("MDT-JD经典彩站点siteId={}，接收数据rows={}",siteId,list.size());
            list.forEach(dtJDMgoPo -> {
                MdtJingdianLotteryPo po=entityManager.find(MdtJingdianLotteryPo.class,dtJDMgoPo.getId());
                if(po==null){
                    po=new MdtJingdianLotteryPo();
                    BeanUtils.copyProperties(dtJDMgoPo,po);
                    entityManager.persist(po);
                }else{
                    BeanUtils.copyProperties(dtJDMgoPo,po);
                    entityManager.merge(po);
                }
            });
            if(list.isEmpty()){
                log.error("MDT-JD经典彩站点siteId={}，接收数据rows={},为空，请查看传参",siteId,list.size());
                return false;
            }
            return true;
        }catch (Exception e){
            throw new DataException(e);
        }
    }

    @Transactional
    public boolean insertReport(Integer siteId, String type, Long startId, Long endId) throws DataException {
        try{
            String result = dsDao.createAuditAndReportForMdtJD(siteId, type, startId, endId);
            log.info("MDT-JD经典彩站点siteId={}，执行审计报表存储过程，CALL ds_jd_audit_report({},{},{},@result);SELECT @result;", siteId,
                    siteId, startId,endId);
            log.info("审计报表Procedure返回结果result={}",result);
            if(result.isEmpty()){
                log.error("MDT-JD经典彩站点siteId={}，执行审计报表存储过程为空，请查看传参",siteId);
                return false;
            }
            return true;
        }catch (Exception e){
            throw new DataException(e);
        }
    }

    @Transactional
    public int deleteRollback(Integer siteId, String type, Long startId, Long endId){
        List<MdtJDMgoPo> list=mongoTemplate.findAllAndRemove(query(where("id")
                .gte(startId)
                .lte(endId)),MdtJDMgoPo.class);
        if(list.isEmpty()){
            log.error("MDT-JD经典彩站点siteId={}，数据回滚操作为空，请查看传参",siteId);
            return 0;
        }
        return list.size();
    }
}
