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
	src="${pageContext.request.contextPath}/campusRecruit/univ/coop/viewCoop.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/campusRecruit/univ/coop/${system_lang}.js"></script>
<style type="text/css">
.content {
	min-height: 0px;
}

html, body {
	min-height: 0px;
}
</style>
<title>合作信息详情</title>
</head>
<body>
	<section class="content" id="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<div class="box box-primary" id="box">
		<form id="editForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label for="name"><s:text name="coop.company_name" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text"
									id="company_name" name="company_name" class="form-control"
									value="${coopVo.company_name}" placeholder="" readonly>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label for="country"><s:text name="coop.country" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text" readonly
									value="${coopVo.country}" id="country" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label for="provinceName"><s:text name="coop.province" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text" readonly
									value="${coopVo.provinceName}" id="provinceName"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label for="cityName"><s:text name="coop.city" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text" readonly
									value="${coopVo.cityName}" id="cityName" class="form-control">
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label for="cityName"><s:text name="coop.university" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-university"></li></span> <input type="text" readonly
									value="${coopVo.univ_name}" id="univ_name" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label for="cityName"><s:text name="coop.univBranch" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-university"></li></span> <input type="text" readonly
									value="${coopVo.univ_branch_name}" " id="univ_branch_name"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label for="cityName"><s:text name="coop.college" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text" readonly
									value="${coopVo.college_name}" " id="college_name"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label for="coop_time"><s:text name="coop.time" /></label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" readonly name="coopVo.coop_time"
									value="${coopVo.coop_time}" id="coop_time" class="form-control"
									placeholder="Coop Time">
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label for="coop_time"><s:text name="coop.style" /></label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa  fa-file-text"></i>
								</div>
								<input type="text" readonly name="style" value="${coopVo.style}"
									id="style" class="form-control" placeholder="Coop Style">
							</div>
						</div>
					</div>

					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label for="coop_time"><s:text name="coop.status" /></label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa  fa-file-text"></i>
								</div>
								<input type="text" readonly name="state" value="${coopVo.state}"
									id="state" class="form-control" placeholder="Coop Status">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-4" id="email">
						<div class="form-group">
							<label for="coop_time"><s:text name="coop.recruit_email" /></label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa  fa-file-text"></i>
								</div>
								<input type="text" readonly name="recruit_email"
									value="${coopVo.recruit_email}" id="recruit_email"
									class="form-control" placeholder="Coop Recruit_Email">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-4" id="time">
						<div class="form-group">
							<label><s:text name="coop.recruit_time" /></label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" readonly name="coopVo.recruit_time"
									value="${coopVo.recruit_time}" id="recruit_time"
									class="form-control" placeholder="NUll">
							</div>
						</div>
					</div>

				</div>

				<div class="row">

					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label><s:text name="createTime" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-calendar"></li></span> <input type="text" readonly
									id="create_time" value="${coopVo.create_time }"
									class="form-control" placeholder="NULL">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label><s:text name="createPerson" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-user"></li></span>
								<input type="text" readonly id="create_user"
									value="${coopVo.create_user }" class="form-control"
									placeholder="NULL">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label><s:text name="updateTime" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-calendar"></li></span> <input type="text" readonly
									id="update_time" value="${coopVo.update_time }"
									class="form-control" placeholder="NULL">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-4">
						<div class="form-group">
							<label><s:text name="updatePerson" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-user"></li></span>
								<input type="text" readonly id="update_user"
									value="${coopVo.update_user }" class="form-control"
									placeholder="NULL">
							</div>
						</div>
					</div>

				</div>

				<div class="row">
					<div class="col-md-4 col-xs-4">
						<div class="form-group">
							<label><s:text name="coop.remark" /></label>
							<div class="input-group">
								<textarea name="coopVo.remark" readonly style="font-size: 14px;"
									maxlength="100" cols="80" rows="10" id="remark"
									class="form-control" placeholder="Remarks">${coopVo.remark}</textarea>
							</div>
						</div>
					</div>
					<div class="col-md-8 col-xs-8">
						<label><s:text name="coop.attn" /></label>
						<div class="col-xs-15">
							<div class="box box-primary" style="margin-bottom: 0px;">
								<div class="box-body">
									<table id="attnTable"
										class="table table-bordered table-striped table-hover">
									</table>
								</div>
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
								<input type="text" readonly name="coopVo.id" id="id"
									value="${coopVo.id}" class="form-control">
							</div>
						</div>
					</div>
				</div>
				<!-- /.box-body -->
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12" style="margin-bottom: 10px;">
						<div class="col-md-3 col-xs-3"></div>
						<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
							<button type="button" id="closeButton"
								class="btn btn-block btn-primary btn-sm"
								onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));refreshTable();">
								<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;
								<s:text name="closeButton" />
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
					<input type="text" readonly id="hiddenid" value="${coopVo.id}"
						class="form-control" placeholder="Attn Id">
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xs-6">
			<div class="form-group">
				<label>univ_id</label>
				<div class="input-group">
					<span class="input-group-addon"><li class="fa fa-sort"></li></span>
					<input type="text" readonly id="hiddenuniv_id"
						value="${coopVo.univ_id}" class="form-control">
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xs-6">
			<div class="form-group">
				<label>Univ_branch_id</label>
				<div class="input-group">
					<span class="input-group-addon"><li class="fa fa-sort"></li></span>
					<input type="text" readonly id="hiddenuniv_branch_id"
						value="${coopVo.univ_branch_id}" class="form-control">
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xs-6">
			<div class="form-group">
				<label>college_id</label>
				<div class="input-group">
					<span class="input-group-addon"><li class="fa fa-sort"></li></span>
					<input type="text" readonly id="hiddencollege_id"
						value="${coopVo.college_id}" class="form-control"
						placeholder="college_id">
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xs-6">
			<div class="form-group">
				<label>province</label>
				<div class="input-group">
					<span class="input-group-addon"><li class="fa fa-sort"></li></span>
					<input type="text" readonly id="hiddenProvince"
						value="${coopVo.province}" class="form-control">
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xs-6">
			<div class="form-group">
				<label>Op_status</label>
				<div class="input-group">
					<span class="input-group-addon"><li class="fa fa-sort"></li></span>
					<input type="text" readonly id="hiddenCity" value="${coopVo.city}"
						class="form-control">
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xs-6">
			<div class="form-group">
				<label>status</label>
				<div class="input-group">
					<span class="input-group-addon"><li class="fa fa-sort"></li></span>
					<input type="text" readonly id="hiddenStatus"
						value="${coopVo.status}" class="form-control">
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xs-6">
			<div class="form-group">
				<label>style</label>
				<div class="input-group">
					<span class="input-group-addon"><li class="fa fa-sort"></li></span>
					<input type="text" readonly id="hiddenStyle"
						value="${coopVo.style}" class="form-control">
				</div>
			</div>
		</div>
		<div class="col-md-6 col-xs-6">
			<div class="form-group">
				<label>recruit_email</label>
				<div class="input-group">
					<span class="input-group-addon"><li class="fa fa-sort"></li></span>
					<input type="text" readonly id="hiddenRecruit_email"
						value="${coopVo.recruit_email}" class="form-control">
				</div>
			</div>
		</div>
	</div>
	</section>
</body>
</html>