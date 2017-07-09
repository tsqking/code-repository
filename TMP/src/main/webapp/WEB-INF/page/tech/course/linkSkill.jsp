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
<title>课程-技能</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<!-- 搜索条件 -->
		<div class="box box-primary">
			<form id="searchForm">
				<div class="box-body">
					<!-- 隐藏 -->
					<div class="row" style="display:none;"> 
						<div class="col-md-12 col-xs-12">
							<div class="form-group">
				            	<label>ID</label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-file-o"></li></span>
				                	<input type="text" name="courseVo.id" id="id" class="form-control" value="${courseVo.id}">
				              	</div>
				            </div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4 col-xs-4">
							<div class="form-group">
								<label><s:text name="firstSkill"/></label> <select class="form-control select"
									name="pointVo.first_skill" id="first_skill"
									style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-4 col-xs-4">
							<div class="form-group">
								<label><s:text name="secondSkill"/></label> <select class="form-control select"
									name="pointVo.second_skill" id="second_skill"
									style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-4 col-xs-4">
							<div class="form-group">
								<label><s:text name="thirdSkill"/></label> <select class="form-control select"
									name="pointVo.third_skill" id="third_skill"
									style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
						<div class="col-md-2 col-xs-2 col-md-offset-6 col-xs-offset-6">
							<button type="button" id="closeButton"
								class="btn btn-block btn-primary btn-sm" onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">
								<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/>
							</button>
						</div>
						<div class="col-md-2 col-xs-2">
							<button type="button" id="resetButton"
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-reply"></i>&nbsp;&nbsp;&nbsp;<s:text name="resetButton"/></button>
						</div>
						<div class="col-md-2 col-xs-2">
							<button type="button" id="searchButton"
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;<s:text name="searchButton"/></button>
						</div>
					</div>
				</div>
			</form>
		
			<div class="box-body">
				<div class="row">
					<div class="col-xs-12 btn-group-vertical">
						<button type="button" onclick="reviewCourseSkillPoint('${courseVo.id }','${courseVo.name }')" class="btn btn-block btn-default">
							<i class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;<s:text name="course.reviewSkillPoint"/></button>
						<button type="button" onclick="toAddNewSkillPoint('${courseVo.id }','${courseVo.name }')" class="btn btn-block btn-default">
							<i class="fa fa-plus-circle"></i>&nbsp;&nbsp;&nbsp;<s:text name="course.addNewPoint"/></button>
					</div>
				</div>
				<br/>
				<div class="row">
					<div class="col-xs-12">
						<div class="box" style="margin-bottom: 5px;">
							<div class="box-header">
								<h3 class="box-title"  >
									<s:text name="course.skillList">
										<s:param>${courseVo.name}</s:param>
									</s:text>
								</h3>
							</div>
							<div class="box-body">
								<table id="skillTable" class="table table-bordered table-striped table-hover">
									<thead>
										<tr>
											<th><s:text name="firstSkill"/></th>
											<th><s:text name="secondSkill"/></th>
											<th><s:text name="thirdSkill"/></th>
											<th><s:text name="operation"/></th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name);
