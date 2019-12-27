package com.eason.transfer.openapi.core.system.entity.base;

import java.util.List;

public class PageVo<T> extends BaseVo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -189291952539218337L;
	private long total;
	private List<T> rows;
	
	public PageVo() {
		super();
	}
	public PageVo(long total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
}
