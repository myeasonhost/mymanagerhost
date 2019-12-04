package com.eason.transfer.openapi.core.sdk.user.vo;

import com.eason.transfer.openapi.core.common.request.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCodeRequest extends Request {

    private Integer codeType;	//验证码类型1为注册 2为忘记密码
    private String phone;		//手机号

}
