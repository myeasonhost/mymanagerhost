package com.eason.report.pull.platform.sgs.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ds_sgs_game")
public class DsSgsGamePo implements Serializable {
    private String ugsBetId;
    private String agentId;
    private Integer siteId;
    private String txid;
    private String betId;
    private Date betOn;
    private Date betClosedOn;
    private Date betUpdatedOn;
    private Date timestamp0;
    private String roundid;
    private String roundStatus;
    private String userid;
    private String username;
    private BigDecimal riskamt;
    private BigDecimal winamt;
    private BigDecimal winloss;
    private BigDecimal beforebal;
    private BigDecimal postbal;
    private Byte winLossType;
    private String currency;
    private String gameProvider;
    private String gameProviderCode;
    private String gameName;
    private String gameId;
    private String platformType;
    private String ipAddress;
    private String betType;
    private String playType;
    private Byte playerType;
    private BigDecimal turnover;
    private BigDecimal validbet;
    private Date createTime;
    private Date updateTime;

    @Id
    @Column(name = "ugs_bet_id")
    public String getUgsBetId() {
        return ugsBetId;
    }

    public void setUgsBetId(String ugsBetId) {
        this.ugsBetId = ugsBetId;
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
    @Column(name = "txid")
    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    @Basic
    @Column(name = "bet_id")
    public String getBetId() {
        return betId;
    }

    public void setBetId(String betId) {
        this.betId = betId;
    }

    @Basic
    @Column(name = "bet_on")
    public Date getBetOn() {
        return betOn;
    }

    public void setBetOn(Date betOn) {
        this.betOn = betOn;
    }

    @Basic
    @Column(name = "bet_closed_on")
    public Date getBetClosedOn() {
        return betClosedOn;
    }

    public void setBetClosedOn(Date betClosedOn) {
        this.betClosedOn = betClosedOn;
    }

    @Basic
    @Column(name = "bet_updated_on")
    public Date getBetUpdatedOn() {
        return betUpdatedOn;
    }

    public void setBetUpdatedOn(Date betUpdatedOn) {
        this.betUpdatedOn = betUpdatedOn;
    }

    @Basic
    @Column(name = "timestamp")
    public Date getTimestamp0() {
        return timestamp0;
    }

    public void setTimestamp0(Date timestamp0) {
        this.timestamp0 = timestamp0;
    }

    @Basic
    @Column(name = "roundid")
    public String getRoundid() {
        return roundid;
    }

    public void setRoundid(String roundid) {
        this.roundid = roundid;
    }

    @Basic
    @Column(name = "round_status")
    public String getRoundStatus() {
        return roundStatus;
    }

    public void setRoundStatus(String roundStatus) {
        this.roundStatus = roundStatus;
    }

    @Basic
    @Column(name = "userid")
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
    @Column(name = "riskamt")
    public BigDecimal getRiskamt() {
        return riskamt;
    }

    public void setRiskamt(BigDecimal riskamt) {
        this.riskamt = riskamt;
    }

    @Basic
    @Column(name = "winamt")
    public BigDecimal getWinamt() {
        return winamt;
    }

    public void setWinamt(BigDecimal winamt) {
        this.winamt = winamt;
    }

    @Basic
    @Column(name = "winloss")
    public BigDecimal getWinloss() {
        return winloss;
    }

    public void setWinloss(BigDecimal winloss) {
        this.winloss = winloss;
    }

    @Basic
    @Column(name = "beforebal")
    public BigDecimal getBeforebal() {
        return beforebal;
    }

    public void setBeforebal(BigDecimal beforebal) {
        this.beforebal = beforebal;
    }

    @Basic
    @Column(name = "postbal")
    public BigDecimal getPostbal() {
        return postbal;
    }

    public void setPostbal(BigDecimal postbal) {
        this.postbal = postbal;
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
    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "game_provider")
    public String getGameProvider() {
        return gameProvider;
    }

    public void setGameProvider(String gameProvider) {
        this.gameProvider = gameProvider;
    }

    @Basic
    @Column(name = "game_provider_code")
    public String getGameProviderCode() {
        return gameProviderCode;
    }

    public void setGameProviderCode(String gameProviderCode) {
        this.gameProviderCode = gameProviderCode;
    }

    @Basic
    @Column(name = "game_name")
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Basic
    @Column(name = "game_id")
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
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
    @Column(name = "ip_address")
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Basic
    @Column(name = "bet_type")
    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
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
    @Column(name = "player_type")
    public Byte getPlayerType() {
        return playerType;
    }

    public void setPlayerType(Byte playerType) {
        this.playerType = playerType;
    }

    @Basic
    @Column(name = "turnover")
    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    @Basic
    @Column(name = "validbet")
    public BigDecimal getValidbet() {
        return validbet;
    }

    public void setValidbet(BigDecimal validbet) {
        this.validbet = validbet;
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
        DsSgsGamePo that = (DsSgsGamePo) o;
        return Objects.equals(ugsBetId, that.ugsBetId) &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(txid, that.txid) &&
                Objects.equals(betId, that.betId) &&
                Objects.equals(betOn, that.betOn) &&
                Objects.equals(betClosedOn, that.betClosedOn) &&
                Objects.equals(betUpdatedOn, that.betUpdatedOn) &&
                Objects.equals(timestamp0, that.timestamp0) &&
                Objects.equals(roundid, that.roundid) &&
                Objects.equals(roundStatus, that.roundStatus) &&
                Objects.equals(userid, that.userid) &&
                Objects.equals(username, that.username) &&
                Objects.equals(riskamt, that.riskamt) &&
                Objects.equals(winamt, that.winamt) &&
                Objects.equals(winloss, that.winloss) &&
                Objects.equals(beforebal, that.beforebal) &&
                Objects.equals(postbal, that.postbal) &&
                Objects.equals(winLossType, that.winLossType) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(gameProvider, that.gameProvider) &&
                Objects.equals(gameProviderCode, that.gameProviderCode) &&
                Objects.equals(gameName, that.gameName) &&
                Objects.equals(gameId, that.gameId) &&
                Objects.equals(platformType, that.platformType) &&
                Objects.equals(ipAddress, that.ipAddress) &&
                Objects.equals(betType, that.betType) &&
                Objects.equals(playType, that.playType) &&
                Objects.equals(playerType, that.playerType) &&
                Objects.equals(turnover, that.turnover) &&
                Objects.equals(validbet, that.validbet) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ugsBetId, siteId, txid, betId, betOn, betClosedOn, betUpdatedOn, timestamp0, roundid, roundStatus, userid, username, riskamt, winamt, winloss, beforebal, postbal, winLossType, currency, gameProvider, gameProviderCode, gameName, gameId, platformType, ipAddress, betType, playType, playerType, turnover, validbet, createTime, updateTime);
    }
}
