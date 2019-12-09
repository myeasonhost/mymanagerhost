<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%String path = request.getContextPath(); %>
<form id="userForm_addTemplateDialog" method="post" action="<%=path%>/api/msg/template/add">
	<table style="padding: 10px 25px ;font-size: 12px;">
		<tr>
			<td  style="display: inline-block; width: 100px; text-align: right; padding-right: 5px;">
				模板CODE：
			</td>
			<td>
				<input type="text" id="code" name="code" class="easyui-validatebox" data-options="required:true" style="width: 150px;"/>
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 100px; text-align: right; padding-right: 5px;">
				模板名：
			</td>
			<td>
				<input type="text" id="name" name="name" class="easyui-validatebox" data-options="required:true" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 100px; text-align: right; padding-right: 5px;">
				模板类型：
			</td>
			<td>
				<select id="tempType" name="tempType" class="easyui-combobox" name="dept" style="width:150px;" data-options="required:true" class="easyui-validatebox">
					    <option value="0">系统消息</option>
					    <option value="1">任务消息</option>
					    <option value="2">推送消息模板</option>
					</select>
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 100px; text-align: right; padding-right: 5px;">
				模板描述：
			</td>
			<td>
				<input type="text" id="description" name="description" class="easyui-validatebox"  style="width: 150px;" />
			</td>
		</tr>
		<tr style='height: 25px;'>
			<td  style="display: inline-block; width: 100px; text-align: right; padding-right: 5px;">
				模板内容：
			</td>
			<td colspan="4">
				<textarea id='context' name="context" style="width: 200px;height: 65px;margin:2px;padding:0px;"/></textarea>
			</td>
		</tr>
		<tr style='height: 25px;'>
			<td  style="display: inline-block; width: 100px; text-align: right; padding-right: 5px;">
				模板示例：
			</td>
			<td colspan="4">
				<textarea id='demo' name="demo" style="width: 200px;height: 65px;margin:2px;padding:0px;"/></textarea>
			</td>
		</tr>
	</table>
</form>