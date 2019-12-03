package com.eason.transfer.openapi.chess.api;

import com.eason.transfer.openapi.chess.aop.LoadConfig;
import com.eason.transfer.openapi.chess.dao.mapper.TReportAuditTotalPoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ChessServiceImpl {

    @Autowired
    private TReportAuditTotalPoMapper reportAuditTotalPoMapper;

    @LoadConfig(name = "棋牌配置信息",targetMgr = LCPullServiceImpl.class)
    public String getPullBet(String... args){
        if (args.length==4){
            Map<String, Object> map = new HashMap<>();
            map.put("agentId", args[0]);
            map.put("gameKind", args[1]);
            map.put("startId", args[2]);
            map.put("endId", args[3]);
            map.put("result", null);

            reportAuditTotalPoMapper.executeAuditAndReport(map);
            String result=(String) map.get("result");
            log.info("\r\n"+result);
            return result;
        }
        return null;
    }

}
