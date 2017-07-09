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
<script src="${pageContext.request.contextPath}/tech/course/courseSearchAndCust.js"></script>
<!-- 页面js国际化 -->
<script src="${pageContext.request.contextPath}/tech/course/${session.system_lang}.js"></script>
<title>课程查询与定制</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" style="padding-top: 10%;padding-bottom: 5px;background-color:#ecf0f5;">
		<div data-toggle="tooltip" title="<s:text name="course.searchAndCustTip"/>">
			<form class="form-horizontal" id="form">
				<div class="form-group">
					<label for="no" class="col-md-2 col-xs-2 col-md-offset-1 col-xs-offset-1 control-label"><s:text name="course.no"/></label>
					<div class="col-md-6 col-xs-6">
						<input type="text" name="courseVo.no" id="no" class="form-control" placeholder="Course No.">
					</div>
                </div>
				<div class="form-group">
					<label for="no" class="col-md-2 col-xs-2 col-md-offset-1 col-xs-offset-1 control-label"><s:text name="course.name"/></label>
					<div class="col-md-6 col-xs-6">
						<select class="form-control select" name="courseVo.name" id="name" style="width: 100%;">
							<option value=""></option>
						</select>
					</div>
                </div>
			</form>
		</div>
		
		<div class="col-md-12 col-xs-12" style="margin-top:40px;">
			<div class="col-md-2 col-xs-2 col-md-offset-4 col-xs-offset-4">
				<button type="button" id="knowledgeUpdateButton"
						class="btn btn-block btn-primary btn-sm"><i class="fa fa-cog"></i>&nbsp;&nbsp;&nbsp;<s:text name="course.coursePointUpdateButton"/></button>
			</div>
			<div class="col-md-2 col-xs-2">
				<button type="button" id="knowledgeCheckButton"
						class="btn btn-block btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;<s:text name="course.coursePointCheckButton"/></button>
			</div>
		</div>
	</section>
</body>
</html>




