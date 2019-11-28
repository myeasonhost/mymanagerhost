package com.eason.transfer.openapi.user.po;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_chess_game_kind")
public class TChessGameKindPo implements Serializable {
    private int kingId;
    private String kindName;
    private Integer parentKindId;
    private String image;
    private Byte status;
    private String info;
    private String createBy;
    private Timestamp createTime;
    private String updateBy;
    private Timestamp updateTime;

    @Id
    @Column(name = "KingID")
    public int getKingId() {
        return kingId;
    }

    public void setKingId(int kingId) {
        this.kingId = kingId;
    }

    @Basic
    @Column(name = "KindName")
    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    @Basic
    @Column(name = "ParentKindID")
    public Integer getParentKindId() {
        return parentKindId;
    }

    public void setParentKindId(Integer parentKindId) {
        this.parentKindId = parentKindId;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "create_by")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_by")
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TChessGameKindPo that = (TChessGameKindPo) o;
        return kingId == that.kingId &&
                Objects.equals(kindName, that.kindName) &&
                Objects.equals(parentKindId, that.parentKindId) &&
                Objects.equals(image, that.image) &&
                Objects.equals(status, that.status) &&
                Objects.equals(info, that.info) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kingId, kindName, parentKindId, image, status, info, createBy, createTime, updateBy, updateTime);
    }
}
