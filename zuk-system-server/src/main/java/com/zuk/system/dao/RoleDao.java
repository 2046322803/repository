package com.zuk.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zuk.system.entity.Role;

public interface RoleDao extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {

	Role findByCode(String code);

}
