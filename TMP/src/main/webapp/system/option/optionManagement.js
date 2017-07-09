//搜索数据 
var searchData = [ {
	"name" : "more_data",
	"value" : "my_value"
} ];
//初始化
$(function() {
	initial();
	//表格控件初始化
	dataTable("optionTable","../system/option!getOptionPage.do",
			[ [ 4, "desc" ] ], 
			[ {
				"mData" : "name",
				"sClass" : "center"
			}, {
				"mData" : "type",
				"sClass" : "center"
			}, {
				"mData" : "value",
				"sClass" : "center"
			}, {
				"mData" : "parent_id",
				"sClass" : "center"
			}, {
				"mData" : "order",
				"sClass" : "center"
			}, {
				"mData" : "enable",
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
				"aTargets" : [ 8 ],
				//"mData" : "操作",
				"mRender" : function(data, type, full) {
					if(full.type=="选项组" || full.type=="Option Group"){//选项组
						return '<a href="javascript:void(0);" onclick="del('+data+');" data-toggle="tooltip" title="Delete"><i class="fa fa-trash"></i></a>&nbsp;&nbsp;&nbsp;'
								+'<a href="javascript:void(0);" onclick="edit('+data+');" data-toggle="tooltip" title="Edit"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;&nbsp;'
								+'<a href="javascript:void(0);" onclick="add('+data+',\''+full.name+'\');" data-toggle="tooltip" title="Add child option"><i class="fa fa-plus-circle"></i></a>';
					}else{
						return '<a href="javascript:void(0);" onclick="del('+data+');" data-toggle="tooltip" title="Delete"><i class="fa fa-trash"></i></a>&nbsp;&nbsp;&nbsp;'
								+'<a href="javascript:void(0);" onclick="edit('+data+');" data-toggle="tooltip" title="Edit"><i class="fa fa-edit"></i></a>';
					}
				}
			} ]
	);
});

//搜索按钮
$(document)
		.on(
				"click",
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
					$("#optionTable").DataTable().draw();
				});

//重置按钮
$(document).on("click", "#resetButton", function() {
	$("#searchOptionName").val("");
	$("#searchOptionGroups").val(null).trigger("change");
	$("#searchOptionStatus").val(null).trigger("change");
	$("#searchOptionType").val(null).trigger("change");
	searchData=[];
});

function del(id){
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
				url:"../system/option!deleteOption.do",
				data: {"id":id},//$("#form").serialize()
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.success=='true'){
						layer.msg(data.message);
						$("#optionTable").DataTable().draw();
						selectInitial("searchOptionGroups","../system/option!getOptionGroups.do","",false);
					}else{
						layer.msg(data.message);
					}
				}
			});
	    }
	});
}

function edit(id){
	var h = document.documentElement.clientHeight;
	layer.open({
		title: [
				manage_editTitle,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    area: [ '60%' , '20%'],
	    offset: '10%',// 距离页面顶距离
	    fix: false, //不固定
	    maxmin: true,
	    content: '../system/option!toOptionManagementEditPage.do?id='+id
	});
}

function add(id,parent_name){
	var h = document.documentElement.clientHeight;
	layer.open({
		title: [
				id=='0'?manage_OptionGroupAddTitle:manage_OptionAddTitle,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    area: [ '60%' , (h-0.46*h)+'px'],
	    offset: '10%',// 距离页面顶距离
	    fix: false, //不固定
	    maxmin: true,
	    content: '../system/option!toOptionManagementAddPage.do?id='+id+'&parent_name='+parent_name
	});
}

//控件初始化
function initial() {
	selectInitial("searchOptionGroups","../system/option!getOptionGroups.do","",false);
	selectInitial("searchOptionStatus","../system/option!getOptionsByGPVal.do?value=STATUS","",false);
	selectInitial("searchOptionType","../system/option!getOptionsByGPVal.do?value=OPTION_TYPE","",false);
}