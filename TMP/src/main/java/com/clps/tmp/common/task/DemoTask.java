package com.clps.tmp.common.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component("demoTask")
public class DemoTask {
	/**
	 * 测试任务，每5s执行一次
	 * 2016年3月18日 Seven
	 */
	@Scheduled(cron = "10 26 9 * * ? ")
	public void TaskTest1() {
		System.out.println("Task测试1.........");
	}
}
