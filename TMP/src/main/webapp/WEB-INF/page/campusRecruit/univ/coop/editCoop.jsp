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
	src="${pageContext.request.contextPath}/campusRecruit/univ/coop/editCoop.js"></script>
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
<title></title>
</head>
<body>
	<section class="content" id="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<div class="box box-primary" id="box">
		<form id="editForm">
			<input id="coopId" value="${coopVo.id}" style="display: none" />
			<div class="callout callout-info" style="margin-bottom: 0px;margin-top: 10px;margin-left: 10px;margin-right: 10px;">
                <h4><s:text name="coop.his.nowcoop"/></h4>
                <p>&nbsp;&nbsp;&nbsp;&nbsp;${coopVo.info}</p>
                <h4><s:text name="coop.attn.info"/></h4>
                <span id="contactsJson" style="display: none">${coopVo.contacts}</span>
                <div id="contacts">
                </div>
                <button type="button" class="btn btn-block btn-primary btn-xs" 
                style="margin-top: 10px;" onclick="attn()"><s:text name="coop.attn.edit"/></button>
            </div>
			<div id="timeLine"></div>
			<div class="box-footer" style="padding: 0 0 0 0">
				<div class="col-md-12 col-xs-12" style="margin-bottom: 10px;">
					<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">

					</div>
					<div class="col-md-3 col-xs-3">
						<button type="button" id="closeButton"
							class="btn btn-block btn-primary btn-sm"
							onclick="javascript: parent.refreshTable();parent.layer.close(parent.layer.getFrameIndex(window.name));">
							<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;
							<s:text name="closeButton" />
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	</section>
</body>
</html>