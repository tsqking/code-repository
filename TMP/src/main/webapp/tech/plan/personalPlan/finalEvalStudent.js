//-------------------------页面加载完成后执行方法-------------------------------
//页面加载完成执行
$(function() {
	//1.初始化方法
	initial();
});
//------------------------------js方法-------------------------------------
/**
 * 初始化
 */
function initial(){
	$.ajax({
		url : "../tech/personalPlan!getFinalEvalStuList.do",
		type : "post",
		data:{
			"procVo.course_id":$("#course_id_hidden").val(),
			"procVo.class_id":$("#class_id_hidden").val()
		},
		success : function(data) {
			if (data.success == 'true') {
				var htmlText='';
				$.each(data.datas.finalEvalStuList, function(i, item){
					htmlText+='<div class="wrapper">';
						htmlText+='<div class="item">';
							htmlText+='<div class="final_eval_box">';
								htmlText+='<div class="row">';
									htmlText+='<div class="stu_name">';
										htmlText+='<strong><span id="'+item.user_id+'_flag">'+iniFlag(item.flag)+'</span>'+item.user_name+'-'+item.user_en_name+'</strong>';
									htmlText+='</div>';
								htmlText+='</div>';
								htmlText+='<div class="row" style="margin-top:.6em;" >';
									htmlText+='<div class="col-xs-4 col-md-4 text-left">';
										htmlText+='<label class="knob-label">'+generalScore+'</label>';
										htmlText+='<input type="text" class="knob" id="'+item.user_id+'_score" value="'+item.score+'" data-width="80" data-height="80" data-fgColor="#3c8dbc">';
									htmlText+='</div>';
									htmlText+='<div class="col-xs-8 col-md-8 text-left">';
										htmlText+='<div class="form-group">';
											htmlText+='<label>'+generalComment+'</label>';
											htmlText+='<textarea class="form-control" id="'+item.user_id+'_comment" rows="4" placeholder="Evaluatioon Comments ...">'+iniComment(item.comment)+'</textarea>';
										htmlText+='</div>';
									htmlText+='</div>';
								htmlText+='</div>';
								htmlText+='<div class="row" style="margin-top:.8em;">';
									htmlText+='<div class="col-md-12 col-xs-12">';
										htmlText+='<div class="col-md-7 col-xs-7">';
											htmlText+='<button type="button" class="btn btn-block btn-default btn-sm" onclick="getRef(\''+item.user_name+'-'+item.user_en_name+'\',\''+item.user_id+'\')"><li class="fa fa-link"></li>&nbsp;&nbsp;'+getPeaceRef+'</button>';
										htmlText+='</div>';
										htmlText+='<div class="col-md-4 col-xs-4 col-md-offset-1 col-xs-offset-1">';
											htmlText+='<button type="button" class="btn btn-block btn-primary btn-sm" onclick="saveEval(\''+item.user_id+'\')"><li class="fa fa-check"></li>&nbsp;&nbsp;&nbsp;'+saveButtons+'</button>';
										htmlText+='</div>';
									htmlText+='</div>';
								htmlText+='</div>';
							htmlText+='</div>';
						htmlText+='</div>';
					htmlText+='</div>';
				}); 
				$("#container").html(checkContainHtml(htmlText));
				//knob初始化
				knobInitial();
			}else{
				layer.alert(data.message);
			}
		}
	});
}
/**
 * 初始化评价与否标志
 * @param flag
 * @returns
 */
function iniFlag(flag){
	if(flag=='N')
		return '<li class="fa fa-square-o"></li>&nbsp;&nbsp;&nbsp;';
	return '<li class="fa fa-check-square-o" style="color:green;" ></li>&nbsp;&nbsp;&nbsp;';
}
/**
 * 初始化评论
 */
function iniComment(comment){
	if(comment==null || comment=="")return "";
	return comment;
}
/**
 * 空处理
 */
