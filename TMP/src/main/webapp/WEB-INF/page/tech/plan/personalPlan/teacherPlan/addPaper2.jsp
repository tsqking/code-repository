<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background-color:#ecf0f5";>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/common/image/logo.ico" />
<s:include value="../../../../common.jsp"></s:include>
<!-- 页面js -->
 <script src="${pageContext.request.contextPath}/tech/plan/personalPlan/teacherPlan/addPaper2.js"></script> 
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/tech/plan/personalPlan/${system_lang}.js"></script>
<style type="text/css">
	.content {
    	min-height: 0px;
	}
	html, body {
    	min-height: 0px;
	}
</style>
<title>试卷添加-输入信息</title>
</head>
<body>
   <section class="content" id="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<!-- 搜索条件 -->
	<div class="box box-primary" id="box">
		<div class="box-header with-border">
			<h3 class="box-title" ><s:text name="paper.info.title"/></h3>
		</div>
		<form id="addForm">
			<input id="planId" name="planPaperVo.plan_id" value="${planPaperVo.plan_id}" style="display: none" />
			<input id="paper_id" name="planPaperVo.paper_id" value="${paperVo.id}" style="display: none" />
			<input id="paper_no" name="planPaperVo.paper_number" value="${paperVo.no}" style="display: none" />
			<input id="class_id" name="planPaperVo.class_id" value="${planPaperVo.class_id}" style="display: none" />
			<input id="course_id" name="planPaperVo.course_id" value="${planPaperVo.course_id}" style="display: none" />
			<input id="paper_name" value="${paperVo.name}" style="display: none" />
			<input id="paper_use_flag_name" value="${paperVo.use_flag_name}" style="display: none" />
			<input id="paper_property_name" value="${paperVo.property_name}" style="display: none" />
			<input id="paper_total_item" value="${paperVo.total_item}" style="display: none" />
			<input id="paper_total_score" value="${paperVo.total_score}" style="display: none" />
			<input id="paper_total_time" value="${paperVo.total_time}" style="display: none" />
			<div class="callout callout-info" style="margin-bottom: 0px;margin-top: 10px;margin-left: 10px;margin-right: 10px;">
                <h4><s:text name="paper.info"/></h4>
                <div id="contacts">
                </div>
            </div>
			<!-- /.box-header -->
			<div class="box-body">
			    <div class="row">
				    <div class="col-md-6 col-xs-6">	
						<div class="form-group">
							<label for="paper_start_time"><s:text name="start.time"/><small><i class="fa fa-star notNull"></i></small></label> 
							<div class="input-group">
							<div class="input-group-addon"><i class="fa fa-clock-o"></i></div>
							<input type="text"  name="planPaperVo.paper_start_time"  id="paper_start_time" class="form-control" 
							style="width: 100%;" placeholder="Open Time">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">	
						<div class="form-group">
							<label for="paper_end_time"><s:text name="end.time"/><small><i class="fa fa-star notNull"></i></small></label> 
							<div class="input-group">
							<div class="input-group-addon"><i class="fa fa-clock-o"></i></div>
							<input type="text"  name="planPaperVo.paper_end_time"  id="paper_end_time" class="form-control" 
							style="width: 100%;" placeholder="Close Time">
							</div>
						</div>
					</div>	
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">	
						<div class="form-group">
							<label for="paper_type"><s:text name="paper.pro"/><small><i class="fa fa-star notNull"></i></small></label> 
							<select class="form-control select" class=""
								name="planPaperVo.paper_type" id="paper_type" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>	
					<div class="col-md-6 col-xs-6">	
						<div class="form-group">
							<label for="paper_long_time"><s:text name="time.long"/><small><i class="fa fa-star notNull"></i></small></label> 
							<div class="input-group">
							<div class="input-group-addon"><i class="fa fa-history"></i></div>
							<input type="text"  name="planPaperVo.paper_long_time"  id="paper_long_time" class="form-control" 
							style="width: 100%;" placeholder="Duration" readonly="readonly">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 col-xs-12">	
						<div class="form-group">
							<label for="paper_remark" ><s:text name="remark.info"/></label>
								<textarea style="font-size:14px;" 
								maxlength="100" cols="80" rows="5"  
								id="paper_remark" 
								name="planPaperVo.paper_remark"
								class="form-control" placeholder="Remark"></textarea>
						</div>
					</div>
				</div>
			<!-- /.box-body -->
			<div class="box-footer" style="padding: 0 0 0 0">
				<div class="col-md-12 col-xs-12" style="margin-bottom: 0px;margin-top: 10px;">
					<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
						<button type="button" id="closeButton"
								class="btn btn-block btn-primary btn-sm"
								onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));refreshTable();">
								<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;
								<s:text name="closeButton" />
							</button>
						</div>
						<div class="col-md-3 col-xs-3">
							<button type="button" id="updateButton" onclick="addPaperTest()"
								class="btn btn-block btn-primary btn-sm">
								<i class="fa fa-check"></i>&nbsp;&nbsp;&nbsp;
								<s:text name="save" />
							</button>
						</div>
				</div>
			</div>
			</div>
		</form>
	</div>
	</section>
</body>
</html>