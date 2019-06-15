package com.eason.report.pull.core.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eason.report.pull.core.annotation.MQProducer;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.base.BaseConfig;
import com.eason.report.pull.core.exception.DsException;
import com.eason.report.pull.core.manager.DtGFMgr;
import com.eason.report.pull.core.model.NumModel;
import com.eason.report.pull.core.model.ResponseModel;
import com.eason.report.pull.core.mongo.po.DtGFMgoPo;
import com.eason.report.pull.core.mq.MQServiceProducer;
import com.eason.report.pull.core.mysqlDao.config.DtLotteryConfigPo;
import com.eason.report.pull.core.mysqlDao.dao.DsGameTypeDao;
import com.eason.report.pull.core.mysqlDao.vo.DsGameTypeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * @author EASON LI
 */
@MQProducer
@Slf4j
@RestController
@RequestMapping(value = "/dsgf")
public class DSGFPullAPIImpl extends MQServiceProducer implements PullAPI{
    protected ExecutorService executorService =  Executors.newFixedThreadPool(BaseAPI.GAMENUM);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DtGFMgr dtDFMgr;

    @Autowired
    private DsGameTypeDao dsGameTypeDao;

    @RequestMapping(value = "/getPullBet",method = RequestMethod.POST)
    public List<ResponseModel> getPullBet() throws DsException {
        try{
            Long maxId=dtDFMgr.getMaxId()+1;
            List<DtLotteryConfigPo> lotteryConfigPoList=dtDFMgr.loadConfig();
            CountDownLatch cdl = new CountDownLatch(lotteryConfigPoList.size());
            List<ResponseModel> list=new ArrayList<>();
            lotteryConfigPoList.forEach(configPo -> {
                Integer length=configPo.getLength();
                if (maxId ==0 || length<=0){
                    log.error("DS-GF官方彩从startId="+maxId+"开始准备拉取,拉取配置可能有误，长度length="+length);
                    return;
                }
                Future<ResponseModel> future=executorService.submit(new Callable<ResponseModel>() {
                    @Override
                    public ResponseModel call() throws Exception {
                        try {
                            if (!executorService.isShutdown()) {
                                ResponseModel result=getPullBet(maxId,length, configPo); //下一个开始
                                return result;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.error("DS-GF官方彩网站={} 拉取数据失败,错误消息={},请检查配置={}", configPo.getUser(), e.getMessage(), configPo);
                        } finally {
                            cdl.countDown();
                        }
                        return errorModel;
                    }
                });
                try{
                    list.add(future.get());
                }catch (ExecutionException e) {
                    e.printStackTrace();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            return list;
        }catch(Exception e){
            throw new DsException(e);
        }
    }


    public ResponseModel getPullBet(Long maxId,
                                    Integer length,
                                    BaseConfig config) throws DsException {
        try {
            boolean flag=false;
            Long startId = maxId;
            DtLotteryConfigPo configPo=(DtLotteryConfigPo) config;
            log.info("DS-GF官方彩从startId="+startId+"开始准备拉取length="+length+",拉取配置configPo={}", configPo);
            List<DsGameTypeVo> dsGameTypePoList=dsGameTypeDao.findByGameTypeList(configPo.getGameKind());

            JSONObject request = new JSONObject();
            request.put("num", length);
            request.put("username", configPo.getUser());
            request.put("beginId", startId);
            request.put("accType", configPo.getLevel());
            HttpEntity<String> entity = new HttpEntity<>(request.toString());
            ResponseEntity<JSONObject> exchange=restTemplate.exchange(configPo.getRecordUrl(), HttpMethod.POST,entity,JSONObject.class);
            if (exchange==null || exchange.getBody().isEmpty()){
                log.error("DS-GF官方彩: 重要报警，用户={},参数={},断网维护或者未加白名单", configPo.getUser(),request.toString());
                return errorModel;
            }
            JSONObject object=exchange.getBody();

            if (object.getString("status").equals("10000") && object.getJSONArray("message").size() != 0) {// 获取成功
                JSONArray array = object.getJSONArray("message");
                int arraySize = array.size();
                log.info("DS-GF官方彩网站={},拉取到注单,数量={}", configPo.getUser(), arraySize);
                for (int i = 0; i < array.size(); i++) {
                    DtGFMgoPo po = array.getObject(i, DtGFMgoPo.class);
                    dtDFMgr.saveAndUpdate(po,configPo,dsGameTypePoList);
                    flag=true;
                }
                if (flag){
                    Long endId=dtDFMgr.getMaxId();
                    NumModel model= NumModel.builder()
                            .startId(startId)
                            .endId(endId)
                            .siteId(configPo.getSiteMap()).build();
                    configPo.getSiteMap().forEach((key,value)-> {
                        notifySite(key, arraySize,model);
                    });
                    ResponseModel responseModel=ResponseModel.builder()
                            .code(SUCCESS)
                            .massge(String.format("DS-GF官方彩站点=%s,拉取到注单,数量=%s",  configPo.getUser(), arraySize))
                            .obj(model.toString())
                            .build();
                    return responseModel;
                }

            } else if (object.getString("status").equals("10000") && object.getJSONArray("message").size() == 0) { // 没有拉取到注单
                log.info("DS-GF官方彩网站={} 拉取成功,但注单数量为0,拉取结果={}", configPo.getUser(), object.toJSONString());
            } else if (!object.getString("status").equals("10000")) {
                log.error("DS-GF官方彩网站={} 拉取数据失败,请检查配置,错误消息={}", configPo.getUser(), object.toJSONString());
            }
            return errorModel;
        }catch (Exception e){
            if (e instanceof ResourceAccessException){
                log.error("DS-GF官方彩未添加白名单或者可能在维护，错误信息={}",e.getMessage());
            }
            e.printStackTrace();
            throw new DsException(e);
        }

    }

    public ResponseModel getPullBet(String maxId,
                                    Integer length,
                                    BaseConfig config) throws DsException {
        return errorModel;
    }

}
