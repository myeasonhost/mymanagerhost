package com.ds.money.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: jerry
 * @Description:
 * @Date: Create in 2018-06-16 16:30
 */

@ApiModel(value = "变更代理金额接口", description = "json")
public class AgentMoneyVo implements Serializable {
    private static final long serialVersionUID = 200000000000000000L;


    @ApiModelProperty(value = "必填，用户名",required = true)
    private String username;
    @ApiModelProperty(value = "必填，网站ID",required = true)
    private String siteId;

    @ApiModelProperty(value = "必填，代理级别",required = true)
    private String agentLevel;
    @ApiModelProperty(value = "必填，代理用户名",required = true)
    private String agentName;
    @ApiModelProperty(value = "必填，转账唯一标识",required = true)
    private String remitno;
    @ApiModelProperty(value = "必填，交易金额",required = true)
    private String remit;
    @ApiModelProperty(value = "必填，用户级别",required = true)
    private String userLevel;
    @ApiModelProperty(value = "必填，in 转入代理，out代理转出",required = true)
    private String agentTransType;

    @ApiModelProperty(value = "非必填，备注")
    private String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public String getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getRemitno() {
        return remitno;
    }

    public void setRemitno(String remitno) {
        this.remitno = remitno;
    }

    public String getRemit() {
        return remit;
    }

    public void setRemit(String remit) {
        this.remit = remit;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getAgentTransType() {
        return agentTransType;
    }

    public void setAgentTransType(String agentTransType) {
        this.agentTransType = agentTransType;
    }
}
