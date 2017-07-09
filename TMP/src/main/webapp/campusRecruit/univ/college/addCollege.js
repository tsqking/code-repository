var index = parent.layer.getFrameIndex(window.name);
$(function() {
    parent.layer.iframeAuto(index);
	$(document).on("click", "#addButton", function() {
		var itemArr=[
		             {"id":"collegeName","type":"1","regular":null,"message":null},
		             ];
		    if(validate(itemArr)){
			$.ajax({
				url : "../univ/college!addCollege.do",
				data : $("#addForm").serialize(),
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.success=='true'){
						if(data.message=='0'){
							parent.layer.msg(add_success);
							parent.refreshTable();
							parent.layer.close(index);
						}else if(data.message=='1'){
							parent.layer.msg(add_fail);
						}
					}else{
						layer.msg(data.message,{shift: 6});
					}
				}
			});
	  }
	});
	adjust();
})
function adjust(){
	var h = document.getElementById("box").offsetHeight;
	var bodyH=parent.document.body.scrollHeight;
	console.info(h+"  "+bodyH);
	if(h>bodyH*0.8){
		content = document.getElementById("content");
		content.style.height = bodyH*0.8+ "px";
		document.body.clientHeight= bodyH*0.8 + "px";
	}else{
		content = document.getElementById("content");
		content.style.height = (h + 10) + "px";
		document.body.clientHeight=(h + 10) + "px";
	}
	parent.layer.iframeAuto(index);
}
