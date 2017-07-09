<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="base.jsp"></jsp:include>
<title>个人资料</title>
</head>
<body>
	<section id="main-content">
		<section class="wrapper">
			<!-- page start-->
			<div class="row self">
				<div class="col-lg-12">
					<section class="panel" style="height: 500px;">
						<div class="bio-graph-heading">
							<h2>个人资料</h2>
						</div>
						<div class="panel-body bio-graph-info">
							<h1>
								<img alt="${userinfo.u_nickname }" src="${userinfo.u_face }"
									style="width: 50px; height: 50px">&nbsp;<span>${userinfo.u_nickname }</span>
							</h1>
							<div class="row">
								<div class="bio-row">
									<p>
										<span>学号/工号:</span><span>${userinfo.u_id }</span>
									</p>
								</div>
								<div class="bio-row">
									<p>
										<span>用户昵称 :</span><span>${userinfo.u_nickname }</span>
									</p>
								</div>
								<div class="bio-row">
									<p>
										<span>姓名:</span><span>${userinfo.u_name }</span>
									</p>
								</div>
								<div class="bio-row">
									<p>
										<span>性别:</span><span>${userinfo.u_gender }</span>
									</p>
								</div>
								<div class="bio-row">
									<p>
										<span>年龄:</span><span>${userinfo.u_age }</span>
									</p>
								</div>
								<div class="bio-row">
									<p>
										<span>邮箱 :</span><span>${userinfo.u_email }</span>
									</p>
								</div>
								<div class="bio-row">
									<p>
										<span>地址:</span><span>${userinfo.u_address }</span>
									</p>
								</div>
								<div class="bio-row">
									<p>
										<span>电话:</span> <span>${userinfo.u_phone }</span>
									</p>
								</div>
								<div class="bio-row">
									<p>
										<span>签名:</span> <span>${userinfo.u_motto }</span>
									</p>
								</div>
								<div class="bio-row">
									<p>
										<span>头像:</span><span><img id="faceImg2"
											alt="${userinfo.u_nickname }" src="${userinfo.u_face }"
											style="width: 50px; height: 50px"></span>
									</p>
								</div>
							</div>
						</div>
					</section>
					<div>
						<button onclick="update()" class="btn btn-info">编辑资料</button>
						<button data-toggle="modal" data-target="#updateFaceModal" class="btn btn-danger">修改头像</button>
						<a href="index"><button class="btn btn-defalut">返回首页</button></a>
					</div>
				</div>
			</div>

			<!-- 修改资料模态框开始 -->
			<div class="modal fade responsive" id="update_self" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">修改资料</h4>
						</div>
						<div class="modal-body" style="margin: 30px;">
							<form id="selfForm" class="form-horizontal" role="form">
								<div class="form-group input-group">
									<span class="input-group-btn">
										<button class="btn btn-white" type="button"
											style="width: 100px;" disabled="disabled">学号/工号：</button>
									</span> <input type="text" class="form-control" id="inputUserid"
										disabled="disabled">
								</div>
								<div class="form-group input-group">
									<span class="input-group-btn">
										<button class="btn btn-white" type="button"
											style="width: 100px;" disabled="disabled">昵称:</button>
									</span> <input type="text" class="form-control" id="inputNickname"
										disabled="disabled">
								</div>
								<div class="form-group input-group">
									<span class="input-group-btn">
										<button class="btn btn-white" type="button"
											style="width: 100px;" disabled="disabled">姓名:</button>
									</span> <input type="text" class="form-control" id="inputName">
								</div>
								<div class="form-group input-group">
									<span class="input-group-btn">
										<button class="btn btn-white" type="button"
											style="width: 100px;" disabled="disabled">性别:</button>
									</span> <select id="inputGender" type="text" class="form-control">
										<option value="" selected="selected">&nbsp;</option>
										<option value="男">男</option>
										<option value="女">女</option>
									</select>
								</div>
								<div class="form-group input-group">
									<span class="input-group-btn">
										<button class="btn btn-white" type="button"
											style="width: 100px;" disabled="disabled">年龄:</button>
									</span><input type="text" class="form-control" id="inputAge">
								</div>
								<div class="form-group input-group">
									<span class="input-group-btn">
										<button class="btn btn-white" type="button"
											style="width: 100px;" disabled="disabled">邮箱:</button>
									</span> <input type="text" class="form-control email" id="inputEmail">
								</div>
								<div class="form-group input-group">
									<span class="input-group-btn">
										<button class="btn btn-white" type="button"
											style="width: 100px;" disabled="disabled">地址:</button>
									</span> <input type="text" class="form-control" id="inputAddress">
								</div>
								<div class="form-group input-group">
									<span class="input-group-btn">
										<button class="btn btn-white" type="button"
											style="width: 100px;" disabled="disabled">电话:</button>
									</span> <input type="text" class="form-control email" id="inputPhone">
								</div>
								<div class="form-group input-group">
									<span class="input-group-btn">
										<button class="btn btn-white" type="button"
											style="width: 100px;" disabled="disabled">签名:</button>
									</span> <input type="text" class="form-control" id="inputMotto">
								</div>
								<div class="form-group input-group">
									<span class="input-group-btn">
										<button class="btn btn-white" type="button"
											style="width: 100px;" disabled="disabled">密保问题:</button>
									</span> <input type="text" class="form-control" id="inputPasswordQues">
								</div>
								<div class="form-group input-group">
									<span class="input-group-btn">
										<button class="btn btn-white" type="button"
											style="width: 100px;" disabled="disabled">密保答案:</button>
									</span> <input type="text" class="form-control" id="inputPasswordAns">
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button onclick="submit()" type="button" class="btn btn-primary">提交更改</button>
							<button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 模态框结束 -->

		</section>
	</section>

	<!-- 修改头像模态框开始 -->
	<div class="modal fade responsive" id="updateFaceModal" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">修改头像</h4>
				</div>
				<div class="modal-body">
					<form action="upFace.do" method="post"
						enctype="multipart/form-data" onsubmit="return chechMyFace()">
						<table>
							<tr>
								<td width="20%" align="center"><span id="showIcon"><img
										id="showImg" src="${userinfo.u_face}" width="120" height="120"
										id="pic" /></span></td>
								<td width="53%"><p>
										<input type="file" name="icon" id="icon"
											onmouseout="checkIcon()" /> <input type="submit"
											class="btn btn-info" name="Submit" id="Submit" value=" 上传 " />
									</p>
									<p>请选择png、jpg、gif格式，且文件大小不超过2M的图片</p>
									<p>
										<span id="iconMsg"></span>&nbsp;
									</p></td>
							</tr>
						</table>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框结束 -->
</body>
<script src="js/own/self.js"></script>
</html>