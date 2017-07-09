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
	src="${pageContext.request.contextPath}/question/question/editQuestion.js"></script>
<!-- js国际化 -->
<script
	src="${pageContext.request.contextPath}/question/question/${system_lang}.js"></script>
<title>编辑题目</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<div class="box box-primary">
		<form id="updateForm">
			<input id="id" name="id" type="hidden" value="${id }"/><!-- 题目ID -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
			            	<label for="type"><s:text name="questionType"/><small><i class="fa fa-star notNull"></i></small></label>           
			            	<select class="form-control select" type="select-one"
								name="show_type" id="show_type" style="width: 100%;">
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
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
			            	<label for="create_person"><s:text name="createPerson"/></label>           
			            	<input class="form-control input" type="text"
								name="create_person" id="create_person" style="width: 100%;" readonly/>
			            </div>
			        </div>
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
			            	<label for="create_time"><s:text name="createTime"/></label>           
			            	<input class="form-control input" type="text"
								name="create_time" id="create_time" style="width: 100%;" readonly/>
			            </div>
			        </div>
				</div>
				<div class="row">
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
							<div><label><s:text name="editQuestion_65_que_image"/></label></div><!-- editQuestion_65_que_image:试题图片 -->
							<input style="display:none" name="queImgIds" id="queImgId" value="${questionVo.queImgsStr}"/>
							<button type="button" style="margin-top:2px;" onclick="toUploadImage('queImgId',1)"
								class="btn btn-primary"><i class="fa fa-cog"></i>&nbsp;&nbsp;&nbsp;<s:text name="editQuestion_65_set_que_image"/></button><!-- editQuestion_65_set_que_image:设置试题图片 -->
			            	<button type="button" style="margin-top:2px;" onclick="toFindImage('queImgId',1)"
								class="btn btn-primary"><i class="fa fa-cog"></i>&nbsp;&nbsp;&nbsp;<s:text name="editQuestion_65_find_que_image"/></button><!-- editQuestion_65_find_que_image:查看已选图片 -->
			            </div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<div><label><s:text name="editQuestion_65_opt_image"/></label></div><!-- editQuestion_65_opt_image:选项图片 -->
							<input style="display:none" name="optImgIds" id="optImgId" value="${questionVo.optImgsStr}"/>
							<button id="optImg1" type="button" style="margin-top:2px;" onclick="toUploadImage('optImgId',2)"
								class="btn btn-primary "><i class="fa fa-cog"></i>&nbsp;&nbsp;&nbsp;<s:text name="editQuestion_65_set_opt_image"/></button><!-- editQuestion_65_set_opt_image:设置选项图片 -->
							<button id="optImg2" type="button" style="margin-top:2px;" onclick="toFindImage('optImgId',2)"
								class="btn btn-primary "><i class="fa fa-cog"></i>&nbsp;&nbsp;&nbsp;<s:text name="editQuestion_65_find_opt_image"/></button><!-- editQuestion_65_find_opt_image:查看已选图片 -->
						</div>
			        </div>
			    </div>
				<div class="row">
					<div class="col-md-4 col-xs-4">
						<div class="form-group">
							<label for="pointName"><s:text name="pointName"/><small><i class="fa fa-star notNull"></i></small></label> 
							<input type="text" class="form-control" name="pointVo.name" id="pointName"  value="${pointNames }" style="width: 100%;" readonly />
						</div>
						<input type="hidden" name="pointIds" id="pointIds" readonly/>
			        </div>
			        <div class="col-md-2 col-xs-2">
			        	<label style="width:100%;height:100%;">&nbsp;</label>
						<button type="button" style="margin-top:1px;" onclick="setPoint()"
								class="btn btn-primary"><i class="fa fa-cog"></i>&nbsp;&nbsp;&nbsp;<s:text name="setPoint"/></button>
					</div>
					<div class="col-md-4 col-xs-4">
						<div class="form-group">
			            	<label for="name"><s:text name="tagName"/><small><i class="fa fa-star notNull"></i></small></label>           
			            	<select multiple class="form-control select" type="select-one"
								name="tagNames" id="name" style="width: 100%;">
								<option value=""></option>
							</select>
							<input type="hidden" name="tagIds" id="tagIds" value="${tagIds }"/>
			            </div>
			        </div>
					<div class="col-md-2 col-xs-2">
						<label style="width:100%;height:100%;">&nbsp;</label>
						<button type="button" style="margin-top:0px;" onclick="newTag()"
								class="btn btn-primary"><i class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;<s:text name="addTag"/></button>
					</div>
				</div>
				<hr style="height:3px;">
				<div id="box">
				<IFRAME id="qustFrame" name="qustFrame" src="" frameBorder=0 marginwidth=0 marginheight=0 
				scrolling=no style="width:80%;height:3px;"scrolling=no ALLOWTRANSPARENCY="true"></IFRAME>
				</div>
				<div style="display:none;">
					<textarea id="content" name="content">${questionVo.content}</textarea><!-- 题干 -->
					<textarea id="selectItems" name="selectItems"></textarea><!-- 选项答案 -->
					<textarea id="answer" name="answer">${questionVo.answer}</textarea><!-- 答案内容 -->
					<input class="form-control input" type="text" name="type" id="type" value="${questionVo.type}"/>
				</div>
				<div class="row">
					<div class="col-md-12 col-xs-12">
						<div class="form-group">
			            	<label for="analysis"><s:text name="questionExplain"/></label>           
			            	<textarea class="form-control" rows="6" name="analysis" id="analysis" style="width: 100%;">${questionVo.analysis}</textarea>
			            </div>
			        </div>
				</div>
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12" style="margin-bottom: 0px;margin-top: 10px;">
						<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
							<button type="button" id="closeWindow" onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));"
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/></button>
						</div>
						<div class="col-md-3 col-xs-3">
							<button type="button" id="updateQuestion"
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-save"></i>&nbsp;&nbsp;&nbsp;<s:text name="saveButton"/></button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	</section>
</body>
<script type="text/javascript">
$(function() {
	//下拉框初始化
	selectInitial("show_type","../system/option!getOptionsByGPVal.do?value=QUST_TYPE", "${questionVo.type}", true);
	selectInitial("property","../system/option!getOptionsByGPVal.do?value=QUST_PROP", "${questionVo.property}", false);
	selectInitial("so_flag","../system/option!getOptionsByGPVal.do?value=QUST_SO", "${questionVo.so_flag}", false);
	selectInitial("use_flag","../system/option!getOptionsByGPVal.do?value=USE_FLAG", "${questionVo.use_flag}", false);
	selectInitial("difficulty","../system/option!getOptionsByGPVal.do?value=DIFFICULTY", "${questionVo.difficulty}", false);
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS", "${questionVo.enable}", false);
	var tagIds = $("#tagIds").val();
	tagIds = tagIds.substr(0,tagIds.length);
	var tagArr = tagIds.split(",");
	multiSelectInitial("name","../question/tag!getAllTag.do",tagArr,false);
	//输入框初始化内容
	$("#create_person").val("${questionVo.create_person}");
	$("#create_time").val("${questionVo.create_time}");
	if("${questionVo.opt6}"!="" && "${questionVo.opt6}"!=null){
		$("#selectItems").text("${questionVo.opt1}%^*^%${questionVo.opt2}%^*^%${questionVo.opt3}%^*^%${questionVo.opt4}%^*^%${questionVo.opt5}%^*^%${questionVo.opt6}");
	}else if("${questionVo.opt5}"!="" && "${questionVo.opt5}"!=null){
		$("#selectItems").text("${questionVo.opt1}%^*^%${questionVo.opt2}%^*^%${questionVo.opt3}%^*^%${questionVo.opt4}%^*^%${questionVo.opt5}");
	}else if("${questionVo.opt4}"!="" && "${questionVo.opt4}"!=null){
		$("#selectItems").text("${questionVo.opt1}%^*^%${questionVo.opt2}%^*^%${questionVo.opt3}%^*^%${questionVo.opt4}");
	}else if("${questionVo.opt3}"!="" && "${questionVo.opt3}"!=null){
		$("#selectItems").text("${questionVo.opt1}%^*^%${questionVo.opt2}%^*^%${questionVo.opt3}");
	}else if("${questionVo.opt2}"!="" && "${questionVo.opt2}"!=null){
		$("#selectItems").text("${questionVo.opt1}%^*^%${questionVo.opt2}");
	}else if("${questionVo.opt1}"!="" && "${questionVo.opt1}"!=null){
		$("#selectItems").text("${questionVo.opt1}");
	}
	//反转义
	$("#selectItems").html($("#selectItems").val());
	$("#content").html($("#content").val());
	$("#analysis").html($("#analysis").val());
	$("#answer").html($("#answer").val());
});
/**
 * 刷新标签
 * @param selectId
 */
function refreshTag(selectId){
	var selected=$("#name").val();
	if(selected==null)
		selected=[];
	selected.push(selectId);
	multiSelectInitial("name","../question/tag!getAllTag.do",selected,false);
}
</script>
</html>
