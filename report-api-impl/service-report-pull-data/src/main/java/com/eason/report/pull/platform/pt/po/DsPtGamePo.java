package com.eason.report.pull.platform.pt.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ds_pt_game")
public class DsPtGamePo  implements Serializable {
    private String gamecode;
    private Integer siteId;
    private String agentId;
    private String username;
    private String playername;
    private String windowcode;
    private Integer gameid;
    private String gametype;
    private String gamename;
    private String sessionid;
    private BigDecimal bet;
    private BigDecimal win;
    private BigDecimal progressivebet;
    private BigDecimal progressivewin;
    private BigDecimal balance;
    private BigDecimal currentbet;
    private Integer rnum;
    private Date gamedate;
    private String livenetwork;
    private Integer winLossType;
    private Date createTime;
    private Date updateTime;

    @Id
    @Column(name = "gamecode")
    public String getGamecode() {
        return gamecode;
    }

    public void setGamecode(String gamecode) {
        this.gamecode = gamecode;
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
    @Column(name = "playername")
    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    @Basic
    @Column(name = "windowcode")
    public String getWindowcode() {
        return windowcode;
    }

    public void setWindowcode(String windowcode) {
        this.windowcode = windowcode;
    }

    @Basic
    @Column(name = "gameid")
    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    @Basic
    @Column(name = "gametype")
    public String getGametype() {
        return gametype;
    }

    public void setGametype(String gametype) {
        this.gametype = gametype;
    }

    @Basic
    @Column(name = "gamename")
    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    @Basic
    @Column(name = "sessionid")
    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    @Basic
    @Column(name = "bet")
    public BigDecimal getBet() {
        return bet;
    }

    public void setBet(BigDecimal bet) {
        this.bet = bet;
    }

    @Basic
    @Column(name = "win")
    public BigDecimal getWin() {
        return win;
    }

    public void setWin(BigDecimal win) {
        this.win = win;
    }

    @Basic
    @Column(name = "progressivebet")
    public BigDecimal getProgressivebet() {
        return progressivebet;
    }

    public void setProgressivebet(BigDecimal progressivebet) {
        this.progressivebet = progressivebet;
    }

    @Basic
    @Column(name = "progressivewin")
    public BigDecimal getProgressivewin() {
        return progressivewin;
    }

    public void setProgressivewin(BigDecimal progressivewin) {
        this.progressivewin = progressivewin;
    }

    @Basic
    @Column(name = "balance")
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "currentbet")
    public BigDecimal getCurrentbet() {
        return currentbet;
    }

    public void setCurrentbet(BigDecimal currentbet) {
        this.currentbet = currentbet;
    }

    @Basic
    @Column(name = "rnum")
    public Integer getRnum() {
        return rnum;
    }

    public void setRnum(Integer rnum) {
        this.rnum = rnum;
    }

    @Basic
    @Column(name = "gamedate")
    public Date getGamedate() {
        return gamedate;
    }

    public void setGamedate(Date gamedate) {
        this.gamedate = gamedate;
    }

    @Basic
    @Column(name = "livenetwork")
    public String getLivenetwork() {
        return livenetwork;
    }

    public void setLivenetwork(String livenetwork) {
        this.livenetwork = livenetwork;
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
        DsPtGamePo that = (DsPtGamePo) o;
        return  Objects.equals(siteId, that.siteId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(playername, that.playername) &&
                Objects.equals(windowcode, that.windowcode) &&
                Objects.equals(gameid, that.gameid) &&
                Objects.equals(gamecode, that.gamecode) &&
                Objects.equals(gametype, that.gametype) &&
                Objects.equals(gamename, that.gamename) &&
                Objects.equals(sessionid, that.sessionid) &&
                Objects.equals(bet, that.bet) &&
                Objects.equals(win, that.win) &&
                Objects.equals(progressivebet, that.progressivebet) &&
                Objects.equals(progressivewin, that.progressivewin) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(currentbet, that.currentbet) &&
                Objects.equals(rnum, that.rnum) &&
                Objects.equals(gamedate, that.gamedate) &&
                Objects.equals(livenetwork, that.livenetwork) &&
                Objects.equals(winLossType, that.winLossType) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(siteId, username, playername, windowcode, gameid, gamecode, gametype, gamename, sessionid, bet, win, progressivebet, progressivewin, balance, currentbet, rnum, gamedate, livenetwork, winLossType, createTime, updateTime);
    }
}
