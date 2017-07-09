<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改菜单</title>
<link rel="stylesheet" href="css/rl_exp.css" />
<link href="../lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="../lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet"
	type="text/css" />

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
				<td align="right" class="l-table-edit-td"><span class="l-star">*</span>&nbsp;菜单名称:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px">
					<input name="mnu_name" type="text" id="mnu_name" ltype="text"
					validate="{required:true,minlength:3,maxlength:20}" />
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td"><span class="l-star"></span>&nbsp;菜单地址:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px">
					<input name="mnu_url" type="text" id="mnu_url" ltype="text" />
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td"><span class="l-star"></span>&nbsp;父菜单:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px">
					<select name="mnu_parent_id" type="text" id="mnu_parent_id"
					ltype="text">
						<option value="" selected="selected">&nbsp;</option>
						<c:forEach var="am" items="${allMenu }">
							<option value="${am.mnu_id }">${am.mnu_name }</option>
						</c:forEach>
				</select>
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td"><span class="l-star"></span>&nbsp;根菜单:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px">
					<select name="mnu_root_id" type="text" id="mnu_root_id"
					ltype="text">
						<option value="" selected="selected">&nbsp;</option>
						<c:forEach var="am" items="${allMenu }">
							<option value="${am.mnu_id }">${am.mnu_name }</option>
						</c:forEach>
				</select>
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td"><span class="l-star"></span>&nbsp;图标地址:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px">
					<input name="mnu_logo_url" type="text" id="mnu_logo_url"
					ltype="text" />
				</td>
				<td><a href="javascript:void(0);" id="rl_exp_btn">选择图标</a></td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td"><span class="l-star">*</span>&nbsp;菜单描述:</td>
				<td align="left" class="l-table-edit-td" style="width: 160px">
					<textarea name="mnu_desc" type="text" id="mnu_desc" ltype="text"
						validate="{required:true,minlength:3,maxlength:50}"></textarea>
				</td>
				<td align="left"></td>
			</tr>
		</table>
		<br /> <input type="submit" value="提交" id="Button1"
			class="l-button l-button-submit" /> <input type="reset" value="重置"
			class="l-button l-button-test" />
	</form>
	<div class="rl_exp" id="rl_bq" style="display: none;">
		<ul class="rl_exp_tab clearfix">
			<li><a href="javascript:void(0);" class="selected">默认</a></li>
		</ul>
		<ul class="rl_exp_main clearfix rl_selected"></ul>
		<a href="javascript:void(0);" class="close">×</a>
	</div>

	<script src="../lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/rl_exp.js"></script>
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
	<script type="text/javascript" src="../lib/tm/plugins/jquery.cookie.js"></script>
	<script type="text/javascript"
		src="../lib/tm/menuinfo/updateMenuInfo.js"></script>
	<div style="display: none">
		<!--  数据统计代码 -->
	</div>


</body>
</html>