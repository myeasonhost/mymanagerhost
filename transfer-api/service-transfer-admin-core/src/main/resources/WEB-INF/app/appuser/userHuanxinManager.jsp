<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/admin/common/head"/> 
<jsp:include page="/admin/common/system"/> 
<%String path = request.getContextPath(); %>
<div id="table_tools" style="padding:0;height:auto;"> 
	<div style="padding:2px;border:1px solid #ccc;">
	<form id="queryParams" style="margin:0" >
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			用户Id
			<input style="width:100px" name="userId" type="text"/>
		</span>
		&nbsp;	&nbsp;	
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			电话号码（模糊查询）
			<input style="width:100px" name="phone" type="text"/>
		</span>
		&nbsp;	&nbsp;
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	</div>
	<div style="padding:2px;border:1px solid #ccc;">
	</div>
 </div> 
<table id="table_grid"  style="min-width:1000px;" data-options="fitColumns:true,rownumbers:true,striped:true">
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'" width='10%'></th>
		<th data-options="field:'logo',sortable:'true',align:'center',
								formatter:function(value,row,index){ 
											if(value!=null&&value!='') 
				                         		return '<img style=\'width:50px; height:50px\' src=\'' + value + '\'>';
				                         }"
							>用户Logo</th>
		<th data-options="field:'userId',sortable:'true',align:'center'" width='5%'>用户Id</th>
		<th data-options="field:'phone',sortable:'true',align:'center'" width='15%'>电话</th>
		<!-- <th data-options="field:'password',sortable:'true',align:'center'">密码</th> -->
		<th data-options="field:'nickName',sortable:'true',align:'center'" width='10%'>昵称</th>
		<th data-options="field:'gender',sortable:'true',align:'center'" width='5%'>性别</th>
	<!-- 	<th data-options="field:'gender',sortable:'true',align:'center',
						formatter:function(value,row,index){ 
											if(value==0) 
				                         		return '女';
				                         	else
				                         	   return '男';	 
				                         }" width='10%'
							>性别</th> -->
		<th data-options="field:'area',sortable:'true',align:'center'" width='5%'>用户居住地</th>
		<th data-options="field:'introduction',sortable:'true',align:'center'" width='20%'>个性签名</th>
		<th data-options="field:'bountyUserLv',sortable:'true',align:'center'" width='5%'>用户等级</th>
		<!-- <th data-options="field:'hunterUserLv',sortable:'true',align:'center'" width='5%'>猎人用户等级</th> -->
		<th data-options="field:'isVip',sortable:'true',align:'center',
							formatter:function(value,row,index){
											if(value==0) 
				                         		return '普通';
				                         	else if(value==1)
				                         	   return 'VIP';
				                         	else
				                         	   return '';   	 
				                         }"  width='5%'
								>是否VIP</th>
		<th data-options="field:'activated',sortable:'true',align:'center',
							formatter:function(value,row,index){
											if(value=='0') 
				                         		return '<a style=\'color:red;\'>未激活</a>';
				                         	else if(value=='1')
				                         	   return '<a style=\'color:green;\'>已激活</a>';
				                         }"  width='5%'
								>是否激活环信</th>
		<th data-options="field:'birth',sortable:'true',align:'center'" width='15%'>生日</th>
		<th data-options="field:'status',sortable:'true',align:'left',
						formatter:function(value,row,index){ 
											if(value==-2) 
				                         		return '<a style=\'color:red;\'>封号</a>';
				                         	if(value==-1) 
				                         		return '<a style=\'color:red;\'>删除</a>';	
				                         	if(value==0) 
				                         		return '';
				                         	if(value==1) 
				                         		return '<a style=\'color:green;\'>1,已注册</a>';	 
				                         	if(value==2) 
				                         		return '<a style=\'color:green;\'>1,已注册;2,个人资料已完善</a>';
				                         	if(value==3) 
				                         		return '<a style=\'color:green;\'>1,已注册;2,个人资料已完善;3,已上传头像</a>';		
				                         }"  
							>状态</th>
		<th data-options="field:'action',align:'center',formatter:userinfoMgr.actionFormatter" width='20%'>操作 </th>							
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<!-- <div id="addCategoryDialog"></div> -->
<div id="detailUserInfoDialog"></div>
 <script>	 
 eason.register("userinfoMgr");
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/api/appuser/userinfo/query",
		checkOnSelect:false,
	  	selectOnCheck:false,
	  	singleSelect:true,
		striped:true, 
		nowrap:true,
		fit:true,
		fitColumns: true,
		pagination: true,
		rownumbers: true, 
		idField   : "userId",
		sortName  : 'userId',
		sortOrder : 'asc',
		toolbar   : "#table_tools", 
		onSortColumn:function(sort,order){
			$(this).datagrid('options').sortName = sort; 
			$(this).datagrid('options').sortOrder = order;
		} 
	});
});

