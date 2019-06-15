package com.eason.report.pull.core.api;

import com.alibaba.fastjson.JSONArray;
import com.eason.report.pull.core.annotation.MQProducer;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.base.BaseConfig;
import com.eason.report.pull.core.config.MGConfigMgoCo;
import com.eason.report.pull.core.exception.DsException;
import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.core.exception.TimeOutException;
import com.eason.report.pull.core.manager.MGMgr;
import com.eason.report.pull.core.model.NumModel;
import com.eason.report.pull.core.model.ResponseModel;
import com.eason.report.pull.core.mq.MQServiceProducer;
import com.eason.report.pull.core.mysqlDao.dao.DsGameTypeDao;
import com.eason.report.pull.core.mysqlDao.dao.DsMGDao;
import com.eason.report.pull.core.mysqlDao.po.DsMgGamePo;
import com.eason.report.pull.core.mysqlDao.vo.DsGameTypeVo;
import com.eason.report.pull.core.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;


/**
 * @author EASON LI
 */
@MQProducer
@Slf4j
public class MGPullAPIImpl  extends MQServiceProducer implements PullAPI {
    protected ExecutorService executorService =  Executors.newFixedThreadPool(BaseAPI.GAMENUM);

    @Autowired
    private MGMgr MGMgr;

    @Autowired
    private DsMGDao dsMGDao;

    @Autowired
    private DsGameTypeDao dsGameTypeDao;


    /**
     * MG拉取
     */
    public List<ResponseModel> getPullBet() throws ServiceException {

        try{
            List<MGConfigMgoCo> configPoList= MGMgr.loadConfig();
            CountDownLatch cdl = new CountDownLatch(configPoList.size());
            List<ResponseModel> list=new ArrayList<>();

            configPoList.forEach(configPo -> {
                Timestamp maxId=dsMGDao.getMaxId();
                if(configPo.getStartTime()==null){
                    maxId= dsMGDao.getMaxId();
                }else{
                    maxId=configPo.getEndTime();
                }
                Date startId=DateUtils.addSeconds(new Date(maxId.getTime()),1);

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
            MGConfigMgoCo configPo=(MGConfigMgoCo) config;
            List<DsGameTypeVo> dsGameTypePoList=dsGameTypeDao.findByGameTypeList(configPo.getGameKind());
            log.info("MG大富豪从startId="+startId+"开始准备拉取length="+length+",拉取配置configPo={}", configPo);
            String token= MGMgr.loginWebSite(configPo).get("token").toString();
            JSONArray jsonArray= MGMgr.loadDataToEndTime(token,DateUtil.covertTime(startId),length,configPo);
            if (jsonArray.isEmpty() || jsonArray.size()==0){
                log.info("MG大富豪网站={} endTime水位上升到当前时间，拉取结束,拉取结果={}",configPo.getUsername(), jsonArray.toJSONString());
                return errorModel;
            }
            int arraySize = jsonArray.size();
            log.info("MG大富豪网站={},拉取到注单,数量={}",configPo.getUsername(), arraySize);
            for (int i = 0; i < arraySize; i++) {
                DsMgGamePo po = jsonArray.getObject(i, DsMgGamePo.class);
                MGMgr.saveAndUpdate(po,configPo,dsGameTypePoList);
                flag=true;
            }
            if (flag){
                Timestamp endId=dsMGDao.getMaxId();
                NumModel model= NumModel.builder()
                        .startId(DateUtil.covertTime(startId).getTime())
                        .endId(endId.getTime())
                        .siteId(configPo.getSiteMap()).build();
                configPo.getSiteMap().forEach((key,value)-> {
                    notifySite(key, arraySize,model);
                });
                ResponseModel responseModel=ResponseModel.builder()
                        .code(SUCCESS)
                        .massge(String.format("MG大富豪网站=%s,拉取到注单,数量=%s",  configPo.getUsername(), arraySize))
                        .obj(model.toString())
                        .build();
                return responseModel;
            }
            return errorModel;
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
