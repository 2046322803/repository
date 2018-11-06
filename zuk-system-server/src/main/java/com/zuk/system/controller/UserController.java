package com.zuk.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zuk.system.service.UserService;

@RestController
@RequestMapping("/w/system/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 登录
	 * 
	 * @param reqMsg
	 * @return
	 */
	@PostMapping("/login")
	public JSONPObject login(@RequestBody JSONPObject reqMsg) {
		return userService.login(reqMsg);
	}

	/**
	 * 登出
	 * 
	 * @param reqMsg
	 * @return
	 */
	@PostMapping("/logout")
	public JSONPObject logout(@RequestBody JSONPObject reqMsg) {
		return userService.logout(reqMsg);
	}

	/**
	 * 保存
	 * 
	 * @param reqMsg
	 * @return
	 */
	@PostMapping("/save")
	public JSONPObject save(@RequestBody JSONPObject reqMsg) {
		return userService.logout(reqMsg);
	}

}
