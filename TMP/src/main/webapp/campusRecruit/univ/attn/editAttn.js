var ini1=true;
var index = parent.layer.getFrameIndex(window.name);
$(function() {
    parent.layer.iframeAuto(index);
    //初始化性别下拉框
    var hiddengender = document.getElementById("hiddengender").value;
    selectInitial("gender", "../system/option!getOptionsByGPVal.do?value=SEX",hiddengender,false);
    var hiddenuniv_id = document.getElementById("hiddenuniv_id").value;
	var hiddenuniv_branch_id = document.getElementById("hiddenuniv_branch_id").value;
	var hiddencollege_id= document.getElementById("hiddencollege_id").value;
	// 初始化 总校 分校 学院信息 下拉框
	selectInitial("univ_id", '../univ/attn!findMainUniv.do?attnVo.id=0', hiddenuniv_id, false);
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
	$(document).on("click", "#updateButton", function() {
		var itemArr=[
		             {"id":"name","type":"1","regular":null,"message":null},
		             {"id":"gender","type":"1","regular":null,"message":null},
		             {"id":"univ_id","type":"1","regular":null,"message":null},
		             {"id":"phone","type":"7","regular":null,"message":null},
		             {"id":"mobile","type":"4","regular":null,"message":null},
		             {"id":"position","type":"1","regular":null,"message":null},
		             {"id":"email","type":"3","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
			layer.open({
				title : [ upadteAttnInfo, 'background-color:#3C8DBC; color:#ffffff;' ],
				content : attn_workHist_content,
				btn : [ confirm, cancel ],
				shadeClose : false,
				yes : function() {
			$.ajax({
				url : "../univ/attn!editAttn.do",
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
	
	//校验用户名字唯一并提示
	$('#name').on( 'change', function(value) {
		//可以输入但是要提示信息
		var value = $('#name').val();
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