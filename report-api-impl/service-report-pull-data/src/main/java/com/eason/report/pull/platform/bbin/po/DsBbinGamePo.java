package com.eason.report.pull.platform.bbin.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ds_bbin_game")
public class DsBbinGamePo implements Serializable {
    private String wagersId;
    private String agentId;
    private Integer siteId;
    private String userName;
    private Date wagersDate;
    private String serialId;
    private String roundNo;
    private String gameType;
    private String gameCode;
    private String result;
    private String resultType;
    private String card;
    private BigDecimal betAmount;
    private BigDecimal payOff;
    private String currency;
    private String exchangeRate;
    private BigDecimal commissionable;
    private Byte winLossType;
    private Date createTime;
    private Date modifiedDate;

    @Id
    @Column(name = "wagers_id")
    public String getWagersId() {
        return wagersId;
    }

    public void setWagersId(String wagersId) {
        this.wagersId = wagersId;
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
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "wagers_date")
    public Date getWagersDate() {
        return wagersDate;
    }

    public void setWagersDate(Date wagersDate) {
        this.wagersDate = wagersDate;
    }

    @Basic
    @Column(name = "serial_id")
    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    @Basic
    @Column(name = "round_no")
    public String getRoundNo() {
        return roundNo;
    }

    public void setRoundNo(String roundNo) {
        this.roundNo = roundNo;
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
    @Column(name = "game_code")
    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    @Basic
    @Column(name = "result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Basic
    @Column(name = "result_type")
    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    @Basic
    @Column(name = "card")
    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
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
    @Column(name = "pay_off")
    public BigDecimal getPayOff() {
        return payOff;
    }

    public void setPayOff(BigDecimal payOff) {
        this.payOff = payOff;
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
    @Column(name = "exchange_rate")
    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Basic
    @Column(name = "commissionable")
    public BigDecimal getCommissionable() {
        return commissionable;
    }

    public void setCommissionable(BigDecimal commissionable) {
        this.commissionable = commissionable;
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
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "modified_date")
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DsBbinGamePo that = (DsBbinGamePo) o;
        return Objects.equals(wagersId, that.wagersId) &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(wagersDate, that.wagersDate) &&
                Objects.equals(serialId, that.serialId) &&
                Objects.equals(roundNo, that.roundNo) &&
                Objects.equals(gameType, that.gameType) &&
                Objects.equals(gameCode, that.gameCode) &&
                Objects.equals(result, that.result) &&
                Objects.equals(card, that.card) &&
                Objects.equals(betAmount, that.betAmount) &&
                Objects.equals(payOff, that.payOff) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(exchangeRate, that.exchangeRate) &&
                Objects.equals(commissionable, that.commissionable) &&
                Objects.equals(winLossType, that.winLossType) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(modifiedDate, that.modifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wagersId, agentId, siteId, userName,wagersDate, serialId, roundNo, gameType, gameCode, result, card, betAmount, payOff, currency, exchangeRate, commissionable, winLossType, createTime, modifiedDate);
    }
}
