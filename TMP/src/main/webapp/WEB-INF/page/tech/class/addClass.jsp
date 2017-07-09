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
	src="${pageContext.request.contextPath}/tech/class/addClass.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/tech/class/${system_lang}.js"></script>
<title>新增班级</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<div class="box box-primary">
		<form id="addForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-4 col-xs-4">
						<div class="form-group">
							<label for="direction"><s:text name="class.classDirection"/></label>
							<select class="form-control select"
								name="classVo.direction" id="direction" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="col-md-4 col-xs-4">
						<div class="form-group">
							<label for="location"><s:text name="class.classLocation"/><small><i class="fa fa-star notNull"></i></small></label>
							<select class="form-control select" name="classVo.location" id="location" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="col-md-4 col-xs-4">
						<div class="form-group">
							<label for="enable"><s:text name="enable"/><small><i class="fa fa-star notNull"></i></small></label> 
							<select class="form-control select"
								name="classVo.enable" id="enable" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="name"><s:text name="class.className_zh_CN"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text"
									name="classVo.name" id="name" class="form-control"
									placeholder="班级名字">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="name_en_US"><s:text name="class.className_en_US"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span><input type="text"
									name="classVo.name_en_US" id="name_en_US" class="form-control"
									placeholder="Class Name in English">
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="session"><s:text name="class.classSession_zh_CN"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text"
									name="classVo.session" id="session" class="form-control"
									placeholder="Class Session(如“2015春季班”)">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="session_en_US"><s:text name="class.classSession_en_US"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text"
									name="classVo.session_en_US" id="session_en_US" class="form-control"
									placeholder="Class Session(e.g.“2015 Spring Class”)">
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="description"><s:text name="class.classDescription_zh_CN"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-object-group"></li></span> <input type="text"
									name="classVo.description" id="description" value=""
									class="form-control" placeholder="Class Description in Chinese">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="description_en_US"><s:text name="class.classDescription_en_US"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-object-group"></li></span> <input type="text"
									name="classVo.description_en_US" id="description_en_US" value=""
									class="form-control" placeholder="Class Description in English">
							</div>
						</div>
					</div>
				</div>
				<!-- /.box-body -->
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
						<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
							<button type="button" id="closeButton"
								class="btn btn-block btn-primary btn-sm" onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">
								<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeWindowButton"/>
							</button>
						</div>
						<div class="col-md-3 col-xs-3">
							<button type="button" id="addButton"
								class="btn btn-block btn-primary btn-sm">
								<i class="fa fa-check"></i>&nbsp;&nbsp;&nbsp;<s:text name="addButton"/>
							</button>
						</div>
					</div>
				</div>
		</form>
	</div>
	</section>
</body>
</html>




