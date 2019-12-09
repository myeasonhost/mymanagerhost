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
		<th data-options="field:'accountId',sortable:'true',align:'center'">内部ID</th>
		<th data-options="field:'externId',sortable:'true',align:'center'">外部ID</th>
		<th data-options="field:'account',sortable:'true',align:'center'">账号</th>
		<th data-options="field:'nickname',sortable:'true',align:'center'" width='15%'>呢称</th>
		<th data-options="field:'status',sortable:'true',align:'center',formatter:CustomerMgr.statusFormatter" width='15%'>状态</th>
		<th data-options="field:'mobile',sortable:'true',align:'center'" width='15%'>手机</th>
		<th data-options="field:'channel',sortable:'true',align:'center'" width='15%'>注册渠道</th>
		<th data-options="field:'registerTime',sortable:'true',align:'center',formatter:function(value,row,index){ 
                         var unixTimestamp = new Date(value); 
                         return unixTimestamp.toLocaleString(); 
                         }">注册时间</th>
		<th data-options="field:'registerIp',sortable:'true',align:'center'">注册ip</th>
		<th data-options="field:'coins',sortable:'true',align:'center'">金币</th>  
		<th data-options="field:'vipGrade',sortable:'true',align:'center'">vip等级</th>  
		<th data-options="field:'leaves',sortable:'true',align:'center'">金叶子 </th>  
		<th data-options="field:'experience',sortable:'true',align:'center'">经验值 </th>
		<th data-options="field:'action',sortable:'true',align:'center',formatter:CustomerMgr.actionFormatter" width='20%'>操作 </th>
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
	<div id="table_tools" style="padding:0;height:auto;"> 
	<div style="padding:2px;border:1px solid #ccc;">
	<form id="queryParams" style="margin:0" >
		<span style="width:80px;height:23px;margin:2px;padding:0px;" >
			内部ID（=）
			<input style="width:100px" name="accountId" type="text"/>
		</span>
		<span style="width:80px;height:23px;margin:2px;padding:0px;" >
			外部ID（=）
			<input style="width:100px" name="externId" type="text"/>
		</span>
		<span style="width:80px;height:23px;margin:2px;padding:0px;" >
			登陆账号（=）
			<input style="width:100px" name="account" type="text"/>
		</span>
		<span style="width:80px;height:23px;margin:2px;padding:0px;" >
			手机（=）
			<input style="width:100px" name="mobile" type="text"/>
		</span>
		<span style="width:80px;height:23px;margin:2px;padding:0px;" >
			呢称（匹配查询）
			<input style="width:100px" name="nickname" type="text"/>
		</span>&nbsp;&nbsp;&nbsp;
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	 <div style="padding:3px;border:1px solid #ccc;text-align:right">
			<font color="red">*警告:非工作人员请勿尝试操作!</font>
			<a id="exportDealBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"><spring:message code='common.ExportDeal'/></a>
	 </div>
	</div>
 </div> 
 <script>	 
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/admin/zjh/customerQuery/query",
		checkOnSelect:false,
	  	selectOnCheck:false,
	  	singleSelect:true,
		striped:true, 
		nowrap:true,
		fit:true,
		fitColumns: true,
		pagination: true,
		rownumbers: true, 
		idField   : "accountId",
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
eason.register("CustomerMgr");
CustomerMgr.statusFormatter = function(value, row, index) {
	var info;
	switch(value){
		case 1: info='<a style=\'color:green;\'>大厅</a>';break;
		case 2: info='<a style=\'color:green;\'>游戏中旁观</a>';break;
		case 3: info='<a style=\'color:green;\'>游戏中中对局</a>';break;
		case 4: info='<a style=\'color:red;\'>已下线</a>';break;
		case 5: info='<a style=\'color:green;\'>老虎机</a>';break;
		case 6: info='<a style=\'color:green;\'>拳馆中</a>';break;
	};
	return info;
}

CustomerMgr.actionFormatter = function(value, row, index) {
	var edit="<span height='23px' width='23px'><img src='<%=path%>/image/edit.png' title='编辑玩家信息' style='cursor: pointer' onclick='CustomerMgr.edit("+row.accountId+",event)'></img></span>";
	var detail="<span height='23px' width='23px'><img src='<%=path%>/image/detail.png' title='查看修改日志' style='cursor: pointer' onclick='CustomerMgr.detail("+row.accountId+",event)'></img></span>";
	return edit+detail;
}
//编辑
CustomerMgr.edit = function(id,e){
	var params=new Object();
	params.id=id;
	params.type="edit";
	com.eason.window.winUtils.showDialogWindowOfWH(650,510,e,"<%=path%>/admin/zjh/customerQueryEdit",params,function(returnValue){
		if("success"==returnValue){
			$('#table_grid').datagrid('reload');
		}
	});
};
//查看Log
CustomerMgr.detail = function(id,e){
	var params=new Object();
	params.id=id;
	params.type="Log";
	com.eason.window.winUtils.showDialogWindowOfWH(700,430,e,"<%=path%>/admin/zjh/customerModifyLogList",params,function(returnValue){
		if("success"==returnValue){
    		$('#table_grid').datagrid('reload');
		}
	});
};
 
</script>
 
