var btTable;
var ini1=true;


$(function() {
	selectInitial("gender", "../system/option!getOptionsByGPVal.do?value=SEX", "", false);
	selectInitial("proj_group", "../system/option!getOptionsByGPVal.do?value=PROJ_GROUP", "", false);
	selectInitial("work_location", "../system/option!getOptionsByGPVal.do?value=LOCATION", "", false);
	selectInitial("position", "../system/option!getOptionsByGPVal.do?value=POSITION", "", false);
	selectInitial("talent_source", "../system/option!getOptionsByGPVal.do?value=TAL_SOURCE", "", false);
	selectInitial("recruit_state", "../system/option!getOptionsByGPVal.do?value=RC_STATE", "", false);
	selectInitial("univ_id", "../campusRC/talent!getUnivSelect.do", "", false);
	selectInitial("college_id","../campusRC/talent!getCollegeSelect.do?univ_id=","",false);
	$('#univ_id').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
			return;
		}
		if(ini1){
			ini1=false;
			return;
		}
		selectInitial("college_id","../campusRC/talent!getCollegeSelect.do?univ_id="+selectedOption,"",false);
	});
	//时间日期控件初始化
	dateInitial([ {"id":"create_time","type":"2"},{"id":"update_time","type":"2"},{"id":"graduate_month_start","type":"4"},{"id":"graduate_month_end","type":"4"},
	              {"id":"in_company_time","type":"6"},{"id":"in_proj_time","type":"6"} ]);
	// 初始化BootStrapTable
	btTable = BtTable('tableId', "../campusRC/talent!findTalentsPage.do", 'toolbarId', 
			[ 
			  { field : 'status',checkbox : true }, 
			  { title : talName,field : 'name',align : 'center',valign : 'middle',sortable : true }, 
			  { title : talTel,field : 'mobile',align : 'center',valign : 'middle',sortable : true }, 
			  { title : talGender,field : 'gender',align : 'center',valign : 'middle',sortable : true }, 
			  { title : talSource,field : 'talent_source',align : 'center',valign : 'middle',sortable : true }, 
			  { title : recState,field : 'recruit_state',align : 'center',valign : 'middle',sortable : true }, 
			  { title : talEdu,field : 'degree',align : 'center',valign : 'middle',sortable : true },
			  { title : talEng,field : 'english_level',align : 'center',valign : 'middle',sortable : true },
			  { title : univName,field : 'univ.name',align : 'center',valign : 'middle',sortable : true }, 
			  { title : collegeName,field : 'college.name',align : 'center',valign : 'middle',sortable : true,visible : false }, 
			  { title : graduateDate,field : 'graduate_month',align : 'center',valign : 'middle',sortable : true,visible : false },
			  { title : proj_grop,field : 'proj_group',align : 'center',valign : 'middle',sortable : true,visible : false },
			  { title : work_loc,field : 'work_location',align : 'center',valign : 'middle',sortable : true,visible : false },
			  { title : position,field : 'position',align : 'center',valign : 'middle',sortable : true,visible : false },
			  { title : in_job_time,field : 'in_company_time',align : 'center',valign : 'middle',sortable : true,visible : false }, 
			  { title : in_proj_time,field : 'in_proj_time',align : 'center',valign : 'middle',sortable : true,visible : false }, 
			  { title : createTime,field : 'create_time',align : 'center',valign : 'middle',sortable : true,visible : false }, 
			  { title : createPerson,field : 'create_person',align : 'center',valign : 'middle',sortable : true,visible : false }, 
			  { title : updateTime,field : 'update_time',align : 'center',valign : 'middle',sortable : true,visible : false }, 
			  { title : updatePerson,field : 'update_person',align : 'center',valign : 'middle',sortable : true,visible : false }
			],
			'update_time', 'desc' , null);
	btTable.Init();
	// 初始化搜索按钮
	$("#searchButton").click(function() {
		var graduate_month_start=document.getElementById("graduate_month_start").value;
		var graduate_month_end=document.getElementById("graduate_month_end").value;			
		if(graduate_month_start!=""&&graduate_month_end!=""&&graduate_month_start>graduate_month_end){
			layer.msg(msgInfo,{shift: 6});
		}else{
			btTable.searchDate('searchForm');
		}
	});
	// 初始化清空按钮
	$("#resetButton").click(function() {
		btTable.cleanDate('searchForm');
	});
	//双击事件
	$("#tableId").bootstrapTable().on('dbl-click-row.bs.table',function(e,row,$element){
		layer.open({
			title: [
					viewTalentTitle1+row.name+viewTalentTitle2,
				 	'background-color:#3C8DBC; color:#ffffff;'
			],
			offset: '0%',// 距离页面顶距离
		    type: 2,
		    area: [ '100%' , '100%'],
		    fix: false, //不固定
		    maxmin: true,
		    scrollbar: false,
		    content: "../campusRC/talent!toViewTalentPage.do?id="+row.id
		});
	});
	
	// 工具栏-新增按钮
	$("#btn_add").click(function() {
		layer.open({
			title: [
					addTalTitle,
				 	'background-color:#3C8DBC; color:#ffffff;'
			],
			offset: '0px',// 距离页面顶距离
		    type: 2,
		    area: [ '100%' , '100%'],
		    fix: false, //不固定
		    maxmin: true,
		    scrollbar: false,
		    content: "../campusRC/talent!toAddTalentPage.do"
		});
	});
	//工具栏-导出人才信息
	$("#btn_download").click(function() {
		var name=$("#name").val();
		var mobile=$("#mobile").val();
		var gender=$("#gender").val();
		var talent_source=$("#talent_source").val();
		var univ_id=$("#univ_id").val();
		var college_id=$("#college_id").val();
		var recruit_state=$("#recruit_state").val();
		var create_time=$("#create_time").val();
		var create_person=$("#create_person").val();
		var update_time=$("#update_time").val();
		var update_person=$("#update_person").val();
		var graduate_month_start=$("#graduate_month_start").val();
		var graduate_month_end=$("#graduate_month_end").val();
		var proj_group=$("#proj_group").val();
		var work_location=$("#work_location").val();
		var position=$("#position").val();
		var in_company_time=$("#in_company_time").val();
		var in_proj_time=$("#in_proj_time").val();
		var graduate_month_start=document.getElementById("graduate_month_start").value;
		var graduate_month_end=document.getElementById("graduate_month_end").value;			
		if(graduate_month_start!=""&&graduate_month_end!=""&&graduate_month_start>graduate_month_end){
			layer.msg(msgInfo,{shift: 6});
			return;
		}
		window.open("../campusRC/talent!downTalentData.do?name="+name+"&mobile="+mobile+"&gender="+gender+"&talent_source="+talent_source+"&univ.id="+univ_id+
				"&college.id="+college_id+"&recruit_state="+recruit_state+"&create_time="+create_time+"&create_person="+create_person+"&update_time="+update_time+
				"&update_person="+update_person+"&graduate_month_start="+graduate_month_start+"&graduate_month_end="+graduate_month_end+"&proj_group="+proj_group+
				"&work_location="+work_location+"&position="+position+"&in_company_time="+in_company_time+"&in_proj_time="+in_proj_time);

	});
	// 工具栏-编辑按钮
	$("#btn_edit").click(function() {
		var rows = btTable.getSelected();
		if (rows.length != 1) {
			layer.msg(chooseToEditTip);
		} else {
			var row = rows[0];
			editTalent(row.id,row.name);
		}
	});
	// 工具栏-删除按钮
	$("#btn_delete").click(function() {
		var rows = btTable.getSelected();
		if (rows.length == 0) {
			layer.msg(chooseToDelTip);
		} else {
			var id=rows[0].id;
			var name=rows[0].name;
			for (var i = 1; i < rows.length; i++) {
				id += ","+rows[i].id;
				name += ","+rows[i].name;
				//TODO Ajax调用删除数据
			}
			deleteTalent(id,name);
		}
	});
});
/**
 * 编辑人才信息
 */
