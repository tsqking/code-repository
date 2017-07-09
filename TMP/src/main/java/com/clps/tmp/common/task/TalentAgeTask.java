package com.clps.tmp.common.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.clps.tmp.campusRecruit.other.talentPool.service.TalentPoolService;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.common.util.config.SpringContextUtil;

@Component("talentAgeTask")
public class TalentAgeTask extends BaseAction {
	/**
	 * 人才年龄每年自增一岁
	 */
	private static final long serialVersionUID = 73668538907125218L;

	@Scheduled(cron = "0  0  0  01  01  ? ")
	public void AddTalentAge() {
		TalentPoolService talentPoolService = (TalentPoolService) SpringContextUtil.getBean("talentPoolService");
		try {
			int b = talentPoolService.updateTalentAge();
			log.info(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
