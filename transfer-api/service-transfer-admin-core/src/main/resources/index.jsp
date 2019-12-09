<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%String path = request.getContextPath(); %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>小雪物流后台登录页面</title>
<link type="text/css" href="style/ht.css" rel="stylesheet" />
<style type="text/css">
#checkCode {
	cursor: hand;
	margin-top: 8px;
	margin-left: 5px;
	/*float: left;*/
	display: inline;
	width: 65px;
	height: 15px;
	color: red;
	font-size: 16px;
	font-family: Arial;
	font-style: italic;
	border: 0;
	padding: 2px 3px;
	letter-spacing: 3px;
	font-weight: bolder;
}
</style>
<script type="text/javascript" src="js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="js/bgstretcher.js"></script>
<script type="text/javascript">
var checkCode;
var validateCode = null;
var createCode = function(isInner) {
				if (!isInner) {
					 validateCode = "";
				}
				var codeLength = 4;// 验证码的长度
				$("#checkCode").html();
				var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C',
						'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q',
						'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
		
				for ( var i = 0; i < codeLength; i++) {
					var charIndex = Math.floor(Math.random() * 32);
					validateCode += selectChar[charIndex];
				}
				if (validateCode.length != codeLength) {  
					this.createCode(true); 
				}
				$("#checkCode").html(validateCode);
			};
function do_login() {
		  var frm = document.frm;
		  var user_code = frm.user_code.value;
		 
		  if (user_code == "") {
			  $("#msgDiv").html("请输入用户名！");
		       frm.user_code.focus();
		       return;
		   }	
		  var user_pwd = frm.user_pwd.value;
		   if (user_pwd == "") {
			   $("#msgDiv").html("请输入密码！");
		       frm.user_pwd.focus();
		       return;
		   }
		   //验证码校验
		   var flag=checkCode();
		   if(!flag){
		   		return;
		   }
		   
		   $.post(
		   		"<%=path%>/admin/user/login?date="+new Date(),
		   		$(frm).serialize(),
		   		function(data){
		   			if(!data.result){
		   				$("#msgDiv").html(data.message);
		   			}else{
		   				document.location.href = "<%=path%>/admin/index";
		   			}
		   	},"json");
};

	$(document).ready(function(){
	
        //  Initialize Backgound Stretcher	   
		$('BODY').bgStretcher({
			images: ['images/1.jpg', 'images/2.jpg', 'images/3.jpg'],
			imageWidth: 1024, 
			imageHeight: 768, 
			slideDirection: 'N',
			slideShowSpeed: 2000,
			transitionEffect: 'fade',
			sequenceMode: 'normal',
			buttonPrev: '#prev',
			buttonNext: '#next',
			pagination: '#nav',
			anchoring: 'left center',
			anchoringImg: 'left center'
		});
        
		document.onkeypress =function (e) {
			var keyCode = e ? e.which : event.keyCode;
			if(keyCode == 13) {
				// 回车事件
				do_login();
			}
		};
		
	 createCode();
	 var $validateCodeEditor = $('#sryz');
	   $validateCodeEditor.bind('focusout', checkCode=function(){
	   		if($validateCodeEditor.val()==""){
	   			$("#msgDiv").html("验证码不能为空");
	   			return false;
	   		}
		 	if($validateCodeEditor.val().toUpperCase() != validateCode.toUpperCase()){
				$("#msgDiv").html("验证码输入有误");
				createCode();
				return false;
			} else {
				$("#msgDiv").empty();
				return true;
			}
	   });
		
		
	});

</script>
<script type="text/javascript" src="js/main.js"></script>
</head>
<body >
	<div class="ht warp1000">
    	 <div class="yzm" id="yzm">
         	<div class="z" id="checkCode">
            </div>
            <div class="y">
            	<img src="images/12.png" id="xx" />
                <img src="images/34.png" class="im34" onclick="createCode();return false;" />
            </div>
            <script>
            	window.onload=function(){
					var	yzm=document.getElementById("yzm");
					var sryz=document.getElementById("sryz");
					var xx=document.getElementById("xx");
					sryz.onfocus=function(){
						yzm.style.display="block";
						this.value="";
					}
					sryz.onblur=function(){
						if(this.value==""){
							this.value="输入验证";
						}else{
							
						}
					}
					xx.onclick=function(){
						yzm.style.display="none";
					}
					
				}
            </script>
         </div>
    	<div class="p_top">
            <p class="p1">小雪物流信息平台后台管理</p>
            <p class="p2">咨询电话：027-86668801/86668802/86668803&nbsp;&nbsp;&nbsp;&nbsp;售后QQ：2987558898</p>
        </div>
        <div class="logo"><img src="images/logo.png" /></div>
        <form name="frm" class="loginForm">
	    	<input type="text" id="user_code" name="account" class="t_01"/>
	        <input type="password" id="user_pwd" name="pwd" class="t_02"/>
	        <input type="text" class="t_03" value="输入验证" id="sryz"/>
	        <input type="button" value="登录" class="t_04" onclick="do_login();return false;"/>
        </form>
        <p  id="msgDiv" style="left:50px; color:red;text-align:center;font-size:15px"></p>
        <p class="p3">content Manage System Powered By：©huihai-cms Version:2.3  CopyRight 2010-2015. </p>
       
    </div>
</body>
</html>
