<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background-color:#ecf0f5";>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/common/image/logo.ico" />
<s:include value="../../common.jsp"></s:include>
<title>考试成绩段人数分析</title>
</head>
<body>
	<div class="box box-primary collapsed-box">
		<div class="box-header with-border" data-widget="collapse">
			<i class="fa fa-plus"></i>
			<h3 class="box-title">
				<!-- 搜索条件 -->
				<s:property value="getText('searchInfo')" />
			</h3>
		</div>
		<form id="searchForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label> <s:property value="getText('company')" />
							</label> <select class="form-control select" name="company" id="company"
								style="width: 100%;">
							</select>
						</div>

						<div class="form-group">
							<label> <!-- 时间段 --> <s:property
									value="getText('endTime')" />
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

					<div class="col-md-6">
						<div class="form-group">
							<label> <!-- 时间段 --> <s:property
									value="getText('startTime')" />
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
				</div>
			</div>
			<!-- /.box-body -->
			<div class="box-footer" style="padding: 0 0 0 0">
				<table class="table text-center" style="margin-bottom: 0px;">
					<tr>
						<td style="width: 200px;"></td>
						<td style="width: 200px;"></td>
						<td style="width: 200px;"></td>
						<td style="width: 200px;">
							<button type="button" id="resetButton"
								class="btn btn-block btn-primary btn-sm">
								<li class="glyphicon glyphicon-remove"></li>&nbsp;&nbsp;
								<!-- 取消搜索条件 -->
								<s:property value="getText('resetSearch')" />
							</button>
						</td>
						<td style="width: 200px;">
							<button type="button" id="searchButton"
								class="btn btn-block btn-success btn-sm">
								<li class="glyphicon glyphicon-ok"></li>&nbsp;&nbsp;
								<!-- 应用搜索条件 -->
								<s:property value="getText('applySearch')" />
							</button>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>

	<!-- highecharts -->

	<div id="container"
		style="margin-left: 10%; width: 80%; height: 650px;"></div>


	<!-- modal -->
	<%-- 	<div class="modal" id="batchAddModel">
		<form id="batchAddForm">
			<div class="modal-dialog" style="width: 40%; height: 20%">
				<div class="modal-content"
					style="width: 100%; height: 100%; margin-top: 25%;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">
							<s:text name="batchAddTitle" />
						</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 col-xs-12">
								<div class="form-group">
									<label><s:text name="batchPeopleList" /></label> <input
										type="file" id="batchPeopleList" name="file.file">
									<p class="help-block">
										<s:text name="batchAddHelp" />
									</p>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="closeButton" class="btn btn-primary">
							<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;
							<s:text name="closeButton" />
						</button>
						<button type="button" id="batchUpload" class="btn btn-primary">
							<i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;&nbsp;
							<s:text name="upload" />
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
 --%>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/highcharts/highcharts.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/highcharts/highcharts-3d.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/highcharts/modules/exporting.js"></script>
<script
	src="${pageContext.request.contextPath}/analysisReport/grade/${system_lang}.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/analysisReport/grade/gradeAnalysisByCom.js"></script>
</html>