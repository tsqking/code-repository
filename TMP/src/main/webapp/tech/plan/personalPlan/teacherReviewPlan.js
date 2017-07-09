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
				var timeRange="<span data-toggle='tooltip' title='"+teachTimeArrange+"'>"+data.starttime+"&nbsp;-&nbsp;"+data.endtime+"</span>&nbsp;&nbsp;&nbsp;";
				var cost="<span data-toggle='tooltip' title='"+teachCost+"'>"+data.hour+"h"+data.min+"m</span>&nbsp;&nbsp;&nbsp;";
				if(data.info=='Y'){
					var finish_state_name="<span data-toggle='tooltip' title='"+updateTeachState+"' onclick=\"toChangeTeachState(\'"+data.starttime+"\',\'"+data.endtime+"\'," +
							"\'"+data.point_id+"\',\'"+data.point+"\',\'"+data.finish_state+"\',\'"+data.course_id+"\')\" >"+data.finish_state_name+"</span>&nbsp;&nbsp;&nbsp;";
					var material='<span data-toggle=\"tooltip\" title=\"'+viewMaterial+'\" onclick=\"PointPDFViewOnline(\''+data.point_id+'\',1,\'\');\">'+materialName+'</span>&nbsp;&nbsp;&nbsp;';
					var tHandBook="<span data-toggle='tooltip'  title='"+viewThandBook+"' onclick=\"PointPDFViewOnline(\'"+data.point_id+"\',2,\'\');\">"+tHandBookName+"</span>&nbsp;&nbsp;&nbsp;";
					var sHandBook="<span data-toggle='tooltip'  title='"+viewShandBook+"' onclick=\"PointPDFViewOnline(\'"+data.point_id+"\',3,\'\');\">"+sHandBookName+"</span>&nbsp;&nbsp;&nbsp;";
					var reference="<span data-toggle='tooltip'  title='"+viewReference+"' onclick=\"PointPDFViewOnline(\'"+data.point_id+"\',4,\'\');\">"+referenceName+"</span>&nbsp;&nbsp;&nbsp;";
					var critical="<span data-toggle='tooltip' title='"+evalStudent+"' onclick=\"toEvalStudent(\'"+data.point_id+"\',\'"+data.point+"\')\" >"+evalStudent+"</span>&nbsp;&nbsp;&nbsp;";
					return timeRange+cost+finish_state_name+material+tHandBook+sHandBook+reference+critical;
				}else{
					var finish_state_name="<span data-toggle='tooltip' title='"+teachState+"'>"+data.finish_state_name+"</span>&nbsp;&nbsp;&nbsp;";
					return timeRange+cost+finish_state_name;
				}
			},clearTree()
	);
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
/**
 * 去往修改教学状态界面
 */
function toChangeTeachState(starttime,endtime,pointId,pointName,finish_state,course_id){
	var class_id=$("#class_id_hidden").val();
	var location_id=$("#location_id_hidden").val();
	layer.open({
		title : [ teachState , 'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		area : [ '40%', '60%' ],
		offset: '10%',// 距离页面顶距离
		fix : false, // 不固定
		maxmin : true,
		content : '../tech/personalPlan!toUpdateTeachStatePage.do?planVo.starttime='+starttime+'&planVo.endtime='+endtime
			+'&planVo.point_id='+pointId+'&planVo.point='+pointName+'&planVo.class_id='+class_id+'&planVo.location_id='+location_id
			+'&planVo.finish_state='+finish_state+'&planVo.id='+$("#teach_plan_id_hidden").val()+"&planVo.course_id="+course_id
	});
}

function toEvalStudent(pointId,pointName){
	window.open("../tech/personalPlan!toProcEvalStudentPage.do?planVo.point_id="+pointId
			+"&planVo.point="+pointName+"&planVo.course_id="+$("#course_id_hidden").val()+"&planVo.class_id="+$("#class_id_hidden").val());
}