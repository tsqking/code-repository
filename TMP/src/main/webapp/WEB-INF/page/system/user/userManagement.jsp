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
	src="${pageContext.request.contextPath}/system/user/userManagement.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/system/user/${system_lang}.js"></script>
<title>用户管理首页</title>
</head>
<body>
	<!-- Main content -->
	<section class="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<!-- 搜索条件 -->
	<div class="box box-primary collapsed-box">
		<div class="box-header with-border" data-widget="collapse">
			<i class="fa fa-plus"></i>
			<h3 class="box-title">
				<!-- 搜索条件 -->
				<s:property value="getText('searchInfo')" />
			</h3>
		</div>
		<form id="searchForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label>
								<!-- 账户角色 -->
								<s:property value="getText('userRole')" />
							</label> <select class="form-control select" name="role" id="role"
								style="width: 100%;">
								<!-- disabled 表示不可编辑 -->
								<option value=""></option>
							</select>
						</div>
						<!-- /.form-group -->
						<div class="form-group">
							<label>
								<!-- 登录名 -->
								<s:property value="getText('username')" />
							</label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-male"></li></span> <input type="text"
									name="username" id="username" class="form-control"
									placeholder="User Account">
							</div>
						</div>
						<!-- /.form-group -->
						<div class="form-group">
							<label>
								<!-- 学号/教员号 -->
								<s:property value="getText('userNumber')" />
							</label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-bookmark-o"></li></span> <input type="text"
									name="no" id="no" class="form-control"
									placeholder="User Number">
							</div>
						</div>
						<!-- /.form-group -->
						<div class="form-group">
							<label>
								<!-- 真实姓名 -->
								<s:property value="getText('trueName')" />
							</label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-tag"></li></span> <input type="text" name="name"
									id="name" class="form-control" placeholder="User Name">
							</div>
						</div>
						<!-- /.form-group -->
						<div class="form-group">
							<label>
								<!-- 英文姓名 -->
								<s:property value="getText('enName')" />
							</label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-tag"></li></span> <input type="text" name="en_name"
									id="en_name" class="form-control"
									placeholder="User English Name">
							</div>
						</div>
						<!-- /.form-group -->
						<div class="form-group">
							<label>
								<!-- 性别 -->
								<s:property value="getText('userSex')" />
							</label> <select class="form-control select" name="gender" id="gender"
								style="width: 100%;">
								<!-- disabled 表示不可编辑 -->
								<option value=""></option>
							</select>
						</div>
						<!-- /.form-group -->
						<div class="form-group">
							<label>
								<!-- 修改人 -->
								<s:property value="getText('updatePerson')" />
							</label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-user"></li></span> <input type="text"
									name="update_person" id="update_person" class="form-control"
									placeholder="Update Person">
							</div>
						</div>
						<!-- /.form-group -->
					</div>
					<!-- /.col -->
					<div class="col-md-6">
						<div class="form-group">
							<label>
								<!-- 手机号码(移动) -->
								<s:property value="getText('userMobile')" />
							</label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-mobile-phone"></li></span> <input type="text"
									name="mobile" id="mobile" class="form-control"
									placeholder="User Mobile">
							</div>
						</div>
						<!-- /.form-group -->
						<div class="form-group">
							<label>
								<!-- 电话号码(座机) -->
								<s:property value="getText('phone')" />
							</label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-phone"></li></span> <input type="text" name="phone"
									id="phone" class="form-control" placeholder="User Phone">
							</div>
						</div>
						<!-- /.form-group -->
						<div class="form-group">
							<label>
								<!-- 电子邮件 -->
								<s:property value="getText('email')" />
							</label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-envelope-o"></li></span> <input type="text"
									name="email" id="email" class="form-control"
									placeholder="User Email">
							</div>
						</div>
						<!-- /.form-group -->
						<div class="form-group">
							<label>
								<!-- 年龄 -->
								<s:property value="getText('userAge')" />
							</label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-child"></li></span> <input type="text" name="age"
									id="age" class="form-control" placeholder="User Age">
							</div>
						</div>
						<!-- /.form-group -->
						<div class="form-group">
							<label>
								<!-- 生效标识 -->
								<s:property value="getText('enableName')" />
							</label> <select class="form-control select" name="enable" id="enable"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
						<!-- /.form-group -->
						<div class="form-group">
							<label>
								<!-- 创建时间 -->
								<s:property value="getText('create_time')" />
							</label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control pull-right"
									name="create_time" id="create_time">
							</div>
						</div>
						<!-- /.form-group -->
						<div class="form-group">
							<label>
								<!-- 修改时间 -->
								<s:property value="getText('updateTime')" />
							</label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control pull-right"
									name="update_time" id="update_time">
							</div>
						</div>
						<!-- /.form-group -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.box-body -->
			<div class="box-footer" style="padding: 0 0 0 0">
				<table class="table text-center" style="margin-bottom: 0px;">
					<tr>
						<td style="width: 200px;"></td>
						<td style="width: 200px;"></td>
						<td style="width: 200px;"></td>
						<td style="width: 200px;">
							<button type="button" id="resetButton"
								class="btn btn-block btn-primary btn-sm">
								<li class="glyphicon glyphicon-remove"></li>&nbsp;&nbsp;
								<!-- 取消搜索条件 -->
								<s:property value="getText('resetSearch')" />
							</button>
						</td>
						<td style="width: 200px;">
							<button type="button" id="searchButton"
								class="btn btn-block btn-success btn-sm">
								<li class="glyphicon glyphicon-ok"></li>&nbsp;&nbsp;
								<!-- 应用搜索条件 -->
								<s:property value="getText('applySearch')" />
							</button>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<!-- /.box --> <!-- 数据表格 -->
	<div class="row">
		<div class="col-xs-12">
			<div class="box" style="margin-bottom: 5px;">
				<div class="box-header">
					<h3 class="box-title" >
						<!-- 系统账户列表 -->
						<s:property value="getText('userList')" />
					</h3>
				</div>
				 <table class="table text-center" style="margin-bottom:0px;width:100%;">
	                <tr> 
	                	<td style="width:33%;">
	                   <button type="button" class="btn btn-block btn-primary btn-sm" onclick="add()"> <li class="fa fa-user-plus"></li>&nbsp;&nbsp;
							<s:property value="getText('addUser')" />
						</button>
	                  </td> 
	                  <td style="width:33%;">
	                    <button type="button" class="btn btn-block btn-primary btn-sm" onclick="batchAdd()"> <li class="glyphicon glyphicon-plus"></li>&nbsp;&nbsp;
							<s:property value="getText('batchAddUser')" />
						</button>
	                  </td>     
	                  <td style="width:33%;">
	                   <button type="button" class="btn btn-block btn-primary btn-sm" onclick="downTemplate()"> <li class="fa fa-cloud-download"></li>&nbsp;&nbsp;
							<s:property value="getText('batchTemple')" />
						</button>
	                  </td>           
	                </tr>
	           </table>
	           
	           		
				<!-- /.box-header -->
				<div class="box-body">
					<table id="example1"
						class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
								<th>
									<!-- <li class="fa fa-fw fa-male"></li>&nbsp;&nbsp; -->
									<!-- 登录名 -->
									<s:property value="getText('username')" />
								</th>
								<th>
									<!-- <li class="fa fa-fw fa-group"></li>&nbsp;&nbsp; -->
									<!-- 账户角色 -->
									<s:property value="getText('userRole')" />
								</th>
								<th>
									<!-- <li class="fa fa-fw fa-tag"></li>&nbsp;&nbsp; -->
									<!-- 真实姓名 -->
									<s:property value="getText('trueName')" />
								</th>
								<th>
									<!-- <li class="fa fa-fw fa-venus-mars"></li>&nbsp;&nbsp; -->
									<!-- 性别 -->
									<s:property value="getText('userSex')" />
								</th>
								<th>
									<!-- <li class="fa fa-fw fa-child"></li>&nbsp;&nbsp; -->
									<!-- 年龄 -->
									<s:property value="getText('userAge')" />
								</th>
								<th>
									<!-- <li class="fa fa-fw fa-mobile-phone"></li>&nbsp;&nbsp; -->
									<!-- 手机号码(移动) -->
									<s:property value="getText('userMobile')" />
								</th>
								<th>
									<!-- <li class="fa fa-fw fa-envelope-o"></li>&nbsp;&nbsp; -->
									<!-- 电子邮件 -->
									<s:property value="getText('email')" />
								</th>
								<th>
									<!-- <li class="fa fa-fw fa-bar-chart"></li>&nbsp;&nbsp; -->
									<!-- 生效标识 -->
									<s:property value="getText('enableName')" />
								</th>
								<th>
									<!-- <li class="fa fa-fw fa-gear"></li>&nbsp;&nbsp; -->
									<!-- 操作 -->
									<s:property value="getText('action')" />
								</th>
							</tr>
						</thead>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row --> </section>
	<!-- /.content -->
	
	<!-- modal -->
	<div class="modal" id="batchAddModel">
		<form id="batchAddForm" >
			<div class="modal-dialog" style="width:40%;height:20%">
				<div class="modal-content"
					style="width: 100%; height: 100%; margin-top: 25%;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title"><s:text name="batchAddTitle"/></h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 col-xs-12">
								<div class="form-group">
				                	<label><s:text name="batchPeopleList"/></label>
				                	<input type="file" id="batchPeopleList" name="file.file">
				                	<p class="help-block"><s:text name="batchAddHelp"/></p>
				                </div>
							</div>
				    	</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="closeButton" class="btn btn-primary"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/></button>
						<button type="button" id="batchUpload" class="btn btn-primary"><i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;&nbsp;<s:text name="upload"/></button>
					</div>
				</div>
			</div>
		</form>
	</div>

</body>
</html>
