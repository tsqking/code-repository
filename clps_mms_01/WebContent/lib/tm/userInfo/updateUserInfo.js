var username=$.cookie('username');
var user=null;
//console.log(username);
$(document).ready(
		function(){
			$.ajax({
				url:'userInfo_findByName',
				type:'POST',
				async: false,
				data:{name:username},
				dataType:'json',
				success:function(data){
				 user=data.datas.userInfo;
				   //console.log(user);
				   $('#name').val(user.name);
				   $('#nickname').val(user.nickname);
				   $('#gender').val(user.gender);
				   $('#email').val(user.email);
				   $('#mobNum').val(user.mobNum);
				   $('#department').val(user.department);
				   $('#position').val(user.position);
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
							console.log("id:"+user.id);
							$.ajax({
						        type:"POST",
						        dataType:'json',
						        url:'userInfo_update',
						        data: $.param({id:user.id})+'&'+$(form).serialize(),
						        success:function(data){
				                        if(data.success=='true') {  
				                        var dialog = frameElement.dialog;
				                        dialog.close();
				                        	 
				                    }else{

				                    } 
				                 }
						      });
						}
					});
			$("form").ligerForm();
			
		})