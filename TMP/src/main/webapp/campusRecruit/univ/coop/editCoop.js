var index = parent.layer.getFrameIndex(window.name);
var coopId;
$(function() {
    //parent.layer.iframeAuto(index);
	reInitCoopInfo("1","contactsJson",null);
	//初始化时间线UI
	coopId = document.getElementById("coopId").value;
	reInitTimeLine();
	//绑定关闭刷新的方法
	parent.closeFun();
})

//重新刷新合作信息
function reInitCoopInfo(type,id,jsonStr){
	var contactsJsonString
	if(type=='1'){
		//联系人信息载入
		contactsJsonString = document.getElementById(id).innerHTML;
	}else{
		//联系人信息载入
		contactsJsonString = jsonStr;
	}
	var contactsJson=eval(contactsJsonString);
	var info = "";
	$.each(contactsJson, function(i, item){   
		info = info + '<p>&nbsp;&nbsp;&nbsp;&nbsp;'+
			item.name+"&nbsp;&nbsp;&nbsp;&nbsp;"+
			item.gender_name+"&nbsp;&nbsp;&nbsp;&nbsp;"+
			item.mobile+"&nbsp;&nbsp;&nbsp;&nbsp;"+
			item.phone+"&nbsp;&nbsp;&nbsp;&nbsp;"+
			item.email+"&nbsp;&nbsp;&nbsp;&nbsp;"+
			item.position+
			'</p>';
	});
	document.getElementById("contacts").innerHTML = info;
}

//重新刷新
function reInitTimeLine(){
	initTimeLine("timeLine","../univ/coop!getCoopHisTimeLine.do",{"coopId":coopId}
		,function(param){
			var contactsInfo = '<h5>'+info1+':'+param.status_name+'&nbsp&nbsp&nbsp&nbsp'+
				info2+':'+param.update_user+'&nbsp&nbsp&nbsp&nbsp'+
				info3+':'+param.remark+'&nbsp&nbsp&nbsp&nbsp'+
				"</h5>";
			var contacts = param.contactsList;
			$.each(contacts, function(i, item){  
				contactsInfo = contactsInfo + ( "<h5>"+
						item.name+"&nbsp;&nbsp;&nbsp;&nbsp;"+
						item.gender_name+"&nbsp;&nbsp;&nbsp;&nbsp;"+
						item.mobile+"&nbsp;&nbsp;&nbsp;&nbsp;"+
						item.phone+"&nbsp;&nbsp;&nbsp;&nbsp;"+
						item.email+"&nbsp;&nbsp;&nbsp;&nbsp;"+
						item.position+"</h5>");
			});
			return contactsInfo;
		},
		[{name:info4,style:null,fun:edit},{name:info37,style:"btn btn-danger btn-xs",fun:del}],
		{name:info5,fun:add});
}

//删除按钮
function del(param){
	layer.open({
		title : [ info35, 'background-color:#3C8DBC; color:#ffffff;' ],
		content : info36,
		btn : [ confirm, cancel ],
		shadeClose : false,
		yes : function() {
				$.ajax({
					url : "../univ/coop!deleteCoopHis.do",
					data: {"id":param.id,"coopId":param.coop_id},
					dataType : "json",
					type : "get",
					success : function(data) {
						if (data.success == 'true') {
							if (data.message == '1') {//删除失败
								layer.alert(fail_delete , {
									title : fail_delete_title
								});
							} else if (data.message == '0')  {//删除成功
								reInitTimeLine();
								layer.msg(success_delete);
							}
						} else {
							layer.msg(info33);
							console.info(data.message);
						}
					}
				});
		}
	});
}

//编辑按钮
function edit(param){
	var index2 = layer.open({
		title: [
				info6,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
		offset: '0%',// 距离页面顶距离
	    type: 2,
	    area: [ '900px', '488px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: "../univ/coop!toCoopHisEdit.do?coopVo.id=" + param.id,
	});
	layer.full(index2);
}

//最后添加按钮
function add(){
	var index3 = layer.open({
		title: [
				info7,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
		offset: '0%',// 距离页面顶距离
	    type: 2,
	    area: [ '500px', '488px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: "../univ/coop!toCoopHisAdd.do?coopVo.id=" + coopId,
	});
	layer.full(index3);
}

//编辑联系人按钮
function attn(){
	var index4 = layer.open({
		title: [
				info34,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
		offset: '0%',// 距离页面顶距离
	    type: 2,
	    area: [ '500px', '488px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: "../univ/coop!toCoopEditAttn.do?coopVo.id=" + coopId,
	});
	layer.full(index4);
}


















