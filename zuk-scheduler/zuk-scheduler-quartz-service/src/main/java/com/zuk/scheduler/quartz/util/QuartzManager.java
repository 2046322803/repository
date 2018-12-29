package com.zuk.scheduler.quartz.util;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QuartzManager {

	private Logger logger = LoggerFactory.getLogger(QuartzManager.class);

	@Resource
	private Scheduler scheduler;

	/**
	 * Add job.
	 *
	 * @param jobName the job name
	 * @param cron    the defaultCron
	 * @param clazz   the clazz
	 */
	public void addTask(String jobName, String cron, Class<? extends Job> clazz) {
		addTask(jobName, Scheduler.DEFAULT_GROUP, cron, clazz);
	}

	/**
	 * Add job.
	 *
	 * @param jobName the job name
	 * @param group   the job group
	 * @param cron    the cron
	 * @param clazz   the clazz
	 */
	public void addTask(String jobName, String group, String cron, Class<? extends Job> clazz) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, group);
			CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (cronTrigger == null) {
				JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobName, group).build();
				cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, group)
						.withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
				scheduler.scheduleJob(jobDetail, cronTrigger);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Refresh job.
	 *
	 * @param taskName the job name
	 * @param cron     the defaultCron
	 */
	public void refreshTask(String taskName, String cron) {
		refreshTask(taskName, Scheduler.DEFAULT_GROUP, cron);
	}

	/**
	 * Refresh group job.
	 * 
	 * @param taskName the job name
	 * @param group    the job group
	 * @param cron     the defaultCron
	 */
	public void refreshTask(String taskName, String group, String cron) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(taskName, group);
			CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
			cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder)
					.build();
			scheduler.rescheduleJob(triggerKey, cronTrigger);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
