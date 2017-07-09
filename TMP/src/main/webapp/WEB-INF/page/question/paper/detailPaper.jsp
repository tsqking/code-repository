<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/question/paper/${system_lang}.js"></script>
<script
	src="${pageContext.request.contextPath}/common/js/jQuery-2.1.4.min.js"></script>
<script
	src="${pageContext.request.contextPath}/common/js/common.js"></script>
<title>试卷详情</title>
<style type="text/css">
	input {
		border-top:none;
		border-left:none;
		border-right:none;
		border-bottom:2px solid #404040;
		text-align:center;
		width:120px;
		HEIGHT:24px;
	}
	input:focus {
	    outline:none;
	}
	/**************************top*************************/
	body {
		text-align:center;/*body中的内容居中*/
		width:93%;
		margin:0 auto;
	}
	div#head {
		font-size:16px;
	}
	div.top {
		height:36px;
	}
	label {
		float:left;
		text-align:left;
	}
	#head span {
		color:#f00;
		font-size:20px;
		font-weight:700;
	}
	/************************content**********************/
	div.content {
		float:left;
		margin-top:10px;
	}
	h3 {
		font-size:18px;
	}
	div.section {
		text-align:left;
	}
	span.section_description {
		font-size:18px;
		font-weight:700;
	}
	span.total_point {
		font-size:14px;
	}
	div.question {
		margin-top:14px;
		text-align:left;
		font-size:14px;
	}
	span.title {
		font-size:16px;
		font-family:"微软雅黑";
		font-weight:600;
	}
	li {
		height:28px;
		list-style-type:none;
	}
	li input.answer {
		float:left;
		margin:0;
		width:16px;
		height:20px;
	}
	li label {
		float:left;
	}
</style>
</head>
<body>
	<div id="head">
		<div style="margin:0 auto;">
			<input type="hidden" id="paper_id" value="${paperVo.id }"/>
			<h1 id="paperName">${paperVo.name }</h1>
		</div>
		<div class="top" style="float:left;width:100%;text-align:left;">
          <%-- <label><s:text name="instruction"/>:&nbsp;${paperVo.instruction }</label> --%>
          
          <s:text name="instruction"/>:&nbsp;
          <textarea rows="5" style="width:100%;" >${paperVo.instruction }</textarea>
        </div>
        <br><br><br><br><br><br><br><br>
        <div class="top" style="float:left;width:100%;">
          <label style="width:33%;"><s:text name="total_time"/>&nbsp;<span id="total_time"></span>&nbsp;<s:text name="minute"/></label>
          <label style="width:33%;"><s:text name="total_point"/>&nbsp;<span id="total_point"></span>&nbsp;<s:text name="point"/></label>
          <label style="width:33%;"><s:text name="total_item"/>&nbsp;<span id="total_item"></span>&nbsp;<s:text name="number"/></label>
        </div>
	</div>
	<div id="content">
		<!-- 题目信息 -->
	</div>
