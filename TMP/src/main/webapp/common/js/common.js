/**
 * 下拉框初始化JS代码<br>
 * 若一个页面针对同一url有多个下拉框请求（单独请求每个会被拦截），可以使用逗号','同时传入多个对应的id与initialVal。如'id1,id2,...' 'ini1,ini2,...'<br>
 * @param id 下拉框id值 
 * @param url			下拉框请求数据url 如：../system/option!getOptionsByGPVal.do?value=OPTION_TYPE 
 * @param initialVal	下拉框初始化值    如："" 
 * @param disable		boolean值     如： true/false 
 */
function selectInitial(id, url, initialVal, disable) {
	$.ajax({
		url : url,
		type : "post",
		success : function(data) {
			if (data.success == 'true') {
				var opt_text = '<option value=""></option>';
				var obj = data.datas.options;
				for (var i = 0; i < obj.length; i++) {
					opt_text += ('<option value="' + obj[i].id + '">'
							+ obj[i].text + '</option>');
				}
				var ids=id.split(",");
				var initialVals;
				if(initialVals!=null)
					initialVals=initialVal.split(",");
				else{
					initialVals=new Array();
					initialVals[0]=initialVal;
				}
				for(var ii=0;ii<ids.length;ii++){
					$("#" + ids[ii] + "").html(opt_text);
					$("#" + ids[ii] + "").select2({
						placeholder : "Select a state",
						allowClear : true
					});
					//
					var tem = document.getElementById(ids[ii]).getAttribute("temValue");
					if(tem==null){
						tem = document.getElementById(ids[ii]).temValue;
					}
					if(tem!=null&&tem!=""){
						$("#" + ids[ii] + "").select2("val", tem);	
					}else{
						$("#" + ids[ii] + "").select2("val", initialVals[ii]);
					}
					$("#" + ids[ii] + "").prop("disabled", disable);
				}
			} else {
				alert(system0);// system0:下拉框初始化出错! - 
			}
		}
	});
}

/**
 * 多选下拉框初始化JS代码<br>
 * @param id			下拉框id值 
 * @param url			下拉框请求数据url 如：../system/option!getOptionsByGPVal.do?value=OPTION_TYPE 
 * @param initialVal	下拉框初始化值    如：["",""] 
 * @param disable		boolean值     如： true/false 
 */
function multiSelectInitial(id, url, initialVal, disable) {
	$.ajax({
		url : url,
		type : "post",
		success : function(data) {
			if (data.success == 'true') {
				var opt_text = '';
				var obj = data.datas.options;
				for (var i = 0; i < obj.length; i++) {
					opt_text += ('<option value="' + obj[i].id + '">'
							+ obj[i].text + '</option>');
				}
				$("#" + id + "").html(opt_text);
				$("#" + id + "").select2({
					placeholder : "Select a state"
				});
				//
				var tem = document.getElementById(id).getAttribute("temValue");
				if(tem==null){
					tem = document.getElementById(id).temValue;
				}
				if(tem!=null&&tem!=""){
					$("#" + id + "").select2("val", tem);	
				}else{
					$("#" + id + "").select2("val", initialVal);
				}
				$("#" + id + "").prop("disabled", disable);
			} else {
				alert(system0);// system0:下拉框初始化出错! - 
			}
		}
	});
}

/**
 * 根据id来插值,可以自定义id前缀后缀<br>
 * @param json:数据的json字符串<br>
 * @param disable:值为disable表示将控件设置成只读<br>
 * @param Prefix:id的前缀<br>
 * @param Suffix:id的后缀<br>
 */
function praseJson(json, disable, Prefix, Suffix) {
	if (Prefix == null) {
		Prefix = ""
	}
	if (Suffix == null) {
		Suffix = ""
	}
	$.each(json,function(n, all) {
						var input = document
								.getElementById(Prefix + n + Suffix);
						if (input != null) {
							if (input.nodeName == "SPAN"||input.nodeName == "TEXTAREA") {
								input.innerHTML = json[n];
							} else {
								// 1
								input.value = json[n];
								// 2
								var classDd = $('#' + Prefix + n + Suffix + '')
										.attr("class");
								if (classDd.indexOf("select") >= 0) {
									$('#' + Prefix + n + Suffix + '').val(
											'\''+json[n]+'\'').trigger("change");
									input.temValue = json[n];
								}
								// 3
								if (disable == "disable") {
									document.getElementById(Prefix + n + Suffix).disabled = "disabled";
								}
							}
						}
					});
}

/**
 * 初始化日期控件 type为1表示单日期控件带时间,2表示初始化双日期控件,3表示单日期控件,4表示年月单日期控件,5表示年月双日期控件<br>
 * @param ids:控件id 实例 [ {"id":"id1","type":"1"},{"id":"id2","type":"2","dir":"l"},{"id":"id2","type":"4","format":"yyyy-mm"}... ] 
 * @param id为控件id 
 * @param type为控件类型 值为1表示单日期控件 值为2表示双日期控件 值为3表示日期控件,年月日 值为4表示自定义控件   
 */
