package com.zuk.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zuk.system.entity.UserRole;

public interface UserRoleDao extends JpaRepository<UserRole, String>, JpaSpecificationExecutor<UserRole>{

}
