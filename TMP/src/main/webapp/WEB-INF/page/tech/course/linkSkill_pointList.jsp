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
<title>课程-技能-知识点列表</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title" id="managePointModel_title">
				</h3>
			</div>
			<div class="box-body">
				<!-- 隐藏 -->
				<div class="row" style="display:none">
					<input type="text" name="courseVo.id" id="courseId" class="form-control" value="${courseVo.id}">
					<input type="text" name="pointVo.first_skill_id" id="firstId" class="form-control" value="${pointVo.first_skill_id}">
					<input type="text" name="pointVo.second_skill_id" id="secondId" class="form-control" value="${pointVo.second_skill_id}">
					<input type="text" name="pointVo.third_skill_id" id="thirdId" class="form-control" value="${pointVo.third_skill_id}">
				</div>
				<div class="row">
					<div class="col-md-12 col-xs-12">
						<table id="pointTable" class="table table-bordered table-striped table-hover">
							<thead>
								<tr>
									<th><s:text name="point.name"/></th>
									<th><s:text name="point.description"/></th>
									<th><s:text name="contains"/></th>
									<th><s:text name="operation"/></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name);
var searchData = [ { "name" : "more_data" , "value" : "my_value" } ];
$(function() {
	parent.layer.iframeAuto(index);
	$("#managePointModel_title").text('${pointVo.description}');
	searchData=[];
	searchData[0]={"name":"courseVo.id","value":'${courseVo.id}'};
	searchData[1]={"name":"pointVo.first_skill_id","value":'${pointVo.first_skill_id}'};
	searchData[2]={"name":"pointVo.second_skill_id","value":'${pointVo.second_skill_id}'};
	searchData[3]={"name":"pointVo.third_skill_id","value":'${pointVo.third_skill_id}'};
	//技能表格控件初始化
	dataTable("pointTable","../tech/course!getPointPage.do",
			[ [ 3, "asc" ] ], 
			[ {
				"mData" : "name",
				"sClass" : "center"
			}, {
				"mData" : "description",
				"sClass" : "center"
			}, {
				"mData" : "description_en_US",
				"sClass" : "center"
			}, {
				"mData" : "id",
				"sClass" : "center"
			} ],
			[ 
			   {
					"aTargets" : [ 3 ],
					"mRender" : function(data, type, full) {
						var add = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+tip_point_add+'" onclick="addPoint(\''
							+ data + '\')"><i class="fa fa-plus-square"></i></a>';
						var remove = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+tip_point_remove+'" onclick="removePoint(\''
							+ data + '\')"><i class="fa fa-minus-square"></i></a>';
						if(full.description_en_US=="是" || full.description_en_US=="Yes")
							return remove;
						else if(full.description_en_US=="否" || full.description_en_US=="No")
							return add;
					}
			   }
			]
	);
});
/**
 * 添加知识点
 */
function addPoint(pointId){
	$.ajax({
		url:"../tech/course!managePoint.do",
		data: {"courseVo.id":$("#courseId").val(),"pointVo.id":pointId,"courseVo.description":"add"},
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.success=='true'){
        		$("#pointTable").DataTable().draw();
        		layer.msg(success_add_point);
			}else{
				layer.msg(data.message,{shift: 6});
			}
		}
	});
}
/**
 * 移除知识点
 */
function removePoint(pointId){
	$.ajax({
		url:"../tech/course!managePoint.do",
		data: {"courseVo.id":$("#courseId").val(),"pointVo.id":pointId,"courseVo.description":"remove"},
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.success=='true'){
        		$("#pointTable").DataTable().draw();
        		layer.msg(success_remove_point);
			}else{
				layer.msg(data.message,{shift: 6});
			}
		}
	});
}
</script>
</html>




