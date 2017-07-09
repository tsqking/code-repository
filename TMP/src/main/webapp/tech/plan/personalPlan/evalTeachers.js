//-------------------------页面加载完成后执行方法-------------------------------
//页面加载完成执行
$(function() {
	//1.初始化方法
	initial();
});
//------------------------------js方法-------------------------------------
/**
 * 初始化
 */
function initial(){
	selectInitial("teachers","../tech/personalPlan!getTeachersByPlan.do?planVo.teach_plan_id="+$("#plan_id_hidden").val(),"",false);
	$("#teachers").on("change", function (e) { 
		var selectedOption = $(this).find("option:selected").val();
		var optionId = selectedOption;
		if(selectedOption = null || "" == selectedOption){
			$("#saveButton").hide();
			return;
		}
		$("#saveButton").show();
		var course_id=$("#course_id_hidden").val();
		var class_id=$("#class_id_hidden").val();
		var teacher_id=optionId;
		$.ajax({
			url : "../tech/personalPlan!getStuEvalTeacherRS.do",
			type : "post",
			data:{
				"procVo.teacher_id":teacher_id,
				"procVo.course_id":course_id,
				"procVo.class_id":class_id
			},
			success : function(data) {
				if (data.success == 'true') {
					setRate(data.datas.score);
					setComment(data.datas.comment);
				}else{
					layer.alert(data.message); 
				}
			}
		});
	});
	initialRate(0);
}

function initialRate(){
	$(".ratyli").ratyli({
		cursor:"pointer",
		full:"<i class='fa fa-thumbs-up'></i>",
		empty:"<i class='fa fa-thumbs-o-up'></i>",
	});
}

/**设置评分星星树start**/
function setRate(score){
	var htmlText="";
	var max=$("#star").attr("data-ratemax");
	var j=0;
	for(var i=0;i<max;i++){
		if(j<score)
			htmlText+=getFull();
		else
			htmlText+=getEmpty();
		j++;
	}
	//设置属性与html
	$("#star").html(htmlText);
	$("#star").attr("data-rate",score);
}
function getFull(){
	return '<span class="rate rate-full" style="cursor:pointer;"><i class="fa fa-thumbs-up"></i></span>';
}
function getEmpty(){
	return '<span class="rate rate-empty" style="cursor:pointer;"><i class="fa fa-thumbs-o-up"></i></span>';
}
/**设置评分星星树end**/

function setComment(comment){
	$("#comment").val(comment);
}
/**
 * 保存评价
 */
$(document).on("click","#saveButton",
	function() {
	 	var score = $("#star").attr("data-rate");
	 	var comment = $("#comment").val();
	 	var teacher_id = $("#teachers").val();
	 	var course_id = $("#course_id_hidden").val();
	 	var class_id = $("#class_id_hidden").val();
	 	/*var load_index=layer.load(0, {
		    shade: [0.1,'#fff']
		});*/
	 	$.ajax({
			url : "../tech/personalPlan!saveFinalToTeacherEval.do",
			type : "post",
			data:{
				"procVo.course_id":course_id,
				"procVo.class_id":class_id,
				"procVo.teacher_id":teacher_id,
				"procVo.score":score,
				"procVo.comment":comment
			},
			success : function(data) {
				if (data.success == 'true') {
					var code=data.datas.code;
					if(code=='1'){
						layer.msg(evalSuccess);
					}else if(code=='2'){
						layer.msg(saveSuccess);
					}else if(code=='-1'){
						layer.alert(unKnowError,{title:feedback});
					}
				}else{
					layer.alert(data.message); 
				}
			}
		});
	}
);
