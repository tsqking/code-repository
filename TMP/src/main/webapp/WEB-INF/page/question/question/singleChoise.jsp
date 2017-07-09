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
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/question/question/${system_lang}.js"></script>
<title>单选题</title>
</head>
<body>
	<div id="box">
		<!-- 题目主体内容 -->
		<div class="row">
			<div class="col-md-9 col-xs-9">
				<div class="form-group">
					<label for="content"><s:text name="content"/><small><i class="fa fa-star notNull"></i></small></label>
					<textarea class="form-control select" rows="3" name="content" id="content" style="width: 100%;"></textarea>
				</div>
			</div>
		</div>
		<label><s:text name="choises"/><small><i class="fa fa-star notNull"></i></small></label> 
		
		<!-- 选项一 start 
		<div class="row">
			<div class="col-md-1 col-xs-1" style="text-align:right;padding-right:0px;padding-top:8px;">
				 <input type="radio" name="opt">
	   		</div>
			<div class="col-md-3 col-xs-3">
				<div class="form-group">
				     <input type="text" class="form-control" placeholder="正确一">
		   		</div>
			</div>
	   		<div class="col-md-1 col-xs-1" style="text-align:left;padding-right:0px;padding-top:8px;">
			     <a href="javascript:void(0);" class="trash"><i class="glyphicon glyphicon-trash"></i></a>
	   		</div>
	   </div>
		 选项一 end -->
	   
	   <!-- <div class="row">
			<div class="col-md-1 col-xs-1" style="text-align:right;padding-right:0px;padding-top:8px;">
				 <input type="radio" name="opt">
	   		</div>
			<div class="col-md-3 col-xs-3">
				<div class="form-group">
				     <input type="text" class="form-control" placeholder="正确一">
		   		</div>
			</div>
	   		<div class="col-md-1 col-xs-1" style="text-align:left;padding-right:0px;padding-top:8px;">
			     <a href="javascript:void(0);" class="trash"><i class="glyphicon glyphicon-trash"></i></a>
	   		</div>
	   </div>
	   
	   
	   <div class="row">
			<div class="col-md-1 col-xs-1" style="text-align:right;padding-right:0px;padding-top:8px;">
				 <input type="radio" name="opt">
	   		</div>
			<div class="col-md-3 col-xs-3">
				<div class="form-group">
				     <input type="text" class="form-control" placeholder="正确一">
		   		</div>
			</div>
	   		<div class="col-md-1 col-xs-1" style="text-align:left;padding-right:0px;padding-top:8px;">
			     <a href="javascript:void(0);" class="trash"><i class="glyphicon glyphicon-trash"></i></a>
	   		</div>
	   </div>
	   
	   <div class="row">
			<div class="col-md-1 col-xs-1" style="text-align:right;padding-right:0px;padding-top:8px;">
				 <input type="radio" name="opt">
	   		</div>
			<div class="col-md-3 col-xs-3">
				<div class="form-group">
				     <input type="text" class="form-control" placeholder="正确一">
		   		</div>
			</div>
	   		<div class="col-md-1 col-xs-1" style="text-align:left;padding-right:0px;padding-top:8px;">
			     <a href="javascript:void(0);" class="trash"><i class="glyphicon glyphicon-trash"></i></a>
	   		</div>
	   </div> -->
	   
	   <div class="row" id="addButton">
			<div class="form-group">
				<div class="col-md-3 col-xs-3 col-md-offset-1 col-xs-offset-1">
					<button type="button" id="addItems" class="btn btn-block btn-primary btn-sm">
						<i class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;<s:text name="addItems"/>
					</button>
				</div>
	   		</div>
	   </div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	//移除选项
	$("div").on("click","a",function(){
		$(this).parent().parent("div").remove();
		adjust();
	});
	
	//添加新项
	$("#addItems").click(function(){
		var rows = $("div[class='row']");
		if(rows.length<8){
			$("#addButton").before('<div class="row">'+
										'<div class="col-md-1 col-xs-1" style="text-align:right;padding-right:0px;padding-top:8px;">'+
					 						'<input type="radio" name="opt" value="true">'+
										'</div>'+
										'<div class="col-md-3 col-xs-3">'+
											'<div class="form-group">'+
					     						'<input type="text" class="form-control" placeholder="答案">'+
											'</div>'+
										'</div>'+
										'<div class="col-md-1 col-xs-1" style="text-align:left;padding-right:0px;padding-top:8px;">'+
				 							'<a href=\"javascript:void(0);\" class=\"trash\"><i class=\"glyphicon glyphicon-trash\"></i></a>'+
										'</div>'+
									'</div>');
			adjust();
		}else{
			parent.layer.msg(optionLimitTip);
		}
	});
	
	adjust();
})

//页面重新布局
function adjust(){
	var index = parent.layer.getFrameIndex(window.name);
	var h = document.getElementById("box").offsetHeight;
	parent.document.getElementById("box").style.cssText="width:100%;height:"+h+"px";
	parent.document.getElementById("qustFrame").style.cssText="width:100%;height:"+h+"px";
	parent.layer.iframeAuto(index);
}
</script>
</html>