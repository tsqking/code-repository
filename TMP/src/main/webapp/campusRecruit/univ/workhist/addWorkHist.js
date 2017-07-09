var index = parent.layer.getFrameIndex(window.name);
$(function() {
	// 初始化 操作状态 联系人 信息下拉框
	selectInitial("op_status", "../system/option!getOptionsByGPVal.do?value=OP_STATE","2",false);
	selectInitial("univ_id", '../univ/attn!findMainUniv.do?attnVo.id=0', "", false);
	// 获取二级联下拉框  总校下的分校和学院
	$('#univ_id').on( 'change', function() {
		var selectedOption = $(this).find("option:selected").val();
		var code = selectedOption;
		if (selectedOption == null || "" == selectedOption) {
			return;
		}
		selectInitial("univ_branch_id", '../univ/attn!findMainUnivBranch.do?attnVo.id='+code, "", false);
		selectInitial("college_id", '../univ/attn!findCollegeOption.do?attnVo.id='+code, "", false);
	});
	//初始化时间控件
	dateInitial([ {"id":"op_time","type":"1"}]);
    parent.layer.iframeAuto(index);
	$(document).on("click", "#addButton", function() {
		var itemArr=[
		             {"id":"op_time","type":"1","regular":null,"message":null},
		             {"id":"op_user_name","type":"1","regular":null,"message":null},
		             {"id":"univ_id","type":"1","regular":null,"message":null},
		             {"id":"work_content","type":"5_1_10","regular":null,"message":null},
		             ];
		if(validate(itemArr)){
			$.ajax({
				url : "../univ/workHist!addWorkHist.do",
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