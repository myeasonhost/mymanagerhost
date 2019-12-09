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
		<th data-options="field:'playerId',sortable:'true',align:'center'">玩家ID</th>
		<th data-options="field:'adminer',align:'center'">操作人</th>
		<th data-options="field:'createdTm',sortable:'true',align:'center'">修改时间</th>
		<th data-options="field:'updType',sortable:'true',align:'center',formatter:LogMgr.statusFormatter" width='10%'>修改字段</th>
		<th data-options="field:'updValue',sortable:'true',align:'center'" width='10%'>更新的值</th>
		<th data-options="field:'memo',sortable:'true',align:'center'">修改原因</th>
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>

 <script>	 
$(function(){ 
	var obj = window.dialogArguments;
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/admin/business/customerModifyLog/query?playerId="+obj.id,
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
		sortName  : 'createdTm',
		sortOrder : 'desc',
		toolbar   : "#table_tools", 
		onSortColumn:function(sort,order){
			$(this).datagrid('options').sortName = sort; 
			$(this).datagrid('options').sortOrder = order;
		}   
	});
});
eason.register("LogMgr");
LogMgr.statusFormatter = function(value, row, index) {
	var info;
	switch(value){
		case 1: info='<a style=\'color:green;\'>玩家</a>';break;
		case 2: info='<a style=\'color:red;\'>密码</a>';break;
		case 3: info='<a style=\'color:green;\'>呢称</a>';break;
		case 4: info='<a style=\'color:green;\'>手机</a>';break;
		case 5: info='<a style=\'color:green;\'>签名</a>';break;
		case 6: info='<a style=\'color:green;\'>头像</a>';break;
		case 11: info='<a style=\'color:green;\'>金币</a>';break;
		case 12: info='<a style=\'color:green;\'>金叶子</a>';break;
		case 13: info='<a style=\'color:green;\'>经验值</a>';break;
		case 14: info='<a style=\'color:green;\'>魅力值</a>';break;
		case 21: info='<a style=\'color:green;\'>玫瑰数量</a>';break;
		case 22: info='<a style=\'color:green;\'>香水数量</a>';break;
		case 23: info='<a style=\'color:green;\'>钻石数量</a>';break;
		case 24: info='<a style=\'color:green;\'>跑车数量</a>';break;
		case 25: info='<a style=\'color:green;\'>别墅数量</a>';break;
		case 31: info='<a style=\'color:green;\'>胜局数</a>';break;
		case 32: info='<a style=\'color:green;\'>负局数</a>';break;
	};
	return info;
}
</script>
 
