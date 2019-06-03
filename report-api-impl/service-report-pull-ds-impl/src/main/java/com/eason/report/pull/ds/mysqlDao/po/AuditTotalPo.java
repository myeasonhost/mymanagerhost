package com.eason.report.pull.ds.mysqlDao.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "ds_audit_total")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ds_gf_audit_report", procedureName = "ds_gf_audit_report",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "siteId", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "startId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "endId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result", type = String.class)}),
        @NamedStoredProcedureQuery(name = "ds_jd_audit_report", procedureName = "ds_jd_audit_report",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "siteId", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "startId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "endId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result", type = String.class)}),
        @NamedStoredProcedureQuery(name = "mdt_jd_audit_report", procedureName = "mdt_jd_audit_report",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "siteId", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "startId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "endId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result", type = String.class)}),
        @NamedStoredProcedureQuery(name = "ds_mg_audit_report", procedureName = "ds_mg_audit_report",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "siteId", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "startId", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "endId", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result", type = String.class)})})
public class AuditTotalPo {
    private long id;
    private Integer liveId;
    private String orderNo;
    private String username;
    private Timestamp betTime;
    private BigDecimal betAmount;
    private BigDecimal validAmount;
    private BigDecimal payAmount;
    private int type;
    private String gameName;
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
    @Column(name = "live_id")
    public Integer getLiveId() {
        return liveId;
    }

    public void setLiveId(Integer liveId) {
        this.liveId = liveId;
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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "game_name")
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
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
                type == that.type &&
                Objects.equals(liveId, that.liveId) &&
                Objects.equals(orderNo, that.orderNo) &&
                Objects.equals(username, that.username) &&
                Objects.equals(betTime, that.betTime) &&
                Objects.equals(betAmount, that.betAmount) &&
                Objects.equals(validAmount, that.validAmount) &&
                Objects.equals(payAmount, that.payAmount) &&
                Objects.equals(gameName, that.gameName) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, liveId, orderNo, username, betTime, betAmount, validAmount, payAmount, type, gameName, createTime, updateTime);
    }
}
