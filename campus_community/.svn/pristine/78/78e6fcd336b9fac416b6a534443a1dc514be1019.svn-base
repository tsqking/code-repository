$(function() {
	function newsQueryParams(params) { // 配置参数
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			offset : params.offset, // 页码
			sortname : params.sort, // 排序列名
			sortorder : params.order,// 排序规则
			pageWhere1 : $("#searchNewsTitle").val(),
			pageWhere2 : $("#searchNewsContent").val()
		};
		return temp;
	}

	$('#newsMngTbl').bootstrapTable({
		method : "post",// 请求方式（*）
		url : "bgListNews.do",// 请求后台的URL（*）
		queryParams : newsQueryParams,// 传递参数（*)
		uniqueId : "n_id",// 唯一标识
		pageSize : 10,// 每页的记录行数（*）
		pageList : [ 5, 10, 15, 20, 25, 30 ],// 可供选择的每页的行数（*）
		pageNumber : 1,// 初始化加载第一页，默认第一页
		pagination : true,// 是否显示分页（*）
		sidePagination : 'server',// 分页方式：client客户端分页，server服务端分页（*）
		sortName : "n_send_date",// 排序字段
		sortOrder : "desc",// 排序方式
		showColumns : true, // 是否显示所有的列
		toolbar : "#toolbar_news",// 工具按钮用哪个容器
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
			field : 'n_id',
			title : '新闻ID'
		}, {
			field : 'n_title',
			title : '新闻标题'
		}, {
			field : 'n_send_nickname',
			title : '新闻发布人'
		}, {
			field : 'n_send_date',
			title : '新闻发布时间'
		}, {
			field : 'n_update_nickname',
			title : '新闻更改人'
		}, {
			field : 'n_update_date',
			title : '新闻更改时间'
		}, {
			field : 'n_is_enable',
			title : '是否可用'
		} ],
		onDblClickRow : function(row) {
			if (row != null) {
				$("#updateNewsId").val(row.n_id);
				$("#updateNewsTitle").val(row.n_title);
				$("#updateNewsContent").val(row.n_content);
				$("#updateNewsEnable").val(row.n_is_enable);
				$("#update_news").modal({
					keyboard : true
				});
			}
		}
	});

	// 触发按钮点击事件，多条件模糊搜索
	$("#bgNewsSearchBtn").on("click", function() {
		$('#newsMngTbl').bootstrapTable("refresh", {
			silent : true
		});
	});

	// 添加
	$("#btn_addNews").click(function() {
		$("#add_news").modal({
			keyboard : true
		});
	});
	$("#addNewsBtn").click(function() {
		var json = {
			n_send_nickname : username,
			n_title : $("#addNewsTitle").val(),
			n_content : $("#addNewsContent").val(),
			n_is_enable : $("#addNewsEnable").val()
		}
		$.ajax({
			type : "post",
			url : "addNewsMng.do",
			contentType : "application/json",
			data : JSON.stringify(json),
			dataType : "json",
			success : function(data) {
				$('#newsMngTbl').bootstrapTable("refresh", {
					silent : true
				});
				$('#add_news').modal('hide');
				alert(data.msg);
			},
			error : function(data) {
				alert(data.msg);
			}
		});
	});

	// 修改
	$("#updateNewsBtn").click(function() {
		var json = {
			n_update_nickname : username,
			n_id : $("#updateNewsId").val(),
			n_title : $("#updateNewsTitle").val(),
			n_content : $("#updateNewsContent").val(),
			n_is_enable : $("#updateNewsEnable").val()
		}
		$.ajax({
			type : "post",
			url : "updateNewsMng.do",
			contentType : "application/json",
			data : JSON.stringify(json),
			dataType : "json",
			success : function(data) {
				$('#newsMngTbl').bootstrapTable("refresh", {
					silent : true
				});
				$('#update_news').modal('hide');
				alert(data.msg);
			},
			error : function(data) {
				alert(data.msg);
			}
		});
	});

	// 删除
	$("#btn_deleteNews").click(function() {
		var rows = $('#newsMngTbl').bootstrapTable('getAllSelections');
		var json = [];
		for (var i = 0; i < rows.length; i++) {
			json.push(rows[i].n_id);
		}
		if (json == null || json == "") {
			alert("删除失败,您还未选择任何数据");
		} else {
			$.ajax({
				type : "post",
				url : "mngDeleteNews.do",
				contentType : "application/json",
				data : JSON.stringify(json),
				dataType : "json",
				success : function(data) {
					$('#newsMngTbl').bootstrapTable("refresh", {
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