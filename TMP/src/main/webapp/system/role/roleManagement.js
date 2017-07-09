//搜索数据 
var searchData = [ {
	"name" : "more_data",
	"value" : "my_value"
} ];
//初始化
$(function() {
	initial();
	//表格控件初始化
	dataTable("roleTable","../system/role!getRolePage.do",
			[ [ 5, "desc" ] ], 
			[ {
				"mData" : "name",
				"sClass" : "center"
			}, {
				"mData" : "description",
				"sClass" : "center"
			}, {
				"mData" : "enable",
				"sClass" : "center"
			}, {
				"mData" : "create_time",
				"sClass" : "center"
			}, {
				"mData" : "create_person",
				"sClass" : "center"
			}, {
				"mData" : "update_time",
				"sClass" : "center"
			}, {
				"mData" : "update_person",
				"sClass" : "center"
			}, {
				"mData" : "id",
				"sClass" : "center"
			}, ],
			[ {
				"aTargets" : [ 7 ],
				//"mData" : "操作",
				"mRender" : function(data, type, full) {
					return '<a href="javascript:void(0);" onclick="delRole('+data+');" data-toggle=\"tooltip\" title=\"Delete\" ><i class="fa fa-trash"></i></a>&nbsp;&nbsp;&nbsp'
							+'<a href="javascript:void(0);" onclick="editRole('+data+');" data-toggle=\"tooltip\" title=\"Edit\"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;&nbsp;'
							+'<a href="javascript:void(0);" onclick="updateMenu('+data+',\''+full.name+'\');" data-toggle=\"tooltip\" title=\"Link Menu\"><i class="fa  fa-calendar-o"></i></a>&nbsp;&nbsp;&nbsp;'
							+'<a href="javascript:void(0);" onclick="updatePerson('+data+',\''+full.name+'\');" data-toggle=\"tooltip\" title=\"Link Person\"><i class="fa fa-child"></i></a>';
				}
			} ]
	);
});

//搜索按钮
$(document).on("click",
				"#searchButton",
				function() {
					var frmID = document.getElementById("searchForm");
					var i, queryString = "", and = "";
					// for each form's object
					var item;
					// store each form object's value
					var itemValue;
					for (i = 0; i < frmID.length; i++) {
						// get form's each object
						item = frmID[i];
						if (item.name != '') {
							if (item.type == 'select-one') {
								itemValue = item.options[item.selectedIndex].value;
							} else if (item.type == 'checkbox'
									|| item.type == 'radio') {
								if (item.checked == false) {
									continue;
								}
								itemValue = item.value;
							} else if (item.type == 'button'
									|| item.type == 'submit'
									|| item.type == 'reset'
									|| item.type == 'image') {// ignore this type
								continue;
							} else {
								itemValue = item.value;
							}
							//存
							var tem = {
								"name" : item.name,
								"value" : itemValue
							};
							searchData[i] = tem;
						}
					}
					$("#roleTable").DataTable().draw();
				});

//重置按钮
$(document).on("click", "#resetButton", function() {
	$("#searchRoleName").val("");
	$("#searchRoleStatus").val(null).trigger("change");
	searchData=[];
});

function delRole(id){
	layer.open({
		title: [
			manage_delTitle,
		 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    content: manage_delContent,
	    btn: [confirm, cancel],
	    shadeClose: false,
	    yes: function(){
	    	$.ajax({
				url:"../system/role!deleteRole.do",
				data: {"role.id":id},//$("#form").serialize()
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.success=='true'){
						layer.msg(data.message);
						$("#roleTable").DataTable().draw();
					}else{
						layer.msg(data.message);
					}
				}
			});
	    }
	});
}

function editRole(id){
	var h = document.documentElement.clientHeight;
	layer.open({
		title: [
				manage_editTitle,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    area: [ '60%' , (h-0.46*h)+'px'],
	    offset: '10%',// 距离页面顶距离
	    fix: false, //不固定
	    maxmin: true,
	    content: '../system/role!toRoleManagementEditRolePage.do?role.id='+id
	});
}

function addRole(){
	var h = document.documentElement.clientHeight;
	layer.open({
		title: [
				manage_roleAddTitle,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    area: [ '60%' , '20%'],
	    offset: '10%',// 距离页面顶距离
	    fix: false, //不固定
	    maxmin: true,
	    content: '../system/role!toRoleManagementAddRolePage.do'
	});
}

function updateMenu(id,roleName){
	var h = document.documentElement.clientHeight;
	layer.open({
		title: [
				manage_roleMenuUpdateTitle,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    area: [ '100%' , '100%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../system/role!toRoleManagementUpdateMenuPage.do?role.id='+id+'&role.name='+roleName
	});
}

function updatePerson(id,roleName){
	var h = document.documentElement.clientHeight;
	layer.open({
		title: [
				manage_rolePersonUpdateTitle,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    area: [ '100%','100%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../system/role!toRoleManagementUpdatePersonPage.do?role.id='+id+'&role.name='+roleName
	});
}

//控件初始化
function initial() {
	//Initialize Select2 Elements
	selectInitial("searchRoleStatus","../system/option!getOptionsByGPVal.do?value=STATUS","",false);
}
