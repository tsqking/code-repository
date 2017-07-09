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
	selectInitial("role","../system/option!getOptionsByGPVal.do?value=USER_TYPE","",false);
	selectInitial("gender","../system/option!getOptionsByGPVal.do?value=SEX","",false);
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS","",false);
	//时间日期控件初始化
	dateInitial([ {"id":"create_time","type":"2"},{"id":"update_time","type":"2"} ]);	
	//数据表格初始化
	//1.搜索按钮绑定
	dataTableSearchButton("searchButton","example1","searchForm",null);
	//2.清空表单按钮绑定
	dataTableClearButton("resetButton","searchForm");
	//3.数据表格设置
	dataTable("example1","../system/user!getUserPage.do",
			[ [ 0, "asc" ] ], 
			[ {
				"mData" : "username",
				"sClass" : "center"
			}, {
				"mData" : "role_name",
				"sClass" : "center"
			}, {
				"mData" : "name",
				"sClass" : "center"
			}, {
				"mData" : "gender_name",
				"sClass" : "center"
			}, {
				"mData" : "age",
				"sClass" : "center"
			}, {
				"mData" : "mobile",
				"sClass" : "center"
			}, {
				"mData" : "email",
				"sClass" : "center"
			}, {
				"mData" : "enable_name",
				"sClass" : "center"
			}, {
				"mData" : "id",
				"sClass" : "center"
			},],
			[ 
			   {
					"aTargets" : [ 8 ],
					//"mData" : "操作",
					"mRender" : function(data, type, full) {
						var detail = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+user13+'" onclick="detail(\''// user13:详细 - 
							+ full.id +'\')"><i class="fa fa-reorder"></i></a>';
						var update = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+user14+'" onclick="update(\''// user14:编辑 - 
							+ full.id +'\',\'' + full.flag + '\')"><i class="fa fa-edit"></i></a>';
						var del = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+user15+'" onclick="del(\''// user15:删除 - 
							+full.id+'\',\''+full.name+'\',\''+full.role+
							'\',\'' + full.flag + '\')"><i class="fa fa-trash"></i></a>';	
						var role = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+user16+'" onclick="role(\''// user16:编辑角色 - 
							+ full.id +'\',\''+full.username+'\',\''+full.name+
							'\',\'' + full.flag + '\')"><i class="fa fa-group"></i></a>';	
						if(full.flag=="true"){
							return detail+"&nbsp;&nbsp;&nbsp;"+update+"&nbsp;&nbsp;&nbsp;"+del+"&nbsp;&nbsp;&nbsp;"+role;
						}else{
							return detail+"&nbsp;&nbsp;&nbsp;";
						}
					}
			   }
			]
	);
	//modal初始化
    $('#batchAddModel').modal({backdrop: 'static', keyboard: false});
    $('#batchAddModel').modal('hide');
}

//详细信息方法
function detail(id){
	//iframe窗
	var index = layer.open({
		title : title1,
		type: 2,
	    area: ['1300px', '650px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../system/user!toUserDetail.do?id='+id
	});
	layer.full(index);
}

//更新编辑方法
function update(id,flag){
	if(flag=="true"){
		//iframe窗
		var index = layer.open({
			title : title2,
			type: 2,
			area: ['1300px', '650px'],
			fix: false, //不固定
			maxmin: true,
			content: '../system/user!toUserUpdate.do?id='+id
		});	
		layer.full(index);
	}else{
		layer.msg(user47);
	}
}

//删除方法
function del(id,name,role,flag){
	if(flag=="true"){
		layer.confirm(user17+' '+name+' '+user48, {// user17:确定要删除 - user18:用户吗? - 
		    btn: [user19,user20] //按钮// user19:确定 - user20:取消 - 
		}, function(){
			layer.close(index);
			//确定
			$.ajax({
		        type : "POST",
		        url : "../system/user!deleteUser.do",
		        data : "id="+id+"&role="+role,
		        success:function(data){
		        	if(data.success=='true'){
		        		 //成功的回调函数
			        	if(data.message=='success'){
			        		layer.alert(user21);// user21:删除成功! - 
			        		$("#example1").DataTable().draw();
			        	}else if(data.message=='1111'){
			        		layer.alert(user22);// user22:权限不足! - 
			        	}else if(data.message=='2222'){
			        		layer.alert(user222);//user222:有关联，不允许删除
			        	}else{
			        		layer.alert(user23);// user23:删除失败! - 
			        	}
		        	}else{
		        	    layer.msg(data.message);
		        	}  
		        }
			});
		}, function(){
		    ;
		});
	}else{
		layer.msg(user47);
	}
}

//添加方法
function add(){
	//iframe窗
	var index = layer.open({
		title : title3,
		type: 2,
		area: ['1300px', '650px'],
		fix: false, //不固定
		maxmin: true,
		content: '../system/user!toUserAdd.do'
	});	
	layer.full(index);
}

//编辑角色方法
function role(id,username,name,flag){
	if(flag=="true"){
		//iframe窗
		var index = layer.open({
			title : title4,
			type: 2,
			area: ['1300px', '650px'],
			fix: false, //不固定
			maxmin: true,
			content: '../system/user!toUserRole.do?id='+id+'&username='+username+'&name='+name
		});	
		layer.full(index);
	}else{
		layer.msg(user47);
	}
}

/**
 * 显示批量添加用户界面
 */
function batchAdd(){
	$('#batchAddModel').modal('show');
}
/**
 * 上传
 */
 $(document).on("click","#batchUpload",
	function() {
	 	var ids = ["batchPeopleList"];
	 	var load_index=layer.load(0, {
		    shade: [0.1,'#fff']
		});
	 	ajaxFileUpload(ids,'../system/userbatch!batchAddUser.do',0,
				"",null,
	    		function(data, status){
	 				layer.close(load_index);
		        	if(data.success=="true"){//上传成功
		        		$('#batchAddModel').modal('hide');
		        		$("#example1").DataTable().draw();
		        		var content=data.datas.message;
		        		content=content.replace(new RegExp("&lt;","g"),"<");
		        		content=content.replace(new RegExp("&gt;","g"),">");
		        		layer.alert(content,{title:user33});
		        	}else{//上传失败
		        		layer.alert(data.message,{title:user34});
		        	}
	        	}
		);
	}
);
/**
 * 关闭上传界面
 */
 $(document).on("click","#closeButton",
	function() {
	 	$('#batchAddModel').modal('hide');
	}
);
/**
 * 下载批量添加模板
 */
function downTemplate(){
	window.open("../system/userbatch!downTemplate.do");
}