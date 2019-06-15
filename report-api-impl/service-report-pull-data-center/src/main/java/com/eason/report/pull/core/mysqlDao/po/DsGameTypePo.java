package com.eason.report.pull.core.mysqlDao.po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ds_game_type")
public class DsGameTypePo {
    private int id;
    private String gameName;
    private String outGameCode;
    private Integer parentId;
    private Byte fkLiveId;
    private String parentName;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "game_name")
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Basic
    @Column(name = "out_game_code")
    public String getOutGameCode() {
        return outGameCode;
    }

    public void setOutGameCode(String outGameCode) {
        this.outGameCode = outGameCode;
    }

    @Basic
    @Column(name = "parent_id")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "fk_live_id")
    public Byte getFkLiveId() {
        return fkLiveId;
    }

    public void setFkLiveId(Byte fkLiveId) {
        this.fkLiveId = fkLiveId;
    }

    @Transient
    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DsGameTypePo that = (DsGameTypePo) o;
        return id == that.id &&
                Objects.equals(gameName, that.gameName) &&
                Objects.equals(outGameCode, that.outGameCode) &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(fkLiveId, that.fkLiveId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameName, outGameCode, parentId, fkLiveId);
    }

    @Override
    public String toString() {
        return "DsGameTypePo{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", outGameCode='" + outGameCode + '\'' +
                ", parentId=" + parentId +
                ", fkLiveId=" + fkLiveId +
                ", parentName='" + parentName + '\'' +
                '}';
    }
}
