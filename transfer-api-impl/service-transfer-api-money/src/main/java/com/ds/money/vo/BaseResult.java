package com.ds.money.vo;

import java.io.Serializable;

public class BaseResult implements Serializable{
	private static final long serialVersionUID = 3587142386599912944L;
	private Integer code;
	private String message;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
