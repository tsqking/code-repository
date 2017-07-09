//数据
var paper_id;//试卷ID
var user_id;//学生ID
var plan_paper_id;//作业测试ID
var count = 0;//已批改条数
var allCount = 0;//总条数
var scoreList = new Map();//分数List
//replaceAll函数
String.prototype.replaceAll = function(s1,s2) { 
	return this.replace(new RegExp(s1,"gm"),s2); 
}
//方法
$(function() {
	//数据
	paper_id = $("#paper_id").val();
	user_id = $("#user_id").val();
	plan_paper_id = $("#plan_paper_id").val();
	//初始化
	init(false);
	//工具栏-返回按钮
	$("#btn_go_back").click(function() {
		parent.refreshTable();
		parent.layer.close(parent.layer.getFrameIndex(window.name));
	});
	//工具栏-刷新按钮
	$("#btn_flash").click(function() {
		init(false);
	});
	//工具栏-自动批阅客观题
	$("#btn_auto_correct").click(function() {
		$.ajax({
			url : "../tech/paperUIAction!setAutoCorrectPaper.do",
			data: "answerInfoVo.paper_id="+paper_id+"&answerInfoVo.user_id="+user_id,
			dataType : "json",
			type : "get",
			success : function(data) {
				if (data.success == 'true') {
					if (data.message == '1') {
						//失败
						layer.msg(correctPaper_84_auto_correct_fail); // correctPaper_84_auto_correct_fail:自动批阅客观题失败!
					} else if (data.message == '0')  {
						//成功
						layer.msg(correctPaper_84_auto_correct_success); // correctPaper_84_auto_correct_success:自动批阅客观题成功
						//重新初始化页面=并判断是否阅卷完成
						init(true);
					}
				} else {
					layer.msg(correctPaper_84_auto_correct_fail_57); // correctPaper_84_auto_correct_fail_57:自动批阅客观题失败!
					console.info(data.message);
				}
			}
		});
	});
	//绑定关闭刷新的方法
	parent.closeFun();
})
//初始化
function init(flag){
	scoreList = new Map();
	//1.初始化载入试卷结构信息
	initQuestionInfo(flag);
}
//初始化载入试卷结构信息
function initQuestionInfo(flag){
	$.ajax({
		url : "../tech/paperUIAction!getSectionInfo.do",
		data: "answerInfoVo.paper_id="+paper_id+"&answerInfoVo.user_id="+user_id,
		dataType : "json",
		type : "get",
		success : function(data) {
			if (data.success == 'true') {
				if (data.message == '1') {
					//失败
					layer.msg(correctPaper_84_getdata_fail); // correctPaper_84_getdata_fail:获取数据失败!
				} else if (data.message == '0')  {
					//成功
					initSection('sectionInfo',data.datas.data);
					//初始化载入批改进度
					initProgressInfo(flag);
					//初始化试卷内容
					initContext();
				}
			} else {
				layer.msg(correctPaper_84_getdata_fail_18); // correctPaper_84_getdata_fail_18:获取数据失败!
				console.info(data.message);
			}
		}
	});
}
//初始化载入批改进度
function initProgressInfo(flag){
	$.ajax({
		url : "../tech/paperUIAction!getProgress.do",
		data: "answerInfoVo.paper_id="+paper_id+"&answerInfoVo.user_id="+user_id,
		dataType : "json",
		type : "get",
		success : function(data) {
			if (data.success == 'true') {
				if (data.message == '1') {
					//失败
					layer.msg(correctPaper_84_getdata_fail_87); // correctPaper_84_getdata_fail_87:获取数据失败!
				} else if (data.message == '0')  {
					//成功
					count = (data.datas.data.count)*1;//已批改条数
					allCount = (data.datas.data.allCount)*1;//总条数
					initProgress('progress',data.datas.data.pro);	
					if(flag){
						//判断更新后台分数
						updateCorrectPro();
					}	
				}
			} else {
				layer.msg(correctPaper_84_getdata_fail_86); // correctPaper_84_getdata_fail_86:获取数据失败!
				console.info(data.message);
			}
		}
	});
}
//初始化试卷内容
function initContext(){
	$.ajax({
		url : "../tech/paperUIAction!getAnswerSheet.do",
		data: "answerInfoVo.paper_id="+paper_id+"&answerInfoVo.user_id="+user_id,
		dataType : "json",
		type : "get",
		success : function(data) {
			if (data.success == 'true') {
				if (data.message == '1') {
					//失败
					layer.msg(correctPaper_84_getdata_fail_43); // correctPaper_84_getdata_fail_43:获取数据失败!
				} else if (data.message == '0')  {
					//成功
					initPaperContext('question',data.datas.data);
				}
			} else {
				layer.msg(correctPaper_84_getdata_fail_95); // correctPaper_84_getdata_fail_95:获取数据失败!
				console.info(data.message);
			}
		}
	});	
}
//打分
function score(id,questionId,section_id,all_score,judge_person){
	var value = $('#'+id+'_score').val();
	if((all_score*1)<(value*1)){
		//目标分数大于满分
		layer.msg(correctPaper_84_target_score_check_big); // correctPaper_84_target_score_check_big:目标分数不能大于满分!
		$('#'+id+'_score').val(all_score);
	}else{
		if(!/^[0-9]+.?[0-9]*$/.test(value)){
			layer.msg(correctPaper_84_target_score_check_number); // correctPaper_84_target_score_check_number:分数必须为数值类型!
		}else{
			value = (value*1).toFixed(2);
			$('#'+id+'_score').val(value);
			//符合要求
			$.ajax({
				url : "../tech/paperUIAction!setScore.do",
				data: "answerSheetVo.paper_id="+paper_id+"&answerSheetVo.user_id="+user_id+
					"&answerSheetVo.score="+value+"&answerSheetVo.question_id="+questionId,
				dataType : "json",
				type : "get",
				success : function(data) {
					if (data.success == 'true') {
						if (data.message == '1') {
							//失败
							layer.msg(correctPaper_84_score_fail); // correctPaper_84_score_fail:提交分数失败!
						} else if (data.message == '0')  {
							//成功
							layer.msg(correctPaper_84_score_success); // correctPaper_84_score_success:提交分数成功!
							//更改本列打分状态
							$('#'+id+'_flag').html(correctPaper_84_get_score+':&nbsp;'+value+'&nbsp;'+correctPaper_84_score); // correctPaper_84_get_score:得分 // correctPaper_84_score:分
							$('#'+id+'_flag').css("color","#3c8dbc");
							//刷新数据列表
							//1.题块总分
							//原来总分
							var list = scoreList.get("_"+section_id).values();
							var allS = 0;
							for(var i = 0;i<list.length;i++){
								allS = allS+(list[i]*1);
							}
							//新的分数
							allS = allS-((scoreList.get("_"+section_id).get("_"+questionId))*1)+(value*1);
							scoreList.get("_"+section_id).remove("_"+questionId);
							scoreList.get("_"+section_id).put("_"+questionId,value*1);
							$('#'+section_id+'_section_score').html(allS.toFixed(2));	
							//2.试卷总分
							var paperScore = getPaperScore();
							//3.批改进度
							if(judge_person=="null"){
								//增加进度
								count++;
								$('#progress_bar').css("width",(count/allCount*100)+"%");
								document.getElementById("pro_num").innerHTML=(count/allCount*100).toFixed(1)+"%";
							}else{
								//不增加进度
							}
							//4.如果批改完成则更新后台分数
							updateCorrectPro();
						}
					} else {
						layer.msg(correctPaper_84_score_fail_35); // correctPaper_84_score_fail_35:提交分数失败!
						console.info(data.message);
					}
				}
			});	
		}
	}
}
//写批注
function remark(id,questionId){
	var value = $('#'+id+'_remark').val();	
	$.ajax({
		url : "../tech/paperUIAction!setRemark.do",
		data: "answerSheetVo.paper_id="+paper_id+"&answerSheetVo.user_id="+user_id+
			"&answerSheetVo.remark="+value+"&answerSheetVo.question_id="+questionId,
		dataType : "json",
		type : "get",
		success : function(data) {
			if (data.success == 'true') {
				if (data.message == '1') {
					//失败
					layer.msg(correctPaper_84_remark_fail); // correctPaper_84_remark_fail:提交备注失败!
				} else if (data.message == '0')  {
					//成功
					layer.msg(correctPaper_84_remark_success); // correctPaper_84_remark_success:提交备注成功!
				}
			} else {
				layer.msg(correctPaper_84_remark_fail_51); // correctPaper_84_remark_fail_51:提交备注失败!
				console.info(data.message);
			}
		}
	});	
}
//---------------------------------------------------------------------
//判断改卷是否完成
function updateCorrectPro(){
	if(count>=allCount){
		$.ajax({
			url : "../tech/paperUIAction!setCheckPaper.do",
			data: "answerInfoVo.paper_id="+paper_id+"&answerInfoVo.user_id="+user_id+"&answerInfoVo.plan_paper_id="+plan_paper_id,
			dataType : "json",
			type : "get",
			success : function(data) {
				if (data.success == 'true') {
					if (data.message == '1') {
						//失败
						layer.msg(correctPaper_84_update_re_fail); // correctPaper_84_update_re_fail:更新批改结果失败!
					} else if (data.message == '0')  {
						//成功
						layer.msg(correctPaper_84_update_re_success); // correctPaper_84_update_re_success:该试卷批改完成!
					}
				} else {
					layer.msg(correctPaper_84_update_re_fail_34); // correctPaper_84_update_re_fail_34:更新批改结果失败!
					console.info(data.message);
				}
			}
		});	
	}
}
//js计算试卷总分
function getPaperScore(){
	var list = scoreList.values();
	var allS = 0;
	for(var i1 = 0;i1<list.length;i1++){
		var list2 = list[i1].values();
		for(var i2 = 0;i2<list2.length;i2++){
			allS = allS+(list2[i2]*1);
		}	
	}		
	allS = allS.toFixed(2);
	$('#paper_score').html(allS);
	return allS;
}
//UI封装控件-题块信息
function initSection(divId,data){
	var context;
	//头部内容
	context = '<table class="table table-striped"><tbody>';
	//标题行
	context = context + ('<tr><th style="width: 10px">#</th>'+
				'<th>'+correctPaper_84_question_section+'</th><th>'+correctPaper_84_question_desc+'</th><th>'+ // correctPaper_84_question_section:题块 // correctPaper_84_question_desc:说明
				correctPaper_84_question_number+'</th><th>'+correctPaper_84_get_score_37+'</th><th>'+correctPaper_84_all_score+'</th></tr>'); // correctPaper_84_question_number:题目数 // correctPaper_84_get_score_37:得分 // correctPaper_84_all_score:总分
	//内容行
	var num = 1;
	$.each(data,function(n, all){
		var score = "0.00";
		if(all.question_score!=null&&all.question_score!=""){
			score = all.question_score;
		}
		var info = '<tr>'+
				   '<td>'+num+'.</td>'+
				   '<td style="word-wrap:break-word;word-break:break-all;">'+all.name+'</td>'+
				   '<td style="word-wrap:break-word;word-break:break-all;">'+all.instruction+'</td>'+
				   '<td>'+all.question_number+'</td>'+
				   '<td><span class="badge bg-green"><span id="'+all.id+'_section_score">'+score+'</span>'+correctPaper_84_score_26+'</span></td>'+ // correctPaper_84_score_26:分
				   '<td><span class="badge bg-green">'+all.question_score_all+correctPaper_84_score_11+'</td>'+		   // correctPaper_84_score_11:分
				   '</tr>';
		context = context + info;
		num++;
	});
	//尾部
	context = context + '</tbody></table>';
	//赋值
	document.getElementById(divId).innerHTML=context;
}
//UI封装控件-批改进度
function initProgress(divId,data){
	//内容
	var info = ('<div class="progress progress-sm active" style="margin-bottom: 0px;margin-top: 6px;">'+
        	  '<div id="progress_bar" class="progress-bar progress-bar-success progress-bar-striped" '+
        	  'role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: '+data+'%">'+
	    	  '</div></div>');
    document.getElementById(divId).innerHTML=info;
    document.getElementById("pro_num").innerHTML=data+"%";
}
//UI封装控件-试卷内容
function initPaperContext(divId,data){
	//内容
	var context = '';
	//题块内容
	var section = '';
	//上一个题块id
	var section_1 = null;
	var section_title = '';
	//计数
	var num = 1;
	$.each(data,function(n, all){
		//同步获取图片
		var queImgs;
		var queImgsStr;
		var optImgs;
		$.ajax({
			url : "../question/question!getImageList.do?questionVo.id="+all.question_id,
			dataType : "json",
			type : "post",
			async: false,
			success : function(data) {
				 if(data.success=='true'){
					 if(data.message=="0"){
						 queImgs=data.datas.que;
						 queImgsStr=data.datas.queStr;
						 optImgs=data.datas.opt;
					 }else if(data.message=="1"){
						 layer.alert('通讯失败', {title:feedback});
						 return null;
					 }
				 }else{
					 layer.msg(data.message,{shift: 6});
					 return null;
				 }
			}
		});
		//包题块
		var section_2 = all.section_id;
		if(section_1!=section_2&&section_1!=null){
			section = '<!-- 题块内容 -->'+
						'<div class="row">'+
							'<div class="col-md-12 col-xs-12">'+
								'<div class="box box-primary">'+
									'<div class="box-header with-border" data-widget="collapse">'+
										'<i class="fa fa-minus"></i>'+
										'<h3 class="box-title" style="word-wrap:break-word;word-break:break-all;">'+
											section_title+
										'</h3>'+
									'</div>'+
									'<!-- /.box-header -->'+
									section+
									'</div></div></div>';
			context = context + section;
			section = '';
		}
		section_1 = section_2;
		section_title = (returnCharactor(all.section_name)+'('+returnCharactor(all.section_instruction)+')');
		//记录分数据列表
		var sectionId = all.section_id;
		var questionId = all.question_id;
		var sectionList = scoreList.get('_'+sectionId);
		if(sectionList==null){
			sectionList = new Map();
		}
		if(all.score!=null&&all.score!=""){
			sectionList.put('_'+questionId,all.score);
		}else{
			sectionList.put('_'+questionId,"0");
		}
		scoreList.remove('_'+sectionId);
		scoreList.put('_'+sectionId,sectionList);
		//遍历每一题
		var type = all.type+"";
		var question2 = '';
		var stuAnswer = '';
		var trueAnswer = '';
		if(type=="1"||type=="2"){
			//1-单选,2-多选
			//选项
			//A
			if(all.opt1!=null){
				question2 = question2 + ('<div class="row" style="margin-left: 0px;margin-bottom: 5px;" ><div class="col-md-6 col-xs-6" style=""><div class="row" style="word-wrap:break-word;word-break:break-all;padding-right: 10px;"><strong>A.</strong>'+stringChange(all.opt1)+'</div>'+optImg(optImgs,1)+'</div>');	
			}
			//B
			if(all.opt2!=null){
				question2 = question2 + ('<div class="col-md-6 col-xs-6" style=""><div class="row" style="word-wrap:break-word;word-break:break-all;padding-right: 10px;"><strong>B.</strong>'+stringChange(all.opt2)+'</div>'+optImg(optImgs,2)+'</div></div>');
			}
			//C
			if(all.opt3!=null){
				question2 = question2 + ('<div class="row" style="margin-left: 0px;margin-bottom: 5px;" ><div class="col-md-6 col-xs-6" style=""><div class="row" style="word-wrap:break-word;word-break:break-all;padding-right: 10px;"><strong>C.</strong>'+stringChange(all.opt3)+'</div>'+optImg(optImgs,3)+'</div>');
			}
			//D
			if(all.opt4!=null){
				question2 = question2 + ('<div class="col-md-6 col-xs-6" style=""><div class="row" style="word-wrap:break-word;word-break:break-all;padding-right: 10px;"><strong>D.</strong>'+stringChange(all.opt4)+'</div>'+optImg(optImgs,4)+'</div></div>');
			}
			//E
			if(all.opt5!=null){
				question2 = question2 + ('<div class="row" style="margin-left: 0px;margin-bottom: 5px;" ><div class="col-md-6 col-xs-6" style=""><div class="row" style="word-wrap:break-word;word-break:break-all;padding-right: 10px;"><strong>E.</strong>'+stringChange(all.opt5)+'</div>'+optImg(optImgs,5)+'</div>');
			}
			//F
			if(all.opt6!=null){
				question2 = question2 + ('<div class="col-md-6 col-xs-6" style=""><div class="row" style="word-wrap:break-word;word-break:break-all;padding-right: 10px;"><strong>F.</strong>'+stringChange(all.opt6)+'</div>'+optImg(optImgs,6)+'</div></div>');
			}
			question2 = '<h5 class="box-title">'+question2+'</h5>';
			//学生答案
			var an = ['','A','B','C','D','E','F'];
			var answer1 = (stringChange(all.answer+"")).split("#$#");
			for(var i = 0;i<answer1.length;i++){
				if(answer1[i]!=null&&answer1[i]!=""){
					stuAnswer = stuAnswer + (an[answer1[i]]+"&nbsp;&nbsp;");
				}	
			}
			//标准答案
			var answer2 = (stringChange(all.true_answer+"")).split("#$#");
			for(var i = 0;i<answer2.length;i++){
				if(answer2[i]!=null&&answer2[i]!=""){
					trueAnswer = trueAnswer + (an[answer2[i]]+"&nbsp;&nbsp;");
				}	
			}	
		}else if(type=="3"){
			//3-判断
			stuAnswer = (all.answer=='2'?correctPaper_84_error:(all.answer=='1'?correctPaper_84_right:"")); // correctPaper_84_error:错误 // correctPaper_84_right:正确
			trueAnswer = (all.true_answer=='2'?correctPaper_84_error_47:correctPaper_84_right_73); // correctPaper_84_error_47:错误 // correctPaper_84_right_73:正确
		}else if(type=="4"){
			//4-填空
			//学生答案
			var answer1 = (stringChange(all.answer+"")).split("#$#");
			for(var i = 0;i<answer1.length;i++){
				if(answer1[i]!=null&&answer1[i]!=""){
					stuAnswer = stuAnswer + (answer1[i]+"&nbsp;&nbsp;&nbsp;");
				}	
			}
			//标准答案
			var answer2 = (stringChange(all.true_answer+"")).split("#$#");
			for(var i = 0;i<answer2.length;i++){
				if(answer2[i]!=null&&answer2[i]!=""){
					trueAnswer = trueAnswer + (answer2[i]+"&nbsp;&nbsp;&nbsp;");
				}	
			}		
			/*
			if(all.opt1!=null){
				trueAnswer = trueAnswer + (all.opt1+'&nbsp;&nbsp;&nbsp;');
			}
			if(all.opt2!=null){
				trueAnswer = trueAnswer + (all.opt2+'&nbsp;&nbsp;&nbsp;');
			}
			if(all.opt3!=null){
				trueAnswer = trueAnswer + (all.opt3+'&nbsp;&nbsp;&nbsp;');
			}
			if(all.opt4!=null){
				trueAnswer = trueAnswer + (all.opt4+'&nbsp;&nbsp;&nbsp;');
			}
			if(all.opt5!=null){
				trueAnswer = trueAnswer + (all.opt5+'&nbsp;&nbsp;&nbsp;');
			}
			if(all.opt6!=null){
				trueAnswer = trueAnswer + (all.opt6+'&nbsp;&nbsp;&nbsp;');
			}
			*/		
		}else if(type=="5"||type=="6"||type=="7"){
			//5-简答, 6-编程, 7-综合
			stuAnswer = stringChange(all.answer);
			trueAnswer = stringChange(all.true_answer);
		}else{
			//其他
			stuAnswer = stringChange(all.answer);
			trueAnswer = stringChange(all.true_answer);
		}	
		var paper = '<!-- 第N题 -->'+
		'<div class="box-body">'+
			'<div class="row">'+
				'<div class="col-md-9 col-xs-9">'+
					'<div class="row">'+
						'<div class="col-md-8 col-xs-8">'+
							'<h4 class="box-title text-light-blue">'+
							'<strong>'+
								num+'.&nbsp;'+all.type_name+'&nbsp;&nbsp;'+
							'</strong>'+
							'</h4>'+			
						'</div>'+
						'<div class="col-md-2 col-xs-2">'+
							'<h5 class="box-title" style="margin-bottom: 0px;margin-top: 15px;">'+
							'<strong style="color:'+(all.judge_person==null?'#dd4b39':'#3c8dbc')+';" id="'+all.paper_id+'_'+all.question_id+'_flag" >'+
								(all.judge_person==null?correctPaper_84_not_score:correctPaper_84_get_score_51+':&nbsp;'+(all.score*1).toFixed(2)+'&nbsp;'+correctPaper_84_score_38)+ // correctPaper_84_not_score:未打分 // correctPaper_84_get_score_51:得分 // correctPaper_84_score_38:分
							'</strong>'+
							'</h5>'+			
						'</div>'+
						'<div class="col-md-2 col-xs-2">'+
							'<h5 class="box-title text-light-blue" style="margin-bottom: 0px;margin-top: 15px;">'+
							'<strong>'+
								correctPaper_84_en_score+':&nbsp;'+all.all_score+'&nbsp;'+correctPaper_84_score_80+ // correctPaper_84_en_score:满分 // correctPaper_84_score_80:分
							'</strong>'+
							'</h5>'+		
						'</div>'+
					'</div>'+
					'<div class="row">'+
						'<div class="col-md-12 col-xs-12">'+
							'<h5 class="box-title" style="word-wrap:break-word;word-break:break-all;"><strong>'+stringChange(all.content)+'</strong></h5>'+
						'</div>'+
					'</div>'+
					'<div class="row">'+
						'<div class="col-md-12 col-xs-12">'+img(queImgs,queImgsStr,num)+												
						'</div>'+
					'</div>'+
					'<div class="row">'+
						'<div class="col-md-12 col-xs-12">'+
							question2+
						'</div>'+
					'</div>'+
					'<div class="row">'+
						'<div class="box-body" style="padding-bottom: 5px;padding-top: 8px;">'+
							'<blockquote style="margin-left: 5px;padding-top: 0px;padding-bottom: 0px;padding-left: 10px; padding-right: 10px;margin-bottom: 0px;border-left-color:#afc6db;">'+
				                '<h5 style="margin-bottom: 0px;margin-top: 0px;font-size:14px;" class="text-light-blue">'+correctPaper_84_stu_answer+':</h5>'+ // correctPaper_84_stu_answer:学生答案
				                '<h5 style="margin-bottom: 0px;margin-top: 0px;font-size:14px;">'+stuAnswer+'</h5>'+
				            '</blockquote>'+
						'</div>'+
						'<div class="box-body" style="padding-bottom: 5px;padding-top: 8px;">'+
							'<blockquote style="margin-left: 5px;padding-top: 0px;padding-bottom: 0px;padding-left: 10px; padding-right: 10px;margin-bottom: 0px;border-left-color:#36bd9b;">'+
				                '<h5 style="margin-bottom: 0px;margin-top: 0px;font-size:14px;" class="text-light-blue">'+correctPaper_84_true_answer+':</h5>'+ // correctPaper_84_true_answer:参考答案
				                '<h5 style="margin-bottom: 0px;margin-top: 0px;font-size:14px;">'+trueAnswer+'</h5>'+
				             '</blockquote>'+
						'</div>'+
						'<div class="box-body" style="padding-bottom: 5px;padding-top: 8px;">'+
							'<h5 class="box-title" style="margin-top: 0px;margin-bottom: 0px;margin-left: 20px;">'+
								'<small class="text-green">'+correctPaper_84_analysis+':&nbsp;'+stringChange(all.analysis)+'</small>'+ // correctPaper_84_analysis:解析
							'</h5>'+
						'</div>'+
					'</div>'+
				'</div>'+
				'<div class="col-md-3 col-xs-3">'+
					'<div class="box box-success direct-chat direct-chat-danger">'+
						'<div class="box-header with-border">'+
							'<span style="color:#00a65a"><strong>'+correctPaper_84_get_score_83+'</strong></span>'+ // correctPaper_84_get_score_83:打分
							'<div class="box-tools pull-right">'+
								'<button type="button" class="btn btn-box-tool"'+
									'data-widget="collapse" style="padding-top: 8px;">'+
									'<i class="fa fa-minus"></i>'+
								'</button>'+
							'</div>'+
						'</div>'+
						'<!-- /.box-header -->'+
						'<div class="box-body" style="display: block;"></div>'+
						'<!-- /.box-body -->'+
						'<div class="box-footer" style="display: block;">'+
							'<form action="#" method="post">'+
								'<div class="input-group" style="margin-bottom: 10px;">'+
									'<input type="text" id="'+all.paper_id+'_'+all.question_id+'_score" name="message"'+
										'placeholder="'+(all.score==null?correctPaper_84_score_info:all.score)+'" class="form-control">'+ // correctPaper_84_score_info:分数...
									'<span class="input-group-btn">'+
										'<button type="button" class="btn btn-success btn-flat" '+
											'style="border-bottom-width: 3px;border-top-width: 2px; '+
											'border-left-width: 2px;border-right-width: 2px;" '+
											'onclick="score(\''+all.paper_id+'_'+all.question_id+'\',\''+all.question_id+
											'\',\''+all.section_id+'\',\''+all.all_score+'\',\''+all.judge_person+'\')">'+correctPaper_84_send+'</button>'+ // correctPaper_84_send:提交
									'</span>'+
								'</div>'+
								'<div class="input-group" style="margin-bottom: 10px;">'+
									'<input type="text" id="'+all.paper_id+'_'+all.question_id+'_remark" name="message"'+
										'placeholder="'+(all.remark==null?correctPaper_84_desc_info:all.remark)+'" class="form-control">'+ // correctPaper_84_desc_info:说明...
									'<span class="input-group-btn">'+
										'<button type="button" class="btn btn-success btn-flat" '+
											'style="border-bottom-width: 3px;border-top-width: 2px; '+
											'border-left-width: 2px;border-right-width: 2px;" '+
											'onclick="remark(\''+all.paper_id+'_'+all.question_id+'\',\''+all.question_id+'\')">'+correctPaper_84_send_23+'</button>'+ // correctPaper_84_send_23:提交
									'</span>'+
								'</div>'+
							'</form>'+
						'</div>'+
						'<!-- /.box-footer-->'+
					'</div>'+
				'</div>'+
			'</div>'+
			'<div class="row">'+
				'<div class="box-body" style="padding-bottom: 0px;padding-top: 5px;">'+
					'<div style=" border-top:1px dashed #8dadcb;margin-top: 10px;"></div>'+
		        '</div>'+
		    '</div>'+
		'</div>';
		section = section + paper;
		num++;
	});	
	//最后一个题块
	section = '<!-- 题块内容 -->'+
	'<div class="row">'+
		'<div class="col-md-12 col-xs-12">'+
			'<div class="box box-primary">'+
				'<div class="box-header with-border" data-widget="collapse">'+
					'<i class="fa fa-minus"></i>'+
					'<h3 class="box-title">'+
						section_title+
					'</h3>'+
				'</div>'+
				'<!-- /.box-header -->'+
				section+
				'</div></div></div>';
	context = context + section;
	//最后赋值
	document.getElementById(divId).innerHTML=context;
	//计算试卷总分
	getPaperScore();
}
//图片显示
function img(data,str,num){
	if(data==null||data==""){
		return "";
	}
	var contextOl = "";
	var contextImg = "";
	for(var i=0;i<data.length;i++){
		var uuid = data[i];
		var className = "";
		if(i==0){
			className = "active";
		}
		if(uuid!=null&&uuid!=""){
			contextOl = contextOl+
					'<li data-target="#carousel-example-generic_'+num+'" data-slide-to="0" class="'+className+'"></li>';
			contextImg = contextImg+
					'<div class="item '+className+'">'+
						'<img style="width: 160px;height: 90px;" onclick="findImg(\''+str+'\')" '+
							'src="../question/fileAction!getImage.do?fileName='+uuid+'"'+
							'alt="">'+
					'</div>';
		}
	}	
	return '<div id="carousel-example-generic_'+num+'" class="carousel slide"'+
		'data-ride="carousel" style="height: 90px;width: 160px">'+
		'<ol class="carousel-indicators" id="contextOl" style="top: 75px;">'+
			contextOl+
		'</ol>'+
		'<div class="carousel-inner" id="contextImg">'+
			contextImg+
		'</div>'+
		'<a class="left carousel-control" href="#carousel-example-generic_'+num+'"'+
			'data-slide="prev"> <span class="fa fa-angle-left"></span>'+
		'</a> <a class="right carousel-control" href="#carousel-example-generic_'+num+'"'+
			'data-slide="next"> <span class="fa fa-angle-right" style="left: -4px;"></span>'+
		'</a>'+
	'</div>';
}
function optImg(data,num){
	if(data==null||data==""){
		return "";
	}else{
		try{
			data = data[(num-1)];
		}catch(e){
			return "";
		}	
		if(data==null||data==""){
			return "";
		}else{
			return '<div class="row"><img onclick="findImg(\''+data+'\')" style="width: 160px;height: 90px;" '+
			'src="../question/fileAction!getImage.do?fileName='+data+'"'+
			'alt=""></div>';
		}
	}
}
function findImg(id){
	if(id==null||id==""){
		layer.msg('无图片可以展示!');
		return null;
	}
	var index1 = layer.open({
		title: [
			'查看图片',
			'background-color:#3C8DBC; color:#ffffff;'
		],
		offset: '1%',// 距离页面顶距离
		type: 2,
		area: [ '100%' , '70%'],
		fix: false, //不固定
		maxmin: true,
		content: "../question/fileAction!toFindImageSim.do?questionVo.info="+id,
	});
	layer.full(index1);
}

//处理内容
function stringChange(str){
	//处理换行
	if(str==null){
		return str;
	}
	return str.replaceAll("\n","<br>")
				.replaceAll("\t","&nbsp;&nbsp;&nbsp;&nbsp;")
				.replaceAll(" ","&nbsp;");
}

