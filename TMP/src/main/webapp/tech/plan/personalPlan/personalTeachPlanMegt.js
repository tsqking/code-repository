//---------------------------js文本全局变量----------------------------------
//搜索数据 (必须)
var searchData = [ { "name" : "more_data" , "value" : "my_value" } ];
//-------------------------页面加载完成后执行方法-------------------------------
//页面加载完成执行
$(function() {
	//1.初始化方法
	initial();
});
//------------------------------js方法-------------------------------------
//初始化方法
function initial() {
	dataTable("teachPlanTable","../tech/personalPlan!getTeachPlanTable.do",
			[ [ 3, "desc" ] ], 
			[ {
				"mData" : "course_id_name",
				"sClass" : "center"
			}, {
				"mData" : "class_id_name",
				"sClass" : "center"
			}, {
				"mData" : "location",
				"sClass" : "center"
			}, {
				"mData" : "start_time",
				"sClass" : "center"
			}, {
				"mData" : "end_time",
				"sClass" : "center"
			}],
			 [ 
			   {
					"aTargets" : [ 5 ],
					//"mData" : "操作",
					"mRender" : function(data, type, full) {
						//教师查看教学计划
						var reviewPlan = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ teacherReviewPlan +'\" onclick="toTeacherReviewPlan(\''+ full.course_id 
								+'\',\'' + full.course_id_name + '\',\''+full.class_id+'\',\''+full.class_id_name+'\',\''+full.location+'\',\''+full.location_id+'\',\''+full.teach_plan_id+'\')"><i class="fa fa-calendar"></i></a>';
						//最终评价学生
						var finalEval = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ toDoFinalEvaluation +'\" onclick="teacherFinalEval(\''+ full.course_id 
								+'\',\'' + full.course_id_name + '\',\''+full.class_id+'\',\''+full.class_id_name+'\',\''+full.location+'\',\''+full.location_id+'\',\''+full.teach_plan_id+'\')"><i class="fa fa-users"></i></a>';
						//测试管理界面
						var paperManage = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+test_button+'\" '+
							'onclick="toPaperManager(\''+full.teach_plan_id+'\',\''+full.class_id+'\',\''+full.course_id+'\')"><i class="fa fa-file-text"></i></a>';
						//学生反馈
						var stuInfo = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+student_feedback+'\" '+
							'onclick="toUserInfo(\''+full.teach_plan_id+'\',\''+full.class_id+'\',\''+full.course_id+'\')"><i class="fa fa-commenting"></i></a>';
						//成绩管理
						var score = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+personalTeachPlanMegt_27_score_manager+'\" '+ // personalTeachPlanMegt_27_score_manager:成绩管理
							'onclick="toScoreInfo(\''+full.teach_plan_id+'\',\''+full.class_id+'\',\''+full.course_id+'\')"><i class="fa fa-fw fa-list"></i></a>';
						return reviewPlan+"&nbsp;&nbsp;&nbsp;"+finalEval+"&nbsp;&nbsp;&nbsp;"+
								paperManage+"&nbsp;&nbsp;&nbsp;"+stuInfo+"&nbsp;&nbsp;&nbsp;"+score;
					}
			   }
			]
	);
}
/**
 * 教师去查看教学计划
 */
function toTeacherReviewPlan(course_id,course_name,class_id,class_name,location,location_id,teach_plan_id){
	window.open("../tech/personalPlan!toTeacherReviewPlanPage.do?planVo.course_id="+course_id
			+"&planVo.course_id_name="+course_name+"&planVo.class_id="+class_id+"&planVo.class_id_name="+class_name
			+"&planVo.location="+location+"&planVo.location_id="+location_id+"&planVo.teach_plan_id="+teach_plan_id);
}
/**
 * 教师最后总评学生
 */
function teacherFinalEval(course_id,course_name,class_id,class_name,location,location_id,teach_plan_id){
	window.open("../tech/personalPlan!toTFinalEvalSPage.do?planVo.course_id="+course_id
			+"&planVo.course_id_name="+course_name+"&planVo.class_id="+class_id+"&planVo.class_id_name="+class_name
			+"&planVo.location="+location+"&planVo.location_id="+location_id+"&planVo.teach_plan_id="+teach_plan_id);
}
/**
 * 跳转测试管理界面
 */
function toPaperManager(planId,class_id,course_id){
	var index = layer.open({
		title : [ paper_test_title1,
				'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		offset : '0%',// 距离页面顶距离
		area : [ '100%', '100%' ],
		fix : false, // 不固定
		maxmin : true,
		content : '../tech/teacherPlan!toPaperManagePage.do?'+
			'planPaperVo.plan_id='+planId+
			'&planPaperVo.class_id='+class_id+
			'&planPaperVo.course_id='+course_id
	});
	layer.full(index);
}
/**
 * 跳转查看学生反馈界面
 */
function toUserInfo(planId,class_id,course_id){
	var index1 = layer.open({
		title : [ student_feedback,
				'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		offset : '0%',// 距离页面顶距离
		area : [ '100%', '100%' ],
		fix : false, // 不固定
		maxmin : true,
		content : '../tech/teacherPlan!toFeedbackManage.do?'+
			'planPaperVo.plan_id='+planId+
			'&planPaperVo.class_id='+class_id+
			'&planPaperVo.course_id='+course_id
	});
	layer.full(index1);
}
/**
 * 跳转成绩管理页面
 * @param planId
 * @param class_id
 * @param course_id
 */
function toScoreInfo(planId,class_id,course_id){
	var index2 = layer.open({
		title : [ personalTeachPlanMegt_27_student_score_manager, // personalTeachPlanMegt_27_student_score_manager:学生成绩管理
				'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		offset : '0%',// 距离页面顶距离
		area : [ '100%', '100%' ],
		fix : false, // 不固定
		maxmin : true,
		content : '../tech/scoreAction!toScoreManagePage.do?'+
			'planPaperVo.plan_id='+planId+
			'&planPaperVo.class_id='+class_id+
			'&planPaperVo.course_id='+course_id
	});
	layer.full(index2);
}
