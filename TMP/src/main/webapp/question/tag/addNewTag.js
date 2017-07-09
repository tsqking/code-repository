var index = parent.layer.getFrameIndex(window.name);

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

$(function() {
	//下拉框初始化
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS", "T", false);
	$(document).on("click", "#addButton", function() {
		var itemArr=[
		             {"id":"name","type":"1","regular":null,"message":null},
		             {"id":"enable","type":"1","regular":null,"message":null}
		             ];
		if(validate(itemArr)){
			$.ajax({
				url : "../question/tag!addNewTagData.do",
				data : $("#addForm").serialize(),
				dataType : "json",
				type : "post",
				success : function(data) {
					 if(data.success=='true'){
						 if(data.message=="0000"){
							 parent.layer.msg(addNewTagSuc);
							 try{//刷新父页面的表格
								 parent.refreshBt();
							 }catch (e) {
							 }
							 try{//刷新列表
								 //刷新标签下拉列表
								 parent.refreshTag(data.datas.id);
							 }catch (e) {
							 }
							 parent.layer.close(index);
						 }else if(data.message=="1111"){
							 layer.alert(addNewTagFail, {title:feedback});
						 }else if(data.message=="2222"){
							 layer.alert(addDuplicateFail, {title:feedback});
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