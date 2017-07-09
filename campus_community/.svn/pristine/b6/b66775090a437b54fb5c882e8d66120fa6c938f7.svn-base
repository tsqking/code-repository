$(function() {
	var username = $.cookie("username");

	$("#currentPassword").on("blur",
			function() {
				if ($("#currentPassword").val() == null
						|| $("#currentPassword").val() == "") {
					alert("请输入当前密码");
				} 
			});
});

var json = {
		u_nickname : username,
		u_password : $("#currentPassword").val()
	}
	$.ajax({
		type : "post",
		url : "checkPwd.do",
		contentType : "application/json",
		data : JSON.stringify(json),
		dataType : "json",
		success : function(data) {
			if (data.msg == "密码验证失败") {
				alert(data.msg);
			} else {
				alert(data.msg);
			}
		}
	});