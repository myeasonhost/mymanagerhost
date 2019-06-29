package com.eason.report.pull.platform.ag.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ds_ag_xin")
public class DsAgXinPo implements Serializable {
    private String billNo;
    private String agentId;
    private Integer siteId;
    private String username;
    private String playerName;
    private Date billTime;
    private Date reckonTime;
    private String slotType;
    private String currency;
    private String gameType;
    private String betIp;
    private BigDecimal account;
    private BigDecimal cusAccount;
    private BigDecimal validAccount;
    private BigDecimal accountBase;
    private BigDecimal accountBonus;
    private BigDecimal cusAccountBase;
    private BigDecimal cusAccountBonus;
    private BigDecimal srcAmount;
    private BigDecimal dstAmount;
    private Byte winLossType;
    private String flag;
    private String platformType;
    private String deviceType;
    private String exttxid;
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
    @Column(name = "player_name")
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Basic
    @Column(name = "bill_time")
    public Date getBillTime() {
        return billTime;
    }

    public void setBillTime(Date billTime) {
        this.billTime = billTime;
    }

    @Basic
    @Column(name = "reckon_time")
    public Date getReckonTime() {
        return reckonTime;
    }

    public void setReckonTime(Date reckonTime) {
        this.reckonTime = reckonTime;
    }

    @Basic
    @Column(name = "slot_type")
    public String getSlotType() {
        return slotType;
    }

    public void setSlotType(String slotType) {
        this.slotType = slotType;
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
    @Column(name = "game_type")
    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
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
    @Column(name = "account")
    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    @Basic
    @Column(name = "cus_account")
    public BigDecimal getCusAccount() {
        return cusAccount;
    }

    public void setCusAccount(BigDecimal cusAccount) {
        this.cusAccount = cusAccount;
    }

    @Basic
    @Column(name = "valid_account")
    public BigDecimal getValidAccount() {
        return validAccount;
    }

    public void setValidAccount(BigDecimal validAccount) {
        this.validAccount = validAccount;
    }

    @Basic
    @Column(name = "account_base")
    public BigDecimal getAccountBase() {
        return accountBase;
    }

    public void setAccountBase(BigDecimal accountBase) {
        this.accountBase = accountBase;
    }

    @Basic
    @Column(name = "account_bonus")
    public BigDecimal getAccountBonus() {
        return accountBonus;
    }

    public void setAccountBonus(BigDecimal accountBonus) {
        this.accountBonus = accountBonus;
    }

    @Basic
    @Column(name = "cus_account_base")
    public BigDecimal getCusAccountBase() {
        return cusAccountBase;
    }

    public void setCusAccountBase(BigDecimal cusAccountBase) {
        this.cusAccountBase = cusAccountBase;
    }

    @Basic
    @Column(name = "cus_account_bonus")
    public BigDecimal getCusAccountBonus() {
        return cusAccountBonus;
    }

    public void setCusAccountBonus(BigDecimal cusAccountBonus) {
        this.cusAccountBonus = cusAccountBonus;
    }

    @Basic
    @Column(name = "src_amount")
    public BigDecimal getSrcAmount() {
        return srcAmount;
    }

    public void setSrcAmount(BigDecimal srcAmount) {
        this.srcAmount = srcAmount;
    }

    @Basic
    @Column(name = "dst_amount")
    public BigDecimal getDstAmount() {
        return dstAmount;
    }

    public void setDstAmount(BigDecimal dstAmount) {
        this.dstAmount = dstAmount;
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
    @Column(name = "flag")
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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
    @Column(name = "exttxid")
    public String getExttxid() {
        return exttxid;
    }

    public void setExttxid(String exttxid) {
        this.exttxid = exttxid;
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
        DsAgXinPo dsAgXinPo = (DsAgXinPo) o;
        return Objects.equals(billNo, dsAgXinPo.billNo) &&
                Objects.equals(agentId, dsAgXinPo.agentId) &&
                Objects.equals(siteId, dsAgXinPo.siteId) &&
                Objects.equals(username, dsAgXinPo.username) &&
                Objects.equals(playerName, dsAgXinPo.playerName) &&
                Objects.equals(billTime, dsAgXinPo.billTime) &&
                Objects.equals(reckonTime, dsAgXinPo.reckonTime) &&
                Objects.equals(slotType, dsAgXinPo.slotType) &&
                Objects.equals(currency, dsAgXinPo.currency) &&
                Objects.equals(gameType, dsAgXinPo.gameType) &&
                Objects.equals(betIp, dsAgXinPo.betIp) &&
                Objects.equals(account, dsAgXinPo.account) &&
                Objects.equals(cusAccount, dsAgXinPo.cusAccount) &&
                Objects.equals(validAccount, dsAgXinPo.validAccount) &&
                Objects.equals(accountBase, dsAgXinPo.accountBase) &&
                Objects.equals(accountBonus, dsAgXinPo.accountBonus) &&
                Objects.equals(cusAccountBase, dsAgXinPo.cusAccountBase) &&
                Objects.equals(cusAccountBonus, dsAgXinPo.cusAccountBonus) &&
                Objects.equals(srcAmount, dsAgXinPo.srcAmount) &&
                Objects.equals(dstAmount, dsAgXinPo.dstAmount) &&
                Objects.equals(winLossType, dsAgXinPo.winLossType) &&
                Objects.equals(flag, dsAgXinPo.flag) &&
                Objects.equals(platformType, dsAgXinPo.platformType) &&
                Objects.equals(deviceType, dsAgXinPo.deviceType) &&
                Objects.equals(exttxid, dsAgXinPo.exttxid) &&
                Objects.equals(createTime, dsAgXinPo.createTime) &&
                Objects.equals(updateTime, dsAgXinPo.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billNo, agentId, siteId, username, playerName, billTime, reckonTime, slotType, currency, gameType, betIp, account, cusAccount, validAccount, accountBase, accountBonus, cusAccountBase, cusAccountBonus, srcAmount, dstAmount, winLossType, flag, platformType, deviceType, exttxid, createTime, updateTime);
    }
}
