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
<title>编辑知识点</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" id="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<div class=row>
			<div class="box" id="box">
				<div class="box-body">
					<form id="updateForm" method="post">
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
														<label for="first_skill"><s:text name="point.firstSkill"/><small><i class="fa fa-star notNull"></i></small></label>
														<select class="form-control select"
															name="pointVo.first_skill" id="first_skill"
															style="width: 100%;">
															<option value=""></option>
														</select>
													</div>
												</div>
												<div class="col-md-6 col-xs-6">
													<div class="form-group">
														<label for="second_skill"><s:text name="point.secondSkill"/></label> <select class="form-control select"
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
														<label for="third_skill"><s:text name="point.thirdSkill"/></label> <select class="form-control select"
															name="pointVo.third_skill" id="third_skill"
															style="width: 100%;">
															<option value=""></option>
														</select>
													</div>
												</div>
												<div class="col-md-6 col-xs-6">
													<div class="form-group">
														<label for="enable"><s:text name="enable"/><small><i class="fa fa-star notNull"></i></small></label> 
														<select class="form-control select"
															name="pointVo.enable" id="enable" style="width: 100%;">
															<option value=""></option>
														</select>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6 col-xs-6">
													<div class="form-group">
										            	<label for="name_zh_CN"><s:text name="point.name_zh_CN"/><small><i class="fa fa-star notNull"></i></small></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-tag"></li></span>
										                	<input type="text" name="pointVo.name" id="name_zh_CN" class="form-control" value="${pointVo.name}" >
										              	</div>
										            </div>
										        </div>
										        <div class="col-md-6 col-xs-6">
													<div class="form-group">
										            	<label for="name_en_US"><s:text name="point.name_en_US"/><small><i class="fa fa-star notNull"></i></small></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-tag"></li></span>
										                	<input type="text" name="pointVo.name_en_US" id="name_en_US" class="form-control" value="${pointVo.name_en_US}">
										              	</div>
										            </div>
										        </div>
											</div>
											<div class="row">
												<div class="col-md-6 col-xs-6">
													<div class="form-group">
										            	<label for="description_zh_CN"><s:text name="point.description_zh_CN"/></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-tags"></li></span>
										                	<input type="text" name="pointVo.description" id="description_zh_CN" class="form-control" value="${pointVo.description}">
										              	</div>
										            </div>
										        </div>
										        <div class="col-md-6 col-xs-6">
													<div class="form-group">
										            	<label for="description_en_US"><s:text name="point.description_en_US"/></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-tags"></li></span>
										                	<input type="text" name="pointVo.description_en_US" id="description_en_US" class="form-control" value="${pointVo.description_en_US}">
										              	</div>
										            </div>
										        </div>
											</div>
											<div class="row">
												<div class="col-md-6 col-xs-6">
													<div class="form-group">
										            	<label for="order"><s:text name="point.order"/><small><i class="fa fa-star notNull"></i></small></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-sort"></li></span>
										                	<input type="text" name="pointVo.order" id="order" class="form-control" value="${pointVo.order}">
										              	</div>
										            </div>
										        </div>
											</div>
											<!-- 隐藏 -->
											<div class="row" style="display:none;">
												<div class="col-md-4 col-xs-4">
													<div class="form-group">
										            	<label>update time</label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-sort"></li></span>
										                	<input type="text" name="pointVo.update_time" id="point_update_time" class="form-control" value="${pointVo.update_time}">
										              	</div>
										            </div>
										        </div>
										        <div class="col-md-4 col-xs-4">
													<div class="form-group">
										            	<label>update person</label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-sort"></li></span>
										                	<input type="text" name="pointVo.update_person" id="point_update_person" class="form-control" value="${pointVo.update_person}">
										              	</div>
										            </div>
										        </div>
										        <div class="col-md-4 col-xs-4">
													<div class="form-group">
										            	<label>ID</label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-sort"></li></span>
										                	<input type="text" name="pointVo.id" id="point_id" class="form-control" value="${pointVo.id}">
										              	</div>
										            </div>
										        </div>
											</div>
					              		</div>
					              		<div class="tab-pane" id="pointDetail">
				              				<div class="row">
												<div class="col-md-6 col-xs-6">
							              			<div class="form-group">
									                	<label for="detail_description"><s:text name="detail.description_zh_CN"/></label>
									                	<textarea class="form-control" name="detailVo.description" id="detail_description" rows="1">${detailVo.description }</textarea>
									                </div>
									        	</div>
									        	<div class="col-md-6 col-xs-6">
							              			<div class="form-group">
									                	<label for="detail_description_en_US"><s:text name="detail.description_en_US"/></label>
									                	<textarea class="form-control" name="detailVo.description_en_US" id="detail_description_en_US" rows="1">${detailVo.description_en_US }</textarea>
									                </div>
									        	</div>
									    	</div>
									    	<div class="row">
												<div class="col-md-6 col-xs-6">
							              			<div class="form-group">
									                	<label for="detail_goal"><s:text name="detail.goal_zh_CN"/></label>
									                	<textarea class="form-control" name="detailVo.goal" id="detail_goal" rows="1">${detailVo.goal }</textarea>
									                </div>
									        	</div>
									        	<div class="col-md-6 col-xs-6">
							              			<div class="form-group">
									                	<label for="detail_goal_en_US"><s:text name="detail.goal_en_US"/></label>
									                	<textarea class="form-control" name="detailVo.goal_en_US" id="detail_goal_en_US" rows="1">${detailVo.goal_en_US }</textarea>
										        	</div>
									        	</div>
									    	</div>
									    	<div class="row">
												<div class="col-md-3 col-xs-3">
													<div class="form-group">
										            	<label for="cost"><s:text name="detail.cost"/><small><i class="fa fa-star notNull"></i></small></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-battery-1"></li></span>
										                	<input type="text" name="detailVo.cost" id="cost" class="form-control" value="${detailVo.cost}">
										              	</div>
										            </div>
												</div>
												<div class="col-md-3 col-xs-3">
									    			<div class="form-group">
										            	<label for="part"><s:text name="detail.part"/></label>
										            	<select class="form-control select" name="detailVo.part" id="part" style="width: 100%;">
															<option value=""></option>
														</select>
										        	</div>
									    		</div>
									    		<div class="col-md-3 col-xs-3" id="detailFromPageDiv" style="display:none;">
									    			<div class="form-group">
										            	<label for="from_page"><s:text name="detail.fromPage"/></label>
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-hourglass-start"></li></span>
										                	<input type="text" id="from_page" name="detailVo.from_page" class="form-control" value="${detailVo.from_page}">
										              	</div>
										        	</div>
									    		</div>
									    		<div class="col-md-3 col-xs-3" id="detailToPageDiv" style="display:none;">
									    			<div class="form-group">
										            	<label for="to_page"><s:text name="detail.toPage"/></label>
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-hourglass-end"></li></span>
										                	<input type="text" id="to_page" name="detailVo.to_page" class="form-control" value="${detailVo.to_page}">
										              	</div>
										        	</div>
									    		</div>
									    	</div>
									    	<div class="row">
												<div class="col-md-2 col-xs-2">
													<div class="form-group">
										            	<label for="material_name"><s:text name="detail.material"/></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-file"></li></span>
										                	<input type="text" name="detailVo.material_file_name" id="material_name" class="form-control" value="${detailVo.material_file_name}" readonly >
										              	</div>
										            </div>
												</div>
												<div class="col-md-2 col-xs-2">
									    			<div class="form-group">
										            	<label for="material_size"><s:text name="detail.size"/></label>
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-balance-scale"></li></span>
										                	<input type="text" name="detailVo.material_file_size" id="material_size" class="form-control" value="${detailVo.material_file_size}" readonly >
										              		<span class="input-group-addon">B</span>
										              	</div>
										        	</div>
									    		</div>
									    		
									    		<div class="col-md-2 col-xs-2">
									    			<div class="form-group">
									    				<label style="width:100%;height:100%;">&nbsp;</label>
											    		<div class="btn-group">
									                    	<button type="button" id="down_material_btn" onclick="getDown(${detailVo.point_id},'material');"
																	class="btn btn-default" data-toggle="tooltip" title="<s:text name="viewAttachTip"/>"><li class="fa fa-book"></li></button>
									                    	<button type="button" id="up_material_btn" onclick="toUpdateFile('material','<s:text name="detail.material"/>');"
																	class="btn btn-default"><li class="fa fa-cloud-upload"></li></button>
									                    	<button type="button" id="del_material_btn" onclick="toDeleteFile('material','<s:text name="detail.material"/>');"
																	class="btn btn-default"><li class="fa fa-trash"></li></button>
									                    </div>
								                    </div>
							                    </div>
									    		
									    		<div class="col-md-2 col-xs-2">
													<div class="form-group">
										            	<label for="t_handbook_name"><s:text name="detail.tHandBook"/></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-file"></li></span>
										                	<input type="text" name="detailVo.t_handbook_file_name" id="t_handbook_name" class="form-control" value="${detailVo.t_handbook_file_name}" readonly >
										              	</div>
										            </div>
												</div>
												<div class="col-md-2 col-xs-2">
									    			<div class="form-group">
										            	<label for="t_handbook_size"><s:text name="detail.size"/></label>
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-balance-scale"></li></span>
										                	<input type="text" name="detailVo.t_handbook_file_size" id="t_handbook_size" class="form-control" value="${detailVo.t_handbook_file_size}" readonly >
										              		<span class="input-group-addon">B</span>
										              	</div>
										        	</div>
									    		</div>
									    		<div class="col-md-2 col-xs-2">
									    			<div class="form-group">
									    				<label style="width:100%;height:100%;">&nbsp;</label>
											    		<div class="btn-group">
									                    	<button type="button" id="down_t_handbook_btn" onclick="getDown(${detailVo.point_id},'t_handbook');"
																class="btn btn-default" data-toggle="tooltip" title="<s:text name="viewAttachTip"/>"><li class="fa fa-book"></li></button>
									                    	<button type="button" id="up_t_handbook_btn" onclick="toUpdateFile('t_handbook','<s:text name="detail.tHandBook"/>');"
																class="btn btn-default"><li class="fa fa-cloud-upload"></li></button>
									                    	<button type="button" id="del_t_handbook_btn" onclick="toDeleteFile('t_handbook','<s:text name="detail.tHandBook"/>');"
																class="btn btn-default"><li class="fa fa-trash"></li></button>
									                    </div>
								                    </div>
							                    </div>
									    	</div>
									    	<div class="row">
												<div class="col-md-2 col-xs-2">
													<div class="form-group">
										            	<label for="s_handbook_name"><s:text name="detail.sHandBook"/></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-file"></li></span>
										                	<input type="text" name="detailVo.s_handbook_file_name" id="s_handbook_name" class="form-control" value="${detailVo.s_handbook_file_name}" readonly >
										              	</div>
										            </div>
												</div>
												<div class="col-md-2 col-xs-2">
									    			<div class="form-group">
										            	<label for="s_handbook_size"><s:text name="detail.size"/></label>
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-balance-scale"></li></span>
										                	<input type="text" name="detailVo.s_handbook_file_size" id="s_handbook_size" class="form-control" value="${detailVo.s_handbook_file_size}" readonly >
										              		<span class="input-group-addon">B</span>
										              	</div>
										        	</div>
									    		</div>
									    		<div class="col-md-2 col-xs-2">
									    			<div class="form-group">
									    				<label style="width:100%;height:100%;">&nbsp;</label>
											    		<div class="btn-group">
									                    	<button type="button" id="down_s_handbook_btn" onclick="getDown(${detailVo.point_id},'s_handbook');"
																class="btn btn-default" data-toggle="tooltip" title="<s:text name="viewAttachTip"/>"><li class="fa fa-book"></li></button>
									                    	<button type="button" id="up_s_handbook_btn" onclick="toUpdateFile('s_handbook','<s:text name="detail.sHandBook"/>');"
																class="btn btn-default"><li class="fa fa-cloud-upload"></li></button>
									                    	<button type="button" id="del_s_handbook_btn" onclick="toDeleteFile('s_handbook','<s:text name="detail.sHandBook"/>');"
																class="btn btn-default"><li class="fa fa-trash"></li></button>
									                    </div>
								                    </div>
							                    </div>
									    		
									    		<div class="col-md-2 col-xs-2">
													<div class="form-group">
										            	<label for="reference_name"><s:text name="detail.reference"/></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-file"></li></span>
										                	<input type="text" name="detailVo.reference_file_name" id="reference_name" class="form-control" value="${detailVo.reference_file_name}" readonly >
										              	</div>
										            </div>
												</div>
												<div class="col-md-2 col-xs-2">
									    			<div class="form-group">
										            	<label for="reference_size"><s:text name="detail.size"/></label>
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-balance-scale"></li></span>
										                	<input type="text" name="detailVo.reference_file_size" id="reference_size" class="form-control" value="${detailVo.reference_file_size}" readonly >
										              		<span class="input-group-addon">B</span>
										              	</div>
										        	</div>
									    		</div>
									    		<div class="col-md-2 col-xs-2">
									    			<div class="form-group">
									    				<label style="width:100%;height:100%;">&nbsp;</label>
											    		<div class="btn-group">
									                    	<button type="button" id="down_reference_btn" onclick="getDown(${detailVo.point_id},'reference');"
																class="btn btn-default" data-toggle="tooltip" title="<s:text name="viewAttachTip"/>"><li class="fa fa-book"></li></button>
									                    	<button type="button" id="up_reference_btn" onclick="toUpdateFile('reference','<s:text name="detail.reference"/>');"
																class="btn btn-default"><li class="fa fa-cloud-upload"></li></button>
									                    	<button type="button" id="del_reference_btn" onclick="toDeleteFile('reference','<s:text name="detail.reference"/>');"
																class="btn btn-default"><li class="fa fa-trash"></li></button>
									                    </div>
								                    </div>
							                    </div>
									    	</div>
									    	<!-- 隐藏 -->
											<div class="row" style="display:none;">
												<div class="col-md-4 col-xs-4">
													<div class="form-group">
										            	<label>ID</label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-sort"></li></span>
										                	<input type="text" name="detailVo.point_id" id="detail_point_id" class="form-control" value="${detailVo.point_id}">
										              	</div>
										            </div>
										        </div>
												<div class="col-md-4 col-xs-4">
													<div class="form-group">
										            	<label>update time</label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-sort"></li></span>
										                	<input type="text" name="detailVo.update_time" id="detail_update_time" class="form-control" value="${detailVo.update_time}">
										              	</div>
										            </div>
										        </div>
										        <div class="col-md-4 col-xs-4">
													<div class="form-group">
										            	<label>update person</label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-sort"></li></span>
										                	<input type="text" name="detailVo.update_person" id="detail_update_person" class="form-control" value="${detailVo.update_person}">
										              	</div>
										            </div>
										        </div>
											</div>
					              		</div>
					           	 	</div>
					          	</div>
					          </div>
					    	<div class="box-footer" style="padding: 0 0 0 0;" >
								<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
									<div class="col-md-3 col-xs-3  col-md-offset-6 col-xs-offset-6">
										<button type="button" id="closeButton" 
													class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/></button>
									</div>
									<div class="col-md-3 col-xs-3">
										<button type="button" id="updateButton" 
													class="btn btn-block btn-primary btn-sm"><i class="fa fa-refresh"></i>&nbsp;&nbsp;&nbsp;<s:text name="updateButton"/></button>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
		<!-- modal -->
	<div class="modal" id="updateFileModel">
		<form id="updateFile">
			<div class="modal-dialog">
				<div class="modal-content"
					style="width: 450px; height: 30%; margin-top: 10%;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title"><s:text name="detail.update_attachment"/></h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
		                	<label id="inputLabel"></label>
		                	<input type="hidden" value="" id="uploadFileType">
                			
                			<button type="button"
								class="btn btn-block btn-default btn-sm"
								onclick="document.getElementById('uploadFile').click();">
								<li class="glyphicon glyphicon-plus"></li>&nbsp;&nbsp;
								<s:text name="chooseFile"/>
							</button>
		                	<p class="help-block" id="uploadFile_name"></p>
		                	<input type="file" style="display: none;" id="uploadFile" name="file1.file" onchange="change(this)">
		                </div>
					</div>
					<div class="modal-footer">
						<button type="button" id="uploadButton" class="btn btn-primary"><i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;&nbsp;<s:text name="detail.upload"/></button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- 
  	显示:$('#updateFileModel').modal('show');
	隐藏:$('#updateFileModel').modal('hide');
	事件:$('#updateFileModel').on('hidden', function () {// do something…}); 
	-->
</body>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name);
var freshTable=false;
var ini1=true;
var ini2=true;

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
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS","${pointVo.enable_key}",false);
	selectInitial("part","../system/option!getOptionsByGPVal.do?value=WHETHER","${detailVo.part_key}",false);
	selectInitial("first_skill", "../tech/skill!findSkillOption.do?skillVo.parent_id=0", "${pointVo.first_skill_id}", false);
	selectInitial("second_skill","../tech/skill!findSkillOption.do?skillVo.parent_id=${pointVo.first_skill_id}","${pointVo.second_skill_id}", false);
	selectInitial("third_skill","../tech/skill!findSkillOption.do?skillVo.parent_id=${pointVo.second_skill_id}","${pointVo.third_skill_id}", false);
	//获取二级联下拉框
	$('#first_skill').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
			return;
		}
		if(ini1){
			ini1=false;
			return;
		}
		selectInitial("second_skill","../tech/skill!findSkillOption.do?skillVo.parent_id="+selectedOption,"",false);
	});
	//获取三级下拉框
	$('#second_skill').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
			return;
		}
		if(ini2){
			ini2=false;
			return;
		}
		selectInitial("third_skill","../tech/skill!findSkillOption.do?skillVo.parent_id="+selectedOption,"",false);
	});
	//textarea内容初始化
	/* $("#detail_description").val("${detailVo.description}");
	$("#detail_description_en_US").val("${detailVo.description_en_US}");
	$("#detail_goal").val("${detailVo.goal}");
	$("#detail_goal_en_US").val("${detailVo.goal_en_US}"); */
	//根据内容显示
	if("${detailVo.part_key}"=="Y"){
		$("#detailFromPageDiv").attr("style","display:block;");
		$("#detailToPageDiv").attr("style","display:block;");
	}
	//'是否'下拉框 -- '是'的时候显示起止页面号输入框
	$('#part').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == "Y"){
			$("#detailFromPageDiv").show();
			$("#detailToPageDiv").show();
			return;
		}else{
			$("#detailFromPageDiv").hide();
			$("#detailToPageDiv").hide();
			return;
		}
	});
	
	adjust();
});

