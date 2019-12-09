<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%String path = request.getContextPath(); %>
<form id="form_acceptOrderCancelDialog" method="post" action="<%=path%>/admin/ordercancel/modify" style="padding: 20px 30px;text-align:left;">
	<table style="font-size: 14px;">
		<tr>
			<td colspan="2">
				货主取消订单，需要进行<span style="color:red;">扣款金额，在订单金额中扣除，并将订单金额返回给货主！</span>请及时与货主进行沟通确认。
				<input type="hidden" id="orderCancelId" name="orderCancelId"/>
				<input type="hidden" id="cargoOrderId" name="cargoOrderId"/>
				<input type="hidden" id="status" name="status" value="2"/>
			</td>
		</tr>
		<tr>
			<td>
				扣款金额：
			</td>
			<td>
				<input type="text" id="chargeBack" name="chargeBack" class="easyui-numberbox" data-options="required:true,min:0,precision:2"/><span style="color:red;">*</span>
			</td>
		</tr>
		<tr>
			<td>
				审批处理结果：
			</td>
			<td>
				<textarea cols="2" rows="2" id="dealRemark" name="dealRemark"  style="width: 300px;height: 150px" class="easyui-validatebox"  data-options="required:true"></textarea>
				<span style="color:red;">*</span>
			</td>
		</tr>
	</table>
</form>
