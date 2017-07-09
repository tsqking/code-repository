var system0 = "Drop box initialization error!";
var system1 = {
		'Today' : [moment().startOf('day'), moment()],// system1:今日 - 
	    'Yesterday' : [moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day')],// system2:昨日 - 
	    '7 days' : [moment().subtract('days', 6), moment()],// system3:最近7日 - 
	    '30 days' : [moment().subtract('days', 29), moment()]// system4:最近30日 - 
	};
var system2 = 'en-US';
var system5 = "Confirm";
var system6 = "Cancel";
var system7 = "Start time";
var system8 = "End time";
var system9 = "Custom";
var system10 = "Today";
var system11 = "Yesterday";
var system12 = "7 days";
var system13 = "30 days";
var system14 = "Last year";
var system15 = "Custom";
var system16 = "Select the _MENU_ records per page";
var system17 = "Sorry, I can't find any relevant data.";
var system18 = "Current display _START_ to _END_, a total of _TOTAL_ records";
var system19 = "Can not find the relevant data";
var system20 = "Under load...";
var system21 = "Quick search";
var system22 = "First page";
var system23 = "Previous page";
var system24 = "Next page";
var system25 = "Last page";


var system26 = [ 'Sun.', 'Mon.', 'Tues.', 'Wed.', 'Thur.', 'Fri.', 'Sat.' ];
var system27 = [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December' ];

var systemOK = "Determine";
var systemCancel = "Cancel";
var systemInfo = "Information";
/*
星期一：Mon.=Monday 
星期二：Tues.=Tuesday 
星期三：Wed.=Wednesday 
星期四：Thur.=Thursday 
星期五：Fri.=Friday 
星期六：Sat.=Saturday 
星期天：Sun.=Sunday
一月：January 
二月：February
三月：March
四月：April
五月：May 
六月：June 
七月：July
八月：August 
九月：September 
十月：October 
十一月：November
十二月：December
*/

$.fn.datetimepicker.dates['lang'] = {
	    days: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
	    daysShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
	    daysMin: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"],
	    months: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
	    monthsShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
	    today: "Today",
	    suffix: [],
		meridiem: ["AM", "PM"]
	};

var functionMenu="Function Menu";
var leaveMess="Confirm to leave the system!";
var tip="Message";

//统一验证
var system40="Please fill the required blank!";
var system41="[";
var system42="]";
var system43=" can not be null!";
var system44="Incorrect Number!";
var system45=" type wrong(Number)!";
var system46="Incorrect Email!";
var system47=" type wrong!";
var system48="Incorrect mobile number(11 number)!";
var system49=" fill wrong(11 number)!";
var system50="The value length";
var system51=" no more than ";
var system52=" no less than ";
var system53=" between ";
var system54=" and ";
var system55=" ";
var system56="Incorrent Card No.!";
var system57=" fill wrong!";
var system58="Incorrent Phone No.!";
var system59=" fill wrong!";
var system60="Incorrect PostCode!";
var system61=" fill wrong!";

var btTable_local_String="en-US";

var system62="Time Line Start Time";
var system63="Operation Failed!";

var time1 = "days";
var time2 = "hours";
var time3 = "min";