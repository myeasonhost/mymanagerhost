package com.eason.transfer.openapi.core.sdk.index.user.model;


import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("UserInfoGetResponse")
public class UserInfoGetResponse extends Response {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String username;   // 用户名
    private Double mainMoney;  //用户主余额
    private String realName; //真实姓名
    private String phoneNum; //电话号码
    private String email;    //电子邮箱
    private String wechat;   //微信号
    private String invite;   //推荐人
    private String token;   //用户token
    private Byte status;
    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy;
}
