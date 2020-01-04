package com.eason.transfer.openapi.zb.im.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.eason.transfer.openapi.zb.api.zhubo.model.RUser;
import com.eason.transfer.openapi.zb.im.dto.ChatObject;
import com.eason.transfer.openapi.zb.im.dto.MsgTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class SystemIOListener{

    @Autowired
    private RedissonClient redisson;
    @Autowired
    private SocketIOServer socketIOServer;

    /**
     * 当客户端发起连接时调用
     */
    @OnConnect
    public void onConnect(SocketIOClient socketIOClient) {
        String roomId = socketIOClient.getHandshakeData().getSingleUrlParam("roomId");
        String userName = socketIOClient.getHandshakeData().getSingleUrlParam("userName");
        if (StringUtils.isNotBlank(userName)) {
            // 发送上线通知
            this.sendMsg(socketIOClient, null, new ChatObject("系统消息管理员", "系统公告：直播间提倡绿色直播！！！"));
        }

    }

    /**
     * 客户端断开连接时调用，刷新客户端信息
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient socketIOClient) {
        String roomId = socketIOClient.getHandshakeData().getSingleUrlParam("roomId");
        String userName = socketIOClient.getHandshakeData().getSingleUrlParam("userName");
        if (StringUtils.isNotBlank(userName)) {

            System.out.println("***********************System**********OnDisconnect***************");

            // 发送下线通知
            this.sendMsg(socketIOClient, null, new ChatObject("系统消息管理员", "系统公告：直播间提倡绿色直播！！！"));
        }

    }

    /**
     * sendMsg发送消息事件
     */
    @OnEvent("message")
    public void sendMsg(SocketIOClient socketIOClient, AckRequest ackRequest, ChatObject data) {
        socketIOClient.getNamespace().getBroadcastOperations().sendEvent("message", data);
    }


}

