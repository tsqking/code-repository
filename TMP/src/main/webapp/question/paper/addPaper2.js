//var begin_total_time="";
var id = 1;
var arr;
var index = parent.layer.getFrameIndex(window.name);
$(function(){
	arr = [first,second,third,four,five,six,seven,eight,nine];
	
    window.onresize = adjust;
    adjust();
    $("#total_item").find("span").text("0");
    $("#total_point").find("span").text("0");
	//新增部分
	$("#addSection").on("click",function(){
		if(id<=9){
			$(this).parent().before("<li id='"+id+"' onclick='addIframe(this)'><a style='border-radius:6px;' class='btn btn-block btn-primary'>"+arr[id-1]+"</a></li>");
			id++;
		}
	});
	
	//保存关闭
	$("#saveCloseButton").on("click",function(){
		var paperId=$("#paperVo_id").val();
		var time=$("#total_time").val();
		var leave_limit=$("#leave_limit").val();
		if(leave_limit==null || leave_limit=="") leave_limit="0";
		var final_total_item=$("#total_item").children("span").text();
		if(final_total_item==null || final_total_item=='') final_total_item="0";
		var final_total_point=$("#total_point").children("span").text();
		if(final_total_point==null || final_total_point=='') final_total_point="0";
		//if(begin_total_time!=time){
			var itemArr=[
			             {"id":"leave_limit","type":"2","regular":null,"message":null}
			             ];
			if(validate(itemArr)){
				$.ajax({//保存total时间信息
					url:"../question/paper!saveTotalInfoForPaper.do",
					data : {
						"id":paperId,"total_time":time,"leave_limit":leave_limit,
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
		//}
	});
	
	//完成创建
	$("#finishButton").on("click",function(){
		layer.open({
			title: [
				tipTitle,
			 	'background-color:#3C8DBC; color:#ffffff;'
			],
		    content: confirm_finish_paper,// var confirm_finish_paper="确认完成？<br><small>完成创建后将不能再修改试卷。</small>";
		    btn: [confirm,cancel],
		    shadeClose: false,
		    yes: function(){
		    	var paperId=$("#paperVo_id").val();
				var time=$("#total_time").val();
				var leave_limit=$("#leave_limit").val();
				if(leave_limit==null || leave_limit=="") leave_limit="0";
				var final_total_item=$("#total_item").children("span").text();
				if(final_total_item==null || final_total_item=='') final_total_item="0";
				var final_total_point=$("#total_point").children("span").text();
				if(final_total_point==null || final_total_point=='') final_total_point="0";
				var itemArr=[
				             {"id":"leave_limit","type":"2","regular":null,"message":null}
				             ];
				
				if(validate(itemArr)){
					$.ajax({
						url:"../question/paper!finishCreatePaper.do",
						data : {"id":paperId,"total_time":time,"leave_limit":leave_limit,
							"total_item":final_total_item,"total_score":final_total_point,
							"property":$("#paperVo_property").val(),"use_flag":$("#paperVo_useFlag").val(),
							"description":$("#paperVo_description").val(),"instruction":$("#paperVo_instruction").val(),
							"name":$("#paperVo_name").val()},
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
		    }
		});
	});
	
	//考试时间限制
	$("#limit").on("click",function(){
		$("#total_time").val("0");
		$("#total_time").removeAttr("readonly");
		$("#total_time").show();
	});
	$("#nolimit").on("click",function(){
		$("#total_time").attr("readonly","readonly");
		$("#total_time").hide();
		$("#total_time").val("-1");
	});
	
})

function justSavePaperInfo(){
	var paperId=$("#paperVo_id").val();
	var time=$("#total_time").val();
	var leave_limit=$("#leave_limit").val();
	if(leave_limit==null || leave_limit=="") leave_limit="0";
	var final_total_item=$("#total_item").children("span").text();
	if(final_total_item==null || final_total_item=='') final_total_item="0";
	var final_total_point=$("#total_point").children("span").text();
	if(final_total_point==null || final_total_point=='') final_total_point="0";
	//if(begin_total_time!=time){
		var itemArr=[
		             {"id":"leave_limit","type":"2","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
			$.ajax({//保存total时间信息
				url:"../question/paper!saveTotalInfoForPaper.do",
				data : {
					"id":paperId,"total_time":time,"leave_limit":leave_limit,
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
	//}
}

//点击li时，显示iframe页面
function addIframe(obj){
	//试卷ID
	var paper_id = $("#paperVo_id").val();
	//点击的第几个li，根据此来为section排序
	var li_id = $(obj).attr("id");
	$("#li_id").val(li_id);
	$.ajax({
		url:"../question/paper!createSection.do",
		data:{"paperVo.id":paper_id,"order":li_id},
		dataType:"json",
		type:"post",
		success:function(){
			$("#paperFrame").attr("src","../question/paper!toQuestionIframe.do");
			$("#paperFrame").load(function(){
				$("#total_item").find("span").text(document.paperFrame.paper_total_item);
				$("#total_point").find("span").text(document.paperFrame.paper_total_point);
			});
		}
	});
}

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
	 $(".content").css("width",2*document.body.clientWidth);
	 w = -document.body.clientWidth;
}