package com.eason.transfer.openapi.chess.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_wallet_correction record")
public class TWalletCorrectionRecordPo implements Serializable {
    private int recordId;
    private String gameKind;
    private String type;
    private BigDecimal amount;
    private Byte status;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String statusLog;

    @Id
    @Column(name = "record_id")
    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    @Basic
    @Column(name = "status_log")
    public String getStatusLog() {
        return statusLog;
    }

    public void setStatusLog(String statusLog) {
        this.statusLog = statusLog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TWalletCorrectionRecordPo that = (TWalletCorrectionRecordPo) o;
        return recordId == that.recordId &&
                Objects.equals(gameKind, that.gameKind) &&
                Objects.equals(type, that.type) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(statusLog, that.statusLog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, gameKind, type, amount, status, createTime, updateTime, statusLog);
    }
}
