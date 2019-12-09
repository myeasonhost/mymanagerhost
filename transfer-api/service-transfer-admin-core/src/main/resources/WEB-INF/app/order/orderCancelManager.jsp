<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/admin/common/head"/> 
<jsp:include page="/admin/common/system"/> 
<%String path = request.getContextPath(); %>
<div id="table_tools" style="padding:0;height:auto;"> 
	<div style="padding:2px;border:1px solid #ccc;">
	<form id="queryParams" style="margin:0" >
		<span style="display:inline-block;width:320px;height:23px;margin:5px;padding:0px;" >
			日期查询
			<input name="createtimeStart" style="width:100px" class="easyui-datebox" data-options="formatter:function(date){
				var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				return y+'-'+m+'-'+d}"/>
			-
			<input name="createtimeEnd" style="width:100px" class="easyui-datebox" data-options="formatter:function(date){
			    var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				return y+'-'+m+'-'+d}"/>
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
		<th data-options="field:'orderCancelId',align:'center'" >订单取消id</th>
		<th data-options="field:'cargoOrderId',align:'center'">订单id</th>
		<th data-options="field:'userId',align:'center'">用户id</th>
		<th data-options="field:'userType',align:'center',formatter:OrderCancelMgr.userTypeFormatter" >用户类型</th>
		<th data-options="field:'chargeBack',align:'center'">扣款金额</th>
		<th data-options="field:'status',align:'center',formatter:OrderCancelMgr.statusFormatter">状态</th>
		<th data-options="field:'cancelReason',align:'center'" width='15%'>取消原因</th>
         <th data-options="field:'cancelTime',align:'center',formatter:function(value,row,index){ 
                         if(value){
 							var unixTimestamp = new Date(value); 
                         	return unixTimestamp.toLocaleString(); 						 
                         } 
                         }">取消时间</th>
		<th data-options="field:'dealBy',align:'center'" width='15%'>处理人</th>    
		<th data-options="field:'dealTime',align:'center',formatter:function(value,row,index){ 
                         if(value){
 							var unixTimestamp = new Date(value); 
                         	return unixTimestamp.toLocaleString(); 						 
                         }
                         }">处理时间</th>
<!--         <th data-options="field:'dealRemark',align:'center'">处理结果</th> -->
		<th data-options="field:'action',align:'center',formatter:OrderCancelMgr.actionFormatter" width='25%'>操作 </th>	
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<div id="acceptOrderCancelDialog"></div>
<div id="refuseOrderCancelDialog"></div>
<div id="detailOrderCancelDialog"></div>
 <script>	 
eason.register("OrderCancelMgr");
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/api/ordercancel/query",
		checkOnSelect:false,
	  	selectOnCheck:false,
	  	singleSelect:true,
		striped:true, 
		nowrap:true,
		fit:true,
		fitColumns: true,
		pagination: true,
		rownumbers: true, 
		idField   : "cargoOrderId",
		sortName  : 'cancel_time',
		sortOrder : 'asc',
		toolbar   : "#table_tools", 
		onSortColumn:function(sort,order){
			$(this).datagrid('options').sortName = sort; 
			$(this).datagrid('options').sortOrder = order;
		} 
	});
});

//用户管理：操作  删除的状态暂时不管
OrderCancelMgr.userTypeFormatter = function(value, row, index) {
	if(value==1){
		return '车主';
	}else if(value==2){
		return '货主';
	}
};

OrderCancelMgr.statusFormatter = function(value, row, index) {
	if(value==1){
		return '<a style=\'color:red;\'>申请处理</a>';
	}else if(value==2){
		return '<a style=\'color:green;\'>处理完成</a>';
	}
};

OrderCancelMgr.actionFormatter = function(value, row, index) {
	var detail = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"OrderCancelMgr.detail('"+ row.orderCancelId + "',event)\">详情</a>";
	var accept = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"OrderCancelMgr.accept('"+ row.orderCancelId + "','"+ row.cargoOrderId +"',event)\">审批处理</a>";
	if(row.status==1){
		return detail+" | "+accept;
	}else{
		return detail;
	}
};



OrderCancelMgr.detail=function(orderCancelId,e){
	var dialogType="detailOrderCancelDialog";
	var info="查看详情";
	var params={};
	OrderCancelMgr.orderCancelId=orderCancelId;//绑定全局变量：此id是method的id
	$.post("<%=path%>/admin/ordercancel/detail",{"orderCancelId":orderCancelId},
			function(data){
				var vo=data.returnObj;
				OrderCancelMgr.vo=vo;
				params=vo;
				params.width=820;
				params.height=580;
				openDiog3(dialogType,info,"dialog/"+dialogType,"form_"+dialogType,params,function(){
					$('#table_grid').datagrid('reload');
					$("#"+dialogType).dialog("close");
				},function(){
					OrderCancelMgr.initDetail();//给详细页面设置参数值
				});
			}
	,"json");
};

OrderCancelMgr.initDetail=function(){
	var vo = OrderCancelMgr.vo;
	var infoStatus;
	switch(vo.status){
		case 1: infoStatus='申请处理';break;
		case 2: infoStatus='处理完成';break;
	};
	$("#status").val(infoStatus);
};

OrderCancelMgr.accept=function(orderCancelId,cargoOrderId,e){
	var dialogType="acceptOrderCancelDialog";
	var info="审批处理";
	var params={};
	params.width=620;
	params.height=420;
	params.orderCancelId=orderCancelId;
	params.cargoOrderId=cargoOrderId;
	openDiog(dialogType,info,"dialog/"+dialogType,"form_"+dialogType,params,function(){
		$("#table_grid").datagrid("reload");
		$("#"+dialogType).dialog("close");
	});
};

</script>
 
