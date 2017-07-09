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
<!-- 页面js -->
<script
	src="${pageContext.request.contextPath}/question/tag/addNewTag.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/question/tag/${system_lang}.js"></script>
<title>新增标签</title>
<style type="text/css">
	.content {
    	min-height: 0px;
	}
	html, body {
    	min-height: 0px;
	}
</style>
</head>
<body>
	<!-- Main content -->
	<section class="content" id="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<div class="box box-primary" id="box">
		<div class="box-body">
			<form id="addForm">
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="row">
							<div class="col-md-12 col-xs-12">
								<div class="form-group">
									<label for="name"><s:text name="tagName"/><small><i class="fa fa-star notNull"></i></small></label>
									<div class="input-group">
										<span class="input-group-addon"><li
											class="fa fa-file-text"></li></span> <input type="text"
											name="name" id="name" class="form-control"
											placeholder="标签名">
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-xs-12">
								<div class="form-group">
									<label for="enable"><s:text name="enable"/><small><i class="fa fa-star notNull"></i></small></label> 
									<select class="form-control select"
										name="enable" id="enable" type="select-one" style="width: 100%;">
										<option value=""></option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="row">
							<div class="col-md-12 col-xs-12">
								<div class="form-group">
									<label for="description"><s:text name="tagDescription"/></label>
									<textarea class="form-control" rows="5" name="description" id="description" style="width: 100%;"></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="box-footer" style="padding: 0 0 0 0">
			<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
				<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
					<button type="button" id="closeButton"
						class="btn btn-block btn-primary btn-sm" onclick="javascript: parent.layer.close(index);"> 
						<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/>
					</button>
				</div>
				<div class="col-md-3 col-xs-3">
					<button type="button" id="addButton"
						class="btn btn-block btn-primary btn-sm">
						<i class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;<s:text name="addButton"/>
					</button>
				</div>
			</div>
		</div>
	</div>
	</section>
</body>
</html>