var planId;
var student_id;
$(function() {
	//获取试卷信息
	planId = document.getElementById("planId").value;
	student_id = document.getElementById("student_id").value;
	//设置
	$("#updateButton").click(function() {
		var itemArr=[{"id":'attitude_score',"type":'2',"regular":null,"message":null}];
		if(validate(itemArr)){
			//
			var val = $("#attitude_score").val();
			if(val*1<0){
				parent.layer.msg(setAtt_29_attitude_score_small); // setAtt_29_attitude_score_small:态度分不能小于0
				$("#attitude_score").val("0");
			}else if(val*1>100){
				parent.layer.msg(setAtt_29_attitude_score_big); // setAtt_29_attitude_score_big:态度分不能大于100
				$("#attitude_score").val("100");
			}else{
				//保存
				$.ajax({
					url : "../tech/scoreAction!setAtt.do",
					data : $("#updateForm").serialize(),
					dataType : "json",
					type : "post",
					success : function(data) {
						if(data.success=='true'){
							if(data.message=='0'){
								parent.layer.msg(setAtt_29_set_success); // setAtt_29_set_success:设置成功!
								//刷新表格
								parent.refresh();
								//关闭窗口
								parent.layer.close(parent.layer.getFrameIndex(window.name));
							}else if(data.message=='1'){
								parent.layer.msg(setAtt_29_set_fail); // setAtt_29_set_fail:设置失败!
							}
						}else{
							layer.msg(data.message,{shift: 6});
						}
					}
				});
			}	
		}	
	});
})
