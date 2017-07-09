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
	selectInitial("role","../system/option!getOptionsByGPVal.do?value=USER_TYPE","2",true);
	selectInitial("gender","../system/option!getOptionsByGPVal.do?value=SEX","",false);
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS","T",false);
	selectInitial("teacType","../system/option!getOptionsByGPVal.do?value=TEACHER_TYPE","",false);
	selectInitial("degree","../system/option!getOptionsByGPVal.do?value=DEGREE","",false);
	selectInitial("education_background","../system/option!getOptionsByGPVal.do?value=EDU_BAK","",false);
	selectInitial("direction","../system/option!getOptionsByGPVal.do?value=DIRECTION","",false);
	selectInitial("cardType","../system/option!getOptionsByGPVal.do?value=CARD_TYPE","1",false);

	//关闭按钮的绑定
	$('#closeIframe').click(function(){
	    parent.layer.close(index);
	});
	//提交更改	按钮的绑定
	$('#editIframe').click(function(){
		if(flag){
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
				layer.confirm(teacher30, {
				    btn: [teacher16,teacher17] //按钮// teacher16:确定 - teacher17:取消 - 
				}, function(){
					layer.close(index);
					
						$.ajax({
				            cache: true,
				            type: "POST",
				            url: "../tech/teacherpool!addTeacherInfo.do",
				            data: $('#searchForm').serialize()+"&tepVo.photo="+document.getElementById("preview").src,// 你的formid
				            async: true,
				            error: function(request) {
				                //nothing to do
				            },
				            success: function(data) {
				            	if(data.success=='true'){
					            	//成功的回调函数
					            	if(data.message=="success"){     		                   			            		
					            		layer.alert(teacher1);//添加成功   
					            		parent.$("#teacherpool").DataTable().draw();   
					            		parent.layer.close(index);
					            	}else{
					            		layer.alert(teacher2);// teacher2:添加失败!提示:头像推荐大小150px 150px - 
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
			layer.msg(teacher31);
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
			layer.msg(teacher3);// teacher3:密码输入成功! - 
			$('#myModal').modal('hide');
		}else{
			layer.msg(teacher4);// teacher4:请重复正确的密码! - 
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
