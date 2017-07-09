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
	src="${pageContext.request.contextPath}/question/question/addQuestion.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/question/question/${system_lang}.js"></script>
<title>新增题目</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<div class="box box-primary">
		<form id="addForm">
			<div class="box-body">
					<div class="row">
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
				            	<label><s:text name="questionType"/><small><i class="fa fa-star notNull"></i></small></label>           
				            	<select class="form-control select"
									name="type" id="type" style="width: 100%;">
									<option value=""></option>
								</select>
				            </div>
				        </div>
				        <div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label><s:text name="property"/><small><i class="fa fa-star notNull"></i></small></label>
								<select class="form-control select"
									name="property" id="property" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
				        </div>
				        <div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label><s:text name="so_flag"/><small><i class="fa fa-star notNull"></i></small></label>
								<select class="form-control select"
									name="so_flag" id="so_flag" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
				        </div>
				        <div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label><s:text name="use_flag"/><small><i class="fa fa-star notNull"></i></small></label>
								<select class="form-control select"
									name="use_flag" id="use_flag" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
				        </div>
					</div>
					<div class="row">
				        <div class="col-md-3 col-xs-3">
							<div class="form-group">
				            	<label><s:text name="difficulty"/><small><i class="fa fa-star notNull"></i></small></label>           
				            	<select class="form-control select"
									name="difficulty" id="difficulty" style="width: 100%;">
									<option value=""></option>
								</select>
				            </div>
				        </div>
				        <div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label><s:text name="enable"/><small><i class="fa fa-star notNull"></i></small></label>
								<select class="form-control select"
									name="enable" id="enable" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
				        </div>
					</div>
					<div class="row">
						<div class="col-md-6 col-xs-6">
							<div class="form-group">
								<label><s:text name="pointName"/><small><i class="fa fa-star notNull"></i></small></label> 
									<select class="form-control select" class="skillSecond"
											name="pointVo.name" id="pointName" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
				        </div>
				        <div class="col-md-2 col-xs-2">
				        	<label style="width:100%;height:100%;">&nbsp;</label>
							<button type="button" id="setPoint" style="margin-top:2px;"
									class="btn btn-primary"><i class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;<s:text name="setPoint"/></button>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 col-xs-6">
							<div class="form-group">
				            	<label><s:text name="tagName"/><small><i class="fa fa-star notNull"></i></small></label>           
				            	<select class="form-control select"
									name="tagVo.name" id="name" style="width: 100%;">
									<option value=""></option>
								</select>
				            </div>
				        </div>
						<div class="col-md-2 col-xs-2">
							<label style="width:100%;height:100%;">&nbsp;</label>
							<button type="button" id="addNewTag" style="margin-top:2px;"
									class="btn btn-primary"><i class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;<s:text name="addNewTag"/></button>
						</div>
					</div>
				
				<hr style="height:3px;">
				<div>
					<!-- 根据题型给出相应的输题界面 -->
				</div>
				
				<div class="row">
						<div class="col-md-9 col-xs-9">
							<div class="form-group">
				            	<label><s:text name="questionExplain"/><small><i class="fa fa-star notNull"></i></small></label>           
				            	<textarea class="form-control select" rows="6"
									name="analysis" id="analysis" style="width: 100%;">
								</textarea>
				            </div>
				        </div>
				</div>
				
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
						<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
							<button type="button" id="resetButton"
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeWindow"/></button>
						</div>
						<div class="col-md-3 col-xs-3">
							<button type="button" id="searchButton"
									class="btn btn-block btn-success btn-sm"><i class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;<s:text name="addQuestion"/></button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	</section>
</body>
</html>