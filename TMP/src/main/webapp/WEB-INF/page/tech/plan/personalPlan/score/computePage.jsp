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
<s:include value="../../../../common.jsp"></s:include>
<!-- 页面js -->
<script
	src="${pageContext.request.contextPath}/tech/plan/personalPlan/score/computePage.js"></script>
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
<title>计算成绩</title>
</head>
<body>
	<section class="content" id="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<!-- 教学计划信息 -->
	<div class="callout callout-info"
		style="margin-bottom: 6px;margin-top: 0px;margin-left: 0px;margin-right: 0px;">
		<h4>
			<s:text name="teah.plan" />
		</h4>
		<div id="contacts"></div>
	</div>
	<input id="planId" value="${planPaperVo.plan_id}" style="display: none" />
	<input id="class_id" name="class_id" value="${planPaperVo.class_id}" style="display: none" />
	<input id="course_id" name="course_id" value="${planPaperVo.course_id}" style="display: none" />
	<!-- Custom Tabs -->
	<div class="nav-tabs-custom">
		<ul class="nav nav-tabs">
			<li id='li1' class="active"><a href="#tab_1" data-toggle="tab" id='tag1' 
				aria-expanded="true"><strong><s:text name="computePage_64_step1"/></strong></a></li><!-- computePage_64_step1:步骤一:选择期末试卷 -->
			<li id='li2' style="display:none" class=""><a href="#tab_2" data-toggle="tab" id='tag2' 
				aria-expanded="false"><strong><s:text name="computePage_64_step2"/></strong></a></li><!-- computePage_64_step2:步骤二:设置平时成绩加权比例 -->
			<li id='li3' style="display:none" class=""><a href="#tab_3" data-toggle="tab" id='tag3' 
				aria-expanded="false"><strong><s:text name="computePage_64_step3"/></strong></a></li><!-- computePage_64_step3:步骤三:设置态度分 -->
			<li id='li4' style="display:none" class=""><a href="#tab_4" data-toggle="tab" id='tag4'
				aria-expanded="false"><strong><s:text name="computePage_64_step4"/></strong></a></li><!-- computePage_64_step4:步骤四:设置总分加权比例 -->
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tab_1">
				<div id="toolbar" class="btn-group">
					<button id="btn_no_paper" type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>
						<s:text name="computePage_64_no_paper"/><!-- computePage_64_no_paper:无期末测试卷 -->
					</button>
					<button id="btn_to_2" type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
						<s:text name="computePage_64_next"/><!-- computePage_64_next:下一步 -->
					</button>
					<button id="btn_go_back_1" type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
						<s:text name="return" />
					</button>
				</div>
				<!-- 数据表格 -->
				<div class="row">
					<div class="col-xs-12">
						<div class="">
							<blockquote style="margin-bottom: 5px;">
               		 			<p class="text-green"><s:text name="computePage_64_last_paper"/>:<!-- computePage_64_last_paper:期末综合测试卷 -->
               		 			<span id='last_paper'><s:text name="computePage_64_no_last_paper"/></span></p><!-- computePage_64_no_last_paper:无期末综合测试卷 -->
               		 			<small><s:text name="computePage_64_last_paper_info"/></small><!-- computePage_64_last_paper_info:请选择一张试卷作为期末综合测试卷,也可以不选择直接跳过下一步 -->
              				</blockquote>
							<div class="">
								<table id="table"
									class="table table-bordered table-striped table-hover"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.tab-pane -->
			<div class="tab-pane" id="tab_2">
				<div id="toolbar1" class="btn-group">
					<button id="btn_de_ratio" type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-saved" aria-hidden="true"></span>
						<s:text name="computePage_64_btn_de_ratio"/><!-- computePage_64_btn_de_ratio:默认加权 -->
					</button>
					<button id="btn_to_3" type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
						<s:text name="computePage_64_next_64"/><!-- computePage_64_next_64:下一步 -->
					</button>
					<button id="btn_go_back_2" type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
						<s:text name="return" />
					</button>
				</div>
				<!-- 数据表格 -->
				<div class="row">
					<div class="col-xs-12">
						<div class="">
							<blockquote style="margin-bottom: 5px;">
               		 			<p class="text-green"><s:text name="computePage_64_set_ratio"/></p><!-- computePage_64_set_ratio:设置每张试卷的分数比例 -->
               		 			<small><s:text name="computePage_64_set_ratio_info"/></small><!-- computePage_64_set_ratio_info:比例为大于0的正数,点击设置输入数字,默认比例为1 -->
              				</blockquote>
							<div class="">
								<table id="table1"
									class="table table-bordered table-striped table-hover"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.tab-pane -->
			<div class="tab-pane" id="tab_3">
				<div id="toolbar2" class="btn-group">
					<button id="btn_de_att" type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-saved" aria-hidden="true"></span>
						<s:text name="computePage_64_btn_de_att"/><!-- computePage_64_btn_de_att:推荐打分 -->
					</button>
					<button id="btn_to_4" type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
						<s:text name="computePage_64_next_17"/><!-- computePage_64_next_17:下一步 -->
					</button>
					<button id="btn_go_back_3" type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
						<s:text name="return" />
					</button>
				</div>
				<!-- 数据表格 -->
				<div class="row">
					<div class="col-xs-12">
						<div class="">
							<blockquote style="margin-bottom: 5px;">
								<p class="text-green"><s:text name="computePage_64_set_att"/></p><!-- computePage_64_set_att:为班级内每个学生打态度分 -->
								<small><s:text name="computePage_64_set_att_info"/></small><!-- computePage_64_set_att_info:态度分为百分制分数,请根据学生平时学习态度设置 -->
							</blockquote>
							<div class="">
								<table id="table2"
									class="table table-bordered table-striped table-hover"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.tab-pane -->
			<div class="tab-pane" id="tab_4">
				<div class="row">
					<div class="col-xs-12">
						<div class="">
							<blockquote style="margin-bottom: 5px;">
								<p class="text-green"><s:text name="computePage_64_set_total_ratio"/></p><!-- computePage_64_set_total_ratio:设置总成绩计算比例 -->
								<small><s:text name="computePage_64_set_total_ratio_info"/></small><!-- computePage_64_set_total_ratio_info:默认比例为 3:1:1:5,即为平时成绩占0.3,考勤占0.1,态度占0.1,期末分数占0.5 -->
							</blockquote>
							<div class="">
								<div id="toolbar3" class="btn-group" style="margin-top: 5px;margin-bottom: 5px;">
									<button id="btn_de" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-saved"
											aria-hidden="true"></span>
										<s:text name="computePage_64_btn_de"/><!-- computePage_64_btn_de:默认比例 -->
									</button>
									<button id="btn_finish" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-ok"
											aria-hidden="true"></span>
										<s:text name="computePage_64_btn_finish"/><!-- computePage_64_btn_finish:完成 -->
									</button>
									<button id="btn_go_back_4" type="button"
										class="btn btn-default">
										<span class="glyphicon glyphicon-arrow-left"
											aria-hidden="true"></span>
										<s:text name="return" />
									</button>
								</div>
							</div>
							<form id="ratioForm">
							<input name='totalScoreRatioVo.plan_id' value="${planPaperVo.plan_id}" style="display: none" />
							<div class="">	
							<div class="row">
								<div class="col-xs-6">
								<div class="form-group">
									<label for="normal_ratio"><s:text name="computePage_64_normal_ratio"/><small><!-- computePage_64_normal_ratio:平时分数比例 -->
									<i class="fa fa-star notNull"></i></small></label>
									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-fw fa-pie-chart"></i>
										</div>
										<input type="text" name="totalScoreRatioVo.normal_ratio"
											id="normal_ratio" class="form-control" style="width: 100%;">
									</div>
								</div>
								</div>
								<div class="col-xs-6">
								<div class="form-group">
									<label for="attendance_ratio"><s:text name="computePage_64_attendance_ratio"/><small><!-- computePage_64_attendance_ratio:考勤分数比例 -->
									<i class="fa fa-star notNull"></i></small></label>
									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-fw fa-pie-chart"></i>
										</div>
										<input type="text" name="totalScoreRatioVo.attendance_ratio"
											id="attendance_ratio" class="form-control" style="width: 100%;">
									</div>
								</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-6">
								<div class="form-group">
									<label for="attitude_ratio"><s:text name="computePage_64_attitude_ratio"/><small><!-- computePage_64_attitude_ratio:态度分数比例 -->
									<i class="fa fa-star notNull"></i></small></label>
									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-fw fa-pie-chart"></i>
										</div>
										<input type="text" name="totalScoreRatioVo.attitude_ratio"
											id="attitude_ratio" class="form-control" style="width: 100%;">
									</div>
								</div>
								</div>
								<div class="col-xs-6">
								<div class="form-group">
									<label for="exam_ratio"><s:text name="computePage_64_exam_ratio"/><small><!-- computePage_64_exam_ratio:期末试卷分数比例 -->
									<i class="fa fa-star notNull"></i></small></label>
									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-fw fa-pie-chart"></i>
										</div>
										<input type="text" name="totalScoreRatioVo.exam_ratio"
											id="exam_ratio" class="form-control" style="width: 100%;">
									</div>
								</div>
								</div>
							</div>			
							</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- /.tab-pane -->
		</div>
		<!-- /.tab-content -->
	</div>
	<!-- nav-tabs-custom --> 
	</section>
</body>
</html>
