<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="../lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet"
	type="text/css" />
<script src="../lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerForm.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerComboBox.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerRadio.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerSpinner.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerTextBox.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerTree.js"
	type="text/javascript"></script>
<script src="../lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="../lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script src="../lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>

<script type="text/javascript" src="../lib/tm/meetingroomInfo/addMeetingroom.js"></script>
<style type="text/css">
body {
	font-size: 12px;
}

.l-table-edit {
	margin-left: 150px;
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit {
	width: 80px;
	margin-left: 100px;
	position: relative;
	text-align: center;
	padding-bottom: 2px;
}

.l-button-test {
	width: 80px;
	margin-left: 150px;
	position: relative;
	text-align: center;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>
</head>
<body style="padding: 10px">

	<form name="form1" method="post" id="form1">
		<table cellpadding="0" cellspacing="0" class="l-table-edit"
			align="center">
			<tr>
				<td align="right" class="l-table-edit-td"><span class="l-star">*</span>&nbsp;会议室名称:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px">
					<input name="roomName" type="text" id="roomName" ltype="text"
					validate="{required:true,minlength:1,maxlength:10}" />
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td"><span class="l-star">*</span>&nbsp;容纳人数:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px">
					<input name="roomNumber" type="text" id="roomNumber" ltype="text"
					validate="{required:true,minlength:1,maxlength:10}" />
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td"><span class="l-star">*</span>&nbsp;会议室地址:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px">
					<input name="roomAddress" type="text" id="roomAddress" ltype="text"
					validate="{required:true,minlength:1,maxlength:10}" />
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td"><span class="l-star">*</span>&nbsp;联系电话:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px"><input
					name="roomPhone" type="text" id="roomPhone" ltype="text"
					validate="{required:true,number:true}" /></td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td"><span class="l-star">*</span>&nbsp;状态:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px">
					<input name="roomState" type="text" id="roomState" ltype="text"
					validate="{required:true,number:true}" />
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td">&nbsp;会议主题:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px">
					<input name="roomTopic" type="text" id="roomTopic" ltype="text" />
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td">&nbsp;备注:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px">
					<textarea name="roomRemark" type="textarea" id="roomRemark" ltype="text"
					 style="width: 180px;height: 100px"></textarea>
				</td>
				<td align="left"></td>
			</tr>	
			<!-- <tr>
				<td align="right" class="l-table-edit-td"><span class="l-star">*</span>&nbsp;所有设备:</td>
				<td align="left" class="l-table-edit-td" style="width: 180px">
					<select name="equipment" id="equipment"
					validate="{required:true}">
						<option value="1">设备1</option>
						<option value="2">设备2</option>
						<option value="3">设备3</option>
						<option value="4">设备4</option>
						<option value="5">设备5</option>
				</select>
				</td>
				<td align="left"></td>
			</tr> -->
		</table>
		<br /> <input type="button" value="重置" class="l-button l-button-test" />
		<input type="submit" value="提交" id="Button1"
			class="l-button l-button-submit" />
	</form>
	<div style="display: none">
		<!--  数据统计代码 -->
	</div>


</body>
</html>