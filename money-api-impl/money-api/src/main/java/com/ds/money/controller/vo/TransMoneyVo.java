package com.ds.money.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jerry
 * @Description:
 * @Date: Create in 2018-06-16 13:39
 */
@ApiModel(value = "用户转账参数", description = "json")
public class TransMoneyVo extends BaseVo {
    @ApiModelProperty(value = "必填，流水号",required = true)
    private String remitno;
    @ApiModelProperty(value = "必填，转入转出标识",required = true)
    private String transType;
    @ApiModelProperty(value = "必填，金额",required = true)
    private String remit;
    @ApiModelProperty(value = "必填，注单是否取消 1：取消    0否",required = true)
    private String wagerCancel;
    @ApiModelProperty(value = "必填，转账说明 例如：2003：注单撤销 ，10001：公司入款 等等",required = true)
    private String fromKeyType;
    @ApiModelProperty(value = "必填，备注",required = true)
    private String memo;
    @ApiModelProperty(value = "非必填，游戏类型",required = true)
    private String gameType;
    @ApiModelProperty(value = "非必填，游戏ID",required = true)
    private String gameId;


    public String getRemitno() {
        return remitno;
    }

    public void setRemitno(String remitno) {
        this.remitno = remitno;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getRemit() {
        return remit;
    }

    public void setRemit(String remit) {
        this.remit = remit;
    }

    public String getWagerCancel() {
        return wagerCancel;
    }

    public void setWagerCancel(String wagerCancel) {
        this.wagerCancel = wagerCancel;
    }

    public String getFromKeyType() {
        return fromKeyType;
    }

    public void setFromKeyType(String fromKeyType) {
        this.fromKeyType = fromKeyType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
