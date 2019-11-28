package com.eason.transfer.openapi.user.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_audit_total")
public class TAuditTotalPo {
    private int id;
    private String siteId;
    private String username;
    private String orderNo;
    private BigDecimal betAmount;
    private BigDecimal validAmount;
    private BigDecimal payAmount;
    private Byte winLossType;
    private String gameKind;
    private String gameType;
    private Timestamp betTime;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "site_id")
    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "order_no")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "bet_amount")
    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    @Basic
    @Column(name = "valid_amount")
    public BigDecimal getValidAmount() {
        return validAmount;
    }

    public void setValidAmount(BigDecimal validAmount) {
        this.validAmount = validAmount;
    }

    @Basic
    @Column(name = "pay_amount")
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    @Basic
    @Column(name = "win_loss_type")
    public Byte getWinLossType() {
        return winLossType;
    }

    public void setWinLossType(Byte winLossType) {
        this.winLossType = winLossType;
    }

    @Basic
    @Column(name = "game_kind")
    public String getGameKind() {
        return gameKind;
    }

    public void setGameKind(String gameKind) {
        this.gameKind = gameKind;
    }

    @Basic
    @Column(name = "game_type")
    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    @Basic
    @Column(name = "bet_time")
    public Timestamp getBetTime() {
        return betTime;
    }

    public void setBetTime(Timestamp betTime) {
        this.betTime = betTime;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TAuditTotalPo that = (TAuditTotalPo) o;
        return id == that.id &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(orderNo, that.orderNo) &&
                Objects.equals(betAmount, that.betAmount) &&
                Objects.equals(validAmount, that.validAmount) &&
                Objects.equals(payAmount, that.payAmount) &&
                Objects.equals(winLossType, that.winLossType) &&
                Objects.equals(gameKind, that.gameKind) &&
                Objects.equals(gameType, that.gameType) &&
                Objects.equals(betTime, that.betTime) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, siteId, username, orderNo, betAmount, validAmount, payAmount, winLossType, gameKind, gameType, betTime, createTime, updateTime);
    }
}
