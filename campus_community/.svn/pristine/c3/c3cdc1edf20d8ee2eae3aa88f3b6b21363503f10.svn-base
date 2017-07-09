<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="base.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的地盘</title>
</head>
<body>
	<!--main content start-->
	<section id="main-content">
		<section class="wrapper">
			<!--mail inbox start-->
			<div class="mail-box">
				<aside class="sm-side">
					<div class="user-head">
						<a href="javascript:;" class="inbox-avatar"> <img
							src="${userinfo.u_face }" alt="头像"
							style="width: 60px; height: 60px">
						</a>
						<div class="user-name">
							<h5>
								<a href="#">${userinfo.u_nickname }</a>
							</h5>
							<span><a href="#">${userinfo.u_motto }</a></span>
						</div>
					</div>
					<div class="inbox-body" id="sendDiv">
						<a class="btn btn-compose" data-toggle="modal"
							href="javascript:void(0)"> 写信息 </a>
					</div>
					<ul class="inbox-nav inbox-divider" style="height: 400px;">
						<li class="active"><a data-toggle="tab" href="#unread_div"><i
								class="icon-envelope-alt"></i> 未读信息 <!-- <span
								class="label label-danger pull-right">1</span> --></a></li>
						<li><a data-toggle="tab" href="#read_div"><i
								class="icon-inbox"></i> 已读信息</a></li>
						<li><a data-toggle="tab" href="#important_div"><i
								class="icon-bookmark-empty"></i> 重要信息</a></li>
						<li><a data-toggle="tab" href="#trash_div"><i
								class=" icon-trash"></i> 垃圾箱</a></li>
						<li><a href="myfriend"><button
									class="btn btn-success btn-block">好友列表</button></a></li>
						<li><a href="black"><button
									class="btn btn-danger btn-block">黑名单</button></a></li>
					</ul>
				</aside>

				<aside class="lg-side">
					<div class="tab-content">
						<div class="tab-pane active" id="unread_div">
							<div class="inbox-head">
								<h3>未读信息</h3>
							</div>
							<div class="inbox-body">
								<form>
									<div class="row">
										<div class="col-lg-3">
											<input id="searchUnreadTitle" class="form-control"
												type="text" placeholder="按信息标题搜索">
										</div>
										<div class="col-lg-3">
											<input id="searchUnreadContent" class="form-control"
												type="text" placeholder="按信息内容搜索">
										</div>
										<div class="col-lg-3">
											<input id="searchUnreadSender" class="form-control"
												type="text" placeholder="按发件人搜索">
										</div>
										<div class="col-lg-3">
											<input class="btn btn-danger pull-right" type="reset"
												value="清空条件"> <input
												class="btn btn-primary pull-right" id="unreadSearchBtn"
												value="搜索" type="button">
										</div>
									</div>
								</form>
								<div class="mail-option" id="unreadToobar">
									<div class="btn-group hidden-phone">
										<a class="btn mini blue" href="#" data-toggle="dropdown">
											更多操作 <i class="icon-angle-down "></i>
										</a>
										<ul class="dropdown-menu">
											<li id="batchToRead"><a
												href="javascript:void(0)><i class="icon-pencil"></i> 标记为已读</a></li>
											<li id="deleteUnread"><a href="javascript:void(0)><i class="icon-trash"></i> 删除</a></li>
										</ul>
									</div>
									<div class="btn-group">
										<a class="btn mini blue" href="javascript:void(0)" data-toggle="dropdown">
											移动到 <i class="icon-angle-down "></i>
										</a>
										<ul class="dropdown-menu">
											<li id="mvToImp"><a href="javascript:void(0)><i class="icon-pencil"></i> 重要信息</a></li>
											<li id="mvToTrash"><a href="javascript:void(0)><i class="icon-ban-circle"></i> 垃圾箱</a></li>
										</ul>
									</div>
								</div>
								<table class="table table-inbox table-hover" id="unreadTbl"></table>
							</div>
						</div>


						<div class="tab-pane" id="read_div">
							<div class="inbox-head">
								<h3>已读信息</h3>
							</div>
							<div class="inbox-body">
								<form>
									<div class="row">
										<div class="col-lg-3">
											<input id="searchReadTitle" class="form-control"
												type="text" placeholder="按信息标题搜索">
										</div>
										<div class="col-lg-3">
											<input id="searchReadContent" class="form-control"
												type="text" placeholder="按信息内容搜索">
										</div>
										<div class="col-lg-3">
											<input id="searchReadSender" class="form-control"
												type="text" placeholder="按发件人搜索">
										</div>
										<div class="col-lg-3">
											<input class="btn btn-danger pull-right" type="reset"
												value="清空条件"> <input
												class="btn btn-primary pull-right" id="readSearchBtn"
												value="搜索" type="button">
										</div>
									</div>
								</form>
								<div class="mail-option" id="readToobar">
									<div class="btn-group hidden-phone">
										<a class="btn mini blue" href="javascript:void(0)" data-toggle="dropdown">
											更多操作 <i class="icon-angle-down "></i>
										</a>
										<ul class="dropdown-menu">
											<li id="deleteRead2"><a href="javascript:void(0)"><i class="icon-trash"></i> 删除</a></li>
										</ul>
									</div>
									<div class="btn-group">
										<a class="btn mini blue" href="javascript:void(0)" data-toggle="dropdown">
											移动到 <i class="icon-angle-down "></i>
										</a>
										<ul class="dropdown-menu">
											<li id="mvToImp2"><a href="javascript:void(0)><i class="icon-pencil"></i> 重要信息</a></li>
											<li id="mvToTrash2"><a href="javascript:void(0)><i class="icon-ban-circle"></i> 垃圾箱</a></li>
										</ul>
									</div>
								</div>
								<table class="table table-inbox table-hover" id="readTbl"></table>
							</div>
						</div>



						<div class="tab-pane" id="important_div">
							<div class="inbox-head">
								<h3>重要信息</h3>
							</div>
							<div class="inbox-body">
								<form>
									<div class="row">
										<div class="col-lg-3">
											<input id="searchImpTitle" class="form-control"
												type="text" placeholder="按信息标题搜索">
										</div>
										<div class="col-lg-3">
											<input id="searchImpContent" class="form-control"
												type="text" placeholder="按信息内容搜索">
										</div>
										<div class="col-lg-3">
											<input id="searchImpSender" class="form-control"
												type="text" placeholder="按发件人搜索">
										</div>
										<div class="col-lg-3">
											<input class="btn btn-danger pull-right" type="reset"
												value="清空条件"> <input
												class="btn btn-primary pull-right" id="impSearchBtn"
												value="搜索" type="button">
										</div>
									</div>
								</form>
								<div class="mail-option" id="impToobar">
									<div class="btn-group hidden-phone">
										<a class="btn mini blue" href="javascript:void(0)" data-toggle="dropdown">
											更多操作 <i class="icon-angle-down "></i>
										</a>
										<ul class="dropdown-menu">
											<li id="deleteImp"><a href="javascript:void(0)"><i class="icon-trash"></i> 删除</a></li>
										</ul>
									</div>
									<div class="btn-group">
										<a class="btn mini blue" href="javascript:void(0)" data-toggle="dropdown">
											移动到 <i class="icon-angle-down "></i>
										</a>
										<ul class="dropdown-menu">
											<li id="mvToRead"><a href="javascript:void(0)><i class="icon-pencil"></i> 收件箱</a></li>
											<li id="mvToTrash3"><a href="javascript:void(0)><i class="icon-ban-circle"></i> 垃圾箱</a></li>
										</ul>
									</div>
								</div>
								<table class="table table-inbox table-hover" id="impTbl"></table>
							</div>
						</div>


						<div class="tab-pane" id="trash_div">
							<div class="inbox-head">
								<h3>垃圾箱</h3>
							</div>
							<div class="inbox-body">
								<form>
									<div class="row">
										<div class="col-lg-3">
											<input id="searchTrashTitle" class="form-control"
												type="text" placeholder="按信息标题搜索">
										</div>
										<div class="col-lg-3">
											<input id="searchTrashContent" class="form-control"
												type="text" placeholder="按信息内容搜索">
										</div>
										<div class="col-lg-3">
											<input id="searchTrashSender" class="form-control"
												type="text" placeholder="按发件人搜索">
										</div>
										<div class="col-lg-3">
											<input class="btn btn-danger pull-right" type="reset"
												value="清空条件"> <input
												class="btn btn-primary pull-right" id="trashSearchBtn"
												value="搜索" type="button">
										</div>
									</div>
								</form>
								<div class="mail-option" id="trashToobar">
									<div class="btn-group hidden-phone">
										<a class="btn mini blue" href="#" data-toggle="dropdown">
											更多操作 <i class="icon-angle-down "></i>
										</a>
										<ul class="dropdown-menu">
											<li id="deleteTrash"><a href="javascript:void(0)><i class="icon-trash"></i> 删除</a></li>
										</ul>
									</div>
								</div>
								<table class="table table-inbox table-hover" id="trashTbl"></table>
							</div>
						</div>

					</div>
				</aside>
			</div>
		</section>
	</section>
	</section>
	
<!-- 发送信息模态框开始 -->
<div class="modal fade" id="sendModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">写信息</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-lg-2 control-label">收信人</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" id="inputRec">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">标题</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" id="inputMessageTitle">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">信息内容</label>
						<div class="col-lg-10">
							<textarea id="inputMessageContent" class="form-control" cols="30"
								rows="10"></textarea>
						</div>
					</div>

					<div class="form-group modal-footer" >
						<div class="col-lg-offset-2 col-lg-10">
							<button id="sendMessageBtn" type="button" class="btn btn-info">发送</button>
							<button type="reset" class="btn btn-reset">重置</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- 模态框结束 -->

<!-- 查看详情模态框开始 -->
<div class="modal fade center" id="msgDetail" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">查看信息详情</h4>
			</div>
			<div class="modal-body" style="word-break: break-all">
				<h4 id="msgTitle" align="center"></h4>
				<p id="msgContent"></p>
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
</body>
<script src="js/own/unread.js"></script>
<script src="js/own/read.js"></script>
<script src="js/own/imp.js"></script>
<script src="js/own/sendMessage.js"></script>
<script src="js/own/sendBox.js"></script>
<script src="js/own/trash.js"></script>
</html>