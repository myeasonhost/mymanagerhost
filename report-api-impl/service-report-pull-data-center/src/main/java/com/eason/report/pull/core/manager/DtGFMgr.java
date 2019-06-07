package com.eason.report.pull.core.manager;


import com.eason.report.pull.core.config.GFAppInfoConfig;
import com.eason.report.pull.core.exception.DsException;
import com.eason.report.pull.core.mongo.mgo.DtGFMgo;
import com.eason.report.pull.core.mongo.po.DtGFMgoPo;
import com.eason.report.pull.core.mysqlDao.DtGFDao;
import com.eason.report.pull.core.mysqlDao.DtLotteryConfigDao;
import com.eason.report.pull.core.mysqlDao.config.DtLotteryConfigPo;
import com.eason.report.pull.core.mysqlDao.po.DtGuangfangLotteryPo;
import com.eason.report.pull.core.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DtGFMgr {
  @Autowired
  private GFAppInfoConfig gfAppInfoConfig;
  @Autowired
  private DtGFDao dtGFDao;
  @Autowired
  private DtGFMgo dtGFMgo;
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
    gfAppInfoConfig.setGfListConfig(dfConfigList);
    log.info("DS-GF读取缓存的配置：dfConfigList="+dfConfigList);
    return dfConfigList;
  }

  public DtGuangfangLotteryPo extAttr(DtGuangfangLotteryPo guanfangEntity,DtLotteryConfigPo configPo) {
    guanfangEntity.setReportTime(DateUtil.covertTime(guanfangEntity.getDrawTime()));
    guanfangEntity.setBetTime(DateUtil.covertTime(guanfangEntity.getAddTime()));
    for(Map.Entry<Integer,String> site:configPo.getSiteMap().entrySet()){
      if(guanfangEntity.getUserName().startsWith(site.getValue())){
        guanfangEntity.setSiteid(site.getKey());
      }
    }
    /**
     * 撤单状态(0未撤单 1用户撤单 2追中撤单 3出号撤单 4未开撤单 5结算前检查撤销 9管理员撤单)
     */
    if (guanfangEntity.getIsCancel() != 0) { // 取消 前端定义
      guanfangEntity.setWinLoseType(Byte.valueOf("4")); // 取消 数据库定义
      return guanfangEntity;
    }
    /**
     * 判断订单已结算 isTake==1 isPay==1 isCancel==0
     */
    if (guanfangEntity.getIsCancel() == 0 && guanfangEntity.getIsPay() == 1 && guanfangEntity.getIsCancel() == 0) {
      BigDecimal wins = guanfangEntity.getWins().subtract(guanfangEntity.getAmount());
      if (wins.doubleValue() > 0) {
        guanfangEntity.setWinLoseType(Byte.valueOf("2")); // 赢 数据库定义
      } else if (wins.doubleValue() < 0) {
        guanfangEntity.setWinLoseType(Byte.valueOf("1")); // 输 数据库定义
      } else {
        guanfangEntity.setWinLoseType(Byte.valueOf("3")); // 和 数据库定义
      }
      guanfangEntity.setWins(wins);
    }
    return guanfangEntity;
  }

  @Transactional
  public void saveAndUpdate(DtGuangfangLotteryPo guanfangEntityPo,DtLotteryConfigPo configPo){
    DtGuangfangLotteryPo po=dtGFDao.findByNid(guanfangEntityPo.getNid());
    if (po!=null){
      guanfangEntityPo=this.extAttr(guanfangEntityPo,configPo);
      Long tid=po.getTid();
      BeanUtils.copyProperties(guanfangEntityPo,po);
      po.setTid(tid);
      entityManager.merge(po);
    }else{
      guanfangEntityPo=this.extAttr(guanfangEntityPo,configPo);
      this.dtGFDao.save(guanfangEntityPo);
    }

    DtGFMgoPo dtGFMgoPo=new DtGFMgoPo();
    BeanUtils.copyProperties(guanfangEntityPo,dtGFMgoPo);
    this.dtGFMgo.save(dtGFMgoPo);
  }


}
