package com.eason.report.pull.platform.ag.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ds_ag_sport")
public class DsAgSportPo {
    private String billNo;
    private String agentId;
    private Integer siteId;
    private String username;
    private String playName;
    private Date billTime;
    private Date reckonTime;
    private String currency;
    private String gameType;
    private String betIp;
    private BigDecimal account;
    private BigDecimal cusAccount;
    private BigDecimal validAccount;
    private String flag;
    private String odds;
    private String competition;
    private String market;
    private String selection;
    private String simplifiedResult;
    private String sport;
    private String category;
    private String extbillno;
    private String thirdbillno;
    private String bettype;
    private String system;
    private String live;
    private String currentScore;
    private Byte winLossType;
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
    @Column(name = "flag")
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "odds")
    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    @Basic
    @Column(name = "competition")
    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    @Basic
    @Column(name = "market")
    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @Basic
    @Column(name = "selection")
    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    @Basic
    @Column(name = "simplified_result")
    public String getSimplifiedResult() {
        return simplifiedResult;
    }

    public void setSimplifiedResult(String simplifiedResult) {
        this.simplifiedResult = simplifiedResult;
    }

    @Basic
    @Column(name = "sport")
    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "extbillno")
    public String getExtbillno() {
        return extbillno;
    }

    public void setExtbillno(String extbillno) {
        this.extbillno = extbillno;
    }

    @Basic
    @Column(name = "thirdbillno")
    public String getThirdbillno() {
        return thirdbillno;
    }

    public void setThirdbillno(String thirdbillno) {
        this.thirdbillno = thirdbillno;
    }

    @Basic
    @Column(name = "bettype")
    public String getBettype() {
        return bettype;
    }

    public void setBettype(String bettype) {
        this.bettype = bettype;
    }

    @Basic
    @Column(name = "system")
    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    @Basic
    @Column(name = "live")
    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    @Basic
    @Column(name = "current_score")
    public String getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(String currentScore) {
        this.currentScore = currentScore;
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
        DsAgSportPo that = (DsAgSportPo) o;
        return Objects.equals(billNo, that.billNo) &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(playName, that.playName) &&
                Objects.equals(billTime, that.billTime) &&
                Objects.equals(reckonTime, that.reckonTime) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(gameType, that.gameType) &&
                Objects.equals(betIp, that.betIp) &&
                Objects.equals(account, that.account) &&
                Objects.equals(cusAccount, that.cusAccount) &&
                Objects.equals(validAccount, that.validAccount) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(odds, that.odds) &&
                Objects.equals(competition, that.competition) &&
                Objects.equals(market, that.market) &&
                Objects.equals(selection, that.selection) &&
                Objects.equals(simplifiedResult, that.simplifiedResult) &&
                Objects.equals(sport, that.sport) &&
                Objects.equals(category, that.category) &&
                Objects.equals(extbillno, that.extbillno) &&
                Objects.equals(thirdbillno, that.thirdbillno) &&
                Objects.equals(bettype, that.bettype) &&
                Objects.equals(system, that.system) &&
                Objects.equals(live, that.live) &&
                Objects.equals(currentScore, that.currentScore) &&
                Objects.equals(winLossType, that.winLossType) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billNo, agentId, siteId, username, playName, billTime, reckonTime, currency, gameType, betIp, account, cusAccount, validAccount, flag, odds, competition, market, selection, simplifiedResult, sport, category, extbillno, thirdbillno, bettype, system, live, currentScore, winLossType, createTime, updateTime);
    }
}
