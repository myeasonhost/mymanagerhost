package com.eason.report.pull.platform.lmg.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ds_lmg_game")
public class DsLmgGamePo implements Serializable {
    private long id;
    private Integer siteId;
    private String agentId;
    private Long sequenceNo;
    private String username;
    private String currency;
    private String liveProductType;
    private String gameType;
    private Integer tableInfoId;
    private Integer shoeInfoId;
    private Integer gameInfoId;
    private String tableName;
    private String bankerResult;
    private String resultList;
    private String pokerList;
    private BigDecimal stakeAmount;
    private BigDecimal validStake;
    private BigDecimal comm;
    private BigDecimal commAmount;
    private BigDecimal winLoss;
    private Integer winLossType;
    private BigDecimal balanceAfter;
    private Date endTime;
    private Date reportDate;
    private String ip;
    private String resultImgName;
    private String hall;
    private String liveMemberReportDetails;
    private Date createTime;
    private Date updateTime;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    @Column(name = "sequence_no")
    public Long getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Long sequenceNo) {
        this.sequenceNo = sequenceNo;
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
    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "live_product_type")
    public String getLiveProductType() {
        return liveProductType;
    }

    public void setLiveProductType(String liveProductType) {
        this.liveProductType = liveProductType;
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
    @Column(name = "table_info_id")
    public Integer getTableInfoId() {
        return tableInfoId;
    }

    public void setTableInfoId(Integer tableInfoId) {
        this.tableInfoId = tableInfoId;
    }

    @Basic
    @Column(name = "shoe_info_id")
    public Integer getShoeInfoId() {
        return shoeInfoId;
    }

    public void setShoeInfoId(Integer shoeInfoId) {
        this.shoeInfoId = shoeInfoId;
    }

    @Basic
    @Column(name = "game_info_id")
    public Integer getGameInfoId() {
        return gameInfoId;
    }

    public void setGameInfoId(Integer gameInfoId) {
        this.gameInfoId = gameInfoId;
    }

    @Basic
    @Column(name = "table_name")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "banker_result")
    public String getBankerResult() {
        return bankerResult;
    }

    public void setBankerResult(String bankerResult) {
        this.bankerResult = bankerResult;
    }

    @Basic
    @Column(name = "result_list")
    public String getResultList() {
        return resultList;
    }

    public void setResultList(String resultList) {
        this.resultList = resultList;
    }

    @Basic
    @Column(name = "poker_list")
    public String getPokerList() {
        return pokerList;
    }

    public void setPokerList(String pokerList) {
        this.pokerList = pokerList;
    }

    @Basic
    @Column(name = "stake_amount")
    public BigDecimal getStakeAmount() {
        return stakeAmount;
    }

    public void setStakeAmount(BigDecimal stakeAmount) {
        this.stakeAmount = stakeAmount;
    }

    @Basic
    @Column(name = "valid_stake")
    public BigDecimal getValidStake() {
        return validStake;
    }

    public void setValidStake(BigDecimal validStake) {
        this.validStake = validStake;
    }

    @Basic
    @Column(name = "comm")
    public BigDecimal getComm() {
        return comm;
    }

    public void setComm(BigDecimal comm) {
        this.comm = comm;
    }

    @Basic
    @Column(name = "comm_amount")
    public BigDecimal getCommAmount() {
        return commAmount;
    }

    public void setCommAmount(BigDecimal commAmount) {
        this.commAmount = commAmount;
    }

    @Basic
    @Column(name = "win_loss")
    public BigDecimal getWinLoss() {
        return winLoss;
    }

    public void setWinLoss(BigDecimal winLoss) {
        this.winLoss = winLoss;
    }

    @Basic
    @Column(name = "win_loss_type")
    public Integer getWinLossType() {
        return winLossType;
    }

    public void setWinLossType(Integer winLossType) {
        this.winLossType = winLossType;
    }

    @Basic
    @Column(name = "balance_after")
    public BigDecimal getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(BigDecimal balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    @Basic
    @Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "report_date")
    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    @Basic
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "result_img_name")
    public String getResultImgName() {
        return resultImgName;
    }

    public void setResultImgName(String resultImgName) {
        this.resultImgName = resultImgName;
    }

    @Basic
    @Column(name = "hall")
    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    @Basic
    @Column(name = "live_member_report_details")
    public String getLiveMemberReportDetails() {
        return liveMemberReportDetails;
    }

    public void setLiveMemberReportDetails(String liveMemberReportDetails) {
        this.liveMemberReportDetails = liveMemberReportDetails;
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
        DsLmgGamePo that = (DsLmgGamePo) o;
        return id == that.id &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(sequenceNo, that.sequenceNo) &&
                Objects.equals(username, that.username) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(liveProductType, that.liveProductType) &&
                Objects.equals(gameType, that.gameType) &&
                Objects.equals(tableInfoId, that.tableInfoId) &&
                Objects.equals(shoeInfoId, that.shoeInfoId) &&
                Objects.equals(gameInfoId, that.gameInfoId) &&
                Objects.equals(tableName, that.tableName) &&
                Objects.equals(bankerResult, that.bankerResult) &&
                Objects.equals(resultList, that.resultList) &&
                Objects.equals(pokerList, that.pokerList) &&
                Objects.equals(stakeAmount, that.stakeAmount) &&
                Objects.equals(validStake, that.validStake) &&
                Objects.equals(comm, that.comm) &&
                Objects.equals(commAmount, that.commAmount) &&
                Objects.equals(winLoss, that.winLoss) &&
                Objects.equals(balanceAfter, that.balanceAfter) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(reportDate, that.reportDate) &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(resultImgName, that.resultImgName) &&
                Objects.equals(hall, that.hall) &&
                Objects.equals(liveMemberReportDetails, that.liveMemberReportDetails) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, siteId, sequenceNo, username, currency, liveProductType, gameType, tableInfoId, shoeInfoId, gameInfoId, tableName, bankerResult, resultList, pokerList, stakeAmount, validStake, comm, commAmount, winLoss, balanceAfter, endTime, reportDate, ip, resultImgName, hall, liveMemberReportDetails, createTime, updateTime);
    }
}
