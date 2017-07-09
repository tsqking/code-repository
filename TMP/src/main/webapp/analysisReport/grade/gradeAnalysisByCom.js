$(function() {
	// 时间日期控件初始化
	dateInitial([ {
		"id" : "start_time",
		"type" : "3"
	} ]);
	dateInitial([ {
		"id" : "end_time",
		"type" : "3"
	} ]);
	// 下拉框初始化
	selectInitial("company",
			"../system/option!getOptionsByGPVal.do?value=company", "", false);
	// 2.清空表单按钮绑定
	dataTableClearButton("resetButton", "searchForm");

	function getHighChartsData(url, param) {
		$.ajax({
			url : url,
			type : 'post',
			data : param,
			dataType : 'json',
			success : function(data) {
				if (data.datas.subList == null || data.datas.subList == "") {
					data.datas.subList = [ "JAVA", "Mainfram", "Testing" ];
				}
				if (data.datas.rangeList1 == null
						|| data.datas.rangeList1 == "") {
					data.datas.rangeList1 = [ 0, 0, 0 ];
				}
				if (data.datas.rangeList2 == null
						|| data.datas.rangeList2 == "") {
					data.datas.rangeList2 = [ 0, 0, 0 ];
				}
				if (data.datas.rangeList3 == null
						|| data.datas.rangeList3 == "") {
					data.datas.rangeList3 = [ 0, 0, 0 ];
				}
				if (data.datas.rangeList4 == null
						|| data.datas.rangeList4 == "") {
					data.datas.rangeList4 = [ 0, 0, 0 ];
				}
				if (data.datas.rangeList5 == null
						|| data.datas.rangeList5 == "") {
					data.datas.rangeList5 = [ 0, 0, 0 ];
				}
				if (data.datas.rangeList6 == null
						|| data.datas.rangeList6 == "") {
					data.datas.rangeList6 = [ 0, 0, 0 ];
				}
				$('#container').highcharts({
					chart : {
						type : 'column',
						margin : 50,
						options3d : {
							enabled : true,
							alpha : 5,
							beta : 10,
							depth : 60,
							viewDistance : 25
						}
					},
					title : {
						text : gradeAnalysisCom,
						style : {
							fontSize : "25px",
							fontWeight : "bold",
						}
					},
					// 去除图表上的highcharts网址
					credits : {
						enabled : false
					},
					yAxis : {
						title : {
							text : amount,
						}
					},
					xAxis : {
						categories : data.datas.subList,
					},
					series : [ {
						name : '0-29(F)',
						data : data.datas.rangeList1,
						color : '#FF5511'
					}, {
						name : '30-39(E)',
						data : data.datas.rangeList2,
						color : '#FFFF00'
					}, {
						name : '40-59(D)',
						data : data.datas.rangeList3,
						color : '#00FF00'
					}, {
						name : '60-79(C)',
						data : data.datas.rangeList4,
						color : '#00FFFF'
					}, {
						name : '80-99(B)',
						data : data.datas.rangeList5,
						color : '#0000FF'
					}, {
						name : '100-120(A)',
						data : data.datas.rangeList6,
						color : '#9900FF'
					} ]

				});
			}
		});
	}

	// 初始化执行默认条件
	getHighChartsData("gradeByCom!getDataByCom.do", null);

	$('#searchButton').click(function() {
		var company = $('#company').val();
		var start_time = $('#start_time').val();
		var end_time = $('#end_time').val();
		var param = {
			"company" : company,
			"start_time" : start_time,
			"end_time" : end_time
		};
		console.log(param);
		getHighChartsData("gradeByCom!getDataByCom.do", param);
	})
});
