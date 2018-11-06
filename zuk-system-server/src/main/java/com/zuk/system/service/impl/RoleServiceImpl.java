package com.zuk.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zuk.system.dao.RoleDao;
import com.zuk.system.entity.Role;
import com.zuk.system.service.RoleService;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public void init() {
		long count = roleDao.count();
		if (0L == count) {
			Role role = new Role();
			role.setCode("system");
			role.setName("系统管理员");
			role.setDescription("初始化默认角色，不可删除！");
			roleDao.save(role);
		}
	}

}
