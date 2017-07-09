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
<script src="${pageContext.request.contextPath}/tech/plan/planManagement.js"></script>
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/tech/plan/${system_lang}.js"></script>
<title>教学计划管理首页</title>
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
              <!-- 课程名称 -->
                <label><!-- 课程名称 --><s:property value="getText('plan.courseName')"/></label>           
                <select class="form-control select" name="course_id" id="course_id" style="width: 100%;">
                  <option value=""></option>
                </select>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 开始时间 -->
				<label><!-- 开始时间 --><s:property value="getText('plan.starttime')"/></label>
				<div class="input-group">
					<div class="input-group-addon"><i class="fa fa-calendar"></i></div>
						<input type="text" class="form-control pull-right" name="start_time" id="start_time">
					</div>
			  </div>
			  <!-- /.form-group -->
			  <div class="form-group">
			  <!-- 结束时间 -->
				 <label><!-- 结束时间 --><s:property value="getText('plan.endtime')"/></label>
				 <div class="input-group">
				     <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
						 <input type="text" class="form-control pull-right" name="end_time" id="end_time">
				 </div>
			   </div>
			   <!-- /.form-group --> 
            </div>
            <!-- /.col -->
            <div class="col-md-6">
              <div class="form-group">
              <!-- 班级名称 -->
                <label><!-- 班级名称 --><s:property value="getText('plan.className')"/></label>
	            <select class="form-control select" name="class_id" id="class_id" style="width: 100%;">
                  <option value=""></option>
                </select>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 创建时间 -->
				<label><!-- 创建时间 --><s:property value="getText('plan.createTime')"/></label>
				<div class="input-group">
					<div class="input-group-addon"><i class="fa fa-calendar"></i></div>
						<input type="text" class="form-control pull-right" name="create_time" id="create_time">
					</div>
			  </div>
			  <!-- /.form-group -->
			  <div class="form-group">
			  <!-- 修改时间 -->
				 <label><!-- 修改时间 --><s:property value="getText('plan.updateTime')"/></label>
				 <div class="input-group">
				     <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
						 <input type="text" class="form-control pull-right" name="update_time" id="update_time">
				 </div>
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
					<li class="glyphicon glyphicon-remove"></li>&nbsp;&nbsp;<!-- 取消搜索条件 --><s:property value="getText('plan.resetSearch')"/></button> 
                  </td>                 
                  <td style="width:200px;">
                   <button type="button" id="searchButton" class="btn btn-block btn-success btn-sm">
                    <!-- 应用搜索条件 -->
					<li class="glyphicon glyphicon-ok"></li>&nbsp;&nbsp;<!-- 应用搜索条件 --><s:property value="getText('plan.applySearch')"/></button>  
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
            <!-- 教学计划列表 -->
              <h3 class="box-title" ><!-- 教学计划列表 --><s:property value="getText('plan.planList')"/></h3>
            </div>
            <table class="table text-center" style="margin-bottom:0px;width:100%;">
                <tr>     
                  <td style="width:100%;">
                    <button type="button" class="btn btn-block btn-primary btn-sm" id="addNewPlan">
                    <!-- 添加新的计划 -->
					<li class="glyphicon glyphicon-plus"></li>&nbsp;&nbsp;<!-- 添加新的教学计划 --><s:property value="getText('plan.addNewPlan')"/></button> 
                  </td>           
                </tr>
           </table>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped table-hover">
                <thead>
                <tr>
                  <!-- 课程名称 -->
                  <th><!-- 课程名称 --><s:property value="getText('plan.courseName')"/></th>
                  <!-- 班级名称 -->
                  <th><!-- 班级名称 --><s:property value="getText('plan.className')"/></th>
                  <!-- 开始时间 -->
                  <th><!-- 开始时间 --><s:property value="getText('plan.starttime')"/></th>
                  <!-- 结束时间 -->
                  <th><!-- 结束时间 --><s:property value="getText('plan.endtime')"/></th>
                  <!-- 创建时间 -->
                  <th><!-- 创建时间 --><s:property value="getText('plan.createTime')"/></th>
                  <!-- 创建人 -->
                  <th><!-- 创建人 --><s:property value="getText('plan.createPerson')"/></th>
                  <!-- 更新时间 -->
                  <th><!-- 更新时间 --><s:property value="getText('plan.updateTime')"/></th>
                  <!-- 更新人 -->
                  <th><!-- 更新人 --><s:property value="getText('plan.updatePerson')"/></th>
                  <!-- 操作 -->
                  <th><!-- 操作 --><s:property value="getText('plan.action')"/></th>
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
