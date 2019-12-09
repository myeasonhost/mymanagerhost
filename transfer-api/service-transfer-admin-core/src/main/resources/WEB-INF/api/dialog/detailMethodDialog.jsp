<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%String path = request.getContextPath(); %>
<html>
	<head>
	<title>方法详细信息</title>
	</head>
	<body style="padding: 0;margin: 0">
		<form id='userForm_detailMethodDialog' method='POST'>
		<div id="activityTabs" class="easyui-tabs" style="height: 450px ;">
		<div title="一：方法基本信息" style="padding: 10px 10px;">
				<div style='padding: 10px 20px;'>
					<table style='padding: 10px 25px;' >
						<tr style='height: 25px;'>
							<td>方法ID：</td>
							<td>
								<input type='text' id='id' name="id" style="width:150px;height:23px;margin:2px;padding:0px;color: green;" disabled="disabled" />
							</td>
							<td>方法名：</td>
							<td>
								<input type='text' id='method' name="method" style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/>
							</td>
						</tr>
						<tr style='height: 25px;'>
							<td>方法中文名：</td>
							<td>
								<input type='text' id='methodName' name="methodName" style="width:150px;height:23px;margin:2px;padding:0px;color: green;" disabled="disabled" />
							</td>
							<td>方法类别：</td>
							<td>
								<input type='text' id='cateEnName' name="cateEnName" style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/>
							</td>
						</tr>
						<tr style='height: 25px;'>
							<td>方法备注：</td>
							<td colspan="4">
								<textarea id='methodMemo' name="methodMemo" style="width: 350px;height: 65px;margin:2px;padding:0px;"/></textarea>
							</td>
						</tr>
						<tr style='height: 25px;'>
							<td>一分钟可调用次数：</td>
							<td>
								<input type='text' id='invokeMinMaxNum' name="invokeMinMaxNum" style="width:150px;height:23px;margin:2px;padding:0px;color: green;" disabled="disabled" />
							</td>
							<td>一天可调用次数：</td>
							<td>
								<input type='text' id='invokeDayMaxNum' name="invokeDayMaxNum" style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/>
							</td>
						</tr>
						<tr style='height: 25px;' class="dateTr">
							<td class="labletd">调用等级：</td>
							<td>
								<input type='text' id='authLevel' name="authLevel"  style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/>
							</td>
							<td class="labletd">是否更新类接口：</td>
							<td>
								<input type='text' id='isUpdated' name="isUpdated" style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/>
							</td>
						</tr>
							<tr style='height: 25px;'>
							<td>XML数据格式示例：</td>
							<td colspan="4">
								<textarea id='xmlResult' name="xmlResult" style="width: 350px;height: 65px;margin:2px;padding:0px;"/></textarea>
							</td>
						</tr>
						<tr style='height: 25px;'>
							<td>JSON数据格式示例：</td>
							<td colspan="4">
								<textarea id='jsonResult' name="jsonResult" style="width: 350px;height: 65px;margin:2px;padding:0px;"/></textarea>
							</td>
						</tr>
						<tr style='height: 25px;' class="dateTr">
							<td class="labletd">是否增值接口：</td>
							<td>
								<!-- <input type='text' id='isExtras' name="isExtras"  style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/> -->
								<input name="isExtras" type="radio" value="0" checked="checked" disabled="disabled" />开放
							  	<input name="isExtras" type="radio" value="1" disabled="disabled" />增值
							</td>
							<td class="labletd">是否对外开放接口：</td>
							<td>
								<!-- <input type='text' id='isOpen' name="isOpen" style="width:150px;height:23px;margin:2px;padding:0px;" disabled="disabled"/> -->
								<input name="isOpen" type="radio" value="1"  disabled="disabled" />是
							  	<input name="isOpen" type="radio" value="0" disabled="disabled" />否
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div title="二：方法参数信息" style="padding: 10px">
           		<div style='padding: 5px 20px;'>
           		<fieldset style="margin: 5px;5px;">
           				<legend>方法参数列表</legend>
           				<table id="table_grid_params"  style="min-width:1000px;" data-options="fitColumns:'true',rownumbers:'true',striped:'true'">
						<caption></caption>
						<thead>
							<tr> 
							<!-- <th data-options="field:'id',sortable:'true',align:'center'">方法参数id</th> -->
							<th data-options="field:'paramName',sortable:'true',align:'center'">参数名称</th>
							<th data-options="field:'paramType',sortable:'true',align:'center'">参数类型</th>
							<th data-options="field:'isNecessary',sortable:'true',align:'center'">是否必须</th>  
							<th data-options="field:'example',sortable:'true',align:'center'">示例值</th>  
							<th data-options="field:'defaultValue',sortable:'true',align:'center'">默认值 </th>
							<th data-options="field:'paramDescription',sortable:'true',align:'center'">参数描述 </th>
							<th data-options="field:'paramClass',sortable:'true',align:'center'">参数类别</th>
							<th data-options="field:'isObject',sortable:'true',align:'center'">是否自定义对象 </th>
							<th data-options="field:'createTime',sortable:'true',align:'center'">创建时间  </th>
							</tr>
						</thead>
					 	<tbody>
					 	</tbody>
					</table>
           			</fieldset>
				</div>
		    </div>
		    <div title="三：方法配置信息" style="padding: 10px">
           		<div style='padding: 5px 20px;'>
           		<fieldset style="margin: 5px;5px;">
           				<legend>方法参数列表</legend>
           				<table id="table_grid_cfgs"  style="min-width:1000px;" data-options="fitColumns:'true',rownumbers:'true',striped:'true'">
						<caption></caption>
						<thead>
							<tr> 
							<!-- <th data-options="field:'id',sortable:'true',align:'center'">方法配置ID</th> -->
							<th data-options="field:'ver',sortable:'true',align:'center'">版本号</th>
							<th data-options="field:'cfgValue',sortable:'true',align:'center'">配置值</th>
							<th data-options="field:'isDeleted',sortable:'true',align:'center'">删除标识</th>
							<th data-options="field:'createTime',sortable:'true',align:'center'">创建时间</th>
							<th data-options="field:'updateTime',sortable:'true',align:'center'">更新时间</th>
							<th data-options="field:'createBy',sortable:'true',align:'center'">创建者id </th>
							<th data-options="field:'updateBy',sortable:'true',align:'center'">更新者id </th>
							<!-- <th data-options="field:'grossProfit',sortable:'true',align:'center'">扣点 </th>
							<th data-options="field:'grossMny',sortable:'true',align:'center'">扣点金额 </th>     -->
							</tr>
						</thead>
					 	<tbody>
					 	</tbody>
					</table>
           			</fieldset>
				</div>
		    </div>
			<div style="heigth: 25px; padding: 10px 0 0 0; text-align: right;">
				<a href="#" style="margin-right: 10px;display: none;" id="save" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">关闭</a>
			</div>
		</div>
		<div style="heigth: 25px; padding: 10px 0 0 0; text-align: right;">
			<a href="#" style="margin-right: 10px;display: none;" id="back" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">上一步</a>
			<a href="#" style="margin-right: 10px;" id="next" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">下一步</a>
			<a href="#" style="margin-right: 10px;display: none;" id="save" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">关闭</a>
		</div>
	</form>
	</body>
	<script type="text/javascript" charset="utf-8">
	$(function(){
	});
	</script>	
</html>