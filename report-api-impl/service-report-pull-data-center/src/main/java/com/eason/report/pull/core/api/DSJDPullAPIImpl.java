package com.eason.report.pull.core.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eason.report.pull.core.annotation.LoadConfig;
import com.eason.report.pull.core.annotation.MQProducer;
import com.eason.report.pull.core.annotation.TypeMgr;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.base.BaseConfig;
import com.eason.report.pull.core.exception.DsException;
import com.eason.report.pull.core.manager.DtJDMgr;
import com.eason.report.pull.core.mongo.po.DtJDMgoPo;
import com.eason.report.pull.core.mysqlDao.config.DtLotteryConfigPo;
import com.eason.report.pull.core.mysqlDao.dao.DtLotteryConfigDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author EASON LI
 */
@MQProducer
@Slf4j
public class DSJDPullAPIImpl extends BaseAPI{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DtLotteryConfigDao dtLotteryConfigDao;

    @LoadConfig(name = "DSJD配置信息")
    public List<DtLotteryConfigPo> loadConfig(){
        List<DtLotteryConfigPo> dfConfigList=dtLotteryConfigDao.findDsJdConfig();
        dfConfigList.forEach(po -> {
            Map<Integer,String> map=new HashMap<>();
            String[] ary=po.getSiteId().split(","); //TYZ_1020,MHD_1040,MAA_1070,MAB_1080
            for (String s:ary){ //TYZ_1020
                String[] i=s.split("_");
                map.put(Integer.parseInt(i[1]),po.getUser()+"_"+i[0]);
            }
            po.setSiteMap(map);
        });
        log.info("DS-JD读取缓存的配置：dfConfigList="+dfConfigList);
        return dfConfigList;
    }

    @TypeMgr(name = "拉取操作",targetMgr = DtJDMgr.class)
    public int getPullBet(BaseConfig config, IPullMgr iPullMgr) throws DsException {
        try{
            Long startId=(Long)iPullMgr.getNextId(config);
            Integer length=config.getLength();
            DtLotteryConfigPo configPo=(DtLotteryConfigPo) config;
            log.info("DS-JD经典彩从startId="+startId+"开始准备拉取length="+length+",拉取配置configPo={}", configPo);

            JSONObject request = new JSONObject();
            request.put("num", length);
            request.put("username", configPo.getUser());
            request.put("beginId", startId);
            request.put("accType", configPo.getLevel());
            HttpEntity<String> entity = new HttpEntity<>(request.toString());
            ResponseEntity<JSONObject> exchange=restTemplate.exchange(configPo.getRecordUrl(), HttpMethod.POST,entity,JSONObject.class);
            JSONObject object=exchange.getBody();

            if (object.getString("status").equals("10000") && object.getJSONArray("message").size() != 0) {// 获取成功
                JSONArray array = object.getJSONArray("message");
                int arraySize = array.size();
                log.info("DS-JD经典彩网站={},拉取到注单,数量={}", configPo.getUser(), arraySize);
                for (int i = 0; i < array.size(); i++) {
                    DtJDMgoPo po = array.getObject(i, DtJDMgoPo.class);
                    iPullMgr.saveAndUpdate(po,configPo);
                }
                return arraySize;
            } else if (object.getString("status").equals("10000") && object.getJSONArray("message").size() == 0) { // 没有拉取到注单
                log.info("DS-JD经典彩网站={} 拉取成功,但注单数量为0,拉取结果={}", configPo.getUser(), object.toJSONString());
            } else if (!object.getString("status").equals("10000")) {
                log.error("DS-JD经典彩网站={} 拉取数据失败,请检查配置,错误消息={}", configPo.getUser(), object.toJSONString());
            }
            return 0;
        }catch(Exception e){
            throw new DsException(e);
        }
    }
}
