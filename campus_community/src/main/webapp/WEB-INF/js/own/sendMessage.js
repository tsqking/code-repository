$(function() {
	var username = $.cookie("username");

	$("#sendDiv").click(function() {
		$("#sendModal").modal({
			keyboard : true
		});
	});
	// 首先验证收件人是否存在
	$("#inputRec").on("blur", function() {
		if ($("#inputRec").val() == null || $("#inputRec").val() == "") {
			alert("请输入收件人");
			$("#sendMessageBtn").attr({
				"disabled" : "disabled"
			});
		} else {
			var json = {
				u_nickname : $("#inputRec").val()
			}
			$.ajax({
				type : "post",
				url : "findFriend.do",
				contentType : "application/json",
				data : JSON.stringify(json),
				dataType : "json",
				success : function(data) {
					if (data.msg == "查无此人") {
						alert(data.msg);
						$("#sendMessageBtn").attr({
							"disabled" : "disabled"
						});
					} else {
						// 确认收件人存在之后，可发送信息
						$("#sendMessageBtn").removeAttr("disabled");
					}
				},
				error : function(data) {
					alert(data.msg);
				}
			});
		}
	});

	// 发送信息
	$("#sendMessageBtn").click(function() {
		if ($("#inputRec").val() == null || $("#inputRec").val() == "") {
			alert("请输入收件人");
			$("#sendMessageBtn").attr({
				"disabled" : "disabled"
			});
		} else {
			var json = {
				m_send_nickname : username,
				m_title : $("#inputMessageTitle").val(),
				m_content : $("#inputMessageContent").val(),
				m_rec_nickname : $("#inputRec").val()
			};
			$.ajax({
				type : "post",
				url : "sendMsg.do",
				contentType : "application/json",
				data : JSON.stringify(json),
				dataType : "json",
				success : function(data) {
					$("#sendModal").modal('hide');
					alert(data.msg);
				},
				error : function(data) {
					alert(data.msg);
				}
			});
		}
	});
});