/**
* 部门表格
*/
	var g = null;
	$(document).ready(function() {
		g = $("#departmentgrid").ligerGrid({
			height : '100%',
			columns : [ {
				display : '部门编号',
				name : 'id',
				align : 'left',
			}, 
			{
				display : '部门名称',
				name : 'name',
			},{
				display : '部门经理',  
				name : 'manager',
			}, {
				display : '部门电话',
				name : 'tel'
			},
			{
				display : '创建时间',
				name : 'createdate'
			},{
				display : '部门邮箱',    
				name : 'email'
			},{
				display : '部门描述',  
				name : 'descp',
			}],
			parms:function search(){
				var queryCondition1=$("#condition1").val().trim();
				var queryCondition2=$("#condition2").val().trim();
				var data={pageWhere1:queryCondition1,pageWhere2:queryCondition2};
				return data;
			},
			dataType : "json",
			usePager : true,
			pageSizeOptions : [ 15, 30, 45, 60, 75 ],
			pageSize : 15,
			rownumbers : true,
			/*sortName: 'createdate',
			sortOrder: 'desc',*/
			width : '100%',
			height : '100%',
			url : 'departmentInfo_getDepartmentInfoLst',
			toolbar : {
				items : [ {
					text : '新增部门',
					click : addDepartmentInfo,
					icon : 'add'
				}, {
					line : true
				}, {
					text : '修改部门',
					click : updateDepartmentInfo,
					icon : 'modify'
				}, {
					line : true
				}, {
					text : '删除部门',
					click:deleteDepartmentInfo,
					img : '../lib/ligerUI/skins/icons/delete.gif'
				}

				]
			}
		});
		$("#pageloading").hide();
	});
	
	function search(){
		var queryCondition1=$("#condition1").val().trim();
		var queryCondition2=$("#condition2").val().trim();
		var data={pageWhere1:queryCondition1,pageWhere2:queryCondition2,page:1,pagesize:15};
		console.log(data);
    	g.loadServerData(data,null);
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
			/* if (selected == "-1" && v.val == 3)
				v.selected = true; */
			retval.push(v);
		});

		return retval;
	}
	function addDepartmentInfo() {
		$.ligerDialog.open({
			height : 500,
			width : 700,
			title : '新增部门',
			url : 'addDepartment.jsp',
			showMax : false,
			showToggle : true,
			showMin : false,
			isResize : true,
			slide : false,
			onClosed : function() {
				$.ligerDialog.success('新增部门成功');
				g.loadData();
			}
		});
	}

	function updateDepartmentInfo() {
		var row = g.getSelectedRow();
		console.log(row);
		if (row != null) {
			$.cookie('id', row.id);
			$.ligerDialog.open({
				height : 500,
				width : 700,
				title : '修改部门',
				url : 'updateDepartment.jsp',
				showMax : false,
				showToggle : true,
				showMin : false,
				isResize : true,
				slide : false,
				onClosed : function() {
					$.ligerDialog.success('修改部门成功');
					g.loadData();
				}
			});
		} else {
			 $.ligerDialog.warn('请选择你要修改的行');
		}
	}
	function deleteDepartmentInfo(){
		var row = g.getSelectedRow();
		if (row != null) {
			$.ajax({
				url : 'departmentInfo_delete',
				async : false,
				type : 'post',
				data : {
					id : row.id
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