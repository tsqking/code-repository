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
	src="${pageContext.request.contextPath}/tech/teacherpool/teacherDetail.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/tech/teacherpool/${system_lang}.js"></script>
<title>教师详细界面</title>
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
										<!-- 教师头像 -->
										<label> <!-- 教师头像 --> <s:property
												value="getText('teacherPhoto')" />
										</label>
										<div class="input-group"
											style="text-align: center; vertical-align: middle; line-height: 200px; border: 2px solid #88C4F7; margin-bottom: 17px; margin-left: 25%; margin-top: 5%;">
											<img class="attachment-img"
												style="height: 160px; width: 160px;" id="photo"
												name="tepVo.photo" value="${tepVo.photo}" src=""
												alt="Attachment Image">
										</div>
									</div>
									<div class="form-group">
										<!-- 账户角色 -->
										<label for="role_name"> <!-- 账户角色 --> <s:property
												value="getText('teacherRole')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-group"></li></span> <input type="text"
												id="role_name" name="tepVo.role_name" class="form-control"
												value="${tepVo.role_name}" readonly>
										</div>
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
										<label for="teacType_name"> <!-- 教师类别 --> <s:property
												value="getText('teacherType')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-bar-chart"></li></span> <input type="text"
												id="teacType_name" class="form-control"
												name="tepVo.teacType_name" value="${tepVo.teacType_name}"
												readonly>
										</div>
									</div>
									<div class="form-group">
										<label for="enable_name"> <!-- 生效标识 --> <s:property
												value="getText('enableName')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-bar-chart"></li></span> <input type="text"
												id="enable_name" name="tepVo.enable_name"
												class="form-control" value="${tepVo.enable_name}" readonly>
										</div>
									</div>
									<div class="form-group">
										<label for="gender_name"> <!-- 性别 --> <s:property
												value="getText('teacherSex')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-venus-mars"></li></span> <input type="text"
												id="gender_name" class="form-control"
												name="tepVo.gender_name" value="${tepVo.gender_name}"
												readonly>
										</div>
									</div>
								</div>
								<div class="col-sm-3 col-xs-3">
									<div class="form-group">
										<!-- 登录名 -->
										<label for="username"> <!-- 登录名 --> <s:property
												value="getText('teacher.name')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-male"></li></span> <input type="text"
												id="username" class="form-control" name="tepVo.username"
												value="${tepVo.username}" readonly>
										</div>
									</div>
									<div class="form-group">
										<!-- 登录密码 -->
										<label for="password"> <!-- 登录密码 --> <s:property
												value="getText('teacherPwd')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-eye-slash"></li></span> <span type="text"
												id="password" class="form-control">******</span> <input
												type="hidden" value="${tepVo.password}" id="hiddenPwd"
												name="password" readonly="readonly" />
										</div>
									</div>
									<div class="form-group">
										<!-- 真实姓名 -->
										<label for="name"> <!-- 真实姓名 --> <s:property
												value="getText('trueName')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-tag"></li></span> <input type="text" id="name"
												class="form-control" name="tepVo.name" value="${tepVo.name}"
												readonly>
										</div>
									</div>
									<div class="form-group">
										<!-- 英文姓名 -->
										<label for="en_name"> <!-- 英文姓名 --> <s:property
												value="getText('enName')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-tag"></li></span> <input type="text" id="en_name"
												class="form-control" name="tepVo.en_name"
												value="${tepVo.en_name}" readonly>
										</div>
									</div>
								</div>
								<div class="col-sm-3 col-xs-3">
									<div class="form-group">
										<label for="cardType_name"> <!-- 证件类型 --> <s:property
												value="getText('cardType')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-bar-chart"></li></span> <input type="text"
												id="cardType_name" class="form-control"
												name="tepVo.cardType_name" value="${tepVo.cardType_name}"
												readonly>
										</div>
									</div>
									<div class="form-group">
										<label for="cardNbr"> <!-- 证件号码 --> <s:property
												value="getText('cardNumber')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-building-o"></li></span> <input type="text"
												id="cardNbr" name="tepVo.cardNbr" class="form-control"
												value="${tepVo.cardNbr}" readonly>
										</div>
									</div>
									<div class="form-group">
										<label for="mobile"> <!-- 手机号码(移动) --> <s:property
												value="getText('teacherMobile')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-mobile-phone"></li></span> <input type="text"
												id="mobile" class="form-control" name="tepVo.mobile"
												value="${tepVo.mobile}" readonly>
										</div>
									</div>
									<div class="form-group">
										<label for="email"> <!-- 电子邮件 --> <s:property
												value="getText('email')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-envelope-o"></li></span> <input type="text"
												id="email" class="form-control" name="tepVo.email"
												value="${tepVo.email}" readonly>
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
												class="form-control" name="tepVo.age" value="${tepVo.age}"
												readonly>
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
												value="${tepVo.birthday}" readonly>
										</div>
									</div>
									<div class="form-group">
										<label for="phone"> <!-- 电话号码(座机) --> <s:property
												value="getText('phone')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-phone"></li></span> <input type="text" id="phone"
												class="form-control" name="tepVo.phone"
												value="${tepVo.phone}" readonly>
										</div>
									</div>
									<div class="form-group">
										<!-- 学习或主教方向 -->
										<label for="direction_name"> <!-- 学习或主教方向 --> <s:property
												value="getText('teacherDir')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-star-half-empty"></li></span> <input type="text"
												id="direction_name" class="form-control"
												name="tepVo.direction_name" value="${tepVo.direction_name}"
												readonly>
										</div>
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
												value="${tepVo.university}" readonly>
										</div>
									</div>
									<div class="form-group">
										<!-- 专业 -->
										<label for="major">
											<!-- 专业 -->
											<s:property value="getText('major')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-bookmark-o"></li></span> <input type="text"
												id="major" class="form-control" name="tepVo.major"
												value="${tepVo.major}" readonly>
										</div>
									</div>
									<div class="form-group">
										<label for="education_background_name"> <!-- 学历 --> <s:property
												value="getText('educationBackground')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-book"></li></span> <input type="text"
												id="education_background_name" class="form-control"
												name="tepVo.education_background_name"
												value="${tepVo.education_background_name}" readonly>
										</div>
									</div>
									<div class="form-group">
										<label for="degree_name"> <!-- 学位 --> <s:property
												value="getText('degree')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-graduation-cap"></li></span> <input type="text"
												id="degree_name" class="form-control"
												name="tepVo.degree_name" value="${tepVo.degree_name}"
												readonly>
										</div>
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
												name="tepVo.contact_address" value="${tepVo.contact_address}"
												readonly>
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
												name="tepVo.home_address" value="${tepVo.home_address}"
												readonly>
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
												name="tepVo.description" value="${tepVo.description}"
												readonly>
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
			<!-- /.box-body -->
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
							<!-- 编辑教师信息 -->
							<s:property value="getText('editTeacherInfo')" />
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	</section>
</body>
</html>
<input type="hidden" value="${tepVo.nbr}" id="teNbr" />
