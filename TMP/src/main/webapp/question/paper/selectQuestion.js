//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 
//初始化技能下拉框
function findMenuOptions(optionId, url, parent_id) { 
	selectInitial(optionId, url + '?skillVo.parent_id=' + parent_id, "",false);
}
$(function() {
	var paper_id = $("#paperVo_id",parent.document).val();
	var order = $("#order",parent.document).val();
	
	//下拉框初始化
	selectInitial("type","../system/option!getOptionsByGPVal.do?value=QUST_TYPE", null, false);
	selectInitial("so_flag","../system/option!getOptionsByGPVal.do?value=QUST_SO", null, false);
	selectInitial("difficulty","../system/option!getOptionsByGPVal.do?value=DIFFICULTY", null, false);
	
	selectInitial("name","../question/tag!getAllTag.do",null,false);
	// 初始化BootStrapTable
	btTable = BtTable('table', "../question/paper!findQuestionData.do", 'toolbar', 
			[ {
				field : 'status',
				class:'choise',
				searchable:true,
				checkbox : true
			}, {
				title : type,
				field : 'type',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : content,
				field : 'content',
				align : 'center',
				valign : 'middle',
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
				title : question_property,
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
			'update_time', 'desc',{'enable':'T',"paper_id":paper_id,"order":order});
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
	
	//添加按钮
	$("#addCommitButton").click(function() {
		var rows = btTable.getSelected();
		//试卷ID
		var paper_id = $("#paperVo_id",parent.document).val();
		//该试卷的第几个section
		var order = $("#li_id",parent.document).val();
		var questionIds = "";
		for(var i=0; i<rows.length; i++){
			questionIds = questionIds + rows[i].id + ",";
		}
		$.ajax({
			url:"../question/paper!saveQuestion.do",
			data:{"paper_id":paper_id,"order":order,"questionIds":questionIds},
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.success=="true"){
					//更新试卷总数
					$("#total_item",parent.document).find("span").text(Number($("#total_item",parent.document).find("span").text())+rows.length);
					//刷新iframe
					$("#paperFrame",parent.document).attr("src",$("#paperFrame",parent.document).attr("src"));
					parent.getFrameMethod();
					parent.layer.close(index);
				}else{
					layer.msg(saveQuestionFail);
				}
			}
		});
	});
})
