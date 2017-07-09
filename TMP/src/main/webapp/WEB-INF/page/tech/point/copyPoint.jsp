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
<script src="${pageContext.request.contextPath}/tech/point/${session.system_lang}.js"></script>
<title>复制知识点</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<div class="box box-primary">
			<div class="box-header with-border">
              <h3 class="box-title"><s:text name="setCopyDestiny"/></h3>
            </div>
			<div class="box-body">
				<div class="row">
					<div class="col-md-12 col-xs-12">
						<div class="form-group">
							<label><s:text name="point.firstSkill"/><small><i class="fa fa-star notNull"></i></small></label> 
							<select class="form-control select"
								name="pointVo.first_skill" id="first_skill"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 col-xs-12">
						<div class="form-group">
							<label><s:text name="point.secondSkill"/></label> <select class="form-control select"
								name="pointVo.second_skill" id="second_skill"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 col-xs-12">
						<div class="form-group">
							<label><s:text name="point.thirdSkill"/></label> <select class="form-control select"
								name="pointVo.third_skill" id="third_skill"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>
				<!-- 隐藏 -->
				<div class="row" style="display:none;">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
			            	<label>ID</label>           
			            	<div class="input-group">
			                	<span class="input-group-addon"><li class="fa fa-tag"></li></span>
			                	<input type="text" name="pointVo.id" id="point_id" class="form-control" value="${pointVo.id}">
			              	</div>
			            </div>
					</div>
				</div>
			</div>
			<div class="box-footer" style="padding: 0 0 0 0;" >
				<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
					<div class="col-md-3 col-xs-3  col-md-offset-6 col-xs-offset-6">
						<button type="button" id="closeButton" 
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/></button>
					</div>
					<div class="col-md-3 col-xs-3">
						<button type="button" id="copyButton" 
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-copy"></i>&nbsp;&nbsp;&nbsp;<s:text name="copyButton"/></button>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name);
$(function() {
	parent.layer.iframeAuto(index);
	//下拉框初始化
	selectInitial("first_skill", "../tech/skill!findSkillOption.do?skillVo.parent_id=0", "", false);
	//获取二级联下拉框
	$('#first_skill').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
			return;
		}
		selectInitial("second_skill","../tech/skill!findSkillOption.do?skillVo.parent_id="+selectedOption,"",false);
	});
	//获取三级下拉框
	$('#second_skill').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
			return;
		}
		selectInitial("third_skill","../tech/skill!findSkillOption.do?skillVo.parent_id="+selectedOption,"",false);
	});
});
/**
 * 拷贝
 */
$(document).on("click","#copyButton",
	function() {
		if($("#first_skill").val()==""){
			layer.msg(skill_need);
			return;
		}
		$.ajax({
			url:"../tech/point!copyPoint.do",
			data: {"pointVo.id": $("#point_id").val(),
					"pointVo.first_skill_id":$("#first_skill").val(),
					"pointVo.second_skill_id":$("#second_skill").val(),
					"pointVo.third_skill_id":$("#third_skill").val()},
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.success=='true'){
					parent.$("#pointTable").DataTable().draw();
					parent.layer.msg(success_copy_point);
					parent.layer.close(index);
				}else{
					layer.msg(data.message);
				}
			}
		});
	}
);
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




