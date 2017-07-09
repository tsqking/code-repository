var system0 = "下拉框初始化出错!";
var system1 = {
		'今日' : [moment().startOf('day'), moment()],// system1:今日 - 
	    '昨日' : [moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day')],// system2:昨日 - 
	    '最近7日' : [moment().subtract('days', 6), moment()],// system3:最近7日 - 
	    '最近30日' : [moment().subtract('days', 29), moment()]// system4:最近30日 - 
	};
var system2 = 'zh-CN';
var system5 = "确定";
var system6 = "取消";
var system7 = "起始时间";
var system8 = "结束时间";
var system9 = "自定义";
var system10 = "今日";
var system11 = "昨日";
var system12 = "最近7日";
var system13 = "最近30日";
var system14 = "最近一年";
var system15 = "自定义";
var system16 = "每页选择 _MENU_ 记录";
var system17 = "对不起，查询不到任何相关数据";
var system18 = "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录";
var system19 = "找不到相关数据";
var system20 = "正在加载中...";
var system21 = "快速搜索";
var system22 = "第一页";
var system23 = "上一页 ";
var system24 = "下一页 ";
var system25 = "最后一页";


var system26 = [ '日', '一', '二', '三', '四', '五', '六' ];
var system27 = [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ];

var systemOK = "确定";
var systemCancel = "取消";
var systemInfo = "信息";

$.fn.datetimepicker.dates['lang'] = {
		days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
		daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
		daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
		months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		today: "今天",
		suffix: [],
		meridiem: ["上午", "下午"]
};

var functionMenu="功能菜单";
var leaveMess="确定要退出系统!";
var tip="提示";

//统一验证
var system40="请填上必填信息！";
var system41="【";
var system42="】";
var system43="不能为空！";
var system44="数字填写不正确！";
var system45="格式不正确(数字)！";
var system46="邮箱格式不正确！";
var system47="格式不正确！";
var system48="手机号填写不正确(11位数字)！";
var system49="填写不正确(11位数字)！";
var system50="值长度";
var system51="应该不大于";
var system52="应该不小于";
var system53="应该介于";
var system54="至";
var system55="之间";
var system56="证件号填写不正确！";
var system57="填写不正确！";
var system58="电话号码填写不正确！";
var system59="填写不正确！";
var system60="邮编填写不正确！";
var system61="填写不正确！";

var btTable_local_String="zh-CN";

var system62="时间线起始时间";
var system63="操作失败！";

var time1 = "天";
var time2 = "小时";
var time3 = "分钟";
