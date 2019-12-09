<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%String path = request.getContextPath(); %>
<form id="form_acceptCarCompanyAuthorDialog" method="post" action="<%=path%>/admin/author/carcompany/modify" style="padding: 20px 30px;text-align:left;">
	<table style="font-size: 14px;">
		<tr>
			<td colspan="2">
				再次确认<span style="color:red;">是否通过认证？</span>，并填写处理结果。
				<input type="hidden" id="carCompanyAuthorId" name="carCompanyAuthorId"/>
				<input type="hidden" id="userId" name="userId"/>
				<input type="hidden" id="status" name="status" value="2"/>
			</td>
		</tr>
		<tr>
			<td>
				审批结果：
			</td>
			<td>
				<textarea cols="2" rows="2" id="result" name="result"  style="width: 300px;height: 150px" class="easyui-validatebox"  data-options="required:true"></textarea>
				<span style="color:red;">*</span>
			</td>
		</tr>
	</table>
</form>
