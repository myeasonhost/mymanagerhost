<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script>
$.ajaxSetup({
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    cache:false,
    dataType:"json",
    complete:function(XHR,TS){
       var resText=XHR.responseText;
       if(resText.indexOf("sessionState")!=-1 && resText.indexOf("loginUrl")!=-1){
	        var resJson=$.parseJSON(resText);
	        if(resJson.sessionState==0){
		        window.top.location.href=resJson.loginUrl;

// 	        	self.opener.location.reload();
		    }
       }
	 }
});
function openDiog(diogId, diogTitle, diogUrl, formId, loadedObj,callback,loadFunction) {
	var $diogId=$("#"+diogId);
	$('#' + diogId).dialog({
		title : diogTitle,
		closed : false,
		width: loadedObj.width || 300,
		height: loadedObj.height || 180,
		cache : false,
		href : diogUrl,
		modal : true,
		buttons : [{
					text : '<spring:message code="common.Save"/>',
					iconCls : 'icon-ok',
					handler : function() {
						var saveBtn = $(this);
						$('#' + formId).form('submit',{
							onSubmit : function() {
								var isValid = $('#' + formId).form('validate');
								if (isValid) {
									saveBtn.unbind("click");
									saveBtn.linkbutton('disable');
									return true;
								} else {
								  	return false;
								}
							},
							success : function(data) {
								var msg = "";
								if(data){
									data=JSON.parse(data);
									if (data.result) {
										$.messager.alert('',data.message,'info',callback);
									} else {
										$.messager.alert('',data.message,'error',function(){
											$diogId.dialog("close");
										});
									}
								}
							}
						});
					}
				},
				{
					text : '<spring:message code="common.Cancel"/> ',
					iconCls : 'icon-cancel',
					handler : function() {
						$diogId.dialog('close');
					}
				}],
		onLoad : function() {
			if ((typeof loadedObj) == "object") {
				$('#' + formId).form('load', loadedObj);
			}
			if(loadFunction){
				loadFunction();
			}
		}
	});
}

function openDiog2(diogId, diogTitle, diogUrl, formId, loadedObj,callback,loadFunction) {
	var $diogId=$("#"+diogId);
	$('#' + diogId).dialog({
		title : diogTitle,
		closed : false,
		width: loadedObj.width || 300,
		height: loadedObj.height || 180,
		cache : false,
		href : diogUrl,
		modal : true,
		buttons : [{
					text : '<spring:message code="common.Save"/>',
					iconCls : 'icon-ok',
					handler : function() {
						var saveBtn = $(this);
						$('#' + formId).form('submit',{
							onSubmit : function() {
								var isValid = $('#' + formId).form('validate');
								if (isValid) {
									saveBtn.unbind("click");
									saveBtn.linkbutton('disable');
									return true;
								} else {
								  	return false;
								}
							},
							success : function(data) {
								var msg = "";
								if(data){
									data=JSON.parse(data);
									if (data.result) {
										$.messager.alert('',data.message,'info',callback);
									} else {
										$.messager.alert('',data.message,'error',function(){
											$diogId.dialog("close");
										});
									}
								}
							}
						});
					}
				},
				{
					text : '<spring:message code="common.Cancel"/> ',
					iconCls : 'icon-cancel',
					handler : function() {
						$diogId.dialog('close');
					}
				}],
		onLoad : function() {
			if ((typeof loadedObj) == "object") {
				$('#' + formId).form('load', loadedObj);
				if(loadFunction){
					loadFunction();
				}
			}
		}
	});
};

//用于方法详情页的弹窗显示。
function openDiog3(diogId, diogTitle, diogUrl, formId, loadedObj,callback,loadFunction) {
	var $diogId=$("#"+diogId);
	$('#' + diogId).dialog({
		title : diogTitle,
		closed : false,
		width: loadedObj.width || 300,
		height: loadedObj.height || 180,
		cache : false,
		href : diogUrl,
		modal : true,
		buttons : [
				{
					text : '<spring:message code="common.Cancel"/> ',
					iconCls : 'icon-cancel',
					handler : function() {
						$diogId.dialog('close');
					}
				}],
		onLoad : function() {
			if ((typeof loadedObj) == "object") {
				$('#' + formId).form('load', loadedObj);
				loadFunction();
			}
		}
	});
};

