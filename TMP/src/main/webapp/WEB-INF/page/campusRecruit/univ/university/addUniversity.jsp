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
<script src="${pageContext.request.contextPath}/campusRecruit/univ/university/addUniversity.js"></script> 
<script src="${pageContext.request.contextPath}/campusRecruit/univ/university/${system_lang}.js"></script>
<style type="text/css">
	.content {
    	min-height: 0px;
	}
	html, body {
    	min-height: 0px;
	}
</style>
<title>添加学校信息</title>
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
							<label for="universityName"><s:text name="university.name"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa  fa-university"></li></span>
								 <input type="text"
									name="universityVo.name" id="universityName" class="form-control"
									placeholder="University Name"  onblur="checkRepetition();">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="type">
							<label for="parent_name"><s:text name="university.type"/><small><i class="fa fa-star notNull"></i></small></label>
								 <select class="form-control select"
								name="universityVo.type" id="type" style="width: 100%;">
								<option value=""></option>
							</select>
					</div>
				</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="quality"><s:text name="university.quality"/></label><small><i class="fa fa-star notNull"></i></small></label>
								<select class="form-control select"
								name="universityVo.quality" id="quality" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="country"><s:text name="university.country"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text"
									name="universityVo.country" id="country" value="中国" class="form-control">
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="regionName"><s:text name="university.province"/><small><i class="fa fa-star notNull"></i></small></label>
								<select class="form-control select" class="regionName"
									name="universityVo.province" id="regionName" style="width: 100%;">
									<option value=""></option>
								</select>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="secondregionName"><s:text name="university.city"/><small><i class="fa fa-star notNull"></i></small></label>
								<select class="form-control select" class="secondregionName"
								name="universityVo.city" id="secondregionName"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="detail_addr"><s:text name="university.detail_addr"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text"
									name="universityVo.detail_addr" id="detail_addr" class="form-control"
									placeholder="Detail Address">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label><s:text name="university.website"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-file-text"></li></span> 
								</label><input type="text"
									name="universityVo.website" id="website" class="form-control"
									placeholder="University Website">
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label><s:text name="university.phone"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-phone"></li></span>
								 <input type="text" name="universityVo.phone" id="phone" class="form-control"
									placeholder="University Phone">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label><s:text name="university.email"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-envelope"></li></span> </label>
									<input type="text" name="universityVo.email" id="email" class="form-control"
									placeholder="University Email">
							</div>
						</div>
					</div>
				</div>
		        <!-- 隐藏 -->
				<div class="row" style="display:none;">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label>上级技能序号</label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-sort"></li></span>
								<input type="text" readonly name="universityVo.id"
									id="id" value="${universityVo.id }"
									class="form-control" placeholder="UniversityVo Id">
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
								<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/>
							</button>
						</div>
						<div class="col-md-3 col-xs-3">
							<button type="button" id="addButton"
								class="btn btn-block btn-primary btn-sm">
								<i class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;<s:text name="addCommitButton"/>
							</button>
						</div>
					</div>
				</div>
				</div>
		</form>
	</div>
	</section>
</body>
</html>