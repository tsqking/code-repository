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
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/tech/plan/personalPlan/${system_lang}.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/tech/plan/personalPlan/finalEvalStudent.css">
<script src="${pageContext.request.contextPath}/tech/plan/personalPlan/finalEvalStudent.js"></script>
<!-- Knob -->
<script src="${pageContext.request.contextPath}/common/plugins/knob/jquery.knob.js"></script>
<%-- <style type="text/css">
	/* header hover */
	.skin-blue .main-header .navbar .nav>li>a:hover, .skin-blue .main-header .navbar .nav>li>a:active, .skin-blue .main-header .navbar .nav>li>a:focus, .skin-blue .main-header .navbar .nav .open>a, .skin-blue .main-header .navbar .nav .open>a:hover, .skin-blue .main-header .navbar .nav .open>a:focus, .skin-blue .main-header .navbar .nav>.active>a {
	    background: rgb(60, 141, 188);
	    color: #f6f6f6;
	}
</style> --%>
<title>学生总评价</title>
</head>
<body>
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5" id="content">
		<div class="htmleaf-container">
			<header class="htmleaf-header">
			<h1>
				<s:text name="pp.studentGeneralEval"/>
			</h1>
			<h2><small>${planVo.course_id_name }&nbsp;&nbsp;-&nbsp;&nbsp;${planVo.class_id_name }&nbsp;&nbsp;-&nbsp;&nbsp;${planVo.location }</small></h2>
			</header>
		</div>
		<div id="container">
			<div class="wrapper">
				<div class="item">
					<div class="final_eval_box">
						<div class="row">
							<div class="stu_name">
								<strong><span id="UID_flag"><li class="fa fa-check-square-o" style="color:green;" ></li>&nbsp;&nbsp;&nbsp;</span>Maven-张三丰</strong>
							</div>
						</div>
						<div class="row" style="margin-top:.6em;">
							<div class="col-xs-4 col-md-4 text-left">
			                	<label class="knob-label">总评分</label>
			                	<input type="text" class="knob" id="UID_score" value="70" data-width="80" data-height="80" data-fgColor="#3c8dbc">
			            	</div>
			            	<div class="col-xs-8 col-md-8 text-left">
			                	<div class="form-group">
				                	<label>总评语</label>
				                	<textarea class="form-control" id="UID_comments" rows="4" 
				                				placeholder="Evaluatioon Comments ..."></textarea>
				                </div>
			            	</div>
						</div>
						<div class="row" style="margin-top:.8em;">
							<div class="col-md-12 col-xs-12">
								<div class="col-md-5 col-xs-5">
									<button type="button" class="btn btn-block btn-default btn-sm" onclick="getRef(UID)"><li class="fa fa-link"></li>&nbsp;&nbsp;&nbsp;参考平时</button>
								</div>
								<div class="col-md-5 col-xs-5 col-md-offset-2 col-xs-offset-2">
									<button type="button" class="btn btn-block btn-primary btn-sm" onclick="saveEval(UID)"><li class="fa fa-check"></li>&nbsp;&nbsp;&nbsp;保存</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
<input type="hidden" value="${planVo.teach_plan_id}" id="plan_id_hidden" />
<input type="hidden" value="${planVo.course_id}" id="course_id_hidden" />
<input type="hidden" value="${planVo.class_id}" id="class_id_hidden" />
<input type="hidden" value="${planVo.location_id}" id="location_id_hidden" />
