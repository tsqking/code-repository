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
<script src="${pageContext.request.contextPath}/tech/course/${session.system_lang}.js"></script>
<title>课程信息编辑</title>
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
	<section class="content" id="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<div class="box box-primary" id="box">
			<form id="editForm">
				<div class="box-body">
					<!-- 隐藏 -->
					<div class="row" style="display:none;">
						<div class="col-md-4 col-xs-4">
							<div class="form-group">
				            	<label>ID</label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-hand-o-right"></li></span>
				                	<input type="text" name="courseVo.id" id="id" class="form-control" value="${courseVo.id}"> 
				              	</div>
				            </div>
				        </div>
				        <div class="col-md-4 col-xs-4">
							<div class="form-group">
				            	<label>ID</label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-hand-o-right"></li></span>
				                	<input type="text" name="courseVo.last_update_time" id="last_update_time" class="form-control" value="${courseVo.last_update_time}"> 
				              	</div>
				            </div>
				        </div>
				        <div class="col-md-4 col-xs-4">
							<div class="form-group">
				            	<label>ID</label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-hand-o-right"></li></span>
				                	<input type="text" name="courseVo.last_update_person" id="last_update_person" class="form-control" value="${courseVo.last_update_person}"> 
				              	</div>
				            </div>
				        </div>
					</div>
					<div class="row">
						<div class="col-md-4 col-xs-4">
							<div class="form-group">
				            	<label for="no"><s:text name="course.no"/></label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-hand-o-right"></li></span>
				                	<input type="text" name="courseVo.no" id="no" class="form-control" placeholder="Generate by System" value="${courseVo.no}" readonly> 
				              	</div>
				            </div>
				        </div>
						<div class="col-md-4 col-xs-4">
							<div class="form-group">
				            	<label for="name"><s:text name="course.name_zh_CN"/><small><i class="fa fa-star notNull"></i></small></label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-fw fa-tag"></li></span>
				                	<input type="text" name="courseVo.name" id="name" class="form-control" placeholder="Course Name in chinese" value="${courseVo.name}">
				              	</div>
				            </div>
				        </div>
						<div class="col-md-4 col-xs-4">
							<div class="form-group">
				            	<label for="name_en_US"><s:text name="course.name_en_US"/><small><i class="fa fa-star notNull"></i></small></label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-fw fa-tag"></li></span>
				                	<input type="text" name="courseVo.name_en_US" id="name_en_US" class="form-control" placeholder="Course Name in English" value="${courseVo.name_en_US}">
				              	</div>
				            </div>
				        </div>
					</div>
					<div class="row">
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label for="category"><s:text name="course.category"/></label> <select class="form-control select"
									name="courseVo.category" id="category" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
				            	<label><s:text name="course.sub_category"/></label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-star"></li></span>
				              		<textarea class="form-control" name="courseVo.sub_category" id="sub_category" rows="1" 
			                				placeholder="First Skill" readonly>${courseVo.sub_category }</textarea>
				              	</div>
				            </div>
						</div>
						<div class="col-md-6 col-xs-6">
							<div class="form-group">
				            	<label><s:text name="course.sub_sub_category"/></label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-star-half"></li></span>
				              		<textarea class="form-control" name="courseVo.sub_sub_category" id="sub_sub_category" rows="1" 
			                				placeholder="Second Skill" readonly>${courseVo.sub_sub_category }</textarea>
				              	</div>
				            </div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 col-xs-6">
	              			<div class="form-group">
			                	<label for="description_zh_CN"><s:text name="course.description_zh_CN"/></label>
			                	<textarea class="form-control" name="courseVo.description" id="description_zh_CN" rows="2" 
			                				placeholder="Course Description in Chinese ...">${courseVo.description}</textarea>
			                </div>
			        	</div>
			        	<div class="col-md-6 col-xs-6">
	              			<div class="form-group">
			                	<label for="description_en_US"><s:text name="course.description_en_US"/></label>
			                	<textarea class="form-control" name="courseVo.description_en_US" id="description_en_US" rows="2" 
			                				placeholder="Course Description in English ...">${courseVo.description_en_US}</textarea>
			                </div>
			        	</div>
					</div>
					<div class="row">
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label for="level"><s:text name="course.level"/></label> <select class="form-control select"
									name="courseVo.level" id="level" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
				            	<label for="cost_number"><s:text name="course.cost_number"/></label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-clock-o"></li></span>
				                	<input type="text" name="courseVo.cost_number" id="cost_number" style="width: 50%;" class="form-control" value="${courseVo.cost_number }"/>
				              		<select class="form-control select" name="courseVo.cost_unit" id="cost_unit" style="width: 50%;">
										<option value=""></option>
									</select>
				              	</div>
				            </div>
						</div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label for="trainer_avl"><s:text name="course.trainer_avl"/></label> <select class="form-control select"
									name="courseVo.trainer_avl" id="trainer_avl" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label for="presentation_avl"><s:text name="course.presentation_avl"/></label> <select class="form-control select"
									name="courseVo.presentation_avl" id="presentation_avl" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label for="stu_manual_avl"><s:text name="course.stu_manual_avl"/></label> <select class="form-control select"
									name="courseVo.stu_manual_avl" id="stu_manual_avl" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label for="act_book_avl"><s:text name="course.act_book_avl"/></label> <select class="form-control select"
									name="courseVo.act_book_avl" id="act_book_avl" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label for="quiz_avl"><s:text name="course.quiz_avl"/></label> <select class="form-control select"
									name="courseVo.quiz_avl" id="quiz_avl" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label for="exec_pf_avl"><s:text name="course.exec_pf_avl"/></label> <select class="form-control select"
									name="courseVo.exec_pf_avl" id="exec_pf_avl" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
					
					</div>
					<div class="row">
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label for="direction"><s:text name="course.direction"/></label> <select class="form-control select"
									name="courseVo.direction" id="direction" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label for="property"><s:text name="course.property"/><small><i class="fa fa-star notNull"></i></small></label> 
								<select class="form-control select"
									name="courseVo.property" id="property" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group" data-toggle="tooltip" title="<s:text name="course.lockTip"/>">
								<label for="lock"><s:text name="course.lock"/><small><i class="fa fa-star notNull"></i></small></label> 
								<select class="form-control select"
									name="courseVo.lock" id="lock" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label for="enable"><s:text name="enable"/><small><i class="fa fa-star notNull"></i></small></label> 
								<select class="form-control select"
									name="courseVo.enable" id="enable" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4 col-xs-4">
							<div class="form-group">
								<label for="target_client"><s:text name="course.target_client"/></label> 
								<select multiple class="form-control select"
									name="courseVo.target_client" id="target_client" style="width: 100%;">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="col-md-4 col-xs-4">
	              			<div class="form-group">
			                	<label for="function_zh_CN"><s:text name="course.function_zh_CN"/></label>
			                	<textarea class="form-control" name="courseVo.function" id="function_zh_CN" rows="2" 
			                				placeholder="Course Function in Chinese ...">${courseVo.function}</textarea>
			                </div>
			        	</div>
			        	<div class="col-md-4 col-xs-4">
	              			<div class="form-group">
			                	<label for="function_en_US"><s:text name="course.function_en_US"/></label>
			                	<textarea class="form-control" name="courseVo.function_en_US" id="function_en_US" rows="2" 
			                				placeholder="Course Function in English ...">${courseVo.function_en_US}</textarea>
			                </div>
			        	</div>
					</div>
					<div class="row">
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
				            	<label for="create_time"><s:text name="createTime"/></label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-clock-o"></li></span>
				                	<input type="text" name="courseVo.create_time" id="create_time" class="form-control" placeholder="" value="${courseVo.create_time}" readonly>
				              	</div>
				            </div>
				        </div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
				            	<label for="create_person"><s:text name="createPerson"/></label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-user"></li></span>
				                	<input type="text" name="courseVo.create_person" id="create_person" class="form-control" placeholder="" value="${courseVo.create_person}" readonly>
				              	</div>
				            </div>
				        </div>
				        <div class="col-md-3 col-xs-3">
							<div class="form-group">
				            	<label for="update_time"><s:text name="updateTime"/></label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-clock-o"></li></span>
				                	<input type="text" name="courseVo.update_time" id="update_time" class="form-control" placeholder="" value="${courseVo.update_time}" readonly>
				              	</div>
				            </div>
				        </div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
				            	<label for="update_person"><s:text name="updatePerson"/></label>           
				            	<div class="input-group">
				                	<span class="input-group-addon"><li class="fa fa-user"></li></span>
				                	<input type="text" name="courseVo.update_person" id="update_person" class="form-control" placeholder="" value="${courseVo.update_person}" readonly>
				              	</div>
				            </div>
				        </div>
					</div>
				</div>
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
						<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
							<button type="button" id="closeButton"
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/></button>
						</div>
						<div class="col-md-3 col-xs-3">
							<button type="button" id="submitButton"
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-check"></i>&nbsp;&nbsp;&nbsp;<s:text name="submitButton"/></button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</section>
	<input type="hidden"  id="function_en_US_hidden" value="${courseVo.function_en_US}">
