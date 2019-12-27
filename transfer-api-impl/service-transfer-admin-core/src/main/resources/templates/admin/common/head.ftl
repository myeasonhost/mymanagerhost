<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>卓尔冷链后台管理</title>
	<link rel="stylesheet" type="text/css" href="<%=path %>/jquery/easyui/themes/default/easyui.css"/> 
	<link rel="stylesheet" type="text/css" href="<%=path %>/jquery/easyui/themes/icon.css"/>
	<script type="text/javascript" src="<%=path %>/jquery/jquery-1.8.0.min.js"></script> 
	<script type="text/javascript" src="<%=path %>/jquery/easyui/jquery.easyui.min.js"></script> 
	<script type="text/javascript" src="<%=path %>/jquery/datagrid-detailview.js"></script>
	<script type="text/javascript" src="<%=path %>/js/json.js"></script>  
	<script type="text/javascript" src="<%=path %>/js/eason.js"></script>
 	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=6116ef6f3c85af52e21b948fe84fba40&plugin=AMap.ToolBar"></script> 
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