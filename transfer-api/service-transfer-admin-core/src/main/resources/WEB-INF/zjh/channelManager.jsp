<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/admin/common/head"/> 
<jsp:include page="/admin/common/system"/> 
<%String path = request.getContextPath(); %>
<div id="table_tools" style="padding:0;height:auto;"> 
	<div style="padding:2px;border:1px solid #ccc;">
	 	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"  onclick="ChannelMgr.add()">新增渠道</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="ChannelMgr.edit()">修改渠道</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="ChannelMgr.remove()">删除渠道</a>
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
<div id="addChannelDialog"></div>
<div id="editChannelDialog"></div>
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
});
eason.register("ChannelMgr");
ChannelMgr.add = function() {
	var dialogType="addChannelDialog";
	var info="新增渠道";
	var params={};
	params.width=430;
	params.height=350;
	openDiog(dialogType,info,"<%=path%>/admin/zjh/dialog/"+dialogType,"userForm_"+dialogType,params,function(){
		$('#table_grid').datagrid('reload');
		$("#"+dialogType).dialog("close");
	});
}

//编辑
ChannelMgr.edit = function(){
	var row = $('#table_grid').datagrid("getChecked"); 
	if (row.length == 1) {
		var dialogType="editChannelDialog";
		var info="修改渠道";
		$.post("<%=path%>/admin/business/channelMgr/query",{"channelId":row[0].channelId},function(data){
			var params={};
			params=data.rows[0];
			params.width=430;
			params.height=350;
			openDiog(dialogType,info,"<%=path%>/admin/zjh/dialog/"+dialogType,"userForm_"+dialogType,params,function(){
				$('#table_grid').datagrid('reload');
				$("#"+dialogType).dialog("close");
			});
		},"json");
	} else {
		$.messager.alert('', '请选择一条修改的记录！', 'info');
	}	

};
//删除
ChannelMgr.remove = function(id,e){
	remove('table_grid', "<%=path%>/admin/zjh/channelMgr/del");
};
 
</script>
 
