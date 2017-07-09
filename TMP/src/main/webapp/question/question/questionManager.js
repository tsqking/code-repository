//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 
//搜索数据 (必须)
var searchData = [ { "name" : "more_data" , "value" : "my_value" } ];

//初始化技能下拉框
function findMenuOptions(optionId, url, parent_id) { 
	selectInitial(optionId, url + '?skillVo.parent_id=' + parent_id, "",false);
}
var btTable;
function refreshTable(){
	btTable.refresh("searchForm");
}
$(function() {
	//下拉框初始化
	selectInitial("type","../system/option!getOptionsByGPVal.do?value=QUST_TYPE", null, false);
	selectInitial("property","../system/option!getOptionsByGPVal.do?value=QUST_PROP", null, false);
	selectInitial("so_flag","../system/option!getOptionsByGPVal.do?value=QUST_SO", null, false);
	selectInitial("use_flag","../system/option!getOptionsByGPVal.do?value=USE_FLAG", null, false);
	selectInitial("difficulty","../system/option!getOptionsByGPVal.do?value=DIFFICULTY", null, false);
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS", null, false);
	
	multiSelectInitial("name","../question/tag!getAllTag.do",null,false);
	// 初始化BootStrapTable
	btTable = BtTable('table', "../question/question!findQuestionData.do", 'toolbar', 
			[ {
				field : 'status',
				class:'choise',
				searchable:true,
				radio : true
			}, {
				title : type,
				field : 'type',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : content,
				field : 'content',
				align : 'left',
				valign : 'middle',
				formatter : function(value,row,index){
					var tempContent=value.replaceAll("\n","<br>")
								.replaceAll("\t","&nbsp;&nbsp;&nbsp;&nbsp;")
								.replaceAll(" ","&nbsp;");//$("#temp_content").html(value).text();
					if(tempContent.length>220){
						tempContent=suitCut(tempContent,200);
					}
					return tempContent;
				}
			}, {
				title : difficulty,
				field : 'difficulty',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : so_flag,
				field : 'so_flag',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : enable,
				field : 'enable',
				align : 'center',
				valign : 'middle'
			}, {
				title : property,
				field : 'property',
				align : 'center',
				valign : 'middle',
				visible: false,
				sortable : true
			}, {
				title : use_flag,
				field : 'use_flag',
				align : 'center',
				valign : 'middle',
				visible: false,
				sortable : true
			}, {
				title : create_time,
				field : 'create_time',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : create_person,
				field : 'create_person',
				align : 'center',
				valign : 'middle',
				visible : false
			}, {
				title : update_time,
				field : 'update_time',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			}, {
				title : update_person,
				field : 'update_person',
				align : 'center',
				valign : 'middle',
				visible : false
			}],
			'update_time', 'desc',null);
	btTable.Init();
	// 初始化搜索按钮
	$("#searchButton").click(function() {
		btTable.searchDate('searchForm');
	});
	// 初始化清空按钮
	$("#resetButton").click(function() {
		btTable.cleanDate('searchForm');
	});

	//下拉框初始化
	findMenuOptions('first_skill_id', '../tech/skill!findSkillOption.do', '0');
	//获取二级联下拉框
	$('#first_skill_id').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
			return;
		}
		selectInitial("second_skill_id","../tech/skill!findSkillOption.do?skillVo.parent_id="+selectedOption,"",false);
		var second=$("#second_skill_id").val();
		var third=$("#third_skill_id").val();
		selectInitial("point_id","../tech/point!getPointOptionBySkillIds.do?pointVo.first_skill_id="+selectedOption+"&pointVo.second_skill_id="+second+"&pointVo.third_skill_id="+third,"",false);
	});
	//获取三级下拉框
	$('#second_skill_id').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
			return;
		}
		selectInitial("third_skill_id","../tech/skill!findSkillOption.do?skillVo.parent_id="+selectedOption,"",false);
		var first=$("#first_skill_id").val();
		var third=$("#third_skill_id").val();
		selectInitial("point_id","../tech/point!getPointOptionBySkillIds.do?pointVo.first_skill_id="+first+"&pointVo.second_skill_id="+selectedOption+"&pointVo.third_skill_id="+third,"",false);
	});
	//获取四级下拉框
	$('#third_skill_id').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
			return;
		}
		var first=$("#first_skill_id").val();
		var second=$("#second_skill_id").val();
		selectInitial("point_id","../tech/point!getPointOptionBySkillIds.do?pointVo.first_skill_id="+first+"&pointVo.second_skill_id="+second+"&pointVo.third_skill_id="+selectedOption,"",false);
	});
	
	// 工具栏-新增按钮
	$("#btn_add").click(function() {
		layer.open({
			//"添加标签"资源文件
			title : [ addTitle, 'background-color:#3C8DBC; color:#ffffff;' ],
			type : 2,
			area : [ '100%', '100%' ],
			fix : false, // 不固定
			maxmin : true,
			content : '../question/question!addNewQuestionPage.do'
		});
		//编辑数据后刷新表格
		btTable.refresh("searchForm");
	});
	
	// 工具栏-编辑按钮
	$("#btn_edit").click(function() {
		var rows = btTable.getSelected();
		if (rows.length != 1) {
			layer.msg(choiseEditData);
		} else {
			var row = rows[0];
			var index = layer.open({
				title: [
						updateQuestion,
					 	'background-color:#3C8DBC; color:#ffffff;'
				],
				offset: '1%',// 距离页面顶距离
			    type: 2,
			    area: [ '100%' , '100%'],
			    fix: false, //不固定
			    maxmin: true,
			    content: "../question/question!toEditQuestionPage.do?questionVo.id="+row.id,
			});
			layer.full(index);
		}
	});
	
	// 工具栏-禁用按钮
	$("#btn_delete").on("click",function(){
		var rows = btTable.getSelected();
		if (rows.length == 0) {
			layer.msg(choiseNeedOptionData);
		} else {
			var row = rows[rows.length-1];
			if($("#btn_delete").html()=='<span class="fa fa-unlock" aria-hidden="true"></span>&nbsp;&nbsp;'+Enable){
				$.ajax({
					url:"../question/question!changeQuestionStateEnable.do",
					data: {"id":row.id},
					dataType:"json",
					type:"get",
					success:function(data){
						if(data.success=='true'){
							layer.msg(EnableQuestionSuccess);
							//编辑数据后刷新表格
							btTable.refresh("searchForm");
						}else{
							if(data.message=='-1'){//未找到数据
								layer.alert(EnableQuestionFail,{title:feedback});
							}else{
								layer.msg(data.message);
							}
						}
					}
				});
			}else if($("#btn_delete").html()=='<span class="fa fa-lock" aria-hidden="true"></span>&nbsp;&nbsp;'+Disabled){
				layer.open({
					title: [
						delQuestionTitle,
					 	'background-color:#3C8DBC; color:#ffffff;'
					],
				    content: delQuestionContent,
				    btn: [confirm,cancel],
				    shadeClose: false,
				    yes: function(){
				    	$.ajax({
							url:"../question/question!changeQuestionStateDisable.do",
							data: {"id":row.id},
							dataType:"json",
							type:"get",
							success:function(data){
								if(data.success=='true'){
									layer.msg(disableQuestionSuccess);
									//编辑数据后刷新表格
									btTable.refresh("searchForm");
								}else{
									if(data.message=='-1'){//被使用中
										layer.alert(disableQuestionFail,{title:feedback});
									}else{
										layer.msg(data.message);
									}
								}
							}
						});
				    }
				});
			}
		}
	});
	
	//根据每行的状态改变禁用和启用按钮
	$("#table").bootstrapTable({
		
//		onClickRow:function(row){
//			if(row.enable != "T" && row.enable != "启用状态" && row.enable != "Enable"){
//				$("#btn_delete").attr("class","btn btn-warning");
//				$("#btn_delete").html("<span class='glyphicon glyphicon-remove' aria-hidden='true'></span>启用");
//			}else if(row.enable != "F" && row.enable != "禁用状态" && row.enable != "Disable"){
//				$("#btn_delete").attr("class","btn btn-info");
//				$("#btn_delete").html("<span class='glyphicon glyphicon-remove' aria-hidden='true'></span>禁用");
//			}
//		},
//		
//		onDblClickRow:function(row){
//			layer.open({
//				title: [
//						questionDetail,
//					 	'background-color:#3C8DBC; color:#ffffff;'
//				],
//				offset: '1%',// 距离页面顶距离
//			    type: 2,
//			    area: [ '80%' , '95%'],
//			    fix: false, //不固定
//			    maxmin: true,
//			    content: "../question/question!toQuestionDetailPage.do?questionVo.id="+row.id,
//			});
//		}
	}).on('click-row.bs.table',function(e,row,$element){
		if(row.enable != "T" && row.enable != "启用状态" && row.enable != "Enable"){
			$("#btn_delete").attr("class","btn btn-default");
			$("#btn_delete").html("<span class='fa fa-unlock' aria-hidden='true'></span>&nbsp;&nbsp;"+Enable);
		}else if(row.enable != "F" && row.enable != "禁用状态" && row.enable != "Disable"){
			$("#btn_delete").attr("class","btn btn-default");
			$("#btn_delete").html("<span class='fa fa-lock' aria-hidden='true'></span>&nbsp;&nbsp;"+Disabled);
		}
	}).on('dbl-click-row.bs.table',function(e,row,$element){
		var index = layer.open({
			title: [
					questionDetail,
				 	'background-color:#3C8DBC; color:#ffffff;'
			],
			offset: '1%',// 距离页面顶距离
		    type: 2,
		    area: [ '80%' , '95%'],
		    fix: false, //不固定
		    maxmin: true,
		    content: "../question/question!toQuestionDetailPage.do?questionVo.id="+row.id,
		});
		layer.full(index);
	});
})
/**
 * 智能裁剪，躲避&lt;br>,&ampnbsp;
 * @param content 
 * @param length
 */
