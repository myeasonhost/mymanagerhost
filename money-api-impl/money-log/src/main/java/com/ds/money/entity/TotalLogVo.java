package com.ds.money.entity;

import java.math.BigDecimal;

public class TotalLogVo {
	private Integer siteId;
	private BigDecimal remit;
	private String transDate;
	private Integer fromKeyType;
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	public BigDecimal getRemit() {
		return remit;
	}
	public void setRemit(BigDecimal remit) {
		this.remit = remit;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public Integer getFromKeyType() {
		return fromKeyType;
	}
	public void setFromKeyType(Integer fromKeyType) {
		this.fromKeyType = fromKeyType;
	}
	
	
}
