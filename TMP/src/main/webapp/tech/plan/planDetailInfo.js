//---------------------------js文本全局变量----------------------------------
//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 


//-------------------------页面加载完成后执行方法-------------------------------
//页面加载完成执行
$(function() {
	//1.初始化方法
	initial();
});



//------------------------------js方法-------------------------------------
function initial(){
	//加载数据
	getPlanInfo();
	//关闭
	$('#closeIframe').click(function(){
	    parent.layer.close(index);
	});	
}

//初始化获得菜单信息
function getPlanInfo(){
	var planVo_starttime = $("#planVo_starttime").val();
	var planVo_endtime = $("#planVo_endtime").val();
	var planVo_teacher_id = $("#planVo_teacher_id").val();
	var planVo_teacher_name = $("#planVo_teacher_name").val();
	var planVo_point = $("#planVo_point").val();
	var planVo_point_id = $("#planVo_point_id").val();
	var finish_state = $("#planVo_finish_state").val();
	//
	getHours2(planVo_starttime,planVo_endtime,function(r){
		$("#hours").val(r[0]);
		$("#mins").val(r[1]);
	});	
	//
	$("#point").val(planVo_point);
	document.getElementById("point").disabled = "disabled";
	$("#starttime").val(planVo_starttime);
	document.getElementById("starttime").disabled = "disabled";
	$("#endtime").val(planVo_endtime);
	document.getElementById("endtime").disabled = "disabled";	
	document.getElementById("hours").disabled = "disabled";		
	document.getElementById("mins").disabled = "disabled";
	selectInitial("finish_state","../tech/plan!getPoint_Finish_state.do",finish_state,true);
	selectInitial("teacher_id","../tech/plan!getAllTeacherSelect.do",planVo_teacher_id,true);
	
}




