package com.eason.transfer.openapi.core.client.chess.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TReportDayUserPo {
    private Integer id;

    private String siteId;

    private String username;

    private String gameKind;

    private Integer betCounts;

    private BigDecimal betAmounts;

    private BigDecimal validAmounts;

    private BigDecimal payAmounts;

    private Date betTime;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGameKind() {
        return gameKind;
    }

    public void setGameKind(String gameKind) {
        this.gameKind = gameKind;
    }

    public Integer getBetCounts() {
        return betCounts;
    }

    public void setBetCounts(Integer betCounts) {
        this.betCounts = betCounts;
    }

    public BigDecimal getBetAmounts() {
        return betAmounts;
    }

    public void setBetAmounts(BigDecimal betAmounts) {
        this.betAmounts = betAmounts;
    }

    public BigDecimal getValidAmounts() {
        return validAmounts;
    }

    public void setValidAmounts(BigDecimal validAmounts) {
        this.validAmounts = validAmounts;
    }

    public BigDecimal getPayAmounts() {
        return payAmounts;
    }

    public void setPayAmounts(BigDecimal payAmounts) {
        this.payAmounts = payAmounts;
    }

    public Date getBetTime() {
        return betTime;
    }

    public void setBetTime(Date betTime) {
        this.betTime = betTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}