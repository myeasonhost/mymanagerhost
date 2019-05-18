package com.eason.report.pull.ag;

import com.eason.report.pull.ag.vo.common.*;
import com.eason.report.pull.ag.vo.competition.CompOrdersRo;
import com.eason.report.pull.ag.vo.competition.CompOrdersVo;
import com.eason.report.pull.ag.vo.hunter.GameOrdersRo;
import com.eason.report.pull.ag.vo.hunter.GameOrdersVo;
import com.eason.report.pull.ag.vo.hunter.ScenesOfUserReportExtVo;
import com.eason.report.pull.ag.vo.hunter.ScenesOfUserReportExtRo;
import com.eason.report.pull.ag.vo.live.OrdersRo;
import com.eason.report.pull.ag.vo.live.OrdersVo;
import com.eason.report.pull.ag.vo.slot.SlotOrdersRo;
import com.eason.report.pull.ag.vo.slot.SlotOrdersTExRo;
import com.eason.report.pull.ag.vo.slot.SlotOrdersTExVo;
import com.eason.report.pull.ag.vo.slot.SlotOrdersVo;
import com.eason.report.pull.ag.vo.sport.AgSportOrdersExVo;
import com.eason.report.pull.ag.vo.sport.AgSportOrdersExRo;
import com.eason.report.pull.ag.vo.xinSlot.XinEventResRo;
import com.eason.report.pull.ag.vo.xinSlot.XinEventResVo;
import com.eason.report.pull.ag.vo.xinSlot.XinSlotEventResRo;
import com.eason.report.pull.ag.vo.xinSlot.XinSlotEventResVo;
import com.eason.report.pull.h8.vo.YoPlayOrdersExRo;
import com.eason.report.pull.h8.vo.YoPlayOrdersExVo;

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
    public SumOrdersRo getSumOrders(SumOrdersVo vo) throws Exception;

    public SumOrdersDaysRo getSumOrdersDays(SumOrdersDaysVo vo);

    public SumOrdersDaysXinRo getSumOrdersDaysXin(SumOrdersDaysXinVo vo);

    public OrdersRo getOrders(OrdersVo vo);

    public SlotOrdersRo getSlotOrdersEX(SlotOrdersVo vo);

    public YoPlayOrdersExRo getYoPlayOrdersEX (YoPlayOrdersExVo vo);

    public SlotOrdersTExRo getSlotOrdersTEX (SlotOrdersTExVo vo);

    public AgSportOrdersExRo getAgSportOrdersEX(AgSportOrdersExVo vo);

    public XinSlotEventResRo getXinSlotEventRes(XinSlotEventResVo vo);

    public XinEventResRo getXinEventRes(XinEventResVo vo);

    public CompOrdersRo getComPorders(CompOrdersVo vo);

    public GameOrdersRo getGameOrders (GameOrdersVo vo);

    public ScenesOfUserReportExtRo getScenesOfUserReportExt (ScenesOfUserReportExtVo vo);
}
