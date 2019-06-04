package com.eason.report.pull.core.manager;


import com.eason.report.pull.core.config.MdtJDAppInfoConfig;
import com.eason.report.pull.core.exception.DsException;
import com.eason.report.pull.core.mysqlDao.MdtJDDao;
import com.eason.report.pull.core.mysqlDao.po.MdtJingdianLotteryPo;
import com.eason.report.pull.core.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class MdtJDMgr {

  @Autowired
  private StringRedisTemplate stringRedisTemplate10;
  @Autowired
  private MdtJDAppInfoConfig jdAppInfoConfig;
  @Autowired
  private MdtJDDao mdtJDDao;
  @Resource
  private EntityManager entityManager;

  public MdtJDAppInfoConfig loadConfig() throws Exception{
    try {
      Object pullUrl = stringRedisTemplate10.boundHashOps("mdt-jd").get("pullUrl");
      Object user = stringRedisTemplate10.boundHashOps("mdt-jd").get("user");
      Object level = stringRedisTemplate10.boundHashOps("mdt-jd").get("level");
      Object length = stringRedisTemplate10.boundHashOps("mdt-jd").get("length");
      Object siteId = stringRedisTemplate10.boundHashOps("mdt-jd").get("siteId");


      if (pullUrl == null|| StringUtils.isEmpty(pullUrl.toString())) {
        throw new DsException("MDT-JD读取缓存的配置，pullUrl为空，请正确配置");
      }
      if (user == null|| StringUtils.isEmpty(user.toString())) {
        throw new DsException("MDT-JD读取缓存的配置，user为空，请正确配置");
      }
      if (level == null|| StringUtils.isEmpty(level.toString())) {
        throw new DsException("MDT-JD读取缓存的配置，level为空，请正确配置");
      }
      if (length == null|| StringUtils.isEmpty(length.toString())) {
        throw new DsException("MDT-JD读取缓存的配置，length为空，请正确配置");
      }
      if (siteId == null || StringUtils.isEmpty(siteId.toString())) {
        throw new DsException("MDT-JD读取缓存的配置，siteId为空，请正确配置");
      }
      Map<Integer,String> map=new HashMap<>();
      String[] ary=siteId.toString().split(","); //TYZ_1020,MHD_1040,MAA_1070,MAB_1080
      for (String s:ary){ //TYZ_1020
        String[] i=s.split("_");
        map.put(Integer.parseInt(i[1]),user+"_"+i[0]);
      }
      jdAppInfoConfig.setPullUrl(pullUrl.toString());
      jdAppInfoConfig.setUser(user.toString());
      jdAppInfoConfig.setLevel(Integer.parseInt(level.toString()));
      jdAppInfoConfig.setLength(Integer.parseInt(length.toString()));
      jdAppInfoConfig.setSiteId(map);
      log.info("MDT-JD读取缓存的配置：jdAppInfoConfig="+jdAppInfoConfig);
      return jdAppInfoConfig;
    }catch (Exception e){
      throw new DsException(e.getMessage());
    }
  }

  public MdtJingdianLotteryPo extAttr(MdtJingdianLotteryPo jingdianEntity,MdtJDAppInfoConfig jdAppInfoConfig) {
    jingdianEntity.setBetTime(DateUtil.covertTime(jingdianEntity.getTimeAdd()));
    jingdianEntity.setReportTime(DateUtil.covertTime(jingdianEntity.getTimeDraw()));
    for(Map.Entry<Integer,String> site:jdAppInfoConfig.getSiteId().entrySet()){
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
  public void saveAndUpdate(MdtJingdianLotteryPo jingdianEntityPo, MdtJDAppInfoConfig jdAppInfoConfig){
    MdtJingdianLotteryPo po=mdtJDDao.findByNid(jingdianEntityPo.getNid());
    if (po!=null){
      jingdianEntityPo=this.extAttr(jingdianEntityPo,jdAppInfoConfig);
      Long tid=po.getTid();
      BeanUtils.copyProperties(jingdianEntityPo,po);
      po.setTid(tid);
      entityManager.merge(po);
    }else{
      jingdianEntityPo=this.extAttr(jingdianEntityPo,jdAppInfoConfig);
      this.mdtJDDao.save(jingdianEntityPo);
    }

  }

}
