package com.eason.report.pull.platform.sgs.dao.po;

import com.eason.report.pull.core.base.BaseConfig;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ds_sgs_game_config")
public class DsSgsGameConfigPo extends BaseConfig {
    private int id;
    private String agentId;
    private String gameKindName;
    private String siteId;
    private String clientSecret;
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
    @Column(name = "client_secret")
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
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
    public String toString() {
        return "DsSgsGameConfigPo{" +
                "id=" + id +
                ", agentId='" + agentId + '\'' +
                ", gameKindName='" + gameKindName + '\'' +
                ", siteId='" + siteId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", length=" + length +
                ", pullUrl='" + pullUrl + '\'' +
                ", initStartId=" + initStartId +
                ", state=" + state +
                ", info='" + info + '\'' +
                ", siteMap=" + siteMap +
                '}';
    }
}
