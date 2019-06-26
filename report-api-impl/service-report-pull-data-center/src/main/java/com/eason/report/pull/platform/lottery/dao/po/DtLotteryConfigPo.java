package com.eason.report.pull.platform.lottery.dao.po;

import com.eason.report.pull.core.base.BaseConfig;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "dt_lottery_config")
public class DtLotteryConfigPo extends BaseConfig {
    private int id;
    private String code;
    private String gameKindName;
    private String agentId;
    private String siteId;
    private Integer level;
    private String recordUrl;
    private Integer length;
    private Integer initStartId;
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
    @Column(name = "agentId")
    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
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
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "record_url")
    public String getRecordUrl() {
        return recordUrl;
    }

    public void setRecordUrl(String recordUrl) {
        this.recordUrl = recordUrl;
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
    @Column(name = "initStartId")
    public Integer getInitStartId() {
        return initStartId;
    }

    public void setInitStartId(Integer initStartId) {
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
        DtLotteryConfigPo that = (DtLotteryConfigPo) o;
        return id == that.id &&
                Objects.equals(gameKindName, that.gameKindName) &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(level, that.level) &&
                Objects.equals(recordUrl, that.recordUrl) &&
                Objects.equals(length, that.length) &&
                Objects.equals(state, that.state) &&
                Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameKindName, agentId, siteId, level, recordUrl, length, state, info);
    }

    @Override
    public String toString() {
        return "DtLotteryConfigPo{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", gameKindName='" + gameKindName + '\'' +
                ", agentId='" + agentId + '\'' +
                ", siteId='" + siteId + '\'' +
                ", level=" + level +
                ", recordUrl='" + recordUrl + '\'' +
                ", length=" + length +
                ", initStartId=" + initStartId +
                ", state=" + state +
                ", info='" + info + '\'' +
                ", siteMap=" + siteMap +
                '}';
    }
}
