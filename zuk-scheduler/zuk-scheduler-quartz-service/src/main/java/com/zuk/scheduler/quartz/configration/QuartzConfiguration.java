package com.zuk.scheduler.quartz.configration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfiguration {

	private final static String configPath = "classpath:quartz.properties";

	private DataSource dataSource;

	@Value("${org.quartz.dataSource:false}")
	private Boolean useDateSource;

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setAutoStartup(true);
		if (useDateSource) {
			schedulerFactoryBean.setDataSource(dataSource);
		}
		DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
		org.springframework.core.io.Resource resource = defaultResourceLoader.getResource(configPath);
		if (resource.exists()) {
			schedulerFactoryBean.setConfigLocation(resource);
		}
		return schedulerFactoryBean;
	}

	@Autowired(required = false)
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
