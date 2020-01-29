package com.ds.money.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: jerry
 * @Description:
 * @Date: Create in 2018-06-16 13:15
 */


public class BaseVo implements Serializable{

    private static final long serialVersionUID = 100000000000000000L;

    @ApiModelProperty(value = "必填，用户名",required = true)
    private String username;
    @ApiModelProperty(value = "必填，客户端key",required = true)
    private String fromKey;
    @ApiModelProperty(value = "必填，秘钥",required = true)
    private String key;
    @ApiModelProperty(value = "必填，网站ID",required = true)
    private String siteId;
    @ApiModelProperty(value = "非必填，如果该字段不为空时，一定要在钱包中心配置，该hashCode 对应的网站ID")
    private String hashCode;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFromKey() {
        return fromKey;
    }

    public void setFromKey(String fromKey) {
        this.fromKey = fromKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }
}
