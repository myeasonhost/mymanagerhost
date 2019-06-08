package com.eason.report.pull.core.base;

import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Map;

public class BaseConfig implements Serializable {
    private String code;
    private Integer liveId;
    private String liveName;
    private String gameKind;
    private String gameKindName;
    @Transient
    private Map<Integer,String> siteMap;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLiveId() {
        return liveId;
    }

    public void setLiveId(Integer liveId) {
        this.liveId = liveId;
    }

    public String getLiveName() {
        return liveName;
    }

    public void setLiveName(String liveName) {
        this.liveName = liveName;
    }

    public String getGameKind() {
        return gameKind;
    }

    public void setGameKind(String gameKind) {
        this.gameKind = gameKind;
    }

    public String getGameKindName() {
        return gameKindName;
    }

    public void setGameKindName(String gameKindName) {
        this.gameKindName = gameKindName;
    }

    public Map<Integer, String> getSiteMap() {
        return siteMap;
    }

    public void setSiteMap(Map<Integer, String> siteMap) {
        this.siteMap = siteMap;
    }
}
