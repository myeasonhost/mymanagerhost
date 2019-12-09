<%@ page contentType="text/html; charset=utf-8" language="java" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统目录</title>
<link type="text/css" rel="stylesheet" href="./javascripts/gzq/xtree/css/xtree2.css">
<link rel="StyleSheet" href="./styles/gstyle/dtree.css" type="text/css" />
<script language="javascript" src="./javascripts/gzq/xtree/js/xtree2.js"></script>


<SCRIPT LANGUAGE="JavaScript">
<!--
     //初始化FXTree
     var t = new WebFXTree('SPSS系统接口测试',changeRootUrls);

	 //改变右框架的URL；
     function changeUrl(url){
        parent.mainFrame.window.location=url;
     }  

	 function changeUrls(){

 
		 //改变右边frame的内容;
		
         //动态加载子节点;
     

         if(t.getSelected().hasChildren()){
			 t.getSelected().expand();
			 return;
		 }
		  parent.mainFrame.window.location=t.getSelected().getId();
		 
	 }

	 function changeRootUrls(){
         //加载子节点;
		 //改变右边frame的内容;
		
	 }

	 //控制一个节点
	 function operatorChild(id,text,flag){

		 if(flag=="1"){
			 //新增节点;
			 var node =new WebFXTreeItem(text,changeUrls);
			 node.setId(id);
		     t.getSelected().add(node);
			 if(t.getSelected().getExpanded()==false){
				 t.getSelected().expand();
			 }
		 }else if(flag=="2"){
			 //修改节点
			 var elements =t.getSelected().getChildNodes();
			 for(var i=0;i<elements.length;i++){
				 if(elements[i].getId()==id){
                     elements[i].setText(text);
					 break;
				 }

			 }
             
		 }else if(flag=="3"){
			 var objs =id.split(",");
			 var elements =t.getSelected().getChildNodes();
			 //删除节点;
			 for(var i=0 ;i<objs.length;i++){
                 for(var j =0;j<elements.length;j++){
                     if(elements[j].getId()==objs[i]){
						 t.getSelected().remove(elements[j]);
						 break;
					 }
				 }

			 }
		 }
	 }
	 function logOut(){
		parent.window.location ="logout.jsp";
	 }
	 
//-->
</SCRIPT>
</head>

<body>


<div class="dtree">
	<script type="text/javascript">
		   var node;
		   var  publish =new WebFXTreeItem('接口发布',changeUrls);
		   publish.setId('0');
		   t.add(publish);
		    node =new WebFXTreeItem('广告列表',changeUrls);
		   node.setId('publish/testcase.jsp?act=adv_list&name=广告列表');
		   publish.add(node);
		   node =new WebFXTreeItem('商品类别列表',changeUrls);
		   node.setId('publish/testcase.jsp?act=gd_type_list&name=商品类别列表');
		   publish.add(node);
		    node =new WebFXTreeItem('区域列表',changeUrls);
		   node.setId('publish/testcase.jsp?act=area_list&name=区域列表');
		   publish.add(node);
		    node =new WebFXTreeItem('公司列表',changeUrls);
		   node.setId('publish/testcase.jsp?act=co_list&name=公司列表');
		   publish.add(node);
		     node =new WebFXTreeItem('公司详情',changeUrls);
		   node.setId('publish/testcase.jsp?act=co_detail&name=公司详情');
		   publish.add(node);
		      node =new WebFXTreeItem('商品详情',changeUrls);
		   node.setId('publish/testcase.jsp?act=gd_detail&name=商品详情');
		   publish.add(node);
		     node =new WebFXTreeItem('商品列表',changeUrls);
		   node.setId('publish/testcase.jsp?act=gd_list&name=商品列表');
		   publish.add(node);
		   node =new WebFXTreeItem('商品搜索',changeUrls);
		   node.setId('publish/testcase.jsp?act=gd_search_list&name=商品搜索');
		   publish.add(node);
		     node =new WebFXTreeItem('用户注册',changeUrls);
		   node.setId('publish/testcase.jsp?act=user_reg&name=用户注册');
		   publish.add(node);
		      node =new WebFXTreeItem('用户资料',changeUrls);
		   node.setId('publish/testcase.jsp?act=user_info&name=用户资料');
		   publish.add(node);
		     node =new WebFXTreeItem('用户修改资料',changeUrls);
		   node.setId('publish/testcase.jsp?act=user_mgr&name=用户修改资料');
		   publish.add(node);
		     node =new WebFXTreeItem('用户登录',changeUrls);
		   node.setId('publish/testcase.jsp?act=user_login&name=用户登录');
		   publish.add(node);
		     node =new WebFXTreeItem('用户退出 ',changeUrls);
		   node.setId('publish/testcase.jsp?act=user_logout&name=用户退出');
		   publish.add(node);
		   node =new WebFXTreeItem('订单列表 ',changeUrls);
		   node.setId('publish/testcase.jsp?act=order_list&name=订单列表');
		   publish.add(node);
		   node =new WebFXTreeItem('订单详情 ',changeUrls);
		   node.setId('publish/testcase.jsp?act=order_detail&name=订单详情 ');
		   publish.add(node);
		   node =new WebFXTreeItem('生成订单 ',changeUrls);
		   node.setId('publish/testcase.jsp?act=order_create&name=生成订单');
		   publish.add(node);
		   node =new WebFXTreeItem('支付订单 ',changeUrls);
		   node.setId('publish/testcase.jsp?act=order_pay&name=支付订单');
		   publish.add(node);
		t.write();
		 //选中根节点;
		t.getTree().select();
		t.getSelected().expand();
	</script>

</div>

</body>

</html>