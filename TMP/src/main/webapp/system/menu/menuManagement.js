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
	selectInitial("searchMenuLevel","../system/option!getOptionsByGPVal.do?value=MENU_LEVEL","",false);
	selectInitial("searchParentMenu","../system/menu!getMenuByLevel.do?level=1","",false);
	selectInitial("searchMenuStatus","../system/option!getOptionsByGPVal.do?value=STATUS","",false);
	//数据表格控件初始化
	//1.搜索按钮初始化
	dataTableSearchButton("searchButton","example1","searchForm",null);
	//2.清空按钮初始化
	dataTableClearButton("resetButton","searchForm");
	//3.表格控件初始化
	dataTable("example1","../system/menu!getMenuPage.do",
			[ [ 4, "asc" ] ], 
			[ {
				"mData" : "name",
				"sClass" : "center"
			}, {
				"mData" : "description",
				"sClass" : "center"
			}, {
				"mData" : "url",
				"sClass" : "center"
			}, {
				"mData" : "level",
				"sClass" : "center"
			}, {
				"mData" : "parent_id",
				"sClass" : "center"
			}, {
				"mData" : "order",
				"sClass" : "center"
			}, {
				"mData" : "enable_name",
				"sClass" : "center"
			}, {
				"mData" : "id",
				"sClass" : "center"
			}, ],
			 [ 
			   {
					"aTargets" : [ 7 ],
					//"mData" : "操作",
					"mRender" : function(data, type, full) {
						var detail = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+menu3+'" onclick="detail(\''// menu3:详细 - 
							+ full.id +'\')"><i class="fa fa-reorder"></i></a>';
						var update = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+menu4+'" onclick="update(\''// menu4:编辑 - 
							+ full.id + '\')"><i class="fa fa-edit"></i></a>';
						var del = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+menu5+'" onclick="del(\''// menu5:删除 - 
							+ full.id + '\',\''+full.name+ '\')"><i class="fa fa-trash"></i></a>';
						var add = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+menu6+'" onclick="add(\''// menu6:添加子菜单 - 
							+ full.id + '\',\''+full.level+'\')"><i class="fa fa-plus"></i></a>';
						return detail+"&nbsp;&nbsp;&nbsp;"+update+"&nbsp;&nbsp;&nbsp;"+add+"&nbsp;&nbsp;&nbsp;"+del;
					}
			   },
			   {
					"aTargets" : [ 6 ],
					"mRender" : function(data, type, full) {
						if(full.enable=="T"){
							return '<span class="label label-success">'+data+'</span>';
						}else if(full.enable=="F"){
							return '<span class="label label-danger">'+data+'</span>';
						}
						return "";
					}
			    }
			]
	);
	
}

//详细信息方法
function detail(id){ 
	//iframe窗
	layer.open({
		title : title1,
		offset: '5%',// 距离页面顶距离
		type: 2,
	    area: ['1000px', '557px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../system/menu!toMenuDetail.do?id='+id
	});
}

//更新编辑方法
function update(id,level){
	//iframe窗
	layer.open({
		title : title2,
		offset: '5%',// 距离页面顶距离
		type: 2,
		area: ['1000px', '557px'],
		fix: false, //不固定
		maxmin: true,
		content: '../system/menu!toMenuUpdate.do?id='+id
	});	
}

//删除方法
function del(id,name){
	layer.confirm(menu14+" "+name+" "+menu15, {// menu14:确定要级联删除 - menu15:菜单吗?
	    btn: [systemOK,systemCancel] //按钮// menu7:确定 - menu8:取消 - 
	}, function(){
		layer.close(index);
		//确定
		$.ajax({
	        type : "POST",
	        url : "../system/menu!deleteMenu.do",
	        data : "id="+id,
	        success:function(data){
	             //成功的回调函数
	        	if(data.success=='true'){
	        		if(data.message=="0000"){
	        			layer.alert(menu9,{btn:systemOK,title :systemInfo});// menu9:删除成功! - 
	        			$("#example1").DataTable().draw();
	        		}else if(data.message=="1111"){
	        			layer.alert(menu99,{btn:systemOK,title :systemInfo});// menu9:已被引用关联，不能删除! - 
	        		}
	        	}else{
	        		layer.alert(menu10,{btn:systemOK,title :systemInfo});// menu10:删除失败! - 
	        	}
	        }
		});
	}, function(){
	    ;
	});
}

//添加方法
function add(id,level){
	if((level*1)>=3){
		//3
		layer.alert(menu11,{btn:systemOK,title :systemInfo});// menu11:三级菜单不能添加子菜单! - 
	}else{
		//iframe窗
		layer.open({
			title : systemInfo,
			offset: '5%',// 距离页面顶距离
			type: 2,
		    area: ['1000px', '557px'],
		    fix: false, //不固定
		    maxmin: true,
		    content: '../system/menu!toMenuAdd.do?id='+id
		});
	}
}