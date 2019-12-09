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
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	</div>
	<div style="padding:2px;border:1px solid #ccc;">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"  onclick="CargoParamMgr.add()">新增项</a>
	</div>
 </div> 
<table id="table_grid"  style="min-width:1000px;" data-options="fitColumns:true,rownumbers:true,striped:true">
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'"></th>
		<th data-options="field:'cargoParamId',align:'center'" >服务项ID</th>
		<th data-options="field:'serverItem',align:'center'" >服务项</th>
		<th data-options="field:'whetherCharge',align:'center',
							formatter:function(value,row,index){
											if(value==0) 
				                         		return '免费';
				                         	else if(value==1)
				                         	   return '<a style=\'color:green;\'>收费</a>';
				                         }" width='15%'>是否收费</th>
		<th data-options="field:'chargeAmount',align:'center'" width='15%'>收费标准（元）</th>
		<th data-options="field:'chargeUnit',align:'center'" width='15%'>收费单位</th>
		<th data-options="field:'createBy',align:'center'" width='15%'>创建人</th>
		<th data-options="field:'createTime',align:'center',formatter:function(value,row,index){ 
                         if(value){
 							var unixTimestamp = new Date(value); 
                         	return unixTimestamp.toLocaleString(); 						 
                         } 
                         }">创建时间</th>
		<th data-options="field:'updateBy',align:'center'" width='15%'>修改人</th>    
		<th data-options="field:'updateTime',align:'center',formatter:function(value,row,index){ 
                         if(value){
 							var unixTimestamp = new Date(value); 
                         	return unixTimestamp.toLocaleString(); 						 
                         }
                         }">修改时间</th>
		<th data-options="field:'action',align:'center',formatter:CargoParamMgr.actionFormatter" width='20%'>操作 </th>							
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<div id="addCargoParamDialog"></div>
<div id="modifyCargoParamDialog"></div>
 <script>	 
 eason.register("CargoParamMgr");
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/admin/cargoparam/query",
		checkOnSelect:false,
	  	selectOnCheck:false,
	  	singleSelect:true,
		striped:true, 
		nowrap:true,
		fit:true,
		fitColumns: true,
		pagination: true,
		rownumbers: true, 
		idField   : "cargoParamId",
		sortName  : 'create_time',
		sortOrder : 'asc',
		toolbar   : "#table_tools", 
		onSortColumn:function(sort,order){
			$(this).datagrid('options').sortName = sort; 
			$(this).datagrid('options').sortOrder = order;
		} 
	});
});

CargoParamMgr.actionFormatter = function(value, row, index) {
	var detail = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"CargoParamMgr.modify('"+ row.cargoParamId + "',event)\">修改</a>";
	var remove = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"CargoParamMgr.remove('"+ row.cargoParamId + "',event)\">删除</a>";

	return detail+"|"+remove;
};



CargoParamMgr.add=function(){
	var dialogType="addCargoParamDialog";
	var params={};
	params.width=510;
	params.height=330;
	openDiog(dialogType,"新增服务项","dialog/"+dialogType,"form_"+dialogType,params,function(){
		$("#table_grid").datagrid("reload");
		$("#"+dialogType).dialog("close");
	});
};

CargoParamMgr.modify=function(cargoParamId,e){
	var dialogType="modifyCargoParamDialog";
	$.post("<%=path%>/admin/cargoparam/detail",{cargoParamId:cargoParamId},function(data){
		if(data.result){
			var params=data.returnObj;
			params.width=510;
			params.height=330;
			openDiog2(dialogType,"编辑服务项","dialog/"+dialogType,"form_"+dialogType,params,function(){
				$("#table_grid").datagrid("reload");
				$("#"+dialogType).dialog("close");
			},function(){
				CargoParamMgr.initDetail();//给详细页面设置参数值
			});
		}
	},"json");
};

CargoParamMgr.initDetail=function(){
	
};

CargoParamMgr.remove=function(cargoParamId,e){
	 $.messager.confirm('温馨提示','是否确定删除?',function(r){   
         if (r){   
             $.post("<%=path%>/admin/cargoparam/remove",{cargoParamId:cargoParamId},function(data){
             	if(data.result){
             		$.messager.alert('提示','删除成功','info',function(){
             			$('#table_grid').datagrid('reload');  //刷新用户列表信息
             		});
             	}else{
             		$.messager.alert('错误',data.message,'error');   
             	}
             },'json');		                  
         }   
	    });
};


</script>
 
