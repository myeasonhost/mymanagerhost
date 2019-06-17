package com.eason.report.pull.core.mysqlDao.config;

import com.eason.report.pull.core.base.BaseConfig;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dt_lottery_config")
public class DtLotteryConfigPo extends BaseConfig {
    private int id;
    private String code;
    private String gameKindName;
    private String user;
    private String siteId;
    private Integer level;
    private String recordUrl;
    private Integer length;
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
    @Column(name = "user")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
                Objects.equals(user, that.user) &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(level, that.level) &&
                Objects.equals(recordUrl, that.recordUrl) &&
                Objects.equals(length, that.length) &&
                Objects.equals(state, that.state) &&
                Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameKindName, user, siteId, level, recordUrl, length, state, info);
    }

    @Override
    public String toString() {
        return "DtLotteryConfigPo{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", gameKindName='" + gameKindName + '\'' +
                ", user='" + user + '\'' +
                ", siteId='" + siteId + '\'' +
                ", level=" + level +
                ", recordUrl='" + recordUrl + '\'' +
                ", length=" + length +
                ", state=" + state +
                ", info='" + info + '\'' +
                '}';
    }
}
