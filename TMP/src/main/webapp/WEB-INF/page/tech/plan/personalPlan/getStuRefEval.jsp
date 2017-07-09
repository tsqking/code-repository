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
<!-- 页面js国际化 -->
<script src="${pageContext.request.contextPath}/tech/plan/personalPlan/${system_lang}.js"></script>
<title>学员平时表现 小结</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<form class="form-horizontal" id="form">
			<div class="row" style="margin-top:.8em;">
					<label for="avg_star" class="col-md-4 col-xs-4 col-md-offset-1 col-xs-offset-1 control-label"><s:text name="pp.avgScore"/></label>
					<div class="col-md-5 col-xs-5">
						<input type="text" class="form-control" id="avg_star" value="" style='background:Transparent;border:1px;' readonly>
					</div>
					<div class="col-md-2 col-xs-2"></div>
            </div>
            <div class="row">
					<label for="avg_star" class="col-md-4 col-xs-4 col-md-offset-1 col-xs-offset-1 control-label"><s:text name="pp.attendInfo"/></label>
					<div class="col-md-5 col-xs-5">
						<input type="text" class="form-control" id="toBe" value="" style='background:Transparent;border:0px;' readonly>
					</div>
					<div class="col-md-2 col-xs-2"></div>
            </div>
            <div class="row">
					<label for="avg_star" class="col-md-4 col-xs-4 col-md-offset-2 col-xs-offset-2 control-label"><s:text name="pp.fullAttend"/></label>
					<div class="col-md-5 col-xs-5">
						<input type="text" class="form-control" id="full" value="" style='background:Transparent;border:0px;' readonly>
					</div>
					<div class="col-md-1 col-xs-1"></div>
            </div>
            <div class="row">
					<label for="avg_star" class="col-md-4 col-xs-4 col-md-offset-2 col-xs-offset-2 control-label"><s:text name="pp.halfAttend"/></label>
					<div class="col-md-5 col-xs-5">
						<input type="text" class="form-control" id="half" value="" style='background:Transparent;border:0px;' readonly>
					</div>
					<div class="col-md-1 col-xs-1"></div>
            </div>
            <div class="row">
					<label for="avg_star" class="col-md-4 col-xs-4 col-md-offset-2 col-xs-offset-2 control-label"><s:text name="pp.holiday"/></label>
					<div class="col-md-5 col-xs-5">
						<input type="text" class="form-control" id="holiday" value="" style='background:Transparent;border:0px;' readonly>
					</div>
					<div class="col-md-1 col-xs-1"></div>
            </div>
            <div class="row">
					<label for="avg_star" class="col-md-4 col-xs-4 col-md-offset-2 col-xs-offset-2 control-label"><s:text name="pp.absence"/></label>
					<div class="col-md-5 col-xs-5">
						<input type="text" class="form-control" id="absence" value="" style='background:Transparent;border:0px;' readonly>
					</div>
					<div class="col-md-1 col-xs-1"></div>
            </div>
		</form>
	</section>
<input type="hidden" value="${procVo.course_id}" id="course_id_hidden" />
<input type="hidden" value="${procVo.class_id}" id="class_id_hidden" />
<input type="hidden" value="${procVo.student_id}" id="student_id_hidden" />
<input type="hidden" value="${planVo.teach_plan_id}" id="plan_id_hidden" />
</body>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name);
$(function() {
	$.ajax({
		url : "../tech/personalPlan!getStuRefEval.do",
		type : "post",
		data:{
			"procVo.course_id":$("#course_id_hidden").val(),
			"procVo.class_id":$("#class_id_hidden").val(),
			"procVo.student_id":$("#student_id_hidden").val(),
			"planVo.teach_plan_id":$("#plan_id_hidden").val()
		},
		success : function(data) {
			if(data.success=='true'){
				var info=data.datas.info;
				//avg_score full half holiday absence record tobe
				//avg_star toBe full half holiday absence
				$("#avg_star").val(info.avg_score);
				$("#toBe").val(shouldToBe+info.tobe+times);
				$("#full").val(info.full+times);
				$("#half").val(info.half+times);
				$("#holiday").val(info.holiday+times);
				$("#absence").val(info.absence+times);
				if(info.record<info.tobe){
					layer.msg(incompleteRecordTip);
				}
			}else{
				layer.alert(data.message,{title:feedback});
			}
		}
	});
});

/**
 * 关闭按钮
 */
$(document).on("click","#closeButton",
	function() {
		parent.layer.close(index);
	}
);
</script>
</html>




