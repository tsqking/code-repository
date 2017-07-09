<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="base.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>跳蚤市场</title>
</head>
<body>
	<section id="main-content">
		<section class="wrapper">
			<!-- page start-->
			<div class="row">
				<div class="col-lg-12">
					<section class="panel">
						<header class="panel-heading">
							<span><a>跳蚤市场</a></span>
						</header>
						<form>
							<div class="row">
								<div class="col-lg-4">
									<input id="searchGoodsName" class="form-control" type="text"
										placeholder="按商品名称搜索">
								</div>
								<div class="col-lg-4">
									<input id="searchGoodsSender" class="form-control" type="text"
										placeholder="按商品发布人搜索">
								</div>
								<div class="col-lg-4">
									<input class="btn btn-danger pull-right" type="reset"
										value="清空条件"> <input
										class="btn btn-primary pull-right" id="goodsSearchBtn"
										value="搜索" type="button">
								</div>
							</div>
						</form>
						<div id="toolbar_market">
							<button id="btn_want" type="button" class="btn btn-info btn-sm">
								<span class="icon-plus" aria-hidden="true"></span>我想要
							</button>
						</div>
						<table id="martketTbl"></table>
					</section>
				</div>
			</div>
			<!-- page end-->
		</section>
	</section>


	<!-- 查看详情模态框开始 -->
	<div class="modal fade center" id="check_detail" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">查看商品详情</h4>
				</div>
				<div class="modal-body" style="word-break: break-all">
					<h4 id="goodsName" align="center"></h4>
					<p id="goodsDetail"></p>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 查看详情模态框结束 -->
	
</body>
<script src="js/own/market.js"></script>
</html>