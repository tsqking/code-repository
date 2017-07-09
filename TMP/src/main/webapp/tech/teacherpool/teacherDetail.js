//---------------------------js文本全局变量----------------------------------
//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 



//-------------------------页面加载完成后执行方法-------------------------------
//页面加载完成执行
$(function() {
	parent.layer.full(index);
	//1.初始化方法
	initial();
});



//------------------------------js方法-------------------------------------
//初始化方法
function initial() {
	//初始载入用户信息
	//getTeacherInfo();
	//读取头像
	loadImg();
	//关闭按钮事件绑定
	$('#closeIframe').click(function(){
	    parent.layer.close(index);
	});	
	//详细按钮事件绑定
	$('#editIframe').click(function(){
		var nbr = $('#teNbr').val();
	    parent.update(nbr);
	    parent.layer.close(index);
	});
/*	$(document).on("click","#editIframe",
			function() {
		var nbr = $('#teNbr').val();
				parent.update(nbr);
				parent.layer.close(index);
			}
		);*/
}

/*//初始载入用户信息
function getTeacherInfo(){
	var nbr = $('#teNbr').val();
	alert(nbr);
	$.ajax({
	   type : "POST",
	   url : "../tech/teacherpool!detailTeacher.do",
	   data : "tepVo.nbr="+nbr,
	   success:function(data){
		   alert(data.datas.info);
	        //成功的回调函数
	     	praseJson(data.datas.info, "disable", " ", " ");
	     	
	   }
	});
}*/

//读取头像方法
function loadImg(){
	// 加载头像
	$.ajax({
		type : "POST",
		url : "../tech/teacherpool!findPhoto.do",
		data : "tepVo.nbr="+$("#nbr").val(),
		success:function(data){
			 //成功的回调函数
			if(data.message=="success"){
				$("#photo").attr("src",data.datas.photo);
			}   				       
		}
	});
}