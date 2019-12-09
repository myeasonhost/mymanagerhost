<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/admin/common/head"/> 
<jsp:include page="/admin/common/system"/> 
<%String path = request.getContextPath(); %>
 <table id="table_grid"  style="min-width:1000px;" data-options="fitColumns:'true',rownumbers:'true',striped:'true'">
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'"></th>
		<th data-options="field:'id',hidden:'true'"></th>
		<th data-options="field:'time',align:'center'" >日期</th>
    	<th data-options="field:'maxOnline',align:'center'" >最高在线人数</th>
    	<th data-options="field:'maxTime',align:'center'">最高在线时间</th>
    	<th data-options="field:'minOnline',align:'center'">最低在线人数</th>
    	<th data-options="field:'minTime',align:'center'">最低在线时间</th>
		<th data-options="field:'avgOnline',align:'center'">平均在线人数</th>
		<th data-options="field:'t1',align:'center'">00:00</th>
		<th data-options="field:'t2',align:'center'">01:00</th>
		<th data-options="field:'t3',align:'center'">02:00</th>
		<th data-options="field:'t4',align:'center'">03:00</th>
		<th data-options="field:'t5',align:'center'">04:00</th>
		<th data-options="field:'t6',align:'center'">05:00</th>
		<th data-options="field:'t7',align:'center'">06:00</th>
		<th data-options="field:'t8',align:'center'">07:00</th>
		<th data-options="field:'t9',align:'center'">08:00</th>
		<th data-options="field:'t10',align:'center'">09:00</th>
		<th data-options="field:'t11',align:'center'">10:00</th>
		<th data-options="field:'t12',align:'center'">11:00</th>
		<th data-options="field:'t13',align:'center'">12:00</th>
		<th data-options="field:'t14',align:'center'">13:00</th>
		<th data-options="field:'t15',align:'center'">14:00</th>
		<th data-options="field:'t16',align:'center'">15:00</th>
		<th data-options="field:'t17',align:'center'">16:00</th>
		<th data-options="field:'t18',align:'center'">17:00</th>
		<th data-options="field:'t19',align:'center'">18:00</th>
		<th data-options="field:'t20',align:'center'">19:00</th>
		<th data-options="field:'t21',align:'center'">20:00</th>
		<th data-options="field:'t22',align:'center'">21:00</th>
		<th data-options="field:'t23',align:'center'">22:00</th>
		<th data-options="field:'t24',align:'center'">23:00</th>
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<div id="table_tools" style="padding:0;height:auto;">  
	<div style="padding:2px;border:1px solid #ccc;">
	<form id="queryParams" style="margin:0" >
		<span style="display:inline-block;width:450px;height:23px;margin:5px;padding:0px;" >
			日期查询
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
				return y+'-'+m+'-'+d+' '+HH+':'+mm+':'+ss}"/>
			（时分秒）
		</span>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	</div>
		<div style="padding:3px;border:1px solid #ccc;text-align:right">
			<span style="color: red;">*注意第一条记录是当前时间同时在线人数统计</span>
			<a id="exportDealBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"><spring:message code='common.ExportDeal'/></a>
	 	</div>
 </div> 

 <script>	 
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/admin/business/onlineCount/query",
		checkOnSelect:false,
	  	selectOnCheck:false,
	  	singleSelect:true,
		striped:true, 
		nowrap:true,
		fit:true,
		fitColumns: false,
		pagination: true,
		rownumbers: true, 
		idField   : "id",
		sortName  : 'time',
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
 
