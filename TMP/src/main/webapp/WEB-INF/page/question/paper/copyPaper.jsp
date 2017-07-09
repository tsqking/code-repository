<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background-color:#ecf0f5">
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
<!-- js页面 -->
<script
	src="${pageContext.request.contextPath}/question/paper/copyPaper.js"></script>
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/question/paper/${system_lang}.js"></script>
<title></title>
</head>
<body><!-- overflow:hidden; -->
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<div class="box">
			<div class="box-body" id="box">
				<form id="addForm" class="form-horizontal">
					<!-- 试卷Id -->
					<input type="hidden" name="id" id="paperVo_id" value="${paperVo.id}"/>
					<div class="form-group">
						<label for="name1" class="col-sm-2 control-label"><s:text name="name" /><small><i class="fa fa-star notNull"></i></small></label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="name" id="name1">
						</div>
						<div class="col-sm-1"></div>
					</div>
					<div class="form-group">
						<label for="property" class="col-sm-2 control-label"><s:text name="property"/><small><i class="fa fa-star notNull"></i></small></label>
						<div class="col-sm-3">
							<select class="form-control select" type="select-one" name="property" id="property">
								<option value=""></option>
							</select>
						</div>
						<label for="use_flag" class="col-sm-3 control-label"><s:text name="use_flag"/><small><i class="fa fa-star notNull"></i></small></label>
						<div class="col-sm-3">
							<select class="form-control select" type="select-one" name="use_flag" id="use_flag">
								<option value=""></option>
							</select>
						</div>
						<div class="col-sm-1"></div>
					</div>
					<div class="form-group">
						<label for="description" class="col-sm-2 control-label"><s:text name="description"/><small>&nbsp;&nbsp;&nbsp;&nbsp;</small></label>
						<div class="col-sm-9">
							<textarea class="form-control" rows="4" name="description" id="description" placeholder="考生不可见" ></textarea>
						</div>
						<div class="col-sm-1"></div>
					</div>
					<div class="form-group">
						<label for="instruction" class="col-sm-2 control-label"><s:text name="instruction"/><small>&nbsp;&nbsp;&nbsp;&nbsp;</small></label>
						<div class="col-sm-9">
							<textarea class="form-control" rows="4" name="instruction" id="instruction" placeholder="考生可见"></textarea>
						</div>
						<div class="col-sm-1"></div>
					</div>


				<div class="box-footer" style="padding: 2% 4% 0 0;border-top-width: 0px;">
						<div class="row">
							<div class="col-md-12 col-xs-12" style="margin-top:10px;">
								<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
									<button type="button" id="closeButton" onclick="javascript: parent.layer.close(index);"
											class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/></button>
								</div>
								<div class="col-md-3 col-xs-3">
									<button type="button" id="nextButton"
											class="btn btn-block btn-primary btn-sm"><i class="fa fa-fw fa-file"></i>&nbsp;&nbsp;&nbsp;<s:text name="copy"/></button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>
</body>
</html>