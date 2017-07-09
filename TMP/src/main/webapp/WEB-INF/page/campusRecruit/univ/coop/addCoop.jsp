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
 <script src="${pageContext.request.contextPath}/campusRecruit/univ/coop/addCoop.js"></script> 
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/campusRecruit/univ/coop/${system_lang}.js"></script>
<style type="text/css">
	.content {
    	min-height: 0px;
	}
	html, body {
    	min-height: 0px;
	}
</style>
<title>添加合作信息</title>
</head>
<body>
   <section class="content" id="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	    <div class="box box-primary" id="box">
		<form id="addForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label for="company_name"><s:text name="coop.company_name"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-file-text"></li></span>
								 <input type="text"
									name="coopVo.company_name" id="company_name" class="form-control" value="CLPS"
									placeholder="Company Name">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label for="country"><s:text name="coop.country"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-file-text"></li></span> <input type="text"
									name="coopVo.country" id="country" value="中国" class="form-control">
							</div>
						</div>
					</div>
					 <div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label for="regionName"><s:text name="coop.province"/><small><i class="fa fa-star notNull"></i></small></label>
								<select class="form-control select" class="regionName"
									name="coopVo.province" id="regionName" style="width: 100%;">
									<option value=""></option>
								</select>
						</div>
					</div>
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label for="secondregionName"><s:text name="coop.city"/><small><i class="fa fa-star notNull"></i></small></label>
								<select class="form-control select" class="secondregionName"
								name="coopVo.city" id="secondregionName"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label for="univ_id"><s:text name="coop.university"/><small><i class="fa fa-star notNull"></i></small></label>
							<select class="form-control select" class="univ_id"
									name="coopVo.univ_id" id="univ_id" style="width: 100%;">
									<option value=""></option>
								</select>
						</div>
					</div>
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label><s:text name="coop.univBranch"/></label>
							 <select class="form-control select" class="univ_branch_id"
									name="coopVo.univ_branch_id" id="univ_branch_id" style="width: 100%;">
									<option value=""></option>
								</select>
						</div>
					</div>
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label><s:text name="coop.college" /></label> 
							<select
								class="form-control select" class="college_id"
								name="coopVo.college_id" id="college_id" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label for="coop_time"><s:text name="coop.time"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<div class="input-group-addon"><i class="fa fa-calendar"></i></div>
								<input type="text"  name="coopVo.coop_time" value="${coopVo.coop_time}" id="coop_time" class="form-control"
									placeholder="Coop Time">
							</div>
						</div>
					</div> 
				</div>
				<div class="row">
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label><s:text name="coop.style"/><small><i class="fa fa-star notNull"></i></small></label>
								<select class="form-control select"  class="coopStyle"
								name="coopVo.style" id="coopStyle"
								style="width: 100%;">
							</select>
						</div>
					</div>
				 <div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label><s:text name="coop.status"/><small><i class="fa fa-star notNull"></i></small></label>
								<select class="form-control select" class="coopStatus"
								name="coopVo.status" id="coopStatus"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>		
					<div class="col-md-3 col-xs-4" id="recruitTime">
						<div class="form-group">
							<label><s:text name="coop.recruit_time"/></label>
								<div class="input-group">
								<div class="input-group-addon"><i class="fa fa-calendar"></i></div>
								<input type="text"  name="coopVo.recruit_time"  id="recruit_time" class="form-control"
									placeholder="Recruit Time">
								</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-4" id="recruitEmail">
						<div class="form-group">
							<label><s:text name="coop.recruit_email"/></label>
								<div class="input-group">
								<div class="input-group-addon"><i class="fa fa-calendar"></i></div>
								<input type="text"  name="coopVo.recruit_email"  id="recruit_email" class="form-control"
									placeholder="Recruit Email" value="CL.CAMPUS@clps.com.cn">
								</div>
						</div>
					</div>
		
				</div>
				<div class="row">
				 <div class="col-md-4 col-xs-4">
						<div class="form-group">
							<label><s:text name="coop.remark"/></label>
							<div class="input-group">
								<textarea name="coopVo.remark" style="font-size:14px;" maxlength="100" cols="80" rows="10" name="collegeVo.remark"  id="remark" class="form-control" placeholder="Remark"></textarea>
							</div>
						</div>
					</div> 
					<div class="col-md-8 col-xs-8">
							<label><s:text name="coop.attn"/></label>
							<div class="col-xs-15">
								<div class="box box-primary" style="margin-bottom: 0px;">
									<div class="box-body">
										<table id="attnTable" class="table table-bordered table-striped table-hover">
										</table>	
									</div>
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
	</section>
</body>
</html>