var inputUserid = $("#inputUserid");
var inputNickname = $("#inputNickname");
var inputName = $("#inputName");
var inputGender = $("#inputGender");
var inputAge = $("#inputAge");
var inputEmail = $("#inputEmail");
var inputAddress = $("#inputAddress");
var inputPhone = $("#inputPhone");
var inputPasswordQues = $("#inputPasswordQues");
var inputPasswordAns = $("#inputPasswordAns");
var inputType = $("#inputType");
var inputEnable = $("#inputEnable");

function userQueryParams(params) { // 配置参数
	var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		limit : params.limit, // 页面大小
		offset : params.offset, // 页码
		sortname : params.sort, // 排序列名
		sortorder : params.order,// 排序规则
		pageWhere1 : $("#searchUserId").val(),// 查询参数:用户学号/工号
		pageWhere2 : $("#searchUserName").val()
	// 查询参数：用户名
	};
	return temp;
}
// 用户管理模块
$('#userMngTbl').bootstrapTable({
	method : "post",// 请求方式（*）
	url : "listUser.do",// 请求后台的URL（*）
	queryParams : userQueryParams,// 传递参数（*)
	uniqueId : "u_id",// 唯一标识
	pageSize : 10,// 每页的记录行数（*）
	pageList : [ 5, 10, 15, 20, 25, 30 ],// 可供选择的每页的行数（*）
	pageNumber : 1,// 初始化加载第一页，默认第一页
	pagination : true,// 是否显示分页（*）
	sidePagination : 'server',// 分页方式：client客户端分页，server服务端分页（*）
	sortName : "u_register_date",// 排序字段
	sortOrder : "desc",// 排序方式
	showColumns : true, // 是否显示所有的列
	toolbar : "#toolbar_User",// 工具按钮用哪个容器
	clickToSelect : true,// 是否启用点击选中行
	cache : false,// 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	striped : false,// 是否显示行间隔色
	showRefresh : true, // 是否显示刷新按钮
	showToggle : true,// 是否显示详细视图和列表视图的切换按钮
	cardView : false,// 是否显示详细视图
	detailView : false,// 是否显示父子表
	paginationLoop : false,// 是否允许循环分页
	paginationPreText : "上一页",// 指定上一页按钮文字，不配置默认<
	paginationNextText : "下一页",
	/*
	 * showExport:true, exportDataType: "selected",
	 */// 是否导出，需要额外的js支持(bootstrap-table-export.js和tableExport.js)
	columns : [ {
		checkbox : true
	}, {
		field : 'u_id',
		title : '学号/工号'
	}, {
		field : 'u_nickname',
		title : '用户名'
	}, {
		field : 'u_name',
		title : '姓名'
	}, {
		field : 'u_gender',
		title : '性别'
	}, {
		field : 'u_age',
		title : '年龄'
	}, {
		field : 'u_email',
		title : '邮箱'
	}, {
		field : 'u_address',
		title : '地址'
	}, {
		field : 'u_phone',
		title : '电话'
	}, {
		field : 'u_point',
		title : '积分'
	}, {
		field : 'u_password_ques',
		title : '密保问题'
	}, {
		field : 'u_password_ans',
		title : '密保答案'
	}, {
		field : 'u_register_date',
		title : '注册时间'
	}, {
		field : 'u_type',
		title : '用户类型'
	}, {
		field : 'u_is_enable',
		title : '是否可用'
	} ],
	onDblClickRow : function(row) {
		if (row != null) {
			inputUserid.val(row.u_id);
			inputNickname.val(row.u_nickname);
			inputName.val(row.u_name);
			inputGender.val(row.u_gender);
			inputAge.val(row.u_age);
			inputEmail.val(row.u_email);
			inputAddress.val(row.u_address);
			inputPhone.val(row.u_phone);
			inputPasswordQues.val(row.u_password_ques);
			inputPasswordAns.val(row.u_password_ans);
			inputType.val(row.u_type);
			inputEnable.val(row.u_is_enable);
			$('#update_user').modal({
				keyboard : true
			});
		}
	}
});

// 修改
$("#editUserBtn").click(function() {
	var json = {
		u_nickname : inputNickname.val(),
		u_name : inputName.val(),
		u_gender : inputGender.val(),
		u_age : inputAge.val(),
		u_email : inputEmail.val(),
		u_address : inputAddress.val(),
		u_phone : inputPhone.val(),
		u_password_ques : inputPasswordQues.val(),
		u_password_ans : inputPasswordAns.val(),
		u_type : inputType.val(),
		u_is_enable : inputEnable.val()
	}

	// 修改
	$.ajax({
		type : "post",
		url : "updateUser.do",
		contentType : "application/json",
		data : JSON.stringify(json),
		dataType : "json",
		success : function(data) {
			$('#userMngTbl').bootstrapTable("refresh", {
				silent : true
			});
			$('#update_user').modal('hide');
			alert(data.msg);
		},
		error : function(data) {
			alert(data.msg);
		}
	});
});

// 批量删除
$("#btn_deleteUser").click(function() {
	var rows = $('#userMngTbl').bootstrapTable('getAllSelections');
	var json = [];
	for (var i = 0; i < rows.length; i++) {
		json.push(rows[i].u_nickname);
	}
	if (json == null || json == "") {
		alert("删除失败,您还未选择任何数据");
	} else {
		$.ajax({
			type : "post",
			url : "deleteUser.do",
			contentType : "application/json",
			data : JSON.stringify(json),
			dataType : "json",
			success : function(data) {
				$('#userMngTbl').bootstrapTable("refresh", {
					silent : true
				});
				alert(data.msg);
			},
			error : function(data) {
				alert(data.msg);
			}
		});
	}
});
// 多条件模糊查询
$("#bgUserSearchBtn").click(function() {
	$('#userMngTbl').bootstrapTable("refresh", {
		silent : true
	});
});
