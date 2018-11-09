package com.zuk.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.zuk.system.service.UserService;

@RestController
@RequestMapping("/system/user")
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
	public JsonObject login(@RequestBody JSONObject reqMsg) {
		return userService.login(reqMsg);
	}

	/**
	 * 登出
	 * 
	 * @param reqMsg
	 * @return
	 */
	@PostMapping("/logout")
	public JsonObject logout(@RequestBody JSONObject reqMsg) {
		return userService.logout(reqMsg);
	}

	/**
	 * 保存
	 * 
	 * @param reqMsg
	 * @return
	 */
	@PostMapping("/save")
	public JsonObject save(@RequestBody JSONObject reqMsg) {
		return null;
	}

}
