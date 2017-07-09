/**
	 * 用户表格
	 */
	var g = null;
	$(document).ready(function() {
		g = $("#meetingroomgrid").ligerGrid({
			height : '100%',
			columns : [ {
				display : '会议室名称',
				name : 'roomName',
				align : 'left',
			}, 
			{
				display : '容纳人数',
				name : 'roomNumber',
			}, {
				display : '会议室地址',
				name : 'roomAddress',
			}, {
				display : '联系电话',
				name : 'roomPhone'
			},{
				display : '状态',
				name : 'roomState',
			},{
				display : '会议主题',
				name : 'roomTopic',
			},{
				display : '创建时间',
				name : 'createDate',
			},{
				display : '创建人',
				name : 'createName',
			},{
				display : '备注',
				name : 'roomRemark',
			}],
			parms:function search(){
				var queryCondition=$("#condition").val().trim();
				var queryCondition2=$("#condition2").val().trim();
				var queryCondition3=$("#condition3").val().trim();
				var queryCondition4=$("#condition4").val().trim();
				var queryCondition5=$("#condition5").val().trim();
				var queryCondition6=$("#condition6").val().trim()+" 23:59:59";
				var data={pageWhere1:queryCondition,pageWhere2:queryCondition2,
						pageWhere3:queryCondition3,pageWhere4:queryCondition4,
						pageWhere5:queryCondition5,pageWhere6:queryCondition6};
				return data;
			},
			dataType : "json",
			usePager : true,
			pageSizeOptions : [ 15, 30, 45, 60, 75 ],
			pageSize : 15,
			rownumbers : true,
			sortName: 'roo_createtime',
			sortOrder: 'desc',
			width : '100%',
			height : '100%',
			url : 'meetingroom_getMeetingroomLst',
			toolbar : {
				items : [ {
					text : '新增会议室',
					click : addMeetingroom,
					icon : 'add'
				}, {
					line : true
				}, {
					text : '修改会议室',
					click : updateMeetingroom,
					icon : 'modify'
				}, {
					line : true
				}, {
					text : '删除会议室',
					click:deleteMeetingroom,
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
		var queryCondition5=$("#condition5").val().trim();
		var queryCondition6=$("#condition6").val().trim()+" 23:59:59";
		var data={pageWhere1:queryCondition,pageWhere2:queryCondition2,
				pageWhere3:queryCondition3,pageWhere4:queryCondition4,
				pageWhere5:queryCondition5,pageWhere6:queryCondition6,page:1,pagesize:15};
		
		console.log(data);
    	g.loadServerData(data,null);
	}
	function addMeetingroom() {
		$.ligerDialog.open({
			height : 500,
			width : 700,
			title : '新增会议室',
			url : 'addMeetingroom.jsp',
			showMax : false,
			showToggle : true,
			showMin : false,
			isResize : true,
			slide : false,
			onClosed : function() {
				$.ligerDialog.success('新增会议室成功');
				g.loadData();
			}
		});
	}

	function updateMeetingroom() {
		var row = g.getSelectedRow();
		console.log(row);
		if (row != null) {
			$.cookie('roomName', row.roomName);
			$.ligerDialog.open({
				height : 500,
				width : 700,
				title : '修改会议室',
				url : 'updateMeetingroom.jsp',
				showMax : false,
				showToggle : true,
				showMin : false,
				isResize : true,
				slide : false,
				onClosed : function() {
					$.ligerDialog.success('修改会议室成功');
					g.loadData();
				}
			});
		} else {
			 $.ligerDialog.warn('请选择你要修改的行');
		}
	}
	function deleteMeetingroom(){
		var row = g.getSelectedRow();
		if(confirm("你确定要删除吗？")){
		if (row != null) {
			$.ajax({
				url : 'meetingroom_delete',
				async : false,
				type : 'post',
				data : {
					roomName : row.roomName
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