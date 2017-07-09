var btTable;
var planId;
var class_id;
var course_id;
$(function() {
	planId = document.getElementById("planId").value;
	class_id = document.getElementById("class_id").value;
	course_id = document.getElementById("course_id").value;
	//初始化下拉框  试卷属性 当前状态
	selectInitial("paper_type", "../system/option!getOptionsByGPVal.do?value=PAPER_PROP","",false);
	selectInitial("status", "../system/option!getOptionsByGPVal.do?value=PAPER_FINISH_STATUS","",false);
	//初始化时间控件
	dateInitial([ {"id":"start_time","type":"2","dir":"r"},{"id":"end_time","type":"2"}]);
	tableInit("?plan_paper_id="+planId);
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
//表格初始化
function tableInit(param){
	if(param==null){
		param="";
	}
	// 初始化BootStrapTable
	btTable = BtTable('table', "../tech/studentPlan!selectStuPaperHisInfo.do"+param, 'toolbar',  
			[ 
			//{	field : '',checkbox : true,}, 
			{
				title : paperManager_paper_name,// I18N - paperManager_paper_name:试卷名称 
				field : 'paper_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true,
			}, {
				title : paperManager_paper_number,// I18N - paperManager_paper_number:试卷编号 
				field : 'paper_number',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true,
				formatter: function operateFormatter(value, row, index) {
					return '<a href="javascript:window.open(\''+window.location.protocol +'//'+window.location.host+row.url+'\')" >'+
					row.paper_number+'</a>';
                }
			}, {
				title : paperManager_class_name,// I18N - paperManager_class_name:班级 
				field : 'class_name',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : paperManager_course_name,// I18N - paperManager_course_name:课程 
				field : 'course_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : paperManager_paper_type_name,// I18N - paperManager_paper_type_name:试卷属性 
				field : 'paper_type_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : paperManager_paper_start_time,// I18N - paperManager_paper_start_time:试卷开放时间 
				field : 'paper_start_time',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : paperManager_paper_end_time,// I18N - paperManager_paper_end_time:试卷结束时间 
				field : 'paper_end_time',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true,
			}, {
				title : paperManager_paper_long_time,// I18N - paperManager_paper_long_time:开放时长 
				field : 'paper_long_time',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true,
			}, {
				title : paperManager_paper_url,// I18N - paperManager_paper_long_time:试卷链接
				field : 'url',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true,
				formatter: function operateFormatter(value, row, index) {
                	return '<a href="javascript:window.open(\''+window.location.protocol +'//'+window.location.host+value+'\')" >'+window.location.protocol +'//'+window.location.host+value+'</a>';
                }
			}, {
				title : paperManager_start_time,// I18N - paperManager_start_time:答卷开始时间 
				field : 'start_time',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : paperManager_end_time,// I18N - paperManager_end_time:答卷结束时间 
				field : 'end_time',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true,
			} ,  {
				title : paperManager_long_time,// I18N - paperManager_long_time:完成耗时
				field : 'long_time',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : paperManager_status_name,// I18N - paperManager_status_name:当前状态 
				field : 'status_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : paperManager_check_time,// I18N - paperManager_check_time:批卷时间 
				field : 'check_time',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : paperManager_teacher_name,// I18N - paperManager_teacher_name:批卷人 
				field : 'teacher_name',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			},{
				title : paperManager_score,// I18N - paperManager_score:分数 
				field : 'score',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			},{
				title : paperManager_create_time,// I18N - paperManager_create_time:创建时间 
				field : 'create_time',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			}, {
				title : paperManager_create_person,// I18N - paperManager_create_person:创建人 
				field : 'create_person',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : paperManager_update_time,// I18N - paperManager_update_time:更新时间 
				field : 'update_time',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			}, {
				title : paperManager_update_person,// I18N - paperManager_update_person:更新人 
				field : 'update_person',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			},{
                field: paperManager_action,// I18N - paperManager_action:操作 
                title: paperManager_action,// I18N - paperManager_action:操作 
                align: 'center',
                events: { },
                formatter: function operateFormatter(value, row, index) {
                	var flag = row.status;
                	//<a href="javascript:window.open(\''+window.location.protocol +'//'+window.location.host+row.url+'\')" >
                	var wirte = '<a href="javascript:window.open(\''+window.location.protocol +'//'+window.location.host+row.url+'\')" data-toggle="tooltip" title=\"'+ 
                		paperManager_wirte +'\" onclick="wirtePaper()"><i class="fa fa-pencil-square-o"></i></a>';// I18N - paperManager_wirte:填写试卷 
                	var look = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ 
            			paperManager_look +'\" onclick="lookPaper(\''+row.paper_id+'\',\''+row.student_id+'\')"><i class="fa fa-file-text-o"></i></a>';// I18N - paperManager_look:查看试卷 
                	if(flag=='0'){
                		//未完成
                		var paper_start_time = timeTimeToNew(row.paper_start_time);
                		if(paper_start_time=="1"){
                			return paperManager_no_open;// I18N - paperManager_no_open:未开放 
                		}else{
                			var paper_end_time = timeTimeToNew(row.paper_end_time);
                			if(paper_end_time=="-1"){
                				return paperManager_closed;// I18N - paperManager_closed:已关闭 
                			}else{
                				return wirte;
                			}	
                		}	
                	}else if(flag=='1'){
                		//已完成
                		return look;
                	}else if(flag=='2'){
                		//已批阅
                		return look;
                	}
                }
            }],
	'update_time','desc',null);
}
//刷新表格
function refreshTable(){
	btTable.refresh("searchForm");
}
//填写试卷
function wirtePaper(){
	layer.msg("施工中.......");
}
//查看试卷
function lookPaper(paperId,studentId){
	var index2 = layer.open({
		title : [ paperManager_33_read_paper_title, // paperManager_33_read_paper_title:查看试卷
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
	layer.full(index2);
}
