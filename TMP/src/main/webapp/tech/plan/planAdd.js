//---------------------------js文本全局变量----------------------------------
//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 
var oldEndTime;
var oldEndTime2;
var f = false;
//压缩比
var cost;


//-------------------------页面加载完成后执行方法-------------------------------
//页面加载完成执行
$(function() {
	//parent.layer.full(index);
	//1.初始化方法
	initial();
});



//------------------------------js方法-------------------------------------
function initial(){
	clearTree();
	//选择框初始化
	selectInitial("course_id","../tech/plan!getAllCourseName_Id.do","",false);
	selectInitial("class_id","../tech/plan!getAllClassName_Id.do","",false);
	//时间日期控件初始化
	dateInitial([ {"id":"start_time","type":"1"},{"id":"end_time","type":"1"}]);
	changeTimeForTrueTime(formatTime(new Date()),function(f){
		$("#start_time").val(f);
	});
	//捆绑应用按钮
	//添加
	$('#addButton').click(function(){
		if($("#start_time").val()==""||$("#end_time").val()==""||$("#class_id").val()==""||$("#course_id").val()==""){
			layer.msg(plan7);// plan7:请填写完整信息! - 
		}else{
			layer.confirm(plan8, {// plan8:确定要添加此教学计划吗? - 
			    btn: [systemOK,systemCancel] //按钮// menu7:确定 - menu8:取消 - 
			}, function(){
				layer.close(index);
				//确定
				$.ajax({
		            cache: true,
		            type: "POST",
		            url: "../tech/plan!addPlan.do?cost="+cost,
		            data: $('#searchForm').serialize(),// 你的formid
		            async: true,
		            error: function(request) {
		                //nothing to do
		            },
		            success: function(data) {
		            	//成功的回调函数
		            	if(data.success=="true"){
		            		layer.msg(plan9);		// plan9:添加成功! - 
		            		var p_id=data.datas.data.id;
			            	var p_courseId=data.datas.data.course_id;
			            	var p_classId=data.datas.data.class_id;
			            	var p_startTime=data.datas.data.start_time;
			            	var p_endTime=data.datas.data.end_time;
		            		$.ajax({
		    		            cache: true,
		    		            type: "POST",
		    		            url: "../tech/class!getClassInfo.do",
		    		            data: {
		    		            	"classVo.id":p_classId
		    		            },
		    		            async: true,
		    		            success: function(data) {
		    		            	var p_location=data.datas.classVo.location;
		    		            	parent.edit(p_id,
		    		            			p_courseId,
		    		            			p_classId,
		    		            			p_startTime,
		    		            			p_endTime,
		    		            			p_location); 
				            		//关闭添加页面	            		
				            		parent.layer.close(index);
		    		            }
		            		});
		            	}else{
		            		layer.msg(plan10);		// plan10:添加失败! - 
		            	}				  
		            }
		        });
			}, function(){
			    ;
			});
		}		
	});
	//关闭
	$('#closeIframe').click(function(){
	    parent.layer.close(index);
	});
	//开始时间的change方法
	$('#start_time').change(function(n){
		if(($('#start_time').val().length)*1==16){
			$('#start_time').val(($('#start_time').val()+":00"));
		}	
		if($("#course_id").val()==null||$("#course_id").val()==""){
			layer.msg(plan11);// plan11:请选择课程! - 
		}else{
			//校验时间
			changeTimeForTrueTime($('#start_time').val(),function(f){
				$('#start_time').val(f);
				layer.msg(plan12);	// plan12:时间已处理非工作日和节假日! - 
				getTreeView("1");
			});	
		}		
	});
	//课程的change方法
	$('#course_id').change(function(n){
		//检验是课程和班级的唯一
		checkCourseClassOnly("course_id");
		if($("#course_id").val()!=null&&$("#course_id").val()!=""){
			//时间
			if($("#start_time").val()==null||$("#start_time").val()==""){
				if(f){
					layer.msg(plan13);// plan13:请输入开始时间! - 
				}
				f = true;
			}else{
				getTreeView("1");		
			}
		}		
	});
	//班级的change方法
	$('#class_id').change(function(n){
		//检验是课程和班级的唯一
		checkCourseClassOnly("class_id");
	});
	//结束时间的change方法
	$('#end_time').change(function(n){
		if(($('#end_time').val().length)*1==16){
			$('#end_time').val(($('#end_time').val()+":00"));
		}
		if($("#start_time").val()!=""&&$("#course_id").val()!=""){
			var l = timeTime($("#start_time").val(),$("#end_time").val());
			if(l=="1"){
				//校验时间
				changeTimeForTrueTime($('#end_time').val(),function(f){
					$('#end_time').val(f);
					layer.msg(plan14);	// plan14:时间已处理非工作日和节假日! - 
					getTreeView("2");
				});			
			}else{
				layer.msg(plan15);// plan15:结束时间必须在开始时间之后! - 
				$("#end_time").val(oldEndTime2);
			}		
		}else{
			layer.msg(plan16);// plan16:请选择课程和开始时间! - 
		}		
	});
}
var load_index;
//初始化知识点结构
function getTreeView(type){
	load_index = layer.load(0, {shade: false});
	//layer.msg(plan17);	// plan17:正在加载知识点.... - 
	//树形结构
	var d = {};
	d.starttime = $("#start_time").val();
	d.course_id = $("#course_id").val();
	//
	if(type=="1"){
		d.endtime = "null";
		d.end_time = "null";
	}else{
		//现在的时间
		d.endtime = $("#end_time").val();
		//原来的时间
		d.end_time = oldEndTime;
	}	
	//初始化树形结构
	treeViewInitial("treeview","../tech/plan!getPlanPoint2.do",d,"btn-expand-all","btn-collapse-all","select-expand-all-levels",
			function(data){
				eval(data);
				var mes1 = '<span data-toggle=\"tooltip\" title=\"'+plan18+'\" onclick=\"editDetail()\">';// plan18:点击编辑和指定授课老师 - 
				var mes11 = "<span>"+data.starttime+"&nbsp;-&nbsp;"+data.endtime+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				var mes2 = "<span>"+data.hour+" Hours "+data.min+" Mins</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				var mes3 = '<span>'+plan19+'</span></span>';// plan19:未指定授课老师 - 
				//结束时间显示
				$("#end_time").val(data.endtime);
				if(type=="1"){
					oldEndTime = data.endtime;
				}
				cost = data.cost;
				oldEndTime2 = data.endtime;
				layer.close(load_index);
				return mes1+mes11+mes2+mes3;
			},clearTree()
	);
}


