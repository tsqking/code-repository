var id;
$(function() {
	//获取试卷信息
	id = document.getElementById("id").value;
	//查询数据
	findPaperTest();
})

//查询试卷数据
function findPaper(paperId){
	$.ajax({
		url : "../tech/teacherPlan!getPaperInfo.do",
		data : "paperVo.id="+paperId,
		dataType : "json",
		type : "post",
		success : function(data) {
			if(data.success=='true'){
				if(data.message=='0'){
					//拼接信息
					var paper = data.datas.data;
					var total_time;
					if(paper.total_time=="-1"){
						total_time = editPaper_no_time;// I18N - editPaper_no_time:不限时 
					}else{
						total_time = paper.total_time+"mins";
					}
					var info = editPaper_paper_name+":"+paper.name+"&nbsp;&nbsp;"+editPaper_paper_no+":"+paper.no+"&nbsp;&nbsp;<br/>"+editPaper_paper_use_flag_name+":"+// I18N - editPaper_paper_name:名称 editPaper_paper_no:编号 editPaper_paper_use_flag_name:用途 
					paper.use_flag_name+"&nbsp;&nbsp;"+editPaper_paper_property_name+":"+paper.property_name+"&nbsp;&nbsp;"+editPaper_paper_total_item+":"+// I18N - editPaper_paper_property_name:属性 editPaper_paper_total_item:题数 
					paper.total_item+"&nbsp;&nbsp;"+editPaper_paper_total_score+":"+paper.total_score+"&nbsp;&nbsp;"+editPaper_total_time+":"+total_time;// I18N - editPaper_paper_total_score:总分 editPaper_total_time:推荐用时 
					document.getElementById("contacts").innerHTML = info;
					//初始化下拉框  试卷属性 用途
					selectInitial("edit_paper_type", "../system/option!getOptionsByGPVal.do?value=PAPER_PROP","",false);
					//初始化时间控件
					dateInitial([ {"id":"edit_paper_start_time","type":"1"},{"id":"edit_paper_end_time","type":"1"}]);
					//时间计算
					changeLongTime();
					//绑定时间框的触发方法
					$('#edit_paper_start_time').on( 'change', function() {
						changeLongTime();
					});
					$('#edit_paper_end_time').on( 'change', function() {
						changeLongTime();
					});	
				}else{
					parent.layer.msg(editPaper_find_fail);// I18N - editPaper_find_fail:查询失败 
				}
			}else{
				layer.msg(data.message,{shift: 6});
			}
		}
	});
}
//查询测试数据
function findPaperTest(){
	$.ajax({
		url : "../tech/teacherPlan!getPaperTestInfo.do",
		data : "planPaperVo.id="+id,
		dataType : "json",
		type : "post",
		success : function(data) {
			if(data.success=='true'){
				if(data.message=='0'){
					//from 赋值
					praseJson(data.datas.data, "", "edit_", "")
					//查找试卷信息
					findPaper(data.datas.data.paper_id);
				}else{
					layer.msg(editPaper_find_fail);// I18N - editPaper_find_fail:查询失败 
				}
			}else{
				layer.msg(data.message,{shift: 6});
			}
		}
	});
}

//计算时长
function changeLongTime(){
	//取值
	var paper_start_time = $('#edit_paper_start_time').val();
	var paper_end_time = $('#edit_paper_end_time').val();
	//非空判断
	if(paper_start_time==null||paper_end_time==null||paper_start_time==""||paper_end_time==""){
		$('#edit_paper_long_time').val("");
	}else{
		var re = timeTime(paper_start_time,paper_end_time);
		if(re!="1"){
			$('#edit_paper_end_time').val("");
			$('#edit_paper_long_time').val("");
			layer.msg(editPaper_time_check);// I18N - editPaper_time_check:结束时间不能等于或者在开始时间之前! 
		}else{
			$('#edit_paper_long_time').val(getLongTime(paper_start_time,paper_end_time));
		}
	}
}

//添加作业
function editPaperTest(){
	var itemArr=[
	             {"id":"edit_paper_start_time","type":"1","regular":null,"message":null},
	             {"id":"edit_paper_end_time","type":"1","regular":null,"message":null}
	             ];
	if(validate(itemArr)){
		$.ajax({
			url : "../tech/teacherPlan!editPaperTest.do",
			data : $("#editForm").serialize(),
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.success=='true'){
					if(data.message=='0'){
						layer.msg(editPaper_update_success);// I18N - editPaper_update_success:更新成功 
						parent.refreshTable()
						parent.layer.close(parent.layer.getFrameIndex(window.name));
					}else if(data.message=='1'){
						layer.msg(editPaper_update_fail);// I18N - editPaper_update_fail:更新失败 
					}
				}else{
					layer.msg(data.message,{shift: 6});
				}
			}
		});
	}
}