function suitCut(content,length){
	var temp=content.substr(0,length);
	var a=temp.substr(length-1,1);
	var result=temp;
	switch(a){
	case '<': 
		var checkStr=content.substr(length,3);
		if(checkStr=='br>')
			result=temp+"br> ...";
		else{
			for(var i=length;i<content.length;i++){
				var tempVar=content.substr(i,6);
				if(tempVar!='&nbsp;'){
					result+=content.substr(i,1);
				}else{
					result+=" ...";
					break;
				}
			}
		}
		break;
	case 'b':
		var beforeStr=content.substr(length-2,2);
		var behandStr=content.substr(length,2);
		if(beforeStr=='<b' && behandStr=='r>')
			result=temp+"r> ...";
		else{
			beforeStr=content.substr(length-3,3);
			behandStr=content.substr(length,3);
			if(beforeStr=='&nb' && behandStr=='sp;')
				result=temp+"sp; ...";
			else{
				for(var i=length;i<content.length;i++){
					var tempVar=content.substr(i,6);
					if(tempVar!='&nbsp;'){
						result+=content.substr(i,1);
					}else{
						result+=" ...";
						break;
					}
				}
			}
		}
		break;
	case 'r':
		var beforeStr=content.substr(length-3,3);
		var behandStr=content.substr(length,1);
		if(beforeStr=='<br' && behandStr=='>')
			result=temp+"> ...";
		else{
			for(var i=length;i<content.length;i++){
				var tempVar=content.substr(i,6);
				if(tempVar!='&nbsp;'){
					result+=content.substr(i,1);
				}else{
					result+=" ...";
					break;
				}
			}
		}
		break;
	case '&':
		var behandStr=content.substr(length,5);
		if(behandStr=='nbsp;')
			result=temp+"nbsp; ...";
		else{
			for(var i=length;i<content.length;i++){
				var tempVar=content.substr(i,6);
				if(tempVar!='&nbsp;'){
					result+=content.substr(i,1);
				}else{
					result+=" ...";
					break;
				}
			}
		}
		break;
	case 'n':
		var beforeStr=content.substr(length-2,2);
		var behandStr=content.substr(length,4);
		if(beforeStr=='&n' && behandStr=='bsp;')
			result=temp+"bsp; ...";
		else{
			for(var i=length;i<content.length;i++){
				var tempVar=content.substr(i,6);
				if(tempVar!='&nbsp;'){
					result+=content.substr(i,1);
				}else{
					result+=" ...";
					break;
				}
			}
		}
		break;
	case 's':
		var beforeStr=content.substr(length-4,4);
		var behandStr=content.substr(length,2);
		if(beforeStr=='&nbs' && behandStr=='p;')
			result=temp+"p; ...";
		else{
			for(var i=length;i<content.length;i++){
				var tempVar=content.substr(i,6);
				if(tempVar!='&nbsp;'){
					result+=content.substr(i,1);
				}else{
					result+=" ...";
					break;
				}
			}
		}
		break;
	case 'p':
		var beforeStr=content.substr(length-5,5);
		var behandStr=content.substr(length,1);
		if(beforeStr=='&nbsp' && behandStr==';')
			result=temp+"; ...";
		else{
			for(var i=length;i<content.length;i++){
				var tempVar=content.substr(i,6);
				if(tempVar!='&nbsp;'){
					result+=content.substr(i,1);
				}else{
					result+=" ...";
					break;
				}
			}
		}
		break;
	default:
		for(var i=length;i<content.length;i++){
			var tempVar=content.substr(i,6);
			var symbolVar=content.substr(i,1);
			if(tempVar!='&nbsp;'){
				//result+=content.substr(i,1);
			}else{
				result+=" ...";
				break;
			}
			if(symbolVar==',' || symbolVar=='，' || symbolVar=='.' || symbolVar=='。' || symbolVar==';' || symbolVar=='；' || symbolVar=='?' || symbolVar=='？'
				 || symbolVar=='!' || symbolVar=='！' || symbolVar==':' || symbolVar=='：' || symbolVar=='.'){
				result+=" ...";
				break;
			}else{
				//result+=content.substr(i,1);
			}
			result+=content.substr(i,1);
		}
	}
	return result;
}
//$.fn.extend({ 
//	displayPart:function () { 
//	var displayLength = 30; 
//	var text = this.text(); 
//	if (!text) return ""; 
//
//	var result = ""; 
//	var count = 0; 
//	for (var i = 0; i < displayLength; i++) { 
//	var _char = text.charAt(i); 
//	if (count >= displayLength) break; 
//	if (/[^x00-xff]/.test(_char)) count++; //双字节字符，//[u4e00-u9fa5]中文 
//
//	result += _char; 
//	count++; 
//	} 
//	if (result.length < text.length) { 
//	result += "..."; 
//	} 
//	this.text(result); 
//	} 
//}); 
//
////截取字符串
//function cutString(value,row,index) {
//	
//}