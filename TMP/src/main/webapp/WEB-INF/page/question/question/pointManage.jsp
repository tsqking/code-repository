<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background-color:#ecf0f5">
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
<!-- js页面 -->
<script
	src="${pageContext.request.contextPath}/question/question/pointManage.js"></script>
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/question/question/${system_lang}.js"></script>
<title>标签管理</title>
</head>
<body>
	<!-- Main content -->
	<section class="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<!-- 搜索条件 -->
	<div class="box box-primary">
			<div class="box-header with-border" data-widget="collapse">
				<i class="fa fa-minus"></i>
				<h3 class="box-title" ><s:text name="selectPoint"/></h3>
			</div>
			<form id="addForm">
				<div class="box-body" class="collapse in">
					<div class="row">
						<div class="col-md-6 col-xs-6">
							<div class="form-group">
								<label for="first_skill"><s:text name="skill.firstSkill"/></label> <select class="form-control select"
									name="pointVo.first_skill" id="first_skill_id"
									style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-6 col-xs-6">
							<div class="form-group">
								<label for="second_skill"><s:text name="skill.secondSkill"/></label> <select class="form-control select"
									name="pointVo.second_skill" id="second_skill_id"
									style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 col-xs-6">
							<div class="form-group">
								<label for="third_skill"><s:text name="skill.thirdSkill"/></label> <select class="form-control select"
									name="pointVo.third_skill" id="third_skill_id"
									style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-6 col-xs-6">
							<div class="form-group">
								<label for="pointName"><s:text name="pointName"/></label> <select class="form-control select"
									name="pointVo.name" id="point_id"
									style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
					</div>
					<div class="box-footer" style="padding: 0 0 0 0">
						<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
							<div class="col-md-3 col-xs-3 col-md-offset-9">
								<button type="button" id="selectPoint"
										class="btn btn-block btn-primary btn-sm"><i class="fa fa-shopping-cart"></i>&nbsp;&nbsp;&nbsp;<s:text name="select"/></button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	<!-- /.box --> <!-- 数据表格 -->
	<div class="row">
		<div class="col-xs-12">
			<div class="box box-primary" style="margin-bottom: 5px;">
				<div class="box-body">
					<table id="pointTable"
						class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
								<th><s:text name="skill.firstSkill"/></th>
								<th><s:text name="skill.secondSkill"/></th>
								<th><s:text name="skill.thirdSkill"/></th>
								<th><s:text name="pointName"/></th>
								<th><s:text name="option"/></th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="box-footer" style="padding: 0 0 0 0">
		<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
			<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
				<button type="button" id="closeWindow" onclick="javascript: parent.layer.close(index);"
						class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/></button>
			</div>
			<div class="col-md-3 col-xs-3">
				<button type="button" id="confirm"
						class="btn btn-block btn-primary btn-sm"><i class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;<s:text name="addCommitButton"/></button>
			</div>
		</div>
	</div>
	</section>
</body>
</html>