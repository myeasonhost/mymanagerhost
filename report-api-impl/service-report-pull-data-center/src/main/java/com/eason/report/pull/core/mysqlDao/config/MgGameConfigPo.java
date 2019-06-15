package com.eason.report.pull.core.mysqlDao.config;

import com.eason.report.pull.core.base.BaseConfig;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ds_mg_game_config")
public class MgGameConfigPo extends BaseConfig {
    private int id;
    private Integer agentId;
    private Integer liveId;
    private String liveName;
    private String gameKind;
    private String gameKindName;
    private String username;
    private String password;
    private String prex;
    private String siteId;
    private Integer length;
    private String pullUrl;
    private Integer state;
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
    @Column(name = "agentId")
    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
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
    @Column(name = "live_name")
    public String getLiveName() {
        return liveName;
    }

    public void setLiveName(String liveName) {
        this.liveName = liveName;
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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "prex")
    public String getPrex() {
        return prex;
    }

    public void setPrex(String prex) {
        this.prex = prex;
    }

    @Basic
    @Column(name = "siteId")
    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "length")
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
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
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
        MgGameConfigPo that = (MgGameConfigPo) o;
        return id == that.id &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(liveId, that.liveId) &&
                Objects.equals(liveName, that.liveName) &&
                Objects.equals(gameKind, that.gameKind) &&
                Objects.equals(gameKindName, that.gameKindName) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(prex, that.prex) &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(length, that.length) &&
                Objects.equals(pullUrl, that.pullUrl) &&
                Objects.equals(state, that.state) &&
                Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, agentId, liveId, liveName, gameKind, gameKindName, username, password, prex, siteId, length, pullUrl, state, info);
    }
}
