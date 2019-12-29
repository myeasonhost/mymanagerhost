package com.eason.transfer.openapi.zb;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.corundumstudio.socketio.store.RedissonStoreFactory;
import com.eason.transfer.openapi.zb.config.SocketConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = {"com.eason.transfer.openapi.zb.api"})
public class ServiceTransferApiZbApplication {
    @Autowired
    private SocketConfig socketConfig;

    @Autowired
    private RedissonClient redissonClient;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setPort(socketConfig.getPort());
        // 连接数大小
        config.setWorkerThreads(socketConfig.getWorkCount());
        // 允许客户请求
        config.setAllowCustomRequests(socketConfig.getAllowCustomRequests());
        // 协议升级超时时间(毫秒)，默认10秒，HTTP握手升级为ws协议超时时间
        config.setUpgradeTimeout(socketConfig.getUpgradeTimeout());
        // Ping消息超时时间(毫秒)，默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件
        config.setPingTimeout(socketConfig.getPingTimeout());
        // Ping消息间隔(毫秒)，默认25秒。客户端向服务器发送一条心跳消息间隔
        config.setPingInterval(socketConfig.getPingInterval());
        // 设置HTTP交互最大内容长度
        config.setMaxHttpContentLength(socketConfig.getMaxHttpContentLength());
        // 设置最大每帧处理数据的长度，防止他人利用大数据来攻击服务器
        config.setMaxFramePayloadLength(socketConfig.getMaxFramePayloadLength());
        //该处可以用来进行身份验证
        config.setAuthorizationListener(new AuthorizationListener() {
            @Override
            public boolean isAuthorized(HandshakeData data) {
                //http://localhost:8081?username=test&password=test
                //例如果使用上面的链接进行connect，可以使用如下代码获取用户密码信息，本文不做身份验证
//              String username = data.getSingleUrlParam("username");
//              String password = data.getSingleUrlParam("password");
                return true;
            }
        });
        config.setStoreFactory(new RedissonStoreFactory(redissonClient));
        final SocketIOServer server = new SocketIOServer(config);

        return server;
    }

    /**
     * 开启SocketIOServer注解支持
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }


    public static void main(String[] args) {
        SpringApplication.run(ServiceTransferApiZbApplication.class, args);
    }

}
