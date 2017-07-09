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
	src="${pageContext.request.contextPath}/tech/plan/personalPlan/paper/readPaper.js"></script>
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
<title>查看试卷</title>
</head>
<body>
	<div class="box-body" style="border: 1px solid #E0E0E0;">
		<!-- 主要信息 -->
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<div class="box box-solid">
					<div class="box-header with-border">
						<i class="glyphicon glyphicon-list-alt"></i>
						<h3 class="box-title">${answerInfoVo.paper_id_name}</h3>
						<input style="display:none" id="paper_id" value="${answerInfoVo.paper_id}"/>
						<input style="display:none" id="user_id" value="${answerInfoVo.user_id}"/>
					</div>
					<!-- /.box-header -->
					<div class="box-body clearfix">
						<div class="callout callout-info" style="margin-bottom: 0px;">
							<div class="row">
								<div class="col-md-9 col-xs-9">
									<div class="row">
										<div class="col-md-6 col-xs-6">
											<h4><i class="fa fa-fw fa-user"  data-toggle="tooltip" title="<s:text name="readPaper_66_answer_person"/>" data-placement="bottom"></i>
											<span><!-- readPaper_66_answer_person:答卷人 -->
											&nbsp;&nbsp;${answerInfoVo.user_id_name}</span></h4>
										</div>
										<div class="col-md-2 col-xs-2"><!-- readPaper_66_paper_finish_flag:完成标记 -->
											<p><i class="fa fa-fw fa-flag" data-toggle="tooltip" title="<s:text name="readPaper_66_paper_finish_flag"/>" data-placement="bottom"></i>
											&nbsp;${answerInfoVo.finish_flag_name}</p>
										</div>
										<div class="col-md-2 col-xs-2"> 			<!-- readPaper_66_answer_times:离开次数 -->
											<p><i class="fa fa-fw fa-hand-stop-o" data-toggle="tooltip" title="<s:text name="readPaper_66_answer_times"/>" data-placement="bottom"></i>
											&nbsp;${answerInfoVo.leave_limit}&nbsp;<s:text name="readPaper_66_times"/></p><!-- readPaper_66_times:次 -->
										</div>
									</div>			
									<div class="row">
										<div class="col-md-6 col-xs-6"><!-- readPaper_66_answer_time:答卷时间 -->
											<p><i class="fa fa-fw fa-clock-o" data-toggle="tooltip" title="<s:text name="readPaper_66_answer_time"/>" data-placement="bottom"></i>
											&nbsp;${answerInfoVo.start_time}&nbsp;<s:text name="readPaper_66_to"/> &nbsp;${answerInfoVo.end_time}</p><!-- readPaper_66_to:至 -->
										</div>
										<div class="col-md-2 col-xs-2"><!-- readPaper_66_paper_allscore:试卷总分 -->
											<p><i class="fa fa-fw fa-bookmark-o" data-toggle="tooltip" title="<s:text name="readPaper_66_paper_allscore"/>" data-placement="bottom"></i>
											&nbsp;${answerInfoVo.all_score}&nbsp;<s:text name="readPaper_66_score"/></p><!-- readPaper_66_score:分 -->
										</div>
										<div class="col-md-2 col-xs-2"><!-- readPaper_66_answer_need_time:答卷耗时 -->
											<p><i class="fa fa-fw fa-history" data-toggle="tooltip" title="<s:text name="readPaper_66_answer_need_time"/>" data-placement="bottom"></i>
											&nbsp;${answerInfoVo.cost_time}</p>
										</div>			
									</div>
								</div>
								<div class="col-md-3 col-xs-3">
									<div class="pull-right" data-toggle="tooltip" title="<s:text name="readPaper_66_paper_score"/>" data-placement="bottom"><!-- readPaper_66_paper_score:试卷得分 -->
										<span style="margin-top: 10px; font-size: 36px" id="paper_score">${answerInfoVo.score}</span>
										<span style="font-size: 20px">&nbsp;<s:text name="readPaper_66_score_49"/></span><!-- readPaper_66_score_49:分 -->
										<div></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /.box-header -->
					<!-- 题块 -->
					<div class="box-body" style="padding-top: 0px;" id='sectionInfo'>
						<!-- js生成内容 -->
					</div>
					<div class="btn-group" style="margin-left: 10px;margin-bottom: 8px;">
				      <button id="btn_flash" type="button" class="btn btn-default">
				        <span class="glyphicon glyphicon-retweet" aria-hidden="true"></span>
				        	<s:text name="readPaper_66_reload_button"/>
				      </button>
				      <button id="btn_go_back" type="button" class="btn btn-default">
				        <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
				        	<s:text name="return" /> 
				      </button>
				    </div>
				</div>
			</div>
		</div>
		<div id='question'>
			<!-- js生成内容 -->
		</div>
	</div>
</body>
</html>
