package com.zuk.core.util;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {

	private static ApplicationContext context;

	public static ApplicationContext getContext() {
		return ApplicationContextUtils.context;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T getBean(final Class<T> c) {
		final Map<String, T> beanMap = (Map<String, T>) getContext().getBeansOfType((Class) c);
		if (beanMap != null && !beanMap.isEmpty()) {
			return (T) getContext().getBean((Class) c);
		}
		return null;
	}

	public static Object getBean(final String name) {
		return getContext().getBean(name);
	}

	public void setApplicationContext(final ApplicationContext context) throws BeansException {
		ApplicationContextUtils.context = context;
	}

}
