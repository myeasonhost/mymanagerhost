package com.eason.report.pull.core.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ds_mg_game")
public class DsMgGamePo implements Serializable {
    private String colId;
    private Integer siteId;
    private String userName;
    private String agentId;
    private String mbrId;
    private String mbrCode;
    private String transId;
    private Integer winLossType;
    private Integer gameId;
    private String transType;
    private BigDecimal amnt;
    private BigDecimal clrngAmnt;
    private BigDecimal balance;
    private String mgsGameId;
    private String mgsActionId;
    private Date transTime;
    private Integer refTransId;
    private String refTransType;
    private Date createTime;
    private Date lastUpdateTime;
    private String keyB;
    private String memo;

    @Id
    @Column(name = "colId")
    public String getColId() {
        return colId;
    }

    public void setColId(String colId) {
        this.colId = colId;
    }

    @Basic
    @Column(name = "siteId")
    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "agentId")
    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    @Basic
    @Column(name = "mbrId")
    public String getMbrId() {
        return mbrId;
    }

    public void setMbrId(String mbrId) {
        this.mbrId = mbrId;
    }

    @Basic
    @Column(name = "mbrCode")
    public String getMbrCode() {
        return mbrCode;
    }

    public void setMbrCode(String mbrCode) {
        this.mbrCode = mbrCode;
    }

    @Basic
    @Column(name = "transId")
    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    @Basic
    @Column(name = "win_loss_type")
    public Integer getWinLossType() {
        return winLossType;
    }

    public void setWinLossType(Integer winLossType) {
        this.winLossType = winLossType;
    }

    @Basic
    @Column(name = "gameId")
    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    @Basic
    @Column(name = "transType")
    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    @Basic
    @Column(name = "amnt")
    public BigDecimal getAmnt() {
        return amnt;
    }

    public void setAmnt(BigDecimal amnt) {
        this.amnt = amnt;
    }

    @Basic
    @Column(name = "clrngAmnt")
    public BigDecimal getClrngAmnt() {
        return clrngAmnt;
    }

    public void setClrngAmnt(BigDecimal clrngAmnt) {
        this.clrngAmnt = clrngAmnt;
    }

    @Basic
    @Column(name = "balance")
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "mgsGameId")
    public String getMgsGameId() {
        return mgsGameId;
    }

    public void setMgsGameId(String mgsGameId) {
        this.mgsGameId = mgsGameId;
    }

    @Basic
    @Column(name = "mgsActionId")
    public String getMgsActionId() {
        return mgsActionId;
    }

    public void setMgsActionId(String mgsActionId) {
        this.mgsActionId = mgsActionId;
    }

    @Basic
    @Column(name = "transTime")
    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    @Basic
    @Column(name = "refTransId")
    public Integer getRefTransId() {
        return refTransId;
    }

    public void setRefTransId(Integer refTransId) {
        this.refTransId = refTransId;
    }

    @Basic
    @Column(name = "refTransType")
    public String getRefTransType() {
        return refTransType;
    }

    public void setRefTransType(String refTransType) {
        this.refTransType = refTransType;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "last_update_time")
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Basic
    @Column(name = "keyB")
    public String getKeyB() {
        return keyB;
    }

    public void setKeyB(String keyB) {
        this.keyB = keyB;
    }

    @Basic
    @Column(name = "memo")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DsMgGamePo that = (DsMgGamePo) o;
        return  Objects.equals(siteId, that.siteId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(colId, that.colId) &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(mbrId, that.mbrId) &&
                Objects.equals(mbrCode, that.mbrCode) &&
                Objects.equals(transId, that.transId) &&
                Objects.equals(winLossType, that.winLossType) &&
                Objects.equals(gameId, that.gameId) &&
                Objects.equals(transType, that.transType) &&
                Objects.equals(amnt, that.amnt) &&
                Objects.equals(clrngAmnt, that.clrngAmnt) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(mgsGameId, that.mgsGameId) &&
                Objects.equals(mgsActionId, that.mgsActionId) &&
                Objects.equals(transTime, that.transTime) &&
                Objects.equals(refTransId, that.refTransId) &&
                Objects.equals(refTransType, that.refTransType) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(lastUpdateTime, that.lastUpdateTime) &&
                Objects.equals(keyB, that.keyB) &&
                Objects.equals(memo, that.memo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(siteId, userName, colId, agentId, mbrId, mbrCode, transId, winLossType, gameId, transType, amnt, clrngAmnt, balance, mgsGameId, mgsActionId, transTime, refTransId, refTransType, createTime, lastUpdateTime, keyB, memo);
    }
}
