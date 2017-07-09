<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<s:include value="../../../common.jsp"></s:include>
<!-- 页面js -->
<script
	src="${pageContext.request.contextPath}/campusRecruit/univ/coop/editCoopHis.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/campusRecruit/univ/coop/${system_lang}.js"></script>
<style type="text/css">
.content {
	min-height: 0px;
}

html, body {
	min-height: 0px;
}
</style>
<title></title>
</head>
<body>
	<section class="content" id="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<div class="box box-primary" id="box">
		<form id="editForm">
		<input name='coopHisVo.id' value="${coopHisVo.id}" style="display: none" />
		<input id="coopId" name='coopHisVo.coop_id' value="${coopHisVo.coop_id}" style="display: none" />
		<input id="contactsInfo" value="${coopHisVo.contacts}" style="display: none" />
			<div class="box-body">
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="time"><s:text name="coop.his.status_time"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-file-text"></li></span>
								 <input type="text" class="form-control"
								 value="${coopHisVo.time}" id="time" name="coopHisVo.time">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for='coopStatus'><s:text name="coop.his.changestatus"/></label>
								<select class="form-control select"
								temValue="${coopHisVo.status}"
								name="coopHisVo.status"
								id='coopStatus'
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					 </div>	
				</div>
				<div class="row">
				     <div class="col-md-12 col-xs-12">
						<div class="form-group">
							<label for="contacts"><s:text name="coop.his.attn.select"/></label>
								<select class="form-control select" class="contacts" multiple
								name="coopHisVo.contacts" id="contacts"
								style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					 </div>
				</div>
				<div class="row">
					<div class="col-md-12 col-xs-12">	
						<div class="form-group">
							<label for="remark" ><s:text name="coop.his.statusremark"/></label>
							<div class="input-group">
								<textarea style="font-size:14px;width: 860px;" 
								maxlength="100" cols="80" rows="5"  
								id="remark" 
								name="coopHisVo.remark"
								class="form-control" placeholder="Remark">${coopHisVo.remark}</textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="time"><s:text name="coop.his.createtime"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-file-text"></li></span>
								 <input type="text" class="form-control"
								 value="${coopHisVo.create_time}" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="time"><s:text name="coop.his.createuser"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-file-text"></li></span>
								 <input type="text" class="form-control"
								 value="${coopHisVo.create_user}" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="time"><s:text name="coop.his.updatetime"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-file-text"></li></span>
								 <input type="text" class="form-control"
								 value="${coopHisVo.update_time}" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="time"><s:text name="coop.his.updateuser"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-file-text"></li></span>
								 <input type="text" class="form-control"
								 value="${coopHisVo.update_user}" readonly="readonly">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="box-footer" style="padding: 0 0 0 0">
				<div class="col-md-12 col-xs-12" style="margin-bottom: 10px;">
					<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
						<button type="button" id="closeButton"
								class="btn btn-block btn-primary btn-sm"
								onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));refreshTable();">
								<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;
								<s:text name="closeButton" />
							</button>
						</div>
						<div class="col-md-3 col-xs-3">
							<button type="button" id="updateButton" onclick="editCoopHis()"
								class="btn btn-block btn-primary btn-sm">
								<i class="fa fa-check"></i>&nbsp;&nbsp;&nbsp;
								<s:text name="saveButton" />
							</button>
						</div>
				</div>
			</div>
		</form>
	</div>
	</section>
</body>
</html>