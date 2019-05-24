package com.eason.report.pull.ds.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eason.report.pull.ds.base.BaseAPI;
import com.eason.report.pull.ds.config.GFAppInfoConfig;
import com.eason.report.pull.ds.exception.DsException;
import com.eason.report.pull.ds.listener.MQServiceListener;
import com.eason.report.pull.ds.manager.DtGFMgr;
import com.eason.report.pull.ds.model.DsLotteryModel;
import com.eason.report.pull.ds.model.MsgModel;
import com.eason.report.pull.ds.mysqlDao.DtGFDao;
import com.eason.report.pull.ds.po.DtGuangfangLotteryPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.persistence.EntityManager;


/**
 * @author EASON LI
 */
@Service
@Slf4j
public class GFPullAPIImpl extends BaseAPI {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GFAppInfoConfig GFAppInfoConfig;

    @Autowired
    private DtGFMgr dtDFMgr;

    @Autowired
    private DtGFDao dtGFDao;

    @Autowired
    private MQServiceListener mqServiceListener;

    /**
     * 官方彩拉取
     */
    public void getPullBet() throws DsException {
        Long maxId=dtGFDao.getMaxId();
        Integer length=GFAppInfoConfig.getLength();
        if (maxId ==0 || length==0){
           log.error("DS-GF官方彩从startId="+maxId+"开始准备拉取,拉取配置可能有误，长度length="+length);
           return;
        }
        getPullBet(maxId+1,length); //下一个开始
    }
    /**
     * 官方彩拉取
     */
    public void getPullBet(Long maxId,Integer length) throws DsException {
        try {
            boolean flag=false;
            Long startId = maxId;
            log.info("DS-GF官方彩从startId="+startId+"开始准备拉取length="+length+",拉取配置GFAppInfoConfig={}", GFAppInfoConfig);

            JSONObject request = new JSONObject();
            request.put("num", length);
            request.put("username", GFAppInfoConfig.getUser());
            request.put("beginId", startId);
            request.put("accType", GFAppInfoConfig.getLevel());
            HttpEntity<String> entity = new HttpEntity<>(request.toString());
            ResponseEntity<JSONObject> exchange=restTemplate.exchange(GFAppInfoConfig.getPullUrl(), HttpMethod.POST,entity,JSONObject.class);
            if (exchange==null || exchange.getBody().isEmpty()){
                log.error("DS-GF官方彩: 重要报警，用户={},参数={},断网维护或者未加白名单", GFAppInfoConfig.getUser(),request.toString());
                return;
            }
            JSONObject object=exchange.getBody();

            if (object.getString("status").equals("10000") && object.getJSONArray("message").size() != 0) {// 获取成功
                JSONArray array = object.getJSONArray("message");
                int arraySize = array.size();
                log.info("DS-GF官方彩网站={},拉取到注单 ,数量={}", GFAppInfoConfig.getUser(), arraySize);
                for (int i = 0; i < array.size(); i++) {
                    DtGuangfangLotteryPo guanfangEntityPo = array.getObject(i, DtGuangfangLotteryPo.class);
                    dtDFMgr.saveAndUpdate(guanfangEntityPo,GFAppInfoConfig);
                    flag=true;
                }
                if (flag){
                    Long endId=dtGFDao.getMaxId();
                    DsLotteryModel dsLotteryModel=DsLotteryModel.builder()
                            .startId(startId)
                            .endId(endId)
                            .siteId(GFAppInfoConfig.getSiteId()).build();
                    mqServiceListener.sendReceiverMsg(
                            MsgModel.builder()
                                    .code("DS-GF-0000")
                                    .message("当前拉取size="+arraySize+"条已完成，开始分发")
                                    .object(dsLotteryModel).build());
                    log.info("DS-GF-0000当前startId={}——endId={}拉取size="+arraySize+"条已完成，开始分发",dsLotteryModel.getStartId(),dsLotteryModel.getEndId());
                }
            } else if (object.getString("status").equals("10000") && object.getJSONArray("message").size() == 0) { // 没有拉取到注单
                log.info("DS-GF官方彩网站={} 拉取成功,但注单数量为0,休眠1分钟,拉取结果={}", GFAppInfoConfig.getUser(), object.toJSONString());
                return;
            } else if (!object.getString("status").equals("10000")) {
                log.error("DS-GF官方彩网站={} 拉取数据失败,请检查配置,休眠1分钟,错误消息={}", GFAppInfoConfig.getUser(), object.toJSONString());
                return;
            }
        }catch (Exception e){
            if (e instanceof ResourceAccessException){
                log.error("DS-GF官方彩未添加白名单或者可能在维护，错误信息={}",e.getMessage());
                return;
            }
            e.printStackTrace();
            throw new DsException(e.getMessage());
        }

    }

}
