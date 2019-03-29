package com.zuk.storage.mangodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zuk.storage.mangodb.domain.File;
 

/**
 * File 存储库.
 * 
 * @since 1.0.0 2017年3月28日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public interface FileRepository extends MongoRepository<File, String> {
}
