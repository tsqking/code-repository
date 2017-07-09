//---------------------------js文本全局变量----------------------------------
//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 
//搜索数据 (必须)
var searchData = [ { "name" : "more_data" , "value" : "my_value" } ];
//该用户拥有的角色id集合
var role = [];



//-------------------------页面加载完成后执行方法-------------------------------
//页面加载完成执行
$(function() {
	//1.初始化方法
	initial();
});



//------------------------------js方法-------------------------------------
//初始化方法
function initial() {
	//加载用户的角色列表
	getUserRoles(true);
	//选项框初始化
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS","",false);
	selectInitial("has","../system/option!getOptionsByGPVal.do?value=HAS","",false);
	//时间日期控件初始化
	dateInitial([ {"id":"create_time","type":"2"},{"id":"update_time","type":"2"} ]);
	//数据表格初始化
	//1.搜索按钮初始化
	dataTableSearchButton("searchButton","example1","searchForm",[{ "name" : "id" , "value" : $('#userId').val() }]);
	//2.清空按钮的初始化
	dataTableClearButton("resetButton","searchForm");
	//关闭按钮事件绑定
	$('#closeIframe').click(function(){
	    parent.layer.close(index);
	});
	//删除全部角色按钮事件绑定
	$('#deleteAllRoles').click(function(){
		$.ajax({
			   type : "POST",
			   url : "../system/user!deleteAllRoles.do",
			   data : "id="+$('#userId').val(),
			   success:function(data){
				 if(data.success=='true'){
					 //成功的回调函数
		             if(data.message=="success"){
		            	layer.msg(user0);// user0:去除成功! - 
		            	getUserRoles(false);
		             }else{
		            	layer.msg(user1);// user1:去除失败! - 
		             }	
				 }else{
				    layer.msg(data.message);
				 } 			
			   }
		});
	});	
}

//加载用户的角色列表方法
function getUserRoles(flag){
	var id = $('#userId').val();
	$.ajax({
		   type : "POST",
		   url : "../system/user!getUserRoles.do",
		   data : "id="+id,
		   success:function(data){
			   if(data.success=='true'){
				   role = data.datas.info;
				   if(flag){
					  dataTableFun(); 
				   }else{
					  $("#example1").DataTable().draw();
				   }
			   }else{
			       layer.msg(data.message);
			   }  
		   }
	});	
}

//数据表格初始化方法
function dataTableFun(){
	dataTable("example1","../system/user!getRolePage.do",
			[ [ 0, "asc" ] ], 
			[ {
				"mData" : "id",
				"sClass" : "center"
			}, {
				"mData" : "name",
				"sClass" : "center"
			}, {
				"mData" : "description",
				"sClass" : "center"
			}, {
				"mData" : "enable_name",
				"sClass" : "center"
			}, {
				"mData" : "create_person",
				"sClass" : "center"
			}, {
				"mData" : "create_time",
				"sClass" : "center"
			}, {
				"mData" : "update_person",
				"sClass" : "center"
			}, {
				"mData" : "update_time",
				"sClass" : "center"
			},],
			[ 
				{
					"aTargets" : [ 0 ],
					//"mData" : "操作",
					"mRender" : function(data, type, full) {
						for(var i=0;i<role.length;i++){
							if(role[i]==full.id){
								return "<button type=\"button\" onclick=\"changeHas('"+full.id+"','0')\" " +
										"class=\"btn btn-block btn-success btn-xs\" " +
										"style=\"padding: 0px 0px 0px 0px;\">" +
										"<li class=\"glyphicon glyphicon-ok\"></li>&nbsp;&nbsp;"+user2+"</button>";// user2:已拥有 - 
							}
						}
						return "<button type=\"button\" onclick=\"changeHas('"+full.id+"','1')\" " +
								"class=\"btn btn-block btn-warning btn-xs\" " +
								"style=\"padding: 0px 0px 0px 0px;\">" +
								"<li class=\"glyphicon glyphicon-remove\"></li>&nbsp;&nbsp;"+user3+"</button>";// user3:未拥有 - 
					}
				}
			]
	);
}

//详细信息方法
function detail(id){
	//iframe窗
	var index = layer.open({
		type: 2,
	    area: ['1300px', '650px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../system/user!toUserDetail.do?id='+id
	});
	layer.full(index);
}

//添加.减少角色方法
function changeHas(id,type){
	if(type=="0"){
		//减少
		$.ajax({
			   type : "POST",
			   url : "../system/user!redUserRoles.do",
			   data : "id="+$('#userId').val()+"&role="+id,
			   success:function(data){
				 if(data.success=='true'){
					 //成功的回调函数
			         if(data.message=="success"){
			        	layer.msg(user4);	// user4:去除成功! - 
			            getUserRoles(false);
			         }else{
			        	layer.msg(user5);// user5:去除成功! - 
			         }
				 }else{
				    layer.msg(data.message);
				 }  
			   }
		});	
	}else{
		//添加
		$.ajax({
			   type : "POST",
			   url : "../system/user!addUserRoles.do",
			   data : "id="+$('#userId').val()+"&role="+id,
			   success:function(data){
				 if(data.success=='true'){
					 //成功的回调函数
		             if(data.message=="success"){
		            	layer.msg(user6);// user6:添加成功! - 
		            	getUserRoles(false);
		             }else{
		            	layer.msg(user7);// user7:添加失败! - 
		             }
				 }else{
				    layer.msg(data.message);
				 }  
			   }
		});	
	}
}