package com.ds.money.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jerry
 * @Description:
 * @Date: Create in 2018-06-16 15:20
 */

@ApiModel(value = "转账结果接口参数", description = "json")
public class CheckTransMoneyVo extends BaseVo{

    @ApiModelProperty(value = "必填，流水号",required = true)
    private String remitno;


    public String getRemitno() {
        return remitno;
    }

    public void setRemitno(String remitno) {
        this.remitno = remitno;
    }
}
