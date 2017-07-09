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
<%-- <script src="${pageContext.request.contextPath}/tech/point/addNewPoint.js"></script> --%>
<!-- 页面js国际化 -->
<script src="${pageContext.request.contextPath}/tech/point/${session.system_lang}.js"></script>

<!-- Ion Slider -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/plugins/ionslider/ion.rangeSlider.css">
<!-- ion slider Nice -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/plugins/ionslider/ion.rangeSlider.skinNice.css">
<!-- Ion Slider -->
<script src="${pageContext.request.contextPath}/common/plugins/ionslider/ion.rangeSlider.min.js"></script>
<style type="text/css">
	.content {
    	min-height: 0px;
	}
	html, body {
    	min-height: 0px;
	}
</style>
<title>添加知识点</title>
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
										                	<input type="text" name="pointVo.name" id="name_zh_CN" class="form-control" placeholder="Name in Chinese">
										              	</div>
										            </div>
										        </div>
										        <div class="col-md-6 col-xs-6">
													<div class="form-group">
										            	<label for="name_en_US"><s:text name="point.name_en_US"/><small><i class="fa fa-star notNull"></i></small></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-tag"></li></span>
										                	<input type="text" name="pointVo.name_en_US" id="name_en_US" class="form-control" placeholder="Name in English">
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
										                	<input type="text" name="pointVo.description" id="description_zh_CN" class="form-control" placeholder="Description in Chinese">
										              	</div>
										            </div>
										        </div>
										        <div class="col-md-6 col-xs-6">
													<div class="form-group">
										            	<label for="description_en_US"><s:text name="point.description_en_US"/></label>           
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-tags"></li></span>
										                	<input type="text" name="pointVo.description_en_US" id="description_en_US" class="form-control" placeholder="Description in English">
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
										                	<input type="text" name="pointVo.order" id="order" class="form-control" placeholder="Point Order(eg.1,2,3...)">
										              	</div>
										            </div>
										        </div>
											</div>
					              		</div>
					              		<div class="tab-pane" id="pointDetail">
											<!-- 隐藏 -->
				              				<div class="row" style="display:none">
				              					<div class="col-md-6 col-xs-6">
							              			<div class="form-group">
									                	<label for="point_id">ID</label>
									                	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-hourglass-start"></li></span>
										                	<input type="text" id="point_id" name="detailVo.point_id" class="form-control" placeholder="ID" value="">
										              	</div>
									                </div>
									        	</div>
				              				</div>
				              				<div class="row">
												<div class="col-md-6 col-xs-6">
							              			<div class="form-group">
									                	<label for="detail_description"><s:text name="detail.description_zh_CN"/></label>
									                	<textarea class="form-control" name="detailVo.description" id="detail_description" rows="1" 
									                				placeholder="Point Detail Description in Chinese ..."></textarea>
									                </div>
									        	</div>
									        	<div class="col-md-6 col-xs-6">
							              			<div class="form-group">
									                	<label for="detail_description_en_US"><s:text name="detail.description_en_US"/></label>
									                	<textarea class="form-control" name="detailVo.description_en_US" id="detail_description_en_US" rows="1"
																	placeholder="Point Detail Description in English ..."></textarea>
									                </div>
									        	</div>
									    	</div>
									    	<div class="row">
												<div class="col-md-6 col-xs-6">
							              			<div class="form-group">
									                	<label for="detail_goal"><s:text name="detail.goal_zh_CN"/></label>
									                	<textarea class="form-control" name="detailVo.goal" id="detail_goal" rows="1" 
									                				placeholder="The Goal for Student in Chinese ..."></textarea>
									                </div>
									        	</div>
									        	<div class="col-md-6 col-xs-6">
							              			<div class="form-group">
									                	<label for="detail_goal_en_US"><s:text name="detail.goal_en_US"/></label>
									                	<textarea class="form-control" name="detailVo.goal_en_US" id="detail_goal_en_US" rows="1" 
									                				placeholder="The Goal for Student in English ..."></textarea>
										        	</div>
									        	</div>
									    	</div>
									    	<div class="row">
												<div class="col-md-12 col-xs-12">
													<div class="form-group">
									                	<label for="cost"><s:text name="detail.cost"/><small><i class="fa fa-star notNull"></i></small></label>
								                		<input id="cost" type="text" name="detailVo.cost" value="0;100">
									                </div>
												</div>
									    	</div>
									    	<div class="row">
									    		<div class="col-md-4 col-xs-4">
									    			<div class="form-group">
										            	<label for="part"><s:text name="detail.part"/></label>
										            	<select class="form-control select" name="detailVo.part" id="part" style="width: 100%;">
															<option value=""></option>
														</select>
										        	</div>
									    		</div>
									    		<div class="col-md-4 col-xs-4" id="fromPageDiv" style="display:none;">
									    			<div class="form-group">
										            	<label for="from_page"><s:text name="detail.fromPage"/></label>
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-hourglass-start"></li></span>
										                	<input type="text" id="from_page" name="detailVo.from_page" class="form-control" placeholder="The start page no. in material">
										              	</div>
										        	</div>
									    		</div>
									    		<div class="col-md-4 col-xs-4"  id="toPageDiv" style="display:none;">
									    			<div class="form-group">
										            	<label for="to_page"><s:text name="detail.toPage"/></label>
										            	<div class="input-group">
										                	<span class="input-group-addon"><li class="fa fa-hourglass-end"></li></span>
										                	<input type="text" id="to_page" name="detailVo.to_page" class="form-control" placeholder="The end page no. in material">
										              	</div>
										        	</div>
									    		</div>
									    	</div>
					              		</div>
					           	 	</div>
					          	</div>
					    	</div>
					    	<div class="box-footer" style="padding: 0 0 0 0">
								<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
									<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
										<button type="button" id="closeButton"
											class="btn btn-block btn-primary btn-sm" onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">
											<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/>
										</button>
									</div>
									<div class="col-md-3 col-xs-3">
										<button type="button" id="addNewPointButton"
											class="btn btn-block btn-primary btn-sm"><i class="fa fa-check"></i>&nbsp;&nbsp;&nbsp;<s:text name="addNewPointButton"/></button>
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
	<div class="modal" id="uploadFileModel">
		<form id="uploadFileForm" >
			<div class="modal-dialog" style="width:100%;height:100%">
				<div class="modal-content"
					style="width: 100%; height: 100%; margin-top: 10%;">
					<div class="modal-header">
						<%-- <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button> --%>
						<h4 class="modal-title"><s:text name="detail.uploadFileTitle"/></h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-3 col-xs-3">
								<div class="form-group">
				                	<label for="material"><s:text name="detail.uploadMaterial"/></label>
				                	
				                	<button type="button"
										class="btn btn-block btn-default btn-sm"
										onclick="document.getElementById('material').click();">
										<li class="glyphicon glyphicon-plus"></li>&nbsp;&nbsp;
										<s:text name="chooseFile"/>
									</button>
				                	<p class="help-block" id="material_name"></p>
				                	<input type="file" id="material" style="display: none;" name="file1.file" onchange="change(this,'material')">
				                </div>
							</div>
							<div class="col-md-3 col-xs-3">
								<div class="form-group">
				                	<label for="t_handbook"><s:text name="detail.uploadTHandBook"/></label>
				                	
				                	<button type="button"
										class="btn btn-block btn-default btn-sm"
										onclick="document.getElementById('t_handbook').click();">
										<li class="glyphicon glyphicon-plus"></li>&nbsp;&nbsp;
										<s:text name="chooseFile"/>
									</button>
									<p class="help-block" id="t_handbook_name"></p>
				                	<input type="file" id="t_handbook" style="display: none;" name="file2.file" onchange="change(this,'t_handbook')">
				                </div>
							</div>
							<div class="col-md-3 col-xs-3">
								<div class="form-group">
				                	<label for="s_handbook"><s:text name="detail.uploadSHandBook"/></label>
				                	
				                	<button type="button"
										class="btn btn-block btn-default btn-sm"
										onclick="document.getElementById('s_handbook').click();">
										<li class="glyphicon glyphicon-plus"></li>&nbsp;&nbsp;
										<s:text name="chooseFile"/>
									</button>
									<p class="help-block" id="s_handbook_name"></p>
				                	<input type="file" id="s_handbook" style="display: none;" name="file3.file" onchange="change(this,'s_handbook')">
				                </div>
							</div>
							<div class="col-md-3 col-xs-3">
								<div class="form-group">
				                	<label for="reference_name"><s:text name="detail.uploadReference"/></label>
				                	
				                	<button type="button"
										class="btn btn-block btn-default btn-sm"
										onclick="document.getElementById('reference').click();">
										<li class="glyphicon glyphicon-plus"></li>&nbsp;&nbsp;
										<s:text name="chooseFile"/>
									</button>
									<p class="help-block" id="reference_name"></p>
				                	<input type="file" id="reference" style="display: none;" name="file4.file" onchange="change(this,'reference')">
				                </div>
							</div>
				    	</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="closeButton" class="btn btn-primary"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/></button>
						<button type="button" id="batchUpload" class="btn btn-primary"><i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;&nbsp;<s:text name="detail.upload"/></button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<%-- <div id="loading-mask" style="display:none;position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #D2E0F2; z-index: 20000;overflow-y: auto;">
		<div id="pageloading" style="position: absolute; top: 50%; left: 50%; margin: -120px 0px 0px -120px; text-align: center; width: 200px; height: 40px; font-size: 14px; padding: 10px; font-weight: bold; color: #15428B;">
			<img src="${pageContext.request.contextPath}/common/image/loadingpn.gif" align="middle" /> <s:property value="getText('拼命上载中....')"/>
		</div>
	</div> --%>
	<!-- 
  	显示:$('#uploadFileModel').modal('show');
	隐藏:$('#uploadFileModel').modal('hide');
	事件:$('#uploadFileModel').on('hidden', function () {// do something…}); 
	-->
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
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS","T",false);
	selectInitial("part","../system/option!getOptionsByGPVal.do?value=WHETHER","N",false);
	selectInitial("first_skill", "../tech/skill!findSkillOption.do?skillVo.parent_id=0", "", false);
	//获取二级联下拉框
	$('#first_skill').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
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
		selectInitial("third_skill","../tech/skill!findSkillOption.do?skillVo.parent_id="+selectedOption,"",false);
	});
	//'是否'下拉框 -- '是'的时候显示起止页面号输入框
	$('#part').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == "Y"){
			$("#fromPageDiv").show();
			$("#toPageDiv").show();
			return;
		}else{
			$("#fromPageDiv").hide();
			$("#toPageDiv").hide();
			return;
		}
	});
	//滑动条初始化
    $("#cost").ionRangeSlider({
        type: "single",
        grid: true,
        step: 1,
        postfix: " Hours",
        from: 2,
        hideMinMax: true,
        hideFromTo: false
     });
	//modal初始化
    $('#uploadFileModel').modal({backdrop: 'static', keyboard: false});
    $('#uploadFileModel').modal('hide');
    
    adjust();
});
/**
 * 添加知识点
 */