$(window).load(function(){
	if($("#material_name").val()=="" || $("#material_name").val()==null){
		$("#down_material_btn").attr("class","btn btn-default disabled");
		$("#down_material_btn").attr("style","display:none;");
	}
	if($("#t_handbook_name").val()=="" || $("#t_handbook_name").val()==null){
		$("#down_t_handbook_btn").attr("class","btn btn-default disabled");
		$("#down_t_handbook_btn").attr("style","display:none;");
	}
	if($("#s_handbook_name").val()=="" || $("#s_handbook_name").val()==null){
		$("#down_s_handbook_btn").attr("class","btn btn-default disabled");
		$("#down_s_handbook_btn").attr("style","display:none;");
	}
	if($("#reference_name").val()=="" || $("#reference_name").val()==null){
		$("#down_reference_btn").attr("class","btn btn-default disabled");
		$("#down_reference_btn").attr("style","display:none;");
	} 
});

/**
 * 去更新附件
 */
function toUpdateFile(type,title){
	$("#inputLabel").text(title);
	$("#uploadFileType").val(type);
	$("#uploadFile_name").text("");
	$('#updateFileModel').modal('show');
}

function change(obj) {
    var file = $(obj);
    var ext=file.val();
    var ext=file.val().substring(file.val().lastIndexOf("/")+1).toLowerCase();
	ext=ext.substring(ext.lastIndexOf("\\")+1).toLowerCase();
    $("#uploadFile_name").text(ext);
    
}

