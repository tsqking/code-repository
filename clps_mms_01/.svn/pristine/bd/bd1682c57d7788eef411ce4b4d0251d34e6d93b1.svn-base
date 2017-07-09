/**
 * Project Name:jmail_test
 * File Name:JMailHelper.java
 * Package Name:jmail_test
 * Date:2016年10月25日上午10:50:58
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.util.jmail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.clps.mms.util.jmail.model.MailModel;



/**
 * ClassName:JMailHelper 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年10月25日 上午10:50:58 
 * @author   tony.tan  
 * 	 
 */
public class JMailHelper {
	private Properties props=null;
	private Session session=null;
	private MimeMessage message=null;
	private String MAILHOST="mail.smtp.host";
	private String MAILAUTH="mail.smtp.auth";
	private String host = "smtp.163.com";// 或者是 smtp.163(sina).com
	private String contentType="text/html;charset=utf-8";
		public JMailHelper() {
			
			super();
			// TODO Auto-generated constructor stub
			props = new Properties();
		}

		public void send(Authenticator auth,MailModel mailModel)  {
			try {
				props.put(MAILHOST, host);
				props.put(MAILAUTH, "true");
				session = Session.getDefaultInstance(props, auth);

				// 设置session,和邮件服务器进行通讯。
				message = new MimeMessage(session);
				message.setSubject(mailModel.getMail_subject()); // 设置邮件主题

				// 添加附件信息
				Multipart mp = new MimeMultipart();
				/*
				 * BodyPart mbp1 = new MimeBodyPart(); FileDataSource fds = new
				 * FileDataSource("d:\\测试.doc"); mbp1.setDataHandler(new
				 * DataHandler(fds)); //设置附件名称
				 * mbp1.setFileName(MimeUtility.encodeText("d:\\测试.doc", "utf-8",
				 * null)); mp.addBodyPart(mbp1);
				 */

				BodyPart mbp = new MimeBodyPart();
				mbp.setContent(mailModel.getMail_body(), contentType);
				mp.addBodyPart(mbp);

				message.setContent(mp);

				message.setSentDate(new Date()); // 设置邮件发送日期

				Address address = new InternetAddress(mailModel.getMail_from(), mailModel.getPersonalName());
				message.setFrom(address); // 设置邮件发送者的地址
				Address toAddress = new InternetAddress(mailModel.getMail_to()); // 设置邮件接收方的地址
				message.addRecipient(Message.RecipientType.TO, toAddress);
				Transport.send(message); // 发送邮件
				System.out.println("send success!");
			} catch (Exception ex) {
				// ex.printStackTrace();
				if (ex instanceof javax.mail.AuthenticationFailedException) {
					System.out.println("认证错误");
				}
				//throw new Exception(ex.getMessage());
			}
		}
		public static void main(String[] args) {
			String username = "tmbasama@163.com"; // 邮箱名称
			String password = "tan821856373"; // 邮箱密码
			String host = "smtp.163.com";// 或者是 smtp.163(sina).com
			String mail_to = "741713034@qq.com"; // 发送至 人邮箱

			String mail_from = "tmbasama@163.com"; // 发送人邮箱username

			String mail_subject = "测试"; // 邮箱主题

			String mail_body = "测试信息"; // 邮箱内容

			String personalName = "tmbasama"; // 发送人邮箱username
			//进行邮件服务器用户认证
			JMailAutherticator auth=new JMailAutherticator(username, password);
			JMailHelper mail=new JMailHelper();
			MailModel model=new MailModel(host, mail_to, mail_from, mail_subject, mail_body, personalName);
			try {
				mail.send(auth,model);
			} catch (Exception e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}

}

