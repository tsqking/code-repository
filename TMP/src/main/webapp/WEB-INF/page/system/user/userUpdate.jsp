<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background-color:#ecf0f5";>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/common/image/logo.ico" />
<s:include value="../../common.jsp"></s:include>
<!-- 页面js -->
<script
	src="${pageContext.request.contextPath}/system/user/userUpdate.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/system/user/${system_lang}.js"></script>
<title>用户编辑界面</title>
</head>
<!-- onload="window.print();" -->
<body>
	<div class="box-body">
		<form id="searchForm" method="post">
			<div class="col-md-12 col-xs-12">
				<div class="box-body">
					<div class="nav-tabs-custom">
						<ul class="nav nav-tabs">
							<li class="active" id="tab1"><a href="#pointInfo1"
								data-toggle="tab"><s:property value="getText('tag1')" /></a></li>
							<li id="tab2"><a href="#pointInfo2" data-toggle="tab"><s:property
										value="getText('tag2')" /></a></li>
							<li id="tab2"><a href="#pointInfo3" data-toggle="tab"><s:property
										value="getText('tag3')" /></a></li>
						</ul>
						<div class="tab-content">
							<div class="active tab-pane" id="pointInfo1">
								<div class="row">
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<label> <!-- 账户头像 --> <s:property
													value="getText('userPhoto')" />
											</label>
											<button type="button"
												class="btn btn-block btn-primary btn-sm"
												onclick="document.getElementById('f').click();">
												<li class="glyphicon glyphicon-plus"></li>&nbsp;&nbsp;
												<!-- 修改头像 -->
												<s:property value="getText('editPhoto')" />
											</button>
											<div style="display: none;">
												<input id="f" type="file" name="f" onchange="change(this)" />
											</div>
											<div style="">
												<div class="input-group"
													style="text-align: center; vertical-align: middle; line-height: 200px; border: 0px solid #88C4F7; margin-bottom: 17px; margin-left: 28%; margin-top: 5%;">
													<img class="attachment-img"
														style="height: 130px; width: 130px; border: 0px solid #88C4F7;"
														id="preview" onclick="clickInput()"
														src="${pageContext.request.contextPath}/common/image/img.png"
														alt="Attachment Image">
												</div>
											</div>
										</div>
										<div class="form-group">
											<label> <!-- 账户编号 --> <s:property
													value="getText('userId')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-flag-o"></li></span> <input type="text" id="id"
													name="id" class="form-control" readonly="readonly">
											</div>
										</div>
									</div>
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<label> <!-- 学号/教员号 --> <s:property
													value="getText('userNumber')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-bookmark-o"></li></span> <input type="text"
													id="no" name="no" class="form-control" readonly="readonly">
											</div>
										</div>
										<div class="form-group">
											<label for="role"> <!-- 账户角色 --> <s:property
													value="getText('userRole')" /><small><i class="fa fa-star notNull"></i></small>
											</label> <select class="form-control select" id="role"
												style="width: 100%;" name="role">
											</select>
										</div>
										<div class="form-group">
											<label for="enable"> <!-- 生效标识 --> <s:property
													value="getText('enableName')" /><small><i class="fa fa-star notNull"></i></small>
											</label> <select class="form-control select" id="enable"
												style="width: 100%;" name="enable">
											</select>
										</div>

										<div class="form-group">
											<label for="gender"> <!-- 性别 --> <s:property
													value="getText('userSex')" /><small><i class="fa fa-star notNull"></i></small>
											</label> <select class="form-control select" id="gender"
												style="width: 100%;" name="gender">
											</select>
										</div>
									</div>
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<label for="username"> <!-- 登录名 --> <s:property
													value="getText('username')" /><small><i class="fa fa-star notNull"></i></small>
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-male"></li></span> <input type="text"
													id="username" name="username" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="hiddenPwd"> <!-- 登录密码 --> <s:property
													value="getText('userPwd')" /><small><i class="fa fa-star notNull"></i></small>
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-eye-slash"></li></span> <span type="password"
													id="password" class="form-control"></span> <input
													type="hidden" value="" id="hiddenPwd" name="password"
													readonly="readonly" />
											</div>
										</div>
										<div class="form-group">
											<label for="name"> <!-- 真实姓名 --> <s:property
													value="getText('trueName')" /><small><i class="fa fa-star notNull"></i></small>
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-tag"></li></span> <input type="text" id="name"
													name="name" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="en_name"> <!-- 英文姓名 --> <s:property
													value="getText('enName')" /><small><i class="fa fa-star notNull"></i></small>
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-tag"></li></span> <input type="text"
													id="en_name" name="en_name" class="form-control">
											</div>
										</div>
									</div>
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<label for="cardtype"> <!-- 证件类型 -->
												<s:property value="getText('cardtype')" /><small><i class="fa fa-star notNull"></i></small>
											</label> <select class="form-control select" id="cardtype"
												style="width: 100%;" name="cardtype">
											</select>
										</div>
										<div class="form-group">
											<label for="cardno"> <!-- 证件号码 -->
												<s:property value="getText('cardno')" /><small><i class="fa fa-star notNull"></i></small>
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-envelope-o"></li></span> <input type="text"
													id="cardno" name="cardno" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="mobile"> <!-- 手机号码(移动) --> <s:property
													value="getText('userMobile')" /><small><i class="fa fa-star notNull"></i></small>
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-mobile-phone"></li></span> <input type="text"
													id="mobile" name="mobile" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="email"> <!-- 电子邮件 --> <s:property
													value="getText('email')" /><small><i class="fa fa-star notNull"></i></small>
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-envelope-o"></li></span> <input type="text"
													id="email" name="email" class="form-control">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="tab-pane" id="pointInfo2">
								<div class="row">
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<label for="birthday"> <!-- 生日 --> <s:property
													value="getText('birthday')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-calendar"></li></span> <input type="text"
													id="birthday" name="birthday" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="age"> <!-- 年龄 --> <s:property
													value="getText('userAge')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-child"></li></span> <input type="text" id="age"
													name="age" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="phone"> <!-- 电话号码(座机) --> <s:property
													value="getText('phone')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-phone"></li></span> <input type="text"
													id="phone" name="phone" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="contact_address"> <!-- 联系地址 --> <s:property
													value="getText('userAddress')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-home"></li></span> <input type="text"
													id="contact_address" name="contact_address"
													class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="contact_postcode"> <!-- 联系地址邮编 --> <s:property
													value="getText('addressCode')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-map-signs"></li></span> <input type="text"
													id="contact_postcode" name="contact_postcode"
													class="form-control">
											</div>
										</div>
									</div>
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<label for="home_address"> <!-- 家庭地址 --> <s:property
													value="getText('homeAddress')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-home"></li></span> <input type="text"
													id="home_address" name="home_address" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="home_postcode"> <!-- 家庭地址邮编 --> <s:property
													value="getText('homeAddrCode')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-map-signs"></li></span> <input type="text"
													id="home_postcode" name="home_postcode"
													class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="education_background"> <!-- 学历 --> <s:property
													value="getText('educationBackground')" />
											</label> <select class="form-control select"
												id="education_background" style="width: 100%;"
												name="education_background">
											</select>
										</div>
										<div class="form-group">
											<label for="degree"> <!-- 学位 --> <s:property
													value="getText('degree')" />
											</label><select class="form-control select" id="degree"
												style="width: 100%;" name="degree">
											</select>
										</div>
										<div class="form-group">
											<label for="university"> <!-- 最高学历毕业院校 --> <s:property
													value="getText('university')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-university"></li></span> <input type="text"
													id="university" name="university" class="form-control">
											</div>
										</div>
									</div>
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<label for="college"> <!-- 学院 --> <s:property
													value="getText('college')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-university"></li></span> <input type="text"
													id="college" name="college" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="major"> <!-- 专业 --> <s:property
													value="getText('major')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-users"></li></span> <input type="text"
													id="major" name="major" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="gpa"> <!-- 绩点 --> <s:property
													value="getText('gpa')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-sticky-note-o"></li></span> <input type="text"
													id="gpa" name="gpa" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="cet4"> <!-- 四级成绩 --> <s:property
													value="getText('cet4')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-sticky-note-o"></li></span> <input type="text"
													id="cet4" name="cet4" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="cet6"> <!-- 六级成绩 --> <s:property
													value="getText('cet6')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-sticky-note-o"></li></span> <input type="text"
													id="cet6" name="cet6" class="form-control">
											</div>
										</div>
									</div>
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<label for="resume"> <!-- 简历地址 --> <s:property
													value="getText('resume')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-file-word-o"></li></span> <input type="text"
													id="resume" name="resume" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="direction"> <!-- 学习或主教方向 --> <s:property
													value="getText('userDir')" />
											</label>
											<select class="form-control select" id="direction"
												style="width: 100%;" name="direction">
											</select>
										</div>
										<div class="form-group">
											<label for="description"> <!-- 人员情况简介 --> <s:property
													value="getText('userDesc')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-building-o"></li></span> <input type="text"
													id="description" name="description" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label for="description"> <!-- 考试证号 --> <s:property
													value="getText('exam_num')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-building-o"></li></span> <input type="text"
													id="exam_num" name="exam_num" class="form-control">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="tab-pane" id="pointInfo3">
								<div class="row">
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<label> <!-- 创建时间 --> <s:property
													value="getText('create_time')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-clock-o"></li></span> <input type="text"
													id="create_time" name="create_time" class="form-control"
													readonly="readonly">
											</div>
										</div>
									</div>
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<label> <!-- 创建人 --> <s:property
													value="getText('createPerson')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-user-secret"></li></span> <input type="text"
													id="create_person" name="create_person"
													class="form-control" readonly="readonly">
											</div>
										</div>
									</div>
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<label> <!-- 修改时间 --> <s:property
													value="getText('updateTime')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-clock-o"></li></span> <input type="text"
													id="update_time" name="update_time" class="form-control"
													readonly="readonly">
											</div>
										</div>
									</div>
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<label> <!-- 修改人 --> <s:property
													value="getText('updatePerson')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-user-secret"></li></span> <input type="text"
													id="update_person" name="update_person"
													class="form-control" readonly="readonly">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
						<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
							<button type="button" id="closeIframe"
								class="btn btn-block btn-primary btn-sm">
								<li class="glyphicon glyphicon-remove"></li>&nbsp;&nbsp;
								<!-- 关闭详细信息窗口 -->
								<s:property value="getText('closeDetailWin')" />
							</button>
						</div>
						<div class="col-md-3 col-xs-3">
							<button type="button" id="editIframe"
								class="btn btn-block btn-success btn-sm">
								<li class="glyphicon glyphicon-ok"></li>&nbsp;&nbsp;
								<!-- 提交用户信息修改 -->
								<s:property value="getText('applyUserInfo')" />
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	
</body>
<div class="modal" id="myModal">
	<div class="modal-dialog">
		<div class="modal-content" style="margin-top: 30%;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">
					<!-- 修改前请输入密码 -->
					<s:property value="getText('checkPassword')" />
					:
				</h4>
			</div>
			<div class="modal-body">
				<form id="searchForm_modal">
					<!-- /.box-header -->
					<div class="box-body">
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group">
									<label>
										<!-- 登录账户密码 -->
										<s:property value="getText('nowUserPwd')" />
									</label>
									<div class="input-group">
										<span class="input-group-addon"><li
											class="fa fa-fw fa-eye-slash"></li></span> <input type="password"
											id="userNowPwd" name="userNowPwd" class="form-control">
									</div>
								</div>
								<!-- /.form-group -->
								<div class="form-group">
									<label>
										<!-- 操作目标账户密码 -->
										<s:property value="getText('targetUserPwd')" />
									</label>
									<div class="input-group">
										<span class="input-group-addon"><li
											class="fa fa-fw fa-eye-slash"></li></span> <input type="password"
											id="userPwd" name="userPwd" class="form-control">
									</div>
								</div>
								<!-- /.form-group -->
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default pull-left"
					data-dismiss="modal">
					<!-- 关闭窗口 -->
					<s:property value="getText('closeWindows')" />
				</button>
				<button type="button" class="btn btn-primary" id="pwdModalApply">
					<!-- 确认密码 -->
					<s:property value="getText('applyPwd')" />
				</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<div class="modal" id="myModal2">
	<div class="modal-dialog">
		<div class="modal-content" style="margin-top: 30%;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">
					<!-- 新密码修改 -->
					<s:property value="getText('newPwdChange')" />
					:
				</h4>
			</div>
			<div class="modal-body">
				<form id="searchForm_modal2">
					<!-- /.box-header -->
					<div class="box-body">
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group">
									<label>
										<!-- 输入新密码 -->
										<s:property value="getText('inputNewPwd')" />
									</label>
									<div class="input-group">
										<span class="input-group-addon"><li
											class="fa fa-fw fa-eye-slash"></li></span> <input type="password"
											id="changePwd1" name="changePwd1" class="form-control">
									</div>
								</div>
								<!-- /.form-group -->
								<div class="form-group">
									<label>
										<!-- 重复输入密码 -->
										<s:property value="getText('inputNewPwd2')" />
									</label>
									<div class="input-group">
										<span class="input-group-addon"><li
											class="fa fa-fw fa-eye-slash"></li></span> <input type="password"
											id="changePwd2" name="changePwd2" class="form-control">
									</div>
								</div>
								<!-- /.form-group -->
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default pull-left"
					data-dismiss="modal">
					<!-- 关闭窗口 -->
					<s:property value="getText('closeWindows')" />
				</button>
				<button type="button" class="btn btn-primary" id="pwdModalApply2">
					<!-- 确认修改 -->
					<s:property value="getText('pwdModalApply2')" />
				</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<input type="hidden" value="${user.id}" id="userId" />
</html>


