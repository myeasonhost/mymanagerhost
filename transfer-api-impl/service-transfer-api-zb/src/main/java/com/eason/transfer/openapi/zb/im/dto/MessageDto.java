package com.eason.transfer.openapi.zb.im.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto implements Serializable {

    /**
     * 源客户端用户名
     */
    private String sourceUserName;

    /**
     * 目标客户端用户名
     */
    private String targetUserName;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 消息内容
     */
    private String msgContent;
}
