var planId;
var paper_id;
$(function() {
	//获取试卷信息
	planId = document.getElementById("planId").value;
	paper_id = document.getElementById("paper_id").value;
	//设置
	$("#updateButton").click(function() {
		var itemArr=[{"id":'ratio',"type":'2',"regular":null,"message":null}];
		if(validate(itemArr)){
			$.ajax({
				url : "../tech/scoreAction!setRatio.do",
				data : $("#updateForm").serialize(),
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.success=='true'){
						if(data.message=='0'){
							parent.layer.msg(setRatio_40_set_success); // setRatio_40_set_success:设置成功!
							//刷新表格
							parent.refresh();
							//关闭窗口
							parent.layer.close(parent.layer.getFrameIndex(window.name));
						}else if(data.message=='1'){
							parent.layer.msg(setRatio_40_set_fail); // setRatio_40_set_fail:设置失败!
						}
					}else{
						layer.msg(data.message,{shift: 6});
					}
				}
			});
		}	
	});
})
