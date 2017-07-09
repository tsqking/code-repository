/**
 * 
 */

/**
 * 根据身份证回显出生日期
 */
function returnDate(){
	var nowDate=new Date();
	var thisYear=nowDate.getFullYear();    //获取完整的年份(4位,1970-????)	
	var cardno=document.getElementById("cardno").value;
	if(cardno.length!=18){
		return;
	}else{
	var year=cardno.substring(6,10);
	var month=cardno.substring(10,12);
	var day=cardno.substring(12,14);
	var birthday=year+"-"+month+"-"+day;
	//回显生日
	document.getElementById("birthday").value=birthday; 
	var age=thisYear-year+1;
	//回显年龄
	document.getElementById("age").value=age;
	}
}

var oldPhoto;
var index;
var resume_name;
var ini1=true;
$(function() {
	index = parent.layer.getFrameIndex(window.name);
	resume_name=$("#resumeFile_name").val();
	native_place_prov=$("#native_place_prov_hidden").val();
	if(native_place_prov==null||native_place_prov==""){
		native_place_prov="110000";
	}
	//时间日期控件初始化
	dateInitial([ {"id":"birthday","type":"3"},{"id":"graduate_month","type":"4"},{"id":"in_company_time","type":"3"},{"id":"in_proj_time","type":"3"},{"id":"leave_time","type":"3"}  ]);
	//选择框初始化
	selectInitial("gender","../system/option!getOptionsByGPVal.do?value=SEX",$("#gender_hidden").val(),false);
	selectInitial("recruit_state","../system/option!getOptionsByGPVal.do?value=RC_STATE",$("#recruit_state_hidden").val(),false);
	selectInitial("degree","../system/option!getOptionsByGPVal.do?value=EDU_BAK",$("#degree_hidden").val(),false);
	selectInitial("engLevel","../system/option!getOptionsByGPVal.do?value=ENG_LEVEL",$("#engLevel_hidden").val(),false);
	selectInitial("talSource","../system/option!getOptionsByGPVal.do?value=TAL_SOURCE",$("#talSource_hidden").val(),false);
	selectInitial("position","../system/option!getOptionsByGPVal.do?value=POSITION",$("#position_hidden").val(),false);
	selectInitial("major","../system/option!getOptionsByGPVal.do?value=MAJOR",$("#major_hidden").val(),false);
	selectInitial("proj_group","../system/option!getOptionsByGPVal.do?value=PROJ_GROUP",$("#proj_group_hidden").val(),false);
	selectInitial("work_location","../system/option!getOptionsByGPVal.do?value=LOCATION",$("#work_location_hidden").val(),false);
	selectInitial("employmt_agreemt","../system/option!getOptionsByGPVal.do?value=SIGN_EPMTAGMT",$("#employmt_agreemt_hidden").val(),false);	
	selectInitial("univ", "../campusRC/talent!getUnivSelect.do", $("#univ_hidden").val(), false);
	selectInitial("college","../campusRC/talent!getCollegeSelect.do?univ_id="+$("#univ_hidden").val(),$("#college_hidden").val(),false);
	
	// 根据code==0 查找出第一级联动数据   省市信息
	selectInitial("native_place_prov", '../region/chinaregion!findRegionOption.do?chinaRegionVo.parentId=0', native_place_prov, false);
	
	// 获取二级联下拉框 省市信息
	$('#native_place_prov').on( 'change', function() {
		var selectedOption = $(this).find("option:selected").val();
		var code = selectedOption;
		if (selectedOption == null || "" == selectedOption) {
			return;
		}
		selectInitial("native_place_city", '../region/chinaregion!findRegionOption.do?chinaRegionVo.parentId='+code, $("#native_place_city_hidden").val(), false);
	});
	
	$('#univ').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
			return;
		}
		if(ini1){
			ini1=false;
			return;
		}
		selectInitial("college","../campusRC/talent!getCollegeSelect.do?univ_id="+selectedOption,"",false);
	});
	oldPhoto="${pageContext.request.contextPath}/common/image/user2-160x160.jpg";
});

