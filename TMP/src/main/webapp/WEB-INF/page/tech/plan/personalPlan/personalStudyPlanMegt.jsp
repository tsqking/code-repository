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
<script src="${pageContext.request.contextPath}/tech/plan/personalPlan/personalStudyPlanMegt.js"></script>
<!-- 页面js国际化 -->
<script src="${pageContext.request.contextPath}/tech/plan/personalPlan/${session.system_lang}.js"></script>
<title>个人学习计划管理</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<!-- 搜索条件 -->
		<div class="box box-primary">
			<div class="box-header with-border" data-widget="collapse">
				<i class="fa fa-minus"></i>
				<h3 class="box-title" ><s:text name="ppVo.myLearnPlanTitle"/></h3>
			</div>
			<div class="box-body">
				<div class="row">
					<div class="col-xs-12">
						<div class="box box-primary" style="margin-bottom: 5px;">
							<div class="box-header">
								<h3 class="box-title"><s:text name="ppVo.learnPlanListTitle"/></h3>
							</div>
							<div class="box-body">
								<table id="learnPlanTable" class="table table-bordered table-striped table-hover">
									<thead>
										<tr>
											<th><s:text name="ppVo.courseName"/></th>
											<th><s:text name="ppVo.ClassName"/></th>
											<th><s:text name="ppVo.ClassLocation"/></th>
											<th><s:text name="ppVo.startTime"/></th>
											<th><s:text name="ppVo.endTime"/></th>
											<th><s:text name="operation"/></th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="box-footer" style="padding: 0 0 0 0">
				<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
					
				</div>
			</div>
		</div>
	</section>
</body>
</html>




