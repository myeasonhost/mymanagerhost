package com.eason.report.pull.core.mysqlDao.config;

import com.eason.report.pull.core.base.BaseConfig;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "mdt_lottery_config")
public class MdtLotteryConfigPo extends BaseConfig {
    private int id;
    private Integer liveId;
    private String liveName;
    private String gameKind;
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
        MdtLotteryConfigPo that = (MdtLotteryConfigPo) o;
        return id == that.id &&
                Objects.equals(liveId, that.liveId) &&
                Objects.equals(liveName, that.liveName) &&
                Objects.equals(gameKind, that.gameKind) &&
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
        return Objects.hash(id, liveId, liveName, gameKind, gameKindName, user, siteId, level, recordUrl, length, state, info);
    }
}
