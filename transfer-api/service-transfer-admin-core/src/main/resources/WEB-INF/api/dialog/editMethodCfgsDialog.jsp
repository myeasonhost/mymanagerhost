<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%String path = request.getContextPath(); %>
<form id="userForm_editMethodCfgsDialog" method="post" action="<%=path%>/admin/api/apiMethodCfg/modify">
	<input type="hidden" id="methodId" name="methodId"/>
	<input type="hidden" id="id" name="id"/>
	<table style="font-size: 12px;">
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				版本号：
			</td>
			<td>
				<input type="text" id="ver" name="ver" class="easyui-validatebox" data-options="required:true" style="width: 150px;"/>
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;" >
				配置值：
			</td>
			<td>
				<textarea id='cfgValue' name="cfgValue" 
				  style="width: 350px;height: 65px;margin:2px;padding:0px;" class="easyui-validatebox" data-options="required:true">
				</textarea>
			</td>
		</tr>
		<tr>
			<td style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;" >
				删除标识：
			</td>
			<td>
				<select id="isDeleted" name='isDeleted' class="easyui-combobox" name="dept" style="width:150px;">
				    <option value="0">否</option>
				    <option value="1">是</option>
				</select>
			</td>
		</tr>
	</table>
</form>