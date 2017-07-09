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
	btTable = BtTable('table', "../tech/scoreAction!selectDetailNormal.do"+param, 'toolbar',  
			[ 
			{
				title : detailNormal_24_paper_name, // detailNormal_24_paper_name:试卷名称
				field : 'paper_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			},{
				title : detailNormal_24_paper_no, // detailNormal_24_paper_no:试卷编号
				field : 'paper_no',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : detailNormal_24_paper_type, // detailNormal_24_paper_type:试卷用途
				field : 'paper_type',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			},{
				title : detailNormal_24_paper_description, // detailNormal_24_paper_description:试卷描述
				field : 'paper_description',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true,
			},{
				title : detailNormal_24_paper_instruction, // detailNormal_24_paper_instruction:试卷说明
				field : 'paper_instruction',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title: detailNormal_24_paper_flag, // detailNormal_24_paper_flag:是否为期末卷
                field: 'paper_flag',
                align: 'center',
                valign : 'middle',
				visible : true,
				sortable : true,
                events: { },
                formatter: function operateFormatter(value, row, index) {
                	if(row.paper_flag=='0'){
                		return detailNormal_24_no; // detailNormal_24_no:否
                	}else{
                		return detailNormal_24_yes; // detailNormal_24_yes:是
                	}
                }
			}, {
				title : detailNormal_24_true_score,  // detailNormal_24_true_score:卷面分数/试卷总分
				field : 'true_score',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true,
				formatter: function operateFormatter(value, row, index) {
                	return row.true_score+'/'+row.paper_total_score;
                }
			}, {
				title : detailNormal_24_switch_score,  // detailNormal_24_switch_score:转换分数/100分
				field : 'switch_score',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true,
				formatter: function operateFormatter(value, row, index) {
                	return row.switch_score+'/100';
                }
			}, {
				title : detailNormal_24_paper_ratio,  // detailNormal_24_paper_ratio:分数比例
				field : 'paper_ratio',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : detailNormal_24_ratio_num,  // detailNormal_24_ratio_num:平时分占比
				field : 'ratio_num',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}],
	'update_time','asc',null);
}
//刷新表格
function refreshTable(){
	btTable.refresh("searchForm");
}
