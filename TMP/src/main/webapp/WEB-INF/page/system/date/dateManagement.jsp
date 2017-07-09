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
<!-- 页面js -->
<script
	src="${pageContext.request.contextPath}/system/date/dateManagement.js"></script>
<!-- js 国际化 -->
<script
	src="${pageContext.request.contextPath}/system/date/${session.system_lang}.js"></script>
<title>日期管理首页</title>
</head>
<body>
	<section class="content" style="background-color:#ecf0f5;">
	<div class="row">
		<div class="col-md-2 col-md-offset-1">
        	<div class="box box-solid"">
        		<div class="box-header with-border">
            		<h3 class="box-title"><s:text name="tip"/></h3>
            		<div class="box-tools pull-right">
                		<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              		</div>
           		</div>
            	<div class="box-body">
              		<s:text name="tipContent"/>
            	</div>
          	</div>
        </div>
		<div class="col-md-7" style="height:700px;" >
			<div class="box box-primary">
				<div class="box-body no-padding">
					<div id="calendar"></div>
				</div>
			</div>
		</div>
	</div>
	</section>
</body>
</html>




