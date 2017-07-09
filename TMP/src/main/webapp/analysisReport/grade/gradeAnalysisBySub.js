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
	// 2.清空表单按钮绑定
	dataTableClearButton("resetButton", "searchForm");
	function getHighChartsData(url, param) {
				$.ajax({
					url : url,
					type : 'post',
					data : param,
					dataType : 'json',
					success : function(data) {
						if (data.datas.data.clps == null
								|| data.datas.data.clps == "") {
							data.datas.data.clps = [ 0, 0, 0, 0, 0, 0 ];
						}
						if (data.datas.data.Chinasoft == null
								|| data.datas.data.Chinasoft == "") {
							data.datas.data.Chinasoft = [ 0, 0, 0, 0, 0, 0 ];
						}
						if (data.datas.data.Capgemini == null
								|| data.datas.data.Capgemini == "") {
							data.datas.data.Capgemini = [ 0, 0, 0, 0, 0, 0 ];
						}
						$('#container').highcharts(
								{
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
										text : gradeAnalysisSub,
										style : {
											fontSize : "25px",
											fontWeight : "bold",
										}
									},

									// 去除图表上的highcharts网址
									credits : {
										enabled : false
									},

									plotOptions : {
										column : {
											depth : 25
										}
									},

									yAxis : {
										min : 0,
										title : {
											text : amount,
										}
									},
									xAxis : {
										categories : [ '0-29(F)', '30-39(E)',
												'40-59(D)', '60-79(C)',
												'80-99(B)', '100-120(A)' ],
									},

									series : [ {
										name : 'CLPS',
										data : data.datas.data.clps,
										color:'#FF5511'

									}, {
										name : 'Chinasoft',
										data : data.datas.data.Chinasoft,
										color:'#FFFF00'
									}, {
										name : 'Capgemini',
										data : data.datas.data.Capgemini,
										color:'#00FF00'
									} ]

								});
					}
				});
	}

	// 初始化执行默认条件
	getHighChartsData("gradeBySub!getDataBySub.do", null);

	$('#searchButton').click(function() {
		var subject = $('#subject').val();
		var start_time = $('#start_time').val();
		var end_time = $('#end_time').val();
		var param = {
			"subject" : subject,
			"start_time" : start_time,
			"end_time" : end_time
		};
		console.log(param);
		getHighChartsData("gradeBySub!getDataBySub.do", param);
	})
});
