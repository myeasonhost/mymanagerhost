package com.eason.transfer.openapi.core.client.user.model;


import com.eason.transfer.openapi.core.api.request.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoGetRequest extends Request {

    private static final long serialVersionUID = 1L;

    private String username;   // 用户名
    private String token;   //用户登录token
}
