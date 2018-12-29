package com.zuk.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zuk.system.dao.RoleDao;
import com.zuk.system.dao.UserDao;
import com.zuk.system.dao.UserRoleDao;
import com.zuk.system.entity.User;
import com.zuk.system.entity.UserRole;
import com.zuk.system.entity.UserRolePK;
import com.zuk.system.service.UserService;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public void init() {
		long count = userDao.count();
		if (0L == count) {
			User user = new User();
			user.setName("admin");
			user.setPassword("123456");
			user = userDao.save(user);

			String userId = user.getId();
			String roleId = roleDao.findByCode("system").getId();

			UserRole userRole = new UserRole();
			UserRolePK pk = new UserRolePK();
			pk.setUserId(userId);
			pk.setRoleId(roleId);
			userRole.setPk(pk);

			userRoleDao.save(userRole);
		}
	}

	@Override
	public JSONObject login(JSONObject reqMsg) {
		JSONObject respMsg = new JSONObject();
		User user = userDao.findByNameAndPassword(reqMsg.getString("userName"), reqMsg.getString("password"));
		if (user == null) {
			respMsg.put("code", "1111");
			respMsg.put("message", "登录失败");
		} else {
			respMsg.put("code", "0000");
			respMsg.put("message", "登录成功");
		}
		return respMsg;
	}

	@Override
	public JSONObject logout(JSONObject reqMsg) {
		return null;
	}

	@Override
	public JSONObject save(JSONObject reqMsg) {
		JSONObject respMsg = new JSONObject();
		User user = new User();
		user.setName(reqMsg.getString("userName"));
		user.setPassword(reqMsg.getString("password"));
		userDao.save(user);

		return respMsg;
	}

}
