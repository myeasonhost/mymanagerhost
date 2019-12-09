<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/admin/common/head"/> 
<jsp:include page="/admin/common/system"/> 
<%String path = request.getContextPath(); %>
<div id="table_tools" style="padding:0;height:auto;"> 
	<div style="padding:2px;border:1px solid #ccc;">
	<form id="queryParams" style="margin:0" >
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			类别英文名（模糊查询）
			<input style="width:100px" name="cateEnName" type="text"/>
		</span>
		&nbsp;	&nbsp;	&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			类别中文名（模糊查询）
			<input style="width:100px" name="cateCnName" type="text"/>
		</span>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	</div>
	<div style="padding:2px;border:1px solid #ccc;">
	 	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"  onclick="categoryMgr.add()">新增类别</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="categoryMgr.edit()">修改类别</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="categoryMgr.remove()">删除类别</a>
	</div>
 </div> 
<table id="table_grid"  style="min-width:1000px;" data-options="fitColumns:true,rownumbers:true,striped:true">
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'" width='10%'></th>
		<th data-options="field:'id',sortable:'true',align:'center'">类别Id</th>
		<th data-options="field:'cateEnName',sortable:'true',align:'center'" width='15%'>类别英文名字</th>
		<th data-options="field:'cateCnName',sortable:'true',align:'center'" width='15%'>类别中文名字</th>
		<th data-options="field:'cateDescription',sortable:'true',align:'center'" width='15%'>类别描述</th>
		<th data-options="field:'isDeleted',sortable:'true',align:'center',
						formatter:function(value,row,index){ 
							if(value==true) 
                         		return '<a style=\'color:red;\'>已删除</a>';
                         	else
                         	   return '未删除';	 
                         }" width='15%'> 是否删除</th>
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<div id="addCategoryDialog"></div>
<div id="editCategoryDialog"></div>
 <script>	 
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/admin/api/category/query",
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
eason.register("categoryMgr");
categoryMgr.add = function() {
	var dialogType="addCategoryDialog";
	var info="新增类别";
	var params={};
	params.width=430;
	params.height=350;
	openDiog(dialogType,info,"<%=path%>/admin/api/dialog/"+dialogType,"userForm_"+dialogType,params,function(){
		$('#table_grid').datagrid('reload');
		$("#"+dialogType).dialog("close");
	});
}

//编辑
categoryMgr.edit = function(){
	var row = $('#table_grid').datagrid("getChecked");
	if (row.length == 1) {
		var dialogType="editCategoryDialog";
		var info="修改类别";
		$.post("<%=path%>/admin/api/categoryByOne/query",{"id":row[0].id},function(data){
			var params={};
			params=data;
			params.width=600;
			params.height=400;
			openDiog(dialogType,info,"<%=path%>/admin/api/dialog/"+dialogType,"userForm_"+dialogType,params,function(){
				$('#table_grid').datagrid('reload');
				$("#"+dialogType).dialog("close");
			});
		},"json");
	} else {
		$.messager.alert('', '请选择一条修改的记录！', 'info');
	}	

};
//删除
categoryMgr.remove = function(id,e){
	remove('table_grid', "<%=path%>/admin/api/category/remove");
};
/* function remove(gridId,url){
	var rows = $('#'+gridId).datagrid("getChecked");
	if(rows.length>0){
		var tmpstr = ' <spring:message code="common.records"/>';
	 	if(rows.length==1){
			tmpstr=  ' <spring:message code="common.record"/>';
		}  
	 	$.messager.confirm('', '<spring:message code="common.del1"/> ' + rows.length + ' ' + tmpstr + '？',
				function(r) {
					if (r) {
						var ids = "";
						var images = "";
						var count = 0;
						var unCount = 0;
						for ( var i = 0; i < rows.length; i++) {
							if(rows[i].isTop=='1'){
								unCount ++;
								continue;
							}
							//ids.push(rows[i].id);
							ids += rows[i].advId+'-';
							images += rows[i].advImage+'@';
							count ++;
						}
						if(unCount>0){
							$.messager.alert('', '有 '+unCount+' 条置顶的广告不能直接删除！', 'info');
						}
						$.post(url, {
							"ids":ids,"images":images
						}, function(data, status) {
							if ("success" == status && "1" == data) {
								$.messager.show({
									title : '',
									msg : '成功删除 '+count+' 条记录',
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
} */
</script>
 
