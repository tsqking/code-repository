<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="Login"/></title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/common/image/logo.ico" />
<link href="${pageContext.request.contextPath}/common/css/style_log.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/userpanel.css">

<!-- jQuery 2.1.4 -->
<script
	src="${pageContext.request.contextPath}/common/js/jQuery-2.1.4.min.js"></script>
<!-- layer -->
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/layer/extend/layer.ext.js"></script>
<script language="JavaScript"> 
	if (window != top) {
		top.location.href = location.href;
	}
</script>
<!-- js国际化 -->
<%-- <script src="${pageContext.request.contextPath}/onlineTest/${system_lang}.js"></script> --%>
</head>
<body class="login" mycollectionplug="bind">
<div id="loading-mask" style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #DEDEDE; z-index: 20000; opacity:0.4; display:none;">
	<div id="pageloading" style="position: absolute; top: 50%; left: 50%; margin: -120px 0px 0px -120px; text-align: center; width: 200px; height: 40px; font-size: 14px; padding: 10px; font-weight: bold; color: #15428B;">
		<img src="${pageContext.request.contextPath}/common/image/loadingpn.gif" align="middle" />
	</div>
</div>
<div class="login_m">
<div class="login_logo" style="margin-bottom:35px;">
<img src="${pageContext.request.contextPath}/common/image/logo2.png" width="800" height="120" style="margin-left:-200px;" /></div>
<div class="login_boder" style="width:800px;margin-left:-200px;">
<div class="login_padding" id="login_model" style="padding: 30px 100px 50px 100px;">
<form id="loginForm">
  <h2 style="">username</h2>
  <label>
    <input type="text" id="username" name="username" class="txt_input txt_input2" 
    onfocus="if (value ==&#39;Mobile&#39;){value =&#39;&#39;}" 
    onblur="if (value ==&#39;&#39;){value=&#39;Mobile&#39;}" value="Mobile"
    style="width:590px;" />
  </label>
  <h2 style="">Password</h2>
  <label>
    <input type="password" name="password" id="userpwd" 
    class="txt_input" onfocus="if (value ==&#39;******&#39;){value =&#39;&#39;}" 
    onblur="if (value ==&#39;&#39;){value=&#39;******&#39;}" value="******"
    style="width:590px;" />
  </label>
  <input type="hidden" id="paperNo" name="paperNo" value="" />
  <input type="hidden" id="planId" name="planId" value="" />
</form>
  <p class="">
  <span id="msg" style="color:red;">&nbsp;&nbsp;&nbsp;</span>
  <p class="forgot" style="margin-bottom:5px;">
  <div class="rem_sub">
  <div class="rem_sub_l">
  <!-- <input type="checkbox" name="checkbox" id="save_me">
   <label for="checkbox" style="">Remember me</label> -->
   </div>
    <label>
      <input type="button" class="sub_button" id="sign"
      name="button" id="button" value="SIGN-IN" 
      style="opacity: 0.7;" onclick="loginExamSystem()" />
    </label>
  </div>
</div>
<div class="copyrights"></div>
<div id="forget_model" class="login_padding" style="display:none">
<br>
   <h1>Forgot password</h1>
   <br>
   <div class="forget_model_h2">(Please enter your registered email below and the system will automatically reset users’ password and send it to user’s registered email address.)</div>
    <label>
    <input type="text" id="usrmail" class="txt_input txt_input2" />
   </label>

  <div class="rem_sub">
  <div class="rem_sub_l">
   </div>
    <label>
     <input type="submit" class="sub_buttons" name="button" id="Retrievenow" value="Retrieve now" style="opacity: 0.7;" />　　
     <input type="submit" class="sub_button" name="button" id="denglou" value="Return" style="opacity: 0.7;" />　　  
    </label>
  </div>
</div>


<!--login_padding  Sign up end-->
</div><!--login_boder end-->
</div><!--login_m end-->
 <br> <br>
<!-- <p align="center" style="margin-top:40px;"> 
更多信息 
<a href="#" target="_blank" title="复旦">复旦</a> 
- More Information 
<a href="#" target="_blank" title="FuDan">FuDan</a> 
</p> -->
</body></html>
<script>
///////////////////////////////////////////
var userAgent = navigator.userAgent,     
rMsie = /(msie\s|trident.*rv:)([\w.]+)/,     
rFirefox = /(firefox)\/([\w.]+)/,     
rOpera = /(opera).+version\/([\w.]+)/,     
rChrome = /(chrome)\/([\w.]+)/,     
rSafari = /version\/([\w.]+).*(safari)/;    
var browser;    
var version;    
var ua = userAgent.toLowerCase();    
var match = rChrome.exec(ua);    
if (match== null) { 
	document.getElementById('sign').disabled = "disabled";
	layer.confirm('Please use Chrome browser,download now?<br>请使用Chrome浏览器，立即下载？', {
 	 	btn: ['Confirm','Cancel'], //按钮
 	 	title:"Prompt"
	}, 
	function(index){
		window.open("../onlineTest/online!downloadChrome.do");
		parent.layer.close(index);
	},
	function(){
		
	});
}else{
	layer.alert("USERNAME - Your mobile<br>PASSWORD - Your last six ID number<br>用户名 - 手机号<br>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码 - 身份证后六位",
	{"title":"Login-Prompt 登录提示",
		shade:0,
		time:0,
		offset: 'rb',
		shift:6,
		btn:["Close"],
		closeBtn:0
	});
	document.onkeydown = function(e) {
		var event = e || window.event;
		var code = event.keyCode || event.which || event.charCode;
		if (code == 13) { 
			//按下回车键，可以进行登录
			$("#button").focus();
			loginExamSystem(); 
		}
	};
}
//   var match = rSafari.exec(ua);    
//   if (match != null) {    
//     return { browser : match[2] || "", version : match[1] || "0" };    
//   }    
//   if (match != null) {    
//     return { browser : "", version : "0" };    
//   }    
//    var match = rMsie.exec(ua);    
//   if(match != null){    
// 		window.open("../onlineTest/online!downloadChrome.do"); 
//   }    
//   var match = rFirefox.exec(ua);    
//   if (match != null) {    
//     return { browser : match[1] || "", version : match[2] || "0" };    
//   }    
//   var match = rOpera.exec(ua);    
//   if (match != null) {    
//     return { browser : match[1] || "", version : match[2] || "0" };    
//   }  
///////////////////////////////////////////
var url=window.location.href;
if(url.indexOf("!")>0){//本地测试
	$("#paperNo").val("${requestScope.paper_no}");
	$("#planId").val("${requestScope.plan_id}");
}else{//简写形式
	var paramArr=url.split("/");
	var paramArr_size=paramArr.length;
	var paper_no_=paramArr[paramArr_size-1].substring(0,10);
	var plan_id_=paramArr[paramArr_size-2];
	console.info(paper_no_+"--"+plan_id_);
	$("#paperNo").val(paper_no_);
	$("#planId").val(plan_id_);
}


