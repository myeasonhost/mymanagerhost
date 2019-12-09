<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=path%>/"/>
<title>卓尔冷链后台管理</title>
	<link rel="stylesheet" type="text/css" href="<%=path %>/jquery/easyui/themes/default/easyui.css"/> 
	<link rel="stylesheet" type="text/css" href="<%=path %>/jquery/easyui/themes/icon.css"/>  
	<script type="text/javascript" src="<%=path %>/jquery/jquery-1.8.0.min.js"></script> 
	<script type="text/javascript" src="<%=path %>/jquery/easyui/jquery.easyui.min.js"></script> 
	<script type="text/javascript" src="<%=path %>/js/json.js"></script>  
	<script type="text/javascript" src="<%=path %>/js/eason.js"></script>
 
 	<c_rt:if test="${language != 'en'}"><script type="text/javascript" src="<%=path %>/jquery/easyui/locale/easyui-lang-zh_CN.js"></script></c_rt:if>
 	
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
			title : '<spring:message code="common.changePwd"/>',
			closed : false,
			cache : false,
			href : "<%=path%>/admin/system/user/changePwd",
			modal : true,
			buttons : [{
						text : '<spring:message code="common.Save"/>',
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
										msg = '<spring:message code="common.Failed"/>';
										$('#changePwdDiog').dialog('close');
									}else if("1" == data){
										msg = '<spring:message code="common.Succ"/>';
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
						text : '<spring:message code="common.Cancel"/>',
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
 <jsp:include page="/admin/common/system"/> 
	 <div region="north" style="overflow:hidden;height:30px;padding-top:5px;text-align:right;background-image: url('image/top_bg.gif');background-repeat: no-repeat;background-position: right 0;position: relative;">
	 	   <span id="dateSpan"></span>&nbsp;&nbsp;&nbsp;&nbsp;
		   ${user.account}&nbsp;&nbsp;欢迎您！<a class="link"  id="changePwd"><spring:message code="index.changePwd"/></a>&nbsp;&nbsp;<a class="link" href="<%=path%>/admin/logout" ><spring:message code="index.logout"/></a> &nbsp;&nbsp;
	</div>
	<div id="west" region="west" split="false" title=" " style="width:200px;" >
		<jsp:include page="menu.jsp"> 
    		<jsp:param name="user" value="${user}" /> 
	    </jsp:include>  
	</div>
	
	<div id="layout_content" region="center">
		<div id="contentRegion" class="easyui-tabs" data-options="fit:true,border:true">
		</div>
	</div>
	<div id="changePwdDiog" style="padding:5px; width:450px; height:220px"></div>
</body>
</html>  
