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
			<input type='text' id='userId' name="userId" style="width:100px;height:23px;margin:2px;padding:0px;"/>
		</span>
		&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			用户昵称
			<input type='text' id='nickName' name="nickName" style="width:100px;height:23px;margin:2px;padding:0px;"/>
		</span>
		&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			用户余额(不少于)
			<input type='text' id='taBalance' name="taBalance" style="width:100px;height:23px;margin:2px;padding:0px;"/>
		</span>
		&nbsp;
		<!-- <span style="width:100px;height:23px;margin:2px;padding:0px;" >
			注册时间(精确到天)
			<input type='text' id='taBlance' name="taBlance" style="width:100px;height:23px;margin:2px;padding:0px;"/>
		</span>
		&nbsp; -->
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	 </div>
	 <div style="padding:2px;border:1px solid #ccc;">
	 	<span id="totalBalance"></span>
		<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="taskMgr.edit()">修改用户</a> -->
		<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="taskMgr.remove()">删除任务</a>  -->
	</div>
</div>
<table id="table_grid"  style="fitColumns:false;min-width:1000px;height=1000px;overflow-x:scroll;overflow-y:scroll;" > 
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'" width='10%'></th>
		<th data-options="field:'userTaId', align:'center'"width='10%'>用户账户id</th> 
		<th data-options="field:'nickName', align:'left'" width='15%'>用户昵称</th>
		<th data-options="field:'taBalance', align:'center'" width='10%'>账户余额</th>
		<th data-options="field:'rates', align:'center'" width='10%'>RMB等值比例</th>
		<th data-options="field:'rechargeTime', align:'center'" width='20%'>充值时间（最新时间）</th>
		<th data-options="field:'withdrawTime', align:'center'" width='20%'>提现时间（最新时间）</th>
		<th data-options="field:'createTime', align:'center'" width='20%'>创建时间</th>
		<!-- <th data-options="field:'action',align:'center',formatter:userWalletMgr.actionFormatter" width='10%'>操作 </th> -->
<!-- 		<th data-options="field:'unknown',align:'center',formatter:walletMgr.formatter" width='0%'>隐藏栏</th> -->
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>

 <script>	 
 eason.register("walletMgr");
 
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/api/wallet/walletList/query",
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
});


//用户管理：操作
/* userWalletMgr.actionFormatter = function(value, row, index) {
	if(row.orderStatus==4 && row.closeType==-1){
		var transfer = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"transferMgr.transfer('"+ row.orderId + "','"+row.taskId+"',event)\">打款</a>";
		return transfer;
	}
	else if(row.closeType==0 && row.orderStatus==7){
		var success = "<a style=\'color:red;\'>打款成功</a>";
		return success;
	}
}; */
walletMgr.formatter = function(value,row,index){
	$("#totalBalance").html(
			"</span>所有用户的钱包余额为：<input style='color:red;width:5%;' value='"+row.totalBalance+"'/>元");
}
</script>
 
