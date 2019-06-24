package com.eason.report.pull.platform.lmg.api;

import com.alibaba.fastjson.JSONArray;
import com.eason.report.pull.core.annotation.LoadConfig;
import com.eason.report.pull.core.annotation.MQProducer;
import com.eason.report.pull.core.annotation.TypeMgr;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.platform.ky.exception.KyException;
import com.eason.report.pull.platform.lmg.dao.LMGConfigDao;
import com.eason.report.pull.platform.lmg.dao.po.DsLmgGameConfigPo;
import com.eason.report.pull.platform.lmg.mgo.LMGMgoPo;
import com.eason.report.pull.platform.lmg.mgr.DSLMGMgr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author EASON LI
 */
@MQProducer(name = "DSLMGPullAPIImpl",code = "DS-LMG",url = "/dslmg/getPullBet")
@Slf4j
public class DSLMGPullAPIImpl extends BaseAPI {

    @Autowired
    private LMGConfigDao lmgConfigDao;

    @LoadConfig(name = "DS-LMG配置信息")
    public List<DsLmgGameConfigPo> loadConfig(){
        List<DsLmgGameConfigPo> configMgoList=lmgConfigDao.findConfig();
        configMgoList.forEach(po -> {
            Map<Integer,String> map=new HashMap<>();
            String[] ary=po.getSiteId().split(","); //_1020,_1040,_1070,_1080
            for (String s:ary){ //_1020
                String[] i=s.split("_");
                map.put(Integer.parseInt(i[1]),""+i[0]);
            }
            po.setSiteMap(map);
        });

        log.info("DS-LMG读取的配置：configList="+configMgoList);
        return configMgoList;
    }

    @TypeMgr(name = "拉取操作",targetMgr = DSLMGMgr.class)
    public int getPullBet(DsLmgGameConfigPo configPo, DSLMGMgr iPullMgr) throws KyException{
        try{
            Long startId=iPullMgr.getNextId(configPo);
            Integer length=configPo.getLength();
            log.info("DS-LMG从startId="+startId+"开始准备拉取length="+length+",拉取配置configPo={}", configPo);
            JSONArray jsonArray= iPullMgr.loadDataByStartId(startId,configPo);
            int arraySize = jsonArray.size();
            log.info("DS-LMG网站={},拉取到注单,数量={}",configPo.getAgentId(), arraySize);
            for (int i = 0; i < arraySize; i++) {
                LMGMgoPo po = jsonArray.getObject(i, LMGMgoPo.class);
                iPullMgr.saveAndUpdate(po, configPo);
            }
            return arraySize;
        }catch(Exception e){
            throw new KyException(e);
        }
    }

}
