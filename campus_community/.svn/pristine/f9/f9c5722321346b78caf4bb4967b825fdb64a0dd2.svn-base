var username = $.cookie("username");
var inputUserid = $("#inputUserid");
var inputNickname = $("#inputNickname");
var inputName = $("#inputName");
var inputGender = $("#inputGender");
var inputAge = $("#inputAge");
var inputEmail = $("#inputEmail");
var inputAddress = $("#inputAddress");
var inputPhone = $("#inputPhone");
var inputMotto = $("#inputMotto");
var inputPasswordQues = $("#inputPasswordQues");
var inputPasswordAns = $("#inputPasswordAns");

function update() {
	var json = {
		u_nickname : username
	}
	$.ajax({
		type : "post",
		url : "self.do",
		contentType : "application/json",
		data : JSON.stringify(json),
		dataType : "json",
		success : function(data) {
			console.log(data);
			inputUserid.val(data.u_id);
			inputNickname.val(data.u_nickname);
			inputName.val(data.u_name);
			inputGender.val(data.u_gender);
			inputAge.val(data.u_age);
			inputEmail.val(data.u_email);
			inputAddress.val(data.u_address);
			inputPhone.val(data.u_phone);
			inputMotto.val(data.u_motto);
			inputPasswordQues.val(data.u_password_ques);
			inputPasswordAns.val(data.u_password_ans);

			// 如果密保问题和答案不是是空字符串,则设置文本框不可编辑
			if (data.u_password_ques != "" || data.u_password_ans != "") {
				inputPasswordQues.attr({
					"disabled" : "disabled"
				});
				inputPasswordAns.attr({
					"disabled" : "disabled"
				});
				// 否则文本框可编辑
			} else {
				inputPasswordQues.removeAttr("disabled");
				inputPasswordAns.removeAttr("disabled");
			}
		}
	});
	$('#update_self').modal({
		keyboard : true
	});

}

var update_modal = $("#update_self");
function submit() {
	var json = {
		u_nickname : inputNickname.val(),
		u_name : inputName.val(),
		u_gender : inputGender.val(),
		u_age : inputAge.val(),
		u_email : inputEmail.val(),
		u_address : inputAddress.val(),
		u_phone : inputPhone.val(),
		u_motto : inputMotto.val(),
		u_password_ques : inputPasswordQues.val(),
		u_password_ans : inputPasswordAns.val()
	}
	$.ajax({
		type : "post",
		url : "updateSelf.do",
		contentType : "application/json",
		data : JSON.stringify(json),
		dataType : "json",
		success : function(data) {
			alert(data.msg);
			update_modal.modal('hide');
			window.location.href = "self";
		},
		error : function(data) {
			alert(data.msg);
		}
	});
}
