<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<s:include value="../../../common.jsp"></s:include>
<!-- 页面js -->
<script src="${pageContext.request.contextPath}/campusRecruit/other/talentPool/talentPoolManage.js"></script>
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/campusRecruit/other/talentPool/${system_lang}.js"></script>
<title>人才资源管理</title>
</head>
<body>
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5;">
	<!-- 搜索条件 -->
	<div class="box box-primary collapsed-box">
		<div class="box-header with-border" data-widget="collapse">
			<i class="fa fa-plus"></i>
			<h3 class="box-title" ><s:text name="searchTitle"/></h3>
		</div>
		<form id="searchForm">
			<div class="box-body">
			    <div class="row">
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
			            	<label for="name"><s:text name="tal.name"/></label>           
			            	<div class="input-group">
			                	<span class="input-group-addon"><li class="fa fa-user"></li></span>
			                	<input type="text" name="name" id="name" class="form-control" placeholder="Name">
			              	</div>
			            </div>
			        </div>
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
			            	<label for="mobile"><s:text name="tal.mobile"/></label>           
			            	<div class="input-group">
			                	<span class="input-group-addon"><li class="fa fa-phone"></li></span>
			                	<input type="text" name="mobile" id="mobile" class="form-control" placeholder="Mobile">
			              	</div>
			            </div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="gender"><s:text name="tal.gender"/></label> <select class="form-control select" 
								id="gender" name="gender" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
			        </div>
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="talent_source"><s:text name="tal.talSource"/></label> <select class="form-control select" 
								id="talent_source" name="talent_source" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
			        </div>
				</div>
			    <div class="row">
			    	<div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="univ_id"><s:text name="tal.univ"/></label> <select class="form-control select" 
								id="univ_id" name="univ_id" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="college_id"><s:text name="tal.college"/></label> <select class="form-control select" 
								id="college_id" name="college_id" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="recruit_state"><s:text name="tal.rcState"/></label> <select class="form-control select" 
								id="recruit_state" name="recruit_state" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="graduate_month"><s:text name="tal.graduateMonth"/></label>
							<div class="input-group" style="width: 100%;">
<!-- 											<div class="input-group-addon"><i class="fa fa-calendar"></i></div> -->
								<input type="text"  name="graduate_month_start"  id="graduate_month_start" class="form-control"
								placeholder="Graduate Time Start" style="width:50%">
								<input type="text"  name="graduate_month_end"  id="graduate_month_end" class="form-control" style="width:50%" 
								placeholder="Graduate Time End">
							</div>
						</div>
			    	</div>
			    </div>
			    <div class="row">
			    	<div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="proj_group"><s:text name="tal.projGrop"/></label> <select class="form-control select" 
								id="proj_group" name="proj_group" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="work_location"><s:text name="tal.workLocation"/></label> <select class="form-control select" 
								id="work_location" name="work_location" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="position"><s:text name="tal.position"/></label> <select class="form-control select" 
								id="position" name="position" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
			        </div>
			    	 <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="in_company_time"><s:text name="tal.inDate"/></label>
							<div class="input-group" style="width: 100%;">
								<input type="text"  name="in_company_time"  id="in_company_time" class="form-control"
								placeholder="Join Company" style="width:50%">
								<input type="text"  name="in_proj_time"  id="in_proj_time" class="form-control" style="width:50%" 
								placeholder="Join Project">
							</div>
						</div>
			    	</div>
			    </div>
			    <div class="row">
			    	<div class="col-md-3 col-xs-3">
						<div class="form-group">
			            	<label for="create_person"><s:text name="createPerson"/></label>           
			            	<div class="input-group">
			                	<span class="input-group-addon"><li class="fa fa-user"></li></span>
			                	<input type="text" name="create_person" id="create_person" class="form-control" placeholder="Create Person">
			              	</div>
			            </div>
			        </div>
			    	<div class="col-md-3 col-xs-3">
			    		<div class="form-group">
							<label for="create_time"><s:text name="createTime"/></label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control pull-right"
									name="create_time" id="create_time" placeholder="Create Time">
							</div>
						</div>
			    	</div>
			    	<div class="col-md-3 col-xs-3">
						<div class="form-group">
			            	<label for="update_person"><s:text name="updatePerson"/></label>           
			            	<div class="input-group">
			                	<span class="input-group-addon"><li class="fa fa-user"></li></span>
			                	<input type="text" name="update_person" id="update_person" class="form-control" placeholder="Update Person">
			              	</div>
			            </div>
			        </div>
			        <div class="col-md-3 col-xs-3">
			    		<div class="form-group">
							<label for="update_time"><s:text name="updateTime"/></label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control pull-right"
									name="update_time" id="update_time" placeholder="Update Time">
							</div>
						</div>
			    	</div>
			    </div>
			</div>
			<div class="box-footer" style="padding: 0 0 0 0">
				<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
					<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
						<button type="button" id="resetButton"
							class="btn btn-block btn-primary btn-sm"><!-- onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));" -->
							<i class="fa fa-reply"></i>&nbsp;&nbsp;&nbsp;<s:text name="resetButton"/>
						</button>
					</div>
					<div class="col-md-3 col-xs-3">
						<button type="button" id="searchButton"
							class="btn btn-block btn-primary btn-sm">
							<i class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;<s:text name="searchButton"/>
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- 表格工具栏 -->
	<div id="toolbarId" class="btn-group">
		<button id="btn_add" type="button" class="btn btn-default">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span><s:text name="addButton"/>
		</button>
		<button id="btn_edit" type="button" class="btn btn-default">
			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span><s:text name="editButton"/>
		</button>
		<button id="btn_delete" type="button" class="btn btn-default">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span><s:text name="deleteButton"/>
		</button>
		<button id="btn_download" type="button" class="btn btn-default">
			<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span><s:text name="tal.export"/>
		</button>
	</div>
	<!-- 数据表格 -->
	<div class="row">
		<div class="col-xs-12">
			<div class="box box-primary" style="margin-bottom: 5px;">
				<div class="box-header">
					<h3 class="box-title"><s:text name="tal.talentListTitle"/></h3>
				</div>
				<div class="box-body">
					<table id="tableId" class="table table-bordered table-striped table-hover"  
							data-toggle="tooltip" title="<s:text name="dbClickTableInfo"/>"><!-- data-single-select="true"  -->
					</table>	
				</div>
			</div>
		</div>
	</div>
	</section>
</body>
</html>