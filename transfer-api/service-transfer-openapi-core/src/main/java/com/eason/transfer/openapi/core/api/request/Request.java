package com.eason.transfer.openapi.core.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer language = 1; //语言设置默认1 （1：中文、2：英文、3：韩文）

    private String ip;
    private String method;
    private String appKey;
    private String version;
    private String sessionKey;
    private String appSecret;
    private String userId;
    private String userType;
}
