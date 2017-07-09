<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<s:include value="../../common.jsp"></s:include>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/common/image/logo.ico" />
<!-- 图片上传 -->
<script src="${pageContext.request.contextPath}/common/plugins/fileinput/js/fileinput.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/plugins/fileinput/js/fileinput_locale_${system_lang}.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/plugins/fileinput/css/default.css">
<link href="${pageContext.request.contextPath}/common/plugins/fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
<script
	src="${pageContext.request.contextPath}/question/question/uploadImage.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/question/question/${system_lang}.js"></script>
<!-- 页面js -->
<style type="text/css">
.content {
	min-height: 0px;
}
html, body {
	min-height: 0px;
}
</style>
<title>上传图片</title>
</head>
<body>
	<section class="content" id="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<input style="display: none" id="question_id" value="${questionVo.id}" />
	<input style="display: none" id="info" value="${questionVo.info}" />
	<div class="row">
		<div class="col-md-12 col-xs-12">
			<label><s:text name="uploadImage_44_title_image"/></label><!-- uploadImage_44_title_image:推荐图片长宽比例 16:9 -->
		</div>
	</div>
	<div class="row">
		<div class="col-md-12 col-xs-12">
			<div class="form-group">
				<form enctype="multipart/form-data">
					<input id="file-1" type="file" name="files" multiple>
				</form>
			</div>
		</div>
	</div>
	<!-- /.box-body -->
	<div class="box-footer" style="padding: 0 0 0 0">
		<div class="col-md-12 col-xs-12"
			style="margin-bottom: 0px; margin-top: 10px;">
			<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
			</div>
			<div class="col-md-3 col-xs-3">
				<button type="button" id="closeButton" style="margin-bottom: 10px;"
					class="btn btn-block btn-primary btn-sm"
					onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">
					<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="uploadImage_44_close_page"/></button><!-- uploadImage_44_close_page:关闭页面 -->
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
</script>
</html>
