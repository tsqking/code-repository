<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/common/image/logo.ico" />
<script src="${pageContext.request.contextPath}/common/js/${session.system_lang}.js"></script>
<title><s:text name="base.pageTitle"/></title>
</head>
<body class="hold-transition skin-blue fixed sidebar-mini" style="letter-spacing:1px;">
	<div id="loading-mask" style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #D2E0F2; z-index: 20000">
		<div id="pageloading" style="position: absolute; top: 50%; left: 50%; margin: -120px 0px 0px -120px; text-align: center; width: 200px; height: 40px; font-size: 14px; padding: 10px; font-weight: bold; color: #15428B;">
			<img src="${pageContext.request.contextPath}/common/image/loadingpn.gif" align="middle" /> <s:property value="getText('拼命进入中....')"/>
		</div>
	</div>
	<!-- Site wrapper -->
	<div class="wrapper">
		<header class="main-header"> <!-- Logo --> 
		<a href="#" style="background-color:#ffffff;"
			class="logo"> <!-- mini logo for sidebar mini 50x50 pixels --> <span
			class="logo-mini"> <b style="color:#00c0ef;">TMP</b>
		</span> <!-- logo for regular state and mobile devices --> <span
			class="logo-lg"> 
			<img src="${pageContext.request.contextPath}/common/image/clps.png"
				height="45px" width="45px" style="margin-top: 2px;" />	
		</span>
		</a> <!-- Header Navbar: style can be found in header.less --> <nav
			class="navbar navbar-static-top" role="navigation"> <a href="#"
			class="sidebar-toggle" data-toggle="offcanvas" role="button"> <span
			class=""></span>
		</a> <a href="Javascript:void(0)" class="navbar-brand" style="font-size:22px;letter-spacing:3px;"><s:text name="base.WebTitle" /></a>
		<!-- 用户信息 -->
		
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<!-- User Account: style can be found in dropdown.less -->
				<li class="dropdown user user-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> 
					<input type="hidden" value="${user.id}" id="userId"/>
					<img id="userImg2"
						src="${pageContext.request.contextPath}/common/image/user2-160x160.jpg"
						class="user-image" alt="User Image"> <span class="hidden-xs">
							<%-- <s:set name="system_lang" value="%{#session.system_lang}"></s:set>
							<s:if test="#system_lang=='zh_CN'">
								${session.system_user.name}
							</s:if>
							<s:else> --%>
								${session.system_user.en_name}
							<%-- </s:else> --%>
						</span>
				</a>
					<ul class="dropdown-menu">
						<!-- User image -->
						<li class="user-header"><img id="userImg"
							src="${pageContext.request.contextPath}/common/image/user2-160x160.jpg"
							class="img-circle" alt="User Image">
							<p style="text-align:left;">
								<small style="text-align:left;"><s:text name="base.loginName"/> - ${session.system_user.username}</small>
								<small style="text-align:left;"><s:text name="base.loginTime"/> - ${session.system_logintime}</small>
							</p></li>
						<!-- Menu Footer-->
						<li class="user-footer">
							<div class="pull-left">
								<a href="Javascript:void(0)" onclick="goToProfile()" class="btn btn-default btn-flat"><s:text name="base.prifile"/></a>
							</div>
							<div class="pull-right">
								<a href="javascript:void(0);" class="btn btn-default btn-flat" onclick="signOut()"><s:text name="base.signOut"/></a>
							</div>
						</li>
					</ul></li>
				<li style="height:50px;">
					<div>
						<a href="../system/system!toMainPage.do?request_locale=zh_CN">
							<img src="${pageContext.request.contextPath}/common/image/CN_Flag.png" height="20px" width="30px" class="img-circle" style="margin-top: 15px;" />
						</a>
						<a href="../system/system!toMainPage.do?request_locale=en_US">
							<img src="${pageContext.request.contextPath}/common/image/UK_Flag.png" height="20px" width="30px" class="img-circle" style="margin-top: 15px;" />
						</a>
					</div>
				</li>
				<!-- Control Sidebar Toggle Button -->
				<li><a href="#" data-toggle="control-sidebar"><i
						class="fa fa-gears"></i></a></li>
			</ul>
		</div>
		</nav> </header>

		<!-- 左边菜单 -->

		<!-- Left side column. contains the sidebar -->
		<aside class="main-sidebar"> <!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar"> <!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img id="userImg1"
					src="${pageContext.request.contextPath}/common/image/user2-160x160.jpg"
					class="img-circle" alt="User Image" style="height: 45px;width:45px;">
			</div>
			<div class="pull-left info">
				<p>${session.system_user.name}</p>
				<a href="#"><i class="fa fa-circle text-success"></i>Online</a>
			</div>
		</div>
		<!-- search form -->
		<form action="#" method="get" class="sidebar-form">
			<div class="input-group">
				<input type="text" name="q" class="form-control"
					placeholder="Search..."> <span class="input-group-btn">
					<button type="submit" name="search" id="search-btn"
						class="btn btn-flat">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</form>
		<!-- /.search form --> <!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu" id="navul">
		<!-- 菜单例子(保存代码)
			<li class="header">MAIN NAVIGATION</li>
					
			<li class="treeview">
				<a href="#"> 
					<i class="fa fa-folder"></i>
					<span>Examples</span>
					<i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<li>
						<a href="#">
							<i class="fa fa-circle-o"></i>
							Invoice
							<i class="fa fa-angle-left pull-right"></i>
						</a>
						<ul class="treeview-menu">
							<li>
								<a href="../demo/demo!getContentPage.do" onclick="changePage('../demo/demo!getContentPage.do');return false"
								   style="cursor: pointer;">
									<i class="fa fa-circle-o"></i>
									1
								</a>
							</li>
							<li>
								<a href="../demo/demo!getContentPage.do" onclick="changePage('../demo/demo!getContentPage.do');return false"
								   style="cursor: pointer;">
									<i class="fa fa-circle-o"></i>
									2
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="../demo/demo!getContentPage.do" onclick="changePage('../demo/demo!getContentPage.do');return false"
						   style="cursor: pointer;">
							<i class="fa fa-circle-o"></i>
							Profile
						</a>
					</li>
					<li>
						<a href="../demo/demo!getContentPage.do" onclick="changePage('../demo/demo!getContentPage.do');return false"
						   style="cursor: pointer;">
							<i class="fa fa-circle-o"></i> 
							Login
						</a>
					</li>
					<li><a href="../demo/demo!getContentPage.do" onclick="changePage('../demo/demo!getContentPage.do');return false"
						style="cursor: pointer;"><i class="fa fa-circle-o"> </i>
							Register</a></li>
					<li><a href="#"><i class="fa fa-circle-o"></i> 404 Error</a></li>
					<li><a href="#"><i class="fa fa-circle-o"></i> 500 Error</a></li>
					<li><a href="#"><i class="fa fa-circle-o"></i> Blank Page</a></li>
				</ul>
			</li>
					
			<li class="header">LABELS</li>
			<li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
			<li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
			<li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>	
		-->
						
		</ul>
		
		</section> <!-- /.sidebar --> </aside>

		<!-- =============================================== -->

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
			<h1>
				<small><span id="dataTime"></span></small>
			</h1>
			<ol class="breadcrumb" id="locDis">
				
			</ol>
			</section>

			<!-- Main content -->
			<section class="content"> 
			<iframe id="main_content"
				width="100%" height="100%" frameborder="no" border="0"
				marginwidth="0" marginheight="0" scrolling="auto"
				allowtransparency="yes"></iframe> 
			</section>		
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<footer class="main-footer">
		<div class="pull-right hidden-xs">
			<b>System Version</b> 1.0.0
		</div>
		<strong>Login time&nbsp;-&nbsp;${session.system_logintime}&nbsp;&nbsp;&nbsp;&nbsp;
				IP Address&nbsp;-&nbsp;${session.system_ip_address}&nbsp;&nbsp;&nbsp;&nbsp;
		</strong>Welcome,${session.system_user.username}!</footer>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark"> <!-- Create the tabs -->
		<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
		</ul>
		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane" id="control-sidebar-home-tab"></div>
		</div>
		</aside>
		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->
	<s:include value="common.jsp"></s:include>
	<script>
		window.onload = function(){
			$('#loading-mask').fadeOut();
			window.onresize = adjust;  
			adjust();
		};
		//界面调整
		function adjust(){
			var h = document.documentElement.clientHeight;
			content = $('#main_content');
			content[0].style.height = (h - 130) + "px";//除去iframe,其余的占用140px  -143
			
		}
		$(document).ready(function() {
			content = $('#main_content');
			content[0].src = "../system/system!toWelcomePage.do";
			//加载菜单
			getMenu();
			//加载头像
			getImg();
		});
		//去往个人主页
		function goToProfile(){
			layer.msg("Coding...");
		}
		function getMenu(){
			$.ajax({
                type: "POST",
                url:'${pageContext.request.contextPath}/system/system!getMainMenu.do',
                success: function(data) {
                	//菜单头部
                	var context = "<li class=\"header\" style=\"font-size:16px;letter-spacing:1px;\">"+functionMenu+"</li>"; 
                	//--->遍历一级菜单
                	$.each(data.datas.menu, function(i1, all1){
                		context+="<li class=\"treeview\">";
                		var name1 = all1.name;
                		var type1 = all1.type;
                		if(type1=="0"){
                			//没有子菜单
                			var url1 = all1.url;
                			context+="<a href=\""+url1+"\" onclick=\"changePage('"+url1+"');return false\""+
							   "style=\"cursor: pointer;\"><i class=\"fa fa-folder\"></i><span style=\"font-size:15px;letter-spacing:1px;\">"
							   +name1+"</span></a>";
                		}else{
                			//含有子菜单
                			context+="<a href=\"#\"><i class=\"fa fa-folder\"></i><span style=\"font-size:15px;letter-spacing:1px;\">"+
                				name1+"</span><i class=\"fa fa-angle-left pull-right\"></i></a>";
                			context+="<ul class=\"treeview-menu\">";
                			//--->遍历二级菜单
                			$.each(all1.menu, function(i2, all2){
                				context+="<li>";
                				var name2 = all2.name;
                        		var type2 = all2.type;
                        		if(type2=="0"){
                        			//没有子菜单
                        			var url2 = all2.url;
                        			context+="<a href=\""+url2+"\" onclick=\"changePage('"+url2+"');return false\""+
         						   		"style=\"cursor: pointer;\"><i class=\"fa fa-circle-o\"></i><span>"
         						   		+name2+"</span></a>";
                        		}else{
                        			//含有子菜单
                        			context+="<a href=\"#\"><i class=\"fa fa-circle-o\"></i><span>"
                        				+name2+"</span><i class=\"fa fa-angle-left pull-right\"></i></a>"
                        			context+="<ul class=\"treeview-menu\">";
                        			//--->遍历三级菜单
                        			$.each(all2.menu, function(i3, all3){
                        				context+="<li>";
                        				var name3 = all3.name;
                                		var type3 = all3.type;
                                		if(type3=="0"){
                                			//没有子菜单
                                			var url3 = all3.url;
                                			context+="<a href=\""+url3+"\" onclick=\"changePage('"+url3+"');return false\""+
         								   		"style=\"cursor: pointer;\"><i class=\"fa fa-circle-o\"></i><span>"+name3+"</span></a>";
                                		}else{
                                			//没有子菜单-最高3级
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
                		context+="</li>";		
                	});
                	//写入数据
                	document.getElementById("navul").innerHTML = context;
                }   
            }); 
		}
		var now_li_parent;//当前点击的li元素
		var flag=true;//第一次
		function changePage(url) {
			content = $('#main_content');
			content[0].src = url;
			var a_s=document.getElementById("navul").getElementsByTagName("a");
			for(var i=0;i<a_s.length;i++){
				if(a_s[i].getAttribute("href")==url){//find
					var  arr=new Array();
					arr[0]=a_s[i].innerText;
					//高亮显示
					if(flag){
						flag=false;
					}else{
						now_li_parent.setAttribute("class","");
					}
					now_li_parent=a_s[i].parentNode;
					now_li_parent.setAttribute("class","active");
					
					var a_parent=a_s[i].parentNode.parentNode;
					var j=1;
					while(a_parent.className.indexOf("sidebar-menu")<0){
						var parent=a_parent.parentNode;
						var a=parent.getElementsByTagName("a");
						if(a.length==0)continue;
						arr[j++]=a[0].innerText;
						a_parent=a_parent.parentNode.parentNode;
					}
					//show curr menu location
					var htmlText="";
					for(var k=arr.length;k>0;k--){
						if(k==arr.length){
							htmlText+="<li><a href='#'><i class='fa fa-dashboard'></i> "+arr[k-1]+"</a></li>";continue;
						}
						if(k==1){
							htmlText+="<li class='active'>"+arr[k-1]+"</li>";continue;
						}
						htmlText+="<li><a href='#'>"+arr[k-1]+"</a></li>";
					}
					$("#locDis").html(htmlText);
					break;
				}
			}
		}
		//Leo
		//登出界面
		function signOut(){
			layer.confirm(leaveMess, {title: tip,
			    btn: [system5,system6] //按钮
			}, function(){
				window.location.href="../system/system!signOut.do";
			}, function(){
			    ;
			});
		}
		//载入头像
		function getImg(){
			// 加载头像
			$.ajax({
				type : "POST",
				url : "../system/user!findPhoto.do",
				data : "id="+$("#userId").val(),
				success:function(data){
					 //成功的回调函数
					if(data.message=="success"){
						$("#userImg").attr("src",data.datas.photo);
						$("#userImg1").attr("src",data.datas.photo);
						$("#userImg2").attr("src",data.datas.photo);
					}   				       
				}
			});
		}
	</script>
	<script>
		//显示当前时间
		var week = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
		var t = window.setInterval(function(){
			var date = new Date();		
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
			//日2
			var day2 = week[(date.getDay()*1)];
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
			document.getElementById("dataTime").innerHTML = 
				year+"-"+month+"-"+day1+" "+day2+" "+hour+":"+minute+":"+second;	
		});
        //终止
	</script>
	<script type="text/javascript">
		var ws = null;
		var tryTime = 0;//尝试连接
		var ini_flag=true;//初始化标志
		//测试 58.246.137.126:60004  本地  localhost:8080
		//生产  172.19.226.240:18080   外网  tmp.clpsgroup.com.cn
		//10.1.1.1
		var websocket_conn="ws://"+window.location.hostname+":8080/TMP/ws/"+$("#userId").val();
		connect();
		
		//连接
		function connect() {
			if ('WebSocket' in window) {
				ws= new WebSocket(websocket_conn);
	            console.info("Wocket连接："+websocket_conn+"...");
				ws.onopen = function() {
					console.info('已连接');
				};
				
				ws.onmessage = function(event) {
					var receive_message=event.data;
					console.info('收到消息:' + receive_message);
					if(ini_flag){
						ini_flag=false;
					}else{
						layer.open({
							type : 1,
							title : false,
							closeBtn : 1, //0-不显示关闭按钮
							shade : [ 0 ],
							area : [ '320px', '200px' ],
							offset : 'rb', //右下角弹出
							time : 1200000, //120秒后自动关闭
							zIndex:-1,
							shift : 1,
							content : "<html><body><div style=\"color:#474747;background-color:#BADDF2;height:100%;width:100%;\">"+
										"<br>&nbsp;&nbsp;"+receive_message+
									  "</div></body></html>",
							end : function() { //此处用于演示20
							}
						});
					}
				};
				
				ws.onclose = function(event) {
					console.info('已断开' + event);
					ini_flag=true;
					// 重试10次，每次之间间隔1秒
					if (tryTime < 10) {
						console.info("第" + (tryTime + 1) + "次尝试重新连接...");
						setTimeout(function() {
							ws = null;
							tryTime++;
							connect();
						}, 1000);
					} else {
						tryTime = 0;
					}
				};
			} else {
				console.info("您的浏览器不支持WebSocket！");
			}
		}
		//断开连接
		function disconnect() {
			if (ws != null) {
				ws.close();
				ws = null;
			}
		}
		//测试交流
		function echo() {
			if (ws != null) {
				var message = "Hello,你好";
				ws.send(message);
				console.info('发送消息: ' + message);
			} else {
				alert('连接未建立，请连接.');
			}
		}
	</script>
</body>
</html>