var btTable;
$(function() {
	//初始化下拉框  合作状态 合作方式 校招状态
	selectInitial("coopStatus", "../system/option!getOptionsByGPVal.do?value=COOP_STATE","",false);
	selectInitial("coopStyle", "../system/option!getOptionsByGPVal.do?value=COOP_WAY","",false);
	//初始化时间控件
	dateInitial([ {"id":"coopTime","type":"2"}]);
	// 初始化BootStrapTable
	btTable = BtTable('table', "../univ/coop!selectCoopInfo.do", 'toolbar',  [ {
				field : 'status',
				checkbox : true,
			}, {
				title : info17,
				field : 'company_name',
				align : 'center',
				valign : 'middle',
				sortable : true,
				cardVisible:false
			}, {
				title : info9,
				field : 'univ_name',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : info10,
				field : 'univ_branch_name',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : info11,
				field : 'college_name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : info18,
				field : 'state',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : info19,
				field : 'style',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : info20,
				field : 'coop_time',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : info21,
				field : 'recruit_time',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true,
			} , {
				title : info22,
				field : 'recruit_email',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			},  {
				title : info23,
				field : 'remark',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			},{
				title : info24,
				field : 'country',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : info25,
				field : 'provinceName',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : info26,
				field : 'cityName',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : info27,
				field : 'create_time',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			}, {
				title : info28,
				field : 'create_user',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : info29,
				field : 'update_time',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			}, {
				title : info30,
				field : 'update_user',
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			}  ],
	'id', 'desc',null);
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
			title : [ add_new_coop_title,
					'background-color:#3C8DBC; color:#ffffff;' ],
			type : 2,
			offset : '0%',// 距离页面顶距离
			area : [ '100%', '100%' ],
			fix : false, // 不固定
			maxmin : true,
			content : '../univ/coop!toAddCoopPage.do'
		});
		layer.full(index);
		btTable.refresh("searchForm");
	});
	// 工具栏-编辑按钮
	$("#btn_edit").click(function() {
		var rows = btTable.getSelected();
		if (rows.length != 1) {
			layer.msg(info31);
		} else {
			var row = rows[0];
			//弹出框进行更新
			var h = document.documentElement.clientHeight;
			var index = layer.open({
				title : [ coopUpdateWindowTitle,
						'background-color:#3C8DBC; color:#ffffff;' ],
				offset : '0%',// 距离页面顶距离
				type : 2,
				area : [ '100%', '100%' ],
				fix : false, // 不固定
				maxmin : true,
				content : "../univ/coop!toEditCoopPage.do?coopVo.id=" + row.id,
			});
			layer.full(index);
		}
	});
	//双击事件
	$("#table").bootstrapTable().on('dbl-click-row.bs.table',function(e,row,$element){
		var index = layer.open({
			title: [
					viewCoopTitle1+row.company_name+viewCoopTitle2,
				 	'background-color:#3C8DBC; color:#ffffff;'
			],
			offset: '0%',// 距离页面顶距离
		    type: 2,
		    area: [ '100%', '100%'],
		    fix: false, //不固定
		    maxmin: true,
		    content: "../univ/coop!viewCoopPage.do?coopVo.id=" + row.id,
		});
		layer.full(index);
	});;
	// 工具栏-删除按钮
	$("#btn_delete").click(function() {
		var rows = btTable.getSelected();
		if (rows.length == 0) {
			layer.msg(info32);
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
				content : coop_delete_content,
				btn : [ confirm, cancel ],
				shadeClose : false,
				yes : function() {
						$.ajax({
							url : "../univ/coop!deleteCoop.do",
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
									layer.msg(info33);
									console.info(data.message);
								}
							}
						});
				}	
			});
		}
	});
	//初始化总校 分校 学院 信息下拉框
	selectInitial("university", '../univ/university!selectMainUniversity.do?universityVo.parent_id=0', "", false);
	selectInitial("companyName", '../univ/coop!selectAllCompany.do', "", false);
	selectInitial("regionName", '../region/chinaregion!findRegionOption.do?chinaRegionVo.parentId=0', "", false);
	// 获取二级联下拉框  省市信息
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

//绑定关闭方法
function closeFun(){
	$("a.layui-layer-close").click(function() {
		refreshTable();
	});
}