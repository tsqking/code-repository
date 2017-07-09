var btTable;
$(function() {
	// 初始化 操作状态下拉框 
	selectInitial("op_status", "../system/option!getOptionsByGPVal.do?value=OP_STATE","",false);
	//总校名称下拉框
	selectInitial("univ_id", '../univ/attn!findMainUniv.do?attnVo.id=0', "", false);
	// 获取二级联下拉框
	$('#univ_id').on( 'change', function() {
		var selectedOption = $(this).find("option:selected").val();
		var code = selectedOption;
		if (selectedOption == null || "" == selectedOption) {
			return;
		}
		selectInitial("univ_branch_id", '../univ/attn!findMainUnivBranch.do?attnVo.id='+code, "", false);
	});
	//初始化时间控件
	dateInitial([ {"id":"opreationTime","type":"2"}]);
	// 初始化BootStrapTable
	btTable = BtTable('table', "../univ/workHist!selectWorkHist.do", 'toolbar',  [ {
				field : 'status',
				checkbox : true,
			}, {
				title : '学校名称',
				field : 'univ_name',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '分校名称',
				field : 'univ_branch_name',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '学院名称',
				field : 'college_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : '操办人名',
				field : 'op_user_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : '工作内容',
				field : 'work_content',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : '操作状态',
				field : 'op_status',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '操作时间',
				field : 'op_time',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '备注',
				field : 'remark',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true,
			} , {
				title : '创建时间',
				field : 'create_time',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			}, {
				title : '创建人员',
				field : 'create_user',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : '更新时间',
				field : 'update_time',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			}, {
				title : '更新人员',
				field : 'update_user',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			}  ],
	'id', 'asc',null);
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
		var index = layer.open({
			title : [ add_new_workHist_title,
					'background-color:#3C8DBC; color:#ffffff;' ],
			type : 2,
			offset : '1%',// 距离页面顶距离
			area : [ '65%', '65%' ],
			fix : false, // 不固定
			maxmin : true,
			content : '../univ/workHist!toAddWorkHistPage.do'
		});
	});
	
	// 工具栏-编辑按钮
	$("#btn_edit").click(function() {
		var rows = btTable.getSelected();
		if (rows.length != 1) {
			layer.msg("请选择一行数据更新");
		} else {
			var row = rows[0];
			//弹出框进行更新
			var h = document.documentElement.clientHeight;
			layer.open({
				title : [ workHistUpdateWindowTitle,
						'background-color:#3C8DBC; color:#ffffff;' ],
				offset : '1%',// 距离页面顶距离
				type : 2,
				area : [ '65%', (h - 0.46 * h) + 'px' ],
				fix : false, // 不固定
				maxmin : true,
				content : "../univ/workHist!toEditWorkHistPage.do?workHistVo.id=" + row.id,
			});
		}
	});
	//双击事件
	$("#table").bootstrapTable().on('dbl-click-row.bs.table',function(e,row,$element){
		layer.open({
			title: [
					viewWorkhistTitle1+row.univ_name+viewWorkhistTitle2,
				 	'background-color:#3C8DBC; color:#ffffff;'
			],
			offset: '0%',// 距离页面顶距离
		    type: 2,
		    area: [ '65%', '65%' ],
		    fix: false, //不固定
		    maxmin: true,
		    content: "../univ/workHist!viewWorkHistPage.do?workHistVo.id=" + row.id,
		});
	});;
	// 工具栏-删除按钮
	$("#btn_delete").click(function() {
		var rows = btTable.getSelected();
		if (rows.length == 0) {
			layer.msg("请选择要删除的数据！");
		} else { 
		  var list =""; 
		  for (var i = 0; i < rows.length; i++) {
			  if(i==0)
				  list+=rows[i].id;
			  else
				  list+=","+rows[i].id;
		  }
		  console.info("*****"+list);
		layer.open({
			title : [ deleteInfo, 'background-color:#3C8DBC; color:#ffffff;' ],
			content : workHist_delete_content,
			btn : [ confirm, cancel ],
			shadeClose : false,
			yes : function() {
					$.ajax({
						url : "../univ/workHist!deleteWorkHist.do",
						data: {"id":list},
						dataType : "json",
						type : "get",
						success : function(data) {
							if (data.success == 'true') {
								if (data.message == '1') {//删除失败
									layer.alert(fail_delete , {
										title : fail_delete_title
									});
								} else if (data.message == '0')  {//删除成功
									layer.msg(success_delete);
									refreshTable();
								}
							} else {
								layer.msg("删除失败！");
								console.info(data.message);
							}
						}
					});
				}
			});
		}
	});
})
//刷新表格
function refreshTable(){
	btTable.refresh("searchForm");
}