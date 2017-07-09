<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-reset.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
<!-- Custom styles for this template -->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />
<title>个人资料</title>
</head>
<body>
		<section>
			<!-- page start-->
			<div class="">
				<div class="col-lg-12">
					<section class="panel" style="height: 620px;">
						<header class="panel-heading"> 找回密码 </header>
						<div class="panel-body">
							<div class="stepy-tab">
								<ul id="default-titles" class="stepy-titles clearfix">
									<li id="default-title-0" class="current-step">
										<div>第一步</div>
									</li>
									<li id="default-title-1" class="">
										<div>第二步</div>
									</li>
									<li id="default-title-2" class="">
										<div>第三步</div>
									</li>
								</ul>
							</div>
							<form class="form-horizontal" id="default">

								<fieldset title="第一步" class="step" id="default-step-0">
									<legend> </legend>
									<div class="form-group">
										<label class="col-lg-2 control-label">请输入学号或工号</label>
										<div class="col-lg-10">
											<input type="text" class="form-control" placeholder="学号或者工号">
										</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label">请输入用户名</label>
										<div class="col-lg-10">
											<input type="text" class="form-control" placeholder="昵称">
										</div>
									</div>
								</fieldset>

								<fieldset title="第二步" class="step" id="default-step-1">
									<legend> </legend>
									<div class="form-group">
										<label class="col-lg-2 control-label">请输入密保问题</label>
										<div class="col-lg-10">
											<input type="text" class="form-control" placeholder="密保问题">
										</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label">请输入密保问题答案</label>
										<div class="col-lg-10">
											<input type="text" class="form-control" placeholder="密保问题答案">
										</div>
									</div>
								</fieldset>

								<fieldset title="第三步" class="step" id="default-step-2">
									<legend> </legend>
									<div class="form-group">
										<label class="col-lg-2 control-label">请设置新的密码</label>
										<div class="col-lg-10">
											<input type="password" class="form-control" placeholder="密码">
										</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 control-label">重复输入密码</label>
										<div class="col-lg-10">
											<input type="password" class="form-control"
												placeholder="重复输入密码">
										</div>
									</div>
								</fieldset>

								<input type="button" class="finish btn btn-danger" value="提交" />
							</form>
						</div>

					</section>
				</div>
			</div>
	</section>
</body>
<!-- js placed at the end of the document so the pages load faster -->
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.scrollTo.min.js"></script>
<script src="js/jquery.nicescroll.js" type="text/javascript"></script>

<!--common script for all pages-->
<script src="js/common-scripts.js"></script>
<script src="js/own/jquery.cookie.js"></script>

<script src="js/jquery.stepy.js"></script>
<script>
	//step wizard

	$(function() {
		$('#default').stepy({
			backLabel : '上一步',
			block : true,
			nextLabel : '下一步',
			titleClick : true,
			titleTarget : '.stepy-tab'
		});
	});
</script>
</html>
