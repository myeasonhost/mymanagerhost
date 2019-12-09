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
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"  onclick="RangeParamsMgr.add()">新增项</a>
	</div>
 </div> 
<table id="table_grid"  style="min-width:1000px;" data-options="fitColumns:true,rownumbers:true,striped:true">
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'"></th>
		<th data-options="field:'rangeParamId',align:'center'" >报价项Id</th>
		<th data-options="field:'distanceMin',align:'center'" width='15%'>最小距离（KM）</th>
		<th data-options="field:'distanceMax',align:'center'" width='15%'>最大距离（KM）</th>
		<th data-options="field:'rangeMin',align:'center'" width='15%'>最小件数（件）</th>
		<th data-options="field:'rangeMax',align:'center',formatter:RangeParamsMgr.rangeMaxFormatter" width='15%'>最大件数（件）</th>
		<th data-options="field:'rangeCharge',align:'center'" >费用（元）</th>
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
		<th data-options="field:'action',align:'center',formatter:RangeParamsMgr.actionFormatter" width='20%'>操作 </th>							
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<div id="modifyRangeParamDialog"></div>
<div id="addRangeParamDialog"></div>
 <script>	 
 eason.register("RangeParamsMgr");
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/admin/range/query",
		checkOnSelect:false,
	  	selectOnCheck:false,
	  	singleSelect:true,
		striped:true, 
		nowrap:true,
		fit:true,
		fitColumns: true,
		pagination: true,
		rownumbers: true, 
		idField   : "range_param_id",
		sortName  : 'create_time',
		sortOrder : 'asc',
		toolbar   : "#table_tools", 
		onSortColumn:function(sort,order){
			$(this).datagrid('options').sortName = sort; 
			$(this).datagrid('options').sortOrder = order;
		} 
	});
});

RangeParamsMgr.rangeMaxFormatter = function(value, row, index) {
	if(value==2147483647){
		return "大于"+row.rangeMin;
	}
	return value;
};

RangeParamsMgr.actionFormatter = function(value, row, index) {
	var detail = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"RangeParamsMgr.modify('"+ row.rangeParamId + "',event)\">修改</a>";
	var remove = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"RangeParamsMgr.remove('"+ row.rangeParamId + "',event)\">删除</a>";

	return detail+"|"+remove;
};



RangeParamsMgr.add=function(){
	var dialogType="addRangeParamDialog";
	var params={};
	params.width=510;
	params.height=330;
	openDiog(dialogType,"新增报价项","dialog/"+dialogType,"form_"+dialogType,params,function(){
		$("#table_grid").datagrid("reload");
		$("#"+dialogType).dialog("close");
	});
};

RangeParamsMgr.modify=function(rangeParamId,e){
	var dialogType="modifyRangeParamDialog";
	$.post("<%=path%>/admin/range/detail",{rangeParamId:rangeParamId},function(data){
		if(data.result){
			var params=data.returnObj;
			params.width=510;
			params.height=330;
			openDiog2(dialogType,"编辑报价项","dialog/"+dialogType,"form_"+dialogType,params,function(){
				$("#table_grid").datagrid("reload");
				$("#"+dialogType).dialog("close");
			},function(){
				RangeParamsMgr.initDetail();//给详细页面设置参数值
			});
		}
	},"json");
};

RangeParamsMgr.initDetail=function(){
	if($("#rangeMax").val()==2147483647){
		var min=$("#rangeMin").val();
		$("#rangeMax").val("大于"+min);
		$("#rangeMax").attr("disabled","disabled");
	}
};

RangeParamsMgr.remove=function(rangeParamId,e){
	 $.messager.confirm('温馨提示','是否确定删除?',function(r){   
         if (r){   
             $.post("<%=path%>/admin/range/remove",{rangeParamId:rangeParamId},function(data){
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
 
