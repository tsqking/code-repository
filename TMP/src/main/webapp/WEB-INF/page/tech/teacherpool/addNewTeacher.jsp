<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background-color:#ecf0f5;">
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
	src="${pageContext.request.contextPath}/tech/teacherpool/teacherAdd.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/tech/teacherpool/${system_lang}.js"></script>
<title>教师添加界面</title>
</head>
<!-- onload="window.print();" -->
<body>
	<!-- Main content -->
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<div class="box">
			<div class="box-body">
				<form id="searchForm">
					<div class="col-md-12 col-xs-12">
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
											<div class="col-xs-3 col-sm-3">
												<div class="form-group">
													<label> <!-- 教师头像 --> <s:property
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
													<label for="role"> <!-- 角色 --> <s:property
															value="getText('teacherRole')" />
													</label> <select class="form-control select" id="role"
														style="width: 100%;" name="tepVo.role">
													</select>
												</div>
											</div>
											<div class="col-xs-3 col-sm-3">
												<div class="form-group">
													<label for="no"> <!-- 教师号 --> <s:property
															value="getText('teacherNumber')" />
													</label>
													<div class="input-group">
														<span class="input-group-addon"><li
															class="fa fa-fw fa-flag-o"></li></span> <input type="text" id="no"
															name="tepVo.no" class="form-control" readonly="readonly"
															value="<s:property value="getText('systemInfo')"/>">
														<!-- 系统生成 -->
													</div>
												</div>
												<div class="form-group">
													<label for="teacType"> <!-- 教师类别 --> <s:property
															value="getText('teacherType')" /><small><i class="fa fa-star notNull"></i></small>
													</label> <select class="form-control select" id="teacType"
														style="width: 100%;" name="tepVo.teacType">
													</select>
												</div>
												<div class="form-group">
													<label for="enable"> <!-- 生效标识 --> <s:property
															value="getText('enableName')" /><small><i class="fa fa-star notNull"></i></small>
													</label> <select class="form-control select" id="enable"
														style="width: 100%;" name="tepVo.enable">
													</select>
												</div>
												<div class="form-group">
													<label for="gender"> <!-- 性别 --> <s:property
															value="getText('teacherSex')" /><small><i class="fa fa-star notNull"></i></small>
													</label> <select class="form-control select" id="gender"
														style="width: 100%;" name="tepVo.gender">
													</select>
												</div>
											</div>
											<div class="col-xs-3 col-sm-3">
												<div class="form-group">
													<label for="username"> <!-- 登录名 --> <s:property
															value="getText('teacher.name')" /><small><i class="fa fa-star notNull"></i></small> 
													</label>
													<div class="input-group">
														<span class="input-group-addon"><li
															class="fa fa-fw fa-male"></li></span> <input type="text"
															id="username" name="tepVo.username" class="form-control">
													</div>
												</div>
												<div class="form-group">
													<label for="hiddenPwd"> <!-- 登录密码 --> <s:property
															value="getText('teacherPwd')" /><small><i class="fa fa-star notNull"></i></small>
													</label>
													<div class="input-group">
														<span class="input-group-addon"><li
															class="fa fa-fw fa-eye-slash"></li></span> <span type="password"
															id="password" class="form-control"></span> <input
															type="hidden" id="hiddenPwd" name="tepVo.password" />
													</div>
												</div>
												<div class="form-group">
													<label for="name"> <!-- 真实姓名 --> <s:property
															value="getText('trueName')" /><small><i class="fa fa-star notNull"></i></small>
													</label>
													<div class="input-group">
														<span class="input-group-addon"><li
															class="fa fa-fw fa-tag"></li></span> <input type="text" id="name"
															name="tepVo.name" class="form-control">
													</div>
												</div>
												<div class="form-group">
													<label for="en_name"> <!-- 英文姓名 --> <s:property
															value="getText('enName')" /><small><i class="fa fa-star notNull"></i></small>
													</label>
													<div class="input-group">
														<span class="input-group-addon"><li
															class="fa fa-fw fa-tag"></li></span> <input type="text"
															id="en_name" name="tepVo.en_name" class="form-control">
													</div>
												</div>
											</div>
											<div class="col-xs-3 col-sm-3">
												<div class="form-group">
													<label for="cardType"> <!-- 卡片类型 --> <s:property
															value="getText('cardType')" /><small><i class="fa fa-star notNull"></i></small>
													</label> <select class="form-control select" id="cardType"
														style="width: 100%;" name="tepVo.cardType">
													</select>
												</div>
												<div class="form-group">
													<label for="cardNbr"> <!-- 证件号码 --> <s:property
															value="getText('cardNumber')" /><small><i class="fa fa-star notNull"></i></small>
													</label>
													<div class="input-group">
														<span class="input-group-addon"><li
															class="fa fa-fw fa-building-o"></li></span> <input type="text"
															id="cardNbr" name="tepVo.cardNbr" class="form-control">
													</div>
												</div>
												<div class="form-group">
													<label for="mobile"> <!-- 手机号码(移动) --> <s:property
															value="getText('teacherMobile')" /><small><i class="fa fa-star notNull"></i></small>
													</label>
													<div class="input-group">
														<span class="input-group-addon"><li
															class="fa fa-fw fa-mobile-phone"></li></span> <input type="text"
															id="mobile" name="tepVo.mobile" class="form-control">
													</div>
												</div>
												<div class="form-group">
													<label for="email"> <!-- 电子邮件 --> <s:property
															value="getText('email')" /><small><i class="fa fa-star notNull"></i></small>
													</label>
													<div class="input-group">
														<span class="input-group-addon"><li
															class="fa fa-fw fa-envelope-o"></li></span> <input type="text"
															id="email" name="tepVo.email" class="form-control">
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="tab-pane" id="otherInfo">
										<div class="row">
											<div class="col-xs-3 col-sm-3">
												<div class="form-group">
													<label for="age"> <!-- 年龄 --> <s:property
															value="getText('teacherAge')" />
													</label>
													<div class="input-group">
														<span class="input-group-addon"><li
															class="fa fa-fw fa-child"></li></span> <input type="text" id="age"
															name="tepVo.age" class="form-control">
													</div>
												</div>
												<div class="form-group">
													<label for="birthday"> <!-- 生日 --> <s:property
															value="getText('birthday')" />
													</label>
													<div class="input-group">
														<span class="input-group-addon"><li
															class="fa fa-fw fa-calendar"></li></span> <input type="text"
															id="birthday" name="tepVo.birthday" class="form-control">
													</div>
												</div>
												<div class="form-group">
													<label for="phone"> <!-- 电话号码(座机) --> <s:property
															value="getText('phone')" />
													</label>
													<div class="input-group">
														<span class="input-group-addon"><li
															class="fa fa-fw fa-phone"></li></span> <input type="text"
															id="phone" name="tepVo.phone" class="form-control">
													</div>
												</div>
												<div class="form-group">
													<label for="direction"> <!-- 主教方向 --> <s:property
															value="getText('teacherDir')" />
													</label> <select class="form-control select" id="direction"
														style="width: 100%;" name="tepVo.direction">
													</select>
												</div>
											</div>
											<div class="col-xs-3 col-sm-3">
												<div class="form-group">
													<label for="university"> <!-- 最高毕业院校 --> <s:property
															value="getText('university')" />
													</label>
													<div class="input-group">
														<span class="input-group-addon"><li
															class="fa fa-university"></li></span> <input type="text"
															id="university" name="tepVo.university"
															class="form-control">
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
															id="major" class="form-control" name="tepVo.major" >
													</div>
												</div>
												<div class="form-group">
													<label for="education_background"> <!-- 学历 --> <s:property
															value="getText('educationBackground')" />
													</label> <select class="form-control select" id="education_background"
														style="width: 100%;" name="tepVo.education_background">
													</select>
												</div>
												<div class="form-group">
													<label for="degree"> <!-- 学位 --> <s:property
															value="getText('degree')" />
													</label> <select class="form-control select" id="degree"
														style="width: 100%;" name="tepVo.degree">
													</select>
												</div>
											</div>
											<div class="col-xs-3 col-sm-3">
												<div class="form-group">
													<label for="contact_address"> <!-- 联系地址 --> <s:property
															value="getText('teacherAddress')" />
													</label>
													<div class="input-group">
														<span class="input-group-addon"><li
															class="fa fa-fw fa-home"></li></span> <input type="text"
															id="contact_address" name="tepVo.contact_address"
															class="form-control">
													</div>
												</div>
												<div class="form-group">
													<label for="home_address"> <!-- 家庭地址 --> <s:property
															value="getText('homeAddress')" />
													</label>
													<div class="input-group">
														<span class="input-group-addon"><li
															class="fa fa-fw fa-home"></li></span> <input type="text"
															id="home_address" name="tepVo.home_address"
															class="form-control">
													</div>
												</div>
												<div class="form-group">
													<label for="description"> <!-- 人员情况简介 --> <s:property
															value="getText('teacherDesc')" />
													</label>
													<div class="input-group">
														<span class="input-group-addon"><li
															class="fa fa-fw fa-building-o"></li></span> <input type="text"
															id="description" name="tepVo.description"
															class="form-control">
													</div>
												</div>
											</div>
											<div class="col-xs-3 col-sm-3">
												<div class="form-group">
													<label for="create_time"> <!-- 创建时间 --> <s:property
															value="getText('create_time')" />
													</label>
													<div class="input-group">
														<span class="input-group-addon"><li
															class="fa fa-fw fa-clock-o"></li></span> <input type="text"
															id="create_time" name="tepVo.create_time"
															class="form-control" readonly="readonly"
															value="<s:property value="getText('nowTime')"/>">
														<!-- 当前时间 -->
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
															class="form-control" readonly="readonly"
															value="<s:property value="getText('nowUser')"/>">
														<!-- 当前用户 -->
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
															class="form-control" readonly="readonly"
															value="<s:property value="getText('nowTime')"/>">
														<!-- 当前时间 -->
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
															class="form-control" readonly="readonly"
															value="<s:property value="getText('nowUser')"/>">
														<!-- 当前用户 -->
													</div>
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
										<s:property value="getText('applyInfoChange')" />
									</button>
							</div>
						</div>
					</div>
				</form>
			</div>
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
						<!-- 填写新密码 -->
						<s:property value="getText('inputNewPwd')" />
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
										<label for="Pwd1"> <!-- 输入密码 --> <s:property
												value="getText('inputPwd')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-eye-slash"></li></span> <input type="password"
												id="Pwd1" class="form-control">
										</div>
									</div>
									<!-- /.form-group -->
									<div class="form-group">
										<label for="Pwd2"> <!-- 重复输入密码 --> <s:property
												value="getText('inputPwd2')" />
										</label>
										<div class="input-group">
											<span class="input-group-addon"><li
												class="fa fa-fw fa-eye-slash"></li></span> <input type="password"
												id="Pwd2" class="form-control">
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
						<s:property value="getText('closeWin')" />
					</button>
					<button type="button" class="btn btn-primary" id="pwdModalApply">
						<!-- 确认输入 -->
						<s:property value="getText('inputOk')" />
					</button>
				</div>
			</div>
		</div>
	</div>

	<input type="hidden" value="${tepVo.nbr}" id="nbr" />
</body>
</html>
