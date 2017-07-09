var id = 1;
/*var first="第一部分";
var second="第二部分";
var third="第三部分";
var four="第四部分";
var five="第五部分";
var six="第六部分";
var seven="第七部分";
var eight="第八部分";
var nine="第九部分";*/
var arr;// = [first,second,third,four,five,six,seven,eight,nine];
var index = parent.layer.getFrameIndex(window.name);

//删除UL下的所有li
function del_ul(){
	$("#addSection").parent().prevAll("li").remove();
}

//重新生成ul
function update_ul(obj){
	for(var i=1; i<obj; i++){
		$("#addSection").parent().before("<li id='"+i+"' onclick='addIframe(this)'><a style='border-radius:6px;' class='btn btn-block btn-primary'>"+arr[i-1]+"</a></li>");
	}
}

function addIframe(obj){
	var paper_id = $("#paperVo_id").val();
	//点击的第几个li，根据此来为section排序
	var li_id = $(obj).attr("id");
	$("#li_id").val(li_id);
	$.ajax({
		url:"../question/paper!createSection.do",
		data:{"paperVo.id":paper_id,"order":li_id},//$("#editForm").serialize(),
		dataType:"json",
		type:"post",
		success:function(data){
			$("#paperFrame").attr("src","../question/paper!toQuestionIframe.do");
			$("#paperFrame").load(function(){
				$("#total_item").find("span").text(document.paperFrame.paper_total_item);
				$("#total_point").find("span").text(document.paperFrame.paper_total_point);
			});
		}
	});
}

//获取iframe中的表格
function getTableInFrame(){
	return $("#paperFrame").contents().find("#paper_question_table");
}

//获取iframe中的总题数
function getItemsInFrame(){
	return $("#paperFrame").contents().find("#total_item").children("span");
}

//获取iframe中的总分数
function getPointInFrame(){
	return $("#paperFrame").contents().find("#total_point").children("span");
}

//调用iframe中的adjust方法
function getFrameMethod(){
	return $("#paperFrame")[0].contentWindow.adjust();
}

function adjust(){
	//$(".content").css("width",2*document.body.clientWidth);
	//w = -document.body.clientWidth;
	//$("body").height($("#step2").height());
}

