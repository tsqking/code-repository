package com.clps.tmp.common.util;

import java.io.File;
import java.util.Map;
import java.util.ResourceBundle;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.context.ContextLoaderListener;

/**
 * 邮件发送工具类
 * @author Seven
 * 2016年3月16日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
public class MailSenderUtil {
	private static JavaMailSender mailSender;
	private static VelocityEngine velocityEngine;
	private static TaskExecutor taskExecutor;//Spring封装的异步执行器
	private static Logger log=Logger.getLogger(MailSenderUtil.class);
	private static String from=ResourceBundle.getBundle("config"+File.separator+"config").getString("mail.from"); ;//发件人(显示,有些邮件服务商需要与用户验证名一致)
	static{
		log.info("MailSenderUtil静态块");
		MailSenderUtil.mailSender=(JavaMailSender) ContextLoaderListener.getCurrentWebApplicationContext().getBean("mailSender");
		MailSenderUtil.velocityEngine=(VelocityEngine) ContextLoaderListener.getCurrentWebApplicationContext().getBean("velocityEngine");
		MailSenderUtil.taskExecutor=(TaskExecutor) ContextLoaderListener.getCurrentWebApplicationContext().getBean("taskExecutor");
	}
	/**
	 * 发送普通邮件
	 * @param to 收件人列表数组。不能为空。如 String [] to={"seven.sun@clps.com.cn"};
	 * @param cc 抄送人数组列表。可以为空，为空时传入NULL。
	 * @param bcc 密抄人数组列表。可以为空，为空时传入NULL。
	 * @param subject 邮件主题。不能为空。
	 * @param htmlText 邮件正文，可带格式。不能为空。如<br>&nbsp;&nbsp;&nbsp;String htmlText="Dear Seven,&lt;br>&lt;i>你好。</i>"
	 * @param attachFile 附件文件地址列表。可以为空，为空时传入NULL。
	 * <br><br>
	 * 2016年3月17日 Seven
	 */
	public static void sendEmail(String [] to,String [] cc,String [] bcc,String subject,String htmlText,String [] attachFile){
		if(null==to || to.length==0){//收件人不能为空
			log.info("发送普通邮件 Error-收件人为空");
			return;
		}
		if(null==subject || subject.length()==0){//主题不能为空
			log.info("发送普通邮件 Error-主题为空");
			return;
		}
		if(null==htmlText || htmlText.length()==0){//邮件内容不能为空
			log.info("发送普通邮件 Error-邮件内容为空");
			return;
		}
		if(to.length>5 || (attachFile!=null && attachFile.length!=0)){//收件人超过5个或者有附件的，使用异步方式发送
			log.info("普通邮件异步发送中...");
			sendEmailAync(to,cc,bcc,subject,htmlText,attachFile);
			log.info("普通邮件异步发送完毕");
		}else{
			log.info("普通邮件发送中...");
			sendEmailSync(to,cc,bcc,subject,htmlText,attachFile);
			log.info("普通邮件发送完毕");
		}
	}
	/**
	 * 发送模板邮件
	 * @param to 收件人列表数组。不能为空。如 String [] to={"seven.sun@clps.com.cn"};
	 * @param cc 抄送人数组列表。可以为空，为空时传入NULL。
	 * @param bcc 密抄人数组列表。可以为空，为空时传入NULL。
	 * @param subject 邮件主题。不能为空。
	 * @param modelName 模板文件名称。不能为空。如String modelName="template_mail";
	 * @param modelMap 模型数据。不能为空。如<br>&nbsp;&nbsp;&nbsp;Map&lt;String, Object> modelMap=new HashMap&lt;String,Object>();<br>&nbsp;&nbsp;&nbsp;modelMap.put("userName","Seven");
	 * @param attachFile 附件文件地址列表。可以为空，为空时传入NULL。
	 * <br><br>
	 * 2016年3月17日 Seven
	 */
	public static void sendTemplateEmail(String [] to,String [] cc,String [] bcc,String subject,String modelName,Map<String, Object> modelMap,String [] attachFile){
		if(null==to || to.length==0){//收件人不能为空
			log.info("发送模板邮件 Error-收件人为空");
			return;
		}
		if(null==subject || subject.length()==0){//主题不能为空
			log.info("发送模板邮件 Error-主题为空");
			return;
		}
		if(null==modelName || modelName.length()==0){//模型文件名不能为空
			log.info("发送模板邮件 Error-模型文件名为空");
			return;
		}
		if(modelMap==null){//模型数据不能为空
			log.info("发送模板邮件 Error-模型数据为空");
			return;
		}
		if(to.length>5 || (attachFile!=null && attachFile.length!=0)){//收件人超过5个或者有附件的，使用异步方式发送
			log.info("模板邮件异步发送中...");
			sendTemplateEmailAync(to,cc,bcc,subject,modelName,modelMap,attachFile);
			log.info("模板邮件异步发送完毕");
		}else{
			log.info("模板邮件发送中...");
			sendTemplateEmailSync(to,cc,bcc,subject,modelName,modelMap,attachFile);
			log.info("模板邮件发送完毕");
		}
	}
	/**
	 * 异步发送普通邮件
	 * 2016年3月18日 Seven
	 */
	private static void sendEmailAync(final String [] to,final String [] cc,final String [] bcc,final String subject,final String htmlText,final String [] attachFile){
		taskExecutor.execute(new Runnable() {
			public void run() {
				try {
					sendEmailSync(to,cc,bcc,subject,htmlText,attachFile);
				} catch (Exception e) {
					log.info(StringUtil.getStackTrace(e));
				}
			}
		});
	}
	
	/**
	 * 异步发送模板邮件
	 * 2016年3月18日 Seven
	 */
	private static void sendTemplateEmailAync(final String[] to, final String[] cc,final String[] bcc, final String subject, final String modelName,final Map<String, Object> modelMap, final String[] attachFile){
		taskExecutor.execute(new Runnable() {
			public void run() {
				try {
					sendTemplateEmailSync(to,cc,bcc,subject,modelName,modelMap,attachFile);
				} catch (Exception e) {
					log.info(StringUtil.getStackTrace(e));
				}
			}
		});
	}
	/**
	 * 发送普通邮件
	 * @param to 收件人列表数组。不能为空。如 String [] to={"seven.sun@clps.com.cn"};
	 * @param cc 抄送人数组列表。可以为空，为空时传入NULL。
	 * @param bcc 密抄人数组列表。可以为空，为空时传入NULL。
	 * @param subject 邮件主题。不能为空。
	 * @param htmlText 邮件正文，可带格式。不能为空。如<br>&nbsp;&nbsp;&nbsp;String htmlText="Dear Seven,&lt;br>&lt;i>你好。</i>"
	 * @param attachFile 附件文件地址列表。可以为空，为空时传入NULL。
	 * <br><br>
	 * 2016年3月17日 Seven
	 */
	private static void sendEmailSync(String [] to,String [] cc,String [] bcc,String subject,String htmlText,String [] attachFile){
		try{
			MimeMessage mimeMessage=mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
			//添加发件人
			helper.setFrom(from);
			//添加收件人
			helper.setTo(to);
			//添加抄送人
			if(cc!=null && cc.length!=0){
				helper.setCc(cc);
			}
			//添加秘抄人
			if(bcc!=null && bcc.length!=0){
				helper.setBcc(bcc);
			}
			//添加邮件标题
			helper.setSubject(subject);
			//添加邮件内容
			helper.setText("<html><body>"+htmlText+"<br><br><img src='cid:clps_logo'/></body></html>",true);
			//添加内联图片
			ClassPathResource image=new ClassPathResource("clps.email.jpg");
			helper.addInline("clps_logo", image);
			//添加附件
			if(attachFile!=null && attachFile.length!=0){
				for(int i=0;i<attachFile.length;i++){
					FileSystemResource file = new FileSystemResource(new File(attachFile[i]));
					helper.addAttachment(file.getFilename(), file);
				}
			}
			mailSender.send(mimeMessage);
		}catch(Exception e){
			log.info(StringUtil.getStackTrace(e));
		}
	}
	
	/**
	 * 发送模板邮件
	 * @param to 收件人列表数组。不能为空。如 String [] to={"seven.sun@clps.com.cn"};
	 * @param cc 抄送人数组列表。可以为空，为空时传入NULL。
	 * @param bcc 密抄人数组列表。可以为空，为空时传入NULL。
	 * @param subject 邮件主题。不能为空。
	 * @param modelName 模板文件名称。不能为空。如String modelName="template_mail";
	 * @param modelMap 模型数据。不能为空。如<br>&nbsp;&nbsp;&nbsp;Map&lt;String, Object> modelMap=new HashMap&lt;String,Object>();<br>&nbsp;&nbsp;&nbsp;modelMap.put("userName","Seven");
	 * @param attachFile 附件文件地址列表。可以为空，为空时传入NULL。
	 * <br><br>
	 * 2016年3月17日 Seven
	 */
	private static void sendTemplateEmailSync(String [] to,String [] cc,String [] bcc,String subject,String modelName,Map<String, Object> modelMap,String [] attachFile){
		try{
			MimeMessage mimeMessage=mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
			//添加发件人
			helper.setFrom(from);
			//添加收件人
			helper.setTo(to);
			//添加抄送人
			if(cc!=null && cc.length!=0){
				helper.setCc(cc);
			}
			//添加秘抄人
			if(bcc!=null && bcc.length!=0){
				helper.setBcc(bcc);
			}
			//添加邮件标题
			helper.setSubject(subject);
			//添加邮件内容
			String mailContentText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity"+File.separator+modelName+".vm", "UTF-8",modelMap);
			helper.setText(mailContentText, true);
			//添加内联图片
			ClassPathResource image=new ClassPathResource("clps.email.jpg");
			helper.addInline("clps_logo", image);
			//添加附件
			if(attachFile!=null && attachFile.length!=0){
				for(int i=0;i<attachFile.length;i++){
					FileSystemResource file = new FileSystemResource(new File(attachFile[i]));
					helper.addAttachment(file.getFilename(), file);
				}
			}
			mailSender.send(mimeMessage);
		}catch(Exception e){
			log.info(StringUtil.getStackTrace(e));
		}
	}
}
