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
	src="${pageContext.request.contextPath}/system/option/optionManagement.js"></script>
<!-- 页面js国际化 -->
<script
	src="${pageContext.request.contextPath}/system/option/optionManagement_${session.system_lang}.js"></script>
<title>选项框管理首页</title>
</head>
<body>
	<!-- Main content -->
	<section class="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<!-- 搜索条件 -->
	<div class="box box-primary collapsed-box">
		<div class="box-header with-border" data-widget="collapse">
			<i class="fa fa-plus"></i>
			<h3 class="box-title"><s:text name="searchTitle" /></h3>
		</div>
		<form id="searchForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label><s:text name="option.optionName" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text"
									name="searchOptionName" id="searchOptionName"
									class="form-control" placeholder="Option Value">
							</div>
						</div>
						<div class="form-group">
							<label><s:text name="option.optionGroup" /></label> <select class="form-control select"
								name="searchOptionGroups" id="searchOptionGroups"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-md-6">
						<div class="form-group">
							<label><s:text name="option.optionState" /></label> <select class="form-control select"
								name="searchOptionStatus" id="searchOptionStatus"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
						<div class="form-group">
							<label><s:text name="option.optionType" /></label> <select class="form-control select"
								name="searchOptionType" id="searchOptionType"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.box-body -->
			<div class="box-footer" style="padding: 0 0 0 0">
				<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
					<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
						<button type="button" id="resetButton"
								class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="resetButton" /></button>
					</div>
					<div class="col-md-3 col-xs-3">
						<button type="button" id="searchButton"
								class="btn btn-block btn-success btn-sm"><i class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;<s:text name="searchButton" /></button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- /.box --> <!-- 数据表格 -->
	<div class="row">
		<div class="col-xs-12">
			<div class="box box-primary" style="margin-bottom: 5px;">
				<div class="box-header">
					<h3 class="box-title" ><s:text name="option.optionList" /></h3>
				</div>
				<table class="table text-center"
					style="margin-bottom: 0px; width: 100%;">
					<tr>
						<td style="width: 100%;">
							<button type="button" onclick="add('0','')" class="btn btn-block btn-primary btn-sm">
								<i class="fa fa-plus-circle"></i>&nbsp;&nbsp;&nbsp;<s:text name="option.addNewOptionGroup" /></button>
						</td>
					</tr>
				</table>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="optionTable"
						class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
								<th><s:text name="option.optionName" /></th>
								<th><s:text name="option.optionType" /></th>
								<th><s:text name="option.optionKey" /></th>
								<th><s:text name="option.optionBelong" /></th>
								<th><s:text name="option.optionOrder" /></th>
								<th><s:text name="option.optionState" /></th>
								<th><s:text name="updateTime" /></th>
								<th><s:text name="updatePerson" /></th>
								<th><s:text name="option" /></th>
							</tr>
						</thead>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>
	</section>
</body>
</html>




