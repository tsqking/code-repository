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
<s:include value="../../common.jsp"></s:include>
<!-- 页面js -->
<script
	src="${pageContext.request.contextPath}/tech/class/ClassStudent.js"></script>

<script
	src="${pageContext.request.contextPath}/tech/class/batchAddExamNum.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/tech/class/${system_lang}.js"></script>
<title>人员添加班级</title>
</head>
<body>
	<!-- Main content -->

	<section class="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<!-- 搜索条件 -->
	<div class="box box-primary">
	<input type="hidden" id="excelName" /> 
				<input type="hidden"id="excelPath" /> 
		<div class="box-header with-border" data-widget="collapse">
			<i class="fa fa-minus"></i>
			<h3 class="box-title">
				<s:text name="searchTitle" />
			</h3>
		</div>
		<form id="searchForm">
		
			<div class="box-body">
				<input type="hidden" style="display: none;" id="id"
					name="classVo.id" value="${classVo.id }" />
				<div class="row">
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label><s:text name="class.personNo" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-tag"></li></span> <input type="text"
									name="userVo.no" id="no" class="form-control"
									placeholder="<s:text name="class.personNoTip" />">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label><s:text name="class.examNum" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-tag"></li></span> <input type="text"
									name="userVo.exam_num" id="exam_num" class="form-control"
									placeholder="<s:text name="class.examNum" />">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label><s:text name="class.person_name" /></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-tag"></li></span> <input type="text"
									name="userVo.name" id="name" class="form-control"
									placeholder="<s:text name="class.personNameTip" />">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label><s:text name="class.personClass" /></label> <select
								class="form-control select" name="userVo.description"
								id="description" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="box-footer" style="padding: 0 0 0 0">
				<div class="col-md-12 col-xs-12" style="margin-bottom: 10px;margin-left:400px">
					
					<div class="col-md-3 col-xs-3">
						<button type="button" id="closeButton"
							class="btn btn-block btn-info btn-sm"
							onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">
							<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;
							<s:text name="closeWindowButton" />
						</button>
					</div>
					<div class="col-md-3 col-xs-3">
						<button type="button" id="resetButton"
							class="btn btn-block btn-success btn-sm">
							<i class="fa fa-reply"></i>&nbsp;&nbsp;&nbsp;
							<s:text name="resetButton" />
						</button>
					</div>
					<div class="col-md-3 col-xs-3">
						<button type="button" id="searchButton"
							class="btn btn-block btn-primary btn-sm">
							<i class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;
							<s:text name="searchButton" />
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- /.box --> <!-- 数据表格 -->
	<div class="row">
		<div class="col-xs-12">
			<div class="box box-primary" style="margin-bottom: 0px;">
				<div class="box-header">
					<h3 class="box-title">
						<s:text name="class.personList" />
					</h3>
				</div>
				<div class="box-body">
					<div class="row">
						<div class="col-md-12 col-xs-12" style="margin-bottom: 0px;margin-left:120px">
						<!--添加一个录入按钮-->
							<div class="col-md-2 col-xs-2 ">
								<button type="button" id="applyButton" data-toggle="modal" data-target="#batchapplyModel"
									class="btn btn-block btn-primary  btn-sm" 
									>
									<i class="fa fa-reply"></i>&nbsp;&nbsp;&nbsp;
									<s:text name="batchApplyWindow" />
								</button>
							</div>
							<div class="col-md-2 col-xs-2">
								<button type="button" onclick="batchAdd()"
									class="btn btn-block btn-primary btn-sm">
									<i class="fa fa-plus-circle"></i>&nbsp;&nbsp;&nbsp;
									<s:text name="class.addNewPerson" />
								</button>
							</div>
							<div class="col-md-2 col-xs-2">
								<button type="button" class="btn btn-block btn-primary btn-sm"
									data-toggle="modal" data-target="#batchTransfermodal">
									<li class="fa fa-cloud-download"></li>&nbsp;&nbsp;
									<s:property value="getText('batchWordToExel')" />
								</button>
							</div>
							<div class="col-md-2 col-xs-2">
								<button type="button" class="btn btn-block btn-primary btn-sm"
									data-toggle="modal" data-target="#transferModal">
									<li class="fa fa-cloud-download"></li>&nbsp;&nbsp;
									<s:property value="getText('batchAddUserExamNum')" />
								</button>
							</div>
							<div class="col-md-2 col-xs-2">
								<button type="button" class="btn btn-block btn-primary btn-sm"
									onclick="downTemplate()">
									<li class="fa fa-cloud-download"></li>&nbsp;&nbsp;
									<s:property value="getText('batchTemple')" />
								</button>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-xs-12" style="margin-top: 0px;">
							<div class="col-md-2 col-xs-2 col-md-offset-1 col-xs-offset-1"
								data-toggle="tooltip"
								title="<s:text name="class.setMonitorTip" />">
								<div class="input-group margin">
									<input type="text" class="form-control" name="student_monitor"
										id="student_monitor"
										placeholder="<s:text name="class.setMonitorTip" />"> <span
										class="input-group-btn">
										<button type="button" class="btn btn-warning btn-flat"
											onclick="setMonitor('student')">
											<s:text name="class.setSMonitorBT" />
										</button>
									</span>
								</div>
							</div>
							<div class="col-md-2 col-xs-2 col-md-offset-1 col-xs-offset-1"
								data-toggle="tooltip"
								title="<s:text name="class.setMonitorTip" />">
								<div class="input-group margin">
									<input type="text" class="form-control" name="teacher_monitor"
										id="teacher_monitor"
										placeholder="<s:text name="class.setMonitorTip" />"> <span
										class="input-group-btn">
										<button type="button" class="btn btn-warning btn-flat"
											onclick="setMonitor('teacher')">
											<s:text name="class.setTMonitorBT" />
										</button>
									</span>
								</div>
							</div>
							<div class="col-md-2 col-xs-2 col-md-offset-1 col-xs-offset-1">
								<div class="input-group margin">
									<input type="text" class="form-control" name="size" id="size"
										readonly> <span class="input-group-btn">
										<button type="button" class="btn btn-warning btn-flat"
											disabled>
											<s:text name="class.classSizeBT" />
										</button>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-xs-12">
							<table id="personTable"
								class="table table-bordered table-striped table-hover">
								<thead>
									<tr>
										<th><s:text name="class.personNo" /></th>
										<th><s:text name="class.examNum" /></th>
										<th><s:text name="class.personName" /></th>
										<th><s:text name="class.personen_Name" /></th>
										<th><s:text name="class.personGender" /></th>
										<th><s:text name="class.personMobile" /></th>
										<th><s:text name="class.personClass" /></th>
										<th><s:text name="option" /></th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>

	<!-- modal1 -->
	<div class="modal" id="batchAddModel">
		<form id="batchAddForm">
			<div class="modal-dialog" style="width: 40%; height: 20%">
				<div class="modal-content"
					style="width: 100%; height: 100%; margin-top: 25%;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">
							<s:text name="batchAddTitle" />
						</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 col-xs-12">
								<div class="form-group">
									<label><s:text name="batchPeopleList" /></label> <input
										type="file" id="batchPeopleList" name="file.file">
									<p class="help-block">
										<s:text name="batchAddHelp" />
									</p>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="closeButton" class="btn btn-primary">
							<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;
							<s:text name="closeButton" />
						</button>
						<button type="button" id="batchUpload" class="btn btn-primary">
							<i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;&nbsp;
							<s:text name="upload" />
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>

	<!-- modal2 -->
	<div class="modal" id="transferModal">
		<form id="batchAddExamNumForm">
			<div class="modal-dialog" style="width: 40%; height: 20%">
				<div class="modal-content"
					style="width: 100%; height: 100%; margin-top: 25%;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">
							<s:text name="batchAddExamTitle" />
						</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 col-xs-12">
								<div class="form-group">
									<label><s:text name="batchPeopleAllInfoList" /></label> <input
										type="file" id="batchPeopleAllInfoList" name="file.file">
									<p class="help-block">
										<s:text name="batchAddExamNumHelp" />
									</p>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn btn-primary">
							<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;
							<s:text name="closeButton" />
						</button>
						<button type="button" id="batchAddExamNumUpload" class="btn btn-primary">
							<i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;&nbsp;
							<s:text name="upload" />
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- modal 3 ,batch upload word transfer to exel -->
	<div class="modal" id="batchTransfermodal">
		<form id="wordToExelForm">
			<div class="modal-dialog" style="width: 40%; height: 20%">
				<div class="modal-content"
					style="width: 100%; height: 100%; margin-top: 25%;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">
							<s:text name="batchWordToExelTitle" />
						</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 col-xs-12">
								<div class="form-group">
									<label><s:text name="applyWord" /></label> <input
										type="file" id="batchApply" name="files" multiple="true">
									<p class="help-block">
										<s:text name="batchWordToExelHelp" />
									</p>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn btn-primary">
							<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;
							<s:text name="closeButton" />
						</button>
						<button type="button" id="batchTransferBtn" class="btn btn-primary">
							<i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;&nbsp;
							<s:text name="uploadAndRead" />
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- modal 4-->
	<div class="modal" id="batchapplyModel">
		<form id="batchAddForm" >
			<div class="modal-dialog" style="width:40%;height:20%">
				<div class="modal-content"
					style="width: 100%; height: 100%; margin-top: 25%;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title"><s:text name="batchApply"/></h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 col-xs-12">
								<div class="form-group">
				                	<label><s:text name="receiveInfo"/></label>
				                	<input type="file" id="batchPeopleApplyList" name="file.file">
				                	<p class="help-block"><s:text name="ApplyTableHelp"/></p>
				                </div>
							</div>
				    	</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="closeButton" class="btn btn-primary" data-dismiss="modal"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/></button>
						<button type="button" id="batchApplyUpload" class="btn btn-primary"><i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;&nbsp;<s:text name="upload"/></button>
					</div>
				</div>
			</div>
		</form>
	</div>
	
	
	
<script src="../tech/class/wordToExel.js"></script>
</body>
</html>




