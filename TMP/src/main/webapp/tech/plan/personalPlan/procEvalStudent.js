//-------------------------页面加载完成后执行方法-------------------------------
//页面加载完成执行
$(function() {
	//1.初始化方法
	initial(true);
	//一键签到
	$("#oneKeyPro").click(function() {
		layer.open({
			title : [ procEvalStudent_64_sure_title, 'background-color:#3C8DBC; color:#ffffff;' ], // procEvalStudent_64_sure_title:确认操作
			content : procEvalStudent_64_sure_sgin_context, // procEvalStudent_64_sure_sgin_context:确认一键设定签到信息?
			btn : [ confirm, cancel ],
			shadeClose : false,
			yes : function() {
					$.ajax({
						url : "../tech/teacherPlan!oneKeyPro.do",
						data: {"point_id": $("#point_id_hidden").val(),"class_id": $("#class_id_hidden").val(),
								"course_id": $("#course_id_hidden").val(),
								"sign":$("#oneKeyProSelect").val()},
						dataType : "json",
						type : "get",
						success : function(data) {
							if (data.success == 'true') {
								if (data.message == '1') {//设定失败
									layer.alert(procEvalStudent_64_fail , { // procEvalStudent_64_fail:失败
										title : procEvalStudent_64_fail_info // procEvalStudent_64_fail_info:设定失败!
									});
								} else if (data.message == '0')  {//设定失败
									initial(false);
									layer.msg(procEvalStudent_64_success_info); // procEvalStudent_64_success_info:设定成功!
								}
							} else {
								layer.msg(procEvalStudent_64_fail_info_18); // procEvalStudent_64_fail_info_18:设定失败!
								console.info(data.message);
							}
						}
					});
			}	
		});	
	});
	//一键打分
	$("#oneKeyScore").click(function() {
		layer.open({
			title : [ procEvalStudent_64_sure_title_72, 'background-color:#3C8DBC; color:#ffffff;' ], // procEvalStudent_64_sure_title_72:确认操作
			content : procEvalStudent_64_sure_score_context, // procEvalStudent_64_sure_score_context:确认一键设定分数信息?
			btn : [ confirm, cancel ],
			shadeClose : false,
			yes : function() {
					$.ajax({
						url : "../tech/teacherPlan!oneKeyScore.do",
						data: {"point_id": $("#point_id_hidden").val(),"class_id": $("#class_id_hidden").val(),
							"course_id": $("#course_id_hidden").val(),
							"score":$("#oneKeyProRatyli").attr("data-rate")},
						dataType : "json",
						type : "get",
						success : function(data) {
							if (data.success == 'true') {
								if (data.message == '1') {//设定失败
									layer.alert(procEvalStudent_64_fail_88 , { // procEvalStudent_64_fail_88:失败!
										title : procEvalStudent_64_fail_info_75 // procEvalStudent_64_fail_info_75:设定失败!
									});
								} else if (data.message == '0')  {//设定失败
									initial(false);
									layer.msg(procEvalStudent_64_success_info_12); // procEvalStudent_64_success_info_12:设定成功!
								}
							} else {
								layer.msg(procEvalStudent_64_fail_info_52); // procEvalStudent_64_fail_info_52:设定失败!
								console.info(data.message);
							}
						}
					});
			}	
		});		
	});
});
//------------------------------js方法-------------------------------------
/**
 * 初始化
 */
