<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%String path = request.getContextPath(); %>
<form id='userForm_registUserDialog' method='POST' action="<%=path%>/api/appuser/appuserInfo/registerUser">
		<div style='padding: 10px 20px;'>
		<input type="hidden" id="id" name="id"/>
			<table style='padding: 10px 25px;' >
				<tr style='height: 25px;'>
					<td>联系电话(11位手机号)：</td>
					<td>
						<input type='text' id='registPhone' name="phone" style="width:150px;height:23px;margin:2px;padding:0px;" />
					</td>
					<td>
					<input type="button" id="getCode" value="获取验证码" onclick="userinfoRegisterMgr.getCode()"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>登录密码：</td>
					<td>
						<input type='text' id='password'  name='password' value="E10ADC3949BA59ABBE56E057F20F883E" style="width:150px;height:23px;margin:2px;padding:0px;" readonly="readonly"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>验证码：</td>
					<td>
						<input type='text' id='validateCode'  name='validateCode'  style="width:100px;height:23px;margin:2px;padding:0px;" readonly="readonly" class="easyui-validatebox" data-options="required:true"/>
					</td>
				</tr>
			</table>
		</div>
</form>
