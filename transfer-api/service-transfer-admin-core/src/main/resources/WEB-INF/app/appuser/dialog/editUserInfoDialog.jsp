<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%String path = request.getContextPath(); %>
<html>
	<head>
	<title>用户详情</title>
	</head>
	<body style="padding: 0;margin: 0">
<form id='userForm_detailUserInfoDialog' method='POST'>
		<div style='padding: 10px 20px;'>
		<input type="hidden" id="id" name="id"/>
			<table style='padding: 10px 25px;' >
				<tr style='height: 25px;'>
					<td>用户ID：</td>
					<td>
						<input type='text' id='userId' name="userId" style="width:100px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
					<td>昵称：</td>
					<td>
						<input type='text' id='nickName' name="nickName" style="width:100px;height:23px;margin:2px;padding:0px;color: green;"   disabled="disabled"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>性别：</td>
					<td>
						<input type='text' id='gender' style="width:100px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
					<td>图像：</td>
					<td>
						<img id='userLogo'  style="width:80px; height:80px;border:1px solid #000000;">
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>居住地：</td>
					<td>
						<input type='text' id='area' name="area" style="width:100px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
					<td>等级：</td>
					<td>
						<input type='text' id='userLv' name="userLv" style="width:100px;height:23px;margin:2px;padding:0px;color: green;"   disabled="disabled"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>VIP用户：</td>
					<td>
						<input type='text' id='isVip' style="width:100px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
					<td>生日：</td>
					<td>
						<input type='text' id='birth' name="birth" style="width:100px;height:23px;margin:2px;padding:0px;color: green;"   disabled="disabled"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>个性签名：</td>
					<td colspan="4">
						<textarea id='introduction' name="introduction" style="width: 350px;height: 23px;margin:2px;padding:0px;"  disabled="disabled"  /></textarea>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>用户动态封面地址：</td>
					<td colspan="4">
						<textarea id='trendCover' name="trendCover" name='trendCover' style="width: 350px;height: 23px;margin:2px;padding:0px;"  disabled="disabled" /></textarea>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>用户头像背景图片地址：</td>
					<td colspan="4">
						<textarea id='backImage' name="backImage" name='backImage' style="width: 350px;height: 23px;margin:2px;padding:0px;"  disabled="disabled" /></textarea>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>手机号修改时间：</td>
					<td>
						<input type='text' id='phoneUpdateTime' name='phoneUpdateTime' style="width:130px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
					<td>创建时间：</td>
					<td>
						<input type='text' id='createTime' name="createTime" style="width:130px;height:23px;margin:2px;padding:0px;color: green;"   disabled="disabled"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>更新时间：</td>
					<td>
						<input type='text' id='updateTime' name='updateTime' style="width:130px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
					<td>状态：</td>
					<td>
						<input type='text' id='status' name="status" style="width:100px;height:23px;margin:2px;padding:0px;color: green;"   disabled="disabled"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>经度：</td>
					<td>
						<input type='text' id='longitude' name='longitude' style="width:100px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
					</td>
					<td>纬度：</td>
					<td>
						<input type='text' id='latitude' name="latitude" style="width:100px;height:23px;margin:2px;padding:0px;color: green;"   disabled="disabled"/>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>坐标更新时间：</td>
					<td>
						<input type='text' id='disUpdateTime' name='disUpdateTime' style="width:130px;height:23px;margin:2px;padding:0px;"   disabled="disabled"/>
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