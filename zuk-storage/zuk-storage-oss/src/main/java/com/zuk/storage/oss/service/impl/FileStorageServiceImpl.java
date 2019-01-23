package com.zuk.storage.oss.service.impl;

import java.io.File;
import java.io.InputStream;

import com.aliyun.oss.OSSClient;
import com.zuk.storage.oss.configuration.OssProperties;
import com.zuk.storage.oss.service.FileStorageService;

public class FileStorageServiceImpl implements FileStorageService {

	private String endPoint;
	private String bucketName;
	private OSSClient ossClient;

	public FileStorageServiceImpl(OSSClient ossClient, OssProperties properties) {
		this.ossClient = ossClient;
		this.endPoint = properties.getEndPoint();
		this.bucketName = properties.getBucketName();
	}

	@Override
	public String upload(final String key, final File file) {
		this.ossClient.putObject(this.bucketName, key, file);
		return this.getUrl(key);
	}

	@Override
	public String upload(final String key, final InputStream is, final long contentLength) {
		this.ossClient.putObject(this.bucketName, key, is);
		return this.getUrl(key);
	}

	@Override
	public void delFile(final String key) {
		this.ossClient.deleteObject(this.bucketName, key);
	}

	@Override
	public String getUrl(final String key) {
		final StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("http://").append(this.bucketName).append(".")
				.append(this.endPoint.replaceFirst("http://", "").replaceFirst("https://", "")).append("/").append(key);
		return strBuilder.toString();
	}

}
