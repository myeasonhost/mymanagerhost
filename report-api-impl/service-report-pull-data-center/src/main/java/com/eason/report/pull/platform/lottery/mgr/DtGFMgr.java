package com.eason.report.pull.platform.lottery.mgr;


import com.eason.report.pull.core.mgr.IPullMgr;
import com.eason.report.pull.core.mongo.po.DtGFMgoPo;
import com.eason.report.pull.platform.lottery.dao.po.DtLotteryConfigPo;
import com.eason.report.pull.core.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Slf4j
public class DtGFMgr implements IPullMgr<DtGFMgoPo,DtLotteryConfigPo> {
  @Resource
  private MongoTemplate mongoTemplate;

  @Override
  public DtGFMgoPo extAttr(DtGFMgoPo po,DtLotteryConfigPo configPo) {
    po.setReportTime(new Date());
    po.setBetTime(DateUtil.covertGMTTime(po.getAddTime()));
    for(Map.Entry<Integer,String> site:configPo.getSiteMap().entrySet()){
      if(po.getUserName().startsWith(site.getValue())){
        po.setSiteId(site.getKey());
        po.setUserName(po.getUserName().substring(site.getValue().length()));
      }
    }
    /**
     * 撤单状态(0未撤单 1用户撤单 2追中撤单 3出号撤单 4未开撤单 5结算前检查撤销 9管理员撤单)
     */
    if (po.getIsCancel() != 0) { // 取消 前端定义
      po.setWinLoseType(Byte.valueOf("4")); // 取消 数据库定义
      return po;
    }
    /**
     * 判断订单已结算 isTake==1 isPay==1 isCancel==0
     */
    if (po.getIsCancel() == 0 && po.getIsPay() == 1 && po.getIsCancel() == 0) {
      BigDecimal wins = po.getWins().subtract(po.getAmount());
      if (wins.doubleValue() > 0) {
        po.setWinLoseType(Byte.valueOf("2")); // 赢 数据库定义
      } else if (wins.doubleValue() < 0) {
        po.setWinLoseType(Byte.valueOf("1")); // 输 数据库定义
      } else {
        po.setWinLoseType(Byte.valueOf("3")); // 和 数据库定义
      }
      po.setWins(wins);
    }
    return po;
  }

  @Transactional
  @Override
  public void saveAndUpdate(DtGFMgoPo po,DtLotteryConfigPo configPo){
    po=this.extAttr(po,configPo);
    Optional<DtGFMgoPo> result =mongoTemplate.update(DtGFMgoPo.class)
            .matching(query(where("id").is(po.getId())))
            .replaceWith(po)
            .withOptions(FindAndReplaceOptions.options().upsert())
            .findAndReplace();
    if(result.isPresent()){
      log.info("DS-GF存在重复值DtGFMgoPo={}",result.get().toString());
    }
  }
  @Override
  public Long getMaxId(DtLotteryConfigPo configPo){
    TypedAggregation<DtGFMgoPo> agg = newAggregation(DtGFMgoPo.class,
            match(where("top4").is(configPo.getAgentId())),
            group().max("$id").as("id")
    );
    AggregationResults<DtGFMgoPo> results = mongoTemplate.aggregate(agg,DtGFMgoPo.class);
    DtGFMgoPo po = results.getUniqueMappedResult();
    if(po==null){
      return configPo.getInitStartId();
    }
    return po.getId();
  }

  @Override
  public Long getNextId(DtLotteryConfigPo configPo) {
    return getMaxId(configPo)+1;
  }

}
