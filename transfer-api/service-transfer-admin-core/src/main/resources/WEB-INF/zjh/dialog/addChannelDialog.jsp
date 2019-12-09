<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%String path = request.getContextPath(); %>
<form id="userForm_addChannelDialog" method="post" action="<%=path%>/admin/zjh/channelMgr/add">
	<table style="font-size: 12px;">
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				内部渠道号：
			</td>
			<td>
				<input type="text" id="channelId" name="channelId" class="easyui-validatebox" data-options="required:true,validType:['length[1,8]','validUnique[\'<%=path%>/admin/zjh/channelMgr/queryByOne\',\'channelId\']']" style="width: 150px;"/>
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				渠道名称：
			</td>
			<td>
				<input type="text" id="channelName" name="channelName" class="easyui-validatebox" data-options="required:true,validType:['length[1,24]']" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				结算方式：
			</td>
			<td>
				<select id="cstype" name="cstype" class="easyui-combobox" size="4" style="width:80px;">
					<option value="Free" selected>Free</option>
					<option value="CPA">CPA</option>  
					<option value="CPS">CPS</option> 
					<option value="CPC">CPC</option> 
				</select>
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				mm渠道号：
			</td>
			<td>
				<input type="text" id="externChannel" name="externChannel" class="easyui-validatebox" data-options="required:true,validType:['length[1,16]','validUnique[\'<%=path%>/admin/zjh/channelMgr/queryByOne\',\'externChannel\']']"  style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				渠道查询账户：
			</td>
			<td>
				<input type="text" id="username" name="username" class="easyui-validatebox" data-options="validType:['length[0,16]','validUnique[\'<%=path%>/admin/zjh/channelMgr/queryByOne\',\'username\']']"  style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				渠道查询密码：
			</td>
			<td>
				<input type="text" id="password" name="password" class="easyui-validatebox" data-options="validType:['length[0,16]']" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				商务：
			</td>
			<td>
				<input type="text" id="shangwu" name="shangwu"  class="easyui-validatebox" data-options="validType:['length[0,8]']" style="width: 150px;" />
			</td>
		</tr>
		<tr>
			<td  style="display: inline-block; width: 150px; text-align: right; padding-right: 5px;">
				备注：
			</td>
			<td>
				<textarea id='memo' name="memo" class="easyui-validatebox" data-options="validType:['length[0,128]']" style="width: 150px;height: 65px;margin:2px;padding:0px;font-size: 12px;"/></textarea>
				
			</td>
		</tr>
	</table>
</form>