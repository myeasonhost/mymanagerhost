package com.eason.report.pull.platform.lottery.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "dt_guangfang_lottery")
public class DtGuangfangLotteryPo implements Serializable {
    private Long id;
    private Integer siteId;
    private String nid;
    private Integer lid;
    private Integer uid;
    private String userName;
    private String top1;
    private String top2;
    private String top3;
    private String top4;
    private Integer proportion1;
    private Integer proportion2;
    private Integer proportion3;
    private Integer proportion4;
    private Integer proportion5;
    private String pan;
    private Long issue;
    private Long mgId;
    private Long traceId;
    private Integer singleNum;
    private Integer multiple;
    private BigDecimal modes;
    private String odd;
    private BigDecimal amount;
    private BigDecimal amount1;
    private BigDecimal amount2;
    private BigDecimal amount3;
    private BigDecimal amount4;
    private BigDecimal amount5;
    private BigDecimal wins;
    private BigDecimal win1;
    private BigDecimal win2;
    private BigDecimal win3;
    private BigDecimal win4;
    private BigDecimal win5;
    private String hitDetail;
    private String proxyIp;
    private String uIp;
    private String serverIp;
    private String hashValue;
    private String code;
    private String updateTime;
    private String drawTime;
    private String sendPrizeTime;
    private String addTime;
    private String jiesuanTime;
    private String cancelTime;
    private Integer isTake;
    private Integer isCancel;
    private Integer isJiesuan;
    private Integer isPay;
    private Integer cancelAdminId;
    private Date betTime;
    private Byte winLoseType;
    private Date reportTime;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    @Basic
    @Column(name = "uid")
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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
    @Column(name = "top1")
    public String getTop1() {
        return top1;
    }

    public void setTop1(String top1) {
        this.top1 = top1;
    }

    @Basic
    @Column(name = "top2")
    public String getTop2() {
        return top2;
    }

    public void setTop2(String top2) {
        this.top2 = top2;
    }

    @Basic
    @Column(name = "top3")
    public String getTop3() {
        return top3;
    }

    public void setTop3(String top3) {
        this.top3 = top3;
    }

    @Basic
    @Column(name = "top4")
    public String getTop4() {
        return top4;
    }

    public void setTop4(String top4) {
        this.top4 = top4;
    }

    @Basic
    @Column(name = "proportion1")
    public Integer getProportion1() {
        return proportion1;
    }

    public void setProportion1(Integer proportion1) {
        this.proportion1 = proportion1;
    }

    @Basic
    @Column(name = "proportion2")
    public Integer getProportion2() {
        return proportion2;
    }

    public void setProportion2(Integer proportion2) {
        this.proportion2 = proportion2;
    }

    @Basic
    @Column(name = "proportion3")
    public Integer getProportion3() {
        return proportion3;
    }

    public void setProportion3(Integer proportion3) {
        this.proportion3 = proportion3;
    }

    @Basic
    @Column(name = "proportion4")
    public Integer getProportion4() {
        return proportion4;
    }

    public void setProportion4(Integer proportion4) {
        this.proportion4 = proportion4;
    }

    @Basic
    @Column(name = "proportion5")
    public Integer getProportion5() {
        return proportion5;
    }

    public void setProportion5(Integer proportion5) {
        this.proportion5 = proportion5;
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
    @Column(name = "issue")
    public Long getIssue() {
        return issue;
    }

    public void setIssue(Long issue) {
        this.issue = issue;
    }

    @Basic
    @Column(name = "mg_id")
    public Long getMgId() {
        return mgId;
    }

    public void setMgId(Long mgId) {
        this.mgId = mgId;
    }

    @Basic
    @Column(name = "trace_id")
    public Long getTraceId() {
        return traceId;
    }

    public void setTraceId(Long traceId) {
        this.traceId = traceId;
    }

    @Basic
    @Column(name = "single_num")
    public Integer getSingleNum() {
        return singleNum;
    }

    public void setSingleNum(Integer singleNum) {
        this.singleNum = singleNum;
    }

    @Basic
    @Column(name = "multiple")
    public Integer getMultiple() {
        return multiple;
    }

    public void setMultiple(Integer multiple) {
        this.multiple = multiple;
    }

    @Basic
    @Column(name = "modes")
    public BigDecimal getModes() {
        return modes;
    }

    public void setModes(BigDecimal modes) {
        this.modes = modes;
    }

    @Basic
    @Column(name = "odd")
    public String getOdd() {
        return odd;
    }

    public void setOdd(String odd) {
        this.odd = odd;
    }

    @Basic
    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "amount1")
    public BigDecimal getAmount1() {
        return amount1;
    }

    public void setAmount1(BigDecimal amount1) {
        this.amount1 = amount1;
    }

    @Basic
    @Column(name = "amount2")
    public BigDecimal getAmount2() {
        return amount2;
    }

    public void setAmount2(BigDecimal amount2) {
        this.amount2 = amount2;
    }

    @Basic
    @Column(name = "amount3")
    public BigDecimal getAmount3() {
        return amount3;
    }

    public void setAmount3(BigDecimal amount3) {
        this.amount3 = amount3;
    }

    @Basic
    @Column(name = "amount4")
    public BigDecimal getAmount4() {
        return amount4;
    }

    public void setAmount4(BigDecimal amount4) {
        this.amount4 = amount4;
    }

    @Basic
    @Column(name = "amount5")
    public BigDecimal getAmount5() {
        return amount5;
    }

    public void setAmount5(BigDecimal amount5) {
        this.amount5 = amount5;
    }

