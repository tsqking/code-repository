<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="base.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的好友</title>
</head>
<body>
	<section id="main-content">
		<section class="wrapper">
			<!-- page start-->
			<div class="row">
				<div class="col-lg-12">
					<section class="panel" style="height: 580px;">
						<header class="panel-heading">
							<span>我的好友</span>
						</header>
						<form>
							<div class="row">
								<div class="col-lg-4">
									<input id="searchUserId" class="form-control" type="text"
										placeholder="按好友ID搜索">
								</div>
								<div class="col-lg-4">
									<input id="searchUsername" class="form-control" type="text"
										placeholder="按好友用户名搜索">
								</div>
								<div class="col-lg-4">
									<input class="btn btn-danger pull-right" type="reset"
										value="清空条件"> <input
										class="btn btn-primary pull-right" id="friendSearchBtn"
										value="搜索" type="button">
								</div>
							</div>
						</form>
						<div id="toolbar_myfriend">
							<button id="btn_addfriend" type="button"
								class="btn btn-info btn-sm">
								<span class="icon-plus" aria-hidden="true"></span>添加
							</button>
							<button id="btn_deletefriend" type="button"
								class="btn btn-danger btn-sm">
								<span class="icon-remove" aria-hidden="true"></span>删除
							</button>
							<button id="btn_friendToBlack" type="button"
								class="btn btn-danger btn-sm">
								<span class="icon-remove" aria-hidden="true"></span>加入黑名单
							</button>
						</div>
						<table id="myfriendTbl"></table>
					</section>
				</div>
			</div>
			<!-- page end-->
		</section>
	</section>

	<!-- 添加好友模态框开始 -->
	<div class="modal fade responsive" id="find_user" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">添加好友</h4>
				</div>
				<div class="modal-body" style="margin: 30px;">
					<form class="form-horizontal" role="form">
						<div class="form-group input-group">
							<span class="input-group-btn">
								<button class="btn btn-white" type="button"
									style="width: 100px;" disabled="disabled">好友用户名:</button>
							</span> <input type="text" class="form-control" id="addfriendNickname">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button id="findfriendBtn" type="button" class="btn btn-primary">搜索</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 添加好友模态框结束 -->

	<!-- 查看好友信息模态框开始 -->
	<div class="modal fade responsive" id="fdetail" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">详情</h4>
				</div>
				<div class="modal-body"  id="userDetail">
					<div class="bio-graph-heading">
						<h2>用户资料</h2>
					</div>
					<div class="panel-body bio-graph-info">
						<h1>
							<img id="fImg" style="width: 50px; height: 50px">&nbsp;<span></span>
						</h1>
						<div class="row">
							<div class="bio-row">
								<p>
									<span>学号/工号:</span><span id="fid"></span>
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span>用户昵称 :</span><span id="fnickname"></span>
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span>姓名:</span><span id="fname"></span>
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span>性别:</span><span id="fgender"></span>
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span>年龄:</span><span id="fage"></span>
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span>邮箱 :</span><span id="femail"></span>
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span>地址:</span><span id="faddress"></span>
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span>电话:</span> <span id="fphone"></span>
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span>签名:</span> <span id="fmotto"></span>
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span>头像:</span><span><img id="fImg2" style="width: 50px; height: 50px"></span>
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="addfriendBtn" type="button" class="btn btn-primary">添加为好友</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 查看好友信息模态框结束 -->
</body>
<script src="js/own/myfriend.js"></script>
</html>