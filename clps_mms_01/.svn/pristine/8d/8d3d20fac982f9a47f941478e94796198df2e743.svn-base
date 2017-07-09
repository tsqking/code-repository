<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="../lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet"
	type="text/css" />
<!-- <link href="../lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet"
	type="text/css" /> -->
<script src="../lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<!-- 表格 -->
<script src="../lib/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerToolBar.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerResizable.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
<!-- 布局js-->
<script src="../lib/ligerUI/js/plugins/ligerLayout.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerDrag.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<link href="../lib/tm/plugins/tinyselect.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="../lib/tm/plugins/tinyselect.js"></script>
<script type="text/javascript" src="../lib/tm/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="../lib/tm/roleInfo/roleInfo.js"></script>
<!-- 增加日历 -->
<script src="../lib/ligerUI/js/ligerui.all.js"></script>
<script src="../lib/jquery-validation/jquery.validate.min.js"></script>
<script src="../lib/jquery-validation/jquery.metadata.js"></script>
<script src="../lib/jquery-validation/messages_cn.js"></script>
<script type="text/javascript">
	$(function() {
		$("#top").ligerForm({
			 validate : true 
		});
	});
</script>
<style type="text/css">
body {
	padding: 5px;
	margin: 0;
	padding-bottom: 15px;
}

#top {
	width: 100%;
	padding-bottom: 10px;
}

.select {
	margin: 10dp;
	width: 60px;
}

#search {
	margin: 0px;
	padding: 0px;
}
</style>
</head>
<body style="padding: 6px; overflow: hidden;">
	<div id="top">
		<table>
			<tr>
		<td>角色名称：</td>
				<td><select id="condition1" name="condition1">
					<option  selected="selected">--请选择角色名称--</option>
					<c:forEach items="${list}" var="role">
						<option value="${role.name }">${role.name }</option>
					</c:forEach>
				</select></td> 
		<td>&nbsp;&nbsp;创建人名：</td>
		<td><select name="condition2" id="condition2">
					<option  selected="selected">--请选择创建人名--</option>
					<c:forEach items="${map}" var="role">
						<option value="${role.key}">${role.value}</option>
					</c:forEach>
				</select></td>
		
	
		<td>&nbsp;&nbsp;起始时间:&nbsp;</td>
				<td><input type="text" id="condition3" name="condition3"
					class="ui-datepicker" validate="{required:true}" /></td>
				
		<td>&nbsp;&nbsp;结束时间:&nbsp;</td>
				<td><input type="text" id="condition4" name="condition4"
					class="ui-datepicker" validate="{required:true}" value="${date}"/></td>
				<td>&nbsp;&nbsp;<input id="search" type="button" value="查询" onclick="search()" /></td>
			</tr>
		</table>
	</div>
	<div id="rolegrid"></div>
	<div style="display: none;"></div>

</body>

</html>