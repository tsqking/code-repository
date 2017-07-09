$(function() {
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
			"../system/option!getOptionsByGPVal.do?value=DIRECTION", "", false);
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
				if (data.datas.data == null || data.datas.data == "") {
					data.datas.data = [ 0, 0, 0, 0, 0, 0, 0 ];
				}
				Highcharts.chart('ageCharts', {
					chart : {
						type : 'column',
						options3d : {
							enabled : true,
							alpha : 10,
							beta : 25,
							depth : 70
						}
					},
					title : {
						text : ageDis
					},
					// 去除图表上的highcharts网址
					credits : {
						enabled : false
					},
					plotOptions : {
						column : {
							depth : 60
						}
					},
					xAxis : {
						categories : [ '<21', '21', '22', '23', '24', '25',
								'>25' ],
						title : {
							text : ageDis
						}
					},
					yAxis : {
						min : 0,
						title : {
							text : ageRate
						},
						labels : {
							formatter : function() {
								return this.value + '%';
							}
						}
					},
					series : [ {
						name : numRate,
						color : '#0066FF',
						data : data.datas.data
					} ],
					tooltip : {
						formatter : function() {
							return numRate + '<b>' + this.y + '% </b>';
						}
					},
				});
			}
		});
	}
	// 初始化执行默认条件
	getHighChartsData("age!getAgeAnalysisReport.do", null);

	$('#searchButton').click(function() {
		var subject = $('#subject').val();
		var company = $('#company').val();
		var start_time = $('#start_time').val();
		var end_time = $('#end_time').val();
		var param = {
			"subject" : subject,
			"company" : company,
			"start_time" : start_time,
			"end_time" : end_time
		};
		console.log(param);
		getHighChartsData("age!getAgeAnalysisReport.do", param);
	})
});