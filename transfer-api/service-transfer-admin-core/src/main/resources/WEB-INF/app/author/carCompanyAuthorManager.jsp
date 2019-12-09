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
		&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			状态
			<select id="status" name="status" style="width:130px;" class="easyui-combobox">
				<option value="" selected="selected">所有状态</option>
				<option value="0" >未认证</option>
				<option value="1" >认证中</option>
				<option value="2" >已认证</option>
				<option value="3" >认证失败</option>
			</select>
		</span>
		&nbsp;
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
		<th data-options="field:'carCompanyAuthorId',align:'center'" width='10%' >企业认证id</th>
		<th data-options="field:'userId',align:'center'"width='10%'>用户id</th>
		<th data-options="field:'companyName',align:'center'" >企业名称</th>
		<th data-options="field:'legalPerson',align:'center'" width='15%'>法人姓名</th>
		<th data-options="field:'legalPhone',align:'center'" width='15%'>法人电话</th>
		<th data-options="field:'idCard',align:'center'">身份证号</th>
		<th data-options="field:'businessNo',align:'center'" width='15%'>企业登记号</th>
		<th data-options="field:'status',align:'center',formatter:CarCompanyAuthorMgr.statusFormatter" width='15%'>状态</th>
		<th data-options="field:'createTime',align:'center',formatter:function(value,row,index){ 
                         if(value){
 							var unixTimestamp = new Date(value); 
                         	return unixTimestamp.toLocaleString(); 						 
                         } 
                         }">创建时间</th>
		<th data-options="field:'dealBy',align:'center'" width='15%'>处理人</th>    
		<th data-options="field:'dealTime',align:'center',formatter:function(value,row,index){ 
                         if(value){
 							var unixTimestamp = new Date(value); 
                         	return unixTimestamp.toLocaleString(); 						 
                         }
                         }">处理时间</th>
		<th data-options="field:'action',align:'center',formatter:CarCompanyAuthorMgr.actionFormatter" width='25%'>操作 </th>							
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<div id="acceptCarCompanyAuthorDialog"></div>
<div id="refuseCarCompanyAuthorDialog"></div>
<div id="detailCarCompanyAuthorDialog"></div>
 <script>	 
 eason.register("CarCompanyAuthorMgr");
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/admin/author/carcompany/query",
		checkOnSelect:false,
	  	selectOnCheck:false,
	  	singleSelect:true,
		striped:true, 
		nowrap:true,
		fit:true,
		fitColumns: true,
		pagination: true,
		rownumbers: true, 
		idField   : "carCompanyAuthorId",
		sortName  : 'create_time',
		sortOrder : 'asc',
		toolbar   : "#table_tools", 
		onSortColumn:function(sort,order){
			$(this).datagrid('options').sortName = sort; 
			$(this).datagrid('options').sortOrder = order;
		} 
	});
});

//0=未认证、1=认证中、2=已认证、3=认证失败
CarCompanyAuthorMgr.statusFormatter = function(value, row, index) {
	var info;
	switch(value){
		case 0: info='<a style=\'color:red;\'>未认证</a>';break;
		case 1: info='<a style=\'color:red;\'>认证中</a>';break;
		case 2: info='<a style=\'color:green;\'>已认证</a>';break;
		case 3: info='<a style=\'color:red;\'>认证失败</a>';break;
	};
	return info;
};

CarCompanyAuthorMgr.actionFormatter = function(value, row, index) {
	var detail = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"CarCompanyAuthorMgr.detail('"+ row.carCompanyAuthorId + "',event)\">详情</a>";
	var accept = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"CarCompanyAuthorMgr.accept('"+ row.carCompanyAuthorId + "',event)\">通过认证</a>";
	var refuse = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"CarCompanyAuthorMgr.refuse('"+ row.carCompanyAuthorId + "',event)\">拒绝认证</a>";
	if(row.status==1){
		return detail+" | "+accept+" | "+refuse;
	}else{
		return detail;
	}
};



CarCompanyAuthorMgr.detail=function(carCompanyAuthorId,e){
	var dialogType="detailCarCompanyAuthorDialog";
	var info="查看详情";
	var params={};
	CarCompanyAuthorMgr.carCompanyAuthorId=carCompanyAuthorId;//绑定全局变量：此id是method的id
	$.post("<%=path%>/admin/author/carcompany/detail",{"carCompanyAuthorId":carCompanyAuthorId},
			function(data){
				var vo=data.returnObj;
				CarCompanyAuthorMgr.vo=vo;
				params=vo;
				params.width=820;
				params.height=600;
				openDiog3(dialogType,info,"dialog/"+dialogType,"form_"+dialogType,params,function(){
					$('#table_grid').datagrid('reload');
					$("#"+dialogType).dialog("close");
				},function(){
					CarCompanyAuthorMgr.initDetail();//给详细页面设置参数值
				});
			}
	);
};

CarCompanyAuthorMgr.initDetail=function(){
	var vo = CarCompanyAuthorMgr.vo;
	$("#idCardPic").attr("src",vo.idCardPic);
	$("#businessPic").attr("src",vo.businessPic);
	 
	//0=未认证、1=认证中、2=已认证、3=认证失败
	var infoStatus;
	switch(vo.status){
		case 0: infoStatus='未认证';break;
		case 1: infoStatus='认证中';break;
		case 2: infoStatus='已认证';break;
		case 3: infoStatus='认证失败';break;
	};
	$("#authorStatus").val(infoStatus);
	
	$('#table_grid_2').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/admin/author/car/query?carCompanyAuthorId="+vo.carCompanyAuthorId,
		checkOnSelect:false,
	  	selectOnCheck:false,
	  	singleSelect:true,
		striped:true, 
		nowrap:true,
		fit:true,
		fitColumns: true,
		pagination: true,
		rownumbers: true, 
		idField   : "carAuthorId",
		sortName  : 'create_time',
		sortOrder : 'asc',
		onSortColumn:function(sort,order){
			$(this).datagrid('options').sortName = sort; 
			$(this).datagrid('options').sortOrder = order;
		} 
	});
};

CarCompanyAuthorMgr.accept=function(carCompanyAuthorId,e){
	var dialogType="acceptCarCompanyAuthorDialog";
	var info="接受认证";
	var params={};
	params.width=510;
	params.height=330;
	params.carCompanyAuthorId=carCompanyAuthorId;
	openDiog(dialogType,info,"dialog/"+dialogType,"form_"+dialogType,params,function(){
		$("#table_grid").datagrid("reload");
		$("#"+dialogType).dialog("close");
	});
};

CarCompanyAuthorMgr.refuse=function(carCompanyAuthorId,e){
	var dialogType="refuseCarCompanyAuthorDialog";
	var info="拒绝认证";
	var params={};
	params.width=510;
	params.height=330;
	params.carCompanyAuthorId=carCompanyAuthorId;
	openDiog(dialogType,info,"dialog/"+dialogType,"form_"+dialogType,params,function(){
		$("#table_grid").datagrid("reload");
		$("#"+dialogType).dialog("close");
	});
};
</script>
 
