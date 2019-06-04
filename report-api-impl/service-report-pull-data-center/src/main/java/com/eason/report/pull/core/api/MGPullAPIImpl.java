package com.eason.report.pull.core.api;

import com.alibaba.fastjson.JSONArray;
import com.eason.report.pull.core.config.MGAppInfoConfig;
import com.eason.report.pull.core.exception.TimeOutException;
import com.eason.report.pull.core.manager.DsMGMgr;
import com.eason.report.pull.core.mysqlDao.DsMGDao;
import com.eason.report.pull.core.mysqlDao.po.DsMgGamePo;
import com.eason.report.pull.core.utils.DateUtil;
import com.eason.report.pull.core.annotation.MQProducer;
import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.core.model.DateModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.CountDownLatch;


/**
 * @author EASON LI
 */
@MQProducer
@Slf4j
public class MGPullAPIImpl extends PullAPIService {

    @Autowired
    private MGAppInfoConfig appInfoConfig;

    @Autowired
    private DsMGMgr dsMGMgr;

    @Autowired
    private DsMGDao dsMGDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate10;

    /**
     * 官方彩拉取
     */
    public void getPullBet() throws ServiceException {
        try{
            Timestamp maxId;
            BoundHashOperations bho=stringRedisTemplate10.boundHashOps(MG_PULL_CONFIG);
            if(bho.get(END_TIME)==null){
                maxId= dsMGDao.getMaxId();
            }else{
                String endTime=(String)bho.get(END_TIME);
                Date endDate= DateUtil.getDateFormat(DATE_PATTERN).parse(endTime);
                maxId=new Timestamp(endDate.getTime());
            }
            Integer length=appInfoConfig.getLength();
            if (maxId == null || length>60){
                log.error("MG大富豪从startId="+maxId+"开始准备拉取,拉取配置可能有误，MG最大拉取长度间隔为1h=60min，长度length="+length);
                return;
            }
            Date startId=DateUtils.addSeconds(new Date(maxId.getTime()),1);
            getPullBet(DateUtil.getDateFormat(DATE_PATTERN).format(startId),length); //下一个秒开始
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
    }
    /**
     * 官方彩拉取
     */
    public void getPullBet(String pullTime,Integer length) throws ServiceException {
        try {
            boolean flag=false;
            String startId = pullTime;
            log.info("MG大富豪从startId="+startId+"开始准备拉取length="+length+",拉取配置appInfoConfig={}", appInfoConfig);
            String token= dsMGMgr.loginWebSite().get("token").toString();
            JSONArray jsonArray= dsMGMgr.loadDataToEndTime(token,pullTime,length);
            if (jsonArray.isEmpty() || jsonArray.size()==0){
                log.info("MG大富豪网站={} endTime水位上升到当前时间，拉取结束,拉取结果={}",appInfoConfig.getAgentId(), jsonArray.toJSONString());
                return;
            }
            int arraySize = jsonArray.size();
            log.info("MG大富豪网站={},拉取到注单,数量={}",appInfoConfig.getAgentId(), arraySize);
            for (int i = 0; i < arraySize; i++) {
                DsMgGamePo po = jsonArray.getObject(i, DsMgGamePo.class);
                dsMGMgr.saveAndUpdate(po);
                flag=true;
            }
            if (flag){
                BoundHashOperations bho=stringRedisTemplate10.boundHashOps(MG_PULL_CONFIG);
                String startTime=(String)bho.get(START_TIME);
                String endTime=(String)bho.get(END_TIME);
                DateModel model= DateModel.builder()
                        .startId(startTime)
                        .endId(endTime)
                        .siteId(appInfoConfig.getSiteId()).build();
                CountDownLatch cdl = new CountDownLatch(appInfoConfig.getSiteId().size());
                appInfoConfig.getSiteId().forEach((key,value)-> {
                    executorService.submit(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (!executorService.isShutdown()) {
                                    notifySite(key, arraySize,model);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                cdl.countDown();
                            }
                        }
                    });
                });
            }
        }catch (Exception e){
            if (e instanceof TimeOutException){
                log.error("MG大富豪未添加白名单或者可能在维护，错误信息={}",e.getMessage());
                return;
            }
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    protected void getPullBet(Long maxId, Integer length) throws ServiceException {

    }
}
