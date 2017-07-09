$(function() {
	//下拉框初始化
	selectInitial("name", "../tech/course!getCourseOption.do?courseVo.description=yesAND-1","",false);
	
});
/**
 * 查看知识点体系,根据no或者id查看
 */
$(document).on("click","#knowledgeCheckButton",
	function() {
		var courseNo=$("#no").val();
		var courseId=$("#name").val();
		if(courseNo=="" && courseId==""){
			layer.msg(no_empty);
			return;
		}
		var index=layer.open({
		    type: 2,
		    title: false,
		    offset: '10%',// 距离页面顶距离
		    area: [ '60%','10%'],
		    fix: false, //不固定
		    maxmin: true,
		    content: '../tech/course!toReviewCoursePage.do?courseVo.id='+courseId+'&courseVo.no='+courseNo
		});
	}
);


/**
 * 定制知识点体系,根据no
 */
$(document).on("click","#knowledgeUpdateButton",
	function() {
		var courseNo=$("#no").val();
		if(courseNo==""){
			layer.msg(need_course_no);
			return;
		}
		$.ajax({
			url:"../tech/course!judgeToLinkSkillPage.do",
			data: {"courseVo.no": courseNo},
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.success=='true'){
					var index=layer.open({
						title: [
								"["+data.datas.course.name+"] "+link_skill_title,
							 	'background-color:#3C8DBC; color:#ffffff;'
						],
					    type: 2,
					    offset: '2%',// 距离页面顶距离
					    area: [ '95%','95%'],
					    fix: false, //不固定
					    maxmin: true,
					    content: '../tech/course!toLinkSkillPage.do?courseVo.id='+data.datas.course.id+'&courseVo.name='+data.datas.course.name
					});
				}else{
					if(data.message=="1111")
						layer.alert(course_not_exist);
					if(data.message=="2222")
						layer.alert(course_locked);
				}
			}
		});
	}
);