function dateInitial(ids) {
	for (var i = 0; i < ids.length; i++) {
		var id = ids[i].id;
		if((ids[i].type+"")=="1"){
			$('#'+id).datetimepicker({
		        language:  'lang',
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				forceParse: 0,
		        showMeridian: 1
		    });
		}else if((ids[i].type+"")=="3"){
			//单日期控件
			$('#'+id).datepicker({
				  format: 'yyyy-mm-dd',
				        weekStart: 1,
				        autoclose: true,
				        todayBtn: 'linked',
				        //
				        language: system2,
				        timePicker : true, //是否显示小时和分钟
				 }).on('changeDate',function(ev){
				  var startTime = ev.date.valueOf();	  
			});	
		}else if((ids[i].type+"")=="4"){
			//单日期控件
			//选择年月的    startView: 3,   minView: 3, format: 'yyyymm',  
			$('#'+id).datetimepicker({  
			        format: 'yyyy-mm',  
			         weekStart: 1,  
			         autoclose: true,  
			         startView: 3,  
			         minView: 3,  
			         language: 'cn',  
			    });
		}else if((ids[i].type+"")=="5"){
			var dir = "left";
			if(ids[i].dir!=null){
				if(ids[i].dir == "l"){
					dir = "left";
				}else{
					dir = "right";
				}
			}
			//双日期控件
			$('#'+id).daterangepicker(
					{
						// startDate: moment().startOf('day'),
						//endDate: moment(),
						//minDate: '01/01/2012',	//最小时间
						//maxDate : moment(), //最大时间 	
						showDropdowns : true,
						showWeekNumbers : false, //是否显示第几周
						timePicker : false, //是否显示小时和分钟
						timePickerIncrement : 60, //时间的增量，单位为分钟
						timePicker12Hour : false, //是否使用12小时制来显示时间
//						ranges : system1,
						opens : dir, //日期选择框的弹出位置
						buttonClasses : [ 'btn btn-default' ],
						applyClass : 'btn-small btn-primary blue',
						cancelClass : 'btn-small',
						format : 'YYYY-MM', //控件中from和to 显示的日期格式
						separator : ' ~ ',// 
						locale : {
							applyLabel : system5,// system5:确定 - 
							cancelLabel : system6,// system6:取消 - 
							fromLabel : system7,// system7:起始时间 - 
							toLabel : system8,// system8:结束时间 - 
							customRangeLabel : system9,// system9:自定义 - 
							daysOfWeek : system26,
							monthNames : system27,
							firstDay : 1
						}
					}, function(start, end, label) {//格式化日期显示框              
		             	$('#'+id+' span').html(start.format('YYYY-MM') + ' ~ ' + end.format('YYYY-MM'));// 
		           });
		
			 //设置日期菜单被选项  --开始--
			  var dateOption ;
//			  if("${riqi}"=='day') {
//				dateOption = system10;// system10:今日 - 
//			  }else if("${riqi}"=='yday') {
//					dateOption = system11;// system11:昨日 - 
//			  }else if("${riqi}"=='week'){
//					dateOption =system12;// system12:最近7日 - 
//			  }else if("${riqi}"=='month'){
//					dateOption =system13;// system13:最近30日 - 
//			  }else if("${riqi}"=='year'){
//					dateOption =system14;// system14:最近一年 - 
//			  }else{
					dateOption = system15;// system15:自定义 - 
//			  }
			  $(".daterangepicker").find("li").each(function (){
				if($(this).hasClass("active")){
					$(this).removeClass("active");
				}
				if(dateOption==$(this).html()){
					$(this).addClass("active");
				}
			  });
		  	   //设置日期菜单被选项  --结束--
		}else if((ids[i].type+"")=="2"){
			var dir = "left";
			if(ids[i].dir!=null){
				if(ids[i].dir == "l"){
					dir = "left";
				}else{
					dir = "right";
				}
			}
			//双日期控件
			$('#'+id).daterangepicker(
					{
						// startDate: moment().startOf('day'),
						//endDate: moment(),
						//minDate: '01/01/2012',	//最小时间
						//maxDate : moment(), //最大时间 
						dateLimit : {
							days : 30
						}, //起止时间的最大间隔
						showDropdowns : true,
						showWeekNumbers : false, //是否显示第几周
						timePicker : true, //是否显示小时和分钟
						timePickerIncrement : 60, //时间的增量，单位为分钟
						timePicker12Hour : false, //是否使用12小时制来显示时间
						ranges : system1,
						opens : dir, //日期选择框的弹出位置
						buttonClasses : [ 'btn btn-default' ],
						applyClass : 'btn-small btn-primary blue',
						cancelClass : 'btn-small',
						format : 'YYYY-MM-DD HH:mm:ss', //控件中from和to 显示的日期格式
						separator : ' ~ ',// 
						locale : {
							applyLabel : system5,// system5:确定 - 
							cancelLabel : system6,// system6:取消 - 
							fromLabel : system7,// system7:起始时间 - 
							toLabel : system8,// system8:结束时间 - 
							customRangeLabel : system9,// system9:自定义 - 
							daysOfWeek : system26,
							monthNames : system27,
							firstDay : 1
						}
					}, function(start, end, label) {//格式化日期显示框              
		             	$('#'+id+' span').html(start.format('YYYY-MM-DD HH:mm:ss') + ' ~ ' + end.format('YYYY-MM-DD HH:mm:ss'));// 
		           });
		
			 //设置日期菜单被选项  --开始--
			  var dateOption ;
			  if("${riqi}"=='day') {
				dateOption = system10;// system10:今日 - 
			  }else if("${riqi}"=='yday') {
					dateOption = system11;// system11:昨日 - 
			  }else if("${riqi}"=='week'){
					dateOption =system12;// system12:最近7日 - 
			  }else if("${riqi}"=='month'){
					dateOption =system13;// system13:最近30日 - 
			  }else if("${riqi}"=='year'){
					dateOption =system14;// system14:最近一年 - 
			  }else{
					dateOption = system15;// system15:自定义 - 
			  }
			  $(".daterangepicker").find("li").each(function (){
				if($(this).hasClass("active")){
					$(this).removeClass("active");
				}
				if(dateOption==$(this).html()){
					$(this).addClass("active");
				}
			  });
		  	   //设置日期菜单被选项  --结束--
		}else if((ids[i].type+"")=="6"){
			var dir = "left";
			if(ids[i].dir!=null){
				if(ids[i].dir == "l"){
					dir = "left";
				}else{
					dir = "right";
				}
			}
			//双日期控件
			$('#'+id).daterangepicker(
					{
						showDropdowns : true,
						showWeekNumbers : false, //是否显示第几周
						timePicker : false, //是否显示小时和分钟
						timePickerIncrement : 60, //时间的增量，单位为分钟
						timePicker12Hour : false, //是否使用12小时制来显示时间
						opens : dir, //日期选择框的弹出位置
						buttonClasses : [ 'btn btn-default' ],
						applyClass : 'btn-small btn-primary blue',
						cancelClass : 'btn-small',
						format : 'YYYY-MM-DD', //控件中from和to 显示的日期格式
						separator : ' ~ ',// 
						locale : {
							applyLabel : system5,// system5:确定 - 
							cancelLabel : system6,// system6:取消 - 
							fromLabel : system7,// system7:起始时间 - 
							toLabel : system8,// system8:结束时间 - 
							customRangeLabel : system9,// system9:自定义 - 
							daysOfWeek : system26,
							monthNames : system27,
							firstDay : 1
						}
					}, function(start, end, label) {//格式化日期显示框              
		             	$('#'+id+' span').html(start.format('YYYY-MM-DD') + ' ~ ' + end.format('YYYY-MM-DD'));// 
		           });
		
			 //设置日期菜单被选项  --开始--
			  var dateOption = system15;// system15:自定义 - 
			  $(".daterangepicker").find("li").each(function (){
				if($(this).hasClass("active")){
					$(this).removeClass("active");
				}
				if(dateOption==$(this).html()){
					$(this).addClass("active");
				}
			  });
		  	   //设置日期菜单被选项  --结束--
		}	
	}
}

/**
 * 数据表格的搜索按钮的实现(功能绑定)<br>
 * @param buttonId:按钮的id
 * @param tableId:数据表格的id
 * @param formId:搜索表单的id
 * @param otherData:额外的数据(没有为null) [{"name":"键1","value":"值1"},{"name":"键2","value":"值2"},...]
 */
