package com.eason.report.pull.ag;

import com.eason.report.pull.ag.vo.common.*;

/**
 * @apiDefine api 通用API
 */
public interface ICommonAPI {

    /**
     * @apiVersion 3.4.0
     * @apiGroup api
     * @api {GET} /api/getsumorders 获取订单总计
     * @apiName getsumorders
     *
     *
     * @apiDescription
     * >  详见AG-AGIN_Data_API_V3.40.pdf 文档
     * >  获取拉取数据存到MongoDB
     *
     * @apiSuccess {String} result 		成功或者失败
     */
    public String getsumorders(SumordersVo vo);

    public String getsumorders_days(SumordersDaysVo vo);

    public String getsumorders_days_xin(SumordersDaysXinVo vo);

    public String getOrders(OrdersVo vo);

    public String getSlotOrdersEX(SlotOrdersVo vo);

    public String getYoPlayOrdersEX (YoPlayOrdersEX vo);

    public String getSlotOrdersTEX (SlotOrdersTEX vo);

    public String getAgSportOrdersEX(AgSportOrdersEX vo);

    public String getXinSlotEventRes(XinSlotEventRes vo);

    public String getXinEventRes(XinEventRes vo);

    public String getComporders(Comporders vo);

    public String getGameOrders (GameOrders vo);

    public String getScenesOfUserReportExt (ScenesOfUserReportExt vo);
}
