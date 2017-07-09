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
<script src="${pageContext.request.contextPath}/tech/plan/personalPlan/teacherReviewPlan.js"></script>
<!-- 页面js国际化 -->
<script src="${pageContext.request.contextPath}/tech/plan/personalPlan/${session.system_lang}.js"></script>
<style type="text/css">
	/* header hover */
	.skin-blue .main-header .navbar .nav>li>a:hover, .skin-blue .main-header .navbar .nav>li>a:active, .skin-blue .main-header .navbar .nav>li>a:focus, .skin-blue .main-header .navbar .nav .open>a, .skin-blue .main-header .navbar .nav .open>a:hover, .skin-blue .main-header .navbar .nav .open>a:focus, .skin-blue .main-header .navbar .nav>.active>a {
	    background: rgb(60, 141, 188);
	    color: #f6f6f6;
	}
</style>
<title>教学计划</title>
</head>
<body class="hold-transition skin-blue layout-top-nav">
	<div class="wrapper">
		<header class="main-header">
			<nav class="navbar navbar-static-top">
				<div class="container">
					<div class="navbar-header">
						<a href="javascript:void(0)" class="navbar-brand"><b>TMP</b></a>
		        	</div>
		        	<div class="collapse navbar-collapse pull-left" id="navbar-collapse">
						<ul class="nav navbar-nav">
							<li><a href="javascript:void(0)">${planVo.course_id_name }</a></li>
							<li><a href="javascript:void(0)">${planVo.class_id_name }</a></li>
							<li><a href="javascript:void(0)">${planVo.location }</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</header>
		<div class="content-wrapper">
			<div class="container">
				<section class="content-header">
					<h1>
						<s:text name="pp.tReviewPlanTitle"/> 
						<small><s:text name="pp.tReviewPlanTitles"/></small>
					</h1>
				</section>
				<section class="content">
					<!-- 隐藏信息 -->
					<div style="display:none;">
						<input type="hidden" value="${planVo.teach_plan_id}" id="teach_plan_id_hidden"/>
						<input type="hidden" value="${planVo.course_id}" id="course_id_hidden"/>
						<input type="hidden" value="${planVo.class_id}" id="class_id_hidden"/>
						<input type="hidden" value="${planVo.location_id}" id="location_id_hidden"/>
					</div>
					<div class="col-md-12">
						<div class="box" style="margin-bottom: 0px;">
							<div class="box-header"></div>
							<div class="box-body" id="treeviewIdDiv">
								<div id="treeview" class="treeview">
						            
								</div>
							</div>
							<div class="box-footer" style="padding: 0 0 0 0">
								<div class="col-md-12 col-xs-12">
									<div class="col-md-3 col-xs-3 col-md-offset-5 col-xs-offset-5" style="margin-top:2px;margin-bottom:2px;">
										<select class="form-control select" id="select-expand-all-levels" style="margin-top:10px">
							            	<option value="1">1</option>
							            	<option value="2">2</option>
							            	<option value="3" selected>3</option>
						        		</select>
									</div>
									<div class="col-md-2 col-xs-2" style="margin-top:7px;">
										<button type="button" class="btn btn-block btn-default btn-sm" id="btn-expand-all"><li class="fa fa-expand"></li></button>
									</div>
									<div class="col-md-2 col-xs-2" style="margin-top:7px;">
										<button type="button" class="btn btn-block btn-default btn-sm" id="btn-collapse-all"><li class="fa fa-compress"></li></button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
		<footer class="main-footer">
			<div class="container">
				<div class="pull-right hidden-xs">
					<b>Version</b> 1.0.0
				</div>
				<strong>Copyright &copy; 2016-2018 <a href="http://www.clps.com.cn">CLPS</a>.</strong> All rights reserved.
			</div>
		</footer>
	</div>
</body>
</html>




