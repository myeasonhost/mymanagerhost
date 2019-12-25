package com.eason.transfer.openapi.core.sdk.user.model;

import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("LoginResponse")
public class LoginResponse extends Response {

    private Integer userId;         //用户ID
    private String username;        //登陆用户名
    private String nickname;        //用户昵称
    private String avatar;          //用户头像
    private String  token;          //用户token

}
