package com.eason.transfer.openapi.pay.task;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.eason.transfer.openapi.pay.domain.MbPayOrder;
import com.eason.transfer.openapi.pay.message.MessageProducer;
import com.eason.transfer.openapi.pay.model.PayNotifyRequest;
import com.eason.transfer.openapi.pay.service.IMbPayOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class PayNotifyWorker {

    @Autowired
    private MessageProducer messageProducer;
    @Autowired
    private IMbPayOrderService iMbPayOrderService;

    public void payNotify(MbPayOrder order) {
        log.info("支付回调通知操作:商户{}，订单号:{}，当前订单状态status={}", order.getSiteId(), order.getId(), order.getStatus());
        if (StringUtils.hasText(order.getNotifyUrl()) || !order.getNotifyUrl().startsWith("http://")) {
            LambdaUpdateWrapper<MbPayOrder> updateWrapper = new LambdaUpdateWrapper();
            //0=未通知，1=通知成功，2=通知失败
            updateWrapper.set(MbPayOrder::getNotifySucceed, "2")
                    .set(MbPayOrder::getLastNotifyTime, new Date(System.currentTimeMillis()))
                    .eq(MbPayOrder::getId, order.getId());
            iMbPayOrderService.update(updateWrapper);
            log.info("支付回调通知操作:商户{}，订单号:{}，通知地址错误,修改通知失败", order.getSiteId(), order.getId());
            return;
        }
        PayNotifyRequest notify = new PayNotifyRequest();
        notify.setNotifyId(order.getId());
        notify.setSiteId(order.getSiteId());
        notify.setUserId(order.getUserId());
        notify.setOrderId(order.getOrderId());
        notify.setAmount(order.getAmount());
        notify.setFinishTime(DateUtils.formatDate(order.getFinishTime(),"yyyy-MM-dd HH:mm:ss"));

        log.info("支付回调通知操作:商户{}，订单号:{}，被发送的消息是{}",  order.getSiteId(), order.getId(),notify);

        Long timeStamp=System.currentTimeMillis();
        String responseString = null;
        try {
//        String appResult = PostClient.sendRequest(order.getNotifyUrl(), appParamMap, fileMap, systemParam.getAppSecret(), tmpPostParams);
//        responseString = MbPayUtils.post(order.getNotifyUrl(), "", request);
            responseString = "SUCCESS";
            log.info("支付回调通知操作：通知成功，第三方回调返回={}", responseString);
        } catch (Exception e) {
            log.error("支付回调通知操作：URL={}，回调出错={}",order.getNotifyUrl(),e.getMessage());
            if (order.getNotifyTimes() < 3) {
                order.setNotifyTimes(order.getNotifyTimes() + 1);
                order.setLastNotifyTime(new Date(timeStamp));
                Integer dalayTime = getTimeForNetNotify(order.getNotifyTimes());
                order.setNextNotifyTime(new Date(timeStamp + dalayTime));
                this.iMbPayOrderService.updateById(order);

                log.info("支付回调通知操作：重试次数={}，回调错误={}", order.getNotifyTimes(), e.getMessage());
                this.messageProducer.payOutput(order, dalayTime);
            } else {
                order.setLastNotifyTime(new Date(timeStamp));
                order.setNotifySucceed("2"); //0=未通知，1=通知成功，2=通知失败
                log.info("支付回调通知操作：通知结束，累计通知回调次数{},通知失败{}，", order.getNotifyTimes(), e.getMessage());
                this.iMbPayOrderService.updateById(order);
            }
            e.printStackTrace();
            return;
        }

        if ("SUCCESS".equals(responseString)) {  //返回结果SUCCESS
            order.setNotifySucceed("1"); //0=未通知，1=通知成功，2=通知失败
        } else {
            order.setNotifySucceed("2");
        }
        order.setLastNotifyTime(new Date(timeStamp));
        this.iMbPayOrderService.updateById(order);
    }

    /****  通知原则:
     * 第一次通知：距离第一次10秒钟;
     * 第二次通知：距离第二次1分钟;
     * 第三次通知: 距离第三次5分钟;
    ****  构造通知次数与每次通知时间间隔的毫秒数 ****/
   private static Map<Integer, Integer> timeSecondsMap = new HashMap<Integer, Integer>() {{
        put(1, 10000);
        put(2, 60000);
        put(3, 300000);
    }};

    /***
     * @Description 根据当前通知次数查询
     ****/
    public static Integer getTimeForNetNotify(Integer times) {
        return timeSecondsMap.getOrDefault(times, 0);
    }

}
