<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%String path = request.getContextPath(); %>
<html>
	<head>
	<title>修改方法</title>
	</head>
	<body style="padding: 0;margin: 0">
<form id='userForm_editMethodDialog' method='POST' action="<%=path%>/admin/api/apiMethod/modify">
		<div style='padding: 10px 20px;'>
		<input type="hidden" id="id" name="id"/>
			<table style='padding: 10px 25px;' >
				<tr style='height: 25px;'>
					<td>方法名：</td>
					<td>
						<input type='text' id='method' name="method" style="width:150px;height:23px;margin:2px;padding:0px;" class="easyui-validatebox" data-options="required:true" />
					</td>
					<td>方法中文名：</td>
					<td>
						<input type='text' id='methodName' name="methodName" style="width:150px;height:23px;margin:2px;padding:0px;color: green;" class="easyui-validatebox" data-options="required:true" />
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>方法类别：</td>
					<td>
						<select id="methodType_edit" name="methodType" style="width:130px;" class="easyui-combobox" class="easyui-validatebox" data-options="required:true">
							<!-- <option value="-1" selected="selected">--方法类别--</option> -->
						</select>
					</td>
					<td>调用等级：</td>
					<td>
					<!-- 0：不需要appkey，1：需要appkey，2：需要appkey&sessionkey，3：需要appkey&sessionkey（每次使用都需要登录授权）） -->
						<select id="cc" name="authLevel" class="easyui-combobox" name="dept" style="width:200px;" data-options="required:true" class="easyui-validatebox">
						    <option value="0">0-不需要appkey</option>
						    <option value="1">1-需要appkey</option>
						    <option value="2">2-需要appkey&sessionkey</option>
						    <option value="3">3-需要appkey&sessionkey</option>
						</select>
					</td>
				</tr>
				<tr style='height: 25px;' class="dateTr">
					<td class="labletd">是否增值接口：</td>
					<td>
						<!-- <input type='text' id='isExtras' name="isExtras"  style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/> -->
						<input name="isExtras" type="radio" value="0" checked="checked" />开放
					  	<input name="isExtras" type="radio" value="1" />增值
					</td>
					<td class="labletd">是否对外开放接口：</td>
					<td>
						<!-- <input type='text' id='isOpen' name="isOpen" style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/> -->
					  	<input name="isOpen" type="radio" value="0" checked="checked" />否
						<input name="isOpen" type="radio" value="1"   />是
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>一分钟可调用次数：</td>
					<td>
						<input type='text' id='invokeMinMaxNum' name="invokeMinMaxNum" style="width:150px;height:23px;margin:2px;padding:0px;color: green;"/>
					</td>
					<td>一天可调用次数：</td>
					<td>
						<input type='text' id='invokeDayMaxNum' name="invokeDayMaxNum" style="width:150px;height:23px;margin:2px;padding:0px;color: green;"/>
					</td>
				</tr>
				<tr style='height: 25px;' class="dateTr">
					<td class="labletd">是否更新类接口：</td>
					<td>
						<!-- <input type='text' id='isOpen' name="isOpen" style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/> -->
					  	<input name="isUpdated" type="radio" value="0" checked="checked" />否
						<input name="isUpdated" type="radio" value="1" />是
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>方法备注：</td>
					<td colspan="4">
						<textarea id='methodMemo' name="methodMemo" style="width: 350px;height: 65px;margin:2px;padding:0px;"/></textarea>
					</td>
				</tr>
					<tr style='height: 25px;'>
					<td>XML数据格式示例：</td>
					<td colspan="4">
						<textarea id='xmlResult' name="xmlResult" style="width: 350px;height: 65px;margin:2px;padding:0px;" class="easyui-validatebox" data-options="required:true" /></textarea>
					</td>
				</tr>
				<tr style='height: 25px;'>
					<td>JSON数据格式示例：</td>
					<td colspan="4">
						<textarea id='jsonResult' name="jsonResult" style="width: 350px;height: 65px;margin:2px;padding:0px;"  class="easyui-validatebox" data-options="required:true"/></textarea>
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