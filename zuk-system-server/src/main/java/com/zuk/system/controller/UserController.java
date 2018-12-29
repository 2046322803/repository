package com.zuk.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zuk.system.service.UserService;

@RestController
@RequestMapping("/system/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 登录
	 * 
	 * @param reqMsg
	 * @return
	 */
	@PostMapping("/login")
	public JSONObject login(@RequestBody JSONObject reqMsg) {
		redisTemplate.opsForValue().set("123", "456");
		String accessToken = "Iind2rg0qCLXFBAvcO3Z3XQT5ir-NFAVRFoqBvcGEaTC5d2nuKKYC37u0bB87UzPUa2oLZ4rcYp_C6EuFkXcgu6yu24nGjqjMQlhnVUlTDXX2wYhQIB-2m_xJDyGKLtFOrbdhCYSVZM6GabbfpniJJPSLTGZuO3ONPes_A-BBuORTKwTPuLpsoI6gg1SR6aLBwNFzeW2SO8Qu5jCKVx-yg";
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=%s";
		JSONObject body = restTemplate.getForEntity(String.format(url, accessToken), JSONObject.class).getBody();
		int errcode = body.getIntValue("errcode");
		JSONArray departments = body.getJSONArray("department");
		System.out.println(departments.toJSONString());
		System.out.println(errcode);
		return userService.login(reqMsg);
	}

	/**
	 * 登出
	 * 
	 * @param reqMsg
	 * @return
	 */
	@PostMapping("/logout")
	public JSONObject logout(@RequestBody JSONObject reqMsg) {
		System.out.println(redisTemplate.opsForValue().get("123"));
		return userService.logout(reqMsg);
	}

	/**
	 * 保存
	 * 
	 * @param reqMsg
	 * @return
	 */
	@PostMapping("/save")
	public JSONObject save(@RequestBody JSONObject reqMsg) {
		return userService.save(reqMsg);
	}

}
