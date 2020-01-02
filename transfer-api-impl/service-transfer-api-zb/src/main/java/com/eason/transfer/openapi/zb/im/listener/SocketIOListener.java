package com.eason.transfer.openapi.zb.im.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.eason.transfer.openapi.zb.api.room.model.RRoom;
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
public class SocketIOListener implements ConnectListener,DisconnectListener,DataListener<ChatObject> {

    @Autowired
    private RedissonClient redisson;
    @Autowired
    private SocketIOServer socketIOServer;

    /**
     * 当客户端发起连接时调用
     */
//    @OnConnect
    public void onConnect(SocketIOClient socketIOClient) {
        String roomId = socketIOClient.getHandshakeData().getSingleUrlParam("roomId");
        String userName = socketIOClient.getHandshakeData().getSingleUrlParam("userName");
        if (StringUtils.isNotBlank(userName)) {
            log.info("用户{}开启长连接通知, NettySocketSessionId: {}, NettySocketRemoteAddress: {}",
                    socketIOClient.getSessionId(), userName, socketIOClient.getRemoteAddress().toString());
            RRoom rRoom=new RRoom();
            rRoom.setId(Long.parseLong(roomId));
            redisson.getLiveObjectService().merge(rRoom);
            RUser rUser=new RUser();
            rUser.setUsername(userName);
            rUser.setSessionId(socketIOClient.getSessionId().toString());
            redisson.getAtomicLong("viewCount_"+rRoom.getId()).incrementAndGet();
            rRoom.getUserList().add(rUser);

            // 发送上线通知
            this.sendMsg(socketIOClient, null, new ChatObject(userName, MsgTypeEnum.ONLINE.getValue()));
        }

    }

    /**
     * 客户端断开连接时调用，刷新客户端信息
     */
//    @OnDisconnect
    public void onDisconnect(SocketIOClient socketIOClient) {
        String roomId = socketIOClient.getHandshakeData().getSingleUrlParam("roomId");
        String userName = socketIOClient.getHandshakeData().getSingleUrlParam("userName");
        if (StringUtils.isNotBlank(userName)) {
            log.info("用户{}断开长连接通知, NettySocketSessionId: {}, NettySocketRemoteAddress: {}",
                    socketIOClient.getSessionId(), userName, socketIOClient.getRemoteAddress().toString());
            RRoom rRoom=new RRoom();
            rRoom.setId(Long.parseLong(roomId));
            redisson.getLiveObjectService().merge(rRoom);
            RUser rUser=new RUser();
            rUser.setUsername(userName);
            rUser.setSessionId(socketIOClient.getSessionId().toString());
            rRoom.getUserList().remove(rUser);
            // 发送下线通知
            this.sendMsg(socketIOClient, null, new ChatObject(userName, MsgTypeEnum.OFFLINE.getValue()));
        }

    }

    /**
     * sendMsg发送消息事件
     */
//    @OnEvent("message")
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

    @Override
    public void onData(SocketIOClient socketIOClient, ChatObject data, AckRequest ackRequest) throws Exception {
        socketIOClient.getNamespace().getBroadcastOperations().sendEvent("message", data);
    }

}
