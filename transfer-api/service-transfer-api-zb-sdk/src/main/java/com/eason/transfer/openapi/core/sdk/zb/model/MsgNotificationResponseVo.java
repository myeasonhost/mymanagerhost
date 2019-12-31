package com.eason.transfer.openapi.core.sdk.zb.model;


import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("MsgNotificationResponseVo")
public class MsgNotificationResponseVo extends Response {
    private static final long serialVersionUID = 1L;
    private Integer id;		//公告消息ID
	private String title; 	//公告消息内容
    private String url; 	//点击事件
}
