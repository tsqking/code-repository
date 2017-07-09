var menuid = $.cookie('menuid');
var menu = null;
console.log(menuid);
$(document).ready(function() {
	$.ajax({
		url : 'menuInfo_findMenuById',
		type : 'POST',
		async : false,
		data : {
			mnu_id : menuid
		},
		dataType : 'json',
		success : function(data) {
			menu = data.datas.menuInfo;
			// console.log(user);
			$('#mnu_name').val(menu.mnu_name);
			$('#mnu_url').val(menu.mnu_url);
			$('#mnu_parent_id').val(menu.mnu_parent_id);
			$('#mnu_child_id').val(menu.mnu_child_id);
			$('#mnu_root_id').val(menu.mnu_root_id);
			$('#mnu_logo_url').val(menu.mnu_logo_url);
			$('#mnu_desc').val(menu.mnu_desc);
		}
	});
});
$(document).ready(
		function() {
			$.metadata.setType("attr", "validate");
			$("form").validate(
					{
						// 调试状态，不会提交数据的
						debug : true,
						errorPlacement : function(lable, element) {

							if (element.hasClass("l-textarea")) {
								element.addClass("l-textarea-invalid");
							} else if (element.hasClass("l-text-field")) {
								element.parent().addClass("l-text-invalid");
							}

							var nextCell = element.parents("td:first").next(
									"td");
							nextCell.find("div.l-exclamation").remove();
							$(
									'<div class="l-exclamation" title="'
											+ lable.html() + '"></div>')
									.appendTo(nextCell).ligerTip();
						},
						success : function(lable) {
							var element = $("#" + lable.attr("for"));
							var nextCell = element.parents("td:first").next(
									"td");
							if (element.hasClass("l-textarea")) {
								element.removeClass("l-textarea-invalid");
							} else if (element.hasClass("l-text-field")) {
								element.parent().removeClass("l-text-invalid");
							}
							nextCell.find("div.l-exclamation").remove();
						},
						submitHandler : function(form) {
							console.log("id:" + menu.mnu_id);
							if(
									$("#mnu_name").val()==menu.mnu_name&&
									$("#mnu_url").val()==menu.mnu_url&&
									$("#mnu_parent_id").val()==menu.mnu_parent_id&&
									$("#mnu_root_id").val()==menu.mnu_root_id&&
									$("#mnu_logo_url").val()==menu.mnu_logo_url&&
									$("#mnu_desc").val()==menu.mnu_desc
									){
								$.ligerDialog.warn("您还未做任何更改");
								return false;
							}
							$.ajax({
								type : "POST",
								dataType : 'json',
								url : 'menuInfo_update',
								data : $.param({
									mnu_id : menu.mnu_id
								}) + '&' + $(form).serialize(),
								success : function(data) {
									if (data.success == 'true') {
										var dialog = frameElement.dialog;
										dialog.close();

									} else {
										
									}
								}
							});
						}
					});
			$("form").ligerForm();

		})