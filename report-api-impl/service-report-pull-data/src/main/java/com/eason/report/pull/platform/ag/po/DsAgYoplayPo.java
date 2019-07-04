package com.eason.report.pull.platform.ag.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ds_ag_yoplay")
public class DsAgYoplayPo implements Serializable {
    private String billNo;
    private String agentId;
    private Integer siteId;
    private String username;
    private String playName;
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
    private String gameCode;
    private Byte winLossType;
    private String flag;
    private String platformType;
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
    @Column(name = "game_code")
    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
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
        DsAgYoplayPo that = (DsAgYoplayPo) o;
        return Objects.equals(billNo, that.billNo) &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(playName, that.playName) &&
                Objects.equals(billTime, that.billTime) &&
                Objects.equals(reckonTime, that.reckonTime) &&
                Objects.equals(slotType, that.slotType) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(gameType, that.gameType) &&
                Objects.equals(betIp, that.betIp) &&
                Objects.equals(account, that.account) &&
                Objects.equals(cusAccount, that.cusAccount) &&
                Objects.equals(validAccount, that.validAccount) &&
                Objects.equals(accountBase, that.accountBase) &&
                Objects.equals(accountBonus, that.accountBonus) &&
                Objects.equals(cusAccountBase, that.cusAccountBase) &&
                Objects.equals(cusAccountBonus, that.cusAccountBonus) &&
                Objects.equals(srcAmount, that.srcAmount) &&
                Objects.equals(dstAmount, that.dstAmount) &&
                Objects.equals(gameCode, that.gameCode) &&
                Objects.equals(winLossType, that.winLossType) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(platformType, that.platformType) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billNo, agentId, siteId, username, playName, billTime, reckonTime, slotType, currency, gameType, betIp, account, cusAccount, validAccount, accountBase, accountBonus, cusAccountBase, cusAccountBonus, srcAmount, dstAmount, gameCode, winLossType, flag, platformType, remark, createTime, updateTime);
    }
}
