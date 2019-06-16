package com.eason.report.pull.core.mq;

import com.eason.report.pull.core.api.PushAPI;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.model.DateModel;
import com.eason.report.pull.core.model.Model;
import com.eason.report.pull.core.model.MsgModel;
import com.eason.report.pull.core.model.NumModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class MQServiceConsumer extends BaseAPI {


    @JmsListener(destination = "${target.siteId}")
    protected void receiverSite(MsgModel msg){
        log.info("站点siteId={},收到消息={}",siteId,msg);
        String type;
        try {
            type=msg.getType();
            PushAPI pushAPIService=(PushAPI) consumerMap.get(type+xxxPushAPIImpl);
            if (StringUtils.isEmpty(type) && pushAPIService!=null) {
                log.error("站点siteId={},pushAPIService={}消息接收任务不能执行，请检查类名规则",siteId,pushAPIService);
                return;
            }
            Model model=msg.getModel();
            if(model instanceof NumModel){
                NumModel numModel=(NumModel)model;
                pushAPIService.getPushBet(siteId, type, numModel.getStartId(), numModel.getEndId());
                log.info("站点siteId={}，游戏type={},当前startId={}——endId={}，开始分发",siteId,type,numModel.getStartId(),numModel.getEndId());
            }else if(model instanceof DateModel){
                DateModel dateModel=(DateModel)model;
                pushAPIService.getPushBet(siteId, type, dateModel.getStartId(), dateModel.getEndId());
                log.info("站点siteId={}，游戏type={},当前startId={}——endId={}，开始分发",siteId,type,dateModel.getStartId(),dateModel.getEndId());
            }else{
                log.error("站点siteId={},model={}消息接收任务不能执行，请检查消息模型Model",siteId,model);
            }

        } catch (Exception e) {
            log.error("站点siteId={},系统异常={}", siteId,e.getMessage());
            e.printStackTrace();
        }
    }

}
