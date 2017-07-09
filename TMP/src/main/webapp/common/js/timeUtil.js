//判断时间前后
function timeTime(starttime,endtime){
	var date1=changeTimeString(starttime); //开始时间
	var date2=changeTimeString(endtime); //结束时间
	var date3=date2.getTime()-date1.getTime() //时间差的毫秒数
	if(date3>0){
		return "1";
	}else if(date3<0){
		return "-1";
	}else{
		return "0";
	}
}

//判断与当前时间前后
//1:目标时间在后   -1:表示当前时间在后
function timeTimeToNew(time){
	var date1=new Date(); //开始时间
	var date2=changeTimeString(time); //结束时间
	var date3=date2.getTime()-date1.getTime() //时间差的毫秒数
	if(date3>0){
		return "1";
	}else if(date3<0){
		return "-1";
	}else{
		return "0";
	}
}

//求日期差的秒数-->X小时X分钟 - fun为方法
function getHours2(starttime,endtime,fun){
	//后台获取数据
	$.ajax({
        type : "POST",
        url : "../system/date!getWorkTimeInterval.do",
        data : "&start_time="+starttime+"&end_time="+endtime,
        success:function(data){
             //成功的回调函数
        	if(data.success=='true'){
        		var r = [];
        		r[0] = data.datas.hour;
        		r[1] = data.datas.min;
        		fun(r);
        		//return r;       		
        	}else{
        		layer.msg('失败!');
        	}
        }
	});
	/*
	var date1=changeTimeString(starttime); //开始时间
	var date2=changeTimeString(endtime); //结束时间
	var date3=date2.getTime()-date1.getTime() //时间差的毫秒数
	var m = date3/(1000*60);
	var m2 = m%60;
	var h = (m-m2)/60;
	var r = [];
	r[0] = h.toFixed(0);
	r[1] = m2.toFixed(0);
	return r;
	*/
}

//获取时间差,不排除假期
function getLongTime(starttime,endtime){
	if(starttime==null||endtime==null||starttime==""||endtime==""){
		return "-";
	}
	var date1=changeTimeString(starttime); //开始时间
	var date2=changeTimeString(endtime); //结束时间
	var date3=date2.getTime()-date1.getTime() //时间差的毫秒数
	var m = date3/(1000*60);
	var m2 = m%60;
	var h = (m-m2)/60;
	var r = [];
	r[0] = 0;
	r[1] = h.toFixed(0);
	r[2] = m2.toFixed(0);
	if((r[1]*1)>24){
		var d = r[1]/24;
		var h2 = r[1]%24;
		r[0] = d.toFixed(0);
		if(r[0].length==1){
			r[0] = "0"+r[0];
		}
		r[1] = h2.toFixed(0);
		if(r[1].length==1){
			r[1] = "0"+r[1];
		}
		if(r[2].length==1){
			r[2] = "0"+r[2];
		}
		return r[0]+time1+" "+r[1]+time2+" "+r[2]+time3;
	}else{
		if(r[1].length==1){
			r[1] = "0"+r[1];
		}
		if(r[2].length==1){
			r[2] = "0"+r[2];
		}
		return r[1]+time2+" "+r[2]+time3;
	}
}

//基准时间增加小时和分钟数-->日期
function addHoursMins(starttime,hours,mins,fun){
	//后台获取数据
	var cost=((hours*3600)+(mins*60))*1000;
	$.ajax({
        type : "POST",
        url : "../system/date!getEndTime.do",
        data : "&start_time="+starttime+"&cost="+cost,
        success:function(data){
             //成功的回调函数
        	if(data.success=='true'){
        		fun(data.datas.end_time);
        	}else{
        		layer.msg('失败!');
        	}
        }
	});
	/*
	//获取时间
	var date1=changeTimeString(starttime);
	var date2=(date1.getTime()*1)+;
	var date3=new Date(date2);
	//格式化时间
	return formatTime(date3);
	*/
}

//校准时间格式
function changeTimeForTrueTime(time,fun){
	//后台获取数据
	$.ajax({
        type : "POST",
        url : "../system/date!getEndTime.do",
        data : "&start_time="+time+"&cost=0",
        success:function(data){
             //成功的回调函数
        	if(data.success=='true'){
        		fun(data.datas.start_time);
        	}else{
        		layer.msg('失败!');
        	}
        }
	});
}


//--内部方法-------------------------------------------------------------------------------------------------------------------------------//

//处理时间字符串
function changeTimeString(timeString){
	//去除字符
	if(timeString.indexOf("-")!=null && timeString.indexOf("-") != -1){
		if(timeString.length<=18){
			timeString = timeString+":000"
		}
		//系统格式 2015-12-08 10:00:00:000
		timeString = timeString.replaceAll(" ", "");
		timeString = timeString.replaceAll("-", "");
		timeString = timeString.replaceAll(":", "");
	}else{
		//数据库格式 20151208 100000000
		timeString = timeString.replaceAll(" ", "");
	}
	//切割
	var t1 = timeString.charAt(0)+timeString.charAt(1)+timeString.charAt(2)+timeString.charAt(3);
	var t2 = (timeString.charAt(4)+timeString.charAt(5))*1-1;
	var t3 = timeString.charAt(6)+timeString.charAt(7);
	var t4 = timeString.charAt(8)+timeString.charAt(9);
	var t5 = timeString.charAt(10)+timeString.charAt(11);
	var t6 = "00";
	if((timeString.length*1)>=14){
		t6 = timeString.charAt(12)+timeString.charAt(13);
	}	
	return new Date(t1,t2,t3,t4,t5,t6);	
}

//替换字符串
String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
}

//格式化时间
function formatTime(d){
	var date = d;		
	//年
	var year = date.getFullYear()+"";
	//月
	var month = (date.getMonth()+1)+"";
	if((month.length*1)<2){
		month="0"+month;
	}
	//日
	var day1 = date.getDate()+"";
	if((day1.length*1)<2){
		day1="0"+day1;
	}
	//小时
	var hour = date.getHours()+"";
	if((hour.length*1)<2){
		hour="0"+hour;
	}
	//分钟
	var minute = date.getMinutes()+"";
	if((minute.length*1)<2){
		minute="0"+minute;
	}
	//秒
	var second = date.getSeconds()+"";
	if((second.length*1)<2){
		second="0"+second;
	}
	// 
	return year+"-"+month+"-"+day1+" "+hour+":"+minute+":"+second;
}




