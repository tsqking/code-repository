/**
 * 设备记录表格
 */
var g = null;
$(document).ready(function() {
	g = $("#logid").ligerGrid({
		height : '100%',
		columns : [ {
			display : '部门名称',
			name : 'name',
			align : 'left',
		}, {
			display : '修改类型',
			name : 'type',
		}, {
			display : '日志内容',
			name : 'content',
		}, {
			display : '修改人员',
			name : 'updateName',
		}, {
			display : '修改时间',
			name : 'createTime',
		} ],
		parms : function search() {
			var queryCondition1=$("#condition1").val().trim();
			var queryCondition2=$("#condition2").val().trim();
			var queryCondition3=$("#condition3").val().trim();	
			var queryCondition4=$("#condition4").val().trim();
			var data={condition1:queryCondition1,condition2:queryCondition2,condition3:queryCondition3,condition4:queryCondition4};
			return data;
		},
		
		dataType : "json",
		usePager : true,
		pageSizeOptions : [ 15, 30, 45, 60, 75 ],
		pageSize : 15,
		rownumbers : true,
		sortName : 'updateName',
		sortOrder : 'asc',
		width : '100%',
		height : '100%',
		url : 'LogEquipmentModel_getDepartInfoLst',
	});
	$("#pageloading").hide();
});
function search() {
	var queryCondition1 = $("#condition1").val().trim();
	var queryCondition2 = $("#condition2").val().trim();
	var queryCondition3 = $("#condition3").val().trim();
	var queryCondition4 = $("#condition4").val().trim();
	var data = {
		pageWhere1 : queryCondition1,
		pageWhere2 : queryCondition2,
		pageWhere3 : queryCondition3,
		pageWhere4 : queryCondition4,
		page : 1,
		pagesize : 15
	};
	console.log(data);
	g.loadServerData(data, null);
}