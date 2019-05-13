package com.eason.report.pull.ag.vo.common;

import java.io.Serializable;

public class Comporders implements Serializable {

    private String cAgent;
    private String startDate;
    private String endDate;
    private String key;
    private String sessionid;
    private String comptype;

    public String getcAgent() {
        return cAgent;
    }

    public void setcAgent(String cAgent) {
        this.cAgent = cAgent;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getComptype() {
        return comptype;
    }

    public void setComptype(String comptype) {
        this.comptype = comptype;
    }
}
