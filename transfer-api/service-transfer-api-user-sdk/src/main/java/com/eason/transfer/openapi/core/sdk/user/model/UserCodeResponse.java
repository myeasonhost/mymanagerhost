package com.eason.transfer.openapi.core.sdk.user.model;

import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("UserCodeResponse")
public class UserCodeResponse extends Response {
    private String code;	//用户id
    private String result;	//返回信息
}
