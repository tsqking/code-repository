$(function() {
	var username = $.cookie("username");

	function friendQueryParams(params) { // 配置参数
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			offset : params.offset, // 页码
			sortname : params.sort, // 排序列名
			sortorder : params.order,// 排序规则
			pageWhere1 : $("#searchUserId").val(),
			pageWhere2 : $("#searchUsername").val(),
			pageWhere3 : username
		};
		return temp;
	}

	$('#myfriendTbl').bootstrapTable({
		method : "post",// 请求方式（*）
		url : "myfriendList.do",// 请求后台的URL（*）
		queryParams : friendQueryParams,// 传递参数（*)
		uniqueId : "f_friend_nickname",// 唯一标识
		pageSize : 10,// 每页的记录行数（*）
		pageList : [ 5, 10, 15, 20, 25, 30 ],// 可供选择的每页的行数（*）
		pageNumber : 1,// 初始化加载第一页，默认第一页
		pagination : true,// 是否显示分页（*）
		sidePagination : 'server',// 分页方式：client客户端分页，server服务端分页（*）
		sortName : "f_add_date",// 排序字段
		sortOrder : "desc",// 排序方式
		showColumns : true, // 是否显示所有的列
		toolbar : "#toolbar_myfriend",// 工具按钮用哪个容器
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
			field : 'u_phone',
			title : '电话'
		}, {
			field : 'f_add_date',
			title : '添加时间'
		} ],
		onDblClickRow : function(row) {
			//在显示详细信息之前，把所有值置空
			$("#fid").html("");
			$("#fnickname").html("");
			$("#fname").html("");
			$("#fgender").html("");
			$("#fage").html("");
			$("#femail").html("");
			$("#faddress").html("");
			$("#fphone").html("");
			$("#fmotto").html("");
			$("#fImg").attr({
				"alt" : "",
				"src" : ""
			});
			$("#fImg2").attr({
				"alt" : "",
				"src" : ""
			});
			$("#addfriendBtn").hide();
			if (row != null) {
				$("#fid").html(row.u_id);
				$("#fnickname").html(row.u_nickname);
				$("#fname").html(row.u_name);
				$("#fgender").html(row.u_gender);
				$("#fage").html(row.u_age);
				$("#femail").html(row.u_email);
				$("#faddress").html(row.u_address);
				$("#fphone").html(row.u_phone);
				$("#fmotto").html(row.u_motto);
				$("#fImg").attr({
					"alt" : row.u_nickname,
					"src" : row.u_face
				});
				$("#fImg2").attr({
					"alt" : row.u_nickname,
					"src" : row.u_face
				});
				$("#fdetail").modal({
					keyboard : true
				});
			}
		}
	});

	// 触发按钮点击事件，多条件模糊搜索
	$("#friendSearchBtn").on("click", function() {
		$('#myfriendTbl').bootstrapTable("refresh", {
			silent : true
		});
	});

	// 添加
	$("#btn_addfriend").click(function() {
		$("#find_user").modal({
			keyboard : true
		});
	});
	$("#findfriendBtn").click(
			function() {
				$("#addfriendBtn").show();
				if ($("#addfriendNickname").val() == null
						|| $("#addfriendNickname").val() == "") {
					alert("请输入用户名再搜索");
				} else {
					$("#fid").html("");
					$("#fnickname").html("");
					$("#fname").html("");
					$("#fgender").html("");
					$("#fage").html("");
					$("#femail").html("");
					$("#faddress").html("");
					$("#fphone").html("");
					$("#fmotto").html("");
					$("#fImg").attr({
						"alt" : "",
						"src" : ""
					});
					$("#fImg2").attr({
						"alt" : "",
						"src" : ""
					});
					$("#find_user").modal("hide");
					var json = {
						u_nickname : $("#addfriendNickname").val()
					}
					$.ajax({
						type : "post",
						url : "findFriend.do",
						contentType : "application/json",
						data : JSON.stringify(json),
						dataType : "json",
						success : function(data) {
							if (data.finduser != null && data.finduser != "") {
								$("#fid").html(data.finduser.u_id);
								$("#fnickname").html(data.finduser.u_nickname);
								$("#fname").html(data.finduser.u_name);
								$("#fgender").html(data.finduser.u_gender);
								$("#fage").html(data.finduser.u_age);
								$("#femail").html(data.finduser.u_email);
								$("#faddress").html(data.finduser.u_address);
								$("#fphone").html(data.finduser.u_phone);
								$("#fmotto").html(data.finduser.u_motto);
								$("#fImg").attr({
									"alt" : data.finduser.u_nickname,
									"src" : data.finduser.u_face

								});
								$("#fImg2").attr({
									"alt" : data.finduser.u_nickname,
									"src" : data.finduser.u_face

								});
								$("#fdetail").modal({
									keyboard : true
								});
							} else {
								alert(data.msg);
							}
						},
						error : function(data) {
							alert(data.msg);
						}
					});
				}

			});

	$("#addfriendBtn").click(function() {
		$("#addfriendBtn").removeAttr("disabled");
		var json = {
			f_self_nickname : username,
			f_friend_nickname : $("#addfriendNickname").val()
		}
		$.ajax({
			type : "post",
			url : "addFriend.do",
			contentType : "application/json",
			data : JSON.stringify(json),
			dataType : "json",
			success : function(data) {
				$('#myfriendTbl').bootstrapTable("refresh", {
					silent : true
				});
				$('#add_friend').modal('hide');
				$('#fdetail').modal('hide');
				alert(data.msg);
			},
			error : function(data) {
				alert(data.msg);
			}
		});
	});

	// 删除
	$("#btn_deletefriend").click(function() {
		var rows = $('#myfriendTbl').bootstrapTable('getAllSelections');
		var json = [];
		for (var i = 0; i < rows.length; i++) {
			json.push(rows[i].u_nickname);
		}
		if (json == null || json == "") {
			alert("删除失败,您还未选择任何数据");
		} else {
			$.ajax({
				type : "post",
				url : "deleteFriend.do",
				contentType : "application/json",
				data : JSON.stringify(json),
				dataType : "json",
				success : function(data) {
					$('#myfriendTbl').bootstrapTable("refresh", {
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

	// 加入黑名单
	$("#btn_friendToBlack").click(function() {
		var rows = $('#myfriendTbl').bootstrapTable('getAllSelections');
		var json = [];
		for (var i = 0; i < rows.length; i++) {
			json.push(rows[i].u_nickname);
		}
		if (json == null || json == "") {
			alert("添加黑名单失败,您还未选择任何数据");
		} else {
			$.ajax({
				type : "post",
				url : "blackFriend.do",
				contentType : "application/json",
				data : JSON.stringify(json),
				dataType : "json",
				success : function(data) {
					$('#myfriendTbl').bootstrapTable("refresh", {
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
});