package com.eason.report.pull.core.mq;

import com.eason.report.pull.core.annotation.MQProducer;
import com.eason.report.pull.core.api.PullAPI;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.model.Model;
import com.eason.report.pull.core.model.MsgModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
@Slf4j
public class MQServiceProducer extends BaseAPI implements ApplicationContextAware{
    protected Map<String, Object> producerMap;
    protected ApplicationContext applicationContext;

    @Autowired
    private JmsTemplate jsmTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
        producerMap = applicationContext.getBeansWithAnnotation(MQProducer.class); // 获取所有带有 MQConsumer 注解的 Spring Bean
        for (Object serviceBean : producerMap.values()) {
            MQProducer result = serviceBean.getClass().getAnnotation(MQProducer.class);
            System.out.println(result);
        }
    }

    protected void notifySite(Integer siteId,Integer size,Model model){
        String type=model.getType();
        if(StringUtils.isEmpty(type)){
            log.error("站点siteId={},消息发送任务不能执行，请检查类名规则",siteId);
            return;
        }
        MsgModel msg=MsgModel.builder()
                .type(type)
                .message("当前拉取size="+size+"条已完成，开始分发")
                .model(model).build();
        jsmTemplate.convertAndSend(Integer.toString(siteId), msg);
        if(model instanceof Model){
            log.info("站点siteId={}，游戏type={},当前startId={}——endId={}，开始分发",siteId,type,model.getStartId(),model.getEndId());
        }else{
            log.error("站点siteId={},model={}消息发送任务不能执行，请检查Model类型",siteId,model);
            return;
        }
    }


}
