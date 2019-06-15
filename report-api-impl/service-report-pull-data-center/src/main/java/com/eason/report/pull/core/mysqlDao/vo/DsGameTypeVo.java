package com.eason.report.pull.core.mysqlDao.vo;

public interface DsGameTypeVo {
    int getId();
    String getGameName();
    String getOutGameCode();
    Integer getParentId();
    Byte getFkLiveId();
    String getParentName();

    default String toStringInfo() {
        return "id=" + getId() + ",gameName=" + getGameName()
                +",outGameCode=" + getOutGameCode() + ",parentId=" + getParentId()
                +",fkLiveId=" + getFkLiveId() + ",parentName=" + getParentName();
    }
}
