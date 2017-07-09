<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="../common.jsp"></s:include>
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/onlineTest/${system_lang}.js"></script>
<title><s:text name="onlineTest"></s:text></title>
<style type="text/css">
input {
	border-top: none;
	border-left: none;
	border-right: none;
	border-bottom: 2px solid #404040;
	text-align: center;
	width: 120px;
	HEIGHT: 24px;
}

input:focus {
	outline: none;
}
/**************************top*************************/
/* body { */
/* 	text-align: center; /*body中的内容居中*/ */
/* 	width: 93%; */
/* 	margin: 0 auto; */
/* } */

div#head {
	font-size: 16px;
}

div.top {
	height: 36px;
}

label {
	float: left;
	text-align: left;
}

#head span {
	color: #f00;
	font-size: 20px;
	font-weight: 700;
}
/************************content**********************/
div.content {
	float: left;
	margin-top: 10px;
}

h3 {
	font-size: 18px;
}

div.section {
	text-align: left;
}

span.section_description {
	font-size: 18px;
	font-weight: 700;
}

span.total_point {
	font-size: 14px;
}

div.question {
	text-align: left;
	margin-left: 14px;
	font:bold 15px/1.5em "Microsoft YaHei";"
}

span.title {
	font-size: 16px;
	font-family: "微软雅黑";
	font-weight: 600;
}

li {
	height: 28px;
	list-style-type: none;
}

li input.answer {
	float: left;
	margin: 0;
	width: 16px;
	height: 20px;
}

