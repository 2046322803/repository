package com.zuk.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zuk.system.entity.Resource;

public interface ResourceDao extends JpaRepository<Resource, String>, JpaSpecificationExecutor<Resource> {

}
