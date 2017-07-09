$(function() {
	// 初始化BootStrapTable
	var btTable = BtTable('tableId', "../getBtTable.html", 'toolbarId', 
			[ {
				field : 'status',
				checkbox : true
			}, {
				title : '序号',
				field : 'id',
				align : 'center',
				valign : 'middle',
				sortable : true,
				cardVisible:false
			}, {
				title : '工号',
				field : 'no',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '姓名',
				field : 'name',
				align : 'center',
				valign : 'middle'
			}, {
				title : '性别',
				field : 'sex',
				align : 'center',
				valign : 'middle'
			}, {
				title : '年龄',
				field : 'age',
				align : 'center',
				valign : 'middle',
				visible : false
			}, {
				title : '状态',
				field : 'enable',
				align : 'center',
				valign : 'middle',
				visible : false
			}, {
				title : '籍贯',
				field : 'nativePlace',
				align : 'center',
				valign : 'middle'
			} ],
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
		//TODO 弹出框新增
		//Demo 提示
		layer.msg("coding");
	});
	// 工具栏-编辑按钮
	$("#btn_edit").click(function() {
		var rows = btTable.getSelected();
		if (rows.length != 1) {
			layer.msg("请选择一行数据更新");
		} else {
			var row = rows[0];
			//Demo 提示
			layer.msg("编辑数据的ID："+row.id);
			//TODO 弹出框编辑
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
			var id="";
			for (var i = 0; i < rows.length; i++) {
				id += (" "+rows[i].id);
				//TODO Ajax调用删除数据
			}
			//Demo 提示
			layer.msg("删除数据的ID："+id);
			//编辑数据后刷新表格
			btTable.refresh("searchForm");
		}
	});
});
/**
 * 父子表格-子查询样例
 */
/*
InitSubTable = function(index, row, $detail) {
	// var parentid = row.MENU_ID;
	var cur_table = $detail.html('<table></table>').find('table');
	$(cur_table).bootstrapTable({
		clickToSelect : true,
		striped : true,
		detailView : true,// 父子表
		uniqueId : "MENU_ID",
		pageSize : 10,
		pageList : [ 10, 25 ],
		columns : [ {
			field : 'MENU_NAME',
			title : '菜单名称',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'MENU_URL',
			title : '菜单URL',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'PARENT_ID',
			title : '父级菜单',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'MENU_LEVEL',
			title : '菜单级别',
			align : 'center',
			valign : 'middle',
		}, ]
	});
};
*/