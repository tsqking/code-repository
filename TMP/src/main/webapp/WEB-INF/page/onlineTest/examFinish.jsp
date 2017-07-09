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
<body class="hold-transition skin-blue layout-top-nav">
	<div class="wrapper" style="height:80%;width:100%">
		<header class="main-header">
			<nav class="navbar navbar-static-top" style="width:100%">
				<div class="container" style="width:100%">
					<div class="navbar-header" style="margin-top: 0px;">
						<!-- <img src="/TMP/common/image/clps.png" height="26px" width="120px" style="margin-top: 10px;"> -->
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
		<div class="content-wrapper " style="height:100%">
			<input type="hidden" id="paperId" value="${ontestVo.paperId }" /> 
				<div class="container" style="width:100%;text-align:center;">
					<div style="margin-top:10%;text-align:center;">
						<img src="/TMP/common/image/check.png" height="80px" width="80px" style="margin-top: 10px;">
					</div>
					<br>
				  <div id="head"> 
						<div style="margin-top:5%;">
							<div style="text-align:center;font-size:20px">
								<s:text name="finishPrompt" />
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
		if ((event.keyCode==8)  ||   //屏蔽退格删除键      
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
</script>
</html>