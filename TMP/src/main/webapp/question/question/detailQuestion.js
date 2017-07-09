//图片查看
function toFindImage(inputId,type){
	var imgType = 0;
	if(type=="2"){
		//选项
		if($("#type").val()!="1"&&$("#type").val()!="2"){
			//不开放
			return null;
		}
		imgType = 1;
	}
	if($("#"+inputId).val()==null||$("#"+inputId).val()==""){
		layer.msg(detailQuestion_56_no_image); // detailQuestion_56_no_image:无图片可以展示!
		return null;
	}
	var index1 = layer.open({
		title: [
			detailQuestion_56_find_image, // detailQuestion_56_find_image:查看图片
			'background-color:#3C8DBC; color:#ffffff;'
		],
		offset: '1%',// 距离页面顶距离
		type: 2,
		area: [ '100%' , '70%'],
		fix: false, //不固定
		maxmin: true,
		content: "../question/fileAction!toFindImage.do?questionVo.info="+inputId+"&questionVo.edit=0&questionVo.imgType="+imgType,
	});
	layer.full(index1);
}
