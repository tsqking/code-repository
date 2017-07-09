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
<script src="${pageContext.request.contextPath}/tech/teacherpool/${system_lang}.js"></script>
<title>教学历史查询</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<!-- 隐藏 -->
		<div class="row" style="display:none;" > 
		
			<div class="col-md-12 col-xs-12">
				<div class="form-group">
	            	<label>ID</label>           
	            	<div class="input-group">
	                	<span class="input-group-addon"><li class="fa fa-file-o"></li></span>
	                	<input type="text" name="tepVo.nbr" id="nbr" class="form-control" value="${tepVo.nbr}">
	              	</div>
	            </div>
			</div>
		</div>
		<div class="box">
			<div class="box-header">
					<s:text name="teacher.teachHistoryTitle">
						<s:param>${tepVo.name}</s:param>
					</s:text>
			</div>
			<div class="box-body">
				<div class="row">
					<div class="col-xs-12">
						<div class="box" style="margin-bottom: 5px;">
						<%-- 	<div class="box-header">
								<h3 class="box-title" style="font-family: msyh">
									<s:text name="tepVo.teacherpoolList">
										<s:param>${courseVo.name}</s:param>
									</s:text>
								</h3>
							</div> --%>
							<div class="box-body">
								<table id="historyTable" class="table table-bordered table-striped table-hover">
									<thead>
										<tr>
											<th><s:text name="courseName"/></th>
											<th><s:text name="firstSkill"/></th>
											<th><s:text name="secondSkill"/></th>
											<th><s:text name="thirdSkill"/></th>
											<th><s:text name="knowledge"/></th>
											<th><s:text name="className"/></th>
											<th><s:text name="start_time"/></th>
											<th><s:text name="end_time"/></th>											
											<th><s:text name="confirmTime"/></th>
											<th><s:text name="address"/></th>
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
	parent.layer.iframeAuto(index);
	//modal初始化
	 //下拉框初始化
/* 	selectInitial("first_skill", "../tech/skill!findSkillOption.do?skillVo.parent_id=0", "", false);
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
	dataTableSearchButton("searchButton","historyTable","searchForm",[{"name":"tepVo.nbr","value":$("#nbr").val()}]);
	//清空按钮初始化
	dataTableClearButton("resetButton","searchForm");  */
	//技能表格控件初始化
	dataTable("historyTable","../tech/teacherpool!getHistoryPage.do?tepVo.nbr="+$("#nbr").val(),
			[ [ 0, "asc" ] ], 
			[ {
				"mData" : "courseName",
				"sClass" : "center"
			},{
				"mData" : "first_skill",
				"sClass" : "center"
			},{
				"mData" : "second_skill",
				"sClass" : "center"
			},{
				"mData" : "third_skill",
				"sClass" : "center"
			},{
				"mData" : "knowledge",
				"sClass" : "center"
			},{
				"mData" : "className",
				"sClass" : "center"
			}, {
				"mData" : "start_time",
				"sClass" : "center"
			} ,{
				"mData" : "end_time",
				"sClass" : "center"
			} ,{
				"mData" : "confirmTime",
				"sClass" : "center"
			},{
				"mData" : "address",
				"sClass" : "center"
			}
			]
			/* [ 
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
			] */
	);
});
//  /**
//  * 课程技能知识点一览
//  */
//  function reviewCourseSkillPoint(courseId,courseName){
// 	var index=layer.open({
// 	    type: 2,
// 	    title: false,
// 	    offset: '2%',// 距离页面顶距离
// 	    area: [ '60%','90%'],
// 	    fix: false, //不固定
// 	    maxmin: true,
// 	    content: '../tech/course!toLinkSkill_ReviewCoursePage.do?courseVo.id='+courseId+'&courseVo.name='+courseName
// 	});
// }
// /**
//  * 去为课程添加技能知识点
//  */
// function toAddNewSkillPoint(courseId,courseName){
// 	var index=layer.open({
// 	    type: 2,
// 	    title: false,
// 	    offset: '20%',// 距离页面顶距离
// 	    area: [ '80%','10%'],
// 	    fix: false, //不固定
// 	    maxmin: true,
// 	    content: '../tech/course!toLinkSkill_AddSkillPage.do?courseVo.id='+courseId+'&courseVo.name='+courseName
// 	});
// }
// /**
//  * 删除课程技能
//  */
// function delCourseSkill(courseId,first_skill_id,second_skill_id,third_skill_id,first_skill,second_skill,third_skill){
// 	layer.open({
// 		title: [
// 			tip,
// 		 	'background-color:#3C8DBC; color:#ffffff;'
// 		],
// 	    content: delete_course_skill_content+"["+first_skill+( (second_skill==null || second_skill=="" || second_skill=="null")? "" : ("-"+ second_skill+ ( (third_skill==null || third_skill=="" || third_skill=="null")?"":("-"+third_skill) )) )+"]",
// 	    btn: [confirm,cancel],
// 	    shadeClose: false,
// 	    yes: function(){
// 	    	$.ajax({
// 				url:"../tech/course!removeCourseSkill.do",
// 				data: {"courseVo.id": courseId,"pointVo.first_skill_id":first_skill_id,"pointVo.second_skill_id":second_skill_id,"pointVo.third_skill_id":third_skill_id},
// 				dataType:"json",
// 				type:"post",
// 				success:function(data){
// 					if(data.success=='true'){
// 						$("#skillTable").DataTable().draw();
// 						layer.msg(success_delete_course_skill);
// 					}else{
// 						layer.msg(data.message);
// 					}
// 				}
// 			});
// 	    }
// 	});
// }
// /**
//  * 去管理技能下知识点
//  */
// function toManageSkillPoint(courseId,first_skill_id,second_skill_id,third_skill_id,first_skill,second_skill,third_skill){
// 	var first=first_skill_id==null?"":first_skill_id;
// 	var second=second_skill_id==null?"":second_skill_id;
// 	var third=third_skill_id==null?"":third_skill_id;
// 	var title=first_skill+( (second_skill==null || second_skill=="" || second_skill=="null")? "" : ("-"+ second_skill+ ( (third_skill==null || third_skill=="" || third_skill=="null")?"":("-"+third_skill) )) );
// 	var index=layer.open({
// 	    type: 2,
// 	    title: false,
// 	    offset: '2%',// 距离页面顶距离
// 	    area: [ '50%','95%'],
// 	    fix: false, //不固定
// 	    maxmin: true,
// 	    content: '../tech/course!toLinkSkill_PointPage.do?courseVo.id='+courseId+'&pointVo.first_skill_id='+first+'&pointVo.second_skill_id='+second+'&pointVo.third_skill_id='+third+"&pointVo.description="+title
// 	});
// } 
</script>
</html>




