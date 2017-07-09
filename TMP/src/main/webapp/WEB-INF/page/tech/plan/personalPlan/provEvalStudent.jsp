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
<script
	src="${pageContext.request.contextPath}/tech/plan/personalPlan/${system_lang}.js"></script>
<!-- 3D旋转星评 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/plugins/3DRotate/css/default.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/plugins/3DRotate/js/modernizr.custom.34807.js"></script>
<script type="text/javascript">
	if(!Modernizr.csstransforms3d) document.getElementById('information').style.display = 'block';
</script>
<script
	src="${pageContext.request.contextPath}/common/plugins/3DRotate/js/jquery.ratyli.min.js"></script>
<!-- 页面CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/tech/plan/personalPlan/procEvalStudent.css">
<!-- 页面js -->
<script
	src="${pageContext.request.contextPath}/tech/plan/personalPlan/procEvalStudent.js"></script>
<title>过程评价</title>
</head>
<body>
	<section class="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5"
		id="content">
	<div class="htmleaf-container">
		<header class="htmleaf-header">
		<h1>
			${planVo.point } - <s:text name="pp.procEvalTitle"/> 
		</h1>
			<a href="javascript:window.close();" title="<s:text name="pp.closePage"/> " target="_blank"><i class="htmleaf-icon icon-arrow-left3"></i></a>
		</header>
	</div>
	<div>
	<div class="eval">
	<div class="row">
		<div class="col-md-2 col-xs-2">
		</div>
		<div class="col-md-2 col-xs-2">
			<select class="form-control selectAll" id="oneKeyProSelect" style="width: 226px;">
				<option value="1"><s:text name="provEvalStudent_31_sign_1"/></option><!-- provEvalStudent_31_sign_1:全程在场 -->
				<option value="2"><s:text name="provEvalStudent_31_sign_2"/></option><!-- provEvalStudent_31_sign_2:半程在场 -->
				<option value="3"><s:text name="provEvalStudent_31_sign_3"/></option><!-- provEvalStudent_31_sign_3:请假缺席 -->
				<option value="4"><s:text name="provEvalStudent_31_sign_4"/></option><!-- provEvalStudent_31_sign_4:无故缺席 -->
			</select>     
		</div>
		<div class="col-md-2 col-xs-2" agin="left">
			<button type="button" id="oneKeyPro" class="btn btn-block btn-primary btn-sm" style="margin-top: 2px;">
			<i class="fa fa-pencil"></i>&nbsp;&nbsp;&nbsp;<s:text name="provEvalStudent_31_one_key_sign_button"/></button><!-- provEvalStudent_31_one_key_sign_button:一键签到 -->
		</div>
		<div class="col-md-2 col-xs-2" style="margin-top: 4px;">	
			<span id="oneKeyProRatyli" class="ratyli" data-ratemax="10"></span>  
		</div>
		<div class="col-md-2 col-xs-2" agin="left">
			<button type="button" id="oneKeyScore" class="btn btn-block btn-primary btn-sm" style="margin-top: 2px;">
			<i class="fa fa-pencil"></i>&nbsp;&nbsp;&nbsp;<s:text name="provEvalStudent_31_one_key_score_button"/></button><!-- provEvalStudent_31_one_key_score_button:一键评分 -->
		</div>
	</div>
	</div>
	</div>
	<div id="container">
		<div class="wrapper">
			<div class="item">
				<div class="simpleInfo">
					<strong>Name-enName</strong>
				</div>
				<div class="information">
					<div class="eval">
						<div class="row">
							<div class="col-md-6 col-xs-6">
								<h3><s:text name="provEvalStudent_31_sign"/></h3><!-- provEvalStudent_31_sign:签到 -->
								<select class="form-control selectAll" id="uID_sign">
									<option value="1"><s:text name="provEvalStudent_31_sign_1_23"/></option><!-- provEvalStudent_31_sign_1_23:全程在场 -->
									<option value="2"><s:text name="provEvalStudent_31_sign_2_75"/></option><!-- provEvalStudent_31_sign_2_75:半程在场 -->
									<option value="3"><s:text name="provEvalStudent_31_sign_3_72"/></option><!-- provEvalStudent_31_sign_3_72:请假缺席 -->
									<option value="4" selected><s:text name="provEvalStudent_31_sign_4_17"/></option><!-- provEvalStudent_31_sign_4_17:无故缺席 -->
								</select>
							</div>
							<div class="col-md-6 col-xs-6">
								<h3><s:text name="provEvalStudent_31_score"/></h3><!-- provEvalStudent_31_score:评分 -->
								<span id="uID_star" class="ratyli" data-ratemax="10" style="margin-top:10px;"></span>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-xs-12"  style="margin-top:5px;">
								<h3><s:text name="provEvalStudent_31_remark"/></h3> <!-- provEvalStudent_31_remark:评语 -->
								<div class="input-group">
									<div class="input-group-addon"><i class="fa fa-smile-o"></i></div>
									<input id="uID_comment" type="text" class="form-control" placeholder="<s:text name="provEvalStudent_31_placeholder"/>" /><!-- provEvalStudent_31_placeholder:学习情况简述 -->
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-5 col-xs-5 col-md-offset-7 col-xs-offset-7"  style="margin-top:5px;">
								<button type="button" class="btn btn-block btn-default btn-sm" onclick="saveEval()">
								<li class="fa fa-check"></li>&nbsp;&nbsp;&nbsp;<s:text name="provEvalStudent_31_save_button"/></button><!-- provEvalStudent_31_save_button:保存 -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="wrapper">
			<div class="item">
				<div class="simpleInfo">
					<strong>Name-enName</strong>
				</div>
				<div class="information">
					<div class="eval">
						<div class="row">
							<div class="col-md-6 col-xs-6">
								<h3><s:text name="provEvalStudent_31_sgin"/></h3><!-- provEvalStudent_31_sgin:签到 -->
								<select class="form-control selectAll" id="uID_sign">
									<option value="1"><s:text name="provEvalStudent_31_sign_1_17"/></option><!-- provEvalStudent_31_sign_1_17:全程在场 -->
									<option value="2"><s:text name="provEvalStudent_31_sign_2_38"/></option><!-- provEvalStudent_31_sign_2_38:半程在场 -->
									<option value="3"><s:text name="provEvalStudent_31_sign_3_77"/></option><!-- provEvalStudent_31_sign_3_77:请假缺席 -->
									<option value="4" selected><s:text name="provEvalStudent_31_sign_4_15"/></option><!-- provEvalStudent_31_sign_4_15:无故缺席 -->
								</select>
							</div>
							<div class="col-md-6 col-xs-6">
								<h3><s:text name="provEvalStudent_31_score_24"/></h3><!-- provEvalStudent_31_score_24:评分 -->
								<span id="uID_star" class="ratyli" data-ratemax="10" style="margin-top:10px;"></span>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-xs-12"  style="margin-top:5px;">
								<h3><s:text name="provEvalStudent_31_remark_30"/></h3> <!-- provEvalStudent_31_remark_30:评语 -->
								<div class="input-group">
									<div class="input-group-addon"><i class="fa fa-smile-o"></i></div>
									<input id="uID_comment" type="text" class="form-control" placeholder="<s:text name="provEvalStudent_31_placeholder_95"/>" /><!-- provEvalStudent_31_placeholder_95:学习情况简述 -->
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-5 col-xs-5 col-md-offset-7 col-xs-offset-7"  style="margin-top:5px;">
								<button type="button" class="btn btn-block btn-default btn-sm" onclick="saveEval()">
								<li class="fa fa-check"></li>&nbsp;&nbsp;&nbsp;<s:text name="provEvalStudent_31_save"/></button><!-- provEvalStudent_31_save:保存 -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<span id="information" style="display: none">Your browser doesn't support CSS3 3D Transform</span> </section>
</body>
</html>
<input type="hidden" value="${planVo.point_id}" id="point_id_hidden" />
<input type="hidden" value="${planVo.class_id}" id="class_id_hidden" />
<input type="hidden" value="${planVo.course_id}" id="course_id_hidden" />
