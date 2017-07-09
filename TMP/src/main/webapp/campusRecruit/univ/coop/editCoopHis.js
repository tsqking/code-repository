var index = parent.layer.getFrameIndex(window.name);
$(function() {
    //parent.layer.iframeAuto(index);
	//初始化联系人 合作状态 合作方式 校招状态 下拉框
	selectInitial("coopStatus", "../system/option!getOptionsByGPVal.do?value=COOP_STATE","",false);	
	//初始化时间控件
	dateInitial([ {"id":"time","type":"1"}]);
	//联系人多选框
	var contacts = [];
	var i = 0;
	var contactsInfo = document.getElementById("contactsInfo").value;
	$.each(eval('(' + contactsInfo + ')'), function(i, item){  
		contacts[i] = item;
		i++;
	});
	multiSelectInitial("contacts", "../univ/coop!getContacts.do?coopVo.id="+document.getElementById("coopId").value, contacts, false);
})


//编辑变更记录
function editCoopHis(){
	var itemArr=[
	             {"id":"time","type":"1","regular":null,"message":null},
	             {"id":"coopStatus","type":"1","regular":null,"message":null},
	             {"id":"contacts","type":"1","regular":null,"message":null}
	             ];
	if(validate(itemArr)){
		$.ajax({
			url : "../univ/coop!editCoopHis.do",
			data : $("#editForm").serialize(),
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.success=='true'){
					if(data.message=='0'){
						parent.layer.msg(edit_success);
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






















