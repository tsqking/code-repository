<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background-color:#ecf0f5;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/common/image/logo.ico" />
<s:include value="../common.jsp"></s:include>
<!-- 页面js -->
<%-- <script src="${pageContext.request.contextPath}/onlineTest/welcome.js"></script> --%>
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

div#head {
	font-size: 16px;
}

div.top {
	height: 300px;
}

div.top1 {
	height: 100px;
}

label {
	float: left;
	text-align: left;
	height: 30px;
	font-weight: normal;
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
	margin-top: 14px;
	text-align: left;
	font-size: 14px;
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
</style>
</head>
<body class="hold-transition skin-blue layout-top-nav" >
	<div class="wrapper" style="height:100%;width:100%">
		<header class="main-header">
			<nav class="navbar navbar-static-top" style="width:100%">
				<div class="container" style="width:100%">
					<div class="navbar-header" style="margin-top: 0px;">
						<!-- <img src="/TMP/common/image/clps.png" height="25px" width="120px" style="margin-top: 10px;"> -->
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

			<div class="container" style="width:80%">
			<div id="head">
				<div style="text-align: left;">
							<div style="margin:0 auto;">
					<input type="hidden" id="paperId" value="${paperId }" />
					<input type="hidden" id="paperNo" value="${paperNo }" />
					<input type="hidden" id="pstartTime" value="${pstartTime }" />
					<input type="hidden" id="startTime" value="${startTime }" />
					<input type="hidden" id="nowTime" value="${nowTime }" />
					<input type="hidden" id="endTime" value="${endTime }" />
					<input type="hidden" id="totalTime" value="${totalTime }" />
					<input type="hidden" id="planPaperId" value="${planPaperId }" />
					<input type="hidden" id="planId" value="${planId }" />
				</div>
				<br> <br>
				<div class="top1" style="float: left; width: 100%; text-align:center;">
					<img src="/TMP/common/image/clock.png" height="120px" width="120px" style="margin-top: 8%;">
					<br>
					<div  class="" id="timer" style="color: red; font-size: 22px;margin-top:3%"></div>
					<br><br>
					<div  class="" id="msg" style="color: #4f4f4f; font-size: 22px;margin-top:3%">Please do not refresh the page. Otherwise re-login.</div>
				</div>
			</div>
		
		</div>
			</div>

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
</body>

<script type="text/javascript">
var now=document.getElementById('nowTime').value;
var pstartTime=document.getElementById('pstartTime').value;
var start=NewDate(pstartTime);
var nowTime= NewDate(now);
var seconds = (start - nowTime)/1000 ;   //差秒数
var startTime=document.getElementById('pstartTime').value;
var endTime=document.getElementById('endTime').value;
var totalTime=document.getElementById('totalTime').value;
var planPaperId=document.getElementById('planPaperId').value;
var planId=document.getElementById('planId').value;
var paperNo=document.getElementById('paperNo').value;
function CountDown(){
	if(seconds>0){
		var h = seconds/3600 < 10 ? ('0' + parseInt(seconds/3600)) : parseInt(seconds/3600);
		var m = (seconds-h*3600)/60 < 10 ? ('0' + parseInt((seconds-h*3600)/60)) : parseInt((seconds-h*3600)/60);
		var s = seconds < 10 ? ('0' + seconds) : seconds%60;
		msg = countDownInfo1+h+hour+m+minute+s+second+countDownInfo2;  
		document.all["timer"].innerHTML=msg;  
		//if(total == 5*60) alert('注意，还有5分钟!');  
		seconds--;  
	}else{
		clearInterval(timer);
		window.location.href="../onlineTest/online!toWelcomeTestPage.do?paperId="+$("#paperId").val()+"&startTime="+startTime
				+"&endTime="+endTime+"&totalTime="+totalTime+"&planPaperId="+planPaperId+"&planId="+planId+"&paperNo="+paperNo;
	}  
}

timer = setInterval("CountDown()",1000);

function NewDate(str) {// 
	//str 2016-07-29 16:14:30
	str = str.split(' ');
	var dateStr=str[0].split('-');
	var timeStr=str[1].split(':');
	var date = new Date(); 
	date.setUTCFullYear(dateStr[0], dateStr[1] - 1, dateStr[2]);
	date.setUTCHours(timeStr[0], timeStr[1], timeStr[2], 0);
	return date-8;
}

</script>