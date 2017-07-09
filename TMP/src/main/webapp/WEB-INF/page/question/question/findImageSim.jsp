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
<script
	src="${pageContext.request.contextPath}/question/question/findImageSim.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/question/question/${system_lang}.js"></script>
<style type="text/css">
.content {
	min-height: 0px;
}
html, body {
	min-height: 0px;
}
</style>
<title>查看图片</title>
</head>
<body>
	<section class="content" id="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<input style="display: none" id="info" value="${questionVo.info}" />
	<div class="box-body" align="center">
		<div class="row" id="text">
			<div class="col-md-12 col-xs-12">
				<label><s:text name="findImageSim_49_image_title"/></label><!-- findImageSim_49_image_title:点击图片即可退回页面 -->
			</div>
		</div>
		<div id="carousel-example-generic" class="carousel slide"
			data-ride="carousel" style="height: 450px;width: 1000px">
			<ol class="carousel-indicators" id="contextOl">
				<!-- 
				<li data-target="#carousel-example-generic" data-slide-to="0"
					class="active"></li>
				<li data-target="#carousel-example-generic" data-slide-to="1"
					class=""></li>
				<li data-target="#carousel-example-generic" data-slide-to="2"
					class=""></li>
				 -->
			</ol>
			<div class="carousel-inner" id="contextImg">
				<!-- 
				<div class="item active">
					<img 
						src="../question/fileAction!getImage.do?fileName=3b08445bf8764fe3863b6a7b30e653ba"
						alt="First slide">
					<div class="carousel-caption">First Slide</div>
				</div>
				<div class="item">
					<img 
						src="../question/fileAction!getImage.do?fileName=6a49aea0fd0f4c3ab546dd2db9f98802"
						alt="Second slide">
					<div class="carousel-caption">Second Slide</div>
				</div>
				<div class="item">
					<img 
						src="../question/fileAction!getImage.do?fileName=796c717d7fcd480581fe2ce7a4c8e8b1"
						alt="Third slide">
					<div class="carousel-caption">Third Slide</div>
				</div>
				 -->
			</div>
			<a class="left carousel-control" href="#carousel-example-generic"
				data-slide="prev"> <span class="fa fa-angle-left"></span>
			</a> <a class="right carousel-control" href="#carousel-example-generic"
				data-slide="next"> <span class="fa fa-angle-right"></span>
			</a>
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
					<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="findImageSim_49_close_page"/></button><!-- findImageSim_49_close_page:关闭页面 -->
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
</script>
</html>
