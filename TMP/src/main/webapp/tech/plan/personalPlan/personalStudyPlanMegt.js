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
	dataTable("learnPlanTable","../tech/personalPlan!getLearnPlanTable.do",
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
						//学员查看教学计划
						var reviewPlan = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ studentReviewPlan +'\" onclick="toStudentReviewPlanPage(\''+ full.course_id 
								+'\',\'' + full.course_id_name + '\',\''+full.class_id+'\',\''+full.class_id_name+'\',\''+full.location+'\',\''+full.location_id+'\',\''+full.teach_plan_id+'\')"><i class="fa fa-calendar"></i></a>';
						//评价教师
						var evaluate = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ toEvalTeacher +'\" onclick="toEvalTeachersPage(\''+ full.course_id 
								+'\',\'' + full.course_id_name + '\',\''+full.class_id+'\',\''+full.class_id_name+'\',\''+full.location+'\',\''+full.location_id+'\',\''+full.teach_plan_id+'\')"><i class="fa fa-smile-o"></i></a>';
						//测试联系
						var test = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ 
							test_button +'\" onclick="toMyTest(\''+full.teach_plan_id+'\',\''+full.class_id+'\',\''+full.course_id+'\')"><i class="fa fa-file-text"></i></a>';
						//教师反馈
						var info = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ 
							teacher_feedback +'\" onclick="toUserInfo(\''+full.teach_plan_id+'\',\''+full.class_id+'\',\''+full.course_id+'\')"><i class="fa fa-commenting-o"></i></a>';
						return reviewPlan+"&nbsp;&nbsp;&nbsp;"+evaluate+"&nbsp;&nbsp;&nbsp;"+test+"&nbsp;&nbsp;&nbsp;"+info;
					}
			   }
			]
	);
	
}
/**
 * 学员去查看教学计划
 */
function toStudentReviewPlanPage(course_id,course_name,class_id,class_name,location,location_id,teach_plan_id){
	window.open("../tech/personalPlan!toStudentReviewPlanPage.do?planVo.course_id="+course_id
			+"&planVo.course_id_name="+course_name+"&planVo.class_id="+class_id+"&planVo.class_id_name="+class_name
			+"&planVo.location="+location+"&planVo.location_id="+location_id+"&planVo.teach_plan_id="+teach_plan_id);
}
/**
 * 学员去评价教师
 */
function toEvalTeachersPage(course_id,course_name,class_id,class_name,location,location_id,teach_plan_id){
	var ref_index=layer.open({
	    type: 2 
	    ,title: toEvalteacherTitle
	    ,area: ['360px', '350px']
	    ,shade: 0
	    ,offset: '13%'
	    ,content: ["../tech/personalPlan!toEvalTeachersPage.do?planVo.course_id="+course_id
	   				+"&planVo.course_id_name="+course_name+"&planVo.class_id="+class_id+"&planVo.class_id_name="+class_name
	   				+"&planVo.location="+location+"&planVo.location_id="+location_id+"&planVo.teach_plan_id="+teach_plan_id,
				'no']
	    ,zIndex: layer.zIndex  
	    ,success: function(layero){
	        layer.setTop(layero);  
	    }
	});
}
/**
 * 跳转测试练习管理界面
 */
function toMyTest(planId,class_id,course_id){
	var index = layer.open({
		title : [ paper_test_title2,
				'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		offset : '0%',// 距离页面顶距离
		area : [ '100%', '100%' ],
		fix : false, // 不固定
		maxmin : true,
		content : '../tech/studentPlan!toPaperManagePage.do?'+
			'planPaperVo.plan_id='+planId+
			'&planPaperVo.class_id='+class_id+
			'&planPaperVo.course_id='+course_id
	});
	layer.full(index);
}

/**
 * 跳转查看教师反馈界面
 */
function toUserInfo(planId,class_id,course_id){
	var index1 = layer.open({
		title : [ teacher_feedback,
				'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		offset : '0%',// 距离页面顶距离
		area : [ '100%', '100%' ],
		fix : false, // 不固定
		maxmin : true,
		content : '../tech/studentPlan!toFeedbackManage.do?'+
			'planPaperVo.plan_id='+planId+
			'&planPaperVo.class_id='+class_id+
			'&planPaperVo.course_id='+course_id
	});
	layer.full(index1);
}
