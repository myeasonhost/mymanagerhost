<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/admin/common/head"/> 
<jsp:include page="/admin/common/system"/> 
<%String path = request.getContextPath(); %>
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
		<span style="width:80px;height:23px;margin:2px;padding:0px;" >
			渠道名（匹配查询）
			<input style="width:100px" name="channelName" type="text"/>
		</span>
		<span style="width:80px;height:23px;margin:2px;padding:0px;" >
			mm渠道号（=）
			<input style="width:100px" name="externChannel" type="text"/>
		</span>
		<span style="width:80px;height:23px;margin:2px;padding:0px;" >
			商务（=）
			<input style="width:100px" name="shangwu" type="text"/>
		</span>
		&nbsp;&nbsp;&nbsp;
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	 <div style="padding:3px;border:1px solid #ccc;text-align:right">
			<a id="exportDealBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"><spring:message code='common.ExportDeal'/></a>
	 </div>
	</div>
 </div> 
<table id="table_grid"  style="min-width:1000px;" data-options="fitColumns:true,rownumbers:true,striped:true">
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'" width='10%'></th>
		<th data-options="field:'channelId',sortable:'true',align:'center'">内部渠道号</th>
		<th data-options="field:'channelName',sortable:'true',align:'center'" width='15%'>渠道名字</th>
		<th data-options="field:'cstype',sortable:'true',align:'center'">结算模式</th>
		<th data-options="field:'externChannel',sortable:'true',align:'center'" width='15%'>mm渠道号</th>
		<th data-options="field:'username',sortable:'true',align:'center'" width='15%'>渠道查询帐号</th>
		<th data-options="field:'password',sortable:'true',align:'center'" width='15%'>渠道查询密码</th>
		<th data-options="field:'shangwu',sortable:'true',align:'center'" width='15%'>商务</th>
		<th data-options="field:'createdAt',sortable:'true',align:'center',formatter:function(value,row,index){ 
                         var unixTimestamp = new Date(value); 
                         return unixTimestamp.toLocaleString(); 
                         }" width='15%'>创建时间</th>
		<th data-options="field:'memo',sortable:'true',align:'center'" width='15%'>备注</th>
	</thead>
 	<tbody>
 	</tbody>
</table>

 <script>	 
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/admin/business/channelMgr/query",
		checkOnSelect:false,
	  	selectOnCheck:false,
	  	singleSelect:true,
		striped:true, 
		nowrap:true,
		fit:true,
		fitColumns: true,
		pagination: true,
		rownumbers: true, 
		idField   : "channelId",
		sortName  : 'createdAt',
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
 
