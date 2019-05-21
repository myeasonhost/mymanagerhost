//package com.eason.report.pull.ds.po;
//
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import java.util.Objects;
//
//@Entity
//@Table(name = "dt_app_info")
//public class DtAppInfoPo {
//    private String user;
//    private Integer siteId;
//    private Integer level;
//    private String recordUrl;
//    private Integer state;
//
//    @Basic
//    @Column(name = "user")
//    public String getUser() {
//        return user;
//    }
//
//    public void setUser(String user) {
//        this.user = user;
//    }
//
//    @Basic
//    @Column(name = "siteId")
//    public Integer getSiteId() {
//        return siteId;
//    }
//
//    public void setSiteId(Integer siteId) {
//        this.siteId = siteId;
//    }
//
//    @Basic
//    @Column(name = "level")
//    public Integer getLevel() {
//        return level;
//    }
//
//    public void setLevel(Integer level) {
//        this.level = level;
//    }
//
//    @Basic
//    @Column(name = "record_url")
//    public String getRecordUrl() {
//        return recordUrl;
//    }
//
//    public void setRecordUrl(String recordUrl) {
//        this.recordUrl = recordUrl;
//    }
//
//    @Basic
//    @Column(name = "state")
//    public Integer getState() {
//        return state;
//    }
//
//    public void setState(Integer state) {
//        this.state = state;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        DtAppInfoPo that = (DtAppInfoPo) o;
//        return Objects.equals(user, that.user) &&
//                Objects.equals(siteId, that.siteId) &&
//                Objects.equals(level, that.level) &&
//                Objects.equals(recordUrl, that.recordUrl) &&
//                Objects.equals(state, that.state);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(user, siteId, level, recordUrl, state);
//    }
//}
