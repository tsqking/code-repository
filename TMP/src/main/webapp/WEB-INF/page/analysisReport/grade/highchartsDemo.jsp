<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background-color:#ecf0f5";>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/common/image/logo.ico" />
<s:include value="../../common.jsp"></s:include>
<title>各成绩段人数分析</title>
</head>
<body>
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
								<!-- 科目 -->
								<s:property value="getText('subject')" />
							</label> <select class="form-control select" name="subject" id="subject"
								style="width: 100%;">
								<!-- disabled 表示不可编辑 -->
								<option value=""></option>
							</select>
						</div>
						<div class="form-group">
							<label>
								<!-- 公司 -->
								<s:property value="getText('company')" />
							</label> <select class="form-control select" name="company" id="company"
								style="width: 100%;">
								<!-- disabled 表示不可编辑 -->
								<option value=""></option>
							</select>
						</div>
						<!-- /.form-group -->
					</div>
					<!-- /.col -->
					<div class="col-md-6">
						<div class="form-group">
							<label>
								<!-- 时间段 -->
								<s:property value="getText('time')" />
							</label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control pull-right"
									name="time" id="time">
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
	
	<!-- echarts -->
<!--     <div id="main" style=" margin-left:10%;width: 80%;height:500px;"></div> -->
    <div id="container" style=" margin-left:10%;width: 80%;height:750px;"></div>
</body>
</html>