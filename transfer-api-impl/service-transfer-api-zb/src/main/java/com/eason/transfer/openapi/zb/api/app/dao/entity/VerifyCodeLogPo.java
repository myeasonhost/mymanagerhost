package com.eason.transfer.openapi.zb.api.app.dao.entity;

import java.util.Date;

//验证码日志成功失败表
public class VerifyCodeLogPo {


	/**
	 * 
	 */
	private static final long serialVersionUID = -872707114757605971L;
	//主键
	private Integer id;
	//用户手机号
	private String phone;
	//验证时间
	private Date verTime;
	//正确的验证码
	private String trueCode;
	//用户用来验证的错误验证码
	private String errorCode;
	//验证结果
	private Integer verResult;
	//验证类型 注册还是重置
	private Integer type;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTrueCode() {
		return trueCode;
	}
	public void setTrueCode(String trueCode) {
		this.trueCode = trueCode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getVerTime() {
		return verTime;
	}
	public void setVerTime(Date verTime) {
		this.verTime = verTime;
	}
	public Integer getVerResult() {
		return verResult;
	}
	public void setVerResult(Integer verResult) {
		this.verResult = verResult;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
