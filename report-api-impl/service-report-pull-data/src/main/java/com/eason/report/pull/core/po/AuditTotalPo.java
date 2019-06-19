package com.eason.report.pull.core.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "audit_total")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ds_gf_audit_report", procedureName = "ds_gf_audit_report",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "siteId", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ds_type", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "startId", type = Object.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "endId", type = Object.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result", type = String.class)}),
        @NamedStoredProcedureQuery(name = "ds_jd_audit_report", procedureName = "ds_jd_audit_report",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "siteId", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ds_type", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "startId", type = Object.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "endId", type = Object.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result", type = String.class)}),
        @NamedStoredProcedureQuery(name = "mdt_jd_audit_report", procedureName = "mdt_jd_audit_report",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "siteId", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ds_type", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "startId", type = Object.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "endId", type = Object.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result", type = String.class)}),
        @NamedStoredProcedureQuery(name = "ds_mg_audit_report", procedureName = "ds_mg_audit_report",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "siteId", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ds_type", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "startId", type = Object.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "endId", type = Object.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result", type = String.class)})})
public class AuditTotalPo implements Serializable {
    private long id;
    private String username;
    private String targetId;
    private String orderNo;
    private BigDecimal betAmount;
    private BigDecimal validAmount;
    private BigDecimal payAmount;
    private String gameKind;
    private int gameType;
    private Timestamp betTime;
    private String heartbeat;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    @Column(name = "target_id")
    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    @Basic
    @Column(name = "order_no")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "bet_amount")
    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    @Basic
    @Column(name = "valid_amount")
    public BigDecimal getValidAmount() {
        return validAmount;
    }

    public void setValidAmount(BigDecimal validAmount) {
        this.validAmount = validAmount;
    }

    @Basic
    @Column(name = "pay_amount")
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    @Basic
    @Column(name = "game_kind")
    public String getGameKind() {
        return gameKind;
    }

    public void setGameKind(String gameKind) {
        this.gameKind = gameKind;
    }

    @Basic
    @Column(name = "game_type")
    public int getGameType() {
        return gameType;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }

    @Basic
    @Column(name = "bet_time")
    public Timestamp getBetTime() {
        return betTime;
    }

    public void setBetTime(Timestamp betTime) {
        this.betTime = betTime;
    }

    @Basic
    @Column(name = "heartbeat")
    public String getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(String heartbeat) {
        this.heartbeat = heartbeat;
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
        AuditTotalPo that = (AuditTotalPo) o;
        return id == that.id &&
                gameType == that.gameType &&
                Objects.equals(username, that.username) &&
                Objects.equals(targetId, that.targetId) &&
                Objects.equals(orderNo, that.orderNo) &&
                Objects.equals(betAmount, that.betAmount) &&
                Objects.equals(validAmount, that.validAmount) &&
                Objects.equals(payAmount, that.payAmount) &&
                Objects.equals(gameKind, that.gameKind) &&
                Objects.equals(betTime, that.betTime) &&
                Objects.equals(heartbeat, that.heartbeat) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, targetId, orderNo, betAmount, validAmount, payAmount, gameKind, gameType, betTime, heartbeat, createTime, updateTime);
    }
}
