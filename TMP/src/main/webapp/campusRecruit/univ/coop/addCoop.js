var index = parent.layer.getFrameIndex(window.name);
var ini1=true;
var ini2=true;
var ini3=true;
$(function() {
	
	//初始化联系人 合作状态 合作方式 校招状态 下拉框
	selectInitial("coopStatus", "../system/option!getOptionsByGPVal.do?value=COOP_STATE","",false);
	selectInitial("coopStyle", "../system/option!getOptionsByGPVal.do?value=COOP_WAY","",false);
	
	$('#coopStyle').on( 'change', function() {
		var selectedOption = $(this).find("option:selected").val();
		var code = selectedOption;
		if (selectedOption == "5") {
			$("#recruitTime").show();
			$("#recruitEmail").show();
		}else{
			$("#recruitTime").hide();
			$("#recruitEmail").hide();
		}
		
	});
	
	//初始化时间控件
	dateInitial([ {"id":"coop_time","type":"1"},{"id":"recruit_time","type":"1"}]);
	//初始化联系人表格 对应于 总校
	selectInitial("univ_id", '../univ/attn!findMainUniv.do?attnVo.id=0', "", false);
	
	
	$('#univ_id').on( 'change', function() {
		var selectedOption = $(this).find("option:selected").val();
		var code = selectedOption;
		if (selectedOption == null || "" == selectedOption) {
			return;
		}
		var urlinfo='../univ/attn!selectAttnList.do?univName='+code;
		initTable(urlinfo);
		
		//初始化联系人表格 对应于 分校
		selectInitial("univ_branch_id", '../univ/attn!findMainUnivBranch.do?attnVo.id='+code, "", false);
		
		//初始化联系人表格 对应于 学院
		selectInitial("college_id", '../univ/attn!findCollegeOption.do?attnVo.id='+code, "", false);
	});
	$('#univ_branch_id').on( 'change', function() {
		var selectedOption = $(this).find("option:selected").val();
		var code1 = selectedOption;
		if (selectedOption == null || "" == selectedOption) {
			//为空,使用学校的联系人
			initTable('../univ/attn!selectAttnList.do?univName='+$('#univ_id').find("option:selected").val());
			return;
		}
		/*if(ini1){
			ini1=false;
			return;
		}*/
		var urlinfo='../univ/attn!selectAttnList.do?univ_branch_id='+code1;
		initTable(urlinfo);
	});
	$('#college_id').on( 'change', function() {
		var selectedOption = $(this).find("option:selected").val();
		var code2 = selectedOption;
		if (selectedOption == null || "" == selectedOption) {
			//判断学校是否为空
			var selectedOptionTem = $('#univ_branch_id').find("option:selected").val();
			if (selectedOptionTem == null || "" == selectedOptionTem) {
				//为空,使用学校的联系人
				initTable('../univ/attn!selectAttnList.do?univName='+$('#univ_id').find("option:selected").val());
			}else{
				//为空,使用分院的联系人
				initTable('../univ/attn!selectAttnList.do?univ_branch_id='+$('#univ_branch_id').find("option:selected").val());
			}			
			return;
		}
		/*if(ini2){
			ini2=false;
			return;
		}*/
		var urlinfo='../univ/attn!selectAttnList.do?college_id='+code2;
		initTable(urlinfo);
	});
	
	
	
	// 根据code==0 查找出第一级联动数据   省市信息
	selectInitial("regionName", '../region/chinaregion!findRegionOption.do?chinaRegionVo.parentId=0', "", false);
	
	// 获取二级联下拉框 省市信息
	$('#regionName').on( 'change', function() {
		var selectedOption = $(this).find("option:selected").val();
		var code = selectedOption;
		if (selectedOption == null || "" == selectedOption) {
			return;
		}
		selectInitial("secondregionName", '../region/chinaregion!findRegionOption.do?chinaRegionVo.parentId='+code, "", false);
	});
    parent.layer.iframeAuto(index);
	$(document).on("click", "#addButton", function() {
		var itemArr=[
		             {"id":"company_name","type":"1","regular":null,"message":null},
		             {"id":"secondregionName","type":"1","regular":null,"message":null},
		             {"id":"regionName","type":"1","regular":null,"message":null},
		             {"id":"country","type":"1","regular":null,"message":null},
		             {"id":"univ_id","type":"1","regular":null,"message":null},
		             {"id":"coop_time","type":"1","regular":null,"message":null},
		             ];
		if(validate(itemArr)){
			var rows = btTable.getSelected();
			 var list =""; 
			  for (var i = 0; i < rows.length; i++) {
				  if(i==0)
					  list+=rows[i].id;
				  else
					  list+=","+rows[i].id;
			  }
			  
			$.ajax({
				url : "../univ/coop!addCoop.do?coop_id="+list,
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
//联系人信息表
function initTable(urlinfo){
	// 初始化BootStrapTable
	btTable = DefBtTable('attnTable',urlinfo , 'toolbar',  [ {
				field : 'status',
				checkbox : true,
			},{
				title : info8,
				field : 'name',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			},{
				title : info9,
				field : 'univ_name',
				align : 'center',
				valign : 'middle',
				sortable : false,
				visible : false
			}, {
				title : info10,
				field : 'univ_branch_name',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : info11,
				field : 'college_name',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			},  {
				title : info12,
				field : 'gender',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : info13,
				field : 'mobile',
				align : 'center',
				valign : 'middle',
				visible : true,
				sortable : true
			}, {
				title : info14,
				field : 'phone',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : info15,
				field : 'email',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : info16,
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