package com.eason.transfer.openapi.core.sdk.user.model;

import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("UserDetailResponse")
public class UserDetailResponse extends Response {

    private Integer userId;	//用户id
    private String username;
    private String nickname;
    private String phone;
    private String birthday;
    private String signature;
    private String avatar;
    private String sex;
    private Byte status;
    private Date createdAt;
    private Date updatedAt;

}
