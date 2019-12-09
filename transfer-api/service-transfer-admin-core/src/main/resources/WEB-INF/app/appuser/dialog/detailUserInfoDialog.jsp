<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%String path = request.getContextPath(); %>
<form id='userForm_detailUserInfoDialog' method='POST' encType="multipart/form-data" action="<%=path%>/api/appuser/userInfo/update">
		<div style='padding: 10px 20px;'>
			<table style='padding: 10px 25px;' >
				<tr style='height: 25px;'>
					<td>用户ID：</td>
					<td>
						<input type='text' id='userId' name="userId" style="width:100px;height:23px;margin:2px;padding:0px;"  readOnly="readOnly"/>
					</td>
					<td>昵称：</td>
					<td>
						<input type='text' id='nickName' name="nickName" style="width:100px;height:23px;margin:2px;padding:0px;color: green;" />
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>性别：</td>
					<td>
						<select id="gender" name="gender" style="width:120px;color: green;">
							<option value="男" selected="selected">男</option>
							<option value="女">女</option>
						</select>
					</td>
					<td>生日：</td>
					<td>
						<input name="birth" id="birth" style="width:140px;color: green;" class="easyui-datetimebox" data-options="formatter:function(date){
					var y = date.getFullYear();
					var m = date.getMonth()+1;
					var d = date.getDate();
					return y + '-' + (m &lt; 10 ? ('0' + m) : m) + '-' + (d &lt; 10 ? ('0' + d) : d)
					}"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>用户手机号码：</td>
					<td>
						<input type='text' id='phone'  name='phone'  style="width:100px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>图像：</td>
					<td>
						<img id='userLogo' name="userLogo"  style="width:80px; height:80px;border:1px solid #000000;">
					</td>
					<td>修改（上传）图像：</td>
					<td>
						<input type="file" id='getLogo' name="logoFile"  style="width:200px; height:40px;">
					</td>
				</tr>
			</table>
		</div>
</form>
