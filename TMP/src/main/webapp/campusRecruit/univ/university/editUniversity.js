var ini1=true;
var index = parent.layer.getFrameIndex(window.name);
$(function() {
    parent.layer.iframeAuto(index);
    //获取隐藏信息
    var hiddentype = document.getElementById("hiddentype").value;
	var hiddenquality = document.getElementById("hiddenquality").value;
	var hiddenprovince = document.getElementById("hiddenprovince").value;
	var province = document.getElementById("province").value;
	var hiddencity= document.getElementById("hiddencity").value;
	//初始化省市信息下拉框
	selectInitial("province", '../region/chinaregion!findRegionOption.do?chinaRegionVo.parentId=0', hiddenprovince, false);
	selectInitial("city", '../region/chinaregion!findRegionOption.do?chinaRegionVo.parentId='+hiddenprovince, hiddencity, false);
	// 获取二级联下拉框
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
    selectInitial("quality", "../system/option!getOptionsByGPVal.do?value=UNIV_PROP",hiddenquality,false);
	selectInitial("type", "../system/option!getOptionsByGPVal.do?value=UNIV_LEVEL",hiddentype,false);
	$(document).on("click", "#updateButton", function() {
		var itemArr=[
		             {"id":"name","type":"1","regular":null,"message":null},
		             {"id":"type","type":"1","regular":null,"message":null},
		             {"id":"quality","type":"1","regular":null,"message":null},
		             {"id":"country","type":"1","regular":null,"message":null},
		             {"id":"province","type":"1","regular":null,"message":null},
		             {"id":"city","type":"1","regular":null,"message":null},
		             {"id":"phone","type":"7","regular":null,"message":null},
		             {"id":"detail_addr","type":"1","regular":null,"message":null},
		             {"id":"email","type":"3","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
			layer.open({
				title : [ upadteUiversityInfo, 'background-color:#3C8DBC; color:#ffffff;' ],
				content : university_update_content,
				btn : [ confirm, cancel ],
				shadeClose : false,
				yes : function() {
			$.ajax({
				url : "../univ/university!editUniversity.do",
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

/**
 * 检查学校名称输入是否重复
 */
function checkRepetition(){
	var univName=document.getElementById("name").value;
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