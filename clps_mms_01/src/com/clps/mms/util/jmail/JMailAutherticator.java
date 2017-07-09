/**
 * Project Name:jmail_test
 * File Name:JMailAutherticator.java
 * Package Name:jmail_test
 * Date:2016年10月25日上午11:30:41
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.util.jmail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * ClassName:JMailAutherticator 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年10月25日 上午11:30:41 
 * @author   tony.tan  
 * 	 
 */
public class JMailAutherticator extends Authenticator{
    private String username;
    private String password;
    
	public JMailAutherticator() {
		
		super();
		// TODO Auto-generated constructor stub
	}

	public JMailAutherticator(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
    	
    	// TODO Auto-generated method stub
    	return new PasswordAuthentication(username, password);
    }
}

