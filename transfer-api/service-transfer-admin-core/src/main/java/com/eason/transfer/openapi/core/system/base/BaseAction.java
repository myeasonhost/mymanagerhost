package com.eason.transfer.openapi.core.system.base;

import com.eason.transfer.openapi.core.system.entity.vo.MessageModel;
import com.eason.transfer.openapi.core.system.entity.vo.UserExtForm;
import com.eason.transfer.openapi.core.system.model.ServiceConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


public class BaseAction {
	protected static final Log log = LogFactory.getLog(BaseAction.class);
	
	protected MessageModel SUCCESS = new MessageModel(true, "操作成功");
	protected MessageModel FAILURE = new MessageModel(false, "操作失败");
	
	
	public void setSessionAccount(UserExtForm userExtForm) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
			      .getRequestAttributes()).getRequest();
		request.getSession().setAttribute(ServiceConstant.SESSIONUSER, userExtForm);
	}
	
	public UserExtForm getSessionAccount() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
			      .getRequestAttributes()).getRequest();
		return (UserExtForm) request.getSession().getAttribute(ServiceConstant.SESSIONUSER);
	}
	public void removeSessionAccount() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
			      .getRequestAttributes()).getRequest();
		request.getSession().removeAttribute(ServiceConstant.SESSIONUSER);
		request.getSession().invalidate();
	}
	
	public final String getAppBaseUrl(String url) throws Exception {
		if(!StringUtils.isEmpty(url) && url.startsWith("/")){
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
				      .getRequestAttributes()).getRequest();
			return request.getContextPath()+url;
		}else{
			throw new Exception("URL ERROR");
		}
	}
}
