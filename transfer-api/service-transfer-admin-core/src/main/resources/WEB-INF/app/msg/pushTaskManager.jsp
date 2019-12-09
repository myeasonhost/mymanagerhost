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
			推送人ID
			<input style="width:100px" id="fromUserId" name="fromUserId" type="text"/>
		</span>
		&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			推送人昵称
			<input style="width:100px" id="fromUserName" name="fromUserName" type="text"/>
		</span>
		&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			接收人ID
			<input style="width:100px" id="toUserId" name="toUserId" type="text"/>
		</span>
		&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			接收人昵称
			<input style="width:100px" id="toUserName" name="toUserName" type="text"/>
		</span>
		&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			推送时间
			<input style="width:100px" id="pushTime" name="pushTime" type="text"/>
		</span>
		&nbsp;
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	</div>
	<div style="padding:2px;border:1px solid #ccc;">
	 	<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"  onclick="pushTaskMgr.add()">新增用户</a> -->
		<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="pushTaskMgr.edit()">修改用户</a> -->
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="pushTaskMgr.remove()">删除消息</a> 
	</div>
 </div> 
<table id="table_grid"  style="fitColumns:false;min-width:1000px;height=1000px;overflow-x:scroll;overflow-y:scroll;" > 
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'" width='10%'></th>
		<th data-options="field:'pushId', align:'center'"  width='10%'>推送ID</th>
		<th data-options="field:'pushCode' , align:'center'" width='20%'>模板CODE</th>
		<th data-options="field:'fromUserName', align:'center'" width='10%'>推送人昵称</th>
		<th data-options="field:'toUserName', align:'center'" width='10%'>接收人昵称</th>
		<th data-options="field:'pushTitle', align:'center'" width='10%'>推送标题</th>
		<th data-options="field:'pushContent', align:'center'" width='20%'>推送内容</th>
		<th data-options="field:'pushTime', align:'center'" width='20%'>推送时间</th>
		<th data-options="field:'registerId', align:'center'" width='20%'>注册ID</th>
		<th data-options="field:'action',align:'center',formatter:pushTaskMgr.actionFormatter" width='10%'>操作 </th>						
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<div id="detailPushMsgDialog"></div>
 <script>	 
 
 eason.register("pushTaskMgr");
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/api/msg/pushTask/query",
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
pushTaskMgr.actionFormatter = function(value, row, index) {
	var detail = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"pushTaskMgr.detail('"+ row.pushId + "',event)\">详细</a>";
	return detail;
};

//详情
//删除
pushTaskMgr.remove = function(){
	remove('table_grid', "<%=path%>/api/msg/pushTask/remove");
};

pushTaskMgr.taskParamFormatter = function(value,row,index){ 
	if(value.length!=0) {
		var obj2=JSON.parse(JSON.stringify(value));
 		return obj2;
 		}
 }

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
pushTaskMgr.detail = function(id,e){
	var dialogType="detailPushMsgDialog";
	var info="查看详情";
		var params={};
		$.post("<%=path%>/api/msg/pushTask/query",{"pushId":id},
				function(data){
					var vo=data.rows[0];
					params=vo;
					params.width=800;
					params.height=600;
					pushTaskMgr.vo=vo;
					openDiog3(dialogType,info,"<%=path%>/api/app/msg/dialog/"+dialogType,"userForm_"+dialogType,params,function(){
						$('#table_grid').datagrid('reload');
						$("#"+dialogType).dialog("close");
					},function(){
						pushTaskMgr.initTaskDetail();
					});
				}
		);
}; 

//初始化详细页面
pushTaskMgr.initTaskDetail= function(){
	var vo = pushTaskMgr.vo;
	$("#pic1").attr("src",vo.pic1);
}
</script>
 
