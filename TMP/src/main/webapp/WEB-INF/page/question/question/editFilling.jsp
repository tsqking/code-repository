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
<title>填空题</title>
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
		<label><s:text name="questionAnswer"/><small><i class="fa fa-star notNull"></i></small></label> 
		<!-- 选项一 start -->
		<div class="row">
			<div class="col-md-2 col-xs-2" style="text-align:right;padding-right:0px;padding-top:8px;">
				 <label for="questionNumber"><s:text name="message"/></label>
	   		</div>
			<div class="col-md-1 col-xs-1">
				<div class="form-group">
					<select class="form-control select"
						name="questionNumber" id="questionNumber" style="width: 100%;font-size:16px;">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
					 </select>
				</div>
			</div>
			<div class="col-md-2 col-xs-2" style="text-align:left;padding-right:0px;padding-top:8px;">
				 <label><s:text name="fillNumber"/></label>
	   		</div>
	   </div>
	   
	   <div id="selectItems">
	   			<!-- 选项位置 -->
	   </div>
	   
	</div>
</body>
<script type="text/javascript">
$(function(){
	$("#content").val(parent.document.getElementById("content").value);
	var arr = [blank1,blank2,blank3,blank4,blank5,blank6,blank7,blank8];
	var selectItems = parent.document.getElementById("answer").value;
	selectItems=turnCharactor(selectItems);
	var selectItems = selectItems.split("#$#");
	$("#questionNumber").find("option[value='"+selectItems.length+"']").attr("selected",true);
	for(var i=0; i<selectItems.length; i++){
		$("#selectItems").append('<div class="row" style="margin-left:10%; margin-bottom:10px;">'+
									'<div class="col-md-3 col-xs-3">'+
										'<div class="input-group">'+
								            '<span class="input-group-addon">'+arr[i]+'</span>'+
								            '<input type="text" class="form-control" value=\"'+selectItems[i]+'\" >'+
								      	'</div>'+
									'</div>'+
								'</div>');
	}
	$("#questionNumber").change(function(){
		$("input[type='text']").each(function(){
			console.info($(this).val());
			if($(this).val()==""){
				$(this).parent().parent().parent().remove();
			}
		});
		var number = $("select option:selected").val();
		selectItems = document.getElementById('selectItems').children.length;
		//选项大于原来的选项个数
		if(number-selectItems > 0){
			for(var i=0; i<number-selectItems; i++){
				$("#selectItems").append('<div class="row" style="margin-left:10%; margin-bottom:10px;">'+
											'<div class="col-md-3 col-xs-3">'+
												'<div class="input-group">'+
										            '<span class="input-group-addon">'+arr[selectItems+i]+'</span>'+
										            '<input type="text" class="form-control" placeholder=\"'+arr[selectItems+i]+'\" >'+
										      	'</div>'+
											'</div>'+
										'</div>');
			}
		}else{
			if(number-selectItems < 0){
				for(var i=1; i<=selectItems-number; i++){
					$("#selectItems").children(".row").eq(selectItems-i).remove();
				}
			}
		}
		adjust();
	});
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