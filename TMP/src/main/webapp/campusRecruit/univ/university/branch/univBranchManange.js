var btTable;
$(function() {
	// 初始化BootStrapTable
	var id=document.getElementById("getid").value;
	// 根据id==0 查找出第一级联动数据
	selectInitial("univBranchName", '../univ/univBranch!selectUnivBranchOptions.do?universityVo.id='+id, "", false);
	 btTable = BtTable('table', "../univ/univBranch!selectUnivBranch.do", 'toolbar',  [ {
				field : 'status',
				checkbox : true,
			}, {
				title : '学校名称',
				field : 'univ_name',
				align : 'center',
				valign : 'middle',
				sortable : true,
				cardVisible:true
			}, {
				title : '分校名称',
				field : 'name',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '学校层次',
				field : 'type',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : '学校性质',
				field : 'quality',
				align : 'center',
				valign : 'middle',
				visible : true,
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
			},{
				title : '详细地址',
				field : 'detail_addr',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '分校网址',
				field : 'website',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '分校电话',
				field : 'phone',
				align : 'center',
				valign : 'middle',
				sortable : true
			} , {
				title : '分校邮箱',
				field : 'email',
				align : 'center',
				valign : 'middle',
				sortable : true
			} , {
				title : '创建时间',
				field : 'create_time',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
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
			'id', 'asc',{"id":id});
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
				var id=document.getElementById("getid").value;
				var index = layer.open({
					title : [ add_new_univbranch_title,
							'background-color:#3C8DBC; color:#ffffff;' ],
					type : 2,
					offset : '1%',// 距离页面顶距离
					area : [ '65%', '65%' ],
					fix : false, // 不固定
					maxmin : true,
					content : "../univ/univBranch!addUnivBranchPage.do?universityVo.id=" + id,
				});
				btTable.refresh("searchForm");
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
						title : [ univBranchUpdateWindowTitle,
								'background-color:#3C8DBC; color:#ffffff;' ],
						offset : '1%',// 距离页面顶距离
						type : 2,
						area : [ '65%', (h - 0.46 * h) + 'px' ],
						fix : false, // 不固定
						maxmin : true,
						content : "../univ/univBranch!editUnivBranchPage.do?universityVo.id=" + row.id,
					});
					//编辑数据后刷新表格
					btTable.refresh("searchForm");
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
									url : "../univ/univBranch!deleteUnivBranch.do",
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
			
			// 返回
			$("#btn_go_back").click(function() {
					window.location.href="../univ/university!toUniversityManagePage.do";
			});
})
//刷新表格
function refreshTable(){
	btTable.refresh("searchForm");
}