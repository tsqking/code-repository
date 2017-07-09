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
<!-- 页面js国际化 -->
<script src="${pageContext.request.contextPath}/tech/teacherpool/${session.system_lang}.js"></script>
<!-- treeview -->
<script src="${pageContext.request.contextPath}/common/plugins/treeview/treeview.min.js"></script>
<style type="text/css">
	.badge {
	    display: inline-block;
	    min-width: 10px;
	    padding: 3px 7px;
	    font-size: 12px;
	    font-weight: 700;
	    line-height: 1;
	    color: #fff;
	    text-align: center;
	    white-space: nowrap;
	    vertical-align: middle;
	    background-color: #F1F1F1;
	    border-radius: 10px;
	}
	.content {
    	min-height: 0px;
	}
	html, body {
    	min-height: 0px;
	}
</style>
<title>教师-一览</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" id="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<div class="box" id="box">
			<form id="addSkillPointForm">
				<div class="box-header">
					<s:text name="teacher.reviewTeacherTitle">
						<s:param>${tepVo.name}</s:param>
					</s:text>
				</div>
				<div class="box-body">
					<div id="treeview" class="treeview">
					</div>
				</div>
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12" style="margin-top:2px;margin-bottom:2px;">
						<div class="col-md-2 col-xs-2 col-md-offset-5 col-xs-offset-5">
							<select class="form-control select" id="select-expand-all-levels">
				            	<option value="1" >1</option>
				            	<option value="2">2</option>
				            	<option value="3">3</option>
				        	</select>
						</div>
						<div class="col-md-1 col-xs-1" style="margin-top:7px;">
							<button type="button" class="btn btn-block btn-default btn-sm" id="btn-expand-all"><li class="fa fa-expand"></li></button>
						</div>
						<div class="col-md-1 col-xs-1" style="margin-top:7px;">
							<button type="button" class="btn btn-block btn-default btn-sm" id="btn-collapse-all"><li class="fa fa-compress"></li></button>
						</div>
						<div class="col-md-3 col-xs-3" style="margin-top:3px;">
							<button type="button" id="closeButton" 
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/></button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</section>
	
</body>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name);

function adjust(){
	var h = document.getElementById("box").offsetHeight;
	var bodyH=parent.document.body.scrollHeight;
	if(h>bodyH*0.7){
		content = document.getElementById("content");
		content.style.height = bodyH*0.7+ "px";
		document.body.clientHeight= bodyH*0.7 + "px";
	}else{
		content = document.getElementById("content");
		content.style.height = (h + 30) + "px";
		document.body.clientHeight=(h + 30) + "px";
	}
		parent.layer.iframeAuto(index);
}

$(function() {
	//parent.layer.iframeAuto(index);
	$.ajax({
		url:"../tech/teacherpool!reviewTeacher.do",
		data: {"tepVo.nbr": '${tepVo.nbr}'},
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.success=='true'){
				if(data.datas.teacherList=="[]"){
					$('#treeview').html("<blockquote>"+ "<p>"+noPointDataForTeacher+"</p>"+// 此课程没有知识点数据 - 
							"<small>"+retryTip+"</cite></small>"+// 请重试! - 
						"</blockquote>");
				}else{
					$('#treeview').treeview({
						levels:2,
						color: "#3C8DBC",
						expandIcon: 'glyphicon glyphicon-chevron-right',
						collapseIcon: 'glyphicon glyphicon-chevron-down',
						nodeIcon: 'glyphicon glyphicon-bookmark',
						showTags: true,
						data: data.datas.teacherList
					});
				}
				adjust();
			}else{
				layer.msg(data.message);
			}
		}
	});
	$("#select-expand-all-levels").select2({
		placeholder: "Select a state"
	});
    // 展开/ 收缩
    $('#btn-expand-all').on('click', function (e) {
      var levels = $('#select-expand-all-levels').val();
      $('#treeview').treeview('expandAll', { levels: levels });
      adjust();
    });

    $('#btn-collapse-all').on('click', function (e) {
      $('#treeview').treeview('collapseAll');
      adjust();
    });
});
/**
 * 关闭 课程添加技能知识点 
 */
$(document).on("click","#closeButton",
	function() {
		parent.layer.close(index);
	}
);

</script>
</html>




