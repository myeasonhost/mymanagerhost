package com.eason.report.pull.core.api;

import com.alibaba.fastjson.JSONArray;
import com.eason.report.pull.core.annotation.LoadConfig;
import com.eason.report.pull.core.annotation.MQProducer;
import com.eason.report.pull.core.annotation.TypeMgr;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.base.BaseConfig;
import com.eason.report.pull.core.exception.DsException;
import com.eason.report.pull.core.manager.MGMgr;
import com.eason.report.pull.core.mongo.po.MGMgoPo;
import com.eason.report.pull.core.mysqlDao.config.MgGameConfigPo;
import com.eason.report.pull.core.mysqlDao.dao.MGConfigDao;
import com.eason.report.pull.core.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author EASON LI
 */
@MQProducer
@Slf4j
public class MGPullAPIImpl  extends BaseAPI {

    @Autowired
    private MGConfigDao mgConfigDao;

    @LoadConfig(name = "MG配置信息")
    public List<MgGameConfigPo> loadConfig(){
        List<MgGameConfigPo> configMgoList=mgConfigDao.findConfig();
        configMgoList.forEach(po -> {
            Map<Integer,String> map=new HashMap<>();
            String[] ary=po.getSiteId().split(","); //TYZ_1020,MHD_1040,MAA_1070,MAB_1080
            for (String s:ary){ //TYZ_1020
                String[] i=s.split("_");
                map.put(Integer.parseInt(i[1]),po.getPrex()+""+i[0]);
            }
            po.setSiteMap(map);
        });

        log.info("MG大富豪读取的配置：configList="+configMgoList);
        return configMgoList;
    }

    @TypeMgr(name = "拉取操作",targetMgr = MGMgr.class)
    public int getPullBet(BaseConfig config, IPullMgr iPullMgr) throws DsException {
        try{
            Date startDate=(Date) iPullMgr.getNextId(config);
            Integer length=config.getLength();
            MgGameConfigPo configPo=(MgGameConfigPo) config;
            log.info("MG大富豪从startId="+DateUtil.covertStr(startDate)+"开始准备拉取length="+length+",拉取配置configPo={}", configPo);
            MGMgr mgMgr=(MGMgr) iPullMgr;
            String token= mgMgr.loginWebSite(configPo).get("token").toString();
            JSONArray jsonArray= mgMgr.loadDataToEndTime(token,startDate,length,configPo);
            int arraySize = jsonArray.size();
            log.info("MG大富豪网站={},拉取到注单,数量={}",configPo.getUsername(), arraySize);
            for (int i = 0; i < arraySize; i++) {
                MGMgoPo po = jsonArray.getObject(i, MGMgoPo.class);
                iPullMgr.saveAndUpdate(po, configPo);
            }
            return arraySize;
        }catch(Exception e){
            throw new DsException(e);
        }
    }

}
