var roomName=$.cookie('roomName');
var room=null;
//console.log(username);
$(document).ready(
		function(){
			$.ajax({
				url:'meetingroom_findByName',
				type:'POST',
				async: false,
				data:{name:roomName},
				dataType:'json',
				success:function(data){
				 room=data.datas.meetingroom;
				   //console.log(user);
				 $('#roomName').val(room.roomName);
				   $('#roomNumber').val(room.roomNumber);
				   $('#roomAddress').val(room.roomAddress);
				   $('#roomPhone').val(room.roomPhone);
				   $('#roomState').val(room.roomState);
				   $('#roomTopic').val(room.roomTopic);
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
							console.log("id:"+room.roomId);
							$.ajax({
						        type:"POST",
						        dataType:'json',
						        url:'meetingroom_update',
						        data: $.param({id:room.roomId})+'&'+$(form).serialize(),
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