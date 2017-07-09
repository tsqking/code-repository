//---------------------------js文本全局变量----------------------------------
//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 


//-------------------------页面加载完成后执行方法-------------------------------
//页面加载完成执行
$(function() {
	//parent.layer.full(index);
	//1.初始化方法
	initial();
});



//------------------------------js方法-------------------------------------
function initial(){
	parent.$("#example1").DataTable().draw();
	//parent.layer.iframeAuto(index);
	//初始化知识点结构
	getTreeView("1");
	//选择框初始化
	selectInitial("course_id","../tech/plan!getAllCourseName_Id.do",$("#course_id_hidden").val(),true);
	selectInitial("class_id","../tech/plan!getAllClassName_Id.do",$("#class_id_hidden").val(),true);
	selectInitial("location","../system/option!getOptionsByGPVal.do?value=LOCATION",$("#location_id_hidden").val(),true);
	//时间日期控件初始化
	dateInitial([ {"id":"start_time","type":"1"},{"id":"end_time","type":"1"}]);
	$("#start_time").val($("#start_time_hidden").val());
	document.getElementById("start_time").disabled = "disabled";
	$("#end_time").val($("#end_time_hidden").val());
	document.getElementById("end_time").disabled = "disabled";
	//捆绑应用按钮
	//关闭
	$('#closeIframe').click(function(){
	    parent.layer.close(index);
	});
}

//初始化知识点结构
function getTreeView(type){	
	//树形结构
	var d = {};
	d.id = $("#planVoId").val();
	d.course_id = $("#course_id_hidden").val();
	//
	if(type=="1"){
		//现在开始的时间
		d.starttime = "null";
		//现在结束的时间
		d.endtime = "null";
		//原来开始的时间
		d.start_time = "null";
		//原来结束的时间
		d.end_time = "null";
	}else{		
		//现在开始的时间
		d.starttime = $("#start_time_hidden").val();
		//现在结束的时间
		d.endtime = $("#end_time_hidden").val();
		//原来开始的时间
		d.start_time = $("#start_time").val();
		//原来结束的时间
		d.end_time = $("#end_time").val();
	}
	//初始化树形结构
	treeViewInitial("treeview","../tech/plan!getPlanPoint.do",d,"btn-expand-all","btn-collapse-all","select-expand-all-levels",
			function(data){
				eval(data);
				var mes1 = '<span data-toggle=\"tooltip\" title=\"'+planDetailInfo1+'\" onclick=\"changeInfo(\''+jsonToStringForAction(data)+'\')\">';// planDetailInfo1:点击编辑 - 
				var mes11 = "<span>"+data.starttime+"&nbsp;-&nbsp;"+data.endtime+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				var mes111 = "<span>"+data.finish_state_name+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				var mes2 = "<span>"+data.hour+" Hours "+data.min+" Mins</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				//老师初始化
				var mes3 = "</span>";
				if(data.teacher_id=="0"){
					mes3 = '<span>'+planDetailInfo2+'</span></span>';// planDetailInfo2:未指定授课老师! - 
				}else{
					mes3 = "<span>"+data.teacher_name+"</span></span>";
				}
				return mes1+mes11+mes111+mes2+mes3;
			},clearTree()
	);
}


//修改知识点方法
function changeInfo(data){
	openDetail(data);
}
function openDetail(data){
	parent.layer.full(index);
	//iframe窗
	layer.open({
		title : titleInfo4,
		offset: '20%',// 距离页面顶距离
		type: 2,
	    area: ['1000px', '345px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/plan!toPlanDetailInfo.do'+data
	});
}

//清空tree
function clearTree(){
	//重置
	document.getElementById("treeviewIdDiv").innerHTML = 
		"<div id=\"treeview\" class=\"treeview\">"+
			"<blockquote>"+
				'<p>'+planDetailInfo3+'</p>'+// planDetailInfo3:此课程没有知识点数据 - 
				'<small>'+planDetailInfo4+'</cite></small>'+// planDetailInfo4:请重新选择课程! - 
			"</blockquote>"
		"</div>";
}
