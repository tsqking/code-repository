<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<script src="${pageContext.request.contextPath}/campusRecruit/univ/attn/addAttn.js"></script> 
<script src="${pageContext.request.contextPath}/campusRecruit/univ/attn/${system_lang}.js"></script>
<style type="text/css">
	.content {
    	min-height: 0px;
	}
	html, body {
    	min-height: 0px;
	}
</style>
<title>添加联系人信息</title>
</head>
<body>
   <section class="content" id="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	    <div class="box box-primary" id="box">
		<form id="addForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
				 <div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="attnName"><s:text name="attn.name"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-user"></li></span>
							    <input type="text"
									name="attnVo.name" id="attnName" class="form-control"
									placeholder="Attn Name">
								</div>
						</div>
				  </div> 
				  
				  <div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="gender"><s:text name="attn.gender"/><small><i class="fa fa-star notNull"></i></small></label>
							 
							 <select class="form-control select" class="gender"
									name="attnVo.gender" id="gender" style="width: 100%;">
									<option value=""></option>
								</select>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="phone"><s:text name="attn.phone"/></label>
								  <div class="input-group">
											<span class="input-group-addon"><li class="fa fa-phone"></li></span>
								 <input type="text"
									name="attnVo.phone" id="phone" class="form-control"
									placeholder="Attn Phone">
								 </div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="mobile"><s:text name="attn.mobile"/></label>
							 <div class="input-group">
											<span class="input-group-addon"><li class="fa fa-mobile"></li></span>
							 <input type="text"
									name="attnVo.mobile" id="mobile" class="form-control"
									placeholder="Attn Mobile">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label><s:text name="attn.email"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-envelope"></li></span>
							    <input type="text" name="attnVo.email" id="email" class="form-control" placeholder="Attn Email">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="position"><s:text name="attn.position"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-file-text"></li></span>
							 <input type="text"
									name="attnVo.position" id="position" class="form-control"
									placeholder="Attn Position">
						    </div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
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
				</div>
				<!-- /.box-body -->
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
						<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
							<button type="button" id="closeButton"
								class="btn btn-block btn-primary btn-sm" onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">
								<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/>
							</button>
						</div>
						<div class="col-md-3 col-xs-3">
							<button type="button" id="addButton"
								class="btn btn-block btn-primary btn-sm">
								<i class="fa fa-user-plus"></i>&nbsp;&nbsp;&nbsp;<s:text name="addCommitButton"/>
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</section>
</body>
</html>