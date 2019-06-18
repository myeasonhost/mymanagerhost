package com.eason.report.pull.core.api.service;

import com.eason.report.pull.core.exception.DataException;
import com.eason.report.pull.core.mongo.po.DtGFMgoPo;
import com.eason.report.pull.core.mysqlDao.DSAuditTotalDao;
import com.eason.report.pull.core.po.DtGuangfangLotteryPo;
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
public class DSGFServiceImpl {
    @Autowired
    private DSAuditTotalDao dsDao;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Resource
    private EntityManager entityManager;

    @Transactional
    public boolean insertSource(Integer siteId, String type, Long startId, Long endId) throws DataException {
        try{
            List<DtGFMgoPo> list=mongoTemplate.find(query(where("id")
                    .gte(startId)
                    .lte(endId)),DtGFMgoPo.class);
            log.info("DS-GF官方彩站点siteId={}，接收数据rows={}",siteId,list.size());
            list.forEach(dtGFMgoPo -> {
                DtGuangfangLotteryPo po=entityManager.find(DtGuangfangLotteryPo.class,dtGFMgoPo.getId());
                if(po==null){
                    po=new DtGuangfangLotteryPo();
                    BeanUtils.copyProperties(dtGFMgoPo,po);
                    entityManager.persist(po);
                }else{
                    BeanUtils.copyProperties(dtGFMgoPo,po);
                    entityManager.merge(po);
                }
            });
            if(list.isEmpty()){
                log.error("DS-GF官方彩站点siteId={}，接收数据rows={},为空，请查看传参",siteId,list.size());
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
            String result = dsDao.createAuditAndReportForDSGF(siteId, type, startId, endId);
            log.info("DS-GF官方彩站点siteId={}，执行审计报表存储过程，CALL ds_gf_audit_report({},{},{},@result);SELECT @result;", siteId,
                    siteId, startId,endId);
            log.info("审计报表Procedure返回结果result={}",result);
            if(result.isEmpty()){
                log.error("DS-GF官方彩站点siteId={}，执行审计报表存储过程为空，请查看传参",siteId);
                return false;
            }
            return true;
        }catch (Exception e){
            throw new DataException(e);
        }
    }

    @Transactional
    public int deleteRollback(Integer siteId, String type, Long startId, Long endId){
        List<DtGFMgoPo> list=mongoTemplate.findAllAndRemove(query(where("id")
                .gte(startId)
                .lte(endId)),DtGFMgoPo.class);
        if(list.isEmpty()){
            log.error("DS-GF官方彩站点siteId={}，数据回滚操作为空，请查看传参",siteId);
            return 0;
        }
        return list.size();
    }
}
