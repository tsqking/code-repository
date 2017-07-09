/**
 * Project Name:campus_community
 * File Name:MainController.java
 * Package Name:com.clps.web.controller
 * Date:2017年3月22日上午11:47:04
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:MainController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月22日 上午11:47:04 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@Controller
public class MainController {
	@RequestMapping("/")
	public String to_default() {
		return "login";
	}

	@RequestMapping("forget")
	public String to_forget() {
		return "forget";
	}

	@RequestMapping("login")
	public String to_login() {
		return "login";
	}

	@RequestMapping("manage")
	public String to_manage() {
		return "manage";
	}

	@RequestMapping("market")
	public String to_market() {
		return "market";
	}

	@RequestMapping("register")
	public String to_register() {
		return "register";
	}

	@RequestMapping("self")
	public String to_self() {
		return "self";
	}

	@RequestMapping("update_pwd")
	public String to_update_pwd() {
		return "update_pwd";
	}

	@RequestMapping("zone")
	public String to_zone() {
		return "zone";
	}

	@RequestMapping("myfriend")
	public String to_myfriend() {
		return "myfriend";
	}

	@RequestMapping("black")
	public String to_black() {
		return "black";
	}

	@RequestMapping("activity")
	public String to_activity() {
		return "activity";
	}

	@RequestMapping("study")
	public String to_study() {
		return "study";
	}

}