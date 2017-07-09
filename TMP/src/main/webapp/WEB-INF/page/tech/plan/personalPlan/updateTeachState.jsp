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
<s:include value="../../../common.jsp"></s:include>
<!-- 页面js -->
<script
	src="${pageContext.request.contextPath}/tech/plan/personalPlan/updateTeachState.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/tech/plan/personalPlan/${system_lang}.js"></script>
<style type="text/css">
	.content {
    	min-height: 0px;
	}
	html, body {
    	min-height: 0px;
	}
</style>
<title>授课状态修改</title>
</head>
<!-- onload="window.print();" -->
<body>
	<!-- Main content -->
	<section class="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5" id="content">
	<div class="box box-primary" style="margin-bottom: 0px;" id="box">
		<form id="searchForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<!-- 知识点 -->
							<label><!-- 知识点 --><s:property value="getText('ppVo.point')"/></label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-bookmark"></i>
								</div>
								<input type="text" class="form-control pull-right" name="point" id="point" value="${planVo.point }" readonly>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<!-- 授课地点 -->
							<label><!-- 授课地点 --><s:property value="getText('ppVo.location')"/></label> <select
								class="form-control select" name="location" id="location"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<!-- 开始时间 -->
							<label><!-- 开始时间 --><s:property value="getText('ppVo.startTime')"/></label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control pull-right"
									name="starttime" id="starttime" value="">
							</div>
						</div>
						<!-- /.form-group -->
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<!-- 结束时间 -->
							<label><!-- 结束时间 --><s:property value="getText('ppVo.endTime')"/></label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control pull-right"
									name="endtime" id="endtime" value="">
							</div>
						</div>
						<!-- /.form-group -->
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6" data-toggle="tooltip" title="<s:text name="ppVo.teachStateTip"/>">
						<div class="form-group">
							<!-- 授课状态 -->
							<label><!-- 授课状态 --><s:property value="getText('ppVo.teachState')"/></label> <select
								class="form-control select" name="finish_state" id="finish_state" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
						<!-- /.form-group -->
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
						<button type="button" id="updateButton" class="btn btn-block btn-success btn-sm">
							<li class="fa fa-check"></li>&nbsp;&nbsp;<s:text name="updateButton"/>
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	</section>
	<!-- /.content -->
</body>
</html>
<input type="hidden" value="${planVo.starttime}" id="starttime_hidden" />
<input type="hidden" value="${planVo.endtime}" id="endtime_hidden" />
<input type="hidden" value="${planVo.point_id}" id="point_id_hidden" />
<input type="hidden" value="${planVo.class_id}" id="class_id_hidden" />
<input type="hidden" value="${planVo.location_id}" id="location_id_hidden" />
<input type="hidden" value="${planVo.finish_state}" id="finish_state_hidden" />
<input type="hidden" value="${planVo.id}" id="plan_id_hidden" />
<input type="hidden" value="${planVo.course_id}" id="course_id_hidden" />
