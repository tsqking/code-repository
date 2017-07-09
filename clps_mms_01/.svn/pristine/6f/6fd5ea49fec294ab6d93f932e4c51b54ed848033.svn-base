/**
 * 角色表格
 */
var g = null;
$(document).ready(function() {
	g = $("#rolegrid").ligerGrid({
		height : '100%',
		columns : [ {
			display : '角色名称',
			name : 'name',
			align : 'left',
		}, {
			display : '角色描述',
			name : 'description',
		}, {
			display : '创建人名',
			name : 'create_name',
		},{
			display : '创建时间',
			name : 'create_time',
		} ],
		parms : function search() {
			var queryCondition1 = $("#condition1").val();
			var queryCondition2 = $("#condition2").val();
			var queryCondition3 = $("#condition3").val();
			var queryCondition4 = $("#condition4").val()+" 23:59:59";
			if(queryCondition1=="--请选择角色名称--"){
				queryCondition1="";
			}
			if(queryCondition2=="--请选择创建人名--"){
				queryCondition2="";		
			}
			if(queryCondition4==" 23:59:59"){
				queryCondition4="";
			}
			var data = {
				pageWhere1 : queryCondition1,
				pageWhere2 : queryCondition2,
				pageWhere3 : queryCondition3,
				pageWhere4 : queryCondition4,
			};
			console.log(data);
			return data;
		},
		dataType : "json",
		usePager : true,
		pageSizeOptions : [ 15, 30, 45, 60, 75 ],
		pageSize : 15,
		rownumbers : true,
		sortname : 'create_time',
		sortorder : 'desc',
		width : '100%',
		height : '100%',
		url : 'roleInfo_getRoleInfoLst',
		toolbar : {
			items : [ {
				text : '新增角色',
				click : addRoleInfo,
				icon : 'add'
			}, {
				line : true
			}, {
				text : '修改角色',
				click : updateRoleInfo,
				icon : 'modify'
			}, {
				line : true
			}, {
				text : '删除角色',
				click : deleteRoleInfo,
				img : '../lib/ligerUI/skins/icons/delete.gif'
			} ]
		}
	});
	$("#pageloading").hide();
});

function search() {
	var queryCondition1 = $("#condition1").val();
	var queryCondition2 = $("#condition2").val();
	var queryCondition3 = $("#condition3").val();
	var queryCondition4 = $("#condition4").val()+" 23:59:59";
	if(queryCondition1=="--请选择角色名称--"){
		queryCondition1="";
	}
	if(queryCondition2=="--请选择创建人名--"){
		queryCondition2="";
	}
	if(queryCondition4==" 23:59:59"){
		queryCondition4="";
	}
	var data = {
		pageWhere1 : queryCondition1,
		pageWhere2 : queryCondition2,
		pageWhere3 : queryCondition3,
		pageWhere4 : queryCondition4,
		page : 1,
		pagesize : 15,
		sortname : 'create_time',
		sortorder : 'desc'
	};
	console.log(data);
	g.loadServerData(data, null);
}

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
function addRoleInfo() {
	$.ligerDialog.open({
		height : 500,
		width : 700,
		title : '新增角色',
		url : 'addRole.jsp',
		showMax : false,
		showToggle : true,
		showMin : false,
		isResize : true,
		slide : false,
		onClosed : function() {
			$.ligerDialog.success('新增角色成功');
			g.loadData();
		}
	});
}

function updateRoleInfo() {
	var row = g.getSelectedRow();
	console.log(row);
	if (row != null) {
		$.cookie('username', row.name);
		$.ligerDialog.open({
			height : 500,
			width : 700,
			title : '修改角色',
			url : 'updateRole.jsp',
			showMax : false,
			showToggle : true,
			showMin : false,
			isResize : true,
			slide : false,
			onClosed : function() {
				$.ligerDialog.success('修改角色成功');
				g.loadData();
			}
		});
	} else {
		$.ligerDialog.warn('请选择你要修改的行');
	}
}
function deleteRoleInfo() {
	var row = g.getSelectedRow();
	if (row != null) {
		$.ligerDialog.confirm("确定是否删除？", function(confirm) {
			if (confirm) {
				$.ajax({
					url : 'roleInfo_delete',
					async : false,
					type : 'post',
					data : {
						name : row.name
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
			}
		});
	} else {
		$.ligerDialog.warn('请选择你要删除的行');
	}
}