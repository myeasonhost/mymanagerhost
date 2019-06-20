package com.eason.report.pull.core.mq;

import com.eason.report.pull.core.api.PushAPI;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.model.MsgModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQServiceConsumer extends BaseAPI{

    @Autowired
    private PushAPI pushAPI;

    @JmsListener(destination = "${target.siteId}")
    protected void receiverSite(MsgModel msg){
        log.info("站点siteId={},收到消息={}",siteId,msg);
        String type=msg.getType();
        pushAPI.getPushBet(type,msg.getModel());
    }

}
