package com.eason.transfer.openapi.core.api.dao.model;

import com.eason.transfer.openapi.core.api.request.Request;

public class CheckUpdateRequest extends Request {

	private static final long serialVersionUID = 1L;
	/** app版本号 */
	private String appVersion;

	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	
}
