//搜索数据 
var searchData = [ {
	"name" : "more_data",
	"value" : "my_value"
} ];

$(function() {
	findMenuOptions('skillFirst', '../tech/skill!findSkillOption.do', '0');
	dataTable("skillTable","../tech/skill!findSkillData.do",
			[ [ 6, "desc" ] ], 
			[ {
				"mData" : "name",
				"sClass" : "center"
			}, {
				"mData" : "type",
				"sClass" : "center"
			}, {
				"mData" : "parent_name",
				"sClass" : "center"
			}, {
				"mData" : "level",
				"sClass" : "center"
			}, {
				"mData" : "order",
				"sClass" : "center"
			}, {
				"mData" : "enable",
				"sClass" : "center"
			}, /*{
				"mData" : "description",
				"sClass" : "center"
			},*/ {
				"mData" : "update_time",
				"sClass" : "center"
			}, {
				"mData" : "update_person",
				"sClass" : "center"
			}, {
				"mData" : "id",
				"sClass" : "center"
			}],
			[ {
				"aTargets" : [ 8 ],
				"mData" : "操作",
				"mRender" : function(data, type, full) {
					var resOperator = '<a href="javascript:void(0);" onclick="del('+ data+ ');" data-toggle=\"tooltip\" title=\"Delete\"><i class="fa fa-trash"></i></a>&nbsp;&nbsp;&nbsp'
									+ '<a href="javascript:void(0);" onclick="edit('+ data+ ');" data-toggle=\"tooltip\" title=\"Edit\"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;&nbsp;'
					if(full.level != "3" && full.level != "三级技能" && full.level != "Third Level Skill"){
						return resOperator +'<a href="javascript:void(0);" onclick="add('+ data+ ');" data-toggle=\"tooltip\" title=\"Add Child Skill\"><i class="fa fa-plus-circle"></i></a>';
					}
					return resOperator;
				}
			} ]
	);

	//获取二级联下拉框
	$('#skillFirst').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		var optionId = selectedOption;
		if(selectedOption = null || "" == selectedOption){
			return;
		}
		findMenuOptions('skillSecond','../tech/skill!findSkillOption.do', optionId);
	});
	
	
	//获取三级下拉框
	$('#skillSecond').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		var optionId = selectedOption;
		if(selectedOption = null || "" == selectedOption){
			return;
		}
		findMenuOptions('skillThird','../tech/skill!findSkillOption.do', optionId);
	});
})

// 添加技能
function add(parentId) {
	// alert(parentId);
	var h = document.documentElement.clientHeight;
	layer.open({
		title : [ skillAddWindowTitle, 'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		offset: '1%',// 距离页面顶距离
		area : [ '60%', (h - 0.46 * h) + 'px' ],
		fix : false, // 不固定
		maxmin : true,
		content : '../tech/skill!addSkillPage.do?skillVo.parent_id='
				+ parentId
	});
}

// 初始化下拉框
function findMenuOptions(optionId, url, parent_id) { 
	selectInitial(optionId, url + '?skillVo.parent_id=' + parent_id, "",false);
}

//搜索按钮
$(document).on("click","#searchButton",
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
					$("#skillTable").DataTable().draw();
				});

//重置按钮
$(document).on("click", "#resetButton", function() {
	$("#skillFirst").val("").trigger("change");;
	$("#skillSecond").val("").trigger("change");;
	$("#skillThird").val("").trigger("change");;
	searchData = [];
});

//删除技能
function del(id){
	layer.open({
		title: [
			deleteInfo,
		 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    content: skill_delete_content,
	    btn: [confirm, cancel],
	    shadeClose: false,
	    yes: function(){
	    	$.ajax({
				url:"../tech/skill!deleteSkill.do?skillVo.id="+id,
//				data: {"id":id},//$("#form").serialize()
				dataType:"json",
				type:"get",
				success:function(data){
					if(data.success=='true'){
						layer.msg(success_delete+data.message);
						$("#skillTable").DataTable().draw();
						findMenuOptions('skillFirst', '../tech/skill!findSkillOption.do', '0');
					}else{ 
						if(data.message=='1'){
							var content=data.datas.existPoint;
			        		content=content.replace(new RegExp("&lt;","g"),"<");
			        		content=content.replace(new RegExp("&gt;","g"),">");
							layer.alert(fail_delete+content,{title:fail_delete_title});
						}else{
							layer.msg(data.message);
						}
					}
				}
			});
	    }
	});
}

//更新技能
function edit(id){
	var h = document.documentElement.clientHeight;
	layer.open({
		title: [
				skillUpdateWindowTitle,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
		offset: '1%',// 距离页面顶距离
	    type: 2,
	    area: [ '60%' , (h-0.46*h)+'px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: "../tech/skill!toEditSkillPage.do?skillVo.id="+id,
	});
}