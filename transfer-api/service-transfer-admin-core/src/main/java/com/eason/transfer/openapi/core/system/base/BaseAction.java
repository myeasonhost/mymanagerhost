package com.eason.transfer.openapi.core.system.base;

import com.eason.transfer.openapi.core.system.entity.po.User;
import com.eason.transfer.openapi.core.system.entity.vo.MessageModel;
import com.eason.transfer.openapi.core.system.model.ServiceConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


public class BaseAction {
	protected static final Log log = LogFactory.getLog(BaseAction.class);
	
	protected MessageModel SUCCESS = new MessageModel(true, "操作成功");
	protected MessageModel FAILURE = new MessageModel(false, "操作失败");
	
	
	public void setSessionAccount(User user) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
			      .getRequestAttributes()).getRequest();
		request.getSession().setAttribute(ServiceConstant.SESSIONUSER, user);
	}
	
	public User getSessionAccount() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
			      .getRequestAttributes()).getRequest();
		return (User) request.getSession().getAttribute(ServiceConstant.SESSIONUSER);
	}
	public void removeSessionAccount() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
			      .getRequestAttributes()).getRequest();
		request.getSession().removeAttribute(ServiceConstant.SESSIONUSER);
		request.getSession().invalidate();
	}

}
