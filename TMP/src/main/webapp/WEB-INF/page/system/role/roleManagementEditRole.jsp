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
<title>角色编辑</title>
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
		<form id="updateForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="roleName"><s:text name="role.roleName_zh_CN"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-user-secret"></li></span> <input type="text"
									name="role.name" id="roleName" value="<s:property value='role.name'/>"
									class="form-control" placeholder="Role Name in Chinese">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="roleName_en_US"><s:text name="role.roleName_en_US"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-bullhorn"></li></span> <input type="text"
									name="role.name_en_US" id="roleName_en_US" value="<s:property value='role.name_en_US'/>"
									class="form-control" placeholder="Role Name in English">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="roleDesc"><s:text name="role.roleDescription_zh_CN"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-bullhorn"></li></span> <input type="text"
									name="role.description" id="roleDesc" value="<s:property value='role.description'/>"
									class="form-control" placeholder="Role Description in Chinese">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="roleDesc_en_US"><s:text name="role.roleDescription_en_US"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-bullhorn"></li></span> <input type="text"
									name="role.description_en_US" id="roleDesc_en_US" value="<s:property value='role.description_en_US'/>"
									class="form-control" placeholder="Role Description in English">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="roleEnable"><s:text name="role.roleState"/><small><i class="fa fa-star notNull"></i></small></label> 
							<select class="form-control select"
								name="role.enable" id="roleEnable"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>
				<!-- 隐藏表项 -->
				<div class="row" style="display:none;">
					<div class="col-md-6 col-xs-6" style="display:none;">
						<div class="form-group">
							<label>ID</label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-font"></li></span> <input type="text"
									name="role.id" id="id" value="<s:property value='role.id'/>"
									class="form-control" placeholder="id">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6" style="display:none;">
						<div class="form-group">
							<label>Update Time</label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-font"></li></span> <input type="text"
									name="role.update_time" id="updateTime" value="<s:property value='role.update_time'/>"
									class="form-control" placeholder="Update Time">
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
						<button type="button" id="updateButton"
								class="btn btn-block btn-primary btn-sm"><i class="fa fa-refresh"></i>&nbsp;&nbsp;&nbsp;<s:text name="updateButton"/></button>
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
	parent.layer.iframeAuto(index);
	adjust();
	//下拉框初始化
	selectInitial("roleEnable","../system/option!getOptionsByGPVal.do?value=STATUS","${role.enable}",false);
});

$(document)
.on(
		"click",
		"#updateButton",
		function() {
			var itemArr=[
			             {"id":"roleName","type":"1","regular":null,"message":null},
			             {"id":"roleName_en_US","type":"1","regular":null,"message":null},
			             {"id":"roleEnable","type":"1","regular":null,"message":null}
			             ];
			if(validate(itemArr)){
				$.ajax({
					url:"../system/role!updateRole.do",
					data: $("#updateForm").serialize(),
					dataType:"json",
					type:"post",
					success:function(data){
						if(data.success=='true'){
							parent.$("#roleTable").DataTable().draw();
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




