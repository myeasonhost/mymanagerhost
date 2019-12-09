<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/admin/common/head"/> 
<jsp:include page="/admin/common/system"/> 
<%String path = request.getContextPath(); %>
<div id="table_tools" style="padding:0;height:auto;"> 
	<div style="padding:2px;border:1px solid #ccc;">
	<form id="queryParams" style="margin:0" >
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			订单号
			<input style="width:100px" name="cargoOrderId" type="text"/>
		</span>
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
		<th data-options="field:'cargoApplyId',align:'center'" width='20%'>接单ID</th>
		<th data-options="field:'cargoOrderId',align:'center'" width='20%'>订单ID</th>
		<th data-options="field:'applyType',align:'center',formatter:OrderApplyMgr.typeFormatter" width='20%'>接单类型</th>
		<th data-options="field:'applyTime',align:'center',formatter:function(value,row,index){ 
                         if(value){
 							var unixTimestamp = new Date(value); 
                         	return unixTimestamp.toLocaleString(); 						 
                         } 
                         }"  width='20%'>接单时间</th>
		<th data-options="field:'action',align:'center',formatter:OrderApplyMgr.actionFormatter" width='15%'>操作 </th>							
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<div id="detailOrderApplyDialog"></div>
 <script>	 
eason.register("OrderApplyMgr");
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/admin/orderapply/query",
		checkOnSelect:false,
	  	selectOnCheck:false,
	  	singleSelect:true,
		striped:true, 
		nowrap:true,
		fit:true,
		fitColumns: true,
		pagination: true,
		rownumbers: true, 
		idField   : "cargo_apply_id",
		sortName  : 'apply_time',
		sortOrder : 'asc',
		toolbar   : "#table_tools", 
		onSortColumn:function(sort,order){
			$(this).datagrid('options').sortName = sort; 
			$(this).datagrid('options').sortOrder = order;
		} 
	});
});

OrderApplyMgr.typeFormatter=function(value, row, index) {
	if(value=="0"){
		return '<a style=\'color:red;\'>个人接单</a>';
	}else if(value=="1"){
		return '<a style=\'color:red;\'>企业接单</a>';
	}
};

//用户管理：操作  删除的状态暂时不管
OrderApplyMgr.actionFormatter = function(value, row, index) {
	var detail = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"OrderApplyMgr.detail('"+ row.cargoApplyId + "',event)\">详细</a>";
	return detail;
};

//详情 
OrderApplyMgr.detail=function(cargoApplyId,e){
	var dialogType="detailOrderApplyDialog";
	var info="查看详情";
	var params={};
	OrderApplyMgr.cargoApplyId=cargoApplyId;//绑定全局变量：此id是method的id
	$.post("<%=path%>/admin/orderapply/detail",{"cargoApplyId":cargoApplyId},
			function(data){
				var vo=data.returnObj;
				OrderApplyMgr.vo=vo;
				params=vo;
				params.width=820;
				params.height=580;
				openDiog3(dialogType,info,"dialog/"+dialogType,"form_"+dialogType,params,function(){
					$('#table_grid').datagrid('reload');
					$("#"+dialogType).dialog("close");
				},function(){
					OrderApplyMgr.initDetail();//给详细页面设置参数值
				});
			}
	,"json");
};

//初始化详细页面
OrderApplyMgr.initDetail= function(){
	var vo = OrderApplyMgr.vo;
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
		case 7: infoStatus='取消完成';break;
		case 2: infoStatus='发布中';break;
		case 3: infoStatus='取货中';break;
		case 4: infoStatus='配送中';break;
		case 5: infoStatus='车主完成配送';break;
		case 6: infoStatus='货主完成收货';break;
	};
	$("#status").val(infoStatus);
	
};
</script>
 
