package com.ds.money.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author: jerry
 * @Description:
 * @Date: Create in 2018-06-16 15:53
 */
@ApiModel(value = "按天统计现金流日志接口参数", description = "json")
public class MoneyLogByDateVo implements Serializable {
    private static final long serialVersionUID = 230000000000000000L;

    @ApiModelProperty(value = "非必填，开始时间")
    private String beginTime;
    @ApiModelProperty(value = "非必填，结束时间")
    private String endTime;
    @ApiModelProperty(value = "必填，网站ID",required = true)
    private String siteId;
    @ApiModelProperty(value = "非必填，转账说明 例如：2003：注单撤销 ，10001：公司入款 等等")
    private String fromKeyType;


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

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getFromKeyType() {
        return fromKeyType;
    }

    public void setFromKeyType(String fromKeyType) {
        this.fromKeyType = fromKeyType;
    }
}
