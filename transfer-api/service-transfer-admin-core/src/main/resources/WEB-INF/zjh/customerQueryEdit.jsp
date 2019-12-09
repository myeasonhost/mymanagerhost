<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/admin/common/head"/> 
<jsp:include page="/admin/common/system"/>
<%String path = request.getContextPath(); %>
<html>
	<head>
	<title>玩家信息编辑</title>
	<script type="text/javascript" charset="utf-8">
	function CustomerOpt(id,type,onConfirm) {
		var vo = null;
	    var $backButton = $('#back');
		var $nextButton = $('#next');
		var $saveButton = $('#save');
		var initParamter = function() {
			//查询填充表达
			$.post("<%=path%>/admin/zjh/customerQuery/query",{"accountId":id},
				function(data){
					var vo=data.rows[0];
					if (!vo) return;
					//一，玩家基本信息
					$("#userImage").attr("src","http://texasimage.pto2o.com/texas_image_srv/download.php?srcID="+vo.accountId+"&destID="+vo.image+"&fileSize=orign");
					$("#accountId").val(vo.accountId);
					$("input[name='playerId']").val(vo.accountId); //所有包含name=id的字段都设置playerId					
					$("#externId").val(vo.externId);
					$("#account").val(vo.account);
					$("#nickname").val(vo.nickname);
					$("#hwsn").val(vo.hwsn);
					var info;
					switch(vo.status){
						case 1: info='大厅';break;
						case 2: info='游戏中旁观';break;
						case 3: info='游戏中中对局';break;
						case 4: info='已下线';break;
						case 5: info='老虎机';break;
						case 6: info='拳馆中';break;
					};
					$("#status").val(info);
					$("#mobile").val(vo.mobile);
					$("input[name='sex'][type='radio']").removeAttr("checked");
					if("1"==vo.sex){
						$("input[name='sex'][type='radio'][value='1']").attr("checked",true);
					}else{
						$("input[name='sex'][type='radio'][value='0']").attr("checked",true);
					}
					$("#channel").val(vo.channel);
					$("#registerTime").val(new Date(vo.registerTime).toLocaleString());
					$("#registerIp").val(vo.registerIp);
					$("#signature").val(vo.signature);
					$("#loginTimes").val(vo.loginTimes+"次");
					$("#lastLoginTime").val(new Date(vo.lastLoginTime).toLocaleString());
					//二，玩家经验和魅力值
					$("#coins").val(vo.coins);
					$("#vipGrade").val(vo.vipGrade);
					$("#leaves").val(vo.leaves);
					$("#experience").val(vo.experience);
					$("#totalOnlineDuration").val(vo.totalOnlineDuration+"分钟");
					$("#totalConsume").val(vo.totalConsume);
					$("#charm").val(vo.charm);
					$("#gift1").val(vo.gift1);
					$("#gift2").val(vo.gift2);
					$("#gift3").val(vo.gift3);
					$("#gift4").val(vo.gift4);
					$("#gift5").val(vo.gift5);
					$("#winCount").val(vo.winCount);
					$("#lostCount").val(vo.lostCount);
					//三，玩家充值和老虎机信息
					$.post("<%=path%>/admin/zjh/customerQuery/queryByOne",{"accountId":id},function(data){
						var vo=data;
						if (!vo) return;
						//玩家充值方式统计
						$("#iosStore").val(vo.iosStore);
						$("#alipay").val(vo.alipay);
						$("#szf").val(vo.szf);
						$("#mmPay").val(vo.mmPay);
						$("#woPay").val(vo.woPay);
						$("#tyPay").val(vo.tyPay);
						$("#uucPay").val(vo.uucPay);
						$("#ylPay").val(vo.ylPay);
						//老虎机统计
						$("#matchTimeSum").val(vo.matchTimeSum);
						$("#matchCoinsSum").val(vo.matchCoinsSum);
						//猜拳信息
						$("#winCountSum").val(vo.winCountSum);
						$("#lostCountSum").val(vo.lostCountSum);
						$("#drawCountSum").val(vo.drawCountSum);
						$("#winLostCountSum").val(vo.winLostCountSum);
						$("#taxCoinsSum").val(vo.taxCoinsSum);
						//探宝信息
						$("#freeChargeUserTimesSum").val(vo.freeChargeUserTimesSum);
						$("#chargeUserTimesSum").val(vo.chargeUserTimesSum);
						$("#freeGenCoinsSum").val(vo.freeGenCoinsSum);
						$("#freeGenLeavesSum").val(vo.freeGenLeavesSum);
						$("#payGenCoinsSum").val(vo.payGenCoinsSum);
						$("#payGenLeavesSum").val(vo.payGenLeavesSum);
					},"json");
				},"json");
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
					$nextButton.show();
					$backButton.show();
					$saveButton.hide();
				}
				if(index==3){
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
				if(index==3){
					$saveButton.hide();
					$backButton.show();
					$nextButton.show();
					$('#activityTabs').tabs('select',2);
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
						$nextButton.show();
						$('#activityTabs').tabs('select',2);
						return true;
					}else{
						return false;
					}
				}
				if(index==2){
					if(validate()){
						$nextButton.hide();
						$backButton.show();
						$saveButton.show();
						$('#activityTabs').tabs('select',3);
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
	
	function validate(){
		return true;//检验为空
	};
	
	eason.register("BaseInfoVo");
	//修改呢称
	BaseInfoVo.modify = function(target,type) {
		var params={};
		params.playerId=$("input[name='playerId']").val();
		params.oldValue=$("#"+target).val();
		params.updType=type;
		//对话框 标题，对话框类型
		var info,dialogType;
		switch(type){
			case 1: info='修改【玩家】';dialogType='stringModifyDialog';break;
			case 2: info='修改【密码】';dialogType='passwordModifyDialog';break;
			case 3: info='修改【呢称】';dialogType='stringModifyDialog';break;
			case 4: info='修改【手机】';dialogType='stringModifyDialog';break;
			case 5: info='修改【签名】';dialogType='stringModifyDialog';break;
			case 6: info='重置【图像】';dialogType='imageModifyDialog';break;
			case 11: info='修改【金币】';dialogType='numberModifyDialog';break;
			case 12: info='修改【金叶子】';dialogType='numberModifyDialog';break;
			case 13: info='修改【经验值】';dialogType='numberModifyDialog';break;
			case 14: info='修改【魅力值】';dialogType='numberModifyDialog';break;
			case 21: info='修改【玫瑰数量】';dialogType='numberModifyDialog';break;
			case 22: info='修改【香水数量】';dialogType='numberModifyDialog';break;
			case 23: info='修改【钻石数量】';dialogType='numberModifyDialog';break;
			case 24: info='修改【跑车数量】';dialogType='numberModifyDialog';break;
			case 25: info='修改【别墅数量】';dialogType='numberModifyDialog';break;
			case 31: info='修改【胜局数】';dialogType='numberModifyDialog';break;
			case 32: info='修改【负局数】';dialogType='numberModifyDialog';break;
		};
		openDiog(dialogType,info,"<%=path%>/admin/zjh/dialog/"+dialogType,"userForm_"+dialogType,params,function(){
		    if(type==6){
		    	 $("#"+target).attr("src","http://texasimage.pto2o.com/texas_image_srv/download.php?srcID=&destID=&fileSize=orign");
		    	//$("#"+target).attr("src","http://115.29.11.227/image_srv/download.php?srcID=102445&destID=102445&fileSize=orign");
		    }else if(type==2){
		    	 $("#account").css("background-color","red");
		    }else{
		    	 $("#"+target).val($("#updValue").val());
		    	 $("#"+target).css("color","red");
		    }
			$("#oldValue").val("");
			$("#updValue").val("");
			window.returnValue="success";
			$("#"+dialogType).dialog("close");
		});
	}
	$(document).ready(function () {
	    var obj = window.dialogArguments;
		new CustomerOpt(obj.id,obj.type);
		if(obj.type=="edit"){
			com.eason.form.disableForm("activityForm",true);
		}
	});
	</script>		
	</head>
	<body style="padding: 0;margin: 0">
		<div id="numberModifyDialog"></div>
	 	<div id="stringModifyDialog"></div>
	 	<div id="imageModifyDialog"></div>
	 	<div id="passwordModifyDialog"></div>
	 	<input type="hidden" name="playerId"/>
	 
		<form id='activityForm' method='POST' enctype='multipart/form-data'>
		<div id="activityTabs" class="easyui-tabs" style="height: 450px ;">
		<div title="一：玩家基础信息" style="padding: 10px 10px;">
				<div style='padding: 10px 20px;'>
					<table style='padding: 10px 25px;' >
						<tr style='height: 25px;'>
							<td>头像：</td>
							<td>
								<img id="userImage" name="userImage" width="150" height="150"/>
							</td>
							<td colspan="2">
								呢称：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' id='nickname' name="nickname" style="width:120px;height:23px;margin:2px;padding:0px;"/>
								<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="BaseInfoVo.modify('nickname',3)">修改<font color='red'>*</font></a><br/>
								账号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' id='account' name="account" style="width:120px;height:23px;margin:2px;padding:0px;" disabled="disabled"/>
								<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="BaseInfoVo.modify('password',2)">修改密码<font color='red'>*</font></a><br/>
								内部ID：&nbsp;&nbsp;<input type='text' id='accountId' name="accountId" style="width:120px;height:23px;margin:2px;padding:0px;" disabled="disabled"/><br/>
								外部ID：&nbsp;&nbsp;<input type='text' id='externId' name="externId" style="width:120px;height:23px;margin:2px;padding:0px;" disabled="disabled"/><br/>
								手机：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' id='mobile' name="mobile" style="width:120px;height:23px;margin:2px;padding:0px;" />
								<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="BaseInfoVo.modify('mobile',4)">修改<font color='red'>*</font></a>
							</td>
						</tr>	
						<tr style='height: 25px;'>
							<td>性别：</td>
							<td>
								<label style="width: 100px; text-align: left; padding-right: 5px;"></label>
								<input name="sex" type="radio" value="0" checked="checked" disabled="disabled" />男
							  	<input name="sex" type="radio" value="1" disabled="disabled" />女
								<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="BaseInfoVo.modify('userImage',6)">重置头像<font color='red'>*</font></a>
							</td>
							<td>设备信息：</td>
							<td>
								<input type='text' id='hwsn' name="hwsn" style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/>
							</td>
						</tr>
						<tr style='height: 25px;'>
							<td>状态：</td>
							<td>
								<input type='text' id='status' name="status" style="width:150px;height:23px;margin:2px;padding:0px;color: green;" disabled="disabled" />
							</td>
							<td>渠道：</td>
							<td>
								<input type='text' id='channel' name="channel" style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/>
							</td>
						</tr>
						<tr style='height: 25px;' class="dateTr">
							<td class="labletd">注册时间：</td>
							<td>
								<input type='text' id='registerTime' name="registerTime"  style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/>
							</td>
							<td class="labletd">注册IP：</td>
							<td>
								<input type='text' id='registerIp' name="registerIp" style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/>
							</td>
						</tr>
						<tr style='height: 25px;' class="dateTr">
							<td class="labletd">登陆次数：</td>
							<td>
								<input type='text' id='loginTimes' name="loginTimes"  style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/>
							</td>
							<td class="labletd">最近登陆时间：</td>
							<td>
								<input type='text' id='lastLoginTime' name="lastLoginTime" style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/>
							</td>
						</tr>
						 <tr style='height: 0px;'>
							<td>签名：</td>
							<td colspan="4">
								<textarea id='signature' name="signature" style="width: 350px;height: 65px;margin:2px;padding:0px;"/></textarea>
								<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="BaseInfoVo.modify('signature',5)">修改<font color='red'>*</font></a>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div title="二：玩家经验和魅力值" style="padding: 10px">
           		<div style='padding: 5px 20px;'>
           			<fieldset style="margin: 5px;5px;">
           				<legend>经验值</legend>
           				<table style='padding: 10px 25px;'>
							<tr style='height: 25px;'>
								<td>金币：</td>
								<td>
									<input type='text' id='coins' name="coins" style="width:120px;height:23px;margin:2px;padding:0px;"/>
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="BaseInfoVo.modify('coins',11)">修改<font color='red'>*</font></a>
								</td>
								<td>vip等级：</td>
								<td>
									<input type='text' id='vipGrade' name="vipGrade" style="width:120px;height:23px;margin:2px;padding:0px;" />
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>金叶子：</td>
								<td>
									<input type='text' id='leaves' name="leaves" style="width:120px;height:23px;margin:2px;padding:0px;"/>
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="BaseInfoVo.modify('leaves',12)">修改<font color='red'>*</font></a>
								</td>
								<td>经验值：</td>
								<td>
									<input type='text' id='experience' name="experience" style="width:120px;height:23px;margin:2px;padding:0px;" />
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="BaseInfoVo.modify('experience',13)">修改<font color='red'>*</font></a>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>胜局数：</td>
								<td>
									<input type='text' id='winCount' name="winCount" style="width:120px;height:23px;margin:2px;padding:0px;"/>
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="BaseInfoVo.modify('winCount',31)">修改<font color='red'>*</font></a>
								</td>
								<td>负局数：</td>
								<td>
									<input type='text' id='lostCount' name="lostCount" style="width:120px;height:23px;margin:2px;padding:0px;" />
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="BaseInfoVo.modify('lostCount',32)">修改<font color='red'>*</font></a>
								</td>
							</tr>
					</table>
           			</fieldset>
           			<fieldset style="margin: 5px;5px;">
           				<legend>魅力值</legend>
           				<table style='padding: 10px 25px;'>
							<tr style='height: 25px;'>
								<td>魅力值：</td>
								<td>
									<input type='text' id='charm' name="charm" style="width:110px;height:23px;margin:2px;padding:0px;"/>
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="BaseInfoVo.modify('charm',14)">修改<font color='red'>*</font></a>
								</td>
								<td>玫瑰数量：</td>
								<td>
									<input type='text' id='gift1' name="gift1" style="width:110px;height:23px;margin:2px;padding:0px;" />
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="BaseInfoVo.modify('gift1',21)">修改<font color='red'>*</font></a>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>香水数量：</td>
								<td>
									<input type='text' id='gift2' name="gift2" style="width:110px;height:23px;margin:2px;padding:0px;"/>
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="BaseInfoVo.modify('gift2',22)">修改<font color='red'>*</font></a>
								</td>
								<td>钻石数量：</td>
								<td>
									<input type='text' id='gift3' name="gift3" style="width:110px;height:23px;margin:2px;padding:0px;" />
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="BaseInfoVo.modify('gift3',23)">修改<font color='red'>*</font></a>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>跑车数量：</td>
								<td>
									<input type='text' id='gift4' name="gift4" style="width:110px;height:23px;margin:2px;padding:0px;"/>
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="BaseInfoVo.modify('gift4',24)">修改<font color='red'>*</font></a>
								</td>
								<td>别墅数量：</td>
								<td>
									<input type='text' id='gift5' name="gift5" style="width:110px;height:23px;margin:2px;padding:0px;" />
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="BaseInfoVo.modify('gift5',25)">修改<font color='red'>*</font></a>
								</td>
							</tr>
					</table>
           			</fieldset>
				</div>
		    </div>
		    <div title="三：玩家充值和老虎机信息" style="padding: 10px">
           		<div style='padding: 5px 20px;'>
           			<fieldset style="margin: 5px;5px;">
           				<legend>充值方式</legend>
           				<table style='padding: 10px 25px;'>
           					<tr style='height: 25px;'>
								<td>在线时长：</td>
								<td>
									<input type='text' id='totalOnlineDuration' name="totalOnlineDuration" style="width:140px;height:23px;margin:2px;padding:0px;"/>
								</td>
								<td>总充值：</td>
								<td>
									<input type='text' id='totalConsume' name="totalConsume" style="width:140px;height:23px;margin:2px;padding:0px;" />
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>IOS商城：</td>
								<td>
									<input type='text' id='iosStore' name="iosStore" style="width:140px;height:23px;margin:2px;padding:0px;"/>
									<font color='red'>*</font>
								</td>
								<td>支付宝：</td>
								<td>
									<input type='text' id='alipay' name="alipay" style="width:140px;height:23px;margin:2px;padding:0px;" />
									<font color='red'>*</font>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>充值卡：</td>
								<td>
									<input type='text' id='szf' name="szf" style="width:140px;height:23px;margin:2px;padding:0px;"/>
									<font color='red'>*</font>
								</td>
								<td>悠悠村话费：</td>
								<td>
									<input type='text' id='uucPay' name="uucPay" style="width:140px;height:23px;margin:2px;padding:0px;"/>
									<font color='red'>*</font>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>银联：</td>
								<td>
									<input type='text' id='ylPay' name="ylPay" style="width:140px;height:23px;margin:2px;padding:0px;"/>
									<font color='red'>*</font>
								</td>
								<td>移动MM：</td>
								<td>
									<input type='text' id='mmPay' name="mmPay" style="width:140px;height:23px;margin:2px;padding:0px;" />
									<font color='red'>*</font>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>联通：</td>
								<td>
									<input type='text' id='woPay' name="woPay" style="width:140px;height:23px;margin:2px;padding:0px;"/>
									<font color='red'>*</font>
								</td>
								<td>天翼：</td>
								<td>
									<input type='text' id='tyPay' name="tyPay" style="width:140px;height:23px;margin:2px;padding:0px;" />
									<font color='red'>*</font>
								</td>
							</tr>
					</table>
           			</fieldset>
           			<fieldset style="margin: 5px;5px;">
           				<legend>老虎机信息</legend>
           				<table style='padding: 10px 25px;'>
							<tr style='height: 25px;'>
								<td>总局数：</td>
								<td>
									<input type='text' id='matchTimeSum' name="matchTimeSum" style="width:140px;height:23px;margin:2px;padding:0px;"/>
									<font color='red'>*</font>
								</td>
								<td>总输赢：</td>
								<td>
									<input type='text' id='matchCoinsSum' name="matchCoinsSum" style="width:140px;height:23px;margin:2px;padding:0px;" />
									<font color='red'>*</font>
								</td>
							</tr>
					</table>
           			</fieldset>
				</div>
		    </div>
		    <div title="四：玩家猜拳和探宝信息" style="padding: 10px">
           		<div style='padding: 5px 20px;'>
           			<fieldset style="margin: 5px;5px;">
           				<legend>猜拳信息</legend>
           				<table style='padding: 10px 25px;'>
							<tr style='height: 25px;'>
								<td>赢的次数：</td>
								<td>
									<input type='text' id='winCountSum' name="winCountSum" style="width:140px;height:23px;margin:2px;padding:0px;"/>
									<font color='red'>*</font>
								</td>
								<td>输的次数：</td>
								<td>
									<input type='text' id='lostCountSum' name="lostCountSum" style="width:140px;height:23px;margin:2px;padding:0px;" />
									<font color='red'>*</font>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>平的次数：</td>
								<td>
									<input type='text' id='drawCountSum' name="drawCountSum" style="width:140px;height:23px;margin:2px;padding:0px;"/>
									<font color='red'>*</font>
								</td>
								<td>输赢额：</td>
								<td>
									<input type='text' id='winLostCountSum' name="winLostCountSum" style="width:140px;height:23px;margin:2px;padding:0px;" />
									<font color='red'>*</font>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>抽水贡献：</td>
								<td>
									<input type='text' id='taxCoinsSum' name="taxCoinsSum" style="width:140px;height:23px;margin:2px;padding:0px;"/>
									<font color='red'>*</font>
								</td>
							</tr>
					</table>
           			</fieldset>
           			<fieldset style="margin: 5px;5px;">
           				<legend>探宝信息</legend>
           				<table style='padding: 10px 25px;'>
							<tr style='height: 25px;'>
								<td>探宝总次数：</td>
								<td>
									<input type='text' id='freeChargeUserTimesSum' name="freeChargeUserTimesSum" style="width:120px;height:23px;margin:2px;padding:0px;"/>
									<font color='red'>*</font>
								</td>
								<td>花金币探宝总次数：</td>
								<td>
									<input type='text' id='chargeUserTimesSum' name="chargeUserTimesSum" style="width:120px;height:23px;margin:2px;padding:0px;" />
									<font color='red'>*</font>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>免费产生的金币：</td>
								<td>
									<input type='text' id='freeGenCoinsSum' name="freeGenCoinsSum" style="width:120px;height:23px;margin:2px;padding:0px;"/>
									<font color='red'>*</font>
								</td>
								<td>免费产生的金叶子：</td>
								<td>
									<input type='text' id='freeGenLeavesSum' name="freeGenLeavesSum" style="width:120px;height:23px;margin:2px;padding:0px;" />
									<font color='red'>*</font>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>付费产生的金币：</td>
								<td>
									<input type='text' id='payGenCoinsSum' name="payGenCoinsSum" style="width:120px;height:23px;margin:2px;padding:0px;"/>
									<font color='red'>*</font>
								</td>
								<td>付费产生的金叶子：</td>
								<td>
									<input type='text' id='payGenLeavesSum' name="payGenLeavesSum" style="width:120px;height:23px;margin:2px;padding:0px;" />
									<font color='red'>*</font>
								</td>
							</tr>
					</table>
           			</fieldset>
				</div>
		    </div>				
		</div>
		<div style="heigth: 25px; padding: 10px 0 0 0; text-align: right;">
			<a href="#" style="margin-right: 10px;display: none;" id="back" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">上一步</a>
			<a href="#" style="margin-right: 10px;" id="next" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">下一步</a>
			<a href="#" style="margin-right: 10px;display: none;" id="save" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">关闭</a>
		</div>
	</form>
	</body>
</html>