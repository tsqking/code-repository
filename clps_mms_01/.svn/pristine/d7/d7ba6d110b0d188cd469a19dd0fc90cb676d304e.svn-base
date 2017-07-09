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

<script type="text/javascript" src="../lib/tm/userInfo/addUserInfo.js"></script>
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
				<td align="right" class="l-table-edit-td"><span class="l-star">*</span>&nbsp;用户姓名:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px">
					<input name="name" type="text" id="name" ltype="text"
					validate="{required:true,minlength:3,maxlength:10}" />
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td"><span class="l-star">*</span>&nbsp;用户昵称:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px">
					<input name="nickname" type="text" id="nickname" ltype="text"
					validate="{required:true,minlength:3,maxlength:10}" />
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td"><span class="l-star">*</span>&nbsp;用户密码:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px">
					<input name="password" type="text" id="password" ltype="text"
					validate="{required:true,minlength:3,maxlength:10}" />
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td"><span class="l-star">*</span>&nbsp;重复密码:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px">
					<input name="rePassword" type="text" id="rePassword" ltype="text"
					validate="{required:true,minlength:3,maxlength:10,equalTo:'#password'}" />
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td" valign="top"><span class="l-star">*</span>&nbsp;用户性别:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px">
					<input id="rbtnl_0" type="radio" name="gender" value="男"
					checked="checked" /><label for="rbtnl_0">男</label> <input
					id="rbtnl_1" type="radio" name="gender" value="女" /><label
					for="rbtnl_1">女</label>
				</td>
				<td align="left"></td>

			</tr>
			<tr>
				<td align="right" class="l-table-edit-td"><span class="l-star">*</span>&nbsp;电子邮箱:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px"><input
					name="email" type="text" id="email" ltype="text"
					validate="{required:true,email:true}" /></td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td"><span class="l-star">*</span>&nbsp;手机号码:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px"><input
					name="mobNum" type="text" id="mobNum" ltype="text"
					validate="{required:true,number:true}" /></td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td"><span class="l-star">*</span>&nbsp;所属部门:</td>
				<td align="left" class="l-table-edit-td" style="width: 180px">
					<select name="department" id="department"
					validate="{required:true}">
						<option value="1">开发部</option>
						<option value="2">研发中心</option>
						<option value="3">销售部</option>
						<option value="4">市场部</option>
						<option value="5">顾问组</option>
				</select>
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td"><span class="l-star">*</span>&nbsp;用户职位:</td>
				<td align="left" class="l-table-edit-td" style="width: 180px">
					<select name="position" id="position" validate="{required:true}">
						<option value="1">项目经理</option>
						<option value="2">开发人员</option>
						<option value="3">接待人员</option>
						<option value="4">实习生</option>
						<option value="5">HR</option>
				</select>
				</td>
				<td align="left"></td>
			</tr>

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