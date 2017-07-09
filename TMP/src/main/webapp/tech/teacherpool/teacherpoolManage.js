//---------------------------js文本全局变量----------------------------------
//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 
//搜索数据 (必须)
var searchData = [ { "name" : "more_data" , "value" : "my_value" } ];



//-------------------------页面加载完成后执行方法-------------------------------
//页面加载完成执行
$(function() {
	//1.初始化方法
	initial();
});



//------------------------------js方法-------------------------------------
//初始化方法
function initial() {
	//选择框初始化
	/*selectInitial("role","../system/option!getOptionsByGPVal.do?value=USER_TYPE","",false);
	selectInitial("gender","../system/option!getOptionsByGPVal.do?value=SEX","",false);
	selectInitial("enable","../system/option!getOptionsByGPVal.do?value=STATUS","",false);
	//时间日期控件初始化
	dateInitial([ {"id":"create_time","type":"2"},{"id":"update_time","type":"2"} ]);	*/
	//数据表格初始化
	//1.搜索按钮绑定
	dataTableSearchButton("searchButton","teacherpool","searchForm",null);
	//2.清空表单按钮绑定
	dataTableClearButton("resetButton","searchForm");
	//3.数据表格设置
	dataTable("teacherpool","../tech/teacherpool!getTeacherpoolPage.do",
			[ [ 0, "asc" ] ], 
			[ {
				"mData" : "no",
				"sClass" : "center"
			},
			{
				"mData" : "name",
				"sClass" : "center"
			}, 
			{
				"mData" : "age",
				"sClass" : "center"
			}, {
				"mData" : "mobile",
				"sClass" : "center"
			}, {
				"mData" : "email",
				"sClass" : "center"
			}, 
			{
				"mData" : "teacType",
				"sClass" : "center",
				"bVisible": false,
			}, 
			{
				"mData" : "direction",
				"sClass" : "center"
			},
			 
			   {
					"aTargets" : [ 8 ],
					//"mData" : "操作",
					"mRender" : function(data, type, full) {
						var detail = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+teacher5+'" onclick="detail(\''// teacher5:教师详细- 
							+ full.nbr +'\')"><i class="fa fa-reorder"></i></a>';
						var update = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+teacher6+'" onclick="update(\''// teacher6:教师编辑 - 
							+ full.nbr +'\')"><i class="fa fa-edit"></i></a>';
						var del = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+teacher7+'" onclick="delTeacher(\''// teacher7:教师删除 - 
							+full.nbr+'\',\''+full.name+'\',\''+full.role+
							'\')"><i class="fa fa-trash"></i></a>';	
						var linkSkill = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+teacher8+'" onclick="linkSkill(\''//teacher8:教师技能管理
						+ full.nbr + '\',\''+full.name+'\',\''+full.teacType+'\')"><i class="fa fa-chain"></i></a>';
						
						var reviewTeacherSkillPoint = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+teacher9+'" onclick="reviewTeacherSkillPoint(\'' //teacher9:教师知识体系一览
						+full.nbr+'\',\''+full.name+'\')"><i class="fa fa-tree"></i></a>';
						
						var hisquery = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+teacher10+'" onclick="hisquery(\''// teacher10:教学历史查询 - 
							+ full.nbr+'\',\''+full.name +'\')"><i class=" fa fa-bookmark"></i></a>';	
						return detail+"&nbsp;&nbsp;&nbsp;"+update+"&nbsp;&nbsp;&nbsp;"+del+"&nbsp;&nbsp;&nbsp;"+linkSkill+"&nbsp;&nbsp;&nbsp;"+reviewTeacherSkillPoint+"&nbsp;&nbsp;&nbsp;"+hisquery;
					}
			   }
			]
	);
}


/**
 * 教师对应的技能知识点管理
 * @param id
 */
function linkSkill(nbr,name,teacType){
	
	var index=layer.open({
		title: [
				"["+name+"] "+teacher11,//教师技能管理
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    offset: '0%',// 距离页面顶距离
	    area: [ '100%','100%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/teacherpool!toLinkSkillPage.do?tepVo.nbr='+nbr+'&tepVo.name='+name
	});
	
	
}
//详细信息方法

function detail(nbr){
		var index=layer.open({
			title: [
					teacher13,//教师详细信息
				 	'background-color:#3C8DBC; color:#ffffff;'
			],
		    type: 2,
		    offset: '1%',// 距离页面顶距离
		    area: [ '95%','95%'],
		    fix: false, //不固定
		    maxmin: true,
		    content: '../tech/teacherpool!toTeacherDetailPage.do?tepVo.nbr='+nbr
		});
	}

//更新编辑方法
function update(nbr){
	//iframe窗
	var index = layer.open({
		title: [
				teacher29,//教师信息更改
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
		type: 2,
		area: ['95%', '95%'],
		fix: false, //不固定
		maxmin: true,
		content: '../tech/teacherpool!toEditTeacherPage.do?tepVo.nbr='+nbr
	});	
	//layer.full(index);
}

/**
 * 删除教师
 */
function delTeacher(nbr,name){
	layer.open({
		title: [
			teacher14,//删除教师
		 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    content: teacher15+"["+name+"]",
	    btn: [teacher16,teacher17],
	    shadeClose: false,
	    yes: function(){
	    	$.ajax({
				url:"../tech/teacherpool!deleteTeacher.do",
				data: {"tepVo.nbr": nbr},
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.success=='true'){
						if(data.message=='1'){
							$("#teacherpool").DataTable().draw();
							layer.msg(teacher18);//删除教师成功
						}else{
							layer.alert(del_invalid);//有关联，不能删除
						}
					}else{
						layer.msg(data.message);
					}
				}
			});
	    }
	});
}

//添加方法
function add(){
	//iframe窗
	var index = layer.open({
		title: [
				teacher26,//新增教师
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
		type: 2,
		area: ['1300px', '650px'],
		fix: false, //不固定
		maxmin: true,
		content: '../tech/teacherpool!toTeacherAdd.do'
	});	
	layer.full(index);
}

/**
 * 教学历史查询
 * @param nbr
 */
function hisquery(nbr,name){
	//iframe窗
	var index = layer.open({
		title: [
				"["+name+"]"+teacher27,//教学历史查询
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
		type: 2,
		area: ['1300px', '650px'],
		fix: false, //不固定
		maxmin: true,
		content: '../tech/teacherpool!toTeacherHistory.do?tepVo.nbr='+nbr+'&tepVo.name='+name
	});	
	layer.full(index);
}

/**
 * 教师技能知识点一览
 */
function reviewTeacherSkillPoint(nbr,name){
	var index=layer.open({
		title: [
				teacher28,//技能知识点一览
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    title: false,
	    offset: '2%',// 距离页面顶距离
	    area: ['60%','50%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/teacherpool!toLinkSkill_ReviewTeacherPage.do?tepVo.nbr='+nbr+'&tepVo.name='+name
	});
}