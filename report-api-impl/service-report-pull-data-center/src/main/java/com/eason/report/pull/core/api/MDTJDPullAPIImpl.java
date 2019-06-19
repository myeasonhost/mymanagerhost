package com.eason.report.pull.core.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eason.report.pull.core.annotation.LoadConfig;
import com.eason.report.pull.core.annotation.MQProducer;
import com.eason.report.pull.core.annotation.TypeMgr;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.base.BaseConfig;
import com.eason.report.pull.core.exception.DsException;
import com.eason.report.pull.core.manager.MdtJDMgr;
import com.eason.report.pull.core.mongo.po.MdtJDMgoPo;
import com.eason.report.pull.core.mysqlDao.config.DtLotteryConfigPo;
import com.eason.report.pull.core.mysqlDao.dao.DtLotteryConfigDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author EASON LI
 */
@MQProducer
@Slf4j
public class MDTJDPullAPIImpl extends BaseAPI {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DtLotteryConfigDao dtLotteryConfigDao;

    @LoadConfig(name = "MDTJD配置信息")
    public List<DtLotteryConfigPo> loadConfig(){
        List<DtLotteryConfigPo> dfConfigList=dtLotteryConfigDao.findMdtJdConfig();
        dfConfigList.forEach(po -> {
            Map<Integer,String> map=new HashMap<>();
            String[] ary=po.getSiteId().split(","); //_1020,_1040,_1070,_1080
            for (String s:ary){ //TYZ_1020
                String[] i=s.split("_");
                map.put(Integer.parseInt(i[1]),po.getUser()+"_"+i[0]);
            }
            po.setSiteMap(map);
        });
        log.info("MDT-JD读取缓存的配置：dfConfigList="+dfConfigList);
        return dfConfigList;
    }

    @TypeMgr(name = "拉取操作",targetMgr = MdtJDMgr.class)
    public int getPullBet(BaseConfig config, IPullMgr iPullMgr) throws DsException {
        try{
            Long startId=(Long)iPullMgr.getNextId(config);
            Integer length=config.getLength();
            DtLotteryConfigPo configPo=(DtLotteryConfigPo) config;
            log.info("MDT-JD经典彩从startId="+startId+"开始准备拉取length="+length+",拉取配置configPo={}", configPo);

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            requestHeaders.add("X-Requested-With", "X-Api-Client");
            requestHeaders.add("X-Api-Call", "X-Api-Client");
            requestHeaders.add("user-agent", "PostmanRuntime/7.13.0");

            JSONObject request = new JSONObject();
            request.put("len",length);
            request.put("user", configPo.getUser());
            request.put("startId", startId);
            request.put("level", configPo.getLevel());

            HttpEntity<String> entity = new HttpEntity<>(request.toString(),requestHeaders);
            ResponseEntity<JSONObject> exchange=restTemplate.exchange(configPo.getRecordUrl(), HttpMethod.POST,entity,JSONObject.class);

            JSONObject object=exchange.getBody();

            if (object.getString("Message").equals("success") && object.getJSONArray("Result").size() != 0) {// 获取成功
                JSONArray array = object.getJSONArray("message");
                int arraySize = array.size();
                log.info("MDT-JD经典彩网站={},拉取到注单,数量={}", configPo.getUser(), arraySize);
                for (int i = 0; i < array.size(); i++) {
                    MdtJDMgoPo po = array.getObject(i, MdtJDMgoPo.class);
                    iPullMgr.saveAndUpdate(po,configPo);
                }
                return arraySize;
            } else if (object.getString("Message").equals("success") && object.getJSONArray("Result").size() == 0) { // 没有拉取到注单
                log.info("MDT-JD经典彩网站={} 拉取成功,但注单数量为0,拉取结果={}", configPo.getUser(), object.toJSONString());
            } else if (!object.getString("Message").equals("success")) {
                log.error("MDT-JD经典彩网站={} 拉取数据失败,请检查配置,错误消息={}", configPo.getUser(), object.toJSONString());
            }
            return 0;
        }catch(Exception e){
            throw new DsException(e);
        }
    }
}
