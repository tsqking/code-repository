$(function() {
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.iframeAuto(index);
    //根据题目类型生成题目输入界面(1-单选，2-多选，3-判断，4-填空，5-简答，6-编程，7-综合)
    $("#show_type").change(function(){
    	var selectedVal = $("#show_type  option:selected").val();
    	if(selectedVal=='5' || selectedVal=='6' || selectedVal=='7' || selectedVal=='8'){
    		$("#qustFrame").prop("src","../question/question!toEditShortAnswerPage.do");    		
    	}else if(selectedVal=='3'){
    		$("#qustFrame").prop("src","../question/question!toEditTrueOrFalsePage.do");    		
    	}else if(selectedVal=='1'){
    		$("#qustFrame").prop("src","../question/question!toEditSingleChoisePage.do"); 
    	}else if(selectedVal=='2'){
    		$("#qustFrame").prop("src","../question/question!toEditMultipleChoisePage.do"); 
    	}else if(selectedVal=='4'){
    		$("#qustFrame").prop("src","../question/question!toEditFillingPage.do"); 
    	}
    });
	$(document).on("click", "#updateQuestion", function() {
		//获取选中的多选下拉框的值
		var tagIds = "";
		$("select#name option:selected").each(function(){
			tagIds = tagIds + $(this).val() + ",";
		});
		$("#tagIds").val(tagIds);
		var itemArr=[
		             {"id":"type","type":"1","regular":null,"message":null},
		             {"id":"property","type":"1","regular":null,"message":null},
		             {"id":"so_flag","type":"1","regular":null,"message":null},
		             {"id":"use_flag","type":"1","regular":null,"message":null},
		             {"id":"difficulty","type":"1","regular":null,"message":null},
		             {"id":"enable","type":"1","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
			//获取iframe中的值
			var pointList = "";
			var i = 0;
			var selectItem = "";
			//选项(选择题/判断题/多选题/填空题)
			$(window.frames["qustFrame"].document).find("input:text").each(function(){
				pointList = pointList + $(this).val() + "%^*^%";
		    });
			$("#selectItems").val(turnCharactor(pointList));
			var content = "";
			//题干
			$(window.frames["qustFrame"].document).find("textarea[id='content']").each(function(){
				content = content + $(this).val();
		    });
			$("#content").val(turnCharactor(content));
			//解析内容特殊字符转义
			$("#analysis").val(turnCharactor($("#analysis").val()));
			//选中的选项(选择题/判断题)
			$(window.frames["qustFrame"].document).find("input:radio").each(function(){
				i++;
				if($(this).prop("checked")){
					selectItem = selectItem + i;
				}
			});
			//选中的选项(多选题)
			$(window.frames["qustFrame"].document).find("input:checkbox").each(function(){
				i++;
				if($(this).prop("checked")){
					selectItem = selectItem + i + "#$#";
				}
			});
			var selectedVal = $("#show_type  option:selected").val();
			if(selectItem=="" && (selectedVal=="1" || selectedVal=="2" ||selectedVal=="3")){
				parent.parent.layer.msg(pleaseSelectAnswer);
			}else{
				$("#answer").val(selectItem);
				//答案内容(编程题/简答题/其他)
				$(window.frames["qustFrame"].document).find("textarea[id='answer']").each(function(){
					$("#answer").val($(this).val());
				});
				$("#answer").val(turnCharactor($("#answer").val()));
				//数据提交
				$.ajax({
					url : "../question/question!updateQuestion.do",
					data : $("#updateForm").serialize(),
					dataType : "json",
					type : "post",
					success : function(data) {
						 if(data.success=='true'){
							 if(data.message=="0000"){
								 parent.layer.msg(updateQuestionSuc);
								 //编辑数据后刷新表格
								 parent.refreshTable();
								 parent.layer.close(index);
							 }else if(data.message=="1111"){
								 layer.alert(updateQuestionFail, {title:feedback});
							 }
						 }else{
							 layer.msg(data.message,{shift: 6});
						 }
					}
				});
			}
		}
	});
});
//设置知识点
function setPoint(){
	layer.open({
		//"设置知识点"资源文件
		title : [ setPoints, 'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		area : [ '90%', '100%' ],
		fix : false, // 不固定
		maxmin : true,
		content : '../question/question!toPointManagePage.do'
	});
}
//新增标签
function newTag(){
	layer.open({
		//"添加标签"资源文件
		title : [ addTitle, 'background-color:#3C8DBC; color:#ffffff;' ],
		type : 2,
		area : [ '60%', '33%' ],
		offset: '10%',// 距离页面顶距离
		fix : false, // 不固定
		maxmin : true,
		content : '../question/tag!addNewTagPage.do'
	});
}
//图片上传
function toUploadImage(inputId,type){
	var context="";
	if(type=="2"){
		//选项
		if($("#type").val()!="1"&&$("#type").val()!="2"){
			//不开放
			return null;
		}
		context=editQuestion_31_image_title; // editQuestion_31_image_title:选项图片应与对应选项的数量和顺序相同
	}else{
		context=editQuestion_31_opt_title; // editQuestion_31_opt_title:题目附图不宜过多,0~3张最佳
	}
	layer.open({
		title : [ editQuestion_31_action_sure, 'background-color:#3C8DBC; color:#ffffff;' ], // editQuestion_31_action_sure:操作确认
		content : context,
		btn : [ editQuestion_31_yes, editQuestion_31_no ], // editQuestion_31_yes:确认 // editQuestion_31_no:取消
		shadeClose : false,
		yes : function() {
			document.getElementsByClassName("layui-layer-ico layui-layer-close layui-layer-close1")[0].click();
			var index = layer.open({
				title: [
					editQuestion_31_sum_image, // editQuestion_31_sum_image:提交图片
					'background-color:#3C8DBC; color:#ffffff;'
				],
				offset: '1%',// 距离页面顶距离
				type: 2,
				area: [ '100%' , '85%'],
				fix: false, //不固定
				maxmin: true,
				content: "../question/fileAction!toUploadImage.do?questionVo.info="+inputId,
			});
			layer.full(index);
		}	
	});		
}
//图片查看
function toFindImage(inputId,type){
	var imgType = 0;
	if(type=="2"){
		//选项
		if($("#type").val()!="1"&&$("#type").val()!="2"){
			//不开放
			return null;
		}
		imgType = 1;
	}
	if($("#"+inputId).val()==null||$("#"+inputId).val()==""){
		layer.msg(editQuestion_31_no_image); // editQuestion_31_no_image:无图片可以展示!
		return null;
	}
	var index1 = layer.open({
		title: [
			editQuestion_31_find_image, // editQuestion_31_find_image:查看图片
			'background-color:#3C8DBC; color:#ffffff;'
		],
		offset: '1%',// 距离页面顶距离
		type: 2,
		area: [ '100%' , '70%'],
		fix: false, //不固定
		maxmin: true,
		content: "../question/fileAction!toFindImage.do?questionVo.info="+inputId+"&questionVo.edit=1&questionVo.imgType="+imgType,
	});
	layer.full(index1);
}
