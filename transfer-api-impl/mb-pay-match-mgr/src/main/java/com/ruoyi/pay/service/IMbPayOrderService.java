package com.ruoyi.pay.service;

import com.ruoyi.pay.domain.MbPayOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * 充值订单Service接口
 *
 * @author doctor
 * @date 2022-09-05
 */
public interface IMbPayOrderService extends IService<MbPayOrder> {

    /**
     * 查询列表
     */
    List<MbPayOrder> queryList(MbPayOrder mbPayOrder);
}
