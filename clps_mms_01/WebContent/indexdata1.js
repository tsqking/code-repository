
/*var indexdata =
[
 {
     isexpand: "false", text: "系统管理", children: [
         
     { url: "jsp/user.jsp", text: "用户管理" }
 
 ]
 },
 {
     isexpand: "false", text: "预约管理", children: [
         
     { url: "jsp/meetingroom.jsp", text: "会议室管理" },
     { url: "jsp/equipment.jsp", text: "设备管理" }
 
 ]
 }
 ];*/
var indexdata = [ {
	isexpand : "false",
	text : "系统管理",
	children : [ {
		url : "jsp/user.jsp",
		text : "用户管理"
	}, {
		url : "jsp/role.jsp",
		text : "角色管理"
	}, {
		isexpand : "false",
		text : "菜单管理",
		children : [ {
			url : "jsp/menu.jsp",
			text : "菜单信息"
		}, {
			url : "jsp/menuConstructor.jsp",
			text : "菜单结构"
		} ]
	}

	]
},
{
    isexpand: "false", text: "预约管理", children: [
        
    { url: "jsp/meetingroom.jsp", text: "会议室管理" },
    { url: "jsp/equipment.jsp", text: "设备管理" },
    { url: "jsp/equLog.jsp", text: "设备日志记录" }

]
}


];

