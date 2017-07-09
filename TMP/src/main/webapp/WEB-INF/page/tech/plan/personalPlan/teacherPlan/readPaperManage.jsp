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
 <script src="${pageContext.request.contextPath}/tech/plan/personalPlan/teacherPlan/readPaperManage.js"></script> 
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
<title>阅卷首页</title>
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
			<input id="planPaperId" name="plan_paper_id" value="${planPaperVo.id}" style="display: none" />
			<input id="paper_id" value="${planPaperVo.paper_id}" style="display: none" />
			<input id="class_name" value="${planPaperVo.class_name}" style="display: none" />
			<!-- /.box-header -->
			<div class="box-body">
			    <div class="row">
				    <div class="col-md-6 col-xs-6">	
						<div class="form-group">
							<label><s:text name="student.name"/></label> 
							<div class="input-group">
							<div class="input-group-addon"><i class="fa fa-child"></i></div>
							<input type="text"  name="student_name"  id="student_name" class="form-control" 
							style="width: 100%;" placeholder="Student Name">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">	
						<div class="form-group">
							<label><s:text name="paper.status"/></label> 
							<select class="form-control select" class=""
								name="status" id="status" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>	
				</div>
				<div class="row">
				    <div class="col-md-6 col-xs-6">	
						<div class="form-group">
							<label><s:text name="paper.open.time"/></label> 
							<div class="input-group">
							<div class="input-group-addon"><i class="fa fa-clock-o"></i></div>
							<input type="text"  name="start_time"  id="start_time" class="form-control" 
							style="width: 100%;" placeholder="Open Paper Time">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">	
						<div class="form-group">
							<label><s:text name="paper.close.time"/></label> 
							<div class="input-group">
							<div class="input-group-addon"><i class="fa fa-clock-o"></i></div>
							<input type="text"  name="end_time"  id="end_time" class="form-control" 
							style="width: 100%;" placeholder="Finish Paper Time">
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
	  <button id="btn_export_question" type="button" class="btn btn-default" data-toggle="tooltip" title="<s:text name="全部导出"/>">
        <span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>
        	<s:text name="scoreManage_13_btn_export"/> <!-- scoreManage_13_btn_export:导出分数 -->
      </button>
      <button id="btn_export_answer" type="button" class="btn btn-default" data-toggle="tooltip" title="<s:text name="全部导出"/>">
        <span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>
        	<s:text name="scoreManage_14_btn_export"/> <!-- scoreManage_13_btn_export:导出答案 -->
      </button>
       <button id="btn_export_transcript" type="button" class="btn btn-default" data-toggle="tooltip" title="<s:text name="勾选导出"/>">
        <span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>
        	<s:text name="scoreManage_15_btn_export"/> <!-- scoreManage_13_btn_export:成绩单导出 -->
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
				<div class="callout callout-info" style="margin-bottom: 0px;margin-top: 10px;margin-left: 10px;margin-right: 10px;">
                	<div class="row">
					    <div class="col-md-6 col-xs-6">	
							<h4><s:text name="paper.info"/></h4>
                			<div id="contacts1"></div>
						</div>
						<div class="col-md-6 col-xs-6">	
							<h4><s:text name="class.info"/></h4>
                			<div id="contacts2"></div>
						</div>	
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