li label {
	float: left;
}
.divBorder{ border:0 solid #000 } 
.divHeight{height:400px;height:auto;min-height:500px;} 
.divcss5{position:relative;} 
/* .divcss5-1,.divcss5-2,.divcss5-3  */
/* {position:absolute;}  */
.divcss5-1{z-index:20;position:absolute} 
.divcss5-2{z-index:10;top:10px;width:90%;left:410px} 
.divcss5-3{z-index:15;top:400px;width:90%} 
.ss{color:grey}
.skin-blue .main-header .navbar .nav>li>a:hover, .skin-blue .main-header .navbar .nav>li>a:active, .skin-blue .main-header .navbar .nav>li>a:focus, .skin-blue .main-header .navbar .nav .open>a, .skin-blue .main-header .navbar .nav .open>a:hover, .skin-blue .main-header .navbar .nav .open>a:focus, .skin-blue .main-header .navbar .nav>.active>a {
	    background:rgb(60, 141, 188);
	    color: #f6f6f6;
	}
.skin-blue .wrapper, .skin-blue .main-sidebar, .skin-blue .left-side {
    background-color: #E9EEF0;
}
.layui-layer-hui{min-width:100px;background-color:#000;filter:alpha(opacity=60);background-color:#B0C4DE;
</style>
</head>

<body class="hold-transition skin-blue layout-top-nav"   onblur="exam()"  onfocus="backExam()"  oncontextmenu="return false;" onclick="fullScreen()"  ><!-- onbeforeunload="checkLeave()"  -->
	<div id="loading-mask" style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #D2E0F2; z-index: 20000; display:none;">
		<div id="pageloading" style="position: absolute; top: 50%; left: 50%; margin: -120px 0px 0px -120px; text-align: center; width: 200px; height: 40px; font-size: 14px; padding: 10px; font-weight: bold; color: #15428B;">
			<img src="${pageContext.request.contextPath}/common/image/loading.gif" align="middle" />
		</div>
	</div>
	<div class="wrapper" style="height:100%;width:100%">
		<header class="main-header">
			<nav class="navbar navbar-static-top" style="width:100%">
				<div class="container" style="width:100%">
					<div class="navbar-header" style="margin-top: 0px;">
						<!-- <img src="/TMP/common/image/clps.png" height="26px" width="120px" style="margin-top:10px;"> -->
						<img src="/TMP/common/image/Financial IT talent_logo.png" width="50" height="50" style="margin-top: 0px;" />
		        	</div>
		        	<div class="collapse navbar-collapse pull-left" id="navbar-collapse">
						<ul class="nav navbar-nav">
							<!-- <li><a href="javascript:void(0)">TMP在线考试系统</a></li> -->
							<li><a href="javascript:void(0)"><s:text name="Financial System" /></a></li>
						</ul>
					</div>
				</div>
			</nav>
		</header>
		<div class="content-wrapper " style="height:100%;height:auto">
			<div class="container" style="width:100%">
				<!-- 题板 -->
				<div class="panel-group divcss5-1 " id="accordion" style="margin-left:100px;float:left;top:70px" id="accordion" >
					<div class="panel panel-info ">
						<div class="panel-heading " style="background:#3C8DBC">
							<h4 class="panel-title" >
								<a data-toggle="collapse" data-parent="#accordion" 
									href="/demo/bootstrap3-plugin-collapse-event.htm#collapseexample">
									<font color=white>
										<s:text name="questionBoard" />
									</font>
								</a>
							</h4>
						</div>
						<div id="collapseexample" class="panel-collapse collapse ">
							<div class="panel-body " style="width:400px">
								<div id="qustorder"></div>
							</div>
						</div>
					</div>
				</div>
				<label style="float:right;font-size: 22px;margin-top:22px;margin-right:100px">
				<s:text name="timeLeft" /><span   id="timer" style="color: red; width:30%;"></span> 
				</label>
				<!-- 题目信息-->
				<div id="content"  style="margin-top:8%;height:100%;width:85%;margin-left:80px">
				</div>
			</div>
			
			<div style="margin-left:80px">
				<div  id="lastQust"  class="col-md-4 col-xs-4" style="visibility : hidden;margin-top:5%;margin-left:6%;width:20%">
					<button type="button" id="lastButton" name="lastButton"
						class="btn btn-block btn-primary btn-sm">
						<i class="fa fa-reply"></i>&nbsp;&nbsp;&nbsp;
						<s:text name="lastQuestion" />
					</button>
				</div>
				<div id="markDiv" class="col-md-4 col-xs-4" style="display: block;margin-top:5%;margin-left:6%;width:20%">
					<!-- 		style="visibility: hidden;" -->
					<button type="button" id="markButton"
						class="btn btn-block btn-primary btn-sm">
						<i class="fa  fa-dot-circle-o"></i>&nbsp;&nbsp;&nbsp;
						<s:text name="mark" />
					</button>
				</div>
				<div id="cancelMarkDiv" class="col-md-4 col-xs-4" style="display:none;margin-top:5%;margin-left:6%;width:20%" >
					<button type="button" id="cancelMark"
						class="btn btn-block btn-primary btn-sm">
						<i class="fa  fa-circle-o"></i>&nbsp;&nbsp;&nbsp;
						<s:text name="cancelMark" />
					</button>
				</div>
				<div id="nextQust" class="col-md-4 col-xs-4" style="display: block;margin-top:5%;margin-left:6%;width:20%">
					<button type="button" id="nextButton"
						class="btn btn-block btn-primary btn-sm">
						<i class="fa fa-share"></i>&nbsp;&nbsp;&nbsp;
						<s:text name="nextQuestion" />
					</button>
				</div>
				<div id="finish" class="col-md-4 col-xs-4" style="margin-top:5%;margin-left:6%;width:20%">
					<button type="button" id="submit"
						class="btn btn-block btn-primary btn-sm">
						<i class="fa fa-check-circle"></i>&nbsp;&nbsp;&nbsp;
						<s:text name="submitPaper" />
					</button>
				</div>
				<div class="col-md-4 col-xs-4" style="display: none;margin-top:5%;width:20%">
					<button type="button" id="abv"
						class="btn btn-block btn-primary btn-sm">
						<i class="fa fa-user-plus"></i>&nbsp;&nbsp;&nbsp;
						<s:text name="selected" />
					</button>
				</div>
				<div class="col-md-4 col-xs-4" style="display: none;margin-top:5%;width:30%">
					<button type="button" id="toQust"
						class="btn btn-block btn-primary btn-sm">
						<i class="fa fa-user-plus"></i>&nbsp;&nbsp;&nbsp;
						<s:text name="jumpToQuestion" />
					</button>
				</div>
			
<!-- 				<button type="button" id="fullScreen" style="display:none" -->
<!-- 						class="btn btn-block btn-primary btn-sm"> -->
<!-- 						<i class="fa  fa-dot-circle-o"></i>&nbsp;&nbsp;&nbsp; -->
<!-- 				</button> -->
			</div>
		</div>
		<div style="display: none;">
				<input type="hidden" id="order" /> 
				<input type="hidden"id="questionId" /> 
				<input type="hidden" id="count" />
				<input type="hidden" id="qustType" />
				<input type="hidden" id="paperId" value='${paperId}' />
				<input type="hidden" id="leavetotal" value='${leaveLimitTotal}' />
				<input type="hidden" id="totalTime" value='${totalTime}' />
				<input type="hidden" id="totalItem" value='${totalItem}' />
				<input type="hidden" id="startTime" value='${startTime}' />
				<input type="hidden" id="endTime" value='${endTime}' />
				<input type="hidden" id="property" value='${property}' />
				<input type="hidden" id="planPaperId" value='${planPaperId}' />
				<input type="hidden" id="planId" value="${planId }" />
				<input type="hidden" id="paperNo" value="${paperNo }" />
				<!-- 选项答案 -->
				<input type="hidden" id="selectItems" name="selectItems" value="" />
		</div>
	</div>
	<footer class="main-footer" style="z-index:20;">
		<div class="container">
			<div class="pull-right hidden-xs">
				<b>Version</b> 1.0.0
			</div>
			<strong>Copyright &copy; 2016-2018 <a href="javascript:void(0);">FU</a>.</strong> All rights reserved.
		</div>
	</footer>
		
	<!-- modal -->
	<div class="modal" id="imgSimModel" >
		<form id="imgSimForm" style="width: 100%;height: 85%;">
			 <div class="modal-dialog" style=" width: 100%;height:90%;">
				<div class="modal-content" style="width: 80%;height: 90%;background-color:#ECF0F5;margin-left:8%;margin-top:7%">
					<div class="modal-header">
						<label><s:text name="exitPage"/></label>
					</div>
					<div class="modal-body" id="contextImgg" style="height:100%">
						
					</div>
					<%-- <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="height:90%">
						<ol class="carousel-indicators" id="contextOll"> </ol>
						<div class="carousel-inner" id="contextImgg" style="height:70%"></div>
						<a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
							<span class="fa fa-angle-left"></span>
						</a> 
						<a class="right carousel-control" href="#carousel-example-generic" style="margin-left:16%" data-slide="next"> 
							<span class="fa fa-angle-right" ></span>
						</a>
					</div> --%>
				</div>
      		</div> 
		</form>
	</div> 
</body>
<script type="text/javascript">
	document.onkeydown = function(e) {
		var event = e || window.event;
		var code = event.keyCode || event.which || event.charCode;
		//
		var obj = event.target || event.srcElement;//获取事件源 

		var t = obj.type || obj.getAttribute('type');//获取事件源类型 

		//获取作为判断条件的事件类型 
		var vReadOnly = obj.getAttribute('readonly'); 
		var vEnabled = obj.getAttribute('enabled'); 
		//处理null值情况 
		vReadOnly = (vReadOnly == null) ? false : vReadOnly; 
		vEnabled = (vEnabled == null) ? true : vEnabled; 

		//当敲Backspace键时，事件源类型为密码或单行、多行文本的， 
		//并且readonly属性为true或enabled属性为false的，则退格键失效 
		var flag1=(event.keyCode == 8 && (t=="password" || t=="text" || t=="textarea") 
		&& (vReadOnly==true || vEnabled!=true))?true:false; 

		//当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效 
		var flag2=(event.keyCode == 8 && t != "password" && t != "text" && t != "textarea") 
		?true:false; 

		//判断 
		if(flag2){ 
		return false; 
		} 
		if(flag1){ 
		return false; 
		} 
		//
		
		console.info("按键："+code);
		if(event.keyCode==27)   
		{   
// 			event.keyCode=0;   
			event.returnValue=false;   
		}   
		if(code == 122){//F11
			return false;
		}
// 		 if(code == 91){//WINDOW
// 			 return false;
// // 			event.keyCode=0;
// // 			event.returnValue=false;
// 		} 
		if ( event.ctrlKey && code==83 ){ //Ctrl + s      
			code=0;      
			event.returnValue=false;      
		}
		if ( event.ctrlKey && code==70 ){ //Ctrl + f  
			code=0;      
			event.returnValue=false;      
		}
// 		if ( event.ctrlKey && code==84 ){ //Ctrl + t  
// 			alert("Ctrl + t");
// 			code=0;      
// 			event.returnValue=false;      
// 		}
		if ( event.ctrlKey && code==72 ){ //Ctrl + h      
			code=0;      
			event.returnValue=false;      
		}
// 		if ( event.ctrlKey && code==87 ){ //Ctrl + w      
// 			code=0;      
// 			event.returnValue=false;      
// 		}
		if ( event.ctrlKey && code==68 ){ //Ctrl + d      
			code=0;      
			event.returnValue=false;      
		}
		if ( event.ctrlKey && code==80 ){ //Ctrl + p      
			code=0;      
			event.returnValue=false;      
		}
		if ( event.ctrlKey && code==84 ){ //Ctrl + T     
			code=0;      
			event.returnValue=false;      
		}
		if ( event.ctrlKey && code==78 ){ //Ctrl + N     
			code=0;      
			event.returnValue=false;      
		}
		if (event.ctrlKey&&(window.event.altKey)&&((code==46))){  //屏蔽 Ctrl+Alt+Del     
				event.returnValue=false;
			}
// 		if ((window.event.altKey)&& ((code==37)||   //屏蔽 Alt+ 方向键 ←      
// 			(window.event.keyCode==39))){  //屏蔽 Alt+ 方向键 →      
// 			event.returnValue=false;
// 		}
		if ((event.keyCode==9)  ||	//屏蔽Tab按键
			(event.keyCode==116)||   //屏蔽 F5 刷新键      
			(event.keyCode==117)||   //屏蔽 F6 刷新键  
			(event.keyCode==112)||   //屏蔽 F1 刷新键      
			(event.keyCode==114)||	//屏蔽F3按键
			(event.keyCode==119)||	//屏蔽F8按键
 			(event.keyCode==123)||	//屏蔽F12按键
			(event.ctrlKey && event.keyCode==82)){ //Ctrl + R      
			event.keyCode=0;
			event.returnValue=false;
		}
		if ( event.altKey && event.keyCode==115 ){ //屏蔽Alt+F4  
			window.showModelessDialog("about:blank","","dialogWidth:1px;dialogheight:1px");     
			return false;
		}
	};
	
	//触发全屏事件
	function fullScreen(){
		var docElm = document.documentElement;
		
		//W3C  
		if (docElm.requestFullscreen) {  
		  docElm.requestFullscreen();  
		}
		//FireFox  
		else if (docElm.mozRequestFullScreen) {  
		  docElm.mozRequestFullScreen();  
		}
		//Chrome等  
		else if (docElm.webkitRequestFullScreen) {  
		  docElm.webkitRequestFullScreen(); 
		}
		//IE11
		else if (elem.msRequestFullscreen) {
		elem.msRequestFullscreen();
		}
	}
	


	var totalTime = document.getElementById('totalTime').value;
	
	function CountDown(){
		if(seconds>0){
			var s = (seconds%60) < 10 ? ('0' + seconds%60) : seconds%60;
			var h = seconds/3600 < 10 ? ('0' + parseInt(seconds/3600)) : parseInt(seconds/3600);
			var m = (seconds-h*3600)/60 < 10 ? ('0' + parseInt((seconds-h*3600)/60)) : parseInt((seconds-h*3600)/60);
			msg = h + hour +' '+ m + minute +' '+ s +second; //剩余时间：时分秒 
			document.all["timer"].innerHTML=msg;  
			if(seconds == 5*60) 
				layer.msg(countDownPrompt); //'注意，还有5分钟!';
			if(seconds <= 3) 
				layer.msg(seconds);  
			--seconds;
		}else{//("时间到，结束!"); 
			clearInterval(timer);  
			forceSubmit();
		}  
		 
	}
	var timer;
	
	var currentPage = 1;//当前题目序号
	var current="1";//判断题目是否已答
	var leaveTotal=document.getElementById('leavetotal').value;
	var leavetime=1;	
	var paperId=document.getElementById('paperId').value;
	var seconds=0;
	var property=document.getElementById('property').value;
	var planPaperId=document.getElementById('planPaperId').value;
	var planId=document.getElementById('planId').value;
	var paperNo=document.getElementById('paperNo').value;

	$(function(){
 		$.ajax({
 			url : "../onlineTest/online!allowExam.do?paperId=" + paperId + "&totalTime=" + totalTime + "&paperNo=" + paperNo + "&planId=" + planId,
 			async : false,
 			dataType : "json",
 			type : "post",
 			success : function(data) {
 				if(data.success){
 					if (data.datas.flag) {
 						layer.msg(paperSubmited);//试卷已经提交
 						window.location.href = "../onlineTest/online!toWelcomeTestPage.do?paperId=" + paperId + "&paperNo=" + paperNo + "&planId=" + planId;
 					} else {
						document.getElementById("content").innerHTML = "";
						var startTime=document.getElementById('startTime').value;
						var endTime=document.getElementById('endTime').value;
						//同步获取图片
						var queImgs;
						var queImgsStr;
						var optImgs;
						$.ajax({
							url : "../onlineTest/online!findSubject.do?paperId=" + paperId + "&paperNo=" + paperNo + "&planId=" + planId + "&currentPage=" + currentPage
								+ "&totalTime=" + totalTime + "&startTime=" + startTime + "&endTime=" + endTime,
							async : false,
							dataType : "json",
							type : "post",
							success : function(data) {
								var count = data.datas.count;
								currentPage=data.datas.qustPage;
								//考试剩余时间
								seconds = data.datas.seconds;
								document.getElementById('count').value = count;
								timer= setInterval("CountDown()",1000);
								if (data.success) {
									if (data.datas.flag == "0") {
										layer.msg(paperMaintain,{shift : 6});//试卷正在维护
									} else {//题板展示
										$("#qustorder").append('<ul id="" class="blank">'+ qustorder(count)+ '</ul>');
										// 页面加载时题板标记的显示//
										var markList = data.datas.markList;
										if(markList!=null && markList.length!=0){
											for(var m in markList){
												var qustnbr=markList[m];
												inimark(qustnbr);
											}
										}
										//页面加载时题板已答题目的显示
										var qfFlag=data.datas.qfFlag;
										if(qfFlag!=null && qfFlag.length!=0){
											for(var q in qfFlag){
												var qfnbr=qfFlag[q];
												iniQustFinish(qfnbr);
											}
										}
										//如果试卷属性为2，即练习卷，则显示和标记按钮（1是测试，2是练习）
										if(property==2){
											document.getElementById('lastQust').style.visibility = 'visible';
											document.getElementById('markDiv').style.display = 'block';
// 											document.getElementById('nextQust').style.left = "30%";
// 											document.getElementById('finish').style.left = "10%";
										}
										//题目展示
										fillTable(data, count);
									}
								}else{
									layer.alert(systemError, {"title":feedback});//系统出错 --反馈
									console.info(data.message);
								}
							}
						});
 					}
 				}
 				else{
 					layer.alert(systemError, {"title":feedback});
 					console.info(data.message);
 				}
 			}
 		});
	});
	//题目上下翻页
	$("#toQust").click(function() {
		document.getElementById("content").innerHTML = "";
		document.getElementById("selectItems").value = "";
		document.getElementById("imgSimModel").value = "";
		var qustId = document.getElementById('questionId').value;
		$.ajax({
			url : "../onlineTest/online!findSubject2.do?paperId=" + paperId + "&paperNo=" + paperNo + "&planId=" + planId
				+ "&currentPage=" + currentPage + "&totalTime=" + totalTime,
			dataType : "json",
			type : "post",
			success : function(data) {
				if (data.success) {
					var count = data.datas.count;
					if (data.datas.flag == "0") {
						layer.msg(paperMaintain, {shift : 6});//"您好，试卷正在维护，暂时无法使用"
					} else {
						console.info(data);
						fillTable(data, count);
					}
				}else{
					layer.alert(systemError, {"title":feedback});//系统出错 --反馈
					console.info(data.message);
				}
			}
		});
	})

	//取出题目信息数据，页面展示
	function fillTable(data, count) {
		 queImgs=data.datas.que;
		 queImgsStr=data.datas.queStr;
		 optImgs=data.datas.opt;
		 var questionList = data.datas.question;
		 for ( var i in questionList) {//List
			var order = parseInt(i) + 1;
			document.getElementById("order").value = order;
			for ( var key in questionList[i]) {//Map
				var a = questionList[i][key];
				if (a.hasOwnProperty('answer')) {
					var strs = new Array();
					strs = a.answer.split('#$#');//填空题需要根据answer判断空个数
				}
				document.getElementById('questionId').value = a.id;
				if (a != null) {
					var b = a.type;
					document.getElementById("qustType").value = b;
					var answer="";
					if (b == "1") {//单选题
						$("#content").append(	'<div id='+'"div'+currentPage+'"'
												+ 'class="box box-info" style="height:90%;border:1px solid grey;border-radius:30px;">'
												+ '<div  class="question title" >'
												+'<span  style="float:left;padding-top: 1px;font: 12px/1.5em "Microsoft YaHei";">'
												+ currentPage
												+ "、"
												+ '</span>'
												+ '<textarea  id="textcontent"  autoHeight="true"  style=\"background:transparent;resize:none;outline:none;border-style:none;width:95%;font: 12px/1.5em "Microsoft YaHei";overflow-y:hidden\ " readonly>'
												+ a.content
												+ '</textarea>'
												+'<div style="margin-left:40px">'
												+img(queImgs,queImgsStr)
												+'</div>'
												+'<div class="ul" style="margin:auto;overflow-y:scroll;overflow: hidden;">'
												+ '<ul id="mutiple'+i+'"  >'
												+ ifNull(1, a.opt1,1)
												+ ifNull(1, a.opt2,2)
												+ ifNull(1, a.opt3,3)
												+ ifNull(1, a.opt4,4)
												+ ifNull(1, a.opt5,5)
												+ ifNull(1, a.opt6,6)
												+ '</ul>'
												+ '</div>' + '</div>' + '</div>'	);
						var textcontent=document.getElementById("textcontent");
				        autoTextarea(textcontent);// 调用
						answer = data.datas.thisAnswer;
						if (answer != null && answer != "") {
							console.info(answer);
							$("ul#mutiple" + i + "").find("li:eq(" + (answer - 1) + ")").find("input");
							$("ul#mutiple" + i + "").find("li:eq(" + (answer - 1) + ")").find("input").attr("checked", "checked");
							$("ul#mutiple" + i + "").find("li:eq(" + (answer - 1) + ")").css("color","red");
						}
					}

					if (b == "2") {//多选题
						$("#content")
								.append(
										'<div id='+'"div'+currentPage+'" class="box box-info" style="height:90%;border:1px solid grey;border-radius:30px;">'
										+ '<div  class="question title">'
										+'<span style="float:left;padding-top: 1px;font: 12px/1.5em "Microsoft YaHei";">'
										+ currentPage
										+ "、"
										+ '</span>'
										+ '<textarea  id="textcontent"  autoHeight="true" style=\"background:transparent;resize:none;outline:none;border-style:none;width:95%;font: 12px/1.5em "Microsoft YaHei";overflow-y:hidden\ " readonly>'
										+ a.content
										+'</textarea>'
										+'<div style="margin-left:40px">'
										+img(queImgs,queImgsStr)
										+'</div>'
										+ '<div class="ul" >'
										+ '<br>'
										+ '<ul id="mutiple'+i+'">'
										+ ifNull(2, a.opt1,1)
										+ ifNull(2, a.opt2,2)
										+ ifNull(2, a.opt3,3)
										+ ifNull(2, a.opt4,4)
										+ ifNull(2, a.opt5,5)
										+ ifNull(2, a.opt6,6)
										+ '</ul>'
										+ '</div>' + '</div>' + '</div>' );
						var textcontent=document.getElementById("textcontent");
				        autoTextarea(textcontent);// 调用
						answer = data.datas.thisAnswer;
						if (answer != null && answer != "") {
							answer = answer.substr(0, answer.length - 3);
							var answers = answer.split("#$#");
							for (var k = 0; k < answers.length; k++) {
								$("ul#mutiple" + i + "").find("li:eq(" + (answers[k] - 1) + ")").find("input").attr("checked","checked");
								$("ul#mutiple" + i + "").find("li:eq(" + (answers[k] - 1) + ")").css("color", "red");
							}
						}
					}
					if (b == "3") {//判断题
						$("#content")
								.append(
										'<div id='+'"div'+currentPage+'"'
										+'class="box box-info" style="height:90%;border:1px solid grey;border-radius:30px;">'
										+ '<div class="question title">'
										+'<span style="float:left;padding-top: 1px;font: 12px/1.5em "Microsoft YaHei";">'
										+ currentPage
										+ "、"
										+ '</span>'
										+ '<textarea  id="textcontent" autoHeight="true" style=\"background:transparent;resize:none;outline:none;border-style:none;width:95%;overflow-y:hidden\ " readonly>'
										+ a.content
										+'</textarea>'
										+'<div style="margin-left:20px">'
										+img(queImgs,queImgsStr)
										+'</div>'
										+ '<div class="ul">'
										+ '<br>'
										+ '<ul id="mutiple'+i+'">'
										+ ifNull(3, a.opt1)
										+ ifNull(3, a.opt2)
										+ '</ul>'
										+ '</div>'
										+ '</div>'
										+ '</div>'
												);
						var textcontent=document.getElementById("textcontent");
				        autoTextarea(textcontent);// 调用
						answer = data.datas.thisAnswer;
						if (answer != null && answer != "") {
							var tag=parseInt(answer)-1;
							var tag1=parseInt(answer)+1;
							if (answer == 1) {
								$("ul#mutiple" + i + "").find("li:eq(" + tag + ")").find("input").attr("checked", "checked");
								$("ul#mutiple" + i + "").find("li:eq(" + tag + ")").css("color", "red");
							} else {
								$("ul#mutiple" + i + "").find("li:eq(" + tag1 + ")").find("input").attr("checked", "checked");
								$("ul#mutiple" + i + "").find("li:eq(" + tag1 + ")").css("color", "red");
							}
						}
					}
					if (b == "4") {//填空题
						$("#content")
								.append(
										'<div id='
												+ '"div'
												+ currentPage
												+ '"'
												+ 'class="box box-info" style="height:90%;border:1px solid grey;border-radius:30px;">'
												+ '<div class="question title">'
												+ '<span style="float:left;padding-top: 1px;font: 12px/1.5em "Microsoft YaHei";" >'
												+ currentPage
												+ "、"
												+ '</span>'
												+ '<textarea  id="textcontent"  autoHeight="true" style=\"margin-bottom:8px;background:transparent;resize:none;outline:none;border-style:none;width:95%;overflow-y:hidden\ " readonly>'
												+ a.content
												+ '</textarea>'
												+'<div style="margin-left:40px">'
												+img(queImgs,queImgsStr)
												+'</div>'
												+ '<div class="ul">'
												+ '<ul id="mutiple'+i+'" class="blank">'
												+ blankNbr(strs) + '</ul>'
												+ '</div>'
												+ '</div>'
												+ '</div>');
						var textcontent=document.getElementById("textcontent");
				        autoTextarea(textcontent);// 调用
						answer = data.datas.thisAnswer;
						if (answer != null && answer != "undefined") {
							answer = answer.substr(0, answer.length - 3);
							var answers =answer.split("#$#");
							for (var k = 0; k < answers.length; k++) {
								document.getElementById("blank" + k + "").value = returnCharactor(answers[k]);
							}
						}
					}
					if (b == "5" || b == "6") {//简答题和编程题
						$("#content")
								.append(
										'<div id='+'"div'+currentPage+'"">'
											+'<div  class="box box-info question title" style="min-height:390px;border:1px solid grey;border-radius:10px;width:60%;float:left">'
												+'<span style="float:left;padding-top: 1px;font: 12px/1.5em "Microsoft YaHei";" >'
													+ currentPage
													+"、"
												+'</span>'
												+'<textarea id="textcontent"  autoHeight="true" style=\"background:transparent;resize:none;outline:none;border-style:none;width:95%;overflow-y:hidden\ " readonly>'
													+ a.content
												+'</textarea>'
												+'<div style="margin-left:20px">'
													+img(queImgs,queImgsStr)
												+'</div>'
												+'</br>'
											+'</div>'	
											+'<div  class="box box-info" style="width:35%;display:inline-block;height:40%;border-radius:10px;margin-left:20px;border:1px solid grey;position: fixed;float:right;left: 60%;top:200px;">'
												+'<div style="margin-top:10px;float:left;display：inline;">'
													+'<span class="question title" style="margin-left:10px">Answer:</span>'
													+'<img  style="width:20px;height:20px;float:right;margin-left:15px" src="${pageContext.request.contextPath}/common/image/blackA.png" data-toggle="tooltip"  title="黑底绿字" onclick="toCobol()" />'
													+'<img  style="width:20px;height:20px;float:right;margin-left: 300px" src="${pageContext.request.contextPath}/common/image/whiteA.png" data-toggle="tooltip"  title="白底黑字" onclick="toDefault()" />'
												+'</div>'
												+'<textarea id="textanswer" autoHeight="true" wrap="off"  style=\"font:10px;outline:none;resize:none;margin-left:30px;margin-top:40px;width:85%;height:70%\"></textarea>'
											+ '</div>' 
										+ '</div>'
										//+ '</div>'
										);
						//textarea的TAB键功能的实现
						$("textarea").on('keydown',function(e){
						    if(e.keyCode == 9){
						        e.preventDefault();
						        var indent = '    ';
						        var start = this.selectionStart;
						        var end = this.selectionEnd;
						        var selected = window.getSelection().toString();
						        selected = indent + selected.replace(/\n/g,'\n'+indent);
						        this.value = this.value.substring(0,start) + selected + this.value.substring(end);
						        this.setSelectionRange(start+indent.length,start+selected.length);
						    }else{
						    	return "";
						    }
						});
						//调用textarea高度自适应方法
						 var textanswer = document.getElementById("textanswer");
						 var textcontent=document.getElementById("textcontent");
  					    //autoTextarea(textanswer);// 调用
						        autoTextarea(textcontent);// 调用
						var answer=data.datas.thisAnswer;
						if(answer!=null&&answer!="undefined"){
							answer = returnCharactor(answer);
							document.getElementById("textanswer").value=returnCharactor(answer);
						}
					}
				}
				//判断是否是第一题或者最后一题
				adjust(count);
			}
		}
	}
	
	//标记的题目
	$("#markButton").click(function() {
		var markflag = "true";
		document.getElementById('btn' + currentPage).style.background = "red";
		adjustmark();
		qustMark(markflag);
	})
	//取消标记的题目
	$("#cancelMark").click(function() {
		var markflag = "false";
		document.getElementById('btn' + currentPage).style.background = "";
		adjustmark();
		qustMark(markflag);
	})
	//根据不同题型展示不同的题目
	function ifNull(type,obj,num) {
		if (type == 1)
			return (obj == null || obj == '') ? ''
					: ('<li class="answer"><label style="float:left;width:90%">&nbsp;<input type="radio" name="radio" class="answer"/>&nbsp;'
							+ obj + '</label></li>'+optImg(optImgs,num));
		if (type == 2)
			return (obj == null || obj == '') ? ''
					: ('<li class="answer"><label >&nbsp;<input type="checkbox" name="checkbox" class="answer"/>&nbsp;'
							+ obj + '</label></li>'+optImg(optImgs,num));
		if (type == 3)
			return (obj == null || obj == '') ? ''
					: ('<li class="answer"><label >&nbsp;<input type="radio" name="radio" class="answer"/>&nbsp;'
							+ obj + '</label></li>');
	}

	//填空题题目展示	
	function blankNbr(strs) {
		var Str = '</br>';
		for (i = 0; i < strs.length; i++) {
			var blankOrder=parseInt(i)+1;
			var blank = ('<li class="answer"><input id="blank' + i + '"  value="" placeholder="'+bbb+blankOrder+'" type="text" name="blank" class="form-control" style="width:50%;text-align:left;border-color:black" ></li>');
			Str = Str + blank + '</br>';
		}
		return Str;
	}
	//根据题目数显示相应的题板
	function qustorder(count) {
		var Btn = '';
		for (var t = 0; t < count; t++) {
			var nbr = parseInt(t) + 1;
			var btn = ('<button  type="button"  class="btn btn-default"    value="'
					+ nbr
					+ '" id="btn'
					+ nbr
					+ '" onclick="jump('
					+ nbr
					+ ')" >' + nbr + '</button>' + ' ');
			if(property==2){
				btn = ('<button  type="button"  class="btn btn-default"    value="'
						+ nbr
						+ '" id="btn'
						+ nbr
						+ '" onclick="jump('
						+ nbr
						+ ')" >' + nbr + '</button>' + ' ');
			}
			Btn = Btn + btn;
			
		}
		return Btn;
	}
	
	//上一题(点击按钮提交当前答案后显示上一题)	
	$("#lastButton").click(function() {
		if (currentPage > 1) {
			currentPage--;
		}
		current=currentPage+1;
		//获取答案信息
		$("#abv").click();
		adjustmark();
		//获取题目ID和考生答案
		var qustAnswer = document.getElementById('selectItems').value;
		var answer=qustAnswer;
		if(qustAnswer.indexOf("#$#")>-1){
			answer="";
			var arr = new Array();
			arr = qustAnswer.split('#$#');
			for(x in arr){
				answer=answer+arr[x];
			}
		}

		adjustQtFinish(current,answer);
		var qustId = document.getElementById('questionId').value;
		$.ajax({
			url : "../onlineTest/online!qustAnswer.do?paperId=" + paperId + "&paperNo=" + paperNo + "&planId=" + planId,
			data : {
				"qustId" : qustId,
				"qustAnswer" : qustAnswer,
				"totalTime" : totalTime,
				"currentPage" : currentPage
			},
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.success){
					if (data.datas.flag=='success') {
						$("#toQust").click();
					} else {
						layer.msg(netException);//"网络异常，请重新作答"
					}
				}else{
					layer.alert(systemError, {"title":feedback});//系统出错 --反馈
					console.info(data.message);
				}
			}
		});
	});

	//下一题(点击按钮提交答案后显示下一题)
	$("#nextButton").click(function() {
		currentPage++;
		current=currentPage-1;
		$("#abv").click();
		if(property==2){
			adjustmark();
		}
		//获取题目ID和考生答案
		var qustAnswer = document.getElementById('selectItems').value;
		if(qustAnswer==null){
			qustAnswer="";
		}
		var answer=qustAnswer;
		if(qustAnswer.indexOf("#$#")>-1){
			answer="";
			var arr = new Array();
			arr = qustAnswer.split('#$#');
			for(x in arr){
				answer=answer+arr[x];
			}
		}
		adjustQtFinish(current,answer);
		var qustId = document.getElementById('questionId').value;
		$.ajax({
			url : "../onlineTest/online!qustAnswer.do?paperId=" + paperId + "&paperNo=" + paperNo + "&planId=" + planId,
			data : {
				"qustId" : qustId,
				"qustAnswer" : qustAnswer,
				"totalTime" : totalTime,
				"currentPage" : currentPage
			},
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.success){
					if (data.datas.flag=='success') {
						$("#toQust").click();
					} else {
						(netException);
					}
				}else{
					layer.alert(systemError, {"title":feedback});//系统出错 --反馈
					console.info(data.message);
				}
			}
		})
	});
	//题板跳转（跳转至点击的题目）
	function jump(nbr) {
		current=currentPage;
		currentPage = document.getElementById('btn' + nbr + '').value;
		//获取答案信息
		$("#abv").click();
		adjustmark();
		//获取题目ID和考生答案
		var qustAnswer = document.getElementById('selectItems').value;
		var answer=qustAnswer;
		if(qustAnswer.indexOf("#$#")>-1){
			answer="";
			var arr = new Array();
			arr = qustAnswer.split('#$#');
			for(x in arr){
				answer=answer+arr[x];
			}
		}
		adjustQtFinish(current,answer);
		var qustId = document.getElementById('questionId').value;
		$.ajax({
			url : "../onlineTest/online!qustAnswer.do?paperId=" + paperId + "&paperNo=" + paperNo + "&planId=" + planId,
			data : {
				"qustId" : qustId,
				"qustAnswer" : qustAnswer,
				"totalTime" : totalTime
			},
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.success){
					if (data.datas.flag=='success') {
						//获取题目信息
						$("#toQust").click();
					} else {
						(netException);
					}
				}else{
					layer.alert(systemError, {"title":feedback});//系统出错 --反馈
					console.info(data.message);
				}
			}
		})
	}

	//获取考生的答案信息
	var result = "";
	$("#abv").click( function() {
		var answer = null;
		var type = document.getElementById("qustType").value;
		//获取单选题（已获取）
		if (type == "1") {
			var obj = document.getElementsByName("radio");
			for (var i = 0; i < obj.length; i++) { //遍历Radio 
				if (obj[i].checked) {
					answer = i + 1;
					$("#selectItems").val(answer);
				}
			}
		}
		//获取多选框中的值(已获取)
		if (type == "2") {
			var obj = document.getElementsByName("checkbox");
			for (var i = 0; i < obj.length; i++) { //遍历checkbox
				if (obj[i].checked) {
					answer = i + 1 + "#$#";
					document.getElementById('selectItems').value = document.getElementById('selectItems').value + answer;
				}
			}
		}
		//获取判断题中的值
		if (type == "3") {
			var obj = document.getElementsByName("radio");
			console.info(obj);
			for (var i = 0; i < obj.length; i++) { //遍历Radio 
				if (obj[i].checked) {
					answer = i + 1;
					$("#selectItems").val(answer);
				}
			}
		}
		//获取填空题答案(已获取)
		if (type == "4") {
			$(".blank input").each(function(i) {
				result =turnCharactor( $(this).val() )+ "#$#";
				document.getElementById('selectItems').value = document.getElementById('selectItems').value + result;
			});
			var _answerStr=document.getElementById('selectItems').value;
			$("#selectItems").val(_answerStr.substring(0,14999));
		}
		//获取简答题和编程题答案
		if (type == "5" || type == "6") {
			result =turnCharactor( $("#textanswer").val() );
			document.getElementById('selectItems').value = result;
			var _answerStr=document.getElementById('selectItems').value;
			$("#selectItems").val(_answerStr.substring(0,14999));
		}
	});
	//如果是第一题则上一题按钮无法点击，如果是最后一题，则下一题隐藏，显示交卷按钮；
	function adjust(count) {
		//上一题按钮状态判断
// 		var oBtn = document.getElementById('lastButton');
		if (currentPage <= 1) {
// 			oBtn.disabled = 'disabled';
			document.getElementById('lastQust').style.visibility = 'hidden';
		} else {
// 			oBtn.disabled = false;
			document.getElementById('lastQust').style.visibility  = 'visible';
// 			document.getElementById('lastQust').style.left = "5%";
		}
		//下一题按钮判断
		if (currentPage == count) {
			document.getElementById('nextQust').style.display = 'none';
// 			document.getElementById('finish').style.left = "30%";
			document.getElementById('finish').style.display = 'block';
		}
		//不是最后一题则只显示上下题，隐藏提交按钮
		else {
			document.getElementById('finish').style.display = 'none';
// 			document.getElementById('nextQust').style.left = "5%";
			document.getElementById('nextQust').style.display = 'block';
		}
	}
	//验证标记按钮是否显示
	function adjustmark() {
		if (document.getElementById('btn' + currentPage).style.background == "red") {
			document.getElementById('markDiv').style.display = 'none';
			document.getElementById('cancelMarkDiv').style.display = 'block';
// 			document.getElementById('nextQust').style.left = "5%";
		} else {
			document.getElementById('cancelMarkDiv').style.display = 'none';
			document.getElementById('markDiv').style.display = 'block';
		}
	}
	//验证题目是否完成的题板按钮显示
	function adjustQtFinish(current,answer){		
		if(document.getElementById('btn' + current).style.background=="red"){
		}else 
			if(answer!=null&&answer!==""){
			document.getElementById('btn' + current).style.background = "#24F707";
			qustFinishFlag(current);
		}
	}
	//初始加载页面时题板标记题目显示红色
	function inimark(qustnbr) {
		document.getElementById('btn' + qustnbr).style.background = "red";
		if (document.getElementById('btn1').style.background == "red") {
			document.getElementById('markDiv').style.display = 'none';
			document.getElementById('cancelMarkDiv').style.display = 'block';
		} else {
			document.getElementById('cancelMarkDiv').style.display = 'none';
			document.getElementById('markDiv').style.display = 'block';
		}
	}
	//初始加载页面时题板完成题目显示绿色
	function iniQustFinish(qfnbr){
		document.getElementById('btn' + qfnbr).style.background = "#24F707";
	}
	//将标记的值传给后台redis
	function qustMark(markflag) {
		$.ajax({
			url : "../onlineTest/online!qustMark.do?paperId=" + paperId + "&paperNo=" + paperNo + "&planId=" + planId
				+ "&currentPage=" + currentPage + "&markflag=" + markflag,
			dataType : "json",
			type : "post",
			success : function(data) {
			}
		});
	}
	
	//将已完成的题目的的值传给后台redis
	function qustFinishFlag(current) {
		$.ajax({
			url : "../onlineTest/online!qustFinishFlag.do?paperId=" + paperId + "&paperNo=" + paperNo + "&planId="
				+ planId + "&current=" + current,
			dataType : "json",
			type : "post",
			success : function(data) {
			}
		});
	}
	//离开考试页面指定次数后将自动交卷
	function exam() {
		examleave();
	}
	function examleave(){
		if(leaveTotal==0){
			return;
		}
		var qustAnswer = document.getElementById('selectItems').value;
		var qustId = document.getElementById('questionId').value;
		$.ajax({
			url : "../onlineTest/online!paperInfo.do?paperId=" + paperId + "&paperNo=" + paperNo + "&planId=" + planId,
			dataType : "json",
			async: false,
			type : "post",
			success : function(data) {
				leavetime = data.datas.leavelimit;
			}
		});
		if (leavetime >= leaveTotal) {
   			forceSubmit();
		}
	}
	//回到页面<span style="line-height:18px;"><font size="6" line-height:18px  color="#FF0000">
	function backExam(){
		if(leaveTotal==0){
			return;
		}else{
			layer.msg('<section style="font-size:30px;color:#ff0000">'+leavePrompt1+leavetime+leavePrompt2+'<br>'+leavePrompt3+leaveTotal+leavePrompt4+'<br>'+leavePrompt5+'<br>'+leavePrompt6+'</section>', {
                time:15000,
                title:false,
                area: ['910px', '200px'],
            });
// 			layer.msg('<font size="12" color="#FF0000">'+leavePrompt1+leavetime+leavePrompt2+leaveTotal+leavePrompt3+'</font>',{time:5000});
			//'您好，您已经离开页面  次了，离开页面 次后将自动交卷,请谨慎作答'
		}
	}
	
	function finishSubmit(){
		//获取答案信息
		$("#abv").click();
		//最后一题标志
		var examEnd = "true";
// 		var count = document.getElementById('count').value;
// 		if (currentPage == count || leavetime >= leaveTotal) {
// 			examEnd = "true";
// 		}
 		//获取题目ID和考生答案
 		var qustAnswer = document.getElementById('selectItems').value;
 		var qustId = document.getElementById('questionId').value;
		$.ajax({
			url : "../onlineTest/online!qustAnswer.do?paperId=" + paperId + "&paperNo=" + paperNo + "&planId=" + planId,
			data:{
				"qustId" : qustId,
				"qustAnswer" : qustAnswer,
				"planPaperId" : planPaperId,
				"examEnd" : examEnd,
				"totalTime":totalTime
			},
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.success){
					if (data.datas.flag=='success') {
						window.location.href = "../onlineTest/online!toExamFinishPage.do?paperId="+paperId+"&paperNo="+paperNo+"&planId="+planId;
					} else {
						layer.msg(netException);
					}
				}else{
					layer.alert(systemError, {"title":feedback});//系统出错 --反馈
					console.info(data.message);
				}
			}
		});
	}
	//延时交卷（时间随机）
	var delayTimer;
	var delayMillSec;
	function delay(){
		if(delayMillSec>0){
			delayMillSec=delayMillSec-500;
			console.info("剩余"+delayMillSec);
		}else{
			clearInterval(delayTimer); 
			processForceSubmit();
		}
	}
		
		
	//强行交卷
	function forceSubmit(){
		//获取答案信息
		$("#abv").click();
		$("#loading-mask").fadeIn();
		//延时提交
		delayMillSec=GetRandomNum(500,5000);
		console.info("等待"+delayMillSec+"毫秒提交.........");
		delayTimer=setInterval("delay()",500);
	}
	
	function processForceSubmit(){
		//最后一题标志
		var examEnd = "true";
 		//获取题目ID和考生答案
 		var qustAnswer = document.getElementById('selectItems').value;
 		var qustId = document.getElementById('questionId').value;
 		$.ajax({
			url : "../onlineTest/online!qustAnswer.do?paperId=" + paperId + "&paperNo=" + paperNo + "&planId=" + planId,
			data:{
				"qustId" : qustId,
				"qustAnswer" : qustAnswer,
				"planPaperId" : planPaperId,
				"examEnd" : examEnd,
				"totalTime":totalTime
			},
			dataType : "json",
			type : "post",
			success : function(data) {
				$("#loading-mask").fadeOut();
				if(data.success){
					if (data.datas.flag=='success') {
						window.location.href = "../onlineTest/online!toExamFinishPage.do?paperId="+paperId;
					} else {
						layer.msg(netException);
					}
				}else{
					layer.alert(systemError, {"title":feedback});//系统出错 --反馈
					console.info(data.message);
				}
			}
		});
	}

	//交卷
	$("#submit").click(function() {
				$("#abv").click();
				$.ajax({
					url : "../onlineTest/online!getUnfinishOrMark.do?paperId=" + paperId + "&paperNo=" + paperNo + "&planId=" + planId,
					dataType : "json",
					type : "post",
					success : function(data) {
						if(data.success){
							markNbr = data.datas.markNbr;
							unFinishNbr = data.datas.unFinishNbr;
							var answer = document.getElementById('selectItems').value;
							if (answer != null && answer != ""&& answer != "undefined") {
								unFinishNbr = unFinishNbr - 1;
							}
							layer.confirm(commitPrompt1+unFinishNbr+commitPrompt2+markNbr+commitPrompt3, {
								btn : [ confirm, cancel ],title:prompt
							//按钮
							},
							function() {
								finishSubmit();
							}, 
							function() {
								
							});
						}else{
							layer.alert(systemError, {"title":feedback});//系统出错 --反馈
							console.info(data.message);	
						}
					}
				});
			});
	//记录离开页面时间（关闭，刷新等操作）
