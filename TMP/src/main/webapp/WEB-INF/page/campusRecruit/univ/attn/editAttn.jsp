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
	src="${pageContext.request.contextPath}/campusRecruit/univ/attn/editAttn.js"></script>
<script src="${pageContext.request.contextPath}/campusRecruit/univ/attn/${system_lang}.js"></script>
<style type="text/css">
	.content {
    	min-height: 0px;
	}
	html, body {
    	min-height: 0px;
	}
</style>
<title>更新联系人信息</title>
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
							<label for="name"><s:text name="attn.name"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-user"></li></span>
							<input type="text"  name="attnVo.name"
									id="name" value="${attnVo.name }" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
							<div class="form-group">
								<label for="gender"><s:text name="attn.gender"/><small><i class="fa fa-star notNull"></i></small></label>
								 <select class="form-control select" name="attnVo.gender" id="gender" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>
				
				<div class="row">
				<div class="col-md-6 col-xs-6">
						<div class="univ_id">
							<label for="univ_id"><s:text name="attn.university"/><small><i class="fa fa-star notNull"></i></small></label>
							<select class="form-control select" class="univ_id"
									name="attnVo.univ_id" id="univ_id" style="width: 100%;">
									<option value=""></option>
								</select>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label><s:text name="attn.univBranch"/></label>
							 <select class="form-control select" class="univ_branch_id"
									name="attnVo.univ_branch_id" id="univ_branch_id" style="width: 100%;">
									<option value=""></option>
								</select>
						</div>
					</div>
				</div>
				<div class="row">
						<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label><s:text name="attn.college"/></label>
							<select class="form-control select" class="college_id"
									name="attnVo.college_id" id="college_id" style="width: 100%;">
									<option value=""></option>
								</select>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="phone"><s:text name="attn.phone"/></label>
							<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-phone"></li></span>
							<input type="text"  name="attnVo.phone"
									id="phone" value="${attnVo.phone}" class="form-control">
							</div>
						</div>
					</div>
					
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="mobile"><s:text name="attn.mobile"/></label>
							 <div class="input-group">
								  <span class="input-group-addon"><li class="fa fa-mobile"></li></span>
							      <input type="text"  name="attnVo.mobile"
									id="mobile" value="${attnVo.mobile}" class="form-control">
							 </div>
						 </div>
					</div>
					<div class="col-md-6 col-xs-6">
							<div class="form-group">
								<label for="email"><s:text name="attn.email"/></label>
								<div class="input-group">
									<span class="input-group-addon"><li class="fa fa-envelope"></li></span>
									<input type="text"  name="attnVo.email"
											id="email" value="${attnVo.email}" class="form-control">
								</div>
							</div>
					</div>
					
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="position"><s:text name="attn.position"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-file-text"></li></span> 
							<input type="text"  name="attnVo.position"
									id="position" value="${attnVo.position}" class="form-control">
						    </div>
						 </div>
					</div>
					
				</div>
				
				<div class="row">
				<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="description"><s:text name="createTime"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-calendar"></li></span> 
								<input type="text" readonly
									 id="create_time" value="${attnVo.create_time }"
									class="form-control" placeholder="NULL">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
							<div class="form-group">
								<label for="description"><s:text name="createPerson"/></label>
								<div class="input-group">
									<span class="input-group-addon"><li class="fa fa-user"></li></span>
									 <input type="text" readonly
										 id="create_user" value="${attnVo.create_user }"
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
								<span class="input-group-addon"><li class="fa fa-calendar"></li></span> 
							 <input type="text" readonly
									 id="update_time" value="${attnVo.update_time }"
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
									 id="update_user" value="${attnVo.update_user }"
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
								<input type="text" readonly name="attnVo.id"
									id="id" value="${attnVo.id}"
									class="form-control" placeholder="Attn Id">
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
	       <!-- 隐藏 -->
				<div class="row" style="display:none;">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label>gender</label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-sort"></li></span>
								<input type="text" readonly name="attnVo.gender"
									id="hiddengender" value="${attnVo.gender}"
									class="form-control" placeholder="Attn Gender">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label>univ_id</label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-sort"></li></span>
								<input type="text" readonly name="attnVo.univ_id"
									id="hiddenuniv_id" value="${attnVo.univ_id}"
									class="form-control" placeholder="Attn univ_id">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label>gender</label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-sort"></li></span>
								<input type="text" readonly name="attnVo.univ_branch_id"
									id="hiddenuniv_branch_id" value="${attnVo.univ_branch_id}"
									class="form-control" placeholder="Attn Univ_branch_id">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label>college Id</label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-sort"></li></span>
								<input type="text" readonly name="attnVo.college_id"
									id="hiddencollege_id" value="${attnVo.college_id}"
									class="form-control" placeholder="Attn College_id">
							</div>
						</div>
					</div>
			</div>
	</section>
</body>
</html>
