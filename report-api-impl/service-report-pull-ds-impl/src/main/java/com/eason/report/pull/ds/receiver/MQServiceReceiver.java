package com.eason.report.pull.ds.receiver;

import com.eason.report.pull.ds.model.DsLotteryModel;
import com.eason.report.pull.ds.model.MsgModel;
import com.eason.report.pull.ds.mysqlDao.DtGFDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class MQServiceReceiver {
    @Autowired
    private DtGFDao dtGFDao;

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
            DsLotteryModel dsLotteryModel=(DsLotteryModel)msg.getObject();
            Map<String,String> map=dsLotteryModel.getSiteId();
            map.forEach((key,value)->{
                dtGFDao.sitePull(Integer.parseInt(value),dsLotteryModel.getStartId(),dsLotteryModel.getEndId());
            });

//            String result=dtGFDao.createAuditAndReport(1020,"888821_TYZ",45,34369023L,35182641L);
//            System.out.println(result);
        }else if("DS-JD-0000".equals(msg.getCode())){

        }else{
            log.error("消息信号ds_pull_receiver不正确，仅支持DS-GF-0000或者DS-JD-0000");
        }
    }

}
