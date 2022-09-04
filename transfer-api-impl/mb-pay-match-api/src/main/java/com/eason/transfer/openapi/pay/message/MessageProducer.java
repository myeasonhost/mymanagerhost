package com.eason.transfer.openapi.pay.message;

import com.eason.transfer.openapi.pay.domain.MbPayOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;


/**
 * @author ：doctor
 * @description：消息生产者
 */
@Slf4j
@EnableBinding(Source.class)
public class MessageProducer {

    @Autowired
    private Source source;


    public boolean payTimeOutput(MbPayOrder mbPayOrder, Integer seconds) {
        log.info("【MQ监控】【payTimeOutput】OUTPUT发送消息：message={}，延迟授权seconds={}",mbPayOrder,seconds);
        boolean result = source.payTimeOutput().send(MessageBuilder.withPayload(mbPayOrder)
                .setHeader("x-delay",  seconds * 1000).build());
        if (result) {
            return true;
        } else {
            log.info("【MQ监控】【payOutput】OUTPUT发送消息失败：message={}，延迟授权seconds={}",mbPayOrder,seconds);
            return false;
        }
    }


    public boolean payOutput(MbPayOrder mbPayOrder, Integer seconds) {
        /**** 2 分钟后消费授权消息 ****/
        log.info("【MQ监控】【payOutput】OUTPUT发送消息：message={}，延迟授权seconds={}",mbPayOrder,seconds);
        boolean result = source.payOutput().send(MessageBuilder.withPayload(mbPayOrder)
                .setHeader("x-delay",  seconds * 1000).build());
        if (result) {
            return true;
        } else {
            log.info("【MQ监控】【payOutput】OUTPUT发送消息失败：message={}，延迟授权seconds={}",mbPayOrder,seconds);
            return false;
        }
    }

}
