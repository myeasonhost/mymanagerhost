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
    public SumordersResponseVo getsumorders(SumordersVo vo) {
        String pullUrl=stringRedisTemplate10.boundHashOps("ag").get("pullUrl").toString();
        String pidtoken=stringRedisTemplate10.boundHashOps("ag").get("pidtoken").toString();

        String key= Md5Util.makeMd5Sum((vo.getCAgent()+vo.getStartDate()+vo.getEndDate()+pidtoken).getBytes());
        vo.setKey(key);
        String str=restTemplate.getForObject(pullUrl+"?enddate=2019-05-09 00:10:00&cagent=CS2&startdate=2019-05-09 00:00:00&key=208c5d78b7b95fe29f401e270de22c98",String.class);
        System.out.println(str);
        return null;
    }

    @Override
    public SumordersDaysResponseVo getsumorders_days(SumordersDaysVo vo) {
        return null;
    }

    @Override
    public SumordersDaysXinResponseVo getsumorders_days_xin(SumordersDaysXinVo vo) {
        return null;
    }

    @Override
    public OrdersResponseVo getOrders(OrdersVo vo) {
        return null;
    }

    @Override
    public SlotOrdersResponseVo getSlotOrdersEX(SlotOrdersVo vo) {
        return null;
    }

    @Override
    public YoPlayOrdersExReponseVo getYoPlayOrdersEX(YoPlayOrdersExVo vo) {
        return null;
    }

    @Override
    public SlotOrdersTExResponseVo getSlotOrdersTEX(SlotOrdersTExVo vo) {
        return null;
    }

    @Override
    public AgSportOrdersResponseVo getAgSportOrdersEX(AgSportOrdersExVo vo) {
        return null;
    }

    @Override
    public XinSlotEventReponseVo getXinSlotEventRes(XinSlotEventResVo vo) {
        return null;
    }

    @Override
    public XinEventResResponseVo getXinEventRes(XinEventResVo vo) {
        return null;
    }

    @Override
    public CompOrdersResponseVo getComporders(CompordersVo vo) {
        return null;
    }

    @Override
    public GameOrdersResponseVo getGameOrders(GameOrdersVo vo) {
        return null;
    }

    @Override
    public ScenesOfUserResponseVo getScenesOfUserReportExt(ScenesOfUserReportExtVo vo) {
        return null;
    }

}