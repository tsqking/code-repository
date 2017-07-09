var depid=$.cookie('id');
var department=null;
//console.log(username);
$(document).ready(
		function(){
			$.ajax({
				url:'departmentInfo_findById',
				type:'POST',
				async: false,
				data:{id:depid},
				dataType:'json',
				success:function(data){
					department=data.datas.departmentInfo;
				   $('#id').val(department.id);
				   $('#name').val(department.name);
				   $('#descp').val(department.descp);
				   $('#manager').val(department.manager);
				   $('#tel').val(department.tel);
				   $('#email').val(department.email);
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
							if($('#name').val() == department.name &&
									$('#descp').val() == department.descp &&
									$('#manager').val() == department.manager &&
									$('#tel').val() == department.tel &&
									$('#email').val() == department.email)
									{
										$.ligerDialog.warn('未作任何修改,请重新修改');
										return false;
									}
							console.log("id:"+department.id);
							$.ajax({
						        type:"POST",
						        dataType:'json',
						        url:'departmentInfo_update',
						        data: $.param({id:department.id})+'&'+$(form).serialize(),
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