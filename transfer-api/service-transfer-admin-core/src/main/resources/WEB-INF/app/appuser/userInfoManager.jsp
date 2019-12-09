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
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			用户昵称（模糊查询）
			<input style="width:100px" name="nickName" type="text"/>
		</span>
		&nbsp;	&nbsp;
		<span style="width:50px;height:23px;margin:2px;padding:0px;" >
			VIP
			<select id="isVip" name="isVip" style="width:100px;" class="easyui-combobox">
				<option value="" selected="selected">是否VIP</option>
				<option value="0" >普通</option>
				<option value="1" >VIP</option>
			</select>
		</span>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	</div>
	<div style="padding:2px;border:1px solid #ccc;">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"  onclick="userinfoMgr.registUser()">注册新用户</a>
	</div>
 </div> 
<table id="table_grid"  style="min-width:1000px;" data-options="fitColumns:true,rownumbers:true,striped:true">
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'"></th>
		<th data-options="field:'logo',sortable:'true',align:'center',
								formatter:function(value,row,index){ 
											if(value!=null&&value!='') 
				                         		return '<img style=\'width:50px; height:50px\' src=\'' + value + '\'>';
				                         }"
							>用户Logo</th>
		<th data-options="field:'userId',sortable:'true',align:'center'" >用户Id</th>
		<th data-options="field:'phone',sortable:'true',align:'center'" width='15%'>电话</th>
		<!-- <th data-options="field:'password',sortable:'true',align:'center'">密码</th> -->
		<th data-options="field:'nickName',sortable:'true',align:'center'" width='10%'>昵称</th>
		<th data-options="field:'gender',sortable:'true',align:'center'" >性别</th>
		<th data-options="field:'introduction',sortable:'true',align:'center'" width='20%'>个性签名</th>
		<th data-options="field:'score',sortable:'true',align:'center'" >用户评分</th>
		<th data-options="field:'isVip',sortable:'true',align:'center',
							formatter:function(value,row,index){
											if(value==0) 
				                         		return '普通';
				                         	else if(value==1)
				                         	   return '<a style=\'color:red;\'>VIP</a>';
				                         	else
				                         	   return '';   	 
				                         }" 
								>是否VIP</th>
		<th data-options="field:'status',sortable:'true',align:'center',
						formatter:function(value,row,index){ 
											if(value=='-1') 
				                         		return '<a style=\'color:red;\'>封号</a>';
				                         	if(value=='0') 
				                         		return '<a style=\'color:red;\'>删除</a>';	
				                         	if(value=='1') 
				                         		return '<a style=\'color:green;\'>已注册</a>';	 
				                         }"
							width='15%'>状态</th>
		<th data-options="field:'createBy',sortable:'true',align:'center'" width='15%'>创建人</th>
		<th data-options="field:'createTime',sortable:'true',align:'center',formatter:function(value,row,index){ 
                         if(value){
 							var unixTimestamp = new Date(value); 
                         	return unixTimestamp.toLocaleString(); 						 
                         } 
                         }">创建时间</th>
		<th data-options="field:'updateBy',sortable:'true',align:'center'" width='15%'>修改人</th>    
		<th data-options="field:'updateTime',sortable:'true',align:'center',formatter:function(value,row,index){ 
                         if(value){
 							var unixTimestamp = new Date(value); 
                         	return unixTimestamp.toLocaleString(); 						 
                         }
                         }">修改时间</th>
		<th data-options="field:'action',align:'center',formatter:userinfoMgr.actionFormatter" width='20%'>操作 </th>							
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<!-- <div id="addCategoryDialog"></div> -->
<div id="detailUserInfoDialog"></div>
<div id="registUserDialog"></div>
 <script>	 
 eason.register("userinfoMgr");
 eason.register("userinfoRegisterMgr");
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
		sortName  : 'user_id',
		sortOrder : 'asc',
		toolbar   : "#table_tools", 
		onSortColumn:function(sort,order){
			$(this).datagrid('options').sortName = sort; 
			$(this).datagrid('options').sortOrder = order;
		} 
	});
});
userinfoRegisterMgr.getCode = function(){
	var url = "<%=path%>/api/appuser/sendCode/registerUser";
	var phone = $("#registPhone").val();
	if(phone==null||phone==""){
		$.messager.show({
			title : '',
			msg : '手机号不能为空！',
			showType : 'show'
		});
	}else if(phone.length!=11){
		$.messager.show({
			title : '',
			msg : '手机号格式不正确，请重新检查输入！',
			showType : 'show'
		});
	}else{
		$.post(
			url,{"phone":phone},
			function(data,status){
				if('success'==status && '0'!=data){
					console.log(data);
					$("#validateCode").val(data);
				}else{
					$.messager.show({
						title : '',
						msg : '验证码获取失败，请30秒后再试',
						showType : 'show'
					});
				}
			}
		);
	}
	
};

//用户管理：操作  删除的状态暂时不管
userinfoMgr.actionFormatter = function(value, row, index) {
	var detail = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"userinfoMgr.detail('"+ row.userId + "',event)\">详细</a>";
	var ban = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"userinfoMgr.updateBan('"+ row.userId + "',event)\">封号</a>";
// 	var enable = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"userinfoMgr.updateEnable('"+ row.userId + "',event)\">启用</a>"; 
// 	if(row.status==1){//被禁用的账户，显示启用按钮
// 		return detail+'|' + enable;	
// 	}else{ //其他的显示禁用按钮。
// 		return detail+'|' + ban;	
// 	}
	return detail;
};

//删除
userinfoMgr.remove = function(userId,e){
	remove('table_grid', "<%=path%>/api/appuser/userinfo/remove");
};

/**
 * 注册虚拟的用户
 */
userinfoMgr.registUser=function(){
	var dialogType="registUserDialog";
	var params={};
	params.width=510;
	params.height=330;
	openDiog(dialogType,"注册用户","dialog/"+dialogType,"userForm_"+dialogType,params,function(){
		$("#table_grid").datagrid("reload");
		$("#"+dialogType).dialog("close");
	});
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
//更新用户资料
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
					openDiog2(dialogType,info,"dialog/"+dialogType,"userForm_"+dialogType,params,function(){
						$('#table_grid').datagrid('reload');
						$("#"+dialogType).dialog("close");
					},function(){
						userinfoMgr.initUserInfoDetail();//给详细页面设置参数值
					},"json");
				}
		);
}; 
//初始化详细页面
userinfoMgr.initUserInfoDetail= function(){
	var vo = userinfoMgr.vo;
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
					$.post("<%=path%>/app/appuser/enable/updateStatus", {
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
 
