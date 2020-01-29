package com.ds.money.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jerry
 * @Description:
 * @Date: Create in 2018-06-16 15:27
 */

@ApiModel(value = "查询用户代理现金流记录接口参数", description = "json")
public class MemberMoneyLogVo extends BaseVo{
    private static final long serialVersionUID = 400000000000000000L;

    @ApiModelProperty(value = "非必填，流水号")
    private String remitno;
    @ApiModelProperty(value = "必填，开始时间",required = true)
    private String beginTime;
    @ApiModelProperty(value = "必填，结束时间",required = true)
    private String endTime;
    @ApiModelProperty(value = "非必填，分页")
    private String page;
    @ApiModelProperty(value = "非必填，分页size")
    private String pageSize;
    @ApiModelProperty(value = "必填，转账说明 例如：2003：注单撤销 ，10001：公司入款 等等")
    private String fromKeyType;
    @ApiModelProperty(value = "非必填，代理级别")
    private String agentLevel;
    @ApiModelProperty(value = "非必填，代理名字")
    private String agentName;
    @ApiModelProperty(value = "非必填，否是统计现金流",notes = "否是统计现金流 ，当改字段不为空时，fromKeyType 必填")
    private String fromKeyTypeIsTotal;
    @ApiModelProperty(value = "非必填，是否查询用户的详细信息")
    private String userInfoIsDetail;


    public String getRemitno() {
        return remitno;
    }

    public void setRemitno(String remitno) {
        this.remitno = remitno;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getFromKeyType() {
        return fromKeyType;
    }

    public void setFromKeyType(String fromKeyType) {
        this.fromKeyType = fromKeyType;
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

    public String getFromKeyTypeIsTotal() {
        return fromKeyTypeIsTotal;
    }

    public void setFromKeyTypeIsTotal(String fromKeyTypeIsTotal) {
        this.fromKeyTypeIsTotal = fromKeyTypeIsTotal;
    }

    public String getUserInfoIsDetail() {
        return userInfoIsDetail;
    }

    public void setUserInfoIsDetail(String userInfoIsDetail) {
        this.userInfoIsDetail = userInfoIsDetail;
    }
}