/**
 * 上传附件
 */
$(document).on("click","#uploadButton",
	function() {
		var ids = ["uploadFile"];//id
		var otherParam = $("#detail_point_id").val()+"**and**"+$("#detail_update_time").val()+"**and**"+$("#uploadFileType").val();
		var load_index=layer.load(0, {
		    shade: [0.1,'#fff']
		});
		ajaxFileUpload(ids,'../tech/point!updateAttachFile.do',1,
				"PointAttach\/"+$("#first_skill").val()+"\/"+$("#second_skill").val()+"\/"+$("#third_skill").val()+"\/"+ $("#detail_point_id").val(),
				otherParam,
	    		function(data, status){
					layer.close(load_index);
		        	if(data.success=="true"){//上传成功
		        		$("#detail_update_time").val(data.datas.update_time);
		        		$("#detail_update_person").val(data.datas.update_person);
		        		$("#point_update_time").val(data.datas.update_time);
		        		$("#point_update_person").val(data.datas.update_person);
		        		$('#updateFileModel').modal('hide');
		        		var type=$("#uploadFileType").val();
		        		$('#'+type+'_name').val(data.datas.file_name);
		        		$('#'+type+'_size').val(data.datas.file_size);
		        		$('#down_'+type+'_btn').attr("class","btn btn-default");
		        		$('#down_'+type+'_btn').attr("style","display:block;");
		        		layer.alert(data.message);
		        		freshTable=true;//修改了知识点信息，在关闭此弹出界面后刷新表格
		        	}else{//上传失败
		        		$('#updateFileModel').modal('hide');
		        		if(data.message=="need_file"){
			        		layer.alert(need_file);
		        		}else if(data.message=="been_update_when_upload"){
		        			layer.alert(been_update_when_upload);
		        		}else if(data.message=="only_pdf"){
		        			layer.alert(only_pdf);
		        		}else{
			        		layer.alert(data.message);
		        		}
		        	}
	        	}
		);
	}
);
/**
 * 删除附件
 */