</body>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name);

function adjust(){
	var h = document.getElementById("box").offsetHeight;
	var bodyH=parent.document.body.scrollHeight;
	console.info(h+"  "+bodyH);
	if(h>bodyH*0.8){
		content = document.getElementById("content");
		content.style.height = bodyH*0.8+ "px";
		document.body.clientHeight= bodyH*0.8 + "px";
	}else{
		content = document.getElementById("content");
		content.style.height = (h + 10) + "px";
		document.body.clientHeight=(h + 10) + "px";
	}
	parent.layer.iframeAuto(index);
}

$(function() {
	parent.layer.iframeAuto(index);
	//下拉框初始化
	selectInitial("enable", "../system/option!getOptionsByGPVal.do?value=STATUS","${courseVo.enable}",false);
	selectInitial("lock", "../system/option!getOptionsByGPVal.do?value=WHETHER","${courseVo.lock}",false);
	selectInitial("property", "../system/option!getOptionsByGPVal.do?value=CSE_PROPERTY","${courseVo.property}",false);
	selectInitial("direction", "../system/option!getOptionsByGPVal.do?value=DIRECTION","${courseVo.direction}",false);
	//textarea初始化内容
	/* $("#description_zh_CN").val("${courseVo.description}");
	$("#description_en_US").val("${courseVo.description_en_US}");
	$("#function_zh_CN").val("${courseVo.function}");
	$("#function_en_US").val("<pre>"+$('#function_en_US_hidden').val()+"</pre>"); */
	selectInitial("category", "../system/option!getOptionsByGPVal.do?value=CSE_TYPE","${courseVo.category}",false);
	selectInitial("level", "../system/option!getOptionsByGPVal.do?value=CSE_DIFF","${courseVo.level}",false);
	selectInitial("cost_unit", "../system/option!getOptionsByGPVal.do?value=COST_UNIT","${courseVo.cost_unit}",false);
	selectInitial("trainer_avl", "../system/option!getOptionsByGPVal.do?value=TRAINER_AVL","${courseVo.trainer_avl}",false);
	selectInitial("presentation_avl,stu_manual_avl,act_book_avl,quiz_avl,exec_pf_avl", "../system/option!getOptionsByGPVal.do?value=AVL",
			"${courseVo.presentation_avl},${courseVo.stu_manual_avl},${courseVo.act_book_avl},${courseVo.quiz_avl},${courseVo.exec_pf_avl}",false);
	var client="${courseVo.target_client}";
	var clients=client.split(",");
	for(var ii=0;ii<clients.length;ii++){
		clients[ii]=clients[ii].replace(/(^\s*)|(\s*$)/g, "");
	}
	multiSelectInitial("target_client", "../system/option!getOptionsByGPVal.do?value=CLIENTS",clients,false);
	adjust();
});

/**
 * 关闭按钮
 */
$(document).on("click","#closeButton",
	function() {
		parent.layer.close(index);
	}
);
/**
 * 去修改按钮
 */
$(document).on("click","#submitButton",
	function() {
		var itemArr=[
		             {"id":"name","type":"1","regular":null,"message":null},
		             {"id":"name_en_US","type":"1","regular":null,"message":null},
		             {"id":"property","type":"1","regular":null,"message":null},
		             {"id":"lock","type":"1","regular":null,"message":null},
		             {"id":"enable","type":"1","regular":null,"message":null},
		             {"id":"cost_number","type":"2","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
			$.ajax({
				url:"../tech/course!editCourse.do",
				data: $("#editForm").serialize(),
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.success=='true'){
						if(data.message=="0"){
							parent.parent.layer.alert(have_been_updated);
						}else{
							parent.$("#courseTable").DataTable().draw();
							parent.parent.layer.msg(success_edit_course);
							parent.layer.close(index);
						}
					}else{
						layer.msg(data.message,{shift: 6});
					}
				}
			});
		}
	}
);

</script>
</html>