//用户管理：操作  删除的状态暂时不管
userinfoMgr.actionFormatter = function(value, row, index) {
	var detail = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"userinfoMgr.detail('"+ row.userId + "',event)\">详细</a>";
	var ban = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"userinfoMgr.updateBan('"+ row.userId + "',event)\">禁止</a>";
	var enable = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"userinfoMgr.updateEnable('"+ row.userId + "',event)\">启用</a>";
	if(row.status==-2){//被禁用的账户，显示启用按钮
		return detail+'|' + enable;	
	}else if(row.status==3){ //其他的显示禁用按钮。
		return detail+'|' + ban;	
	}else{
		return "<a style=\'color:red;\'>待完善</a>";	
	}
};


//详情
//删除
userinfoMgr.remove = function(userId,e){
	remove('table_grid', "<%=path%>/api/appuser/userinfo/remove");
};
/**
 * 给用户注册环信帐号
 */
userinfoMgr.registIM = function(){
	var rows = $('#table_grid').datagrid("getChecked");
	url = "<%=path%>/api/appuser/checkedPeople/registerIM";
	if(rows.length > 0) {
		$.messager.confirm('','确认给选定的'+rows.length+'个用户注册环信帐号吗？',
				function(r){
			if(r){
				//循环取出所有选中用户的值并包装为JSON
				var columns = JSON.stringify(rows);
				$.post(url,
						{'columns':columns},
					function(data,status){
						if('success'==status && '1'==data){
							$.messager.show({
								title : '',
								msg : '创建环信帐号成功',
								showType : 'show'
							});
						} else {
							$.messager.show({
								title : '',
								msg : '创建环信帐号异常，请检查用户数据是否完整或是否已注册' ,
								showType : 'show'
							});
						}
						$('#table_grid').datagrid('uncheckAll').datagrid(
								'unselectAll').datagrid('clearSelections');
						$('#table_grid').datagrid('reload');
					},"json");
				}
			}
		);
	}else {
		$.messager.alert('', '<spring:message code="common.NoRecordSct"/>', 'info');
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
							ids += rows[i].userId+'-';
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
userinfoMgr.detail = function(userId,e){
	var dialogType="detailUserInfoDialog";
	var info="查看详情";
		var params={};
		userinfoMgr.detailId=userId;//绑定全局变量：此id是method的id
		$.post("<%=path%>/api/appuser/userinfo/query",{"userId":userId},
				function(data){
					var vo=data.rows[0];
					userinfoMgr.vo = vo;
					params=vo;
					params.width=800;
					params.height=500;
					userinfoMgr.vo=vo;
					openDiog3(dialogType,info,"<%=path%>/api/appuser/dialog/"+dialogType,"userForm_"+dialogType,params,function(){
						$('#table_grid').datagrid('reload');
						$("#"+dialogType).dialog("close");
					},function(){
						userinfoMgr.initUserInfoDetail();//给详细页面设置参数值
					});
				}
		);
}; 
//初始化详细页面
userinfoMgr.initUserInfoDetail= function(){
	var vo = userinfoMgr.vo;
	/* var info;//（0：女；1：男）  
	switch(vo.gender){
		case 0: info='女';break;
		case 1: info='男';break;
	}; 
	$("#gender").val(info); */
	
	var infoVip;//（0=普通，1=VIP）
	if(vo.isVip=='0'){
		infoVip='普通';
	}else if(vo.isVip=='1'){
		infoVip='VIP';
	}
	$("#isVip1").val(infoVip);
	$("#userLogo").attr("src",vo.logo);
	
	var infoStatus;
	switch(vo.status){
		case -2: infoStatus='封号';break;
		case -1: infoStatus='删除';break;
		case 0: infoStatus='正常';break;
		case 1: infoStatus='V已注册';break;
		case 2: infoStatus='个人资料已完善';break;
		case 3: infoStatus='已上传头像';break;
	};
	$("#status").val(infoStatus);
	
}

userinfoMgr.updateBan= function(userId,e){
	$.messager.confirm('', '确定要禁用该用户？',
			function(r) {
				if (r) {
					$.post("<%=path%>/api/appuser/ban/updateStatus", {
						'userId':userId
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

userinfoMgr.updateEnable=function(userId,e){
	$.messager.confirm('', '确定要启用该用户？',
			function(r) {
				if (r) {
					$.post("<%=path%>/api/appuser/enable/updateStatus", {
						'userId':userId
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
 
