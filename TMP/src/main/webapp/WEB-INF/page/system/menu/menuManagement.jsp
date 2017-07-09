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
<script src="${pageContext.request.contextPath}/system/menu/menuManagement.js"></script>
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/system/menu/${system_lang}.js"></script>
<title>菜单管理首页</title>
</head>
<body>
	<!-- Main content -->
    <section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
    <!-- 搜索条件 --> 
    <div class="box box-primary collapsed-box">
        <div class="box-header with-border" data-widget="collapse">
        <i class="fa fa-plus"></i>
          <!-- 搜索条件 -->
          <h3 class="box-title"><s:property value="getText('searchTitle')"/></h3>
        </div>
        <form id="searchForm">
        <!-- /.box-header -->
        <div class="box-body">
          <div class="row">
            <div class="col-md-6">
              <div class="form-group">
              <!-- 菜单名称 -->
                <label><s:property value="getText('menuName1')"/></label>           
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-tag"></li></span>
                	<input type="text" name="searchMenuName" id="searchMenuName" class="form-control" placeholder="Menu Name">
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 菜单描述 -->
                <label><s:property value="getText('menuDesc1')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-sticky-note-o"></li></span>
                	<input type="text" name="searchMenuDesc" id="searchMenuDesc" class="form-control" placeholder="Menu Description">
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- URL地址 -->
                <label><s:property value="getText('UrlAddress')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-television"></li></span>
                	<input type="text" name="searchMenuUrl" id="searchMenuUrl" class="form-control" placeholder="Menu Url Address">
              	</div>
              </div>
              <!-- /.form-group -->
            </div>
            <!-- /.col -->
            <div class="col-md-6">
              <div class="form-group">
              <!-- 菜单等级 -->
                <label><s:property value="getText('menuLevel')"/></label>
	            <select class="form-control select" name="searchMenuLevel" id="searchMenuLevel" style="width: 100%;"><!-- disabled 表示不可编辑 -->
	                <option value=""></option>
	            </select>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 一级菜单 -->
                <label><s:property value="getText('oneLevelMenu')"/></label>
                <select class="form-control select" name="searchParentMenu" id="searchParentMenu" style="width: 100%;">
                  <option value=""></option>
                </select>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 菜单状态 -->
                <label><s:property value="getText('menuStatus')"/></label>
                <select class="form-control select" name="searchMenuStatus" id="searchMenuStatus" style="width: 100%;">
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
                    <!-- 取消搜索条件 -->
					<li class="glyphicon glyphicon-remove"></li>&nbsp;&nbsp;<!-- 取消搜索条件 --><s:property value="getText('resetSearch')"/></button> 
                  </td>                 
                  <td style="width:200px;">
                   <button type="button" id="searchButton" class="btn btn-block btn-success btn-sm">
                    <!-- 应用搜索条件 -->
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
            <!-- 系统菜单列表 -->
              <h3 class="box-title"  ><s:property value="getText('menuList')"/></h3>
            </div>
            <table class="table text-center" style="margin-bottom:0px;width:100%;">
                <tr>     
                  <td style="width:100%;">
                    <button type="button" class="btn btn-block btn-primary btn-sm" onclick="add('','1')">
                    <!-- 添加新的一级主菜单 -->
					<li class="glyphicon glyphicon-plus"></li>&nbsp;&nbsp;<s:property value="getText('addOneLevelMenu')"/></button> 
                  </td>           
                </tr>
           </table>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped table-hover">
                <thead>
                <tr>
                  <!-- 菜单名称 -->
                  <th><!-- <li class="fa fa-fw fa-tag"></li>&nbsp;&nbsp; --><s:property value="getText('menuName1')"/></th>
                  <!-- 菜单描述 -->
                  <th><!-- <li class="fa fa-fw fa-sticky-note-o"></li>&nbsp;&nbsp; --><s:property value="getText('menuDesc1')"/></th>
                  <!-- URL地址 -->
                  <th><!-- <li class="fa fa-fw fa-television"></li>&nbsp;&nbsp; --><s:property value="getText('UrlAddress')"/></th>
                  <!-- 菜单等级 -->
                  <th><!-- <li class="fa fa-fw fa-reorder"></li>&nbsp;&nbsp; --><s:property value="getText('menuLevel')"/></th>
                  <!-- 父级菜单 -->
                  <th><!-- <li class="fa fa-fw fa-user"></li>&nbsp;&nbsp; --><s:property value="getText('pMenu')"/></th>
                  <!-- 菜单排序 -->
                  <th><!-- <li class="fa fa-fw fa-signal"></li>&nbsp;&nbsp; --><s:property value="getText('menuOrder')"/></th>
                  <!-- 状态 -->
                  <th><!-- <li class="fa fa-fw fa-area-chart"></li>&nbsp;&nbsp; --><s:property value="getText('menuStatus')"/></th>
                  <!-- 操作 -->
                  <th><!-- <li class="fa fa-fw fa-cog"></li>&nbsp;&nbsp; --><s:property value="getText('option')"/></th>
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
