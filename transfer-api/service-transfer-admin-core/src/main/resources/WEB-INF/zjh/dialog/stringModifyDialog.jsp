<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%String path = request.getContextPath(); %>
<form id="userForm_stringModifyDialog" method="post" action="<%=path%>/admin/zjh/customerQuery/modify">
	<input type="hidden" name="playerId" />
	<table style="font-size: 14px;">
		<tr>
			<td>
				原来为：
			</td>
			<td>
				<input type="hidden" id="updType" name="updType"/>
				<input type="hidden" id="target" name="target" disabled="disabled"/>
				<input type="text" id="oldValue" name="oldValue" style="width: 150px;border-color: gray;" disabled="disabled"/>
			</td>
		</tr>
		<tr>
			<td>
				修改为：
			</td>
			<td>
				<input type="text" id="updValue" name="updValue" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td>
				修改原因：
			</td>
			<td>
				<input type="text" id="memo" name="memo"  style="width: 150px;" />
			</td>
		</tr>
	</table>
</form>