/**
 * 用户表格
 */
var g = null;
$(document).ready(function() {
	g = $("#menugrid").ligerGrid({
		height : '100%',
		columns : [ {
			display : '菜单编号',
			name : 'mnu_id',
			align : 'left',
		}, {
			display : '菜单名称',
			name : 'mnu_name',
		}, {
			display : '菜单地址',
			name : 'mnu_url',
		}, {
			display : '创建人',
			name : 'mnu_create_user'
		}, {
			display : '创建时间',
			name : 'mnu_create_time',
		}, {
			display : '更新人',
			name : 'mnu_update_user',
		}, {
			display : '更新时间',
			name : 'mnu_update_time',
		}, {
			display : '父菜单编号',
			name : 'mnu_parent_id',
		}, {
			display : '根菜单编号',
			name : 'mnu_root_id',
		}, {
			display : '图标地址',
			name : 'mnu_logo_url',
		}, {
			display : '菜单描述',
			name : 'mnu_desc',
		} ],
		pageSizeOptions : [ 15, 30, 45, 60, 75 ],
		pageSize : 15,
		rownumbers : true,
		sortName : 'mnu_create_time',
		sortOrder : 'desc',
		width : '100%',
		height : '100%',
		url : 'menuInfo_getMenuInfoLst',
		toolbar : {
			items : [ {
				text : '新增',
				click : addMenuInfo,
				icon : 'add'
			}, {
				line : true
			}, {
				text : '修改',
				click : updateMenuInfo,
				icon : 'modify'
			}, {
				line : true
			}, {
				text : '删除',
				click : deleteMenuInfo,
				img : '../lib/ligerUI/skins/icons/delete.gif'
			}

			]
		}
	});
	$("#pageloading").hide();
});
$(document).ready(function() {
	$("#pageWhere2").tinyselect({
		dataUrl : "../lib/tm/select/contactNum.json",
		dataParser : dataParserA
	});
	$("#pageWhere3").tinyselect({
		dataUrl : "../lib/tm/select/email.json",
		dataParser : dataParserA
	});
	$("#pageWhere4").tinyselect({
		dataUrl : "../lib/tm/select/principal.json",
		dataParser : dataParserA
	});
});
function dataParserA(data, selected) {
	retval = [ {
		val : "-1",
		text : "---"
	} ];

	data.forEach(function(v) {
		/*
		 * if (selected == "-1" && v.val == 3) v.selected = true;
		 */
		retval.push(v);
	});

	return retval;
}
function addMenuInfo() {
	$.ligerDialog.open({
		height : 500,
		width : 700,
		title : '新增',
		url : 'addMenu.jsp',
		showMax : false,
		showToggle : true,
		showMin : false,
		isResize : true,
		slide : false,
		onClosed : function() {
			$.ligerDialog.success('新增用户成功');
			g.loadData();
		}
	});
}

function updateMenuInfo() {
	var row = g.getSelectedRow();
	console.log(row);
	if (row != null) {
		$.cookie('menuid', row.mnu_id);
		$.ligerDialog.open({
			height : 500,
			width : 700,
			title : '修改',
			url : 'updateMenu.jsp',
			showMax : false,
			showToggle : true,
			showMin : false,
			isResize : true,
			slide : false,
			onClosed : function() {
				$.ligerDialog.success('修改用户成功');
				g.loadData();
			}
		});
	} else {
		$.ligerDialog.warn('请选择你要修改的行');
	}
}
function deleteMenuInfo() {
	var row = g.getSelectedRow();
	if (confirm("确定要删除吗？")) {
		if (row != null) {
			$.ajax({
				url : 'menuInfo_delete',
				async : false,
				type : 'post',
				data : {
					mnu_id : row.mnu_id
				},
				dataType : 'json',
				success : function(data) {
					if (data.success == 'true') {
						g.deleteSelectedRow();
						$.ligerDialog.success('删除成功!');
						g.loadData();
					} else {
						$.ligerDialog.success('删除失败!');
					}
				}
			});
		} else {
			$.ligerDialog.warn('请选择你要删除的行');
		}
	}
}