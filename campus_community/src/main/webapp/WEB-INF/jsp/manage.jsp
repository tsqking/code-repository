<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="base.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>
</head>
<body>
	<section id="main-content">
		<section class="wrapper">
			<!-- page start-->
			<div class="row">
				<div class="col-lg-12">
					<section class="panel">
						<header class="panel-heading tab-bg-dark-navy-blue ">
							<ul class="nav nav-pills">
								<li class="active"><a data-toggle="tab" href="#back_news">新闻管理</a>
								</li>
								<li id="to_userMng" class=""><a data-toggle="tab"
									href="#back_user">用户管理</a></li>
								<li id="to_weiboMng" class=""><a data-toggle="tab"
									href="#back_weibo">微博管理</a></li>
								<li class=""><a data-toggle="tab" href="#back_study">文章管理</a></li>
								<li class=""><a data-toggle="tab" href="#back_activity">校园活动管理</a>
								</li>
								<li class=""><a data-toggle="tab"
									href="#back_activity_apply">校园活动报名管理</a></li>
								<li class=""><a data-toggle="tab" href="#back_market">跳蚤市场管理</a></li>
								<li class=""><a data-toggle="tab" href="#back_market_apply">商品申请管理</a></li>
							</ul>
						</header>
						<div class="panel-body">
							<div class="tab-content">
								<div id="back_news" class="tab-pane active">
									<form>
										<div class="row">
											<div class="col-lg-4">
												<input id="searchNewsTitle" class="form-control" type="text"
													placeholder="按新闻ID搜索">
											</div>
											<div class="col-lg-4">
												<input id="searchNewsContent" class="form-control"
													type="text" placeholder="按新闻标题搜索">
											</div>
											<div class="col-lg-4">
												<input class="btn btn-danger pull-right" type="reset"
													value="清空条件"> <input
													class="btn btn-primary pull-right" id="bgNewsSearchBtn"
													value="搜索" type="button">
											</div>
										</div>
									</form>
									<div id="toolbar_news">
										<button id="btn_addNews" type="button"
											class="btn btn-info btn-sm">
											<span class="icon-plus" aria-hidden="true"></span>添加
										</button>
										<button id="btn_deleteNews" type="button"
											class="btn btn-danger btn-sm">
											<span class="icon-remove" aria-hidden="true"></span>删除
										</button>
									</div>
									<table id="newsMngTbl"></table>

									<!-- 添加新闻模态框开始 -->
									<div class="modal fade responsive" id="add_news" role="dialog"
										aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title">添加新闻</h4>
												</div>
												<div class="modal-body" style="margin: 30px;">
													<form class="form-horizontal" role="form">
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">新闻标题:</button>
															</span> <input type="text" class="form-control"
																id="addNewsTitle">
														</div>
														<div class="form-group">
															<span class="input-group-btn">
																<button class="btn btn-white pull-up" type="button"
																	style="width: 100px;" disabled="disabled">新闻内容:</button>
															</span>
															<textarea class="form-control" id="addNewsContent"
																rows="8"></textarea>
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">是否可用:</button>
															</span> <select id="addNewsEnable" type="text"
																class="form-control">
																<option value="1" selected="selected">可用</option>
																<option value="0">不可用</option>
															</select>
														</div>
													</form>
												</div>
												<div class="modal-footer">
													<button id="addNewsBtn" type="button"
														class="btn btn-primary">提交更改</button>
													<button type="button" class="btn btn-danger"
														data-dismiss="modal">关闭</button>
												</div>
											</div>
										</div>
									</div>
									<!-- 添加新闻模态框结束 -->

									<!-- 修改新闻模态框开始 -->
									<div class="modal fade responsive" id="update_news"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title">新闻审核</h4>
												</div>
												<div class="modal-body" style="margin: 30px;">
													<form class="form-horizontal" role="form">
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">新闻ID:</button>
															</span> <input type="text" class="form-control"
																id="updateNewsId" disabled="disabled">
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">新闻标题:</button>
															</span> <input type="text" class="form-control"
																id="updateNewsTitle">
														</div>
														<div class="form-group">
															<span class="input-group-btn">
																<button class="btn btn-white pull-up" type="button"
																	style="width: 100px;" disabled="disabled">活动内容:</button>
															</span>
															<textarea class="form-control" id="updateNewsContent"
																rows="8"></textarea>
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">是否可用:</button>
															</span> <select id="updateNewsEnable" type="text"
																class="form-control">
																<option value="" selected="selected">&nbsp;</option>
																<option value="1">可用</option>
																<option value="0">不可用</option>
															</select>
														</div>
													</form>
												</div>
												<div class="modal-footer">
													<button id="updateNewsBtn" type="button"
														class="btn btn-primary">提交更改</button>
													<button type="button" class="btn btn-danger"
														data-dismiss="modal">关闭</button>
												</div>
											</div>
										</div>
									</div>
									<!-- 修改新闻模态框结束 -->
								</div>

								<!-- 用户管理模块 -->
								<div id="back_user" class="tab-pane">
									<form>
										<div class="row">
											<div class="col-lg-4">
												<input id="searchUserId" class="form-control" type="text"
													placeholder="按学号/工号搜索">
											</div>
											<div class="col-lg-4">
												<input id="searchUserName" class="form-control" type="text"
													placeholder="按用户名搜索">
											</div>
											<div class="col-lg-4">
												<input class="btn btn-danger pull-right" type="reset"
													value="清空条件"> <input
													class="btn btn-primary pull-right" id="bgUserSearchBtn"
													value="搜索" type="button">
											</div>
										</div>
									</form>
									<div id="toolbar_User">
										<button id="btn_deleteUser" type="button"
											class="btn btn-danger btn-sm">
											<span class="icon-remove" aria-hidden="false"></span>删除
										</button>
									</div>
									<table id="userMngTbl"></table>

									<!-- 修改资料模态框开始 -->
									<div class="modal fade responsive" id="update_user"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="myModalLabel">修改用户信息</h4>
												</div>
												<div class="modal-body" style="margin: 30px;">
													<form id="selfForm" class="form-horizontal" role="form">
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">学号/工号：</button>
															</span> <input type="text" class="form-control" id="inputUserid"
																disabled="disabled">
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">昵称:</button>
															</span> <input type="text" class="form-control"
																id="inputNickname" disabled="disabled">
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">姓名:</button>
															</span> <input type="text" class="form-control" id="inputName">
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">性别:</button>
															</span> <select id="inputGender" type="text"
																class="form-control">
																<option value="" selected="selected">&nbsp;</option>
																<option value="男">男</option>
																<option value="女">女</option>
															</select>
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">年龄:</button>
															</span><input type="text" class="form-control" id="inputAge">
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">邮箱:</button>
															</span> <input type="text" class="form-control email"
																id="inputEmail">
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">地址:</button>
															</span> <input type="text" class="form-control"
																id="inputAddress">
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">电话:</button>
															</span> <input type="text" class="form-control email"
																id="inputPhone">
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">密保问题:</button>
															</span> <input type="text" class="form-control"
																id="inputPasswordQues">
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">密保答案:</button>
															</span> <input type="text" class="form-control"
																id="inputPasswordAns">
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">用户类型:</button>
															</span> <select id="inputType" type="text" class="form-control">
																<option value="" selected="selected">&nbsp;</option>
																<option value="1">普通用户</option>
																<option value="0">管理员</option>
															</select>
														</div>

														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">是否可用:</button>
															</span> <select id="inputEnable" type="text"
																class="form-control">
																<option value="" selected="selected">&nbsp;</option>
																<option value="1">可用</option>
																<option value="0">不可用</option>
															</select>
														</div>
													</form>
												</div>
												<div class="modal-footer">
													<button id="editUserBtn" type="button"
														class="btn btn-primary">提交更改</button>
													<button type="button" class="btn btn-danger"
														data-dismiss="modal">关闭</button>
												</div>
											</div>
										</div>
									</div>
									<!-- 修改资料模态框结束 -->
								</div>

								<!-- 微博管理模块 -->
								<div id="back_weibo" class="tab-pane">
									<form>
										<div class="row">
											<div class="col-lg-4">
												<input id="searchWeiboSender" class="form-control"
													type="text" placeholder="按发布人搜索">
											</div>
											<div class="col-lg-4">
												<input id="searchWeiboContent" class="form-control"
													type="text" placeholder="按微博内容搜索">
											</div>
											<div class="col-lg-4">
												<input class="btn btn-danger pull-right" type="reset"
													value="清空条件"> <input
													class="btn btn-primary pull-right" id="bgWeiboSearchBtn"
													value="搜索" type="button">
											</div>
										</div>
									</form>
									<div id="toolbar_Weibo" class="">
										<button id="btn_deleteWeibo" type="button"
											class="btn btn-danger btn-sm">
											<span class="icon-remove" aria-hidden="true"></span>删除
										</button>
									</div>
									<table id="weiboMngTbl"></table>

									<!-- 修改微博模态框开始 -->
									<div class="modal fade responsive" id="update_weibo"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="myModalLabel">审核微博</h4>
												</div>
												<div class="modal-body" style="margin: 30px;">
													<form id="selfForm" class="form-horizontal" role="form">
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">微博ID:</button>
															</span> <input type="text" class="form-control" id="inputWbid"
																disabled="disabled">
														</div>
														<div class="form-group">
															<span class="input-group-btn">
																<button class="btn btn-white pull-up" type="button"
																	style="width: 100px;" disabled="disabled">微博内容:</button>
															</span>
															<textarea class="form-control" id="inputWbContent"
																rows="8"></textarea>
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">是否可用:</button>
															</span> <select id="wbEnable" type="text" class="form-control">
																<option value="" selected="selected">&nbsp;</option>
																<option value="1">可用</option>
																<option value="0">不可用</option>
															</select>
														</div>
													</form>
												</div>
												<div class="modal-footer">
													<button id="editWeiboBtn" type="button"
														class="btn btn-primary">提交更改</button>
													<button type="button" class="btn btn-danger"
														data-dismiss="modal">关闭</button>
												</div>
											</div>
										</div>
									</div>
									<!-- 修改微博模态框结束 -->
								</div>

								<!-- 文章管理 -->
								<div id="back_study" class="tab-pane">
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
									<div id="stuToolbar">
										<button id="publishMngBtn" type="button"
											class="btn btn-info btn-sm">
											<span class="icon-plus" aria-hidden="true"></span>发表文章
										</button>
										<button id="btn_deleteStudy" type="button"
											class="btn btn-danger btn-sm">
											<span class="icon-remove" aria-hidden="true"></span>删除
										</button>
									</div>

									<table id="studyTblMng"></table>

									<!-- 发布文章模态框 -->
									<div class="modal fade center" id="publishStudy" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
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
																<textarea id="inputContent" class="form-control"
																	cols="30" rows="10"></textarea>
															</div>
														</div>
														<div class="form-group">
															<div class="col-lg-offset-2 col-lg-10">
																<button id="publishBtn" type="button"
																	class="btn btn-send">发表</button>
																<button type="reset" class="btn btn-reset">重置</button>
															</div>
														</div>
													</form>
												</div>
											</div>
										</div>
									</div>
									<!-- 发表文章模态框结束 -->

									<!-- 审核文章模态框开始 -->
									<div class="modal fade responsive" id="update_study"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="myModalLabel">审核文章</h4>
												</div>
												<div class="modal-body" style="margin: 30px;">
													<form id="selfForm" class="form-horizontal" role="form">
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">文章ID:</button>
															</span> <input type="text" class="form-control"
																id="updateStudyId" disabled="disabled">
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">文章名称:</button>
															</span> <input type="text" class="form-control"
																id="updateStudyTitle">
														</div>
														<div class="form-group">
															<span class="input-group-btn">
																<button class="btn btn-white pull-up" type="button"
																	style="width: 100px;" disabled="disabled">文章详情:</button>
															</span>
															<textarea class="form-control" id="updateStudyContent"
																rows="8"></textarea>
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">是否可用:</button>
															</span> <select id="studyEnable" type="text"
																class="form-control">
																<option value="" selected="selected">&nbsp;</option>
																<option value="1">可用</option>
																<option value="0">不可用</option>
															</select>
														</div>
													</form>
												</div>
												<div class="modal-footer">
													<button id="editStudyBtn" type="button"
														class="btn btn-primary">提交更改</button>
													<button type="button" class="btn btn-danger"
														data-dismiss="modal">关闭</button>
												</div>
											</div>
										</div>
									</div>
									<!-- 审核文章模态框结束 -->
								</div>

								<!-- 活动管理模块 -->
								<div id="back_activity" class="tab-pane">
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
												<input id="acReset" class="btn btn-danger pull-right"
													type="reset" value="清空条件"> <input
													class="btn btn-primary pull-right" id="bgAcSearchBtn"
													value="搜索" type="button">
											</div>
										</div>
									</form>
									<div id="toolbar_activity" class="">
										<button id="btn_addActivity" type="button"
											class="btn btn-info btn-sm">
											<span class="icon-plus" aria-hidden="true"></span>添加
										</button>
										<button id="btn_deleteActivity" type="button"
											class="btn btn-danger btn-sm">
											<span class="icon-remove" aria-hidden="true"></span>删除
										</button>
									</div>
									<table id="activityMngTbl"></table>

									<!-- 添加活动模态框开始 -->
									<div class="modal fade responsive" id="add_activity"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="myModalLabel">添加活动</h4>
												</div>
												<div class="modal-body" style="margin: 30px;">
													<form id="selfForm" class="form-horizontal" role="form">
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">活动名称:</button>
															</span> <input type="text" class="form-control" id="addAcName">
														</div>
														<div class="form-group">
															<span class="input-group-btn">
																<button class="btn btn-white pull-up" type="button"
																	style="width: 100px;" disabled="disabled">活动详情:</button>
															</span>
															<textarea class="form-control" id="addAcContent" rows="8"></textarea>
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">是否可用:</button>
															</span> <select id="addAcEnable" type="text"
																class="form-control">
																<option value="1" selected="selected">可用</option>
																<option value="0">不可用</option>
															</select>
														</div>
													</form>
												</div>
												<div class="modal-footer">
													<button id="addAcBtn" type="button" class="btn btn-primary">提交更改</button>
													<button type="button" class="btn btn-danger"
														data-dismiss="modal">关闭</button>
												</div>
											</div>
										</div>
									</div>
									<!-- 添加活动模态框结束 -->

									<!-- 修改活动模态框开始 -->
									<div class="modal fade responsive" id="update_activity"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="myModalLabel">修改活动</h4>
												</div>
												<div class="modal-body" style="margin: 30px;">
													<form id="selfForm" class="form-horizontal" role="form">
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">活动ID:</button>
															</span> <input type="text" class="form-control" id="updateAcId"
																disabled="disabled">
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">活动名称:</button>
															</span> <input type="text" class="form-control"
																id="updateAcName">
														</div>
														<div class="form-group">
															<span class="input-group-btn">
																<button class="btn btn-white pull-up" type="button"
																	style="width: 100px;" disabled="disabled">活动详情:</button>
															</span>
															<textarea class="form-control" id="updateAcContent"
																rows="8"></textarea>
														</div>
														<div class="form-group input-group">
															<span class="input-group-btn">
																<button class="btn btn-white" type="button"
																	style="width: 100px;" disabled="disabled">是否可用:</button>
															</span> <select id="updateAcEnable" type="text"
																class="form-control">
																<option value="" selected="selected">&nbsp;</option>
																<option value="1">可用</option>
																<option value="0">不可用</option>
															</select>
														</div>
													</form>
												</div>
												<div class="modal-footer">
													<button id="updateAcBtn" type="button"
														class="btn btn-primary">提交更改</button>
													<button type="button" class="btn btn-danger"
														data-dismiss="modal">关闭</button>
												</div>
											</div>
										</div>
									</div>
									<!-- 修改活动模态框结束 -->
								</div>

								<!-- 活动报名管理模块 -->
								<div id="back_activity_apply" class="tab-pane">
									<form>
										<div class="row">
											<div class="col-lg-4">
												<input id="searchAcApplyId" class="form-control" type="text"
													placeholder="按活动ID搜索">
											</div>
											<div class="col-lg-4">
												<input id="searchAcApplyNickname" class="form-control"
													type="text" placeholder="按报名人搜索">
											</div>
											<div class="col-lg-4">
												<input id="acReset" class="btn btn-danger pull-right"
													type="reset" value="清空条件"> <input
													class="btn btn-primary pull-right" id="bgAcApplySearchBtn"
													value="搜索" type="button">
											</div>
										</div>
									</form>
									<table id="applyActMngTbl"></table>
								</div>

								<!-- 跳蚤市场模块 -->
								<div id="back_market" class="tab-pane">
									<div class="row">
										<div class="col-lg-4">
											<input id="searchGoodsName" class="form-control" type="text"
												placeholder="按商品名称搜索">
										</div>
										<div class="col-lg-4">
											<input id="searchGoodsSender" class="form-control"
												type="text" placeholder="按商品发布人搜索">
										</div>
										<div class="col-lg-4">
											<input class="btn btn-danger pull-right" type="reset"
												value="清空条件"> <input
												class="btn btn-primary pull-right" id="goodsSearchBtn"
												value="搜索" type="button">
										</div>
									</div>
									</form>
									<div id="toolbar_market">
										<button id="btn_want" type="button"
											class="btn btn-info btn-sm">
											<span class="icon-plus" aria-hidden="true"></span>发布商品
										</button>
									</div>
									<table id="martketTbl"></table>

									<!-- 发布商品模态框 -->
									<div class="modal fade center" id="goods" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title">发布商品</h4>
												</div>
												<div class="modal-body text-info">
													<form class="form-horizontal" role="form">
														<div class="form-group">
															<label class="col-lg-2 control-label">商品名称</label>
															<div class="col-lg-10">
																<input type="text" class="form-control"
																	id="inputGoodName">
															</div>
														</div>
														<div class="form-group">
															<label class="col-lg-2 control-label">商品描述</label>
															<div class="col-lg-10">
																<textarea id="inputGoodsDetail" class="form-control"
																	cols="30" rows="10"></textarea>
															</div>
														</div>

														<div class="form-group">
															<div class="col-lg-offset-2 col-lg-10">
																<button id="publishGoods" type="button"
																	class="btn btn-send">发表</button>
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
									<div class="modal fade center" id="check_detail" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title">查看商品详情</h4>
												</div>
												<div class="modal-body" style="word-break: break-all">
													<h4 id="goodsName" align="center"></h4>
													<p id="goodsDetail"></p>
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
								</div>

								<!-- 商品申请管理 -->
								<div id="back_market_apply" class="tab-pane">
									<form>
										<div class="row">
											<div class="col-lg-4">
												<input id="searchGoodsApplyId" class="form-control" type="text"
													placeholder="按商品ID搜索">
											</div>
											<div class="col-lg-4">
												<input id="searchGoodsApplyNickname" class="form-control"
													type="text" placeholder="按申请人搜索">
											</div>
											<div class="col-lg-4">
												<input id="acReset" class="btn btn-danger pull-right"
													type="reset" value="清空条件"> <input
													class="btn btn-primary pull-right" id="bgGoodsApplySearchBtn"
													value="搜索" type="button">
											</div>
										</div>
									</form>
									<table id="MarketApplyMngTbl"></table>
								</div>
							</div>
						</div>
					</section>
				</div>
			</div>
		</section>
	</section>
</body>
<script src="js/own/backMng/newsMng.js"></script>
<script src="js/own/backMng/userMng.js"></script>
<script src="js/own/backMng/weiboMng.js"></script>
<script src="js/own/backMng/studyMng.js"></script>
<script src="js/own/backMng/activityMng.js"></script>
<script src="js/own/backMng/marketMng.js"></script>
<script src="js/own/backMng/acApplyMng.js"></script>
<script src="js/own/backMng/marketApplyMng.js"></script>
</html>