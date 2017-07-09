<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="base.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>朋友圈</title>
</head>
<body>
	<section id="main-content">
		<section class="wrapper">
			<!-- page start-->
			<div class="row">
				<aside class="profile-nav col-lg-3">
					<section class="panel">
						<div class="user-heading round ">
							<a href="#"> <img src="${userinfo.u_face }" alt="头像"
								style="width: 140px; height: 140px">
							</a>
							<h1>${userinfo.u_nickname }</h1>
							<p>${userinfo.u_motto }</p>
						</div>
					</section>
					<section class="panel">
						<div class="panel-body">
							<span>积分排行</span>
						</div>
						<table class="table table-hover personal-task">
							<c:forEach var="qp" items="${queryPoint }">
								<tr>
									<td><img alt="" src="${qp.u_face }"
										style="width: 50px; height: 50px;"></td>
									<td><div>
											<h4>${qp.u_nickname }</h4>
										</div></td>
									<td><div>当前积分</div>
										<div>${qp.u_point }</div></td>
								</tr>
							</c:forEach>
						</table>
					</section>
				</aside>
				<!-- 微博发布 -->
				<aside class="profile-info col-lg-6">
					<section class="panel">
						<form>
							<textarea id="emojiContent" placeholder="今天心情如何？" rows="4"
								class="form-control input-lg p-text-area"></textarea>
						</form>
						<footer class="panel-footer">
							<button class="btn btn-success pull-right" id="weiboPublish">发布</button>
							<ul class="nav nav-pills">
								<button id="emojiBtn" class="icon-smile btn"></button>
								<button id="imgBtn" class="icon-camera btn"></button>
							</ul>
							<form action="getWeiBoImg.do" method="post"
								enctype="multipart/form-data" id="weiboImgForm">
								<input type="file" id="imgfile" name="imgfile"
									multiple="multiple" onchange="getFilePath()"
									style="filter: alpha(opacity = 0); opacity: 0; width: 0; height: 0;"/<
							</form>
						</footer>
					</section>
					<c:forEach var="aw" items="${allWeibo }">
						<section class="panel">
							<div class="panel">
								<div class="panel-body bio-graph-info">
									<article class="media">
										<a class="pull-left thumb p-thumb"><img
											alt="${aw.w_nickname }" src="${aw.u_face }"
											style="width: 50px; height: 50px"> </a>
										<div class="media-body">
											<a class=" p-head">${aw.w_nickname }</a>
											<p>${aw.w_date }</p>
										</div>
									</article>
									<div class="text">${aw.w_content }</div>
									<div id="weiboImgDiv" hidden="true"></div>
									<div id="commentDiv" hidden="true"></div>
									<%-- <div class="btn-group btn-group-justified">
										<a class="btn icon-share" href="#">&nbsp;${aw.w_forward_num }</a>
										<a class="btn icon-comment" href="#">&nbsp;${aw.w_praise_num }</a>
										<a class="btn icon-thumbs-up" href="#">&nbsp;${aw.w_praise_num }</a>
									</div> --%>
								</div>
							</div>
						</section>
					</c:forEach>
				</aside>

				<aside class="profile-info col-lg-3">
					<section class="panel">
						<div class="panel-body bio-graph-info">
							<ul class="nav nav-pills nav-stacked labels-info ">
								<li>
									<h3 class="">推荐添加</h3>
								</li>
								</a>
								</li>
								<c:forEach var="nm" items="${nicMotto }">
									<li><a href="#"> <i class=" icon-circle text-success"></i>
											${nm.u_nickname }
											<p>${nm.u_motto }</p></a></li>
								</c:forEach>
							</ul>
						</div>
					</section>
				</aside>
			</div>
			<!-- page end-->
		</section>
	</section>
	<!--main content end-->
	<script src="js/own/myEmoji.js"></script>
	<script src="js/own/friend.js"></script>
</body>
</html>