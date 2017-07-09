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
/* 	color: #f00; */
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
<body class="hold-transition skin-blue layout-top-nav" style="height: 100%" oncontextmenu="return false;">
	<div class="wrapper" style="height: 100%; width: 100%">
		<header class="main-header"> 
			<nav class="navbar navbar-static-top" style="width:100%">
				<div class="container" style="width: 100%">
					<div class="navbar-header" style="margin-top: 0px;">
						<img src="/TMP/common/image/Financial IT talent_logo.png" width="50"
							height="50" style="margin-top: 0px;" />
					</div>
					<div class="collapse navbar-collapse pull-left" id="navbar-collapse">
						<ul class="nav navbar-nav">
							<li><a href="javascript:void(0)"><s:text name="Financial System" /></a></li>
						</ul>
					</div>
				</div>
			</nav> 
		</header>

		<div class="content-wrapper " style="height: 100%; height: auto">
			<div class="container" style="width: 80%">
				<div id="head">
					<div style="text-align: left">
						<div style="margin: 0 auto;">
							<input type="hidden" id="paperId"
								value="${requestScope.paperId }" /> <input type="hidden"
								id="paperNo" value="${paperNo }" /> <input type="hidden"
								id="totalTimeHidden" value="${totalTime }" /> <input
								type="hidden" id="startTime" value="${startTime }" /> <input
								type="hidden" id="endTime" value="${endTime }" /> <label
								style="width: 100%;"><s:text name="【" />&nbsp;<span
								id="userName"></span>&nbsp;<s:text name="w12" /> <span
								id="paperName"></span> <s:text name="w13" /></label>
							<h1 id="paperName">${paperName }</h1>
						</div>
						<br> <br>
						<div>
							<label style="font-size:20px">
									<s:text name="examInstruction" />
							</label><br>

						</div>
						<br>
						<textarea autoHeight="true" id="instruction"
							style="width: 100%; background: transparent; resize: none; outline: none; border-style: none; width: 95%; overflow-y: hidden"
							readonly>'
						</textarea>
						<label style="width: 100%;"><s:text name="w1" />&nbsp;<span
							id="totalItem"></span>&nbsp;<s:text name="w2" /> <s:text
								name="w3" />&nbsp;<span id="totalTime"></span>&nbsp;<s:text
								name="w4" />
						</label>
					</div>
					<%-- <div style="color:blue;">
						<div>
							<label style="text-align: left;font-size:20px; margin-top: 4%;">
								<s:text name="attentions" />
							</label>
						</div>
						<div style="text-align: left;margin-top:20px">
							<label style="width: 100%;">·<s:text name="w5" /></label> <br>
							<label style="width: 100%; text-align: left;">·<s:text name="w6" /></label><br>
							<label style="width: 100%;text-align: left;">·<s:text name="w10" /><span id="leaveLimitTotal"></span>&nbsp;<s:text name="w11" /></label><br> 
							<label style="width: 100%;text-align: left;">·<s:text name="w7" /></label><br>
							<label style="width: 100%;">·<s:text name="w8" /></label>
						</div>
					</div> --%>
					<div style="color:blue;">
						<div>
							<label style="text-align: left;font-size:20px; margin-top: 4%;">
								Attention:
							</label>
						</div>
						<div style="text-align: left;margin-top:20px">
							<label style="width: 100%;">· Answering process uses automatic timing. Your answer is submitted automatically once time expires.</label>
							<label style="width: 100%; text-align: left;"><strong>· Before the exam, please close other browsers and applications, such as QQ , Screensaver, etc. DO NOT switch to other windows other than the exam window.</strong></label>
							<label style="width: 100%;text-align: left;">· Exiting the exam for more than&nbsp;<span id="leaveLimitTotal"></span>&nbsp;times will render the exam automatically submitted, and hence, ended.</label>
							<label style="width: 100%;text-align: left;">· The number of times you exited an exam will be reflected in the final results.</label>
							<label style="width: 100%;">· Chrome browser is recommended for the online tests.</label>
						</div>
					</div>
					<div style="color:blue;">
						<div>
							<label style="text-align: left;font-size:20px; margin-top: 4%;">
								注意事项:
							</label>
						</div>
						<div style="text-align: left;margin-top:20px">
							<label style="width: 100%;">· 答题过程中系统自动计时，到时自动交卷；</label>
							<label style="width: 100%; text-align: left;"><strong>· 考试前请关闭其他浏览器窗口，关闭可能弹窗的应用如QQ、屏保等，考试中不要切换到考试窗口之外的区域；</strong></label>
							<label style="width: 100%;text-align: left;">· 离开试卷超过&nbsp;<span id="leaveLimitTotal2"></span>&nbsp;次将强制提交试卷，考试结束；</label>
							<label style="width: 100%;text-align: left;">· 离开试卷次数将被记录到最后的成绩中；</label>
							<label style="width: 100%;">· 推荐使用Chrome浏览器进行在线测试。</label>
						</div>
					</div>
					<div>
						<label style="float: left; marign-left: 10%; margin-top: 3%">
							<input class="flat-red" type="checkbox" id="checkbox"
							style="float: left;" />&nbsp;<s:text name="w9" />
						</label>
					</div>
					<div class="row" style="margin-left: 50%">
						<div class="col-md-7 col-xs-7">
							<button type="button" id="toExamBtn" disabled style="margin-top:11%"
								class="btn btn-block btn-primary btn-sm">
								<i class="fa fa-hand-peace-o"></i>&nbsp;&nbsp;&nbsp;
								<s:text name="startAnswer" />
							</button>
						</div>
						<br> <br>
					</div>
				</div>
			</div>
		</div>
		<div style="display: none">
			<span id="property" /></span> <input type="hidden" id="planPaperId"
				value="${planPaperId }" /> <input type="hidden" id="planId"
				value="${planId }" />
		</div>
		<footer class="main-footer" style="z-index:20;">
			<div class="container">
				<div class="pull-right hidden-xs">
					<b>Version</b> 1.0.0
				</div>
				<strong>Copyright &copy; 2016-2018 <a href="javascript:void(0);">FU</a>.</strong> All rights reserved.
			</div>
		</footer>
	</div>
