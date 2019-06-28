package com.eason.report.pull.platform.bbin.dao.po;

import com.eason.report.pull.core.base.BaseConfig;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "ds_bbin_game_config")
public class DsBbinGameConfigPo extends BaseConfig {
    private int id;
    private String agentId;
    private String gameKindName;
    private String siteId;
    private String paramsKey;
    private Integer length;
    private String pullUrl;
    private Timestamp initStartId;
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
    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
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
    @Column(name = "siteId")
    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "params_key")
    public String getParamsKey() {
        return paramsKey;
    }

    public void setParamsKey(String paramsKey) {
        this.paramsKey = paramsKey;
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
    @Column(name = "initStartId")
    public Timestamp getInitStartId() {
        return initStartId;
    }

    public void setInitStartId(Timestamp initStartId) {
        this.initStartId = initStartId;
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
        DsBbinGameConfigPo that = (DsBbinGameConfigPo) o;
        return id == that.id &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(gameKindName, that.gameKindName) &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(length, that.length) &&
                Objects.equals(pullUrl, that.pullUrl) &&
                Objects.equals(initStartId, that.initStartId) &&
                Objects.equals(state, that.state) &&
                Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, agentId, gameKindName, siteId, length, pullUrl, initStartId, state, info);
    }

    @Override
    public String toString() {
        return "DsBbinGameConfigPo{" +
                "id=" + id +
                ", agentId='" + agentId + '\'' +
                ", gameKindName='" + gameKindName + '\'' +
                ", siteId='" + siteId + '\'' +
                ", paramsKey='" + paramsKey + '\'' +
                ", length=" + length +
                ", pullUrl='" + pullUrl + '\'' +
                ", initStartId=" + initStartId +
                ", state=" + state +
                ", info='" + info + '\'' +
                ", siteMap=" + siteMap +
                '}';
    }
}
