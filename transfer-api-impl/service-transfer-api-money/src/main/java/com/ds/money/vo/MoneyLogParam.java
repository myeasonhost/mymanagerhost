package com.ds.money.vo;

import java.util.Date;
import java.util.List;

import com.ds.money.entity.DsMemberMoneyLog;

public class MoneyLogParam extends DsMemberMoneyLog{
	private Date beginTime;
	private Date endTime;
	private String fromKeyTypeIsTotal;//否是统计现金流
	private String userInfoIsDetail;
	private String agentLevel;
	private List<Integer> fromKeyTypeList;
	private String agentName;
	private String remitno;
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
	public List<Integer> getFromKeyTypeList() {
		return fromKeyTypeList;
	}
	public void setFromKeyTypeList(List<Integer> fromKeyTypeList) {
		this.fromKeyTypeList = fromKeyTypeList;
	}
	public String getFromKeyTypeIsTotal() {
		return fromKeyTypeIsTotal;
	}
	public void setFromKeyTypeIsTotal(String fromKeyTypeIsTotal) {
		this.fromKeyTypeIsTotal = fromKeyTypeIsTotal;
	}
	public String getUserInfoIsDetail() {
		return userInfoIsDetail;
	}
	public void setUserInfoIsDetail(String userInfoIsDetail) {
		this.userInfoIsDetail = userInfoIsDetail;
	}
	public String getAgentLevel() {
		return agentLevel;
	}
	public void setAgentLevel(String agentLevel) {
		this.agentLevel = agentLevel;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getRemitno() {
		return remitno;
	}
	public void setRemitno(String remitno) {
		this.remitno = remitno;
	}
	
	
}
