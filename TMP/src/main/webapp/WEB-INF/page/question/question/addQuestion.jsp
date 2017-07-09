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
<!-- 页面js -->
<script
	src="${pageContext.request.contextPath}/question/question/addQuestion.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/question/question/${system_lang}.js"></script>
<title>新增题目</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<div class="box box-primary">
		<form id="addForm">
			<input type="hidden" id="content" name="content"/><!-- 题干 -->
			<input type="hidden" id="selectItems" name="selectItems"/><!-- 选项答案 -->
			<!--<input type="hidden" id="selectItem" name="selectItem"/> 正确答案号 -->
			<input type="hidden" id="answer" name="answer"/><!-- 答案内容 -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
			            	<label for="type"><s:text name="questionType"/><small><i class="fa fa-star notNull"></i></small></label>           
			            	<select class="form-control select" type="select-one"
								name="type" id="type" style="width: 100%;">
								<option value=""></option>
							</select>
			            </div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="property"><s:text name="property"/><small><i class="fa fa-star notNull"></i></small></label>
							<select class="form-control select" type="select-one"
								name="property" id="property" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="so_flag"><s:text name="so_flag"/><small><i class="fa fa-star notNull"></i></small></label>
							<select class="form-control select" type="select-one"
								name="so_flag" id="so_flag" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="use_flag"><s:text name="use_flag"/><small><i class="fa fa-star notNull"></i></small></label>
							<select class="form-control select" type="select-one"
								name="use_flag" id="use_flag" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
			        </div>
				</div>
				<div class="row">
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
							<div><label><s:text name="addQuestion_44_que_image"/></label></div><!-- addQuestion_44_que_image:试题图片 -->
							<input style="display:none" name="queImgIds" id="queImgId" value=""/>
							<button type="button" style="margin-top:2px;" onclick="toUploadImage('queImgId',1)"
								class="btn btn-primary"><i class="fa fa-cog"></i>&nbsp;&nbsp;&nbsp;<s:text name="addQuestion_44_set_que_image"/></button><!-- addQuestion_44_set_que_image:设置试题图片 -->
			            	<button type="button" style="margin-top:2px;" onclick="toFindImage('queImgId',1)"
								class="btn btn-primary"><i class="fa fa-cog"></i>&nbsp;&nbsp;&nbsp;<s:text name="addQuestion_44_find_que_image"/></button><!-- addQuestion_44_find_que_image:查看已选图片 -->
			            </div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<div><label><s:text name="addQuestion_44_opt_image"/></label></div><!-- addQuestion_44_opt_image:选项图片 -->
							<input style="display:none" name="optImgIds" id="optImgId" value=""/>
							<button id="optImg1" type="button" style="margin-top:2px;" onclick="toUploadImage('optImgId',2)"
								class="btn btn-primary "><i class="fa fa-cog"></i>&nbsp;&nbsp;&nbsp;<s:text name="addQuestion_44_set_opt_image"/></button><!-- addQuestion_44_set_opt_image:设置选项图片 -->
							<button id="optImg2" type="button" style="margin-top:2px;" onclick="toFindImage('optImgId',2)"
								class="btn btn-primary "><i class="fa fa-cog"></i>&nbsp;&nbsp;&nbsp;<s:text name="addQuestion_44_find_opt_image"/></button><!-- addQuestion_44_find_opt_image:查看已选图片 -->
						</div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
			            	<label for="difficulty"><s:text name="difficulty"/><small><i class="fa fa-star notNull"></i></small></label>           
			            	<select class="form-control select" type="select-one"
								name="difficulty" id="difficulty" style="width: 100%;">
								<option value=""></option>
							</select>
			            </div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="enable"><s:text name="enable"/><small><i class="fa fa-star notNull"></i></small></label>
							<select class="form-control select" type="select-one"
								name="enable" id="enable" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
			        </div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="pointName"><s:text name="pointName"/></label> 
							<input type="text" class="form-control" name="pointVo.name" id="pointName" style="width: 100%;"/>
						</div>
						<input type="hidden" name="pointIds" id="pointIds" readonly/>
			        </div>
			        <div class="col-md-2 col-xs-2">
			        	<label style="width:100%;height:100%;">&nbsp;</label>
						<button type="button" style="margin-top:2px;" onclick="setPoint()"
								class="btn btn-primary"><i class="fa fa-cog"></i>&nbsp;&nbsp;&nbsp;<s:text name="setPoint"/></button>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
			            	<label for="name"><s:text name="tagName"/></label>           
			            	<select multiple class="form-control select"
								name="tagNames" id="name" style="width: 100%;">
								<option value=""></option>
							</select>
							<input type="hidden" name="tagIds" id="tagIds"/>
			            </div>
			        </div>
					<div class="col-md-2 col-xs-2">
						<label style="width:100%;height:100%;">&nbsp;</label>
						<button type="button" style="margin-top:2px;" onclick="newTag()"
								class="btn btn-primary"><i class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;<s:text name="addTag"/></button>
					</div>
				</div>
				<hr color=#987cb9 size=3 style="filter:progid:DXImageTransform.Microsoft.Shadow(color:#987cb9,direction:145,strength:15)">
				<div id="box">
				<IFRAME id="qustFrame" name="qustFrame" src="../question/question!toSingleChoisePage.do" frameBorder=0 marginwidth=0 marginheight=0 
				scrolling=no style="width:80%;height:3px;"scrolling=no ALLOWTRANSPARENCY="true"></IFRAME>
				</div>
				<hr style="height:3px;">
				<div class="row">
					<div class="col-md-9 col-xs-9">
						<div class="form-group">
			            	<label for="analysis"><s:text name="questionExplain"/></label>           
			            	<textarea class="form-control" rows="6"
								name="analysis" id="analysis" style="width: 100%;"></textarea>
			            </div>
			        </div>
				</div>
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
						<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
							<button type="button" id="closeWindow" onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));"
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/></button>
						</div>
						<div class="col-md-3 col-xs-3">
							<button type="button" id="addQuestion"
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;<s:text name="addCommitButton"/></button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	</section>
</body>
</html>
