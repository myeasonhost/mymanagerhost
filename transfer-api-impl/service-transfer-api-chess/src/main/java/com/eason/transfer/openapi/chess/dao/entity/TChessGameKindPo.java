package com.eason.transfer.openapi.chess.dao.entity;

import java.util.Date;

public class TChessGameKindPo {
    private Integer kingid;

    private String kindname;

    private Integer parentkindid;

    private String image;

    private Byte status;

    private String info;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    public Integer getKingid() {
        return kingid;
    }

    public void setKingid(Integer kingid) {
        this.kingid = kingid;
    }

    public String getKindname() {
        return kindname;
    }

    public void setKindname(String kindname) {
        this.kindname = kindname;
    }

    public Integer getParentkindid() {
        return parentkindid;
    }

    public void setParentkindid(Integer parentkindid) {
        this.parentkindid = parentkindid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}