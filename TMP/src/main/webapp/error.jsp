<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/bootstrap/css/bootstrap.min.css">
<!-- jQuery 2.1.4 -->
<script src="${pageContext.request.contextPath}/common/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<!-- Theme style -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/dist/css/AdminLTE.min.css">
<!-- Bootstrap 3.3.5 -->
<script src="${pageContext.request.contextPath}/common/bootstrap/js/bootstrap.min.js"></script>


<title>Error</title>
</head>
<body class="hold-transition skin-blue fixed sidebar-mini">
	<br><br><br><br><br>
	<div class="error-page">
		<h2 class="headline text-yellow">Error</h2>
		<div class="error-content">
			<h3>
				<i class="fa fa-warning text-yellow"></i> Oops! There is a error.
			</h3>
			<p>
				Please try again or contact the administrator to fix the problem. 
			</p>
		</div>
	</div>
	<%-- <s:include value="common.jsp"></s:include> --%>
	<script>
		$(document).ready(function() {
			
		});
	</script>
</body>
</html>