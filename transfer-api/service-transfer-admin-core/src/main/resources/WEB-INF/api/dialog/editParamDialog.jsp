<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%String path = request.getContextPath(); %>
<form id="userForm_editParamDialog" method="post" action="<%=path%>/admin/api/methodParam/modify">
	<table style="font-size: 12px;">
			<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				参数名称：
			</td>
			<td>
				<input type="hidden" id="id" name="id"/>
				<input type="hidden" id="methodId" name="methodId"/>
				<input type="text" id="paramName" name="paramName" class="easyui-validatebox" data-options="required:true" style="width: 150px;"/>
			</td>
		</tr>
		<tr>	
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				参数类型:
			</td>
			<td>
				<select id="paramType" name='paramType' class="easyui-combobox" name="dept" style="width:150px;">
				    <option value="String">String</option>
				    <option value="Integer">Integer</option>
				    <option value="Double">Double</option>
				    <option value="byte[]">byte[]</option>
				</select>
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;" >
				是否必须：
			</td>
			<td>
				<select id="isNecessary" name='isNecessary' class="easyui-combobox" name="dept" style="width:150px;">
				    <option value="0">否</option>
				    <option value="1">是</option>
				</select>
				<!-- <input name="isNecessary" type="radio" value="0" checked="checked" />否
				<input name="isNecessary" type="radio" value="1"   />是 -->
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				示例值：
			</td>
			<td>
				<input type="text" id="example" name="example" class="easyui-validatebox" data-options="required:true" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				默认值：
			</td>
			<td>
				<input type="text" id="defaultValue" name="defaultValue" class="easyui-validatebox" data-options="required:true" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				参数描述：
			</td>
			<td>
				<textarea id='paramDescription' name="paramDescription" style="width: 350px;height: 65px;margin:2px;padding:0px;"/></textarea>
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				参数类别：
			</td>
			<td>                   
				<select id="paramClass" name='paramClass' class="easyui-combobox" name="dept" style="width:150px;">
				    <option value="0">0-返回结果属性</option>
				    <option value="1">1-应用级参数</option>
				</select>
				<!-- <input name="paramClass" type="radio" value="0" checked="checked" />否
				<input name="paramClass" type="radio" value="1"   />是 -->
			</td>
			
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				是否自定义对象：
			</td>
			<td>
				<select id="isObject" name='isObject' class="easyui-combobox" name="dept" style="width:150px;">
				    <option value="0">否</option>
				    <option value="1">是</option>
				</select>
				<!-- <input name="isObject" type="radio" value="0" checked="checked" />否
				<input name="isObject" type="radio" value="1"   />是 -->
			</td>
		</tr>
	</table>
</form>