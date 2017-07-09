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
	src="${pageContext.request.contextPath}/tech/teacherpool/teacherUpdate.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/tech/teacherpool/${system_lang}.js"></script>
<title>用户编辑界面</title>
</head>
<!-- onload="window.print();" -->
<body>
	<!-- Main content -->
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<div class="box">
			<form id="searchForm">
				<div class="box-body">
					<div class="nav-tabs-custom">
						<ul class="nav nav-tabs">
							<li class="active" id="tab1"><a href="#baseInfo"
								data-toggle="tab"><s:text name="baseInfoTab"/></a></li>
							<li id="tab2"><a href="#otherInfo" data-toggle="tab"><s:text name="otherInfoTab"/></a></li>
						</ul>
						<div class="tab-content">
							<div class="active tab-pane" id="baseInfo">
							<div class="row">
								<div class="col-sm-3 col-xs-3">
									<input type="hidden" id="nbr" name="tepVo.nbr" class="form-control" value="${tepVo.nbr}" readonly>
									<div class="form-group">
										<label> <!-- 账户头像 --> <s:property
												value="getText('teacherPhoto')" />
										</label>
										<button type="button" class="btn btn-block btn-primary btn-sm"
											onclick="document.getElementById('f').click();">
											<li class="glyphicon glyphicon-plus"></li>&nbsp;&nbsp;
											<!-- 修改头像 -->
											<s:property value="getText('teacher.editPhoto')" />
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
													src="${pageContext.request.contextPath}/common/image/user2-160x160.jpg"
													alt="Attachment Image">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="role"> <!-- 教师角色 --> <s:property
												value="getText('teacherRole')" />
										</label> <select class="form-control select" id="role"
											style="width: 100%;" name="tepVo.role"
											temValue="${tepVo.role}">
										</select>
									</div>
								</div>
								<div class="col-sm-3 col-xs-3">
									<div class="form-group">
										<!-- 账户编号 -->
										<label for="no"> <!-- 账户编号 --> <s:property
												value="getText('teacherNumber')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-flag-o"></li></span> <input type="text" id="no"
												name="tepVo.no" class="form-control" value="${tepVo.no}"
												readonly>
										</div>
									</div>
									<div class="form-group">
										<label for="teacType"> <!-- 教师类别 --> <s:property
												value="getText('teacherType')" /><small><i class="fa fa-star notNull"></i></small>
										</label> <select class="form-control select" id="teacType"
											style="width: 100%;" name="tepVo.teacType"
											temValue="${tepVo.teacType}">
										</select>
									</div>
									<div class="form-group">
										<label for="enable">  <!-- 生效标志 --> <s:property
												value="getText('enable')" /><small><i class="fa fa-star notNull"></i></small>
										</label> <select class="form-control select" id="enable"
											style="width: 100%;" name="tepVo.enable"
											temValue="${tepVo.enable}">
										</select>
									</div>
									<div class="form-group">
										<label for="gender"> <!-- 性别 --> <s:property
												value="getText('teacherSex')" /><small><i class="fa fa-star notNull"></i></small>
										</label> <select class="form-control select" id="gender"
											style="width: 100%;" name="tepVo.gender"
											temValue="${tepVo.gender}">
										</select>
									</div>
								</div>
								<div class="col-sm-3 col-xs-3">
									<div class="form-group">
										<!-- 登录名 -->
										<label for="username"> <!-- 登录名 --> <s:property
												value="getText('teacher.name')" /><small><i class="fa fa-star notNull"></i></small>
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-male"></li></span> <input type="text"
												id="username" class="form-control" name="tepVo.username"
												value="${tepVo.username}">
										</div>
									</div>
									<div class="form-group">
										<label for="hiddenPwd"> <!-- 登录密码 --> <s:property
												value="getText('teacherPwd')" /><small><i class="fa fa-star notNull"></i></small>
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-eye-slash"></li></span> <span type="password"
												id="password" class="form-control">******</span> <input
												type="hidden" value="${tepVo.password}" id="hiddenPwd"
												name="tepVo.password" readonly="readonly" />
										</div>
									</div>
									<div class="form-group">
										<!-- 真实姓名 -->
										<label  for="name"> <!-- 真实姓名 --> <s:property
												value="getText('trueName')" /><small><i class="fa fa-star notNull"></i></small>
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-tag"></li></span> <input type="text" id="name"
												class="form-control" name="tepVo.name" value="${tepVo.name}">
										</div>
									</div>
									<div class="form-group">
										<!-- 英文姓名 -->
										<label for="en_name"> <!-- 英文姓名 --> <s:property
												value="getText('enName')" /><small><i class="fa fa-star notNull"></i></small>
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-tag"></li></span> <input type="text"
												id="en_name" class="form-control" name="tepVo.en_name"
												value="${tepVo.en_name}">
										</div>
									</div>
								</div>
								<div class="col-sm-3 col-xs-3">
									<div class="form-group">
										<label for="cardType"> <!-- 卡片类型 --> <s:property
												value="getText('cardType')" /><small><i class="fa fa-star notNull"></i></small>
										</label> <select class="form-control select" id="cardType"
											style="width: 100%;" name="tepVo.cardType"
											temValue="${tepVo.cardType}">
										</select>
									</div>
									<div class="form-group">
										<label for="cardNbr"> <!-- 证件号码 --> <s:property
												value="getText('cardNumber')" /><small><i class="fa fa-star notNull"></i></small> 
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-building-o"></li></span> <input type="text"
												id="cardNbr" name="tepVo.cardNbr" class="form-control"
												value="${tepVo.cardNbr}">
										</div>
									</div>
									<div class="form-group">
										<label for="mobile"> <!-- 手机号码(移动) --> <s:property
												value="getText('teacherMobile')" /><small><i class="fa fa-star notNull"></i></small>
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-mobile-phone"></li></span> <input type="text"
												id="mobile" class="form-control" name="tepVo.mobile"
												value="${tepVo.mobile}">
										</div>
									</div>
									<div class="form-group">
										<label for="email"> <!-- 电子邮件 --> <s:property
												value="getText('email')" /><small><i class="fa fa-star notNull"></i></small>
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-envelope-o"></li></span> <input type="text"
												id="email" class="form-control" name="tepVo.email"
												value="${tepVo.email}">
										</div>
									</div>
								</div>
							</div>
						</div>
							<div class="tab-pane" id="otherInfo">
								<div class="row">
									<div class="col-sm-3 col-xs-3">
										<div class="form-group">
											<label for="age"> <!-- 年龄 --> <s:property
													value="getText('teacherAge')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-child"></li></span> <input type="text" id="age"
													class="form-control" name="tepVo.age" value="${tepVo.age}">
											</div>
										</div>
										<div class="form-group">
											<label for="birthday"> <!-- 生日 --> <s:property
													value="getText('birthday')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-calendar"></li></span> <input type="text"
													id="birthday" class="form-control" name="tepVo.birthday"
													value="${tepVo.birthday}">
											</div>
										</div>
										<div class="form-group">
											<label for="phone"> <!-- 电话号码(座机) --> <s:property
													value="getText('phone')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-phone"></li></span> <input type="text"
													id="phone" class="form-control" name="tepVo.phone"
													value="${tepVo.phone}">
											</div>
										</div>
										<div class="form-group">
											<!-- 学习或主教方向 -->
											<label for="direction"> <!-- 学习或主教方向 --> <s:property
													value="getText('teacherDir')" />
											</label> <select class="form-control select" id="direction"
												style="width: 100%;" name="tepVo.direction"
												temValue="${tepVo.direction}">
											</select>
										</div>
									</div>
									<div class="col-sm-3 col-xs-3">
										<div class="form-group">
											<label for="university"> <!-- 最高毕业院校 --> <s:property
													value="getText('university')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-university"></li></span> <input type="text"
													id="university" name="tepVo.university" class="form-control"
													value="${tepVo.university}">
											</div>
										</div>
										<div class="form-group">
											<!-- 专业 -->
											<label for="major"> <!-- 专业 --> <s:property
													value="getText('major')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-bookmark-o"></li></span> <input type="text"
													id="major" class="form-control" name="tepVo.major"
													value="${tepVo.major}">
											</div>
										</div>
										<!-- /.form-group -->
										<div class="form-group">
											<label for="education_background"> <!-- 学历 --> <s:property
													value="getText('educationBackground')" />
											</label> <select class="form-control select" id="education_background"
												style="width: 100%;" name="tepVo.education_background"
												temValue="${tepVo.education_background}">
											</select>
										</div>
										<!-- /.form-group -->
										<div class="form-group">
											<label for="degree"> <!-- 学位 --> <s:property
													value="getText('degree')" />
											</label> <select class="form-control select" id="degree"
												style="width: 100%;" name="tepVo.degree"
												temValue="${tepVo.degree}">
											</select>
										</div>
									</div>
									<div class="col-sm-3 col-xs-3">
										<div class="form-group">
											<!-- 联系地址 -->
											<label for="contact_address"> <!-- 联系地址 --> <s:property
													value="getText('teacherAddress')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-home"></li></span> <input type="text"
													id="contact_address" class="form-control"
													name="tepVo.contact_address"
													value="${tepVo.contact_address}">
											</div>
										</div>
										<div class="form-group">
											<label for="home_address"> <!-- 家庭地址 --> <s:property
													value="getText('homeAddress')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-home"></li></span> <input type="text"
													id="home_address" class="form-control"
													name="tepVo.home_address" value="${tepVo.home_address}">
											</div>
										</div>
										<div class="form-group">
											<!-- 人员情况简介 -->
											<label for="description"> <!-- 人员情况简介 --> <s:property
													value="getText('teacherDesc')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-building-o"></li></span> <input type="text"
													id="description" class="form-control"
													name="tepVo.description" value="${tepVo.description}">
											</div>
										</div>
									</div>
									<div class="col-sm-3 col-xs-3">
										<div class="form-group">
											<label for="create_time"> <!-- 创建时间 --> <s:property
													value="getText('create_time')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-clock-o"></li></span> <input type="text"
													id="create_time" name="tepVo.create_time"
													class="form-control" value="${tepVo.create_time}" readonly>
											</div>
										</div>
										<div class="form-group">
											<label for="create_person"> <!-- 创建人 --> <s:property
													value="getText('createPerson')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-user-secret"></li></span> <input type="text"
													id="create_person" name="tepVo.create_person"
													class="form-control" value="${tepVo.create_person}" readonly>
											</div>
										</div>
										<div class="form-group">
											<label for="update_time"> <!-- 修改时间 --> <s:property
													value="getText('updateTime')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-clock-o"></li></span> <input type="text"
													id="update_time" name="tepVo.update_time"
													class="form-control" value="${tepVo.update_time}" readonly>
											</div>
										</div>
										<div class="form-group">
											<label for="update_person"> <!-- 修改人 --> <s:property
													value="getText('updatePerson')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-user-secret"></li></span> <input type="text"
													id="update_person" name="tepVo.update_person"
													class="form-control" value="${tepVo.update_person}" readonly>
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
								<!-- 提交教师信息修改 -->
								<s:property value="getText('updateTeacherInfo')" />
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</section>
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
										<label for="userNowPwd">
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
										<label for="userPwd">
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
		</div>
	</div>
	
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
										<label for="changePwd1">
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
										<label for="changePwd2">
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
		</div>
	</div>
</body>
</html>

<input type="hidden" value="${tepVo.nbr}" id="nbr" />