function toDeleteFile(type,title){
	layer.open({
		title: [
			attach_delete,
		 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    content: confirm_delete_attach+"("+title+")",
	    btn: [confirm,cancel],
	    shadeClose: false,
	    yes: function(){
	    	$.ajax({
				url:"../tech/point!deleteAttachFile.do",
				data: {"detailVo.point_id": $("#detail_point_id").val(),
					   "detailVo.description":type},
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.success=='true'){
						$("#detail_update_time").val(data.datas.update_time);
		        		$("#detail_update_person").val(data.datas.update_person);
		        		$("#point_update_time").val(data.datas.update_time);
		        		$("#point_update_person").val(data.datas.update_person);
		        		$('#'+type+'_name').val("");
		        		$('#'+type+'_size').val("");
		        		$('#down_'+type+'_btn').attr("class","btn btn-default disabled");
		        		$('#down_'+type+'_btn').attr("style","display:none;");
						layer.msg(success_del_attach);
						freshTable=true;//修改了知识点信息，在关闭此弹出界面后刷新表格
					}else{
						layer.msg(data.message);
					}
				}
			});
	    }
	});
}
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
 * 更新数据
 */
$(document).on("click","#updateButton",
	function() {
		if($("#first_skill").val()==""){
			layer.msg(skill_need);
			return ;
		}
		var itemArr=[    
		             {"id":"first_skill","type":"1","regular":null,"message":null},
		             {"id":"enable","type":"1","regular":null,"message":null},
		             {"id":"name_zh_CN","type":"1","regular":null,"message":null},
		             {"id":"name_en_US","type":"1","regular":null,"message":null},
		             {"id":"order","type":"1","regular":null,"message":null},
		             {"id":"order","type":"2","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
			$.ajax({
				url:"../tech/point!updatePoint.do",
				data: $("#updateForm").serialize(),
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.success=='true'){
						if(data.message=='21'){
							parent.$("#pointTable").DataTable().draw();
							parent.layer.msg(success_update);
							parent.layer.close(index);
						}else{
							layer.alert(have_update_alert);
						}
							
					}else{
						layer.msg(data.message);
					}
				}
			});
		}
	}
);

/**
 * 关闭按钮
 */
$(document).on("click","#closeButton",
	function() {
		if(freshTable)
			parent.$("#pointTable").DataTable().draw();
		parent.layer.close(index);
	}
);

</script>
</html>