function dataTableSearchButton(buttonId,tableId,formId,otherData){
	$('#'+buttonId).click(function(){
		var frmID = document.getElementById(formId);
		var i, queryString = "", and = "";
		// for each form's object
		var item;
		// store each form object's value
		var itemValue;
		searchData=[ { "name" : "more_data" , "value" : "my_value" } ];
		for (i = 0; i < frmID.length; i++) {
			// get form's each object
			item = frmID[i];
			if (item.name != '') {
				if (item.type == 'select-one') {
					itemValue = item.options[item.selectedIndex].value;
				} else if (item.type == 'select-multiple') {
					var tempValue = $("#"+item.id).val();
					if(tempValue==null){
						itemValue=""
					}else{
						for(var iii=0;iii<tempValue.length;iii++){
							if(iii==0){
								itemValue+=tempValue[iii];
							}else{
								itemValue+=", "+tempValue[iii];
							}
						}
					}
				} else if (item.type == 'checkbox'
						|| item.type == 'radio') {
					if (item.checked == false) {
						continue;
					}
					itemValue = item.value;
				} else if (item.type == 'button'
						|| item.type == 'submit'
						|| item.type == 'reset'
						|| item.type == 'image') {// ignore this type
					continue;
				} else {
					itemValue = item.value;
				}
				//存
				var tem = {
					"name" : item.name,
					"value" : itemValue
				};
				searchData[i] = tem;
			}
		}
		if(otherData!=null){
			//自定义数据
			for (i = 0; i < otherData.length; i++) {
				searchData[((searchData.length*1)+(i*1))] = {
						"name" : otherData[i].name,
						"value" : otherData[i].value
				};	
			}
		}	
		var tempDate=[ { "name" : "more_data" , "value" : "my_value" } ];
		var ti=0;
		for(var iiii=0;iiii<searchData.length;iiii++){
			if(searchData[iiii]!=null){
				tempDate[ti++]=searchData[iiii];
			}
		}
		searchData=tempDate;
		$("#"+tableId).DataTable().draw();
	});
}

/**
 * 数据表格清空表单功能实现
 * @param buttonId:按钮的id
 * @param formId:表单的id
 */
function dataTableClearButton(buttonId,formId){
	$('#'+buttonId).click(function(){
		var frmID = document.getElementById(formId);
		var i, queryString = "", and = "";
		// for each form's object
		var item;
		// store each form object's value
		var itemValue;
		for (i = 0; i < frmID.length; i++) {
			// get form's each object
			item = frmID[i];
			if (item.name != '') {
				var display=item.style.display;
				if(display=='none') continue;
				if (item.type == 'select-one') {
					$("#"+item.id).val(null).trigger("change");
				} else if (item.type == 'select-multiple') {
					$("#"+item.id).val(null).trigger("change");
				} else if (item.type == 'checkbox'
					|| item.type == 'radio') {
					//nothing to do
					continue;
				} else if (item.type == 'button'
						|| item.type == 'submit'
						|| item.type == 'reset'
						|| item.type == 'image') {// ignore this type
					continue;
				} else {
					$("#"+item.id).val("");			
				}
			}
		}
		searchData = [];
	});
}
function clearTable(formId){
	var frmID = document.getElementById(formId);
	var i, queryString = "", and = "";
	// for each form's object
	var item;
	// store each form object's value
	var itemValue;
	for (i = 0; i < frmID.length; i++) {
		// get form's each object
		item = frmID[i];
		if (item.name != '') {
			var display=item.style.display;
			if(display=='none') continue;
			if (item.type == 'select-one') {
				$("#"+item.id).val(null).trigger("change");
			} else if (item.type == 'select-multiple') {
				$("#"+item.id).val(null).trigger("change");
			} else if (item.type == 'checkbox'
				|| item.type == 'radio') {
				//nothing to do
				continue;
			} else if (item.type == 'button'
					|| item.type == 'submit'
					|| item.type == 'reset'
					|| item.type == 'image') {// ignore this type
				continue;
			} else {
				$("#"+item.id).val("");			
			}
		}
	}
}




/**
 * 数据表格初始化
 * @param tableId:表id
 * @param url:数据获取url
 * @param sort:排序信息
 * @param columns:列信息
 * @param columnDefs:列的处理
 */
function dataTable(tableId,url,sort,columns,columnDefs){
	var oTable = $('#'+tableId).DataTable({
				"bLengthChange" : false,
				"bFilter" : false,
				//保存状态到cookie *************** 很重要 ， 当搜索的时候页面一刷新会导致搜索的消失。使用这个属性就可避免了
				"bStateSave" : true,
				//每页显示的记录数
				"bLengthChange" : true,
				"bSort" : true,
				"aaSorting" : sort,
				"iDisplayLength" : 10,
				"sPaginationType" : "full_numbers",
				"bProcessing" : true,
				"bServerSide" : true,
				"sScrollX": "100%",
				"sScrollXInner": "110%",
				"bScrollCollapse": true,
				//url
				"sAjaxSource" : url,
				"fnServerParams" : function(aoData) {
					for (var i = 0; i < searchData.length; i++) {
						aoData.push(searchData[i]);
					}
				},
				"sServerMethod" : "POST",
				//列的定义
				"aoColumns" : columns,
				"aoColumnDefs" : columnDefs,
				"fnInitComplete" : function(oSettings, json) {
					//表格初始化完成后调用 在这里和服务器分页没关系可以忽略
					//nothing to do
				},
				"oLanguage" : {
					"sLengthMenu" : system16,// system16:每页选择 _MENU_ 记录 - 
					"sZeroRecords" : system17,// system17:对不起，查询不到任何相关数据 - 
					"sInfo" : system18,// system18:当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录 - 
					"sInfoEmtpy" : system19,// system19:找不到相关数据 - 
					"sProcessing" : system20,// system20:正在加载中... - 
					"sSearch" : system21,// system21:快速搜索 - 
					"sUrl" : "", 
					//多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
					"oPaginate" : {
						"sFirst" : system22,// system22:第一页 - 
						"sPrevious" : system23,// system23:上一页  - 
						"sNext" : system24,// system24:下一页  - 
						"sLast" : system25// system25:最后一页 - 
					}
				},
	});	
}


/**
 * 文件上传
 * @param ids 要上传input框的id,支持批量上传    如:["file1","file2","file3",...]
 * @param url 处理上传的url    如：'../tech/point!upload.do'
 * @param moduleNo 模块;上传的文件将按照模块存放在磁盘上<br>0-Temp(临时文件夹)<br>1-Tech(教学模块)<br>2-Campus(校招模块)<br>其它值-Temp(临时文件夹)
 * @param inModulePath 在模块中的存放位置;不指定请传""    如:'1\/2\/3' 表示文件存放在“文件夹1/文件夹2/文件夹3”下
 * @param otherParam 其它参数(Map类型);若无,请传null	
 * @param successCallBack 上传之后的回调函数
 */
