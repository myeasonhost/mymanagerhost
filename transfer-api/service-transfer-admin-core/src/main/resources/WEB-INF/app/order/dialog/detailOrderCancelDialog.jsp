<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%String path = request.getContextPath(); %>
<form id='form_detailOrderCancelDialog' method='POST' enctype='multipart/form-data'>
		<div id="activityTabs" class="easyui-tabs" style="height: 465px ;">
			<div title="一：订单取消信息" style="padding: 10px">
				<div style='padding: 5px 20px;'>
           			<fieldset style="margin: 5px;5px;">
           				<legend>取消订单信息</legend>
           				<table style='padding: 10px 10px;'>
							<tr style='height: 25px;'>
								<td>订单取消id：</td>
								<td>
									<input type='text' id='orderCancelId' name="orderCancelId" style="width:120px;height:23px;margin:2px;padding:0px;"/>
								</td>
								<td>用户id：</td>
								<td>
									<input type='text' id='userId' name="userId" style="width:120px;height:23px;margin:2px;padding:0px;"/>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>取消时间：</td>
								<td>
									<input type='text' id='cancelTime' name="cancelTime" style="width:120px;height:23px;margin:2px;padding:0px;" />
								</td>
								<td>用户类型：</td>
								<td>
									<input name="userType" type="radio" value="1" disabled="disabled" />车主
								  	<input name="userType" type="radio" value="2" disabled="disabled" />货主
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>货物订单id：</td>
								<td>
									<input type='text' id='cargoOrderId' name="cargoOrderId" style="width:150px;height:23px;margin:2px;padding:0px;" />
								</td>
								<td>状态：</td>
								<td>
									<input type='text' id='status' name="status" style="color:red;width:120px;height:23px;margin:2px;padding:0px;" />
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>取消原因：</td>
								<td colspan="4">
									<textarea id='cancelReason' name="cancelReason" style="width: 350px;height: 65px;margin:2px;padding:0px;"/></textarea>
								</td>
							</tr>
					</table>
					</fieldset>
				</div>
		    </div>
		    <div title="二：审批记录信息" style="padding: 10px">
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
								<td>扣款金额：</td>
								<td>
									<input type='text' id='chargeBack' name="chargeBack" style="color:red;width:140px;height:23px;margin:2px;padding:0px;"/>
								</td>
							</tr>
							<tr style='height: 25px;'>
								<td>处理结果：</td>
								<td colspan="6">
									<textarea id='dealRemark' name="dealRemark" style="width: 360px;height: 200px;margin:2px;padding:0px;"/></textarea>
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
		com.eason.form.disableForm("form_detailOrderCancelDialog",true);
		new CustomerOpt();
	})
	</script>	