<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%String path = request.getContextPath(); %>
<script>
 $(function(){
	 
 });
</script>
<form id="userForm_editCategoryDialog" method="post" action="<%=path%>/admin/api/category/modify">
	<table style="font-size: 12px;">
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				类别ID：
			</td>
			<td>
				<input type="text" id="id" name="id" readonly="readonly" style="width: 150px;border-color: gray;"/>
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				类别英文名：
			</td>
			<td>
				<input type="text" id="cateEnName" name="cateEnName" class="easyui-validatebox" data-options="required:true" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				类别中文名：
			</td>
			<td>
				<input type="text" id="cateCnName" name="cateCnName" class="easyui-validatebox" data-options="required:true" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				类别描述：
			</td>
			<td>
				<input type="text" id="cateDescription" name="cateDescription" class="easyui-validatebox" data-options="required:true" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				是否删除：
			</td>
			<td>
				<!-- <input type="text" id="isDeleted" name="isDeleted" class="easyui-validatebox" data-options="required:true" /> -->
				<!-- 已删除<input type='radio' id="isDeleted" name="isDeleted" value="1" checked="checked"/>
				未删除<input type='radio' id="isDeleted" name="isDeleted" value="0"/> -->
				<select id="isDeleted" name='isDeleted' class="easyui-combobox" name="dept" style="width:150px;">
				    <option value="0">否</option>
				    <option value="1">是</option>
				</select>
				
			</td>
		</tr>
	</table>
</form>