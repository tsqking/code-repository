var index = parent.layer.getFrameIndex(window.name);
$(function() {
	selectInitial("quality", "../system/option!getOptionsByGPVal.do?value=UNIV_PROP","",false);
	selectInitial("type", "../system/option!getOptionsByGPVal.do?value=UNIV_LEVEL","",false);
	// 根据code==0 查找出第一级联动数据
	selectInitial("regionName", '../region/chinaregion!findRegionOption.do?chinaRegionVo.parentId=0', "", false);
	// 获取二级联下拉框
	$('#regionName').on( 'change', function() {
		var selectedOption = $(this).find("option:selected").val();
		var code = selectedOption;
		if (selectedOption == null || "" == selectedOption) {
			return;
		}
		selectInitial("secondregionName", '../region/chinaregion!findRegionOption.do?chinaRegionVo.parentId='+code, "", false);
	});
    parent.layer.iframeAuto(index);
	$(document).on("click", "#addButton", function() {
		var itemArr=[
		             {"id":"universityName","type":"1","regular":null,"message":null},
		             {"id":"country","type":"1","regular":null,"message":null},
		             {"id":"regionName","type":"1","regular":null,"message":null},
		             {"id":"secondregionName","type":"1","regular":null,"message":null},
		             {"id":"type","type":"1","regular":null,"message":null},
		             {"id":"quality","type":"1","regular":null,"message":null},
		             {"id":"phone","type":"7","regular":null,"message":null},
		             {"id":"detail_addr","type":"1","regular":null,"message":null},
		             {"id":"email","type":"3","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
			$.ajax({
				url : "../univ/university!addUniversity.do",
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

/**
 * 检查学校名称输入是否重复
 */
function checkRepetition(){
	var univName=document.getElementById("universityName").value;
	$.ajax({
		url : "../univ/university!checkUnivName.do",
		data:{"universityVo.name":univName},
		dataType : "json",
		type : "post",
		success : function(data) {
			if(data.message=='true'){
				layer.msg(univNameRepeat,{shift: 6});
			}
		}
	});
	
}
