/**
	 * 用户表格
	 */
	var g = null;
	$(document).ready(function() {
		g = $("#usergrid").ligerGrid({
			height : '100%',
			columns : [ {
				display : '用户名称',
				name : 'name',
				align : 'left',
			}, 
			{
				display : '昵称',
				name : 'nickname',
			}, {
				display : '联系电话',
				name : 'mobNum',
			}, {
				display : '电子邮箱',
				name : 'email'
			},{
				display : '注册时间',
				name : 'createdate',
			} ],
			pageSizeOptions : [ 15, 30, 45, 60, 75 ],
			pageSize : 15,
			rownumbers : true,
			sortName: 'createdate',
			sortOrder: 'desc',
			width : '100%',
			height : '100%',
			url : 'userInfo_getUserInfoLst',
			toolbar : {
				items : [ {
					text : '新增用户',
					click : addUserInfo,
					icon : 'add'
				}, {
					line : true
				}, {
					text : '修改用户',
					click : updateUserInfo,
					icon : 'modify'
				}, {
					line : true
				}, {
					text : '删除用户',
					click:deleteUserInfo,
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
			/* if (selected == "-1" && v.val == 3)
				v.selected = true; */
			retval.push(v);
		});

		return retval;
	}
	function addUserInfo() {
		$.ligerDialog.open({
			height : 500,
			width : 700,
			title : '新增用户',
			url : 'addUser.jsp',
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

	function updateUserInfo() {
		var row = g.getSelectedRow();
		console.log(row);
		if (row != null) {
			$.cookie('username', row.name);
			$.ligerDialog.open({
				height : 500,
				width : 700,
				title : '修改用户',
				url : 'updateUser.jsp',
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
	function deleteUserInfo(){
		var row = g.getSelectedRow();
		if (row != null) {
			$.ajax({
				url : 'userInfo_delete',
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
		}else{
			$.ligerDialog.warn('请选择你要删除的行');	
		}
	}