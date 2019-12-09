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
    	<th data-options="field:'id',align:'center'"  width='10%'>玩家id</th>
    	<th data-options="field:'coins',sortable:'true',align:'center'" width='15%'>金币总数</th>
    	<th data-options="field:'totalConsume',align:'center'">充值总额</th>
		<th data-options="field:'count',align:'center'">总局数</th>
		<th data-options="field:'registerTime',align:'center'" width='20%'>注册时间</th>
		<th data-options="field:'lastLoginTime',align:'center'" width='20%'>上次登录时间</th>
		<th data-options="field:'totalTaxCoins',align:'center'" width='15%'>总抽水</th>
		<th data-options="field:'iosStore',align:'center'"  width='10%'>IOS商城</th>
		<th data-options="field:'alipay',align:'center'"  width='10%'>支付宝</th>
		<th data-options="field:'szf',align:'center'"  width='10%'>神州付</th>
		<th data-options="field:'mmPay',align:'center'"  width='10%'>移动MM</th>
		<th data-options="field:'tyPay',align:'center'"  width='10%'>天翼</th>
		<th data-options="field:'uucPay',align:'center'"  width='10%'>悠悠村</th>
		<th data-options="field:'ylPay',align:'center'"  width='10%'>银联</th>
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<div id="table_tools" style="padding:0;height:auto;">  
	<div style="padding:2px;border:1px solid #ccc;">
	<form id="queryParams" style="margin:0" >
		<span style="display:inline-block;width:400px;height:23px;margin:5px;padding:0px;" >
			注册时间
			<input name="createtimeStart" style="width:120px" class="easyui-datetimebox" data-options="formatter:function(date){
				var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				var HH = date.getHours(); 
    			var mm = date.getMinutes(); 
    			var ss = date.getSeconds();
				return y+'-'+m+'-'+d+' '+HH+':'+mm+':'+ss}"/>
			-
			<input name="createtimeEnd" style="width:120px" class="easyui-datetimebox" data-options="formatter:function(date){
			    var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				var HH = date.getHours(); 
    			var mm = date.getMinutes(); 
    			var ss = date.getSeconds();
				return y+'-'+m+'-'+d+' '+HH+':'+mm+':'+ss}"/>
			（时分秒）
		</span>
		<span style="width:80px;height:23px;margin:2px;padding:0px;" >
			玩家ID（=）
			<input style="width:80px" name="id" type="text"/>
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
	    url:"<%=path%>/admin/zjh/coinsTop/query",
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
		sortName  : 'coins',
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
 
