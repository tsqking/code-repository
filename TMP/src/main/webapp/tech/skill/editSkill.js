$(function() {
	var hiddenType = document.getElementById("hiddenType").value;
	var hiddenLevel = document.getElementById("hiddenLevel").value;
	var hiddenEnable = document.getElementById("hiddenEnable").value;
	
	selectInitial("type",
			"../system/option!getOptionsByGPVal.do?value=SKILL_TYPE", hiddenType, false);
	selectInitial("levelCopy",
			"../system/option!getOptionsByGPVal.do?value=SKILL_LEVEL", hiddenLevel,true);
	selectInitial("enable",
			"../system/option!getOptionsByGPVal.do?value=STATUS", hiddenEnable, false);
	
	
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.iframeAuto(index);

	$(document).on("click", "#editButton", function() {
		var itemArr=[
		             {"id":"type","type":"1","regular":null,"message":null},
		             {"id":"enable","type":"1","regular":null,"message":null},
		             {"id":"name","type":"1","regular":null,"message":null},
		             {"id":"name_en_US","type":"1","regular":null,"message":null},
		             {"id":"order","type":"1","regular":null,"message":null},
		             {"id":"order","type":"2","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
			$("#levelCopy").prop("disabled", false);
			$.ajax({
				url : "../tech/skill!editSkill.do",
				data : $("#editForm").serialize(),
				dataType : "json",
				type : "post",
				success : function(data) {
					 if(data.success=='true'){
						 if(data.message=='0'){
							 parent.$("#skillTable").DataTable().draw();
							 parent.layer.msg(edit_success);
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
})