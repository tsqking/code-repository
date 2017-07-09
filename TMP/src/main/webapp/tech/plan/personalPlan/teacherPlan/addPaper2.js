var planId;
var paper_id;
var paper_no;
var paper_name;
var paper_use_flag_name;
var paper_property_name;
var paper_total_item;
var paper_total_score;
var paper_total_time;
$(function() {
	//获取试卷信息
	planId = document.getElementById("planId").value;
	paper_id = document.getElementById("paper_id").value;
	paper_no = document.getElementById("paper_no").value;
	paper_name = document.getElementById("paper_name").value;
	paper_use_flag_name = document.getElementById("paper_use_flag_name").value;
	paper_property_name = document.getElementById("paper_property_name").value;
	paper_total_item = document.getElementById("paper_total_item").value;
	paper_total_score = document.getElementById("paper_total_score").value;
	paper_total_time = document.getElementById("paper_total_time").value;
	//拼接信息
	var total_time;
	if(paper_total_time=="-1"){
		total_time = addPaper2_no_time;// I18N - addPaper2_no_time:不限时 
	}else{
		total_time = paper_total_time+"mins";
	}
	var info = addPaper2_paper_name+":"+paper_name+"&nbsp;&nbsp;"+addPaper2_paper_no+":"+paper_no+"&nbsp;&nbsp;<br/>"+addPaper2_paper_use_flag_name+":"+// I18N - addPaper2_paper_name:名称 addPaper2_paper_no:编号 addPaper2_paper_use_flag_name:用途 
		paper_use_flag_name+"&nbsp;&nbsp;"+addPaper2_paper_property_name+":"+paper_property_name+"&nbsp;&nbsp;"+addPaper2_paper_total_item+":"+// I18N - addPaper2_paper_property_name:属性 addPaper2_paper_total_item:题数 
		paper_total_item+"&nbsp;&nbsp;"+addPaper2_paper_total_score+":"+paper_total_score+"&nbsp;&nbsp;"+addPaper2_total_time+":"+total_time;// I18N - addPaper2_paper_total_score:总分 addPaper2_total_time:推荐用时 
	document.getElementById("contacts").innerHTML = info;
	//初始化下拉框  试卷属性 用途
	selectInitial("paper_type", "../system/option!getOptionsByGPVal.do?value=PAPER_PROP","",false);
	//初始化时间控件
	dateInitial([ {"id":"paper_start_time","type":"1"},{"id":"paper_end_time","type":"1"}]);
	//绑定时间框的触发方法
	$('#paper_start_time').on( 'change', function() {
		changeLongTime();
	});
	$('#paper_end_time').on( 'change', function() {
		changeLongTime();
	});
})

//计算时长
function changeLongTime(){
	//取值
	var paper_start_time = $('#paper_start_time').val();
	var paper_end_time = $('#paper_end_time').val();
	//非空判断
	if(paper_start_time==null||paper_end_time==null||paper_start_time==""||paper_end_time==""){
		$('#paper_long_time').val("");
	}else{
		var re = timeTime(paper_start_time,paper_end_time);
		if(re!="1"){
			$('#paper_end_time').val("");
			$('#paper_long_time').val("");
			parent.layer.msg(addPaper2_time_check);// I18N - addPaper2_time_check:结束时间不能等于或者在开始时间之前! 
		}else{
			$('#paper_long_time').val(getLongTime(paper_start_time,paper_end_time));
		}
	}
}

//添加作业
function addPaperTest(){
	var itemArr=[
	             {"id":"paper_start_time","type":"1","regular":null,"message":null},
	             {"id":"paper_end_time","type":"1","regular":null,"message":null}
	             ];
	if(validate(itemArr)){
		$.ajax({
			url : "../tech/teacherPlan!addPaperTest.do",
			data : $("#addForm").serialize(),
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.success=='true'){
					if(data.message=='0'){
						parent.layer.msg(addPaper2_add_success);// I18N - addPaper2_add_success:添加成功 
						parent.closeWindows()
					}else if(data.message=='1'){
						parent.layer.msg(addPaper2_add_fail);// I18N - addPaper2_add_fail:添加失败 
					}
				}else{
					layer.msg(data.message,{shift: 6});
				}
			}
		});
	}
}
