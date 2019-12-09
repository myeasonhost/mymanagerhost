<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%String path = request.getContextPath(); %>
<form id="form_modifyCargoParamDialog" method="post" action="<%=path%>/admin/cargoparam/modify" style="padding: 20px 30px;text-align:left;">
	<table style="font-size: 14px;">
		<tr>
			<td>
				服务项ID：
			</td>
			<td>
				<input type="text" id="cargoParamId" name="cargoParamId" style="width: 200px;" readonly="readonly"/>
				<br><span style="color:red;font-size:10px;">(服务项ID不能修改)</span>
			</td>
		</tr>
		<tr>
			<td>
				服务项：
			</td>
			<td>
				<input type="text" id="serverItem" name="serverItem" style="width: 200px;" class="easyui-validatebox"  data-options="required:true"/>
			</td>
		</tr>
		<tr>
			<td>
				是否收费：
			</td>
			<td>
				<input name="whetherCharge" type="radio" value="0" checked="checked"  />免费
				<input name="whetherCharge" type="radio" value="1" />收费
			</td>
		</tr>
		<tr>
			<td>
				收费标准(元)：
			</td>
			<td>
				<input type="text" id="chargeAmount" name="chargeAmount" style="width: 200px;"  class="easyui-numberbox"  data-options="min:0,precision:2,required:true"/>
			</td>
		</tr>
		<tr>
			<td>
				收费单位：
			</td>
			<td>
				<input type="text" id="chargeUnit" name="chargeUnit" style="width: 200px;"  class="easyui-validatebox"  data-options="min:0,required:true"/>(如：每件)
			</td>
		</tr>
	</table>
</form>
