<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="base.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学习天地</title>
</head>
<body>
	<section id="main-content">
		<section class="wrapper">
			<!-- page start-->
			<div class="row">
				<div class="col-lg-12">
					<section class="panel">
						<header class="panel-heading">
							<h4>学习天地</h4>
						</header>
						<form>
							<div class="row">
								<div class="col-lg-4">
									<input id="searchStuId" class="form-control" type="text"
										placeholder="按文章ID搜索">
								</div>
								<div class="col-lg-4">
									<input id="searchStuTitle" class="form-control" type="text"
										placeholder="按文章标题搜索">
								</div>
								<div class="col-lg-4">
									<input class="btn btn-danger pull-right" type="reset"
										value="清空条件"> <input
										class="btn btn-primary pull-right" id="searchStuBtn"
										value="搜索" type="button">
								</div>
							</div>
						</form>
						<div id="stuToolbar" class="btn-group">
							<button id="btn_publish" type="button"
								class="btn btn-sm btn-primary">
								<span class="icon-plus" aria-hidden="true"></span>发表文章
							</button>
						</div>
						<table id="studyTbl"></table>
					</section>
				</div>
			</div>
		</section>
	</section>

	<!-- 发布文章模态框 -->
	<div class="modal fade center" id="publishStudy" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">发表文章</h4>
				</div>
				<div class="modal-body text-info">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label class="col-lg-2 control-label">文章标题</label>
							<div class="col-lg-10">
								<input type="text" class="form-control" id="inputTitle"
									placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label">文章内容</label>
							<div class="col-lg-10">
								<textarea id="inputContent" class="form-control" cols="30" rows="10"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-offset-2 col-lg-10">
								<button id="publishBtn" type="button" class="btn btn-send">发表</button>
								<button type="reset" class="btn btn-reset">重置</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 发表文章模态框结束 -->

	<!-- 查看文章模态框开始 -->
	<div class="modal fade center" id="check_study" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">查看文章详情</h4>
				</div>
				<div class="modal-body" style="word-break: break-all">
					<h4 id="studyTitle" align="center"></h4>
					<p id="studyContent"></p>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 查看文章模态框结束 -->
</body>

<script src="js/own/study.js"></script>
</html>