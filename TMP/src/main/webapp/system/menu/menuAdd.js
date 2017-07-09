//---------------------------js文本全局变量----------------------------------
//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 



//-------------------------页面加载完成后执行方法-------------------------------
//页面加载完成执行
$(function() {
	//1.初始化方法
	initial();
});



//------------------------------js方法-------------------------------------
//初始化方法
function initial(){
	//初始获得菜单信息
	getMenuInfo();
	//选项框初始化
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS","",false);
	//关闭
	$('#closeIframe').click(function(){
	    parent.layer.close(index);
	});
	//新增 s
	$('#addIframe').click(function(){
		var itemArr=[
		             {"id":"name","type":"1","regular":null,"message":null},
		             {"id":"name_en_US","type":"1","regular":null,"message":null},
		             //{"id":"order","type":"1","regular":null,"message":null},
		             //{"id":"url","type":"1","regular":null,"message":null},
		             {"id":"enable","type":"1","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
			$.ajax({
	            cache: true,
	            type: "POST",
	            url: "../system/menu!addMenu.do",
	            data: $('#infoForm').serialize(),// 你的formid
	            async: true,
	            error: function(request) {
	                //nothing to do
	            },
	            success: function(data) {
	            	//成功的回调函数
	            	if(data.success=="true"){
	            		parent.layer.alert(menu0,{btn:systemOK,title :systemInfo});// menu0:更新成功! - 
	            		parent.$("#example1").DataTable().draw();
	            		parent.layer.close(index);        		
	            	}else{
	            		parent.layer.alert(menu1,{btn:systemOK,title :systemInfo});// menu1:更新失败! - 
	            	}				  
	            }
	        });
		}s
	});	
}

//初始获得菜单信息
function getMenuInfo(){
	parent.layer.iframeAuto(index);
	var id = $('#menuId').val();
	if(id==""){
		//一级菜单添加
		selectInitial("level","../system/option!getOptionsByGPVal.do?value=MENU_LEVEL","1",true);
		//初始化排序
		selectInitial("order","../system/menu!getMenuByOrder.do?level=1","",false);
		$('#parent_name').val(menu2);	// menu2:此菜单为一级菜单 - 
	}else{
		//非一级菜单菜单添加
		$.ajax({
			   type : "POST",
			   url : "../system/menu!detailMenu.do",
			   data : "id="+id,
			   success:function(data){
			        //成功的回调函数
			    	//nothing to do
				   var json = data.datas.info;
				   selectInitial("level","../system/option!getOptionsByGPVal.do?value=MENU_LEVEL",(json.level)*1+1,true);
				   selectInitial("order","../system/menu!getMenuByOrder.do?level="+((json.level)*1+1)+"&id="+id,"",false);
				   $('#parent_name').val(json.name);   
			   }
		});
	}	
}