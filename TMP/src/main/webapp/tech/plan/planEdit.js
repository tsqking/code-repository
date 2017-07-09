//---------------------------js文本全局变量----------------------------------
//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 
//结束时间备份
var oldEndTime2;
//开始和结束时间的修改开关
var flag = true;
var flag1 = true;


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
	//parent.parent.layer.iframeAuto(index);
	//初始化知识点结构
	getTreeView("1");
	//选择框初始化
	selectInitial("course_id","../tech/plan!getAllCourseName_Id.do",$("#course_id_hidden").val(),true);
	selectInitial("class_id","../tech/plan!getAllClassName_Id.do",$("#class_id_hidden").val(),true);
	selectInitial("location","../system/option!getOptionsByGPVal.do?value=LOCATION",$("#location_id_hidden").val(),true);
	//时间日期控件初始化
	dateInitial([ {"id":"start_time","type":"1"},{"id":"end_time","type":"1"}]);
	$("#start_time").val($("#start_time_hidden").val());
	$("#end_time").val($("#end_time_hidden").val());
	//捆绑应用按钮
	//编辑
	$('#editIframe').click(function(){
		layer.confirm(planEdit1, {// planEdit1:确定要生效此修改吗? - 
		    btn: [systemOK,systemCancel] //按钮// menu7:确定 - menu8:取消 - 
		}, function(){
			saveData(function(){
				
			});
		}, function(){
		    ;
		});
	});
	//关闭
	$('#closeIframe').click(function(){
	    parent.layer.close(index);
	});
	//开始时间的change/click方法
	var old = $('#start_time').val();
	var old2 = $('#end_time').val();
	$('#start_time').change(function(n){
		if(($('#start_time').val().length)*1==16){
			$('#start_time').val(($('#start_time').val()+":00"));
		}		
		var time1 = old;
		var time2 = $('#start_time').val();
		//校验时间
		changeTimeForTrueTime($('#start_time').val(),function(f){
			$('#start_time').val(f);
			layer.msg(planEdit2);// planEdit2:时间已处理非工作日和节假日! - 
			//获取正确的结束时间		
			getHours2(old,old2,function(r){
				addHoursMins($('#start_time').val(),r[0],r[1],function(r1){
					$('#end_time').val(r1);
					//刷新知识点tree
					getTreeView("2");
					flag1 = false;
				})	
			});			
		});			
	});
	//结束时间的方法
	$('#end_time').change(function(n){
		if(($('#end_time').val().length)*1==16){
			$('#end_time').val(($('#end_time').val()+":00"));
		}
		if($("#start_time").val()!=""){
			var l = timeTime($("#start_time").val(),$("#end_time").val());
			if(l=="1"){
				//校验时间
				changeTimeForTrueTime($('#end_time').val(),function(f){
					$('#end_time').val(f);
					layer.msg(planEdit3);// planEdit3:时间已处理非工作日和节假日! - 
					getTreeView("2");
				});	
			}else{
				layer.msg(planEdit4);// planEdit4:结束时间必须在开始时间之后! - 
				$("#end_time").val(oldEndTime2);
			}	
			flag1 = false;
		}else{
			layer.msg(planEdit5);// planEdit5:请选开始时间! - 
		}	
	});
	//重置时间
	/*$('#reset').click(function(){		
		$('#start_time').val($('#start_time_hidden').val());
		$('#end_time').val($('#end_time_hidden').val());
		getTreeView("3");
		flag1 = true;
	});*/
}
//重置时间
$(document).on("click","#reset",
	function() {
		$('#start_time').val($('#start_time_hidden').val());
		$('#end_time').val($('#end_time_hidden').val());
		getTreeView("3");
		flag1 = true;
	}
);
//初始化知识点结构
function getTreeView(type){	
	//树形结构
	var d = {};
	d.id = $("#planVoId").val();
	d.course_id = $("#course_id_hidden").val();
	//
	if(type=="1"){
		//查询数据库信息的时间
		//现在开始的时间
		d.starttime = "null";
		//现在结束的时间
		d.endtime = "null";
		//原来开始的时间
		d.start_time = "null";
		//原来结束的时间
		d.end_time = "null";
	}else if(type=="2"){	
		//修改开始和结束时间修改
		//现在开始的时间
		d.starttime = $("#start_time_hidden").val();
		//现在结束的时间
		d.endtime = $("#end_time_hidden").val();
		//原来开始的时间
		d.start_time = $("#start_time").val();
		//原来结束的时间
		d.end_time = $("#end_time").val();
	}else if(type=="3"){
		//不修改开始和结束时间,重新计算
		//现在开始的时间
		d.starttime = "null";
		//现在结束的时间
		d.endtime = "null";
		//原来开始的时间
		d.start_time = "null";
		//原来结束的时间
		d.end_time = "null";
		//类型->重设
		d.type = "reSet";
	}	
	flag = true;
	var load_index = layer.load(0, {shade: false});
	//layer.msg(plan17);
	//初始化树形结构
	treeViewInitial("treeview","../tech/plan!getPlanPoint.do",d,"btn-expand-all","btn-collapse-all","select-expand-all-levels",
			function(data){
				eval(data);
				var mes1 = '<span data-toggle=\"tooltip\" title=\"'+planEdit6+'\" onclick=\"changeInfo(\''+jsonToStringForAction(data)+'\')\">';// planEdit6:点击编辑 - 
				var mes11 = "<span>"+data.starttime+"&nbsp;-&nbsp;"+data.endtime+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				var mes111 = "<span>"+data.finish_state_name+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				var mes2 = "<span>"+data.hour+" Hours "+data.min+" Mins</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				//老师初始化
				var mes3 = "</span>";
				if(data.teacher_id=="0"){
					mes3 = '<span>'+planEdit7+'</span></span>';// planEdit7:未指定授课老师! - 
				}else{
					mes3 = "<span>"+data.teacher_name+"</span></span>";
				}
				//结束时间显示	
				oldEndTime2 = data.endtime;
				//判断是否关闭时间控件
				if(data.finish_state!="4"){
					flag = false;	
					document.getElementById("start_time").disabled="disabled";
					document.getElementById("end_time").disabled="disabled";
				}
				//赋值页面隐藏结束值
				if(type=="1"){
					$("#end_time_hidden").val(data.endtime);
				}
				//将结束时间显示出来
				$("#end_time").val(data.endtime);
				layer.close(load_index);
				return mes1+mes11+mes111+mes2+mes3;
			},clearTree()
	);
	close_open_time();
}


