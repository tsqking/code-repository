var index = parent.layer.getFrameIndex(window.name);
$(function() {
	//初始化学校层次和学校性质下拉框
	selectInitial("type", "../system/option!getOptionsByGPVal.do?value=UNIV_LEVEL","",false);
	selectInitial("quality", "../system/option!getOptionsByGPVal.do?value=UNIV_PROP","",false);
	// 初始化省市信息下拉框
	selectInitial("regionName", '../region/chinaregion!findRegionOption.do?chinaRegionVo.parentId=0', "", false);
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
		             {"id":"phone","type":"7","regular":null,"message":null},
		             {"id":"detail_addr","type":"1","regular":null,"message":null},
		             {"id":"email","type":"3","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
		    var id=document.getElementById("getid").value;
			$.ajax({
				url : "../univ/univBranch!addUniversity.do?universityVo.id"+id,
				data : $("#addForm").serialize(),
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.success=='true'){
						if(data.message=='0'){
							parent.layer.msg(add_univBranch_success);
							parent.refreshTable();
							parent.layer.close(index);
						}else if(data.message=='1'){
							parent.layer.msg(add_univBranch_fail);
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
