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
<script src="${pageContext.request.contextPath}/system/user/roleManagement.js"></script>
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/system/user/${system_lang}.js"></script>
<title>账户拥有的角色管理</title>
</head>
<body>
	<!-- Main content -->
    <section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
    <!-- 搜索条件 --> 
    <div class="box box-primary collapsed-box">
        <div class="box-header with-border" data-widget="collapse">
        <i class="fa fa-plus"></i>
          <h3 class="box-title"><!-- 搜索条件 --><s:property value="getText('searchInfo')"/></h3>
        </div>
        <form id="searchForm">
        <!-- /.box-header -->
        <div class="box-body">
          <div class="row">
            <div class="col-md-6">
              <div class="form-group">
                <label><!-- 角色名称 --><s:property value="getText('roleName')"/></label>           
	            <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-tag"></li></span>
                	<input type="text" name="name" id="name" class="form-control" placeholder="User Email">
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
                <label><!-- 创建人员 --><s:property value="getText('createPerson')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-user-secret"></li></span>
                	<input type="text" name="create_person" id="create_person" class="form-control" placeholder="User Account">
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
                <label><!-- 更新人员 --><s:property value="getText('updatePerson')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-user-secret"></li></span>
                	<input type="text" name="update_person" id="update_person" class="form-control" placeholder="User Number">
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
                <label><!-- 角色状态 --><s:property value="getText('roleStatus')"/></label>
                <select class="form-control select" name="enable" id="enable" style="width: 100%;"><!-- disabled 表示不可编辑 -->
	                <option value=""></option>
	            </select>
              </div>
              <!-- /.form-group -->    
            </div>
            <!-- /.col -->
            <div class="col-md-6">
              <div class="form-group">
                <label><!-- 角色描述 --><s:property value="getText('roleDesc')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-font"></li></span>
                	<input type="text" name="description" id="description" class="form-control" placeholder="User Email">
              	</div>
              </div>
              <!-- /.form-group -->             
              <div class="form-group">
                <label><!-- 创建时间 --><s:property value="getText('createTime')"/></label>
                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <input type="text" class="form-control pull-right" name="create_time" id="create_time">
                </div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
                <label><!-- 更新时间 --><s:property value="getText('updateTime')"/></label>
                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <input type="text" class="form-control pull-right" name="update_time" id="update_time">
                </div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
                <label><!-- 是否拥有 --><s:property value="getText('has')"/></label>
                <select class="form-control select" name="has" id="has" style="width: 100%;"><!-- disabled 表示不可编辑 -->
	                <option value=""></option>
	            </select>
              </div>
              <!-- /.form-group -->   
            </div>
            <!-- /.col -->
          </div>
          <!-- /.row -->
        </div>
        <!-- /.box-body -->
        <div class="box-footer" style="padding:0 0 0 0">
          <table class="table text-center" style="margin-bottom:0px;">
                <tr>
                  <td style="width:200px;">
                  </td>
                  <td style="width:200px;">
                  </td>                 
                  <td style="width:200px;">
                  </td>     
                  <td style="width:200px;">
                    <button type="button" id="resetButton" class="btn btn-block btn-primary btn-sm">
					<li class="glyphicon glyphicon-remove"></li>&nbsp;&nbsp;<!-- 取消搜索条件 --><s:property value="getText('resetSearch')"/></button> 
                  </td>                 
                  <td style="width:200px;">
                   <button type="button" id="searchButton" class="btn btn-block btn-success btn-sm">
					<li class="glyphicon glyphicon-ok"></li>&nbsp;&nbsp;<!-- 应用搜索条件 --><s:property value="getText('applySearch')"/></button>  
                  </td>   
                </tr>
           </table>
        </div>
        </form>
      </div>
      <!-- /.box -->
      <!-- 数据表格 -->
      <div class="row">
        <div class="col-xs-12">
          <div class="box" style="margin-bottom: 5px;">
            <div class="box-header">
              <h3 class="box-title" >
              <label><!-- 当前操作账户 --><s:property value="getText('nowUser')"/></label>&nbsp;
              <label><!-- 账户名 --><s:property value="getText('userName')"/>
              : ${user.username} - 
              		 <!-- 真实姓名 --><s:property value="getText('trueName')"/>
              : ${user.name}</label></h3>
            </div>
            <table class="table text-center" style="margin-bottom:0px;width:100%;"> 
            	  <td style="width:50%;">
                    <button type="button" class="btn btn-block btn-primary btn-sm" id="deleteAllRoles">
					<li class="glyphicon glyphicon-remove"></li>&nbsp;&nbsp;<!-- 去除拥有的所有角色 --><s:property value="getText('removeHasRole')"/></button> 
                  </td>  
                  <td style="width:100%;">
                    <button type="button" class="btn btn-block btn-primary btn-sm" id="closeIframe">
					<li class="glyphicon glyphicon-plus"></li>&nbsp;&nbsp;<!-- 关闭窗口 --><s:property value="getText('closeWindows')"/></button> 
                  </td>           
                </tr>
           </table>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped table-hover">
                <thead>
                <tr>
                  <th><!-- <li class="fa fa-fw fa-folder-o"></li>&nbsp;&nbsp; --><!-- 是否拥有 --><s:property value="getText('has')"/></th>
                  <th><!-- <li class="fa fa-fw fa-tag"></li>&nbsp;&nbsp; --><!-- 角色名称 --><s:property value="getText('roleName')"/></th>
                  <th><!-- <li class="fa fa-fw fa-font"></li>&nbsp;&nbsp; --><!-- 角色描述 --><s:property value="getText('roleDesc')"/></th>
                  <th><!-- <li class="fa fa-fw fa-bar-chart"></li>&nbsp;&nbsp; --><!-- 角色状态 --><s:property value="getText('roleStatus')"/></th>
                  <th><!-- <li class="fa fa-fw fa-user"></li>&nbsp;&nbsp; --><!-- 创建人 --><s:property value="getText('createPerson')"/></th>
                  <th><!-- <li class="fa fa-fw fa-calendar"></li>&nbsp;&nbsp; --><!-- 创建时间 --><s:property value="getText('createTime')"/></th>
                  <th><!-- <li class="fa fa-fw fa-user"></li>&nbsp;&nbsp; --><!-- 更新人 --><s:property value="getText('updatePerson')"/></th>
                  <th><!-- <li class="fa fa-fw fa-calendar"></li>&nbsp;&nbsp; --><!-- 更新时间 --><s:property value="getText('updateTime')"/></th>
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
      <!-- /.row -->      
    </section>
    <!-- /.content -->
    
    
</body>
</html>

<input type="hidden" value="${user.id}" id="userId"/>
