<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/admin/common/head"/> 
<jsp:include page="/admin/common/system"/> 
<%String path = request.getContextPath(); %>
<div id="table_tools" style="padding:0;height:auto;"> 
	<div style="padding:2px;border:1px solid #ccc;">
	<form id="queryParams" style="margin:0" >
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			订单号（模糊查询）
			<input style="width:100px" name="cargoOrderId" type="text"/>
		</span>
		&nbsp;	
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			订单名称（模糊查询）
			<input style="width:100px" name="cargoOrderName" type="text"/>
		</span>
		&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			货主（模糊查询）
			<input style="width:100px" name="ownerName" type="text"/>
		</span>
		&nbsp;
		<span style="width:50px;height:23px;margin:2px;padding:0px;" >
			VIP
			<select id="isVip" name="isVip" style="width:100px;" class="easyui-combobox">
				<option value="" selected="selected">是否VIP</option>
				<option value="0" >普通</option>
				<option value="1" >VIP</option>
			</select>
		</span>
		&nbsp;
		<span style="width:50px;height:23px;margin:2px;padding:0px;" >
			订单状态
			<select id="status" name="status" style="width:100px;" class="easyui-combobox">
				<option value="" selected="selected">--请选择--</option>
				<option value="-1">调整中</option>
				<option value="0" >已创建</option>
				<option value="1" >申请取消</option>
				<option value="7" >取消完成</option>
				<option value="2" >发布中</option>
				<option value="3" >取货中</option>
				<option value="4" >配送中</option>
				<option value="5" >车主完成配送</option>
				<option value="6" >货主完成收货</option>
			</select>
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
		<th data-options="field:'cargoOrderId',sortable:'true',align:'center'" >订单号</th>
		<th data-options="field:'cargoOrderName',align:'center'">订单名称</th>
		<th data-options="field:'ownerName',align:'center'">货主</th>
		<th data-options="field:'ownerPhone',align:'center'">货主电话</th>
		<th data-options="field:'isVip',align:'center',formatter: OrderMgr.isVipFormatter">是否VIP</th>
		<th data-options="field:'dischargeAmount',align:'center'" width='15%'>卸货费用（元）</th>
		<th data-options="field:'rangeCharge',align:'center'" width='15%'>距离费用（元）</th>
<!-- 		<th data-options="field:'goCharge',align:'center'" width='15%'>运费（元）</th> -->
		<th data-options="field:'tip',align:'center'" width='15%'>小费（元）</th>
		<th data-options="field:'amount',align:'center'" width='15%'>订单运费（元）</th>
		<th data-options="field:'score',align:'center'">订单评分</th>
		<th data-options="field:'sendType',align:'center',
							formatter:function(value,row,index){
											if(value==1) 
				                         		return '拼车';
				                         	else if(value==2)
				                         	   return '<a style=\'color:green;\'>包车</a>';
				                         }" 
								>配送类型</th>
		<th data-options="field:'status',align:'center',
						formatter:function(value,row,index){ 
											if(value=='-1') 
				                         		return '<a style=\'color:green;\'>调整中</a>';
											if(value=='0') 
				                         		return '<a style=\'color:green;\'>已创建</a>';
				                         	if(value=='1') 
				                         		return '<a style=\'color:red;\'>已取消</a>';	
				                         	if(value=='2') 
				                         		return '<a style=\'color:green;\'>发布中</a>';
			                         		if(value=='3') 
			                         			return '<a style=\'color:green;\'>取货中</a>';
			                         		if(value=='4') 
			                         			return '<a style=\'color:green;\'>配送中</a>';
			                         		if(value=='5') 
			                         			return '<a style=\'color:red;\'>车主完成配送</a>';	
			                         		if(value=='6') 
			                         			return '<a style=\'color:red;\'>货主完成收货</a>'; 
			                         		if(value=='7') 
			                         			return '<a style=\'color:red;\'>取消完成</a>'; 
				                         }">状态</th>
		<th data-options="field:'createTime',align:'center',formatter:function(value,row,index){ 
                         if(value){
 							var unixTimestamp = new Date(value); 
                         	return unixTimestamp.toLocaleString(); 						 
                         } 
                         }">创建时间</th>
		<th data-options="field:'action',align:'center',formatter: OrderMgr.actionFormatter" width='20%'>操作 </th>							
		</tr>
	</thead>
 	<tbody>
 	</tbody>
