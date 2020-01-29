//package com.ds.money.service;
//
//import javax.annotation.Resource;
//import javax.jms.Destination;
//import javax.jms.TextMessage;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ConsumerService
//{
//  @Resource(name="jmsTemplate")
//  private JmsTemplate jmsTemplate;
//
//  public TextMessage receive(Destination destination)
//  {
//    TextMessage tm = (TextMessage)this.jmsTemplate.receive(destination);
//    return tm;
//  }
//}
