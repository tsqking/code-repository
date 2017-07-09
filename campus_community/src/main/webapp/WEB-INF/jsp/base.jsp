<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Mosaddek">
<meta name="keyword"
	content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-reset.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
<!-- Custom styles for this template -->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />
<!-- emoji -->
<link href="css/jquery.mCustomScrollbar.min.css" rel="stylesheet" />
<link href="css/jquery.emoji.css" rel="stylesheet" />
<!-- table -->
<link href="css/bootstrap-table.css" rel="stylesheet" />

</head>

<body style="background-image: url('img/background.jpg');">
	<section id="container">
		<!--header start-->
		<header class="header white-bg">
			<div class="sidebar-toggle-box">
				<div data-original-title="切换" data-placement="right"
					class="icon-reorder tooltips"></div>
			</div>
			<!--logo start-->
			<a href="index" class="logo">校园<span>社区</span></a>
			<!--logo end-->
			<div class="nav notify-row" id="top_menu">

				<ul class="nav top-menu">
					<li id="header_inbox_bar" class="dropdown"><a
						data-toggle="dropdown" class="dropdown-toggle" href="#"> <i
							class="icon-envelope-alt"></i> <span class="badge bg-important">
						信箱</span>
					</a>
						<ul class="dropdown-menu extended inbox">
							<div class="notify-arrow notify-arrow-red"></div>
							<li>
								<p class="red">点击下方查看信息</p>
							</li>
							<li><a href="zone">查看所有信息</a></li>
						</ul></li>

					<a href="manage">
						<button class="btn btn-sm btn-danger" id="bgMngBtn">后台管理</button>
					</a>
				</ul>
				<!--  notification end -->
			</div>
			<div class="top-nav ">
				<!--search & user info start-->
				<ul class="nav pull-right top-menu">
					<!-- user login dropdown start-->
					<li class="dropdown"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <img
							alt="${userinfo.u_nickname }" src="${userinfo.u_face }"
							style="width: 35px; height: 35px"> <span>${userinfo.u_nickname }</span>
							<b class="caret"></b>
					</a>
						<ul class="dropdown-menu extended logout">
							<div class=""></div>
							<li><a href="self"><i class="icon-suitcase"></i>个人资料</a></li>
							<li><a href="#"><i class="icon-money"></i>当前积分:${userinfo.u_point }<span
									id="pointSpan"></span></a></li>
							<li><a href="update_pwd"><i class="icon-user"></i> 修改密码</a></li>
							<li><a href="login"><i class="icon-key"></i>注销 登录</a></li>
						</ul></li>
					<!-- user login dropdown end -->
				</ul>
				<!--search & user info end-->
			</div>
		</header>
		<!--header end-->
		<!--sidebar start-->
		<aside>
			<div id="sidebar" class="nav-collapse ">
				<!-- sidebar menu start-->
				<ul class="sidebar-menu">
					<li class="active"><a class="" href="index"> <i
							class="icon-home"></i> <span>首页</span>
					</a></li>
					<li class="active"><a class="" href="weibo"> <i
							class="icon-weibo"></i> <span>校内微博</span>
					</a></li>
					<li class="active"><a class="" href="friend"> <i
							class="icon-male"></i> <span>朋友圈</span>
					</a></li>
					<li class="active"><a class="" href="study"> <i
							class="icon-book"></i> <span>学习天地</span>
					</a></li>
					<li class="active"><a class="" href="activity"> <i
							class="icon-camera-retro"></i> <span>校园活动</span>
					</a></li>
					<li class="active"><a class="" href="market"> <i
							class="icon-stackexchange"></i> <span>跳蚤市场</span>
					</a></li>
					<li class="active"><a class="" href="zone"> <i
							class="icon-user"></i> <span>我的地盘</span>
					</a></li>
					<li class="active"><a data-toggle="modal" href="#modal"> <i
							class="icon-bullhorn"></i> <span>关于</span>
					</a></li>
				</ul>
				<!-- sidebar menu end-->
			</div>
		</aside>
		<!--sidebar end-->
		<footer class="site-footer">
			<div class="text-center">
				2013 © FlatLab by VectorLab. <a class="go-top" href="#"> <i
					class="fa fa-angle-up"></i>
				</a>
			</div>
		</footer>
	</section>

	<div class="modal fade center" id="modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">关于</h4>
				</div>
				<div class="modal-body text-info">本校园社区，从设计到实现均由谭世强先生一人完成。使用本程序，需征得本人同意。任何非法盗用行为将被依法追究法律责任。</div>
				<div class="modal-body text-info text-center">
					版权所有，仿冒必究<br> 2017 &copy; Campus Community by Charles.Tan
				</div>
				<div class="modal-footer">
					<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<!-- js placed at the end of the document so the pages load faster -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.scrollTo.min.js"></script>
	<script src="js/jquery.nicescroll.js"></script>
	<script src="js/jquery.stepy.js"></script>

	<!--common script for all pages-->
	<script src="js/common-scripts.js"></script>

	<!-- cookie -->
	<script src="js/own/jquery.cookie.js"></script>

	<!-- 用于emoji 注意要先引用jquery和jquery.mCustomScrollbar，再引用该js-->
	<script src="js/emoji/jquery.mCustomScrollbar.min.js"></script>
	<script src="js/emoji/jquery.mousewheel-3.0.6.min.js"></script>
	<script src="js/emoji/highlight.pack.js"></script>
	<script src="js/emoji/jquery.emoji.js"></script>

	<!-- table -->
	<script src="js/table/bootstrap-table.js"></script>
	<script src="js/table/bootstrap-table-zh-CN.js"></script>

	<!-- 限制未登录用户不能访问 -->
	<c:if test="${msg!=null&&userinfo==null }">
		<script type="text/javascript">
			var msg = "${msg}";
			alert(msg);
			window.location.href = "login";
		</script>
	</c:if>
	<!-- 限制非管理员禁止进入后台管理模块 -->
	<c:if test="${userinfo!=null }">
		<script type="text/javascript">
			var type = "${userinfo.u_type}";
			if (type == 1) {
				$("#bgMngBtn").attr({
					"disabled" : "disabled"
				});
			}
			if (type == 0) {
				$("#bgMngBtn").removeAttr("disabled");
			}
		</script>
	</c:if>
</body>
</html>
