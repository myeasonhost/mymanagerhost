package com.eason.transfer.openapi.core.system.base.service;

import com.eason.transfer.openapi.core.system.model.ServiceConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthorServiceImpl{
	protected static final Log log = LogFactory.getLog(AuthorServiceImpl.class);
	
//	public void setSessionAccount(UserExtForm userExtForm) {
//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
//			      .getRequestAttributes()).getRequest();
//		request.getSession().setAttribute(ServiceConstant.SESSIONUSER, userExtForm);
//	}
//
//	public UserExtForm getSessionAccount() {
//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
//			      .getRequestAttributes()).getRequest();
//		return (UserExtForm) request.getSession().getAttribute(ServiceConstant.SESSIONUSER);
//	}
//	/**
//	 * 是否是当前操作用户
//	 */
//	public boolean isCurrentOptAccount(UserExtForm optAccount) {
//		UserExtForm currentPo=this.getSessionAccount();
//		if(currentPo.getId()==optAccount.getId()){
//			return true;
//		}
//		return false;
//	}
//	/**
//	 * 是否是Admin操作用户(admin 是唯一的)
//	 * @return TRUE
//	 */
//	public boolean isAdminOptAccount() {
//		UserExtForm currentPo=this.getSessionAccount();
//		if("admin".equals(currentPo.getAccount())){
//			return true;
//		}
//		return false;
//	}

}
