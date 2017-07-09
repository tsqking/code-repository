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
<!-- 页面js国际化 -->
<script src="${pageContext.request.contextPath}/tech/course/${session.system_lang}.js"></script>
<title>课程-添加技能</title>
<style type="text/css">
.content {
    min-height: 100px;
}
</style>
</head>
<body>
	<!-- Main content -->
	<section class="content" id="content" style="padding-top: 5px;padding-bottom: 1px;background-color:#ecf0f5">
		<div class="box" id="box">
			<form id="addSkillPointForm">
				<div class="box-header">
					<s:text name="course.addSkillPointTitle">
						<s:param>${courseVo.name}</s:param>
					</s:text>
				</div>
				<div class="box-body">
					<div class="row">
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label><s:text name="firstSkill"/></label> <select class="form-control select"
									name="pointVo.first_skill_id" id="first_skill_id"
									style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label><s:text name="secondSkill"/></label> <select class="form-control select"
									name="pointVo.second_skill_id" id="second_skill_id"
									style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label><s:text name="thirdSkill"/></label> <select class="form-control select"
									name="pointVo.third_skill_id" id="third_skill_id"
									style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label><s:text name="point"/></label> <select class="form-control select"
									name="pointVo.id" id="point_id"
									style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
					</div>
					<!-- 隐藏 -->
					<div class="row" style="display:none;"> 
						<div class="col-md-12 col-xs-12">
							<div class="form-group">
				            	<label>ID</label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-file-o"></li></span>
				                	<input type="text" name="courseVo.id" id="courseId" class="form-control" value="${courseVo.id}">
				              	</div>
				            </div>
						</div>
					</div>
				</div>
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12" style="margin-bottom:2px;">
						<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
							<button type="button" id="closeButton_addSkillPointModel" 
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/></button>
						
						</div>
						<div class="col-md-3 col-xs-3">
							<button type="button" id="addButton_addSkillPointModel" 
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-check"></i>&nbsp;&nbsp;&nbsp;<s:text name="addButton"/></button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</section>
	
</body>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name);
/* window.onload = function(){
	adjust();
};
function adjust(){
	var h = document.getElementById("box").offsetHeight;
	content = document.getElementById("content");
	content.style.height = (h + 2) + "px";
	document.body.clientHeight=(h + 2) + "px";
} */
$(function() {
	parent.layer.iframeAuto(index);
	//下拉框初始化
	selectInitial("first_skill_id", "../tech/skill!findSkillOption.do?skillVo.parent_id=0", "", false);
	//获取二级联下拉框
	$('#first_skill_id').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
			return;
		}
		selectInitial("second_skill_id","../tech/skill!findSkillOption.do?skillVo.parent_id="+selectedOption,"",false);
		var second=$("#second_skill_id").val();
		var third=$("#third_skill_id").val();
		selectInitial("point_id","../tech/point!getPointOptionBySkillIds.do?pointVo.first_skill_id="+selectedOption+"&pointVo.second_skill_id="+second+"&pointVo.third_skill_id="+third,"",false);
	});
	//获取三级下拉框
	$('#second_skill_id').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
			return;
		}
		selectInitial("third_skill_id","../tech/skill!findSkillOption.do?skillVo.parent_id="+selectedOption,"",false);
		var first=$("#first_skill_id").val();
		var third=$("#third_skill_id").val();
		selectInitial("point_id","../tech/point!getPointOptionBySkillIds.do?pointVo.first_skill_id="+first+"&pointVo.second_skill_id="+selectedOption+"&pointVo.third_skill_id="+third,"",false);
	});
	//获取四级下拉框
	$('#third_skill_id').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
			return;
		}
		var first=$("#first_skill_id").val();
		var second=$("#second_skill_id").val();
		selectInitial("point_id","../tech/point!getPointOptionBySkillIds.do?pointVo.first_skill_id="+first+"&pointVo.second_skill_id="+second+"&pointVo.third_skill_id="+selectedOption,"",false);
	});
});
/**
 * 关闭 课程添加技能知识点 Model
 */
$(document).on("click","#closeButton_addSkillPointModel",
	function() {
		parent.layer.close(index);
	}
);
/**
 * 为课程添加技能知识点按钮
 */
$(document).on("click","#addButton_addSkillPointModel",
	function() {
		var first=$("#first_skill_id").val();
		var pointId=$("#point_id").val();
		if(first==null || first==""){
			layer.alert(tip_model_must_first);
			return;
		}
		if(pointId==null || pointId==""){
			layer.open({
				title: [
					tip,
				 	'background-color:#3C8DBC; color:#ffffff;'
				],
			    content: tip_model_only_point,
			    btn: [confirm,cancel],
			    shadeClose: false,
			    yes: function(){
			    	addSkillPoint();
			    }
			});
		}else{
			addSkillPoint();
		}
	}
);
function addSkillPoint(){
	$.ajax({
		url:"../tech/course!addSkillPoint.do",
		data: $("#addSkillPointForm").serialize(),
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.success=='true'){
				if(data.message=='0'){
					parent.$("#skillTable").DataTable().draw();
	        		layer.msg(success_add_skill_point);
				}else if(data.message=='1'){
					layer.alert(success_invalid_add_skill_point);//添加成功，但是技能下没知识点，无效操作
				}
			}else{
				layer.msg(data.message,{shift: 6});
			}
		}
	}); 
}
</script>
</html>




