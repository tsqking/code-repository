var index = parent.layer.getFrameIndex(window.name);
$(function() {
    parent.layer.iframeAuto(index);
    
    //隐藏信息 
    var hiddenid=document.getElementById("hiddenid").value;
	var hiddenuniv_id = document.getElementById("hiddenuniv_id").value;
	var hiddenuniv_branch_id = document.getElementById("hiddenuniv_branch_id").value;
	var hiddencollege_id= document.getElementById("hiddencollege_id").value;
	//根据合作方式判断是否展示邮箱信息
	var style=document.getElementById("style").value;
	if(style=="校园招聘"){
		$("#email").show();
		$("#time").show();
	}else{
		$("#email").hide();
		$("#time").hide();
	}
		
	if(hiddencollege_id!=null){
		// 初始化  学院下的联系人
		var urlinfo3="../univ/coop!findAttnList.do?college_id="+hiddencollege_id+"&coop_id="+hiddenid;
		console.info(urlinfo3);
		initTable(urlinfo3);
	}
	else{
		if(hiddenuniv_branch_id!=null){
			// 初始化 分校下的联系人
			var urlinfo2="../univ/coop!findAttnList.do?univ_branch_id="+hiddenuniv_branch_id+"&coop_id="+hiddenid;
			console.info(urlinfo2);
			initTable(urlinfo2);
		}
		else{
			// 初始化总校的联系人
			var urlinfo1="../univ/coop!findAttnList.do?univ_id="+hiddenuniv_id+"&coop_id="+hiddenid;
			console.info(urlinfo1);
			initTable(urlinfo2);
		}
	}
	refreshTable();
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
function initTable(urlinfo){
	// 初始化BootStrapTable
	btTable = DefBtTable('attnTable',urlinfo, 'toolbar',  [ {
				field : 'status',
				checkbox : true,
			}, {
				title : '联系人名',
				field : 'name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : '手机号码',
				field : 'mobile',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : '座机号码',
				field : 'phone',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '联系人邮箱',
				field : 'email',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '联系人职务',
				field : 'position',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}],
	'id', 'asc',false,null);
	btTable.searchDate("attnTable");
	btTable.Init();
}
//刷新联系人信息表
function refreshTable(){
	btTable.searchDate("editForm");
}