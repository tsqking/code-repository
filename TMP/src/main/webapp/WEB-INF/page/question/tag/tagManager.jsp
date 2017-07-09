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
	src="${pageContext.request.contextPath}/question/tag/tagManager.js"></script>
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/question/tag/${system_lang}.js"></script>
<title>标签管理</title>
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
				<div class="box-body">
					<div class="row">
						<div class="col-md-6 col-xs-6">
							<div class="form-group">
				            	<label for="name"><s:text name="tagName"/></label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-fw fa-tag"></li></span>
				                	<input type="text" name="name" id="name" class="form-control" placeholder="tagName">
				              	</div>
				            </div>
				        </div>
				        <div class="col-md-6 col-xs-6">
							<div class="form-group">
								<label for="enable"><s:text name="enable"/></label>
								<select class="form-control select"
									name="enable" id="enable" style="width: 100%;">
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
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-reply"></i>&nbsp;&nbsp;&nbsp;<s:text name="resetButton"/></button>
						</div>
						<div class="col-md-3 col-xs-3">
							<button type="button" id="searchButton"
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;<s:text name="searchButton"/></button>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div id="toolbar" class="btn-group">
	      <button id="btn_add" type="button" class="btn btn-default">
	        <span class="fa fa-plus" aria-hidden="true"></span>&nbsp;&nbsp;<s:text name="addButton"></s:text>
	      </button>
	      <button id="btn_delete" type="button" class="btn btn-default">
	        <span class="fa fa-lock" aria-hidden="true"></span>&nbsp;&nbsp;<s:text name="disableButton"></s:text>
	      </button>
	    </div>
		<!-- 数据表格 -->
		<div class="row">
			<div class="col-xs-12">
				<div class="box box-primary" style="margin-bottom: 5px;">
					<div class="box-header">
						<h3 class="box-title"><s:text name="tagListTitle"/></h3>
					</div>
					<div class="box-body">
						<table id="table" class="table table-bordered table-striped table-hover" data-single-select="true">
						</table>	
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>