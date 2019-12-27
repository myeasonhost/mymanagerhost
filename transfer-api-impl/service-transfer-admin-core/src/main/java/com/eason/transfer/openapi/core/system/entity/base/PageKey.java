package com.eason.transfer.openapi.core.system.entity.base;


public class PageKey extends BaseVo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6011912608284492515L;
	private int page;
	private int rows;
	private String order;
	private String sort;
	
	public PageKey() {
		super();
	}
	public PageKey(int page, int rows, String order, String sort) {
		super();
		this.page = page;
		this.rows = rows;
		this.order = order;
		this.sort = sort;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
}