function ajaxFileUpload(ids,url,moduleNo,inModulePath,otherParam,successCallBack){
	var moduleName="";
	switch(moduleNo){
		case 0: moduleName="Temp";break;//临时文件夹
		case 1: moduleName="Tech";break;//教学模块文件夹
		case 2: moduleName="Campus";break;//校招模块文件夹
		default:moduleName="Temp";//匹配不上，默认为临时文件夹
	}
	var indexMask;
	$.ajaxFileUpload({
        url:url,
        //secureuri:false,
        fileElementId: ids,
        dataType: 'text',
        data:{"module":moduleName,"inModulePath":inModulePath,"otherParam":otherParam},
        success:function(data, status) {
	        	var reg = /<pre.+?>(.+)<\/pre>/g;  
	        	var result = data.match(reg);  
	        	data = RegExp.$1;
	        	data = eval("("+data+")");
        		successCallBack(data, status);
        	},
        error:function(data, status, e){
        	var reg = /<pre.+?>(.+)<\/pre>/g;  
        	var result = data.match(reg);  
        	data = RegExp.$1;
        	data = eval("("+data+")");
        	layer.alert("Upload Fail!"+data.message);
        }
    });  
	$("#"+ids[0]+"").ajaxStart(function(){  
		indexMask = layer.load(0, {
		    shade: [0.1,'#fff']
		});
    }).ajaxComplete(function(){  
    	layer.close(indexMask);
    });
}


/**
 * 初始化树形结构
 * @param id - 目标tree的id
 * @param url - 获取数据的action地址
 * @param data - 输入参数
 * @param openId - 展开按钮id
 * @param closeId - 收缩按钮id
 * @param level - 展开等级的下拉框id
 * @param changeTags - tags的处理方法
 * 					data - 数据
 * @param nodata - 没有数据的执行方法
 */
function treeViewInitial(id,url,data,openId,closeId,level,changeTags,nodata) {
	$.ajax({
		url:url,
		data: data ,
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.success=='true'){
				if(data.datas.tree==null||data.datas.tree==""){
					nodata;
				}else{
					$('#'+id).treeview({
						levels:4,
						color: "#3C8DBC",
						expandIcon: 'glyphicon glyphicon-chevron-right',
						collapseIcon: 'glyphicon glyphicon-chevron-down',
						nodeIcon: 'glyphicon glyphicon-bookmark',
						showTags: true,
						data: changeJson(data.datas.tree,changeTags)
					});
				}							
			}else{
				layer.msg(data.message);
			}
		}
	});
    // 展开/ 收缩
    $('#'+openId).on('click', function (e) {
      var levels = $('#'+level).val();
      $('#'+id).treeview('expandAll', { levels: levels });
    });
    $('#'+closeId).on('click', function (e) {
      $('#'+id).treeview('collapseAll');
    });
    //
}
function changeJson(data,changeTags){
	$.each(data,function(n, all){
		if(data[n].point!=null){
			//
			var t = [];
			t[0] = changeTags(data[n].data);
			data[n].tags = t;
		}else{
			if(data[n].nodes!=null){
				$.each(data[n].nodes,function(n1, all1){
					if(all1.point!=null){
						//					
						var t = [];
						t[0] = changeTags(all1.data);
						all1.tags = t;
					}else{
						if(all1.nodes!=null){
							$.each(all1.nodes,function(n2, all2){
								if(all2.point!=null){
									//
									var t = [];
									t[0] = changeTags(all2.data);
									all2.tags = t;
								}else{
									if(all2.nodes!=null){
										$.each(all2.nodes,function(n3, all3){
											if(all3.point!=null){
												//
												var t = [];
												t[0] = changeTags(all3.data);
												all3.tags = t;
											}else{
												//nothing to do
											}
										});
									}
								}
							});
						}			
					}
				});
			}		
		}
	});	
	return data;
}

/**
 * 将json遍历组成?XXX=XXXX&XXX=XXXX的形式,方便提交数据
 * @returns {String}
 */
function jsonToStringForAction(data){
	var t = "?";
	$.each(data,function(n, all){
		t = t + n + "=" + data[n] + "&";
	});	
	t = t.replaceAll("\"", "");
	return t.substr(0, t.length*1-1);
}
//替换字符串
String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
}
/**
 * 在线浏览PDF文件
 * @param filePath 文件路径
 * @param fileName 在线浏览页面 Title显示的文件名称
 */
function PDFViewOnline(filePath,fileName){
	window.open("../util/pdf!toPDFViewPage.do?method=getPDFFile&filePath="+filePath+"&fileName="+fileName);
}
/**
 * 在线浏览知识点PDF文件
 * @param pointId 知识点ID
 * @param pointFileType 查阅知识点附件种类<br>1-教材资料(material)<br>2-教师手册(t_handbok)<br>3-学生手册(s_handbook)<br>4-参考资料(reference)
 * @param fileName 在线浏览页面 Title显示的文件名称; 若没有，请传""，系统会自动设置文件名,
 */
function PointPDFViewOnline(pointId,pointFileType,fileName){
	window.open("../util/pdf!toPDFViewPage.do?method=getPointPDFFile&pointId="+pointId+"&pointFileType="+pointFileType+"&fileName="+fileName);
}

 
/**
 * 所有ajax请求的通用前置filter<br>不重复提交请求
 */
var pendingRequests = {};
$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
	var key = options.url;
	console.info("============================================")
	console.info("url:"+key)
	console.info("pendingReuests:")
    for(var i in pendingRequests) { 
        console.log(i,":",pendingRequests[i]);
    }  
    
	if (!pendingRequests[key]) {
		console.info("请求通过...");
		console.info("============================================")
		pendingRequests[key] = jqXHR;
	} else{
		console.info("请求重复，拒绝通过！");
		layer.msg("请勿频繁操作(Do not operate frequently)！");
		console.info("============================================")
		jqXHR.abort();//放弃后触发的重复提交
		//pendingRequests[key].abort(); // 放弃先触发的提交
	}

	var complete = options.complete;
	options.complete = function(jqXHR, textStatus) {
		pendingRequests[key] = null;
		if ($.isFunction(complete)) {
			complete.apply(this, arguments);
		}
	};
});

