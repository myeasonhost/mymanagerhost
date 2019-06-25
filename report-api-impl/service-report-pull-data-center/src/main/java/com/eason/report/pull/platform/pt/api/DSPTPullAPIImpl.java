package com.eason.report.pull.platform.pt.api;

import com.alibaba.fastjson.JSONArray;
import com.eason.report.pull.core.annotation.LoadConfig;
import com.eason.report.pull.core.annotation.MQProducer;
import com.eason.report.pull.core.annotation.TypeMgr;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.platform.ky.exception.KyException;
import com.eason.report.pull.platform.pt.dao.DSPTConfigDao;
import com.eason.report.pull.platform.pt.dao.po.DsPtGameConfigPo;
import com.eason.report.pull.platform.pt.mgo.DSPTMgoPo;
import com.eason.report.pull.platform.pt.mgr.DSPTMgr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author EASON LI
 */
@MQProducer(name = "DSPTPullAPIImpl",code = "DS-PT",url = "/dspt/getPullBet")
@Slf4j
public class DSPTPullAPIImpl extends BaseAPI {

    @Autowired
    private DSPTConfigDao ptConfigDao;

    @LoadConfig(name = "DS-PT配置信息")
    public List<DsPtGameConfigPo> loadConfig(){
        List<DsPtGameConfigPo> configMgoList=ptConfigDao.findConfig();
        configMgoList.forEach(po -> {
            Map<Integer,String> map=new HashMap<>();
            String[] ary=po.getSiteId().split(","); //TYZ_1020,MHD_1040
            for (String s:ary){ //TYZ_1020
                String[] i=s.split("_");
                map.put(Integer.parseInt(i[1]),i[0]+"_");
            }
            po.setSiteMap(map);
        });

        log.info("DS-PT读取的配置：configList="+configMgoList);
        return configMgoList;
    }

    @TypeMgr(name = "拉取操作",targetMgr = DSPTMgr.class)
    public int getPullBet(DsPtGameConfigPo configPo, DSPTMgr iPullMgr) throws KyException{
        try{
            Timestamp startId=iPullMgr.getNextId(configPo);
            Integer length=configPo.getLength();
            log.info("DS-PT从startId="+startId+"开始准备拉取length="+length+",拉取配置configPo={}", configPo);
            JSONArray jsonArray= iPullMgr.loadDataToEndTime(startId,length,configPo);
            int arraySize = jsonArray.size();
            log.info("DS-PT网站={},拉取到注单,数量={}",configPo.getAgentId(), arraySize);
            for (int i = 0; i < arraySize; i++) {
                DSPTMgoPo po = jsonArray.getObject(i, DSPTMgoPo.class);
                iPullMgr.saveAndUpdate(po, configPo);
            }
            return arraySize;
        }catch(Exception e){
            throw new KyException(e);
        }
    }

}
