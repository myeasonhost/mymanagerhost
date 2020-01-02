package com.eason.transfer.openapi.zb.api.entity;

import java.util.Date;

public class ZbRoomReport {
    private Long id;

    private String zbPlanSeqno;

    private Long roomid;

    private String roomname;

    private Long viewCount;

    private Long newFans;

    private Long giftCount;

    private String liveTimeCount;

    private Date startTime;

    private Date stopTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZbPlanSeqno() {
        return zbPlanSeqno;
    }

    public void setZbPlanSeqno(String zbPlanSeqno) {
        this.zbPlanSeqno = zbPlanSeqno;
    }

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getNewFans() {
        return newFans;
    }

    public void setNewFans(Long newFans) {
        this.newFans = newFans;
    }

    public Long getGiftCount() {
        return giftCount;
    }

    public void setGiftCount(Long giftCount) {
        this.giftCount = giftCount;
    }

    public String getLiveTimeCount() {
        return liveTimeCount;
    }

    public void setLiveTimeCount(String liveTimeCount) {
        this.liveTimeCount = liveTimeCount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }
}