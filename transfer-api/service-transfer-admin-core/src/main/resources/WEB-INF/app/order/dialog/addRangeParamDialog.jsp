<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%String path = request.getContextPath(); %>
<form id="form_addRangeParamDialog" method="post" action="<%=path%>/admin/range/add" style="padding: 20px 30px;text-align:left;">
	<table style="font-size: 14px;">
		<tr>
			<td>
				最小距离（KM）：
			</td>
			<td>
				<input type="text" id="distanceMin" name="distanceMin" style="width: 200px;" class="easyui-numberbox"  data-options="min:0,required:true"/>
			</td>
		</tr>
		<tr>
			<td>
				最大距离（KM）：
			</td>
			<td>
				<input type="text" id="distanceMax" name="distanceMax" style="width: 200px;" class="easyui-numberbox"  data-options="min:0,required:true"/>
			</td>
		</tr>
		<tr>
			<td>
				最小件数（件）：
			</td>
			<td>
				<input type="text" id="rangeMin" name="rangeMin" style="width: 200px;"  class="easyui-numberbox"  data-options="min:0,required:true"/>
			</td>
		</tr>
		<tr>
			<td>
				最大件数（件）：
			</td>
			<td>
				<input type="text" id="rangeMax" name="rangeMax" style="width: 200px;"  class="easyui-numberbox"  data-options="min:0,required:true"/>
				<br><span style="color:red;font-size:10px;">(大于最小件，请输入2147483647)</span>
			</td>
		</tr>
		<tr>
			<td>
				费用（元）：
			</td>
			<td>
				<input type="text" id="rangeCharge" name="rangeCharge" style="width: 200px;"  class="easyui-numberbox"  data-options="min:0,precision:2,required:true"/>
			</td>
		</tr>
	</table>
</form>
