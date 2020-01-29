package com.ds.money.vo;

import java.util.Date;
import java.util.List;

public class MoneyLogByDateParam {
	private Date beginTime;
	private Date endTime;
	private Integer siteId;
	private List<Integer> fromKeyTypeList;
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	public List<Integer> getFromKeyTypeList() {
		return fromKeyTypeList;
	}
	public void setFromKeyTypeList(List<Integer> fromKeyTypeList) {
		this.fromKeyTypeList = fromKeyTypeList;
	}
	
	
}