//清空tree
function clearTree(){
	//重置
	document.getElementById("treeviewIdDiv").innerHTML = 
		"<div id=\"treeview\" class=\"treeview\">"+
			"<blockquote>"+
				'<p>'+plan20+'</p>'+// plan20:此课程没有知识点数据 - 
				'<small>'+plan21+'</cite></small>'+// plan21:请重新选择课程! - 
			"</blockquote>"
		"</div>";
	$("#end_time").val("");
	layer.close(load_index);
}


//编辑详细
function editDetail(){
	layer.msg(plan22);// plan22:想要编辑详细信息,请先保存该计划! - 
}

//检验课程和班级的唯一
function checkCourseClassOnly(id){
	var course_id = $("#course_id").val();
	var class_id = $("#class_id").val();
	if((course_id!=null&&course_id!="")&&(class_id!=null&&class_id!="")){
		$.ajax({
            cache: true,
            async: true,
            type: "POST",
            url: "../tech/plan!checkCourseClassOnly.do?planVo.course_id="+course_id+"&planVo.class_id="+class_id,
            error: function(request) {
            	$('#'+id).val('').trigger("change");
            },
            success: function(data) {
            	//成功的回调函数
            	if(data.success=="true"){
            		//nothing to do
            	}else{	
            		layer.msg(plan23); 
            		$('#'+id).val('').trigger("change");
            	}				  
            }
        });
	}
}

