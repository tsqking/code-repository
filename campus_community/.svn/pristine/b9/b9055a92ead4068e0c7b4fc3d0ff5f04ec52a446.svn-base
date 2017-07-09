$(function() {
	var username = $.cookie("username");

	function goodsApplyQueryParams(params) { // 配置参数
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			offset : params.offset, // 页码
			sortname : params.sort, // 排序列名
			sortorder : params.order,// 排序规则
			pageWhere1 : $("#searchGoodsApplyId").val(),
			pageWhere2 : $("#searchGoodsApplyNickname").val()
		};
		return temp;
	}

	$('#MarketApplyMngTbl').bootstrapTable({
		method : "post",// 请求方式（*）
		url : "listGoodsApply.do",// 请求后台的URL（*）
		queryParams : goodsApplyQueryParams,// 传递参数（*)
		uniqueId : "ma_id",// 唯一标识
		pageSize : 10,// 每页的记录行数（*）
		pageList : [ 5, 10, 15, 20, 25, 30 ],// 可供选择的每页的行数（*）
		pageNumber : 1,// 初始化加载第一页，默认第一页
		pagination : true,// 是否显示分页（*）
		sidePagination : 'server',// 分页方式：client客户端分页，server服务端分页（*）
		sortName : "ma_apply_date",// 排序字段
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
			field : 'ma_id',
			title : '申请ID'
		}, {
			field : 'ma_market_id',
			title : '申请商品ID'
		}, {
			field : 'm_name',
			title : '商品名称'
		}, {
			field : 'ma_nickname',
			title : '商品申请人'
		}, {
			field : 'ma_apply_date',
			title : '申请时间'
		}, {
			field : 'ma_phone',
			title : '联系方式'
		} ],
	});

	// 触发按钮点击事件，多条件模糊搜索
	$("#bgGoodsApplySearchBtn").on("click", function() {
		$('#MarketApplyMngTbl').bootstrapTable("refresh", {
			silent : true
		});
	});

});
