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
				url:"../question/paper!copyPaper.do",
				data : $("#addForm").serialize(),
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.success=='true'){
						 if(data.message=="0"){
							 parent.btTable.refresh("searchForm");
							 parent.layer.msg(copy_success);
							 //关闭当前页面
							 parent.layer.close(index); 
						 }else{
							 layer.alert(copy_fail, {title:feedback});
						 }
					 }else{
						 layer.msg(data.message,{shift: 6});
					 }
				}
			});
		}
	});
	
})