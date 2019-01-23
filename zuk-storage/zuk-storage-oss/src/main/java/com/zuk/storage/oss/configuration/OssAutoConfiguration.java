package com.zuk.storage.oss.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aliyun.oss.OSSClient;
import com.zuk.storage.oss.service.FileStorageService;
import com.zuk.storage.oss.service.impl.FileStorageServiceImpl;

@Configuration
@EnableConfigurationProperties(OssProperties.class)
public class OssAutoConfiguration {

	@Autowired
	private OssProperties properties;

	@Bean
	public OSSClient ossClient() {
		return new OSSClient(properties.getEndPoint(), properties.getAccessKeyId(), properties.getAccessKeySecret());
	}

	@Bean
	public FileStorageService fileStorageService(OSSClient ossClient) {
		return new FileStorageServiceImpl(ossClient, properties);
	}

}
