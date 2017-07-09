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
<script src="${pageContext.request.contextPath}/tech/teacherpool/${system_lang}.js"></script>
<title>教师-技能-知识点列表</title>
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
					<input type="text" name="tepVo.nbr" id="nbr" class="form-control" value="${tepVo.nbr}">
					<input type="text" name="ptVo.first_skill_id" id="firstId" class="form-control" value="${ptVo.first_skill_id}">
					<input type="text" name="ptVo.second_skill_id" id="secondId" class="form-control" value="${ptVo.second_skill_id}">
					<input type="text" name="ptVo.third_skill_id" id="thirdId" class="form-control" value="${ptVo.third_skill_id}">
					<%-- <input type="text" name="ptVo.id" id="point_id" class="form-control" value="${ptVo.id}"> --%>
				</div>
				<div class="row">
					<div class="box-body">
						<table id="pointTable" class="table table-bordered table-striped table-hover">
							<thead>
								<tr>
									<th><s:text name="point.name"/></th>
									<th><s:text name="point.description"/></th>
									<th><s:text name="contains"/></th>
									<th><s:text name="manageStatus"/></th>
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
	$("#managePointModel_title").text('${ptVo.description}');
	searchData=[];
	searchData[0]={"name":"tepVo.nbr","value":'${tepVo.nbr}'};
	searchData[1]={"name":"ptVo.first_skill_id","value":'${ptVo.first_skill_id}'};
	searchData[2]={"name":"ptVo.second_skill_id","value":'${ptVo.second_skill_id}'};
	searchData[3]={"name":"ptVo.third_skill_id","value":'${ptVo.third_skill_id}'};
	//searchData[4]={"name":"ptVo.id","value":'${ptVo.id}'};
	//技能表格控件初始化
	dataTable("pointTable","../tech/teacherpool!getPointPage.do",
			[ [ 0, "asc" ] ], 
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
				"mData" : "manage",
				"sClass" : "center"
			}, {
				"mData" : "id",
				"sClass" : "center"
			} ],
			[ 
			   {
					"aTargets" : [ 4 ],
					"mRender" : function(data, type, full) {
						var add = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+tip_point_add+'" onclick="addPoint(\''
							+ data + '\')"><i class="fa fa-plus-square"></i></a>';
						var remove = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+tip_point_remove+'" onclick="removePoint(\''
							+ data + '\')"><i class="fa fa-minus-square"></i></a>';
						var addManage = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+tipGrantManagePri+'" onclick="addManage(\''
							+ data + '\')"><i class="fa fa-check-circle"></i></a>';
						var revoke = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+tipRemoveManagePri+'" onclick="revoke(\''
								+ data + '\')"><i class="fa  fa-times-circle"></i></a>';
						if((full.manage=="是" || full.manage=="Yes") && (full.description_en_US=="是" || full.description_en_US=="Yes"))
									return revoke+"&nbsp;&nbsp;&nbsp;"+remove;
						else if((full.description_en_US=="是" || full.description_en_US=="Yes")&&(full.manage=="否" || full.manage=="No"))
							return addManage+"&nbsp&nbsp;&nbsp;"+remove;
						else if(full.description_en_US=="否" || full.description_en_US=="No")
							return add; 
						else 
							return null;
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
		url:"../tech/teacherpool!managePoint.do",
		data: {"tepVo.nbr":$("#nbr").val(),"ptVo.id":pointId,"tepVo.description":"add"},
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
		url:"../tech/teacherpool!managePoint.do",
		data: {"tepVo.nbr":$("#nbr").val(),"ptVo.id":pointId,"tepVo.description":"remove"},
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

/**
 * 移除管理知识点权限
 */
function revoke(pointId){
	$.ajax({
		type : "POST",
		url : "../tech/teacherpool!revokeToTeacher.do",
		data: {"tepVo.nbr":$("#nbr").val(),"ptVo.id":pointId},		
		success:function(data){
			 //成功的回调函数
				if(data.message=="success"){
					$("#pointTable").DataTable().draw();
					layer.msg(teacher45);
				}  
			else{
			    layer.msg(data.message);
			}		 				       
	}
})
}
/**
 * 添加管理知识点权限
 */
 function addManage(pointId){
 	$.ajax({
 		type : "POST",
 		url : "../tech/teacherpool!addManage.do",
 		data: {"tepVo.nbr":$("#nbr").val(),"ptVo.id":pointId},		
 		success:function(data){
 			 //成功的回调函数
 				if(data.message=="success"){
 					$("#pointTable").DataTable().draw();
 					layer.msg(teacher46);
 				}  
 			else{
 			    layer.msg(data.message);
 			}		 				       
 	}
 })
 }
</script>
</html>




