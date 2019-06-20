package com.eason.report.pull.core.mq;

import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.model.Model;
import com.eason.report.pull.core.model.MsgModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQServiceProducer extends BaseAPI{

    @Autowired
    private JmsTemplate jsmTemplate;

    public void notifySite(Integer siteId,String code,Object startId,Object endId,MsgModel msgModel){
        Model model= Model.builder()
                .code(code)
                .startId(startId)
                .endId(endId).build();
        msgModel.setModel(model);
        jsmTemplate.convertAndSend(Integer.toString(siteId), msgModel);
        log.info("站点siteId={},code={},当前startId={}——endId={}，开始分发",siteId,code,model.getStartId(),model.getEndId());
    }

}
