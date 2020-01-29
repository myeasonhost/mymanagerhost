package com.ds.money.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jerry
 * @Description:
 * @Date: Create in 2018-06-16 16:12
 */

@ApiModel(value = "修改现金流备注接口参数", description = "json")
public class MoneyLogVo extends TransMoneyInfoVo {

    @ApiModelProperty(value = "必填，备注",required = true)
    private String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