//图片文件操作方法
//图片显示和预览----------------------------------------start-----------------------------------------------
function change(obj) {
	var fileSize = getFileSize("photo");
	if (fileSize > 500) {
		layer.alert(picTooLarge);
		document.getElementById("photo").value = "";
		document.getElementById("preview").src = oldPhoto;
		return;
	}
	var pic = $("#preview");
	var file = $(obj);
	var ext = file.val().substring(file.val().lastIndexOf(".") + 1)
			.toLowerCase();
	// gif在IE浏览器暂时无法显示
	if (ext != 'png' && ext != 'jpg' && ext != 'jpeg') {
		// alert("文件必须为图片！");
		layer.alert(choosePic);// choosePic:请选择图片文件! -
		document.getElementById("photo").value = "";
		document.getElementById("preview").src = oldPhoto;
		return;
	}
	// IE浏览器
	if (document.all) {
		file.select();
		file.blur();// 这句为了蛋疼的IE9
		var reallocalpath = document.selection.createRange().text;

		filePath = reallocalpath;
		var ie6 = /msie 6/i.test(navigator.userAgent);
		// IE6浏览器设置img的src为本地路径可以直接显示图片
		if (ie6)
			pic.src = reallocalpath;
		else {
			/* 这种方式不能控制大小 */
			// 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
			// pic[0].style.filter =
			// "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\""
			// + reallocalpath + "\")";
			// 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
			// pic[0].src =
			// 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
			pic = pic.parent('div');
			pic.html('');
			pic[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			pic[0].filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = reallocalpath;
			pic[0].style.width = "130px";
			pic[0].style.height = "130px";
		}
	} else {
		html5Reader(file);
	}
}

function html5Reader(file) {
	var file = file[0].files[0];
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function(e) {
		var pic = document.getElementById("preview");
		pic.src = this.result;
	}
}

function clickInput() {
	document.getElementById("photo").click();
}

/**
 * 获取文件大小
 * @param fileid
 */
function getFileSize(fileid) {
	try {
		var fileSize = 0;
		// for IE
		if ($.support.msie) {
			// before making an object of ActiveXObject,
			// please make sure ActiveX is enabled in your IE browser
			var objFSO = new ActiveXObject("Scripting.FileSystemObject");
			var filePath = $("#" + fileid)[0].value;
			var objFile = objFSO.getFile(filePath);
			var fileSize = objFile.size; // size in b
			fileSize = fileSize / 1024; // size in kb
		}
		// for FF, Safari, Opeara and Others
		else {
			fileSize = $("#" + fileid)[0].files[0].size // size in b
			fileSize = fileSize / 1024; // size in kb
		}
		return fileSize;
	} catch (e) {
		layer.alert("Error is :" + e);
	}
}
//图片显示和预览----------------------------------------end-----------------------------------------------


//遮罩层使用----------------------------------------start-----------------------------------------------
function showMask(){
	setProcessBarVal(1);
	$("#loading-mask").fadeIn();
	
}

function setProcessBarVal(value){
	$("#progressbar").attr("aria-valuenow",'"'+value+'"');
	$("#progressbar").css("width",value+"%");
	$("#progressbar").text(value);
}

function hideMask(){
	$("#loading-mask").fadeOut();
}

//遮罩层使用----------------------------------------end-------------------------------------------------


//简历附件操作方法
function resumeChange(obj) {
    var file = $(obj);
    var ext=file.val();
    var ext=file.val().substring(file.val().lastIndexOf("/")+1).toLowerCase();
	//ext=ext.substring(ext.lastIndexOf("\\")+1).toLowerCase();
    $("#resumeFile_name").val(ext);
}
//下载附件
function downLoadResume(id){
	if(resume_name!="")
		window.open("../campusRC/talent!downTalentResume.do?id="+id);
	else
		parent.layer.msg(noResumeTip);
}
//移除简历
function removeResume(id){
	if(resume_name!=""){
		layer.open({
			title: [
				tipTitle,
			 	'background-color:#3C8DBC; color:#ffffff;'
			],
		    content: confirm_del_resume,//confirm_del_resume="确认删除简历吗？";
		    btn: [confirm,cancel],
		    shadeClose: false,
		    yes: function(){
				$.ajax({
					url:"../campusRC/talent!removeResume.do",
					data: {"id":id},
					dataType:"json",
					type:"post",
					success:function(data){
						if(data.success=='true'){
							layer.msg(delResumeSuccess);
						}else{
							layer.alert(delResumeFailed,{"title":feedback});//"删除附件失败！"
							console.info(data.message);
						}
					}
				});
		    }
		});
	
	}else
		parent.layer.msg(noResumeTip);
}

var updateMessage="";
//保存修改
function save(){
	layer.open({
		title: [
			tipTitle,
		 	'background-color:#3C8DBC; color:#ffffff;'
		],
	    content: confirm_save_talent,
	    btn: [confirm,cancel],
	    shadeClose: false,
	    yes: function(){
			var itemArr=[
			             {"id":"name","type":"1","regular":null,"message":null},
			             {"id":"gender","type":"1","regular":null,"message":null},
			             {"id":"recruit_state","type":"1","regular":null,"message":null},
			             {"id":"mobile","type":"1","regular":null,"message":null},
			             {"id":"email","type":"1","regular":null,"message":null},
			             /*{"id":"univ","type":"1","regular":null,"message":null},
			             {"id":"college","type":"1","regular":null,"message":null},*/
			             {"id":"age","type":"2","regular":null,"message":null},
			             {"id":"cardno","type":"6","regular":null,"message":null},
			             {"id":"mobile","type":"4","regular":null,"message":null},
			             {"id":"email","type":"3","regular":null,"message":null}
			             ];
			if(validate(itemArr)){
				showMask();
				updateMessage="";
				$.ajax({
					url:"../campusRC/talent!updateTalentInfo.do",
					data: $("#editForm").serialize(),
					dataType:"json",
					type:"post",
					success:function(data){
						if(data.success=='true'){
							if(data.message=='1'){//信息修改成功
								setProcessBarVal(50);
								updateMessage+=updateInfoSuccess;
								//保存头像
								savePhoto($("#id").val());
							}else{
								hideMask();
								layer.alert(alreadyUpdateTip);//"信息已被修改，请刷新后重试~"
							}
						}else{
							hideMask();
							layer.alert(updateInfoFailed);//"信息修改失败！"
							console.info(data.message);
						}
					}
				});
			}
	    }
	});
}

//上传保存头像
function savePhoto(id){
	if($("#photo").val()!=""){//不为空，需要上传
		var ids = ["photo"];//id
		var otherParam = id;
		/*var load_index=layer.load(0, {
	    shade: [0.1,'#fff']
		});*/
		ajaxFileUpload(ids,'../campusRC/talent!uploadPhoto.do',2,"Photo",otherParam,
			function(data, status){
				/*layer.close(load_index);*/
				if(data.success=="true"){//上传成功
					//TODO 头像配置成功
				}else{//上传失败
					if(data.message=='need_file'){//不会发生，因为已经过滤
						updateMessage+=unPicWhenAddTalent;
						console.info("请选择一张图片上传！");
					}else if(data.message=='large_file'){//不会发生，因为已经过滤
						updateMessage+=picLargeWhenAddTalent;
						console.info("图片过大，请不要超过500KB！");
					}else{
						updateMessage+=picFailWhenAddTalent;
						console.info("头像添加失败！"+data.message);
					}
				}
				setProcessBarVal(80);
				//传简历
				saveResume(id);
			}
		);
	}else{
		setProcessBarVal(80);
		//传简历
		saveResume(id)
	}
}

//上传保存简历
function saveResume(id){
	if($("#resume").val()!=""){//不为空，需要上传
		var ids = ["resume"];//id
		var otherParam = id;
		/*var load_index=layer.load(0, {
	    shade: [0.1,'#fff']
		});*/
		ajaxFileUpload(ids,'../campusRC/talent!uploadResume.do',2,"Resume",otherParam,
			function(data, status){
				/*layer.close(load_index);*/
				if(data.success=="true"){//上传成功
					//TODO 简历配置成功
				}else{//上传失败
					if(data.message=='need_file'){//不会发生，因为已经过滤
						updateMessage+=unResumeWhenAddTalent;
						console.info("请选择简历附件上传！");
					}else if(data.message=='large_file'){//不会发生，因为已经过滤
						updateMessage+=ResumeLargeWhenAddTalent
						console.info("文件过大，请不要超过8MB！");
					}else{
						updateMessage+=ResumeFailWhenAddTalent;
						console.info("简历添加失败！"+data.message);
					}
				}
				hideMask();
				parent.layer.alert(updateMessage,{"title":feedback});
				parent.btTable.refresh("searchForm");
				parent.layer.close(index);
			}
		);
	}else{
		hideMask();
		parent.layer.alert(updateMessage,{"title":feedback});
		parent.btTable.refresh("searchForm");
		parent.layer.close(index);
	}
}