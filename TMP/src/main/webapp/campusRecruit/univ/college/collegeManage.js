var btTable;
$(function() {
	 // 初始化BootStrapTable
	 var univ_id=document.getElementById("getid").value;
	 btTable = BtTable('table', "../univ/college!selectCollege.do", 'toolbar',  [ {
				field : 'status',
				checkbox : true,
			},  {
				title : '学校名称',
				field : 'univ_name',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '学院名称',
				field : 'name',
				align : 'center',
				valign : 'middle',
				sortable : true
			},  {
				title : '创建时间',
				field : 'create_time',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '创建人员',
				field : 'create_user',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			},{
				title : '更新时间',
				field : 'update_time',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : true
			}, {
				title : '更新人员',
				field : 'update_user',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : true
			}  ],
			'id', 'asc',{"univ_id":univ_id});
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
					title : [ add_new_college_title,
							'background-color:#3C8DBC; color:#ffffff;' ],
					type : 2,
					offset : '1%',// 距离页面顶距离
					area : [ '65%', '5%' ],
					fix : false, // 不固定
					maxmin : true,
					content : "../univ/college!addcollegePage.do?universityVo.id=" + id,
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
						title : [ collegeUpdateWindowTitle,
								'background-color:#3C8DBC; color:#ffffff;' ],
						offset : '1%',// 距离页面顶距离
						type : 2,
						area : [ '65%', (h - 0.85 * h) + 'px' ],
						fix : false, // 不固定
						maxmin : true,
						content : "../univ/college!editUniversityPage.do?collegeVo.id=" + row.id,
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
						content : college_delete_content,
						btn : [ confirm, cancel ],
						shadeClose : false,
						yes : function() {
								$.ajax({
									url : "../univ/college!deleteCollege.do",
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
			var id=document.getElementById("getid").value;
			// 根据id==0 查找出第一级联动数据
			selectInitial("collegeName", '../univ/college!selectCollegeName.do?collegeVo.univ_id='+id, "", false);
})
//表格刷新
function refreshTable(){
	btTable.refresh("searchForm");
}