    @Basic
    @Column(name = "wins")
    public BigDecimal getWins() {
        return wins;
    }

    public void setWins(BigDecimal wins) {
        this.wins = wins;
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
    @Column(name = "hit_detail")
    public String getHitDetail() {
        return hitDetail;
    }

    public void setHitDetail(String hitDetail) {
        this.hitDetail = hitDetail;
    }

    @Basic
    @Column(name = "proxy_ip")
    public String getProxyIp() {
        return proxyIp;
    }

    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
    }

    @Basic
    @Column(name = "u_ip")
    public String getuIp() {
        return uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    @Basic
    @Column(name = "server_ip")
    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    @Basic
    @Column(name = "hash_value")
    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "update_time")
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "draw_time")
    public String getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(String drawTime) {
        this.drawTime = drawTime;
    }

    @Basic
    @Column(name = "send_prize_time")
    public String getSendPrizeTime() {
        return sendPrizeTime;
    }

    public void setSendPrizeTime(String sendPrizeTime) {
        this.sendPrizeTime = sendPrizeTime;
    }

    @Basic
    @Column(name = "add_time")
    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    @Basic
    @Column(name = "jiesuan_time")
    public String getJiesuanTime() {
        return jiesuanTime;
    }

    public void setJiesuanTime(String jiesuanTime) {
        this.jiesuanTime = jiesuanTime;
    }

    @Basic
    @Column(name = "cancel_time")
    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    @Basic
    @Column(name = "is_take")
    public Integer getIsTake() {
        return isTake;
    }

    public void setIsTake(Integer isTake) {
        this.isTake = isTake;
    }

    @Basic
    @Column(name = "is_cancel")
    public Integer getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
    }

    @Basic
    @Column(name = "is_jiesuan")
    public Integer getIsJiesuan() {
        return isJiesuan;
    }

    public void setIsJiesuan(Integer isJiesuan) {
        this.isJiesuan = isJiesuan;
    }

    @Basic
    @Column(name = "is_pay")
    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    @Basic
    @Column(name = "cancel_admin_id")
    public Integer getCancelAdminId() {
        return cancelAdminId;
    }

    public void setCancelAdminId(Integer cancelAdminId) {
        this.cancelAdminId = cancelAdminId;
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
    public Byte getWinLoseType() {
        return winLoseType;
    }

    public void setWinLoseType(Byte winLoseType) {
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
        DtGuangfangLotteryPo that = (DtGuangfangLotteryPo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(nid, that.nid) &&
                Objects.equals(lid, that.lid) &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(top1, that.top1) &&
                Objects.equals(top2, that.top2) &&
                Objects.equals(top3, that.top3) &&
                Objects.equals(top4, that.top4) &&
                Objects.equals(proportion1, that.proportion1) &&
                Objects.equals(proportion2, that.proportion2) &&
                Objects.equals(proportion3, that.proportion3) &&
                Objects.equals(proportion4, that.proportion4) &&
                Objects.equals(proportion5, that.proportion5) &&
                Objects.equals(pan, that.pan) &&
                Objects.equals(issue, that.issue) &&
                Objects.equals(mgId, that.mgId) &&
                Objects.equals(traceId, that.traceId) &&
                Objects.equals(singleNum, that.singleNum) &&
                Objects.equals(multiple, that.multiple) &&
                Objects.equals(modes, that.modes) &&
                Objects.equals(odd, that.odd) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(amount1, that.amount1) &&
                Objects.equals(amount2, that.amount2) &&
                Objects.equals(amount3, that.amount3) &&
                Objects.equals(amount4, that.amount4) &&
                Objects.equals(amount5, that.amount5) &&
                Objects.equals(wins, that.wins) &&
                Objects.equals(win1, that.win1) &&
                Objects.equals(win2, that.win2) &&
                Objects.equals(win3, that.win3) &&
                Objects.equals(win4, that.win4) &&
                Objects.equals(win5, that.win5) &&
                Objects.equals(hitDetail, that.hitDetail) &&
                Objects.equals(proxyIp, that.proxyIp) &&
                Objects.equals(uIp, that.uIp) &&
                Objects.equals(serverIp, that.serverIp) &&
                Objects.equals(hashValue, that.hashValue) &&
                Objects.equals(code, that.code) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(drawTime, that.drawTime) &&
                Objects.equals(sendPrizeTime, that.sendPrizeTime) &&
                Objects.equals(addTime, that.addTime) &&
                Objects.equals(jiesuanTime, that.jiesuanTime) &&
                Objects.equals(cancelTime, that.cancelTime) &&
                Objects.equals(isTake, that.isTake) &&
                Objects.equals(isCancel, that.isCancel) &&
                Objects.equals(isJiesuan, that.isJiesuan) &&
                Objects.equals(isPay, that.isPay) &&
                Objects.equals(cancelAdminId, that.cancelAdminId) &&
                Objects.equals(betTime, that.betTime) &&
                Objects.equals(winLoseType, that.winLoseType) &&
                Objects.equals(reportTime, that.reportTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, siteId, nid, lid, uid, userName, top1, top2, top3, top4, proportion1, proportion2, proportion3, proportion4, proportion5, pan, issue, mgId, traceId, singleNum, multiple, modes, odd, amount, amount1, amount2, amount3, amount4, amount5, wins, win1, win2, win3, win4, win5, hitDetail, proxyIp, uIp, serverIp, hashValue, code, updateTime, drawTime, sendPrizeTime, addTime, jiesuanTime, cancelTime, isTake, isCancel, isJiesuan, isPay, cancelAdminId, betTime, winLoseType, reportTime);
    }
}
