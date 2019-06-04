package com.eason.report.pull.core.mongo.po;

import com.eason.report.pull.core.mongo.config.GeneratedValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Document(collection = "ds_mg_game")
public class DsMGMgoPo extends BasePo {
    @Id
    @GeneratedValue
    private long id;
    private Integer siteId;
    private String userName;
    private String colId;
    private String agentId;
    private String mbrId;
    private String mbrCode;
    private String transId;
    private Integer winLossType;
    private Integer gameId;
    private String transType;
    private BigDecimal amnt;
    private BigDecimal clrngAmnt;
    private BigDecimal balance;
    private String mgsGameId;
    private String mgsActionId;
    private Timestamp transTime;
    private Integer refTransId;
    private String refTransType;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;
    private String keyB;
    private String memo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getColId() {
        return colId;
    }

    public void setColId(String colId) {
        this.colId = colId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getMbrId() {
        return mbrId;
    }

    public void setMbrId(String mbrId) {
        this.mbrId = mbrId;
    }

    public String getMbrCode() {
        return mbrCode;
    }

    public void setMbrCode(String mbrCode) {
        this.mbrCode = mbrCode;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public Integer getWinLossType() {
        return winLossType;
    }

    public void setWinLossType(Integer winLossType) {
        this.winLossType = winLossType;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public BigDecimal getAmnt() {
        return amnt;
    }

    public void setAmnt(BigDecimal amnt) {
        this.amnt = amnt;
    }

    public BigDecimal getClrngAmnt() {
        return clrngAmnt;
    }

    public void setClrngAmnt(BigDecimal clrngAmnt) {
        this.clrngAmnt = clrngAmnt;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getMgsGameId() {
        return mgsGameId;
    }

    public void setMgsGameId(String mgsGameId) {
        this.mgsGameId = mgsGameId;
    }

    public String getMgsActionId() {
        return mgsActionId;
    }

    public void setMgsActionId(String mgsActionId) {
        this.mgsActionId = mgsActionId;
    }

    public Timestamp getTransTime() {
        return transTime;
    }

    public void setTransTime(Timestamp transTime) {
        this.transTime = transTime;
    }

    public Integer getRefTransId() {
        return refTransId;
    }

    public void setRefTransId(Integer refTransId) {
        this.refTransId = refTransId;
    }

    public String getRefTransType() {
        return refTransType;
    }

    public void setRefTransType(String refTransType) {
        this.refTransType = refTransType;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getKeyB() {
        return keyB;
    }

    public void setKeyB(String keyB) {
        this.keyB = keyB;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
