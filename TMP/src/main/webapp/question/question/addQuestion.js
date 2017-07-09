//没什么用途
function refreshBt(){}
$(function() {
	//下拉框初始化
	selectInitial("type","../system/option!getOptionsByGPVal.do?value=QUST_TYPE", "1", false);
	selectInitial("property","../system/option!getOptionsByGPVal.do?value=QUST_PROP", "00", false);
	selectInitial("so_flag","../system/option!getOptionsByGPVal.do?value=QUST_SO", "o", false);
	selectInitial("use_flag","../system/option!getOptionsByGPVal.do?value=USE_FLAG", "2", false);
	selectInitial("difficulty","../system/option!getOptionsByGPVal.do?value=DIFFICULTY", "1", false);
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS", "T", false);
	multiSelectInitial("name","../question/tag!getAllTag.do",null,false);
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.iframeAuto(index);
    //根据题目类型生成题目输入界面(1-单选，2-多选，3-判断，4-填空，5-简答，6-编程，7-综合)
    $("#type").change(function(){
    	var selectedVal = $("#type  option:selected").val();
    	if(selectedVal=='5' || selectedVal=='6' || selectedVal=='7' || selectedVal=='8'){
    		$("#qustFrame").prop("src","../question/question!toShortAnswerPage.do"); 
    		$("#optImg1").addClass("disabled");
    		$("#optImg2").addClass("disabled");
    	}else if(selectedVal=='3'){
    		$("#qustFrame").prop("src","../question/question!toTrueOrFalsePage.do");  
    		$("#optImg1").addClass("disabled");
    		$("#optImg2").addClass("disabled");
    	}else if(selectedVal=='1'){
    		$("#qustFrame").prop("src","../question/question!toSingleChoisePage.do"); 
    		$("#optImg1").removeClass("disabled");
    		$("#optImg2").removeClass("disabled");
    	}else if(selectedVal=='2'){
    		$("#qustFrame").prop("src","../question/question!toMultipleChoisePage.do"); 
    		$("#optImg1").removeClass("disabled");
    		$("#optImg2").removeClass("disabled");
    	}else if(selectedVal=='4'){
    		$("#qustFrame").prop("src","../question/question!toFillingPage.do"); 
    		$("#optImg1").addClass("disabled");
    		$("#optImg2").addClass("disabled");
    	}
    	//删除图片数据
    	$("#queImgId").val("");
    	$("#optImgId").val("");
    });
	$(document).on("click", "#addQuestion", function() {
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
			var selectedVal = $("#type  option:selected").val();
			if(selectItem=="" && (selectedVal=="1" || selectedVal=="2" ||selectedVal=="3")){
				parent.layer.msg(pleaseSelectAnswer);
			}else{
				$("#answer").val(selectItem);
				//答案内容(编程题/简答题/其他)
				$(window.frames["qustFrame"].document).find("textarea[id='answer']").each(function(){
					$("#answer").val($(this).val());
				});
				$("#answer").val(turnCharactor($("#answer").val()));
				//数据提交
				$.ajax({
					url : "../question/question!addNewQuestion.do",
					data : $("#addForm").serialize(),
					dataType : "json",
					type : "post",
					success : function(data) {
						 if(data.success=='true'){
							 if(data.message=="0000"){
								 parent.layer.msg(addQuestionSuc);
								//编辑数据后刷新表格
								 parent.refreshTable();
								 parent.layer.close(index);
							 }else if(data.message=="1111"){
								 layer.alert(addQuestionFail, {title:feedback});
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
/**
 * 刷新标签
 * @param selectId
 */
function refreshTag(selectId){
	var selected=$("#name").val();
	if(selected==null)
		selected=[];
	selected.push(selectId);
	multiSelectInitial("name","../question/tag!getAllTag.do",selected,false);
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
		context=addQuestion_56_opt_title; // addQuestion_56_opt_title:选项图片应与对应选项的数量和顺序相同
	}else{
		context=addQuestion_56_image_title; // addQuestion_56_image_title:题目附图不宜过多,0~3张最佳
	}
	layer.open({
		title : [ addQuestion_56_action_sure, 'background-color:#3C8DBC; color:#ffffff;' ], // addQuestion_56_action_sure:操作确认
		content : context,
		btn : [ addQuestion_56_yes, addQuestion_56_no ], // addQuestion_56_yes:确认 // addQuestion_56_no:取消
		shadeClose : false,
		yes : function() {
			document.getElementsByClassName("layui-layer-ico layui-layer-close layui-layer-close1")[0].click();
			var index = layer.open({
				title: [
					addQuestion_56_sum_image, // addQuestion_56_sum_image:提交图片
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
		layer.msg(addQuestion_56_no_image); // addQuestion_56_no_image:无图片可以展示!
		return null;
	}
	var index1 = layer.open({
		title: [
			addQuestion_56_find_image, // addQuestion_56_find_image:查看图片
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
