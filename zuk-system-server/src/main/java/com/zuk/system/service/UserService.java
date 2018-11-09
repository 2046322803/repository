package com.zuk.system.service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

public interface UserService {

	void init();

	JsonObject login(JSONObject reqMsg);

	JsonObject logout(JSONObject reqMsg);

}
