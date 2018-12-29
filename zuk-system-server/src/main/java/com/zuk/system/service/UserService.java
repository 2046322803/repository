package com.zuk.system.service;

import com.alibaba.fastjson.JSONObject;

public interface UserService {

	void init();

	JSONObject login(JSONObject reqMsg);

	JSONObject logout(JSONObject reqMsg);

	JSONObject save(JSONObject reqMsg);
	
}
