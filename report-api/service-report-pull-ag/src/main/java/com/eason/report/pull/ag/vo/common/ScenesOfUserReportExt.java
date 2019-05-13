package com.eason.report.pull.ag.vo.common;

import java.io.Serializable;
import java.math.BigDecimal;

public class ScenesOfUserReportExt implements Serializable {
    private String act;
    private String pidtoken;
    private String productid;
    private BigDecimal begintime;
    private BigDecimal endtime;
    private Integer page;
    private String sessionkey;

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getPidtoken() {
        return pidtoken;
    }

    public void setPidtoken(String pidtoken) {
        this.pidtoken = pidtoken;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public BigDecimal getBegintime() {
        return begintime;
    }

    public void setBegintime(BigDecimal begintime) {
        this.begintime = begintime;
    }

    public BigDecimal getEndtime() {
        return endtime;
    }

    public void setEndtime(BigDecimal endtime) {
        this.endtime = endtime;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getSessionkey() {
        return sessionkey;
    }

    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey;
    }
}
