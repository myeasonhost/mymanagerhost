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
		<th data-options="field:'carAuthorId',align:'center'" >车辆认证id</th>
		<th data-options="field:'userId',align:'center'">用户id</th>
		<th data-options="field:'carNo',align:'center'" width='10%'>车牌号</th>
		<th data-options="field:'idCard',align:'center'" width='15%'>身份证号</th>
		<th data-options="field:'status',align:'center',formatter:CarAuthorMgr.statusFormatter" width='15%'>状态</th>
		<th data-options="field:'createBy',align:'center'" width='15%'>创建人</th>
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
		<th data-options="field:'action',align:'center',formatter:CarAuthorMgr.actionFormatter" width='25%'>操作 </th>							
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<div id="acceptCarAuthorDialog"></div>
<div id="refuseCarAuthorDialog"></div>
<div id="detailCarAuthorDialog"></div>
 <script>	 
 eason.register("CarAuthorMgr");
$(function(){ 
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/admin/author/car/query",
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
		toolbar   : "#table_tools", 
		onSortColumn:function(sort,order){
			$(this).datagrid('options').sortName = sort; 
			$(this).datagrid('options').sortOrder = order;
		} 
	});
});

//0=未认证、1=认证中、2=已认证、3=认证失败
CarAuthorMgr.statusFormatter = function(value, row, index) {
	var info;
	switch(value){
		case 0: info='<a style=\'color:red;\'>未认证</a>';break;
		case 1: info='<a style=\'color:red;\'>认证中</a>';break;
		case 2: info='<a style=\'color:green;\'>已认证</a>';break;
		case 3: info='<a style=\'color:red;\'>认证失败</a>';break;
	};
	return info;
};

CarAuthorMgr.actionFormatter = function(value, row, index) {
	var detail = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"CarAuthorMgr.detail('"+ row.carAuthorId + "',event)\">详情</a>";
	var accept = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"CarAuthorMgr.accept('"+ row.carAuthorId + "',event)\">通过认证</a>";
	var refuse = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"CarAuthorMgr.refuse('"+ row.carAuthorId + "',event)\">拒绝认证</a>";
	if(row.status==1){
		return detail+" | "+accept+" | "+refuse;
	}else{
		return detail;
	}
};



CarAuthorMgr.detail=function(carAuthorId,e){
	var dialogType="detailCarAuthorDialog";
	var info="查看详情";
	var params={};
	CarAuthorMgr.carAuthorId=carAuthorId;//绑定全局变量：此id是method的id
	$.post("<%=path%>/admin/author/car/detail",{"carAuthorId":carAuthorId},
			function(data){
				var vo=data.returnObj;
				CarAuthorMgr.vo=vo;
				params=vo;
				params.width=820;
				params.height=600;
				openDiog3(dialogType,info,"dialog/"+dialogType,"form_"+dialogType,params,function(){
					$('#table_grid').datagrid('reload');
					$("#"+dialogType).dialog("close");
				},function(){
					CarAuthorMgr.initDetail();//给详细页面设置参数值
				});
			}
	);
};

CarAuthorMgr.initDetail=function(){
	var vo = CarAuthorMgr.vo;
	$("#logo").attr("src",vo.logo);
	$("#xszPic").attr("src",vo.xszPic);
	$("#yszPic").attr("src",vo.yszPic);
	$("#qzxPic").attr("src",vo.qzxPic);
	$("#szxPic").attr("src",vo.szxPic);
	$("#ssxPic").attr("src",vo.ssxPic);
	$("#txzPic").attr("src",vo.txzPic);
	$("#idCardPic").attr("src",vo.idCardPic);
	$("#operationCardPic").attr("src",vo.operationCardPic);
	$("#carCardPic").attr("src",vo.carCardPic);
	//1=双温，2=单温
	var tempType;
	switch(vo.tempType){
		case 1: tempType='双温';break;
		case 2: tempType='单温';break;
	};
	$("#tempType").val(tempType);
	
	 
	//0=未认证、1=认证中、2=已认证、3=认证失败
	var infoStatus;
	switch(vo.status){
		case 0: infoStatus='未认证';break;
		case 1: infoStatus='认证中';break;
		case 2: infoStatus='已认证';break;
		case 3: infoStatus='认证失败';break;
	};
	$("#authorStatus").val(infoStatus);
};

CarAuthorMgr.accept=function(carAuthorId,e){
	var dialogType="acceptCarAuthorDialog";
	var info="接受认证";
	var params={};
	params.width=510;
	params.height=330;
	params.carAuthorId=carAuthorId;
	openDiog(dialogType,info,"dialog/"+dialogType,"form_"+dialogType,params,function(){
		$("#table_grid").datagrid("reload");
		$("#"+dialogType).dialog("close");
	});
};

CarAuthorMgr.refuse=function(carAuthorId,e){
	var dialogType="refuseCarAuthorDialog";
	var info="拒绝认证";
	var params={};
	params.width=510;
	params.height=330;
	params.carAuthorId=carAuthorId;
	openDiog(dialogType,info,"dialog/"+dialogType,"form_"+dialogType,params,function(){
		$("#table_grid").datagrid("reload");
		$("#"+dialogType).dialog("close");
	});
};
</script>
 
