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
<script type="text/javascript" src="../lib/tm/departmentInfo/departmentInfo.js"></script>
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
<body style="padding:6px; overflow:hidden;">
	<div id="top">
	    部门编号：<select id="condition1">
	    		<option value="" selected="selected">--请输入部门编号--</option>
	    		<c:forEach items="${list}" var="dept">
	    			<option>${dept.id }</option>
	    		</c:forEach>
	    	</select>
	    部门名称：<select id="condition2">
	    		<option value="" selected="selected">--请输入部门名称--</option>
	    		<c:forEach items="${map}" var="dept">
	    			<option value="${dept.key}">${dept.value}</option>
	    		</c:forEach>
	    	</select>
	    <input id="search" type="button" value="查询" onclick="search()" />
	</div>
    <div id="departmentgrid"></div>
  <div style="display:none;">
</div>

</body>

</html>