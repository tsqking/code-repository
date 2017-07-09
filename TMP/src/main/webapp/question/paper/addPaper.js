var index = parent.layer.getFrameIndex(window.name);
$(function(){
	selectInitial("property","../system/option!getOptionsByGPVal.do?value=PAPER_PROP", "", false);
	selectInitial("use_flag","../system/option!getOptionsByGPVal.do?value=USE_FLAG", "", false);
    //下一步
	$("#nextButton").on("click",function(){
		var itemArr=[
		             {"id":"name1","type":"1","regular":null,"message":null},
		             {"id":"property","type":"1","regular":null,"message":null},
		             {"id":"use_flag","type":"1","regular":null,"message":null},
		             ];
		if(validate(itemArr)){
			$("#name1").val(turnCharactor($("#name1").val()));
			$("#description").val(turnCharactor($("#description").val()));
			$("#instruction").val(turnCharactor($("#instruction").val()));
			$.ajax({
				url:"../question/paper!addPaper.do",
				data : $("#addForm").serialize(),
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.success=='true'){
						 if(data.message=="0000"){
							 //得到试卷ID
							 var paper_id = data.datas.paper_id;
							 $("#paperVo_id").val(paper_id);
							 parent.layer.msg(addPaperSuc);
							 //编辑数据后刷新表格
							 parent.refreshTable();
							 parent.layer.open({
									title : [ addTitle, 'background-color:#3C8DBC; color:#ffffff;' ],
									type : 2,
									area : [ '100%', '100%' ],
									fix : false, // 不固定
									maxmin : true,
									scrollbar: false,
									content : '../question/paper!nextStep.do'+'?paper_id='+data.datas.paper_id+'&paper_name='+data.datas.paper_name+'&paper_property='+
											data.datas.paper_property+'&paperProperty='+data.datas.paperProperty+'&paper_useFlag='+data.datas.paper_useFlag
							 });
							 //关闭当前页面
							 parent.layer.close(index);
						 }else if(data.message=="1111"){
							 layer.alert(addPaperFail, {title:feedback});
						 }
					 }else{
						 layer.msg(data.message,{shift: 6});
					 }
				}
			});
		}
	});
	
})