// 	function checkLeave() {
		// 		var examEnd = null;
		// 		var count = document.getElementById('count').value;
		// 		if(leaveTotal==0){
		// 			if(currentPage == count){
		// 				examEnd = "true";
		// 			}
		// 		}else
		// 		if (currentPage == count || leavetime > leaveTotal) {
		// 			examEnd = "true";
		// 		}

		// 		exam();
// 		$.ajax({
// 			url : "../onlineTest/online!recordLeaveTime.do?paperId="+ paperId+ "&paperNo="+ paperNo+ "&planId="+ planId
// 				+ "&currentPage="+ currentPage,
// 			dataType : "json",
// 			type : "post",
// 			success : function(data) {
				
// 			}
// 		});
// 	}
	///////////////////textarea的高度自适应/////////////////	
// 	$.fn.autoHeight = function() {

// 		function autoHeight(elem) {
// 			elem.style.height = 'auto';
// 			elem.scrollTop ="0"; //防抖动
// 			elem.style.height = elem.scrollHeight + 'px';
// 		}

// 		this.each(function() {
// 			autoHeight(this);
// 			$(this).on('keyup', function() {
// 				autoHeight(this);
// 			});
// 		});

// 	}
// 	$('textarea[autoHeight]').autoHeight();
//文本域textarea高度自适应（键盘按键不会回到页面顶部）
	var autoTextarea = function (elem, extra, maxHeight) {
        extra = extra || 0;
        var isFirefox = !!document.getBoxObjectFor || 'mozInnerScreenX' in window,
        isOpera = !!window.opera && !!window.opera.toString().indexOf('Opera'),
                addEvent = function (type, callback) {
                        elem.addEventListener ?
                                elem.addEventListener(type, callback, false) :
                                elem.attachEvent('on' + type, callback);
                },
                getStyle = elem.currentStyle ? function (name) {
                        var val = elem.currentStyle[name];
 
                        if (name === 'height' && val.search(/px/i) !== 1) {
                                var rect = elem.getBoundingClientRect();
                                return rect.bottom - rect.top -
                                        parseFloat(getStyle('paddingTop')) -
                                        parseFloat(getStyle('paddingBottom')) + 'px';        
                        };
 
                        return val;
                } : function (name) {
                                return getComputedStyle(elem, null)[name];
                },
                minHeight = parseFloat(getStyle('height'));
 
        elem.style.resize = 'none';
 
        var change = function () {
                var scrollTop, height,padding = 0,
                        style = elem.style;
 
                if (elem._length === elem.value.length) return;
                elem._length = elem.value.length;
 
                if (!isFirefox && !isOpera) {
                        padding = parseInt(getStyle('paddingTop')) + parseInt(getStyle('paddingBottom'));
                };
                scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
 
                elem.style.height = minHeight + 'px';
                if (elem.scrollHeight > minHeight) {
                        if (maxHeight && elem.scrollHeight > maxHeight) {
                                height = maxHeight - padding;
                                style.overflowY = 'auto';
                        } else {
                                height = elem.scrollHeight - padding;
                                style.overflowY = 'hidden';
                        };
                        style.height = height + extra + 'px';
                        scrollTop += parseInt(style.height) - elem.currHeight;
                        document.body.scrollTop = scrollTop;
                        document.documentElement.scrollTop = scrollTop;
                        elem.currHeight = parseInt(style.height);
                };
        };
 
        addEvent('propertychange', change);
        addEvent('input', change);
        addEvent('focus', change);
        change();
};


	////////////////////////////////////////////////////
	
	////////////////////////随机数///////////////////////
	function GetRandomNum(Min,Max)
	{
		var Range = Max - Min;   
		var Rand = Math.random();   
		return(Min + Math.round(Rand * Range));   
	}
	////////////////////////////////////////////////////
	//图片显示
	function img(data,str){
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
				contextImg = contextImg+
						'<div id="image" class="item '+className+'">'+
							'<img title=<s:text name="larger"/> style="width: 320px;height: 180px;"   onclick="findImg(\''+str+'\')" '+
								'src="../question/fileAction!getImage.do?fileName='+uuid+'"'+
								'alt=""/>'+
						'</div>';
			}
		}	
		return '<div id="carousel-example-generic_" class="carousel slide"'+
					'data-ride="carousel" style="height: 180px;width: 320px">'+
					'<div class="carousel-inner" id="contextImg">'+
						contextImg+
					'</div>'+
					'<a class="left carousel-control" href="#carousel-example-generic_"'+
						'data-slide="prev"> <span class="fa fa-angle-left"></span>'+
					'</a>'+
					'<a class="right carousel-control" href="#carousel-example-generic_"'+
						'data-slide="next"> <span class="fa fa-angle-right" style="left: -4px;"></span>'+
					'</a>'+
				'</div>';
	}
	
	function findImg(ids){
		$('#imgSimModel').modal('show');
		$("#contextOll").html("");
		$("#contextImgg").html("");
		id = ids.split("-");
		var contextImg = "";
// 		for(var i=0;i<1;i++){
		for(var i=0;i<id.length;i++){
			var uuid = id[i];
			var className = "";
			if(i==0){
				className = "active";
			}
			if(uuid!=null&&uuid!=""){
				contextImg = contextImg+
				'<div class="item '+className+'" style="height:100%;text-align:center;vertical-align:middle;" > '+
					//'<div>'+
						'<img title="Click Exit" style="display:block;height:100%;margin-top:1%;margin-left:5%;margin:auto" onclick="closemodal()" '+
							'src="../question/fileAction!getImage.do?fileName='+uuid+'"'+
							'alt="Click Exit"/>'+
					//'</div>'
				'</div>';
			}
		}
		$("#contextImgg").html(
		'<div id="carousel-example-generic2_" class="carousel slide" data-ride="carousel" style="height:95%;width:100%;display: flex;align-items:center;">'+
			'<div class="carousel-inner" id="contextImg2">'+
				contextImg+
			'</div>'+
			'<a class="left carousel-control" href="#carousel-example-generic2_"'+
				'data-slide="prev"> <span class="fa fa-angle-left"></span>'+
			'</a>'+
			'<a class="right carousel-control" href="#carousel-example-generic2_"'+
				'data-slide="next"> <span class="fa fa-angle-right" style="left: -4px;"></span>'+
			'</a>'+
		'</div>');
	}
	
	/* function findImg(ids){
		$('#imgSimModel').modal('show');
		$("#contextOll").html("");
		$("#contextImgg").html("");
		id = ids.split("-");
		for(var i=0;i<id.length;i++){
			var uuid = id[i];
			var className = "";
			if(i==0){
				className = "active";
			}
			if(uuid!=null&&uuid!=""){
				$("#contextOll").html($("#contextOll").html()+'<li data-target="#carousel-example-generic" data-slide-to="'+i+'" class="'+className+'" ></li>');
				$("#contextImgg").html($("#contextImgg").html()
				+('<div class="item '+className+'"  style="height:99%"><img onclick="closemodal() "'
				+'style="height:83%;width: 50%;margin-top:4%;margin-left:25%" '
				+'src="../question/fileAction!getImage.do?fileName='+uuid+'" '
				+'alt=""></div>')
				)
			}
		}
		return '<div id="carousel-example-generic_" class="carousel slide"'+
					'data-ride="carousel" style="height: 180px;width: 320px">'+
					'<div class="carousel-inner" id="contextImg">'+
						contextImg+
					'</div>'+
					'<a class="left carousel-control" href="#carousel-example-generic_"'+
						'data-slide="prev"> <span class="fa fa-angle-left"></span>'+
					'</a>'+
					'<a class="right carousel-control" href="#carousel-example-generic_"'+
						'data-slide="next"> <span class="fa fa-angle-right" style="left: -4px;"></span>'+
					'</a>'+
				'</div>';
	} */
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
				return '<div class="row" style="margin-left:1px"><img title="点击放大图片" onclick="findImg(\''+data+'\')" style="width: 320px;height: 180px;" '+
				'src="../question/fileAction!getImage.do?fileName='+data+'"'+
				'alt=""></div>';
			}
		}
	}
	
	$("#closeButton").click(function(){
		$('#imgSimModel').modal('hide');
	})
	function closemodal(){
		$('#imgSimModel').modal('hide');
	}
	
	//编程题转成
	function toDefault(){
 		 $("#textanswer").attr({"style":"font:10px;width:60%;resize:none;outline:none;margin-left:30px;margin-top:50px;width:85%;height:70%"}); 
	}
	function toCobol(){
 		 $("#textanswer").attr({"style":"font:10px;color:#06F006;background:black;font-family:courier new;resize:none;width:85%;margin-left:30px;margin-top:50px;height:70%"}); 
	}
	

</script>
</html>
