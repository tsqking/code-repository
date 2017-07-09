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
<s:include value="../../../common.jsp"></s:include>
<!-- 页面js -->
<script
	src="${pageContext.request.contextPath}/campusRecruit/univ/college/editCollege.js"></script>
<script src="${pageContext.request.contextPath}/campusRecruit/univ/college/${system_lang}.js"></script>
<style type="text/css">
	.content {
    	min-height: 0px;
	}
	html, body {
    	min-height: 0px;
	}
</style>
<title>更新学院信息</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" id="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<div class="box box-primary" id="box">
		<form id="editForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					 <div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="type"><s:text name="university.name"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-university"></li></span> 
								<input type="text" readonly  value="${collegeVo.univ_name}" id="universityName" class="form-control"
									placeholder="University Name">
							</div>
						</div>
					</div> 
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="name"><s:text name="college.name"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-file-text"></li></span> 
							<input type="text"  name="collegeVo.name"
									id="name" value="${collegeVo.name }" class="form-control">
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="description"><s:text name="createPerson"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-calendar"></li></span> <input type="text" readonly
									name="collegeVo.create_time" id="create_time" value="${collegeVo.create_time }"
									class="form-control" placeholder="NULL">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="description"><s:text name="createPerson"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-user"></li></span> <input type="text" readonly
									name="collegeVo.create_user" id="create_user" value="${collegeVo.create_user }"
									class="form-control" placeholder="NULL">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="description"><s:text name="createTime"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-calendar"></li></span> <input type="text" readonly
									name="collegeVo.update_time" id="update_time" value="${collegeVo.update_time }"
									class="form-control" placeholder="NULL">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="description"><s:text name="updatePerson"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-user"></li></span> <input type="text" readonly
									name="collegeVo.update_user" id="update_user" value="${collegeVo.update_user }"
									class="form-control" placeholder="NULL">
							</div>
						</div>
					</div>
				</div>
				<!-- 隐藏 -->
				<div class="row" style="display:none;">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label>id</label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-sort"></li></span>
								<input type="text" readonly name="collegeVo.id"
									id="id" value="${collegeVo.id }"
									class="form-control" placeholder="CollegeVo Id">
							</div>
						</div>
					</div>
				</div>
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
						<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
							<button type="button" id="closeButton"
								class="btn btn-block btn-primary btn-sm" onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">
								<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/>
							</button>
						</div>	
						<div class="col-md-3 col-xs-3">
							<button type="button" id="updateButton"
								class="btn btn-block btn-primary btn-sm">
								<i class="fa fa-check"></i>&nbsp;&nbsp;&nbsp;<s:text name="saveButton"/>
							</button>
						</div>
					</div>
				</div>
		</form>
	</div>
	</section>
</body>
</html>
