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
	src="${pageContext.request.contextPath}/campusRecruit/univ/university/batchAddUniversity.js"></script>
<script
	src="${pageContext.request.contextPath}/campusRecruit/univ/university/${system_lang}.js"></script>
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
		<form id="uploadFileForm">
			<div class="box-body">
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="universityList"><s:text
									name="university.uploadUniversity" /></label>
							<button type="button" class="btn btn-block btn-default btn-sm"
								onclick="document.getElementById('universityList').click();return false;">
								<li class="glyphicon glyphicon-plus"></li>&nbsp;&nbsp;
								<s:text name="chooseFile" />
							</button>
							<p class="help-block" id="universityList_name">&nbsp;</p>
							<input type="file" id="universityList" style="display: none;"
								name="file.file" onchange="change(this,'universityList')">
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="universityList"><s:text
									name="university.downloadmodel" /></label>
							<button type="button" class="btn btn-block btn-default btn-sm" id="downloadmodel">
								<li class="fa fa-cloud-download"></li>&nbsp;&nbsp;
								<s:text name="excelModel" />
							</button>

						</div>
					</div>
					<%-- <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="universityList"></label> <a
								href="${pageContext.request.contextPath}/univ/university!downloadmodel.do">下载模板文件</a>
						</div>
					</div> --%>

				</div>
			
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12" style="margin-bottom:2px;margin-top:2px;">
						<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
							<button type="button" id="closeButton" class="btn btn-primary" style="width:100%;"
								onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">
								<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;
								<s:text name="closeButton" />
							</button>
						</div>
						<div class="col-md-3 col-xs-3">
							<button type="button" id="batchUpload" class="btn btn-primary" style="width:100%;">
								<i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;&nbsp;
								<s:text name="university.upload" />
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