/**
 * 通用的数据校验JS方法(针对文本框、下拉框)<br>
 * itemArr : [{<br>
 * &nbsp;&nbsp;&nbsp;"id",(组件ID；文本框或者)<br>
 * &nbsp;&nbsp;&nbsp;"type",(常用校验类型：'0'-自定义，'1'-非空，'2'-数字类型，'3'-邮件类型，'4'-11位手机号，'5_min_max'-文本长度，'6'-证件号码，'7'-电话号码(座机号码)，'8'-邮政编码...)<br>
 * &nbsp;&nbsp;&nbsp;"regular",(正则表达式。当type=0时，需要传入此项；否则传null)<br>
 * &nbsp;&nbsp;&nbsp;"message"(错误提示信息。当type=0时，需要传入此项；否则传null)<br>
 * &nbsp;&nbsp;&nbsp;},<br>
 * &nbsp;&nbsp;&nbsp;{...},...]<br>
 * <br>
 * e.g. valudate([{"id":id1,"type":type1,"regular":regular1,"message":message1},{"id":id2,"type":type2,"regular":regular2,"message":message2}])
 */
function validate(itemArr){
	for(var i=0;i<itemArr.length;i++){
		var item=itemArr[i];
		var item_id=item.id;
		var item_type=item.type;
		var item_reg=item.regular;
		var item_mes=item.message;
		var item_value;
		var obj=document.getElementById(item_id);
		if(obj.type == 'select-one'){//下拉框
			item_value = obj.value;//obj.options[obj.selectedIndex].value;
		}else if(obj.type == 'select-multiple'){//多选下拉框
			item_value = obj.value;//obj.options[obj.selectedIndex].value;
		}else if(obj.type == 'text'){//文本框
			item_value = obj.value;
		}else if(obj.type == 'hidden'){//隐藏框
			item_value = obj.value;
		}else if(obj.type == 'checkbox'//checkbox
				|| obj.type == 'radio'){
			if (obj.checked == false) {
				item_value = "";
			}else{
				item_value = obj.value;
			}
		}else{//忽略
			continue;
		}
		if(!validateItem(item_id,item_value,item_type,item_reg,item_mes)){
			return false;
		}
	}
	return true;
}
function validateItem(item_id,item_value,item_type,item_reg,item_mes){
	var type=item_type.substring(0,1);
	/**
	 * 自定义
	 */
	if(type=='0'){
		if(item_value==null || item_value=="")return true;
		if(!item_reg.test(item_value)){
			layer.msg(item_mes);
			return false;
        }
	}
	/**
	 * 非空校验
	 */
	else if(type=='1'){
		//获取字段名称
		var label=$("label[for='"+item_id+"']").text();
		//定义提示信息
		if(label==null || label==""){
			item_mes=system40;//"请填上必填信息！";
		}else{
			item_mes=system41+label+system42+system43;
		}
		//校验
		if(item_value==null || item_value==""){
			layer.msg(item_mes);
			return false;
		}
	}
	/**
	 * 数字类型
	 */
	else if(type=='2'){
		if(item_value==null || item_value=="")return true;
		//正则表达式
		item_reg = /^(([+-]?[0-9]\.[0-9]*)|([+-]?[0-9]*\.[0-9]*)|([+-]?[0-9]*)|([+-]?0))$/;//实数
		//获取字段名称
		var label=$("label[for='"+item_id+"']").text();
		//定义提示信息
		if(label==null || label==""){
			item_mes=system44;
		}else{
			item_mes=system41+label+system42+system45;
		}
		if(!item_reg.test(item_value)){
			layer.msg(item_mes);
			return false;
        }
	}
	/**
	 * 邮件类型
	 */
	else if(type=='3'){
		if(item_value==null || item_value=="")return true;
		//正则表达式
		item_reg = /^[a-zA-Z0-9_]([a-zA-Z0-9_.-])+@([a-zA-Z0-9])+([.]{1}([a-zA-Z0-9])+)+/;//邮箱
		//获取字段名称
		var label=$("label[for='"+item_id+"']").text();
		//定义提示信息
		if(label==null || label==""){
			item_mes=system46;
		}else{
			item_mes=system41+label+system42+system47;
		}
		if(!item_reg.test(item_value)){
			layer.msg(item_mes);
			return false;
        }
	}
	/**
	 * 11位手机号
	 */
	else if(type=='4'){
		if(item_value==null || item_value=="")return true;
		//正则表达式
		item_reg = /^1\d{10}$/;
		//获取字段名称
		var label=$("label[for='"+item_id+"']").text();
		//定义提示信息
		if(label==null || label==""){
			item_mes=system48;
		}else{
			item_mes=system41+label+system42+system49;
		}
		if(!item_reg.test(item_value)){
			layer.msg(item_mes);
			return false;
        }
	}
	/**
	 * 文本长度
	 */
	else if(type=='5'){
		if(item_value==null || item_value=="")return true;
		var str=item_type.split("_");
		var begin=str[1];
		var end=str[2];
		//获取字段名称
		var label=$("label[for='"+item_id+"']").text();
		//定义提示信息
		if(label==null || label==""){
			item_mes=system50;
		}else{
			item_mes=system41+label+system42+system50;
		}
		var length=item_value.length;
		if(begin<0){
			if(length>end){
				layer.msg(item_mes+system51+end);
				return false;
			}
		}else{
			if(end<0){
				if(length<begin){
					layer.msg(item_mes+system52+begin);
					return false;
				}
			}else{
				if(length<begin || length>end){
					layer.msg(item_mes+system53+begin+system54+end+system55);
					return false;
				}
			}
		}
	}
	/**
	 * 证件号码
	 */
	else if(type=='6'){
		if(item_value==null || item_value=="")return true;
		//正则表达式
		item_reg = /^[1-9]\d{5}(19\d{2}|[2-9]\d{3})((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])(\d{4}|\d{3}X|\d{3}x)$/;//18位身份证
		var item_reg2= /^[1-9]\d{7}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}$/;//15位身份证
		var item_reg3=/(P\d{7})|(G\d{8})/;//护照
		//获取字段名称
		var label=$("label[for='"+item_id+"']").text();
		//定义提示信息
		if(label==null || label==""){
			item_mes=system56;
		}else{
			item_mes=system41+label+system42+system57;
		}
		var test1=item_reg.test(item_value);
		var test2=item_reg2.test(item_value);
		var test3=item_reg3.test(item_value);
		if(!test1 && !test2 && !test3){
			layer.msg(item_mes);
			return false;
        }
	}
	/**
	 * 电话号码(座机号码)
	 */
	else if(type=='7'){
		if(item_value==null || item_value=="")return true;
		//正则表达式
		item_reg = /(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/; 
		//获取字段名称
		var label=$("label[for='"+item_id+"']").text();
		//定义提示信息
		if(label==null || label==""){
			item_mes=system58;
		}else{
			item_mes=system41+label+system42+system59;
		}
		if(!item_reg.test(item_value)){
			layer.msg(item_mes);
			return false;
        }
	}
	/**
	 * 邮编
	 */
	else if(type=='8'){
		if(item_value==null || item_value=="")return true;
		//正则表达式
		item_reg = /^[0-9]{6}$/;; 
		//获取字段名称
		var label=$("label[for='"+item_id+"']").text();
		//定义提示信息
		if(label==null || label==""){
			item_mes=system60;
		}else{
			item_mes=system41+label+system42+system61;
		}
		if(!item_reg.test(item_value)){
			layer.msg(item_mes);
			return false;
        }
	}
	return true;
}
/**
 * 定义Bootstrap Table
 * @param tableId
 * @param url
 * @param toolbarId
 * @param columns
 * @param sortFieldName
 * @param sortOrder
 * @param pagination
 * @param otherParams
 * @returns {___anonymous24287_24296}
 */
