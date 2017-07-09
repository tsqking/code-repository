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
<title>选项(组)增加</title>
<style type="text/css">
	.content {
    	min-height: 0px;
	}
	html, body {
    	min-height: 0px;
	}
</style>
</head>
<body>
	<!-- Main content -->
	<section class="content" id="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<!-- 搜索条件 -->
	<div class="box box-primary" id="box">
		<form id="addForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="name"><s:text name="option.optionName_zh_CN"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text"
									name="name" id="name"
									class="form-control" placeholder="Option Value in Chinese">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="name_en_US"><s:text name="option.optionName_en_US"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text"
									name="name_en_US" id="name_en_US"
									class="form-control" placeholder="Option Value in English">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="value"><s:text name="option.optionKey"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-font"></li></span> <input type="text"
									name="value" id="value"
									class="form-control" placeholder="Option Key">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="order"><s:text name="option.optionOrder"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-sort"></li></span> <input type="text"
									name="order" id="order"
									class="form-control" placeholder="Option Order(eg.1,2,3...)">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="enable"><s:text name="option.optionState"/><small><i class="fa fa-star notNull"></i></small></label> 
							<select class="form-control select"
								name="enable" id="enable"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="parent_name"><s:text name="option.optionBelong"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-object-group"></li></span> <input type="text" readonly
									name="parent_name" id="parent_name" value="<s:property value='option.parent_name'/>"
									class="form-control" placeholder="NULL">
							</div>
						</div>
					</div>
				</div>
				<!-- 隐藏表项 -->
				<div class="row" style="display:none;">
					<div class="col-md-6 col-xs-6" style="display:none;">
						<div class="form-group">
							<label>Level</label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-font"></li></span> <input type="text"
									name="level" id="level" value="<s:property value='option.level'/>"
									class="form-control" placeholder="id">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label>type</label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-font"></li></span> <input type="text"
									name="type" id="type" value="<s:property value='option.type'/>"
									class="form-control" placeholder="update_time">
							</div>
						</div>
					</div>
				</div>
				<!-- 隐藏表项 -->
				<div class="row" style="display:none;">
					<div class="col-md-6 col-xs-6" style="display:none;">
						<div class="form-group">
							<label>Parent</label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-font"></li></span> <input type="text"
									name="parent_id" id="parent_id" value="<s:property value='option.parent_id'/>"
									class="form-control" placeholder="id">
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.box-body -->
			<div class="box-footer" style="padding: 0 0 0 0">
				<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
					<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
						<button type="button" id="closeButton"
							class="btn btn-block btn-primary btn-sm" onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">
							<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/>
						</button>
					</div>
					<div class="col-md-3 col-xs-3">
						<button type="button" id="addButton"
								class="btn btn-block btn-primary btn-sm"><i class="fa fa-check"></i>&nbsp;&nbsp;&nbsp;<s:text name="addButton"/></button>
					</div>
				</div>
			</div>
		</form>
	</div>
	</section>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name);

function adjust(){
	var h = document.getElementById("box").offsetHeight;
	var bodyH=parent.document.body.scrollHeight;
	console.info(h+"  "+bodyH);
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
	//下拉框初始化
	parent.layer.iframeAuto(index);
	adjust();
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS","T",false);
});

$(document)
.on(
		"click",
		"#addButton",
		function() {//name name_en_US value order enable
			var itemArr=[
			             {"id":"name","type":"1","regular":null,"message":null},
			             {"id":"name_en_US","type":"1","regular":null,"message":null},
			             {"id":"value","type":"1","regular":null,"message":null},
			             {"id":"order","type":"1","regular":null,"message":null},
			             {"id":"enable","type":"1","regular":null,"message":null},
			             {"id":"order","type":"2","regular":null,"message":null}
			             ];
			if(validate(itemArr)){
				$.ajax({
					url:"../system/option!addOption.do",
					data: $("#addForm").serialize(),
					dataType:"json",
					type:"post",
					success:function(data){
						if(data.success=='true'){
							parent.$("#optionTable").DataTable().draw();
							parent.selectInitial("searchOptionGroups","../system/option!getOptionGroups.do","",false);
							parent.layer.msg(data.message);
							parent.layer.close(index);
						}else{
							layer.msg(data.message,{shift: 6});
						}
					}
				});
			}
		}
);
</script>
</body>
</html>




