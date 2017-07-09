//---------------------------js文本全局变量----------------------------------
//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 
//图片信息
var filePath = "no";
//校验控制
var flag = false;
//原登录名
var oldUsername;
//原手机号
var oldPhone;


//-------------------------页面加载完成后执行方法-------------------------------
//页面加载完成执行
$(function() {
	//1.初始化方法
	initial();
});



//------------------------------js方法-------------------------------------
//初始化方法
function initial() {
	flag = false;
	//日期时间控件初始化
	dateInitial([ {"id":"birthday","type":"3"} ]);
	//头像加载
	loadImg();
	//初始获取用户信息
	getUserInfo();
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
    		url : "../system/user!judgeSuperAdmin.do",
    		success:function(data){
    			if(data.success=='true'){
    				document.getElementById("userPwd").disabled = "";
        			document.getElementById("userPwd").type = "password";
        			 //成功的回调函数
        			if(data.message=="0"){
        				//超级管理员
        				document.getElementById("userPwd").type = "text";
        				document.getElementById("userPwd").value = user24;// user24:无需输入 - 
        				document.getElementById("userPwd").disabled = "disabled";	
        			}
    			}else{
    				layer.msg(data.message);
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
		//验证密码
		$.ajax({
			type : "POST",
			url : "../system/user!checkUserNow.do",
			data : "pwd1="+p1+"&pwd2="+p2,
			success:function(data){
				if(data.success=='true'){
					 //成功的回调函数
					if(data.message=="success"){
						layer.msg(user25);// user25:密码校验成功! - 
						$('#myModal').modal('hide');
						//打开
						clearTable("searchForm_modal2");
						$('#myModal2').modal('show');
					}else{
						layer.msg(user26);// user26:密码校验失败! - 
					} 
				}else{
				    layer.msg(data.message);
				}	 				       
			}
		});	
	});
	//确认修改
	$("#pwdModalApply2").click(function(){
		var p1 = document.getElementById("changePwd1").value;
		var p2 = document.getElementById("changePwd2").value;
		if(p1!=p2){
			layer.msg(user27);// user27:密码重复不正确! - 
		}else{						
			document.getElementById("hiddenPwd").value = p1;
			layer.msg(user28);// user28:修改成功! - 
			$('#myModal2').modal('hide');
		}	
	});
	//提交更改	按钮事件绑定
	$('#editIframe').click(function(){
		if(flag){
			var itemArr=[
			             //非空校验 
			             {"id":"role","type":"1","regular":null,"message":null},
			             {"id":"enable","type":"1","regular":null,"message":null},
			             {"id":"gender","type":"1","regular":null,"message":null},
			             {"id":"username","type":"1","regular":null,"message":null},
			             //{"id":"hiddenPwd","type":"1","regular":null,"message":null},
			             {"id":"name","type":"1","regular":null,"message":null},
			             {"id":"en_name","type":"1","regular":null,"message":null},
			             {"id":"cardtype","type":"1","regular":null,"message":null},
			             {"id":"cardno","type":"1","regular":null,"message":null},
			             {"id":"mobile","type":"1","regular":null,"message":null},
			             {"id":"email","type":"1","regular":null,"message":null},
			             //其他校验
			             {"id":"email","type":"3","regular":null,"message":null},
			             {"id":"mobile","type":"4","regular":null,"message":null},
			             {"id":"cardno","type":"6","regular":null,"message":null},
			             {"id":"hiddenPwd","type":"5_6_15","regular":null,"message":null},
			             {"id":"age","type":"2","regular":null,"message":null},
			             {"id":"phone","type":"7","regular":null,"message":null},
			             {"id":"gpa","type":"2","regular":null,"message":null},
			             {"id":"cet4","type":"2","regular":null,"message":null},
			             {"id":"cet6","type":"2","regular":null,"message":null},
			             {"id":"contact_postcode","type":"8","regular":null,"message":null},
			             {"id":"home_postcode","type":"8","regular":null,"message":null}
			             ];
			if(validate(itemArr)){
				$.ajax({
		            cache: true,
		            type: "POST",
		            url: "../system/user!updateUserInfo.do",
		            data: $('#searchForm').serialize(),// 你的formid
		            async: true,
		            error: function(request) {
		                //nothing to do
		            },
		            success: function(data) {
		            	if(data.success=='true'){
		            		//成功的回调函数
			            	if(data.message=="success"){
			            		//更新图片
			            		filePath = document.getElementById("preview").src;
			            		$.ajax({
			            			type : "POST",
			            			url : "../system/user!updateUserPhoto.do",
			            			data : "photo="+filePath+"&id="+document.getElementById("id").value,
			            			success:function(data){
			            				if(data.success=='true'){
			            					 //成功的回调函数
				            				if(data.message=="success"){
				            					 parent.layer.alert(user29);// user29:更新成功! - 
				            				}else{
				            					 parent.layer.alert(user30);// user30:头像图片更新失败!提示:头像推荐大小150px 150px - 
				            				} 
				            				parent.$("#example1").DataTable().draw();
				       					 	parent.layer.close(index);
			            				}else{
			            				    layer.msg(data.message);
			            				}				
			            			}
			            		});	          			               		        		
			            	}else{
			            		layer.alert(user31);// user31:更新失败! - 
			            	}
		            	}else{
		            	    layer.msg(data.message);
		            	}            					  
		            }
		        });
			}
		}else{
			layer.msg(user36);
		}		
	});
	//账户名的唯一验证
	$('#username').change(function(n){
		if($('#username').val()==""){
			layer.msg(user37);
			flag = false;
		}else if($('#username').val()==oldUsername){
			flag = true;
		}else{
			$.ajax({
	            cache: true,
	            type: "POST",
	            url: "../system/user!checkData.do",
	            data: "username="+$('#username').val(),
	            async: true,
	            error: function(request) {
	                //nothing to do
	            },
	            success: function(data) {
	            	if(data.success=='true'){
	            		var r = data.datas.result;
	            		if(r=="error"){
	        				//查询失败
	            			layer.msg(user38);
	            			$('#username').val(oldUsername);
	            			flag = true;
	        			}else if(r=="success"){
	        				//无重复
	        				layer.msg(user39);
	        				flag = true;
	        			}else if(r=="repeat"){
	        				//重复
	        				layer.msg(user40);
	        				$('#username').val(oldUsername);
	        				flag = true;
	        			}else if(r=="name"){
	        				//禁止开头
	        				layer.msg(user46);
	        				$('#username').val(oldUsername);
	        				flag = true;
	        			}else{
	        				//查询失败
	        				layer.msg(user38);
	        				$('#username').val(oldUsername);
	        				flag = true;
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
			layer.msg(user43);
			flag = false;
		}else if($('#mobile').val()==oldPhone){
			flag = true;
		}else{
			$.ajax({
	            cache: true,
	            type: "POST",
	            url: "../system/user!checkData.do",
	            data: "mobile="+$('#mobile').val(),
	            async: true,
	            error: function(request) {
	                //nothing to do
	            },
	            success: function(data) {
	            	if(data.success=='true'){
	            		var r = data.datas.result;
	            		if(r=="error"){
	        				//查询失败
	            			layer.msg(user38);
	            			$('#mobile').val(oldPhone);
	            			flag = true;
	        			}else if(r=="success"){
	        				//无重复
	        				layer.msg(user44);
	        				flag = true;
	        			}else if(r=="repeat"){
	        				//重复
	        				layer.msg(user45);
	        				$('#mobile').val(oldPhone);
	        				flag = true;
	        			}else{
	        				//查询失败
	        				layer.msg(user38);
	        				$('#mobile').val(oldPhone);
	        				flag = true;
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
		url : "../system/user!findPhoto.do",
		data : "id="+$("#userId").val(),
		success:function(data){
			if(data.success=='true'){
				 //成功的回调函数
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
function getUserInfo(){
	var id = $('#userId').val();
	$.ajax({
	   type : "POST",
	   url : "../system/user!detailUser.do",
	   data : "id="+id,
	   success:function(data){
		   if(data.success=='true'){
			 //成功的回调函数
		    	//nothing to do
		     	praseJson(data.datas.info, "", "", "");	
		     	//选项框初始化
		    	selectInitial("role","../system/option!getOptionsByGPVal.do?value=USER_TYPE",data.datas.info.role,false);
		    	selectInitial("gender","../system/option!getOptionsByGPVal.do?value=SEX",data.datas.info.gender,false);
		    	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS",data.datas.info.enable,false);
		    	selectInitial("education_background","../system/option!getOptionsByGPVal.do?value=EDU_BAK",data.datas.info.education_background,false);
		    	selectInitial("degree","../system/option!getOptionsByGPVal.do?value=DEGREE",data.datas.info.degree,false);
		    	selectInitial("cardtype","../system/option!getOptionsByGPVal.do?value=CARD_TYPE",data.datas.info.cardtype,false);
		    	selectInitial("direction","../system/option!getOptionsByGPVal.do?value=DIRECTION",data.datas.info.direction,false);
		     	//保存登录名和手机号
				oldUsername = data.datas.info.username;
				oldPhone = data.datas.info.mobile;
				flag = true;
		     	//角色权限
		     	$.ajax({
		    		type : "POST",
		    		url : "../system/user!judgeSuperAdmin.do",
		    		success:function(data){
		    			if(data.success=='true'){
		    				 //成功的回调函数
			    			if((data.message!="0")&&(data.message!="1")){
			    				document.getElementById("role").disabled="disabled";
			    			}  
		    			}else{
		    			    layer.msg(data.message);
		    			}	 				       
		    		}
		    	}); 
		   }else{
		       layer.msg(data.message);
		   }     	
	   }
	});
}

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
			/*这种方式不能控制大小*/	 
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