function DefBtTable(tableId, url, toolbarId, columns, sortFieldName, sortOrder, pagination, otherParams){
	var bootObject = new Object();
	bootObject.Init = function() {
		$('#' + tableId + '').bootstrapTable({                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
			url : url,
			method : 'post', // 请求方式（*）
			contentType: "application/x-www-form-urlencoded",//post时候需要设置contentType
			dataType: "json",
			toolbar : '#' + toolbarId +'', // 工具按钮用哪个容器
			striped : false, // 是否显示行间隔色
			cache : true, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : pagination, // 是否显示分页（*）
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
//			scrollX: true,
			sortable : true, // 是否启用排序
			sortOrder : sortOrder, // 排序方式
			sortName : sortFieldName,
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 20, 40, 80 ], // 可供选择的每页的行数（*）
			queryParams : function(params) {
				var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
					limit : params.limit, // 页面大小
					offset : params.offset, // 当前记录位置
					sortOrder : params.order,
					sortName : params.sort
				};
				return mergeJsonArr(temp,otherParams);
			},
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			minimumCountColumns : 2, // 最少允许的列数
			clickToSelect : true, // 是否启用点击选中行
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : false, // 是否显示父子表
			locale: btTable_local_String,
			responseHandler : function(res) {
				var resData=eval(res);
				return resData.datas.data;
			},
			columns : columns
		});
	};
	//formId 搜索数据的表单;为空时，传NULL
	bootObject.searchDate = function(formId) {
		var searchData;
		if(formId==null)
			searchData=null;
		else
			searchData = $('#' + formId + '').serialize();
		$('#' + tableId + '').bootstrapTable('refresh', {
			url : '' + url + '?' + searchData,
		})
	};
	//formId 清空搜索表单数据;为空时，传NULL
	bootObject.cleanDate = function(formId) {
		if(formId!=null){
			var frmID = document.getElementById(formId);
			var i;
			var item;
			var itemValue;
			for (i = 0; i < frmID.length; i++) {
				item = frmID[i];
				if (item.name != '') {
					var display=item.style.display;
					if(display=='none') continue;
					if (item.type == 'select-one') {
						$("#"+item.id).val(null).trigger("change");
					} else if (item.type == 'select-multiple') {
						$("#"+item.id).val(null).trigger("change");
					} else if (item.type == 'checkbox'
						|| item.type == 'radio') {
						continue;
					} else if (item.type == 'button'
							|| item.type == 'submit'
							|| item.type == 'reset'
							|| item.type == 'image') {
						continue;
					} else {
						$("#"+item.id).val("");		
					}
				}
			}
		}
		
	};
	//刷新表格数据
	//formId 清空搜索表单数据;为空时，传NULL
	bootObject.refresh = function(formId) {
		bootObject.searchDate(formId);
	}
	//获取选中行
	bootObject.getSelected = function() {
		return $('#'+tableId+'').bootstrapTable('getAllSelections');
	}
	return bootObject;
}
/**
 * Bootstrap Table对象操作; 
 * <br><b>搜索数据</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对象.searchDate("searchFormId");
 * <br><b>清空条件</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对象.cleanDate("searchFormId");
 * <br><b>刷新</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对象.refresh("searchFormId");
 * <br><b>获取选中行</b>&nbsp;&nbsp;对象.getSelected();
 * @param tableId 不能为空。表格ID
 * @param url 不能为空。请求后台数据URL
 * @param toolbarId 不能为空。工具栏ID
 * @param columns 不能为空。表格列定义
 * @param sortFieldName 不能为空。初始排序对应列的field
 * @param sortOrder 不能为空。初始对应排序方法： "desc"或者"asc"
 * @param otherParams 可以为空。其他参数: {"p1":"v1","p2":"v2"}，为空时，传入NULL
 */
function BtTable(tableId, url, toolbarId, columns, sortFieldName, sortOrder, otherParams) {
	return DefBtTable(tableId, url, toolbarId, columns, sortFieldName, sortOrder, true, otherParams);
}

function mergeJsonArr(destination, source) {
	if(source == null )return destination;
    for (var property in source)  
      destination[property] = source[property];  
    return destination;  
}

/**
 * Bootstrap Table对象操作（携带子表）; 
 * <br><b>搜索数据</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对象.searchDate("searchFormId");
 * <br><b>清空条件</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对象.cleanDate("searchFormId");
 * <br><b>刷新</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对象.refresh("searchFormId");
 * <br><b>获取选中行</b>&nbsp;&nbsp;对象.getSelected();
 * @param tableId 不能为空。表格ID
 * @param url 不能为空。请求后台数据URL
 * @param toolbarId 不能为空。工具栏ID
 * @param columns 不能为空。表格列定义
 * @param sortFieldName 不能为空。初始排序对应列的field
 * @param sortOrder 不能为空。初始对应排序方法： "desc"或者"asc"
 * @param otherParams 可以为空。其他参数: {"p1":"v1","p2":"v2"}，为空时，传入NULL
 * @param InitSubTable 不能为空。子查询函数
 */
