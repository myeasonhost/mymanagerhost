<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理平台</title>
<link href="css/reset.css" rel="stylesheet" type="text/css"/>
<link href="css/ftlcommon.css" rel="stylesheet" type="text/css"/>
<link href="js/easyui/themes/icon.css" rel="stylesheet" type="text/css">
<link href="js/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>

 <style>
 	a.link{text-decoration: none;color:blue;cursor:pointer};
 	a.linkGray{text-decoration: none;color:gray;cursor:pointer};
 	a:visited.linkBlue {text-decoration: none;background:gold;font-weight:750};
 	/*操作栏样式*/
	a.operateChannel,
	a.operateChannel:link{
		color: #00F;
		text-decoration: none;
	}
	a.operateChannel:hover{
		color: #F00;
		text-decoration: underline;
	}
 </style>
 <script>
function setDateStr()   {
	    var today = new Date();
	    var weekStr = "  星期" + "日一二三四五六".charAt(today.getDay());
	    var dateStr = (today.getFullYear()) + "年" + (today.getMonth() + 1 ) + "月" + today.getDate() + "日" + weekStr +"";
	    $("#dateSpan").text(dateStr); 
};
	
$(function(){ 
	//获取当前时间
	setDateStr();
	//更改密码
	$("#changePwd").click(function(){
		 $('#changePwdDiog').dialog({
			title : '更改密码',
			closed : false,
			cache : false,
			href : "<%=path%>/admin/system/user/changePwd",
			modal : true,
			buttons : [{
						text : '保存',
						iconCls : 'icon-ok',
						handler : function() {
							var saveBtn = $(this);
							$('#changePwdForm').form('submit',{
								onSubmit : function() {
									var isValid = $('#changePwdForm').form('validate');
									if (isValid) {
										saveBtn.unbind("click");
										saveBtn.linkbutton('disable');
										return true;
									} else {
										return false;
									}
								},
								success : function(data) {
									if ("-1" == data) {
										msg = '保存失败';
										$('#changePwdDiog').dialog('close');
									}else if("1" == data){
										msg = '保存成功';
										$('#changePwdDiog').dialog('close');
									}
									$.messager.show({
										title : '',
										msg : msg,
										showType : 'show'
									});
								}
							});
						}
					},
					{
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							$('#changePwdDiog').dialog('close');
						}
					}]
		});
	});
});  
</script>
</head>
<body class="easyui-layout"> 
	 <div region="north" style="overflow:hidden;height:30px;padding-top:5px;text-align:right;background-image: url('images/top_bg.gif');background-repeat: no-repeat;background-position: right 0;position: relative;">
	 	   <span id="dateSpan"></span>&nbsp;&nbsp;&nbsp;&nbsp;
		 ${(user.account)!''}&nbsp;&nbsp;欢迎您！<a class="link"  id="changePwd">更改密码</a>&nbsp;&nbsp;<a class="link" href="xxxx" >登出</a> &nbsp;&nbsp;
	</div>
	<div id="west" region="west" split="false" title=" " style="width:200px;" >
<#--		<jsp:include page="menu.jsp"> -->
<#--    		<jsp:param name="user" value="${user}" /> -->
<#--	    </jsp:include>-->
		<#include "menu.ftl" encoding="UTF-8" parse=true>

	</div>
	
	<div id="layout_content" region="center">
		<div id="contentRegion" class="easyui-tabs" data-options="fit:true,border:true">
		</div>
	</div>
	<div id="changePwdDiog" style="padding:5px; width:450px; height:220px"></div>
</body>
</html>  
