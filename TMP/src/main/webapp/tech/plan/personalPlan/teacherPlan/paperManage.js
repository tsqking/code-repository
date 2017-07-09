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
	selectInitial("status", "../system/option!getOptionsByGPVal.do?value=PAPER_STATUS","",false);
	//初始化时间控件
	dateInitial([ {"id":"paper_start_time","type":"2","dir":"r"},{"id":"paper_end_time","type":"2"}]);
	tableInit("?plan_id="+planId);
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
	// 工具栏-新增按钮
	$("#btn_add").click(function() {
		var index = layer.open({
			title : [ paperManager_add_paper_test,// I18N - paperManager_add_paper_test:添加新的测试&作业 
					'background-color:#3C8DBC; color:#ffffff;' ],
			type : 2,
			offset : '0%',// 距离页面顶距离
			area : [ '100%', '100%' ],
			fix : false, // 不固定
			maxmin : true,
			content : '../tech/teacherPlan!toAddPaper.do'+
				'?planPaperVo.plan_id='+planId+
				'&planPaperVo.class_id='+class_id+
				'&planPaperVo.course_id='+course_id
		});
		layer.full(index);
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
	btTable = BtTable('table', "../tech/teacherPlan!selectPaperInfo.do"+param, 'toolbar',  
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
					//生产
					return '<a href="javascript:window.open(\''+window.location.protocol +'//'+window.location.host+row.url+'\')" >'+
					row.paper_number+'</a>';
					//测试
//					return '<a href="javascript:window.open(\''+window.location.protocol +'//'+window.location.hostname+':80'+row.url+'\')" >'+
//					row.paper_number+'</a>';
					//复旦
//					return '<a href="javascript:window.open(\''+window.location.protocol +'//'+window.location.hostname+row.url+'\')" >'+
//					row.paper_number+'</a>';
					//本地
//					if(row.url==""||row.url==null) return "";
//					var temp=row.url.split("/");
//					var p_id=temp[2];
//					var p_no=temp[3].substring(0,temp[3].indexOf('.'))
//					return '<a href="javascript:window.open(\''+window.location.protocol +'//'+window.location.hostname+':8081'+'/TMP/tech/teacherPlan!toOnlineTestPage.do?planPaperVo.paper_number='+p_no+'&amp;planPaperVo.plan_id='+p_id+'\')" >'+
//					row.paper_number+'</a>';
                }
			}, {
				title : paperManager_class_name,// I18N - paperManager_class_name:班级 
				field : 'class_name',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : paperManager_paper_type_name,// I18N - paperManager_paper_type_name:试卷属性 
				field : 'paper_type_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : paperManager_paper_start_time,// I18N - paperManager_paper_start_time:开始时间 
				field : 'paper_start_time',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : paperManager_paper_end_time,// I18N - paperManager_paper_end_time:结束时间 
				field : 'paper_end_time',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : paperManager_paper_long_time,// I18N - paperManager_paper_long_time:时长 
				field : 'paper_long_time',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true,
				formatter: function operateFormatter(value, row, index) {
					return getLongTime(row.paper_start_time,row.paper_end_time);
	            }
			}, {
				title : paperManager_paper_url,// I18N - paperManager_paper_url:考试链接
				field : 'url',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true,
				formatter: function operateFormatter(value, row, index) {
					//生产
                	return '<a href="javascript:window.open(\''+window.location.protocol +'//'+window.location.host+value+'\')" >'+
                	window.location.protocol +'//'+window.location.host+value+'</a>';
					//测试
                	/*return '<a href="javascript:window.open(\''+window.location.protocol +'//'+window.location.hostname+':81'+value+'\')" >'+
                	window.location.protocol +'//'+window.location.hostname+':81'+value+'</a>';*/
					//复旦
//					return '<a href="javascript:window.open(\''+window.location.protocol +'//'+window.location.hostname+value+'\')" >'+
//                	window.location.protocol +'//'+window.location.hostname+value+'</a>';
                	//本地
//					if(value==""||value==null) return "";
//					var temp=value.split("/");
//					var p_id=temp[2];
//					var p_no=temp[3].substring(0,temp[3].indexOf('.'))
//                	return '<a href="javascript:window.open(\''+window.location.protocol +'//'+window.location.hostname+':8080'+'/TMP/tech/teacherPlan!toOnlineTestPage.do?planPaperVo.paper_number='+p_no+'&amp;planPaperVo.plan_id='+p_id+'\')" >'+
//                	window.location.protocol +'//'+window.location.hostname+':81'+value+'</a>';
                }
			} ,{
				title : paperManager_paper_remark,// I18N - paperManager_paper_remark:备注 
				field : 'paper_remark',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true,
			} , {
				title : paperManager_status_name,// I18N - paperManager_status_name:当前状态 
				field : 'status_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : paperManager_create_time,// I18N - paperManager_create_time:创建时间 
				field : 'create_time',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			}, {
				title : paperManager_create_user,// I18N - paperManager_create_user:创建人 
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
				title : paperManager_update_user,// I18N - paperManager_update_user:更新人 
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
                	var read = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ 
                		paperManager_read +'\" onclick="readPaper(\''+row.id+'\',\''+row.paper_id+'\',\''+row.class_name+'\')"><i class="fa fa-eye"></i></a>';// I18N - paperManager_read:阅卷 
                	var ready = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ 
                		paperManager_ready_tool +'\" onclick="changePaper(\''+row.id+'\',\''+row.class_id+'\')"><i class="fa fa-send"></i></a>';// I18N - paperManager_ready_tool:布置 
                	var edit = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ 
        				paperManager_edit +'\" onclick="editPaper(\''+row.id+'\')"><i class="fa fa-edit"></i></a>';// I18N - paperManager_edit:编辑 
                	var del = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ 
        				paperManager_del +'\" onclick="deletePaper(\''+row.id+'\')"><i class="fa fa-trash"></i></a>';// I18N - paperManager_del:删除 
                	if(flag=='0'){
                		//未布置
                		return ready+'&nbsp;&nbsp;'+edit+'&nbsp;&nbsp;'+del;
                	}else{
                		//已布置
                		return read;
                	}
                }
            }],
	'update_time','desc',null);
}
//刷新表格
function refreshTable(){
	btTable.refresh("searchForm");
}
//删除按钮
function deletePaper(id){
	layer.open({
		title : [ paperManager_del_title, 'background-color:#3C8DBC; color:#ffffff;' ],// I18N - paperManager_del_title:删除提示 
		content : paperManager_delete_title,// I18N - paperManager_delete_title:确认删除该条记录? 
		btn : [ confirm, cancel ],
		shadeClose : false,
		yes : function() {
				$.ajax({
					url : "../tech/teacherPlan!deletePaperTest.do",
					data: {"id": id },
					dataType : "json",
					type : "get",
					success : function(data) {
						if (data.success == 'true') {
							if (data.message == '1') {//删除失败
								layer.alert(paperManager_fail , {// I18N - paperManager_fail:失败 
									title : paperManager_del_fail// I18N - paperManager_del_fail:删除失败! 
								});
							} else if (data.message == '0')  {//删除成功
								layer.msg(paperManager_del_success);// I18N - paperManager_del_success:删除成功! 
								refreshTable();
							}
						} else {
							layer.msg(paperManager_del_fail);// I18N - paperManager_del_fail:删除失败! 
							console.info(data.message);
						}
					}
				});
		}	
	});
}
//编辑按钮
function editPaper(id){
	var index2 = layer.open({
		title : [ paperManager_edit_paper_title,// I18N - paperManager_edit_paper_title:编辑联系&测试 
				'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		offset : '0%',// 距离页面顶距离
		area : [ '100%', '100%' ],
		fix : false, // 不固定
		maxmin : true,
		content : '../tech/teacherPlan!toEditPaper.do'+
			'?planPaperVo.id='+id
	});
	layer.full(index2);
}
//布置按钮
function changePaper(id,classId){
	layer.open({
		title : [ paperManager_ready_title, 'background-color:#3C8DBC; color:#ffffff;' ],// I18N - paperManager_ready_title:布置提示 
		content : paperManager_ready_title_info,// I18N - paperManager_ready_title_info:确认布置该测试&练习(布置之后将无法修改)? 
		btn : [ confirm, cancel ],
		shadeClose : false,
		yes : function() {
				$.ajax({
					url : "../tech/teacherPlan!changePaperTest.do",
					data: "planPaperVo.id="+id+"&planPaperVo.class_id="+classId,
					dataType : "json",
					type : "get",
					success : function(data) {
						if (data.success == 'true') {
							if (data.message == '1') {//删除失败
								layer.alert(paperManager_ready , {// I18N - paperManager_ready:失败 
									title : paperManager_ready_fail// I18N - paperManager_ready_fail:布置失败! 
								});
							} else if (data.message == '0')  {//删除成功
								layer.msg(paperManager_ready_success);// I18N - paperManager_ready_success:布置成功! 
								refreshTable();
							}
						} else {
							layer.msg(paperManager_ready_fail);// I18N - paperManager_ready_fail:布置失败! 
							console.info(data.message);
						}
					}
				});
		}	
	});
}
//阅卷按钮
function readPaper(id,paper_id,class_name){
	var index3 = layer.open({
		title : [ paperManager_read_paper,// I18N - paperManager_read_paper:批改试卷 
				'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		offset : '0%',// 距离页面顶距离
		area : [ '100%', '100%' ],
		fix : false, // 不固定
		maxmin : true,
		content : '../tech/teacherPlan!toReadPaper.do'+
			'?planPaperVo.id='+id+'&planPaperVo.paper_id='+paper_id+'&planPaperVo.class_name='+class_name
	});
	layer.full(index3);
}
