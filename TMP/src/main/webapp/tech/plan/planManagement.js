//---------------------------js文本全局变量----------------------------------
//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 
//搜索数据 (必须)
var searchData = [ { "name" : "more_data" , "value" : "my_value" } ];



//-------------------------页面加载完成后执行方法-------------------------------
//页面加载完成执行
$(function() {
	//1.初始化方法
	initial();
});



//------------------------------js方法-------------------------------------
//初始化方法
function initial() {
	//选择框初始化
	selectInitial("course_id","../tech/plan!getAllCourseName_Id.do","",false);
	selectInitial("class_id","../tech/plan!getAllClassName_Id.do","",false);
	//时间日期控件初始化
	dateInitial([ {"id":"create_time","type":"2"},{"id":"update_time","type":"2"},{"id":"start_time","type":"2","dir":"r"},{"id":"end_time","type":"2","dir":"r"}]);	
	//数据表格控件初始化
	//1.搜索按钮初始化
	dataTableSearchButton("searchButton","example1","searchForm",null);
	//2.清空按钮初始化
	dataTableClearButton("resetButton","searchForm");
	//3.表格控件初始化
	dataTable("example1","../tech/plan!getPlanPage.do",
			[ [ 7, "desc" ] ], 
			[ {
				"mData" : "course_id_name",
				"sClass" : "center"
			}, {
				"mData" : "class_id_name",
				"sClass" : "center"
			}, {
				"mData" : "start_time",
				"sClass" : "center"
			}, {
				"mData" : "end_time",
				"sClass" : "center"
			}, {
				"mData" : "create_time",
				"sClass" : "center"
			}, {
				"mData" : "create_person",
				"sClass" : "center"
			}, {
				"mData" : "update_time",
				"sClass" : "center"
			}, {
				"mData" : "update_person",
				"sClass" : "center"
			}, {
				"mData" : "id",
				"sClass" : "center"
			}],
			 [ 
			   {
					"aTargets" : [ 8 ],
					//"mData" : "操作",
					"mRender" : function(data, type, full) {
						var detail = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+plan1+'\" onclick="detail(\''// plan1:详细 - 
							+ full.id +'\',\'' + full.course_id + '\',\''+full.class_id+'\',\''+full.start_time+'\',\''+full.end_time+'\',\''+full.location+'\')"><i class="fa fa-reorder"></i></a>';
						var edit = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+plan2+'\" onclick="edit(\'' // plan2:编辑 - 
							+ full.id +'\',\'' + full.course_id + '\',\''+full.class_id+'\',\''+full.start_time+'\',\''+full.end_time+'\',\''+full.location+'\')"><i class="fa fa-edit"></i></a>';
						var del = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+plan3+'\" onclick="del(\''// plan3:删除 - 
							+ full.id +'\')"><i class="fa fa-trash"></i></a>';
						return detail+"&nbsp;&nbsp;&nbsp;"+edit+"&nbsp;&nbsp;&nbsp;"+del;
					}
			   }
			]
	);
	
	//添加计划
	$('#addNewPlan').click(function(){
		//iframe窗
		var index=layer.open({
			title : titleInfo1,
			offset: '0px',// 距离页面顶距离
			type: 2,
		    area: ['100%', '100%'],
		    fix: false, //不固定
		    maxmin: true,
		    content: '../tech/plan!toPlanAdd.do?'
		});
		layer.full(index);
	});
}

//编辑信息方法
function edit(id,course_id,class_id,start_time,end_time,location){ 
	//iframe窗
	var index=layer.open({
		title : titleInfo2,
		offset: '0px',// 距离页面顶距离
		type: 2,
	    area: ['100%', '100%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/plan!toPlanEdit.do?id='+id+'&course_id='+course_id+"&class_id="+class_id+"&start_time="+start_time+"&end_time="+end_time+"&location="+location
	});
	layer.full(index);
}

//查看详细信息方法
function detail(id,course_id,class_id,start_time,end_time,location){ 
	//iframe窗
	var index=layer.open({
		title : titleInfo3,
		offset: '0px',// 距离页面顶距离
		type: 2,
	    area: ['100%', '100%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/plan!toPlanDetail.do?id='+id+'&course_id='+course_id+"&class_id="+class_id+"&start_time="+start_time+"&end_time="+end_time+"&location="+location
	});
	layer.full(index);
}

//删除方法
function del(id){
	layer.confirm(plan4, {// plan4:确定要删除此教学计划吗? - 
	    btn: [systemOK,systemCancel] //按钮// menu7:确定 - menu8:取消 - 
	}, function(){
		layer.close(index);
		//确定
		$.ajax({
	        type : "POST",
	        url : "../tech/plan!planDel.do",
	        data : "id="+id,
	        success:function(data){
	             //成功的回调函数
	        	if(data.success=='true'){
	        		layer.msg(plan5);		// plan5:删除成功! - 
	        		$("#example1").DataTable().draw();
	        	}else{
	        		layer.msg(plan6);		// plan6:删除失败! - 
	        	}
	        }
		});
	}, function(){
	    ;
	});
}
