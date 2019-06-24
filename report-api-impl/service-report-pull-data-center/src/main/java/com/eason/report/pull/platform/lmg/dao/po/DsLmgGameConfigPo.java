package com.eason.report.pull.platform.lmg.dao.po;

import com.eason.report.pull.core.base.BaseConfig;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ds_lmg_game_config")
public class DsLmgGameConfigPo extends BaseConfig {
    private int id;
    private String agentId;
    private String gameKindName;
    private String hashcode;
    private String siteId;
    private Integer length;
    private String pullUrl;
    private Integer state;
    private Long initStartId;
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
    @Column(name = "hashcode")
    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
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
    @Column(name = "initStartId")
    public Long getInitStartId() {
        return initStartId;
    }

    public void setInitStartId(Long initStartId) {
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
        DsLmgGameConfigPo that = (DsLmgGameConfigPo) o;
        return id == that.id &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(gameKindName, that.gameKindName) &&
                Objects.equals(hashcode, that.hashcode) &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(length, that.length) &&
                Objects.equals(pullUrl, that.pullUrl) &&
                Objects.equals(state, that.state) &&
                Objects.equals(initStartId, that.initStartId) &&
                Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, agentId, gameKindName, hashcode, siteId, length, pullUrl, state, initStartId, info);
    }

    @Override
    public String toString() {
        return "DsLmgGameConfigPo{" +
                "id=" + id +
                ", agentId='" + agentId + '\'' +
                ", gameKindName='" + gameKindName + '\'' +
                ", hashcode='" + hashcode + '\'' +
                ", siteId='" + siteId + '\'' +
                ", length=" + length +
                ", pullUrl='" + pullUrl + '\'' +
                ", state=" + state +
                ", initStartId=" + initStartId +
                ", info='" + info + '\'' +
                '}';
    }
}
