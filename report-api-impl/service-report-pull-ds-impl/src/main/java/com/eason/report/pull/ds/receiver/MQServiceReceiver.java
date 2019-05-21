package com.eason.report.pull.ds.receiver;

import com.eason.report.pull.ds.model.MsgModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQServiceReceiver {

    @JmsListener(destination = "ds_pull_error")
    public void errorMsg(MsgModel msg){
        log.info("收到ERROR消息，请注意="+msg);
        if("DS-GF-1111".equals(msg.getCode())){

        }else if("DS-JD-1111".equals(msg.getCode())){

        }
    }

    @JmsListener(destination = "ds_pull_receiver")
    public void receiverMsg(MsgModel msg){
        log.info("收到消息，开始分发="+msg);
        if("DS-GF-0000".equals(msg.getCode())){

        }else if("DS-JD-0000".equals(msg.getCode())){

        }else{
            log.error("消息信号ds_pull_receiver不正确，仅支持DS-GF-0000或者DS-JD-0000");
        }
    }

}
