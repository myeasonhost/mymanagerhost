<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%String path = request.getContextPath(); %>
<form id='activityForm' method='POST' enctype='multipart/form-data'>
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
								用&nbsp;&nbsp;户id：<input type='text' id='accountId' name="userId" style="width:120px;height:23px;margin:2px;padding:0px;" disabled="disabled"/><br/>
								货&nbsp;&nbsp;物id：<input type='text' id='cargoId' name="cargoId" style="width:120px;height:23px;margin:2px;padding:0px;" disabled="disabled"/><br/>
								用户评分：<input type='text' id='userScore' name="userScore" style="width:120px;height:23px;margin:2px;padding:0px;" /><br/>
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
			<div title="二：货物基本信息" style="padding: 10px">
           				<table style='padding: 10px 10px;'>
							<tr style='height: 25px;'>
								<td>货物名：</td>
								<td>
									<input type='text' id='cargoName' name="cargoName" style="width:120px;height:23px;margin:2px;padding:0px;"/>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>出货人：</td>
								<td>
									<input type='text' id='shipperName' name="shipperName" style="width:120px;height:23px;margin:2px;padding:0px;"/>
								</td>
								<td>出货人电话：</td>
								<td>
									<input type='text' id='shipperPhone' name="shipperPhone" style="width:120px;height:23px;margin:2px;padding:0px;" />
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>出货时间：</td>
								<td colspan="3">
									<input type='text' id='startTime' name="startTime" style="width:300px;height:23px;margin:2px;padding:0px;" />
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>出货地点：</td>
								<td colspan="3">
									<input type='text' id='startLocation' name="startLocation" style="width:300px;height:23px;margin:2px;padding:0px;"/>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>目的地点：</td>
								<td colspan="3">
									<input type='text' id='endLocation' name="endLocation" style="width:300px;height:23px;margin:2px;padding:0px;"/>
								</td>
							</tr>
