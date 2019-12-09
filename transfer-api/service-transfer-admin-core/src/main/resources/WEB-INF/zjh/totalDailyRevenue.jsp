<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/admin/common/head"/> 
<jsp:include page="/admin/common/system"/> 
<%String path = request.getContextPath(); %>
 <table id="table_grid"  style="min-width:1000px;" data-options="fitColumns:'true',rownumbers:'true',striped:'true'">
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'id',hidden:'true'"></th>
		<th data-options="field:'check',checkbox:'true',align:'center'" width='10%'></th>
		<th data-options="field:'date',sortable:'true',align:'center'" width='15%'>日期</th>
		<th data-options="field:'mmPay',sortable:'true',align:'center'" width='15%'>移动MM</th>
		<th data-options="field:'tyPay',sortable:'true',align:'center'" width='15%'>天翼</th>
		<th data-options="field:'alipay',sortable:'true',align:'center'" width='15%'>支付宝</th>
		<th data-options="field:'szf',sortable:'true',align:'center'" width='15%'>神州付</th>
		<th data-options="field:'woPay',sortable:'true',align:'center'" width='15%'>联通沃</th>  
		<th data-options="field:'iosStore',sortable:'true',align:'center'" width='15%'>IOS商城</th>  
		<th data-options="field:'ylPay',sortable:'true',align:'center'" width='15%'>银联</th>  
		<th data-options="field:'uucPay',sortable:'true',align:'center'" width='15%'>悠悠村</th>  
		<th data-options="field:'totalRecharge',sortable:'true',align:'center'" width='15%'>总计充值</th>
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<div id="table_tools" style="padding:0;height:auto;">  
	<div style="padding:2px;border:1px solid #ccc;">
	<form id="queryParams" style="margin:0" >
		<span style="display:inline-block;width:320px;height:23px;margin:5px;padding:0px;" >
			日期查询
			<input name="createtimeStart" style="width:100px" class="easyui-datebox" data-options="formatter:function(date){
				var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				return y+'-'+m+'-'+d}"/>
			-
			<input name="createtimeEnd" style="width:100px" class="easyui-datebox" data-options="formatter:function(date){
			    var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				return y+'-'+m+'-'+d}"/>
		</span>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	</div>
		<div style="padding:3px;border:1px solid #ccc;text-align:right">
			<a id="exportDealBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"><spring:message code='common.ExportDeal'/></a>
	 	</div>
 </div> 

 <script>	 
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/admin/business/dailyRevenue/query",
		checkOnSelect:false,
	  	selectOnCheck:false,
	  	singleSelect:true,
		striped:true, 
		nowrap:true,
		fit:true,
		fitColumns: true,
		pagination: true,
		rownumbers: true, 
		idField   : "id",
		sortName  : 'date',
		sortOrder : 'desc',
		toolbar   : "#table_tools", 
		onSortColumn:function(sort,order){
			$(this).datagrid('options').sortName = sort; 
			$(this).datagrid('options').sortOrder = order;
		}   
	});
	
 	$('#exportDealBtn').click(function(){
 		$('#table_grid').datagrid('doExport');
 	});
});
 
 
</script>
 
