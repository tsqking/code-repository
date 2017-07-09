<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
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
<!-- 系统字体 -->
<style type="text/css">
	@font-face {
		font-family: 'msyh';
		src: url('${pageContext.request.contextPath}/common/fonts/MicrosoftYaHeiGB.ttf') format('truetype');
	}
</style>
<script language="JavaScript"> 
	if (window != top) {
		top.location.href = location.href;
	}
</script>
</head>
<body class="login" mycollectionplug="bind"  oncut="return false;" oncontextmenu="return false;">
<div id="loading-mask" style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #DEDEDE; z-index: 20000; opacity:0.4; display:none;">
	<div id="pageloading" style="position: absolute; top: 50%; left: 50%; margin: -120px 0px 0px -120px; text-align: center; width: 200px; height: 40px; font-size: 14px; padding: 10px; font-weight: bold; color: #15428B;">
		<img src="${pageContext.request.contextPath}/common/image/loadingpn.gif" align="middle" />
	</div>
</div>
<div class="login_m">
<div class="login_logo" style="margin-bottom:35px;">
<img src="${pageContext.request.contextPath}/common/image/logo.png" width="800" height="100" style="margin-left:-200px;"></div>
<div class="login_boder" style="width:800px;margin-left:-200px;">
<div class="login_padding" id="login_model" style="padding: 30px 100px 50px 100px;">
<form id="loginForm">
  <h2 style="">用户名</h2>
  <label>
    <input type="text" id="username" name="username" class="txt_input txt_input2" 
    onfocus="if (value ==&#39;UserName / Mobile&#39;){value =&#39;&#39;}" 
    onblur="if (value ==&#39;&#39;){value=&#39;UserName / Mobile&#39;}" value="UserName / Mobile"
    style="width:590px;">
  </label>
  <h2 style="">密码</h2>
  <label>
    <input type="password" name="password" id="userpwd" 
    class="txt_input" onfocus="if (value ==&#39;******&#39;){value =&#39;&#39;}" 
    onblur="if (value ==&#39;&#39;){value=&#39;******&#39;}" value="******"
    style="width:590px;">
  </label>
</form>
  <p class="">
  <span id="msg" style="color:red;">&nbsp;&nbsp;&nbsp;</span>
  <p class="forgot" style="margin-bottom:5px;">
  <a id="iforget" href="javascript:forgetPassWord()" style="">Forgot your password?</a></p>
  <div class="rem_sub">
<!--   <div class="rem_sub_l">
  <input type="checkbox" name="checkbox" id="save_me">
   <label for="checkbox" style="">Remember me</label>
   </div> -->
    <label>
      <input type="button" class="sub_button" 
      name="button" id="button" value="SIGN-IN" 
      style="opacity: 0.7;" onclick="loginSystem()">
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
    <input type="text" id="usrmail" class="txt_input txt_input2">
   </label>

  <div class="rem_sub">
  <div class="rem_sub_l">
   </div>
    <label>
     <input type="submit" class="sub_buttons" name="button" id="Retrievenow" value="Retrieve now" style="opacity: 0.7;"/>　　
     <input type="submit" class="sub_button" name="button" id="denglou" value="Return" style="opacity: 0.7;"/>　　  
    </label>
  </div>
</div>


<!--login_padding  Sign up end-->
</div><!--login_boder end-->
</div><!--login_m end-->
 <br> <br>
<!-- <p align="center" style="margin-top:50px;"> 
更多信息 
<a href="http://www.clps.com.cn/" target="_blank" title="上海华钦软件技术有限公司">上海华钦软件技术有限公司</a> 
- More Information 
<a href="http://www.clps.com.cn/" target="_blank" title="CLPS">CLPS</a> 
</p> -->
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
	if (code == 13) {
		//按下回车键，可以进行登录
		$("#button").focus();
		loginSystem(); 
	}
};
	
//全屏
function launchFullscreen(element) {
	if(element.requestFullscreen) {
		element.requestFullscreen();
	} else if(element.mozRequestFullScreen) {
		element.mozRequestFullScreen();
	} else if(element.webkitRequestFullscreen) {
		element.webkitRequestFullscreen();
	} else if(element.msRequestFullscreen) {
		element.msRequestFullscreen();
	}
}
//退出全屏
function exitFullscreen() {
	if(document.exitFullscreen) {
		document.exitFullscreen();
	} else if(document.mozCancelFullScreen) {
		document.mozCancelFullScreen();
	} else if(document.webkitExitFullscreen) {
		document.webkitExitFullscreen();
	}
}
//launchFullscreen(document.documentElement);

function loginSystem(){
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
                url:'${pageContext.request.contextPath}/system/system!loginSystem.do',
                data:$('#loginForm').serialize(),// 你的formid
                success: function(data) {
                	if(data.success=="true"){
                		var re = data.message;
    					if(re=="0000"){
    						//成功
    						document.getElementById("msg").style.color="blue";
    						document.getElementById("msg").innerHTML="登陆成功! - Login success ! ";
    						window.location.href="${pageContext.request.contextPath}/system/system!toMainPage.do";
    					}else if(re=="1111"){
    						$("#loading-mask").fadeOut();
    						//没有用户信息
    						document.getElementById("msg").innerHTML="没有此用户! - No user! Please enter your account number.";
    					}else if(re=="2222"){
    						$("#loading-mask").fadeOut();
    						//密码不正确
    						document.getElementById("msg").innerHTML="密码不正确! - Incorrect password! Please enter your password again.";
    					}else if(re=="3333"){
    						$("#loading-mask").fadeOut();
    						//用户被禁用
    						document.getElementById("msg").innerHTML="您已被禁用! - Your account has been suspended.";
    					}		
                	}else{
                		$("#loading-mask").fadeOut();
                		document.getElementById("msg").innerHTML="登录失败！请刷新后重试~ - Login failed, please refresh the page and try again~";
                	}
                }
            });
		}
	}
}
/**
 * 忘记密码功能
 */
function forgetPassWord(){
	layer.prompt({
		title: '您的注册账号 Account',
		formType: 0 //prompt风格，支持0-2
	}, function(username){
		layer.prompt({title: '您的注册邮箱 Email', formType: 0}, function(email){
			var item_reg = /^[a-zA-Z0-9_]([a-zA-Z0-9_.-])+@([a-zA-Z0-9])+([.]{1}([a-zA-Z0-9])+)+/;//邮箱
			if(!item_reg.test(email)){
				layer.msg("邮箱格式不正确！<br>Wrong email format.");
	        }else{
	        	var index=layer.load();
				$.ajax({
	                type: "POST",
	                url:'${pageContext.request.contextPath}/system/system!forgetPassword.do',
	                data:{"username":username,"email":email},// 你的formid
	                success: function(data) {
	                	layer.closeAll('loading');
						if(data.success=="true"){
							if(data.message=="0"){//成功索取密码
								layer.msg("已成功索取密码，请前往邮箱查看<br>Get password from your receive box");
							}else if(data.message=="1"){//用户不存在
								layer.alert("用户不存在！<br>User not exist!",{"title":"不存在"});
							}else if(data.message=="2"){//用户邮箱不匹配
								layer.alert("用户邮箱不匹配！<br>User&Email not matched!",{"title":"不匹配"});
							}
						}else{
							layer.alert("系统出错了~<br>System wrong~",{"title":"反馈"});
							console.info(data.message);
						}
	                }
	            });
	        }
		});
	});
	 
}
</script>
</html>