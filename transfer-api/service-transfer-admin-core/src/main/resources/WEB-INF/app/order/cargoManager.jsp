<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/admin/common/head"/> 
<jsp:include page="/admin/common/system"/> 
<%String path = request.getContextPath(); %>
<div id="table_tools" style="padding:0;height:auto;"> 
	<div style="padding:2px;border:1px solid #ccc;">
	<form id="queryParams" style="margin:0" >
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			货物名称（模糊查询）
			<input style="width:100px" name="cargoName" type="text"/>
		</span>
		&nbsp;	&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			用户账号（模糊查询）
			<input style="width:100px" name="account" type="text"/>
		</span>
		&nbsp;	&nbsp;
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	</div>
 </div> 
<table id="table_grid"  style="min-width:1000px;" data-options="fitColumns:true,rownumbers:true,striped:true">
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'"></th>
		<th data-options="field:'account',align:'center'" >用户账号</th>
		<th data-options="field:'cargoName',align:'center'">货品名称</th>
		<th data-options="field:'startLocation',align:'center'">出货地点</th>
		<th data-options="field:'shipperName',align:'center'" >发货人</th>
		<th data-options="field:'shipperPhone',align:'center'">发货电话</th>
		<th data-options="field:'endLocation',align:'center'" >收货地址</th>
		<th data-options="field:'receiverName',align:'center'">收货人名称</th>
		<th data-options="field:'remark',align:'center'" width='15%'>货物描述</th>    
		<th data-options="field:'createTime',align:'center',formatter:function(value,row,index){ 
                         if(value){
 							var unixTimestamp = new Date(value); 
                         	return unixTimestamp.toLocaleString(); 						 
                         } 
                         }">创建时间</th>
<!-- 		<th data-options="field:'action',align:'center',formatter:CargoMgr.actionFormatter" width='15%'>操作 </th>							 -->
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<div id="cargoDetailDialog"></div>
 <script>	 
eason.register("CargoMgr");
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/api/cargo/query",
		checkOnSelect:false,
	  	selectOnCheck:false,
	  	singleSelect:true,
		striped:true, 
		nowrap:true,
		fit:true,
		fitColumns: true,
		pagination: true,
		rownumbers: true, 
		idField   : "cargoId",
		sortName  : 'create_time',
		sortOrder : 'asc',
		toolbar   : "#table_tools", 
		onSortColumn:function(sort,order){
			$(this).datagrid('options').sortName = sort; 
			$(this).datagrid('options').sortOrder = order;
		} 
	});
});

//用户管理：操作  删除的状态暂时不管
CargoMgr.actionFormatter = function(value, row, index) {
	var detail = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"CargoMgr.detail('"+ row.userId + "',event)\">详细</a>";
	return detail;
};

//详情
CargoMgr.detail = function(userId,e){
	var dialogType="cargoDetailDialog";
	var info="查看详情";
		var params={};
		CargoMgr.detailId=userId;//绑定全局变量：此id是method的id
		$.post("<%=path%>/api/appuser/userinfo/query",{"userId":userId},
				function(data){
					var vo=data.rows[0];
					CargoMgr.vo = vo;
					params=vo;
					params.width=820;
					params.height=570;
					CargoMgr.vo=vo;
					openDiog3(dialogType,info,"dialog/"+dialogType,"userForm_"+dialogType,params,function(){
						$('#table_grid').datagrid('reload');
						$("#"+dialogType).dialog("close");
					},function(){
						CargoMgr.initDetail();//给详细页面设置参数值
					});
				}
		);
}; 
//初始化详细页面
CargoMgr.initDetail= function(){
	var vo = CargoMgr.vo;
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
		case -1: infoStatus='调整中';break;
		case 0: infoStatus='已创建';break;
		case 1: infoStatus='已取消';break;
		case 2: infoStatus='发布中';break;
		case 3: infoStatus='取货中';break;
		case 4: infoStatus='配送中';break;
		case 5: infoStatus='已完成';break;
	};
	$("#status").val(infoStatus);
	
}
</script>
 
