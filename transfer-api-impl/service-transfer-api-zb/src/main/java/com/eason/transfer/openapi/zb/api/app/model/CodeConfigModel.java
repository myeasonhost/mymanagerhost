package com.eason.transfer.openapi.zb.api.app.model;

public class CodeConfigModel {
    private String oneDayNum;  //一天发送验证码的总量
    private String totalNum;  //总共发送验证码的总量
    private String lastTime;  //30分钟内只能发送3次
    private String interval_time;  //过多少（分钟）可以重新发送验证码
    private String code_valid_time;  //验证码失效时间（分钟）
    private String verFailTime;  //多长时间内失败3次则禁止再进行验证
    private String verFialForbidTime;  //如果规定时间内验证失败超过3次，禁止验证多长时间

    public String getOneDayNum() {
        return oneDayNum;
    }

    public void setOneDayNum(String oneDayNum) {
        this.oneDayNum = oneDayNum;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getInterval_time() {
        return interval_time;
    }

    public void setInterval_time(String interval_time) {
        this.interval_time = interval_time;
    }

    public String getCode_valid_time() {
        return code_valid_time;
    }

    public void setCode_valid_time(String code_valid_time) {
        this.code_valid_time = code_valid_time;
    }

    public String getVerFailTime() {
        return verFailTime;
    }

    public void setVerFailTime(String verFailTime) {
        this.verFailTime = verFailTime;
    }

    public String getVerFialForbidTime() {
        return verFialForbidTime;
    }

    public void setVerFialForbidTime(String verFialForbidTime) {
        this.verFialForbidTime = verFialForbidTime;
    }
}
