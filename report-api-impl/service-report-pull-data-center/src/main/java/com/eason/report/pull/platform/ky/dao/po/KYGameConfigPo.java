package com.eason.report.pull.platform.ky.dao.po;

import com.eason.report.pull.core.base.BaseConfig;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ky_game_config")
public class KYGameConfigPo extends BaseConfig {
    private int id;
    private String agentId;
    private String gameKindName;
    private String siteId;
    private String desKey;
    private String md5Key;
    private Integer length;
    private String pullUrl;
    private Integer state;
    private Date initStartId;
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
    public Date getInitStartId() {
        return initStartId;
    }

    public void setInitStartId(Date initStartId) {
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
        KYGameConfigPo that = (KYGameConfigPo) o;
        return id == that.id &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(gameKindName, that.gameKindName) &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(desKey, that.desKey) &&
                Objects.equals(md5Key, that.md5Key) &&
                Objects.equals(length, that.length) &&
                Objects.equals(pullUrl, that.pullUrl) &&
                Objects.equals(state, that.state) &&
                Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, agentId, gameKindName, siteId, desKey, md5Key, length, pullUrl, state, info);
    }

    @Override
    public String toString() {
        return "KYGameConfigPo{" +
                "id=" + id +
                ", agentId='" + agentId + '\'' +
                ", gameKindName='" + gameKindName + '\'' +
                ", siteId='" + siteId + '\'' +
                ", desKey='" + desKey + '\'' +
                ", md5Key='" + md5Key + '\'' +
                ", length=" + length +
                ", pullUrl='" + pullUrl + '\'' +
                ", state=" + state +
                ", info='" + info + '\'' +
                '}';
    }
}
