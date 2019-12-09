<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/admin/common/head"/> 
<jsp:include page="/admin/common/system"/> 
<%String path = request.getContextPath(); %>
<div id="table_tools" style="padding:0;height:auto;"> 
	<div style="padding:2px;border:1px solid #ccc;">
	<form id="queryParams" style="margin:0" >
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			验证码ID
			<input style="width:100px" name="id1" type="text"/>
		</span>
		&nbsp;	&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			用户ID
			<input style="width:100px" name=userId1 type="text"/>
		</span>
		&nbsp;	&nbsp;
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	</div>
	<div style="padding:2px;border:1px solid #ccc;">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="vcodeMgr.remove()">删除验证码</a>
	</div>
 </div> 
 
<!-- <table id="table_grid" class="easyui-datagrid" style="width:auto;  height: 450px; padding: 5px" >  -->
<table id="table_grid"  style="min-width:1000px; width:auto;" data-options="fitColumns:true,rownumbers:true,striped:true"> 
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'" width='10%'></th>
		<th data-options="field:'phone1',sortable:'true',align:'center'" width='15%'>电话号码</th>
		<!-- <th data-options="field:'id1',sortable:'true',align:'center'">ID</th> -->
		<th data-options="field:'userId1',sortable:'true',align:'center'" width='10%'>用户ID</th>
		<th data-options="field:'nickName1',sortable:'true',align:'center'" width='15%'>用户昵称</th>
		<th data-options="field:'code1',sortable:'true',align:'center'" width='15%'>验证码</th>
		<th data-options="field:'type1',sortable:'true',align:'center',
						formatter:function(value,row,index){ 
											if(value==1) 
				                         		return '注册';
				                         	if(value==2) 
				                         		return '重置密码';
				                         	if(value==3) 
				                         		return '修改手机号 ';		
				                         }"  width='20%'
							>类型</th>
		<th data-options="field:'sendTime1',sortable:'true',align:'center'" width='20%'>发送时间</th>
		
		<th data-options="field:'state1',sortable:'true',align:'center',
						formatter:function(value,row,index){ 
											if(value==1) 
				                         		return '未使用 ';
				                         	if(value==2) 
				                         		return '已经失效 ';
				                         	if(value==3) 
				                         		return '已经使用 ';		
				                         }"  width='20%'
							>状态</th>
		<th data-options="field:'updateTime1',sortable:'true',align:'center'" width='20%'>更新时间</th>
		<th data-options="field:'action',align:'center',formatter:vcodeMgr.actionFormatter" width='20%'>操作 </th>							
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<!-- <div id="addCategoryDialog"></div> -->
<div id="detailVcodeDialog"></div>
 <script>	 
 
 eason.register("vcodeMgr");
 
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/api/appuser/userValidationCode/query",
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
vcodeMgr.actionFormatter = function(value, row, index) {
	var detail = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"vcodeMgr.detail('"+ row.id1 + "',event)\">详细</a>";
	return detail;
};


//详情
//删除
vcodeMgr.remove = function(){
	remove('table_grid', "<%=path%>/api/appuser/userValidationCode/remove");
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
							ids += rows[i].id1+'-';
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
vcodeMgr.detail = function(id,e){
	var dialogType="detailVcodeDialog";
	var info="查看详情";
		var params={};
		$.post("<%=path%>/api/appuser/userValidationCode/query",{"id1":id},
				function(data){
					var vo=data.rows[0];
					params=vo;
					params.width=600;
					params.height=300;
					vcodeMgr.vo=vo;
					openDiog3(dialogType,info,"dialog/"+dialogType,"userForm_"+dialogType,params,function(){
						$('#table_grid').datagrid('reload');
						$("#"+dialogType).dialog("close");
					},function(){
						vcodeMgr.initVCodeDetail();
					});
				}
		);
}; 

//初始化详细页面
vcodeMgr.initVCodeDetail= function(){
	var vo = vcodeMgr.vo;
	var infoType;//（1为注册,2为重置密码,3修改手机号）  
	switch(vo.type1){
		case 1: infoType='注册';break;
		case 2: infoType='重置密码';break;
		case 3: infoType='修改手机号';break;
	};
	$("#type").val(infoType);
	
	var infoState;//（1:未使用 2：已经失效 3：已经使用）  
	switch(vo.state1){
		case 1: infoVip='未使用';break;
		case 2: infoVip='已经失效';break;
		case 3: infoVip='已经使用';break;
	};
	$("#isVip").val(infoVip); 
	$("#userLogo").attr("src",vo.logo);
	
	var infoStatus;
	switch(vo.status){
		case -2: infoStatus='封号';break;
		case -1: infoStatus='删除';break;
		case 0: infoStatus='正常';break;
		case 1: infoStatus='已注册';break;
		case 2: infoStatus='个人资料已完善';break;
		case 3: infoStatus='已上传头像';break;
	};
	$("#status").val(infoStatus);
	
}

</script>
 
