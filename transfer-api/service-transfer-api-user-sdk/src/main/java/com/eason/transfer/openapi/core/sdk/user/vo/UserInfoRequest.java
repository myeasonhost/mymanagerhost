package com.eason.transfer.openapi.core.sdk.user.vo;

import com.eason.transfer.openapi.core.common.request.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequest extends Request {

    private String nickname;
    private String sex;
    private String birthday;
    private String signature;
    private String avatar;

}
