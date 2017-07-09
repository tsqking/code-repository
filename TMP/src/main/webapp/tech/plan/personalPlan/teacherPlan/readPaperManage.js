var btTable;
var planPaperId;
var paper_id;
var class_name;
$(function() {
	planPaperId = document.getElementById("planPaperId").value;
	paper_id = document.getElementById("paper_id").value;
	class_name = document.getElementById("class_name").value;
	//初始化下拉框  试卷属性 用途
	selectInitial("status", "../system/option!getOptionsByGPVal.do?value=PAPER_FINISH_STATUS","",false);
	//时间空间
	dateInitial([{"id":"start_time","type":"2","dir":"r"},{"id":"end_time","type":"2"}]);
	// 初始化BootStrapTable
	tableInit("?plan_paper_id="+planPaperId);
	btTable.Init();
	tableInit(null);
	//获取信息
	findPaper(paper_id);
	findClass(class_name);
	// 初始化搜索按钮
	$("#searchButton").click(function() {
		btTable.searchDate('searchForm');
	});
	// 初始化清空按钮
	$("#resetButton").click(function() {
		btTable.cleanDate('searchForm');
	});
	// 导出Excel
	$("#btn_export_question").click(function() {
		exportScore();
	});
	$("#btn_export_answer").click(function() {
		exportAnswer();
	});
	//导出成绩单
	$("#btn_export_transcript").click(function() {
		exportTranscript();
	});
	// 工具栏-返回按钮
	$("#btn_go_back").click(function() {
		parent.layer.close(parent.layer.getFrameIndex(window.name));
	});
})
//刷新表格
function refreshTable(){
	btTable.refresh("searchForm");
}
//初始化参数
function tableInit(param){
	if(param==null){
		param="";
	}
	btTable = BtTable('table', "../tech/teacherPlan!selectClassPaperList.do"+param, 'toolbar',  
			[ 
			{	field : 'choose',checkbox : true,}, 
			{
				title : readPaper_student_name,// I18N - readPaper_student_name:学生名称 
				field : 'student_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true,
			}, {
				title : readPaper_status_name,// I18N - readPaper_status_name:是否完成 
				field : 'status_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : readPaper_start_time,// I18N - readPaper_start_time:开始答卷时间 
				field : 'start_time',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : readPaper_end_time,// I18N - readPaper_end_time:结束答卷时间 
				field : 'end_time',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : readPaper_long_time,// I18N - readPaper_long_time:答卷耗时 
				field : 'long_time',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true,
				formatter: function operateFormatter(value, row, index) {
					return getLongTime(row.start_time,row.end_time);
	            }
			}, {
				title : readPaper_teacher_name,// I18N - readPaper_teacher_name:批卷人 
				field : 'teacher_name',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : readPaper_check_time,// I18N - readPaper_check_time:批卷时间 
				field : 'check_time',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : readPaper_score,// I18N - readPaper_score:总分数 
				field : 'score',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true,
			} ,{
				title : readPaper_create_time,// I18N - readPaper_create_time:创建时间 
				field : 'create_time',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			}, {
				title : readPaper_create_person,// I18N - readPaper_create_person:创建人 
				field : 'create_person',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : readPaper_update_time,// I18N - readPaper_update_time:更新时间 
				field : 'update_time',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			}, {
				title : readPaper_update_person,// I18N - readPaper_update_person:更新人 
				field : 'update_person',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			},{
                field: readPaper_action,// I18N - readPaper_action:操作 
                title: readPaper_action,// I18N - readPaper_action:操作 
                align: 'center',
                events: { },
                formatter: function operateFormatter(value, row, index) {
                	var flag = row.status;
                	var read = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ 
                		readPaper_read +'\"  onclick="readPaper(\''+row.student_id+'\',\''+row.paper_id+'\',\''+row.id+'\')"><i class="fa fa-eye"></i></a>';// I18N - readPaper_read:查看试卷 
                	var check = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ 
            			readPaper_check +'\" onclick="correctPaper(\''+row.student_id+'\',\''+row.paper_id+'\',\''+row.id+'\')"><i class="fa fa-edit"></i></a>';// I18N - readPaper_check:批改试卷 
                	var want = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ 
        				readPaper_want +'\" onclick="build()"><i class="fa fa-exclamation-circle"></i></a>';// I18N - readPaper_want:催交作业 
                	if(flag=='0'){
                		//未完成
                		return want;
                	}else if(flag=='1'){
                		//已完成
                		return check;
                	}else if(flag=='2'){
                		//已批改
                		return read+"&nbsp;&nbsp;&nbsp;"+check;
                	}
                }
            }],
	'update_time','desc',null);
}
//查询试卷数据
function findPaper(paperId){
	$.ajax({
		url : "../tech/teacherPlan!getPaperInfo.do",
		data : "paperVo.id="+paperId,
		dataType : "json",
		type : "post",
		success : function(data) {
			if(data.success=='true'){
				if(data.message=='0'){
					//拼接信息
					var paper = data.datas.data;
					var total_time;
					if(paper.total_time=="-1"){
						total_time = readPaper_no_time;// I18N - readPaper_no_time:不限时 
					}else{
						total_time = paper.total_time+"mins";
					}
					var info = readPaper_name+":"+paper.name+"&nbsp;&nbsp;"+readPaper_no+":"+paper.no+"&nbsp;&nbsp;<br/>"+readPaper_use_flag_name+":"+// I18N - readPaper_name:名称 readPaper_no:编号 readPaper_use_flag_name:用途 
					paper.use_flag_name+"&nbsp;&nbsp;"+readPaper_property_name+":"+paper.property_name+"&nbsp;&nbsp;"+readPaper_total_item+":"+// I18N - readPaper_property_name:属性 readPaper_total_item:题数 
					paper.total_item+"&nbsp;&nbsp;"+readPaper_total_score+":"+paper.total_score+"&nbsp;&nbsp;"+readPaper_total_time+":"+total_time;// I18N - readPaper_total_score:总分 readPaper_total_time:推荐用时 
					document.getElementById("contacts1").innerHTML = info;
				}else{
					layer.msg(readPaper_find_fail);// I18N - readPaper_find_fail:查询失败 
				}
			}else{
				layer.msg(data.message,{shift: 6});
			}
		}
	});
}
//查询班级数据
function findClass(class_name){
	var info = readPaper_class_name+":"+class_name;// I18N - readPaper_class_name:名称 
	document.getElementById("contacts2").innerHTML = info;
}
//批改试卷
function correctPaper(studentId,paperId,id){
	var index = layer.open({
		title : [ readPaperManage_90_correct_paper_title, // readPaperManage_90_correct_paper_title:批改试卷
				'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		offset : '0%',// 距离页面顶距离
		area : [ '100%', '100%' ],
		fix : false, // 不固定
		maxmin : true,
		content : '../tech/paperUIAction!toCorrectPaper.do'+
			'?answerInfoVo.user_id='+studentId+
			'&answerInfoVo.paper_id='+paperId+
			'&answerInfoVo.plan_paper_id='+id
	});
	layer.full(index);
}
//查阅试卷
function readPaper(studentId,paperId,id){
	var index = layer.open({
		title : [ readPaperManage_90_read_paper_title, // readPaperManage_90_read_paper_title:查看试卷
				'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		offset : '0%',// 距离页面顶距离
		area : [ '100%', '100%' ],
		fix : false, // 不固定
		maxmin : true,
		content : '../tech/paperUIAction!toReadPaper.do'+
			'?answerInfoVo.user_id='+studentId+
			'&answerInfoVo.paper_id='+paperId
	});
	layer.full(index);
}
//绑定关闭方法
function closeFun(){
	$("a.layui-layer-close").click(function() {
		refreshTable();
	});
}
/**
 * 导出方法
 */
function exportScore(){
	window.open("../tech/teacherPlan!downScoreData.do?planPaperVo.id="+planPaperId+"&planPaperVo.plan_id="+paper_id+"&planPaperVo.class_name="+class_name);
}
function exportAnswer(){
	window.open("../tech/teacherPlan!downAnswerData.do?planPaperVo.id="+planPaperId+"&planPaperVo.plan_id="+paper_id+"&planPaperVo.class_name="+class_name);
}
function exportTranscript(){
	
	var rows = btTable.getSelected();
	if (rows.length == 0) {
		parent.layer.msg("请选择数据");
	} else {
		var student=rows[0].student_id;
		for (var i = 1; i < rows.length; i++) {
			student += ","+rows[i].student_id;
		}
		window.open("../tech/teacherPlan!downLoadTranscript.do?planPaperVo.student_ids="+student+"&planPaperVo.paper_id="+rows[0].paper_id+"&planPaperVo.plan_paper_id="+rows[0].plan_paper_id);
	}
}
//未完成
function build(){
	layer.msg("抱歉,施工中.....");
}
