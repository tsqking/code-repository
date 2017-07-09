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
<link rel="shortcut icon" href="${pageContext.request.contextPath}/common/image/logo.ico" />
<s:include value="../../../common.jsp"></s:include>
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/tech/plan/personalPlan/${system_lang}.js"></script>
<!-- 星评 -->
<script src="${pageContext.request.contextPath}/common/plugins/3DRotate/js/jquery.ratyli.min.js"></script>
<style type="text/css">
	/* all sign style */
	.ratyli .rate{color: #a94039; font-size: 18px;}
	
	/* empty sign style */
	.ratyli .rate-empty{color: #666;}
	
	/* full sign style after rating*/
	.ratyli.rated .rate-full{color: #a94039;}
	
	/* active signs (hover)*/
	.ratyli .rate-active{color: #a94039;}
</style>


<script src="${pageContext.request.contextPath}/tech/plan/personalPlan/evalTeachers.js"></script>
<title>评价教师</title>
</head>
<body>
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5" id="content">
		<div class="box box-primary">
			<div class="box-body">
				<div class="row">
					<div class="col-md-5 col-xs-5">
						<label><s:text name="pp.myTeacher"/></label>
					</div>
					<div class="col-md-7 col-xs-7">
						<select class="form-control select"  id="teachers" style="width: 100%;">
							<option value=""></option>
						</select>
					</div>
				</div>
				<hr>
				<div class="row" style="margin-top:.4em;">
					<div class="col-md-3 col-xs-3 col-md-offset-1 col-xs-offset-1">
						<label><s:text name="pp.score"/></label>
					</div>
					<div class="col-md-7 col-xs-7">
						<span class="ratyli" id="star" data-ratemax="10" ></span>
					</div>
				</div>
				<div class="row">
					<div class="col-md-10 col-xs-10 col-md-offset-1 col-xs-offset-1">
						<label><s:text name="pp.iwantsay"/></label>
		        	</div>
				</div>
				<div class="row">
					<div class="col-md-10 col-xs-10 col-md-offset-1 col-xs-offset-1">
	                	<textarea class="form-control" id="comment" rows="4" placeholder="Your options or suggestions..."></textarea>
		        	</div>
				</div>
				<div class="row" style="margin-top:.4em;">
					<div class="col-md-3 col-xs-3 col-md-offset-8 col-xs-offset-8">
	                	<button type="button" id="saveButton" style="display:none;"
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-check"></i>&nbsp;&nbsp;&nbsp;<s:text name="save"/></button>
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
