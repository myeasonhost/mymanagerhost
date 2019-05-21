package com.eason.report.pull.ds.listener;

import com.eason.report.pull.ds.model.MsgModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MQServiceListener {
    @Autowired
    private JmsTemplate jsmTemplate;

    public void sendErrorMsg(MsgModel msg){
        jsmTemplate.convertAndSend("ds_pull_error", msg);
    }

    public void sendReceiverMsg(MsgModel msg){
        jsmTemplate.convertAndSend("ds_pull_receiver", msg);
    }

}
