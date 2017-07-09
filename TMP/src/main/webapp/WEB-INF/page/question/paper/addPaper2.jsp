<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background-color:#ecf0f5">
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
<!-- js页面 -->
<script
	src="${pageContext.request.contextPath}/question/paper/addPaper2.js"></script>
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/question/paper/${system_lang}.js"></script>
<title>题库管理</title>
</head>
<body>
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<div class="box">
			<div class="box-body" id="box">
				<form id="searchForm" class="form-horizontal">
					<!-- 试卷Id -->
					<input type="hidden" name="id" id="paperVo_id" value="${paperVo.id }"/>
					<input type="hidden" name="name" id="paperVo_name" value="${paperVo.name }"/>
					<input type="hidden" name="property" id="paperVo_property" value="${paperVo.property }"/>
					<input type="hidden" name="use_flag" id="paperVo_useFlag" value="${paperVo.use_flag }"/>
					<input type="hidden" name="description" id="paperVo_description" value="${paperVo.description }"/>
					<input type="hidden" name="instruction" id="paperVo_instruction" value="${paperVo.instruction }"/>
					
					<!-- 步骤二 start -->
					<div class="form-group">
		                 <label for="name2" class="col-sm-2 control-label"><s:text name="name"/><small><i class="fa fa-star notNull"></i></small></label>
		                 <div class="col-sm-9">
		                    <input type="text" class="form-control" id="name2" value="${paperVo.name }" readonly>
		                 </div>
		                 <div class="col-sm-1"></div>
	                </div>
					<div class="form-group">
					    <label for="property1" class="col-sm-2 control-label"><s:text name="property"/><small><i class="fa fa-star notNull"></i></small></label>
				        <div class="col-sm-3">
		                    <input type="text" class="form-control" id="property1" value="${paper_property }" readonly>
		                </div>
		                <label for="use_flag1" class="col-sm-3 control-label"><s:text name="use_flag"/><small><i class="fa fa-star notNull"></i></small></label>
				        <div class="col-sm-3">
		                    <input type="text" class="form-control" id="use_flag1" value="${paper_useFlag }" readonly>
		                </div>
		                <div class="col-sm-1"></div>
				    </div>
				    <div class="form-group" >
		                 <label for="leave_limit" class="col-sm-2 control-label"><s:text name="leave_limit"/></label>
		                 <div class="col-sm-3">
		                    <input type="text" class="form-control" name="leave_limit" id="leave_limit" value="${paperVo.leave_limit }" placeholder="0表示不限制">
		                 </div>
		                 <label for="no" class="col-sm-3 control-label"><s:text name="no"/></label>
		                 <div class="col-sm-3">
		                    <input type="text" class="form-control" name="no" id="no" value="${paperVo.no }" placeholder="试卷编号" readonly>
		                 </div>
		                 <div class="col-sm-1"></div>
	                </div>
				    <div class="form-group">
		            	<label class="col-sm-2 control-label"><s:text name="paper_time"/><small><i class="fa fa-star notNull"></i></small></label>       
		            	<div class="col-sm-5">
		            		<!-- 限制时间 -->
					    	<input type="radio" id="limit" name="radio" style="margin-top:1%;line-height:24px;">&nbsp;&nbsp;<s:text name="limit"/>
					    	<input type="text" id="total_time" name="total_time" style="line-height:24px;color:#f00;font-weight:700;display:none" value="-1" readonly>&nbsp;&nbsp;<s:text name="minute"/>
						</div>
						<div class="col-sm-2">
						</div>
						<div class="col-sm-3">
							<label id="total_item"><s:text name="total_item"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-weight:700;font-size:20px;color:#f00;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="number"/></label>
						</div>
		            </div>
		            <div class="form-group">
		            	<label class="col-sm-2 control-label">&nbsp;</label>       
		            	<div class="col-sm-5">
		            		<!-- 不限制时间 -->
					    	<input type="radio" checked id="nolimit" name="radio" style="margin-top:1%;line-height:24px;">&nbsp;&nbsp;<s:text name="noLimit"/>
						</div>
						<div class="col-sm-2">
						</div>
						<div class="col-sm-3">
							<label id="total_point"><s:text name="total_point"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-weight:700;font-size:20px;color:#f00;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="point"/></label>
						</div>
		            </div>
					<hr style="width:96%;position:relative;magin-left:2%;">
					<div style="position:relative;height:540px;">
						<div style="width:8%;margin-left:1%;position:relative;font-size:12px;">
							<ul class="nav nav-pills nav-stacked">
							   <li id="0" ><a style="border-radius:6px;" id="addSection" class="btn btn-block btn-success fa fa-plus">&nbsp;<s:text name="Add"/></a></li>
							</ul>
						</div>
						<div id="divIframe" style="width:90%;height:98%;position:absolute;left:10%;top:-1%;border-left:1px dashed #000;">
							<IFRAME id="paperFrame" name="paperFrame" src="" frameBorder=0 marginwidth=0 marginheight=0 
								style="width:100%;"scrolling=yes ALLOWTRANSPARENCY="true"></IFRAME>
							<input type="hidden" id="li_id"/>
						</div>
					</div>
					
					<div class="box-footer" style="padding: 0 1% 0 0;">
						<div class="col-md-12 col-xs-12">
							<div class="col-md-2 col-xs-2 col-md-offset-8 col-xs-offset-8">
								<button type="button" id="saveCloseButton"
										class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="saveCloseButton"/></button>
							</div>
							<div class="col-md-2 col-xs-2">
								<button type="button" id="finishButton"
										class="btn btn-block btn-primary btn-sm"><i class="fa fa-check"></i>&nbsp;&nbsp;&nbsp;<s:text name="finishButton"/></button>
							</div>
						</div>
					</div>
					<!-- 步骤二 end -->
				</form>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
var w;
$(function(){
    window.onresize = adjust;
    adjust();
})

function adjust(){
	 /* $(".content").css("width",2*document.body.clientWidth);
	 w = -document.body.clientWidth; */
}
</script>
</html>