function BtTableWithSub(tableId, url, toolbarId, columns, sortFieldName, sortOrder, otherParams, InitSubTable) {
	var bootObject = new Object();
	bootObject.Init = function() {
		$('#' + tableId + '').bootstrapTable({                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
			url : url,
			method : 'post', // 请求方式（*）
			contentType: "application/x-www-form-urlencoded",//post时候需要设置contentType
			dataType: "json",
			toolbar : '#' + toolbarId +'', // 工具按钮用哪个容器
			striped : false, // 是否显示行间隔色
			cache : true, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			sortable : true, // 是否启用排序
			sortOrder : sortOrder, // 排序方式
			sortName : sortFieldName,
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			queryParams : function(params) {
				var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
					limit : params.limit, // 页面大小
					offset : params.offset, // 当前记录位置
					sortOrder : params.order,
					sortName : params.sort
				};
				return mergeJsonArr(temp,otherParams);
			},
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			minimumCountColumns : 2, // 最少允许的列数
			clickToSelect : true, // 是否启用点击选中行
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : true, // 是否显示父子表
			locale: btTable_local_String,
			responseHandler : function(res) {
				var resData=eval(res);
				return resData.datas.data;
			},
			columns : columns,
			onExpandRow : function(index, row, $detail) {
				InitSubTable(index, row, $detail);
			}
		});
	};
	//formId 搜索数据的表单;为空时，传NULL
	bootObject.searchDate = function(formId) {
		var searchData;
		if(formId==null)
			searchData=null;
		else
			searchData = $('#' + formId + '').serialize();
		$('#' + tableId + '').bootstrapTable('refresh', {
			url : '' + url + '?' + searchData,
		})
	};
	//formId 清空搜索表单数据;为空时，传NULL
	bootObject.cleanDate = function(formId) {
		if(formId!=null){
			var frmID = document.getElementById(formId);
			var i;
			var item;
			var itemValue;
			for (i = 0; i < frmID.length; i++) {
				item = frmID[i];
				if (item.name != '') {
					var display=item.style.display;
					if(display=='none') continue;
					if (item.type == 'select-one') {
						$("#"+item.id).val(null).trigger("change");
					} else if (item.type == 'select-multiple') {
						$("#"+item.id).val(null).trigger("change");
					} else if (item.type == 'checkbox'
						|| item.type == 'radio') {
						continue;
					} else if (item.type == 'button'
							|| item.type == 'submit'
							|| item.type == 'reset'
							|| item.type == 'image') {
						continue;
					} else {
						$("#"+item.id).val("");		
					}
				}
			}
		}
		
	};
	//刷新表格数据
	//formId 清空搜索表单数据;为空时，传NULL
	bootObject.refresh = function(formId) {
		bootObject.searchDate(formId);
	}
	//获取选中行
	bootObject.getSelected = function() {
		return $('#'+tableId+'').bootstrapTable('getAllSelections');
	}
	return bootObject;
}



/**
 * 时间线UI控件
 * @param divId div的id
 * @param url 数据获取的地址
 * @param param 提交到后台的参数
 * @param context 内容方法定义
 * @param contextButton 内容下方的按钮
 * 			[{name:"编辑",style:"btn-primary",fun:edit}]
 * 			name:名字 style:样式 fun:触发方法
 * @param addButton 基线上的按钮
 * 			{name:"添加",fun:add}
 * 			name:名字 fun:触发方法
 * 例子:initTimeLine("timeLine","../univ/coop!getCoopHisTimeLine.do",{"coopId":document.getElementById("coopId").value}
		,function(param){
			return '状态:'+param.status+'&nbsp&nbsp'+
					'更新人:'+param.update_user+'&nbsp&nbsp'+
					'描述:'+param.remark+'&nbsp&nbsp';
		},
		[{name:"编辑",style:"btn-primary",fun:edit}],
		{name:"添加",fun:add});
 * 
 */
function initTimeLine(divId,url,param,context,contextButton,addButton){
	$.ajax({
		url : url,
		data: param,
		dataType : "json",
		type : "get",
		success : function(data) {
			if (data.success == 'true') {
				data = data.datas.timeLine;
				//处理数据
				//头部
				var html =  '<!-- /.box-header -->'+
							'<div class="box-body">'+
							'<!-- row -->'+
							'<div class="row">'+
							'<div class="col-md-12">'+
							'<!-- The time line -->'+
							'<ul class="timeline">'+
							'<!-- timeline time label -->'+
							'<li class="time-label"><span class="bg-red">'+system62+'</span></li>'+
							'<!-- /.timeline-label -->';
				//中部
				var buttonId = "timeLineButton";
				var num = 0;
				var buttonList = [];
				$.each(data, function(i, item){   
					//判断类型
					if(item.type=="0"){
						//时间标记
						html = html + '<li class="time-label"><span class="bg-green">'+item.title+'</span></li>';
					}else{
						//内容
						//按钮
						var button = "";
						$.each(contextButton, function(i0, item0){
							var tem = buttonId+num;
							var style = "btn btn-primary btn-xs";
							if(item0.style!=null&&item0.style!=""){
								style = item0.style;
							}	
							button = button + '<a style="width: 5%;" class="'+style+
							'" id="'+tem+'">'+item0.name+'</a>&nbsp';
							buttonList[num]={id:tem,fun:item0,param:item.param};
							num=(num*1)+1;	
						});
						//内容
						html = html + '<!-- timeline item -->'+
									'<li><i class="fa fa-user bg-aqua"></i>'+
									'<div class="timeline-item direct-chat-text">'+
									'<span class="time"><i class="fa fa-clock-o"></i>&nbsp'+item.time+'</span>'+
									'<h3 class="timeline-header">'+item.title+'</h3>'+
									'<div class="timeline-body">'+
									context(item.param)+		
									'</div>'+
									'<div class="timeline-footer" style="padding-top: 0px;height: 30px;padding-bottom: 0px;">'+
									button+
									'</div>'+
									'</div></li>'+
									'<!-- END timeline item -->';
					}	
				}); 
				//添加
				if(addButton!=null){
					var tem = buttonId+num;
					html = html + '<!-- timeline time label -->'+
								'<li class="time-label"><a class="btn btn-primary btn-xs" style="width: 67px;" id="'+tem+'">'+
								addButton.name+
								'</a></li>'+
								'<!-- /.timeline-label -->';
					buttonList[num]={id:tem,fun:addButton,param:null};
					console.info(buttonList);
				}
				//尾部
				html = html + '<!-- END timeline item -->'+
							'<li><i class="fa fa-clock-o bg-gray"></i></li>'+
							'</ul>'+
							'</div>'+
							'<!-- /.col -->'+
							'</div>'+
							'<!-- /.row -->'+
							'<div>'+
							'<!-- /.box-body -->';
				document.getElementById(divId).innerHTML = html;
				//给按钮绑定方法
				$.each(buttonList, function(i1, item1){
					$("#"+item1.id).click(function(){
						item1.fun.fun(item1.param)
					});
				});  
			} else {
				layer.msg(system63);
				console.info(data.message);
			}
		}
	});
}


