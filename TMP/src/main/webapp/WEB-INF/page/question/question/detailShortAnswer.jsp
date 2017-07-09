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
<title>简答题/编程题</title>
</head>
<body>
	<div id="box">
		<div class="row">
			<div class="col-md-9 col-xs-9">
				<div class="form-group">
	            	<label for="content"><s:text name="content"/><small><i class="fa fa-star notNull"></i></small></label>           
	            	<textarea class="form-control select" rows="3"
						name="content" id="content" style="width: 100%;" readonly></textarea>
	            </div>
	        </div>
	    </div>
	    <div class="row">
	        <div class="col-md-9 col-xs-9">
	            <div class="form-group">
	            	<label for="answer"><s:text name="answer"/><small><i class="fa fa-star notNull"></i></small></label>           
	            	<textarea class="form-control select" rows="6"
						name="answer" id="answer" style="width: 100%;" readonly></textarea>
	            </div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$("#content").val(parent.document.getElementById("content").value);
	$("#answer").val(returnCharactor(parent.document.getElementById("answer").value));
	var index = parent.layer.getFrameIndex(window.name);
	var h = document.getElementById("box").offsetHeight;
	parent.document.getElementById("box").style.cssText="width:100%;height:"+h+"px";
	parent.document.getElementById("qustFrame").style.cssText="width:100%;height:"+h+"px";
	parent.layer.iframeAuto(index);
})
</script>
</html>