function initial(flag){
	$.ajax({
		url : "../system/option!getOptionsByGPVal.do?value=SIGN",
		type : "post",
		success : function(data) {
			if (data.success == 'true') {
				var optArr = data.datas.options;
				$.ajax({
					url : "../tech/personalPlan!getProcEvalList.do",
					type : "post",
					data:{
						"procVo.course_id":$("#course_id_hidden").val(),
						"procVo.class_id":$("#class_id_hidden").val(),
						"procVo.point_id":$("#point_id_hidden").val()
					},
					success : function(data) {
						if (data.success == 'true') {
							var htmlText='';
							$.each(data.datas.procEvalList, function(i, item){
								htmlText+='<div class="wrapper">';
									htmlText+='<div class="item">';
										htmlText+='<div class="simpleInfo">';
											htmlText+='<strong><span id="'+item.user_id+'_flag">'+iniFlag(item.flag)+'</span>'+item.user_name+'-'+item.user_en_name+'</strong>';
										htmlText+='</div>';
										htmlText+='<div class="information">';
											htmlText+='<div class="eval">';
												htmlText+='<div class="row">';
													htmlText+='<div class="col-md-6 col-xs-6">';
														htmlText+='<h3>'+signUp+'</h3>';
														htmlText+='<select class="form-control selectAll" id="'+item.user_id+'_sign">';
															htmlText+=generateOptionText(optArr,item.sign);
														htmlText+='</select>';
													htmlText+='</div>';
													htmlText+='<div class="col-md-6 col-xs-6">';
														htmlText+='<h3>'+evalScore+'</h3>';
														htmlText+='<span id="'+item.user_id+'_star" class="ratyli" data-rate='+item.score+' data-ratemax="10" style="margin-top:10px;"></span>';
													htmlText+='</div>';
												htmlText+='</div>';
												htmlText+='<div class="row">';
													htmlText+='<div class="col-md-12 col-xs-12"  style="margin-top:5px;">';
														htmlText+='<h3>'+evalComment+'</h3> ';
														htmlText+='<div class="input-group">';
														htmlText+='<div class="input-group-addon"><i class="fa fa-smile-o"></i></div>';
														htmlText+='<input id="'+item.user_id+'_comment" type="text" class="form-control" value="'+iniComment(item.comment)+'" placeholder="'+simpleLeanIntro+'" />';
														htmlText+='</div>';
													htmlText+='</div>';
												htmlText+='</div>';
												htmlText+='<div class="row">';
													htmlText+='<div class="col-md-5 col-xs-5 col-md-offset-7 col-xs-offset-7"  style="margin-top:5px;">';
														htmlText+='<button type="button" class="btn btn-block btn-default btn-sm" '
															+' onclick="saveEval(\''+item.user_id+'\')"><li class="fa fa-check"></li>&nbsp;&nbsp;&nbsp;'+saveBt+'</button>';
													htmlText+='</div>';
												htmlText+='</div>';
											htmlText+='</div>';
										htmlText+='</div>';
									htmlText+='</div>';
								htmlText+='</div>';
							}); 
							$("#container").html(checkContainHtml(htmlText));
							//星评初始化
							if(flag){
								$(".eval .ratyli").ratyli({rate:5,cursor:"pointer",
									onRated:function(value,init){
										//rating callback
										//if(!init) alert(value);  // prevent run at init
									},
									disable:false
								});
							}else{
								$(".eval .ratyli:not(#oneKeyProRatyli)").ratyli({rate:5,cursor:"pointer",
									onRated:function(value,init){
										//rating callback
										//if(!init) alert(value);  // prevent run at init
									},
									disable:false
								});
							}
						} else {
							layer.alert(data.message); 
						}
					}
				});
			} else {
				layer.alert(optIniError);// 下拉框初始化出错! - 
			}
		}
	});
	//下拉框初始化--使用效果不尽人意
	/*$(".selectAll").select2({
		placeholder: "Select a state"
	});*/
}
/**
 * 初始化评价与否标志
 * @param flag
 * @returns
 */
function iniFlag(flag){
	if(flag=='N')
		return '<li class="fa fa-square-o"></li>&nbsp;&nbsp;&nbsp;';
	return '<li class="fa fa-check-square-o"></li>&nbsp;&nbsp;&nbsp;';
}
/**
 * 初始化评论
 */
function iniComment(comment){
	if(comment==null || comment=="")return "";
	return comment;
}
/**
 * 生成下拉框
 * @param optArr
 * @param selKey
 * @returns {String}
 */
function generateOptionText(optArr,selKey){
	var opt_text="";
	opt_text += ('<option value=""> </option>');
	for (var i = 0; i < optArr.length; i++) {
		if(selKey=="" || selKey==null){
			opt_text += ('<option value="' + optArr[i].id + '">'
					+ optArr[i].text + '</option>');
		}else{
			if(optArr[i].id==selKey){
				opt_text += ('<option value="' + optArr[i].id + '" selected>'
						+ optArr[i].text + '</option>');
			}else{
				opt_text += ('<option value="' + optArr[i].id + '">'
						+ optArr[i].text + '</option>');
			}
		}
	}
	return opt_text;
}
/**
 * 保存评价
 */
function saveEval(userId){
	var score=$("#"+userId+"_star").attr("data-rate");
	var sign=$("#"+userId+"_sign").val();
	var comment=$("#"+userId+"_comment").val();
	$.ajax({
		url : "../tech/personalPlan!saveProcEval.do",
		type : "post",
		data:{
			"procVo.course_id":$("#course_id_hidden").val(),
			"procVo.class_id":$("#class_id_hidden").val(),
			"procVo.point_id":$("#point_id_hidden").val(),
			"procVo.student_id":userId,
			"procVo.sign":sign,
			"procVo.score":score,
			"procVo.comment":comment
		},
		success : function(data) {
			if(data.success=='true'){
				var code=data.datas.code;
				if(code=='1'){
					layer.msg(evalSuccess);
					changeFlag(userId);
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
/**
 * 更新评价与否标志
 */
function changeFlag(userId){
	$("#"+userId+"_flag").html('<li class="fa fa-check-square-o"></li>&nbsp;&nbsp;&nbsp;');
}
/**
 * 空处理
 */
function checkContainHtml(htmlText){
	if(htmlText=='')
		return '<div class="row">'
					+'<div class="col-md-12 col-xs-12">'
						+'<div class="col-md-9 col-xs-9 col-md-offset-3 col-xs-offset-3">'
							+noStudentInClass
						+'</div>'
					+'</div>'
				+'</div>';
	return htmlText;
}
