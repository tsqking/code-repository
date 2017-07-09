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
<script
	src="${pageContext.request.contextPath}/question/question/detailQuestion.js"></script>
<title>题目详情</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<div class="box box-primary">
		<form id="addForm">
			<div class="box-body">
				<div class="row">
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
			            	<label for="type"><s:text name="questionType"/><small><i class="fa fa-star notNull"></i></small></label>           
			            	<select class="form-control select"
								name="type" id="type" style="width: 100%;">
								<option value=""></option>
							</select>
			            </div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="property"><s:text name="property"/><small><i class="fa fa-star notNull"></i></small></label>
							<select class="form-control select"
								name="property" id="property" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="so_flag"><s:text name="so_flag"/><small><i class="fa fa-star notNull"></i></small></label>
							<select class="form-control select"
								name="so_flag" id="so_flag" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="use_flag"><s:text name="use_flag"/><small><i class="fa fa-star notNull"></i></small></label>
							<select class="form-control select"
								name="use_flag" id="use_flag" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
			        </div>
				</div>
				<div class="row">
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
							<div><label><s:text name="detailQuestion_17_que_image"/></label></div><!-- detailQuestion_17_que_image:试题图片 -->
							<input style="display:none" name="queImgIds" id="queImgId" value="${questionVo.queImgsStr}"/>
			            	<button type="button" style="margin-top:2px;" onclick="toFindImage('queImgId',1)"
								class="btn btn-primary"><i class="fa fa-cog"></i>&nbsp;&nbsp;&nbsp;<s:text name="detailQuestion_17_find_que_image"/></button><!-- detailQuestion_17_find_que_image:查看试题图片 -->
			            </div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<div><label><s:text name="detailQuestion_17_opt_image"/></label></div><!-- detailQuestion_17_opt_image:选项图片 -->
							<input style="display:none" name="optImgIds" id="optImgId" value="${questionVo.optImgsStr}"/>
							<button id="optImg2" type="button" style="margin-top:2px;" onclick="toFindImage('optImgId',2)"
								class="btn btn-primary "><i class="fa fa-cog"></i>&nbsp;&nbsp;&nbsp;<s:text name="detailQuestion_17_find_opt_image"/></button><!-- detailQuestion_17_find_opt_image:查看选项图片 -->
						</div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
			            	<label for="difficulty"><s:text name="difficulty"/><small><i class="fa fa-star notNull"></i></small></label>           
			            	<select class="form-control select"
								name="difficulty" id="difficulty" style="width: 100%;">
								<option value=""></option>
							</select>
			            </div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
							<label for="enable"><s:text name="enable"/><small><i class="fa fa-star notNull"></i></small></label>
							<select class="form-control select"
								name="enable" id="enable" style="width: 100%;">
								<option value=""></option>
							</select>
						</div>
			        </div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-6">
						<div class="form-group">
							<label for="pointNames"><s:text name="pointName"/><small><i class="fa fa-star notNull"></i></small></label> 
							<input type="text" class="form-control" name="pointNames" id="pointNames" value="${pointNames }" style="width: 100%;" readonly/>
						</div>
			        </div>
			        <div class="col-md-6 col-xs-6">
						<div class="form-group">
			            	<label for="name"><s:text name="tagName"/><small><i class="fa fa-star notNull"></i></small></label>           
			            	<select multiple class="form-control select"
								name="tagNames" id="name" style="width: 100%;">
								<option value=""></option>
							</select>
							<input type="hidden" name="tagIds" id="tagIds" value="${tagIds }"/>
			            </div>
			        </div>
				</div>
				<div class="row">
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
			            	<label for="create_person"><s:text name="createPerson"/></label>           
			            	<input class="form-control input"
								name="create_person" id="create_person" style="width: 100%;" readonly/>
			            </div>
			        </div>
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
			            	<label for="create_time"><s:text name="createTime"/></label>           
			            	<input class="form-control input"
								name="create_time" id="create_time" style="width: 100%;" readonly/>
			            </div>
			        </div>
			        <div class="col-md-3 col-xs-3">
						<div class="form-group">
			            	<label for="update_person"><s:text name="updatePerson"/></label>           
			            	<input class="form-control input"
								name="update_person" id="update_person" style="width: 100%;" readonly/>
			            </div>
			        </div>
					<div class="col-md-3 col-xs-3">
						<div class="form-group">
			            	<label for="update_time"><s:text name="updateTime"/></label>           
			            	<input class="form-control input"
								name="update_time" id="update_time" style="width: 100%;" readonly/>
			            </div>
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
				</div>
				<div class="row">
					<div class="col-md-9 col-xs-9">
						<div class="form-group">
			            	<label for="analysis"><s:text name="questionExplain"/><small><i class="fa fa-star notNull"></i></small></label>           
			                <textarea class="form-control" rows="6" id="analysis" style="width: 100%;" readonly>${questionVo.analysis}</textarea>
			            </div>
			        </div>
				</div>
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
						<div class="col-md-3 col-xs-3 col-md-offset-9 col-xs-offset-9">
							<button type="button" id="closeWindow" onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));"
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/></button>
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
	selectInitial("type","../system/option!getOptionsByGPVal.do?value=QUST_TYPE", "${questionVo.type}", true);
	selectInitial("property","../system/option!getOptionsByGPVal.do?value=QUST_PROP", "${questionVo.property}", true);
	selectInitial("so_flag","../system/option!getOptionsByGPVal.do?value=QUST_SO", "${questionVo.so_flag}", true);
	selectInitial("use_flag","../system/option!getOptionsByGPVal.do?value=USE_FLAG", "${questionVo.use_flag}", true);
	selectInitial("difficulty","../system/option!getOptionsByGPVal.do?value=DIFFICULTY", "${questionVo.difficulty}", true);
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS", "${questionVo.enable}", true);
	var tagIds = $("#tagIds").val();
	tagIds = tagIds.substr(0,tagIds.length);
	var tagArr = tagIds.split(",");
	multiSelectInitial("name","../question/tag!getAllTag.do",tagArr,true);
	//输入框初始化内容
	$("#create_person").val("${questionVo.create_person}");
	$("#create_time").val("${questionVo.create_time}");
	$("#update_person").val("${questionVo.update_person}");
	$("#update_time").val("${questionVo.update_time}"); 
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
  	//根据题目类型生成题目输入界面(1-单选，2-多选，3-判断，4-填空，5-简答，6-编程，7-综合)
    $("#type").change(function(){
    	var selectedVal = $("#type  option:selected").val();
    	if(selectedVal=='5' || selectedVal=='6' || selectedVal=='7' || selectedVal=='8'){
    		$("#qustFrame").prop("src","../question/question!toDetailShortAnswerPage.do");    		
    	}else if(selectedVal=='3'){
    		$("#qustFrame").prop("src","../question/question!toDetailTrueOrFalsePage.do");    		
    	}else if(selectedVal=='1'){
    		$("#qustFrame").prop("src","../question/question!toDetailSingleChoisePage.do"); 
    	}else if(selectedVal=='2'){
    		$("#qustFrame").prop("src","../question/question!toDetailMultipleChoisePage.do"); 
    	}else if(selectedVal=='4'){
    		$("#qustFrame").prop("src","../question/question!toDetailFillingPage.do"); 
    	}
    });
	//反转义
  	//$("#content").val(turnCharactor($("#content").val()));
  	$("#selectItems").val(turnCharactor($("#selectItems").val()));
  	$("#answer").val(turnCharactor($("#answer").val()));
  	//$("#analysis").val(turnCharactor($("#analysis").val()));
});
</script>
</html>
