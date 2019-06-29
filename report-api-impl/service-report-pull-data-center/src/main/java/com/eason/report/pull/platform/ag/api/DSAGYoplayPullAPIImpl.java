package com.eason.report.pull.platform.ag.api;

import com.alibaba.fastjson.JSONArray;
import com.eason.report.pull.core.annotation.LoadConfig;
import com.eason.report.pull.core.annotation.MQProducer;
import com.eason.report.pull.core.annotation.TypeMgr;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.platform.ag.dao.DSAGConfigDao;
import com.eason.report.pull.platform.ag.po.DsAGGameConfigPo;
import com.eason.report.pull.platform.ag.exception.AGException;
import com.eason.report.pull.platform.ag.mgr.DSAGAginMgr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author EASON LI
 */
@MQProducer(name = "DSAGYoPlayPullAPIImpl",code = "DS-AG-YoPlay",url = "/dsagyoplay/getPullBet")
@Slf4j
public class DSAGYoplayPullAPIImpl extends BaseAPI {

    @Autowired
    private DSAGConfigDao configDao;

    @LoadConfig(name = "DS-BBIN配置信息")
    public List<DsAGGameConfigPo> loadConfig(){
        List<DsAGGameConfigPo> configMgoList=configDao.findConfig();
        configMgoList.forEach(po -> {
            Map<Integer,String> map=new HashMap<>();
            String[] ary=po.getSiteId().split(","); //TYZ_1020,MHD_1040
            for (String s:ary){ //TYZ_1020
                String[] i=s.split("_");
                map.put(Integer.parseInt(i[1]),i[0]+"");
            }
            po.setSiteMap(map);
        });

        log.info("DS-BBIN读取的配置：configList="+configMgoList);
        return configMgoList;
    }

    @TypeMgr(name = "拉取操作",targetMgr = DSAGAginMgr.class)
    public int getPullBet(DsAGGameConfigPo configPo, DSAGAginMgr iPullMgr) throws AGException {
        try{
            JSONArray jsonArray= iPullMgr.loadDataToEndTime(configPo);
            int arraySize = jsonArray.size();
//            log.info("DS-BBIN网站={},拉取到注单,数量={}",configPo.getAgentId(), arraySize);
//            for (int i = 0; i < arraySize; i++) {
//                DSBBINMgoPo po = jsonArray.getObject(i, DSBBINMgoPo.class);
//                iPullMgr.saveAndUpdate(po, configPo);
//            }
            return arraySize;
        }catch(Exception e){
            throw new AGException(e);
        }
    }

}
