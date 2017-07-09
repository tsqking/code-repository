/*
 *	评论表情渲染JS
 *	@author:	小毛(微博：BUPT朱小毛)
 *	@data:		2013年2月17日
 *	@version:	1.0
 *	@rely:		jQuery
 */
$(function() {
	/*
	 * 参数说明 baseUrl: 【字符串】表情路径的基地址 pace: 【数字】表情弹出层淡入淡出的速度 dir: 【数组】保存表情包文件夹名字
	 * text: 【二维数组】保存表情包title文字 num: 【数组】保存表情包表情个数 isExist:
	 * 【数组】保存表情是否加载过,对于加载过的表情包不重复请求。
	 */
	var rl_exp = {
		baseUrl : '',
		pace : 200,
		dir : [ 'menuicon' ],
		text : [ /* 表情包title文字，自己补充 */
		[ 'jsp/images/menuicon/0.png', 
		  'jsp/images/menuicon/1.png',
		  'jsp/images/menuicon/2.png', 
		  'jsp/images/menuicon/3.png', 
		  'jsp/images/menuicon/4.png', 
		  'jsp/images/menuicon/5.png', 
		  'jsp/images/menuicon/6.png', 
		  'jsp/images/menuicon/7.png', 
		  'jsp/images/menuicon/8.png', 
		  'jsp/images/menuicon/9.png', 
		  'jsp/images/menuicon/10.png', 
		  'jsp/images/menuicon/11.png', 
		  'jsp/images/menuicon/12.png', 
		  'jsp/images/menuicon/13.png', 
		  'jsp/images/menuicon/14.png', 
		  'jsp/images/menuicon/15.png', 
		  'jsp/images/menuicon/16.png', 
		  'jsp/images/menuicon/17.png', 
		  'jsp/images/menuicon/18.png', 
		  'jsp/images/menuicon/19.png', 
		  'jsp/images/menuicon/20.png', 
		  'jsp/images/menuicon/21.png', 
		  'jsp/images/menuicon/22.png', 
		  'jsp/images/menuicon/23.png', 
		  'jsp/images/menuicon/24.png', 
		  'jsp/images/menuicon/25.png', 
		  'jsp/images/menuicon/26.png', 
		  'jsp/images/menuicon/27.png', 
		  'jsp/images/menuicon/28.png', 
		  'jsp/images/menuicon/29.png', 
		  'jsp/images/menuicon/30.png', 
		  'jsp/images/menuicon/31.png', 
		  'jsp/images/menuicon/32.png', 
		  'jsp/images/menuicon/33.png', 
		  'jsp/images/menuicon/34.png', 
		  'jsp/images/menuicon/35.png', 
		  'jsp/images/menuicon/36.png', 
		  'jsp/images/menuicon/37.png', 
		  'jsp/images/menuicon/38.png', 
		  'jsp/images/menuicon/39.png'
				] 
		],
		num : [ 39 ],
		isExist : [ 0 ],
		bind : function(i) {
			$("#rl_bq .rl_exp_main").eq(i).find('.rl_exp_item').each(
					function() {
						$(this).bind(
								'click',
								function() {
									$("#mnu_logo_url").val("");
									rl_exp.insertText(document
											.getElementById('mnu_logo_url'), $(
											this).find('img').attr('title'));
									$('#rl_bq').fadeOut(rl_exp.pace);
								});
					});
		},
		/* 加载表情包函数 */
		loadImg : function(i) {
			var node = $("#rl_bq .rl_exp_main").eq(i);
			for (var j = 0; j < rl_exp.num[i]; j++) {
				var domStr = '<li class="rl_exp_item">' + '<img src="'
						+ rl_exp.baseUrl + 'images/' + rl_exp.dir[i] + '/' + j
						+ '.png" alt="' + rl_exp.text[i][j] + '" title="'
						+ rl_exp.text[i][j] + '" />' + '</li>';
				$(domStr).appendTo(node);
			}
			rl_exp.isExist[i] = 1;
			rl_exp.bind(i);
		},
		/* 在textarea里光标后面插入文字 */
		insertText : function(obj, str) {
			obj.focus();
			if (document.selection) {
				var sel = document.selection.createRange();
				sel.text = str;
			} else if (typeof obj.selectionStart == 'number'
					&& typeof obj.selectionEnd == 'number') {
				var startPos = obj.selectionStart, endPos = obj.selectionEnd, cursorPos = startPos, tmpStr = obj.value;
				obj.value = tmpStr.substring(0, startPos) + str
						+ tmpStr.substring(endPos, tmpStr.length);
				cursorPos += str.length;
				obj.selectionStart = obj.selectionEnd = cursorPos;
			} else {
				obj.value += str;
			}
		},
		init : function() {
			$("#rl_bq > ul.rl_exp_tab > li > a")
					.each(
							function(i) {
								$(this)
										.bind(
												'click',
												function() {
													if ($(this).hasClass(
															'selected'))
														return;
													if (rl_exp.isExist[i] == 0) {
														rl_exp.loadImg(i);
													}
													$(
															"#rl_bq > ul.rl_exp_tab > li > a.selected")
															.removeClass(
																	'selected');
													$(this)
															.addClass(
																	'selected');
													$('#rl_bq .rl_selected')
															.removeClass(
																	'rl_selected')
															.hide();
													$('#rl_bq .rl_exp_main')
															.eq(i)
															.addClass(
																	'rl_selected')
															.show();
												});
							});
			/* 绑定表情弹出按钮响应，初始化弹出默认表情。 */
			$("#rl_exp_btn").bind('click', function() {
				if (rl_exp.isExist[0] == 0) {
					rl_exp.loadImg(0);
				}
				var w = $(this).position();
				$('#rl_bq').css({
					left : 90,
					top : 100
				}).fadeIn(400);
			});
			/* 绑定关闭按钮 */
			$('#rl_bq a.close').bind('click', function() {
				$('#rl_bq').fadeOut(rl_exp.pace);
			});
			/* 绑定document点击事件，对target不在rl_bq弹出框上时执行rl_bq淡出，并阻止target在弹出按钮的响应。 */
			$(document).bind('click', function(e) {
				var target = $(e.target);
				if (target.closest("#rl_exp_btn").length == 1)
					return;
				if (target.closest("#rl_bq").length == 0) {
					$('#rl_bq').fadeOut(rl_exp.pace);
				}
			});
		}
	};
	rl_exp.init(); // 调用初始化函数。
});