//---------------------------js文本全局变量----------------------------------
//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 
//搜索数据 (必须)
var searchData = [ { "name" : "more_data" , "value" : "my_value" } ];
//图片数据
var filePath = "no";
//校验控制
var flag = false;



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
	//时间日期控件初始化
	dateInitial([ {"id":"birthday","type":"3"} ]);
	//选择框初始化
	selectInitial("role","../system/option!getOptionsByGPVal.do?value=USER_TYPE","",false);
	selectInitial("gender","../system/option!getOptionsByGPVal.do?value=SEX","",false);
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS","",false);
	selectInitial("education_background","../system/option!getOptionsByGPVal.do?value=EDU_BAK","",false);
	selectInitial("degree","../system/option!getOptionsByGPVal.do?value=DEGREE","",false);
	selectInitial("cardtype","../system/option!getOptionsByGPVal.do?value=CARD_TYPE","",false);
	selectInitial("direction","../system/option!getOptionsByGPVal.do?value=DIRECTION","",false);
	//关闭按钮的绑定
	$('#closeIframe').click(function(){
	    parent.layer.close(index);
	});
	//提交更改	按钮的绑定
	$('#editIframe').click(function(){
		if(flag){
			var itemArr=[
			             //非空校验 
			             {"id":"role","type":"1","regular":null,"message":null},
			             {"id":"enable","type":"1","regular":null,"message":null},
			             {"id":"gender","type":"1","regular":null,"message":null},
			             {"id":"username","type":"1","regular":null,"message":null},
			             {"id":"hiddenPwd","type":"1","regular":null,"message":null},
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
				layer.confirm(user35, {
				    btn: [systemOK,systemCancel] //按钮// menu7:确定 - menu8:取消 - 
				}, function(){
					layer.close(index);
					//确定
					$.ajax({
			            cache: true,
			            type: "POST",
			            url: "../system/user!addUserInfo.do",
			            data: $('#searchForm').serialize()+"&photo="+document.getElementById("preview").src,// 你的formid
			            async: true,
			            error: function(request) {
			                //nothing to do
			            },
			            success: function(data) {
			            	if(data.success=='true'){
				            	//成功的回调函数
				            	if(data.message=="success"){
				            		layer.alert(user8);// user8:添加成功! - 	            		
				            		//刷新表格
				            		parent.$("#example1").DataTable().draw();
				            		//关闭窗口
				            		parent.layer.close(index);
				            	}else{
				            		layer.alert(user9);// user9:添加失败!提示:头像推荐大小150px 150px - 
				            	}	
			            	}else{
			            		layer.alert(data.message);
			            	}
			            }
			        });
				}, function(){
				    ;
				});	
			}
		}else{
			layer.msg(user36);
		}	
	});
	//绑定
	$("#password").click(function(){
		clearTable("searchForm_modal");
     	//打开
		$('#myModal').modal('show');
	});
	//确认验证
	$("#pwdModalApply").click(function(){
		var p1 = document.getElementById("Pwd1").value;
		var p2 = document.getElementById("Pwd2").value;
		if(p1==p2){
			document.getElementById("password").innerHTML = "******";
			document.getElementById("hiddenPwd").value = p1;
			layer.msg(user10);// user10:密码输入成功! - 
			$('#myModal').modal('hide');
		}else{
			layer.msg(user11);// user11:请重复正确的密码! - 
		}
	});
	//账户名的唯一验证
	$('#username').change(function(n){
		if($('#username').val()==""){
			layer.msg(user37);
			flag = false;
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
	            			$('#username').val("");
	            			flag = false;
	        			}else if(r=="success"){
	        				//无重复
	        				layer.msg(user39);
	        				flag = true;
	        			}else if(r=="repeat"){
	        				//重复
	        				layer.msg(user40);
	        				$('#username').val("");
	        				flag = false;
	        			}else if(r=="name"){
	        				//禁止开头
	        				layer.msg(user41);
	        				$('#username').val("");
	        				flag = false;
	        			}else{
	        				//查询失败
	        				layer.msg(user42);
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
			layer.msg(user43);
			flag = false;
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
	            			$('#mobile').val("");
	            			flag = false;
	        			}else if(r=="success"){
	        				//无重复
	        				layer.msg(user44);
	        				flag = true;
	        			}else if(r=="repeat"){
	        				//重复
	        				layer.msg(user45);
	        				$('#mobile').val("");
	        				flag = false;
	        			}else{
	        				//查询失败
	        				layer.msg(user38);
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

//图片文件操作方法
//图片显示和预览----------------------------------------start-----------------------------------------------
function change(obj) {
    var pic = $("#preview");
    var file = $(obj);
    var ext=file.val().substring(file.val().lastIndexOf(".")+1).toLowerCase();
    // gif在IE浏览器暂时无法显示
    if(ext!='png'&&ext!='jpg'&&ext!='jpeg'){
        //alert("文件必须为图片！"); 
   	 layer.alert(user12);// user12:请选择图片文件! - 
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