$(function(){
	selectInitial("property","../system/option!getOptionsByGPVal.do?value=PAPER_PROP", $("#hidden_prop").val(), false);
	selectInitial("use_flag","../system/option!getOptionsByGPVal.do?value=USE_FLAG", $("#hidden_useFlag").val(), false);
	
	arr = [first,second,third,four,five,six,seven,eight,nine];
    window.onresize = adjust;
    adjust();
	//获取试卷总分和总题数
	$.ajax({
		url:"../question/paper!selectPaperInfo.do",
		data :$("#editForm").serialize(),
		dataType:"json",
		type:"post",
		success:function(data){
			var total_point = data.datas.total_point;
			$("#total_point").find("span").text(total_point);
			var total_item = data.datas.total_item;
			$("#total_item").find("span").text(total_item);
			var total_time = data.datas.total_time;
			if(total_time > 0){//限制时间
				$("#nolimit").removeAttr("checked");
				$("#limit").attr("checked","checked");
				$("#total_time").removeAttr("readonly");
				$("#total_time").val(total_time);
			}else{
				$("#nolimit").attr("checked","checked");
				$("#total_time").val("-1");
				$("#total_time").attr("readonly","readonly");
				$("#total_time").hide();
			}
			
			//清空数据
			id = 1;
			$("#addSection").parent().prevAll().remove();
			
			//部分的数量
			if(Number($("#section_count").val()) > 0){
				for(var i=0; i<Number($("#section_count").val()); i++){
					$("#addSection").parent().before("<li id='"+id+"' onclick='addIframe(this)'><a style='border-radius:6px;' class='btn btn-block btn-primary'>"+arr[id-1]+"</a></li>");
					id++;
				}
			}
		}
	});
	
	//新增部分
	$("#addSection").on("click",function(){
		if(id<=9){
			$(this).parent().before("<li id='"+id+"' onclick='addIframe(this)'><a style='border-radius:6px;' class='btn btn-block btn-primary'>"+arr[id-1]+"</a></li>");
			id++;
		}
	});
	
	//刷新总题数、总分数信息
	$("#refreshTotalInfoButton").on("click",function(){
		$.ajax({
			url:"../question/paper!selectPaperInfo.do",
			data :{"paperVo.id":$("#paperVo_id").val()},
			dataType:"json",
			type:"post",
			success:function(data){
				var total_point = data.datas.total_point;
				$("#total_point").find("span").text(total_point);
				var total_item = data.datas.total_item;
				$("#total_item").find("span").text(total_item);
			}
		});
	});
	
	//保存关闭
	$("#saveCloseButton").on("click",function(){
		var property = $("select#property option:selected").val();
		var use_flag = $("select#use_flag option:selected").val();
		var description = turnCharactor($("#description").val());
		var instruction = turnCharactor($("#instruction").val());
		var paperId=$("#paperVo_id").val();
		var time=$("#total_time").val();
		var leave_limit=$("#leave_limit").val();
		if(leave_limit==null || leave_limit=="") leave_limit="0";
		var final_total_item=$("#total_item").children("span").text();
		if(final_total_item==null || final_total_item=='') final_total_item="0";
		var final_total_point=$("#total_point").children("span").text();
		if(final_total_point==null || final_total_point=='') final_total_point="0";
		//TODO 数据校验
		var itemArr=[
		             {"id":"property","type":"1","regular":null,"message":null},
		             {"id":"use_flag","type":"1","regular":null,"message":null},
		             {"id":"leave_limit","type":"2","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
			$.ajax({//保存total时间信息
				url:"../question/paper!savePaperInfo.do",
				data : {
					"id":paperId,"total_time":time,"property":property,"use_flag":use_flag,"description":description,"instruction":instruction,"leave_limit":leave_limit,
					"total_item":final_total_item,"total_score":final_total_point
				},
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.success=='true'){
						 parent.refreshTable();
						 parent.layer.close(index);
					 }else{
						 layer.alert(error,{shift: 6});
						 console.info(data.message);
					 }
				}
			});
		}
	});
	
	//完成创建
	$("#finishButton").on("click",function(){
		var property = $("select#property option:selected").val();
		var use_flag = $("select#use_flag option:selected").val();
		var description =  turnCharactor($("#description").val());
		var instruction =  turnCharactor($("#instruction").val());
		var paperId=$("#paperVo_id").val();
		var time=$("#total_time").val();
		var leave_limit=$("#leave_limit").val();
		if(leave_limit==null || leave_limit=="") leave_limit="0";
		var final_total_item=$("#total_item").children("span").text();
		if(final_total_item==null || final_total_item=='') final_total_item="0";
		var final_total_point=$("#total_point").children("span").text();
		if(final_total_point==null || final_total_point=='') final_total_point="0";
		//TODO 数据校验
		var itemArr=[
		             {"id":"property","type":"1","regular":null,"message":null},
		             {"id":"use_flag","type":"1","regular":null,"message":null},
		             {"id":"leave_limit","type":"2","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
			layer.open({
				title: [
					tipTitle,
				 	'background-color:#3C8DBC; color:#ffffff;'
				],
			    content: confirm_finish_paper,// var confirm_finish_paper="确认完成？<br><small>完成创建后将不能再修改试卷。</small>";
			    btn: [confirm,cancel],
			    shadeClose: false,
			    yes: function(){
			    	$.ajax({
						url:"../question/paper!finishCreatePaper.do",
						data : {"id":paperId,"total_time":time,"property":property,"use_flag":use_flag,"description":description,
							"instruction":instruction,"leave_limit":leave_limit,"total_item":final_total_item,"total_score":final_total_point,"name":$("#name1").val()},
						dataType:"json",
						type:"post",
						success:function(data){
							if(data.success=='true'){
								 if(data.message=="0000"){
									 parent.layer.msg(addPaperSuc);
									//编辑数据后刷新表格
									 parent.refreshTable();
									 parent.layer.close(index);
								 }else if(data.message=="1111"){
									 layer.alert(addPaperFail, {title:feedback});
								 }
							 }else{
								 layer.msg(data.message,{shift: 6});
							 }
						}
					});
			    }
			});
		}
	});
	
	//考试时间限制
	$("#limit").on("click",function(){
		$("#total_time").show();
		$("#total_time").val("0");
		$("#total_time").removeAttr("readonly");
	});
	$("#nolimit").on("click",function(){
		$("#total_time").attr("readonly","readonly");
		$("#total_time").hide();
		$("#total_time").val("-1");
	});
	
})

function justSavePaperInfo(){
	var property = $("select#property option:selected").val();
	var use_flag = $("select#use_flag option:selected").val();
	var description =  turnCharactor($("#description").val());
	var instruction =  turnCharactor($("#instruction").val());
	var paperId=$("#paperVo_id").val();
	var time=$("#total_time").val();
	var leave_limit=$("#leave_limit").val();
	var final_total_item=$("#total_item").children("span").text();
	if(final_total_item==null || final_total_item=='') final_total_item="0";
	var final_total_point=$("#total_point").children("span").text();
	if(final_total_point==null || final_total_point=='') final_total_point="0";
	//TODO 数据校验
	var itemArr=[
	             {"id":"property","type":"1","regular":null,"message":null},
	             {"id":"use_flag","type":"1","regular":null,"message":null},
	             {"id":"leave_limit","type":"2","regular":null,"message":null}
	             ];
	if(validate(itemArr)){
		$.ajax({//保存total信息
			url:"../question/paper!savePaperInfo.do",
			data : {
				"id":paperId,"total_time":time,"property":property,"use_flag":use_flag,"description":description,"instruction":instruction,"leave_limit":leave_limit,
				"total_item":final_total_item,"total_score":final_total_point
			},
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.success=='true'){
					 parent.refreshTable();
				 }else{
					 layer.alert(error,{shift: 6});
					 console.info(data.message);
				 }
			}
		});
	}
}