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
			充值时间(精确到天)
			<input name="rechargeTime" id="rechargeTime" style="width:140px" class="easyui-datetimebox" data-options="formatter:function(date){
				var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				return y+'-'+m+'-'+d}"/>
		</span>
		&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
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
			充值金额
			<input type='text' id='rechargeMny' name="rechargeMny" style="width:100px;height:23px;margin:2px;padding:0px;"/>
		</span>
		&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			支付状态
			<select id="userPayStatus" name="userPayStatus" style="width:130px;" class="easyui-combobox">
				<option value="" selected="selected">所有状态</option>
				<option value="1" >待支付</option>
				<option value="2" >充值成功</option>
				<option value="3" >充值失败</option>
			</select>
		</span>
		&nbsp;
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	 </div>
	 <div style="padding:2px;border:1px solid #ccc;">
	 	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"  id="exportRecharge">导出Excel表</a>
		<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="taskMgr.edit()">修改用户</a> -->
		<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="taskMgr.remove()">删除任务</a>  -->
	</div>
</div>
<table id="table_grid"  style="fitColumns:false;min-width:1000px;height=1000px;overflow-x:scroll;overflow-y:scroll;" > 
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'" width='10%'></th>
		<th data-options="field:'rechargeOrderId', align:'left'"width='17%'>充值明细id</th>
		<th data-options="field:'nickName', align:'center'" width='10%'>用户昵称</th>
		<th data-options="field:'rechargeMny', align:'center'"width='10%'>充值金额</th>
		<th data-options="field:'rechargeTa', align:'center'"width='10%'>RMB等值</th> 
		<th data-options="field:'rechargeTime', align:'center'" width='17%'>充值时间（最新时间）</th>
		<th data-options="field:'accountName', align:'center'" width='17%'>账户名称</th>
		<th data-options="field:'account', align:'center'" width='17%'>充值账户</th>
		<th data-options="field:'accountType', align:'center',
						formatter:function(value,row,index){ 
											if(value=='AliPay') 
				                         		return '支付宝';
				                         	if(value=='WeChatPay') 
				                         		return '微信支付';
				                         	if(value=='UnionPay') 
				                         		return '银联卡';
				                         }"  width='10%'
							>任务类型</th>
		<th data-options="field:'userPayStatus',align:'center',formatter:userRechargeMgr.actionFormatter" width='10%'>支付状态 </th>
		</tr>
	</thead>
</table>

 <script>	 
 eason.register("userRechargeMgr");
 
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/api/recharge/rechargeList/query",
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
	$("#exportRecharge").click(function(){
 		$('#table_grid').datagrid('doExport');
 	});
});


//用户管理：操作
userRechargeMgr.actionFormatter = function(value, row, index) {
	if(row.userPayStatus=='1'){
		var transfer = "<p style=\'color:red;\'>待支付</p>";
	}else if(row.userPayStatus=='2'){
		var transfer = "<p style=\'color:green;\'>支付成功</p>";
	}else if(row.userPayStatus=='3'){
		var transfer = "<p style=\'color:#7c828b;\'>支付失败</p>";
	}
	return transfer;
}; 

</script>
 
