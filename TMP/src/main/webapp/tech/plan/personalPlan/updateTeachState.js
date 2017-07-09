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
function adjust(){
	var h = document.getElementById("box").offsetHeight;
	var bodyH=parent.document.body.scrollHeight;
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

var loading_index;
var flag=false;
function initial(){
	adjust();
	//加载数据
	//时间日期控件初始化
	dateInitial([ {"id":"starttime","type":"1"},{"id":"endtime","type":"1"}]);
	$("#starttime").val($("#starttime_hidden").val());
	$("#endtime").val($("#endtime_hidden").val());
	iniCheckState();
	//修改
	$('#updateButton').click(function(){
		layer.confirm( confirmToUpdateTeachState , { 
		    btn: [confirm ,cancel],title:[tip]  
		}, function(){
			//layer.close(index);
			loading_index = layer.load(0, {shade: false});
			updateDate();
		}, function(){
		    ;
		}); 
	});
	//状态的改变
	$('#finish_state').change(function(n){
		if(!flag){
			flag=true;
		}else{
			if($("#finish_state").val()=='1'){//完成状态
				layer.msg(finishStateMessage);
				$("#location").prop("disabled", false);
				document.getElementById("starttime").disabled=false;
				document.getElementById("endtime").disabled=false;
			}else{
				$("#location").prop("disabled", true);
				document.getElementById("starttime").disabled=true;
				document.getElementById("endtime").disabled=true;
			}
		}
	});
}

//初始化获得菜单信息
function iniCheckState(){
	if($("#finish_state_hidden").val()=='1'){//完成状态
		selectInitial("finish_state","../system/option!getOptionsByGPVal.do?value=POINT_FINISH_STATE", $("#finish_state_hidden").val(), true);
		selectInitial("location","../system/option!getOptionsByGPVal.do?value=LOCATION", $("#location_id_hidden").val(), true);
		document.getElementById("starttime").disabled=true;
		document.getElementById("endtime").disabled=true;
		document.getElementById("updateButton").disabled=true;
	}else{//非完成状态
		selectInitial("finish_state","../system/option!getOptionsByGPVal.do?value=POINT_FINISH_STATE", $("#finish_state_hidden").val(), false);
		selectInitial("location","../system/option!getOptionsByGPVal.do?value=LOCATION", $("#location_id_hidden").val(), true);
		document.getElementById("starttime").disabled=true;
		document.getElementById("endtime").disabled=true;
	}
}

/**
 * 保存数据
 */
function updateDate(){
	var showFlag=true;
	//如果状态是已完成，则需要记录授课历史
	if($('#finish_state').val()=='1'){//完成状态，记录教学历史
		if(timeTime($('#starttime').val(),$('#endtime').val())=='-1'){
			layer.close(loading_index);
			layer.msg(timeOrderError);
			return;
		}
		showFlag=false;
		$.ajax({
	        cache: true,
	        type: "POST",
	        url: "../tech/personalPlan!recordHistory.do",
	        data: {  
	        	//"histVo.teacher_id":$('#teacher_id').val(),
	        	"planVo.class_id": $('#class_id_hidden').val(),
	        	"planVo.point_id":$('#point_id_hidden').val(),
	        	"planVo.starttime":$('#starttime').val(),
	        	"planVo.endtime":$('#endtime').val(),
	        	"planVo.location":$('#location').val(),
	        	"planVo.course_id":$('#course_id_hidden').val()
	        },
	        async: true,
	        error: function(request) {
	            // nothing to do
	        },
	        success: function(data) {
//	        	if(data.success=='true'){
//	        		//parent.layer.msg("恭喜您完成本知识点的教学！");
//	        		parent.layer.alert("恭喜您完成本知识点的教学！");
//	        	}else{
//	        		parent.layer.msg("纪录授课历史出错！");
//	        		//parent.layer.alert("纪录授课历史出错！");
//	        	}	
	        }
	    });
	}
	//修改授课状态
	$.ajax({
        cache: true,
        type: "POST",
        url: "../tech/personalPlan!updateTeachState.do",
        data: {  
        	"planVo.id":$("#plan_id_hidden").val(),
        	"planVo.point_id":$("#point_id_hidden").val(),
        	"planVo.finish_state":$("#finish_state").val()
        },
        async: true,
        error: function(request) {
            // nothing to do
        },
        success: function(data) {
        	if(data.success=='true'){
        		if(data.message=='0'){//修改成功
        			layer.close(loading_index);
        			//刷新父界面
            		if(showFlag){
            			parent.layer.msg(updateSuccess);
            		}else{
            			parent.layer.msg(teachDoneMessage);
            		}
            		parent.initial();
            		//关闭弹窗
            		parent.layer.close(index);
        		}else if(data.message=='1'){//修改失败
        			layer.close(loading_index);
        			parent.layer.msg(updateFail);
        		}else{
        			layer.close(loading_index);
        			parent.layer.msg(unKnowError);
        			parent.initial();
        			parent.layer.close(index);
        		}
        	}else{//修改出错
        		layer.close(loading_index);
        		layer.msg(data.message);
        	}
        }
    });
}

