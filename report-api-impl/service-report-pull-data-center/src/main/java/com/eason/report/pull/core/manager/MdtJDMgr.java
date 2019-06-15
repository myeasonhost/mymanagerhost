package com.eason.report.pull.core.manager;


import com.eason.report.pull.core.mongo.mgo.MdtJDMgo;
import com.eason.report.pull.core.mongo.po.DtJDMgoPo;
import com.eason.report.pull.core.mongo.po.MdtJDMgoPo;
import com.eason.report.pull.core.mysqlDao.config.MdtLotteryConfigPo;
import com.eason.report.pull.core.mysqlDao.dao.MdtJDDao;
import com.eason.report.pull.core.mysqlDao.dao.MdtLotteryConfigDao;
import com.eason.report.pull.core.mysqlDao.po.DsGameTypePo;
import com.eason.report.pull.core.mysqlDao.po.MdtJingdianLotteryPo;
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
public class MdtJDMgr {

  @Autowired
  private MdtLotteryConfigDao mdtLotteryConfigDao;
  @Autowired
  private MdtJDDao mdtJDDao;
  @Autowired
  private MdtJDMgo mdtJDMgo;
  @Resource
  private EntityManager entityManager;

  public List<MdtLotteryConfigPo> loadConfig(){
    List<MdtLotteryConfigPo> dfConfigList=mdtLotteryConfigDao.findDsDfConfig();
    dfConfigList.forEach(po -> {
      Map<Integer,String> map=new HashMap<>();
      String[] ary=po.getSiteId().split(","); //_1020,_1040,_1070,_1080
      for (String s:ary){ //TYZ_1020
        String[] i=s.split("_");
        map.put(Integer.parseInt(i[1]),po.getUser()+""+i[0]);
      }
      po.setSiteMap(map);
    });
    log.info("MDT-JD读取缓存的配置：dfConfigList="+dfConfigList);
    return dfConfigList;
  }

  public MdtJingdianLotteryPo extAttr(MdtJingdianLotteryPo jingdianEntity,MdtLotteryConfigPo configPo) {
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
  public void saveAndUpdate(MdtJingdianLotteryPo jingdianEntityPo, MdtLotteryConfigPo configPo, List<DsGameTypeVo> dsGameTypePoList){
    MdtJingdianLotteryPo po=mdtJDDao.findByNid(jingdianEntityPo.getNid());

    MdtJDMgoPo mdtJDMgoPo=new MdtJDMgoPo();
    BeanUtils.copyProperties(jingdianEntityPo,mdtJDMgoPo);
//    mdtJDMgoPo.setLiveName(configPo.getLiveName());
//    dsGameTypePoList.forEach(dsGameTypePo -> {
//      if(mdtJDMgoPo.getLid().equals(dsGameTypePo.getOutGameCode())){
//        mdtJDMgoPo.setLiveId(dsGameTypePo.getFkLiveId().intValue());
//        mdtJDMgoPo.setParentType(dsGameTypePo.getParentId());
//        mdtJDMgoPo.setParentName(dsGameTypePo.getParentName());
//        mdtJDMgoPo.setType(mdtJDMgoPo.getLid().intValue());
//        mdtJDMgoPo.setGameName(dsGameTypePo.getGameName());
//      }else{
//        DsGameTypePo dsGameTypePo1=new DsGameTypePo();
//        String newGameName=String.format(configPo.getInfo(),mdtJDMgoPo.getLid());
//        dsGameTypePo1.setGameName(newGameName);
//        dsGameTypePo1.setOutGameCode(mdtJDMgoPo.getLid().toString());
//        dsGameTypePo1.setParentId(Integer.parseInt(configPo.getGameKind().split(",")[0]));
//        dsGameTypePo1.setFkLiveId(configPo.getLiveId().byteValue());
//        entityManager.persist(dsGameTypePo1);
//      }
//    });
    this.mdtJDMgo.save(mdtJDMgoPo);

    if(po!=null){
      jingdianEntityPo=this.extAttr(jingdianEntityPo,configPo);
      Long tid=po.getTid();
      BeanUtils.copyProperties(jingdianEntityPo,po);
      po.setTid(tid);
      entityManager.merge(po);
    }else{
      jingdianEntityPo=this.extAttr(jingdianEntityPo,configPo);
      this.mdtJDDao.save(jingdianEntityPo);
    }

  }

}
