//---------------------------js文本全局变量----------------------------------

//-------------------------页面加载完成后执行方法-------------------------------
//页面加载完成执行
$(function() {
	//1.初始化方法
	initial();
	$("#select-expand-all-levels").select2({
		placeholder: "Select a state"
	});
	layer.open({
	    type: 2,
	    title: false,
	    closeBtn: 1, //不显示关闭按钮
	    shade: [0],
	    area: ['10%', '20%'],//宽高
	    offset: ['40%', '90%'], //弹出位置 top、left
	    time: 0, //60秒后自动关闭
	    shift: 2,
	    zIndex:-1,
	    content: ['../util/pdf!toSuggestReviewWayPage.do','no'], //iframe的url，no代表不显示滚动条
	    end: function(){ //此处用于演示
	    }
	});
});
//------------------------------js方法-------------------------------------
//初始化方法
function initial() {		
	var param = {
			"planVo.teach_plan_id":$("#teach_plan_id_hidden").val()
	};
	//初始化树形结构
	treeViewInitial("treeview","../tech/personalPlan!getTeacherTeachPlan.do",param,"btn-expand-all","btn-collapse-all","select-expand-all-levels",
			function(data){
				eval(data);
				var timeRange="<span data-toggle='tooltip' title='"+learnTimeArrange+"'>"+data.starttime+"&nbsp;-&nbsp;"+data.endtime+"</span>&nbsp;&nbsp;&nbsp;";
				var cost="<span data-toggle='tooltip' title='"+learnCost+"'>"+data.hour+"h"+data.min+"m</span>&nbsp;&nbsp;&nbsp;";
				var teacher_name="<span data-toggle='tooltip' title='"+byTeacher+"'>"+showTeacherName(data.teacher_name)+"</span>&nbsp;&nbsp;&nbsp;";
				var finish_state_name="<span data-toggle='tooltip' title='"+learningState+"'>"+data.finish_state_name+"</span>&nbsp;&nbsp;&nbsp;";
				var material='<span data-toggle=\"tooltip\" title=\"'+viewMaterial+'\" onclick=\"PointPDFViewOnline(\''+data.point_id+'\',1,\'\');\">'+materialName+'</span>&nbsp;&nbsp;&nbsp;';
				var sHandBook="<span data-toggle='tooltip'  title='"+viewShandBook+"' onclick=\"PointPDFViewOnline(\'"+data.point_id+"\',3,\'\');\">"+sHandBookName+"</span>&nbsp;&nbsp;&nbsp;";
				return timeRange+cost+teacher_name+finish_state_name+material+sHandBook;
			},clearTree()
	);
}
//显示教师名
function showTeacherName(teacherName){
	if(teacherName==null || teacherName=="")
		return waitingTeacher;
	return teacherName;
}
//清空tree
function clearTree(){
	//重置
	document.getElementById("treeviewIdDiv").innerHTML = 
		"<div id=\"treeview\" class=\"treeview\">"+
			"<blockquote>"+
				'<p>'+noDataInCourse+'</p>'+// 此课程没有知识点数据 - 
				'<small>'+retry+'</cite></small>'+// 请重试! - 
			"</blockquote>"
		"</div>";
}
