<form id="changePwdForm" method="post"  action="/admin/user/changePassword" >
		<input  name="id" value="${user.id}" type="hidden"/>
	<p>  
		<label style="display: inline-block; width: 120px; text-align: right; padding-right: 5px;"><span style="color:red">*</span>原始密码:</label>
	  	<input id="oldPwd" name="oldPwd" type="password" value="" style="width:200px" class="easyui-validatebox" data-options="required:true,validType:'validOldPwd[\'<%=path%>/admin/user/checkedAccountId?id=${user.id}\']'"/>
 	</p>
	<p>
		<label style="display: inline-block; width: 120px; text-align: right; padding-right: 5px;"><span style="color:red">*</span>新密码:</label>
	  	<input id="password" name="pwd" type="password" value="" style="width:200px" class="easyui-validatebox" data-options="required:true "/>
 	</p>
  	<p>
		<label style="display: inline-block; width: 120px; text-align: right; padding-right: 5px;"><span style="color:red">*</span>确认新密码:</label>
	  	<input type="password" value="" style="width:200px" class="easyui-validatebox" data-options="required:true,validType:'equals[\'#password\']'"/>
 	</p>			 
</form>
 
