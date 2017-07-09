	var question_id;
	var info;
	var imgType;
	var edit;
	$(function() {
		question_id = $("#question_id").val();
		info = $("#info").val();
		imgType = $("#imgType").val();
		edit = $("#edit").val();
		//初始化
		init();
	});
	//初始化
	function init(){
		$("#contextOl").html("");
		$("#contextImg").html("");
		var id = parent.$('#'+info).val();
		if(id==null||id==""){
			parent.layer.msg(findImage_73_no_image); // findImage_73_no_image:无图片可以展示!
			parent.layer.close(parent.layer.getFrameIndex(window.name));
			return null;
		}
		var editFlag;
		var context;
		if(edit=="0"){
			//不能编辑
			editFlag = "";
			$("#text").html("");
		}
		id = id.split("-");
		for(var i=0;i<id.length;i++){
			var uuid = id[i];
			var className = "";
			if(i==0){
				className = "active";
			}
			if(uuid!=null&&uuid!=""){
				if(edit=="1"){
					//编辑
					editFlag = 'onclick="deleteImg(\''+uuid+'\')"';
				}
				if(imgType=="0"){
					//试题
					context = findImage_73_image; // findImage_73_image:张图片
				}else{
					//选项
					context = findImage_73_option; // findImage_73_option:个选项
				}
				$("#contextOl").html($("#contextOl").html()+
						'<li data-target="#carousel-example-generic" data-slide-to="'+i+'" class="'+className+'"></li>');
				$("#contextImg").html($("#contextImg").html()+
						('<div class="item '+className+'"><img '+
								editFlag+' style="height: 450px;width: 800px;" '+
						'src="../question/fileAction!getImage.do?fileName='+uuid+'" '+
						'alt=""><div class="carousel-caption">'+findImage_73_no+(i+1)+context+'</div></div>')) // findImage_73_no:第
			}
		}	
	}
	//删除图片
	function deleteImg(uuid){
		layer.open({
			title : [ findImage_73_action_sure, 'background-color:#3C8DBC; color:#ffffff;' ], // findImage_73_action_sure:操作确认
			content : findImage_73_action_title, // findImage_73_action_title:确认删除该图片吗?
			btn : [ findImage_73_yes, findImage_73_no_17 ], // findImage_73_yes:确认 // findImage_73_no_17:取消
			shadeClose : false,
			yes : function() {
					$.ajax({
						url : "../question/fileAction!deleteFiles.do",
						data: "fileNames="+uuid,
						dataType : "json",
						type : "get",
						success : function(data) {
							if (data.success == 'true') {
								if (data.message == '1') {//删除失败
									layer.msg(findImage_73_fail); // findImage_73_fail:删除失败
								} else if (data.message == '0')  {
									layer.msg(findImage_73_success); // findImage_73_success:删除成功
									//父类id修改
									parent.$('#'+info).val((parent.$('#'+info).val().replaceAll((uuid+"-"),"")));	
									//初始化
									init();
								}
							} else {
								layer.msg(findImage_73_fail_99); // findImage_73_fail_99:删除失败
								console.info(data.message);
							}
						}
					});
			}	
		});
	}
