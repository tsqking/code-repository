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
<script src="${pageContext.request.contextPath}/tech/skill/addSkill.js"></script>
<script src="${pageContext.request.contextPath}/tech/skill/${system_lang}.js"></script>
<title>新增技能</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<input type="hidden" name="hiddenType" id="hiddenType" value="${skillVo.level }">
	<input type="hidden" name="skillTypeId" id="skillTypeId" value="${skillVo.type }">
	<div class="box box-primary">
		<form id="addForm">
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="type"><s:text name="skill.skillType"/><small><i class="fa fa-star notNull"></i></small></label>
							<select class="form-control select"
								name="skillVo.type" id="type" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="parent_name"><s:text name="skill.parentSkill"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-sort"></li></span>
								<input type="text" readonly name="skillVo.parent_name"
									id="parent_name" value="${skillVo.parent_name }"
									class="form-control" placeholder="NULL">
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="levelCopy"><s:text name="skill.skillLevel"/></label><select class="form-control select"
								name="skillVo.levelCopy" id="levelCopy" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="enable"><s:text name="skill.enable"/><small><i class="fa fa-star notNull"></i></small></label>
							<select class="form-control select"
								name="skillVo.enable" id="enable" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="name"><s:text name="skill.skillName_zh_CN"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text"
									name="skillVo.name" id="name" class="form-control"
									placeholder="Skill Name in Chinese">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="name_en_US"><s:text name="skill.skillName_en_US"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-file-text"></li></span> <input type="text"
									name="skillVo.name_en_US" id="name_en_US" class="form-control"
									placeholder="Skill Name in English">
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="description"><s:text name="skill.skillDescription_zh_CN"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-object-group"></li></span> <input type="text"
									name="skillVo.description" id="description" value=""
									class="form-control" placeholder="Description in Chinese">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="description_en_US"><s:text name="skill.skillDescription_en_US"/></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-object-group"></li></span> <input type="text"
									name="skillVo.description_en_US" id="description_en_US" value=""
									class="form-control" placeholder="Description in English">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="order"><s:text name="order"/><small><i class="fa fa-star notNull"></i></small></label>
							<div class="input-group">
								<span class="input-group-addon"><li
									class="fa fa-object-group"></li></span> <input type="text"
									name="skillVo.order" id="order" class="form-control"
									placeholder="Order eg. 1,2,3...">
							</div>
						</div>
					</div>
				</div>
				<!-- 隐藏 -->
				<div class="row" style="display:none;">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label>上级技能序号</label>
							<div class="input-group">
								<span class="input-group-addon"><li class="fa fa-sort"></li></span>
								<input type="text" readonly name="skillVo.parent_id"
									id="parent_id" value="${skillVo.parent_id }"
									class="form-control" placeholder="Parent Skill">
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
								class="btn btn-block btn-primary btn-sm">
								<i class="fa fa-check"></i>&nbsp;&nbsp;&nbsp;<s:text name="addButton"/>
							</button>
						</div>
					</div>
				</div>
		</form>
	</div>
	</section>
</body>
</html>




