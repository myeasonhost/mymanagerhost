<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>SPSS测试用例</title>
		<style type="text/css">
			* {
				font-size: 12px;
				margin: 0;
				padding: 0;
			}
			
			fieldset {
				padding: 10px;
				margin: 10px;
				width: 370px;
				color: #333;
				border: #06c dashed 1px;
				background-color: #EEE;
			}
			
			legend {
				color: #06c;
				font-weight: 800;
				background: #fff;
			}
			
			ul {
				list-style-type: none;
				margin: 8px 0 4px 0;
				color: #880;
			}
			
			li {
				margin-top: 4px;
			}
		</style>
		<script type="text/javascript" src="<%=basePath%>/scripts/jquery/jquery-1.8.1.min.js"></script>
		<script type="text/javascript">
			var action = '<%=request.getParameter("act")%>'
			$(function(){
				var $form=$("#testForm");
				//$form.attr("action","<%=basePath%>service/userBindService");
				//alert($form.attr('action'));
				$form.submit(function(){
					return false;
				});
				$("#testForm > input[type='submit']").click(function(){
					if($("#testForm > textarea").val()==""){
						alert("不能为空哦~亲！");
						return false;
					}
					$("#textArea").val("");
					$.post(
					"<%=basePath%>service/proxy",
					$form.serialize(),
					function(data){
							$("#textArea").val(data);
						
					},"text");
				});
				$("#btn1").click(function(){
				if("gd_type_list"==action){
					$("#show1").toggle();
				}else if("area_list"==action){
					$("#show2").toggle();
				}else if("co_list"==action){
					$("#show3").toggle();
				}else if("co_detail"==action){
					$("#show4").toggle();
				}else if("gd_detail"==action){
					$("#show5").toggle();
				}else if("gd_list"==action){
					$("#show6").toggle();
				}else if("gd_search_list"==action){
					$("#show7").toggle();
				}else if("adv_list"==action){
					$("#show8").toggle();
				}else if("user_reg"==action){
					$("#show9").toggle();
				}else if("user_mgr"==action){
					$("#show10").toggle();
				}else if("user_login"==action){
					$("#show11").toggle();
				}else if("user_logout"==action){
					$("#show12").toggle();
				}else if("order_list"==action){
					$("#show13").toggle();
				}else if("order_detail"==action){
					$("#show14").toggle();
				}else if("order_create"==action){
					$("#show15").toggle();
				}else if("order_pay"==action){
					$("#show16").toggle();
				}else if("user_info"==action){
					$("#show17").toggle();
				}
				
				});
				
			});
		</script>
	</head>

	<%
		String flag = request.getParameter("flag");
		flag = flag == null ? "" : flag.trim();
	%>

	<body>
	<div style="float:left;clear: both;width: 500px;">
		<fieldset>
			<legend>
				<%=request.getParameter("name")%>
			</legend>
			<form id="testForm" name="testForm" action="#"
				method="post">
				请输入正确的JSON字符串：
				</br>
				<textarea rows="20" cols="70" name="jsonRequest"></textarea>
				</br>
				<input type="submit" value="提交">
				</br>
			</form>
		</fieldset>
	<fieldset >
			<legend>
				返回结果
			</legend>
				<textarea id="textArea" rows="9" cols="70"></textarea>
			</form>
		</fieldset>
	</div>
		<fieldset style="float:left;">
			<legend>
				注意的相关事项
			</legend>

			<ul>
				<li>
					（1）测试地址:http://ip:port/spss/service/proxy
				</li>
				<li>
					（2）JSON格式测试地址：http://www.bejson.com/go.php?u=http://www.bejson.com/index.php
				</li>
				<li>
					（3）JSON测试例子：<input id="btn1" type="button" value="点击展开例子"/>
				</li>
			</ul>
			<ul id="show1" style="display: none">
			---------------------------------------------商品类型列表接口----------------------------------------------
						<li>{					</li>
						<li>"serviceName":"spss_gd_type_list_req",	</li>
						<li>"callType":"001",</li>
						<li>"params": {</li>
						<li>"showGd":"0",</li>
						<li>"pageSize":"100",</li>
						<li>"currentPage":"1"</li>
						<li>}				</li>
						<li>}					</li>
			</ul>
			<ul id="show2" style="display: none">
			---------------------------------------------地域列表接口----------------------------------------------
						<li>{					</li>
						<li>"serviceName":"spss_area_list_req",	</li>
						<li>"callType":"001",</li>
						<li>"params": {</li>
						<li>"showGd":"0",</li>
						<li>"pageSize":"100",</li>
						<li>"currentPage":"1"</li>
						<li>}				</li>
						<li>}					</li>
			</ul>
			<ul id="show3" style="display: none">
			---------------------------------------------公司列表接口----------------------------------------------
						<li>{					</li>
						<li>"serviceName":"spss_co_list_req",	</li>
						<li>"callType":"001",</li>
						<li>"params": {</li>
						<li>"showGd":"0",</li>
						<li>"pageSize":"100",</li>
						<li>"currentPage":"1"</li>
						<li>}				</li>
						<li>}					</li>
			</ul>
			<ul id="show4" style="display: none">
			---------------------------------------------公司详情接口----------------------------------------------
						<li>{					</li>
						<li>"serviceName":"spss_co_detail_req",	</li>
						<li>"callType":"001",</li>
						<li>"params": {</li>
						<li>"coId":"1"</li>
						<li>}				</li>
						<li>}					</li>
			</ul>
			<ul id="show5" style="display: none">
			---------------------------------------------商品详情接口----------------------------------------------
						<li>{					</li>
						<li>"serviceName":"spss_gd_detail_req",	</li>
						<li>"callType":"001",</li>
						<li>"params": {</li>
						<li>"gdId":"1"</li>
						<li>}				</li>
						<li>}					</li>
			</ul>
			<ul id="show6" style="display: none">
			---------------------------------------------商品列表接口----------------------------------------------
						<li>{					</li>
						<li>"serviceName":"spss_gd_list_req",	</li>
						<li>"callType":"001",</li>
						<li>"params": {</li>
						<li>"gdTypeId":"0",</li>
						<li>"priceRange":"0-10",</li>
						<li>"coId":"0",</li>
						<li>"areaId":"0",</li>
						<li>"orderInfo":"",</li>
						<li>"operType":"0",</li>
						<li>"pageSize":"20",</li>
						<li>"currentPage":"1"</li>
						<li>}				</li>
						<li>}					</li>
			</ul>
			<ul id="show7" style="display: none">
			---------------------------------------------商品搜索列表接口----------------------------------------------
						<li>{					</li>
						<li>"serviceName":"spss_gd_search_list_req",	</li>
						<li>"callType":"001",</li>
						<li>"params": {</li>
						<li>"keyword":"西",</li>
						<li>"byWay":"",</li>						
						<li>"pageSize":"20",</li>
						<li>"currentPage":"1"</li>
						<li>}				</li>
						<li>}					</li>
			</ul>
			<ul id="show8" style="display: none">
			---------------------------------------------广告列表接口----------------------------------------------
						<li>{					</li>
						<li>"serviceName":"spss_adv_list_req",	</li>
						<li>"callType":"001",</li>
						<li>"params": {</li>
						<li>"advAscribe":"1",</li>
						<li>"areaId":"0",</li>						
						<li>"pageSize":"20",</li>
						<li>"currentPage":"1"</li>
						<li>}				</li>
						<li>}					</li>
			</ul>
			<ul id="show9" style="display: none">
			---------------------------------------------用户注册接口----------------------------------------------
						<li>{					</li>
						<li>"serviceName":"spss_user_reg_req",	</li>
						<li>"callType":"001",</li>
						<li>"params": {</li>
						<li>"userId":"caiwen",</li>
						<li>"userPwd":"WWWWWW",</li>		
						<li>"userName":"文",</li>	
						<li>"userTel":"15730287885",</li>	
						<li>"userEmail":"119056914@qq.com",</li>	
						<li>"userAddr":"重庆市渝北区锦绣广苑E10-1",</li>	
						<li>"zipCode":"637000"</li>					
						<li>}				</li>
						<li>}					</li>
			</ul>
			<ul id="show10" style="display: none">
			---------------------------------------------用户修改资料----------------------------------------------
						<li>{					</li>
						<li>"serviceName":"spss_user_mgr_req",	</li>
						<li>"callType":"001",</li>
						<li>"params": {</li>
						<li>"userCode":"SPSS_CUS14072521344400001",</li>
						<li>"userOldPwd":"WWWWWW",</li>
						<li>"userNewPwd":"WWWWWW",</li>			
						<li>"userName":"文",</li>	
						<li>"userTel":"15730287885",</li>	
						<li>"userEmail":"119056914@qq.com",</li>	
						<li>"userAddr":"重庆市渝北区锦绣广苑E10-1",</li>	
						<li>"zipCode":"637000"</li>					
						<li>}				</li>
						<li>}					</li>
			</ul>
			<ul id="show11" style="display: none">
			---------------------------------------------用户登陆----------------------------------------------
						<li>{					</li>
						<li>"serviceName":"spss_user_login_req",	</li>
						<li>"callType":"001",</li>
						<li>"params": {</li>
						<li>"userId":"caiwen",</li>
						<li>"userPwd":"WWWWWW"</li>
						<li>}				</li>
						<li>}					</li>
			</ul>
	<ul id="show12" style="display: none">
			---------------------------------------------用户退出登陆----------------------------------------------
						<li>{					</li>
						<li>"serviceName":"spss_user_logout_req",	</li>
						<li>"callType":"001",</li>
						<li>"params": {</li>
						<li>"userCode":"SPSS_CUS14072521344400001"</li>
						<li>}				</li>
						<li>}					</li>
			</ul>
			<ul id="show13" style="display: none">
			---------------------------------------------订单列表----------------------------------------------
						<li>{					</li>
						<li>"serviceName":"spss_order_list_req",	</li>
						<li>"callType":"001",</li>
						<li>"params": {</li>
						<li>"orderType":"0",</li>
						<li>"userCode":"SPSS_CUS14072521344400001",</li>						
						<li>"pageSize":"20",</li>
						<li>"currentPage":"1"</li>
						<li>}				</li>
						<li>}					</li>
			</ul>
			<ul id="show14" style="display: none">
			---------------------------------------------订单详情----------------------------------------------
						<li>{					</li>
						<li>"serviceName":"spss_order_detail_req",	</li>
						<li>"callType":"001",</li>
						<li>"params": {</li>
						<li>"orderId":"SPSS_OR1",</li>
						<li>"userCode":"SPSS_CUS14072521344400001"</li>
						<li>}				</li>
						<li>}					</li>
			</ul>	
			<ul id="show15" style="display: none">
			---------------------------------------------生成订单----------------------------------------------
						<li>{					</li>
						<li>"serviceName":"spss_order_create_req",	</li>
						<li>"callType":"001",</li>
						<li>"params": {</li>
						<li>"userCode":"SPSS_CUS14072521344400001",</li>
						<li>"gdId":"1",</li>
						<li>"orderGdNum":"2",</li>
						<li>"orderMemo":"老板，来两打！"</li>
						<li>}				</li>
						<li>}					</li>
			</ul>	
			<ul id="show16" style="display: none">
			---------------------------------------------生成订单----------------------------------------------
						<li>{					</li>
						<li>"serviceName":"spss_order_pay_req",	</li>
						<li>"callType":"001",</li>
						<li>"params": {</li>
						<li>"userCode":"SPSS_CUS14072521344400001",</li>
						<li>"orderId":"aCIBCVZee/jZ0aaj4Z5IbQd+80FkuSuSWpnfNh+wmu4koNs265TlkkcjBTzFiDMzSfERX6VE",</li>
						<li>"orderStatus":"aCIBCVZee/jZ0aaKpYcEeENi2hw5/HO6TpnGt"</li>
						<li>}				</li>
						<li>}					</li>
			</ul>	
			<ul id="show17" style="display: none">
			---------------------------------------------生成订单----------------------------------------------
						<li>{					</li>
						<li>"serviceName":"spss_user_info_req",	</li>
						<li>"callType":"001",</li>
						<li>"params": {</li>
						<li>"userCode":"SPSS_CUS14072521344400001"</li>
						<li>}				</li>
						<li>}					</li>
			</ul>	
		</fieldset>
		
	</body>
</html>
