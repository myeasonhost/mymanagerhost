<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%String path = request.getContextPath(); %>
<form id='userForm_detailVcodeDialog' method='POST'>
		<div style='padding: 10px 20px;'>
		<input type="hidden" id="id" name="id"/>
			<table style='padding: 10px 25px;' >
				<tr style='height: 25px;'>
					<td>主键ID：</td>
					<td>
						<input type='text' id='id' name="id1" style="width:100px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
					<td>用户昵称：</td>
					<td>
						<input type="hidden" id='userId' name="userId1" />
						<input type='text' id='nickName' name="nickName1" style="width:100px;height:23px;margin:2px;padding:0px;color: green;"   disabled="disabled"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>验证码：</td>
					<td>
						<input type='text' id='code' name="code1" style="width:100px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
					<td>类型：</td>
					<td>
						<input type='text' id='type' name="type1" style="width:100px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>发送时间：</td>
					<td>
						<input type='text' id='sendTime' name='sendTime1' style="width:130px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
					<td>手机号：</td>
					<td>
						<input type='text' id='phone' name="phone1" style="width:130px;height:23px;margin:2px;padding:0px;color: green;"   disabled="disabled"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>更新时间：</td>
					<td>
						<input type='text' id='updateTime' name='updateTime1' style="width:130px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
					<td>状态：</td>
					<td>
						<input type='text' id='state' name="state1" style="width:100px;height:23px;margin:2px;padding:0px;color: green;"   disabled="disabled"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>设备MAC：</td>
					<td>
						<input type='text' id='mac' name='mac1' style="width:130px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
				</tr>
			</table>
		</div>
	</form>
