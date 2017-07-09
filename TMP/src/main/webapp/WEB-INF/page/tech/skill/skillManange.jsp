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
	src="${pageContext.request.contextPath}/tech/skill/skillManage.js"></script>
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/tech/skill/${system_lang}.js"></script>
<title>技能管理首页</title>
</head>
<body>
	<!-- Main content -->
	<section class="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<!-- 搜索条件 -->
	<div class="box box-primary collapsed-box">
		<div class="box-header with-border" data-widget="collapse">
			<i class="fa fa-plus"></i>
			<h3 class="box-title" ><s:text name="searchTitle"/></h3>
		</div>
		<form id="searchForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label><s:text name="skill.firstSkill"/></label> <select class="form-control select" class="skillFirst"
								name="skillVo.skillFirst" id="skillFirst"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
						<div class="form-group">
							<label><s:text name="skill.thirdSkill"/></label> <select class="form-control select" class="skillThird"
								name="skillVo.skillThird" id="skillThird"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="col-md-6">	
						<div class="form-group">
							<label><s:text name="skill.secondSkill"/></label> <select class="form-control select" class="skillSecond"
								name="skillVo.skillSecond" id="skillSecond"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>
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
								class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="resetButton"/></button>
						</td>
						<td style="width: 200px;">
							<button type="button" id="searchButton"
								class="btn btn-block btn-success btn-sm"><i class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;<s:text name="searchButton"/></button>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<!-- /.box --> <!-- 数据表格 -->
	<div class="row">
		<div class="col-xs-12">
			<div class="box box-primary" style="margin-bottom: 5px;">
				<div class="box-header">
					<h3 class="box-title"><s:text name="skill.skillList"/></h3>
				</div>
				<table class="table text-center"
					style="margin-bottom: 0px; width: 100%;">
					<tr>
						<td style="width: 100%;">
							<button type="button" onclick="add('0')" class="btn btn-block btn-primary btn-sm">
								<i class="fa fa-plus-circle"></i>&nbsp;&nbsp;&nbsp;<s:text name="skill.addNewFirstSkill"/></button>
						</td>
					</tr>
				</table>
				<div class="box-body">
					<table id="skillTable"
						class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
								<th><s:text name="skill.skillName"/></th>
								<th><s:text name="skill.skillType"/></th>
								<th><s:text name="skill.parentSkill"/></th>
								<th><s:text name="skill.skillLevel"/></th>
								<th><s:text name="order"/></th>
								<th><s:text name="skill.enable"/></th>
								<%-- <th><s:text name="skill.skillDescription"/></th> --%>
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