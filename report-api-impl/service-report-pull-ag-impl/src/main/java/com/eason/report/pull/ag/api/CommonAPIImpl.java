package com.eason.report.pull.ag.api;

import com.eason.report.pull.ag.ICommonAPI;
import com.eason.report.pull.ag.base.BaseAPI;
import com.eason.report.pull.ag.utils.Md5Util;
import com.eason.report.pull.ag.vo.common.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author EASON LI
 */
@Service
@Slf4j
public class CommonAPIImpl extends BaseAPI implements ICommonAPI {

    // 启动的时候要注意，由于我们在controller中注入了RestTemplate，所以启动的时候需要实例化该类的一个实例
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisTemplate redisTemplate10;
    @Autowired
    private StringRedisTemplate stringRedisTemplate10;


    @Override
    public String getsumorders(SumordersVo vo) {
        String pullUrl=stringRedisTemplate10.boundHashOps("ag").get("pullUrl").toString();
        String pidtoken=stringRedisTemplate10.boundHashOps("ag").get("pidtoken").toString();

        String key= Md5Util.makeMd5Sum((vo.getCAgent()+vo.getStartDate()+vo.getEndDate()+pidtoken).getBytes());
        vo.setKey(key);
        String str=restTemplate.getForObject(pullUrl+"?enddate=2019-05-09 00:10:00&cagent=CS2&startdate=2019-05-09 00:00:00&key=208c5d78b7b95fe29f401e270de22c98",String.class);
        System.out.println(str);
        return null;
}

    @Override
    public String getsumorders_days(SumordersDaysVo vo) {
        return null;
    }

    @Override
    public String getsumorders_days_xin(SumordersDaysXinVo vo) {
        return null;
    }

    @Override
    public String getOrders(OrdersVo vo) {
        return null;
    }

    @Override
    public String getSlotOrdersEX(SlotOrdersVo vo) {
        return null;
    }

    @Override
    public String getYoPlayOrdersEX(YoPlayOrdersEX vo) {
        return null;
    }

    @Override
    public String getSlotOrdersTEX(SlotOrdersTEX vo) {
        return null;
    }

    @Override
    public String getAgSportOrdersEX(AgSportOrdersEX vo) {
        return null;
    }

    @Override
    public String getXinSlotEventRes(XinSlotEventRes vo) {
        return null;
    }

    @Override
    public String getXinEventRes(XinEventRes vo) {
        return null;
    }

    @Override
    public String getComporders(Comporders vo) {
        return null;
    }

    @Override
    public String getGameOrders(GameOrders vo) {
        return null;
    }

    @Override
    public String getScenesOfUserReportExt(ScenesOfUserReportExt vo) {
        return null;
    }

    //@Override
    public String getsumorders_days_xin(SumordersDaysXinVo vo) {
        return null;
    }
}
