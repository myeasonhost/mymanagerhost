package com.ds.money.vo;

import java.math.BigDecimal;
import java.util.Date;

public class MoneyLogByDateVo {
	private Date transDate;
	private BigDecimal transMoney;
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	public BigDecimal getTransMoney() {
		return transMoney;
	}
	public void setTransMoney(BigDecimal transMoney) {
		this.transMoney = transMoney;
	}
	
}
