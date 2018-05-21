package com.eason.api.zb.vo.zhubo;

import java.io.Serializable;

public class MgrStartPlayRequestVo implements Serializable {
    private String roomType;   //房间类型
    private String roomTitle;      //房间标题
    private Long startTime;    //开始时间
    private Integer activityTime;     //继续时间=[30,60,90,120]
    private Integer price;    //每分钟单价=[1,2,5,10],门票单价=[20,50,100,120]
    private String  playUrl;    //拉流地址
    private String  pushUrl;    //推流地址

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public Integer getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Integer activityTime) {
        this.activityTime = activityTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getPushUrl() {
        return pushUrl;
    }

    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }
}
