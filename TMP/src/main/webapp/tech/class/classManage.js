//搜索数据 (必须)
var searchData = [ { "name" : "more_data" , "value" : "my_value" } ];

$(function() {
	//下拉框初始化
	selectInitial("location",
			"../system/option!getOptionsByGPVal.do?value=LOCATION", "", false);
	selectInitial("direction",
			"../system/option!getOptionsByGPVal.do?value=DIRECTION", "",false);
	selectInitial("enable", "../system/option!getOptionsByGPVal.do?value=STATUS","",false);
	//搜索按钮初始化
	dataTableSearchButton("searchButton","classTable","searchForm",null);
	//清空按钮初始化
	dataTableClearButton("resetButton","searchForm");
	//表格控件初始化
	dataTable("classTable","../tech/class!getClassPage.do",
			[ [ 5, "desc" ] ], 
			[ {
				"mData" : "no",
				"sClass" : "center"
			}, {
				"mData" : "name",
				"sClass" : "center"
			}, {
				"mData" : "direction",
				"sClass" : "center"
			}, {
				"mData" : "location",
				"sClass" : "center"
			},{
				"mData" : "enable",
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
			}, ],
			[ 
			   {
					"aTargets" : [ 7 ],
					"mRender" : function(data, type, full) {
						var resOperator = '<a href="javascript:void(0);" data-toggle="tooltip" title=\"'+manageDetailTip+'\" onclick="detail(\''// menu3:详细 - 
							+ full.name +'\',\''+ full.id +'\')"><i class="fa fa-users"></i></a>&nbsp;&nbsp;&nbsp'
							+ '<a href="javascript:void(0);" onclick="edit('+ data+ ');" data-toggle=\"tooltip\" title=\"'+manageEditTip+'\"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;&nbsp;'
							+'<a href="javascript:void(0);" onclick="del('+ data+ ');" data-toggle=\"tooltip\" title=\"'+manageDelTip+'\"><i class="fa fa-trash"></i></a>&nbsp;&nbsp;&nbsp';
						return resOperator;
					}
			   }
			]
	);	
});
//添加技能
function add() {
	layer.open({
		//"创建班级"资源文件
		title : [ addNewClassTitle, 'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		area : [ '80%', '20%' ],
		offset: '1%',// 距离页面顶距离
		fix : false, // 不固定
		maxmin : true,
		content : '../tech/class!addClassPage.do'
	});
}

//删除班级
function del(id){
	layer.open({
		title: [
			delClassTitle,
		 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    content: delClassContent,
	    btn: [confirm,cancel],
	    shadeClose: false,
	    yes: function(){
	    	$.ajax({
				url:"../tech/class!deleteClass.do",
				data: {"classVo.id":id},
				dataType:"json",
				type:"get",
				success:function(data){
					if(data.success=='true'){
						layer.msg(delClassSuccess);
						$("#classTable").DataTable().draw();
					}else{
						if(data.message=='-1'){//被使用中
							layer.alert(delClassFail,{title:feedback});
						}else{
							layer.msg(data.message);
						}
					}
				}
			});
	    }
	});
}
//更新技能
function edit(id){
	var h = document.documentElement.clientHeight;
	layer.open({
		//"班级信息修改页面"资源文件
		title: [
				editClassWindowTitle,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    area: [ '80%' , '20%'],
	    offset: '1%',// 距离页面顶距离
	    fix: false, //不固定
	    maxmin: true,
	    content: "../tech/class!toEditClassPage.do?classVo.id="+id,
	});
}
//添加班级学生页面
function detail(name,id) {
	//iframe窗
	var index = layer.open({
		title: [
				name,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
		type: 2,
		area: ['100%', '100%'],
		fix: false, //不固定
		maxmin: true,
		content: '../tech/class!toClassStudentPage.do?classVo.id='+id
	});	
	layer.full(index);
}
