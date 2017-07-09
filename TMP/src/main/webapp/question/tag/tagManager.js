var btTable;
function refreshBt(){
	//编辑数据后刷新表格
	btTable.refresh("searchForm");
}

$(function(){
	//下拉框初始化
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS", null, false);
	// 初始化BootStrapTable
	btTable = BtTable('table', "../question/tag!getTagPage.do", 'toolbar', 
			[ {
				field : 'status',
				radio : true
			}, {
				title : name,
				field : 'name',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : description,
				field : 'description',
				align : 'center',
				valign : 'middle'
			}, {
				title : use_count,
				field : 'user_count',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : enable,
				field : 'enable',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : create_time,
				field : 'create_time',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			}, {
				title : create_person,
				field : 'create_person',
				align : 'center',
				valign : 'middle'
			} ],
			'user_count', 'desc',null);
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
			//"添加标签"资源文件
			title : [ addNewTagTitle, 'background-color:#3C8DBC; color:#ffffff;' ],
			type : 2,
			area : [ '60%', '32%' ],
			offset: '1%',// 距离页面顶距离
			fix : false, // 不固定
			maxmin : true,
			content : '../question/tag!addNewTagPage.do'
		});
	});
	
	// 工具栏-禁用按钮
	$("#btn_delete").click(function() {
		var rows = btTable.getSelected();
		if (rows.length == 0) {
			layer.msg(pleaseSelectTag);
		} else {
			var row = rows[rows.length-1];
			if($("#btn_delete").html()=='<span class="fa fa-unlock" aria-hidden="true"></span>&nbsp;&nbsp;'+Enable){
				$.ajax({
					url:"../question/tag!changeTagStateEnable.do",
					data: {"id":row.id},
					dataType:"json",
					type:"get",
					success:function(data){
						if(data.success=='true'){
							layer.msg(enableTagSuccess);
							//编辑数据后刷新表格
							btTable.refresh("searchForm");
						}else{
							if(data.message=='-1'){//未找到数据
								layer.alert(enableTagFail,{title:feedback});
							}else{
								layer.msg(data.message);
							}
						}
					}
				});
			}else if($("#btn_delete").html()=='<span class="fa fa-lock" aria-hidden="true"></span>&nbsp;&nbsp;'+Disabled){
				layer.open({
					title: [
						disableTagTitle,
					 	'background-color:#3C8DBC; color:#ffffff;'
					],
				    content: disableTagContent,
				    btn: [confirm,cancel],
				    shadeClose: false,
				    yes: function(){
				    	$.ajax({
							url:"../question/tag!changeTagStateDisable.do",
							data: {"id":row.id},
							dataType:"json",
							type:"get",
							success:function(data){
								if(data.success=='true'){
									layer.msg(disableTagSuccess);
									//编辑数据后刷新表格
									btTable.refresh("searchForm");
								}else{
									if(data.message=='0'){//被使用中
										layer.alert(disableTagFail,{title:feedback});
									}else{
										layer.msg(data.message);
									}
								}
							}
						});
				    }
				});
			}
		}
	});
	
	//根据每行的状态改变禁用和启用按钮
	$("#table").bootstrapTable({ }).on('click-row.bs.table',function(e,row,$element){
		if(row.enable != "T" && row.enable != "启用状态" && row.enable != "Enable"){
			$("#btn_delete").attr("class","btn btn-default");
			$("#btn_delete").html("<span class='fa fa-unlock' aria-hidden='true'></span>&nbsp;&nbsp;"+Enable);
		}else if(row.enable != "F" && row.enable != "禁用状态" && row.enable != "Disable"){
			$("#btn_delete").attr("class","btn btn-default");
			$("#btn_delete").html("<span class='fa fa-lock' aria-hidden='true'></span>&nbsp;&nbsp;"+Disabled);
		}
	});
});
