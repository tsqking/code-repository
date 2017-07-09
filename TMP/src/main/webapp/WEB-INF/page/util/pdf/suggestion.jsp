<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background-color:#ecf0f5";>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/common/image/logo.ico" />
<s:include value="../../common.jsp"></s:include>
<title>建议浏览方式</title>
</head>
<body>
	<!-- Main content -->
    <section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
    <div class="box box-primary" style="margin-bottom: 0px;">
        <div class="box-body">
			<div class="row">
				<div class="col-md-12 col-xs-12">
					<s:text name="viewAttachTip"/>
				</div>
			</div>
        </div>
	</div>     
    </section>
</body>
</html>
