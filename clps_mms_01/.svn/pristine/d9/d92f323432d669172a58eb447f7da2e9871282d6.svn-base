<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="../lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet"
	type="text/css" />
<script src="../lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="../lib/ligerUI/js/core/base.js" type="text/javascript"></script>

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
<script type="text/javascript" src="../lib/tm/logInfo/equLog.js"></script>

<!-- 增加日历 -->
<script src="../lib/ligerUI/js/ligerui.all.js"></script>
<script src="../lib/jquery-validation/jquery.validate.min.js"></script>
<script src="../lib/jquery-validation/jquery.metadata.js"></script>
<script src="../lib/jquery-validation/messages_cn.js"></script>



<script src="lib/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$("#form1").ligerForm({
			validate : true

		});
	});
	//设置最终时间显示，格式修改 
	onload = function() {
		setInterval(function() {
			var date = new Date();
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			month = month < 10 ? "0" + month : month;
			var date1 = date.getDate();
			date1 = date1 < 10 ? "0" + date1 : date1;
			var hour = date.getHours();
			hour = hour < 10 ? "0" + hour : hour;
			var minute = date.getMinutes();
			minute = minute < 10 ? "0" + minute : minute;
			var second = date.getSeconds();
			second = second < 10 ? "0" + second : second;
			condition2.value = year + "-" + month + "-" + date1  + " " + hour + ":"
					+ minute /* + ":" + second  */;
		}, 1000);
	}
</script>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

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
	width: 30px;
}

#search {
	margin: 0px;
	padding: 0px;
}

#button {
	background: url("images/timg.jpg");
}

input {padding; 5px;
	width: 30px;
	background: background: url("images/timg.jpg");
}

#condition4 {
	width: 30px;
}
</style>
</head>
<body style="padding: 2px; position: relative;">
	<!-- 设备名称：<input id="condition1" type="text" />
	日志类型：<input id="condition2" type="text" />  -->
	<div id="form1">
		<div class="fields">
			<table style="margin: 0px 0px">
				<tr>
					<td><span>日志类型：</span>&nbsp;</td>
					<td><select id="condition3">
						<option selected="selected">--请输入操作类型--</option>
						<c:forEach var="equipLog" items="${mapType}">
							<option value="${equipLog.key}">${equipLog.value}</option>
						</c:forEach>
				</select></td>
					<td>&nbsp;&nbsp;日志内容：</td>
					<td><input type="text" id="condition4" style="width: 150px"
						placeholder="请输入关键字"></td>
					<td><label for="date1">&nbsp;&nbsp;修改时间：</label></td>
					<td><input id="condition1" name="birthDay" type="text"
						class="ui-datepicker" value="2017-2-12" /></td>

					<td><label for="date1">&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
					<td><input id="condition2" name="birthDay1" type="text" 
						class="ui-datepicker" /></td>

					<td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="查询日志"
						onclick="search()" id="button" /></td>
				</tr>
			</table>
		</div>
	</div>
	<div id="logid"></div>

	<div style="display: none;"></div>
</body>
</html>