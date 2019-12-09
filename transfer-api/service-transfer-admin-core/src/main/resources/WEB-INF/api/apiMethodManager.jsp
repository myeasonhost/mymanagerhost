<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/admin/common/head"/> 
<jsp:include page="/admin/common/system"/> 
<%String path = request.getContextPath(); %>
<div id="table_tools" style="padding:0;height:auto;"> 
	<div style="padding:2px;border:1px solid #ccc;">
	<form id="queryParams" style="margin:0" >
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			方法类别
			<select id="methodType" name="methodType" style="width:130px;" class="easyui-combobox">
				<!-- <option value="-1" selected="selected">--方法类别--</option> -->
			</select>
		</span>
		
		&nbsp;	&nbsp;	&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			方法名（模糊查询）
			<input style="width:100px" name="method" type="text"/>
		</span>
		&nbsp;	&nbsp;	&nbsp;
		<span style="width:100px;height:23px;margin:2px;padding:0px;" >
			方法中文名（模糊查询）
			<input style="width:100px" name="methodName" type="text"/>
		</span>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="resetData('queryParams')"><spring:message code='common.Clean'/></a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryByPara('table_grid','queryParams')"><spring:message code='common.Search'/></a>
	 </form>
	</div>
	<div style="padding:2px;border:1px solid #ccc;">
	 	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"  onclick="methodMgr.add()">新增方法</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="methodMgr.edit()">修改方法</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"  onclick="methodMgr.remove()">删除方法</a>
	</div>
 </div> 
<table id="table_grid"  style="min-width:1000px;" data-options="fitColumns:true,rownumbers:true,striped:true">
	<caption></caption>
	<thead>
		<tr> 
		<th data-options="field:'check',checkbox:'true',align:'center'" width='10%'></th>
		<th data-options="field:'id',sortable:'true',align:'center'">方法Id</th>
		<th data-options="field:'cateEnName',sortable:'true',align:'center'" width='15%'>类别英文名字</th>
		<th data-options="field:'cateCnName',sortable:'true',align:'center'" width='15%'>类别中文名字</th>
		<th data-options="field:'method',sortable:'true',align:'center'" width='15%'>方法名称</th>
		<th data-options="field:'methodName',sortable:'true',align:'center'" width='15%'>方法中文名称</th>
		<th data-options="field:'methodMemo',sortable:'true',align:'center'" width='15%'>方法备注</th>
<!-- 		<th data-options="field:'invokeMinMaxNum',sortable:'true',align:'center'" width='15%'>一分钟可调用次数</th> -->
<!-- 		<th data-options="field:'invokeDayMaxNum',sortable:'true',align:'center'" width='15%'>一天可调用次数</th> -->
		<th data-options="field:'authLevel',sortable:'true',align:'center',
						formatter:function(value,row,index){ 
								if(value=='0') 
	                         		return '0-不需要appkey';
	                         	if(value=='1')	
	                         		return '1-需要appkey';
	                         	if(value=='2') 
	                         		return '2-需要appkey&sessionkey';
	                         	if(value=='3')	
	                         		return '3-需要appkey&sessionkey';	
	                         }" 
			 width='15%'>调用等级</th>
		<th data-options="field:'isUpdated',sortable:'true',align:'center',  
						formatter:function(value,row,index){ 
								if(value=='1') 
	                         		return '<a style=\'color:red;\'>是</a>';
	                         	else
	                         	   return '否';	 
	                         }" 
                         width='15%'>是否更新接口</th>
		
		<th data-options="field:'isExtras',sortable:'true',align:'center',
						formatter:function(value,row,index){ 
							if(value==true) 
                         		return '<a style=\'color:red;\'>增值</a>';
                         	else
                         		return '开放';
                         }" width='15%'> 是否增值接口</th>
                         
		<th data-options="field:'isOpen',sortable:'true',align:'center' ,
						formatter:function(value,row,index){ 
								if(value=='1') 
	                         		return '<a style=\'color:red;\'>是</a>';
	                         	else
	                         	   return '否';	 
	                         }" 
                         width='15%'>是否对外开放</th>
		
		<th data-options="field:'action',align:'center',formatter:methodMgr.actionFormatter" >操作 </th>                            
         </tr>                                         
	</thead>
 	<tbody>
 	</tbody>
