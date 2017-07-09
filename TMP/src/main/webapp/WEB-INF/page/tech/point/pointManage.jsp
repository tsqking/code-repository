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
	src="${pageContext.request.contextPath}/tech/point/pointManage.js"></script>
<!-- 页面js国际化 -->
<script src="${pageContext.request.contextPath}/tech/point/${session.system_lang}.js"></script>
<title>知识点管理</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<!-- 搜索条件 -->
		<div class="box box-primary collapsed-box">
			<div class="box-header with-border" data-widget="collapse">
				<i class="fa fa-plus"></i>
				<h3 class="box-title" ><s:text name="searchTitle"/></h3>
			</div>
			<form id="searchForm">
				<div class="box-body">
					<div class="row">
						<div class="col-md-6 col-xs-6">
							<div class="form-group">
				            	<label><s:text name="point.name"/></label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-fw fa-tag"></li></span>
				                	<input type="text" name="pointVo.name" id="name" class="form-control" placeholder="Point Name">
				              	</div>
				            </div>
				        </div>
						<div class="col-md-6 col-xs-6">
							<div class="form-group">
								<label><s:text name="enable"/></label> <select class="form-control select"
									name="pointVo.enable" id="enable" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4 col-xs-4">
							<div class="form-group">
								<label><s:text name="point.firstSkill"/></label> <select class="form-control select"
									name="pointVo.first_skill" id="first_skill"
									style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-4 col-xs-4">
							<div class="form-group">
								<label><s:text name="point.secondSkill"/></label> <select class="form-control select"
									name="pointVo.second_skill" id="second_skill"
									style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-4 col-xs-4">
							<div class="form-group">
								<label><s:text name="point.thirdSkill"/></label> <select class="form-control select"
									name="pointVo.third_skill" id="third_skill"
									style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
						<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
							<button type="button" id="resetButton"
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="resetButton"/></button>
						</div>
						<div class="col-md-3 col-xs-3">
							<button type="button" id="searchButton"
									class="btn btn-block btn-success btn-sm"><i class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;<s:text name="searchButton"/></button>
						</div>
					</div>
				</div>
			</form>
		</div>
		<!-- 数据表格 -->
		<div class="row">
			<div class="col-xs-12">
				<div class="box box-primary" style="margin-bottom: 5px;">
					<div class="box-header">
						<h3 class="box-title" ><s:text name="point.pointList"/></h3>
					</div>
					<div class="row">
						<div class="col-md-6 col-xs-6 btn-group-vertical">
							<button type="button" onclick="addNewPoint()" class="btn btn-block btn-primary btn-sm">
									<i class="fa fa-plus-circle"></i>&nbsp;&nbsp;&nbsp;<s:text name="point.addNewPoint"/></button>
						</div>
						<div class="col-md-6 col-xs-6 btn-group-vertical">
							<button type="button" onclick="exportTopic()" class="btn btn-block btn-primary btn-sm">
									<i class="fa fa-download"></i>&nbsp;&nbsp;&nbsp;<s:text name="point.exportTopic"/></button>
						</div>
					</div>
					<div class="box-body">
						<table id="pointTable"
							class="table table-bordered table-striped table-hover">
							<thead>
								<tr>
									<th><s:text name="point.name"/></th>
									<%-- <th><s:text name="point.description"/></th> --%>
									<th><s:text name="point.order"/></th>
									<th><s:text name="point.firstSkill"/></th>
									<th><s:text name="point.secondSkill"/></th>
									<th><s:text name="point.thirdSkill"/></th>
									<th><s:text name="enable"/></th>
									<th><s:text name="updateTime"/></th>
									<th><s:text name="updatePerson"/></th>
									<th><s:text name="option"/></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		
	</section>
</body>
</html>




