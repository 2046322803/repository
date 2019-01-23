package com.zuk.storage.cos.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import com.zuk.storage.cos.service.FileStorageService;
import com.zuk.storage.cos.service.impl.FileStorageServiceImpl;

@Configuration
@EnableConfigurationProperties(CosProperties.class)
public class CosAutoConfiguration {

	@Autowired
	private CosProperties properties;

	@Bean
	public COSCredentials cred() {
		return new BasicCOSCredentials(properties.getSecretId(), properties.getSecretKey());
	}

	@Bean
	public ClientConfig clientConfig() {
		return new ClientConfig(new Region(properties.getRegion()));
	}

	@Bean
	public COSClient cosClient(COSCredentials cred, ClientConfig clientConfig) {
		return new COSClient(cred, clientConfig);
	}

	@Bean
	public FileStorageService fileStorageService(COSCredentials cred, ClientConfig clientConfig, COSClient cosClient) {
		return new FileStorageServiceImpl(cred, clientConfig, cosClient, properties);
	}

}
