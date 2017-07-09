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
	selectInitial("freeState","../system/option!getOptionsByGPVal.do?value=FREE","",false);
	//数据表格初始化
	//1.搜索按钮绑定
	dataTableSearchButton("searchButton","teacherpool","searchForm",null);
	//2.清空表单按钮绑定
	dataTableClearButton("resetButton","searchForm");
	searchData[0]={"name":"ptVo.id","value": $("#point_id").val() };
	searchData[1]={"name":"tepVo.start_time","value": $("#start_time").val() };
	searchData[2]={"name":"tepVo.end_time","value": $("#end_time").val() };
	searchData[3]={"name":"tepVo.description","value": 'Y' };
	//3.数据表格设置
	dataTable("teacherpool","../tech/teacherpool!getTeachersBySpecialPoint.do",
			[ [ 6, "asc" ] ], 
			[ {
				"mData" : "name",
				"sClass" : "center"
			}, {
				"mData" : "en_name",
				"sClass" : "center"
			}, {
				"mData" : "no",
				"sClass" : "center"
			}, {
				"mData" : "gender",
				"sClass" : "center"
			}, {
				"mData" : "email",
				"sClass" : "center"
			}, {
				"mData" : "mobile",
				"sClass" : "center"
			}, {
				"mData" : "description",
				"sClass" : "center"
			}, {
				"mData" : "nbr",
				"sClass" : "center"
			}],
			[ {
					"aTargets" : [ 7 ],
					//"mData" : "操作",
					"mRender" : function(data, type, full) {
						var detail = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+personInfo+'" onclick="detail(\''// user13:详细 - 
							+ full.nbr +'\')"><i class="fa fa-reorder"></i></a>';																			
						var reviewTeacherSkillPoint = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+knowledgeAllView+'" onclick="reviewTeacherSkillPoint(\''
							+ full.nbr+'\',\''+full.name+'\')"><i class="fa fa-tree"></i></a>';
						var chooseTeacher = '<a href="javascript:void(0);" data-toggle="tooltip" title="'+chooseToTeach+'" onclick="chooseTeacher(\''
							+ full.nbr+'\',\''+full.name+'\')"><i class="fa fa-hand-o-left"></i></a>';
						return detail+"&nbsp;&nbsp;&nbsp;"+reviewTeacherSkillPoint+"&nbsp;&nbsp;&nbsp;"+chooseTeacher;
						/*if(full.description=="空闲" || full.description=="Free")
						else
							return detail+"&nbsp;&nbsp;&nbsp;"+reviewTeacherSkillPoint;*/
					}
			} ]
	);
}

//详细信息方法

function detail(nbr){
	var index=layer.open({
		title: [
				personInfo,
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

/**
 * 教师技能知识点一览
 */
function reviewTeacherSkillPoint(nbr,name){
	var index=layer.open({
	    type: 2,
	    title: false,
	    offset: '2%',// 距离页面顶距离
	    area: [ '60%','90%'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '../tech/teacherpool!toLinkSkill_ReviewTeacherPage.do?tepVo.nbr='+nbr+'&tepVo.name='+name
	});
}
/**
 * 关闭窗口按钮
 */
$(document).on("click","#closeButton",
	function() {
		parent.parent.layer.restore(parent.index);
		parent.layer.close(index);
	}
)
/**
 * 指定某个教师授课
 * @param id
 * @param name
 */
function chooseTeacher(id,name){
	layer.confirm( chooseTeacherTipContent1+name+chooseTeacherTipContent2 , {// 确定要指定XX进行授课吗? - 
	    btn: [systemOK,systemCancel],title:[tip] //按钮// menu7:确定 - menu8:取消 - 
	}, function(){
		//确定
		parent.setTeacher(id);
		parent.parent.layer.restore(parent.index);
		parent.layer.close(index);
		//layer.close(index);
	}, function(){
	    ;
	}); 
}