package com.eason.transfer.openapi.core.client.chess.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TChessGamePo {
    private String gameid;

    private String siteId;

    private String account;

    private Integer serverid;

    private Integer kindid;

    private Long tableid;

    private Integer chairid;

    private Integer usercount;

    private String cardvalue;

    private BigDecimal cellscore;

    private BigDecimal allbet;

    private BigDecimal profit;

    private BigDecimal revenue;

    private Date gamestarttime;

    private Date gameendtime;

    private Integer channelid;

    private String linecode;

    private Date createTime;

    private Date updateTime;

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getServerid() {
        return serverid;
    }

    public void setServerid(Integer serverid) {
        this.serverid = serverid;
    }

    public Integer getKindid() {
        return kindid;
    }

    public void setKindid(Integer kindid) {
        this.kindid = kindid;
    }

    public Long getTableid() {
        return tableid;
    }

    public void setTableid(Long tableid) {
        this.tableid = tableid;
    }

    public Integer getChairid() {
        return chairid;
    }

    public void setChairid(Integer chairid) {
        this.chairid = chairid;
    }

    public Integer getUsercount() {
        return usercount;
    }

    public void setUsercount(Integer usercount) {
        this.usercount = usercount;
    }

    public String getCardvalue() {
        return cardvalue;
    }

    public void setCardvalue(String cardvalue) {
        this.cardvalue = cardvalue;
    }

    public BigDecimal getCellscore() {
        return cellscore;
    }

    public void setCellscore(BigDecimal cellscore) {
        this.cellscore = cellscore;
    }

    public BigDecimal getAllbet() {
        return allbet;
    }

    public void setAllbet(BigDecimal allbet) {
        this.allbet = allbet;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public Date getGamestarttime() {
        return gamestarttime;
    }

    public void setGamestarttime(Date gamestarttime) {
        this.gamestarttime = gamestarttime;
    }

    public Date getGameendtime() {
        return gameendtime;
    }

    public void setGameendtime(Date gameendtime) {
        this.gameendtime = gameendtime;
    }

    public Integer getChannelid() {
        return channelid;
    }

    public void setChannelid(Integer channelid) {
        this.channelid = channelid;
    }

    public String getLinecode() {
        return linecode;
    }

    public void setLinecode(String linecode) {
        this.linecode = linecode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}