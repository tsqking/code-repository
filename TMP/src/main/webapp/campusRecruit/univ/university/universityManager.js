var btTable;
$(function() {
	// 初始化BootStrapTable
	btTable = BtTable('table', "../univ/university!selectUniversity.do", 'toolbar',  [ {
				field : 'status',
				checkbox : true,
			}, {
				title : '学校名称',
				field : 'name',
				align : 'center',
				valign : 'middle',
				sortable : true,
				cardVisible:false
			}, {
				title : '学校层次',
				field : 'type',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '学校性质',
				field : 'quality',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '国家',
				field : 'country',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : '所在省份',
				field : 'provinceName',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : '所在市区',
				field : 'cityName',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : '详细地址',
				field : 'detail_addr',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '学校网址',
				field : 'website',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '学校电话',
				field : 'phone',
				align : 'center',
				valign : 'middle',
				sortable : true,
			} , {
				title : '学校邮箱',
				field : 'email',
				align : 'center',
				valign : 'middle',
				sortable : true
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
			title : [ add_new_university_title,
					'background-color:#3C8DBC; color:#ffffff;' ],
			type : 2,
			offset : '1%',// 距离页面顶距离
			area : [ '65%', '65%' ],
			fix : false, // 不固定
			maxmin : true,
			content : '../univ/university!toAddUniversityPage.do'
		});
	});
	// 工具栏-新增分校信息
	$("#btn_add_newuniv").click(function() {
		var rows = btTable.getSelected();
		if (rows.length != 1) {
			layer.msg("请选择一所学校");
		}else{
			var row = rows[0];
			window.location.href="../univ/univBranch!univBranchPage.do?universityVo.id=" + row.id;
		}
	});
	// 工具栏-新增学校分院信息
	$("#btn_add_college").click(function() {
		var rows = btTable.getSelected();
		if (rows.length != 1) {
			layer.msg("请选择一所学校");
		}else{
			var row = rows[0];
			window.location.href="../univ/college!collegeManagePage.do?universityVo.id=" + row.id;
		}
	});
	// 批量添加学校信息
	$("#btn_batchAdd_university").click(function() {
		var index = layer.open({
			title : [ btn_batchAdd_university,
					'background-color:#3C8DBC; color:#ffffff;' ],
			type : 2,
			offset : '1%',// 距离页面顶距离
			area : [ '65%', '1%' ],
			fix : false, // 不固定
			maxmin : true,
			content : '../univ/university!tobBatchAddUniversityPage.do'
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
				title : [ universityUpdateWindowTitle,
						'background-color:#3C8DBC; color:#ffffff;' ],
				offset : '1%',// 距离页面顶距离
				type : 2,
				area : [ '65%', (h - 0.46 * h) + 'px' ],
				fix : false, // 不固定
				maxmin : true,
				content : "../univ/university!toEditUniversityPage.do?universityVo.id=" + row.id,
			});
		}
	});
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
				content : university_delete_content,
				btn : [ confirm, cancel ],
				shadeClose : false,
				yes : function() {
						$.ajax({
							url : "../univ/university!deleteUniversity.do",
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
	// 根据code==0 查找出第一级联动数据
	selectInitial("university", '../univ/university!selectMainUniversity.do?universityVo.parent_id=0', "", false);
	selectInitial("regionName", '../region/chinaregion!findRegionOption.do?chinaRegionVo.parentId=0', "", false);
	// 获取二级联下拉框  省事信息
	$('#regionName').on( 'change', function() {
		var selectedOption = $(this).find("option:selected").val();
		var code = selectedOption;
		if (selectedOption == null || "" == selectedOption) {
			return;
		}
		selectInitial("secondregionName", '../region/chinaregion!findRegionOption.do?chinaRegionVo.parentId='+code, "", false);
	});
})
//刷新表格
function refreshTable(){
	btTable.refresh("searchForm");
}