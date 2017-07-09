<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background-color:#ecf0f5";>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/common/image/logo.ico" />
<s:include value="../../../../common.jsp"></s:include>
<!-- 页面js -->
 <script src="${pageContext.request.contextPath}/tech/plan/personalPlan/teacherPlan/paperManage.js"></script> 
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/tech/plan/personalPlan/${system_lang}.js"></script>
<style type="text/css">
	.content {
    	min-height: 0px;
	}
	html, body {
    	min-height: 0px;
	}
</style>
<title>试卷管理首页</title>
</head>
<body>
   <section class="content" id="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<!-- 搜索条件 -->
	<div class="box box-primary collapsed-box" id="box">
		<div class="box-header with-border" data-widget="collapse">
			<i class="fa fa-plus"></i>
			<h3 class="box-title" ><s:text name="searchTitle"/></h3>
		</div>
		<form id="searchForm">
			<input id="planId" name="plan_id" value="${planPaperVo.plan_id}" style="display: none" />
			<input id="class_id" value="${planPaperVo.class_id}" style="display: none" />
			<input id="course_id" value="${planPaperVo.course_id}" style="display: none" />
			<!-- /.box-header -->
			<div class="box-body">
			    <div class="row">
				     <div class="col-md-4 col-xs-4">
						<div class="form-group">
							<label><s:text name="paper.pro"/></label> 
							<select class="form-control select" class=""
								name="paper_type" id="paper_type" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					 </div>
					<div class="col-md-4 col-xs-4">	
						<div class="form-group">
							<label><s:text name="now.status"/></label> 
							<select class="form-control select" class=""
								name="status" id="status" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="col-md-4 col-xs-4">	
						<div class="form-group">
							<label><s:text name="paper.number"/></label> 
							<div class="input-group">
							<div class="input-group-addon"><i class="fa fa-crosshairs"></i></div>
							<input type="text"  name="paper_number"  id="paper_number" class="form-control" 
							style="width: 100%;" placeholder="Paper Number">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
				     <div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label><s:text name="start.time"/></label> 
							<div class="input-group">
							<div class="input-group-addon"><i class="fa fa-clock-o"></i></div>
							<input type="text"  name="paper_start_time"  id="paper_start_time" class="form-control" 
							style="width: 100%;" placeholder="Open time">
							</div>
						</div>
					 </div>
					<div class="col-md-6 col-xs-6">	
						<div class="form-group">
							<label><s:text name="end.time"/></label> 
							<div class="input-group">
							<div class="input-group-addon"><i class="fa fa-clock-o"></i></div>
							<input type="text"  name="paper_end_time"  id="paper_end_time" class="form-control" 
							style="width: 100%;" placeholder="Close time">
							</div>
						</div>
					</div>
				</div>
			<!-- /.box-body -->
			<div class="box-footer" style="padding: 0 0 0 0">
				<div class="col-md-12 col-xs-12" style="margin-bottom: 0px;margin-top: 10px;">
					<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
						<button type="button" id="resetButton"class="btn btn-block btn-primary btn-sm">
							<i class="fa fa-reply"></i>&nbsp;&nbsp;&nbsp;<s:text name="reset"/>
					</div>
					<div class="col-md-3 col-xs-3">
						<button type="button" id="searchButton"class="btn btn-block btn-primary btn-sm">
							<i class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;<s:text name="rearch"/>
					</div>
				</div>
			</div>
			</div>
		</form>
	</div>
	<div id="toolbar" class="btn-group">
      <button id="btn_add" type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        	<s:text name="add" />
      </button>
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
					<h3 class="box-title"><s:text name="paper.test.list.title"/></h3>
				</div>
				<div class="box-body">
					<table id="table" class="table table-bordered table-striped table-hover"></table>	
				</div>
			</div>
		</div>
	</div>
	</section>
</body>
</html>