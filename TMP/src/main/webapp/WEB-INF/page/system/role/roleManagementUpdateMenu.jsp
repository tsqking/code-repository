<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background-color:#ecf0f5";>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/common/image/logo.ico" />
<!-- 页面js -->
<title>角色菜单</title>
</head>
<body>
	<!-- Main content -->
	<section class="content"
		style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
	<!-- 搜索条件 -->
	<div class="box box-primary">
		<div>
			<h4 class="box-title" style="text-align:center;">
				<s:text name="role.roleUpdateMenuTitle">
					<s:param>${role.name }</s:param>
				</s:text>
			</h4>
		</div>
		<hr>
		<form id="addForm">
			<!-- /.box-header -->
			<div class="box-body" style="font-size:15px;">
				<div class="row">
					<div class="col-md-11 col-xs-11 col-md-offset-1" >
						<button type="button" class="btn btn-default btn-sm checkbox-toggle"><i class="fa fa-square-o"></i><s:text name="chooseAll"/></button>
					</div>
				</div>
				<br>
				<div class="row" id="menuList">
					 <%--<div class="col-md-4 col-xs-4 tree well" >
						<ul>
					        <li>
					            <span><i class="fa fa-folder-open"></i>
					            	 一级菜单
		                  		</span>
		                  		<input type="checkbox" name="menuId" value="1" checked />
					            <ul>
					                <li>
					                	<span><i class="fa fa-minus-circle" ></i> 二级菜单-1</span>
					                	<input type="checkbox" name="menuId" value="2" checked />
					                    <ul>
					                        <li>
						                        <span><i class="fa fa-leaf"></i> 三级菜单-1</span>
						                        <input type="checkbox" name="menuId" value="3" checked />
					                        </li>
					                        <li>
						                        <span><i class="fa fa-leaf"></i> 三级菜单-2</span>
						                        <input type="checkbox" name="menuId" checked />
					                        </li>
					                    </ul>
					                </li>
					                <li>
					                	<span><i class="fa fa-minus-circle"></i> 二级菜单-2</span>
					                	<input type="checkbox" name="menuId" checked />
					                    <ul>
					                        <li>
						                        <span><i class="fa fa-leaf"></i> 三级菜单-3</span>
						                        <input type="checkbox" name="menuId" checked />
					                        </li>
					                    </ul>
					                </li>
					            </ul>
					        </li>
					    </ul>
					</div>
					<div class="col-md-4 col-xs-4 tree well" >
					    <ul>    
					        <li>
					            <span><i class="fa fa-folder-open"></i> 系统管理</span>
					            <input type="checkbox" checked />
					            <ul>
					                <li>
					                	<span><i class="fa fa-leaf"></i> 账户管理</span>
					                	<input type="checkbox" checked />
							        </li>
							        <li>
					                	<span><i class="fa fa-leaf"></i> 角色管理</span>
					                	<input type="checkbox" checked />
							        </li>
							        <li>
					                	<span><i class="fa fa-leaf"></i> 菜单管理</span>
					                	<input type="checkbox" checked />
							        </li>
							        <li>
					                	<span><i class="fa fa-leaf"></i> 选框管理</span>
					                	<input type="checkbox" checked />
							        </li>
							        <li>
					                	<span><i class="fa fa-leaf"></i> 日期管理</span>
					                	<input type="checkbox" checked />
							        </li>
							    </ul>
					        </li>
					    </ul>
					</div>
					<div class="col-md-4 col-xs-4 tree well" >
					    <ul>    
					        <li>
					            <span><i class="fa fa-folder-open"></i> 教学管理</span>
					            <input type="checkbox" checked />
					            <ul>
					                <li>
					                	<span><i class="fa fa-leaf"></i> 技能管理</span>
					                	<input type="checkbox" checked />
							        </li>
							        <li>
					                	<span><i class="fa fa-leaf"></i> 知识点管理</span>
					                	<input type="checkbox" checked />
							        </li>
							        <li>
					                	<span><i class="fa fa-leaf"></i> 课程管理</span>
					                	<input type="checkbox" checked />
							        </li>
							        <li>
					                	<span><i class="fa fa-leaf"></i> 班级管理</span>
					                	<input type="checkbox" checked />
							        </li>
							        <li>
					                	<span><i class="fa fa-leaf"></i> 教学计划管理</span>
					                	<input type="checkbox" checked />
							        </li>
							    </ul>
					        </li>
					    </ul>
					</div>  --%>
				</div>
			</div>
			<!-- /.box-body -->
			<div class="box-footer" style="padding: 0 0 0 0">
				<div class="col-md-12 col-xs-12" style="margin-bottom:10px;">
					<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
						<button type="button" id="closeButton"
							class="btn btn-block btn-primary btn-sm" onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));">
							<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/>
						</button>
					</div>
					<div class="col-md-3 col-xs-3">
						<button type="button" id="addButton"
								class="btn btn-block btn-primary btn-sm"><i class="fa fa-check"></i>&nbsp;&nbsp;&nbsp;<s:text name="submitButton"/></button>
					</div>
				</div>
			</div>
		</form>
	</div>
	</section>
	<s:include value="../../common.jsp"></s:include>
	<!-- tree -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/common/plugins/tree/tree.css">
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name);
$(function() {
    //初始化界面
    initialPage();
});

