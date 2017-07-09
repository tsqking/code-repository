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
<s:include value="../../../../common.jsp"></s:include>
<!-- 页面js -->
<script
	src="${pageContext.request.contextPath}/campusRecruit/univ/university/branch/editUnivBranch.js"></script>
<script src="${pageContext.request.contextPath}/campusRecruit/univ/university/${system_lang}.js"></script>
<style type="text/css">
	.content {
    	min-height: 0px;
	}
	html, body {
    	min-height: 0px;
	}
</style>
<title>更新分校信息</title>
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
					 <div class="col-md-12 col-xs-12">
						<div class="form-group">
							<label for="type"><s:text name="university.name"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa  fa-university"></li></span> 
								<input type="text" style="text-align:center" readonly  value="${universityVo.univ_name}" id="universityName" class="form-control"
									placeholder="University Name">
							 </div>
						</div>
					</div> 
				</div>
				<div class="row">	
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="name"><s:text name="univBranch.name"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-university"></li></span> 
							    <input type="text"  name="universityVo.name"
									id="name" value="${universityVo.name}" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="country"><s:text name="univBranch.country"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text" 
									name="universityVo.country" id="country" value="${universityVo.country}"
									class="form-control" placeholder="NULL">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="province"><s:text name="univBranch.province"/><small><i class="fa fa-star notNull"></i></small></label>
							 <select class="form-control select"
								name="universityVo.province" id="province" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="city"><s:text name="univBranch.city"/><small><i class="fa fa-star notNull"></i></small></label>
									<select class="form-control select" name="universityVo.city" id="city" style="width: 100%;">
								<option value=""></option>
							</select>
					</div>
				</div>
				</div>
				<div class="row">
			         <div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="type"><s:text name="univBranch.type"/><small><i class="fa fa-star notNull"></i></small></label>
								<select class="form-control select"
								name="universityVo.type" id="type" style="width: 100%;">
								<option value=""></option>
							</select>
					     </div>
					  </div>
					  <div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="quality"><s:text name="univBranch.quality"/><small><i class="fa fa-star notNull"></i></small></label>
							<select class="form-control select"
								name="universityVo.quality" id="quality" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					  </div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="description"><s:text name="univBranch.email"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-envelope"></li></span> <input type="text"
									name="universityVo.email" id="email" value="${universityVo.email }"
									class="form-control" placeholder="NULL">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="detail_addr"><s:text name="univBranch.detail_addr"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-file-text"></li></span> 
								<input type="text"
									name="universityVo.detail_addr" id="detail_addr" value="${universityVo.detail_addr}"
									class="form-control" placeholder="NULL">
							</div>
						</div>
					</div>
					
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="description"><s:text name="univBranch.phone"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-phone"></li></span> <input type="text"
									name="universityVo.phone" id="phone" value="${universityVo.phone }"
									class="form-control" placeholder="NULL">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="description"><s:text name="univBranch.website"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-file-text"></li></span> 
									 <input type="text"
									name="universityVo.website" id="website" value="${universityVo.website }"
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
									name="universityVo.create_time" id="create_time" value="${universityVo.create_time }"
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
									name="universityVo.create_user" id="create_user" value="${universityVo.create_user }"
									class="form-control" placeholder="NULL">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="description"><s:text name="updateTime"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-calendar"></li></span> <input type="text" readonly
									name="universityVo.update_time" id="update_time" value="${universityVo.update_time }"
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
									name="universityVo.update_user" id="update_user" value="${universityVo.update_user }"
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
								<input type="text" readonly name="universityVo.id"
									id="id" value="${universityVo.id }"
									class="form-control" placeholder="UniversityVo Id">
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
			</div>
		</form>
		
	</div>
	<!-- 隐藏 -->
	<div class="row" style="display: none;">
		<div class="col-md-6 col-xs-6">
			<div class="form-group">
				<label>type</label>
				<div class="input-group">
					<span class="input-group-addon"><li class="fa fa-sort"></li></span>
					<input type="text" readonly name="universityVo.type"
						id="hiddentype" value="${universityVo.type}" class="form-control"
						placeholder="UniversityVo type">
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xs-6">
			<div class="form-group">
				<label>quality</label>
				<div class="input-group">
					<span class="input-group-addon"><li class="fa fa-sort"></li></span>
					<input type="text" readonly name="universityVo.quality"
						id="hiddenquality" value="${universityVo.quality}"
						class="form-control" placeholder="UniversityVo quality">
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xs-6">
			<div class="form-group">
				<label>province</label>
				<div class="input-group">
					<span class="input-group-addon"><li class="fa fa-sort"></li></span>
					<input type="text" readonly name="universityVo.province"
						id="hiddenprovince" value="${universityVo.province}"
						class="form-control" placeholder="UniversityVo Province">
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xs-6">
			<div class="form-group">
				<label>province</label>
				<div class="input-group">
					<span class="input-group-addon"><li class="fa fa-sort"></li></span>
					<input type="text" readonly name="universityVo.city"
						id="hiddencity" value="${universityVo.city }" class="form-control"
						placeholder="UniversityVo city">
				</div>
			</div>
		</div>
	</div>
	</section>
</body>
</html>


