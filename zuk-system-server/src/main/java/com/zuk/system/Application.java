package com.zuk.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
 * @EnableEurekaClient与@EnableDiscoveryClient
 * 这个两个注解都可以实现服务发现的功能
 * 在spring cloud中discoveryservice有许多种实现（eureka、consul、zookeeper等等）
 * 
 * @EnableEurekaClient基于spring-cloud-netflix，服务采用eureka作为注册中心，使用场景较为单一。
 * 
 * @EnableDiscoveryClient基于spring-cloud-commons， 服务采用其他注册中心。
 */
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EntityScan(basePackages = "com.zuk")
@EnableJpaRepositories(basePackages = "com.zuk")
@SpringBootApplication(scanBasePackages = "com.zuk")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
