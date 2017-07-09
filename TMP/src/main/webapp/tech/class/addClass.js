$(function() {

	selectInitial("location",
			"../system/option!getOptionsByGPVal.do?value=LOCATION", "D", false);
	selectInitial("direction",
			"../system/option!getOptionsByGPVal.do?value=DIRECTION", "Java",false);
	selectInitial("enable",
			"../system/option!getOptionsByGPVal.do?value=STATUS", "T", false);
	
	
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.iframeAuto(index);

	$(document).on("click", "#addButton", function() {
		var itemArr=[
		             {"id":"location","type":"1","regular":null,"message":null},
		             {"id":"enable","type":"1","regular":null,"message":null},
		             {"id":"name","type":"1","regular":null,"message":null},
		             {"id":"name_en_US","type":"1","regular":null,"message":null},
		             {"id":"session","type":"1","regular":null,"message":null},
		             {"id":"session_en_US","type":"1","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
			$.ajax({
				url : "../tech/class!addClassData.do",
				data : $("#addForm").serialize(),
				dataType : "json",
				type : "post",
				success : function(data) {
					 if(data.success=='true'){
						 if(data.message=="0000"){
							 parent.layer.msg("班级创建成功！");
							 parent.$("#classTable").DataTable().draw();
							 parent.layer.close(index);
						 }else if(data.message=="1111"){
							 layer.alert("班级创建失败，稍后重试！", {title:"反馈"});
						 }
					 }else{
						 layer.msg(data.message,{shift: 6});
					 }
				}
			});
		}
	});
})