</body>

<script type="text/javascript">
	document.onkeydown = function(e) {
		var event = e || window.event;
		var code = event.keyCode || event.which || event.charCode;
		console.info("按键："+code);
		if(code == 122){//F11
			event.returnValue=false;
		}
		/* if(code == 91){//WINDOW
			event.keyCode=0;
			event.returnValue=false;
		} */
		if ( event.ctrlKey && code==83 ){ //Ctrl + s      
			code=0;      
			event.returnValue=false;      
		}
		if ((window.event.altKey)&& ((code==37)||   //屏蔽 Alt+ 方向键 ←      
			(window.event.keyCode==39))){  //屏蔽 Alt+ 方向键 →      
			event.returnValue=false;
		}
		if (//(event.keyCode==8)  ||   //屏蔽退格删除键      
			(event.keyCode==116)||   //屏蔽 F5 刷新键      
			(event.keyCode==112)||   //屏蔽 F1 刷新键      
			(event.ctrlKey && event.keyCode==82)){ //Ctrl + R      
			event.keyCode=0;
			event.returnValue=false;
		}
		if ( event.altKey && event.keyCode==115 ){ //屏蔽Alt+F4  
			window.showModelessDialog("about:blank","","dialogWidth:1px;dialogheight:1px");     
			return false;
		}
	};

	$.fn.autoHeight = function() {

		function autoHeight(elem) {
			elem.style.height = 'auto';
			elem.scrollTop = 0; //防抖动
			elem.style.height = elem.scrollHeight + 'px';
		}

		this.each(function() {
			autoHeight(this);
			$(this).on('keyup', function() {
				autoHeight(this);
			});
		});

	}
	$('textarea[autoHeight]').autoHeight();
	//复选框checkbox样式
	$('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
		checkboxClass : 'icheckbox_flat-green',
		radioClass : 'iradio_flat-green'
	});
	//复选框触发事件
	$('#checkbox').on('ifChecked', function(event) {
		document.getElementById('toExamBtn').disabled = "";
	});
	$('#checkbox').on('ifUnchecked', function(event) {
		document.getElementById('toExamBtn').disabled = "disabled";
	});

	var totalTime = document.getElementById('totalTimeHidden').value;
	document.getElementById('totalTime').innerText = totalTime;
	$(function() {
		var paperId = document.getElementById('paperId').value;
		var paperNo = document.getElementById('paperNo').value;
		var planId = document.getElementById('planId').value;
		$.ajax({
			url : "../onlineTest/online!findPaperInfo.do?paperId=" + paperId
					+ "&paperNo=" + paperNo + "&planId=" + planId,
			type : "post",
			dataType : "json",
			type : "post",
			success : function(data) {
				console.info(data);
				$("#userName").text(data.datas.userName);
				$("#paperName").text(data.datas.paperName);
				$("#instruction").text(data.datas.instruction);
				$("#totalItem").text(data.datas.totalItem);
				//$("#totalTime").text(data.datas.totalTime);
				$("#paperId").text(data.datas.paperId);
				$("#leaveLimitTotal").text(data.datas.leaveLimitTotal);
				$("#leaveLimitTotal2").text(data.datas.leaveLimitTotal);
				$("#property").text(data.datas.property);
				$('textarea[autoHeight]').autoHeight();
			}

		});

	});
	/**
	 * 跳转到试题页面
	 * 
	 */
	$("#toExamBtn").click( function() {
		var paperId = document.getElementById('paperId').value;
		var totalTime = $("#totalTime").html();
		var totalItem = $("#totalItem").html();
		var leaveLimitTotal = $("#leaveLimitTotal").html();
		var startTime = document.getElementById('startTime').value;
		var endTime = document.getElementById('endTime').value;
		var property = $("#property").html();
		var planPaperId = document.getElementById('planPaperId').value;
		var paperNo = document.getElementById('paperNo').value;
		var planId = document.getElementById('planId').value;
 		if(startTime==""||endTime==""){
 			layer.msg(paperSubmited);//试卷已提交
 			return;
 		}
 		$.ajax({
 			url : "../onlineTest/online!allowExam.do?paperId=" + paperId + "&totalTime=" + totalTime + "&paperNo=" + paperNo + "&planId=" + planId,
 			dataType : "json",
 			type : "post",
 			success : function(data) {
 				console.info(data);
 				if (data.datas.flag) {
 					layer.msg(paperSubmited);//试卷已提交
 				} else {
					window.location.href = "../onlineTest/online!toExamQuestionPage.do?paperId="
							+ paperId
							+ "&totalTime="
							+ totalTime
							+ "&totalItem="
							+ totalItem
							+ "&leaveLimitTotal="
							+ leaveLimitTotal
							+ "&startTime="
							+ startTime
							+ "&endTime="
							+ endTime
							+ "&property="
							+ property
							+ "&planPaperId="
							+ planPaperId
							+ "&paperNo="
							+ paperNo
							+ "&planId="
							+ planId
 				}
 			}
 		});
	});
</script>
</html>
