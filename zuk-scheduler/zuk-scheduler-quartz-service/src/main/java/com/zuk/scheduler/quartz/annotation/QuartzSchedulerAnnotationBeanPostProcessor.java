package com.zuk.scheduler.quartz.annotation;

import javax.annotation.Resource;

import org.quartz.Job;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.zuk.scheduler.quartz.util.QuartzManager;

@Component
public class QuartzSchedulerAnnotationBeanPostProcessor implements BeanPostProcessor {

	@Resource
	private QuartzManager quartzManager;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Class<?> targetClass = AopUtils.getTargetClass(bean);
		QuartzScheduler annotation = AnnotationUtils.findAnnotation(targetClass, QuartzScheduler.class);
		if (annotation != null) {
			quartzManager.addTask(annotation.taskName(), annotation.group(), annotation.defaultCron(),
					((Class<? extends Job>) targetClass));
		}
		return bean;
	}

}
