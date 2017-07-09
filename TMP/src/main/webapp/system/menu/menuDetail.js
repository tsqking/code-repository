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
	//初始化获得菜单信息
	getMenuInfo();
	//关闭
	$('#closeIframe').click(function(){
	    parent.layer.close(index);
	});	
	//编辑
	$('#editIframe').click(function(){
	    var id = $('#menuId').val();
	    parent.update(id);
	    //
	    parent.layer.close(index);
	});
}

//初始化获得菜单信息
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
	     	praseJson(data.datas.info, "disable", "", "");        	   	
	   }
	});
}

