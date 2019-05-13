package com.eason.report.pull.ag;

import com.eason.report.pull.ag.vo.common.SumordersDaysVo;
import com.eason.report.pull.ag.vo.common.SumordersDaysXinVo;
import com.eason.report.pull.ag.vo.common.SumordersVo;

/**
 * @apiDefine common 通用API
 */
public interface ICommonAPI {

    /**
     * @apiVersion 3.4.0
     * @apiGroup common
     * @api {GET} /common/getsumorders 获取订单总计
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
}
