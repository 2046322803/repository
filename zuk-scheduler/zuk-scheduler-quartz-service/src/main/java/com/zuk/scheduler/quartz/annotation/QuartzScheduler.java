package com.zuk.scheduler.quartz.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.quartz.Scheduler;
import org.springframework.stereotype.Component;

@Component
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QuartzScheduler {

	/**
	 * Job name string.
	 *
	 * @return the string
	 */
	String taskName();

	/**
	 * Group string.
	 *
	 * @return the string
	 */
	String group() default Scheduler.DEFAULT_GROUP;

	/**
	 * 默认的cron
	 *
	 * @return the string
	 */
	String defaultCron();

}
