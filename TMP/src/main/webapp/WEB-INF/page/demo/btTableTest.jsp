<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<s:include value="../common.jsp"></s:include>
<!-- 页面js -->
<script src="${pageContext.request.contextPath}/demo/btTableTest.js"></script>
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/demo/${system_lang}.js"></script>
<title>员工管理首页</title>
</head>
<body>
   <section class="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<!-- 搜索条件 -->
	<div class="box box-primary collapsed-box">
		<div class="box-header with-border" data-widget="collapse">
			<i class="fa fa-plus"></i>
			<h3 class="box-title" ><s:text name="test.searchTitle"/></h3>
		</div>
		<form id="searchForm">
			<!-- /.box-header -->
			<div class="box-body">
			    <div class="row">
						<div class="col-md-6 col-xs-6">
							<div class="form-group">
				            	<label><s:text name="test.no"/></label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-fw fa-tag"></li></span>
				                	<input type="text" name="no" id="no" class="form-control" placeholder="Point NO">
				              	</div>
				            </div>
				        </div>
						<div class="col-md-6 col-xs-6">
							<div class="form-group">
				            	<label><s:text name="test.name"/></label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-fw fa-tag"></li></span>
				                	<input type="text" name="name" id="name" class="form-control" placeholder="Point Name">
				              	</div>
				            </div>
				        </div>
					</div>
			    
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label><s:text name="test.sex"/></label> <select class="form-control select" 
								 id="sex" name="sex"
								style="width: 100%;">
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
						</div>
						
					</div>
					<div class="col-md-6">	
						<div class="form-group">
							<label><s:text name="test.enable"/></label> <select class="form-control select" class="enable"
							 id="enable" name="enable"
								style="width: 100%;">
								<option value="可用">可用</option>
								<option value="不可用">不可用</option>
							</select>
						</div>
					</div>
				</div>
				<!-- /.row -->
			</div>
			<!-- /.box-body -->
			<div class="box-footer" style="padding: 0 0 0 0">
				<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
					<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
						<button type="button" id="closeButton"
							class="btn btn-block btn-primary btn-sm"><!-- onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));" -->
							<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="test.resetButton"/>
						</button>
					</div>
					<div class="col-md-3 col-xs-3">
						<button type="button" id="searchButton"
							class="btn btn-block btn-primary btn-sm">
							<i class="fa fa-check"></i>&nbsp;&nbsp;&nbsp;<s:text name="test.searchButton"/>
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div id="toolbarId" class="btn-group">
      <button id="btn_add" type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
      </button>
      <button id="btn_edit" type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
      </button>
      <button id="btn_delete" type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
      </button>
    </div>
	<!-- 数据表格 -->
	<div class="row">
		<div class="col-xs-12">
			<div class="box box-primary" style="margin-bottom: 5px;">
				<div class="box-body">
					<table id="tableId" class="table table-bordered table-striped table-hover">
					</table>	
				</div>
			</div>
		</div>
	</div>
	</section>
</body>
</html>