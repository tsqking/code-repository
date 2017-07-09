<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background-color:#ecf0f5">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/common/image/logo.ico" />
<s:include value="../../common.jsp"></s:include>
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/question/paper/${system_lang}.js"></script>
<title>题目部分模板</title>
</head>
<body>
	<form id="addForm">
		<!-- 试卷信息 -->
		<input type="hidden" name="paperVo.id" id="paperVo_id"/><!-- 试卷ID -->
		<input type="hidden" name="paperVo.total_time" id="paperVo_total_time"/><!-- 考试时间 -->
		<input type="hidden" name="paperVo.total_item" id="paperVo_total_item"/><!-- 试卷总题数 -->
		<input type="hidden" name="paperVo.total_score" id="paperVo_total_score"/><!-- 试卷总分数 -->
		
		<input type="hidden" name="questionIds" id="questionIds"/><!-- 该部分题目IDs -->
		<input type="hidden" name="questionPoints" id="questionPoints"/><!-- 该部分题目对应的分值 -->
		<input type="hidden" name="order" id="order"/><!-- 该部分属于该试卷的第几部分 -->
		
		<div class="box-body" id="box">
			<div class="content" id="content">
				<div class="row">
					<div class="col-md-9 col-xs-9">
					    <label for="name" style="width:100%; line-height:34px;"><s:text name="section_name"/><small><i class="fa fa-star notNull"></i></small>
				        <!-- 为了更新时不新增一个部分 -->
				        <input name="sectionVo.id" id="id" type="hidden"/>
				        <input name="sectionVo.name" id="name" type="text" class="form-control" style="width: 87%; float:right;font-weight:400;"/></label>
				    </div>
				    <div>
				    	<a class="btn btn-block btn-primary" id="deleteSection" style="width:20%;float:right;margin-right:2%;"><i class="fa fa-trash"></i>&nbsp;&nbsp;<s:text name="deleteSection"/></a>
				    </div>
				</div>
				
				<div class="row">
					<div class="col-md-12 col-xs-12">
						<div class="form-group" style="margin-top:2%;">
			            	<label for="instruction"><s:text name="section_instruction"/></label>       
			            	<div style="margin-left:4%;margin-top:-2%;">
			            		<textarea class="form-control" rows="3" name="sectionVo.instruction" id="instruction" style="width: 94%;float:right;"></textarea>
							</div>
			            </div>
			        </div>
				</div>
				
				<div class="row" style="margin-top:2%;">
					<div class="col-md-2 col-xs-2">
					    <label style="width:100%; line-height:34px;"><s:text name="info"/></label>
				    </div>
				    <div class="col-md-6 col-xs-6" style="margin-top:1%;">
				        <label style="width: 44%; float:right;font-weight:400;" id="total_item"><s:text name="total_item"/><span style="color:#f00;font-size:22px;"></span>&nbsp;&nbsp;<s:text name="number"/></label>
				        <label style="width: 44%; float:right;font-weight:400;" id="total_point"><s:text name="total_point"/><span style="color:#f00;font-size:22px;"></span>&nbsp;&nbsp;<s:text name="point"/></label>
				    </div>
				    <div style="width:20%;float:right;margin-right:2%;">
				    	<a id="selectQuestion" class="btn btn-block btn-primary" ><i class="fa fa-shopping-cart"></i>&nbsp;&nbsp;<s:text name="selectQuestions"/></a>
				    </div>
				</div>
				
				<div class="row" style="margin-top:3%;">
					<div class="col-xs-12">
						<div class="box box-primary" >
							<div class="box-body">
								<table id="paper_question_table"
									class="table table-bordered table-striped table-hover">
									<thead>
										<tr>
											<th style="text-align:center;"><s:text name="type"/></th>
											<th><s:text name="content"/></th>
											<th style="text-align:center;"><s:text name="difficulty"/></th>
											<th style="text-align:center;"><s:text name="sort"/></th>
											<th style="text-align:center;"><s:text name="setPoint"/></th>
											<th style="text-align:center;"><s:text name="option"/></th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div>
			    	<a class="btn btn-block btn-primary" id="saveSection" style="width:20%;float:right;margin-right:2%;"><i class="fa fa-save"></i>&nbsp;&nbsp;<s:text name="saveSection"/></a>
			    </div>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	var needSave=false;
	var total_point = 0;
	var question_ids = new Array();
	$(function(){
		parent.justSavePaperInfo();
		var order = $("#li_id",parent.document).val();
		var paper_id = $("#paperVo_id",parent.document).val();
		
		//页面初始化获取该部分信息
		$.ajax({
			url:"../question/paper!findQuestionBySection.do",
			data : {"order":order,"paper_id":paper_id},
			dataType:"json",
			type:"post",
			success:function(data){
				var sectionVo = data.datas.sectionVo;
				//该部分信息（ID，部分名称，部分描述）
				if(sectionVo != null){
					$("#id").val(sectionVo.id);
					$("#name").val(returnCharactor(sectionVo.name));
					$("#instruction").val(returnCharactor(sectionVo.instruction));
				}
				
				//解绑table的所有事件，否则下面会重复绑定事件
				$("table#paper_question_table").off();
				
				//题目列表
				var list = data.datas.questionList;
				if(list != null){
					for(var question in list){
						$("table#paper_question_table tbody").append('<tr>'+
								'<td>'+list[question].id+'</td>'+
								'<td style="text-align:center;">'+list[question].type+'</td>'+
								'<td>'+list[question].content+'</td>'+
								'<td style="text-align:center;">'+list[question].difficulty+'</td>'+
								'<td style="text-align:center;"><small class="up"><i class="glyphicon glyphicon-chevron-up"></i></small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small class="down"><i class="glyphicon glyphicon-chevron-down"></i></small></td>'+
								'<td style="width:8%;text-align:center;"><input type="text" value="'+list[question].question_score+'" class="point" style="height:24px;line-height:24px;width:50%;text-align:center;color:#f00;font-weight:700;">分</td>'+
								'<td style="text-align:center;"><a type="button" class="btn btn-warning btn-xs">移除</a></td>'+
						'</tr>');
						question_ids[question] = list[question].id;
						total_point += list[question].question_score;
				    }
					for(var i=0; i<question_ids.length; i++){
						console.info("question_ids["+i+"]===="+question_ids[i]);
					}
					var current_point;
					//获取当前输入框改变前的值
					$("table#paper_question_table tbody").on("focus","input.point",function(){
						current_point = Number($(this).val());
					});
					
					//分数输入框改变时，改变部分总分数和试卷总分数
					$("table#paper_question_table tbody").on("change","input.point",function(){
						var totalPoint = Number($("#total_point").find("span").text()) + Number($(this).val());
						//更改部分总分
						$("#total_point").find("span").text(totalPoint-current_point);
						//更改试卷总分
						$("#total_point",parent.document).find("span").text(Number($("#total_point",parent.document).find("span").text()) + Number($(this).val())-current_point);
						needSave=true;
					});
					
					//部分总题数和总分
					$("#total_item").find("span").text(list.length);
					$("#total_point").find("span").text(total_point);
					//隐藏表格第一列
					$("table#paper_question_table").find("tr").find("td:eq(0)").hide();
					
					//上移
					$("table#paper_question_table").on("click","small.up",function(){
						var pre_tr = $(this).parent().parent("tr").prev();
						var index = $("tbody tr").index($(this).parent().parent("tr"));
						if(index > 0){//当前tr不为第一个tr
							pre_tr.before("<tr>"+$(this).parent().parent("tr").html()+"</tr>");
							$(this).parent().parent("tr").remove();
							needSave=true;
						}
					});
					//下移
					$("table#paper_question_table").on("click","small.down",function(){
						var next_tr = $(this).parent().parent("tr").next();
						var length = $("tbody").find("tr").length;
						var index = $("tbody tr").index($(this).parent().parent("tr"));
						if(length - index > 1){//表示最后一个tr
							next_tr.after("<tr>"+$(this).parent().parent("tr").html()+"</tr>");
							$(this).parent().parent("tr").remove();
							needSave=true;
						}
					});
					//页面重新布局
					adjust();
				}
				
				//移除表格一行数据
				$("table#paper_question_table").on("click","a",function(){
					/* 
					//获取当前部分总分
					var sectionPoint = $("#total_point").find("span").text();
					//当前题目的分值
					var current_point = $(this).parent().prev("td").children("input.point").val();
					//题目ID
					var question_id = $(this).parent().parent("tr").find("td:eq(0)").text();
					//试卷ID
					var paper_id = $("#paperVo_id",parent.document).val();
					//该试卷的第几个section
					var order = $("#li_id",parent.document).val();
					$.ajax({
						url:"../question/paper!deleteQuestion.do",
						data:{"question_id":question_id,"paper_id":paper_id,"order":order},
						dataType:"json",
						type:"post",
						success:function(data){
							if(data.success=="true"){
								var totalPoint = Number($("#total_point").find("span").text()) - Number(current_point);
								if($.inArray(Number(question_id), question_ids)!=-1){//该题目的分值已保存过
									console.info("question_id====)"+question_id);
									//试卷总分
									$("#total_point",parent.document).find("span").text(Number($("#total_point",parent.document).find("span").text())-Number(sectionPoint)+Number(total_point)-Number(current_point));
								}else{
									console.info("question_id====)"+question_id);
									//试卷总分
									$("#total_point",parent.document).find("span").text(Number($("#total_point",parent.document).find("span").text())-Number(sectionPoint)+Number(total_point));
								}
								//试卷总题数
								$("#total_item",parent.document).find("span").text(Number($("#total_item",parent.document).find("span").text())-1);
								//刷新iframe
								$("#paperFrame",parent.document).attr("src",$("#paperFrame",parent.document).attr("src"));
							}
						}
					});
					//页面重新布局
					adjust(); */
					if(needSave){
						layer.open({
							title: [
								tipTitle,
							 	'background-color:#3C8DBC; color:#ffffff;'
							],
						    content: confirm_save_section_paper,// var confirm_save_section_paper="请先保存此部分数据后再操作~";
						    btn: [confirm,cancel],
						    shadeClose: false,
						    yes: function(){
						    	$("#saveSection").click();
						    	layer.msg(dataSaving);
						    }
						});
					}else{
						//获取当前部分总分
						var sectionPoint = $("#total_point").find("span").text();
						//当前题目的分值
						var current_point = $(this).parent().prev("td").children("input.point").val();
						//题目ID
						var question_id = $(this).parent().parent("tr").find("td:eq(0)").text();
						//试卷ID
						var paper_id = $("#paperVo_id",parent.document).val();
						//该试卷的第几个section
						var order = $("#li_id",parent.document).val();
						removeTR(sectionPoint,current_point,question_id,paper_id,order);
					}
				});
			}
		});
		$("#name").change( function() {
			needSave=true;
		});
		$("#instruction").change( function() {
			needSave=true;
		});
	})
	
	//删除该部分
	$("#deleteSection").on("click",function(){
		parent.layer.open({
			title: [
				delSectionTitle,
			 	'background-color:#3C8DBC; color:#ffffff;'
			],
		    content: delSectionContent,
		    btn: [confirm,cancel],
		    shadeClose: false,
		    yes: function(){
		    	//试卷ID
		    	var paper_id = $("#paperVo_id",parent.document).val();
				//第几部分
				var order = $("#li_id",parent.document).val();
				
		    	$.ajax({
					url:"../question/paper!deleteSection.do",
					data: {"paper_id":paper_id,"order":order},
					dataType:"json",
					type:"post",
					success:function(data){
						if(data.success=='true'){
							if(data.message=='0000'){
								//更新试卷题目数和总分数
								$("#total_item",parent.document).find("span").text(Number($("#total_item",parent.document).find("span").text())-Number($("#total_item").find("span").text()));
								$("#total_point",parent.document).find("span").text(Number($("#total_point",parent.document).find("span").text())-Number($("#total_point").find("span").text()));
								//更新试卷总题数以及试卷总分备份信息(试卷总题数和总分数都是由备份信息更新的)
								$("#total_items",parent.document).val($("#total_item",parent.document).find("span").text());
								$("#total_points",parent.document).val($("#total_point",parent.document).find("span").text());
								//移除该部分对应的父页面的li
								var li_id = $("#li_id",window.parent.document).val();
						    	li_id = "#"+li_id+"";
						    	$(li_id,window.parent.document).remove();
						    	$("#paperFrame",window.parent.document).attr("src","");
						    	parent.id=parent.id - 1;
						    	//删除父页面的UL
						    	parent.del_ul();
						    	//更新父页面的UL
						    	parent.update_ul(parent.id);
						    	parent.layer.msg(deleteSectionSucc);
							}else if(data.message=='1111'){
								//更新试卷题目数和总分数
								$("#total_item",parent.document).find("span").text(Number($("#total_item",parent.document).find("span").text())-Number($("#total_item").find("span").text()));
								$("#total_point",parent.document).find("span").text(Number($("#total_point",parent.document).find("span").text())-Number($("#total_point").find("span").text()));
								//移除该部分对应的父页面的li
								var li_id = $("#li_id",window.parent.document).val();
						    	li_id = "#"+li_id+"";
						    	$(li_id,window.parent.document).remove();
						    	$("#paperFrame",window.parent.document).attr("src","");
						    	parent.id=parent.id - 1;
						    	//删除父页面的UL
						    	parent.del_ul();
						    	//更新父页面的UL
						    	parent.update_ul(parent.id);
						    	parent.layer.msg(deleteSectionSucc);
							}
						}else{
					    	parent.layer.msg(deleteFail);
						}
					}
				});
		    }
		});
	});
	
	//保存该部分
	$("#saveSection").on("click",function(){
		//清空数组
		question_ids = [];
		$("#paperVo_id").val($("#paperVo_id",parent.document).val());//试卷ID
		
		//更新总题数和总分数的备份信息
		$("#total_items",parent.document).val($("#total_item",parent.document).find("span").text());
		$("#total_points",parent.document).val($("#total_point",parent.document).find("span").text());
		//题目IDs
		var questionIds = "";
		$("table#paper_question_table tbody").find("tr").each(function(){
			var questionId = $(this).find("td:eq(0)").text();
			questionIds = questionIds + questionId + ",";
		});
		$("#questionIds").val(questionIds);
		//题目分值
		var questionPoints = "";
		$("table#paper_question_table tbody").find("tr").each(function(){
			var questionPoint = $(this).find("td:eq(5)").find("input").val();
			questionPoints = questionPoints + questionPoint + ",";
		});
		$("#questionPoints").val(questionPoints);
		//第几部分
		$("#order").val($("#li_id",parent.document).val());
		$("#name").val(turnCharactor($("#name").val()));
		$("#instruction").val(turnCharactor($("#instruction").val()));
		var itemArr=[
		             {"id":"name","type":"1","regular":null,"message":null},
		             ];
		if(validate(itemArr)){
	     	$.ajax({
				url:"../question/paper!saveSection.do",
				data : $("#addForm").serialize(),
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.success=='true'){
						//得到试卷ID
						var paper_id = data.datas.paper_id;
						$("#paperVo_id",parent.document).val(paper_id);
						parent.layer.msg(saveSuccess);
						needSave=false;
						//parent.begin_total_time=$("#paperVo_total_time").val();
						parent.justSavePaperInfo();
					}else{
						parent.layer.alert(saveFail,{title:feedback});
					}
					$("#name").val(returnCharactor($("#name").val()));
					$("#instruction").val(returnCharactor($("#instruction").val()));
				}
			});
		}
	});
	
	//选择题目
	$("#selectQuestion").click(function() {
		if(needSave){
			layer.open({
				title: [
					tipTitle,
				 	'background-color:#3C8DBC; color:#ffffff;'
				],
			    content: confirm_save_section_paper,// var confirm_save_section_paper="请先保存此部分数据后再操作~";
			    btn: [confirm,cancel],
			    shadeClose: false,
			    yes: function(){
			    	$("#saveSection").click();
			    	layer.msg(dataSaving);
			    }
			});
		}else{
			parent.layer.open({
				title : [ selectQuestion, 'background-color:#3C8DBC; color:#ffffff;' ],
				type : 2,
				area : [ '100%', '100%' ],
				fix : false, // 不固定
				maxmin : true,
				scrollbar: false,
				content : '../question/paper!toSelectQuestionPage.do'
			});
		}
	});
	
	//页面重新布局
	function adjust(){
		var index = parent.layer.getFrameIndex(window.name);
		var h = document.getElementById("box").offsetHeight;
		if(h<535){
			//parent.document.getElementById("box").style.cssText="width:100%;height:100%";
			parent.document.getElementById("paperFrame").style.cssText="width:100%;height:"+h+"px";
		}else{
			parent.document.getElementById("paperFrame").style.cssText="width:100%;height:535px";
		}
		parent.layer.iframeAuto(index);
	}
	
	function removeTR(sectionPoint,current_point,question_id,paper_id,order){
		$.ajax({
			url:"../question/paper!deleteQuestion.do",
			data:{"question_id":question_id,"paper_id":paper_id,"order":order},
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.success=="true"){
					var totalPoint = Number($("#total_point").find("span").text()) - Number(current_point);//现在的
					//试卷总分
					$("#total_point",parent.document).find("span").text(Number($("#total_point",parent.document).find("span").text())-Number(current_point));
					/* if($.inArray(question_id, question_ids)!=-1){//该题目的分值已保存过
						//试卷总分
						$("#total_point",parent.document).find("span").text(Number($("#total_point",parent.document).find("span").text())-Number(sectionPoint)+Number(total_point)-Number(current_point));
					}else{
						//试卷总分
						$("#total_point",parent.document).find("span").text(Number($("#total_point",parent.document).find("span").text())-Number(sectionPoint)+Number(total_point));
					} */
					//试卷总题数
					$("#total_item",parent.document).find("span").text(Number($("#total_item",parent.document).find("span").text())-1);
					//刷新iframe
					$("#paperFrame",parent.document).attr("src",$("#paperFrame",parent.document).attr("src"));
					//parent.justSavePaperInfo();//刷新了iframe，达到了效果
				}
			}
		});
		//页面重新布局
		adjust();
	}
</script>
</html>