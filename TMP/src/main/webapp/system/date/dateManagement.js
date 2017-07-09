var curr;
$(function() {
	/* initialize the calendar*/
	var now=new Date();
	var year=now.getFullYear();
	var month=(now.getMonth()+1)<10?("0"+(now.getMonth()+1)):(now.getMonth()+1);
	curr=year+"-"+month;
	$('#calendar').fullCalendar(
			{
				header : {
					left : 'prev,next today',
					center : 'title',
					right : ''
				},
				buttonText : {
					today : today,
		            prev: last_month,  
		            next: next_month
				},
				monthNames: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"],//month,
	            monthNamesShort: short_month, //["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],//
	            dayNames: week,  
	            dayNamesShort: short_week, 
			    dayClick : function(date, allDay, jsEvent, view) {
			    	//alert(date.format("YYYYMMDD"));
			    	var temp=$(this);
			    	if($(this).css("background-color") == 'rgb(196, 225, 255)'){//取消
						//$(this).css('background-color', 'rgb(255, 255, 255)');
			    		$.ajax({
							url:"../system/date!setHoliday.do",
							data: {"date":date.format("YYYYMMDD"),
								   "holiday":"N"},//$("#form").serialize()
							dataType:"json",
							type:"post",
							success:function(data){
								if(data.success=='true'){
									layer.msg(success_cancel_message+data.message);
									temp.css('background-color', 'rgb(255, 255, 255)');
								}else{
									layer.msg(fail_message,{shift: 6});
								}
							}
						});
	                } else {//添加
						//$(this).css('background-color', 'rgb(196, 225, 255)');
	                	$.ajax({
							url:"../system/date!setHoliday.do",
							data: {"date":date.format("YYYYMMDD"),
								   "holiday":"Y"},//$("#form").serialize()
							dataType:"json",
							type:"post",
							success:function(data){
								if(data.success=='true'){
									layer.msg(success_set_message+data.message);
									temp.css('background-color', 'rgb(196, 225, 255)');
								}else{
									layer.msg(fail_message,{shift: 6});
								}
							}
						});
	                }
				},
	});
	$('.fc-prev-button').unbind('click');
    $('.fc-next-button').unbind('click');
  
        $('.fc-prev-button').bind('click', fnMthChange);  
    $('.fc-next-button').bind('click', fnMthChange);  
    $('.fc-today-button').bind('click', fnMthChange);
    markHolidays(curr);
});

function fnMthChange() {  
    changeTime(this.innerText);  
}

function changeTime(label){
	if(label==last_month){
		$('#calendar').fullCalendar('prev');
		var year=curr.split("-")[0];
		var month=curr.split("-")[1];
		if(month >1 ){  
            month = Number(month) -1;
        }else{
            year = Number(year) - 1;
            month = 12;  
        }
        curr=year+"-"+(month<10?("0"+month):month);
	}else if(label==next_month){
		$('#calendar').fullCalendar('next');
		var year=curr.split("-")[0];
		var month=curr.split("-")[1];
		if(month < 12 ){
            month = Number(month) +1;
        }else{
            year = Number(year) + 1; 
            month = 1;
        }
        curr=year+"-"+(month<10?("0"+month):month);
	}else if(label==today){
		$('#calendar').fullCalendar('today');
		var now=new Date();
		var year=now.getFullYear();
		var month=(now.getMonth()+1)<10?("0"+(now.getMonth()+1)):(now.getMonth()+1);
		curr=year+"-"+month;
	}
	markHolidays(curr);
}

function markHolidays(curr){
	$.ajax({
		url:"../system/date!getNearHoliday.do",
		data: {"date":curr},//$("#form").serialize()
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.success=='true'){
				var holidays=data.datas.holidays;
				var days=$(".fc-day");
				for(var i=0;i<days.length;i++){
					var day=days[i].getAttribute('data-date');
					for(var j=0;j<holidays.length;j++){
						if(days[i].getAttribute('data-date') == holidays[j].date){
							days[i].setAttribute('style','background-color:rgb(196, 225 ,255)');
						}
					}
				}
			}else{
				layer.msg(data.message);
			}
		}
	});
	
}

