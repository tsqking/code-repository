//---------------------------js文本全局变量----------------------------------
//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 
//图片信息
var filePath = "no";



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
	//日期时间控件初始化
	dateInitial([ {"id":"birthday","type":"3"} ]);
	//选项框初始化
	selectInitial("role","../system/option!getOptionsByGPVal.do?value=USER_TYPE","2",true);
	selectInitial("gender","../system/option!getOptionsByGPVal.do?value=SEX","",false);
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS","",false);
	selectInitial("teacType","../system/option!getOptionsByGPVal.do?value=TEACHER_TYPE","",false);
	selectInitial("degree","../system/option!getOptionsByGPVal.do?value=DEGREE","",false);
	selectInitial("education_background","../system/option!getOptionsByGPVal.do?value=EDU_BAK","",false);
	selectInitial("direction","../system/option!getOptionsByGPVal.do?value=DIRECTION","",false);
	selectInitial("cardType","../system/option!getOptionsByGPVal.do?value=CARD_TYPE","",false);
	//头像加载
	loadImg();
	//初始获取用户信息
	//getTeacherInfo();
	//关闭按钮事件绑定
	$('#closeIframe').click(function(){
	    parent.layer.close(index);
	});
	//输入密码验证
	$("#password").click(function(){
		clearTable("searchForm_modal");
		//角色权限
     	$.ajax({
    		type : "POST",
    		url : "../tech/teacherpool!judgeSuperAdmin.do",
    		success:function(data){
    			document.getElementById("userPwd").disabled = "";
    			document.getElementById("userPwd").type = "password";
    			 //成功的回调函数
    			if(data.message=="0"){
    				//超级管理员
    				document.getElementById("userPwd").type = "text";
    				document.getElementById("userPwd").value = teacher19;// teacher19:无需输入 - 
    				document.getElementById("userPwd").disabled = "disabled";	
    			}			       
    		}
    	}); 
     	//打开
		$('#myModal').modal('show');
	});
	//确认验证
	$("#pwdModalApply").click(function(){
		var p1 = document.getElementById("userNowPwd").value;
		var p2 = document.getElementById("userPwd").value;
		//alert(p1);
		//验证密码
		$.ajax({
			type : "POST",
			url : "../tech/teacherpool!checkTeacherNow.do",
			data : "tepVo.pwd1="+p1+"&tepVo.pwd2="+p2,
			success:function(data){
				 //成功的回调函数
				if(data.message=="success"){
					layer.msg(teacher20);// teacher20:密码校验成功!  
					$('#myModal').modal('hide');
					//打开
					clearTable("searchForm_modal2");
					$('#myModal2').modal('show');
				}else{
					layer.msg(teacher21);// teacher21:密码校验失败! - 
				}  				       
			}
		});	
	});
	//确认修改
	$("#pwdModalApply2").click(function(){
		var p1 = document.getElementById("changePwd1").value;
		var p2 = document.getElementById("changePwd2").value;
		if(p1!=p2){
			layer.msg(teacher22);// teacher22:密码重复不正确! - 
		}else{						
			document.getElementById("hiddenPwd").value = p1;
			//layer.msg(teacher23);// teacher23:修改成功! - 
			$('#myModal2').modal('hide');
		}	
	});
	//提交更改	按钮事件绑定
	$('#editIframe').click(function(){
		var itemArr=[
		             //非空校验
		             {"id":"teacType","type":"1","regular":null,"message":null},
		             {"id":"enable","type":"1","regular":null,"message":null},
		             {"id":"gender","type":"1","regular":null,"message":null},
		             {"id":"username","type":"1","regular":null,"message":null},
		             {"id":"hiddenPwd","type":"1","regular":null,"message":null},
		             {"id":"name","type":"1","regular":null,"message":null},
		             {"id":"en_name","type":"1","regular":null,"message":null},
		             {"id":"cardType","type":"1","regular":null,"message":null},
		             {"id":"cardNbr","type":"1","regular":null,"message":null},
		             {"id":"mobile","type":"1","regular":null,"message":null},
		             {"id":"email","type":"1","regular":null,"message":null},
		             //其他校验
		             {"id":"email","type":"3","regular":null,"message":null},
		             {"id":"mobile","type":"4","regular":null,"message":null},
		             {"id":"cardNbr","type":"6","regular":null,"message":null},
		             {"id":"hiddenPwd","type":"5_6_15","regular":null,"message":null},
		             {"id":"age","type":"2","regular":null,"message":null},
		             {"id":"phone","type":"7","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
			$.ajax({
	            cache: true,
	            type: "POST",
	            url: "../tech/teacherpool!updateTeacher.do",
	            data: $('#searchForm').serialize(),// 你的formid
	            async: true,        
	            success: function(data) {
	            	//成功的回调函数
	            	if(data.message=="success"){
	            		//更新图片
	            		filePath = document.getElementById("preview").src;
	            		$.ajax({
	            			type : "POST",
	            			url : "../tech/teacherpool!updateTeacherPhoto.do",
	            			data : "tepVo.photo="+filePath+"&tepVo.nbr="+document.getElementById("nbr").value,
	            			success:function(data){
	            				 //成功的回调函数
	            				if(data.message=="success"){
	            					$("#teacherpool").DataTable().draw();
	            					 parent.layer.alert(teacher24);// teacher24:更新成功! - 
	            				}else{
	            					 parent.layer.alert(teacher25);// teacher25:头像图片更新失败!提示:头像推荐大小150px 150px - 
	            				} 
	            				parent.$("#teacherpool").DataTable().draw();
	       					 	parent.layer.close(index);
	            			}
	            		});	          			               		        		
	            	}else{
	            		layer.alert(teacher25);// teacher24:更新失败! - 
	            	}				  
	            }
	        });
		}
	});
	
	//账户名的唯一验证
	$('#username').change(function(n){
		//alert($('#username').val());
		if($('#username').val()==""){
			layer.msg(teacher32);
			flag = false;
		}else{
			$.ajax({
	            cache: true,
	            type: "POST",
	            url: "../tech/teacherpool!checkData.do",
	            data: "tepVo.username="+$('#username').val(),
	            async: true,
	            error: function(request) {
	                //nothing to do
	            },
	            success: function(data) {
	            	if(data.success=='true'){
	            		var r = data.datas.result;
	            		if(r=="error"){
	        				//查询失败
	            			layer.msg(teacher33);
	            			$('#username').val("");
	            			flag = false;
	        			}else if(r=="success"){
	        				//无重复
	        				layer.msg(teacher34);
	        				flag = true;
	        			}else if(r=="repeat"){
	        				//重复
	        				layer.msg(teacher35);
	        				$('#username').val("");
	        				flag = false;
	        			}else if(r=="username"){
	        				//禁止开头
	        				layer.msg(teacher36);
	        				$('#username').val("");
	        				flag = false;
	        			}else{
	        				//查询失败
	        				layer.msg(teacher33);
	        				$('#username').val("");
	        				flag = false;
	        			}    		
	            	}else{
	            		layer.alert(data.message);
	            	}
	            }
	        });
		}			
	});
	//手机的唯一验证
	$('#mobile').change(function(n){
		if($('#mobile').val()==""){
			layer.msg(teacher37);
			flag = false;
		}else{
			$.ajax({
	            cache: true,
	            type: "POST",
	            url: "../tech/teacherpool!checkData.do",
	            data: "tepVo.mobile="+$('#mobile').val(),
	            async: true,
	            error: function(request) {
	                //nothing to do
	            },
	            success: function(data) {
	            	if(data.success=='true'){
	            		var r = data.datas.result;
	            		if(r=="error"){
	        				//查询失败
	            			layer.msg(teacher33);
	            			$('#mobile').val("");
	            			flag = false;
	        			}else if(r=="success"){
	        				//无重复
	        				layer.msg(teacher38);
	        				flag = true;
	        			}else if(r=="repeat"){
	        				//重复
	        				layer.msg(teacher39);
	        				$('#mobile').val("");
	        				flag = false;
	        			}else{
	        				//查询失败
	        				layer.msg(teacher33);
	        				$('#mobile').val("");
	        				flag = false;
	        			}    		
	            	}else{
	            		layer.alert(data.message);
	            	}
	            }
	        });	
		}		
	});
	
}

