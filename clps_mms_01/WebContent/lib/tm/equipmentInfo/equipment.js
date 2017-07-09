/**
	 * 用户表格
	 */
	var g = null;
	$(document).ready(function() {
		g = $("#equipmentgrid").ligerGrid({
			height : '100%',
			columns : [ {
				display : '设备名称',
				name : 'equipmentName',
				align : 'left',
			}, 
			{
				display : '设备价格',
				name : 'equipmentPrice',
			}, {
				display : '设备状态',
				name : 'equipmentState',
			}, {
				display : '设备使用者',
				name : 'equipmentUse'
			},{
				display : '设备类型',
				name : 'equipmentType',
			},{
				display : '创建时间',
				name : 'createDate',
			},{
				display : '创建人',
				name : 'createName',
			},{
				display : '备注',
				name : 'equipmentRemark',
			}],
			parms:function search(){
				var queryCondition=$("#condition").val().trim();
				var queryCondition2=$("#condition2").val().trim();
				var queryCondition3=$("#condition3").val().trim();
				var queryCondition4=$("#condition4").val().trim();
				var queryCondition5=$("#condition5").val().trim();
				var data={condition:queryCondition,condition2:queryCondition2,condition3:queryCondition3,
						condition4:queryCondition4,condition5:queryCondition5};
				return data;
			},
			dataType : "json",
			usePager : true,
			pageSizeOptions : [ 15, 30, 45, 60, 75 ],
			pageSize : 15,
			rownumbers : true,
			sortName: 'equ_createtime',
			sortOrder: 'desc',
			width : '100%',
			height : '100%',
			url : 'equipment_getEquipmentLst',
			toolbar : {
				items : [ {
					text : '新增设备',
					click : addEquipment,
					icon : 'add'
				}, {
					line : true
				}, {
					text : '修改设备',
					click : updateEquipment,
					icon : 'modify'
				}, {
					line : true
				}, {
					text : '删除设备',
					click:deleteEquipment,
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
	function search(){
		var queryCondition=$("#condition").val().trim();
		var queryCondition2=$("#condition2").val().trim();
		var queryCondition3=$("#condition3").val().trim();
		var queryCondition4=$("#condition4").val().trim();
		var queryCondition5=$("#condition5").val().trim()+" 23:59:59";
		var data={pageWhere1:queryCondition,pageWhere2:queryCondition2,
				pageWhere3:queryCondition3,pageWhere4:queryCondition4,
				pageWhere5:queryCondition5,page:1,pagesize:15};
		
		console.log(data);
    	g.loadServerData(data,null);
	}
	function addEquipment() {
		$.ligerDialog.open({
			height : 500,
			width : 700,
			title : '新增设备',
			url : 'addEquipment.jsp',
			showMax : false,
			showToggle : true,
			showMin : false,
			isResize : true,
			slide : false,
			onClosed : function() {
				$.ligerDialog.success('新增设备成功');
				g.loadData();
			}
		});
	}

	function updateEquipment() {
		var row = g.getSelectedRow();
		console.log(row);
		if (row != null) {
			$.cookie('equipmentName', row.equipmentName);
			$.ligerDialog.open({
				height : 500,
				width : 700,
				title : '修改设备',
				url : 'updateEquipment.jsp',
				showMax : false,
				showToggle : true,
				showMin : false,
				isResize : true,
				slide : false,
				onClosed : function() {
					$.ligerDialog.success('修改设备成功');
					g.loadData();
				}
			});
		} else {
			 $.ligerDialog.warn('请选择你要修改的行');
		}
	}
	function deleteEquipment(){
		var row = g.getSelectedRow();
		if(confirm("你确定要删除吗？")){
		if (row != null) {
			$.ajax({
				url : 'equipment_delete',
				async : false,
				type : 'post',
				data : {
					equipmentName : row.equipmentName
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
	}