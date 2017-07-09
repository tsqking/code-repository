//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 
//搜索数据 (必须)
var searchData = [ { "name" : "more_data" , "value" : "my_value" } ];

var btTable;
function refreshTable(){
	btTable.refresh("searchForm");
}
$(function() {
	//下拉框初始化
	selectInitial("use_flag","../system/option!getOptionsByGPVal.do?value=USE_FLAG", null, false);
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS", null, false);
	
	// 初始化BootStrapTable
	btTable = BtTable('table', "../question/paper!findPaperData.do", 'toolbar', 
			[ {
				field : 'status',
				searchable:true,
				radio : true
			}, {
				title : name,
				field : 'name',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : no,
				field : 'no',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : property,
				field : 'property',
				align : 'center',
				valign : 'middle',
				visible: true,
				sortable : true
			}, {
				title : description,
				field : 'description',
				align : 'center',
				valign : 'middle',
				visible: false
			}, {
				title : instruction,
				field : 'instruction',
				align : 'center',
				valign : 'middle',
				visible: false
			}, {
				title : total_item,
				field : 'total_item',
				align : 'center',
				valign : 'middle',
				visible: false,
				sortable: true
			}, {
				title : total_score,
				field : 'total_score',
				align : 'center',
				valign : 'middle',
				visible: false,
				sortable : true
			}, {
				title : total_time,
				field : 'total_time',
				align : 'center',
				valign : 'middle',
				visible: false,
				sortable: true
			}, {
				title : leave_limit,
				field : 'leave_limit',
				align : 'center',
				valign : 'middle',
				visible: false,
				sortable : true
			}, {
				title : url,
				field : 'url',
				align : 'center',
				valign : 'middle',
				visible: false,
				sortable: true
			}, {
				title : finish_flag,
				field : 'finish_flag',
				align : 'center',
				valign : 'middle',
				visible: false,
				sortable: true
			}, {
				title : use_flag,
				field : 'use_flag',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : enable,
				field : 'enable',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : create_time,
				field : 'create_time',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : create_person,
				field : 'create_person',
				align : 'center',
				valign : 'middle',
				visible : false
			}, {
				title : update_time,
				field : 'update_time',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : update_person,
				field : 'update_person',
				align : 'center',
				valign : 'middle',
				visible : true
			}],
			'update_time', 'desc',null);
	btTable.Init();
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
		layer.open({
			title : [ addTitle, 'background-color:#3C8DBC; color:#ffffff;' ],
			type : 2,
			area : [ '100%', '100%' ],
			fix : false, // 不固定
			maxmin : true,
			scrollbar: false,
			content : '../question/paper!toAddNewPaperPage.do'
		});
		//编辑数据后刷新表格
		btTable.refresh("searchForm");
	});
	
	// 工具栏-编辑按钮
	$("#btn_edit").click(function() {
		var rows = btTable.getSelected();
		if (rows.length != 1) {
			layer.msg(choiseEditData);
		} else {
			var row = rows[0];
			$.ajax({
				url:"../question/paper!validatePaperStatus.do",
				data:{"paper_id":row.id},
				dataType:"json",
				type:"get",
				success:function(data){
					if(data.success=='true'){
						if(data.message=="0000"){
							layer.open({
								title: [
										updatePaper,
									 	'background-color:#3C8DBC; color:#ffffff;'
								],
							    type: 2,
							    scrollbar: false,
							    area: [ '100%' , '100%'],
							    fix: false, //不固定
							    maxmin: true,
							    content: "../question/paper!toPaperEditPage.do?paperVo.id="+row.id,
							});
							//编辑数据后刷新表格
							btTable.refresh("searchForm");
						}else if(data.message=="1111"){
							layer.msg(openPaperFail);
						}
					}else{
						layer.alert(delPaperFail,{title:feedback});
					}
				}
			});
		}
	});
	
	//工具栏-删除试卷
	$("#btn_delete").on("click",function(){
		var rows = btTable.getSelected();
		if (rows.length != 1) {
			layer.msg(choiseEditData);
		} else {
			var row = rows[0];
			layer.open({
				title: [
					delPaperTitle,
				 	'background-color:#3C8DBC; color:#ffffff;'
				],
			    content: delPaperContent,
			    btn: [confirm,cancel],
			    shadeClose: false,
			    yes: function(){
			    	$.ajax({
						url:"../question/paper!deletePaper.do",
						data: {"paperVo.id":row.id},
						dataType:"json",
						type:"get",
						success:function(data){
							if(data.success=='true'){
								if(data.message=="0000"){
									layer.msg(delPaperSuc);
									//编辑数据后刷新表格
									btTable.refresh("searchForm");
								}else if(data.message=="1111"){
									layer.alert(delPaperFail,{title:feedback});
								}
							}else{
								layer.alert(delPaperFail,{title:feedback});
							}
						}
					});
			    }
			});
		}
	});
	
	// 工具栏-禁用按钮
	$("#btn_enable").on("click",function(){
		var rows = btTable.getSelected();
		if (rows.length == 0) {
			layer.msg(chooseData);
		} else {
			var row = rows[0];
			if($("#btn_enable").html()=='<span class="fa fa-unlock" aria-hidden="true"></span>&nbsp;&nbsp;'+EnableBtn){
				$.ajax({
					url:"../question/paper!changeEnableState.do",
					data: {"id":row.id,"enable":"T"},
					dataType:"json",
					type:"get",
					success:function(data){
						if(data.success=='true'){
							layer.msg(enableSuccess);//var enableSuccess="启用成功";
							//编辑数据后刷新表格
							btTable.refresh("searchForm");
						}else{
							if(data.message=='-1'){//未找到数据
								layer.alert(operateFail,{title:feedback});//var operateFail="操作未成功";
							}else{
								layer.alert(wrongMsg);//var wrongMsg="出错了";
								console.info(data.message);
							}
						}
					}
				});
			}else if($("#btn_enable").html()=='<span class="fa fa-lock" aria-hidden="true"></span>&nbsp;&nbsp;'+DisableBtn){
		    	$.ajax({
					url:"../question/paper!changeEnableState.do",
					data: {"id":row.id,"enable":"F"},
					dataType:"json",
					type:"get",
					success:function(data){
						if(data.success=='true'){
							layer.msg(disableSuccess);//var disableSuccess="禁用成功";
							//编辑数据后刷新表格
							btTable.refresh("searchForm");
						}else{
							if(data.message=='-1'){//被使用中
								layer.alert(operateFail,{title:feedback});
							}else{
								layer.alert(wrongMsg);
								console.info(data.message);
							}
						}
					}
				});
			}
		}
	});
	
	// 工具栏-拷贝按钮
	$("#btn_copy").on("click",function(){
		var rows = btTable.getSelected();
		if (rows.length == 0) {
			layer.msg(chooseData);
		} else {
			var row = rows[0];
			layer.open({
				title : [ copy_paper, 'background-color:#3C8DBC; color:#ffffff;' ],
				type : 2,
				area : [ '100%', '100%' ],
				fix : false, // 不固定
				maxmin : true,
				scrollbar: false,
				content : '../question/paper!toCopyNewPaperPage.do?paperVo.id='+row.id
			});
		}
	});
	
	//双击查看详情
	$("#table").bootstrapTable(
	).on('dbl-click-row.bs.table',function(e,row,$element){
		layer.open({
			title: [
					paperDetail,
				 	'background-color:#3C8DBC; color:#ffffff;'
			],
			offset: '1%',// 距离页面顶距离
		    type: 2,
		    scrollbar: false,
		    area: [ '80%' , '99%'],
		    fix: false, //不固定
		    maxmin: true,
		    content: "../question/paper!toPaperDetailPage.do?paperVo.id="+row.id,
		});
	});
	//单击启用禁用显示
	$("#table").bootstrapTable(
	).on('click-row.bs.table',function(e,row,$element){
		if(row.enable != "T" && row.enable != "启用状态" && row.enable != "Enable"){
			$("#btn_enable").attr("class","btn btn-default");
			$("#btn_enable").html("<span class='fa fa-unlock' aria-hidden='true'></span>&nbsp;&nbsp;"+EnableBtn);
		}else if(row.enable != "F" && row.enable != "禁用状态" && row.enable != "Disable"){
			$("#btn_enable").attr("class","btn btn-default");
			$("#btn_enable").html("<span class='fa fa-lock' aria-hidden='true'></span>&nbsp;&nbsp;"+DisableBtn);
		}
	});
	
	//试卷导出
	$("#btn_export").on("click",function(){
		var rows = btTable.getSelected();
		if (rows.length == 0) {
			layer.msg(chooseData);
		} else {
			var row = rows[0];
			window.open("../question/paper!downLoadPaperDoc.do?paperVo.Id="+row.id);
		}
	});
})