// 表格删除
// 表格删除
function remove(gridId, url) {
	var rows = $('#' + gridId).datagrid("getChecked"); 
	if (rows.length > 0) {
	 	var tmpstr = ' <spring:message code="common.records"/>';
	 	if(rows.length==1){
			tmpstr=  ' <spring:message code="common.record"/>';
		}  
	  
		$.messager.confirm('', '<spring:message code="common.del1"/> ' + rows.length + ' ' + tmpstr + '？',
				function(r) {
					if (r) {
						var ids = "";
						for ( var i = 0; i < rows.length; i++) {
							//ids.push(rows[i].id);
							ids += rows[i].id+'-';
						}
						$.post(url, {
							ids:ids
						}, function(data, status) {
							if ("success" == status && "1" == data) {
								$.messager.show({
									title : '',
									msg : '<spring:message code="common.Succ"/>',
									showType : 'show'
								});
							} else {
								$.messager.show({
									title : '',
									msg : '<spring:message code="common.Failed"/>' ,
									showType : 'show'
								});
							}
							$('#' + gridId).datagrid('uncheckAll').datagrid(
									'unselectAll').datagrid('clearSelections');
							$('#' + gridId).datagrid('reload');
						}, "json");
					}
				});
	} else {
		$.messager.alert('', '<spring:message code="common.NoRecordSct"/>', 'info');
	}
}
//用于方法详情页的弹窗显示。
function openDiog3(diogId, diogTitle, diogUrl, formId, loadedObj,callback,loadFunction) {
	var $diogId=$("#"+diogId);
	$('#' + diogId).dialog({
		title : diogTitle,
		closed : false,
		width: loadedObj.width || 300,
		height: loadedObj.height || 180,
		cache : false,
		href : diogUrl,
		modal : true,
		buttons : [
				{
					text : '<spring:message code="common.Cancel"/> ',
					iconCls : 'icon-cancel',
					handler : function() {
						$diogId.dialog('close');
					}
				}],
		onLoad : function() {
			if ((typeof loadedObj) == "object") {
				$('#' + formId).form('load', loadedObj);
				loadFunction();
			}
		}
	});
}

//表格 记录恢复
function reset(gridId, url) {
	var rows = $('#' + gridId).datagrid("getChecked");
	if (rows.length > 0) {
		$.messager.confirm('<spring:message code="common.PlsSct"/>', '<spring:message code="common.reset1"/> ' + rows.length + ' <spring:message code="common.record"/>？',
				function(r) {
					if (r) {
						var ids = [];
						for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.post(url, {
							ids : ids.join(','),optType:"reset"
						}, function(data, status) {
							if ("success" == status && "1" == data) {
								$.messager.show({
									title : ' ',
									msg : '<spring:message code="common.Succ"/>',
									showType : 'show'
								});
							} else {
								$.messager.show({
									title : ' ',
									msg : '<spring:message code="common.Failed"/>' ,
									showType : 'show'
								});
							}
							$('#' + gridId).datagrid('uncheckAll').datagrid(
									'unselectAll').datagrid('clearSelections');
							$('#' + gridId).datagrid('reload');
						}, "text");
					}
				});
	} else {
		$.messager.alert('', '<spring:message code="common.NoRecordSct"/>', 'info');
	}
}

function disable(gridId, url) {
	var rows = $('#' + gridId).datagrid("getChecked");
	if (rows.length > 0) { 
		$.messager.confirm('<spring:message code="common.PlsSct"/> ', '<spring:message code="common.diable1"/> ' + rows.length + '<spring:message code="common.record"/>？',
				function(r) {
					if (r) {
						var ids = [];
						for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.post(url, {
							ids : ids.join(',') ,optType:"disable"
						}, function(data, status) {
							if ("success" == status && "1" == data) {
								$.messager.show({
									title : '',
									msg : '<spring:message code="common.Succ"/>',
									showType : 'show'
								});
								$('#' + gridId).datagrid('uncheckAll').datagrid(
								'unselectAll').datagrid('clearSelections');
								$('#' + gridId).datagrid('reload');
							} else if("0" == data){
								$.messager.alert('', '<spring:message code="common.optErr"/>', 'error');
							}else{
								$.messager.show({
									title : '',
									msg : '<spring:message code="common.Failed"/>',
									showType : 'show'
								});
							}
						}, "text");
					}
				});
	} else {
		$.messager.alert('', '<spring:message code="common.NoRecordSct"/>', 'info');
	}
}

/* data */
function queryByPara(gridId, formId) {
	var o = {};
	var form = $('#' + formId);
	if(com.eason.check.checkTextValid($("input[name='licensekey']").val())){
		$.messager.alert('', '<spring:message code="common.queryCharactersValid"/>', 'info');
		return ;
	}
	if(com.eason.check.compareTime($("input[name='createtimeStart']").val(),$("input[name='createtimeEnd']").val())=='-1'){
		$.messager.alert('', '<spring:message code="common.dateCompareValid"/>', 'info');
		return ;
	}
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	$('#' + gridId).datagrid('load', serializeForm(formId));
}

