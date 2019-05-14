package com.eason.report.pull.ag.common;

import com.eason.report.pull.ag.ICommonAPI;
import com.eason.report.pull.ag.vo.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author EASON LI
 */
public class CommonAPIImpl implements ICommonAPI {
    private static Logger _log = LoggerFactory.getLogger(CommonAPIImpl.class);

    // 启动的时候要注意，由于我们在controller中注入了RestTemplate，所以启动的时候需要实例化该类的一个实例
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getsumorders(SumordersVo vo) {
//        restTemplate.getForEntity()
        return null;
    }

    @Override
    public String getsumorders_days(SumordersDaysVo vo) {
        return null;
    }

    @Override
    public String getsumorders_daysgetsumorders_days(SumordersDaysXinVo vo) {
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
