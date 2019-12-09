<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%String path = request.getContextPath(); %>

<div id="table_tools_params" style="padding:0;height:auto;"> 
	<div style="padding:2px;border:1px solid #ccc;">
	 	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"  onclick="methodParamMgr.add()">新增参数</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="methodParamMgr.edit()">修改参数</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="methodParamMgr.remove()">删除参数</a>
	</div>
 </div> 
<table id="table_grid_paramsList"  style="min-width:1000px;" data-options="fitColumns:true,rownumbers:true,striped:true">
	<caption></caption>
	<thead>
		<tr> 
		<!-- <th data-options="field:'id',sortable:'true',align:'center'">方法参数id</th> -->
		<th data-options="field:'check',checkbox:'true',align:'center'" width='10%'></th>
		<!-- <th data-options="field:'action',align:'center',formatter:methodParamMgr.actionFormatter" >操作 </th> -->
		<th data-options="field:'paramName',sortable:'true',align:'center'">参数名称</th>
		<th data-options="field:'paramType',sortable:'true',align:'center'">参数类型</th>
		<th data-options="field:'isNecessary',sortable:'true',align:'center',
					formatter:function(value,row,index){
											if(value==true) 
				                         		return '<a style=\'color:red;\'>是</a>';
				                         	else
				                         	   return '否';	 
				                         }" 
						>是否必须</th>  
		<th data-options="field:'example',sortable:'true',align:'center'">示例值</th>  
		<th data-options="field:'defaultValue',sortable:'true',align:'center'">默认值 </th>
		<th data-options="field:'paramDescription',sortable:'true',align:'center'">参数描述 </th>
		<th data-options="field:'paramClass',sortable:'true',align:'center',
					formatter:function(value,row,index){
													if(value==true) 
						                         		return '<a style=\'color:red;\'>1-应用级参数</a>';
						                         	else
						                         	   return '0-返回结果属性';	 
						                         }" 
						>参数类别</th>
		<th data-options="field:'isObject',sortable:'true',align:'center',
				formatter:function(value,row,index){
												if(value==true) 
					                         		return '<a style=\'color:red;\'>是</a>';
					                         	else
					                         	   return '否';	 
					                         }" 
						>是否自定义对象 </th>
		<th data-options="field:'createTime',sortable:'true',align:'center'">创建时间  </th>
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
