var index = parent.layer.getFrameIndex(window.name);
var coopId;
$(function() {
    //parent.layer.iframeAuto(index);
	coopId = document.getElementById("coopId").value;
	//初始化联系人 合作状态 合作方式 校招状态 下拉框
	selectInitial("coopStatus", "../system/option!getOptionsByGPVal.do?value=COOP_STATE","",false);
	//初始化时间控件
	dateInitial([ {"id":"time","type":"1"}]);
	//联系人多选框
	multiSelectInitial("contacts", "../univ/coop!getContacts.do?coopVo.id="+coopId, [""], false);
})

//添加新的变更记录
function addNewHis(){
	var itemArr=[
	             {"id":"time","type":"1","regular":null,"message":null},
	             {"id":"coopStatus","type":"1","regular":null,"message":null},
	             {"id":"contacts","type":"1","regular":null,"message":null}
	             ];
	if(validate(itemArr)){
		$.ajax({
			url : "../univ/coop!addCoopHis.do",
			data : $("#addForm").serialize(),
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.success=='true'){
					if(data.message=='0'){
						parent.layer.msg(add_success);
						parent.reInitTimeLine();
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
}






















