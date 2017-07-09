<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="base.jsp"></jsp:include>
<!-- bootstrap table -->
<title>校园活动</title>
</head>
<body>
	<section id="main-content">
		<section class="wrapper">
			<!-- page start-->
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading">
						<span>校园活动</span>
					</header>
					<form>
						<div class="row">
							<div class="col-lg-4">
								<input id="searchAcId" class="form-control" type="text"
									placeholder="按活动ID搜索">
							</div>
							<div class="col-lg-4">
								<input id="searchAcName" class="form-control" type="text"
									placeholder="按活动名称搜索">
							</div>
							<div class="col-lg-4">
								<input class="btn btn-danger pull-right" type="reset"
									value="清空条件"> <input class="btn btn-primary pull-right"
									id="bgAcSearchBtn" value="搜索" type="button">
							</div>
						</div>
					</form>
					<div id="toolbar" class="btn-group">
						<button id="btn_apply" type="button"
							class="btn btn-sm btn-primary">
							<span class="icon-plus" aria-hidden="true"></span>报名
						</button>
					</div>
					<table id="activityTbl"></table>

					<!-- 查看详情模态框开始 -->
					<div class="modal fade center" id="check_detail" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title">查看活动详情</h4>
								</div>
								<div class="modal-body" style="word-break: break-all">
									<h4 id="acName" align="center"></h4>
									<p id="acDetail"></p>
								</div>
								<div class="modal-body"></div>
								<div class="modal-footer">
									<button data-dismiss="modal" class="btn btn-default"
										type="button">关闭</button>
								</div>
							</div>
						</div>
					</div>
					<!-- 查看详情模态框结束 -->
				</section>
			</div>
			</div>
			<!-- page end-->
		</section>
	</section>
</body>
<script src="js/own/activity.js"></script>
</html>