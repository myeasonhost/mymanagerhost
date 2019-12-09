<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=path%>/"/>
<title>WebDT WCM License Server</title>
	<link rel="stylesheet" type="text/css" href="<%=path%>/jquery/easyui/themes/default/easyui.css"/> 
	<link rel="stylesheet" type="text/css" href="<%=path%>/jquery/easyui/themes/icon.css"/>  
	<script type="text/javascript" src="<%=path%>/jquery/jquery-1.8.0.min.js"></script> 
	<script type="text/javascript" src="<%=path%>/jquery/easyui/jquery.easyui.min.js"></script> 
	<c_rt:if test="${language != 'en'}"><script type="text/javascript" src="<%=path %>/jquery/easyui/locale/easyui-lang-zh_CN.js"></script></c_rt:if>
</head>
<body>
	<div id="dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;padding:10px" buttons="#dlg-buttons">
		Dialog Content.
	</div>
	<div id="dlg-buttons">
		<table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td style="text-align:right">
					<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="javascript:alert('save')">Save</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dd').dialog('close')">Close</a>
				</td>
			</tr>
		</table>
	</div>

</body>
</html>