//修改知识点方法
function changeInfo(data){
	if(flag&&!flag1){
		//判断
		layer.confirm(planEdit8, {// planEdit8:修改详细信息必须先保存修改,确认保存修改吗? - 
		    btn: [systemOK,systemCancel] //按钮// menu7:确定 - menu8:取消 - 
		}, function(){
			saveData(function(){
				openDetail(data);
			});
			flag1=false;
		}, function(){
		    ;
		});
	}else{
		//直接打开
		openDetail(data);	
	}
}
function openDetail(data){
	parent.layer.full(index);
	//iframe窗
	layer.open({
		title : titleInfo5,
		offset: '7%',// 距离页面顶距离
		type: 2,
	    area: ['70%', '40%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/plan!toPlanChangeInfo.do'+data
	});
}

//清空tree
function clearTree(){
	//重置
	document.getElementById("treeviewIdDiv").innerHTML = 
		"<div id=\"treeview\" class=\"treeview\">"+
			"<blockquote>"+
				'<p>'+planEdit9+'</p>'+// planEdit9:此课程没有知识点数据 - 
				'<small>'+planEdit10+'</cite></small>'+// planEdit10:请重新选择课程! - 
			"</blockquote>"
		"</div>";
}

//时间控件开关
function close_open_time(){
	if(flag){
		document.getElementById("start_time").disabled="";
		document.getElementById("end_time").disabled="";
	}else{
		document.getElementById("start_time").disabled="disabled";
		document.getElementById("end_time").disabled="disabled";
	}
}

//保存数据的方法
function saveData(seccessFun){
	//现在开始的时间
	var t1 = $("#start_time").val();
	//现在结束的时间
	var t2 =  $("#end_time").val();
	//原来开始的时间
	var t3 = $("#start_time_hidden").val();
	//原来结束的时间
	var t4 = $("#end_time_hidden").val();

	$.ajax({
        type : "POST",
        url : "../tech/plan!updatePlanStartTime.do",
        data : "id="+$("#planVoId").val()+
        		"&course_id="+$("#course_id_hidden").val()+
        		"&start_time="+t1+
        		"&end_time="+t2+
        		"&starttime="+t3+
        		"&endtime="+t4,
        success:function(data){
             //成功的回调函数
        	if(data.success=='true'){
        		layer.msg(planEdit11);// planEdit11:修改成功! - 
        		$("#start_time_hidden").val(t1);
        		$("#end_time_hidden").val(t2);
        		initial();
        		//parent.$("#example1").DataTable().draw();
        		seccessFun();
        	}else{
        		layer.alert(planEdit12,{btn:systemOK,title :systemInfo});// planEdit12:修改失败! - 
        		$('#start_time').val(old);
        		$('#end_time').val(old2);
        	}
        }
	});
}

//重新赋值+刷新页面数据
function reGetInfo(start_time,end_time){
	$("#start_time_hidden").val(start_time);
	$("#end_time_hidden").val(end_time);
	initial();
}
/**
 * 返回班级ID号
 */
function getClassId(){
	return $("#class_id").val();
}
/**
 * 返回授课地点
 */
function getLocation(){
	return $("#location_id_hidden").val();
}