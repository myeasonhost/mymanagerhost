<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/admin/common/head"/> 
<jsp:include page="/admin/common/system"/> 
<%String path = request.getContextPath(); %>
<script type="text/javascript" src="<%=path %>/js/json.js"></script> 
<div id="table_tools" style="padding:0;height:auto;"> 
	<div style="padding:2px;border:1px solid #ccc;">
	<form id="queryParams" style="margin:0" >
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			App名称（模糊）
			<input style="width:100px" name=appName type="text"/>
		</span>
		&nbsp;
		<span style="width:100px;height:20px;margin:2px;padding:0px;" >
			推送时间(精确到天)
			<input name="pushTime" id="pushTime" style="width:140px" class="easyui-datetimebox" data-options="formatter:function(date){
				var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				return y+'-'+m+'-'+d}"/>
		</span>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	</div>
	<div style="padding:2px;border:1px solid #ccc;">
	 	<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"  onclick="pushSystemMgr.add()">新增用户</a> -->
		<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="pushSystemMgr.edit()">修改用户</a> -->
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="pushSystemMgr.remove()">删除</a> 
	</div>
 </div> 
<table id="table_grid"  style="fitColumns:false;min-width:1000px;height=1000px;overflow-x:scroll;overflow-y:scroll;" > 
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'" width='10%'></th>
		<th data-options="field:'code', align:'center'"  width='10%'>模板CODE</th>
		<th data-options="field:'appId', align:'center'">AppID</th> 
		<th data-options="field:'appName', align:'center'" width='10%'>APP名称</th>
		<th data-options="field:'pushTitle', align:'center'" width='20%'>推送标题</th>
		<th data-options="field:'pushContent', align:'center'" width='50%'>推送内容</th>
		<th data-options="field:'pushTime', align:'center'" width='20%'>推送时间</th>
		<th data-options="field:'pushResult', align:'center'" width='20%'>推送返回结果</th>
<!-- 		<th data-options="field:'action',align:'center',formatter:pushSystemMgr.actionFormatter" width='10%'>操作 </th>							 -->
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<div id="detailTaskDialog"></div>
 <script>	 
 
 eason.register("pushSystemMgr");
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/api/msg/sysMsg/query",
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
		sortName  : 'id',
		sortOrder : 'asc',
		toolbar   : "#table_tools", 
		onSortColumn:function(sort,order){
			$(this).datagrid('options').sortName = sort; 
			$(this).datagrid('options').sortOrder = order;
		} 
	});
});

//用户管理：操作
pushSystemMgr.actionFormatter = function(value, row, index) {
	var pushAll = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"pushSystemMgr.pushAll('"+ row.appId + "','"+ row.code + "','"+row.pushTitle+ "','"+row.pushContent+"',event)\">推送所有设备</a>";
	return pushAll;
};

//详情
//删除
pushSystemMgr.remove = function(){
	remove('table_grid', "<%=path%>/api/msg/sysMsg/remove");
};

//表格删除
function remove(gridId, url) {
	var rows = $('#' + gridId).datagrid("getChecked"); 
	if (rows.length > 0) {
	 	var tmpstr = ' <spring:message code="common.records"/>';
	 	if(rows.length==1){
			tmpstr=  ' <spring:message code="common.record"/>';
		}  
	  
		$.messager.confirm('', '<spring:message code="common.del1"/> ' + rows.length + ' ' + tmpstr + '？',
				function(r) {
					if (r) {
						var ids = "";
						for ( var i = 0; i < rows.length; i++) {
							//ids.push(rows[i].id);
							ids += rows[i].id+'-';
						}
						$.post(url, {
							ids:ids
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
							$('#' + gridId).datagrid('uncheckAll').datagrid(
									'unselectAll').datagrid('clearSelections');
							$('#' + gridId).datagrid('reload');
						}, "json");
					}
				});
	} else {
		$.messager.alert('', '<spring:message code="common.NoRecordSct"/>', 'info');
	}
}
//查看详情
pushSystemMgr.pushAll = function(appId,code,pushTitle,pushContent,e){
	$.messager.confirm('', '确定给所有用户推送此消息？',
			function(r) {
				if (r) {
					$.post("<%=path%>/api/msg/sysMsg/pushAll", {
						'appId':appId,
						'code':code,
						'pushTitle':pushTitle,
						'pushContent':pushContent
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

//初始化详细页面
pushSystemMgr.initTaskDetail= function(){
	var vo = pushSystemMgr.vo;
	$("#pic1").attr("src",vo.pic1);
}
</script>
 
