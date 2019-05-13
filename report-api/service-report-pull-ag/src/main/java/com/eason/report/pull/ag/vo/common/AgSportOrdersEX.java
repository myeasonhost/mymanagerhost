package com.eason.report.pull.ag.vo.common;

import java.io.Serializable;

public class AgSportOrdersEX implements Serializable {
    private String cAgent;
    private String startDate;
    private String endDate;
    private String key;

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
}
