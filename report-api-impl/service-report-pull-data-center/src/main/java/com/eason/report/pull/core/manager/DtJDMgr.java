package com.eason.report.pull.core.manager;


import com.eason.report.pull.core.mongo.mgo.DtJDMgo;
import com.eason.report.pull.core.mongo.po.DtJDMgoPo;
import com.eason.report.pull.core.mysqlDao.config.DtLotteryConfigPo;
import com.eason.report.pull.core.mysqlDao.dao.DtJDDao;
import com.eason.report.pull.core.mysqlDao.dao.DtLotteryConfigDao;
import com.eason.report.pull.core.mysqlDao.po.DsGameTypePo;
import com.eason.report.pull.core.mysqlDao.po.DtJingdianLotteryPo;
import com.eason.report.pull.core.mysqlDao.vo.DsGameTypeVo;
import com.eason.report.pull.core.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DtJDMgr {
  @Autowired
  private DtJDDao dtJDDao;
  @Autowired
  private DtJDMgo dtJDMgo;
  @Autowired
  private DtLotteryConfigDao dtLotteryConfigDao;
  @Resource
  private EntityManager entityManager;

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

  public DtJingdianLotteryPo extAttr(DtJingdianLotteryPo jingdianEntity,DtLotteryConfigPo configPo) {
    jingdianEntity.setBetTime(DateUtil.covertTime(jingdianEntity.getTimeAdd()));
    jingdianEntity.setReportTime(DateUtil.covertTime(jingdianEntity.getTimeDraw()));
    for(Map.Entry<Integer,String> site:configPo.getSiteMap().entrySet()){
      if(jingdianEntity.getUser().startsWith(site.getValue())){
        jingdianEntity.setSiteid(site.getKey());
      }
    }

    if (jingdianEntity.isCancel()) { // 取消 前端定义
      jingdianEntity.setWinLoseType(Byte.valueOf("4")); // 取消 数据库定义
      return jingdianEntity;
    }
    switch (jingdianEntity.getStataus()) {
      case 1: // 和 前端定义
        jingdianEntity.setWinLoseType(Byte.valueOf("3")); // 和 数据库定义
        break;
      case 2: // 赢 前端定义
        jingdianEntity.setWinLoseType(Byte.valueOf("2")); // 赢 数据库定义
        break;
      case 3: // 输 前端定义
        jingdianEntity.setWinLoseType(Byte.valueOf("1")); // 输 数据库定义
        break;
    }
    return jingdianEntity;
  }

  @Transactional
  public void saveAndUpdate(DtJingdianLotteryPo jingdianEntityPo, DtLotteryConfigPo configPo, List<DsGameTypeVo> dsGameTypePoList){
    DtJingdianLotteryPo po=dtJDDao.findByNid(jingdianEntityPo.getNid());

    DtJDMgoPo dtJDMgoPo=new DtJDMgoPo();
    BeanUtils.copyProperties(jingdianEntityPo,dtJDMgoPo);
//    dtJDMgoPo.setLiveName(configPo.getLiveName());
//    dsGameTypePoList.forEach(dsGameTypePo -> {
//      if(dtJDMgoPo.getLid().equals(dsGameTypePo.getOutGameCode())){
//        dtJDMgoPo.setLiveId(dsGameTypePo.getFkLiveId().intValue());
//        dtJDMgoPo.setParentType(dsGameTypePo.getParentId());
//        dtJDMgoPo.setParentName(dsGameTypePo.getParentName());
//        dtJDMgoPo.setType(dtJDMgoPo.getLid().intValue());
//        dtJDMgoPo.setGameName(dsGameTypePo.getGameName());
//      }else{
//        DsGameTypePo dsGameTypePo1=new DsGameTypePo();
//        String newGameName=String.format(configPo.getInfo(),dtJDMgoPo.getLid());
//        dsGameTypePo1.setGameName(newGameName);
//        dsGameTypePo1.setOutGameCode(dtJDMgoPo.getLid().toString());
//        dsGameTypePo1.setParentId(Integer.parseInt(configPo.getGameKind().split(",")[0]));
//        dsGameTypePo1.setFkLiveId(configPo.getLiveId().byteValue());
//        entityManager.persist(dsGameTypePo1);
//      }
//    });
    this.dtJDMgo.save(dtJDMgoPo);

    if(po!=null){
      jingdianEntityPo=this.extAttr(jingdianEntityPo,configPo);
      Long tid=po.getTid();
      BeanUtils.copyProperties(jingdianEntityPo,po);
      po.setTid(tid);
      entityManager.merge(po);
    }else{
      jingdianEntityPo=this.extAttr(jingdianEntityPo,configPo);
      this.dtJDDao.save(jingdianEntityPo);
    }

  }


}
