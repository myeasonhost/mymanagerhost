package com.eason.report.pull.platform.lottery.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "mdt_jingdian_lottery")
public class MdtJingdianLotteryPo implements Serializable {
    private long id;
    private int siteId;
    private String nid;
    private byte lid;
    private String user;
    private byte userType;
    private String user1;
    private String user2;
    private String user3;
    private String user4;
    private String pan;
    private long qishu;
    private Byte pid;
    private Short oid;
    private String project;
    private String itmes;
    private String content;
    private String odds;
    private int jiner;
    private BigDecimal jiner1;
    private BigDecimal jiner2;
    private BigDecimal jiner3;
    private BigDecimal jiner4;
    private BigDecimal jiner5;
    private BigDecimal jinerb;
    private byte share1;
    private byte share2;
    private byte share3;
    private byte share4;
    private byte share5;
    private BigDecimal winDream;
    private BigDecimal win;
    private BigDecimal win1;
    private BigDecimal win2;
    private BigDecimal win3;
    private BigDecimal win4;
    private BigDecimal win5;
    private BigDecimal bonus;
    private BigDecimal bonus1;
    private BigDecimal bonus2;
    private BigDecimal bonus3;
    private BigDecimal bonus4;
    private BigDecimal bonus5;
    private BigDecimal tuishui;
    private BigDecimal tuishui1;
    private BigDecimal tuishui2;
    private BigDecimal tuishui3;
    private BigDecimal tuishui4;
    private BigDecimal tuishui5;
    private String ip;
    private String hash;
    private byte stataus;
    private String timeDraw;
    private String timeIn;
    private String timeAdd;
    private String timeJiesuan;
    private String timePay;
    private boolean isCancel;
    private String cancelContent;
    private Date betTime;
    private byte winLoseType;
    private Date reportTime;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "siteId")
    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "nid")
    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    @Basic
    @Column(name = "lid")
    public byte getLid() {
        return lid;
    }

    public void setLid(byte lid) {
        this.lid = lid;
    }

    @Basic
    @Column(name = "user")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Basic
    @Column(name = "user_type")
    public byte getUserType() {
        return userType;
    }

    public void setUserType(byte userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "user1")
    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    @Basic
    @Column(name = "user2")
    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    @Basic
    @Column(name = "user3")
    public String getUser3() {
        return user3;
    }

    public void setUser3(String user3) {
        this.user3 = user3;
    }

    @Basic
    @Column(name = "user4")
    public String getUser4() {
        return user4;
    }

    public void setUser4(String user4) {
        this.user4 = user4;
    }

    @Basic
    @Column(name = "pan")
    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    @Basic
    @Column(name = "qishu")
    public long getQishu() {
        return qishu;
    }

    public void setQishu(long qishu) {
        this.qishu = qishu;
    }

    @Basic
    @Column(name = "pid")
    public Byte getPid() {
        return pid;
    }

    public void setPid(Byte pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "oid")
    public Short getOid() {
        return oid;
    }

    public void setOid(Short oid) {
        this.oid = oid;
    }

    @Basic
    @Column(name = "project")
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Basic
    @Column(name = "itmes")
    public String getItmes() {
        return itmes;
    }

    public void setItmes(String itmes) {
        this.itmes = itmes;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "jiner")
    public int getJiner() {
        return jiner;
    }

    public void setJiner(int jiner) {
        this.jiner = jiner;
    }

    @Basic
    @Column(name = "jiner1")
    public BigDecimal getJiner1() {
        return jiner1;
    }

    public void setJiner1(BigDecimal jiner1) {
        this.jiner1 = jiner1;
    }

    @Basic
    @Column(name = "jiner2")
    public BigDecimal getJiner2() {
        return jiner2;
    }

    public void setJiner2(BigDecimal jiner2) {
        this.jiner2 = jiner2;
    }

    @Basic
    @Column(name = "jiner3")
    public BigDecimal getJiner3() {
        return jiner3;
    }

    public void setJiner3(BigDecimal jiner3) {
        this.jiner3 = jiner3;
    }

    @Basic
    @Column(name = "jiner4")
    public BigDecimal getJiner4() {
        return jiner4;
    }

    public void setJiner4(BigDecimal jiner4) {
        this.jiner4 = jiner4;
    }

    @Basic
    @Column(name = "jiner5")
    public BigDecimal getJiner5() {
        return jiner5;
    }

    public void setJiner5(BigDecimal jiner5) {
        this.jiner5 = jiner5;
    }

    @Basic
    @Column(name = "jinerb")
    public BigDecimal getJinerb() {
        return jinerb;
    }

    public void setJinerb(BigDecimal jinerb) {
        this.jinerb = jinerb;
    }

    @Basic
    @Column(name = "share1")
    public byte getShare1() {
        return share1;
    }

    public void setShare1(byte share1) {
        this.share1 = share1;
    }

    @Basic
    @Column(name = "share2")
    public byte getShare2() {
        return share2;
    }

    public void setShare2(byte share2) {
        this.share2 = share2;
    }

    @Basic
    @Column(name = "share3")
    public byte getShare3() {
        return share3;
    }

    public void setShare3(byte share3) {
        this.share3 = share3;
    }

    @Basic
    @Column(name = "share4")
    public byte getShare4() {
        return share4;
    }

    public void setShare4(byte share4) {
        this.share4 = share4;
    }

    @Basic
    @Column(name = "share5")
    public byte getShare5() {
        return share5;
    }

    public void setShare5(byte share5) {
        this.share5 = share5;
    }

    @Basic
    @Column(name = "win_dream")
    public BigDecimal getWinDream() {
        return winDream;
    }

    public void setWinDream(BigDecimal winDream) {
        this.winDream = winDream;
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
    @Column(name = "win1")
    public BigDecimal getWin1() {
        return win1;
    }

    public void setWin1(BigDecimal win1) {
        this.win1 = win1;
    }

    @Basic
    @Column(name = "win2")
    public BigDecimal getWin2() {
        return win2;
    }

    public void setWin2(BigDecimal win2) {
        this.win2 = win2;
    }

    @Basic
    @Column(name = "win3")
    public BigDecimal getWin3() {
        return win3;
    }

    public void setWin3(BigDecimal win3) {
        this.win3 = win3;
    }

    @Basic
    @Column(name = "win4")
    public BigDecimal getWin4() {
        return win4;
    }

    public void setWin4(BigDecimal win4) {
        this.win4 = win4;
    }

    @Basic
    @Column(name = "win5")
    public BigDecimal getWin5() {
        return win5;
    }

    public void setWin5(BigDecimal win5) {
        this.win5 = win5;
    }

    @Basic
    @Column(name = "bonus")
    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    @Basic
    @Column(name = "bonus1")
    public BigDecimal getBonus1() {
        return bonus1;
    }

    public void setBonus1(BigDecimal bonus1) {
        this.bonus1 = bonus1;
    }

    @Basic
    @Column(name = "bonus2")
    public BigDecimal getBonus2() {
        return bonus2;
    }

    public void setBonus2(BigDecimal bonus2) {
        this.bonus2 = bonus2;
    }

    @Basic
    @Column(name = "bonus3")
    public BigDecimal getBonus3() {
        return bonus3;
    }

    public void setBonus3(BigDecimal bonus3) {
        this.bonus3 = bonus3;
    }

    @Basic
    @Column(name = "bonus4")
    public BigDecimal getBonus4() {
        return bonus4;
    }

    public void setBonus4(BigDecimal bonus4) {
        this.bonus4 = bonus4;
    }

    @Basic
    @Column(name = "bonus5")
    public BigDecimal getBonus5() {
        return bonus5;
    }

    public void setBonus5(BigDecimal bonus5) {
        this.bonus5 = bonus5;
    }

    @Basic
    @Column(name = "tuishui")
    public BigDecimal getTuishui() {
        return tuishui;
    }

    public void setTuishui(BigDecimal tuishui) {
        this.tuishui = tuishui;
    }

    @Basic
    @Column(name = "tuishui1")
    public BigDecimal getTuishui1() {
        return tuishui1;
    }

    public void setTuishui1(BigDecimal tuishui1) {
        this.tuishui1 = tuishui1;
    }

    @Basic
    @Column(name = "tuishui2")
    public BigDecimal getTuishui2() {
        return tuishui2;
    }

    public void setTuishui2(BigDecimal tuishui2) {
        this.tuishui2 = tuishui2;
    }

    @Basic
    @Column(name = "tuishui3")
    public BigDecimal getTuishui3() {
        return tuishui3;
    }

    public void setTuishui3(BigDecimal tuishui3) {
        this.tuishui3 = tuishui3;
    }

    @Basic
    @Column(name = "tuishui4")
    public BigDecimal getTuishui4() {
        return tuishui4;
    }

    public void setTuishui4(BigDecimal tuishui4) {
        this.tuishui4 = tuishui4;
    }

    @Basic
    @Column(name = "tuishui5")
    public BigDecimal getTuishui5() {
        return tuishui5;
    }

    public void setTuishui5(BigDecimal tuishui5) {
        this.tuishui5 = tuishui5;
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
    @Column(name = "hash")
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Basic
    @Column(name = "stataus")
    public byte getStataus() {
        return stataus;
    }

    public void setStataus(byte stataus) {
        this.stataus = stataus;
    }

    @Basic
    @Column(name = "time_draw")
    public String getTimeDraw() {
        return timeDraw;
    }

    public void setTimeDraw(String timeDraw) {
        this.timeDraw = timeDraw;
    }

    @Basic
    @Column(name = "time_in")
    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    @Basic
    @Column(name = "time_add")
    public String getTimeAdd() {
        return timeAdd;
    }

    public void setTimeAdd(String timeAdd) {
        this.timeAdd = timeAdd;
    }

    @Basic
    @Column(name = "time_jiesuan")
    public String getTimeJiesuan() {
        return timeJiesuan;
    }

    public void setTimeJiesuan(String timeJiesuan) {
        this.timeJiesuan = timeJiesuan;
    }

    @Basic
    @Column(name = "time_pay")
    public String getTimePay() {
        return timePay;
    }

    public void setTimePay(String timePay) {
        this.timePay = timePay;
    }

    @Basic
    @Column(name = "is_cancel")
    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

    @Basic
    @Column(name = "cancel_content")
    public String getCancelContent() {
        return cancelContent;
    }

    public void setCancelContent(String cancelContent) {
        this.cancelContent = cancelContent;
    }

    @Basic
    @Column(name = "bet_time")
    public Date getBetTime() {
        return betTime;
    }

    public void setBetTime(Date betTime) {
        this.betTime = betTime;
    }

    @Basic
    @Column(name = "win_lose_type")
    public byte getWinLoseType() {
        return winLoseType;
    }

    public void setWinLoseType(byte winLoseType) {
        this.winLoseType = winLoseType;
    }

    @Basic
    @Column(name = "report_time")
    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdtJingdianLotteryPo that = (MdtJingdianLotteryPo) o;
        return id == that.id &&
                siteId == that.siteId &&
                lid == that.lid &&
                userType == that.userType &&
                qishu == that.qishu &&
                jiner == that.jiner &&
                share1 == that.share1 &&
                share2 == that.share2 &&
                share3 == that.share3 &&
                share4 == that.share4 &&
                share5 == that.share5 &&
                stataus == that.stataus &&
                isCancel == that.isCancel &&
                winLoseType == that.winLoseType &&
                Objects.equals(nid, that.nid) &&
                Objects.equals(user, that.user) &&
                Objects.equals(user1, that.user1) &&
                Objects.equals(user2, that.user2) &&
                Objects.equals(user3, that.user3) &&
                Objects.equals(user4, that.user4) &&
                Objects.equals(pan, that.pan) &&
                Objects.equals(pid, that.pid) &&
                Objects.equals(oid, that.oid) &&
                Objects.equals(project, that.project) &&
                Objects.equals(itmes, that.itmes) &&
                Objects.equals(content, that.content) &&
                Objects.equals(odds, that.odds) &&
                Objects.equals(jiner1, that.jiner1) &&
                Objects.equals(jiner2, that.jiner2) &&
                Objects.equals(jiner3, that.jiner3) &&
                Objects.equals(jiner4, that.jiner4) &&
                Objects.equals(jiner5, that.jiner5) &&
                Objects.equals(jinerb, that.jinerb) &&
                Objects.equals(winDream, that.winDream) &&
                Objects.equals(win, that.win) &&
                Objects.equals(win1, that.win1) &&
                Objects.equals(win2, that.win2) &&
                Objects.equals(win3, that.win3) &&
                Objects.equals(win4, that.win4) &&
                Objects.equals(win5, that.win5) &&
                Objects.equals(bonus, that.bonus) &&
                Objects.equals(bonus1, that.bonus1) &&
                Objects.equals(bonus2, that.bonus2) &&
                Objects.equals(bonus3, that.bonus3) &&
                Objects.equals(bonus4, that.bonus4) &&
                Objects.equals(bonus5, that.bonus5) &&
                Objects.equals(tuishui, that.tuishui) &&
                Objects.equals(tuishui1, that.tuishui1) &&
                Objects.equals(tuishui2, that.tuishui2) &&
                Objects.equals(tuishui3, that.tuishui3) &&
                Objects.equals(tuishui4, that.tuishui4) &&
                Objects.equals(tuishui5, that.tuishui5) &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(hash, that.hash) &&
                Objects.equals(timeDraw, that.timeDraw) &&
                Objects.equals(timeIn, that.timeIn) &&
                Objects.equals(timeAdd, that.timeAdd) &&
                Objects.equals(timeJiesuan, that.timeJiesuan) &&
                Objects.equals(timePay, that.timePay) &&
                Objects.equals(cancelContent, that.cancelContent) &&
                Objects.equals(betTime, that.betTime) &&
                Objects.equals(reportTime, that.reportTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, siteId, nid, lid, user, userType, user1, user2, user3, user4, pan, qishu, pid, oid, project, itmes, content, odds, jiner, jiner1, jiner2, jiner3, jiner4, jiner5, jinerb, share1, share2, share3, share4, share5, winDream, win, win1, win2, win3, win4, win5, bonus, bonus1, bonus2, bonus3, bonus4, bonus5, tuishui, tuishui1, tuishui2, tuishui3, tuishui4, tuishui5, ip, hash, stataus, timeDraw, timeIn, timeAdd, timeJiesuan, timePay, isCancel, cancelContent, betTime, winLoseType, reportTime);
    }
}
