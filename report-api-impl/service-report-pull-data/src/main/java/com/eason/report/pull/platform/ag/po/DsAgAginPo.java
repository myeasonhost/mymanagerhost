package com.eason.report.pull.platform.ag.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ds_ag_agin")
public class DsAgAginPo implements Serializable {
    private String billNo;
    private String agentId;
    private Integer siteId;
    private String username;
    private String playName;
    private String gameCode;
    private BigDecimal netAmount;
    private Byte winLossType;
    private Date betTime;
    private String gameType;
    private BigDecimal betAmount;
    private BigDecimal validBetAmount;
    private String flag;
    private String playType;
    private String currency;
    private String tableCode;
    private Date recalcuTime;
    private BigDecimal beforeCredit;
    private String betIp;
    private String platformType;
    private String deviceType;
    private String remark;
    private Date createTime;
    private Date updateTime;

    @Id
    @Column(name = "bill_no")
    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
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
    @Column(name = "siteId")
    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
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
    @Column(name = "play_name")
    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    @Basic
    @Column(name = "game_code")
    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    @Basic
    @Column(name = "net_amount")
    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
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
    @Column(name = "bet_time")
    public Date getBetTime() {
        return betTime;
    }

    public void setBetTime(Date betTime) {
        this.betTime = betTime;
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
    @Column(name = "bet_amount")
    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    @Basic
    @Column(name = "valid_bet_amount")
    public BigDecimal getValidBetAmount() {
        return validBetAmount;
    }

    public void setValidBetAmount(BigDecimal validBetAmount) {
        this.validBetAmount = validBetAmount;
    }

    @Basic
    @Column(name = "flag")
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "play_type")
    public String getPlayType() {
        return playType;
    }

    public void setPlayType(String playType) {
        this.playType = playType;
    }

    @Basic
    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "table_code")
    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

    @Basic
    @Column(name = "recalcu_time")
    public Date getRecalcuTime() {
        return recalcuTime;
    }

    public void setRecalcuTime(Date recalcuTime) {
        this.recalcuTime = recalcuTime;
    }

    @Basic
    @Column(name = "before_credit")
    public BigDecimal getBeforeCredit() {
        return beforeCredit;
    }

    public void setBeforeCredit(BigDecimal beforeCredit) {
        this.beforeCredit = beforeCredit;
    }

    @Basic
    @Column(name = "bet_ip")
    public String getBetIp() {
        return betIp;
    }

    public void setBetIp(String betIp) {
        this.betIp = betIp;
    }

    @Basic
    @Column(name = "platform_type")
    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    @Basic
    @Column(name = "device_type")
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DsAgAginPo that = (DsAgAginPo) o;
        return Objects.equals(billNo, that.billNo) &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(playName, that.playName) &&
                Objects.equals(gameCode, that.gameCode) &&
                Objects.equals(netAmount, that.netAmount) &&
                Objects.equals(winLossType, that.winLossType) &&
                Objects.equals(betTime, that.betTime) &&
                Objects.equals(gameType, that.gameType) &&
                Objects.equals(betAmount, that.betAmount) &&
                Objects.equals(validBetAmount, that.validBetAmount) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(playType, that.playType) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(tableCode, that.tableCode) &&
                Objects.equals(recalcuTime, that.recalcuTime) &&
                Objects.equals(beforeCredit, that.beforeCredit) &&
                Objects.equals(betIp, that.betIp) &&
                Objects.equals(platformType, that.platformType) &&
                Objects.equals(deviceType, that.deviceType) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billNo, agentId, siteId, username, playName, gameCode, netAmount, winLossType, betTime, gameType, betAmount, validBetAmount, flag, playType, currency, tableCode, recalcuTime, beforeCredit, betIp, platformType, deviceType, remark, createTime, updateTime);
    }
}