function editTalent(id,name){
	layer.open({
		title: [
				editTalentTitle1+name+editTalentTitle2,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
		offset: '0%',// 距离页面顶距离
	    type: 2,
	    area: [ '100%' , '100%'],
	    fix: false, //不固定
	    maxmin: true,
	    scrollbar:false,
	    content: "../campusRC/talent!toEditTalentPage.do?id="+id
	});
}
/**
 * 删除人才
 */
function deleteTalent(id,name){
	layer.open({
		title: [
			tipTitle,
		 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    content: deleteTalentContent1+name+deleteTalentContent2,// var confirm_delete_talent="确定要删除以下人员信息吗?\n【"+name+"】";
	    btn: [confirm,cancel],
	    shadeClose: false,
	    yes: function(){
	    	$.ajax({
				url:"../campusRC/talent!deleteTalentInfo.do",
				data: {"name":id},
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.success=='true'){
						if(data.message=='-1'){//未选择删除人员
							layer.alert(deleteRtn1);//"请选择需要删除的人员！"
						}else if(data.message=='1'){
							layer.msg(deleteRtn2);//删除成功！
							btTable.refresh("searchForm");
						}else if(data.message=='0'){
							layer.alert(deleteRtn3);//删除失败！
						}
					}else{
						layer.alert(deleteRtn4);//删除出错！
						console.info(data.message);
					}
				}
			});
	    }
	});
}