//js对map的操作
//注意,map中key可以重复,put之前先remove原先的key
function Map() {  
  this.elements = new Array();  
  //获取MAP元素个数  
  this.size = function() {  
      return this.elements.length;  
  };  
  //判断MAP是否为空  
  this.isEmpty = function() {  
      return (this.elements.length < 1);  
  };  
  //删除MAP所有元素  
  this.clear = function() {  
      this.elements = new Array();  
  };  
  //向MAP中增加元素（key, value)   
  this.put = function(_key, _value) {  
      this.elements.push( {  
          key : _key,  
          value : _value  
      });  
  };  
  //删除指定KEY的元素，成功返回True，失败返回False  
  this.remove = function(_key) {  
      var bln = false;  
      try {  
          for (i = 0; i < this.elements.length; i++) {  
              if (this.elements[i].key == _key) {  
                  this.elements.splice(i, 1);  
                  return true;  
              }  
          }  
      } catch (e) {  
          bln = false;  
      }  
      return bln;  
  };  
  //获取指定KEY的元素值VALUE，失败返回NULL  
  this.get = function(_key) {  
      try {  
          for (i = 0; i < this.elements.length; i++) {  
              if (this.elements[i].key == _key) {  
                  return this.elements[i].value;  
              }  
          }  
      } catch (e) {  
          return null;  
      }  
  };  
  //获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL  
  this.element = function(_index) {  
      if (_index < 0 || _index >= this.elements.length) {  
          return null;  
      }  
      return this.elements[_index];  
  };  
  //判断MAP中是否含有指定KEY的元素  
  this.containsKey = function(_key) {  
      var bln = false;  
      try {  
          for (i = 0; i < this.elements.length; i++) {  
              if (this.elements[i].key == _key) {  
                  bln = true;  
              }  
          }  
      } catch (e) {  
          bln = false;  
      }  
      return bln;  
  };  
  //判断MAP中是否含有指定VALUE的元素  
  this.containsValue = function(_value) {  
      var bln = false;  
      try {  
          for (i = 0; i < this.elements.length; i++) {  
              if (this.elements[i].value == _value) {  
                  bln = true;  
              }  
          }  
      } catch (e) {  
          bln = false;  
      }  
      return bln;  
  };  
  //获取MAP中所有VALUE的数组（ARRAY）  
  this.values = function() {  
      var arr = new Array();  
      for (i = 0; i < this.elements.length; i++) {  
          arr.push(this.elements[i].value);  
      }  
      return arr;  
  };  
  //获取MAP中所有KEY的数组（ARRAY）  
  this.keys = function() {  
      var arr = new Array();  
      for (i = 0; i < this.elements.length; i++) {  
          arr.push(this.elements[i].key);  
      }  
      return arr;  
  };  
} /**
 * 转义字符
 * @param content
 * @returns
 */
function turnCharactor(content){
	if(content==null)return null;
	content=content.replaceAll('&','&amp;');
	content=content.replaceAll('"','&quot;');
	content=content.replaceAll("'",'&apos;');
	content=content.replaceAll('<','&lt;');
	content=content.replaceAll('>','&gt;');
	return content;
}
/**
 * 反转义
 * @param content
 * @returns
 */
function returnCharactor(content){
	if(content==null)return null;
	content=content.replaceAll('&quot;','"');
	content=content.replaceAll("&apos;","'");
	content=content.replaceAll('&lt;','<');
	content=content.replaceAll('&gt;','>');
	content=content.replaceAll('&amp;','&');
	/*content=content.replaceAll("\n","<br>");
  	content=content.replaceAll("\t","&nbsp;&nbsp;&nbsp;&nbsp;");
  	content=content.replaceAll(" ","&nbsp;");*/
	return content;
}



/*
<!-- /.box-header -->
<div class="box-body">
	<!-- row -->
	<div class="row">
		<div class="col-md-12">
			<!-- The time line -->
			<ul class="timeline">

				<!-- timeline time label -->
				<li class="time-label"><span class="bg-red">时间线起始时间</span></li>
				<!-- /.timeline-label -->

				<!-- timeline item -->
				<!-- 第一件事件 -->
				<li><i class="fa fa-user bg-aqua"></i>
					<div class="timeline-item direct-chat-text">
						<span class="time"><i class="fa fa-clock-o"></i>发生事件</span>
						<h3 class="timeline-header">状态一</h3>
						<div class="timeline-body">详细内容</div>
						<div class="timeline-footer">
							<a class="btn btn-primary btn-xs">编辑</a> <a
								class="btn btn-danger btn-xs">删除</a>
						</div>
					</div></li>
				<!-- END timeline item -->
				
				
				<!-- timeline item -->
				<!-- 第二件事件 -->
				<li><i class="fa fa-user bg-aqua"></i>
					<div class="timeline-item direct-chat-text">
						<span class="time"><i class="fa fa-clock-o"></i>发生事件</span>
						<h3 class="timeline-header">状态二</h3>
						<div class="timeline-body">详细内容</div>
						<div class="timeline-footer">
							<a class="btn btn-primary btn-xs">编辑</a> <a
								class="btn btn-danger btn-xs">删除</a>
						</div>
					</div></li>
				<!-- END timeline item -->
				
				
				<!-- timeline item -->
				<!-- 第三件事件 -->
				<li><i class="fa fa-user bg-aqua"></i>
					<div class="timeline-item direct-chat-text">
						<span class="time"><i class="fa fa-clock-o"></i>发生事件</span>
						<h3 class="timeline-header">状态三</h3>
						<div class="timeline-body">详细内容</div>
						<div class="timeline-footer">
							<a class="btn btn-primary btn-xs">编辑</a> <a
								class="btn btn-danger btn-xs">删除</a>
						</div>
					</div></li>
				<!-- END timeline item -->

				<!-- timeline time label -->
				<li class="time-label"><span class="bg-green">第二时间段</span></li>
				<!-- /.timeline-label -->

				<!-- timeline item -->
				<!-- 第四件事件 -->
				<li><i class="fa fa-user bg-aqua"></i>
					<div class="timeline-item direct-chat-text">
						<span class="time"><i class="fa fa-clock-o"></i>发生事件</span>
						<h3 class="timeline-header">状态四</h3>
						<div class="timeline-body">详细内容</div>
						<div class="timeline-footer">
							<a class="btn btn-primary btn-xs">编辑</a> <a
								class="btn btn-danger btn-xs">删除</a>
						</div>
					</div></li>
				<!-- END timeline item -->

				<!-- timeline time label -->
				<li class="time-label"><span class="bg-green">第三时间段</span></li>
				<!-- /.timeline-label -->

				<!-- timeline item -->
				<!-- 第五件事件 -->
				<li><i class="fa fa-user bg-aqua"></i>
					<div class="timeline-item direct-chat-text">
						<span class="time"><i class="fa fa-clock-o"></i>发生事件</span>
						<h3 class="timeline-header">状态五</h3>
						<div class="timeline-body">详细内容</div>
						<div class="timeline-footer">
							<a class="btn btn-primary btn-xs">编辑</a> <a
								class="btn btn-danger btn-xs">删除</a>
						</div>
					</div></li>
				<!-- END timeline item -->

				<!-- END timeline item -->
				<li><i class="fa fa-clock-o bg-gray"></i></li>
			</ul>
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
<div>
<!-- /.box-body -->
*/
