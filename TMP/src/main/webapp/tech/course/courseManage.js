//搜索数据 (必须)
var searchData = [ { "name" : "more_data" , "value" : "my_value" } ];

$(function() {
	//下拉框初始化
	selectInitial("searchEnable", "../system/option!getOptionsByGPVal.do?value=STATUS","",false);
	selectInitial("searchProperty", "../system/option!getOptionsByGPVal.do?value=CSE_PROPERTY","",false);
	selectInitial("searchDirection", "../system/option!getOptionsByGPVal.do?value=DIRECTION","",false);
	selectInitial("searchCategory", "../system/option!getOptionsByGPVal.do?value=CSE_TYPE","",false);
	multiSelectInitial("searchSubCategory", "../tech/course!getSubCategory.do","",false);
	multiSelectInitial("searchSubSubCategory", "../tech/course!getSubSubCategory.do","",false);
	//搜索按钮初始化
	dataTableSearchButton("searchButton","courseTable","searchForm",null);
	//清空按钮初始化
	dataTableClearButton("resetButton","searchForm");
	//表格控件初始化
	dataTable("courseTable","../tech/course!getCoursePage.do",
			[ [ 6, "desc" ] ], 
			[ {
				"mData" : "name",
				"sClass" : "center"
			}, {
				"mData" : "no",
				"sClass" : "center"
			}, {
				"mData" : "property",
				"sClass" : "center"
			}, {
				"mData" : "direction",
				"sClass" : "center"
			}, {
				"mData" : "lock",
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
			} ],
			[ 
			   {
					"aTargets" : [ 8 ],
					"mRender" : function(data, type, full) {
						var detail = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+tip_info+'" onclick="getCourseDetail(\''
							+ data +'\')"><i class="fa fa-navicon"></i></a>';
						var edit = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+tip_edit+'" onclick="editCourseDetail(\''
							+ data + '\')"><i class="fa fa-edit"></i></a>';
						var linkSkill = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+tip_contain_skill+'" onclick="linkSkill(\''
							+ data + '\',\''+full.name+'\')"><i class="fa fa-chain"></i></a>';
						var reviewCourseSkillPoint = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+tip_review_course_skill_point+'" onclick="reviewCourseSkillPoint(\''
							+ data + '\',\''+full.name+'\')"><i class="fa fa-tree"></i></a>';
						var del = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+tip_delete+'" onclick="delCourse(\''
							+ data + '\',\''+full.name+'\')"><i class="fa fa-trash"></i></a>';
						return detail+"&nbsp;&nbsp;&nbsp;"+edit+"&nbsp;&nbsp;&nbsp;"+linkSkill+"&nbsp;&nbsp;&nbsp;"+reviewCourseSkillPoint+"&nbsp;&nbsp;&nbsp;"+del;
					}
			   }
			]
	);
});
/**
 * 课程技能知识点一览
 */
function reviewCourseSkillPoint(courseId,courseName){
	var index=layer.open({
	    type: 2,
	    title: false,
	    offset: '1%',// 距离页面顶距离
	    area: [ '60%','10%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/course!toLinkSkill_ReviewCoursePage.do?courseVo.id='+courseId+'&courseVo.name='+courseName
	});
}
/**
 * 添加新的课程
 */
function addNewCourse(){
	var index=layer.open({
		title: [
				add_new_course_title,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    offset: '1%',// 距离页面顶距离
	    area: [ '85%','75%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/course!toAddNewCoursePage.do'
	});
}
/**
 * 查看课程详细
 * @param id
 */
function getCourseDetail(id){
	var index=layer.open({
		title: [
				view_course_title,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    offset: '1%',// 距离页面顶距离
	    area: [ '85%','75%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/course!toViewCoursePage.do?courseVo.id='+id
	});
}
/**
 * 编辑课程信息
 * @param id
 */
function editCourseDetail(id){
	var index=layer.open({
		title: [
				edit_course_title,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    offset: '1%',// 距离页面顶距离
	    area: [ '85%','75%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/course!toEditCoursePage.do?courseVo.id='+id
	});
}
/**
 * 课程对应的技能知识点管理
 * @param id
 */
function linkSkill(id,name){
	var index=layer.open({
		title: [
				"["+name+"] "+link_skill_title,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    offset: '1%',// 距离页面顶距离
	    area: [ '95%','95%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/course!toLinkSkillPage.do?courseVo.id='+id+'&courseVo.name='+name
	});
}
/**
 * 删除课程
 */
function delCourse(id,name){
	layer.open({
		title: [
			delete_course_title,
		 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    content: delete_course_content+"["+name+"]",
	    btn: [confirm,cancel],
	    shadeClose: false,
	    yes: function(){
	    	$.ajax({
				url:"../tech/course!deleteCourse.do",
				data: {"courseVo.id": id},
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.success=='true'){
						$("#courseTable").DataTable().draw();
						layer.msg(success_delete_course);
					}else{
						if(data.message=='-1'){//教学计划使用
							layer.alert(fail_delete_content,{title:fail_delete_title});
						}else if(data.message=='-2'){//其他使用
							layer.alert(fail_delete_content1,{title:fail_delete_title});
						}{
							layer.msg(data.message);
						}
					}
				}
			});
	    }
	});
}
/**
 * 导出课程数据
 */
function exportCourse(){
	var no=$("#searchNo").val();
	var name=$("#searchName").val();
	var dir=$("#searchDirection").val();
	var prop=$("#searchProperty").val();
	var category=$("#searchCategory").val();
	var subcategory1=$("#searchSubCategory").val();
	var subcategory="";
	if(subcategory1!=null){
		for(var ii=0;ii<subcategory1.length;ii++){
			if(ii==0)
				subcategory+=subcategory1[ii];
			else
				subcategory+=","+subcategory1[ii]
		}
	}
	var subsubcategory1=$("#searchSubSubCategory").val();
	var subsubcategory="";
	if(subsubcategory1!=null){
		for(var ii=0;ii<subsubcategory1.length;ii++){
			if(ii==0)
				subsubcategory+=subsubcategory1[ii];
			else
				subsubcategory+=","+subsubcategory1[ii]
		}
	}
	var enable=$("#searchEnable").val();
	window.open("../tech/course!downCourseData.do?no="+no+"&name="+name+"&dir="+dir+"&prop="+prop+"&category="+category+"&scatrgory="+subcategory+"&sscategory="+subsubcategory+"&enable="+enable);
}
