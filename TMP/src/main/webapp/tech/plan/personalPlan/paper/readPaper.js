//数据
var paper_id;//试卷ID
var user_id;//学生ID
//replaceAll函数
String.prototype.replaceAll = function(s1,s2) { 
	return this.replace(new RegExp(s1,"gm"),s2); 
}
//方法
$(function() {
	//数据
	paper_id = $("#paper_id").val();
	user_id = $("#user_id").val();
	//初始化
	init();
	//工具栏-返回按钮
	$("#btn_go_back").click(function() {
		parent.layer.close(parent.layer.getFrameIndex(window.name));
	});
	//工具栏-刷新按钮
	$("#btn_flash").click(function() {
		init();
	});
})
//初始化
function init(){
	scoreList = new Map();
	//1.初始化载入试卷结构信息
	initQuestionInfo();
}
//初始化载入试卷结构信息
function initQuestionInfo(){
	$.ajax({
		url : "../tech/paperUIAction!getSectionInfo.do",
		data: "answerInfoVo.paper_id="+paper_id+"&answerInfoVo.user_id="+user_id,
		dataType : "json",
		type : "get",
		success : function(data) {
			if (data.success == 'true') {
				if (data.message == '1') {
					//失败
					layer.msg(readPaper_89_getdata_fail); // readPaper_89_getdata_fail:获取数据失败!
				} else if (data.message == '0')  {
					//成功
					initSection('sectionInfo',data.datas.data);
					//初始化试卷内容
					initContext();
				}
			} else {
				layer.msg(readPaper_89_getdata_fail_80); // readPaper_89_getdata_fail_80:获取数据失败!
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
					layer.msg(readPaper_89_getdata_fail_35); // readPaper_89_getdata_fail_35:获取数据失败!
				} else if (data.message == '0')  {
					//成功
					initPaperContext('question',data.datas.data);
				}
			} else {
				layer.msg(readPaper_89_getdata_fail_65); // readPaper_89_getdata_fail_65:获取数据失败!
				console.info(data.message);
			}
		}
	});	
}
//---------------------------------------------------------------------
//UI封装控件-题块信息
function initSection(divId,data){
	var context;
	//头部内容
	context = '<table class="table table-striped"><tbody>';
	//标题行
	context = context + ('<tr><th style="width: 10px">#</th>'+
				'<th>'+readPaper_89_question_section+'</th><th>'+readPaper_89_question_desc+'</th><th>'+ // readPaper_89_question_section:题块 // readPaper_89_question_desc:说明
				readPaper_89_question_number+'</th><th>'+readPaper_89_get_score+'</th><th>'+readPaper_89_all_score+'</th></tr>'); // readPaper_89_question_number:题目数 // readPaper_89_get_score:得分 // readPaper_89_all_score:总分
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
				   '<td><span class="badge bg-green"><span id="'+all.id+'_section_score">'+score+'</span>'+readPaper_89_score+'</span></td>'+ // readPaper_89_score:分
				   '<td><span class="badge bg-green">'+all.question_score_all+readPaper_89_score_67+'</td>'+		   // readPaper_89_score_67:分
				   '</tr>';
		context = context + info;
		num++;
	});
	//尾部
	context = context + '</tbody></table>';
	//赋值
	document.getElementById(divId).innerHTML=context;
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
			var answer1 = (stringChange(all.answer)+"").split("#$#");
			for(var i = 0;i<answer1.length;i++){
				if(answer1[i]!=null&&answer1[i]!=""){
					stuAnswer = stuAnswer + (an[answer1[i]]+"&nbsp;&nbsp;");
				}	
			}
			//标准答案
			var answer2 = (stringChange(all.true_answer)+"").split("#$#");
			for(var i = 0;i<answer2.length;i++){
				if(answer2[i]!=null&&answer2[i]!=""){
					trueAnswer = trueAnswer + (an[answer2[i]]+"&nbsp;&nbsp;");
				}	
			}	
		}else if(type=="3"){
			//3-判断
			stuAnswer = (all.answer=='2'?readPaper_89_error:(all.answer=='1'?readPaper_89_right:"")); // readPaper_89_error:错误 // readPaper_89_right:正确
			trueAnswer = (all.true_answer=='2'?readPaper_89_error_40:readPaper_89_right_85); // readPaper_89_error_40:错误 // readPaper_89_right_85:正确
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
			if(stringChange(all.opt1)!=null){
				trueAnswer = trueAnswer + (all.opt1+'&nbsp;&nbsp;&nbsp;');
			}
			if(stringChange(all.opt2)!=null){
				trueAnswer = trueAnswer + (all.opt2+'&nbsp;&nbsp;&nbsp;');
			}
			if(stringChange(all.opt3)!=null){
				trueAnswer = trueAnswer + (all.opt3+'&nbsp;&nbsp;&nbsp;');
			}
			if(stringChange(all.opt4)!=null){
				trueAnswer = trueAnswer + (all.opt4+'&nbsp;&nbsp;&nbsp;');
			}
			if(stringChange(all.opt5)!=null){
				trueAnswer = trueAnswer + (all.opt5+'&nbsp;&nbsp;&nbsp;');
			}
			if(stringChange(all.opt6)!=null){
				trueAnswer = trueAnswer + (all.opt6+'&nbsp;&nbsp;&nbsp;');
			}		
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
				'<div class="col-md-12 col-xs-12">'+
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
								(all.judge_person==null?readPaper_89_not_score:readPaper_89_get_score_29+':&nbsp;'+(all.score*1).toFixed(2)+'&nbsp;'+readPaper_89_score_49)+ // readPaper_89_not_score:未打分 // readPaper_89_get_score_29:得分 // readPaper_89_score_49:分
							'</strong>'+
							'</h5>'+			
						'</div>'+
						'<div class="col-md-2 col-xs-2">'+
							'<h5 class="box-title text-light-blue" style="margin-bottom: 0px;margin-top: 15px;">'+
							'<strong>'+
								readPaper_89_en_score+':&nbsp;'+all.all_score+'&nbsp;'+readPaper_89_score_58+ // readPaper_89_en_score:满分 // readPaper_89_score_58:分
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
				                '<h5 style="margin-bottom: 0px;margin-top: 0px;font-size:14px;" class="text-light-blue">'+readPaper_89_stu_answer+':</h5>'+ // readPaper_89_stu_answer:学生答案
				                '<h5 style="margin-bottom: 0px;margin-top: 0px;font-size:14px;">'+stuAnswer+'</h5>'+
				            '</blockquote>'+
						'</div>'+
						'<div class="box-body" style="padding-bottom: 5px;padding-top: 8px;">'+
							'<blockquote style="margin-left: 5px;padding-top: 0px;padding-bottom: 0px;padding-left: 10px; padding-right: 10px;margin-bottom: 0px;border-left-color:#36bd9b;">'+
				                '<h5 style="margin-bottom: 0px;margin-top: 0px;font-size:14px;" class="text-light-blue">'+readPaper_89_true_answer+':</h5>'+ // readPaper_89_true_answer:参考答案
				                '<h5 style="margin-bottom: 0px;margin-top: 0px;font-size:14px;">'+trueAnswer+'</h5>'+
				             '</blockquote>'+
						'</div>'+
						'<div class="box-body" style="padding-bottom: 5px;padding-top: 8px;">'+
							'<h5 class="box-title" style="margin-top: 0px;margin-bottom: 0px;margin-left: 20px;">'+
								'<small class="text-green">'+readPaper_89_analysis+':&nbsp;'+(all.analysis==null?"":stringChange(all.analysis))+'</small>'+ // readPaper_89_analysis:解析
							'</h5>'+
						'</div>'+
						'<div class="box-body" style="padding-bottom: 5px;padding-top: 8px;">'+
							'<h5 class="box-title" style="margin-top: 0px;margin-bottom: 0px;margin-left: 20px;">'+
								'<small class="text-yellow">'+readPaper_89_remark+':&nbsp;'+(all.remark==null?"":stringChange(all.remark))+'</small>'+ // readPaper_89_remark:批改备注
							'</h5>'+
						'</div>'+
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