</table>
<div id="cargoDetailDialog"></div>
<div id="companyApplyDialog"></div>
<script>	 
 eason.register("OrderMgr");
 $(function(){ 
 	$('#table_grid').datagrid({ 
 		width:"auto",
 	    height:"auto", 
 	    url:"<%=path%>/admin/order/query",
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

 OrderMgr.actionFormatter = function(value, row, index) {
 	var detail = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"OrderMgr.detail('"+ row.cargoOrderId + "',event)\">详细</a>";
 	if(row.status=='2'){
 	 	var apply = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"OrderMgr.apply('"+ row.cargoOrderId + "',event)\">企业接单</a>";
		return detail+"|"+apply;
 	}
 	return detail;
 };
 
 //用户管理：操作  删除的状态暂时不管
 OrderMgr.isVipFormatter = function(value, row, index) {
 	if(value=="0"){
		return '<a style=\'color:green;\'>普通用户</a>';
	}else if(value=="1"){
		return '<a style=\'color:red;\'>VIP用户</a>';
	}
 };

 //详情
 OrderMgr.detail = function(cargoOrderId,e){
 	var dialogType="cargoDetailDialog";
 	var info="查看详情";
 		var params={};
 		OrderMgr.cargoOrderId=cargoOrderId;//绑定全局变量：此id是method的id
 		$.post("<%=path%>/admin/order/detail/query",{"cargoOrderId":cargoOrderId},
 				function(data){
 					var vo=data.returnObj;
 					OrderMgr.vo=vo;
 					params=vo;
 					params.width=820;
 					params.height=600;
 					openDiog3(dialogType,info,"dialog/"+dialogType,"activityForm",params,function(){
 						$('#table_grid').datagrid('reload');
 						$("#"+dialogType).dialog("close");
 					},function(){
 						OrderMgr.initDetail();//给详细页面设置参数值
 					});
 				}
 		);
 }; 
 //初始化详细页面
 OrderMgr.initDetail= function(){
 	var vo = OrderMgr.vo;
 	$("#logo").attr("src",vo.logo);
 	$("#finishAuthorPic").attr("src",vo.finishAuthorPic);
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
 	
 	var tempEnvType; //0=任何温度  1=冷栋：-18~-22   2=冷藏：0--7  3=恒温：18-22  4=双温：-18~7   5=单温：7-22
 	switch(vo.tempEnvType){
 		case 0: tempEnvType='任何温度';break;
 		case 1: tempEnvType='冷栋（-18~-22）';break;
 		case 2: tempEnvType='冷藏（0~-7）';break;
 		case 3: tempEnvType='恒温（18~22）';break;
 		case 4: tempEnvType='双温（-18~7）';break;
 		case 5: tempEnvType='单温（7~22）';break;
 	};
 	$("#tempEnvType").val(tempEnvType);
 	
 	//取货人异常：0=没有异常，1=问题反馈异常，2=取消订单异常
 	var isPickupStatus;
 	switch(vo.isPickup){
 		case 0: isPickupStatus='订单正常';break;
 		case 1: isPickupStatus='车主订单问题反馈';break;
 		case 2: isPickupStatus='货主在发布中取消订单';break;
 	};
 	$("#isPickup").val(isPickupStatus);
	//加载地图
	var carList=vo.userCarList || [];
	var map = new AMap.Map("container", {
		zoom: 10,
        resizeEnable: true
    });
	map.addControl(new AMap.ToolBar());
	$.each(carList,function(index,item){
		if(item.carGps){
			   var marker = new AMap.Marker({
			        map:map,
			        position:item.carGps.split(",")
			    });
			    marker.setLabel({
			        offset: new AMap.Pixel(20, 20),//修改label相对于maker的位置
			        content: "<span style='color:red'>"+item.carLocation+"</span>"
			    });
		}
	});
 };
 
 //企业接单
 OrderMgr.apply = function(cargoOrderId,e){
 	var dialogType="companyApplyDialog";
 	var info="企业接单";
 	alert(info);
//  		var params={};
//  		OrderMgr.cargoOrderId=cargoOrderId;//绑定全局变量：此id是method的id
<%--  		$.post("<%=path%>/admin/order/detail/query",{"cargoOrderId":cargoOrderId}, --%>
//  				function(data){
//  					var vo=data.returnObj;
//  					OrderMgr.vo=vo;
//  					params=vo;
//  					params.width=820;
//  					params.height=600;
//  					openDiog3(dialogType,info,"dialog/"+dialogType,"activityForm",params,function(){
//  						$('#table_grid').datagrid('reload');
//  						$("#"+dialogType).dialog("close");
//  					},function(){
//  						OrderMgr.initDetail();//给详细页面设置参数值
//  					});
//  				}
//  		);
 }; 
</script>
 
