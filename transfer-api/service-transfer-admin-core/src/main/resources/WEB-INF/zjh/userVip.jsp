<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/admin/common/head"/> 
<jsp:include page="/admin/common/system"/> 
<%String path = request.getContextPath(); %>
 <table id="table_grid"  style="min-width:1000px;" data-options="fitColumns:'true',rownumbers:'true',striped:'true'">
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'" width='10%'></th>
		<th data-options="field:'date',sortable:'true',align:'center'" >日期</th>
		<th data-options="field:'totalVip',sortable:'true',align:'center'" width='10%'>VIP人数 </th>
		<th data-options="field:'vip1Cnt',sortable:'true',align:'center'" width='10%'>1级VIP</th>
		<th data-options="field:'vip2Cnt',sortable:'true',align:'center'" width='10%'>2级VIP</th>
		<th data-options="field:'vip3Cnt',sortable:'true',align:'center'" width='10%'>3级VIP</th>
		<th data-options="field:'vip4Cnt',sortable:'true',align:'center'" width='10%'>4级VIP</th>
		<th data-options="field:'vip5Cnt',sortable:'true',align:'center'" width='10%'>5级VIP</th>
		<th data-options="field:'vip6Cnt',sortable:'true',align:'center'" width='10%'>6级VIP</th>
		<th data-options="field:'vip7Cnt',sortable:'true',align:'center'" width='10%'>7级VIP</th>
		<th data-options="field:'vip8Cnt',sortable:'true',align:'center'" width='10%'>8级VIP</th>
		<th data-options="field:'vip9Cnt',sortable:'true',align:'center'" width='10%'>9级VIP</th>
		<th data-options="field:'vip10Cnt',sortable:'true',align:'center'" width='10%'>10级VIP</th>
		<th data-options="field:'vip11Cnt',sortable:'true',align:'center'" width='10%'>11级VIP</th>
		<th data-options="field:'vip12Cnt',sortable:'true',align:'center'" width='10%'>12级VIP</th>
		<th data-options="field:'vipAdd',sortable:'true',align:'center'" width='10%'>VIP日新增</th>
		<th data-options="field:'vipSub',sortable:'true',align:'center'" width='10%'>VIP日消亡</th>
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
	    url:"<%=path%>/admin/business/vipUser/query",
		checkOnSelect:false,
	  	selectOnCheck:false,
	  	singleSelect:true,
		striped:true, 
		nowrap:true,
		fit:true,
		fitColumns: true,
		pagination: true,
		rownumbers: true, 
		idField   : "date",
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
 
