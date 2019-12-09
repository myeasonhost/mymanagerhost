var eason={};

/*注册命名空间*/
eason.register = function(path){    
    var arr = path.split(".");    
    var ns = "";    
    for(var i=0;i<arr.length;i++){    
        if(i>0) ns += ".";    
        ns += arr[i];    
        eval("if(typeof(" + ns + ") == 'undefined') " + ns + " = new Object();");    
    }    
}; 
/*********************************************/
/*************校验检查************************/
/*********************************************/
eason.register("com.eason.check");
eason.register("com.eason.check.easyui");
/*密码由字母和数字组成，至少6位 */
com.eason.check.safePassword = function(value) {
    return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/.test(value));
};
/*校验输入域是否含有特殊符号 */ 
com.eason.check.checkTextValid = function(inputValue) {
    var reg =  /<script[^>]*?>[\s\S]*?<\/script>/;
	var reg1 =  /<script[^>]*?>/;
	var reg2 =  /<\/script>/;
	var reg3 = /<img[^>]+\>/;///<[^>]+>/;
	var reg4 = /<frameset[\s\S]+<\/frameset *>/;
	var reg5 = /<iframe[\s\S]+<\/iframe *>/;
	var reg6 = /<iframe>/;
	var reg7 = /<\/iframe>/;
	if (typeof(inputValue)=="undefined")return false;
	inputValue = inputValue.replace(/\s/ig,'').toLocaleLowerCase();
	flag = true;
	if(reg.test(inputValue)) {
			return true;
	}else{
		flag = false;
	}
	if(reg1.test(inputValue)) {
			return true;
	}else{
		flag = false;
	}
	if(reg2.test(inputValue)) {
			return true;
	}else{
		flag = false;
	}
	if(reg3.test(inputValue)) {
			return true;
	}else{
		flag = false;
	}
	if(reg4.test(inputValue)) {
			return true;
	}else{
		flag = false;
	}
	if(reg5.test(inputValue)) {
			return true;
	}else{
		flag = false;
	}
	if(reg6.test(inputValue)) {
			return true;
	}else{
		flag = false;
	}
	if(reg7.test(inputValue)) {
			return true;
	}else{
		flag = false;
	}
	var reg =  /[@#\$%&\'\"]/;
	if(reg.test(inputValue)) {
		return true;
	}else{
		flag = false;
	}
		return flag;
	} ;        
/*返回中英文字符串长度*/
com.eason.check.getValueLen = function(nameValue){  
    var nameStr = $.trim(nameValue);  
    var len = 0;  
    for(var i=0; i<nameStr.length; i++){  
       //str = markerStr.charAt(i);  
       if(nameStr.charCodeAt(i)>255 || nameStr.charCodeAt(i)<0){  
           len +=2;  
       }else{  
           len++;  
       }  
    }  
    return len;  
}; 
/*是否为空*/
com.eason.check.trim = function(sString){
	    return sString.replace(/(^\s*)|(\s*$)/g, "");
};
/*是否为数字*/
com.eason.check.isInt = function(sString){	
	if(sString==null) return false;
		var s = trim(sString);
		var pat_hd=/^[0-9]+$/;
		return pat_hd.test(s);
};
/*非空验证*/
com.eason.check.easyui.isEmpty = function($target,labName){
	var targetText=$target.val();
	if(com.eason.check.getValueLen(targetText)==0){
		$.messager.alert("提示", "【"+labName+"】不能为空。", "info", function() {
			$target.focus();
		});
		return false;
	}
	return true;
};
com.eason.check.easyui.isEmptyForDate = function($target,labName){
	var targetText=$target.val();
	if(com.eason.check.getValueLen(targetText)==0){
		$.messager.alert("提示", "【"+labName+"】不能为空。", "info", function() {
			//$target.focus();
		});
		return false;
	}
	return true;
};
/*非法字符验证*/
com.eason.check.easyui.isIllagalChar = function($target,labName){
	var targetText=$target.val();
	if(com.eason.check.checkTextValid(targetText)){
	    $.messager.alert("提示", "【"+labName+"】包含非法字符。", "info", function() {
			$target.focus();
		});
		return false;
	 }
	return true;
};
/*字符长度限制*/
com.eason.check.easyui.limitLength = function($target,labName,length){
	var targetText=$target.val();
	if(com.eason.check.getValueLen(targetText)>=length){
		$.messager.alert("提示", "【"+labName+"】不能超过"+length+"个字符（一个中文算两个字符）。", 'info', function() {
			$target.focus();
		});
		return false;
	}
	return true;
};
/*必须输入正数校验*/
com.eason.check.easyui.isNum = function($target,labName){
	var targetText=$target.val();
	if(isNaN(targetText)){
		$.messager.alert("提示", "【"+labName+"】请输入正确的"+labName+"。", "info", function() {
			$target.focus();
		});
		return false;
	}else{
		var a=parseFloat(targetText);
		if(a<0){
			$.messager.alert("提示", "【"+labName+"】不能为负数。", "info", function() {
				$target.focus();
			});
			return false;
		}
	}
	return true;
};
/*开始时间和比较时间的校验*/
com.eason.check.compareTime = function(startTime,endTime){
	if($.trim(startTime)=="" || $.trim(endTime)==""){
		return "0";
	}
	startTime=new Date(Date.parse(startTime.replace(/-/g,"/")));
	endTime=new Date(Date.parse(endTime.replace(/-/g,"/")));
	var a =endTime-startTime;
	if(a>0){
		return "1";
	}else if(a==0){
		return "0";
	}else{
		return "-1";
	}
};
/*检查结束时间*/
com.eason.check.easyui.checkEndTime = function($endTime,endLabName){
	var endTime=$endTime.val();
	if(endTime==null || endTime==""){
		$.messager.alert("警告","请选择【"+endLabName+"】");
		return false;
	}
	var startTime=new Date();
	endTime=new Date(Date.parse(endTime.replace(/-/g,"/")));
	if(endTime<startTime){
		$.messager.alert("警告","【"+endLabName+"】必须大于【当前时间】");
		return false;
	}
	return true;
};
/*检查开始和结束时间*/
com.eason.check.easyui.checkStartEndTime = function($startTime,startLabName,$endTime,endLabName){
	var startTime=$startTime.val();
	if(startTime==null || startTime==""){
		$.messager.alert("警告","请选择【"+startLabName+"】");
		return false;
	}
	var endTime=$endTime.val();
	if(endTime==null || endTime==""){
		$.messager.alert("警告","请选择【"+endLabName+"】");
		return false;
	}
	var flag=com.eason.check.compareTime(startTime,endTime);
	if(flag=="-1"){
		$.messager.alert("警告","【"+startLabName+"】必须大于【"+endLabName+"】");
		return false;
	}
	return true;
};
/*文件上传格式校验*/
com.eason.check.easyui.checkFileUpload = function($attachFile){
	var fileUpload = $attachFile.val();
	if(fileUpload!=''){
		var extend=fileUpload.substring(fileUpload.lastIndexOf('.')+1).toLowerCase();
		var express="txt,xls,xlsx,doc,docx,ppt,pptx,pdf,zip,rar";
		if(express.indexOf(extend) == -1){
			$.messager.alert('警告', '您上传的附件格式不对，请检查附件格式。', 'warning');
			return false;
		}		
	}
	return true;
};
/*********************************************/
/*************常量获取************************/
/*********************************************/
eason.register("com.eason.system");
/*获取格式系统时间字符串*/
com.eason.system.getSYSTime = function(date){
	var now = date; //当前时间    
    var yyyy = now.getFullYear(); //获取完整的年份(4位,1970-????)  
    var MM = now.getMonth() + 1; //获取当前月份(0-11,0代表1月)   
    var dd = now.getDate();  //获取当前日(1-31)
    var HH = now.getHours(); //获取当前小时数(0-23)
    var mm = now.getMinutes();  //获取当前分钟数(0-59)
    var ss = now.getSeconds(); //获取当前秒数(0-59)
    var mmm = now.getMilliseconds(); //获取当前毫秒数(0-999) 
    return format+yyyy+MM+dd+HH+mm+ss+mmm;
};
/*********************************************/
/*************弹出窗体获取*********************/
/*********************************************/
eason.register("com.eason.window");
com.eason.window.winUtils = {
		showDialogWindow : function(e,url,param,execute) {
			var whparamObj = { width: 520, height: 380 };  
				 // 相对于浏览器的居中位置  
			 var bleft = ($(window).width() - whparamObj.width) / 2;  
			 var btop = ($(window).height() - whparamObj.height) / 2;  
			 // 根据鼠标点击位置算出绝对位置  
			 var tleft = e.screenX - e.clientX;  
			 var ttop = e.screenY - e.clientY;  
			 // 最终模态窗口的位置  
			var left = bleft + tleft-120;  
		    var top = btop + ttop-100;   
			 var p = "help:no;status:no;center:yes;";  
			     p += 'dialogWidth:'+(whparamObj.width)+'px;';  
			     p += 'dialogHeight:'+(whparamObj.height)+'px;';  
			     p += 'dialogLeft:' + left + 'px;';  
			     p += 'dialogTop:' + top + 'px;'; 
				var returnValue = window.showModalDialog(url, param, p);
				execute(returnValue);
			},
			showDialogWindowOfWH : function(vwidth,vheight,e,url,param,execute) {
				var whparamObj = { width: vwidth, height: vheight };  
					 // 相对于浏览器的居中位置  
				 var bleft = ($(window).width() - whparamObj.width) / 2;  
				 var btop = ($(window).height() - whparamObj.height) / 2;  
				 // 根据鼠标点击位置算出绝对位置  
				 var tleft = e.screenX - e.clientX;  
				 var ttop = e.screenY - e.clientY;  
				 // 最终模态窗口的位置  
				var left = bleft + tleft-120;  
			    var top = btop + ttop-100;   
				 var p = "help:no;status:no;center:yes;";  
				     p += 'dialogWidth:'+(whparamObj.width)+'px;';  
				     p += 'dialogHeight:'+(whparamObj.height)+'px;';  
				     p += 'dialogLeft:' + left + 'px;';  
				     p += 'dialogTop:' + top + 'px;'; 
					var returnValue = window.showModalDialog(url, param, p);
					execute(returnValue);
				}
};
/**
 * JSON工具对象
 * 
 */
JsonUtil = {
	/**
	 * json对象转为字符串
	 * @param {} jsonObj
	 * @return string
	 */
	toString : function(jsonObj) {
		var arr = [];
		var fmt = function(s) {
			if (typeof s == 'object' && s != null)
				return this.toString(s);
			return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s;
		};
		for (var i in jsonObj)
			arr.push("'" + i + "':" + fmt(jsonObj[i]));
		return '{' + arr.join(',') + '}';
	}
};

//禁用form表单中所有的input[文本框、复选框、单选框],select[下拉选],多行文本框[textarea] 
eason.register("com.eason.form");
com.eason.form.disableForm=function(formId,isDisabled) {  
    var attr="disable";  
    if(!isDisabled){  
       attr="enable";  
    }  
    $("form[id='"+formId+"'] :text").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] textarea").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] select").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] :radio").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] :checkbox").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] :file").attr("disabled",isDisabled);  
    
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
};
 