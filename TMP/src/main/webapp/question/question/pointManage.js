//获取窗口索引(必须)
var index = parent.layer.getFrameIndex(window.name); 
//初始化技能下拉框
function findMenuOptions(optionId, url, parent_id) { 
	selectInitial(optionId, url + '?skillVo.parent_id=' + parent_id, "",false);
}

$(function() {
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
	
	//存放已选择的题目ID
	var arr = new Array();
	
	//初始化表格内容
    var question_id = $("#id",parent.document).val();
    $.ajax({
    	url:"../question/question!findPointsByQuestion.do",
    	data:{"questionVo.id":question_id},
    	dataType:"json",
    	type:"post",
    	success:function(data){
    		if(data.success="true"){
    			var pointList = data.datas.pointList;
    			for(var i in pointList){
    				if(i!="remove"){//不做此判断的话会添加一条undefined数据
	    				$("table > tbody").append('<tr>'+
		    						'<td id="pointId">'+pointList[i].id+'</td>'+
		    						'<td>'+pointList[i].first_skill+'</td>'+
		    						'<td>'+isNull(pointList[i].second_skill)+'</td>'+
		    						'<td>'+isNull(pointList[i].third_skill)+'</td>'+
		    						'<td id="point">'+pointList[i].name+'</td>'+
		    						'<td><button type="button" class="btn btn-warning btn-xs">移除</button></td>'+
		    				'</tr>');
	    				arr[i] = pointList[i].id;
    				}
    			}
    			//隐藏表格第一列
    			$("table#pointTable tr").find("td:eq(0)").hide();
    		}
    	}
    });
	
	//添加一行记录
	$("#selectPoint").click(function(){
		var first_skill_id = $("#first_skill_id option:selected").val();
		var second_skill_id = $("#second_skill_id option:selected").val();
		var third_skill_id = $("#third_skill_id option:selected").val();
		var point_id = $("#point_id option:selected").val();
		
		if(first_skill_id==null){
			parent.layer.msg(pleaseSelectSkill);
		}else{
			$.ajax({
				url:"../question/question!findPoints.do",
				data:{"first_skill_id":first_skill_id,"second_skill_id":second_skill_id,"third_skill_id":third_skill_id,"point_id":point_id},
				dataType:"json",
				type:"post",
				success:function(data){
					var pointList = data.datas.pointList;
					for(var i in pointList){//list
						if(i!="remove"){//不做此判断的话会添加一条undefined数据
							//该知识点存在的标识
							var isExist = false;
							for(var j=0; j<arr.length; j++){
								if(arr[j]==pointList[i]["point_id"]){
									console.info("point_name===="+pointList[i]["point_name"]);
									isExist = true;
								}
							}
							if(!isExist){
								arr[arr.length] = pointList[i]["point_id"];
								$("table > tbody").append('<tr>'+
										'<td id="pointId">'+pointList[i]["point_id"]+'</td>'+
										'<td>'+pointList[i]["skill_first_name"]+'</td>'+
										'<td>'+isUndefined(pointList[i]["skill_second_name"])+'</td>'+
										'<td>'+isUndefined(pointList[i]["skill_third_name"])+'</td>'+
										'<td id="point">'+pointList[i]["point_name"]+'</td>'+
										'<td><button type="button" class="btn btn-warning btn-xs">移除</button></td>'+
								'</tr>');
							}else{
								layer.msg("【"+pointList[i]["point_name"]+"】"+alreadyInTable);
							}
						}
					}
					//隐藏表格第一列
					$("table#pointTable tr").find("td:eq(0)").hide();
				}
			});
		}
	});
	
	//移除一行记录
	$("table").on("click","button",function(){
		var id = $(this).parent().parent("tr").find("td:eq(0)").html();
		var questionId = $("#id",parent.document).val();
		var tr = $(this).parent().parent("tr");
		$.ajax({
			url:"../question/question!deletePoint.do",
			data:{"point_id":id,"questionId":questionId},
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.success=="true"){
					//根据数组中的值来移除数组中的元素
					arr.splice($.inArray(Number(id),arr),1);
					tr.remove();
				}else{
					//
				}
			}
		});
	});
	
	//点击【确认添加】按钮
	$("#confirm").on("click",function(){
		var questionId = $("#id",parent.document).val();
		var pointList = "【";
		var idList = "";
		$ ('table tr td[id="point"]').each(function(){
			pointList = pointList + this.innerHTML + "】【";
	    })
	    $ ('table tr td[id="pointId"]').each(function(){
	    	idList = idList + this.innerHTML + ",";
	    })
	    idList = idList.substr(0,idList.length-1);
	    $.ajax({
	    	url:"../question/question!savePoint.do",
	    	data:{"pointIds":idList,"questionId":questionId},
	    	dataType:"json",
	    	type:"post",
	    	success:function(data){
	    		if(data.success=="true"){
	    			parent.document.getElementById("pointName").value=pointList.substr(0,pointList.length-1);
	    			parent.document.getElementById("pointIds").value=idList.substr(0,pointList.length-1);
	    			parent.layer.close(index);
	    		}
	    	}
	    });
	});
})

function isUndefined(obj){
	return obj==undefined?"-":obj;
}
function isNull(obj){
	return obj==null?"-":obj;
}
