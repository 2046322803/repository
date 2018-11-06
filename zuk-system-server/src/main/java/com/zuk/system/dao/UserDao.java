package com.zuk.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zuk.system.entity.User;

public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

	User findByNameAndPassword(String name, String password);

}
