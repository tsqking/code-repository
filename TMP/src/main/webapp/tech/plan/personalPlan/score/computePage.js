var btTable;
var planId;
var class_id;
var course_id;
$(function() {
	planId = document.getElementById("planId").value;
	class_id = document.getElementById("class_id").value;
	course_id = document.getElementById("course_id").value;
	//绑定关闭刷新的方法
	parent.closeFun();
	//获取教学计划信息
	getPlanInfo();
	tableInit("?plan_id="+planId);
	btTable.Init();
	// 无期末测试卷
	$("#btn_no_paper").click(function() {
		layer.open({
			title : [ computePage_79_action_sure, 'background-color:#3C8DBC; color:#ffffff;' ],  // computePage_79_action_sure:操作确认
			content : computePage_79_action_sure_info, // computePage_79_action_sure_info:确认不设置期末综合卷并进行下一步操作?
			btn : [ confirm, cancel ],
			shadeClose : false,
			yes : function() {
				document.getElementsByClassName("layui-layer-ico layui-layer-close layui-layer-close1")[0].click();
				//取消期末卷
				$.ajax({
					url : "../tech/scoreAction!unsetLastPaper.do",
					data : "exerciseScoreRatioVo.plan_id="+planId,
					dataType : "json",
					type : "post",
					success : function(data) {
						if(data.success=='true'){
							if(data.message=='0'){
								//去第二tag 
								$("#li2").attr("style","");
								$("#tag2").trigger("click");	
								$("#li1").attr("style","display:none");
								tableInit1("?plan_id="+planId);
								btTable.Init();
							}else{
								layer.msg(computePage_79_unset_fail); // computePage_79_unset_fail:期末卷取消失败
							}
						}else{
							layer.msg(data.message,{shift: 6});
						}
					}
				});		
			}	
		});
	});
	//默认加权
	$("#btn_de_ratio").click(function() {
		layer.open({
			title : [ computePage_79_action_sure_82, 'background-color:#3C8DBC; color:#ffffff;' ],  // computePage_79_action_sure_82:操作确认
			content : computePage_79_action_sure_info_30, // computePage_79_action_sure_info_30:确认每张试卷比例都将设置成1?
			btn : [ confirm, cancel ],
			shadeClose : false,
			yes : function() {
				//每张试卷加权都设置成1
				$.ajax({
					url : "../tech/scoreAction!setDeRatio.do",
					data : "exerciseScoreRatioVo.plan_id="+planId,
					dataType : "json",
					type : "post",
					success : function(data) {
						if(data.success=='true'){
							if(data.message=='0'){
								refresh();
								layer.msg(computePage_79_de_set_success); // computePage_79_de_set_success:默认加权设置成功!
							}else{
								layer.msg(computePage_79_de_set_fail); // computePage_79_de_set_fail:默认加权设置失败!
							}
						}else{
							layer.msg(data.message,{shift: 6});
						}
					}
				});		
			}	
		});
	});
	//态度分推荐打分
	$("#btn_de_att").click(function() {
		layer.open({
			title : [ computePage_79_action_sure_50, 'background-color:#3C8DBC; color:#ffffff;' ],  // computePage_79_action_sure_50:操作确认
			content : computePage_79_action_sure_info1+'<br/>'+computePage_79_action_sure_info2, // computePage_79_action_sure_info1:默认分数即为推荐打分 // computePage_79_action_sure_info2:推荐打分将根据平时课堂分数折算态度分数
			btn : [ confirm, cancel ],
			shadeClose : false,
			yes : function() {
				//计算分数
				$.ajax({
					url : "../tech/scoreAction!setDeAtt.do",
					data : "exerciseScoreRatioVo.plan_id="+planId,
					dataType : "json",
					type : "post",
					success : function(data) {
						if(data.success=='true'){
							if(data.message=='0'){
								refresh();
								layer.msg(computePage_79_set_success); // computePage_79_set_success:推荐态度分设置成功!
							}else{
								layer.msg(computePage_79_set_fail); // computePage_79_set_fail:推荐态度分设置失败!
							}
						}else{
							layer.msg(data.message,{shift: 6});
						}
					}
				});		
			}	
		});
	});
	//默认总分比例
	$("#btn_de").click(function() {
		layer.open({
			title : [ computePage_79_action_sure_53, 'background-color:#3C8DBC; color:#ffffff;' ],  // computePage_79_action_sure_53:操作确认
			content : computePage_79_action_sure_info2_73+'&nbsp;3:1:1:5<br/>'+computePage_79_action_sure_info2_73, // computePage_79_action_sure_info1_35:默认比例为 // computePage_79_action_sure_info2_73:平时成绩占0.3,考勤占0.1,态度占0.1,期末分数占0.5
			btn : [ confirm, cancel ],
			shadeClose : false,
			yes : function() {
				$("#normal_ratio").val("3");
				$("#attendance_ratio").val("1");
				$("#attitude_ratio").val("1");
				$("#exam_ratio").val("5");
				document.getElementsByClassName("layui-layer-ico layui-layer-close layui-layer-close1")[0].click();
			}	
		});
	});
	//确认完成计算
	$("#btn_finish").click(function() {
		layer.open({
			title : [ computePage_79_action_sure_22, 'background-color:#3C8DBC; color:#ffffff;' ],  // computePage_79_action_sure_22:操作确认
			content : computePage_79_action_sure_info_31, // computePage_79_action_sure_info_31:是否完成总分成绩计算?
			btn : [ confirm, cancel ],
			shadeClose : false,
			yes : function() {
				document.getElementsByClassName("layui-layer-ico layui-layer-close layui-layer-close1")[0].click();
				finish();
			}	
		});
	});
	// 去第二tag
	$("#btn_to_2").click(function() {
		$("#li2").attr("style","");
		$("#tag2").trigger("click");	
		$("#li1").attr("style","display:none");
		//表格数据
		tableInit1("?plan_id="+planId);
		btTable.Init();
	});
	// 去第三tag
	$("#btn_to_3").click(function() {
		$("#li3").attr("style","");
		$("#tag3").trigger("click");	
		$("#li2").attr("style","display:none");
		//表格数据
		saveNormalScore();
	});
	// 去第四tag
	$("#btn_to_4").click(function() {
		$("#li4").attr("style","");
		$("#tag4").trigger("click");	
		$("#li3").attr("style","display:none");
		//获取比例分数
		getTotalRatio();
	});
	// 工具栏-返回按钮
	$("#btn_go_back_1").click(function() {
		parent.refreshTable();
		parent.layer.close(parent.layer.getFrameIndex(window.name));
	});
	$("#btn_go_back_2").click(function() {
		parent.refreshTable();
		parent.layer.close(parent.layer.getFrameIndex(window.name));
	});
	$("#btn_go_back_3").click(function() {
		parent.refreshTable();
		parent.layer.close(parent.layer.getFrameIndex(window.name));
	});
	$("#btn_go_back_4").click(function() {
		parent.refreshTable();
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
//第一tag---------------------------------------------------------------------------------------------------------------
//tag1试卷表格初始化
function tableInit(param){
	if(param==null){
		param="";
	}
	//初始化BootStrapTable
	btTable = BtTable('table', "../tech/scoreAction!selectPlanPaperInfo.do"+param, 'toolbar',  
			[ 
			//{	field : '',checkbox : true,}, 
			{
				title : computePage_79_paper_name, // computePage_79_paper_name:试卷名称
				field : 'paper_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			},{
				title : computePage_79_paper_number, // computePage_79_paper_number:试卷编号
				field : 'paper_number',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : computePage_79_paper_type_name, // computePage_79_paper_type_name:试卷用途
				field : 'paper_type_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			},{
				title : computePage_79_paper_start_time, // computePage_79_paper_start_time:开始时间
				field : 'paper_start_time',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true,
			},{
				title : computePage_79_paper_end_time, // computePage_79_paper_end_time:结束时间
				field : 'paper_end_time',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : computePage_79_paper_long_time, // computePage_79_paper_long_time:时长
				field : 'paper_long_time',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : computePage_79_paper_remark,  // computePage_79_paper_remark:备注
				field : 'paper_remark',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
                field: paperManager_action,// I18N - paperManager_action:操作 
                title: paperManager_action,// I18N - paperManager_action:操作 
                align: 'center',
                events: { },
                formatter: function operateFormatter(value, row, index) {
                	var flag = row.flag;
                	//
                	var select = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ computePage_79_set + // computePage_79_set:设置期末卷
                	'\" onclick="setLastPaper(\''+row.paper_id+'\')"><i class="fa fa-pencil-square-o"></i></a>';
                	var unselect = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ computePage_79_unset + // computePage_79_unset:取消期末卷
                	'\" onclick="unsetLastPaper(\''+row.paper_id+'\')"><i class="fa fa-file-text-o"></i></a>';
                	if(flag=='0'){
                		//未选择
                		return select;	
                	}else if(flag=='1'){
                		$('#last_paper').html(row.paper_name);
                		//已选择
                		return unselect;
                	}
                }
            }],
	'paper_start_time','desc',null);
}
/**
 * 设置期末卷
 */
function setLastPaper(paperId){
	$.ajax({
		url : "../tech/scoreAction!setLastPaper.do",
		data : "exerciseScoreRatioVo.paper_id="+paperId+"&exerciseScoreRatioVo.plan_id="+planId,
		dataType : "json",
		type : "post",
		success : function(data) {
			if(data.success=='true'){
				if(data.message=='0'){
					layer.msg(computePage_79_set_success_68); // computePage_79_set_success_68:期末卷设置成功
					btTable.refresh();
				}else{
					layer.msg(computePage_79_set_fail_36); // computePage_79_set_fail_36:期末卷设置失败
				}
			}else{
				layer.msg(data.message,{shift: 6});
			}
		}
	});
}
/**
 * 取消期末卷
 */
function unsetLastPaper(paperId){
	$.ajax({
		url : "../tech/scoreAction!unsetLastPaper.do",
		data : "exerciseScoreRatioVo.paper_id="+paperId+"&exerciseScoreRatioVo.plan_id="+planId,
		dataType : "json",
		type : "post",
		success : function(data) {
			if(data.success=='true'){
				if(data.message=='0'){
					layer.msg(computePage_79_set_success_52); // computePage_79_set_success_52:期末卷取消成功
					btTable.refresh();
				}else{
					layer.msg(computePage_79_set_fail_41); // computePage_79_set_fail_41:期末卷取消失败
				}
			}else{
				layer.msg(data.message,{shift: 6});
			}
		}
	});
}
//第二tag---------------------------------------------------------------------------------------------------------------
function tableInit1(param){
	if(param==null){
		param="";
	}
	//初始化BootStrapTable
	btTable = BtTable('table1', "../tech/scoreAction!selectPlanPaperInfo1.do"+param, 'toolbar1',  
			[ 
			//{	field : '',checkbox : true,}, 
			{
				title : computePage_79_paper_name_68, // computePage_79_paper_name_68:试卷名称
				field : 'paper_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			},{
				title : computePage_79_paper_number_32, // computePage_79_paper_number_32:试卷编号
				field : 'paper_number',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : computePage_79_paper_type_name_55, // computePage_79_paper_type_name_55:试卷用途
				field : 'paper_type_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			},{
				title : computePage_79_paper_start_time_62, // computePage_79_paper_start_time_62:开始时间
				field : 'paper_start_time',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true,
			},{
				title : computePage_79_paper_end_time_25, // computePage_79_paper_end_time_25:结束时间
				field : 'paper_end_time',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : computePage_79_paper_long_time_89, // computePage_79_paper_long_time_89:时长
				field : 'paper_long_time',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : computePage_79_paper_remark_18,  // computePage_79_paper_remark_18:备注
				field : 'paper_remark',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : computePage_79_ratio,  // computePage_79_ratio:加权比例
				field : 'ratio',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
                field: paperManager_action,// I18N - paperManager_action:操作 
                title: paperManager_action,// I18N - paperManager_action:操作 
                align: 'center',
                events: { },
                formatter: function operateFormatter(value, row, index) {
                	return  '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ computePage_79_set_45 + // computePage_79_set_45:设置加权
                	'\" onclick="setRatio(\''+row.paper_id+'\')"><i class="fa fa-pencil-square-o"></i></a>';
                }
            }],
	'paper_start_time','asc',null);
}
//打开设置比例的界面
function setRatio(paperId){
	var index = layer.open({
		title : [ computePage_79_set_ratio, // computePage_79_set_ratio:设置试卷分数比例
				'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		offset : '0%',// 距离页面顶距离
		area : [ '800px', '224px' ],
		fix : false, // 不固定
		maxmin : true,
		content : '../tech/scoreAction!toSetRatio.do?'+
			'planPaperVo.plan_id='+planId+
			'&planPaperVo.paper_id='+paperId
	});
	//layer.full(index);
}
//刷新表格
function refresh(){
	btTable.refresh();
}
//计算平时成绩
function saveNormalScore(){
	$.ajax({
		url : "../tech/scoreAction!saveNormalScore.do",
		data : "exerciseScoreRatioVo.plan_id="+planId,
		dataType : "json",
		type : "post",
		success : function(data) {
			if(data.success=='true'){
				if(data.message=='0'){
					//nothing to do
					tableInit2("?plan_id="+planId+"&flag=0");
					btTable.Init();
				}else{
					layer.msg(computePage_79_set_fail_86); // computePage_79_set_fail_86:期末卷取消失败
				}
			}else{
				layer.msg(data.message,{shift: 6});
			}
		}
	});
}
//第三tag---------------------------------------------------------------------------------------------------------------
function tableInit2(param){
	if(param==null){
		param="";
	}
	//初始化BootStrapTable
	btTable = BtTable('table2', "../tech/scoreAction!selectTotalScoreInfo.do"+param, 'toolbar2',  
			[ 
			//{	field : '',checkbox : true,}, 
			{
				title : computePage_79_student_name, // computePage_79_student_name:学生姓名
				field : 'student_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			},{
				title : computePage_79_student_no, // computePage_79_student_no:学生编号
				field : 'student_no',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : computePage_79_normal_score, // computePage_79_normal_score:平时成绩
				field : 'normal_score',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : computePage_79_attendance_score, // computePage_79_attendance_score:考勤成绩
				field : 'attendance_score',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true,
			}, {
				title : computePage_79_exam_score, // computePage_79_exam_score:期末测试
				field : 'exam_score',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : computePage_79_attitude_score, // computePage_79_attitude_score:态度成绩
				field : 'attitude_score',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
                field: paperManager_action,// I18N - paperManager_action:操作 
                title: paperManager_action,// I18N - paperManager_action:操作 
                align: 'center',
                events: { },
                formatter: function operateFormatter(value, row, index) {
                	return  '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+ computePage_79_set_attitude_score + // computePage_79_set_attitude_score:设置态度分
                	'\" onclick="setAtt(\''+row.student_id+'\')"><i class="fa fa-pencil-square-o"></i></a>';
                }
            }],
	'student_no','asc',null);
}
//打开设置态度分界面
function setAtt(student_id){
	var index1 = layer.open({
		title : [ computePage_79_set_attitude_score_95, // computePage_79_set_attitude_score_95:设置学生态度分
				'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		offset : '0%',// 距离页面顶距离
		area : [ '800px', '224px' ],
		fix : false, // 不固定
		maxmin : true,
		content : '../tech/scoreAction!toSetAtt.do?'+
			'planPaperVo.plan_id='+planId+
			'&planPaperVo.student_id='+student_id
	});
	//layer.full(index1);
}
//第四tag---------------------------------------------------------------------------------------------------------------
//获取原来的比例
function getTotalRatio(){
	$.ajax({
		url : "../tech/scoreAction!getTotalRatio.do",
		data : "totalScoreRatioVo.plan_id="+planId,
		dataType : "json",
		type : "post",
		success : function(data) {
			if(data.success=='true'){
				if(data.message=='0'){
					praseJson(data.datas.data, null, null, null);
					$('#normal_ratio').change(function(n){
						checkNumber('normal_ratio');
					});
					$('#attendance_ratio').change(function(n){
						checkNumber('attendance_ratio');				
					});
					$('#attitude_ratio').change(function(n){
						checkNumber('attitude_ratio');
					});
					$('#exam_ratio').change(function(n){
						checkNumber('exam_ratio');
					});
				}else{
					layer.msg(computePage_79_get_ratio_fail); // computePage_79_get_ratio_fail:获取比例失败
				}
			}else{
				layer.msg(data.message,{shift: 6});
			}
		}
	});
}
//校验是否为正整数
function checkNumber(id){
	var num = $("#"+id).val();
	if(num*1!=0){
		var re = /^[0-9]*[1-9][0-9]*$/ ; 
		if(re.test(num)){
			//正整数
			//nothing to do
		}else{
			//不为正整数
			$("#"+id).val("1");
			layer.msg(computePage_79_input_right_format); // computePage_79_input_right_format:请输入正确的格式
		}
	}
}
//完成总分计算
function finish(){
	var itemArr=[
	             {"id":'normal_ratio',"type":'2',"regular":null,"message":null},
	             {"id":'attendance_ratio',"type":'2',"regular":null,"message":null},
	             {"id":'attitude_ratio',"type":'2',"regular":null,"message":null},
	             {"id":'exam_ratio',"type":'2',"regular":null,"message":null},
	             ];
	if(validate(itemArr)){
		$.ajax({
			url : "../tech/scoreAction!finish.do",
			data : $("#ratioForm").serialize(),
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.success=='true'){
					if(data.message=='0'){
						parent.layer.msg(computePage_79_success); // computePage_79_success:计算成功!
						//刷新表格
						parent.refreshTable();
						//关闭窗口
						parent.layer.close(parent.layer.getFrameIndex(window.name));
					}else if(data.message=='1'){
						parent.layer.msg(computePage_79_fail); // computePage_79_fail:计算失败!
					}
				}else{
					layer.msg(data.message,{shift: 6});
				}
			}
		});
	}	
}
