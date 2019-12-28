<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%String path = request.getContextPath(); %>
<form id="changePwdForm" method="post"  action="<%=path%>/admin/user/changePassword" > 
		<input  name="id" value="${user.id}" type="hidden"/>
	<p>  
		<label style="display: inline-block; width: 120px; text-align: right; padding-right: 5px;"><span style="color:red">*</span><spring:message code="index.OldPassword"/>:</label>
	  	<input id="oldPwd" name="oldPwd" type="password" value="" style="width:200px" class="easyui-validatebox" data-options="required:true,validType:'validOldPwd[\'<%=path%>/admin/user/checkedAccountId?id=${user.id}\']'"/>
 	</p>
	<p>
		<label style="display: inline-block; width: 120px; text-align: right; padding-right: 5px;"><span style="color:red">*</span><spring:message code="login.Password"/>:</label>
	  	<input id="password" name="pwd" type="password" value="" style="width:200px" class="easyui-validatebox" data-options="required:true "/>
 	</p>
  	<p>
		<label style="display: inline-block; width: 120px; text-align: right; padding-right: 5px;"><span style="color:red">*</span><spring:message code="index.ConfirmPassword"/>:</label>
	  	<input type="password" value="" style="width:200px" class="easyui-validatebox" data-options="required:true,validType:'equals[\'#password\']'"/>
 	</p>			 
</form>
 
