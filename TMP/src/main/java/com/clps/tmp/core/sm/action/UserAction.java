package com.clps.tmp.core.sm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.DataTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.common.util.BeanUtil;
import com.clps.tmp.core.common.util.SecurityHelper;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.service.UserService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户管理action
  * @ClassName: UserAction
  * @Description: TODO
  * @author Comsys-liuchen
  * @date 2015年10月19日 上午10:58:11
 */
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/system")
@Controller
@Scope("prototype")
@Action("user")
@Results({ @Result(name = "toUserManagementPage", location = "user/userManagement.jsp"),
		@Result(name = "toUserAdd", location = "user/userAdd.jsp"),
		@Result(name = "toUserUpdate", location = "user/userUpdate.jsp"),
		@Result(name = "toUserDetail", location = "user/userDetail.jsp"),
		@Result(name = "toUserRole", location = "user/roleManagement.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class UserAction extends BaseAction implements ModelDriven {

	private UserVo user;
	private String USER_PASSWORD_INFO = "userPwdInfo";
	// json返回数据map
	private HashMap<String, Object> resultMap;

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	
	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	@Override
	public Object getModel() {
		if (user == null) {
			user = new UserVo();
		}
		return user;
	}

	@Resource
	private UserService userService;
	
	/**
	 * 跳转用户添加界面
	  * toUserAdd(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: toUserAdd
	  * @Description: Comsys-liuchen
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String toUserAdd(){
		return "toUserAdd";
	}
	
	/**
	 * 跳转用户更新界面
	  * toUserUpdate(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: toUserUpdate
	  * @Description: Comsys-liuchen
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String toUserUpdate(){
		return "toUserUpdate";
	}
	
	/**
	 * 跳转用户详细查询界面
	  * toUserDetail(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: toUserDetail
	  * @Description: Comsys-liuchen
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String toUserDetail(){
		return "toUserDetail";
	}
	
	/**
	 * 跳转用户管理首页
	  * toUserManagementPage(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: toUserManagement
	  * @Description: Comsys-liuchen
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String toUserManagementPage(){
		return "toUserManagementPage";
	}
	
	/**
	 * 跳转用户角色管理
	  * toUserRole(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: toUserRole
	  * @Description: Comsys-liuchen
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String toUserRole(){
		return "toUserRole";
	}
	
	/**
	 * 用户账户数据表格
	  * getUserPage(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: getUserPage
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String getUserPage() throws Exception{
		DataTableUtil<UserVo> du = new DataTableUtil<UserVo>();
		PageVo<UserVo> pv = du.getTableData(this.getHttpRequest());
		//1.搜索条件
		//1.1.精确查询
		HashMap<String, String> where1 = new HashMap<String, String>();
		if(user.getRole()!=null&&!user.getRole().equals("")){
			where1.put("role", user.getRole());
		}
		if(user.getNo()!=null&&!user.getNo().equals("")){
			where1.put("no", user.getNo());
		}
		if(user.getEn_name()!=null&&!user.getEn_name().equals("")){
			where1.put("en_name", user.getEn_name());
		}
		if(user.getGender()!=null&&!user.getGender().equals("")){
			where1.put("gender", user.getGender());
		}
		if(user.getAge()!=null&&!user.getAge().equals("")){
			where1.put("age", user.getAge());
		}
		if(user.getEnable()!=null&&!user.getEnable().equals("")){
			where1.put("enable", user.getEnable());
		}
		pv.setWhere1(where1);
		//1.2.模糊查询
		HashMap<String, String> where2 = new HashMap<String, String>();
		if(user.getUsername()!=null&&!user.getUsername().equals("")){
			where2.put("username", user.getUsername());
		}
		if(user.getName()!=null&&!user.getName().equals("")){
			where2.put("name", user.getName());
		}
		if(user.getMobile()!=null&&!user.getMobile().equals("")){
			where2.put("mobile", user.getMobile());
		}
		if(user.getPhone()!=null&&!user.getPhone().equals("")){
			where2.put("phone", user.getPhone());
		}
		if(user.getEmail()!=null&&!user.getEmail().equals("")){
			where2.put("email", user.getEmail());
		}
		if(user.getUpdate_person()!=null&&!user.getUpdate_person().equals("")){
			where2.put("update_person", user.getUpdate_person());
		}
		pv.setWhere2(where2);
		//1.2.时间范围
		HashMap<String, String> dateTime = new HashMap<String, String>();
		if(user.getCreate_time()!=null&&!user.getCreate_time().equals("")){
			dateTime.put("create_time", user.getCreate_time());
		}
		if(user.getUpdate_time()!=null&&!user.getUpdate_time().equals("")){
			dateTime.put("update_time", user.getUpdate_time());
		}
		pv.setDateTime(dateTime);
		//3.获取数据	
		pv = userService.queryUserPage(pv);
		//3.5.权限修改
		UserVo userNow = (UserVo)session.get(SystemConstant.USER);
		//修改人角色
		int role = Integer.parseInt(userNow.getRole());
		List<UserVo> list = pv.getList();
		for(UserVo u : list){
			int role1 = Integer.parseInt(u.getRole());
			//权限大小分级
			if(role<role1){
				//登录人权限大
				u.setFlag("true");
			}else{
				//登录人权限小或相等
				if(u.getId().equals(userNow.getId())){
					//本人
					u.setFlag("true");
				}else{
					//非本人
					u.setFlag("false");
				}
			}	
		}
		pv.setList(list);
		//4.返回值
		resultMap = du.getReturnMap(pv);		
		return "json";
	}
	
	/**
	 * 用户详细信息查询
	  * detailUser(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: detailUser
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String detailUser() throws Exception{
		UserVo uv = userService.detailUser(user.getId());
		//记录用户密码信息
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("0", uv.getId());//0为id
		map.put("1", uv.getPassword());//1为旧密码
		map.put("2", "false");//2为标识
		session.put(this.USER_PASSWORD_INFO, map);
		//
		uv.setCreate_time(DateTimeUtil.databaseToSystem(uv.getCreate_time()));
		uv.setUpdate_time(DateTimeUtil.databaseToSystem(uv.getUpdate_time()));
		uv.setBirthday(DateTimeUtil.databaseToSystem(uv.getBirthday()));
		uv.setPassword("******");
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("info", BeanUtil.changeBean(uv));
		resultMap=rtn.getReturnMap();
		return "json";
	}
	

	/**
	 * @throws Exception 
	 * 获取账户头像
	  * findPhoto(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: findPhoto
	  * @Description: Comsys-liuchen
	  * @param     设定文件
	  * @return void    返回类型
	  * @throws
	 */
	public String findPhoto() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("image/jpeg");
		response.setCharacterEncoding("UTF-8");
		String photo = userService.queryUserPhoto(user.getId());
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(photo!=null){
			rtn.setMessage("success");
			photo = photo.replaceAll(" ", "+");
			rtn.add("photo", photo);
		}else{
			rtn.setMessage("error");
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @throws Exception 
	 * 跟新用户信息
	  * updateUserInfo(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: updateUserInfo
	  * @Description: Comsys-liuchen
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@SuppressWarnings("static-access")
	public String updateUserInfo() throws Exception{
		UserVo userNow = (UserVo)session.get(SystemConstant.USER);
		//修改人角色
		int role = Integer.parseInt(userNow.getRole());
		if(role<=1){
			//可以修改角色
			//被修改人角色
			int role1 = Integer.parseInt(user.getRole());
			//判断角色,越小越大
			if(role1<role){
				//修改人角色赋予
				user.setRole(userNow.getRole());
			}
		}else{
			//不可以修改角色
			user.setRole(null);
		}
		user.setUpdate_person(userNow.getName());
		user.setUpdate_time(DateTimeUtil.nowToDatabase());
		user.setBirthday(user.getBirthday().replaceAll("-", ""));
		//对密码的处理
		@SuppressWarnings("unchecked")
		HashMap<String,String> map= (HashMap<String,String>)session.get(this.USER_PASSWORD_INFO);
		if(map.get("0").equals(user.getId())){
			if(map.get("2").equals("true")){
				//nothing to do
				SecurityHelper sh = new SecurityHelper();		
				user.setPassword(sh.DESEncrypt(user.getPassword()));	
			}else{
				user.setPassword(map.get("1"));	
			}
			boolean re = userService.updateUserInfo(user);
			AjaxReturnInfo rtn = AjaxReturnInfo.success("");
			if(re){
				rtn.setMessage("success");
			}else{
				rtn.setMessage("error");
			}
			resultMap=rtn.getReturnMap();
			return "json";
		}else{
			AjaxReturnInfo rtn = AjaxReturnInfo.success("");
			rtn.setMessage("error");
			resultMap=rtn.getReturnMap();
			return "json";
		}	
	}
	
	/**
	 * 判断是否为超级管理员
	  * judgeSuperAdmin(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: judge
	  * @Description: Comsys-liuchen
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String judgeSuperAdmin(){
		UserVo user = (UserVo)session.get(SystemConstant.USER);
		String role = user.getRole();
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.setMessage(role);
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 更新图片
	  * updateUserPhoto(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: updateUserPhoto
	  * @Description: Comsys-liuchen
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String updateUserPhoto() throws Exception{
		String photo = user.getPhoto();
		String id = user.getId();
		boolean re = userService.updateUserPhoto(photo, id);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(re){
			rtn.setMessage("success");
		}else{
			rtn.setMessage("error");
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 新建账户
	  * addUserInfo(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: addUserInfo
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String addUserInfo() throws Exception{
		UserVo userNow = (UserVo)session.get(SystemConstant.USER);
		int maxRole = Integer.parseInt(userNow.getRole());
		int target = Integer.parseInt(user.getRole());
		if(target<maxRole){
			//目标权限>自己权限
			user.setRole(maxRole+"");
		}
		boolean re = userService.addUserInfo(user, userNow);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(re){
			rtn.setMessage("success");
		}else{
			rtn.setMessage("error");
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 删除用户
	  * deleteUser(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: deleteUser
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String deleteUser() throws Exception{
		UserVo userNow = (UserVo)session.get(SystemConstant.USER);
		int maxRole = Integer.parseInt(userNow.getRole());
		int target = Integer.parseInt(user.getRole());
		if(target<maxRole){
			//目标权限>自己权限
			AjaxReturnInfo rtn = AjaxReturnInfo.success("");
			rtn.setMessage("1111");//权限不足
			resultMap=rtn.getReturnMap();
			return "json";
		}else{
			boolean re = userService.deleteUser(user);
			AjaxReturnInfo rtn = AjaxReturnInfo.success("");
			if(re){
				rtn.setMessage("success");
			}else{
				rtn.setMessage("2222");//有关联，不允许删除
			}
			resultMap=rtn.getReturnMap();
			return "json";
		}		
	}
	
	/**
	 * 检查数据的唯一
	  * checkData(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: checkData
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String checkData()throws Exception{
		String userName = user.getUsername();
		String phone = user.getMobile();
		//登录名
		if(userName!=null&&!userName.equals("")){
			AjaxReturnInfo rtn = AjaxReturnInfo.success("");
			rtn.setSuccess(true);
			if(userName.toUpperCase().startsWith("TMPUSER")){
				//名称前缀不合法
				rtn.add("result", "name");
			}else{
				int r = userService.checkData(userName, null);
				if(r==0){
					//查询失败
					rtn.add("result", "error");
				}else if(r==1){
					//无重复
					rtn.add("result", "success");
				}else if(r==2){
					//重复
					rtn.add("result", "repeat");
				}else{
					//查询失败
					rtn.add("result", "error");
				}
			}		
			//返回			
			resultMap=rtn.getReturnMap();
			return "json";
		}
		//手机号
		if(phone!=null&&!phone.equals("")){
			int r = userService.checkData(null, phone);
			AjaxReturnInfo rtn = AjaxReturnInfo.success("");
			rtn.setSuccess(true);
			if(r==0){
				//查询失败
				rtn.add("result", "error");
			}else if(r==1){
				//无重复
				rtn.add("result", "success");
			}else if(r==2){
				//重复
				rtn.add("result", "repeat");
			}else{
				//查询失败
				rtn.add("result", "error");
			}
			//返回			
			resultMap=rtn.getReturnMap();
			return "json";
		}
		//返回	
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.setSuccess(false);
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	
	//角色管理---------------------------------------------------------------------
	
	/**
	 * 用户角色管理
	  * getRolePage(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: getRolePage
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String getRolePage() throws Exception{
		DataTableUtil<UserVo> du = new DataTableUtil<UserVo>();
		PageVo<UserVo> pv = du.getTableData(this.getHttpRequest());
		//1.搜索条件
		//1.1.精确查询
		HashMap<String, String> where1 = new HashMap<String, String>();
		if(user.getEnable()!=null&&!user.getEnable().equals("")){
			where1.put("enable", user.getEnable());
		}
		pv.setWhere1(where1);
		//1.2.模糊查询
		HashMap<String, String> where2 = new HashMap<String, String>();
		if(user.getName()!=null&&!user.getName().equals("")){
			where2.put("name", user.getName());
		}
		if(user.getDescription()!=null&&!user.getDescription().equals("")){
			where2.put("description", user.getDescription());
		}
		if(user.getCreate_person()!=null&&!user.getCreate_person().equals("")){
			where2.put("create_person", user.getCreate_person());
		}
		if(user.getUpdate_person()!=null&&!user.getUpdate_person().equals("")){
			where2.put("update_person", user.getUpdate_person());
		}
		pv.setWhere2(where2);
		//1.3自定义查询
		HashMap<String, String> other = new HashMap<String, String>();
		if(user.getHas()!=null&&!user.getHas().equals("")){
			other.put("has", user.getId()+"-"+user.getHas());
		}
		pv.setOther(other);
		//1.4.时间范围
		HashMap<String, String> dateTime = new HashMap<String, String>();
		if(user.getCreate_time()!=null&&!user.getCreate_time().equals("")){
			dateTime.put("create_time", user.getCreate_time());
		}
		if(user.getUpdate_time()!=null&&!user.getUpdate_time().equals("")){
			dateTime.put("update_time", user.getUpdate_time());
		}
		pv.setDateTime(dateTime);
		//3.获取数据	
		pv = userService.queryRolePage(pv);
		//4.返回值
		resultMap = du.getReturnMap(pv);		
		return "json";
	}
	
	/**
	 * 获取所有角色
	  * getUserRoles(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: getUserRoles
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String getUserRoles() throws Exception{
		ArrayList<String> li = userService.queryUserRoles(user.getId());
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("info", li);
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 去除用户角色
	  * redUserRoles(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: redUserRoles
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String redUserRoles() throws Exception {
		boolean re = userService.updateUserRole(user.getId(), user.getRole(), false);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(re){
			rtn.setMessage("success");
		}else{
			rtn.setMessage("error");
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 添加用户角色
	  * addUserRoles(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: addUserRoles
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String addUserRoles() throws Exception {
		boolean re = userService.updateUserRole(user.getId(), user.getRole(), true);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(re){
			rtn.setMessage("success");
		}else{
			rtn.setMessage("error");
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 删除所有角色
	  * deleteAllRoles(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: deleteAllRoles
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String deleteAllRoles() throws Exception {
		boolean re = userService.deleteUserAllRole(user.getId());
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(re){
			rtn.setMessage("success");
		}else{
			rtn.setMessage("error");
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 确认密码
	  * checkUserNow(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: checkUserNow
	  * @Description: Comsys-liuchen
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String checkUserNow(){
		UserVo userNow = (UserVo)session.get(SystemConstant.USER);
		SecurityHelper sh = new SecurityHelper();
		@SuppressWarnings("static-access")
		String p1 = sh.DESEncrypt(user.getPwd1());
		String pwd1 = userNow.getPassword();
		@SuppressWarnings("static-access")
		String p2 = sh.DESEncrypt(user.getPwd2());
		@SuppressWarnings("unchecked")
		String pwd2 = ((HashMap<String,String>)session.get(this.USER_PASSWORD_INFO)).get("1");
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		//1.校验当前用户密码
		if(p1.equals(pwd1)){
			//判断二次校验
			boolean f = false;
			String role = userNow.getRole();
			if(!role.equals("0")){
				if(p2.equals(pwd2)){
					rtn.setMessage("success");
					f = true;
				}else{
					rtn.setMessage("error");
				}	
			}else{
				rtn.setMessage("success");
				f = true;
			}
			//写入判断值
			if(f){
				@SuppressWarnings("unchecked")
				HashMap<String,String> map = (HashMap<String,String>)session.get(this.USER_PASSWORD_INFO);
				map.put("2", "true");
				session.put(this.USER_PASSWORD_INFO, map);
			}	
		}else{
			rtn.setMessage("error");
		}
		resultMap=rtn.getReturnMap();
		return "json";	
	}
	
}
