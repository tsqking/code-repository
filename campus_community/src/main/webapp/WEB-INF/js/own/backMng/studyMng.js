$(function() {

	function studyMngQueryParams(params) { // 配置参数
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			offset : params.offset, // 页码
			sortname : params.sort, // 排序列名
			sortorder : params.order,// 排序规则
			pageWhere1 : $("#searchStuId").val(),
			pageWhere2 : $("#searchStuTitle").val()
		};
		return temp;
	}

	$('#studyTblMng').bootstrapTable({
		method : "post",// 请求方式（*）
		url : "listStudyMng.do",// 请求后台的URL（*）
		queryParams : studyMngQueryParams,// 传递参数（*)
		uniqueId : "s_id",// 唯一标识
		pageSize : 10,// 每页的记录行数（*）
		pageList : [ 5, 10, 15, 20, 25, 30 ],// 可供选择的每页的行数（*）
		pageNumber : 1,// 初始化加载第一页，默认第一页
		pagination : true,// 是否显示分页（*）
		sidePagination : 'server',// 分页方式：client客户端分页，server服务端分页（*）
		sortName : "s_date",// 排序字段
		sortOrder : "desc",// 排序方式
		showColumns : true, // 是否显示所有的列
		toolbar : "#stuToolbar",// 工具按钮用哪个容器
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
			field : 's_id',
			title : '文章ID'
		}, {
			field : 's_title',
			title : '文章标题'
		}, {
			field : 's_nickname',
			title : '发布人'
		}, {
			field : 's_date',
			title : '发布时间'
		}, {
			field : 's_check_nickname',
			title : '审核人'
		}, {
			field : 's_check_date',
			title : '审核时间'
		}, {
			field : 's_is_enable',
			title : '是否可用'
		} ],
		onDblClickRow : function(row) {
			$("#update_study").modal({
				keyboard : true
			});
			$("#updateStudyId").val(row.s_id);
			$("#updateStudyTitle").val(row.s_title);
			$("#updateStudyContent").val(row.s_content);
			$("#studyEnable").val(row.s_is_enable);
		}
	});
	// 多条件模糊查询
	$("#searchStuBtn").click(function() {
		$('#studyTblMng').bootstrapTable("refresh", {
			silent : true
		});
	});

	// 发表文章
	$("#publishMngBtn").click(function() {
		$("#publishStudy").modal({
			keyboard : true
		});
	});
	$("#publishBtn").click(function() {
		var json = {
			s_title : $("#inputTitle").val(),
			s_content : $("#inputContent").val(),
			s_nickname : $.cookie("username")
		}
		$.ajax({
			type : "post",
			url : "publishStudy.do",
			contentType : "application/json",
			data : JSON.stringify(json),
			dataType : "json",
			success : function(data) {
				$('#studyTblMng').bootstrapTable("refresh", {
					silent : true
				});
				alert(data.msg);
				$("#publishStudy").modal("hide");
			},
			error : function(data) {
				alert(data.msg);
			}
		});
	});

	// 修改
	$("#editStudyBtn").click(function() {
		var json = {
			s_check_nickname : username,
			s_id : $("#updateStudyId").val(),
			s_title : $("#updateStudyTitle").val(),
			s_content : $("#updateStudyContent").val(),
			s_is_enable : $("#studyEnable").val()
		}
		$.ajax({
			type : "post",
			url : "updateStudyMng.do",
			contentType : "application/json",
			data : JSON.stringify(json),
			dataType : "json",
			success : function(data) {
				$('#studyTblMng').bootstrapTable("refresh", {
					silent : true
				});
				$('#update_study').modal('hide');
				alert(data.msg);
			},
			error : function(data) {
				alert(data.msg);
			}
		});
	});
	
	//批量删除
	// 删除
	$("#btn_deleteStudy").click(function() {
		var rows = $('#studyTblMng').bootstrapTable('getAllSelections');
		var json = [];
		for (var i = 0; i < rows.length; i++) {
			json.push(rows[i].s_id);
		}
		if (json == null || json == "") {
			alert("删除失败,您还未选择任何数据");
		} else {
			$.ajax({
				type : "post",
				url : "deleteStudyMng.do",
				contentType : "application/json",
				data : JSON.stringify(json),
				dataType : "json",
				success : function(data) {
					$('#studyTblMng').bootstrapTable("refresh", {
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