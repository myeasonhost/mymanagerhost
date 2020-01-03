package com.eason.transfer.openapi.zb.api.entity;

import java.util.Date;

public class RelationFriendsPo {
    private Integer id;

    private String userId;

    private String relationUserid;

    private Date relationTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRelationUserid() {
        return relationUserid;
    }

    public void setRelationUserid(String relationUserid) {
        this.relationUserid = relationUserid;
    }

    public Date getRelationTime() {
        return relationTime;
    }

    public void setRelationTime(Date relationTime) {
        this.relationTime = relationTime;
    }
}