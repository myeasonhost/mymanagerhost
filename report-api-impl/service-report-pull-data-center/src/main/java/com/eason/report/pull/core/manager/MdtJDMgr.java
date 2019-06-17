package com.eason.report.pull.core.manager;


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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Slf4j
public class MdtJDMgr {
  @Autowired
  private DtLotteryConfigDao dtLotteryConfigDao;

  @Resource
  private MongoTemplate mongoTemplate;

  public List<DtLotteryConfigPo> loadConfig(){
    List<DtLotteryConfigPo> dfConfigList=dtLotteryConfigDao.findMdtJdConfig();
    dfConfigList.forEach(po -> {
      Map<Integer,String> map=new HashMap<>();
      String[] ary=po.getSiteId().split(","); //_1020,_1040,_1070,_1080
      for (String s:ary){ //TYZ_1020
        String[] i=s.split("_");
        map.put(Integer.parseInt(i[1]),po.getUser()+"_"+i[0]);
      }
      po.setSiteMap(map);
    });
    log.info("MDT-JD读取缓存的配置：dfConfigList="+dfConfigList);
    return dfConfigList;
  }

  public MdtJDMgoPo extAttr(MdtJDMgoPo po,DtLotteryConfigPo configPo) {
    po.setBetTime(DateUtil.covertGMTTime(po.getTimeAdd()));
    po.setReportTime(DateUtil.covertGMTTime(po.getTimeDraw()));
    for(Map.Entry<Integer,String> site:configPo.getSiteMap().entrySet()){
      if(po.getUser().startsWith(site.getValue())){
        po.setSiteid(site.getKey());
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

  public Long getMaxId(){
    TypedAggregation<MdtJDMgoPo> agg = newAggregation(MdtJDMgoPo.class,
            group().max("$id").as("id")
    );
    AggregationResults<MdtJDMgoPo> results = mongoTemplate.aggregate(agg,MdtJDMgoPo.class);
    MdtJDMgoPo po = results.getUniqueMappedResult();
    return po.getId();
  }

}
