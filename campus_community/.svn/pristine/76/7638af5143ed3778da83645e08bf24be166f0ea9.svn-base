$(function() {
	var username = $.cookie("username");

	function readQueryParams(params) { // 配置参数
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			offset : params.offset, // 页码
			sortname : params.sort, // 排序列名
			sortorder : params.order,// 排序规则
			pageWhere1 : $("#searchReadTitle").val(),
			pageWhere2 : $("#searchReadContent").val(),
			pageWhere3 : $("#searchReadSender").val(),
			pageWhere4 : username
		};
		return temp;
	}

	$('#readTbl').bootstrapTable({
		method : "post",// 请求方式（*）
		url : "listRead.do",// 请求后台的URL（*）
		queryParams : readQueryParams,// 传递参数（*)
		uniqueId : "m_id",// 唯一标识
		pageSize : 5,// 每页的记录行数（*）
		pageList : [ 5, 10, 15, 20, 25, 30 ],// 可供选择的每页的行数（*）
		pageNumber : 1,// 初始化加载第一页，默认第一页
		pagination : true,// 是否显示分页（*）
		sidePagination : 'server',// 分页方式：client客户端分页，server服务端分页（*）
		sortName : "m_date",// 排序字段
		sortOrder : "desc",// 排序方式
		showColumns : true, // 是否显示所有的列
		toolbar : "#readToobar",// 工具按钮用哪个容器
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
			field : 'm_id',
			title : '信息ID'
		}, {
			field : 'm_title',
			title : '标题'
		}, {
			field : 'm_send_nickname',
			title : '发件人'
		}, {
			field : 'm_date',
			title : '发送时间'
		} ],
		onDblClickRow : function(row) {
			if (row != null) {
				$("#msgTitle").html(row.m_title);
				$("#msgContent").html(row.m_content);
				$("#msgDetail").modal({
					keyboard : true
				});
			}
		}
	});

	// 触发按钮点击事件，多条件模糊搜索
	$("#readSearchBtn").on("click", function() {
		$('#readTbl').bootstrapTable("refresh", {
			silent : true
		});
	});

	// 批量删除
	$("#deleteRead2").click(function() {
		var rows = $('#readTbl').bootstrapTable('getAllSelections');
		var json = [];
		for (var i = 0; i < rows.length; i++) {
			json.push(rows[i].m_id);
		}
		if (json == null || json == "") {
			alert("删除失败,您还未选择任何数据");
		} else {
			$.ajax({
				type : "post",
				url : "deleteUnread.do",
				contentType : "application/json",
				data : JSON.stringify(json),
				dataType : "json",
				success : function(data) {
					$('#readTbl').bootstrapTable("refresh", {
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
	
	//移动到重要信息
	$("#mvToImp2").click(function() {
		var rows = $('#readTbl').bootstrapTable('getAllSelections');
		var json = [];
		for (var i = 0; i < rows.length; i++) {
			json.push(rows[i].m_id);
		}
		if (json == null || json == "") {
			alert("操作失败,您还未选择任何数据");
		} else {
			$.ajax({
				type : "post",
				url : "moveToImp.do",
				contentType : "application/json",
				data : JSON.stringify(json),
				dataType : "json",
				success : function(data) {
					$('#readTbl').bootstrapTable("refresh", {
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
	
	//移动到垃圾箱
	$("#mvToTrash2").click(function() {
		var rows = $('#readTbl').bootstrapTable('getAllSelections');
		var json = [];
		for (var i = 0; i < rows.length; i++) {
			json.push(rows[i].m_id);
		}
		if (json == null || json == "") {
			alert("操作失败,您还未选择任何数据");
		} else {
			$.ajax({
				type : "post",
				url : "moveToTrash.do",
				contentType : "application/json",
				data : JSON.stringify(json),
				dataType : "json",
				success : function(data) {
					$('#readTbl').bootstrapTable("refresh", {
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
