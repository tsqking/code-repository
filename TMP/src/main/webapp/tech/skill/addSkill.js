var index = parent.layer.getFrameIndex(window.name);
$(function() {
	var hiddenType = document.getElementById("hiddenType").value;
	var skillTypeId = document.getElementById("skillTypeId").value;
	selectInitial("type",
			"../system/option!getOptionsByGPVal.do?value=SKILL_TYPE", skillTypeId, false);
	selectInitial("levelCopy",
			"../system/option!getOptionsByGPVal.do?value=SKILL_LEVEL", hiddenType,true);
	selectInitial("enable",
			"../system/option!getOptionsByGPVal.do?value=STATUS", "T", false);
	
    parent.layer.iframeAuto(index);

	$(document).on("click", "#addButton", function() {
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
				url : "../tech/skill!addSkillData.do",
				data : $("#addForm").serialize(),
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.success=='true'){
						if(data.message=='0'){
							parent.$("#skillTable").DataTable().draw();
							parent.findMenuOptions('skillFirst', '../tech/skill!findSkillOption.do', '0');
							parent.layer.msg(add_success);
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
})