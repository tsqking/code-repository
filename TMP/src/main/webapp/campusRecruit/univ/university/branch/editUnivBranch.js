var ini1=true;
var index = parent.layer.getFrameIndex(window.name);
$(function() {
    parent.layer.iframeAuto(index);
    var hiddentype = document.getElementById("hiddentype").value;
	var hiddenquality = document.getElementById("hiddenquality").value;
	var hiddenprovince = document.getElementById("hiddenprovince").value;
	var hiddencity= document.getElementById("hiddencity").value;
	//初始化 省市信息下拉框
	selectInitial("province", '../region/chinaregion!findRegionOption.do?chinaRegionVo.parentId=0', hiddenprovince, false);
	selectInitial("city", '../region/chinaregion!findRegionOption.do?chinaRegionVo.parentId='+hiddenprovince, hiddencity, false);
	$('#province').on( 'change', function() {
		var selectedOption = $(this).find("option:selected").val();
		var code = selectedOption;
		if (selectedOption == null || "" == selectedOption) {
			return;
		}
		if(ini1){
			ini1=false;
			return;
		}
		selectInitial("city", '../region/chinaregion!findRegionOption.do?chinaRegionVo.parentId='+code, "", false);	
	});
	//初始化学校层次和学校性质下拉框
    selectInitial("type", "../system/option!getOptionsByGPVal.do?value=UNIV_LEVEL",hiddentype,false);
	selectInitial("quality", "../system/option!getOptionsByGPVal.do?value=UNIV_PROP",hiddenquality,false);
	$(document).on("click", "#updateButton", function() {
		var itemArr=[
		             {"id":"name","type":"1","regular":null,"message":null},
		             {"id":"website","type":"1","regular":null,"message":null},
		             {"id":"phone","type":"1","regular":null,"message":null},
		             {"id":"detail_addr","type":"1","regular":null,"message":null},
		             {"id":"email","type":"3","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
			layer.open({
				title : [ upadteUivbranchInfo, 'background-color:#3C8DBC; color:#ffffff;' ],
				content : univBranch_update_content,
				btn : [ confirm, cancel ],
				shadeClose : false,
				yes : function() {
			$.ajax({
				url : "../univ/univBranch!editUnivBranch.do",
				data : $("#editForm").serialize(),
				dataType : "json",
				type : "post",
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
					 layer.msg(edit_success);
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