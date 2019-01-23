package com.zuk.scheduler.task.util;

import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Component
public class DynamicTask {

	private ScheduledFuture<?> future;

	@Autowired
	private ThreadPoolTaskScheduler threadPoolTaskScheduler;

	@Bean
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		return new ThreadPoolTaskScheduler();
	}

	public void addTask(Runnable task, Trigger trigger) {
		future = threadPoolTaskScheduler.schedule(task, trigger);
	}

	public void stopTask() {
		if (future != null) {
			future.cancel(true);
		}
	}

}
