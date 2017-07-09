$(function() {
	$("#weiboPublish").click(function() {
		var json = {
			w_nickname : $.cookie("username"),
			w_content:$("#emojiContent").val(),
		}
		$.ajax({
			type : "post",
			url : "publishWeibo.do",
			contentType : "application/json",
			data : JSON.stringify(json),
			dataType : "json",
			success : function(data) {
				alert(data.msg);
			},
			error : function(data) {
				alert(data.msg);
			}
		});
	});
	
	//点击按钮触发文件选择器
	$("#imgBtn").click(function() {
		$("#imgfile").trigger("click");
	});
});