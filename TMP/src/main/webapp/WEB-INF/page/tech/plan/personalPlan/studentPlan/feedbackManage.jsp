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
 <script src="${pageContext.request.contextPath}/tech/plan/personalPlan/studentPlan/feedbackManage.js"></script> 
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
<title>教师反馈管理首页</title>
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
			<input id="planId"  value="${planPaperVo.plan_id}" style="display: none" />
			<input id="class_id" name="class_id" value="${planPaperVo.class_id}" style="display: none" />
			<input id="course_id" name="course_id" value="${planPaperVo.course_id}" style="display: none" />
			<!-- /.box-header -->
			<div class="box-body">
			    <div class="row">
				     <div class="col-md-6 col-xs-6">	
						<div class="form-group">
							<label><s:text name="teacher.name"/></label> 
							<div class="input-group">
							<div class="input-group-addon"><i class="fa fa-user"></i></div>
							<input type="text"  name="user_name"  id="user_name" class="form-control" 
							style="width: 100%;" placeholder="Teacher Name">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">	
						<div class="form-group">
							<label><s:text name="eval.time"/></label> 
							<div class="input-group">
							<div class="input-group-addon"><i class="fa fa-clock-o"></i></div>
							<input type="text"  name="update_time"  id="update_time" class="form-control" 
							style="width: 100%;" placeholder="Evaluation Time">
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
					<h3 class="box-title"><s:text name="rea.eval.list.title"/></h3>
				</div>
				<div class="callout callout-info" style="margin-bottom: 0px;margin-top: 10px;margin-left: 10px;margin-right: 10px;">
	                <h4><s:text name="teah.plan"/></h4>
	                <div id="contacts">
	                </div>
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