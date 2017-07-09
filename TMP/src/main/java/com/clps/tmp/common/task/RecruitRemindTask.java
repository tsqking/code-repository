package com.clps.tmp.common.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.clps.tmp.campusRecruit.univ.coop.service.CoopService;
import com.clps.tmp.campusRecruit.univ.coop.vo.CoopVo;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.MailSenderUtil;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.common.util.config.SpringContextUtil;

@Component("recruitRemindTask")
public class RecruitRemindTask extends BaseAction {
	/**
	 * 校园招聘合作提前一周邮件提醒
	 */
	private static final long serialVersionUID = 1L;
	@Scheduled(cron = "0 00 08 * * ? ")
	public void SelectRecruitInfo() {
		CoopService coopService = (CoopService) SpringContextUtil.getBean("coopService");
		Date today = new Date();
		String nowTime = DateTimeUtil.toDatabase(today).substring(0, 8);
		String targetTime = DateTimeUtil.addJustDate(nowTime, 7);
		String startTime = targetTime + " 000000000";
		String endTime = targetTime + " 235959999";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<CoopVo> list=null;
		CoopVo cvo = null;
		String [] bcc={"seven.sun@clps.com.cn"};
		// 获取邮箱
		try {
			list = coopService.getRecruitInfo(map);
			if (list.size() > 0) {
				List<String> l = new ArrayList<String>();
				for (int i = 0; i < list.size(); i++) {
					cvo = list.get(i);
					String email = cvo.getRecruit_email();
					l.add(email);
				}
				String[] to = new String[l.size()];
				to = l.toArray(to);
				// 发送邮件
				Map<String, Object> modelMap = new HashMap<String, Object>();
				modelMap.put("univ_name", cvo.getUniv_name());
				modelMap.put("username",cvo.getUpdate_user());
				modelMap.put("city", cvo.getCityName());
				modelMap.put("collegename",cvo.getCollege_name());
				String recruitTime=cvo.getRecruit_time();	
				modelMap.put("recruitTime",DateTimeUtil.databaseToSystem(recruitTime));
				MailSenderUtil.sendTemplateEmail(to, null, bcc, "校园招聘合作邮件提醒",
						"template_recruitMail", modelMap, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