$(document).on("click","#addNewPointButton",
	function() {
		if($("#first_skill").val()==""){
			layer.msg(skill_need);
			return;
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
				url:"../tech/point!addPoint.do",
				data: $("#addForm").serialize(),
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.success=='true'){
						$("#point_id").val(data.datas.point_id);
						$('#uploadFileModel').modal('show');
						layer.msg(success_add_attach);
					}else{
						layer.msg(data.message,{shift: 6});
					}
				}
			});
		}
	}
);

function change(obj,type) {
    var file = $(obj);
    var ext=file.val();
    var ext=file.val().substring(file.val().lastIndexOf("/")+1).toLowerCase();
	ext=ext.substring(ext.lastIndexOf("\\")+1).toLowerCase();
    $("#"+type+"_name").text(ext);
}

/**
 * 上传
 */
$(document).on("click","#batchUpload",
	function() {
		var ids = ["material","t_handbook","s_handbook","reference"];//id
		var otherParam = $("#point_id").val();
		var load_index=layer.load(0, {
		    shade: [0.1,'#fff']
		});
    	ajaxFileUpload(ids,'../tech/point!upload.do',1,
    			"PointAttach\/"+$("#first_skill").val()+"\/"+$("#second_skill").val()+"\/"+$("#third_skill").val()+"\/"+$("#point_id").val(),
    			otherParam,
	    		function(data, status){
    				layer.close(load_index);
		        	if(data.success=="true"){//上传成功
		        		$('#uploadFileModel').modal('hide');
		        		parent.$("#pointTable").DataTable().draw();
		        		parent.layer.alert(add_point_and_file_success);
		        		parent.layer.close(index);
		        	}else{//上传失败
		        		if(data.message=='need_file'){
		        			layer.alert(need_file);
		        		}else if(data.message=='only_pdf'){
		        			layer.alert(need_file);
		        		}else{
		        			layer.alert(data.message);
		        		}
		        	}
	        	}
    	);
	}
);
/**
 * 关闭上传界面
 */
 $(document).on("click","#closeButton",
	function() {
	 	$('#uploadFileModel').modal('hide');
		parent.$("#pointTable").DataTable().draw();
		parent.layer.alert(add_point_success);
		parent.layer.close(index);
	}
);

</script>
</html>