function checkContainHtml(htmlText){
	if(htmlText=='')
		return '<div class="row">'
					+'<div class="col-md-12 col-xs-12">'
						+'<div class="col-md-9 col-xs-9 col-md-offset-3 col-xs-offset-3">'
							+noStudentInClass
						+'</div>'
					+'</div>'
				+'</div>';
	return htmlText;
}
function knobInitial(){
	$(".knob").knob({
      /*change : function (value) {
       //console.log("change : " + value);
       },
       release : function (value) {
       console.log("release : " + value);
       },
       cancel : function () {
       console.log("cancel : " + this.value);
       },*/
      draw: function () {
        // "tron" case
        if (this.$.data('skin') == 'tron') {
          var a = this.angle(this.cv)  // Angle
              , sa = this.startAngle          // Previous start angle
              , sat = this.startAngle         // Start angle
              , ea                            // Previous end angle
              , eat = sat + a                 // End angle
              , r = true;
          this.g.lineWidth = this.lineWidth;

          this.o.cursor
          && (sat = eat - 0.3)
          && (eat = eat + 0.3);

          if (this.o.displayPrevious) {
            ea = this.startAngle + this.angle(this.value);
            this.o.cursor
            && (sa = ea - 0.3)
            && (ea = ea + 0.3);
            this.g.beginPath();
            this.g.strokeStyle = this.previousColor;
            this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sa, ea, false);
            this.g.stroke();
          }

          this.g.beginPath();
          this.g.strokeStyle = r ? this.o.fgColor : this.fgColor;
          this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sat, eat, false);
          this.g.stroke();

          this.g.lineWidth = 2;
          this.g.beginPath();
          this.g.strokeStyle = this.o.fgColor;
          this.g.arc(this.xy, this.xy, this.radius - this.lineWidth + 1 + this.lineWidth * 2 / 3, 0, 2 * Math.PI, false);
          this.g.stroke();

          return false;
        }
      }
    });
}
/**
 * 获取平时学生表现情况
 */
function getRef(combi_name,userId){
	var course_id=$("#course_id_hidden").val();
	var class_id=$("#class_id_hidden").val();
	var plan_id=$("#plan_id_hidden").val();
	var ref_index=layer.open({
	    type: 2  
	    ,title: combi_name+getPeaceTimeTile
	    ,area: ['400px', '310px']
	    ,shade: 0
	    ,offset: '16%'
	    ,content: ['../tech/personalPlan!toGetStuRefEvalPage.do?procVo.course_id='+course_id+'&procVo.class_id='+class_id+'&procVo.student_id='+userId+'&planVo.teach_plan_id='+plan_id,'no']
	    ,btn: [closButtons]
	    ,yes: function(){
	    	layer.close(ref_index);
	    }
	    ,zIndex: layer.zIndex 
	    ,success: function(layero){
	        layer.setTop(layero); 
	    }
	});
}
/**
 * 保存评价
 */
function saveEval(userId){
	var score=$("#"+userId+"_score").val();
	var comment=$("#"+userId+"_comment").val();
	$.ajax({
		url : "../tech/personalPlan!saveFinalToStuEval.do",
		type : "post",
		data:{
			"procVo.course_id":$("#course_id_hidden").val(),
			"procVo.class_id":$("#class_id_hidden").val(),
			"procVo.student_id":userId,
			"procVo.score":score,
			"procVo.comment":comment
		},
		success : function(data) {
			if(data.success=='true'){
				var code=data.datas.code;
				if(code=='1'){
					layer.msg(evalSuccess);
					changeFlag(userId);
				}else if(code=='2'){
					layer.msg(saveSuccess);
				}else if(code=='-1'){
					layer.alert(unKnowError,{title:feedback});
				}
			}else{
				layer.alert(data.message,{title:feedback});
			}
		}
	});
}
/**
 * 更新评价与否标志
 */
function changeFlag(userId){
	$("#"+userId+"_flag").html('<li class="fa fa-check-square-o" style="color:green;" ></li>&nbsp;&nbsp;&nbsp;');
}
