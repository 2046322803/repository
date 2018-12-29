package com.zuk.system.scheduling;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.zuk.core.util.ApplicationContextUtils;
import com.zuk.scheduler.quartz.annotation.QuartzScheduler;
import com.zuk.system.service.UserService;

@QuartzScheduler(taskName = "JobScheduling", defaultCron = "0 31 15 * * ?")
public class JobScheduling implements Job {

	@Autowired
	private ApplicationContextUtils applicationContextUtils;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		System.out.println("任务启动");
		@SuppressWarnings("static-access")
		UserService userService = applicationContextUtils.getBean(UserService.class);
		userService.logout(null);
	}

}
