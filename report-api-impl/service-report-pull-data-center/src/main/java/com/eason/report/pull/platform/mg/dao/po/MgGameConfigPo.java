package com.eason.report.pull.platform.mg.dao.po;

import com.eason.report.pull.core.base.BaseConfig;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ds_mg_game_config")
public class MgGameConfigPo extends BaseConfig {
    private int id;
    private String agentId;
    private String code;
    private String gameKindName;
    private String username;
    private String password;
    private String prex;
    private String siteId;
    private Integer length;
    private String pullUrl;
    private Date initStartId;
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
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    @Column(name = "initStartId")
    public Date getInitStartId() {
        return initStartId;
    }

    public void setInitStartId(Date initStartId) {
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
        MgGameConfigPo that = (MgGameConfigPo) o;
        return id == that.id &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(code, that.code) &&
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
        return Objects.hash(id, agentId, code, gameKindName, username, password, prex, siteId, length, pullUrl, state, info);
    }

    @Override
    public String toString() {
        return "MgGameConfigPo{" +
                "id=" + id +
                ", agentId='" + agentId + '\'' +
                ", code='" + code + '\'' +
                ", gameKindName='" + gameKindName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", prex='" + prex + '\'' +
                ", siteId='" + siteId + '\'' +
                ", length=" + length +
                ", pullUrl='" + pullUrl + '\'' +
                ", initStartId=" + initStartId +
                ", state=" + state +
                ", info='" + info + '\'' +
                '}';
    }
}
