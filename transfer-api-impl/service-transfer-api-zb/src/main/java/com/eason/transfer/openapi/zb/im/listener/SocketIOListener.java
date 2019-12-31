package com.eason.transfer.openapi.zb.im.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.eason.transfer.openapi.zb.im.dto.ChatObject;
import com.eason.transfer.openapi.zb.im.dto.MsgTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SocketIOListener {

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private SocketIOServer socketIOServer;

    /**
     * 当客户端发起连接时调用
     */
    @OnConnect
    public void onConnect(SocketIOClient socketIOClient) {
        String userName = socketIOClient.getHandshakeData().getSingleUrlParam("userName");
        if (StringUtils.isNotBlank(userName)) {
            log.info("用户{}开启长连接通知, NettySocketSessionId: {}, NettySocketRemoteAddress: {}",
                    socketIOClient.getSessionId(), userName, socketIOClient.getRemoteAddress().toString());
            // 保存
//            clientMap.put(userName, socketIOClient.getSessionId());
            // 发送上线通知
            this.sendMsg(socketIOClient, null, new ChatObject(userName, MsgTypeEnum.ONLINE.getValue()));
        }

    }

    /**
     * 客户端断开连接时调用，刷新客户端信息
     */
    @OnDisconnect
    public void onDisConnect(SocketIOClient socketIOClient) {
        String userName = socketIOClient.getHandshakeData().getSingleUrlParam("userName");
        if (StringUtils.isNotBlank(userName)) {
            log.info("用户{}断开长连接通知, NettySocketSessionId: {}, NettySocketRemoteAddress: {}",
                    socketIOClient.getSessionId(), userName, socketIOClient.getRemoteAddress().toString());
            // 移除
//            clientMap.remove(userName);
            // 发送下线通知
            this.sendMsg(socketIOClient, null, new ChatObject(userName, MsgTypeEnum.OFFLINE.getValue()));
        }

    }

    /**
     * sendMsg发送消息事件
     */
    @OnEvent("message")
    public void sendMsg(SocketIOClient socketIOClient, AckRequest ackRequest, ChatObject data) {
        socketIOClient.getNamespace().getBroadcastOperations().sendEvent("message", data);

//        if (messageDto != null) {
//             全部发送
//            clientMap.forEach((key, value) -> {
//                if (value != null) {
//                    socketIOServer.getClient(value).sendEvent("receiveMsg", data);
//                }
//            });
//        }
    }
}
