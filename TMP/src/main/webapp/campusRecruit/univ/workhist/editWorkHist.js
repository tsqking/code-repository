var ini1=true;
var index = parent.layer.getFrameIndex(window.name);
$(function() {
    parent.layer.iframeAuto(index);
    //获取隐藏信息 
    var hiddenuniv_id = document.getElementById("hiddenuniv_id").value;
	var hiddenuniv_branch_id = document.getElementById("hiddenuniv_branch_id").value;
	var hiddencollege_id= document.getElementById("hiddencollege_id").value;
	var hiddenop_status= document.getElementById("hiddenop_status").value;
	selectInitial("univ_id", '../univ/attn!findMainUniv.do?attnVo.id=0', hiddenuniv_id, false);
	// 获取二级联下拉框   总校下的分校，学院
	selectInitial("univ_branch_id", '../univ/attn!findMainUniv.do?attnVo.id='+hiddenuniv_id, hiddenuniv_branch_id, false);	
	selectInitial("college_id", '../univ/attn!findCollegeOption.do?attnVo.id='+hiddenuniv_id,hiddencollege_id,false);
	$('#univ_id').on( 'change', function() {
		var selectedOption = $(this).find("option:selected").val();
		var code = selectedOption;
		if (selectedOption == null || "" == selectedOption) {
			return;
		}
		if(ini1){
			ini1=false;
			return;
		}
		selectInitial("univ_branch_id", '../univ/attn!findMainUniv.do?attnVo.id='+code, "", false);	
		selectInitial("college_id", '../univ/attn!findCollegeOption.do?attnVo.id='+code,"",false);
	});
	//初始化操作状态下拉框
	selectInitial("op_status", "../system/option!getOptionsByGPVal.do?value=OP_STATE",hiddenop_status,false);
	//初始化时间控件
	dateInitial([ {"id":"op_time","type":"1"}]);
	$(document).on("click", "#updateButton", function() {
		var itemArr=[
		             {"id":"op_user_name","type":"1","regular":null,"message":null},
		             {"id":"work_content","type":"5_1_10","regular":null,"message":null},
		             ];
		if(validate(itemArr)){
			layer.open({
				title : [ upadteWorkHistInfo, 'background-color:#3C8DBC; color:#ffffff;' ],
				content : coop_workHist_content,
				btn : [ confirm, cancel ],
				shadeClose : false,
				yes : function() {
			$.ajax({
				url : "../univ/workHist!editWorkHistInfo.do",
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