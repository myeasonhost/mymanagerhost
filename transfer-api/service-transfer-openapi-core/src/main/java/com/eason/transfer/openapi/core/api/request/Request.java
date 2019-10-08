package com.eason.transfer.openapi.core.api.request;

import com.eason.transfer.openapi.core.annotation.MyConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer language = 1; //语言设置默认1 （1：中文、2：英文、3：韩文）

    private String ip;
    @NotEmpty(message = "方法名不能为空")
    private String method;
    @NotEmpty(message = "应用签名不能为空")
    @MyConstraint(message = "应用签名不正确")
    private String appKey;
    @NotEmpty(message = "版本不能为空")
    private String version;

    @MyConstraint(message = "应用签名不正确")
    private String sessionKey;
    private String appSecret;


}
