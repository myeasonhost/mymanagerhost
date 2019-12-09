<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%String path = request.getContextPath(); %>
<html>
	<head>
	<title>任务详情</title>
	</head>
	<body style="padding: 0;margin: 0">
<form id='userForm_detailPushMsgDialog' method='POST'>
		<div style='padding: 10px 20px;'>
			<table style='padding: 10px 25px;' >
				<tr style='height: 25px;'>
					<td>推送ID：</td>
					<td>
						<input type='text' id='pushId' name="pushId" style="width:130px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
					<td>推送code：</td>
					<td>
						<input type='text' id='pushCode' name="pushCode" style="width:130px;height:23px;margin:2px;padding:0px;color: green;"   disabled="disabled"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>接收人ID：</td>
					<td>
						<input type='text' id='toUserId' name="toUserId" style="width:130px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
					<td>接收人昵称：</td>
					<td>
						<input type='text' id='toUserName' name="toUserName" style="width:130px;height:23px;margin:2px;padding:0px;color: green;"   disabled="disabled"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>推送标题：</td>
					<td>
						<input type='text' id='pushTitle' name="pushTitle" style="width:130px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>推送内容：</td>
					<td colspan="2">
						<textarea id='pushContent' name="pushContent" style="width: 350px;height: 46px;margin:2px;padding:0px;"  disabled="disabled"  /></textarea>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>推送结果：</td>
					<td colspan="4">
						<textarea id='pushResult' name="pushResult" style="width: 350px;height: 46px;margin:2px;padding:0px;"  disabled="disabled"  /></textarea>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>推送时间：</td>
						<td>
							<input type='text' id='pushTime' name="pushTime" style="width:130px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
						</td>
					<td>设备ID：</td>
					<td>
						<input type='text' id='equipmentId' name="equipmentId" style="width:130px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>注册ID：</td>
					<td>
						<input type='text' id='registerId' name="registerId" style="width:130px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
				</tr>
			</table>
		</div>
	</form>
	</body>
	<script type="text/javascript" charset="utf-8">
	$(function(){
	});
	</script>	
</html>