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
	src="${pageContext.request.contextPath}/tech/plan/planDetailInfo.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/tech/plan/${system_lang}.js"></script>
<title>计划详细界面</title>
</head>
<!-- onload="window.print();" -->
<body>
	<!-- Main content -->
	<section class="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<div class="box box-primary" style="margin-bottom: 0px;">
		<form id="searchForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<!-- 知识点 -->
							<label><!-- 知识点 --><s:property value="getText('plan.point')"/></label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-bookmark"></i>
								</div>
								<input type="text" class="form-control pull-right" name="point"
									id="point">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<!-- 授课老师 -->
							<label><!-- 授课老师 --><s:property value="getText('plan.teacher')"/></label> <select
								class="form-control select" name="teacher_id" id="teacher_id"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
						<!-- /.form-group -->
					</div>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<!-- 开始时间 -->
							<label><!-- 开始时间 --><s:property value="getText('plan.starttime')"/></label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control pull-right"
									name="starttime" id="starttime">
							</div>
						</div>
						<!-- /.form-group -->
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<!-- 结束时间 -->
							<label><!-- 结束时间 --><s:property value="getText('plan.endtime')"/></label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control pull-right"
									name="endtime" id="endtime">
							</div>
						</div>
						<!-- /.form-group -->
					</div>
				</div>
				<div class="row">
					<div class="col-md-3">
						<div class="form-group">
							<!-- 授课时长(小时) -->
							<label><!-- 授课时长.小时 --><s:property value="getText('plan.hours')"/></label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-clock-o"></i>
								</div>
								<input type="text" class="form-control pull-right" name="hours"
									id="hours" onchange="changeEndTime()">
							</div>
						</div>
						<!-- /.form-group -->
					</div>

					<div class="col-md-3">
						<div class="form-group">
							<!-- 授课时长(分钟) -->
							<label><!-- 授课时长.分钟 --><s:property value="getText('plan.mins')"/></label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-clock-o"></i>
								</div>
								<input type="text" class="form-control pull-right" name="mins"
									id="mins" onchange="changeEndTime()">
							</div>
						</div>
						<!-- /.form-group -->
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<!-- 知识点状态 -->
							<label><!-- 知识点状态 --><s:property value="getText('plan.teachStatus')"/></label> <select
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
				<table class="table text-center" style="margin-bottom: 0px;">
					<tr>
						<td style="width: 200px;"></td>
						<td style="width: 200px;"></td>
						<td style="width: 200px;"></td>
						<td style="width: 200px;"></td>
						<td style="width: 200px;">
							<button type="button" id="closeIframe"
								class="btn btn-block btn-primary btn-sm">
								<!-- 关闭详细信息窗口 -->
								<li class="glyphicon glyphicon-remove"></li>&nbsp;&nbsp;
								<!-- 关闭详细信息窗口 --><s:property value="getText('plan.closeDetailWin')"/>
							</button>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	</section>
	<!-- /.content -->
</body>
</html>

<input type="hidden" value="${planVo.starttime}" id="planVo_starttime" />
<input type="hidden" value="${planVo.endtime}" id="planVo_endtime" />
<input type="hidden" value="${planVo.teacher_id}" id="planVo_teacher_id" />
<input type="hidden" value="${planVo.teacher_name}" id="planVo_teacher_name" />
<input type="hidden" value="${planVo.point}" id="planVo_point" />
<input type="hidden" value="${planVo.point_id}" id="planVo_point_id" />
<input type="hidden" value="${planVo.finish_state}" id="planVo_finish_state" />
<input type="hidden" value="${planVo.id}" id="planVo_id" />