</table>
<div id="addMethodDialog"></div> <!-- 方法添加窗口 -->
<div id="editMethodDialog"></div> <!-- 方法编辑窗口 -->
<div id="detailMethodDialog"></div> <!-- 方法详情窗口 -->

<div id="addMethodCfgsDialog"></div> <!-- 添加方法参数配置窗口 -->
<div id="editMethodCfgsDialog"></div> <!-- 修改方法参数配置窗口 -->

<div id="addMethodParamsDialog"></div> <!-- 添加方法参数窗口 -->
<div id="mgrMethodParamsDialog"></div> <!-- 方法参数管理窗口 -->
<div id="editParamDialog"></div><!-- 修改方法参数窗口 -->
 <script>	 
 eason.register("methodMgr");
 eason.register("methodParamMgr");
$(function(){
	//加载主页面的条件筛选
 	$.post("<%=path%>/admin/api/categorySelect/query","",function(data){
 		if(data.rows.length!=0){
 	 		//初始化 方法分类
 	 		methodMgr.data=data.rows;
 	 		$('#methodType').combobox({  
 	 			data: data.rows,  
 			    valueField:'id',  
 			    textField:'cateCnName',
 			});
 		}
 	},"json");
	
	<%-- $('#methodType').combobox({
	    url:"<%=path%>/admin/api/category/query",
	    valueField:'id',
	    textField:'cateCnName'
	}); --%>
	
	$('#table_grid').datagrid({ 
		width:"auto",
	    height:"auto", 
	    url:"<%=path%>/admin/api/apiMethod/query",
		checkOnSelect:false,
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
//方法管理操作
methodMgr.actionFormatter = function(value, row, index) {
	var detail = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-search',plain:true\" onclick=\"methodMgr.detail('"+ row.id + "',event)\">详细</a>";
	//var addParams = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-add',plain:true\" onclick=\"methodMgr.addParams('"+ row.id + "',event)\" >添加参数</a>";
	var mgrParams = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-add',plain:true\" onclick=\"methodMgr.mgrParams('"+ row.id + "',event)\" >参数管理</a>";
	var addCfg = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-add',plain:true\" onclick=\"methodMgr.addCfg('"+ row.id + "',event)\">添加配置</a>";
	var modifyCfg = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-add',plain:true\" onclick=\"methodMgr.modifyCfg('"+ row.id + "',event)\">修改配置</a>";
<%-- 	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="methodMgr.edit()">修改方法</a>
	var detail1 = "<span  height = '23px'  width = '30px'><img src='<%=path%>/images/icon/detail.png' title='详情' href='#' onclick=\"OrderMgr.detail('"
		+ row.orderId + "',event)\" style='cursor: pointer'></a></span>" --%>
	if(row.methodCfgVos.length>0){	
		return detail+"|"+mgrParams+"|"+modifyCfg;
	}else{
		return detail+"|"+mgrParams+"|"+addCfg;
	}
};
//参数管理操作
/* methodParamMgr.actionFormatter = function(value, row, index) {
	var addParams = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-add',plain:true\" onclick=\"methodParamMgr.modifyParams('"+ row.id + "',event)\" >修改参数</a>";
	var mgrParams = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-add',plain:true\" onclick=\"methodParamMgr.mgrParams('"+ row.id + "',event)\" >参数管理</a>";
	var addCfgs = "<a href='#' class='easyui-linkbutton' data-options=\"iconCls:'icon-add',plain:true\" onclick=\"methodMgr.addCfgs('"+ row.id + "',event)\">添加配置</a>";
	return addParams+"|"+mgrParams+"|"+addCfgs+"|"+mgrParams;
}; */

//------------------参数管理操作--------------------------------开始

//参数管理页面
methodMgr.mgrParams = function(id,e){
	var row = $('#table_grid').datagrid("getChecked");
	var dialogType="mgrMethodParamsDialog";
	var info="参数管理";
	var params={};
	methodParamMgr.mgrParamsId=id;//这个绑定的是方法id
	$.post("<%=path%>/admin/api/apiMethodParams/query",{"methodId":methodParamMgr.mgrParamsId},
			function(data){
				var paramsVo=data.rows;
				params=paramsVo;
				params.width=1000;
				params.height=600;
				methodParamMgr.paramsVo=paramsVo;
				$('#mgrMethodParamsDialog').dialog({
					title : info,
					closed : false,
					width: 1000,
					height: 600,
					cache : false,
					href : "<%=path%>/admin/api/dialog/"+dialogType,
					modal : true,
					buttons : [
								{
									text : '<spring:message code="common.Cancel"/> ',
									iconCls : 'icon-cancel',
									handler : function() {
										$('#mgrMethodParamsDialog').dialog('close');
									}
								}
					           ],
			   		onLoad : function() {
			   			//二，方法参数信息
			   			$('#table_grid_paramsList').datagrid({
			   			      url:"<%=path%>/admin/api/apiMethodParams/query?methodId="+methodParamMgr.mgrParamsId,
			   			      emptyMsg: 'no records found'
			   			});
					}});
				
			}
	);
}; 

//参数管理操作-修改参数
methodParamMgr.edit =function(id,e){
	var row = $('#table_grid_paramsList').datagrid("getChecked");
	if (row.length == 1) {
		var rowObj = $('#table_grid_paramsList').datagrid("getSelected");//（rowObj.id）得到方法参数id
		var dialogType="editParamDialog";
		var info="修改参数";
		$.post("<%=path%>/admin/api/apiMethodParams/query",{"id":rowObj.id},function(data){
			var params={};
			params=data.rows[0];
			params.width=600;
			params.height=400;
			openDiog(dialogType,info,"<%=path%>/admin/api/dialog/"+dialogType,"userForm_"+dialogType,params,function(){
				$('#table_grid_paramsList').datagrid('load');
				$("#"+dialogType).dialog("close");
			});
		},"json");
	} else {
		$.messager.alert('', '请选择一条修改的记录！', 'info');
	}	
} 
//参数管理操作-新增参数
methodParamMgr.add = function(){
	var row = $('#table_grid_paramsList').datagrid("getChecked");
	var dialogType="addMethodParamsDialog";
	var info="新增方法参数信息";
	var params={};
	params.methodId=methodParamMgr.mgrParamsId;//向弹出窗口传值，hidden中获取。只要页面有name属性相同就能获取
	params.width=600;
	params.height=400;
	openDiog(dialogType,info,"<%=path%>/admin/api/dialog/"+dialogType,"userForm_"+dialogType,params,function(){
		$('#table_grid_paramsList').datagrid('load');
		$("#"+dialogType).dialog("close");
	},null);
}

//参数管理操作--删除(批量删除)
methodParamMgr.remove = function(){
	remove('table_grid_paramsList', "<%=path%>/admin/api/apiMethodParam/remove");
};
//------------------参数管理操作--------------------------------完毕
//新增方法
methodMgr.add = function() {
	var dialogType="addMethodDialog";
	var info="新增方法";
	var params={};
	params.width=800;
	params.height=500;
	
	openDiog(dialogType,info,"<%=path%>/admin/api/dialog/"+dialogType,"userForm_"+dialogType,params,function(){
		$('#table_grid').datagrid('reload');
		$("#"+dialogType).dialog("close");
	},function(){
		$('#methodType_add').combobox({  
			data: methodMgr.data,  
		    valueField:'id',  
		    textField:'cateCnName',
		    onLoadSuccess:function(){ //加载完成后,设置选中第一项
			   	$(this).combobox('select',2); //默认新增方法的类别是“其他API”
			 }
		});
	});
};

//编辑方法
methodMgr.edit = function(){
	var row = $('#table_grid').datagrid("getChecked");
	if (row.length == 1) {
		var dialogType="editMethodDialog";
		var info="修改方法";
		$.post("<%=path%>/admin/api/apiMethod/query",{"id":row[0].id},function(data){
			var params={};
			methodMgr.editInfo =data.rows[0]; 
			params=data.rows[0];
			params.width=1000;
			params.height=600;
			openDiog(dialogType,info,"<%=path%>/admin/api/dialog/"+dialogType,"userForm_"+dialogType,params,function(){
				$('#table_grid').datagrid('reload');
				$("#"+dialogType).dialog("close");
			},function(){
				$('#methodType_edit').combobox({  
					data: methodMgr.data,  
				    valueField:'id',  
				    textField:'cateCnName',
				    onLoadSuccess:function(){ //加载完成后,设置选中第一项
					   	$(this).combobox('select',data.rows[0].methodType); 
					 }
				});
				
			});
		},"json");
	} else {
		$.messager.alert('', '请选择一条修改的记录！', 'info');
	}	

};

//删除(批量删除)
methodMgr.remove = function(){
	remove('table_grid', "<%=path%>/admin/api/apiMethod/remove");
};
//查看详情
methodMgr.detail = function(id,e){
	var row = $('#table_grid').datagrid("getChecked");
	var dialogType="detailMethodDialog";
	var info="查看详情";
		var params={};
		methodMgr.detailId=id;//绑定全局变量：此id是method的id
		$.post("<%=path%>/admin/api/apiMethod/query",{"id":methodMgr.detailId},
				function(data){
					var vo=data.rows[0];
					params=vo;
					params.width=1000;
					params.height=600;
					methodMgr.vo=vo;
					openDiog3(dialogType,info,"<%=path%>/admin/api/dialog/"+dialogType,"userForm_"+dialogType,params,function(){
						$('#table_grid').datagrid('reload');
						$("#"+dialogType).dialog("close");
					},function(){
						methodMgr.initDetail(methodMgr.detailId);
						//二，方法参数信息
						$('#table_grid_params').datagrid({
					          data:methodMgr.vo.methodParamVos
					    });
						//三，方法配置信息						$('#table_grid_cfgs').datagrid({					          data:methodMgr.vo.methodCfgVos
					          //emptyMsg: 'no records found'
					    });
					});
				}
		);
}; 
//给方法增加参数
methodMgr.addParams = function(id,e){
	methodMgr.addParamsId=id;//这个是全局的变量，只能用js取
	var row = $('#table_grid').datagrid("getChecked");
	var dialogType="addMethodParamsDialog";
	var info="新增方法参数信息";
	var params={};
	params.methodId=id;//向弹出窗口传值，hidden中获取。只要页面有name属性相同就能获取
	params.width=600;
	params.height=400;
	openDiog(dialogType,info,"<%=path%>/admin/api/dialog/"+dialogType,"userForm_"+dialogType,params,function(){
		$('#table_grid').datagrid('reload');
		$("#"+dialogType).dialog("close");
	},function(){
	});
};

//--------------方法配置管理（主页面）--------开始------------------------------------

//增加方法配置
methodMgr.addCfg = function(id,e){
	var row = $('#table_grid').datagrid("getChecked");
	var dialogType="addMethodCfgsDialog";
	var info="新增方法配置信息";
	var params={};
	params.methodId=id;//向弹出窗口传值，hidden中获取。只要页面有name属性相同就能获取
	params.width=600;
	params.height=400;
	openDiog(dialogType,info,"<%=path%>/admin/api/dialog/"+dialogType,"userForm_"+dialogType,params,function(){
		$('#table_grid').datagrid('reload');
		$("#"+dialogType).dialog("close");
	},null);
}; 
//修改方法配置
methodMgr.modifyCfg = function(id,e){
	var dialogType="editMethodCfgsDialog";
	var info="修改方法配置信息";
	$.post("<%=path%>/admin/api/apiMethodCfg/query",{"methodId":id},function(data){
		var params={};
		params=data.rows[0];
		params.width=600;
		params.height=400;
		openDiog(dialogType,info,"<%=path%>/admin/api/dialog/"+dialogType,"userForm_"+dialogType,params,function(){
			$('#table_grid').datagrid('reload');
			$("#"+dialogType).dialog("close");
		},null);
	},"json");
	
}; 

//--------------方法配置管理（主页面）--------结束------------------------------------

function validate(){
	return true;//检验为空
};
methodMgr.initDetail=function (id) {
	var vo = null;
	var $backButton = $('#back');
	var $nextButton = $('#next');
	var $saveButton = $('#save');
	var initParamter = function() {
		//查询填充表达
		var vo=methodMgr.vo;
		if (!vo) return;
		//一，方法基本信息
		$("#id").val(vo.id);
		//$("input[name='playerId']").val(vo.accountId); //所有包含name=id的字段都设置playerId
		$("#method").val(vo.method);
		$("#methodName").val(vo.methodName);
		$("#cateEnName").val(vo.cateEnName);
		$("#methodMemo").val(vo.methodMemo);
		$("#invokeMinMaxNum").val(vo.invokeMinMaxNum);
		$("#invokeDayMaxNum").val(vo.invokeMinMaxNum);
		var info;//（0：不需要appkey，1：需要appkey，2：需要appkey&sessionkey，3：需要appkey&sessionkey（每次使用都需要登录授权））  
		switch(vo.authLevel){
			case 0: info='0-不需要appkey';break;
			case 1: info='1-需要appkey';break;
			case 2: info='2-需要appkey&sessionkey';break;
			case 3: info='3-需要appkey&sessionkey（每次使用都需要登录授权）';break;
		};
		$("#authLevel").val(info);
		
		$("#isUpdated").val(vo.isUpdated);
		
		$("#xmlResult").val(vo.xmlResult);
		$("#jsonResult").val(vo.jsonResult);
		
		$("input[name='isExtras'][type='radio']").removeAttr("checked");
		if(true==vo.isExtras){ 
			$("input[name='isExtras'][type='radio'][value='1']").attr("checked",true);
		}else{
			$("input[name='isExtras'][type='radio'][value='0']").attr("checked",true);
		}
		
		$("input[name='isOpen'][type='radio']").removeAttr("checked");
		if('1'==vo.isOpen){
			$("input[name='isOpen'][type='radio'][value='1']").attr("checked",true);
		}else{
			$("input[name='isOpen'][type='radio'][value='0']").attr("checked",true);
		}
		
		//选项卡点击
		$('#activityTabs').click(function(){
			var tab = $('#activityTabs').tabs('getSelected'); 
			var index = $('#activityTabs').tabs('getTabIndex',tab);
			if(index==0){
				$nextButton.show();
				$backButton.hide();
				$saveButton.hide();
			}
			if(index==1){
				$nextButton.show();
				$backButton.show();
				$saveButton.hide();
			}
			if(index==2){
				$nextButton.hide();
				$backButton.show();
				$saveButton.show();
			}
		});
		
		//上一步
		$backButton.click(function(){
			var tab = $('#activityTabs').tabs('getSelected'); 
			var index = $('#activityTabs').tabs('getTabIndex',tab);
			if(index==1){
				$nextButton.show();
				$backButton.hide();
				$saveButton.hide();
				$('#activityTabs').tabs('select',0);
			}
			if(index==2){
				$nextButton.show();
				$backButton.show();
				$saveButton.hide();
				$('#activityTabs').tabs('select',1);
			}
		});
		//下一步
		$nextButton.click(function(){
			var tab = $('#activityTabs').tabs('getSelected'); 
			var index = $('#activityTabs').tabs('getTabIndex',tab);
			if(index==0){
				if(validate()){
					$saveButton.hide();
					$backButton.show();
					$nextButton.show();
					$('#activityTabs').tabs('select',1);
					return true;
				}else{
					return false;
				}
			}
			if(index==1){
				if(validate()){
					$saveButton.hide();
					$backButton.show();
					$nextButton.hide();
					$('#activityTabs').tabs('select',2);
					return true;
				}else{
					return false;
				}
			}
		});
		//保存
		$saveButton.click(function(){
			window.close();
		});
	};
	initParamter();
}

</script>
 
