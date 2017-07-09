var btTable;
var planId;
var student_id;
var course_id;
var class_id;
$(function() {
	planId = document.getElementById("planId").value;
	student_id = document.getElementById("student_id").value;
	course_id = document.getElementById("course_id").value;
	class_id = document.getElementById("class_id").value;
	//获取教学计划信息
	getPlanInfo();
	tableInit("?plan_id="+planId+"&student_id="+student_id);
	btTable.Init();
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
	//初始化BootStrapTable
	btTable = BtTable('table', "../tech/scoreAction!selectDetailAttendance.do"+param, 'toolbar',  
			[ 
			{
				title : detailAttendance_86_point_name, // detailAttendance_86_point_name:知识点名称
				field : 'point_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			},{
				title : detailAttendance_86_point_description, // detailAttendance_86_point_description:知识点描述
				field : 'point_description',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : detailAttendance_86_teacher_name, // detailAttendance_86_teacher_name:授课老师
				field : 'teacher_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			},{
				title : detailAttendance_86_sign_name, // detailAttendance_86_sign_name:出席情况
				field : 'sign_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true,
			},{
				title : detailAttendance_86_score, // detailAttendance_86_score:态度打分
				field : 'score',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : detailAttendance_86_comment, // detailAttendance_86_comment:表现评价
				field : 'comment',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : detailAttendance_86_update_time,  // detailAttendance_86_update_time:打分时间
				field : 'update_time',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : detailAttendance_86_update_person,  // detailAttendance_86_update_person:打分人
				field : 'update_person',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}],
	'update_time','asc',null);
}
//刷新表格
function refreshTable(){
	btTable.refresh("searchForm");
}
