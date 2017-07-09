<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="base.jsp"></jsp:include>
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="assets/gritter/css/jquery.gritter.css" />
<!-- Custom styles for this template -->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />
<style type="text/css">
</style>
<title>首页</title>
</head>
<body>
	<section id="main-content">
		<!--carousel start-->
		<section class="wrapper">
			<div id="c-slide" class="carousel slide auto">
				<ol class="carousel-indicators out">
					<li class="active" data-slide-to="0" data-target="#c-slide"></li>
					<li class="" data-slide-to="1" data-target="#c-slide"></li>
					<li class="" data-slide-to="2" data-target="#c-slide"></li>
					<li class="" data-slide-to="3" data-target="#c-slide"></li>
					<li class="" data-slide-to="4" data-target="#c-slide"></li>
					<li class="" data-slide-to="5" data-target="#c-slide"></li>
				</ol>
				<div class="carousel-inner">
					<div class="item  active">
						<img class="img-rounded img-responsive" alt="1"
							src="img/news/1.jpg" style="width: 1366px; height: 450px">
						<div class="carousel-caption">标题1</div>
					</div>
					<div class="item text-center">
						<img class="img-rounded img-responsive" alt="2"
							src="img/news/2.jpg" style="width: 1366px; height: 450px">
						<div class="carousel-caption">标题2</div>
					</div>
					<div class="item text-center">
						<img class="img-rounded img-responsive" alt="3"
							src="img/news/3.jpg" style="width: 1366px; height: 450px">
						<div class="carousel-caption">标题3</div>
					</div>
					<div class="item text-center">
						<img class="img-rounded img-responsive" alt="3"
							src="img/news/4.jpg" style="width: 1366px; height: 450px">
						<div class="carousel-caption">标题4</div>
					</div>
					<div class="item text-center">
						<img class="img-rounded img-responsive" alt="3"
							src="img/news/5.jpg" style="width: 1366px; height: 450px">
						<div class="carousel-caption">标题5</div>
					</div>
					<div class="item text-center">
						<img class="img-rounded img-responsive" alt="3"
							src="img/news/6.jpg" style="width: 1366px; height: 450px">
						<div class="carousel-caption">标题6</div>
					</div>
				</div>
				<a data-slide="prev" href="#c-slide" class="left carousel-control">
					<i class="icon-angle-left"></i>
				</a> <a data-slide="next" href="#c-slide" class="right carousel-control">
					<i class="icon-angle-right"></i>
				</a>
			</div>
			<c:forEach var="an" items="${allNews }">
				<aside class="col-lg-3">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<span>${an.n_title }</span> <span class="pull-rignt"
								style="font-size: x-small;">${an.n_send_date }</span>
						</div>
						<div class="panel-body" style="word-break: break-all">${an.n_content }</div>
					</div>
				</aside>
			</c:forEach>
		</section>
		<!--carousel end-->
	</section>
	<!--main content end-->
	</section>

	<!--script for this page only-->
	<script src="js/gritter.js"></script>
</body>
</html>