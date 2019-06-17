package com.eason.report.pull.core.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eason.report.pull.core.annotation.MQProducer;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.base.BaseConfig;
import com.eason.report.pull.core.exception.DsException;
import com.eason.report.pull.core.manager.MdtJDMgr;
import com.eason.report.pull.core.model.NumModel;
import com.eason.report.pull.core.model.ResponseModel;
import com.eason.report.pull.core.mongo.po.MdtJDMgoPo;
import com.eason.report.pull.core.mq.MQServiceProducer;
import com.eason.report.pull.core.mysqlDao.config.DtLotteryConfigPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
@RequestMapping(value = "/mdtjd")
public class MDTJDPullAPIImpl extends MQServiceProducer implements PullAPI {
    protected ExecutorService executorService =  Executors.newFixedThreadPool(BaseAPI.GAMENUM);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MdtJDMgr mdtJDMgr;


    /**
     * MDT经典彩拉取
     */
    @RequestMapping(value = "/getPullBet",method = RequestMethod.POST)
    public List<ResponseModel> getPullBet() throws DsException {
        try{
            Long maxId=mdtJDMgr.getMaxId();
            List<DtLotteryConfigPo> lotteryConfigPoList=mdtJDMgr.loadConfig();
            CountDownLatch cdl = new CountDownLatch(lotteryConfigPoList.size());
            List<ResponseModel> list=new ArrayList<>();
            lotteryConfigPoList.forEach(configPo -> {
                Integer length=configPo.getLength();
                if (maxId ==0 || length<=0){
                    log.error("MDT-JD经典彩从startId="+maxId+"开始准备拉取,拉取配置可能有误，长度length="+length);
                    return;
                }
                Future<ResponseModel> future=executorService.submit(new Callable<ResponseModel>() {
                    @Override
                    public ResponseModel call() throws Exception {
                        try {
                            if (!executorService.isShutdown()) {
                                ResponseModel result=getPullBet(maxId+1,length, configPo); //下一个开始
                                return result;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.error("MDT-JD经典彩网站={} 拉取数据失败,错误消息={},请检查配置={}", configPo.getUser(), e.getMessage(), configPo);
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

    /**
     * 经典彩拉取
     */
    public ResponseModel getPullBet(Long maxId,
                                    Integer length,
                                    BaseConfig config) throws DsException {
        try {
            boolean flag=false;
            Long startId = maxId;
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
            if (exchange==null || exchange.getBody().isEmpty()){
                log.error("MDT-JD经典彩: 重要报警，用户={},参数={},断网维护或者未加白名单", configPo.getUser(),request.toString());
                return errorModel;
            }
            JSONObject object=exchange.getBody();

            if (object.getString("Message").equals("success") && object.getJSONArray("Result").size() != 0) {// 获取成功
                JSONArray array = object.getJSONArray("Result");
                int arraySize = array.size();
                log.info("MDT-JD经典彩网站={},拉取到注单 ,数量={}", configPo.getUser(), arraySize);
                for (int i = 0; i < array.size(); i++) {
                    MdtJDMgoPo po = array.getObject(i, MdtJDMgoPo.class);
                    mdtJDMgr.saveAndUpdate(po,configPo);
                    flag=true;
                }
                if (flag){
                    Long endId=mdtJDMgr.getMaxId();
                    NumModel model= NumModel.builder()
                            .startId(startId)
                            .endId(endId).build();
                    configPo.getSiteMap().forEach((key,value)-> {
                        notifySite(key, arraySize,model);
                    });
                    ResponseModel responseModel=ResponseModel.builder()
                            .code(SUCCESS)
                            .massge(String.format("MDT-JD经典彩站点=%s,拉取到注单,数量=%s",  configPo.getUser(), arraySize))
                            .obj(model.toString())
                            .build();
                    return responseModel;
                }
            } else if (object.getString("Message").equals("success") && object.getJSONArray("Result").size() == 0) { // 没有拉取到注单
                log.info("MDT-JD经典彩网站={} 拉取成功,但注单数量为0,拉取结果={}", configPo.getUser(),object.toJSONString());
                ResponseModel responseModel=ResponseModel.builder()
                        .code(SUCCESS)
                        .massge(String.format("MDT-JD经典彩站点=%s,拉取到注单,数量=%s",  configPo.getUser(), 0))
                        .obj(object.toString())
                        .build();
                return responseModel;
            } else if (!object.getString("Message").equals("success")) {
                log.error("MDT-JD经典彩网站={} 拉取数据失败,请检查配置,错误消息={}", configPo.getUser(), object.toJSONString());
            }
            return errorModel;
        }catch (Exception e){
            if (e instanceof ResourceAccessException){
                log.error("MDT-JD经典彩未添加白名单或者可能在维护，错误信息={}",e.getMessage());
            }
            e.printStackTrace();
            throw new DsException(e.getMessage());
        }

    }

    public ResponseModel getPullBet(String maxId,
                                    Integer length,
                                    BaseConfig config) throws DsException {
        return errorModel;
    }
}
