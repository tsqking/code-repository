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
<script src="${pageContext.request.contextPath}/tech/plan/planDetail.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/tech/plan/${system_lang}.js"></script>
<title>计划详细信息界面</title>
</head>
<!-- onload="window.print();" -->
<body>
	<!-- Main content -->
	<section class="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<div class="box box-primary" style="margin-bottom: 0px;">
		<form id="searchForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-4 col-xs-4">
						<div class="form-group">
							<!-- 课程名称 -->
							<label>
								<!-- 课程名称 -->
								<s:property value="getText('plan.courseName')" />
							</label> <select class="form-control select" name="course_id"
								id="course_id" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="col-md-4 col-xs-4">
						<div class="form-group">
							<!-- 班级名称 -->
							<label>
								<!-- 班级名称 -->
								<s:property value="getText('plan.className')" />
							</label> <select class="form-control select" name="class_id"
								id="class_id" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="col-md-4 col-xs-4" data-toggle="tooltip" title="<s:text name="plan.locationTip"/>" data-placement="bottom">
						<div class="form-group">
							<label><s:property value="getText('plan.location')"/></label>
							<select class="form-control select" name="location"
								id="location" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<!-- 开始时间 -->
							<label>
								<!-- 开始时间 -->
								<s:property value="getText('plan.starttime')" />
							</label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control pull-right"
									name="start_time" id="start_time">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<!-- 结束时间 -->
							<label>
								<!-- 结束时间 -->
								<s:property value="getText('plan.endtime')" />
							</label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control pull-right"
									name="end_time" id="end_time">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 col-xs-12">
						<div class="col-md-3 col-xs-3 col-md-offset-9 col-xs-offset-9">
							<button type="button" id="closeIframe"
								class="btn btn-block btn-primary btn-sm">
								<!-- 关闭详细信息窗口 -->
								<li class="glyphicon glyphicon-remove"></li>&nbsp;&nbsp;
								<!-- 关闭详细信息窗口 -->
								<s:property value="getText('plan.closeDetailWin')" />
							</button>
						</div>
					</div>
					<div class="col-md-12 col-xs-12">
						<form id="addSkillPointForm">
							<div class="box-header">
								<span>
									<!-- 课程详细安排 -->
									<s:property value="getText('plan.courseDetailList')" />
								</span>
							</div>
							<div class="box-body" id="treeviewIdDiv">
								<div id="treeview" class="treeview"></div>
							</div>
							<div class="box-footer" style="padding: 0 0 0 0">
								<div class="col-md-12 col-xs-12">
									<div class="col-md-4 col-xs-4"></div>
									<div class="col-md-4 col-xs-4">
										<select class="form-control select"
											id="select-expand-all-levels" style="margin-top: 10px">
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
										</select>
									</div>
									<div class="col-md-2 col-xs-2" style="margin-top: 15px;">
										<button type="button" class="btn btn-block btn-default btn-sm"
											id="btn-expand-all">
											<li class="fa fa-expand"></li>
										</button>
									</div>
									<div class="col-md-2 col-xs-2" style="margin-top: 15px;">
										<button type="button" class="btn btn-block btn-default btn-sm"
											id="btn-collapse-all">
											<li class="fa fa-compress"></li>
										</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
				<!-- /.row -->
			</div>
			<!-- /.box-body -->
		</form>
	</div>
	</section>
	<!-- /.content -->
</body>
</html>

<input type="hidden" value="${planVo.id}" id="planVoId" />
<input type="hidden" value="${planVo.course_id}" id="course_id_hidden" />
<input type="hidden" value="${planVo.class_id}" id="class_id_hidden" />
<input type="hidden" value="${planVo.start_time}" id="start_time_hidden" />
<input type="hidden" value="${planVo.end_time}" id="end_time_hidden" />
<input type="hidden" value="${planVo.location}" id="location_id_hidden" />
