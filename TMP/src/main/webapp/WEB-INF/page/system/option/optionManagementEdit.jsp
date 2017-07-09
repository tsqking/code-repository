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
<title>选项框编辑</title>
</head>
<body>
	<!-- Main content -->
	<section class="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<!-- 搜索条件 -->
	<div class="box box-primary">
		<form id="updateForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="name"><s:text name="option.optionName_zh_CN"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text"
									name="name" id="name" value="<s:property value='option.name'/>"
									class="form-control" placeholder="name">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="name_en_US"><s:text name="option.optionName_en_US"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text"
									name="name_en_US" id="name_en_US" value="<s:property value='option.name_en_US'/>"
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
									name="value" id="value" value="<s:property value='option.value'/>"
									class="form-control" placeholder="value">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="type"><s:text name="option.optionType"/></label> <select class="form-control select"
								name="type" id="type"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label  for="order"><s:text name="option.optionOrder"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-sort"></li></span> <input type="text"
									name="order" id="order" value="<s:property value='option.order'/>"
									class="form-control" placeholder="order">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="parent_name"><s:text name="option.optionBelong"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-object-group"></li></span> <input type="text"
									name="parent_name" id="parent_name" value="<s:property value='option.parent_name'/>"
									class="form-control" placeholder="<s:text name="option.optionGroupAlready"/>" readOnly="true">
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
				</div>
				<!-- 隐藏表项 -->
				<div class="row" style="display:none;">
					<div class="col-md-6 col-xs-6" style="display:none;">
						<div class="form-group">
							<label>ID</label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-font"></li></span> <input type="text"
									name="id" id="id" value="<s:property value='option.id'/>"
									class="form-control" placeholder="id">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label>更新时间</label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-fw fa-font"></li></span> <input type="text"
									name="update_time" id="update_time" value="<s:property value='option.update_time'/>"
									class="form-control" placeholder="update_time">
							</div>
						</div>
					</div>
				</div>
				<!-- 隐藏表项 -->
				<div class="row" style="display:none;">
					<div class="col-md-6 col-xs-6" style="display:none;">
						<div class="form-group">
							<label>所属选项组ID</label>
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
$(function() {
	parent.layer.iframeAuto(index);
	//下拉框初始化
	selectInitial("type","../system/option!getOptionsByGPVal.do?value=OPTION_TYPE","${option.type}",true);
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS","${option.enable}",false);
});

$(document)
.on(
		"click",
		"#updateButton",
		function() {
			var itemArr=[
			             {"id":"type","type":"1","regular":null,"message":null},
			             {"id":"enable","type":"1","regular":null,"message":null},
			             {"id":"name","type":"1","regular":null,"message":null},
			             {"id":"name_en_US","type":"1","regular":null,"message":null},
			             {"id":"order","type":"1","regular":null,"message":null},
			             {"id":"order","type":"2","regular":null,"message":null}
			             ];
			if(validate(itemArr)){
				$.ajax({
					url:"../system/option!updateOption.do",
					data: $("#updateForm").serialize(),
					dataType:"json",
					type:"post",
					success:function(data){
						if(data.success=='true'){
							parent.$("#optionTable").DataTable().draw();
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




