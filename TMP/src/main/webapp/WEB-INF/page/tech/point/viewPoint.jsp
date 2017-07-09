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
<script src="${pageContext.request.contextPath}/tech/point/${session.system_lang}.js"></script>
<style type="text/css">
	.content {
    	min-height: 0px;
	}
	html, body {
    	min-height: 0px;
	}
</style>
<title>查看知识点</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" id="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<div class=row>
			<div class="box" id="box">
				<div class="box-body">
					<form id="addForm" method="post">
						<div class="col-md-12 col-xs-12">
							<div class="box-body">
					        	<div class="nav-tabs-custom">
					            	<ul class="nav nav-tabs">
					              		<li class="active" id="tab1"><a href="#pointInfo" data-toggle="tab"><s:text name="detail.simpleInfo"/></a></li>
					              		<li id="tab2"><a href="#pointDetail" data-toggle="tab"><s:text name="detail.resourceDetail"/></a></li>
					            	</ul>
					            	<div class="tab-content">
					              		<div class="active tab-pane" id="pointInfo">
			              					<div class="row">
												<div class="col-md-6 col-xs-6">
													<div class="form-group">
														<label><s:text name="point.firstSkill"/></label> <select class="form-control select"
															name="pointVo.first_skill" id="first_skill"
															style="width: 100%;">
															<option value=""></option>
														</select>
													</div>
												</div>
												<div class="col-md-6 col-xs-6">
													<div class="form-group">
														<label><s:text name="point.secondSkill"/></label> <select class="form-control select"
															name="pointVo.second_skill" id="second_skill"
															style="width: 100%;">
															<option value=""></option>
														</select>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6 col-xs-6">
													<div class="form-group">
														<label><s:text name="point.thirdSkill"/></label> <select class="form-control select"
															name="pointVo.third_skill" id="third_skill"
															style="width: 100%;">
															<option value=""></option>
														</select>
													</div>
												</div>
												<div class="col-md-6 col-xs-6">
													<div class="form-group">
														<label><s:text name="enable"/></label> <select class="form-control select"
															name="pointVo.enable" id="enable" style="width: 100%;">
															<option value=""></option>
														</select>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6 col-xs-6">
													<div class="form-group">
										            	<label><s:text name="point.name_zh_CN"/></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-tag"></li></span>
										                	<input type="text" name="pointVo.name" id="name_zh_CN" class="form-control" value="${pointVo.name}" readonly >
										              	</div>
										            </div>
										        </div>
										        <div class="col-md-6 col-xs-6">
													<div class="form-group">
										            	<label><s:text name="point.name_en_US"/></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-tag"></li></span>
										                	<input type="text" name="pointVo.name_en_US" id="name_en_US" class="form-control" value="${pointVo.name_en_US}" readonly >
										              	</div>
										            </div>
										        </div>
											</div>
											<div class="row">
												<div class="col-md-6 col-xs-6">
													<div class="form-group">
										            	<label><s:text name="point.description_zh_CN"/></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-tags"></li></span>
										                	<input type="text" name="pointVo.description" id="description_zh_CN" class="form-control" value="${pointVo.description}" readonly >
										              	</div>
										            </div>
										        </div>
										        <div class="col-md-6 col-xs-6">
													<div class="form-group">
										            	<label><s:text name="point.description_en_US"/></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-tags"></li></span>
										                	<input type="text" name="pointVo.description_en_US" id="description_en_US" class="form-control" value="${pointVo.description_en_US}"  readonly >
										              	</div>
										            </div>
										        </div>
											</div>
											<div class="row">
												<div class="col-md-6 col-xs-6">
													<div class="form-group">
										            	<label><s:text name="point.order"/></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-sort"></li></span>
										                	<input type="text" name="pointVo.order" id="order" class="form-control" value="${pointVo.order}" readonly >
										              	</div>
										            </div>
										        </div>
											</div>
					              		</div>
					              		<div class="tab-pane" id="pointDetail">
				              				<div class="row">
												<div class="col-md-6 col-xs-6">
							              			<div class="form-group">
									                	<label><s:text name="detail.description_zh_CN"/></label>
									                	<textarea class="form-control" name="detailVo.description" id="detail_description" rows="1" 
									                				readonly >${detailVo.description }</textarea>
									                </div>
									        	</div>
									        	<div class="col-md-6 col-xs-6">
							              			<div class="form-group">
									                	<label><s:text name="detail.description_en_US"/></label>
									                	<textarea class="form-control" name="detailVo.description_en_US" id="detail_description_en_US" rows="1"
																	readonly >${detailVo.description_en_US }</textarea>
									                </div>
									        	</div>
									    	</div>
									    	<div class="row">
												<div class="col-md-6 col-xs-6">
							              			<div class="form-group">
									                	<label><s:text name="detail.goal_zh_CN"/></label>
									                	<textarea class="form-control" name="detailVo.goal" id="detail_goal" rows="1" 
									                				readonly >${detailVo.goal }</textarea>
									                </div>
									        	</div>
									        	<div class="col-md-6 col-xs-6">
							              			<div class="form-group">
									                	<label><s:text name="detail.goal_en_US"/></label>
									                	<textarea class="form-control" name="detailVo.goal_en_US" id="detail_goal_en_US" rows="1" 
									                				readonly >${detailVo.goal_en_US }</textarea>
										        	</div>
									        	</div>
									    	</div>
									    	<div class="row">
												<div class="col-md-3 col-xs-3">
													<div class="form-group">
										            	<label><s:text name="detail.cost"/></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-battery-1"></li></span>
										                	<input type="text" name="detailVo.cost" id="cost" class="form-control" value="${detailVo.cost}" readonly >
										              	</div>
										            </div>
												</div>
												<div class="col-md-3 col-xs-3">
									    			<div class="form-group">
										            	<label><s:text name="detail.part"/></label>
										            	<select class="form-control select" name="detailVo.part" id="part" style="width: 100%;">
															<option value=""></option>
														</select>
										        	</div>
									    		</div>
									    		<div class="col-md-3 col-xs-3" id="detailFromPageDiv" style="display:none;">
									    			<div class="form-group">
										            	<label><s:text name="detail.fromPage"/></label>
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-hourglass-start"></li></span>
										                	<input type="text" id="from_page" name="detailVo.from_page" class="form-control" value="${detailVo.from_page}" readonly >
										              	</div>
										        	</div>
									    		</div>
									    		<div class="col-md-3 col-xs-3" id="detailToPageDiv" style="display:none;">
									    			<div class="form-group">
										            	<label><s:text name="detail.toPage"/></label>
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-hourglass-end"></li></span>
										                	<input type="text" id="to_page" name="detailVo.to_page" class="form-control" value="${detailVo.to_page}" readonly >
										              	</div>
										        	</div>
									    		</div>
									    	</div>
									    	<div class="row">
												<div class="col-md-3 col-xs-3">
													<div class="form-group">
										            	<label><s:text name="detail.material"/></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-file"></li></span>
										                	<input type="text" name="detailVo.material_file_name" id="material_name" class="form-control" value="${detailVo.material_file_name}" readonly >
										              	</div>
										            </div>
												</div>
												<div class="col-md-2 col-xs-2">
									    			<div class="form-group">
										            	<label><s:text name="detail.size"/></label>
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-balance-scale"></li></span>
										                	<input type="text" name="detailVo.material_file_size" id="material_size" class="form-control" value="${detailVo.material_file_size}" readonly >
										              		<span class="input-group-addon">B</span>
										              	</div>
										        	</div>
									    		</div>
									    		<div class="col-md-1 col-xs-1" data-toggle="tooltip" title="<s:text name="viewAttachTip"/>">
									    			<div class="form-group">
									    				<label style="width:100%;height:100%;">&nbsp;</label>
											            	<button type="button" id="down_material_btn" onclick="getDown(${detailVo.point_id},'material');"
																class="btn btn-default"><li class="fa fa-book"></li>&nbsp;</button>
										        	</div>
									    		</div>
									    		
									    		<div class="col-md-3 col-xs-3">
													<div class="form-group">
										            	<label><s:text name="detail.tHandBook"/></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-file"></li></span>
										                	<input type="text" name="detailVo.t_handbook_file_name" id="tHandBook_name" class="form-control" value="${detailVo.t_handbook_file_name}" readonly >
										              	</div>
										            </div>
												</div>
												<div class="col-md-2 col-xs-2">
									    			<div class="form-group">
										            	<label><s:text name="detail.size"/></label>
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-balance-scale"></li></span>
										                	<input type="text" name="detailVo.t_handbook_file_size" id="tHandBook_size" class="form-control" value="${detailVo.t_handbook_file_size}" readonly >
										              		<span class="input-group-addon">B</span>
										              	</div>
										        	</div>
									    		</div>
									    		<div class="col-md-1 col-xs-1" data-toggle="tooltip" title="<s:text name="viewAttachTip"/>">
									    			<div class="form-group">
									    				<label style="width:100%;height:100%;">&nbsp;</label>
											            	<button type="button" id="down_t_handbook_btn" onclick="getDown(${detailVo.point_id},'t_handbook');"
																class="btn btn-default"><li class="fa fa-book"></li>&nbsp;</button>
										        	</div>
									    		</div>
									    	</div>
									    	<div class="row">
												<div class="col-md-3 col-xs-3">
													<div class="form-group">
										            	<label><s:text name="detail.sHandBook"/></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-file"></li></span>
										                	<input type="text" name="detailVo.s_handbook_file_name" id="sHandBook_name" class="form-control" value="${detailVo.s_handbook_file_name}" readonly >
										              	</div>
										            </div>
												</div>
												<div class="col-md-2 col-xs-2">
									    			<div class="form-group">
										            	<label><s:text name="detail.size"/></label>
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-balance-scale"></li></span>
										                	<input type="text" name="detailVo.s_handbook_file_size" id="sHandBook_size" class="form-control" value="${detailVo.s_handbook_file_size}" readonly >
										              		<span class="input-group-addon">B</span>
										              	</div>
										        	</div>
									    		</div>
									    		<div class="col-md-1 col-xs-1" data-toggle="tooltip" title="<s:text name="viewAttachTip"/>" >
									    			<div class="form-group">
									    				<label style="width:100%;height:100%;">&nbsp;</label>
											            	<button type="button" id="down_s_handbook_btn" onclick="getDown(${detailVo.point_id},'s_handbook');"
																class="btn btn-default"><li class="fa fa-book"></li>&nbsp;</button>
										        	</div>
									    		</div>
									    		
									    		<div class="col-md-3 col-xs-3">
													<div class="form-group">
										            	<label><s:text name="detail.reference"/></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-file"></li></span>
										                	<input type="text" name="detailVo.reference_file_name" id="reference_name" class="form-control" value="${detailVo.reference_file_name}" readonly >
										              	</div>
										            </div>
												</div>
												<div class="col-md-2 col-xs-2">
									    			<div class="form-group">
										            	<label><s:text name="detail.size"/></label>
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-balance-scale"></li></span>
										                	<input type="text" name="detailVo.reference_file_size" id="reference_size" class="form-control" value="${detailVo.reference_file_size}" readonly >
										              		<span class="input-group-addon">B</span>
										              	</div>
										        	</div>
									    		</div>
									    		<div class="col-md-1 col-xs-1" data-toggle="tooltip" title="<s:text name="viewAttachTip"/>" >
									    			<div class="form-group">
									    				<label style="width:100%;height:100%;">&nbsp;</label>
											            	<button type="button" id="down_reference_btn" onclick="getDown(${detailVo.point_id},'reference');"
																class="btn btn-default"><li class="fa fa-book"></li>&nbsp;</button>
										        	</div>
									    		</div>
									    	</div>
					              		</div>
					           	 	</div>
					          	</div>
					          </div>
					    	<div class="box-footer" style="padding: 0 0 0 0;" >
								<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
									<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
										<button type="button" id="toEditButton" 
													class="btn btn-block btn-primary btn-sm"><i class="fa fa-edit"></i>&nbsp;&nbsp;&nbsp;<s:text name="point.getEditButton"/></button>
									</div>
									<div class="col-md-3 col-xs-3">
										<button type="button" id="closeButton" 
													class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/></button>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
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
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS","${pointVo.enable_key}",true);
	selectInitial("part","../system/option!getOptionsByGPVal.do?value=WHETHER","${detailVo.part_key}",true);
	selectInitial("first_skill", "../tech/skill!findSkillOption.do?skillVo.parent_id=0", "${pointVo.first_skill_id}", true);
	selectInitial("second_skill","../tech/skill!findSkillOption.do?skillVo.parent_id=${pointVo.first_skill_id}","${pointVo.second_skill_id}",true);
	selectInitial("third_skill","../tech/skill!findSkillOption.do?skillVo.parent_id=${pointVo.second_skill_id}","${pointVo.third_skill_id}",true);
	//textarea初始化内容
	/* $("#detail_description").val("${detailVo.description}");
	$("#detail_description_en_US").val("${detailVo.description_en_US}");
	$("#detail_goal").val("${detailVo.goal}");
	$("#detail_goal_en_US").val("${detailVo.goal_en_US}"); */
	//根据内容显示
	if("${detailVo.part_key}"=="Y"){
		$("#detailFromPageDiv").attr("style","display:block;");
		$("#detailToPageDiv").attr("style","display:block;");
	}
	adjust();
});

