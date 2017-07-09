var btTable;
var planId;
var class_id;
var course_id;
var className;
$(function() {
	planId = document.getElementById("planId").value;
	class_id = document.getElementById("class_id").value;
	course_id = document.getElementById("course_id").value;
	//获取教学计划信息
	getPlanInfo();
	tableInit("?plan_id="+planId+"&flag=1");
	btTable.Init();
	//tableInit(null);
	// 初始化搜索按钮
	$("#searchButton").click(function() {
		btTable.searchDate('searchForm');
	});
	// 初始化清空按钮
	$("#resetButton").click(function() {
		btTable.cleanDate('searchForm');
	});
	// 计算总成绩
	$("#btn_compute").click(function() {
		toComputePage(planId,class_id,course_id);
	});
	// 导出Excel
	$("#btn_export").click(function() {
		exportScore();
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
					className = data.class_id_name;
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
	btTable = BtTable('table', "../tech/scoreAction!selectTotalScoreInfo.do"+param, 'toolbar',  
			[ 
			//{	field : '',checkbox : true,}, 
			{
				title : scoreManage_96_student_name, // scoreManage_96_student_name:学生姓名
				field : 'student_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			},{
				title : scoreManage_96_student_no, // scoreManage_96_student_no:学生编号
				field : 'student_no',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title: scoreManage_96_normal_score, // scoreManage_96_normal_score:平时成绩
                field: 'normal_score',
                align: 'center',
                valign : 'middle',
				visible : true,
				sortable : true,
                events: { },
                formatter: function operateFormatter(value, row, index) {
                	return '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ 
                	scoreManage_96_look_normal_score + // scoreManage_96_look_normal_score:点击查看平时成绩明细
                	'\" onclick="getDeatilNormal(\''+row.student_id+'\',\''+row.student_name+'\')">'+
                	'<i class="fa fa-file-text-o"></i>&nbsp;&nbsp;'+row.normal_score+'</a>';
                }
			}, {
				title: scoreManage_96_attendance_score, // scoreManage_96_attendance_score:考勤成绩
                field: 'attendance_score',
                align: 'center',
                valign : 'middle',
				visible : true,
				sortable : true,
                events: { },
                formatter: function operateFormatter(value, row, index) {
                	return '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ 
                	scoreManage_96_look_attendance_score + // scoreManage_96_look_attendance_score:点击查看考勤成绩明细
                	'\" onclick="getDeatilCheck(\''+row.student_id+'\',\''+row.student_name+'\')">'+
                	'<i class="fa fa-file-text-o"></i>&nbsp;&nbsp;'+row.attendance_score+'</a>';
                }
			}, {
				title: scoreManage_96_attitude_score, // scoreManage_96_attitude_score:态度成绩
                field: 'attitude_score',
                align: 'center',
                valign : 'middle',
				visible : true,
				sortable : true,
                events: { },
                formatter: function operateFormatter(value, row, index) {
                	return '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ 
                	scoreManage_96_look_attitude_score + // scoreManage_96_look_attitude_score:点击查看态度成绩明细
                	'\" onclick="getDeatilAtt(\''+row.student_id+'\',\''+row.student_name+'\')">'+
                	'<i class="fa fa-file-text-o"></i>&nbsp;&nbsp;'+row.attitude_score+'</a>';
                }
			}, {
				title : scoreManage_96_exam_score, // scoreManage_96_exam_score:期末测试
				field : 'exam_score',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title: scoreManage_96_total_score, // scoreManage_96_total_score:总成绩
                field: 'total_score',
                align: 'center',
                valign : 'middle',
				visible : true,
				sortable : true,
                events: { },
                formatter: function operateFormatter(value, row, index) {
                	return '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ 
                	scoreManage_96_look_total_score + // scoreManage_96_look_total_score:点击查看总成绩明细
                	'\" onclick="getDeatilTotal(\''+row.student_id+'\',\''+row.student_name+'\',\''+
                		row.normal_score+'\',\''+row.attendance_score+'\',\''+row.attitude_score+'\',\''+row.exam_score+'\',\''+row.total_score+'\')">'+
                	'<i class="fa fa-file-text-o"></i>&nbsp;&nbsp;'+row.total_score+'</a>';
                }
			}, ],
	'student_no','asc',null);
}
//刷新表格
function refreshTable(){
	btTable.refresh("searchForm");
}
/**
 * 跳转计算总成绩页面
 * @param planId
 * @param class_id
 * @param course_id
 */
function toComputePage(planId,class_id,course_id){
	layer.open({
		title : [ scoreManage_96_action_true, 'background-color:#3C8DBC; color:#ffffff;' ],  // scoreManage_96_action_true:操作确认
		content : scoreManage_96_action_true_info, // scoreManage_96_action_true_info:计算总成绩会删除原来的计算结果,请确认
		btn : [ confirm, cancel ],
		shadeClose : false,
		yes : function() {
			document.getElementsByClassName("layui-layer-ico layui-layer-close layui-layer-close1")[0].click();
			var index = layer.open({
				title : [ scoreManage_96_get_total_score, // scoreManage_96_get_total_score:计算总成绩
						'background-color:#3C8DBC; color:#ffffff;' ],
				type : 2,
				offset : '0%',// 距离页面顶距离
				area : [ '100%', '100%' ],
				fix : false, // 不固定
				maxmin : true,
				content : '../tech/scoreAction!toComputePage.do?'+
					'planPaperVo.plan_id='+planId+
					'&planPaperVo.class_id='+class_id+
					'&planPaperVo.course_id='+course_id
			});
			layer.full(index);
		}	
	});
}
//绑定关闭方法
function closeFun(){
	$("a.layui-layer-close").click(function() {
		refreshTable();
	});
}
//查看平时成绩明细
function getDeatilNormal(student_id,student_name){
	var index1 = layer.open({
		title : [ student_name+scoreManage_96_normal_score_title, // scoreManage_96_normal_score_title:的平时成绩明细
				'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		offset : '0%',// 距离页面顶距离
		area : [ '100%', '100%' ],
		fix : false, // 不固定
		maxmin : true,
		content : '../tech/scoreAction!toDetailNormal.do?'+
			'planPaperVo.plan_id='+planId+
			'&planPaperVo.class_id='+class_id+
			'&planPaperVo.course_id='+course_id+
			'&planPaperVo.student_id='+student_id
	});
	layer.full(index1);
}
//查看考勤成绩明细
function getDeatilCheck(student_id,student_name){
	var index2 = layer.open({
		title : [ student_name+scoreManage_96_attendance_socre_title, // scoreManage_96_attendance_socre_title:的考勤和态度成绩
				'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		offset : '0%',// 距离页面顶距离
		area : [ '100%', '100%' ],
		fix : false, // 不固定
		maxmin : true,
		content : '../tech/scoreAction!toDetailAttendance.do?'+
			'planPaperVo.plan_id='+planId+
			'&planPaperVo.class_id='+class_id+
			'&planPaperVo.course_id='+course_id+
			'&planPaperVo.student_id='+student_id
	});
	layer.full(index2);
}
//查看态度成绩明细
function getDeatilAtt(student_id,student_name){
	getDeatilCheck(student_id,student_name);
}
//查看总成绩明细
function getDeatilTotal(student_id,student_name,normal_score,attendance_score,attitude_score,exam_score,total_score){
	$.ajax({
		url : "../tech/scoreAction!getTotalScoreRatio.do",
		data: "planPaperVo.plan_id="+planId,
		dataType : "json",
		type : "get",
		success : function(data) {
			if (data.success == 'true') {
				if (data.message == '1') {//失败
					layer.msg(feedback_find_fail);// I18N - feedback_find_fail:查询失败! 
				} else if (data.message == '0')  {//成功
					data = data.datas.data;
					var normal_ratio = data.normal_ratio;
					var attendance_ratio = data.attendance_ratio;
					var attitude_ratio = data.attitude_ratio;
					var exam_ratio = data.exam_ratio;
					var all = normal_ratio*1 + attendance_ratio*1 + attitude_ratio*1 + exam_ratio*1;
					normal_ratio=((normal_ratio*1/all*1)*100).toFixed(2)+"%";
					attendance_ratio=((attendance_ratio*1/all*1)*100).toFixed(2)+"%";
					attitude_ratio=((attitude_ratio*1/all*1)*100).toFixed(2)+"%";
					exam_ratio=((exam_ratio*1/all*1)*100).toFixed(2)+"%";
					var context = total_score+scoreManage_96_score+'=('+normal_score+scoreManage_96_score+'*'+normal_ratio+')+('+ // scoreManage_96_score:分 // scoreManage_96_score_66:分
							attendance_score+scoreManage_96_score_29+'*'+attendance_ratio+')+('+ // scoreManage_96_score_29:分
							attitude_score+scoreManage_96_score_98+'*'+attitude_ratio+')+('+ // scoreManage_96_score_98:分
							exam_score+scoreManage_96_score_92+'*'+exam_ratio+')'; // scoreManage_96_score_92:分
					var context2 = scoreManage_96_total_score_85+'='+scoreManage_96_score1+'*'+ // scoreManage_96_total_score_85:总成绩 // scoreManage_96_score1:平时分数
								   scoreManage_96_ratio1+'+'+scoreManage_96_score2+'*'+ // scoreManage_96_ratio1:平时分占比 // scoreManage_96_score2:考勤分数
								   scoreManage_96_ratio2+'+'+scoreManage_96_score3+'*'+ // scoreManage_96_ratio2:考勤分占比 // scoreManage_96_score3:态度分数
								   scoreManage_96_ratio3+'+'+scoreManage_96_score4+'*'+ // scoreManage_96_ratio3:态度分占比 // scoreManage_96_score4:期末卷分数
								   scoreManage_96_ratio4; // scoreManage_96_ratio4:期末卷分占比
					layer.alert("总分计算"+":<br/>"+context2+"<br/>"+context);
				}
			} else {
				layer.msg(feedback_find_fail);// I18N - feedback_find_fail:查询失败! 
				console.info(data.message);
			}
		}
	});
}

/**
 * 导出方法
 */
function exportScore(){
	window.open("../tech/scoreAction!downTotalScoreData.do?totalScoreVo.plan_id="+planId+"&planPaperVo.class_name="+className);
}
