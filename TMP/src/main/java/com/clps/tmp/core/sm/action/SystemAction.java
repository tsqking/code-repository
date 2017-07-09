package com.clps.tmp.core.sm.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.MailSenderUtil;
import com.clps.tmp.common.util.StringUtil;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.common.util.SecurityHelper;
import com.clps.tmp.core.sm.vo.MenuVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.service.MenuService;
import com.clps.tmp.core.sm.service.UserService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 系统action
 * 
 * @ClassName: SystemAction
 * @Description: TODO
 * @author Comsys-liuchen
 * @date 2015年10月9日 上午9:46:25
 */
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/system")
@Controller
@Scope("prototype")
@Action("system")
@Results({ @Result(name = "toMainPage", location = "../main.jsp"),
		@Result(name = "toLoginPage", location = "../../../login.jsp"),
		@Result(name = "toWelcomePage", location = "../welcome.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class SystemAction extends BaseAction implements ModelDriven {

	private UserVo user;
	// json返回数据map
	private HashMap<String, Object> resultMap;

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
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
	@Resource
	private MenuService menuService;
	
	

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	/**
	 * 用户登录方法 loginSystem(这里用一句话描述这个方法的作用) TODO(这里描述这个方法适用条件 – 可选)
	 * TODO(这里描述这个方法的执行流程 – 可选) TODO(这里描述这个方法的使用方法 – 可选) TODO(这里描述这个方法的注意事项 –
	 * 可选)
	 * 
	 * @Title: loginSystem
	 * @Description: TODO
	 * @param @throws Exception 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	@SuppressWarnings("static-access")
	public String loginSystem() throws Exception{		
		AjaxReturnInfo rtn=null;
		//根据账户名取出用户信息
		UserVo user2 = userService.queryUserInfo(user);
		if(user2==null){
			//没有用户信息
			rtn=AjaxReturnInfo.success("1111");		
		}else{
			SecurityHelper sh = new SecurityHelper();
			if(!sh.DESEncrypt(user.getPassword()).equals(user2.getPassword())){
				//密码不正确
				rtn=AjaxReturnInfo.success("2222");
			}else if("F".equals(user2.getEnable())){
				//用户被禁用
				rtn=AjaxReturnInfo.success("3333");
			}else{
				//登录成功
				rtn=AjaxReturnInfo.success("0000");	
				//塞入用户信息到session
				this.getSession().put(SystemConstant.USER, user2);
				//塞入登录时间
				this.getSession().put(SystemConstant.LOGINTIME, StringUtil.getDate(new Date(),null));
				//塞入账户
				this.getSession().put(SystemConstant.USERNAME, user2.getUsername());
				//用户语言环境  初始化为zh_CN
				this.session.put(SystemConstant.LANG, "zh_CN");
				//写入访问者IP
				// 获取访问者IP
				String ip = this.httpRequest.getHeader("x-forwarded-for");
				if (ip == null || ip.length() == 0
						|| "unknown".equalsIgnoreCase(ip)) {
					ip = httpRequest.getHeader("Proxy-Client-IP");
				}
				if (ip == null || ip.length() == 0
						|| "unknown".equalsIgnoreCase(ip)) {
					ip = httpRequest.getHeader("WL-Proxy-Client-IP");
				}
				if (ip == null || ip.length() == 0
						|| "unknown".equalsIgnoreCase(ip)) {
					ip = httpRequest.getRemoteAddr();
				}	
				this.getSession().put(SystemConstant.IP_ADDRESS,ip);
				//传入用户类别  1-招生  2-教学
				this.getSession().put(SystemConstant.USER_TYPE, "2");//当前此种方式设定为教学，以后校招的会有校招登陆模块
			}	
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	
	/**
	 * 跳转首页
	  * toMainPage(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  * @Title: toMainPage
	  * @Description: TODO
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String toMainPage(){
		Locale locale=(Locale) this.session.get("WW_TRANS_I18N_LOCALE");
		if(locale!=null){
			this.session.put(SystemConstant.LANG, locale.toString());//zh_CN/en_US
		}
		//id写入
		UserVo userNow = (UserVo)session.get(SystemConstant.USER);
		user.setId(userNow.getId());
		//跳转
		return "toMainPage";
	}
	
	
	/**
	 * 登出系统
	  * signOut(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  * @Title: signOut
	  * @Description: TODO
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String signOut(){
		//清除数据
		this.session.remove(SystemConstant.USERNAME);
		//this.httpRequest.getSession(false).invalidate();
		return "toLoginPage";
	}
	
	
	/**
	 * 主页获取系统菜单
	  * getMainMenu(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  * @Title: getMainMenu
	  * @Description: TODO
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public String getMainMenu() throws Exception{
		//1.获取当前用户信息
		UserVo uv = (UserVo)this.session.get(SystemConstant.USER);
		//2.获取菜单
		List<MenuVo> li = menuService.queryUserMenu(uv);
		//3.给菜单分类-默认有三级菜单
		//3.1.分类
		LinkedHashMap<String,Object> li1 = new LinkedHashMap<String,Object>();
		LinkedHashMap<String,Object> li2= new LinkedHashMap<String,Object>();
		LinkedHashMap<String,Object> li3= new LinkedHashMap<String,Object>();
		for(MenuVo mv : li){
			HashMap<String,Object> m = new HashMap<String,Object>();
			m.put("name", mv.getName()==null?"":mv.getName());
			m.put("url", mv.getUrl()==null?"":mv.getUrl());
			m.put("pid", mv.getParent_id()==null?"":mv.getParent_id());
			m.put("type", "0");
			m.put("order", mv.getOrder()==null?"":mv.getOrder());
			m.put("menu", null);
			if(mv.getLevel().equals("1")){	
				li1.put(mv.getId(), m);
			}else if(mv.getLevel().equals("2")){
				li2.put(mv.getId(), m);
			}else if(mv.getLevel().equals("3")){
				li3.put(mv.getId(), m);
			}
		}
		//3.2.循环三级菜单
		for (Map.Entry<String, Object> entry : li3.entrySet()) {
			//取出父级id
			HashMap<String,Object> m = (HashMap<String,Object>)entry.getValue();
			String pid = (String)m.get("pid");
			//找出id的menu
			HashMap<String,Object> mp = (HashMap<String,Object>)li2.get(pid);
			if(mp!=null){
				String type = (String)mp.get("type");
				if(type.equals("0")){
					ArrayList<HashMap<String,Object>> al = new ArrayList<HashMap<String,Object>>();
					al.add(m);
					mp.put("menu", al);
					mp.put("type", "1");
				}else{
					ArrayList<HashMap<String,Object>> al = (ArrayList<HashMap<String,Object>>)mp.get("menu");
					al.add(m);
				}
			}
		}
		//3.3.循环二级菜单
		for (Map.Entry<String, Object> entry : li2.entrySet()) {
			//取出父级id
			HashMap<String,Object> m = (HashMap<String,Object>)entry.getValue();
			String pid = (String)m.get("pid");
			//找出id的menu
			HashMap<String,Object> mp = (HashMap<String,Object>)li1.get(pid);
			if(mp!=null){
				String type = (String)mp.get("type");
				if(type.equals("0")){
					ArrayList<HashMap<String,Object>> al = new ArrayList<HashMap<String,Object>>();
					al.add(m);
					mp.put("menu", al);
					mp.put("type", "1");
				}else{
					ArrayList<HashMap<String,Object>> al = (ArrayList<HashMap<String,Object>>)mp.get("menu");
					al.add(m);
				}
			}
		}
		//3.4.循环一级菜单
		ArrayList<HashMap<String,Object>> al = new ArrayList<HashMap<String,Object>>();
		for (Map.Entry<String, Object> entry : li1.entrySet()) {
			HashMap<String,Object> m = (HashMap<String,Object>)entry.getValue();
			al.add(m);	
		}	
		//4.写入返回数据
		AjaxReturnInfo rtn = AjaxReturnInfo.success("content");
		rtn.add("menu",al);
		resultMap=rtn.getReturnMap();
		log.info("菜单:"+resultMap.toString());
		return "json";
	}
	/**
	 * 跳转欢迎界面
	 */
	public String toWelcomePage(){
		return "toWelcomePage";
	}
	
	public String forgetPassword() throws Exception{
		log.info("找回密码的用户信息："+user.getUsername()+"-"+user.getEmail());
		String checkUsername=user.getUsername();
		String checkEmail=user.getEmail();
		user=userService.queryUserInfo(user);
		AjaxReturnInfo rtn = null;
		if(user==null){
			rtn = AjaxReturnInfo.success("1");//用户名不存在
		}else{
			if(checkEmail.equals(user.getEmail())){
				//发送邮件
				rtn = AjaxReturnInfo.success("0");//成功
				String [] to={checkEmail};
				String [] bcc={"seven.sun@clps.com.cn"};
				Map<String,Object> modelMap=new HashMap<String,Object>();
				modelMap.put("userName", checkUsername);
				modelMap.put("passWord", SecurityHelper.DESDecrypt(user.getPassword()));
				MailSenderUtil.sendTemplateEmail(to, null, bcc, "TMP系统邮件-找回密码", "template_getBackPassMail", modelMap, null);
			}else{
				rtn = AjaxReturnInfo.success("2");//邮箱不符
			}
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
}
