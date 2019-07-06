package com.eason.report.pull.platform.ag.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ds_ag_hunter")
public class DsAgHunterPo implements Serializable {
    private String billNo;
    private String agentId;
    private Integer siteId;
    private String username;
    private String playName;
    private Integer roomId;
    private String sceneId;
    private Date startTime;
    private Date endTime;
    private Integer bulletOutNum;
    private BigDecimal roomBet;
    private BigDecimal cost;
    private BigDecimal earn;
    private BigDecimal win;
    private Byte winLossType;
    private BigDecimal jpDraw;
    private BigDecimal sceneEx;
    private BigDecimal jackPotComm;
    private BigDecimal bombDraw;
    private String remark;
    private Integer operType;
    private Integer billHitNum;
    private Integer billType;
    private Date createTime;
    private Date updateTime;
    private Integer mission;
    private Integer wb;

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
    @Column(name = "roomId")
    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "sceneId")
    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    @Basic
    @Column(name = "startTime")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "endTime")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "bulletOutNum")
    public Integer getBulletOutNum() {
        return bulletOutNum;
    }

    public void setBulletOutNum(Integer bulletOutNum) {
        this.bulletOutNum = bulletOutNum;
    }

    @Basic
    @Column(name = "roomBet")
    public BigDecimal getRoomBet() {
        return roomBet;
    }

    public void setRoomBet(BigDecimal roomBet) {
        this.roomBet = roomBet;
    }

    @Basic
    @Column(name = "cost")
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "earn")
    public BigDecimal getEarn() {
        return earn;
    }

    public void setEarn(BigDecimal earn) {
        this.earn = earn;
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
    @Column(name = "win_loss_type")
    public Byte getWinLossType() {
        return winLossType;
    }

    public void setWinLossType(Byte winLossType) {
        this.winLossType = winLossType;
    }

    @Basic
    @Column(name = "jpDraw")
    public BigDecimal getJpDraw() {
        return jpDraw;
    }

    public void setJpDraw(BigDecimal jpDraw) {
        this.jpDraw = jpDraw;
    }

    @Basic
    @Column(name = "sceneEx")
    public BigDecimal getSceneEx() {
        return sceneEx;
    }

    public void setSceneEx(BigDecimal sceneEx) {
        this.sceneEx = sceneEx;
    }

    @Basic
    @Column(name = "jackPotComm")
    public BigDecimal getJackPotComm() {
        return jackPotComm;
    }

    public void setJackPotComm(BigDecimal jackPotComm) {
        this.jackPotComm = jackPotComm;
    }

    @Basic
    @Column(name = "bombDraw")
    public BigDecimal getBombDraw() {
        return bombDraw;
    }

    public void setBombDraw(BigDecimal bombDraw) {
        this.bombDraw = bombDraw;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "operType")
    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    @Basic
    @Column(name = "billHitNum")
    public Integer getBillHitNum() {
        return billHitNum;
    }

    public void setBillHitNum(Integer billHitNum) {
        this.billHitNum = billHitNum;
    }

    @Basic
    @Column(name = "billType")
    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
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

    @Basic
    @Column(name = "mission")
    public Integer getMission() {
        return mission;
    }

    public void setMission(Integer mission) {
        this.mission = mission;
    }

    @Basic
    @Column(name = "WB")
    public Integer getWb() {
        return wb;
    }

    public void setWb(Integer wb) {
        this.wb = wb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DsAgHunterPo that = (DsAgHunterPo) o;
        return Objects.equals(billNo, that.billNo) &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(playName, that.playName) &&
                Objects.equals(roomId, that.roomId) &&
                Objects.equals(sceneId, that.sceneId) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(bulletOutNum, that.bulletOutNum) &&
                Objects.equals(roomBet, that.roomBet) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(earn, that.earn) &&
                Objects.equals(win, that.win) &&
                Objects.equals(winLossType, that.winLossType) &&
                Objects.equals(jpDraw, that.jpDraw) &&
                Objects.equals(sceneEx, that.sceneEx) &&
                Objects.equals(jackPotComm, that.jackPotComm) &&
                Objects.equals(bombDraw, that.bombDraw) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(operType, that.operType) &&
                Objects.equals(billHitNum, that.billHitNum) &&
                Objects.equals(billType, that.billType) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(mission, that.mission) &&
                Objects.equals(wb, that.wb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billNo, agentId, siteId, username, playName, roomId, sceneId, startTime, endTime, bulletOutNum, roomBet, cost, earn, win, winLossType, jpDraw, sceneEx, jackPotComm, bombDraw, remark, operType, billHitNum, billType, createTime, updateTime, mission, wb);
    }
}
