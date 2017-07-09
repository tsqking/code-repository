var btTable;
var planId;
var class_id;
var course_id;
$(function() {
	planId = document.getElementById("planId").value;
	class_id = document.getElementById("class_id").value;
	course_id = document.getElementById("course_id").value;
	//获取教学计划信息
	getPlanInfo();
	//初始化时间控件
	dateInitial([ {"id":"update_time","type":"2"}]);
	tableInit("?class_id="+class_id+"&course_id="+course_id);
	btTable.Init();
	tableInit(null);
	// 初始化搜索按钮
	$("#searchButton").click(function() {
		btTable.searchDate('searchForm');
	});
	// 初始化清空按钮
	$("#resetButton").click(function() {
		btTable.cleanDate('searchForm');
	});
	// 工具栏-返回按钮
	$("#btn_go_back").click(function() {
		parent.layer.close(parent.layer.getFrameIndex(window.name));
	});
})
//获取教学计划信息
function getPlanInfo(){
	$.ajax({
		url : "../tech/teacherPlan!getPlanInfo.do",
		data: "planPaperVo.class_id="+class_id+"&planPaperVo.course_id="+course_id,
		dataType : "json",
		type : "get",
		success : function(data) {
			if (data.success == 'true') {
				if (data.message == '1') {//删除失败
					layer.alert(feedback_fail , {// I18N - feedback_fail:失败 
						title : feedback_find_fail// I18N - feedback_find_fail:查询失败! 
					});
				} else if (data.message == '0')  {//删除成功
					var data = data.datas.data;
					var info = feedback_course_name+":"+data.course_id_name+"&nbsp;&nbsp;&nbsp;&nbsp;"+feedback_class_name+":"+data.class_id_name+// I18N - feedback_course_name:课程名称 feedback_class_name:班级名称 
					"&nbsp;&nbsp;&nbsp;&nbsp;"+feedback_teach_time+":"+data.start_time+"&nbsp;~&nbsp;"+data.end_time;// I18N - feedback_teach_time:教学时间 
					document.getElementById("contacts").innerHTML = info;
				}
			} else {
				layer.msg(feedback_find_fail);// I18N - feedback_find_fail:查询失败! 
				console.info(data.message);
			}
		}
	});
}
//表格初始化
function tableInit(param){
	if(param==null){
		param="";
	}
	// 初始化BootStrapTable
	btTable = BtTable('table', "../tech/studentPlan!selectFeedBackList.do"+param, 'toolbar',  
			[ 
			//{	field : '',checkbox : true,}, 
			{
				title : feedback_teacher_name,// I18N - feedback_teacher_name:老师名称 
				field : 'teacher_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			},{
				title : feedback_course_name,// I18N - feedback_course_name:课程名称 
				field : 'course_name',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : feedback_class_name,// I18N - feedback_class_name:班级名称 
				field : 'class_name',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			},{
				title : feedback_student_name,// I18N - feedback_student_name:学生名称 
				field : 'student_name',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true,
			},{
				title : feedback_score,// I18N - feedback_score:评价分数 
				field : 'score',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : feedback_update_time,// I18N - feedback_update_time:评价时间 
				field : 'update_time',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : feedback_comment,// I18N - feedback_comment:评语 
				field : 'comment',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : feedback_create_time,// I18N - feedback_create_time:创建时间 
				field : 'create_time',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			}, {
				title : feedback_create_person,// I18N - feedback_create_person:创建人 
				field : 'create_person',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			},{
				title : feedback_update_person,// I18N - feedback_update_person:更新人 
				field : 'update_person',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			}],
	'update_time','desc',null);
}
//刷新表格
function refreshTable(){
	btTable.refresh("searchForm");
}
