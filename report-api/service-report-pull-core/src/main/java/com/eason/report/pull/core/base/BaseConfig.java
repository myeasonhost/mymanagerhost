package com.eason.report.pull.core.base;

import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Map;

public class BaseConfig implements Serializable {
    private Integer length;
    private String agent;
    @Transient
    private Map<Integer,String> siteMap;

    public Map<Integer, String> getSiteMap() {
        return siteMap;
    }

    public void setSiteMap(Map<Integer, String> siteMap) {
        this.siteMap = siteMap;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}
