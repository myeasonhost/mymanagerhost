<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%String path = request.getContextPath(); %>
<img src="<%=path%>/image/logo2.png" style="width: 100%;margin-top: 0px; margin-right: 0px; margin-bottom: 0px; margin-left: 0px;" />
<div style="background: #A6C6A1; width: 100%; margin: 0px 0px; line-height: 25px; text-align: center; font-weight: 700; font-size: 14px; padding-top: 5px">
	<spring:message code="menu.stat"/>
</div>
<div style="width: 100%;height: 84%;">
	 <jsp:include page="/admin/desktop/navigation"/> 
</div>
</body>