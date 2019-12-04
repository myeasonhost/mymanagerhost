package com.eason.transfer.openapi.core.sdk.index.user.model;


import com.eason.transfer.openapi.core.common.request.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequest extends Request {

    private static final long serialVersionUID = 1L;

    private String username;   // 用户名
    private String password;   //密码
    private String surePassword;  //密码
    private String realName; //真实姓名
    private String phoneNum; //电话号码
    private String email;    //电子邮箱
    private String wechat;   //微信号
    private String invite;   //推荐人

}
