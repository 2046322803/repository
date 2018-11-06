package com.zuk.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/*
 * @EnableEurekaClient与@EnableDiscoveryClient
 * 这个两个注解都可以实现服务发现的功能
 * 在spring cloud中discoveryservice有许多种实现（eureka、consul、zookeeper等等）
 * 
 * @EnableEurekaClient基于spring-cloud-netflix，服务采用eureka作为注册中心，使用场景较为单一。
 * 
 * @EnableDiscoveryClient基于spring-cloud-commons， 服务采用其他注册中心。
 */
@EnableEurekaClient
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
