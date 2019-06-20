package com.eason.report.pull.core.manager;


import com.eason.report.pull.core.api.IPullMgr;
import com.eason.report.pull.core.mongo.po.DtJDMgoPo;
import com.eason.report.pull.core.mongo.po.MdtJDMgoPo;
import com.eason.report.pull.core.mysqlDao.config.DtLotteryConfigPo;
import com.eason.report.pull.core.mysqlDao.dao.DtLotteryConfigDao;
import com.eason.report.pull.core.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Slf4j
public class MdtJDMgr implements IPullMgr<MdtJDMgoPo,DtLotteryConfigPo> {

  @Resource
  private MongoTemplate mongoTemplate;

  @Override
  public MdtJDMgoPo extAttr(MdtJDMgoPo po,DtLotteryConfigPo configPo) {
    po.setReportTime(new Date());
    po.setBetTime(DateUtil.covertGMTTime(po.getTimeAdd()));
    for(Map.Entry<Integer,String> site:configPo.getSiteMap().entrySet()){
      if(po.getUser().startsWith(site.getValue())){
        po.setSiteId(site.getKey());
        po.setUser(po.getUser().substring(site.getValue().length()));
      }
    }
    if (po.isCancel()) { // 取消 前端定义
      po.setWinLoseType(Byte.valueOf("4")); // 取消 数据库定义
      return po;
    }
    switch (po.getStataus()) {
      case 1: // 和 前端定义
        po.setWinLoseType(Byte.valueOf("3")); // 和 数据库定义
        break;
      case 2: // 赢 前端定义
        po.setWinLoseType(Byte.valueOf("2")); // 赢 数据库定义
        break;
      case 3: // 输 前端定义
        po.setWinLoseType(Byte.valueOf("1")); // 输 数据库定义
        break;
    }
    return po;
  }

  @Transactional
  @Override
  public void saveAndUpdate(MdtJDMgoPo po, DtLotteryConfigPo configPo){
    po=this.extAttr(po,configPo);
    Optional<MdtJDMgoPo> result =mongoTemplate.update(MdtJDMgoPo.class)
            .matching(query(where("id").is(po.getId())))
            .replaceWith(po)
            .withOptions(FindAndReplaceOptions.options().upsert())
            .findAndReplace();
    if(result.isPresent()){
      log.info("MDT-JD存在重复值MdtJDMgoPo={}",result.get().toString());
    }
  }

  @Override
  public Long getMaxId(DtLotteryConfigPo configPo){
    TypedAggregation<MdtJDMgoPo> agg = newAggregation(MdtJDMgoPo.class,
            match(where("user4").is(configPo.getAgentId())),
            group().max("$id").as("id")
    );
    AggregationResults<MdtJDMgoPo> results = mongoTemplate.aggregate(agg,MdtJDMgoPo.class);
    MdtJDMgoPo po = results.getUniqueMappedResult();
    return po.getId();
  }

  @Override
  public Long getNextId(DtLotteryConfigPo configPo) {
    return getMaxId(configPo)+1;
  }
}
