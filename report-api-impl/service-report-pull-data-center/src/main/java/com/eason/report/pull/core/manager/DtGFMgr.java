package com.eason.report.pull.core.manager;


import com.eason.report.pull.core.mongo.config.SequenceId;
import com.eason.report.pull.core.mongo.po.DtGFMgoPo;
import com.eason.report.pull.core.mysqlDao.config.DtLotteryConfigPo;
import com.eason.report.pull.core.mysqlDao.dao.DtLotteryConfigDao;
import com.eason.report.pull.core.mysqlDao.po.DsGameTypePo;
import com.eason.report.pull.core.mysqlDao.vo.DsGameTypeVo;
import com.eason.report.pull.core.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Slf4j
public class DtGFMgr {
  @Autowired
  private DtLotteryConfigDao dtLotteryConfigDao;
  @Resource
  private EntityManager entityManager;
  @Resource
  private MongoTemplate mongoTemplate;

  public List<DtLotteryConfigPo> loadConfig(){
    List<DtLotteryConfigPo> dfConfigList=dtLotteryConfigDao.findDsDfConfig();
    dfConfigList.forEach(po -> {
      Map<Integer,String> map=new HashMap<>();
      String[] ary=po.getSiteId().split(","); //TYZ_1020,MHD_1040,MAA_1070,MAB_1080
      for (String s:ary){ //TYZ_1020
        String[] i=s.split("_");
        map.put(Integer.parseInt(i[1]),po.getUser()+"_"+i[0]);
      }
      po.setSiteMap(map);
    });
    log.info("DS-GF读取缓存的配置：dfConfigList="+dfConfigList);
    return dfConfigList;
  }

  public DtGFMgoPo extAttr(DtGFMgoPo po,DtLotteryConfigPo configPo) {
    po.setReportTime(DateUtil.covertGMTTime(po.getDrawTime()));
    po.setBetTime(DateUtil.covertGMTTime(po.getAddTime()));
    for(Map.Entry<Integer,String> site:configPo.getSiteMap().entrySet()){
      if(po.getUserName().startsWith(site.getValue())){
        po.setSiteid(site.getKey());
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
  public void saveAndUpdate(DtGFMgoPo po,DtLotteryConfigPo configPo, List<DsGameTypeVo> dsGameTypePoList){
    po=this.extAttr(po,configPo);
    boolean flag=false;
    for(DsGameTypeVo vo:dsGameTypePoList){
      if(po.getLid().toString().equals(vo.getOutGameCode())){
        flag=true;
        break;
      }
    }
    if(!flag){
      DsGameTypePo dsGameTypePo1=new DsGameTypePo();
      String newGameName=String.format(configPo.getInfo(),po.getLid());
      dsGameTypePo1.setGameName(newGameName);
      dsGameTypePo1.setOutGameCode(po.getLid().toString());
      dsGameTypePo1.setParentId(Integer.parseInt(configPo.getGameKind().split(",")[0]));
      dsGameTypePo1.setFkLiveId(configPo.getLiveId().byteValue());
      entityManager.persist(dsGameTypePo1);
    }

    Optional<DtGFMgoPo> result =mongoTemplate.update(DtGFMgoPo.class)
            .matching(query(where("id").is(po.getId())))
            .replaceWith(po)
            .withOptions(FindAndReplaceOptions.options().upsert())
            .findAndReplace();
    if(result.isPresent()){
      log.info("DS-GF存在重复值DtGFMgoPo={}",result.get().toString());
    }
  }

  public Long getMaxId(){
    TypedAggregation<DtGFMgoPo> agg = newAggregation(DtGFMgoPo.class,
            group().max("$id").as("id")
    );
    AggregationResults<DtGFMgoPo> results = mongoTemplate.aggregate(agg,DtGFMgoPo.class);
    DtGFMgoPo po = results.getUniqueMappedResult();
    return po.getId();
  }

}
