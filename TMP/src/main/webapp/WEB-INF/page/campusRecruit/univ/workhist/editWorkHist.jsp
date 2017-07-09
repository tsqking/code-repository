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
<script
	src="${pageContext.request.contextPath}/campusRecruit/univ/workhist/editWorkHist.js"></script>
<script
	src="${pageContext.request.contextPath}/campusRecruit/univ/workhist/${system_lang}.js"></script>
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
	<section class="content" id="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<div class="box box-primary" id="box">
		<form id="editForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="gender"><s:text name="workHist.op_user_name" /><small><i
									class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-user"></li></span>
								<input class="form-control select" class="op_user_name"
									name="workHistVo.op_user_name" id="op_user_name"
									value="${workHistVo.op_user_name}" style="width: 100%;"
									placeholder="Staff Name">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="op_time"><s:text name="workHist.op_time" /><small><i
									class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
								<input type="text" name="workHistVo.op_time" id="op_time"
									value="${workHistVo.op_time}" class="form-control"
									placeholder="Work Time">
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="univ_id"><s:text name="workHist.univ_id" /><small><i
									class="fa fa-star notNull"></i></small></label> <select
								class="form-control select" class="univ_id"
								name="workHistVo.univ_id" id="univ_id" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="univ_branch_id"><s:text
									name="workHist.univ_branch_id" /></label> <select
								class="form-control select" class="univ_branch_id"
								name="workHistVo.univ_branch_id" id="univ_branch_id"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-xs-4">
						<div class="form-group">
							<label for="college_id"><s:text
									name="workHist.college_id" /></label> <select
								class="form-control select" class="college_id"
								name="workHistVo.college_id" id="college_id"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="col-md-4 col-xs-4">
						<div class="form-group">
							<label for="position"><s:text
									name="workHist.work_content" /><small><i
									class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-file-text"></i></span> <input type="text" maxlength=10
									onMouseMove="this.title='请输入工作内容简要(1-10字)'"
									name="workHistVo.work_content" id="work_content"
									value="${workHistVo.work_content}" class="form-control"
									placeholder="Work Content">
							</div>
						</div>
					</div>
					<div class="col-md-4 col-xs-4">
						<div class="form-group">
							<label for="univ_id"><s:text name="workHist.op_status" /><small><i
									class="fa fa-star notNull"></i></small></label> <select
								class="form-control select" class="op_status"
								name="workHistVo.op_status" id="op_status" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-md-12 col-xs-12">
						<div class="form-group">
							<label><s:text name="workHist.remark" /></label>
							<textarea style="font-size: 14px;" maxlength="100" cols="90"
								rows="3" maxlength="100" class="form-control" class="remark"
								name="workHistVo.remark" id="remark">${workHistVo.remark}</textarea>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label><s:text name="createTime" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-calendar"></li></span> <input type="text" readonly
									name="workHistVo.create_time" id="create_time"
									value="${workHistVo.create_time }" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label><s:text name="createPerson" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-user"></li></span>
								<input type="text" readonly name="workHistVo.create_user"
									id="create_user" value="${workHistVo.create_user }"
									class="form-control">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="description"><s:text name="updateTime" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-calendar"></li></span> <input type="text" readonly
									name="workHistVo.update_time" id="update_time"
									value="${workHistVo.update_time }" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="description"><s:text name="updatePerson" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-user"></li></span>
								<input type="text" readonly name="workHistVo.update_user"
									id="update_user" value="${workHistVo.update_user }"
									class="form-control" placeholder="NULL">
							</div>
						</div>
					</div>
				</div>
				<div class="row" style="display: none;">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label>id</label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-sort"></li></span>
								<input type="text" readonly name="workHistVo.id" id="id"
									value="${workHistVo.id}" class="form-control"
									placeholder="Attn Gender">
							</div>
						</div>
					</div>
				</div>
				<!-- /.box-body -->
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12" style="margin-bottom: 10px;">
						<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
							<button type="button" id="closeButton"
								class="btn btn-block btn-primary btn-sm"
								onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">
								<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;
								<s:text name="closeButton" />
							</button>
						</div>
						<div class="col-md-3 col-xs-3">
							<button type="button" id="updateButton"
								class="btn btn-block btn-primary btn-sm">
								<i class="fa fa-check"></i>&nbsp;&nbsp;&nbsp;
								<s:text name="saveButton" />
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
				<label>id</label>
				<div class="input-group">
					<span class="input-group-addon"><li class="fa fa-sort"></li></span>
					<input type="text" readonly name="attnVo.gender" id="hiddengender"
						value="${workHistVo.id}" class="form-control"
						placeholder="Attn Gender">
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xs-6">
			<div class="form-group">
				<label>univ_id</label>
				<div class="input-group">
					<span class="input-group-addon"><li class="fa fa-sort"></li></span>
					<input type="text" readonly name="workHistVo.univ_id"
						id="hiddenuniv_id" value="${workHistVo.univ_id}"
						class="form-control" placeholder="workHist univ_id">
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xs-6">
			<div class="form-group">
				<label>Univ_branch_id</label>
				<div class="input-group">
					<span class="input-group-addon"><li class="fa fa-sort"></li></span>
					<input type="text" readonly name="workHistVo.univ_branch_id"
						id="hiddenuniv_branch_id" value="${workHistVo.univ_branch_id}"
						class="form-control" placeholder="Univ_branch_id">
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xs-6">
			<div class="form-group">
				<label>college_id</label>
				<div class="input-group">
					<span class="input-group-addon"><li class="fa fa-sort"></li></span>
					<input type="text" readonly name="workHistVo.college_id"
						id="hiddencollege_id" value="${workHistVo.college_id}"
						class="form-control" placeholder="college_id">
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xs-6">
			<div class="form-group">
				<label>Op_status</label>
				<div class="input-group">
					<span class="input-group-addon"><li class="fa fa-sort"></li></span>
					<input type="text" readonly name="workHistVo.op_status"
						id="hiddenop_status" value="${workHistVo.op_status}"
						class="form-control" placeholder="WorkHist Op_status">
				</div>
			</div>
		</div>
	</div>
	</section>
</body>
</html>