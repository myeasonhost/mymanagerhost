package com.eason.report.pull.ds.activemqDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Service
public class ActivemqService {

    @Autowired
    private JmsTemplate jsmTemplate;
    public void sendMsg(String msg){
        jsmTemplate.send("erick", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText(msg);
                return textMessage;
            }
        });
    }

    @JmsListener(destination = "erick")
    public void rctiveMsg(String message){
        System.out.println("------监听到activemq的数据"+message);
    }
}
