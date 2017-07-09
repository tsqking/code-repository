$(function() {
	var username = $.cookie("username");

	function acApplyQueryParams(params) { // 配置参数
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			offset : params.offset, // 页码
			sortname : params.sort, // 排序列名
			sortorder : params.order,// 排序规则
			pageWhere1 : $("#searchAcApplyId").val(),
			pageWhere2 : $("#searchAcApplyNickname").val()
		};
		return temp;
	}

	$('#applyActMngTbl').bootstrapTable({
		method : "post",// 请求方式（*）
		url : "listAcApply.do",// 请求后台的URL（*）
		queryParams : acApplyQueryParams,// 传递参数（*)
		uniqueId : "aa_id",// 唯一标识
		pageSize : 10,// 每页的记录行数（*）
		pageList : [ 5, 10, 15, 20, 25, 30 ],// 可供选择的每页的行数（*）
		pageNumber : 1,// 初始化加载第一页，默认第一页
		pagination : true,// 是否显示分页（*）
		sidePagination : 'server',// 分页方式：client客户端分页，server服务端分页（*）
		sortName : "aa_apply_date",// 排序字段
		sortOrder : "desc",// 排序方式
		showColumns : true, // 是否显示所有的列
		toolbar : false,// 工具按钮用哪个容器
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
			field : 'aa_id',
			title : '报名ID'
		}, {
			field : 'a_name',
			title : '报名活动名称'
		}, {
			field : 'aa_apply_ac_id',
			title : '报名活动ID'
		}, {
			field : 'aa_apply_nickname',
			title : '报名人'
		}, {
			field : 'aa_apply_date',
			title : '报名时间'
		} ],
	});

	// 触发按钮点击事件，多条件模糊搜索
	$("#bgAcApplySearchBtn").on("click", function() {
		$('#applyActMngTbl').bootstrapTable("refresh", {
			silent : true
		});
	});

});
