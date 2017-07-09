//---------------------------js文本全局变量----------------------------------
//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 
//状态原值
var status;
//防止1状态循环
var flag = true;



//-------------------------页面加载完成后执行方法-------------------------------
//页面加载完成执行
$(function() {
	//1.初始化方法
	initial();
});



//------------------------------js方法-------------------------------------

function adjust(){
	var h = document.getElementById("box").offsetHeight;
	var bodyH=parent.document.body.scrollHeight;
	console.info(h+"  "+bodyH);
	if(h>bodyH*0.7){
		content = document.getElementById("content");
		content.style.height = bodyH*0.7+ "px";
		document.body.clientHeight= bodyH*0.7 + "px";
	}else{
		content = document.getElementById("content");
		content.style.height = (h + 30) + "px";
		document.body.clientHeight=(h + 30) + "px";
	}
		parent.layer.iframeAuto(index);
}

function initial(){
	adjust();
	//加载数据
	getPlanInfo();
	//关闭
	$('#closeIframe').click(function(){
	    parent.layer.close(index);
	});	
	//确认知识点信息的修改
	$('#editIframe').click(function(){
		var itemArr=[ 
		             {"id":"hours","type":"1","regular":null,"message":null},
		             {"id":"mins","type":"1","regular":null,"message":null},
		             {"id":"finish_state","type":"1","regular":null,"message":null},
		             {"id":"hours","type":"2","regular":null,"message":null},
		             {"id":"mins","type":"2","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
			layer.confirm( planChangeInfo1 , {// planChangeInfo1:确定要修改此知识点的授课信息吗? - 
			    btn: [systemOK,systemCancel],title:[tip] //按钮// menu7:确定 - menu8:取消 - 
			}, function(){
				layer.close(index);
				//确定
				updateDate();
			}, function(){
			    ;
			}); 
		}
	});
	//状态的改变
	$('#finish_state').change(function(n){
		
	});
	//老师的选择
	$('#teacher_id').change(function(n){
		
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
	//
	checkStatus(finish_state,planVo_teacher_id);
	status = finish_state;
}


//更改结束时间
function changeEndTime(){
	var start_time = $("#starttime").val();
	var hours = $("#hours").val();
	var mins = $("#mins").val();
	addHoursMins(start_time,hours,mins,function(f){
		$("#endtime").val(f);
	})	
}

//检查知识点的状态,控制其他参数的可编辑
function checkStatus(value,planVo_teacher_id){
	if(value=="1"){
		//完成
		selectInitial("finish_state","../system/option!getOptionsByGPVal.do?value=POINT_FINISH_STATE",value,true);
		selectInitial("teacher_id","../tech/plan!getAllTeacherSelect.do",planVo_teacher_id,true);
		document.getElementById("hours").disabled = "disabled";
		document.getElementById("mins").disabled = "disabled";
		document.getElementById("editIframe").disabled = "disabled";
		document.getElementById("teacher_id").disabled = "disabled";
	}else if(value=="2"){
		//进行中
		selectInitial("finish_state","../system/option!getOptionsByGPVal.do?value=POINT_FINISH_STATE",value,false);
		selectInitial("teacher_id","../tech/plan!getAllTeacherSelect.do",planVo_teacher_id,false);
		document.getElementById("hours").disabled = "";
		document.getElementById("mins").disabled = "";
	}else if(value=="3"){
		//未完成
		selectInitial("finish_state","../system/option!getOptionsByGPVal.do?value=POINT_FINISH_STATE",value,false);
		selectInitial("teacher_id","../tech/plan!getAllTeacherSelect.do",planVo_teacher_id,false);
		document.getElementById("hours").disabled = "";
		document.getElementById("mins").disabled = "";
	}else if(value=="4"){
		//待授课
		selectInitial("finish_state","../system/option!getOptionsByGPVal.do?value=POINT_FINISH_STATE",value,false);
		selectInitial("teacher_id","../tech/plan!getAllTeacherSelect.do",planVo_teacher_id,false);
		document.getElementById("hours").disabled = "";
		document.getElementById("mins").disabled = "";
	}
}
/**
 * 弹出查看相关教师的界面
 */
function toViewRelevantTeacher(){
	if($("#teacher_id").prop("disabled")){
		return;
	}
	parent.layer.full(index);
	var load_index = layer.load(0, {shade: false});
	setTimeout(function(){
		layer.open({
			title: [
					viewRelevantTeacherTitle1 + $("#planVo_point").val()+ viewRelevantTeacherTitle2
				 	/*,'background-color:#3C8DBC; color:#ffffff;'*/
			],
		    type: 2,
		    offset: '1%',// 距离页面顶距离
		    area: [ '90%','95%'],
		    fix: false, //不固定
		    maxmin: true,
		    content: '../tech/teacherpool!toPlanToPoolPage.do?ptVo.id='+$("#planVo_point_id").val()+'&tepVo.start_time='+$("#starttime").val()+'&tepVo.end_time='+$("#endtime").val()
		});
		layer.close(load_index);
	},500);
}
/**
 * 保存数据
 */
function updateDate(){
	//如果状态是已完成，则需要记录授课历史
	if($('#finish_state').val()=='1'){//完成状态，记录教学历史
		var class_id=parent.getClassId();
		var location=parent.getLocation();
		$.ajax({
	        cache: true,
	        type: "POST",
	        url: "../tech/teacherpool!recordTechHistory.do",
	        data: {  //    
	        	"histVo.teacher_id":$('#teacher_id').val(),
	        	"histVo.class_id":class_id,
	        	"histVo.point_id":$('#planVo_point_id').val(),
	        	"histVo.starttime":$('#planVo_starttime').val(),
	        	"histVo.endtime":$('#planVo_endtime').val(),
	        	"histVo.address":location,
	        	"histVo.course_id":$('#planVo_course_id').val()
	        },
	        async: true,
	        error: function(request) {
	            // nothing to do
	        },
	        success: function(data) {
	        	// nothing to do	  
	        }
	    });
	}
	$.ajax({
        cache: true,
        type: "POST",
        url: "../tech/plan!updatePointDetail.do",
        data: {
        	"id":$("#planVo_id").val(),
        	"point_id":$("#planVo_point_id").val(),
        	"hours":$("#hours").val(),
        	"mins":$("#mins").val(),
        	"teacher_id":$("#teacher_id").val(),
        	"finish_state":$("#finish_state").val()
        },
        async: true,
        error: function(request) {
            //nothing to do
        },
        success: function(data) {
        	//成功的回调函数
        	if(data.success=="true"){
        		layer.msg(planChangeInfo2);		// planChangeInfo2:添加成功! - 
        		//关闭页面	            		
        		parent.reGetInfo(data.datas.info.starttime,data.datas.info.endtime);
        		//parent.parent.$("#example1").DataTable().draw();
        		parent.layer.close(index);
        	}else{
        		layer.msg(planChangeInfo3);		// planChangeInfo3:添加失败! - 
        	}				  
        }
    });
}
/**
 * 设置教师
 */
function setTeacher(id){
	$('#teacher_id').val(id).trigger("change");
}


