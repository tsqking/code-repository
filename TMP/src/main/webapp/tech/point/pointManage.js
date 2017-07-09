//搜索数据 (必须)
var searchData = [ { "name" : "more_data" , "value" : "my_value" } ];

$(function() {
	//下拉框初始化
	selectInitial("first_skill", "../tech/skill!findSkillOption.do?skillVo.parent_id=0", "", false);
	selectInitial("enable", "../system/option!getOptionsByGPVal.do?value=STATUS","",false);
	//搜索按钮初始化
	dataTableSearchButton("searchButton","pointTable","searchForm",null);
	//清空按钮初始化
	dataTableClearButton("resetButton","searchForm");
	//表格控件初始化
	dataTable("pointTable","../tech/point!getPointPage.do",
			[ [ 6, "desc" ] ], 
			[ {
				"mData" : "name",
				"sClass" : "center"
			}, /*{
				"mData" : "description",
				"sClass" : "center"
			},*/ {
				"mData" : "order",
				"sClass" : "center"
			}, {
				"mData" : "first_skill",
				"sClass" : "center"
			}, {
				"mData" : "second_skill",
				"sClass" : "center"
			}, {
				"mData" : "third_skill",
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
			[ 
			   {
					"aTargets" : [ 8 ],
					"mRender" : function(data, type, full) {
						var detail = '<a href="javascript:void(0);" data-toggle="tooltip" title="Detail" onclick="getPointDetail(\''
							+ data +'\')"><i class="fa fa-navicon"></i></a>';
						var edit = '<a href="javascript:void(0);" data-toggle="tooltip" title="Edit" onclick="editPointDetail(\''
							+ data + '\')"><i class="fa fa-edit"></i></a>';
						var copy = '<a href="javascript:void(0);" data-toggle="tooltip" title="Copy" onclick="copyPointDetail(\''
							+ data + '\')"><i class="fa fa-copy"></i></a>';
						var del = '<a href="javascript:void(0);" data-toggle="tooltip" title="Delete" onclick="delPoint(\''
							+ data + '\',\''+full.name+'\')"><i class="fa fa-trash"></i></a>';
						return detail+"&nbsp;&nbsp;&nbsp;"+edit+"&nbsp;&nbsp;&nbsp;"+copy+"&nbsp;&nbsp;&nbsp;"+del;
					}
			   }
			]
	);
	//获取二级联下拉框
	$('#first_skill').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
			return;
		}
		selectInitial("second_skill","../tech/skill!findSkillOption.do?skillVo.parent_id="+selectedOption,"",false);
	});
	//获取三级下拉框
	$('#second_skill').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
			return;
		}
		selectInitial("third_skill","../tech/skill!findSkillOption.do?skillVo.parent_id="+selectedOption,"",false);
	});
});
/**
 * 添加新的知识点
 */
function addNewPoint(){
	var index=layer.open({
		title: [
				add_new_point_title,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    offset: '1%',// 距离页面顶距离
	    area: [ '85%','75%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/point!toAddNewPointPage.do'
	});
}
/**
 * 获取知识点详细
 */
function getPointDetail(pointId){
	var index=layer.open({
		title: [
				view_point_title,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    offset: '1%',// 距离页面顶距离
	    area: [ '85%','75%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/point!toViewPointPage.do?pointVo.id='+pointId
	});
}
/**
 * 修改知识点详细
 */
function editPointDetail(pointId){
	var index=layer.open({
		title: [
				edit_point_title,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    offset: '1%',// 距离页面顶距离
	    area: [ '85%','75%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/point!toEditPointPage.do?pointVo.id='+pointId
	});
}
/**
 * 删除知识点
 */
function delPoint(pointId,name){
	layer.open({
		title: [
			delete_point_title,
		 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    content: delete_point_content+"("+name+")",
	    btn: [confirm,cancel],
	    shadeClose: false,
	    yes: function(){
	    	$.ajax({
				url:"../tech/point!deletePoint.do",
				data: {"pointVo.id": pointId},
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.success=='true'){
						$("#pointTable").DataTable().draw();
						layer.msg(success_delete_point);
					}else{
						if(data.message=='1'){//课程用
							var content=data.datas.inUseCourse;
			        		content=content.replace(new RegExp("&lt;","g"),"<");
			        		content=content.replace(new RegExp("&gt;","g"),">");
			        		layer.alert(fail_delete + fail_content1 + content,{title:fail_delete_title});
						}else if(data.message=='2'){//教学计划用
							var content=data.datas.inUsePlan;
			        		content=content.replace(new RegExp("&lt;","g"),"<");
			        		content=content.replace(new RegExp("&gt;","g"),">");
			        		layer.alert(fail_delete + fail_content2 + content,{title:fail_delete_title});
						}else if(data.message=='3'){//课程&教学计划用
							var inUseCourse=data.datas.inUseCourse;
							var inUsePlan=data.datas.inUsePlan;
							inUseCourse=inUseCourse.replace(new RegExp("&lt;","g"),"<");
							inUseCourse=inUseCourse.replace(new RegExp("&gt;","g"),">");
							inUsePlan=inUsePlan.replace(new RegExp("&lt;","g"),"<");
							inUsePlan=inUsePlan.replace(new RegExp("&gt;","g"),">");
							layer.alert(fail_delete + fail_content1 + inUseCourse
									+ fail_content2 + inUsePlan,{title:fail_delete_title});
						}else if(data.message=='4'){//有除课程&教学计划之外的地方在用
							layer.alert(fail_delete + fail4_content,{title:fail_delete_title});
						}else{
							layer.msg(data.message);
						}
						
					}
				}
			});
	    }
	});
}
/**
 * 复制知识点
 */
function copyPointDetail(pointId){
	var index=layer.open({
		title: [
				copy_point_title,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    offset: '10%',// 距离页面顶距离
	    area: [ '40%','40%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/point!toCopyPointPage.do?pointVo.id='+pointId
	});
}

/**
 * 导出知识点数据
 */
function exportTopic(){
	var name=$("#name").val();
	var enable=$("#enable").val();
	var first_skill=$("#first_skill").val();
	var second_skill=$("#second_skill").val();
	var third_skill=$("#third_skill").val();
	window.open("../tech/point!downTopicData.do?name="+name+"&enable="+enable+"&first_skill="+first_skill+"&second_skill="+second_skill+"&third_skill="+third_skill);
}