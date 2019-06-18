package com.eason.report.pull.core.api.service;

import com.eason.report.pull.core.exception.DataException;
import com.eason.report.pull.core.mongo.po.MGMgoPo;
import com.eason.report.pull.core.mysqlDao.DSAuditTotalDao;
import com.eason.report.pull.core.po.DsMgGamePo;
import com.eason.report.pull.core.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.ConvertOperators.ToDate.toDate;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Slf4j
public class MGServiceImpl {
    @Autowired
    private DSAuditTotalDao dsDao;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Resource
    private EntityManager entityManager;

    @Transactional
    public boolean insertSource(Integer siteId, String type, String startId, String endId) throws DataException {
        try{
            List<MGMgoPo> list=mongoTemplate.find(query(where("transTime")
                    .gte(DateUtil.covertTime(startId))
                    .lte(DateUtil.covertTime(endId))),MGMgoPo.class);
            log.info("MG大富豪站点siteId={}，接收数据rows={}",siteId,list.size());
            list.forEach(mgMgoPo -> {
                DsMgGamePo po=entityManager.find(DsMgGamePo.class,mgMgoPo.getColId());
                if(po==null){
                    po=new DsMgGamePo();
                    BeanUtils.copyProperties(mgMgoPo,po);
                    entityManager.persist(po);
                }else{
                    BeanUtils.copyProperties(mgMgoPo,po);
                    entityManager.merge(po);
                }
            });
            if(list.isEmpty()){
                log.error("MG大富豪站点siteId={}，接收数据rows={},为空，请查看传参",siteId,list.size());
                return false;
            }
            return true;
        }catch (Exception e){
            throw new DataException(e);
        }
    }

    @Transactional
    public boolean insertReport(Integer siteId, String type, String startId, String endId) throws DataException {
        try{
            String result = dsDao.createAuditAndReportForMG(siteId, type, startId, endId);
            log.info("MG大富豪站点siteId={}，执行审计报表存储过程，CALL ds_mg_audit_report({},{},{},@result);SELECT @result;", siteId,
                    siteId, startId,endId);
            log.info("审计报表Procedure返回结果result={}",result);
            if(result.isEmpty()){
                log.error("MG大富豪站点siteId={}，执行审计报表存储过程为空，请查看传参",siteId);
                return false;
            }
            return true;
        }catch (Exception e){
            throw new DataException(e);
        }
    }

    @Transactional
    public int deleteRollback(Integer siteId, String type, String startId, String endId){
        List<MGMgoPo> list=mongoTemplate.findAllAndRemove(query(where("transTime")
                .gte(toDate(startId))
                .lte(toDate(endId))),MGMgoPo.class);
        if(list.isEmpty()){
            log.error("MG大富豪站点siteId={}，数据回滚操作为空，请查看传参",siteId);
            return 0;
        }
        return list.size();
    }
}
