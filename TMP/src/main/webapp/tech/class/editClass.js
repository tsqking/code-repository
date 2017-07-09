$(function() {
	var hiddenDirection = document.getElementById("hiddenDirection").value;
	var hiddenLocation = document.getElementById("hiddenLocation").value;
	var hiddenEnable = document.getElementById("hiddenEnable").value;
	
	selectInitial("direction", "../system/option!getOptionsByGPVal.do?value=DIRECTION", hiddenDirection, false);
	selectInitial("location", "../system/option!getOptionsByGPVal.do?value=LOCATION", hiddenLocation,false);
	selectInitial("enable", "../system/option!getOptionsByGPVal.do?value=STATUS", hiddenEnable, false);
	
	
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.iframeAuto(index);

	$(document).on("click", "#editButton", function() {
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
				url : "../tech/class!editClass.do",
				data : $("#editForm").serialize(),
				dataType : "json",
				type : "post",
				success : function(data) {
					 if(data.success=='true'){
						 if(data.message=='0'){
							 parent.$("#classTable").DataTable().draw();
							 parent.layer.msg(updateSuc);
							 parent.layer.close(index); 
						 }else{
							 layer.alert(updateFail,{title:feedback});
						 }
					 }else{
						 layer.msg(data.message,{shift: 6});
					 }
				}
			});
		}
	});
})