package com.eason.transfer.openapi.chess.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_lc_chess_game")
public class TLcChessGamePo implements Serializable {
    private int gameId;
    private String siteId;
    private String account;
    private Integer serverId;
    private Integer kindId;
    private Long tableId;
    private Integer chairId;
    private Integer userCount;
    private String cardValue;
    private BigDecimal cellScore;
    private BigDecimal allBet;
    private BigDecimal profit;
    private BigDecimal revenue;
    private Timestamp gameStartTime;
    private Timestamp gameEndTime;
    private Integer channelId;
    private String lineCode;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "GameID")
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
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
    @Column(name = "Account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "ServerID")
    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    @Basic
    @Column(name = "KindID")
    public Integer getKindId() {
        return kindId;
    }

    public void setKindId(Integer kindId) {
        this.kindId = kindId;
    }

    @Basic
    @Column(name = "TableID")
    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    @Basic
    @Column(name = "ChairID")
    public Integer getChairId() {
        return chairId;
    }

    public void setChairId(Integer chairId) {
        this.chairId = chairId;
    }

    @Basic
    @Column(name = "UserCount")
    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    @Basic
    @Column(name = "CardValue")
    public String getCardValue() {
        return cardValue;
    }

    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }

    @Basic
    @Column(name = "CellScore")
    public BigDecimal getCellScore() {
        return cellScore;
    }

    public void setCellScore(BigDecimal cellScore) {
        this.cellScore = cellScore;
    }

    @Basic
    @Column(name = "AllBet")
    public BigDecimal getAllBet() {
        return allBet;
    }

    public void setAllBet(BigDecimal allBet) {
        this.allBet = allBet;
    }

    @Basic
    @Column(name = "Profit")
    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    @Basic
    @Column(name = "Revenue")
    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    @Basic
    @Column(name = "GameStartTime")
    public Timestamp getGameStartTime() {
        return gameStartTime;
    }

    public void setGameStartTime(Timestamp gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    @Basic
    @Column(name = "GameEndTime")
    public Timestamp getGameEndTime() {
        return gameEndTime;
    }

    public void setGameEndTime(Timestamp gameEndTime) {
        this.gameEndTime = gameEndTime;
    }

    @Basic
    @Column(name = "ChannelID")
    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    @Basic
    @Column(name = "LineCode")
    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
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
        TLcChessGamePo that = (TLcChessGamePo) o;
        return gameId == that.gameId &&
                Objects.equals(siteId, that.siteId) &&
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
                Objects.equals(gameStartTime, that.gameStartTime) &&
                Objects.equals(gameEndTime, that.gameEndTime) &&
                Objects.equals(channelId, that.channelId) &&
                Objects.equals(lineCode, that.lineCode) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, siteId, account, serverId, kindId, tableId, chairId, userCount, cardValue, cellScore, allBet, profit, revenue, gameStartTime, gameEndTime, channelId, lineCode, createTime, updateTime);
    }
}
