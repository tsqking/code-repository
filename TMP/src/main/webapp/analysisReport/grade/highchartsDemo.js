
//-------------------------页面加载完成后执行方法-------------------------------
//页面加载完成执行
$(function() {
	//1.初始化方法
	initial();
});

function initial() {
	//搜索按钮初始化
	dataTableSearchButton("searchButton","classTable","searchForm",null);
	//清空按钮初始化
	dataTableClearButton("resetButton","searchForm");
    $('#container').highcharts({
        chart: {
            type: 'column',
            margin: 70,
            options3d: {
                enabled: true,
                alpha: 0,
                beta: 5,
                depth: 20
            }
        },
        title: {
            text: '各成绩段人数分析'
        },
        //去除图表上的highcharts网址
        credits: {
            enabled: false
        },
//        subtitle: {
//            text: '请注意值为 0 和 null 的区别'
//        },
        plotOptions: {
            column: {
                depth: 25
            }
        },
        xAxis: {
        	title: {
                text: "分数段"
            },
            categories: ['0-30(F)','30-40(E)','40-60(D)','60-80(C)','80-100(B)','100-120(A)']
        },
        yAxis: {
            title: {
                text: "人数"
            }
        },
        series: [{
            name: 'Capgemini',
            color : "#7cb5ec", 
            data:[2, 3, null, 2, 0, 5]
        },
        {
            name: 'Chinasoft',
            color : "#91e8e1" ,
            data:[12, 6, 1, 4, 0, 3]
        },
        {
            name: 'CLPS',
            color : "#f7a35c" ,
            data:[5, 2, 1, 2, 6, 5]
        }
        ]
    });
}

////------------------------------js方法-------------------------------------
//初始化方法
/*function initial() {
	//1.搜索按钮绑定
 	dataTableSearchButton("searchButton","example1","searchForm",null);
 	//时间日期控件初始化
	dateInitial([ {"id":"update_time","type":"2"}]);
 	//modal初始化
    $('#batchAddModel').modal({backdrop: 'static', keyboard: false});
    $('#batchAddModel').modal('hide');
     var dataArray=new Array();
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
        		 title: {
        		        text: '各成绩段人数分析',
        		        //subtext:'在此测试',
        		        x:'center',
        		        y:'top',
        		        textStyle:{
        		        	fontSize:25,
        		        	fontWeight:'bold', 
        		        } 
        		        //textAlign:'left'
        		    },
        		    tooltip: {
        		    	formatter: '{a}<br/> {b0}: {c0}%<br />'
        		    },
        		     legend: {
        		        data:['销量'],
        		        top:'5%'
        		    }, 
        		    grid:{
        		    	y2:140
        		    },
        		    xAxis: [
        		        {
        		    	 type : 'category',
        		    	 position: 'bottom',
         	            boundaryGap: true,
         	           axisLabel : {
       	                show:true,
       	                interval: '0',    // {number}
       	                rotate: 45,
       	                margin: 8,
       	                //formatter: '{value}月',
       	                 textStyle: {
       	                    color: 'blue',
       	                    fontFamily: 'sans-serif',
       	                    fontSize: 15,
       	                    fontStyle: 'italic',
       	                    fontWeight: 'bold'
       	                } 
       	            },
        		        data: ["0-9","10-19","20-29","30-39","40-49","50-59",
        		               "60-69","70-79","80-89","90-99","100-109","110-120"]
        		        }
        		        ],
        		    yAxis:[
        		             {
        		            	 min:0,
        		            	 max:100,
        		            	 splitNumber:10, 
        		            	 axisLabel : {
        		       	                show:true,
        		       	                interval: 0,    // {number}
        		       	                formatter: '{value}%',
        		       	                 textStyle: {
        		       	                    color: 'blue',
        		       	                    fontFamily: 'sans-serif',
        		       	                    fontSize: 15,
        		       	                    fontStyle: 'italic',
        		       	                    fontWeight: 'bold'
        		       	                } 
        		       	            },
        		             }
        		         ],
        		  series: [{
        		        name: '成绩所占比例',
        		        type: 'bar',
        		        data: [],
        		        itemStyle: {
                            normal: {
                                color: function(params) {
                                    var colorList = [
                                      '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                                       '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                                       '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                                    ];
                                    return colorList[params.dataIndex]
                                }, 
                                //color:'#60C0DD',
                                label: {
                                    show: true,
                                    position: 'top',
                                    //formatter: '{c}'
                                    formatter: '{c}%'
                                }
                            }
                        },
                        barWidth:30,
        		    }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        $.ajax({
        	url:'grade!getGradeAnalysisReport.do',
        	type:'post',
        	dataType:'json',
        	success:function(data){
        		if(data.success=="true"){
        			var object=data.datas.data;
        			var range1=object.range1;
        			var range2=object.range2;
        			var range3=object.range3;
        			var range4=object.range4;
        			var range5=object.range5;
        			var range6=object.range6;
        			var range7=object.range7;
        			var range8=object.range8;
        			var range9=object.range9;
        			var range10=object.range10;
        			var range11=object.range11;
        			var range12=object.range12;
        			dataArray.push(range1);
        			dataArray.push(range2);
        			dataArray.push(range3);
        			dataArray.push(range4);
        			dataArray.push(range5);
        			dataArray.push(range6);
        			dataArray.push(range7);
        			dataArray.push(range8);
        			dataArray.push(range9);
        			dataArray.push(range10);
        			dataArray.push(range11);
        			dataArray.push(range12);
        			console.log("dataArray:"+dataArray);
        			myChart.setOption({        //加载数据图表
                        series: [{
                            data: dataArray
                        }]
                    });
        			
        		}
        		console.log(data);
        	}
         })
}*/