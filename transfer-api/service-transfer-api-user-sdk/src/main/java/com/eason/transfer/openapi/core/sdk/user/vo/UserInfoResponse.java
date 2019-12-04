package com.eason.transfer.openapi.core.sdk.user.vo;

import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("UserInfoResponse")
public class UserInfoResponse extends Response {

    private Integer userId;	//用户id
    private String result;	//返回信息

}
