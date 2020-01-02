package com.eason.transfer.openapi.zb.listener;

import com.corundumstudio.socketio.SocketIOServer;
import com.eason.transfer.openapi.zb.api.room.model.RRoom;
import com.eason.transfer.openapi.zb.api.zhubo.model.RUser;
import com.eason.transfer.openapi.zb.api.zhubo.model.RZhubo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RLongAdder;
import org.redisson.api.RedissonClient;
import org.redisson.api.condition.Conditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
@Slf4j
public class RunnerListener implements CommandLineRunner {

    @Autowired
    private RedissonClient redisson;
    @Autowired
    private SocketIOServer socketIOServer;

    @Override
    public void run(String... args)  {
        socketIOServer.start();
        log.info("*************************（1）NettySocketIO服务已启动********************");
        RLiveObjectService service = redisson.getLiveObjectService();
        service.registerClass(RRoom.class);
        service.registerClass(RZhubo.class);
        service.registerClass(RUser.class);
        log.info("*************************（2）Redisson对象缓存成功************************");
        RLiveObjectService liveObjectService=redisson.getLiveObjectService();
        Collection<RRoom> roomCollection=liveObjectService.find(RRoom.class, Conditions.eq("status",0));
        roomCollection.forEach(rRoom -> {
            if(!StringUtils.isEmpty(rRoom.getImUrl())){
                socketIOServer.addNamespace(rRoom.getImUrl());
                log.info("主播{}的直播间，聊天室{}重建成功",rRoom.getRZhubo().getUsername(),rRoom.getImUrl());
            }
        });
        log.info("*************************（3）直播间聊天室加载缓存完成************************");

    }

}
