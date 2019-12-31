package com.eason.transfer.openapi.zb.listener;

import com.corundumstudio.socketio.SocketIOServer;
import com.eason.transfer.openapi.zb.api.room.model.RRoom;
import com.eason.transfer.openapi.zb.api.zhubo.model.RUser;
import com.eason.transfer.openapi.zb.api.zhubo.model.RZhubo;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


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
        log.info("*************************NettySocketIO服务已启动************************");
        RLiveObjectService service = redisson.getLiveObjectService();
        service.registerClass(RRoom.class);
        service.registerClass(RZhubo.class);
        service.registerClass(RUser.class);

    }

}
