<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%String path = request.getContextPath(); %>
<form id="userForm_passwordModifyDialog" method="post" action="<%=path%>/admin/zjh/customerQuery/modify">
	<input type="hidden" name="playerId" />
	<table style="font-size: 12px;">
		<tr>
			<td style="width: 60px;">
				注意事项：
			</td>
			<td>
				<input type="hidden" id="updType" name="updType"/>
				<input type="hidden" id="target" name="target" disabled="disabled"/>
				<input type="hidden" id="oldValue" name="oldValue" style="width: 150px;border-color: gray;" disabled="disabled"/>
				<span style="color: red;">非客服人员禁止此操作</span>
			</td>
		</tr>
		<tr>
			<td>
				新密码：
			</td>
			<td>
				<input type="text" id="updValue" name="updValue" style="width: 180px;" />
			</td>
		</tr>
		<tr>
			<td style="width: 60px;">
				修改原因：
			</td>
			<td>
				<input type="text" id="memo" name="memo"  style="width: 180px;" />
			</td>
		</tr>
	</table>
</form>