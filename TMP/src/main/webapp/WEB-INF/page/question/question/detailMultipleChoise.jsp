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
<title>多选题</title>
</head>
<body>
	<div id="box">
		<!-- 题目主体内容 -->
		<div class="row">
			<div class="col-md-9 col-xs-9">
				<div class="form-group">
					<label for="content"><s:text name="content"/><small><i class="fa fa-star notNull"></i></small></label>
					<textarea class="form-control select" rows="3"name="content" id="content" style="width: 100%;" readonly></textarea>
				</div>
			</div>
		</div>
		<label><s:text name="choises"/><small><i class="fa fa-star notNull"></i></small></label> 
		
		<!-- 选项一 start 
		<div class="row">
			<div class="col-md-1 col-xs-1" style="text-align:right;padding-right:0px;padding-top:8px;">
				 <input type="checkbox" name="checkbox1" id="options1" disabled>
	   		</div>
			<div class="col-md-3 col-xs-3">
				<div class="form-group">
				     <input type="text" class="form-control" readonly>
		   		</div>
			</div>
	   </div>
		 选项一 end -->
	   
	   <!-- <div class="row">
			<div class="col-md-1 col-xs-1" style="text-align:right;padding-right:0px;padding-top:8px;">
				 <input type="checkbox" name="checkbox2" id="options2" disabled>
	   		</div>
			<div class="col-md-3 col-xs-3">
				<div class="form-group">
				     <input type="text" class="form-control" readonly>
		   		</div>
			</div>
	   </div>
	   
	   
	   <div class="row">
			<div class="col-md-1 col-xs-1" style="text-align:right;padding-right:0px;padding-top:8px;">
				 <input type="checkbox" name="checkbox3" id="options3" disabled>
	   		</div>
			<div class="col-md-3 col-xs-3">
				<div class="form-group">
				     <input type="text" class="form-control" readonly>
		   		</div>
			</div>
	   </div>
	   
	   <div class="row">
			<div class="col-md-1 col-xs-1" style="text-align:right;padding-right:0px;padding-top:8px;">
				 <input type="checkbox" name="checkbox4" id="options4" disabled>
	   		</div>
			<div class="col-md-3 col-xs-3">
				<div class="form-group">
				     <input type="text" class="form-control" readonly>
		   		</div>
			</div>
	   </div> -->
	   
	</div>
</body>
<script type="text/javascript">
$(function(){
	$("#content").val(parent.document.getElementById("content").value);
	var answer = parent.document.getElementById("answer").value;
	var selectItems = parent.document.getElementById("selectItems").value;
	selectItems = selectItems.split("%^*^%");
	answer = answer.split("#$#");
	for(var i=0; i<selectItems.length; i++){
		$("div#box").append('<div class="row">'+
				'<div class="col-md-1 col-xs-1" style="text-align:right;padding-right:0px;padding-top:8px;">'+
						'<input type="checkbox" value="true" disabled>'+
				'</div>'+
				'<div class="col-md-3 col-xs-3">'+
					'<div class="form-group">'+
 						'<input type="text" class="form-control" placeholder="答案" value=\"'+returnCharactor(selectItems[i])+'\" readonly>'+
					'</div>'+
				'</div>'+
			'</div>');
	}
	for(var i=0; i<answer.length-1; i++){
		$("div#box").find("input:checkbox").eq(answer[i]-1).attr("checked",true);
	}
	adjust();
})

function adjust(){
	var index = parent.layer.getFrameIndex(window.name);
	var h = document.getElementById("box").offsetHeight;
	parent.document.getElementById("box").style.cssText="width:100%;height:"+h+"px";
	parent.document.getElementById("qustFrame").style.cssText="width:100%;height:"+h+"px";
	parent.layer.iframeAuto(index);
}
</script>
</html>