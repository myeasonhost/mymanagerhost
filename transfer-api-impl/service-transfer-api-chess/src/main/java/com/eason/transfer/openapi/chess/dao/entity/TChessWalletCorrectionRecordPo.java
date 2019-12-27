package com.eason.transfer.openapi.chess.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TChessWalletCorrectionRecordPo {
    private String recordId;

    private String siteId;

    private String account;

    private String gameKindA;

    private String optType;

    private String gameKindB;

    private String optAmount;

    private BigDecimal balance;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private String statusLog;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getGameKindA() {
        return gameKindA;
    }

    public void setGameKindA(String gameKindA) {
        this.gameKindA = gameKindA;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getGameKindB() {
        return gameKindB;
    }

    public void setGameKindB(String gameKindB) {
        this.gameKindB = gameKindB;
    }

    public String getOptAmount() {
        return optAmount;
    }

    public void setOptAmount(String optAmount) {
        this.optAmount = optAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public String getStatusLog() {
        return statusLog;
    }

    public void setStatusLog(String statusLog) {
        this.statusLog = statusLog;
    }
}