package com.eason.transfer.openapi.pay.task;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.eason.transfer.openapi.pay.domain.MbPayOrder;
import com.eason.transfer.openapi.pay.service.IMbPayOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Slf4j
@Service
public class PayTimeOutWorker {

    @Autowired
    private IMbPayOrderService iMbPayOrderService;

    /**
     * 订单超时，关闭订单--status=4
     */
    public void payTimeOut(MbPayOrder order) {
        order = iMbPayOrderService.getById(order.getId());
        log.info("订单超时通知操作:商户{}，订单号:{}，当前订单状态status={}，开始关闭订单", order.getSiteId(), order.getId(), order.getStatus());
        if ("1".equals(order.getStatus()) || "2".equals(order.getStatus())) {
            LambdaUpdateWrapper<MbPayOrder> updateWrapper = new LambdaUpdateWrapper();
            //0=下单成功，1=支付中,2=玩家确认支付，3=玩家支付取消，4=支付超时，5=支付审核，6=支付成功
            updateWrapper.set(MbPayOrder::getStatus, "4")
                    .set(MbPayOrder::getTimeoutTime, new Date(System.currentTimeMillis()))
                    .eq(MbPayOrder::getId, order.getId());
            iMbPayOrderService.update(updateWrapper);
        }
    }

}
