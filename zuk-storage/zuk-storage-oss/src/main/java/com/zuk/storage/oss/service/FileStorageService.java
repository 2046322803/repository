package com.zuk.storage.oss.service;

import java.io.File;
import java.io.InputStream;

public interface FileStorageService {

	String upload(final String p0, final File p1);

	String upload(final String p0, final InputStream p1, final long p2);

	void delFile(final String p0);

	String getUrl(final String p0);

}
