var rolename=$.cookie('username');
var role=null;
//console.log(rolename);
$(document).ready(
		function(){
			$.ajax({
				url:'roleInfo_findByName',
				type:'POST',
				async: false,
				data:{name:rolename},
				dataType:'json',
				success:function(data){
				 role=data.datas.roleInfo;
				   //console.log(role);
				   $('#name').val(role.name);
				   $('#create_name').val(role.create_name);
				   $('#create_time').val(role.create_time);
				   $('#update_name').val(role.update_name);
				   $('#update_time').val(role.update_time);
				   $('#description').val(role.description);
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
				console.log("id:"+role.id);
							$.ajax({
						        type:"POST",
						        dataType:'json',
						        url:'roleInfo_update',
						        data: $.param({id:role.id})+'&'+$(form).serialize(),
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