//搜索数据 (必须)
var searchData = [ { "name" : "more_data" , "value" : "my_value" } ];
$(function() {
	parent.layer.full(index);
	parent.layer.iframeAuto(index);
	//modal初始化
    $('#addSkillPointModel').modal({backdrop: 'static', keyboard: false});
    $('#addSkillPointModel').modal('hide');
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
	//搜索按钮初始化
	dataTableSearchButton("searchButton","skillTable","searchForm",[{"name":"courseVo.id","value":$("#id").val()}]);
	//清空按钮初始化
	dataTableClearButton("resetButton","searchForm");
	//技能表格控件初始化
	dataTable("skillTable","../tech/course!getSkillPage.do?courseVo.id="+$("#id").val(),
			[ [ 0, "asc" ] ], 
			[ {
				"mData" : "first_skill",
				"sClass" : "center"
			}, {
				"mData" : "second_skill",
				"sClass" : "center"
			}, {
				"mData" : "third_skill",
				"sClass" : "center"
			}, {
				"mData" : "id",
				"sClass" : "center"
			} ],
			[ 
			   {
					"aTargets" : [ 3 ],
					"mRender" : function(data, type, full) {
						var edit = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+tip_skill_edit+'" onclick="toManageSkillPoint(\''
						+ data + '\','+full.first_skill_id + ','+full.second_skill_id + ','+full.third_skill_id+ ',\''
							+ full.first_skill + '\',\''+full.second_skill + '\',\''+ full.third_skill + '\''
							+')"><i class="fa fa-edit"></i></a>';
						var del = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+tip_skill_delete+'" onclick="delCourseSkill(\''
							+ data + '\','+full.first_skill_id + ','+full.second_skill_id + ','+full.third_skill_id+ ',\''
							+ full.first_skill + '\',\''+full.second_skill + '\',\''+ full.third_skill + '\''
							+')"><i class="fa fa-trash"></i></a>';
						return edit+"&nbsp;&nbsp;&nbsp;"+del;
					}
			   }
			]
	);
});
/**
 * 课程技能知识点一览
 */
function reviewCourseSkillPoint(courseId,courseName){
	var index=layer.open({
	    type: 2,
	    title: false,
	    offset: '10%',// 距离页面顶距离
	    area: [ '60%','10%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/course!toLinkSkill_ReviewCoursePage.do?courseVo.id='+courseId+'&courseVo.name='+courseName
	});
}
/**
 * 去为课程添加技能知识点
 */
function toAddNewSkillPoint(courseId,courseName){
	var index=layer.open({
	    type: 2,
	    title: false,
	    offset: '1%',// 距离页面顶距离
	    area: [ '90%','50%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/course!toLinkSkill_AddSkillPage.do?courseVo.id='+courseId+'&courseVo.name='+courseName
	});
}
/**
 * 删除课程技能
 */
function delCourseSkill(courseId,first_skill_id,second_skill_id,third_skill_id,first_skill,second_skill,third_skill){
	layer.open({
		title: [
			tip,
		 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    content: delete_course_skill_content+"["+first_skill+( (second_skill==null || second_skill=="" || second_skill=="null")? "" : ("-"+ second_skill+ ( (third_skill==null || third_skill=="" || third_skill=="null")?"":("-"+third_skill) )) )+"]",
	    btn: [confirm,cancel],
	    shadeClose: false,
	    yes: function(){
	    	$.ajax({
				url:"../tech/course!removeCourseSkill.do",
				data: {"courseVo.id": courseId,"pointVo.first_skill_id":first_skill_id,"pointVo.second_skill_id":second_skill_id,"pointVo.third_skill_id":third_skill_id},
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.success=='true'){
						$("#skillTable").DataTable().draw();
						layer.msg(success_delete_course_skill);
					}else{
						layer.msg(data.message);
					}
				}
			});
	    }
	});
}
/**
 * 去管理技能下知识点
 */
function toManageSkillPoint(courseId,first_skill_id,second_skill_id,third_skill_id,first_skill,second_skill,third_skill){
	var first=first_skill_id==null?"":first_skill_id;
	var second=second_skill_id==null?"":second_skill_id;
	var third=third_skill_id==null?"":third_skill_id;
	var title=first_skill+( (second_skill==null || second_skill=="" || second_skill=="null")? "" : ("-"+ second_skill+ ( (third_skill==null || third_skill=="" || third_skill=="null")?"":("-"+third_skill) )) );
	var index=layer.open({
	    type: 2,
	    title: false,
	    offset: '2%',// 距离页面顶距离
	    area: [ '50%','65%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/course!toLinkSkill_PointPage.do?courseVo.id='+courseId+'&pointVo.first_skill_id='+first+'&pointVo.second_skill_id='+second+'&pointVo.third_skill_id='+third+"&pointVo.description="+title
	});
}
</script>
</html>




