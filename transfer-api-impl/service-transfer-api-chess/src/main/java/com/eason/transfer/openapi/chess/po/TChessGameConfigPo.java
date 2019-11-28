package com.eason.transfer.openapi.chess.po;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_chess_game_config")
public class TChessGameConfigPo implements Serializable {
    private int id;
    private String agentId;
    private String gameKind;
    private String gameKindName;
    private String siteId;
    private String desKey;
    private String md5Key;
    private String walletUrl;
    private String transferUrl;
    private String pullUrl;
    private Byte length;
    private Byte status;
    private Timestamp initStartId;
    private String info;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "agent_id")
    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
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
    @Column(name = "game_kind_name")
    public String getGameKindName() {
        return gameKindName;
    }

    public void setGameKindName(String gameKindName) {
        this.gameKindName = gameKindName;
    }

    @Basic
    @Column(name = "site_id")
    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "DesKey")
    public String getDesKey() {
        return desKey;
    }

    public void setDesKey(String desKey) {
        this.desKey = desKey;
    }

    @Basic
    @Column(name = "Md5Key")
    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }

    @Basic
    @Column(name = "walletUrl")
    public String getWalletUrl() {
        return walletUrl;
    }

    public void setWalletUrl(String walletUrl) {
        this.walletUrl = walletUrl;
    }

    @Basic
    @Column(name = "transferUrl")
    public String getTransferUrl() {
        return transferUrl;
    }

    public void setTransferUrl(String transferUrl) {
        this.transferUrl = transferUrl;
    }

    @Basic
    @Column(name = "pullUrl")
    public String getPullUrl() {
        return pullUrl;
    }

    public void setPullUrl(String pullUrl) {
        this.pullUrl = pullUrl;
    }

    @Basic
    @Column(name = "length")
    public Byte getLength() {
        return length;
    }

    public void setLength(Byte length) {
        this.length = length;
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
    @Column(name = "initStartId")
    public Timestamp getInitStartId() {
        return initStartId;
    }

    public void setInitStartId(Timestamp initStartId) {
        this.initStartId = initStartId;
    }

    @Basic
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TChessGameConfigPo that = (TChessGameConfigPo) o;
        return id == that.id &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(gameKind, that.gameKind) &&
                Objects.equals(gameKindName, that.gameKindName) &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(desKey, that.desKey) &&
                Objects.equals(md5Key, that.md5Key) &&
                Objects.equals(walletUrl, that.walletUrl) &&
                Objects.equals(transferUrl, that.transferUrl) &&
                Objects.equals(pullUrl, that.pullUrl) &&
                Objects.equals(length, that.length) &&
                Objects.equals(status, that.status) &&
                Objects.equals(initStartId, that.initStartId) &&
                Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, agentId, gameKind, gameKindName, siteId, desKey, md5Key, walletUrl, transferUrl, pullUrl, length, status, initStartId, info);
    }

    @Override
    public String toString() {
        return "TChessGameConfigPo{" +
                "id=" + id +
                ", agentId='" + agentId + '\'' +
                ", gameKind='" + gameKind + '\'' +
                ", gameKindName='" + gameKindName + '\'' +
                ", siteId='" + siteId + '\'' +
                ", desKey='" + desKey + '\'' +
                ", md5Key='" + md5Key + '\'' +
                ", walletUrl='" + walletUrl + '\'' +
                ", transferUrl='" + transferUrl + '\'' +
                ", pullUrl='" + pullUrl + '\'' +
                ", length=" + length +
                ", status=" + status +
                ", initStartId=" + initStartId +
                ", info='" + info + '\'' +
                '}';
    }
}
