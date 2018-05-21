package com.eason.socket.im.service;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.eason.socket.im.po.Room;
import com.eason.socket.im.protocol.MessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/im")
public class IMServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(IMServiceImpl.class);
    private final SocketIOServer server;

    public IMServiceImpl(SocketIOServer server) {
        this.server = server;
    }

    @RequestMapping(value = "/createRoom", method = RequestMethod.GET)
    public void createRoom(Room room) {
        SocketIONamespace chatNamespace=server.addNamespace("/"+room.getRoomName());
        MessageInfo sendData = new MessageInfo();
        sendData.setMsgContent("聊天室创建成功");
        System.out.println("--聊天室创建成功--");
        chatNamespace.getBroadcastOperations().sendEvent("sendMsg",sendData);
    }

    @RequestMapping(value = "/destoryRoom", method = RequestMethod.GET)
    public void destoryRoom(Room room) {
        SocketIONamespace chatNamespace=server.getNamespace("/"+room.getRoomName());
        MessageInfo sendData = new MessageInfo();
        sendData.setMsgContent("聊天室已经关闭");
        System.out.println("--聊天室已经关闭--");
        chatNamespace.getBroadcastOperations().sendEvent("sendMsg",sendData);
        server.removeNamespace("/"+room.getRoomName());
    }


}
