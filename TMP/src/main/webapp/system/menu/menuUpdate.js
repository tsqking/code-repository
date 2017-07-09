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
function initial(){
	//获取菜单信息
	getMenuInfo();
	//关闭
	$('#closeIframe').click(function(){
	    parent.layer.close(index);
	});
	//提交更改	
	$('#updateIframe').click(function(){
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
	            url: "../system/menu!updateMenuInfo.do",
	            data: $('#infoForm').serialize(),// 你的formid
	            async: true,
	            error: function(request) {
	                //nothing to do
	            },
	            success: function(data) {
	            	//成功的回调函数
	            	if(data.success=="true"){
	            		parent.layer.alert(menu12,{btn:systemOK,title :systemInfo});// menu12:更新成功! - 
	            		parent.$("#example1").DataTable().draw();
	            		parent.layer.close(index);        		
	            	}else{
	            		parent.layer.alert(menu13,{btn:systemOK,title :systemInfo});// menu13:更新失败! - 
	            	}				  
	            }
	        });
		}
	});
}

//获取菜单信息
function getMenuInfo(){
	parent.layer.iframeAuto(index);
	var id = $('#menuId').val();
	$.ajax({
	   type : "POST",
	   url : "../system/menu!detailMenu.do",
	   data : "id="+id,
	   success:function(data){
	        //成功的回调函数
	    	//nothing to do
	       praseJson(data.datas.info, "", "", "");
	       //选项框初始化
	   	   selectInitial("level","../system/option!getOptionsByGPVal.do?value=MENU_LEVEL",data.datas.info.level,true);
	   	   selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS",data.datas.info.enable,false);
	   }
	});	
}