function loginExamSystem(){
	document.getElementById("msg").style.color="red";
	document.getElementById("msg").innerHTML="&nbsp;&nbsp;&nbsp;";
	var user = document.getElementById("username").value;
	var pwd = document.getElementById("userpwd").value;
	//判断信息
	if(user=="UserName"){
		//没有输入名字
		document.getElementById("msg").innerHTML="请输入账户! - Please enter the account!";
	}else{
		if(pwd=="******"){
			//没有输入密码
			document.getElementById("msg").innerHTML="请输入密码! - Please enter a password!";
		}else{
			$("#loading-mask").fadeIn();
			//登录方法		
			$.ajax({
                type: "POST",
                url:"../onlineTest/online!loginExamSystem.do?request_locale=en_US",
                data:$('#loginForm').serialize(),// 你的formid
                success: function(data) {
                	if(data.success=="true"){
	                	var re = data.message;
						if(re=="0000"){
							//成功
							document.getElementById("msg").style.color="blue";
							document.getElementById("msg").innerHTML="登陆成功! - Login success ! ";
		                	
							var hasTestedFlag=data.datas.hasTestedFlag;
							if(hasTestedFlag!=="0"){
								layer.msg("You have already taken this exam. You cannot answer it again.");//cannotAnswer
							}else{
								var ppInfo=data.datas.planPaperInfo;
			                	var startTime=ppInfo.paperStartTime;
			                	var endTime=ppInfo.paperEndTime;
			                	var pstartTime=ppInfo.pstartTime;
			                	var totalTime=ppInfo.paperLongTime;
								var planPaperId=ppInfo.id;
								var duringExamTimeFlag=data.datas.duringExamTimeFlag;
								var paper_Id=data.datas.paperId;
								var nowTime=ppInfo.nowTime;
			                	var paperNo=document.getElementById("paperNo").value;
			                	var planId=document.getElementById("planId").value;
			                	console.info("paperId="+paper_Id+" paperNo="+paperNo+" startTime="+startTime
										+" endTime="+endTime+" totalTime="+totalTime+" planPaperId="+planPaperId+" planId="+planId);
								console.info("paperId="+paper_Id+" paperNo="+paperNo+" pstartTime="
										+pstartTime+" startTime="+startTime+" endTime="+endTime+" totalTime="+totalTime+" planPaperId="+planPaperId+" planId="+planId+" nowTime="+nowTime);
								if(duringExamTimeFlag=="0"){
									window.location.href="../onlineTest/online!toWelcomeTestPage.do?paperId="+paper_Id+"&paperNo="+paperNo+"&startTime="+startTime
											+"&endTime="+endTime+"&totalTime="+totalTime+"&planPaperId="+planPaperId+"&planId="+planId;
								}else if(duringExamTimeFlag=="1"){
									window.location.href="../onlineTest/online!toExamWaitPage.do?paperId="+paper_Id+"&paperNo="+paperNo+"&pstartTime="
											+pstartTime+"&startTime="+startTime+"&endTime="+endTime+"&totalTime="+totalTime+"&planPaperId="+planPaperId+"&planId="+planId+"&nowTime="+nowTime;
								}else if(duringExamTimeFlag=="2"){
									layer.msg("The exam time is over. You cannot take this exam anymore");//examEndPrompt
								}
							}
							
						}else if(re=="1111"){
							//没有用户信息
							document.getElementById("msg").innerHTML="没有此用户! - No user! Please enter your account number.";
						}else if(re=="2222"){
							//密码不正确
							document.getElementById("msg").innerHTML="密码不正确! - Incorrect password! Please enter your password again.";
						}else if(re=="3333"){
							//用户被禁用
							document.getElementById("msg").innerHTML="您已被禁用! - Your account has been suspended.";
						}			
                		
                	}else{
                		layer.alert("An error occurs or the test paper does not exist!",{"title":"Error"});//paperNotExsit
                	}
                	$("#loading-mask").fadeOut();
                }
            });
		}
	}
}
</script>