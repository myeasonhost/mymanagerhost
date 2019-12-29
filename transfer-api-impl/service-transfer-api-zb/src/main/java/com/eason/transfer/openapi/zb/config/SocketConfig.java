package com.eason.transfer.openapi.zb.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "socket")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocketConfig {
    private Integer port;

    private Integer workCount; //连接数大小

    private Boolean allowCustomRequests; //允许客户请求

    private Integer upgradeTimeout; //协议升级超时时间(毫秒)，默认10秒，HTTP握手升级为ws协议超时时间

    private Integer pingTimeout; //Ping消息超时时间(毫秒)，默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件

    private Integer pingInterval; //Ping消息间隔(毫秒)，默认25秒。客户端向服务器发送一条心跳消息间隔

    private Integer maxHttpContentLength; //设置HTTP交互最大内容长度

    private Integer maxFramePayloadLength;  //设置HTTP交互最大内容长度
}
