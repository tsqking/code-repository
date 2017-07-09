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
<script src="${pageContext.request.contextPath}/campusRecruit/univ/workhist/addWorkHist.js"></script> 
<script src="${pageContext.request.contextPath}/campusRecruit/univ/workhist/${system_lang}.js"></script>
<style type="text/css">
	.content {
    	min-height: 0px;
	}
	html, body {
    	min-height: 0px;
	}
</style>

<title>添加工作历史信息</title>
</head>
<body>
   <section class="content" id="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	    <div class="box box-primary" id="box">
		<form id="addForm" >
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
				  <div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="gender"><s:text name="workHist.op_user_name"/><small><i class="fa fa-star notNull"></i></small></label>
							 <div class="input-group">
								<span class="input-group-addon"><li class="fa fa-user"></li></span>
							 <input class="form-control select" class="op_user_name"
									name="workHistVo.op_user_name" id="op_user_name" style="width: 100%;" placeholder="Staff Name">
						     </div>
						</div>
					</div>
				 <div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="attnName"><s:text name="workHist.op_time"/><small><i class="fa fa-star notNull"></i></small></label>
							    <div class="input-group">
								<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
							    <input type="text" name="workHistVo.op_time" id="op_time" class="form-control" placeholder="Work Time">
						    </div>
						</div>
				  </div> 
				</div>
				
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label><s:text name="workHist.univ_id"/><small><i class="fa fa-star notNull"></i></small></label> 
							<select class="form-control select" class="univ_id"
								name="workHistVo.univ_id" id="univ_id" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					 </div>
					 <div class="col-md-6 col-xs-6">	
						<div class="form-group">
							<label><s:text name="workHist.univ_branch_id"/></label> 
							<select class="form-control select" class="univ_branch_id"
								name="workHistVo.univ_branch_id" id="univ_branch_id" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="college_id"><s:text name="workHist.college_id"/></label>
							<select class="form-control select" class="college_id"
									name="workHistVo.college_id" id="college_id" style="width: 100%;">
									<option value=""></option>
								</select>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="position"><s:text name="workHist.work_content"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-file-text"></li></span>
							 <input type="text" maxlength=10 onMouseMove="this.title='请输入工作内容简要(1-10字)'" name="workHistVo.work_content" id="work_content" class="form-control" placeholder="Work Content">
						    </div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="univ_id"><s:text name="workHist.op_status"/><small><i class="fa fa-star notNull"></i></small></label>
							<select class="form-control select" class="op_status"
									name="workHistVo.op_status" id="op_status" style="width: 100%;">
									<option value=""></option>
								</select>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">  <!-- cols="25" rows="8" -->
							<label><s:text name="workHist.remark"/></label>
							<textarea style="font-size:14px;"   cols="25" rows="6" maxlength="100"  class="form-control" class="remark"
									name="workHistVo.remark" id="remark" style="width: 100%;" placeholder="Remarks"></textarea>
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