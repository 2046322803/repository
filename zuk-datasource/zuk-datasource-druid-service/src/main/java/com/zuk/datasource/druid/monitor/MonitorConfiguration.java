package com.zuk.datasource.druid.monitor;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnProperty(name = "datasource.druid.monitor", havingValue = "true")
public class MonitorConfiguration {

	@Value("${datasource.druid.monitor.username:}")
	private String username;

	@Value("${datasource.druid.monitor.password:}")
	private String password;

	@Value("${datasource.druid.monitor.allow:}")
	private String allow;

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		Map<String, String> map = new HashMap<String, String>();
		map.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
		// 添加过滤规则.
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setInitParameters(map);
		return filterRegistrationBean;
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		Map<String, String> map = new HashMap<>();
		if (StringUtils.hasLength(allow)) {
			map.put("allow", allow);
		}
		if (StringUtils.hasLength(username)) {
			map.put("loginUsername", username);
		}
		if (StringUtils.hasLength(password)) {
			map.put("loginPassword", password);
		}
		map.put("resetEnable", "false");
		servletRegistrationBean.setInitParameters(map);
		return servletRegistrationBean;
	}
}
