var index = parent.layer.getFrameIndex(window.name);
$(function() {
    parent.layer.iframeAuto(index);
	$(document).on("click", "#updateButton", function() {
		var itemArr=[
		             {"id":"name","type":"1","regular":null,"message":null},
		             ];
		if(validate(itemArr)){
		layer.open({
			title : [ upadteCollegeInfo, 'background-color:#3C8DBC; color:#ffffff;' ],
			content : college_update_content,
			btn : [ confirm, cancel ],
			shadeClose : false,
			yes : function() {
			$.ajax({
				url : "../univ/college!editCollege.do",
				data : $("#editForm").serialize(),
				dataType : "json",
				type : "get",
				success : function(data) {
					 if(data.success=='true'){
						 if(data.message=='0'){
							 parent.layer.msg(edit_success);
							 parent.refreshTable();
							 parent.layer.close(index);
						 }else if(data.message=='1'){
							 parent.layer.msg(edit_fail);
						 }
					 }else{
					 layer.msg(data.message,{shift: 6});
					    }
				    }
			    });
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