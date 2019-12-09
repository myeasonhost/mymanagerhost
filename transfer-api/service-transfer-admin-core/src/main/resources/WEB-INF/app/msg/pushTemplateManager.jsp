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
			模板Code
			<input style="width:100px" id="code" name="code" type="text"/>
		</span>
		&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			模板名（模糊）
			<input style="width:100px" name="name" type="text"/>
		</span>
		&nbsp;
<!-- 		<span style="width:100px;height:23px;margin:2px;padding:0px;" > -->
<!-- 			模板类型 -->
<!-- 			<select id="tempType" name="tempType" style="width:130px;" class="easyui-combobox"> -->
<!-- 				<option value="-1" selected="selected">-- 模板类型 --</option> -->
<!-- 				<option value="0" >系统消息</option> -->
<!-- 				<option value="1" >任务消息</option> -->
<!-- 				<option value="2">推送消息模板</option> -->
<!-- 				<option value="3" >支付消息</option> -->
<!-- 			</select> -->
<!-- 		</span> -->
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	</div>
	<div style="padding:2px;border:1px solid #ccc;">
	 	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"  onclick="pushMsgMgr.add()">新增模板</a> 
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="pushMsgMgr.edit()">修改模板</a>		
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="pushMsgMgr.remove()">删除模板</a> 
	</div>
 </div> 
<table id="table_grid"  style="fitColumns:false;min-width:1000px;height=1000px;overflow-x:scroll;overflow-y:scroll;" > 
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'" width='10%'></th>
		<th data-options="field:'code', align:'center'"width='10%'>模板CODE</th>
		<th data-options="field:'name', align:'center'" width='10%'>模板名</th>
		<th data-options="field:'context', align:'left'" width='60%'>模板内容</th>
		<th data-options="field:'tempType', align:'center',
					formatter:function(value,row,index){ 
										if(value==0) 
			                         		return '系统消息';
			                         	if(value==1) 
			                         		return '任务消息';
			                         	if(value==2) 
			                         		return '推送消息模板';
			                         	if(value==3) 
			                         		return '支付消息';
			                         }"  width='10%'
						>模板类型</th>
		<th data-options="field:'demo', align:'center'" width='20%'>模板示例</th>
		<th data-options="field:'description', align:'center'" width='20%'>模板描述</th>
		<th data-options="field:'createtime', align:'center'" width='20%'>创建时间</th>
		<!-- <th data-options="field:'createby', align:'center'" width='20%'>创建人</th> -->
		<th data-options="field:'updatetime', align:'center'" width='20%'>更新时间</th>
		<th data-options="field:'action',align:'center',formatter:pushMsgMgr.actionFormatter" width='10%'>操作 </th>							
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>

<div id="addTemplateDialog"></div>
<div id="editTemplateDialog"></div>

 <script>	 
 eason.register("pushMsgMgr");
 
 pushMsgMgr.add = function() {
		var dialogType="addTemplateDialog";
		var info="新增模板";
		var params={};
		params.width=430;
		params.height=350;
		openDiog(dialogType,info,"<%=path%>/api/msg/dialog/"+dialogType,"userForm_"+dialogType,params,function(){
			$('#table_grid').datagrid('reload');
			$("#"+dialogType).dialog("close");
		});
	}
	//编辑
	pushMsgMgr.edit = function(){
		var row = $('#table_grid').datagrid("getChecked");
		if (row.length == 1) {
			var dialogType="editTemplateDialog";
			var info="修改模板";
			$.post("<%=path%>/api/msg/template/query",{"code":row[0].code},function(data){
				var params={};
				params=data.rows[0];
				params.width=430;
				params.height=350;
				openDiog(dialogType,info,"<%=path%>/api/msg/dialog/"+dialogType,"userForm_"+dialogType,params,function(){
					$('#table_grid').datagrid('reload');
					$("#"+dialogType).dialog("close");
				});
			},"json");
		} else {
			$.messager.alert('', '请选择一条修改的记录！', 'info');
		}	
	};
 
 
 
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/api/msg/template/query",
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
pushMsgMgr.actionFormatter = function(value, row, index) {
	if(row.tempType==0){
		var pushAll = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"pushMsgMgr.pushAll('"+ row.appId + "','"+ row.code + "','"+row.name+ "','"+row.context+"',event)\">推送所有设备</a>";
		return pushAll;
	}
};

//查看详情 appId设定为1
pushMsgMgr.pushAll = function(appId,code,pushTitle,pushContent,e){
	$.messager.confirm('', '确定给所有用户推送此消息？',
			function(r) {
				if (r) {
					$.post("<%=path%>/api/msg/sysMsg/pushAll", {
						'appId':1,
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

//详情
//删除
pushMsgMgr.remove = function(){
	var row = $('#table_grid').datagrid("getChecked");
	if (row.length == 1) {
		//删除时判断是否为系统消息，系统消息才能删除
		remove('table_grid', "<%=path%>/api/msg/template/remove?tempType="+row[0].tempType);		
	}else {
		$.messager.alert('', '请选择一条修改的记录！', 'info');
	}	
};

pushMsgMgr.taskParamFormatter = function(value,row,index){ 
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
							ids += rows[i].code+'-';
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
</script>
 
