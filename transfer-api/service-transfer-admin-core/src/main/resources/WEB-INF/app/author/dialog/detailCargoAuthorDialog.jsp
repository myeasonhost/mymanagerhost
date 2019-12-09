<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%String path = request.getContextPath(); %>
<form id='form_detailCargoAuthorDialog' method='POST' enctype='multipart/form-data'>
		<div id="activityTabs" class="easyui-tabs" style="height: 465px ;">
		<div title="一：客户基础信息" style="padding: 10px 10px;">
				<div style='padding: 10px 20px;'>
					<table style='padding: 10px 25px;' >
						<tr style='height: 25px;'>
							<td>客户头像：</td>
							<td>
								<img id="logo" name="logo" width="150" height="150"/>
							</td>
							<td colspan="2">
								呢&nbsp;&nbsp;&nbsp;&nbsp;称：<input type='text' id='nickName' name="nickName" style="width:120px;height:23px;margin:2px;padding:0px;"/><br/>
								账&nbsp;&nbsp;&nbsp;&nbsp;号：<input type='text' id='account' name="account" style="width:120px;height:23px;margin:2px;padding:0px;" disabled="disabled"/><br/>
								用&nbsp;&nbsp;&nbsp;户id：<input type='text' id='accountId' name="userId" style="width:120px;height:23px;margin:2px;padding:0px;" disabled="disabled"/><br/>
								用&nbsp;户&nbsp;评分：<input type='text' id='userScore' name="userScore" style="width:120px;height:23px;margin:2px;padding:0px;" /><br/>
							</td>
						</tr>	
						<tr style='height: 25px;'>
							<td>是否VIP：</td>
							<td>
								<label style="width: 100px; text-align: left; padding-right: 5px;"></label>
								<input name="isVip" type="radio" value="0" disabled="disabled" />普通用户
							  	<input name="isVip" type="radio" value="1" disabled="disabled" />合同用户（VIP）
							</td>
							<td>注册时间：</td>
							<td>
								<input type='text' id='userCreateTime' name="userCreateTime" style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/>
							</td>
						</tr>
						<tr style='height: 25px;' class="dateTr">
							<td class="labletd">账号余额（元）：</td>
							<td>
								<input type='text' id='taBalance' name="taBalance"  style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/>
							</td>
							<td class="labletd">最近登陆时间：</td>
							<td>
								<input type='text' id='userUpdateTime' name="userUpdateTime" style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/>
							</td>
						</tr>
						 <tr style='height: 0px;'>
							<td>个性签名：</td>
							<td colspan="4">
								<textarea id='introduction' name="introduction" style="width: 350px;height: 65px;margin:2px;padding:0px;"/></textarea>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div title="二：货物认证信息" style="padding: 10px">
				<div style='padding: 5px 20px;'>
           			<fieldset style="margin: 5px;5px;">
           				<legend>认证基本信息</legend>
           				<table style='padding: 10px 10px;'>
							<tr style='height: 25px;'>
								<td>货物认证id：</td>
								<td>
									<input type='text' id='cargoAuthorId' name="cargoAuthorId" style="width:120px;height:23px;margin:2px;padding:0px;"/>
								</td>
								<td>姓名：</td>
								<td>
									<input type='text' id='authorName' name="authorName" style="width:120px;height:23px;margin:2px;padding:0px;"/>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>身份证号：</td>
								<td>
									<input type='text' id='idCard' name="idCard" style="width:120px;height:23px;margin:2px;padding:0px;" />
								</td>
								<td>状态：</td>
								<td>
									<input type='text' id='authorStatus' name="status" style="width:120px;height:23px;margin:2px;padding:0px;"/>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>创建时间：</td>
								<td colspan="3">
									<input type='text' id='createTime' name="createTime" style="width:300px;height:23px;margin:2px;padding:0px;" />
								</td>
							</tr>
					</table>
					</fieldset>
				</div>
		    </div>
		    <div title="三：认证证件信息" style="padding: 10px">
           		<div style='padding: 5px 20px;'>
           			<fieldset style="margin: 5px;5px;">
           				<legend>认证证件</legend>
           				<table style='padding: 10px 25px;'>
							<tr style='height: 25px;'>
								<td>身份证照片：</td>
								<td colspan="3">
									<img id="idCardPic" name="idCardPic" width="200" height="300" />
								</td>
							</tr>	
						</table>
           			</fieldset>
				</div>
		    </div>
		    <div title="四：审批记录信息" style="padding: 10px">
           		<div style='padding: 5px 20px;'>
           				<table style='padding: 10px 25px;'>
							<tr style='height: 25px;'>
								<td>处理人：</td>
								<td>
									<input type='text' id='dealBy' name="dealBy" style="width:140px;height:23px;margin:2px;padding:0px;"/>
								</td>
								<td>处理时间：</td>
								<td>
									<input type='text' id='dealTime' name="dealTime" style="width:140px;height:23px;margin:2px;padding:0px;" />
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>处理结果：</td>
								<td colspan="6">
									<textarea id='result' name="result" style="width: 360px;height: 200px;margin:2px;padding:0px;"/></textarea>
								</td>
							</tr>
					</table>
				</div>
		    </div>				
		</div>
		<div style="heigth: 25px; padding: 10px 0 0 0; text-align: right;">
			<a href="#" style="margin-right: 10px;display: none;" id="back" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">上一步</a>
			<a href="#" style="margin-right: 10px;" id="next" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">下一步</a>
		</div>
</form>
<script type="text/javascript" charset="utf-8">
	function CustomerOpt() {
		var vo = null;
	    var $backButton = $('#back');
		var $nextButton = $('#next');
		var $saveButton = $('#save');
		var initParamter = function() {
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
	
	$(function(){
		com.eason.form.disableForm("form_detailCargoAuthorDialog",true);
		new CustomerOpt();
	})
	</script>	