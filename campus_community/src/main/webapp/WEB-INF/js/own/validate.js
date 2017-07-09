var userid = $("#userid");
var nickname = $("#nickname");
var pwd1 = $("#password1");
var pwd2 = $("#password2");

$("#password2").blur(function() {
	if (pwd1.val() != pwd2.val()) {
		$("#propose").html("请输入相同的密码");
		$('#myModal').modal({
			keyboard : true
		})
	}
});
