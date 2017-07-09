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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/analysisReport/summary/summaryAnalysis.js"></script>
<script
	src="${pageContext.request.contextPath}/analysisReport/summary/${system_lang}.js"></script>
<title>分析总表</title>
</head>
<body>
	<!-- 搜索条件 -->
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
							<label> <!-- 科目 --> <s:property
									value="getText('subject')" />
							</label> <select class="form-control select" name="subject" id="subject"
								style="width: 100%;">
								<!-- disabled 表示不可编辑 -->
								<option value=""></option>
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
						<!-- /.form-group -->
					</div>
					<div class="col-md-6">
						<!-- /.col -->
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
				<!-- /.form-group -->
			</div>
			<!-- /.col -->
	</form>
	<!-- /.row -->
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

	<div id="sumToolbar">
		<h4 class="text text-info"><s:text name="summaryTblHead" /></h4>
	</div>
	<!-- 数据表格 -->
	<div class="row">
		<div class="col-xs-12">
			<div class="box box-primary" style="margin-bottom: 5px;">
				<div class="box-header callout callout-info">
					<div class="col-lg-8 " style="font-size:large;">
						<div class="col-xs-2">
							<s:text name="male" />
							<span id="maleNum"></span>
						</div>
						<div class="col-xs-2">
							<s:text name="female" />
							<span id="femaleNum"></span>
						</div>
						<div class="col-xs-2">
							<s:text name="averageAge" />
							<span id="avgAge"></span>
						</div>
						<div class="col-xs-2">
							<s:text name="subject" />
							<span id="subSpan"></span>
						</div>
					</div>
				</div>
				<div class="box-body">
					<table id="summaryTbl"
						class="table table-bordered table-striped table-hover">
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/analysisReport/summary/summaryAnalysis.js"></script>
<script
	src="${pageContext.request.contextPath}/analysisReport/summary/${system_lang}.js"></script>

</html>