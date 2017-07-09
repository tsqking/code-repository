var inputWbid = $("#inputWbid");
var wbEnable = $("#wbEnable");
var username = $.cookie('username');

function weiboQueryParams(params) { // 配置参数
	var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		limit : params.limit, // 页面大小
		offset : params.offset, // 页码
		sortname : params.sort, // 排序列名
		sortorder : params.order,// 排序规则
		pageWhere1 : $("#searchWeiboSender").val(),
		pageWhere2 : $("#searchWeiboContent").val()
	};
	return temp;
}

$('#weiboMngTbl').bootstrapTable({
	method : "post",// 请求方式（*）
	url : "listWeibo.do",// 请求后台的URL（*）
	queryParams : weiboQueryParams,// 传递参数（*)
	uniqueId : "w_id",// 唯一标识
	pageList : [ 5, 10, 15, 20, 25, 30 ],// 可供选择的每页的行数（*）
	pageNumber : 1,// 初始化加载第一页，默认第一页
	pagination : true,// 是否显示分页（*）
	pageSize : 10,// 每页的记录行数（*）
	sidePagination : 'server',// 分页方式：client客户端分页，server服务端分页（*）
	sortName : "w_date",// 排序字段
	sortOrder : "desc",// 排序方式
	showColumns : true, // 是否显示所有的列
	toolbar : "#toolbar_Weibo",// 工具按钮用哪个容器
	clickToSelect : true,// 是否启用点击选中行
	cache : false,// 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	striped : false,// 是否显示行间隔色
	showRefresh : true, // 是否显示刷新按钮
	showToggle : true,// 是否显示详细视图和列表视图的切换按钮
	cardView : false,// 是否显示详细视图
	detailView : false,// 是否显示父子表
	showPaginationSwitch : true,// 影藏显示分页
	paginationLoop : false,// 是否允许循环分页
	columns : [ {
		checkbox : true
	}, {
		field : 'w_id',
		title : '微博ID'
	}, {
		field : 'w_nickname',
		title : '发布人'
	}, {
		field : 'w_date',
		title : '发布日期'
	}, {
		field : 'check_nickname',
		title : '审核人'
	}, {
		field : 'w_check_date',
		title : '审核时间'
	}, {
		field : 'w_topic',
		title : '话题'
	}, {
		field : 'w_praise_num',
		title : '点赞数量'
	}, {
		field : 'w_forward_num',
		title : '转发数量'
	}, {
		field : 'w_img',
		title : '附加图片'
	}, {
		field : 'w_is_enable',
		title : '是否可用'
	} ],
	onDblClickRow : function(row) {
		if (row != null) {
			$("#inputWbid").val(row.w_id);
			$("#inputWbContent").val(row.w_content);
			$("#wbEnable").val(row.w_is_enable);
			$('#update_weibo').modal({
				keyboard : true
			});
		}
	}
});

// 触发按钮点击事件，多条件模糊搜索
$("#bgWeiboSearchBtn").on("click", function() {
	$('#weiboMngTbl').bootstrapTable("refresh", {
		silent : true
	});
});

// 修改
$("#editWeiboBtn").click(function() {
	var json = {
		check_nickname : username,
		w_id : inputWbid.val(),
		w_is_enable : wbEnable.val(),
	}
	$.ajax({
		type : "post",
		url : "editWeibo.do",
		contentType : "application/json",
		data : JSON.stringify(json),
		dataType : "json",
		success : function(data) {
			$('#weiboMngTbl').bootstrapTable("refresh", {
				silent : true
			});
			$('#update_weibo').modal('hide');
			alert(data.msg);
		},
		error : function(data) {
			alert(data.msg);
		}
	});
});

// 删除
$("#btn_deleteWeibo").click(function() {
	var rows = $('#weiboMngTbl').bootstrapTable('getAllSelections');
	var json = [];
	for (var i = 0; i < rows.length; i++) {
		json.push(rows[i].w_id);
	}
	if (json == null || json == "") {
		alert("删除失败,您还未选择任何数据");
	} else {
		$.ajax({
			type : "post",
			url : "deleteWeibo.do",
			contentType : "application/json",
			data : JSON.stringify(json),
			dataType : "json",
			success : function(data) {
				$('#weiboMngTbl').bootstrapTable("refresh", {
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