$(window).load(function(){
	if($("#material_name").val()=="" || $("#material_name").val()==null){
		$("#down_material_btn").attr("class","btn btn-default disabled");
		$("#down_material_btn").attr("style","display:none;");
	}
	if($("#tHandBook_name").val()=="" || $("#tHandBook_name").val()==null){
		$("#down_t_handbook_btn").attr("class","btn btn-default disabled");
		$("#down_t_handbook_btn").attr("style","display:none;");
	}
	if($("#sHandBook_name").val()=="" || $("#sHandBook_name").val()==null){
		$("#down_s_handbook_btn").attr("class","btn btn-default disabled");
		$("#down_s_handbook_btn").attr("style","display:none;");
	}
	if($("#reference_name").val()=="" || $("#reference_name").val()==null){
		$("#down_reference_btn").attr("class","btn btn-default disabled");
		$("#down_reference_btn").attr("style","display:none;");
	}
});
/**
 * 下载文件
 */
function getDown(pointId,type){//服务器请求 路径
	//文件下载修改为在线查看
	var pointFileType;
	if(type=="material")pointFileType="1";
	if(type=="t_handbook")pointFileType="2";
	if(type=="s_handbook")pointFileType="3";
	if(type=="reference")pointFileType="4";
	PointPDFViewOnline(pointId,pointFileType,"");
	//window.open("../tech/point!getDown.do?detailVo.point_id="+pointId+"&detailVo.description="+type);
}

/**
 * 跳转编辑界面
 */
$(document).on("click","#toEditButton",
	function() {
		parent.editPointDetail("${detailVo.point_id}");
		parent.layer.close(index);
	}
);

/**
 * 关闭按钮
 */
$(document).on("click","#closeButton",
	function() {
		parent.layer.close(index);
	}
);

</script>
</html>




