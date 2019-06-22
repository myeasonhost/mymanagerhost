package com.eason.report.pull.platform.ky.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ky_chess_game")
public class KyChessGamePo implements Serializable {
    private Integer siteId;
    private String gameId;
    private String account;
    private Integer serverId;
    private Integer kindId;
    private Integer tableId;
    private Integer chairId;
    private Integer userCount;
    private String cardValue;
    private BigDecimal cellScore;
    private BigDecimal allBet;
    private BigDecimal profit;
    private BigDecimal revenue;
    private Byte winLossType;
    private Date gameStartTime;
    private Date gameEndTime;
    private Integer channelId;
    private Date createTime;
    private Date updateTime;

    @Id
    @Column(name = "game_id")
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    @Column(name = "siteId")
    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "server_id")
    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    @Basic
    @Column(name = "kind_id")
    public Integer getKindId() {
        return kindId;
    }

    public void setKindId(Integer kindId) {
        this.kindId = kindId;
    }

    @Basic
    @Column(name = "table_id")
    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    @Basic
    @Column(name = "chair_id")
    public Integer getChairId() {
        return chairId;
    }

    public void setChairId(Integer chairId) {
        this.chairId = chairId;
    }

    @Basic
    @Column(name = "user_count")
    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    @Basic
    @Column(name = "card_value")
    public String getCardValue() {
        return cardValue;
    }

    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }

    @Basic
    @Column(name = "cell_score")
    public BigDecimal getCellScore() {
        return cellScore;
    }

    public void setCellScore(BigDecimal cellScore) {
        this.cellScore = cellScore;
    }

    @Basic
    @Column(name = "all_bet")
    public BigDecimal getAllBet() {
        return allBet;
    }

    public void setAllBet(BigDecimal allBet) {
        this.allBet = allBet;
    }

    @Basic
    @Column(name = "profit")
    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    @Basic
    @Column(name = "revenue")
    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
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
    @Column(name = "game_start_time")
    public Date getGameStartTime() {
        return gameStartTime;
    }

    public void setGameStartTime(Date gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    @Basic
    @Column(name = "game_end_time")
    public Date getGameEndTime() {
        return gameEndTime;
    }

    public void setGameEndTime(Date gameEndTime) {
        this.gameEndTime = gameEndTime;
    }

    @Basic
    @Column(name = "channel_id")
    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
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
        KyChessGamePo that = (KyChessGamePo) o;
        return  Objects.equals(siteId, that.siteId) &&
                Objects.equals(gameId, that.gameId) &&
                Objects.equals(account, that.account) &&
                Objects.equals(serverId, that.serverId) &&
                Objects.equals(kindId, that.kindId) &&
                Objects.equals(tableId, that.tableId) &&
                Objects.equals(chairId, that.chairId) &&
                Objects.equals(userCount, that.userCount) &&
                Objects.equals(cardValue, that.cardValue) &&
                Objects.equals(cellScore, that.cellScore) &&
                Objects.equals(allBet, that.allBet) &&
                Objects.equals(profit, that.profit) &&
                Objects.equals(revenue, that.revenue) &&
                Objects.equals(winLossType, that.winLossType) &&
                Objects.equals(gameStartTime, that.gameStartTime) &&
                Objects.equals(gameEndTime, that.gameEndTime) &&
                Objects.equals(channelId, that.channelId) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(siteId, gameId, account, serverId, kindId, tableId, chairId, userCount, cardValue, cellScore, allBet, profit, revenue, winLossType, gameStartTime, gameEndTime, channelId, createTime, updateTime);
    }
}
