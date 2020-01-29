package com.ds.money.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: jerry
 * @Description:
 * @Date: Create in 2018-06-16 16:19
 */

@ApiModel(value = "创建用户或修改接口参数", description = "json")
public class MemberDataVo implements Serializable {
    private static final long serialVersionUID = 300000000000000000L;

    @ApiModelProperty(value = "必填，用户名",required = true)
    private String username;
    @ApiModelProperty(value = "必填，网站ID",required = true)
    private String siteId;
    @ApiModelProperty(value = "必填，代理",required = true)
    private String agents;
    @ApiModelProperty(value = "必填，-",required = true)
    private String world;
    @ApiModelProperty(value = "必填，-",required = true)
    private String corprator;
    @ApiModelProperty(value = "必填，-",required = true)
    private String superior;
    @ApiModelProperty(value = "必填，-",required = true)
    private String company;


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

    public String getAgents() {
        return agents;
    }

    public void setAgents(String agents) {
        this.agents = agents;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getCorprator() {
        return corprator;
    }

    public void setCorprator(String corprator) {
        this.corprator = corprator;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
