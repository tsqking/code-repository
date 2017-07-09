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
<s:include value="../../../common.jsp"></s:include>
<!-- 页面js -->
<script src="${pageContext.request.contextPath}/tech/teacherpool/tecplanToPool/planToPool.js"></script>
<!-- 页面js国际化 -->
<script src="${pageContext.request.contextPath}/tech/teacherpool/tecplanToPool/${session.system_lang}.js"></script>
<title>教学计划—教师资源库</title>
</head>
<body>
	<!-- Main content -->
	<section class="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<!-- 搜索条件 -->
	<div class="box box-primary">
		<div class="box-header with-border" data-widget="collapse">
			<i class="fa fa-minus"></i>
			<h3 class="box-title">
				<!-- 搜索条件 -->
				<s:property value="getText('searchInfo')" />
			</h3>
		</div>
		<form id="searchForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row" style="display:none;">
					<div class="col-md-4 col-xs-4">
						<div class="form-group">
			            	<label>ID</label>           
			            	<div class="input-group">
			                	<span class="input-group-addon"><li class="fa fa-file-o"></li></span>
			                	<input type="text" name="ptVo.id" id="point_id" class="form-control" style="display:none;" value="${ptVo.id}">
			              	</div>
			            </div>
					</div>
					<div class="col-md-4 col-xs-4">
						<div class="form-group">
			            	<label>starttime</label>           
			            	<div class="input-group">
			                	<span class="input-group-addon"><li class="fa fa-file-o"></li></span>
			                	<input type="text" name="tepVo.start_time" id="start_time" class="form-control" style="display:none;" value="${tepVo.start_time}">
			              	</div>
			            </div>
					</div>
					<div class="col-md-4 col-xs-4">
						<div class="form-group">
			            	<label>endtime</label>           
			            	<div class="input-group">
			                	<span class="input-group-addon"><li class="fa fa-file-o"></li></span>
			                	<input type="text" name="tepVo.end_time" id="end_time" class="form-control" style="display:none;" value="${tepVo.end_time}">
			              	</div>
			            </div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-xs-4">
						<div class="form-group">
							<label>
								<!-- 学号/教员号 -->
								<s:property value="getText('userNbr')" />
							</label>
							<div class="input-group">
								<span class="input-group-addon">
									<li class="fa fa-fw fa-bookmark-o"></li>
								</span>
								<input type="text" name="tepVo.nbr" id="nbr" class="form-control" placeholder="User No.">
							</div>
						</div>
					</div>
					<div class="col-md-4 col-xs-4">
						<div class="form-group">
							<label>
								<!-- 姓名 -->
								<s:property value="getText('names')" />
							</label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-mobile-phone"></li></span> <input type="text"
									name="tepVo.name" id="name" class="form-control"
									placeholder="Name">
							</div>
						</div>
					</div>
					<div class="col-md-4 col-xs-4">
						<div class="form-group">
							<label><s:text name="freeState"/></label> <select class="form-control select"
								name="tepVo.description" id="freeState" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>
			</div>
			<!-- /.box-body -->
			<div class="box-footer" style="padding: 0 0 0 0">
				<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
					<div class="col-md-2 col-xs-2 col-md-offset-6 col-xs-offset-6">
						<button type="button" id="closeButton"
								class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeWindow"/></button>
					</div>
					<div class="col-md-2 col-xs-2">
						<button type="button" id="resetButton"
								class="btn btn-block btn-info btn-sm"><i class="fa fa-repeat"></i>&nbsp;&nbsp;&nbsp;<s:text name="reset"/></button>
					</div>
					<div class="col-md-2 col-xs-2">
						<button type="button" id="searchButton"
								class="btn btn-block btn-success btn-sm"><i class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;<s:text name="search"/></button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- /.box --> <!-- 数据表格 -->
	<div class="row">
		<div class="col-xs-12">
			<div class="box" style="margin-bottom: 5px;">
				<div class="box-header">
					<h3 class="box-title">
						<!-- 教师资源库列表 -->
						<s:property value="getText('teacherpoolList')" />
					</h3>
				</div>
				<div class="box-body">
					<table id="teacherpool"
						class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
								<th>
									<!--中文姓名 -->
									<s:property value="getText('zh_name')" />
								</th>
								<th>
									<!--英文姓名 -->
									<s:property value="getText('en_name')" />
								</th>
								<th>
									<!-- 编号 -->
									<s:property value="getText('userNbr')" />
								</th>
								<th>
									<!-- 性别 -->
									<s:property value="getText('gender')" />
								</th>
								<th>
									<!-- 电子邮件 -->
									<s:property value="getText('email')" />
								</th>
								<th>
									<!-- 手机号码(移动) -->
									<s:property value="getText('mobile')" />
								</th>
								<th>
									<!-- 手机号码(移动) -->
									<s:property value="getText('freeState')" />
								</th>
								<th>
									<!-- 操作 -->
									<s:property value="getText('operation')" />
								</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	</section>

</body>
</html>
