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
function initial() {
	//初始载入用户信息
	getUserInfo();
	//读取头像
	loadImg();
	//关闭按钮事件绑定
	$('#closeIframe').click(function(){
	    parent.layer.close(index);
	});	
	//详细按钮事件绑定
	$('#editIframe').click(function(){
		var id = $('#userId').val();
	    parent.update(id);
	    parent.layer.close(index);
	});
}

//初始载入用户信息
function getUserInfo(){
	var id = $('#userId').val();
	$.ajax({
	   type : "POST",
	   url : "../system/user!detailUser.do",
	   data : "id="+id,
	   success:function(data){
		   if(data.success=='true'){
			 //成功的回调函数
		     praseJson(data.datas.info, "disable", "", "");  
		   }else{
		       layer.msg(data.message);
		   }  
	   }
	});
}

//读取头像方法
function loadImg(){
	// 加载头像
	$.ajax({
		type : "POST",
		url : "../system/user!findPhoto.do",
		data : "id="+$("#userId").val(),
		success:function(data){
			if(data.success=='true'){
				//成功的回调函数
				if(data.message=="success"){
					$("#photo").attr("src",data.datas.photo);
				} 
			}else{
			    layer.msg(data.message);
			}	   				       
		}
	});
}