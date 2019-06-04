package com.eason.report.pull.core.mq;

import com.eason.report.pull.core.api.PullAPIService;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.model.DateModel;
import com.eason.report.pull.core.model.Model;
import com.eason.report.pull.core.model.MsgModel;
import com.eason.report.pull.core.model.NumModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class MQServiceProducer extends BaseAPI {

    @Autowired
    private JmsTemplate jsmTemplate;

    protected void notifySite(Integer siteId,Integer size,Model model){
        String className=this.getClass().getSimpleName();
        String type=className.substring(0, className.indexOf(xxxPullAPIImpl));
        PullAPIService pullAPIService=(PullAPIService)producerMap.get(className);
        if(className!=null && pullAPIService!=null){
            if(StringUtils.isEmpty(type)){
                log.error("站点siteId={},className={}消息发送任务不能执行，请检查类名规则",siteId,className);
                return;
            }
            MsgModel msg=MsgModel.builder()
                    .type(type)
                    .message("当前拉取size="+size+"条已完成，开始分发")
                    .model(model).build();
            jsmTemplate.convertAndSend(Integer.toString(siteId), msg);
            if(model instanceof NumModel){
                NumModel numModel=(NumModel)model;
                log.info("站点siteId={}，游戏type={},当前startId={}——endId={}，开始分发",siteId,type,numModel.getStartId(),numModel.getEndId());
            }else if(model instanceof DateModel){
                DateModel dateModel=(DateModel)model;
                log.info("站点siteId={}，游戏type={},当前startId={}——endId={}，开始分发",siteId,type,dateModel.getStartId(),dateModel.getEndId());
            }else{
                log.error("站点siteId={},model={}消息发送任务不能执行，请检查Model类型",siteId,model);
                return;
            }
        }
    }


}