function initialPage(){
    $.ajax({
		url:"../system/role!initialRoleAddMenuPage.do",
		data: {"role.id":"${role.id}"},
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.success=='true'){
				//TODO initial
				//菜单头部
				var htmlText="";
            	//--->遍历一级菜单
            	$.each(data.datas.menu, function(i1, all1){
            		var context="";
            		if(i1%3==0){
            			context+="<div class=\"row\">";
            		}
            		if(i1%3==0){
            			context += "<div class=\"col-md-3 col-xs-4 tree well col-md-offset-1\" >"; 
            			//context += "<div class=\"col-md-2 col-xs-4 tree well col-md-offset-1\" >"; 
            		}
            		if(i1%3!=0){
            			context += "<div class=\"col-md-3 col-xs-4 tree well\" >"; 
            			//context += "<div class=\"col-md-2 col-xs-4 tree well col-md-offset-2\" >"; 
            		} 
            		//context += "<div class=\"col-md-3 col-xs-3 tree well \" >"; 
            		context+="<ul><li>";
            		var name1 = all1.name;
            		var type1 = all1.type;
            		var id1 = all1.id;
            		var checked1 = all1.checked;
            		if(type1=="0"){
            			//没有子菜单
            			context+="<span><i class=\"fa fa-leaf\"></i>"+name1+"</span><input type=\"checkbox\" name=\"menuId\" value=\""
            					+id1+"\" "+checked1+" />";
            		}else{
            			//含有子菜单
            			context+="<span><i class=\"fa fa-folder-open\"></i>"+name1+"</span><input type=\"checkbox\" name=\"menuId\" value=\""
            					+id1+"\" "+checked1+" />";
            			context+="<ul>";	
            			//--->遍历二级菜单
            			$.each(all1.menu, function(i2, all2){
            				context+="<li>";
            				var name2 = all2.name;
                    		var type2 = all2.type;
                    		var id2 = all2.id;
                    		var checked2 = all2.checked;
                    		if(type2=="0"){
                    			//没有子菜单
                    			context+="<span><i class=\"fa fa-leaf\"></i>"+name2+"</span><input type=\"checkbox\" name=\"menuId\" value=\""
            					+id2+"\" "+checked2+" />";
                    		}else{
                    			//含有子菜单
                    			context+="<span><i class=\"fa fa-minus-circle\"></i>"+name2+"</span><input type=\"checkbox\" name=\"menuId\" value=\""
            					+id2+"\" "+checked2+" />";
            					context+="<ul>";	
                    			//--->遍历三级菜单
                    			$.each(all2.menu, function(i3, all3){
                    				context+="<li>";
                    				var name3 = all3.name;
                            		var type3 = all3.type;
                            		var id3 = all3.id;
                            		var checked3 = all3.checked;
                            		if(type3=="0"){
                            			//没有子菜单
                            			context+="<span><i class=\"fa fa-leaf\"></i>"+name3+"</span><input type=\"checkbox\" name=\"menuId\" value=\""
                    					+id3+"\" "+checked3+" />";
                            		}else{
                            			//含有子菜单-最高3级
                            			//nothing to do
                            		}
                            		context+="</li>";
                    			});	
                    			context+="</ul>";
                    		}
                    		context+="</li>";
            			});	
            			context+="</ul>";
            		}
            		
            		context+="</li></ul></div>";
            		if(i1%3==2){
            			context+="</div>";
            		}
            		htmlText+=context;
            	});
            	//写入数据
            	document.getElementById("menuList").innerHTML = htmlText;
				
            	//复选框初始化
            	parent.layer.iframeAuto(index);
                $('.tree input[type="checkbox"]').iCheck({
                    checkboxClass: 'icheckbox_flat-blue',
                    radioClass: 'iradio_flat-blue'
                });
            	
                //全选按钮
                $(".checkbox-toggle").click(function () {
                    var clicks = $(this).data('clicks');
                    if (clicks) {
                      //Uncheck all checkboxes
                      $("input[type='checkbox']").iCheck("uncheck");
                      $(".fa", this).removeClass("fa-check-square-o").addClass('fa-square-o');
                    } else {
                      //Check all checkboxes
                      $("input[type='checkbox']").iCheck("check");
                      $(".fa", this).removeClass("fa-square-o").addClass('fa-check-square-o');
                    }
                    $(this).data("clicks", !clicks);
                });
                
                //tree
                $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
                $('.tree li.parent_li > span').on('click', function (e) {
                    var children = $(this).parent('li.parent_li').find(' > ul > li');
                    if (children.is(":visible")) {
                    	children.hide('fast');
                    	$(this).attr('title', 'Expand this branch').find(' > i').addClass('fa-plus-circle').removeClass('fa-minus-circle');
                    } else {
                    	children.show('fast');
                    	$(this).attr('title', 'Collapse this branch').find(' > i').addClass('fa-minus-circle').removeClass('fa-plus-circle');
                    }
                    e.stopPropagation();
                });
            	
			}else{
				layer.msg(data.message,{shift: 6});
			}
		}
    });
}

$(document)
.on(
		"click",
		"#addButton",
		function() {
			var menuIds="";
            $("input[name='menuId']:checked").each(function(){ 
            	menuIds += $(this).val()+","
            })
            menuIds=menuIds.substring(0,menuIds.length-1);
			$.ajax({
				url:"../system/role!updateRoleMenu.do",
				data: { "role.description":menuIds,
						"role.id":"${role.id}"},
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.success=='true'){
						parent.layer.msg(data.message);
						parent.layer.close(index);
					}else{
						layer.alert(data.message);
					}
				}
			});
		}
);

</script>
</body>
</html>




