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
<link href="../lib/tm/plugins/tinyselect.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../lib/tm/plugins/tinyselect.js"></script>
<script type="text/javascript" src="../lib/tm/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="../lib/tm/equipmentInfo/equipment.js"></script>
<!-- 增加日历 -->
<script src="../lib/ligerUI/js/ligerui.all.js"></script>
<script src="../lib/jquery-validation/jquery.validate.min.js"></script>
<script src="../lib/jquery-validation/jquery.metadata.js"></script>
<script src="../lib/jquery-validation/messages_cn.js"></script>

<script type="text/javascript">
        $(function ()
        {
            $("#top").ligerForm({
                validate: true
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
#search {
	margin: 0px;
	padding: 0px;
}
</style>
</head>
<body style="padding: 2px">
	<div id="top">
	<table>
	<tr>
	<td>
		<a>设备名称:</a> <select id="condition">
						<option selected="selected" value="&nbsp"></option>
						<c:forEach var="name" items="${names}">
							<option value="${name}">${name}</option>
						</c:forEach>
					  </select>
	</td>
	<td>
		<a>状态:</a> <select id="condition2">
						<option value="0">空闲</option>
						<option value="1">占用</option>
					</select>
	</td>
	<td>
		<a>设备类型:</a> <select id="condition3">
						<option selected="selected" value="&nbsp"></option>
						<c:forEach var="type" items="${list}">
							<option value="${type}">${type}</option>
						</c:forEach>
						</select>	
	</td>
	<td>
		<a>起始时间：</a> <input id="condition4"  type="text" class="ui-datepicker" validate="{required:true}"/>
	</td>
	<td>
		<a>结束时间：</a> <input id="condition5" type="text" class="ui-datepicker" validate="{required:true}" value="${endDate}"/>				
	</td>
	<td>
		<input type="button" value="查询" id="search" onclick="search()" />
	</td>
	</tr>
	</table>
	</div>
	<div id="equipmentgrid"></div>

	<div style="display: none;"></div>
</body>
</html>