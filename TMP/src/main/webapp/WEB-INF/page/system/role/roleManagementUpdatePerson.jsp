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
<!-- 页面js -->
<title>角色人员</title>
</head>
<body>
	<!-- Main content -->
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<div class="box box-primary ">
			<div class="box-header with-border" data-widget="collapse">
				<i class="fa fa-minus"></i>
				<h3 class="box-title"><s:text name="searchTitle"/></h3>
			</div>
			<form id="searchForm">
				<!-- /.box-header -->
				<div class="box-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label><s:text name="role.userName"/></label>
								<div class="input-group">
									<span class="input-group-addon"><li
										class="fa fa-user-secret"></li></span> <input type="text"
										name="user.username" id="searchUserName"
										class="form-control" placeholder="UserName">
								</div>
							</div>
						</div>
						<!-- /.col -->
						<div class="col-md-6">
							<div class="form-group">
								<label><s:text name="role.userHasRole"/></label> <select class="form-control select"
									name="user.description" id="searchDescription"
									style="width: 100%;">
									<option value=""></option>
								</select>	
							</div>
						</div>
					</div>
				</div>
				<div class="box-footer" style="padding: 0 0 0 0">
					<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
						<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
							<button type="button" id="resetButton"
									class="btn btn-block btn-primary btn-sm"><i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="resetButton"/></button>
						</div>
						<div class="col-md-3 col-xs-3">
							<button type="button" id="searchButton" onclick="searchTable()"
									class="btn btn-block btn-success btn-sm"><i class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;<s:text name="searchButton"/></button>
						</div>
					</div>
				</div>
			</form>
		</div>
		<!-- 数据表格 -->
		<div class="row">
			<div class="col-xs-12">
				<div class="box box-primary" style="margin-bottom: 5px;">
					<div class="box-header">
						<h3 class="box-title" >
							<s:text name="role.roleUpdatePersonTitle">
								<s:param>${role.name }</s:param>
							</s:text>
						</h3>
					</div>
					<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
						<div class="col-md-6 col-xs-6">
							<button type="button" onclick="batchUpdatePersonsInRole()" class="btn btn-block btn-primary btn-sm">
									<i class="fa fa-space-shuttle"></i>&nbsp;&nbsp;&nbsp;<s:text name="role.roleBatchUpdatePerson"/></button>
						</div>
						<div class="col-md-6 col-xs-6">
							<button type="button" id="closeButton"
								class="btn btn-block btn-primary btn-sm" onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">
								<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/>
							</button>
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table id="userTable"
							class="table table-bordered table-striped table-hover">
							<thead>
								<tr>
									<th><s:text name="role.userName"/></th>
									<th><s:text name="role.systemRole"/></th>
									<th><s:text name="role.userNo"/></th>
									<th><s:text name="role.userRealName"/></th>
									<th><s:text name="role.userEnName"/></th>
									<th><s:text name="role.userGender"/></th>
									<th><s:text name="role.userRolePossess"/></th>
									<th><s:text name="role.userHasRole"/></th>
									<th><s:text name="option"/></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- modal -->
	<div class="modal" id="batchUpdateModel">
		<form id="batchUpdateForm">
			<div class="modal-dialog">
				<div class="modal-content"
					style="width: 520px; height: 340px; margin-top: 30%;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title"><s:text name="role.roleBatchUpdatePersonTitle"/></h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
	                		<label><s:text name="role.userName"/>:</label>
	                		<textarea class="form-control" rows="3" placeholder="Please use English mark ';' to split up every username" id='names'></textarea>
	                	</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="batchUnlink" class="btn btn-default" data-dismiss="modal"><i class="fa fa-unlink"></i>&nbsp;&nbsp;&nbsp;<s:text name="role.batchUnlinkButton"/></button>
						<button type="button" id="batchLink" class="btn btn-primary"><i class="fa fa-link"></i>&nbsp;&nbsp;&nbsp;<s:text name="role.batchLinkButton"/></button>
					</div>
				</div>
			</div>
		</form>
	</div>
	
	<!-- 
    		显示:$('#batchUpdateModel').modal('show');
			隐藏:$('#batchUpdateModel').modal('hide');
			事件:$('#batchUpdateModel').on('hidden', function () {// do something…}); 
		-->
	
	<s:include value="../../common.jsp"></s:include>
	<!-- tree -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/common/plugins/tree/tree.css">
	
<script type="text/javascript">

var index = parent.layer.getFrameIndex(window.name);

//搜索数据 
var searchData = [ {
	"name" : "more_data",
	"value" : "my_value"
} ];

$(function() {
    //初始化界面
    initialPage();
});

function initialPage(){
	//Initialize Select2 Elements
	selectInitial("searchDescription","../system/option!getOptionsByGPVal.do?value=HAS","1",false);
	searchData[0]={"name":"user.description","value":"1"};
	//initial datatable
	dataTable("userTable","../system/role!getRoleUpdatePersonPage.do?role.id=${role.id}",
			[ [ 0, "desc" ] ], 
			[ {
				"mData" : "username",
				"sClass" : "center"
			}, {
				"mData" : "role",
				"sClass" : "center"
			}, {
				"mData" : "no",
				"sClass" : "center"
			}, {
				"mData" : "name",
				"sClass" : "center"
			}, {
				"mData" : "en_name",
				"sClass" : "center"
			}, {
				"mData" : "gender",
				"sClass" : "center"
			}, {
				"mData" : "role_name",
				"sClass" : "center"
			}, {
				"mData" : "description",
				"sClass" : "center"
			}, {
				"mData" : "id",
				"sClass" : "center"
			}, ],
			[ {
				"aTargets" : [ 8 ],
				//"mData" : "操作",
				"mRender" : function(data, type, full) {
					if(full.description=="已拥有" || full.description=="Has")
						return '<a href="javascript:void(0);" onclick="updatePersonsInRole(\''+0+'\',\''+full.username+'\');" data-toggle=\"tooltip\" title=\"Remove from Role\"><i class="fa fa-user-times"></i></a>';
					else
						return '<a href="javascript:void(0);" onclick="updatePersonsInRole(\''+1+'\',\''+full.username+'\');" data-toggle=\"tooltip\" title=\"Add to Role\"><i class="fa fa-user-plus"></i></a>';
				}
			} ]
	);
}

//搜索按钮
$(document).on("click",
				"#searchButton",
				function() {
					var frmID = document.getElementById("searchForm");
					var i, queryString = "", and = "";
					// for each form's object
					var item;
					// store each form object's value
					var itemValue;
					for (i = 0; i < frmID.length; i++) {
						// get form's each object
						item = frmID[i];
						if (item.name != '') {
							if (item.type == 'select-one') {
								itemValue = item.options[item.selectedIndex].value;
							} else if (item.type == 'checkbox'
									|| item.type == 'radio') {
								if (item.checked == false) {
									continue;
								}
								itemValue = item.value;
							} else if (item.type == 'button'
									|| item.type == 'submit'
									|| item.type == 'reset'
									|| item.type == 'image') {// ignore this type
								continue;
							} else {
								itemValue = item.value;
							}
							//存
							var tem = {
								"name" : item.name,
								"value" : itemValue
							};
							searchData[i] = tem;
						}
					}
					$("#userTable").DataTable().draw();
				});

//重置按钮
$(document).on("click", "#resetButton", function() {
	$("#searchUserName").val("");
	$("#searchDescription").val(null).trigger("change");
	searchData=[];
});

//角色人员关联关系修改
function updatePersonsInRole(operation,usernames){
	$.ajax({
		url:"../system/role!updatePersonsInRole.do",
		data: {
			"user.username": usernames,
			"user.enable":operation,
			"role.id":"${role.id}"
			},
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.success=='true'){
				layer.msg(data.message);
				$("#userTable").DataTable().draw();
			}else{
				layer.msg(data.message,{shift: 6});
			}
		}
    });
	
}

//批量修改人员角色关联关系
function batchUpdatePersonsInRole(){
	$('#batchUpdateModel').modal('show');
}

//批量人员失联按钮
$(document).on("click", "#batchUnlink", function() {
	var userlist=$("#names").val();
	updatePersonsInRole('0',userlist);
});
//批量人员关联按钮
$(document).on("click", "#batchLink", function() {
	var userlist=$("#names").val();
	updatePersonsInRole('1',userlist);
	$('#batchUpdateModel').modal('hide');
});

</script>
</body>
</html>




