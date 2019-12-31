package com.eason.transfer.openapi.zb.im.listener;


import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;

public class SocketAuthorListener implements AuthorizationListener {
    @Override
    public boolean isAuthorized(HandshakeData handshakeData) {
        //http://localhost:8081?username=test&password=test
        //例如果使用上面的链接进行connect，可以使用如下代码获取用户密码信息，本文不做身份验证
//              String username = data.getSingleUrlParam("username");
//              String password = data.getSingleUrlParam("password");
        return true;
    }
}
