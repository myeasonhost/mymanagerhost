package com.eason.transfer.openapi.core.sdk.user.vo;

import com.eason.transfer.openapi.core.common.request.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest extends Request {
    private String phone;		//手机号
    private String password;	//密码 md5加密
    private String validateCode; //验证码

}
