package com.eason.transfer.openapi.core.api.dao.model;


import com.eason.transfer.openapi.core.api.response.Response;

public class CheckUpdateResponse extends Response {


	private static final long serialVersionUID = 1L;
	/** 是否需要更新(0:不需要;1:需要) */
	private Integer isNeedUpdate;

	public Integer getIsNeedUpdate() {
		return isNeedUpdate;
	}

	public void setIsNeedUpdate(Integer isNeedUpdate) {
		this.isNeedUpdate = isNeedUpdate;
	}
	
	
}