//头像加载
function loadImg(){
	// 加载头像
	$.ajax({
		type : "POST",
		url : "../tech/teacherpool!findPhoto.do",
		data : "tepVo.nbr="+$("#nbr").val(),
		success:function(data){
			 //成功的回调函数
			if(data.message=="success"){
				if(data.message=="success"){
					$("#preview").attr("src",data.datas.photo);
				}  
			}else{
			    layer.msg(data.message);
			}		 				       
		}
	});	
}


//初始获取用户信息
//function getTeacherInfo(){
//	var nbr = $('#teNbr').val();
//	$.ajax({
//	   type : "POST",
//	   url : "../tech/teacherpool!toEditTeacherPage.do",
//	   data : "tepVo.nbr="+nbr,
//	   success:function(data){
//	        //成功的回调函数
//	     	praseJson(data.datas.info, "disable", "", "");  
//	     	console.log(data.datas.info);
//	   }
//	});	
//}

//图片方法
//图片显示和预览----------------------------------------start-----------------------------------------------
function change(obj) {
    var pic = $("#preview");
    var file = $(obj);
    var ext=file.val().substring(file.val().lastIndexOf(".")+1).toLowerCase();
    // gif在IE浏览器暂时无法显示
    if(ext!='png'&&ext!='jpg'&&ext!='jpeg'){
        //alert("文件必须为图片！"); 
   	 layer.alert(user32);// user32:请选择图片文件! - 
   	 document.getElementById("f").value="";
   	 document.getElementById("preview").src=old;
   	 return;
    }
    // IE浏览器
    if (document.all) {
        file.select();
		 file.blur();//这句为了蛋疼的IE9
        var reallocalpath = document.selection.createRange().text;
        //liuchen
        filePath = reallocalpath;
        var ie6 = /msie 6/i.test(navigator.userAgent);
        // IE6浏览器设置img的src为本地路径可以直接显示图片
        if (ie6) pic.src = reallocalpath;
        else {
			这种方式不能控制大小	 
           // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
           //pic[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
           // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
           // pic[0].src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
			pic = pic.parent('div');
			pic.html('');
			pic[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)"; 
			pic[0].filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = reallocalpath;
			pic[0].style.width = "130px";  
			pic[0].style.height = "130px"; 
        }
    }else{
        html5Reader(file);
    }
}

function html5Reader(file){
	 var file = file[0].files[0];
	 var reader = new FileReader();
	 reader.readAsDataURL(file);
	 reader.onload = function(e){
		var pic = document.getElementById("preview");
	 	pic.src=this.result;	 
	 }
}

function clickInput(){
	 document.getElementById("f").click();
}
//图片显示和预览----------------------------------------end-----------------------------------------------
