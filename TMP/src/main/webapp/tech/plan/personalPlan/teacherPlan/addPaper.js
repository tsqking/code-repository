var btTable;
var planId;
var class_id;
var course_id;
$(function() {
	planId = document.getElementById("planId").value;
	class_id = document.getElementById("class_id").value;
	course_id = document.getElementById("course_id").value;
	//初始化下拉框  试卷属性 用途
	selectInitial("use_flag", "../system/option!getOptionsByGPVal.do?value=USE_FLAG","",false);
	selectInitial("property", "../system/option!getOptionsByGPVal.do?value=PAPER_PROP","",false);
	// 初始化BootStrapTable
	btTable = BtTable('table', 
			"../tech/teacherPlan!selectPaperList.do?plan_id="+planId+"&course_id="+course_id+"&class_id="+class_id, 
			'toolbar',  
			[ 
			{	field : '',radio : true,}, 
			{
				title : addPaper_name,// I18N - addPaper_name:试卷名称 
				field : 'name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true,
			}, {
				title : addPaper_no,// I18N - addPaper_no:试卷编号 
				field : 'no',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : addPaper_use_flag_name,// I18N - addPaper_use_flag_name:试卷用途 
				field : 'use_flag_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : addPaper_property_name,// I18N - addPaper_property_name:试卷属性 
				field : 'property_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : addPaper_description,// I18N - addPaper_description:试卷描述 
				field : 'description',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : addPaper_instruction,// I18N - addPaper_instruction:试卷说明 
				field : 'instruction',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : addPaper_total_item,// I18N - addPaper_total_item:总题数 
				field : 'total_item',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : addPaper_total_score,// I18N - addPaper_total_score:总分数 
				field : 'total_score',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true,
			} , {
				title : addPaper_total_time_name,// I18N - addPaper_total_time_name:总时长 
				field : 'total_time_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : addPaper_url,// I18N - addPaper_url:考试链接 
				field : 'url',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			},{
				title : addPaper_leave_limit,// I18N - addPaper_leave_limit:限定离开次数 
				field : 'leave_limit',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			},{
				title : addPaper_create_time,// I18N - addPaper_create_time:创建时间 
				field : 'create_time',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			}, {
				title : addPaper_create_person,// I18N - addPaper_create_person:创建人 
				field : 'create_person',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : addPaper_update_time,// I18N - addPaper_update_time:更新时间 
				field : 'update_time',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			}, {
				title : addPaper_update_person,// I18N - addPaper_update_person:更新人 
				field : 'update_person',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			}],
	'update_time','desc',null);
	btTable.Init();
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
	// 工具栏-添加按钮
	$("#btn_add").click(function() {
		var rows = btTable.getSelected();
		if (rows.length != 1) {
			layer.msg(addPaper_select_action);// I18N - addPaper_select_action:请选择一条数据进行操作 
		} else {
			var row = rows[0];
			//弹出框进行更新
			var index = layer.open({
				title : [ addPaper_input_info,// I18N - addPaper_input_info:输入信息 
						'background-color:#3C8DBC; color:#ffffff;' ],
				offset : '0%',// 距离页面顶距离
				type : 2,
				area : [ '100%', '100%' ],
				fix : false, // 不固定
				maxmin : true,
				content : "../tech/teacherPlan!toAddPaper2.do" +
					"?planPaperVo.plan_id=" + planId + 
					"&planPaperVo.class_id=" + class_id + 
					"&planPaperVo.course_id=" + course_id + 
					"&paperVo.id="+row.id+
					"&paperVo.no="+row.no+
					"&paperVo.name="+row.name+
					"&paperVo.use_flag_name="+row.use_flag_name+
					"&paperVo.property_name="+row.property_name+
					"&paperVo.total_item="+row.total_item+
					"&paperVo.total_score="+row.total_score+
					"&paperVo.total_time="+row.total_time,
			});
			layer.full(index);
		}
	});
})
//刷新表格
function refreshTable(){
	btTable.refresh("searchForm");
}
//关闭方法,并刷新上层数据表格
function closeWindows(){
	parent.refreshTable();
	parent.layer.close(parent.layer.getFrameIndex(window.name));
}
