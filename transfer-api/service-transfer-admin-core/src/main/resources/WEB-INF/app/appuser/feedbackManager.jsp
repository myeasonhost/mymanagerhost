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
			反馈主题（模糊）:
			<input style="width:100px" id="title" name="title" type="text"/>
		</span>
		&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			反馈人（模糊）:
			<input style="width:100px" id="feedbackuserName" name="feedbackuserName" type="text"/>
		</span>
		&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			处理人（模糊）:
			<input style="width:100px" id="dealuserName" name="dealuserName" type="text"/>
		</span>
		&nbsp;
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	</div>
	<div style="padding:2px;border:1px solid #ccc;">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="feedbackMgr.remove()">删除</a> 
	</div>
 </div> 
<table id="table_grid"  style="fitColumns:false;min-width:1000px;height=1000px;overflow-x:scroll;overflow-y:scroll;" > 
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'" width='10%'></th>
		<th data-options="field:'id', align:'center'"width='5%'>ID</th>
		<th data-options="field:'title', align:'center'" width='10%'>反馈主题</th>
		<th data-options="field:'content', align:'center'" width='50%'>反馈内容</th>
		<th data-options="field:'feedbackuserName', align:'left'" width='10%'>反馈人</th>
		<th data-options="field:'feedbacktime', align:'center'" width='15%'>反馈时间</th>
		<th data-options="field:'dealuserName', align:'left'" width='10%'>处理人</th>
		<th data-options="field:'dealtime', align:'center'" width='15%'>处理时间</th>
		<th data-options="field:'action',align:'center',formatter:feedbackMgr.actionFormatter" width='10%'>操作 </th>
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>

 <script>	 
 eason.register("feedbackMgr");
 
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/api/appuser/feedback/query",
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

//删除
feedbackMgr.remove = function(){
	remove('table_grid', "<%=path%>/api/appuser/feedback/remove");
};


//用户管理：操作
feedbackMgr.actionFormatter = function(value, row, index) {
	if(row.dealtime == null){
		var deal = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"feedbackMgr.deal('"+ row.id + "',event)\">处理</a>";
		return deal;
	}
};
//处理操作
feedbackMgr.deal= function(id,e){
	$.messager.confirm('', '确定已经处理过反馈人意见？',
			function(r) {
				if (r) {
					$.post("<%=path%>/api/appuser/feedback/updateTime", {
						'id':id
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
 
