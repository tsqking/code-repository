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
	src="${pageContext.request.contextPath}/tech/plan/personalPlan/score/setAtt.js"></script>
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
<title>输入态度分信息</title>
</head>
<body>
	<section class="content" id="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<!-- 搜索条件 -->
	<div class="box box-primary" id="box">
		<div class="box-header with-border">
			<h3 class="box-title">
				<s:text name="setAtt_18_please_input"/><!-- setAtt_18_please_input:请输入态度分数 -->
			</h3>
		</div>
		<form id="updateForm">
			<input id="planId" name="totalScoreVo.plan_id"
				value="${planPaperVo.plan_id}" style="display: none" />
			<input id="student_id" name="totalScoreVo.student_id" 
				value="${planPaperVo.student_id}" style="display: none" />
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-12 col-xs-12">
						<div class="form-group">
							<label for="attitude_score"><s:text name="setAtt_18_attitude_score"/><small><!-- setAtt_18_attitude_score:态度分 -->
							<i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-fw fa-bars"></i>
								</div>
								<input type="text" name="totalScoreVo.attitude_score" placeholder="Attitude Score"
									id="attitude_score" class="form-control" style="width: 100%;">
							</div>
						</div>
					</div>
				</div>
				<!-- /.box-body -->
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12"
						style="margin-bottom: 0px; margin-top: 10px;">
						<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
							<button type="button" id="closeButton"
								class="btn btn-block btn-primary btn-sm"
								onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">
								<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;
								<s:text name="closeButton" />
							</button>
						</div>
						<div class="col-md-3 col-xs-3">
							<button type="button" id="updateButton" onclick=""
								class="btn btn-block btn-primary btn-sm">
								<i class="fa fa-check"></i>&nbsp;&nbsp;&nbsp;
								<s:text name="save" />
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	</section>
</body>
</html>
