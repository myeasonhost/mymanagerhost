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
    public SumordersResponseVo getsumorders(SumordersVo vo);

    public SumordersDaysResponseVo getsumorders_days(SumordersDaysVo vo);

    public SumordersDaysXinResponseVo getsumorders_days_xin(SumordersDaysXinVo vo);

    public OrdersResponseVo getOrders(OrdersVo vo);

    public SlotOrdersResponseVo getSlotOrdersEX(SlotOrdersVo vo);

    public YoPlayOrdersExReponseVo getYoPlayOrdersEX (YoPlayOrdersExVo vo);

    public SlotOrdersTExResponseVo getSlotOrdersTEX (SlotOrdersTExVo vo);

    public AgSportOrdersResponseVo getAgSportOrdersEX(AgSportOrdersExVo vo);

    public XinSlotEventReponseVo getXinSlotEventRes(XinSlotEventResVo vo);

    public XinEventResResponseVo getXinEventRes(XinEventResVo vo);

    public CompOrdersResponseVo getComporders(CompordersVo vo);

    public GameOrdersResponseVo getGameOrders (GameOrdersVo vo);

    public ScenesOfUserResponseVo getScenesOfUserReportExt (ScenesOfUserReportExtVo vo);
}