<!-- 							<tr style='height: 25px;'> -->
<!-- 								<td>出货地点定位：</td> -->
<!-- 								<td colspan="2"> -->
<!-- 									<input type='text' id='startGps' name="startGps" style="width:200px;height:23px;margin:2px;padding:0px;" /> -->
<!-- 								</td> -->
<!-- 								<td>目的地点定位：</td> -->
<!-- 								<td colspan="2"> -->
<!-- 									<input type='text' id='endGps' name="endGps" style="width:200px;height:23px;margin:2px;padding:0px;" /> -->
<!-- 								</td> -->
<!-- 							</tr> -->
							<tr style='height: 25px;'>
								<td>收货人：</td>
								<td>
									<input type='text' id='receiverName' name="receiverName" style="width:120px;height:23px;margin:2px;padding:0px;"/>
								</td>
								<td>收货人电话：</td>
								<td>
									<input type='text' id='receiverPhone' name="receiverPhone" style="width:120px;height:23px;margin:2px;padding:0px;" />
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>温度类型：</td>
								<td>
									<input type='text' id='tempEnvType' name="tempEnvType" style="width:120px;height:23px;margin:2px;padding:0px;"/>
								</td>
								<td>温度最低：</td>
								<td>
									<input type='text' id='tempMin' name="tempMin" style="width:120px;height:23px;margin:2px;padding:0px;" />
								</td>
								<td>温度最高：</td>
								<td>
									<input type='text' id='tempMax' name="tempMax" style="width:120px;height:23px;margin:2px;padding:0px;" />
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>货物数量：</td>
								<td>
									<input type='text' id='cargoNum' name="cargoNum" style="width:120px;height:23px;margin:2px;padding:0px;"/>
								</td>
								<td>货物长宽高：</td>
								<td>
									<input type='text' id='cargoZhg' name="cargoZhg" style="width:120px;height:23px;margin:2px;padding:0px;" />
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>货物尺寸：</td>
								<td>
									<input type='text' id='cargoSize' name="cargoSize" style="width:120px;height:23px;margin:2px;padding:0px;" />
								</td>
								<td>货物重量：</td>
								<td>
									<input type='text' id='cargoWeight' name="cargoWeight" style="width:120px;height:23px;margin:2px;padding:0px;" />
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>货物留言：</td>
								<td colspan="4">
									<textarea id='remark' name="remark" style="width: 350px;height: 65px;margin:2px;padding:0px;"/></textarea>
								</td>
							</tr>
					</table>
		    </div>
		    <div title="三：订单基本信息" style="padding: 10px">
           		<div style='padding: 5px 20px;'>
           			<fieldset style="margin: 5px;5px;">
           				<legend>订单信息</legend>
           				<table style='padding: 10px 25px;'>
           					<tr style='height: 25px;'>
								<td>货主名称：</td>
								<td>
									<input type='text' id='ownerName' name="ownerName" style="width:140px;height:23px;margin:2px;padding:0px;"/>
								</td>
								<td>货主电话：</td>
								<td>
									<input type='text' id='ownerPhone' name="ownerPhone" style="width:140px;height:23px;margin:2px;padding:0px;" />
								</td>
							</tr>
           					<tr style='height: 25px;'>
								<td>订单id：</td>
								<td>
									<input type='text' id='cargoOrderId' name="cargoOrderId" style="width:140px;height:23px;margin:2px;padding:0px;"/>
								</td>
								<td>订单名称：</td>
								<td>
									<input type='text' id='cargoOrderName' name="cargoOrderName" style="width:140px;height:23px;margin:2px;padding:0px;" />
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>配送类型：</td>
								<td>
									<input name="sendType" type="radio" value="1" disabled="disabled" />拼车
								  	<input name="sendType" type="radio" value="2" disabled="disabled" />包车
								</td>
								<td>订单状态：</td>
								<td>
									<input type='text' id='status' name="status" style="color:red;width:140px;height:23px;margin:2px;padding:0px;" /><font color='red'>*</font>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>订单评分：</td>
								<td>
									<input type='text' id='orderScore' name="orderScore" style="width:140px;height:23px;margin:2px;padding:0px;"/>
								</td>
								<td>创建时间：</td>
								<td>
									<input type='text' id='orderCreateTime' name="orderCreateTime" style="width:140px;height:23px;margin:2px;padding:0px;"/>
								</td>
							</tr>
					</table>
           			</fieldset>
           			<fieldset style="margin: 5px;5px;">
           				<legend>费用明细</legend>
           				<table style='padding: 10px 25px;'>
							<tr style='height: 25px;'>
								<td>总运费：</td>
								<td>
									<input type='text' id='amount' name="amount" style="width:140px;height:23px;margin:2px;padding:0px;"/>
									<font color='red'>*</font>
								</td>
								<td>是否发票：</td>
								<td>
									<input name="isInvoice" type="radio" value="0" disabled="disabled" />不需要
								  	<input name="isInvoice" type="radio" value="1" disabled="disabled" />需要
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>装货费用：</td>
								<td>
									<input type='text' id='loadAmount' name="loadAmount" style="width:140px;height:23px;margin:2px;padding:0px;"/>
								</td>
								<td>卸货费用：</td>
								<td>
									<input type='text' id='dischargeAmount' name="dischargeAmount" style="width:140px;height:23px;margin:2px;padding:0px;" />
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>距离配送费：</td>
								<td>
									<input type='text' id='rangeCharge' name="rangeCharge" style="width:140px;height:23px;margin:2px;padding:0px;"/>
								</td>
								<td>小费：</td>
								<td>
									<input type='text' id='tip' name="tip" style="width:140px;height:23px;margin:2px;padding:0px;" />
								</td>
							</tr>
					</table>
           			</fieldset>
				</div>
		    </div>
		    <div title="四：配送物流信息" style="padding: 10px">
           		<div style='padding: 5px 20px;'>
           				<table style='padding: 10px 25px;'>
							<tr style='height: 25px;'>
								<td>取货人ID：</td>
								<td>
									<input type='text' id='pickupId' name="pickupId" style="width:140px;height:23px;margin:2px;padding:0px;"/>
								</td>
								<td>是否异常：</td>
								<td>
									<input type='text' id='isPickup' name="isPickup" style="width:140px;height:23px;margin:2px;padding:0px;" />
									<font color='red'>*</font>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>问题描述：</td>
								<td colspan="6">
									<textarea id='pickupDesc' name="pickupDesc" style="width: 350px;height: 65px;margin:2px;padding:0px;"/></textarea>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>发布时间：</td>
								<td>
									<input type='text' id='publishTime' name="publishTime" style="width:140px;height:23px;margin:2px;padding:0px;"/>
								</td>
								<td>装货时间：</td>
								<td>
									<input type='text' id='deliTime' name="deliTime" style="width:140px;height:23px;margin:2px;padding:0px;" />
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>取消时间：</td>
								<td>
									<input type='text' id='cancelTime' name="cancelTime" style="width:140px;height:23px;margin:2px;padding:0px;"/>
								</td>
								<td>完成时间：</td>
								<td>
									<input type='text' id='finishTime' name="finishTime" style="width:140px;height:23px;margin:2px;padding:0px;" />
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>完成凭证：</td>
								<td colspan="6">
									<img id="finishAuthorPic" name="finishAuthorPic" width="200" height="300"/>
								</td>
							</tr>
					</table>
				</div>
		    </div>	
		    <div title="五：货物订单GPS定位" style="padding: 10px">
           		<div id="container" style="width:770px; height: 500px;">
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
				if(index==4){
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
				if(index==4){
					$saveButton.hide();
					$backButton.show();
					$nextButton.show();
					$('#activityTabs').tabs('select',3);
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
				if(index==3){
					if(validate()){
						$nextButton.hide();
						$backButton.show();
						$saveButton.show();
						$('#activityTabs').tabs('select',4);
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
		com.eason.form.disableForm("activityForm",true);
		new CustomerOpt();
	})
	</script>	