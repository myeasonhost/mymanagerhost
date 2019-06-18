package com.eason.report.pull.core.api;

import com.alibaba.fastjson.JSONArray;
import com.eason.report.pull.core.annotation.MQProducer;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.base.BaseConfig;
import com.eason.report.pull.core.exception.DsException;
import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.core.exception.TimeOutException;
import com.eason.report.pull.core.manager.MGMgr;
import com.eason.report.pull.core.model.DateModel;
import com.eason.report.pull.core.model.ResponseModel;
import com.eason.report.pull.core.mongo.po.MGMgoPo;
import com.eason.report.pull.core.mq.MQServiceProducer;
import com.eason.report.pull.core.mysqlDao.config.MgGameConfigPo;
import com.eason.report.pull.core.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;


/**
 * @author EASON LI
 */
@MQProducer
@Slf4j
@RestController
@RequestMapping(value = "/mg")
public class MGPullAPIImpl  extends MQServiceProducer implements PullAPI {
    protected ExecutorService executorService =  Executors.newFixedThreadPool(BaseAPI.GAMENUM);

    @Autowired
    private MGMgr mgMgr;

    /**
     * MG拉取
     */
    @RequestMapping(value = "/getPullBet",method = RequestMethod.POST)
    public List<ResponseModel> getPullBet() throws ServiceException {

        try{
            List<MgGameConfigPo> configPoList= mgMgr.loadConfig();
            CountDownLatch cdl = new CountDownLatch(configPoList.size());
            List<ResponseModel> list=new ArrayList<>();
            configPoList.forEach(configPo -> {
                Date maxId=mgMgr.getMaxId(configPo);
                Integer length=configPo.getLength();
                if (maxId == null || length>60){
                    log.error("MG大富豪从startId="+maxId+"开始准备拉取,拉取配置可能有误，MG最大拉取长度间隔为1h=60min，长度length="+length);
                    return;
                }
                Future<ResponseModel> future=executorService.submit(new Callable<ResponseModel>() {
                    @Override
                    public ResponseModel call() throws Exception {
                        try {
                            if (!executorService.isShutdown()) {
                                Date startId= DateUtils.addSeconds(new Date(maxId.getTime()),1);
                                ResponseModel result=getPullBet(DateUtil.covertStr(startId),length,configPo); //下一个秒开始
                                return result;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.error("MG大富豪网站={} 拉取数据失败,错误消息={},请检查配置={}", configPo.getUsername(), e.getMessage(), configPo);
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
     * MG拉取
     */
    public ResponseModel getPullBet(String pullTime, Integer length, BaseConfig config) throws ServiceException {
        try {
            boolean flag=false;
            String startId = pullTime;
            Date startDate=DateUtil.covertTime(startId);
            MgGameConfigPo configPo=(MgGameConfigPo) config;
            log.info("MG大富豪从startId="+startId+"开始准备拉取length="+length+",拉取配置configPo={}", configPo);
            String token= mgMgr.loginWebSite(configPo).get("token").toString();
            JSONArray jsonArray= mgMgr.loadDataToEndTime(token,startDate,length,configPo);
            if (jsonArray.isEmpty() || jsonArray.size()==0){
                log.info("MG大富豪网站={} endTime水位上升到当前时间，拉取结束,拉取结果={}",configPo.getUsername(), jsonArray.toJSONString());
                return errorModel;
            }
            int arraySize = jsonArray.size();
            log.info("MG大富豪网站={},拉取到注单,数量={}",configPo.getUsername(), arraySize);
            for (int i = 0; i < arraySize; i++) {
                MGMgoPo po = jsonArray.getObject(i, MGMgoPo.class);
                mgMgr.saveAndUpdate(po,configPo);
                flag=true;
            }
            Date endTime= DateUtils.addMinutes(startDate,length);
            DateModel model= DateModel.builder()
                    .startId(startId)
                    .endId(DateUtil.covertStr(endTime)).build();
            if (flag){
                configPo.getSiteMap().forEach((key,value)-> {
                    notifySite(key, arraySize,model);
                });
            }
            ResponseModel responseModel=ResponseModel.builder()
                    .code(SUCCESS)
                    .massge(String.format("MG大富豪网站=%s,拉取到注单,数量=%s",  configPo.getUsername(), arraySize))
                    .obj(model.toString())
                    .build();
            return responseModel;
        }catch (Exception e){
            if (e instanceof TimeOutException){
                log.error("MG大富豪未添加白名单或者可能在维护，错误信息={}",e.getMessage());
            }
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public ResponseModel getPullBet(Long maxId, Integer length,BaseConfig config) throws ServiceException {
        return errorModel;
    }
}
