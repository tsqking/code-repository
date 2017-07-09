<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<s:include value="../../../../common.jsp"></s:include>
<!-- 页面js -->
<script
	src="${pageContext.request.contextPath}/tech/plan/personalPlan/score/detailAttendance.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/tech/plan/personalPlan/${system_lang}.js"></script>
<style type="text/css">
.content {
	min-height: 0px;
}
html, body {
	min-height: 0px;
}
</style>
<title>考勤和态度成绩明细</title>
</head>
<body>
	<section class="content" id="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<div id="toolbar" class="btn-group">
		<button id="btn_go_back" type="button" class="btn btn-default">
			<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
			<s:text name="return" />
		</button>
	</div>
	<!-- 数据表格 -->
	<div class="row">
		<div class="col-xs-12">
			<div class="box box-primary" style="margin-bottom: 5px;">
				<div class="box-header">
					<h3 class="box-title">
						<s:text name="detailAttendance_80_score_list"/><!-- detailAttendance_80_score_list:考勤和态度成绩明细 -->
					</h3>
				</div>
				<input id="planId"  value="${planPaperVo.plan_id}" style="display: none" />
				<input id="student_id" name="student_id" value="${planPaperVo.student_id}" style="display: none" />
				<input id="class_id" name="class_id" value="${planPaperVo.class_id}" style="display: none" />
				<input id="course_id" name="course_id" value="${planPaperVo.course_id}" style="display: none" />
				<div class="callout callout-info"
					style="margin-bottom: 0px; margin-top: 10px; margin-left: 10px; margin-right: 10px;">
					<h4>
						<s:text name="teah.plan" />
					</h4>
					<div id="contacts"></div>
					<div style="margin-top: 5px;">
					<s:text name="detailAttendance_80_info1" /> </div><!-- detailAttendance_80_info1:考勤分数计算公式: (全程在场(100分)*次数+半程在场(50分)*次数+请假缺席(50分)*次数+无故缺席(0分)*次数)/应出勤数 -->
					<div data-toggle="tooltip" title="<s:text name="detailAttendance_80_info2"/>"><!-- detailAttendance_80_info2:态度分计算公式仅为系统参考公式,实际分数用户可以修改 -->
					<s:text name="detailAttendance_80_info3"/></div><!-- detailAttendance_80_info3:态度分数计算公式: (课堂打分*10分+...)/应出勤数 -->
				</div>
				<div class="box-body">
					<table id="table"
						class="table table-bordered table-striped table-hover"></table>
				</div>
			</div>
		</div>
	</div>
	</section>
</body>
</html>