function serializeForm(formId) {
	var o = {};
	var form = $('#' + formId);
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

function resetData(formId) {
	$('#' + formId).form('reset');
}

function clearData(formId) {
	$('#' + formId).form('clear');
}
function disableForm(formId,isDisabled) {  
    
    var attr="disable";  
    if(!isDisabled){  
       attr="enable";  
    }  
    $("form[id='"+formId+"'] :text").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] textarea").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] select").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] :radio").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] :checkbox").attr("disabled",isDisabled);  
      
    //禁用jquery easyui中的下拉选（使用input生成的combox）  
  
    $("#" + formId + " input[class='combobox-f combo-f']").each(function () {  
        if (this.id) {alert("input"+this.id);  
            $("#" + this.id).combobox(attr);  
        }  
    });  
      
    //禁用jquery easyui中的下拉选（使用select生成的combox）  
    $("#" + formId + " select[class='combobox-f combo-f']").each(function () {  
        if (this.id) {  
        alert(this.id);  
            $("#" + this.id).combobox(attr);  
        }  
    });  
      
    //禁用jquery easyui中的日期组件dataBox  
    $("#" + formId + " input[class='datebox-f combo-f']").each(function () {  
        if (this.id) {  
        alert(this.id)  
            $("#" + this.id).datebox(attr);  
        }  
    });  
}  
$.extend($.fn.validatebox.defaults.rules, {
	lengthAccount : {
		validator : function(value, param) {
			return value.length < param[0];
		},
		message : '<spring:message code="common.lengthAccount"/>'
	},
	equals : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '<spring:message code="common.newPwdErr"/>'
	},
	validStr: {
		validator : function(value, param) {
			return(/^[\u4E00-\u9FA5\w\d]+$/.test(value)); 
		},
		message : '<spring:message code="common.Characters"/>'
	},
	validOldPwd: {
		validator : function(value, param) {
			var result = $.ajax({
				url : param[0],
				dataType : "json",
				data : {"oldPwd":value},
				async : false,
				cache : false,
				type : "post"
			}).responseText;
			return result!=0;
		},
		message : '<spring:message code="common.oldPwdErr"/>'
	},
	mac: {
        validator: function(value, param){
        	return(/^([0-9a-fA-F]{2})(([0-9a-fA-F]{2}){5})+$/.test(value)); 
        },
        message: '<spring:message code="common.macErr"/>'
	},
	version: {
        validator: function(value, param){
        	return(/^([0-9]{4})((.[0-9]{4}){3})+$/.test(value)); 
        },
        message: '<spring:message code="common.versionErr"/>'
    },
    validUnique : {
		validator : function(value, param) {
			var postdata = {};
			if(param.length==2){
				postdata[param[1]] = value;
			}
			if(param.length==3){
				postdata[param[1]] = value;
				postdata[param[2]] = $("#"+param[2]).val();
			}
			var result = $.ajax({
				url : param[0],
				dataType : "json",
				data : postdata,
				async : false,
				cache : false,
				type : "post"
			}).responseText;
			return !result;
		},
		message : '<spring:message code="common.fieldRepeat"/>'
	}
});

/**
 * 为datagrid添加导出方法 
 */
$.extend($.fn.datagrid.methods, {
			/**
			 * 表格导出方法
			 * @param jq
			 * @param paramters
			 */
			doExport : function(jq, paramters) {
				var options = jq.datagrid('options');
				var rows=jq.datagrid('getChecked');
				if($.isEmptyObject(rows)){
					$.messager.alert('', '<spring:message code="common.NoRecordSct"/>', 'info');
					return ;
				}
				var data={};
				data.columnFields=JSON.stringify(rows);
				data.columnTitles=jq.datagrid('getSimplifiedColumn', options.columns[0]);
				var $tempForm = $("<form method='POST'></form>");
				var $textInput = $("<textarea name='columnFields' style='display:none'>"+data.columnFields+"</>");
				$textInput.appendTo($tempForm);
				var $urlInput = $("<textarea name='columnTitles' style='display:none'>"+data.columnTitles+"</>");
				$urlInput.appendTo($tempForm);
				var sort = options.sortName;
				var order = options.sortOrder;
				if(null!=sort && $.trim(sort)!=''){
					var $sortInput = $("<input type='hidden' name='sort' value=\"" +$.trim(sort) + "\" />");
					$sortInput.appendTo($tempForm);
				}
				if(null!=order && $.trim(sort)!=''){
					var $orderInput = $("<input type='hidden' name='order' value=\"" +$.trim(order) + "\" />");
					$orderInput.appendTo($tempForm);
				}
				$tempForm.appendTo($('body'));
				var exportURL;
				if(options.url.indexOf("?")!=-1){
					exportURL=options.url.substring(0,options.url.indexOf("?"));
				}else{
					exportURL=options.url;
				}
				$tempForm.attr('action', exportURL + "Export");
				$tempForm.submit();
			},
			
			/**
			 * 获得简化版的列信息(只包括field,title,align信息)，以JSON字符串形式返回
			 * @param jq
			 * @param columnArray
			 * @returns {String}
			 */
			getSimplifiedColumn : function(jq, columnArray) {
				var columnJsonStr = "[";
				for (var columnIndex = 0; columnIndex < columnArray.length; columnIndex++) {
					columnJsonStr += "{";
					columnJsonStr += "\"field\":\"" + columnArray[columnIndex].field + "\"," ;
					columnJsonStr += "\"title\":\"" + columnArray[columnIndex].title + "\"," ;
					columnJsonStr += "\"align\":\"center\"" ;
					columnJsonStr += "}";
					if (columnIndex != columnArray.length - 1) {
						columnJsonStr += ",";
					}
				}
				columnJsonStr += "]";
				return columnJsonStr;
			}
		
		});
		
</script>