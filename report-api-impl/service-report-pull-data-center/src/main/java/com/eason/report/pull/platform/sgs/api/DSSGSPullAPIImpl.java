package com.eason.report.pull.platform.sgs.api;

import com.alibaba.fastjson.JSONArray;
import com.eason.report.pull.core.annotation.LoadConfig;
import com.eason.report.pull.core.annotation.MQProducer;
import com.eason.report.pull.core.annotation.TypeMgr;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.utils.DateUtil;
import com.eason.report.pull.platform.sgs.dao.DSSGSConfigDao;
import com.eason.report.pull.platform.sgs.dao.po.DsSgsGameConfigPo;
import com.eason.report.pull.platform.sgs.exception.SGSException;
import com.eason.report.pull.platform.sgs.mgo.DSSGSMgoPo;
import com.eason.report.pull.platform.sgs.mgr.DSSGSMgr;
import com.eason.report.pull.platform.sgs.vo.SgsCsvVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author EASON LI
 */
@MQProducer(name = "DSSGSPullAPIImpl",code = "DS-SGS",url = "/dssgs/getPullBet")
@Slf4j
public class DSSGSPullAPIImpl extends BaseAPI {

    @Autowired
    private DSSGSConfigDao ptConfigDao;

    @LoadConfig(name = "DS-SGS配置信息")
    public List<DsSgsGameConfigPo> loadConfig(){
        List<DsSgsGameConfigPo> configMgoList=ptConfigDao.findConfig();
        configMgoList.forEach(po -> {
            Map<Integer,String> map=new HashMap<>();
            String[] ary=po.getSiteId().split(","); //TYZ_1020,MHD_1040
            for (String s:ary){ //TYZ_1020
                String[] i=s.split("_");
                map.put(Integer.parseInt(i[1]),i[0]+"");
            }
            po.setSiteMap(map);
        });

        log.info("DS-SGS读取的配置：configList="+configMgoList);
        return configMgoList;
    }

    @TypeMgr(name = "拉取操作",targetMgr = DSSGSMgr.class)
    public int getPullBet(DsSgsGameConfigPo configPo, DSSGSMgr iPullMgr) throws SGSException {
        try{
            Timestamp startId=iPullMgr.getNextId(configPo);
            Integer length=configPo.getLength();
            log.info("DS-SGS从startId="+startId+"开始准备拉取length="+length+",拉取配置configPo={}", configPo);
            JSONArray jsonArray= iPullMgr.loadDataToEndTime(startId,length,configPo);
            int arraySize = jsonArray.size();
            log.info("DS-SGS网站={},拉取到注单,数量={}",configPo.getAgentId(), arraySize);
            for (int i = 0; i < arraySize; i++) {
                SgsCsvVo vo = jsonArray.getObject(i, SgsCsvVo.class);
                DSSGSMgoPo po=new DSSGSMgoPo();
                BeanUtils.copyProperties(vo,po);
                po.setBetOn(DateUtil.covertUTCTime(vo.getBetOn()));
                po.setBetClosedOn(DateUtil.covertUTCTime(vo.getBetClosedOn()));
                po.setBetUpdatedOn(DateUtil.covertUTCTime(vo.getBetUpdatedOn()));
                po.setTimestamp0(DateUtil.covertUTCTime(vo.getTimestamp()));
                iPullMgr.saveAndUpdate(po, configPo);
            }
            return arraySize;
        }catch(Exception e){
            throw new SGSException(e);
        }
    }

}
