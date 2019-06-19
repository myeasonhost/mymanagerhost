package com.eason.report.pull.core.base;

import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Map;

public class BaseConfig implements Serializable {

    @Transient
    private Map<Integer,String> siteMap;

    public Map<Integer, String> getSiteMap() {
        return siteMap;
    }

    public void setSiteMap(Map<Integer, String> siteMap) {
        this.siteMap = siteMap;
    }
}
