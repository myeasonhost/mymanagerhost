package com.eason.report.pull.platform.ky.api;

import com.alibaba.fastjson.JSONArray;
import com.eason.report.pull.core.annotation.LoadConfig;
import com.eason.report.pull.core.annotation.MQProducer;
import com.eason.report.pull.core.annotation.TypeMgr;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.platform.ky.dao.KYConfigDao;
import com.eason.report.pull.platform.ky.dao.po.KYGameConfigPo;
import com.eason.report.pull.platform.ky.exception.KyException;
import com.eason.report.pull.platform.ky.mgo.KyMgoPo;
import com.eason.report.pull.platform.ky.mgr.KYMgr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author EASON LI
 */
@MQProducer(name = "KYPullAPIImpl",code = "KY",url = "/ky/getPullBet")
@Slf4j
public class KYPullAPIImpl extends BaseAPI {

    @Autowired
    private KYConfigDao kyConfigDao;

    @LoadConfig(name = "KY配置信息")
    public List<KYGameConfigPo> loadConfig(){
        List<KYGameConfigPo> configMgoList=kyConfigDao.findConfig();
        configMgoList.forEach(po -> {
            Map<Integer,String> map=new HashMap<>();
            String[] ary=po.getSiteId().split(","); //_1020,_1040,_1070,_1080
            for (String s:ary){ //_1020
                String[] i=s.split("_");
                map.put(Integer.parseInt(i[1]),po.getAgentId()+"_"+i[0]);
            }
            po.setSiteMap(map);
        });

        log.info("KY读取的配置：configList="+configMgoList);
        return configMgoList;
    }

    @TypeMgr(name = "拉取操作",targetMgr = KYMgr.class)
    public int getPullBet(KYGameConfigPo configPo, KYMgr iPullMgr) throws KyException{
        try{
            Timestamp startDate=iPullMgr.getNextId(configPo);
            Integer length=configPo.getLength();
            log.info("KY从startId="+startDate+"开始准备拉取length="+length+",拉取配置configPo={}", configPo);
            JSONArray jsonArray= iPullMgr.loadDataToEndTime(startDate,length,configPo);
            int arraySize = jsonArray.size();
            log.info("KY网站={},拉取到注单,数量={}",configPo.getAgentId(), arraySize);
            for (int i = 0; i < arraySize; i++) {
                KyMgoPo po = jsonArray.getObject(i, KyMgoPo.class);
                iPullMgr.saveAndUpdate(po, configPo);
            }
            return arraySize;
        }catch(Exception e){
            throw new KyException(e);
        }
    }

}
