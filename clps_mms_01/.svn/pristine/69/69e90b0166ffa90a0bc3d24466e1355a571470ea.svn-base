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

<script type="text/javascript" src="../lib/tm/roleInfo/addRoleInfo.js"></script>
<style type="text/css">
body {
	font-size: 12px;
}

.l-table-edit {
	margin-left: 70px;
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit {
	width: 80px;
	margin-left: 150px;
	position: relative;
	text-align: center;
	padding-bottom: 2px;
}

.l-button-reset {
	width: 80px;
	margin-left: 230px;
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
				<td align="right" class="l-table-edit-td"><span class="l-star">*</span>&nbsp;角色名称:</td>
				<td align="left" class="l-table-edit-td" style="width: 260px">
					<input 
					onkeyup="value=value.replace(/[^\u4E00-\u9FA5-\w]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5-\w]/g,''))"
					name="name" type="text" id="name" ltype="text" placeholder="请输入新增角色名称"
					validate="{required:true,minlength:1,maxlength:10}" />
				</td>
				<td align="left"></td>
			</tr>

			<tr>
				<td align="right" class="l-table-edit-td">角色描述:</td>
				<td align="left" class="l-table-edit-td" colspan="2"><textarea
						onkeyup="value=value.replace(/[^\u4E00-\u9FA5-\w]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5-\w]/g,''))"
						cols="100" rows="4" class="l-textarea" id="description"
						name="description" style="width: 400px" validate="{required:true,maxlength:255}"
						placeholder="请输入角色描述(不超过255个字符)"></textarea></td>
				<td align="left"></td>
			</tr>
		</table>
		<input type="submit" value="提交" id="Button1" class="l-button l-button-submit" />
		<input type="reset" value="重置" id="Button2" class="l-button l-button-reset" />
	</form>
	<div style="display: none">
		<!--  数据统计代码 -->
	</div>

</body>
</html>