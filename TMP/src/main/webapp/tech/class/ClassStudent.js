//---------------------------js文本全局变量----------------------------------
//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 
//搜索数据 (必须)
var searchData = [ { "name" : "more_data" , "value" : "my_value" } ];
//-------------------------页面加载完成后执行方法-------------------------------
//页面加载完成执行
var SM;
var TM;
$(function() {
	//1.初始化方法
	initial();
	getMonitor();
});
//------------------------------js方法-------------------------------------
//初始化方法
function initial() {
	//选择框初始化
	selectInitial("description","../system/option!getOptionsByGPVal.do?value=WHETHER","Y",false);
	//数据表格初始化
	//1.搜索按钮绑定
	dataTableSearchButton("searchButton","personTable","searchForm",null);
	searchData=[];
	searchData[0]={"name":"classVo.id","value": $("input[id=id]").val()};
	searchData[1]={"name":"userVo.description","value": 'Y'};
	//2.清空表单按钮绑定
	dataTableClearButton("resetButton","searchForm");
	//3.数据表格设置
	dataTable("personTable","../tech/class!getUserPage.do",
			[ [ 0, "asc" ] ], 
			[ {
				"mData" : "no",
				"sClass" : "center"
			},{
				"mData" : "exam_num",
				"sClass" : "center"
			}, {
				"mData" : "name",
				"sClass" : "center"
			}, {
				"mData" : "en_name",
				"sClass" : "center"
			}, {
				"mData" : "gender",
				"sClass" : "center"
			}, {
				"mData" : "mobile",
				"sClass" : "center"
			}, {
				"mData" : "description",
				"sClass" : "center"
			}, {
				"mData" : "id",
				"sClass" : "center"
			},],
			[ 
			   {
					"aTargets" : [ 7 ],
					//"mData" : "操作",
					"mRender" : function(data, type, full) {
						var addClass = null;
						if(full.description=="是" || full.description=="Yes"){
							var del = '<a href="javascript:void(0);" onclick="removeFromClass('+data+');" data-toggle=\"tooltip\" title=\"'+removeFromClassTip+'\"><i class="fa fa-user-times"></i></a>';
							addClass = del;
						}else{
							addClass = '<a href="javascript:void(0);" onclick="addToClass('+data+');" data-toggle=\"tooltip\" title=\"'+addIntoClassTip+'\"><i class="fa fa-user-plus"></i></a>';
						}						
						return addClass;
					}
			   }
			]
	);
	
}
function setMonitor(type){
	var id =$("input[id=id]").val();
	var typeName=(type=="student")?SMonitor:TMonitor;
	var typeValue=(type=="student")?$("#student_monitor").val():$("#teacher_monitor").val();
	layer.open({
			title: [
				setMonitorTitle,
			 	'background-color:#3C8DBC; color:#ffffff;'
			],
		    content: setContent1+typeName+setContent2,
		    btn: [confirm, cancel],
		    shadeClose: false,
		    yes: function(){
		    	$.ajax({
				url:"../tech/class!setMonitorInClass.do",
				data: {
					"classVo.description": type,
					"classVo.description_en_US": typeValue,
					"classVo.id":id
					},
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.success=='true'){
						if(data.message=='-1'){
							if(type=="student") 
								$("#student_monitor").val(SM);
							else 
								$("#teacher_monitor").val(TM);
							layer.alert(setNullFeed,{title:feedback});
						}else if(data.message=='0'){
							if(type=="student") 
								SM=typeValue;
							else 
								TM=typeValue;
							layer.alert(setSuccess,{title:feedback});
							$("#personTableTable").DataTable().draw();
						}else if(data.message=='1'){
							if(type=="student") 
								$("#student_monitor").val(SM);
							else 
								$("#teacher_monitor").val(TM);
							layer.alert(setFail,{title:feedback});
						}
					}else{
						layer.msg(data.message,{shift: 6});
					}
				}
			});
		   }
		});	
}
function addToClass(id){
	$.ajax({
		url:"../tech/class!addPersonInClass.do",
		data: {
			"classVo.id":$("input[id=id]").val(),
			"userVo.id":id
		},
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.success=='true'){
				if(data.message=='0'){
					layer.msg(addInClassSuc);
					$("#personTable").DataTable().draw();
					getMonitor();
				}else if(data.message=='1'){
					layer.alert(operationFail,{title:feedback});
				}
			}else{
				layer.msg(data.message,{shift: 6});
			}
		}
    });	
}
function removeFromClass(id){
	$.ajax({
		url:"../tech/class!deletePersonInClass.do",
		data: {
			"classVo.id":$("input[id=id]").val(),
			"userVo.id":id
		},
		dataType:"json",
		type:"get",
		success:function(data){
			if(data.success=='true'){
				if(data.message=='0'){
					layer.msg(removeFromClassSuccess);
					$("#personTable").DataTable().draw();
					getMonitor();
				}else if(data.message=='1'){
					layer.alert(operationFail,{title:feedback});
				}
			}else{
				layer.msg(data.message);
			}
		}
	});
}
function add(){
	var h = document.documentElement.clientHeight;
	var id = $("input[id=id]").val();
	layer.open({
		//"班级信息修改页面"资源文件
		title: [
				batchAddInClassTitles,
			 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    type: 2,
	    area: [ '60%' , (h-0.70*h)+'px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: "../tech/class!toAddPersonInClass.do?classVo.id="+id,
	});
}

function batchAdd(){
	$('#batchAddModel').modal('show');
}
/**
 * 上传
 */
 $(document).on("click","#batchUpload",
	function() {
	 	var id = $("input[id=id]").val();
	 	var ids = ["batchPeopleList"];
	 	var load_index=layer.load(0, {
		    shade: [0.1,'#fff']
		});
	 	ajaxFileUpload(ids,'../tech/class!batchAddUser.do?classVo.id='+id,0,
				"",null,
	    		function(data, status){
	 				layer.close(load_index);
		        	if(data.success=="true"){//上传成功
		        		$('#batchAddModel').modal('hide');
		        		$("#personTable").DataTable().draw();
		        		var content=data.datas.message;
		        		content=content.replace(new RegExp("&lt;","g"),"<");
		        		content=content.replace(new RegExp("&gt;","g"),">");
		        		layer.alert(content,{title:batchAddSucTitle});
		        		getMonitor();
		        	}else{//上传失败
		        		layer.alert(data.message,{title:feedback});
		        	}
	        	}
		);
	}
);
 /**
  * 上传接收人信息表
  */
 $(document).on("click","#batchApplyUpload",
 		function() {
 		 	var id = $("input[id=id]").val();
 		 	var ids = ["batchPeopleApplyList"];
 		 	var load_index=layer.load(0, {
 			    shade: [0.1,'#fff']
 			});
 		 	ajaxFileUpload(ids,'../tech/class!batchAddApplyInfo.do?classVo.id='+id,0,
 					"",null,
 		    		function(data, status){
 		 				layer.close(load_index);
 			        	if(data.success=="true"){//上传成功
 			        		$('#batchapplyModel').modal('hide');
 			        		//$("#personTable").DataTable().draw();
 			        		var content=data.datas.message;
 			        		content=content.replace(new RegExp("&lt;","g"),"<");
 			        		content=content.replace(new RegExp("&gt;","g"),">");
 			        		layer.alert(content,{title:batchAddSucTitle});
 			        		getMonitor();
 			        	}else{//上传失败
 			        		layer.alert(data.message,{title:feedback});
 			        	}
 		        	}
 			);
 		}
 	);
 
 
/**
 * 关闭上传界面
 */
 $(document).on("click","#closeButton",
	function() {
	 	$('#batchAddModel').modal('hide');
	}
);
 /**
  * 下载批量添加模板
  */
 function downTemplate(){
 	window.open("../system/userbatch!downTemplate.do");
 }
 
 
function getMonitor(){
	var id =$("input[id=id]").val();
	$.ajax({
		url:"../tech/class!selectMonitorInClass.do",
		data: {
			"classVo.id":id
			},
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.datas.student_monitor==0){
				$("input[id=student_monitor]").attr("value",null);
			}else{
				$("input[id=student_monitor]").attr("value",data.datas.student_monitor);
			}
			if(data.datas.teacher_monitor==0){
				$("input[id=teacher_monitor]").attr("value",null);
			}else{
				$("input[id=teacher_monitor]").attr("value",data.datas.teacher_monitor);	
			}
			$("input[id=size]").attr("value",data.datas.size);
			SM=$("#student_monitor").val();
			TM=$("#teacher_monitor").val();
		}
    });
} 
 
 

