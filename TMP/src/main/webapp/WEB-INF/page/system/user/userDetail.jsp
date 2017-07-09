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
	src="${pageContext.request.contextPath}/system/user/userDetail.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/system/user/${system_lang}.js"></script>
<title>用户详细界面</title>
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
											<!-- 账户头像 -->
											<label> <!-- 账户头像 --> <s:property
													value="getText('userPhoto')" />
											</label>
											<div class="input-group"
												style="text-align: center; vertical-align: middle; line-height: 200px; border: 2px solid #88C4F7; margin-bottom: 17px; margin-left: 25%; margin-top: 5%;">
												<img class="attachment-img"
													style="height: 160px; width: 160px;" id="photo" src=""
													alt="Attachment Image">
											</div>
										</div>
										<div class="form-group">
											<!-- 账户编号 -->
											<label> <!-- 账户编号 --> <s:property
													value="getText('userId')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-flag-o"></li></span> <span type="text" id="id"
													class="form-control"></span>
											</div>
										</div>
									</div>
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<!-- 学号/教员号 -->
											<label> <!-- 学号/教员号 --> <s:property
													value="getText('userNumber')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-bookmark-o"></li></span> <span type="text"
													id="no" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<!-- 账户角色 -->
											<label> <!-- 账户角色 --> <s:property
													value="getText('userRole')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-group"></li></span> <span type="text"
													id="role_name" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<label> <!-- 生效标识 --> <s:property
													value="getText('enableName')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-bar-chart"></li></span> <span type="text"
													id="enable_name" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<label> <!-- 性别 --> <s:property
													value="getText('userSex')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-venus-mars"></li></span> <span type="text"
													id="gender_name" class="form-control"></span>
											</div>
										</div>
									</div>
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<!-- 登录名 -->
											<label> <!-- 登录名 --> <s:property
													value="getText('username')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-male"></li></span> <span type="text"
													id="username" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<!-- 登录密码 -->
											<label> <!-- 登录密码 --> <s:property
													value="getText('userPwd')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-eye-slash"></li></span> <span type="text"
													id="pwd" class="form-control">******</span>
											</div>
										</div>
										<div class="form-group">
											<!-- 真实姓名 -->
											<label> <!-- 真实姓名 --> <s:property
													value="getText('trueName')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-tag"></li></span> <span type="text" id="name"
													class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<!-- 英文姓名 -->
											<label> <!-- 英文姓名 --> <s:property
													value="getText('enName')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-tag"></li></span> <span type="text"
													id="en_name" class="form-control"></span>
											</div>
										</div>
									</div>
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<label> <!-- 证件类型 -->
												<s:property value="getText('cardtype')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-envelope-o"></li></span> <span type="text"
													id="cardtype_name" name="cardtype_name"
													class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<label> <!-- 证件号码 -->
												<s:property value="getText('cardno')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-envelope-o"></li></span> <span type="text"
													id="cardno" name="cardno" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<label> <!-- 手机号码(移动) --> <s:property
													value="getText('userMobile')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-mobile-phone"></li></span> <span type="text"
													id="mobile" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<label> <!-- 电子邮件 --> <s:property
													value="getText('email')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-envelope-o"></li></span> <span type="text"
													id="email" class="form-control"></span>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="tab-pane" id="pointInfo2">
								<div class="row">
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<label> <!-- 生日 --> <s:property
													value="getText('birthday')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-calendar"></li></span> <span type="text"
													id="birthday" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<label> <!-- 年龄 --> <s:property
													value="getText('userAge')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-child"></li></span> <span type="text" id="age"
													class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<label> <!-- 电话号码(座机) --> <s:property
													value="getText('phone')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-phone"></li></span> <span type="text"
													id="phone" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<!-- 联系地址 -->
											<label> <!-- 联系地址 --> <s:property
													value="getText('userAddress')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-home"></li></span> <span type="text"
													id="contact_address" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<label> <!-- 联系地址邮编 --> <s:property
													value="getText('addressCode')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-map-signs"></li></span> <span type="text"
													id="contact_postcode" class="form-control"></span>
											</div>
										</div>
									</div>
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<label> <!-- 家庭地址 --> <s:property
													value="getText('homeAddress')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-home"></li></span> <span type="text"
													id="home_address" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<label> <!-- 家庭地址邮编 --> <s:property
													value="getText('homeAddrCode')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-map-signs"></li></span> <span type="text"
													id="home_postcode" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<label> <!-- 学历 --> <s:property
													value="getText('educationBackground')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-book"></li></span> <span type="text"
													id="education_background_name" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<label> <!-- 学位 --> <s:property
													value="getText('degree')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-graduation-cap"></li></span> <span type="text"
													id="degree_name" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<label> <!-- 最高学历毕业院校 --> <s:property
													value="getText('university')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-university"></li></span> <span type="text"
													id="university" class="form-control"></span>
											</div>
										</div>
									</div>
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<label> <!-- 学院 --> <s:property
													value="getText('college')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-university"></li></span> <span type="text"
													id="college" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<label> <!-- 专业 --> <s:property
													value="getText('major')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-users"></li></span> <span type="text"
													id="major" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<label> <!-- 绩点 --> <s:property
													value="getText('gpa')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-sticky-note-o"></li></span> <span type="text"
													id="gpa" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<label> <!-- 四级成绩 --> <s:property
													value="getText('cet4')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-sticky-note-o"></li></span> <span type="text"
													id="cet4" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<label> <!-- 六级成绩 --> <s:property
													value="getText('cet6')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-sticky-note-o"></li></span> <span type="text"
													id="cet6" class="form-control"></span>
											</div>
										</div>
									</div>
									<div class="col-md-3 col-xs-3">
										<div class="form-group">
											<label> <!-- 简历地址 --> <s:property
													value="getText('resume')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-file-word-o"></li></span> <span type="text"
													id="resume" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<!-- 学习或主教方向 -->
											<label> <!-- 学习或主教方向 --> <s:property
													value="getText('userDir')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-star-half-empty"></li></span> <span type="text"
													id="direction" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<!-- 人员情况简介 -->
											<label> <!-- 人员情况简介 --> <s:property
													value="getText('userDesc')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-building-o"></li></span> <span type="text"
													id="description" class="form-control"></span>
											</div>
										</div>
										<div class="form-group">
											<!-- 准考证号 -->
											<label> <!-- 准考证号 --> <s:property
													value="getText('exam_num')" />
											</label>
											<div class="input-group">
												<span class="input-group-addon"><li
													class="fa fa-fw fa-building-o"></li></span> <span type="text"
													id="exam_num" class="form-control"></span>
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
													class="fa fa-fw fa-clock-o"></li></span> <span type="text"
													id="create_time" class="form-control"></span>
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
													class="fa fa-fw fa-user-secret"></li></span> <span type="text"
													id="create_person" class="form-control"></span>
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
													class="fa fa-fw fa-clock-o"></li></span> <span type="text"
													id="update_time" class="form-control"></span>
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
													class="fa fa-fw fa-user-secret"></li></span> <span type="text"
													id="update_person" class="form-control"></span>
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
								<!-- 编辑用户信息 -->
								<s:property value="getText('editUserInfo')" />
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
<input type="hidden" value="${user.id}" id="userId" />
</html>

