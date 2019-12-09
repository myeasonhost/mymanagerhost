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
    	<th data-options="field:'registerTime',sortable:'true',align:'center'" width='15%'>统计时间</th>
		<th data-options="field:'id',sortable:'true',align:'center'">玩家ID</th>
		<th data-options="field:'nickname',sortable:'true',align:'center'" width='15%'>玩家呢称</th>
		<th data-options="field:'coins',sortable:'true',align:'center'" width='15%'>金币</th>
		<th data-options="field:'count',align:'center'" width='15%'>咋金花次数</th>
		<th data-options="field:'totalConsume',sortable:'true',align:'center'" width='15%'>付费额</th>
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<div id="table_tools" style="padding:0;height:auto;">  
	<div style="padding:2px;border:1px solid #ccc;">
	<form id="queryParams" style="margin:0" >
		<span style="display:inline-block;width:420px;height:23px;margin:5px;padding:0px;" >
			统计时间
			<input name="createtimeStart" style="width:140px" class="easyui-datetimebox" data-options="formatter:function(date){
				var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				var HH = date.getHours(); 
    			var mm = date.getMinutes(); 
    			var ss = date.getSeconds();
				return y+'-'+m+'-'+d+' '+HH+':'+mm+':'+ss}"/>
			-
			<input name="createtimeEnd" style="width:140px" class="easyui-datetimebox" data-options="formatter:function(date){
			    var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				var HH = date.getHours(); 
    			var mm = date.getMinutes(); 
    			var ss = date.getSeconds();
				return y+'-'+m+'-'+d+' '+HH+':'+mm+':'+ss}"/>（时分秒）
		</span>
		<span style="width:80px;height:23px;margin:2px;padding:0px;" >
			玩家ID（=）
			<input style="width:80px" name="id" type="text"/>
		</span>
		<span style="width:80px;height:23px;margin:2px;padding:0px;" >
			炸金花次数 （&gt;=）
			<input style="width:80px" name="count" type="text"/>
		</span>
		<span style="width:80px;height:23px;margin:2px;padding:0px;" >
			付费额（&lt;=）
			<input style="width:80px" name="totalConsume" type="text"/>
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
	    url:"<%=path%>/admin/zjh/userRecharge/query",
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
		sortName  : 'registerTime',
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
 
