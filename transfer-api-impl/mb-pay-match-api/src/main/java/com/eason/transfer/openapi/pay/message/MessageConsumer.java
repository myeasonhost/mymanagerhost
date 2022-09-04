package com.eason.transfer.openapi.pay.message;

import com.eason.transfer.openapi.pay.domain.MbPayOrder;
import com.eason.transfer.openapi.pay.task.PayNotifyWorker;
import com.eason.transfer.openapi.pay.task.PayTimeOutWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @author ：doctor
 * @description：订单消息消费者
 */
@Slf4j
@EnableBinding(UserSink.class)
public class MessageConsumer {

    @Autowired
    PayTimeOutWorker payTimeOutWorker;
    @Autowired
    PayNotifyWorker payNotifyWorker;


    @StreamListener("payTimeInput")
    public void payTimeInput(Message<MbPayOrder> message, @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) {
        log.info("收到订单超时通知消息:{},deliveryTag={}", message.getPayload(),deliveryTag);
        payTimeOutWorker.payTimeOut(message.getPayload());
    }

    @StreamListener("payInput")
    public void payInput(Message<MbPayOrder> message, @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) {
        log.info("收到支付通知消息:{},deliveryTag={}", message.getPayload(),deliveryTag);
        payNotifyWorker.payNotify(message.getPayload());
    }

}
