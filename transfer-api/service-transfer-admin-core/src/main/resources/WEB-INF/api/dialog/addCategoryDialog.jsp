<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%String path = request.getContextPath(); %>
<form id="userForm_addCategoryDialog" method="post" action="<%=path%>/admin/api/category/add">
	<table style="font-size: 12px;">
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				方法类别英文名：
			</td>
			<td>
				<input type="text" id="cateEnName" name="cateEnName" class="easyui-validatebox" data-options="required:true" style="width: 150px;"/>
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				方法类别中文名：
			</td>
			<td>
				<input type="text" id="cateCnName" name="cateCnName" class="easyui-validatebox" data-options="required:true" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				方法类别描述：
			</td>
			<td>
				<input type="text" id="cateDescription" name="cateDescription" class="easyui-validatebox" data-options="required:true" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				是否删除
			</td>
			<td>
				<!-- select id="isDeleted" name="isDeleted" class="easyui-combobox" size="4" style="width:80px;">
					<option value="0" selected>未删除</option>
					<option value="1">已删除</option>  
				</select> -->
				已删除<input type='radio' id="isDeleted" name="isDeleted" value="1"/>
				未删除<input type='radio' id="isDeleted" name="isDeleted" value="0"/>
			</td>
		</tr>
	</table>
</form>