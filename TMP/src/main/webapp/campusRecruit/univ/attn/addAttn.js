var index = parent.layer.getFrameIndex(window.name);
$(function() {
	// 根据code==0 查找出第一级联动数据
	selectInitial("gender", "../system/option!getOptionsByGPVal.do?value=SEX","",false);
	selectInitial("univ_id", '../univ/attn!findMainUniv.do?attnVo.id=0', "", false);
	// 获取二级联下拉框
	$('#univ_id').on( 'change', function() {
		var selectedOption = $(this).find("option:selected").val();
		var code = selectedOption;
		if (selectedOption == null || "" == selectedOption) {
			return;
		}
		selectInitial("univ_branch_id", '../univ/attn!findMainUnivBranch.do?attnVo.id='+code, "", false);
		selectInitial("college_id", '../univ/attn!findCollegeOption.do?attnVo.id='+code, "", false);
	});
	
	//校验用户名字唯一并提示
	$('#attnName').on( 'change', function(value) {
		//可以输入但是要提示信息
		var value = $('#attnName').val();
		if(null != value && "" != value){
			$.ajax({
				url : "../univ/attn!findAttnOnlyOneInfo.do",
				data : {name:value},
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.success=='true'){
						if(data.message=='1'){
							//显示信息
							var info = data.datas.info;
							layer.msg(attnInfo1+'<br/>'+info, {
								  time: 0 //不自动关闭
								  ,btn: [confirm]
								  ,yes: function(index){
									  layer.close(index);
								  }
								});
						}else if(data.message=='0'){
							//没有存在的联系人
							layer.msg(attnInfo2);
						}
					}else{
						layer.msg(data.message,{shift: 6});
					}
				}
			});
		}	
	});

    parent.layer.iframeAuto(index);
	$(document).on("click", "#addButton", function() {
		var itemArr=[
		             {"id":"attnName","type":"1","regular":null,"message":null},
		             {"id":"phone","type":"7","regular":null,"message":null},
		             {"id":"mobile","type":"4","regular":null,"message":null},
		             {"id":"univ_id","type":"1","regular":null,"message":null},
		             {"id":"gender","type":"1","regular":null,"message":null},
		             {"id":"position","type":"1","regular":null,"message":null},
		             {"id":"email","type":"3","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
			$.ajax({
				url : "../univ/attn!addAttn.do",
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
