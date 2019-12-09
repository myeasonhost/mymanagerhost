<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/admin/common/head"/> 
<jsp:include page="/admin/common/system"/> 
<%String path = request.getContextPath(); %>
<script type="text/javascript" src="<%=path %>/js/json.js"></script>
<div id="table_tools" style="padding:0;height:auto;">
	<div style="padding:2px;border:1px solid #ccc;">
	<form id="queryParams" style="margin:0" >
		<span style="width:100px;height:20px;margin:2px;padding:0px;" >
			用户ID
			<input type='text' id='userId' name="userId" style="width:100px"/>
		</span>
		&nbsp;
		<span style="width:100px;height:20px;margin:2px;padding:0px;" >
			用户名
			<input type='text' id='nickName' name="nickName" style="width:100px"/>
		</span>
		&nbsp;
		<span style="width:100px;height:20px;margin:2px;padding:0px;" >
			账户名称
			<input type='text' id='accountName' name="accountName" style="width:100px"/>
		</span>
		&nbsp;
		<span style="width:100px;height:20px;margin:2px;padding:0px;" >
			提现时间(精确到天)
			<input name="withdrawTime" id="withdrawTime" style="width:140px" class="easyui-datetimebox" data-options="formatter:function(date){
				var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				return y+'-'+m+'-'+d}"/>
		</span>
		<!-- -
		var HH = date.getHours(); 
    			var mm = date.getMinutes(); 
    			var ss = date.getSeconds(); 
			<input name="createtimeEnd_1" id="createtimeEnd_1" style="width:140px" class="easyui-datetimebox" data-options="formatter:function(date){
			    var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				var HH = date.getHours(); 
    			var mm = date.getMinutes(); 
    			var ss = date.getSeconds();
				return y+'-'+m+'-'+d+' '+HH+':'+mm+':'+ss}"/>（时分秒） -->
		&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			支付状态
			<select id="userPayStatus" name="userPayStatus" style="width:130px;" class="easyui-combobox">
				<option value="" selected="selected">所有订单</option>
				<option value="1" >待提现</option>
				<option value="2" >提现成功</option>
				<option value="3" >提现失败</option>
			</select>
		</span> 
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	 </div>
	 <div style="padding:2px;border:1px solid #ccc;">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="exportTransfer">导出Excel表</a>
		<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="taskMgr.remove()">删除转账记录</a> -->
	 </div>
</div>
<table id="table_grid"  style="fitColumns:false;min-width:1000px;height=1000px;overflow-x:scroll;overflow-y:scroll;" > 
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'" width='10%'></th>
		<th data-options="field:'withdrawOrderId', align:'center'"width='20%'>提现明细订单id</th>
		<!-- <th data-options="field:'userTaId', align:'center'" width='20%'>用户Ta币ID</th> -->
		<th data-options="field:'nickName', align:'center'" width='15%'>用户昵称</th>
		<th data-options="field:'withdrawTa', align:'center'" width='10%'>等值Ta币</th>
		<!-- <th data-options="field:'withdrawMny', align:'left'" width='10%'>提现金额</th> -->
		<th data-options="field:'withdrawTime', align:'center'" width='15%'>提现时间</th>
		<th data-options="field:'withdrawSubject', align:'center'" width='10%'>显示标题</th>
		<!-- <th data-options="field:'userPayId', align:'center'" width='20%'>提现账户</th> -->
		<th data-options="field:'accountName', align:'center'" width='15%'>账户名称</th>
		<th data-options="field:'account', align:'center'" width='15%'>提现账户</th>
		<th data-options="field:'userPayStatus',align:'center',formatter:transferMgr.statuFormatter" width='15%'>提现状态 </th>		
		<th data-options="field:'action',align:'center',formatter:transferMgr.actionFormatter" width='10%'>操作 </th>
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>

 <script>	 
 eason.register("transferMgr");
 
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/api/transfer/transferList/query",
		checkOnSelect:false,
	  	selectOnCheck:false,
	  	singleSelect:true,
		striped:true, 
		nowrap:true,
		fit:true,
		fitColumns: true,
		pagination: true,
		rownumbers: true, 
		toolbar   : "#table_tools", 
		onSortColumn:function(sort,order){
			$(this).datagrid('options').sortName = sort; 
			$(this).datagrid('options').sortOrder = order;
		} 
	});
	$('#exportTransfer').click(function(){
 		$('#table_grid').datagrid('doExport');
 	});
});


//用户管理：操作
transferMgr.actionFormatter = function(value, row, index) {
	if(row.userPayStatus==1){
		var transfer = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"transferMgr.transfer('"+row.withdrawOrderId+"',event)\">转账</a>"
		+"|"+"<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"transferMgr.resetTransfer('"+row.withdrawOrderId+"','"+ row.withdrawTa+"','"+row.userTaId+"',event)\">转账失败</a>";
	}
	return transfer;
};
//状态管理
transferMgr.statuFormatter = function(value, row, index) {
	var transfer = "";
	if(row.userPayStatus==1){
		transfer = "<span style='color:red;'>待提现</span>";
	}else if(row.userPayStatus==2){
		transfer = "<span style='color:green;'>提现成功</span>";
	}else if(row.userPayStatus==3){
		transfer = "<span style='color:#7c828b;'>提现失败</span>";
	}
	return transfer;
};

//转账操作
transferMgr.transfer= function(withdrawOrderId,e){
	$.messager.confirm('', '确定转账给用户？',
			function(r) {
				if (r) {
					$.post("<%=path%>/api/transfer/money2user/updateStatus", {
						'withdrawOrderId':withdrawOrderId
					}, function(data, status) {
						if ("success" == status && "1" == data) {
							$.messager.show({
								title : '',
								msg : '<spring:message code="common.Succ"/>',
								showType : 'show'
							});
						} else {
							$.messager.show({
								title : '',
								msg : '<spring:message code="common.Failed"/>' ,
								showType : 'show'
							});
						}
						$('#table_grid').datagrid('uncheckAll').datagrid(
								'unselectAll').datagrid('clearSelections');
						$('#table_grid').datagrid('reload');
					}, "json");
				}
			});
};

//退款操作
transferMgr.resetTransfer= function(withdrawOrderId,withdrawTa,userTaId,e){
	$.messager.confirm('', '确定转账失败并将Ta币退回？',
			function(r) {
				if (r) {
					$.post("<%=path%>/api/reset/money2ta/resetStatus", {
						'withdrawTa':withdrawTa,
						'userTaId':userTaId,
						'withdrawOrderId':withdrawOrderId
					}, function(data, status) {
						if ("success" == status && "1" == data) {
							$.messager.show({
								title : '',
								msg : '<spring:message code="common.Succ"/>',
								showType : 'show'
							});
						} else {
							$.messager.show({
								title : '',
								msg : '<spring:message code="common.Failed"/>' ,
								showType : 'show'
							});
						}
						$('#table_grid').datagrid('uncheckAll').datagrid(
								'unselectAll').datagrid('clearSelections');
						$('#table_grid').datagrid('reload');
					}, "json");
				}
			});
};
</script>
 
