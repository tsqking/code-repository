$(function() {
	var btTable;
	// 初始化时间选择控件
	dateInitial([ {
		"id" : "start_time",
		"type" : "3"
	} ]);
	dateInitial([ {
		"id" : "end_time",
		"type" : "3"
	} ]);
	// 下拉框初始化
	selectInitial("subject",
			"../system/option!getOptionsByGPVal.do?value=subject_summary",
			"JAVA", false);
	// 清空表单按钮绑定
	dataTableClearButton("resetButton", "searchForm");

	// 获得表头上男女生人数和平均考试年龄
	function getHeadData(param) {
		$.ajax({
			url : "summary!getHead.do",
			type : 'post',
			data : param,
			dataType : 'json',
			success : function(data) {
				if (data.datas.headData.examSub == "5") {
					data.datas.headData.examSub = "Testing"
				}
				$("#maleNum").html(data.datas.headData.maleNum);
				$("#femaleNum").html(data.datas.headData.femaleNum);
				$("#avgAge").html(data.datas.headData.examAvgAge);
				$("#subSpan").html(data.datas.headData.examSub);
			}
		});
	}
	var defaultParam = {
		"subject" : "JAVA"
	}
	getHeadData(defaultParam);

	btTable = BtTable("summaryTbl", "summary!getData.do", "sumToolbar", [ {
		title : chioseLogicAvgScore,
		field : 'chioseLogicAvgScore',
		align : 'center',
		valign : 'middle',
		visible : false
	}, {
		title : chioseTechAvgScore,
		field : 'chioseTechAvgScore',
		align : 'center',
		valign : 'middle',
		visible : false
	}, {
		title : chioseAvg,
		field : 'chioseAvg',
		align : 'center',
		valign : 'middle',
	}, {
		title : chioseQuesCount,
		field : 'chioseQuesCount',
		align : 'center',
		valign : 'middle',
	}, {
		title : chioseTotalScore,
		field : 'chioseTotalScore',
		align : 'center',
		valign : 'middle',
	}, {
		title : chioseFinishRate,
		field : 'chioseFinishRate',
		align : 'center',
		valign : 'middle'
	}, {
		title : fillAvgScore,
		field : 'fillAvgScore',
		align : 'center',
		valign : 'middle'
	}, {
		title : fillQuesCount,
		field : 'fillQuesCount',
		align : 'center',
		valign : 'middle'
	}, {
		title : fillTotalScore,
		field : 'fillTotalScore',
		align : 'center',
		valign : 'middle'
	}, {
		title : fillFinishRate,
		field : 'fillFinishRate',
		align : 'center',
		valign : 'middle'
	}, {
		title : programAvgScore,
		field : 'programAvgScore',
		align : 'center',
		valign : 'middle'
	}, {
		title : programQuesCount,
		field : 'programQuesCount',
		align : 'center',
		valign : 'middle'
	}, {
		title : programTotalScore,
		field : 'programTotalScore',
		align : 'center',
		valign : 'middle'
	}, {
		title : programFinishRate,
		field : 'programFinishRate',
		align : 'center',
		valign : 'middle'
	}, {
		title : tfAvgScore,
		field : 'tfAvgScore',
		align : 'center',
		valign : 'middle'
	}, {
		title : tfQuesCount,
		field : 'tfQuesCount',
		align : 'center',
		valign : 'middle'
	}, {
		title : tfTotalScore,
		field : 'tfTotalScore',
		align : 'center',
		valign : 'middle'
	}, {
		title : tfFinishRate,
		field : 'tfFinishRate',
		align : 'center',
		valign : 'middle'
	}, {
		title : shortAvgScore,
		field : 'shortAvgScore',
		align : 'center',
		valign : 'middle'
	}, {
		title : shortQuesCount,
		field : 'shortQuesCount',
		align : 'center',
		valign : 'middle'
	}, {
		title : shortTotalScore,
		field : 'shortTotalScore',
		align : 'center',
		valign : 'middle'
	}, {
		title : shortFinishRate,
		field : 'shortFinishRate',
		align : 'center',
		valign : 'middle'
	}, {
		title : examAvgDuration,
		field : 'examAvgDuration',
		align : 'center',
		valign : 'middle'
	}, {
		title : formularyExamDuration,
		field : 'formularyExamDuration',
		align : 'center',
		valign : 'middle'
	}, {
		title : absentRate,
		field : 'absentRate',
		align : 'center',
		valign : 'middle'
	} ], 'absentRate', 'asc', null);
	// 初始化搜索按钮
	btTable.searchDate('searchForm');
	btTable.Init();

	// 初始化搜索按钮
	$("#searchButton").click(function() {
		var param = {
			"subject" : $('#subject').val(),
			"start_time" : $('#start_time').val(),
			"end_time" : $('#end_time').val()
		};
		getHeadData(param);
		btTable.searchDate('searchForm');
	});
	// 初始化清空按钮
	$("#resetButton").click(function() {
		btTable.cleanDate('searchForm');
	});
});