</body>
<script type="text/javascript">
	//replaceAll函数
	String.prototype.replaceAll = function(s1,s2) { 
		return this.replace(new RegExp(s1,"gm"),s2); 
	}
	$(function(){
		var paper_id = $("#paper_id").val();
		$.ajax({
			url:"../question/paper!detailPaper.do",
			data:{"paperVo.id":paper_id},
			dataType:"json",
			type:"post",
			success:function(data){
				//试卷信息（试卷总分，试卷总题数，考试时长）
				var total_time = data.datas.total_time;
				if(total_time<0)
					$("#total_time").text(no_limit);
				else
					$("#total_time").text(total_time);
					
				var total_point = data.datas.total_point;
				$("#total_point").text(total_point);
				var total_item = data.datas.total_item;
				$("#total_item").text(total_item);
				
				//迭代
				var questionList = data.datas.questionList;
				var totalPoint = 0;
				var current_section = -1;
				for(var i in questionList){//list
					//获取图片
					var question_id = questionList[i].question_id;
					var queImgs;
					var queImgsStr;
					var optImgs;
					$.ajax({
						url : "../question/question!getImageList.do?questionVo.id="+question_id,
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
									 console.log('通讯失败');
									 return null;
								 }
							 }else{
								 console.log(data.message);
								 return null;
							 }
						}
					});
					for(var j in questionList[i]){//map	
							//其他
							if(current_section != questionList[i]["section_order"]){
								totalPoint = 0;
								current_section = questionList[i]["section_order"];
								totalPoint = data.datas.section_arr[questionList[i]["section_order"]];
								//是否含有部分名称
								$("#content").append('<div id="section'+questionList[i]["section_order"]+'">'+
											        	'<div class="section"><span class="section_description">'+questionList[i]["section_name"]+'</span><span class="total_point">('+totalPoint+'分)</span></div>'+
											   		'</div>');
							}
							
							if(j=="question_type" && questionList[i][j]==1){//单选题
								$("#section"+questionList[i]["section_order"]+"").append('<div class="question">'+
												   			'<span class="title">('+questionList[i]["question_score"]+'分)</span>'+
												   			'<span class="title">'+stringChange(questionList[i]["content"])+'</span>'+
												   			img(queImgs)+
												   			'<div class="ul">'+
												   				'<ul id="mutiple'+i+'">'+
													   				'<div>'+ifNull(1,stringChange(questionList[i]["opt1"]),i)+
													   				'</div>'+opt(questionList[i]["opt1"],1,optImgs)+
													   				'<div>'+ifNull(1,stringChange(questionList[i]["opt2"]),i)+
													   				'</div>'+opt(questionList[i]["opt2"],2,optImgs)+
													   				'<div>'+ifNull(1,stringChange(questionList[i]["opt3"]),i)+
													   				'</div>'+opt(questionList[i]["opt3"],3,optImgs)+
													   				'<div>'+ifNull(1,stringChange(questionList[i]["opt4"]),i)+
													   				'</div>'+opt(questionList[i]["opt4"],4,optImgs)+
													   				'<div>'+ifNull(1,stringChange(questionList[i]["opt5"]),i)+
													   				'</div>'+opt(questionList[i]["opt5"],5,optImgs)+
													   				'<div>'+ifNull(1,stringChange(questionList[i]["opt6"]),i)+
													   				'</div>'+opt(questionList[i]["opt6"],6,optImgs)+
													   			'</ul>'+
												   			'</div>'+
												   		'</div>');
								var answer = questionList[i]["answer"];
								$("ul#mutiple"+i+"").find("li:eq("+(answer-1)+")").find("input").attr("checked","checked");
								$("ul#mutiple"+i+"").find("li:eq("+(answer-1)+")").css("color","red");
							}
							if(j=="question_type" && questionList[i][j]==2){//多选题
								$("#section"+questionList[i]["section_order"]+"").append('<div class="question">'+
											   			'<span class="title">('+questionList[i]["question_score"]+'分)</span>'+
											   			'<span class="title">'+stringChange(questionList[i]["content"])+'</span>'+
											   			img(queImgs)+
											   			'<div class="ul">'+
												   			'<ul id="mutiple'+i+'">'+
													   			'<div>'+ifNull(2,stringChange(questionList[i]["opt1"]),i)+
													   			'</div>'+opt(questionList[i]["opt1"],1,optImgs)+
													   			'<div>'+ifNull(2,stringChange(questionList[i]["opt2"]),i)+
													   			'</div>'+opt(questionList[i]["opt2"],2,optImgs)+
													   			'<div>'+ifNull(2,stringChange(questionList[i]["opt3"]),i)+
													   			'</div>'+opt(questionList[i]["opt3"],3,optImgs)+
													   			'<div>'+ifNull(2,stringChange(questionList[i]["opt4"]),i)+
													   			'</div>'+opt(questionList[i]["opt4"],4,optImgs)+
													   			'<div>'+ifNull(2,stringChange(questionList[i]["opt5"]),i)+
													   			'</div>'+opt(questionList[i]["opt5"],5,optImgs)+
													   			'<div>'+ifNull(2,stringChange(questionList[i]["opt6"]),i)+
													   			'</div>'+opt(questionList[i]["opt6"],6,optImgs)+
												   			'</ul>'+
											   			'</div>'+
											   		'</div>');
								var answer = questionList[i]["answer"];
								answer = answer.substr(0,answer.length-3);
								var answers = answer.split("#$#");
								for(var k=0; k<answers.length; k++){
									$("ul#mutiple"+i+"").find("li:eq("+(answers[k]-1)+")").find("input").attr("checked","checked");
									$("ul#mutiple"+i+"").find("li:eq("+(answers[k]-1)+")").css("color","red");
								}
							}
							if(j=="question_type" && questionList[i][j]==3){//判断题
								var answer = questionList[i]["answer"];
								$("#section"+questionList[i]["section_order"]+"").append('<div class="question">'+
											   			'<span class="title">('+questionList[i]["question_score"]+'分)</span>'+
											   			'<span class="title">'+stringChange(questionList[i]["content"])+'</span>'+
											   			img(queImgs)+
											   			'<span style="color:red;">&nbsp;&nbsp;'+questionList[i]["opt"+answer+""]+'</span>'+
											   		'</div>');
							}
							if(j=="question_type" && questionList[i][j]==4){//填空题
								var answer = stringChange(questionList[i]["answer"]);
								var answers = answer.split("#$#");
								$("#section"+questionList[i]["section_order"]+"").append('<div class="question">'+
											   			'<span class="title">('+questionList[i]["question_score"]+'分)</span>'+
											   			'<span class="title">'+stringChange(questionList[i]["content"])+'</span>'+
											   			img(queImgs)+
											   			'<div class="ul">'+
												   			'<ul style="color:red;">'+
												   				getFillBlankAnswer(answers)+
												   			'</ul>'+
											   			'</div>'+
											   		'</div>');
							}
							if(j=="question_type" && (questionList[i][j]==5 || questionList[i][j]==6)){//简答题和编程题
								$("#section"+questionList[i]["section_order"]+"").append('<div class="question">'+
											   			'<span class="title">('+questionList[i]["question_score"]+'分)</span>'+
											   			'<span class="title">'+stringChange(questionList[i]["content"])+'</span>'+
											   			img(queImgs)+
											   		'</div>');
							}
						}
					
					}
				}
		});
	})
	
	function ifNull(type,obj,i){
		if(type==1)//单选
			return (obj==null || obj=='')?'':('<li><input type="radio" name="answer'+i+'" class="answer"/><label>&nbsp;'+obj+'</label></li>');
		if(type==2)//多选
			return (obj==null || obj=='')?'':('<li><input type="checkbox" name="answer" class="answer"/><label>&nbsp;'+obj+'</label></li>');
	}
	
	function getFillBlankAnswer(answers){
		var htmlText="";
		for(var k=0; k<answers.length; k++){
			htmlText+=('<li style="display:inline;">&nbsp;'+answers[k]+'</li>');
		}
		return htmlText;
	}
	
	//试题图片显示
	function img(data){
		if(data==null||data==""){
			return "";
		}
		var context = "";
		for(var i=0;i<data.length;i++){
			var uuid = data[i];
			context = context+
   				'<img style="width: 240px;height: 135px;margin-right: 15px;" src="../question/fileAction!getImage.do?fileName='+uuid+'" alt="">';
		}	
		return '<div style="margin-top: 5px;margin-bottom: 0px;margin-left: 2px;margin-right: 2px;" >'+context+'</div>';
	}
	//选项图片显示
	function opt(obj,num,data){
		if(obj==null || obj==''){
			return "";
		}else{
			if(data==null||data==""){
				return "";
			}
			return '<br><img style="width: 240px;height: 135px;margin-bottom: 10px;" '+
				'src="../question/fileAction!getImage.do?fileName='+data[(num-1)]+'" alt="">';
		}
	}
	
	//特殊字符
	function stringChange(str){
		//处理换行
		if(str==null){
			return str;
		}
		return str.replaceAll("\n","<br>")
					.replaceAll("\t","&nbsp;&nbsp;&nbsp;&nbsp;")
					.replaceAll(" ","&nbsp;");
		/*
		str.replaceAll('&','&amp;')
					.replaceAll('"','&quot;')
					.replaceAll("'",'&apos;')
					.replaceAll('<','&lt;')
					.replaceAll('>','&gt;')
					.replaceAll("\n","<br>")
				  	.replaceAll("\t","&nbsp;&nbsp;&nbsp;&nbsp;")
				  	.replaceAll(" ","&nbsp;");
		*/
	}
	
</script>
</html>
