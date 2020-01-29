package com.ds.money.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: jerry
 * @Description:
 * @Date: Create in 2018-06-16 16:04
 */

@ApiModel(value = "查询现金流接口参数", description = "json")
public class TransMoneyInfoVo  implements Serializable {
    private static final long serialVersionUID = 200000300000000000L;

    @ApiModelProperty(value = "必填，流水号",required = true)
    private String remitno;
    @ApiModelProperty(value = "必填，用户名",required = true)
    private String username;
    @ApiModelProperty(value = "必填，网站ID",required = true)
    private String siteId;
    @ApiModelProperty(value = "非必填，唯一标识")
    private String hashCode;

    public String getRemitno() {
        return remitno;
    }

    public void setRemitno(String remitno) {
        this.remitno = remitno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
