package com.eason.transfer.openapi.chess.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_report_day_user")
public class TReportDayUserPo implements Serializable {
    private int id;
    private String siteId;
    private String username;
    private Integer betCounts;
    private BigDecimal betAmounts;
    private BigDecimal validAmounts;
    private BigDecimal payAmounts;
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
    @Column(name = "bet_counts")
    public Integer getBetCounts() {
        return betCounts;
    }

    public void setBetCounts(Integer betCounts) {
        this.betCounts = betCounts;
    }

    @Basic
    @Column(name = "bet_amounts")
    public BigDecimal getBetAmounts() {
        return betAmounts;
    }

    public void setBetAmounts(BigDecimal betAmounts) {
        this.betAmounts = betAmounts;
    }

    @Basic
    @Column(name = "valid_amounts")
    public BigDecimal getValidAmounts() {
        return validAmounts;
    }

    public void setValidAmounts(BigDecimal validAmounts) {
        this.validAmounts = validAmounts;
    }

    @Basic
    @Column(name = "pay_amounts")
    public BigDecimal getPayAmounts() {
        return payAmounts;
    }

    public void setPayAmounts(BigDecimal payAmounts) {
        this.payAmounts = payAmounts;
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
        TReportDayUserPo that = (TReportDayUserPo) o;
        return id == that.id &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(betCounts, that.betCounts) &&
                Objects.equals(betAmounts, that.betAmounts) &&
                Objects.equals(validAmounts, that.validAmounts) &&
                Objects.equals(payAmounts, that.payAmounts) &&
                Objects.equals(gameKind, that.gameKind) &&
                Objects.equals(gameType, that.gameType) &&
                Objects.equals(betTime, that.betTime) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, siteId, username, betCounts, betAmounts, validAmounts, payAmounts, gameKind, gameType, betTime, createTime, updateTime);
    }
}
