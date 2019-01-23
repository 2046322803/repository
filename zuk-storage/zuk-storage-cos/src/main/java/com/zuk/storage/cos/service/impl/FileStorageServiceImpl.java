package com.zuk.storage.cos.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.internal.BucketNameUtils;
import com.qcloud.cos.internal.UrlComponentUtils;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.utils.UrlEncoderUtils;
import com.zuk.storage.cos.configuration.CosProperties;
import com.zuk.storage.cos.service.FileStorageService;

public class FileStorageServiceImpl implements FileStorageService {

	private static final Logger LOGGER;

	private String bucketName;
	private COSCredentials cred;
	private ClientConfig clientConfig;
	private COSClient cosClient;

	public FileStorageServiceImpl(COSCredentials cred, ClientConfig clientConfig, COSClient cosClient,
			CosProperties properties) {
		this.cred = cred;
		this.clientConfig = clientConfig;
		this.cosClient = cosClient;
		this.bucketName = properties.getBucketName();
	}

	@Override
	public String upload(final String key, final File file) {
		this.cosClient.putObject(this.bucketName, key, file);
		FileStorageServiceImpl.LOGGER.info((Object) ("Upload file by COS , KEY = " + key));
		return this.generateUrl(key);
	}

	@Override
	public String upload(final String key, final InputStream is, final long contentLength) {
		final ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(contentLength);
		this.cosClient.putObject(this.bucketName, key, is, metadata);
		FileStorageServiceImpl.LOGGER.info((Object) ("Upload file by COS , KEY = " + key));
		return this.generateUrl(key);
	}

	@Override
	public void delFile(final String key) {
		this.cosClient.deleteObject(this.bucketName, key);
	}

	@Override
	public String getUrl(final String key) {
		return this.generateUrl(key);
	}

	private String generateUrl(final String key) {
		final StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("https://").append(this.formatBucket(this.bucketName, this.cred.getCOSAppId()));
		String endpointSuffix = this.clientConfig.getEndPointSuffix();
		if (endpointSuffix == null) {
			endpointSuffix = String.format(".%s.myqcloud.com",
					this.formatRegion(this.clientConfig.getRegion().getRegionName()));
		}
		if (!endpointSuffix.startsWith(".")) {
			endpointSuffix = "." + endpointSuffix;
		}
		strBuilder.append(endpointSuffix).append(UrlEncoderUtils.encodeEscapeDelimiter(this.formatKey(key)));
		return strBuilder.toString();
	}

	private String formatKey(String key) {
		if (key == null) {
			return "/";
		}
		if (!key.startsWith("/")) {
			key = "/" + key;
		}
		return key;
	}

	private String formatRegion(final String regionName) throws CosClientException {
		UrlComponentUtils.validateRegionName(regionName);
		if (regionName.startsWith("cos.")) {
			return regionName;
		}
		if (regionName.equals("cn-east") || regionName.equals("cn-south") || regionName.equals("cn-north")
				|| regionName.equals("cn-south-2") || regionName.equals("cn-southwest") || regionName.equals("sg")) {
			return regionName;
		}
		return "cos." + regionName;
	}

	private String formatBucket(final String bucketName, final String appid) throws CosClientException {
		BucketNameUtils.validateBucketName(bucketName);
		if (appid == null) {
			final String parrtern = ".*-(125|100|20)[0-9]{3,}$";
			if (Pattern.matches(parrtern, bucketName)) {
				return bucketName;
			}
			throw new CosClientException(
					"please make sure bucket name must contain legal appid when appid is missing. example: music-1251122334");
		} else {
			final String appidSuffix = "-" + appid;
			if (bucketName.endsWith(appidSuffix)) {
				return bucketName;
			}
			return bucketName + appidSuffix;
		}
	}

	static {
		LOGGER = Logger.getLogger((Class<?>) FileStorageServiceImpl.class);
	}

}
