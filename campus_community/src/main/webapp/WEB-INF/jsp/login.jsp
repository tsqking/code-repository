<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Mosaddek">
<meta name="keyword"
	content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
<title>用户登录</title>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-reset.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
<!-- Custom styles for this template -->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />

</head>
<body class="login-body"
	style="background-image: url('img/background.jpg');">
	<div class="container">
		<form class="form-signin" action="login.do" method="post">
			<h2 class="form-signin-heading">登录</h2>
			<div class="login-wrap">
				<input id="loginNickname" type="text" class="form-control"
					placeholder="昵称" autofocus> <input id="loginPassword"
					type="password" class="form-control" placeholder="密码"> <label
					class="checkbox"> <input type="checkbox" value="1"><span
					class="pull-left">记住我 </span> <span class="pull-right"> <a
						href="forget"> 忘记密码？</a></span>
				</label> <input id="loginBtn" type="button"
					class="btn btn-lg btn-primary btn-block" value="登录"> <input
					type="reset" class="btn btn-lg btn-warning btn-block" value="重置">
				<span class="pull-right "> <a href="register"> 没有帐号？点击注册</a></span>
			</div>
		</form>
	</div>

</body>
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/own/jquery.cookie.js"></script>
<script src="js/own/login.js"></script>
</html>