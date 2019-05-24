package com.eason.report.pull.ds.manager;


import com.eason.report.pull.ds.config.GFAppInfoConfig;
import com.eason.report.pull.ds.config.JDAppInfoConfig;
import com.eason.report.pull.ds.exception.DsException;
import com.eason.report.pull.ds.mysqlDao.DtGFDao;
import com.eason.report.pull.ds.po.DtGuangfangLotteryPo;
import com.eason.report.pull.ds.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DtGFMgr {
  @Autowired
  private StringRedisTemplate stringRedisTemplate10;
  @Autowired
  private GFAppInfoConfig gfAppInfoConfig;
  @Autowired
  private DtGFDao dtGFDao;
  @Resource
  private EntityManager entityManager;

  public GFAppInfoConfig loadConfig() throws Exception{
    try {
      Object pullUrl = stringRedisTemplate10.boundHashOps("ds-gf").get("pullUrl");
      Object user = stringRedisTemplate10.boundHashOps("ds-gf").get("user");
      Object level = stringRedisTemplate10.boundHashOps("ds-gf").get("level");
      Object length = stringRedisTemplate10.boundHashOps("ds-gf").get("length");
      Object siteId = stringRedisTemplate10.boundHashOps("ds-gf").get("siteId");

      if (pullUrl == null || StringUtils.isEmpty(pullUrl.toString())) {
        throw new DsException("DS-GF读取缓存的配置，pullUrl为空，请正确配置");
      }
      if (user == null || StringUtils.isEmpty(user.toString())) {
        throw new DsException("DS-GF读取缓存的配置，user为空，请正确配置");
      }
      if (level == null || StringUtils.isEmpty(level.toString())) {
        throw new DsException("DS-GF读取缓存的配置，level为空，请正确配置");
      }
      if (length == null || StringUtils.isEmpty(length.toString())) {
        throw new DsException("DS-GF读取缓存的配置，length为空，请正确配置");
      }
      if (siteId == null || StringUtils.isEmpty(siteId.toString())) {
        throw new DsException("DS-GF读取缓存的配置，siteId为空，请正确配置");
      }
      Map<String,String> map=new HashMap<>();
      String[] ary=siteId.toString().split(","); //TYZ_1020,MHD_1040,MAA_1070,MAB_1080
      for (String s:ary){ //TYZ_1020
        String[] i=s.split("_");
        map.put(user+"_"+i[0],i[1]);
      }
      gfAppInfoConfig.setPullUrl(pullUrl.toString());
      gfAppInfoConfig.setUser(user.toString());
      gfAppInfoConfig.setLevel(Integer.parseInt(level.toString()));
      gfAppInfoConfig.setLength(Integer.parseInt(length.toString()));
      gfAppInfoConfig.setSiteId(map);
      log.info("DS-GF读取缓存的配置：gfAppInfoConfig="+gfAppInfoConfig);
      return gfAppInfoConfig;
    }catch (Exception e){
      throw new DsException(e.getMessage());
    }
  }

  public DtGuangfangLotteryPo extAttr(DtGuangfangLotteryPo guanfangEntity,GFAppInfoConfig GFAppInfoConfig) {
    guanfangEntity.setReportTime(DateUtil.covertTime(guanfangEntity.getDrawTime()));
    guanfangEntity.setBetTime(DateUtil.covertTime(guanfangEntity.getAddTime()));
    for(Map.Entry<String,String> site:GFAppInfoConfig.getSiteId().entrySet()){
      if(guanfangEntity.getUserName().startsWith(site.getKey())){
        guanfangEntity.setSiteid(Integer.parseInt(site.getValue()));
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
  public void saveAndUpdate(DtGuangfangLotteryPo guanfangEntityPo,GFAppInfoConfig GFAppInfoConfig){
    DtGuangfangLotteryPo po=dtGFDao.findByNid(guanfangEntityPo.getNid());
    if (po!=null){
      guanfangEntityPo=this.extAttr(guanfangEntityPo,GFAppInfoConfig);
      Long tid=po.getTid();
      BeanUtils.copyProperties(guanfangEntityPo,po);
      po.setTid(tid);
      entityManager.merge(po);
    }else{
      guanfangEntityPo=this.extAttr(guanfangEntityPo,GFAppInfoConfig);
      this.dtGFDao.save(guanfangEntityPo);
    }

  }


}
